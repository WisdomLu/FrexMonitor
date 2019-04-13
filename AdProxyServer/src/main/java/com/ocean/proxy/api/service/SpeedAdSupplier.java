package com.ocean.proxy.api.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inveno.util.CollectionUtils;
import com.ocean.core.common.system.ErrorCode;
import com.ocean.core.common.system.SystemContext;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.persist.api.proxy.AdPullException;
import com.ocean.persist.api.proxy.diankai.DiankaiAd;
import com.ocean.persist.api.proxy.diankai.DiankaiAdPullResponse;
import com.ocean.persist.api.proxy.speed.SpeedAd;
import com.ocean.persist.api.proxy.speed.SpeedAdPullParams;
import com.ocean.persist.api.proxy.speed.SpeedAdPuller;
import com.ocean.persist.api.proxy.speed.SpeedAdResponse;
import com.ocean.persist.api.proxy.speed.SpeedCreative;
import com.ocean.persist.api.proxy.speed.SpeedMedia;
import com.ocean.persist.common.ProxyConstants;
import com.ocean.persist.model.proxy.DSPPosition;
import com.ocean.proxy.api.base.AbstractAdSupplier;
import com.ocean.proxy.thrift.entity.AdContent;
import com.ocean.proxy.thrift.entity.AdImg;
import com.ocean.proxy.thrift.entity.AdMutiAction;
import com.ocean.proxy.thrift.entity.AdRecomReply;
import com.ocean.proxy.thrift.entity.AdRecomReq;
import com.ocean.proxy.thrift.entity.AdSpaceType;
import com.ocean.proxy.thrift.entity.AdUserInfo;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年4月21日 
      @version 1.0 
 */
@Component(value="speedAdSupplier")
public class SpeedAdSupplier    extends AbstractAdSupplier{
	@Autowired
	SpeedAdPuller speedAdPuller;
	protected static final Map<String, Integer> speedmobiles = new HashMap<String, Integer>(4);

	private static final Map<String, Integer> conn = new HashMap<String, Integer>();
	static{
		speedmobiles.put("CMCC", 1);
		speedmobiles.put("CUCC", 2);
		speedmobiles.put("CTCC", 3);
		
		conn.put(NETWORK_2G, 2);
		conn.put(NETWORK_3G, 3);
		conn.put(NETWORK_4G, 4);
		conn.put(NETWORK_WIFI, 1);
	}
	public AdRecomReply invoke(InvokeAttribute attribute)
			throws AdPullException {
		// 参数转换
		AdRecomReq adreq = attribute.getAdreq();
	    SpeedAdPullParams params = parseParams(attribute);
		// 调用API
	    SpeedAdResponse response = (SpeedAdResponse) speedAdPuller.api(params,adreq.getHash(),String.valueOf(adreq.getUserAdSpaceAttri().getAdSpaceId()));
		// 解析结果
		return parseResult(response);
	}
	private SpeedAdPullParams parseParams(InvokeAttribute attribute) throws AdPullException {
		AdRecomReq adreq = attribute.getAdreq();
		DSPPosition positionInfo = attribute.getDspPosition();
		SpeedAdPullParams param=new SpeedAdPullParams();
		String adSource=SystemContext.getDynamicPropertyHandler().get(ProxyConstants.SPEED_AD_SOURCE);
		if(StringUtils.isNotEmpty(adSource)){
			param.setSource(adSource);//广告来源
		}
		if(StringUtils.isEmpty(positionInfo.getText1())){
			throw new AdPullException("ad request parameter error:speed channel is empty!");
		}
		param.setChannel(positionInfo.getText1());//渠道号|appid
		//广告位信息
		param.setPid(positionInfo.getPos());//广告位id
		
		
		AdUserInfo user=adreq.getUserinfo();
		try {
			param.setModel(URLEncoder.encode(user.getPhonemodel(), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			param.setModel("unknown");
		}
		if(StringUtils.isNotEmpty(user.getBrand_name())){
			param.setVendor(user.getBrand_name());
		}else{
			throw new AdPullException(ErrorCode.PARAM_ERROR,"vendor param/brand name is empty!");
		}
		
		
		param.setWidth(user.getDevice_width());
		param.setHeight(user.getDevice_height());
	
		//手机信息
		param.setOsv(this.convOSVersion(user.getOsversion()));
		param.setAppver(adreq.getVersion());
		
		param.setSize("4.7");
		String osCode = user.getOs();
        int osType=1;//1-安卓，2-IOS
	    if(OS_IOS.equals(osCode)){
	    	osType=2;
			// ios 必填信息
	        param.setIdfa(user.getIdfa());
	    }else if(OS_ANDROID.equals(osCode)){
	    	param.setImei(user.getImei());
	    	if(StringUtils.isNotEmpty(user.getAdid())){
	    		param.setAndroidid(user.getAdid());
	    	}
	    	if(StringUtils.isNotEmpty(user.getAaid())){
	        	param.setAaid(user.getAaid());
	    	}
	
		}
		param.setOstype(osType);
		if(StringUtils.isNotEmpty(user.getMac())&&!user.getMac().equals(user.getImei())){
			param.setMac(user.getMac());
		}
		
		param.setIp(user.getClient_ip());
		if(StringUtils.isEmpty(adreq.getNet())){
			param.setNetype(0);
		}else{
			Integer net=conn.get(adreq.getNet());
			param.setNetype(net==null?0:net);
		}
	
		if(StringUtils.isEmpty(user.getMobile())){
			param.setCarrier(0);
		}else{
			Integer carr=speedmobiles.get(user.getMobile());
			param.setCarrier(carr==null?0:carr);
		}
		param.setApiver("1.1");
		if(StringUtils.isNotEmpty(user.getImsi())){
			param.setImsi(user.getImsi());
		}
		try {
			param.setUa(URLEncoder.encode(user.getUa(), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return param;
		
	}
	private AdRecomReply parseResult(SpeedAdResponse response)
			throws AdPullException {
		if(response == null||CollectionUtils.isEmpty(response.getAd())){
			return null;
		}
		if(response.getError_code()<0){
			
			throw new AdPullException("ad request error,return error code:"+response.getError_code()+",error massage:"+response.getMsg());
		}
		// 返回对象
		AdRecomReply recomReply = new AdRecomReply();
		recomReply.setStatus(status);
		recomReply.setAd_contents(this.dealAds(response));
		return recomReply;
	}
	private List<AdContent>  dealAds(SpeedAdResponse resp){
		List<AdContent> list=new ArrayList<AdContent> ();
		for(SpeedAd adResponse:resp.getAd()){
			SpeedCreative creative=adResponse.getCreative().get(0);

			//------------------BEGIN 常规设置-------------------
			AdContent content = new AdContent();
			AdMutiAction action = new AdMutiAction();
			String title = defTitle;
			// 内容类型1表示链接推广,2表示应用推广,3未使用 4表示电话推广
			action.setType(ACTION_WEB);  
		
			//------------------END 常规设置---------------------
	        //设置落地页
			SpeedMedia media=creative.getMedia();
			if(StringUtils.isNotEmpty(media.getTarget())){
				content.setLinkurl(media.getTarget());
				action.setLinkurl(media.getTarget());
			}
			if(media.getEvent()==2){//下载
				action.setType(ACTION_APP); 
			}else if(media.getEvent()==4){
				action.setType(ACTION_VIDEO); 
			}else if(media.getEvent()==3){
				action.setType(ACTION_CALL); 
				
			}
			
			//所有广告都是html片段
			if(StringUtils.isNotEmpty(media.getHtml())){
				content.setIsHtmlAd(true);
				content.setHtmlSnippet(media.getHtml());
			}else{
				List<AdImg> imgs = new ArrayList<AdImg>();
				if(StringUtils.isNotEmpty(media.getImage())){
					AdImg img = new AdImg();
					img.setSrc(media.getImage());
					imgs.add(img);
				}
				
				if(StringUtils.isNotEmpty(media.getIcon())){
					AdImg img = new AdImg();
					img.setSrc(media.getIcon());
					imgs.add(img);
				}
				if(StringUtils.isNotEmpty(media.getTitle())){
					title=media.getTitle();
				}
				
				// 图片地址
				content.setImglist(imgs);
			}

			
			Map<String, List<String>> map = new HashMap<String, List<String>>();
			// 点击监测
			List<String> clickL=new ArrayList<String>();
			List<String> viewL=new ArrayList<String>();
			List<String> download=new ArrayList<String>();
			List<String> install=new ArrayList<String>();
			/*
			 * 
			 * */
			if("GDT".equals(adResponse.getSource())&&media.getEvent()==2){
				action.setLinkurl_type(1);
			}
			
			if(!CollectionUtils.isEmpty(creative.getImpression())){
				viewL.addAll(creative.getImpression());
				map.put(SHOW, viewL);
			}
			if(!CollectionUtils.isEmpty(creative.getClick())){
				clickL.addAll(creative.getClick());
				map.put(CLICK, clickL);
			}
			if(creative.getTrack()!=null&&!CollectionUtils.isEmpty(creative.getTrack().getDownloadfinish())){
				download.addAll(creative.getTrack().getDownloadfinish());
				map.put(DOWNLOAD, download);
			}
			if(creative.getTrack()!=null&&!CollectionUtils.isEmpty(creative.getTrack().getInstallfinish())){
				install.addAll(creative.getTrack().getInstallfinish());
				map.put(INSTALL, install);
			}
			content.setThirdReportLinks(map);
			
			// 类型

	 	
			action.setGuideTitle(title);
			content.setMarketTitle(title);
			content.setGuideTitle(title);
			content.setMutiAction(Collections.singletonList(action));

			list.add(content);
		}
		return list;
		
	}
	public SpeedAdPuller getSpeedAdPuller() {
		return speedAdPuller;
	}

	public void setSpeedAdPuller(SpeedAdPuller speedAdPuller) {
		this.speedAdPuller = speedAdPuller;
	}

}
