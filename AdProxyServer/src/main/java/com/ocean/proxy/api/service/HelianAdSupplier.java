package com.ocean.proxy.api.service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
import com.ocean.core.common.system.SystemContext;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
import com.ocean.persist.api.proxy.AdPullException;
import com.ocean.persist.api.proxy.diankai.DiankaiAd;
import com.ocean.persist.api.proxy.diankai.DiankaiAdPullResponse;
import com.ocean.persist.api.proxy.helian.HelianAd;
import com.ocean.persist.api.proxy.helian.HelianAdPullParams;
import com.ocean.persist.api.proxy.helian.HelianAdPullResponse;
import com.ocean.persist.api.proxy.helian.HelianAdPuller;
import com.ocean.persist.api.proxy.yincheng.YcmModel;
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
import com.ocean.proxy.thrift.entity.UserAdSpaceAttri;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年6月15日 
      @version 1.0 
 */
@Component(value="helianAdSupplier")
public class HelianAdSupplier  extends AbstractAdSupplier{
	protected static final Map<String, Integer> helianMobiles = new HashMap<String, Integer>(3);
	private static final Map<String, Integer> conn = new HashMap<String, Integer>();

	static{
		helianMobiles.put("CMCC", 1);
		helianMobiles.put("CUCC", 2);
		helianMobiles.put("CTCC", 3);
		
		conn.put(NETWORK_2G, 1);
		conn.put(NETWORK_3G, 2);
		conn.put(NETWORK_4G, 4);
		conn.put(NETWORK_WIFI, 3);
	}
	@Autowired
	private HelianAdPuller helianAdPulller;
	public AdRecomReply invoke(InvokeAttribute attribute)
			throws AdPullException {
		// TODO Auto-generated method stub
		AdRecomReq adreq = attribute.getAdreq();
		DSPPosition positionInfo = attribute.getDspPosition();
		// 参数转换
		HelianAdPullParams params = parseParams(adreq, positionInfo);
		// 调用API
		HelianAdPullResponse response = (HelianAdPullResponse)helianAdPulller.api(params,adreq.getHash(),String.valueOf(adreq.getUserAdSpaceAttri().getAdSpaceId()));
		// 解析结果
		return parseResult(response);
	}
	private AdRecomReply parseResult(HelianAdPullResponse response)
			throws AdPullException {
		
		if(response == null||CollectionUtils.isEmpty(response.getAds())){
			return null;
		}
		if(!"200".equals(response.getReturncode())){
			throw new AdPullException("ad request error,third dsp return code is :"+response.getReturncode()+" return msg:"+response.getMsg());
		}
		AdRecomReply reply=new AdRecomReply();
		reply.setStatus(status);
		reply.setAd_contents(this.dealAds(response));
		return reply;
	}
	private List<AdContent>  dealAds(HelianAdPullResponse resp){
		List<AdContent> list=new ArrayList<AdContent> ();
		for(HelianAd ad:resp.getAds()){
			//***********************BEGIN 常规设置*************
			AdContent content = new AdContent();
			AdMutiAction action = new AdMutiAction();
			String title = defTitle;
			// 内容类型1表示链接推广,2表示应用推广,3未使用 4表示电话推广
		 
	        int acType=ACTION_WEB;
			//***********************END 常规设置***************
	        //物料类型，2：图片广告、4：html广告
			if(ad.getAdmt()==1){
				content.setIsHtmlAd(true);
				content.setHtmlSnippet(ad.getAdhtml());
			}else{
				if(StringUtils.isNotEmpty(ad.getAdm())){
					List<AdImg> imgs = new ArrayList<AdImg>();
					AdImg img = new AdImg();
					img.setSrc(ad.getAdm());
					imgs.add(img);
					content.setImglist(imgs);
				}
				//广告交互类型，0:未知、1:打开网页、2:下载
				if(ad.getAdct()==2){
					 acType=ACTION_APP;
				}
				content.setLinkurl(ad.getUrl());
				action.setLinkurl(ad.getUrl());
				
				
				//上报
				Map<String, List<String>> map = new HashMap<String, List<String>>();
		

				if(CollectionUtils.isNotEmpty(ad.getImgtracking())){

					map.put(SHOW, ad.getImgtracking());
				}
				if(CollectionUtils.isNotEmpty(ad.getThclkurl())){

					map.put(CLICK,ad.getThclkurl());
				}
				if(CollectionUtils.isNotEmpty(ad.getDsurl())){
					acType=ACTION_APP;
					map.put(DOWNLOAD, ad.getDsurl());
				}
				if(CollectionUtils.isNotEmpty(ad.getIsurl())){
					acType=ACTION_APP;
					map.put(INSTALL, ad.getDsurl());
				}
				if(CollectionUtils.isNotEmpty(ad.getOurl())){
					acType=ACTION_APP;
					map.put(ACTIVE, ad.getOurl());
				}
				
				if(StringUtils.isNotEmpty(ad.getDisplaytitle())){
					title=ad.getDisplaytitle();
					
				}
				if(StringUtils.isNotEmpty(ad.getDisplaytext())){
					action.setGuideTitle(ad.getDisplaytext());
				}
				content.setThirdReportLinks(map);
			}
		    action.setType(acType); 
		
			content.setMarketTitle(title);
			content.setGuideTitle(title);
			content.setMutiAction(Collections.singletonList(action));
			list.add(content);
		}
		return list;
	}
	private HelianAdPullParams parseParams(AdRecomReq adreq, 
			DSPPosition positionInfo) throws AdPullException {
		AdUserInfo userInfo=adreq.getUserinfo();
		UserAdSpaceAttri attri=adreq.getUserAdSpaceAttri();
		if(StringUtils.isEmpty(positionInfo.getText2())){
		    throw new 	AdPullException("package name is empty!");
		}
		
		HelianAdPullParams param=new HelianAdPullParams();
		param.setAdid(positionInfo.getPos());
		param.setAdtype(conPosType(positionInfo.getPosType()));
		
		param.setWidth(attri.getSpaceWidth());
		param.setHeight(attri.getSpaceHeight());
		param.setDevicetype(1);
		String osCode = userInfo.getOs();
		// 如果是ios
	    int os = 0;
	    if(OS_IOS.equals(osCode)){
			// ios 必填信息
	    	param.setUuid(userInfo.getIdfa());
			os=1;

	    }else if(OS_ANDROID.equals(osCode)){
	    	param.setUuid(userInfo.getImei());

		}else{
            os=3;
		}
	    param.setOsv(this.convOSVersion(userInfo.getOsversion()));
	    try {
			param.setPkgname(URLDecoder.decode(positionInfo.getText2(),"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    param.setAppname(adreq.getApp());
	    param.setUa(URLEncoder.encode(userInfo.getUa()));
	    if(StringUtils.isNotEmpty(userInfo.getMac())&&!userInfo.getImei().equals(userInfo.getMac())){
		    param.setMac(this.converMac(userInfo.getMac()));
	    }else{
	    	 throw new 	AdPullException("mac is empty or illegal!"+userInfo.getMac());
	    }

	    param.setPw(attri.getSpaceWidth());
	    param.setPh(attri.getSpaceHeight());
	    if(StringUtils.isNotEmpty(userInfo.getMobile())){
	    	 Integer mobile=helianMobiles.get(userInfo.getMobile());
	    	 param.setCarrier(mobile==null?4:mobile); 
	    }else{
	    	 param.setCarrier(4); 
	    }
	    if(StringUtils.isNotEmpty(adreq.getNet())){
	    	Integer net=conn.get(adreq.getNet());
	    	param.setConn(net==null?5:net);
	    }else{
	    	param.setConn(5);
	    }
	    if(StringUtils.isNotEmpty(userInfo.getAdid())){
	    	 param.setAnid(userInfo.getAdid());
	    }
	    param.setIp(userInfo.getClient_ip());
	    if(StringUtils.isNotEmpty(userInfo.getDensity())){
	    	param.setDensity(userInfo.getDensity());
	    }
	    param.setModel(userInfo.getBrand_id());
	    param.setBrand(userInfo.getBrand_name());
	    param.setTrq(SystemContext.getDynamicPropertyHandler().getInt(ProxyConstants.HELIAN_IS_TEST,0));
	    if(StringUtils.isNotEmpty(userInfo.getImsi())){
	    	param.setImsi(userInfo.getImsi());
	    }
	    
		return param;
	}
	private int conPosType(int posType){
		try{
			AdSpaceType type=AdSpaceType.findByValue(posType);
			switch(type){
			    case BANNER:
			    	return 1;
			    case OPENING:
			    	return 2;
			    case INTERSTITIAL:
			    	return 4;
			    case INFOFLOW:
			    	return 7;
			    default:
			    	return 1;
			}
		}catch(Exception e){
			return 1;
		}
		
	}
	public HelianAdPuller getHelianAdPulller() {
		return helianAdPulller;
	}
	public void setHelianAdPulller(HelianAdPuller helianAdPulller) {
		this.helianAdPulller = helianAdPulller;
	}
}
