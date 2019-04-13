package com.ocean.proxy.api.service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inveno.util.CollectionUtils;
import com.ocean.persist.api.proxy.AdPullException;
import com.ocean.persist.api.proxy.shaibo.ShaiboAdImg;
import com.ocean.persist.api.proxy.shaibo.ShaiboAdImp;
import com.ocean.persist.api.proxy.shaibo.ShaiboAdPullParams;
import com.ocean.persist.api.proxy.shaibo.ShaiboAdPullResponse;
import com.ocean.persist.api.proxy.shaibo.ShaiboAdPuller;
import com.ocean.persist.api.proxy.shaibo.ShaiboAdVideo;
import com.ocean.persist.api.proxy.shaibo.ShaiboAdWord;
import com.ocean.persist.model.proxy.DSPPosition;
import com.ocean.proxy.api.base.AbstractAdSupplier;
import com.ocean.proxy.thrift.entity.AdContent;
import com.ocean.proxy.thrift.entity.AdImg;
import com.ocean.proxy.thrift.entity.AdMutiAction;
import com.ocean.proxy.thrift.entity.AdRecomReply;
import com.ocean.proxy.thrift.entity.AdRecomReq;
import com.ocean.proxy.thrift.entity.AdUserInfo;
import com.ocean.proxy.thrift.entity.AdVid;


/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年6月20日 
      @version 1.0 
 */
@Component(value="shaiboAdSupplier")
public class ShaiboAdSupplier    extends AbstractAdSupplier{
	private static final Map<String, Integer> conn = new HashMap<String, Integer>();

	static{
	
		conn.put(NETWORK_2G, 2);
		conn.put(NETWORK_3G, 3);
		conn.put(NETWORK_4G, 4);
		conn.put(NETWORK_WIFI, 1);
	}
	@Autowired
    private ShaiboAdPuller shaiboAdPuller;
	public AdRecomReply invoke(InvokeAttribute attribute)

				throws AdPullException {
			// TODO Auto-generated method stub
			AdRecomReq adreq = attribute.getAdreq();
			DSPPosition positionInfo = attribute.getDspPosition();
			// 参数转换
			ShaiboAdPullParams params = parseParams(adreq, positionInfo);
			// 调用API
			ShaiboAdPullResponse response = (ShaiboAdPullResponse)shaiboAdPuller.api(params,adreq.getHash(),String.valueOf(adreq.getUserAdSpaceAttri().getAdSpaceId()));
			// 解析结果
			return parseResult(response);
	}
	private ShaiboAdPullParams parseParams(AdRecomReq adreq, 
			DSPPosition positionInfo) throws AdPullException {
		
		AdUserInfo userInfo=adreq.getUserinfo();
		if(StringUtils.isEmpty(positionInfo.getText1())||StringUtils.isEmpty(positionInfo.getText2())){
		    throw new 	AdPullException("app id or app key is empty!");
		}
		
		ShaiboAdPullParams param=new ShaiboAdPullParams();
		param.setVersion("2.0");
		String time=String.valueOf(System.currentTimeMillis());
		param.setTime(time);
		
		String reqId=UUID.randomUUID().toString();
		param.setReqid(reqId.replaceAll("-", ""));
		String appId=positionInfo.getText1();
		param.setAppid(appId);
		
		param.setToken(DigestUtils.md5Hex(appId+positionInfo.getText2()+time));
		param.setAppver(adreq.getVersion());
		param.setAdspotid(positionInfo.getPos());
		param.setImpsize(adreq.getResult_num());
		param.setIp(userInfo.getClient_ip());
		param.setUa(userInfo.getUa());
		param.setSw(userInfo.getDevice_width());
		param.setSh(userInfo.getDevice_height());
		
		param.setMake(userInfo.getBrand_name());
		param.setModel(userInfo.getPhonemodel());
		String osCode = userInfo.getOs();
		// 如果是ios
	    int os = 0;
	    if(OS_IOS.equals(osCode)){
			// ios 必填信息
	    	if(StringUtils.isNotEmpty(userInfo.getIdfa())){
	    		param.setIdfa(userInfo.getIdfa());
	    	}
	    
			os=1;

	    }else if(OS_ANDROID.equals(osCode)){
			if(StringUtils.isNotEmpty(userInfo.getImei())){
				param.setImei(userInfo.getImei());
			}
			if(StringUtils.isNotEmpty(userInfo.getMac())){
				param.setMac(converMac(userInfo.getMac()));
			}
			if(StringUtils.isNotEmpty(userInfo.getAdid())){
				param.setAndroidid(userInfo.getAdid());
			}
	    	os=2;
		}
		param.setOs(os);
		param.setOsv(this.convOSVersion(userInfo.getOsversion()));
		String cattier=mobiles.get(userInfo.getMobile());
        param.setCarrier(StringUtils.isEmpty(cattier)?"46000":cattier);
        Integer net=conn.get(adreq.getNet());
        param.setNetwork(net==null?0:net);
		
		return param;
	}
	
	private AdRecomReply parseResult(ShaiboAdPullResponse response)
			throws AdPullException {
		
		if(response == null||CollectionUtils.isEmpty(response.getImp())){
			return null;
		}
		if(response.getCode()!=200){
			throw new AdPullException(response.getCode(),"ad request error,third dsp error code:"+response.getCode()+"msg :"+response.getMsg());
		}
		AdRecomReply reply=new AdRecomReply();
		reply.setStatus(status);
		reply.setAd_contents(this.dealAds(response));
		
		return reply;
	}
	private List<AdContent>  dealAds(ShaiboAdPullResponse resp){
		List<AdContent> list=new ArrayList<AdContent> ();
		for(ShaiboAdImp imp:resp.getImp()){
			//***********************BEGIN 常规设置*************
			AdContent content = new AdContent();
			AdMutiAction action = new AdMutiAction();
			String title = defTitle;
			String marketTitle= defTitle;
			// 内容类型1表示链接推广,2表示应用推广,3未使用 4表示电话推广
			 
	        int acType=ACTION_WEB;
			//***********************END 常规设置***************
	        List<AdImg> imgs = new ArrayList<AdImg>();
	        if(CollectionUtils.isNotEmpty(imp.getImage())){
	        	for(ShaiboAdImg sbImg:imp.getImage()){//图片类型,图标(101-199),截图(201-299),主图(301-399)。
					if(301<=sbImg.getType()&&sbImg.getType()<=399){
						AdImg img = new AdImg();
						img.setSrc(sbImg.getIurl());
						imgs.add(img);
					}
	        	}
	        }
	        //title
	        if(CollectionUtils.isNotEmpty(imp.getWord())){
	        	for(ShaiboAdWord sbWord:imp.getWord()){
	        		if(sbWord.getType()==1){
	        			title=sbWord.getText();
	        		}else if(sbWord.getType()==2){
	        			marketTitle=sbWord.getText();
	        		}
	        	}
	        }
	        if(StringUtils.isNotEmpty(imp.getLink())){
	        	content.setLinkurl(imp.getLink());
	        	action.setLinkurl(imp.getLink());
	        }
	        if(imp.getAction()==2){
	        	acType=ACTION_APP;
	        }
	        
	        if(StringUtils.isNotEmpty(imp.getHtmlstring())){
	        	content.setIsHtmlAd(true);
	        	content.setHtmlSnippet(imp.getHtmlstring());
	        }else if(CollectionUtils.isNotEmpty(imp.getVideo())){//video
	        	ShaiboAdVideo video=imp.getVideo().get(0);
	        	if(StringUtils.isNotEmpty(video.getVurl())){
	    			AdVid adVid=new AdVid();
	    			adVid.setSrc(video.getVurl());
	    			adVid.setDuration(video.getDuration());
	    			content.setAdVid(adVid);
	    			content.setLinkurl(video.getVurl());
	    			action.setLinkurl(video.getVurl());
	    			acType=ACTION_VIDEO;
	        	}

	        }

				
			//上报
			Map<String, List<String>> map = new HashMap<String, List<String>>();

			if(CollectionUtils.isNotEmpty(imp.getImptk())){

				map.put(SHOW, imp.getImptk());
			}
			if(CollectionUtils.isNotEmpty(imp.getClicktk())){

				map.put(CLICK,imp.getClicktk());
			}

			

	        
			//
			content.setThirdReportLinks(map);
	        content.setImglist(imgs);
		    action.setType(acType); 
			content.setGuideTitle(title);
			content.setMarketTitle(marketTitle);
			content.setMutiAction(Collections.singletonList(action));

			list.add(content);
		}
		return list;
		
	}
	public ShaiboAdPuller getShaiboAdPuller() {
		return shaiboAdPuller;
	}
	public void setShaiboAdPuller(ShaiboAdPuller shaiboAdPuller) {
		this.shaiboAdPuller = shaiboAdPuller;
	}

}
