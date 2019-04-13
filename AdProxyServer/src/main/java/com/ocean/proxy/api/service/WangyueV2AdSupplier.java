package com.ocean.proxy.api.service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.inveno.util.CollectionUtils;
import com.ocean.core.common.JsonUtils;
import com.ocean.core.common.system.SystemContext;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
import com.ocean.core.common.threadpool.workthread.Task.DataFormat;
import com.ocean.core.common.threadpool.workthread.Task.InvokeType;
import com.ocean.persist.api.proxy.AdPullException;
import com.ocean.persist.api.proxy.wanyueV2.SignUtil;
import com.ocean.persist.api.proxy.wanyueV2.WanyuV2Creative;
import com.ocean.persist.api.proxy.wanyueV2.WanyueV2AdPullReq;
import com.ocean.persist.api.proxy.wanyueV2.WanyueV2AdPullResp;
import com.ocean.persist.api.proxy.wanyueV2.WanyueV2Adspace;
import com.ocean.persist.api.proxy.wanyueV2.WanyueV2App;
import com.ocean.persist.api.proxy.wanyueV2.WanyueV2Banner;
import com.ocean.persist.api.proxy.wanyueV2.WanyueV2BannerText;
import com.ocean.persist.api.proxy.wanyueV2.WanyueV2Bar;
import com.ocean.persist.api.proxy.wanyueV2.WanyueV2Data;
import com.ocean.persist.api.proxy.wanyueV2.WanyueV2Device;
import com.ocean.persist.api.proxy.wanyueV2.WanyueV2Interaction;
import com.ocean.persist.api.proxy.wanyueV2.WanyueV2Interstitial;
import com.ocean.persist.api.proxy.wanyueV2.WanyueV2Native;
import com.ocean.persist.api.proxy.wanyueV2.WanyueV2SignParam;
import com.ocean.persist.api.proxy.wanyueV2.WanyueV2Splash;
import com.ocean.persist.api.proxy.wanyueV2.WanyueV2Track;
import com.ocean.persist.common.ProxyConstants;
import com.ocean.persist.model.proxy.DSPPosition;
import com.ocean.proxy.api.base.AbstractAsynAdSupplier;
import com.ocean.proxy.serverdis.AdProxyWangyueV2Task;
import com.ocean.proxy.thrift.entity.AdContent;
import com.ocean.proxy.thrift.entity.AdImg;
import com.ocean.proxy.thrift.entity.AdMutiAction;
import com.ocean.proxy.thrift.entity.AdRecomReply;
import com.ocean.proxy.thrift.entity.AdRecomReq;
import com.ocean.proxy.thrift.entity.AdUserInfo;
import com.ocean.proxy.thrift.entity.UserAdSpaceAttri;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年10月27日 
      @version 1.0 
 */
@Component(value="WangyueV2AdSupplier")
public class WangyueV2AdSupplier   extends AbstractAsynAdSupplier {
	 private static final String REQUEST_CLICKID="__CLICKID__";
	 public static final String WY_MACRO_DOWN_X = "__DOWNX__";
	 public static final String WY_MACRO_DOWN_Y = "__DOWNY__";
	 public static final String WY_MACRO_UP_X = "__UPX__";
	 public static final String WY_MACRO_UP_Y = "__UPY__";
	 public static final String WY_MACRO_EVENT_TIME="__EVENT_TIME__";
	 public static final String WY_MACRO_WIDTH="__WIDTH__";
	 public static final String WY_MACRO_HEIGHT="__HEIGHT__";
	 public static final String WY_MACRO_LAT="__LAT__";
	 public static final String WY_MACRO_LON_="__LON__";
	 public static final String WY_MACRO_DURATION="__DURATION__";
	 
	 
	 public AdRecomReply invoke(InvokeAttribute attribute)
			throws AdPullException {
		// TODO Auto-generated method stub
		AdRecomReq adreq = attribute.getAdreq();
		DSPPosition positionInfo = attribute.getDspPosition();
		// 参数转换
		WanyueV2SignParam params = parseParams(adreq, positionInfo);
		// 调用API
		WanyueV2AdPullResp response = (WanyueV2AdPullResp) invoke(params,WanyueV2AdPullResp.class,adreq.getHash()
				,String.valueOf(adreq.getUserAdSpaceAttri().getAdSpaceId()));
		
		// 解析结果
		return parseResult(response, adreq.getUserinfo().getLat(),adreq.getUserinfo().getLon(),adreq.getUserAdSpaceAttri().getSpaceWidth(),adreq.getUserAdSpaceAttri().getSpaceHeight());
	}

	private WanyueV2SignParam parseParams(AdRecomReq adreq, 
			DSPPosition positionInfo) throws AdPullException {
		if(StringUtils.isEmpty(positionInfo.getText1())||StringUtils.isEmpty(positionInfo.getText2())||StringUtils.isEmpty(positionInfo.getText3())){
			throw  new AdPullException("appid 、sec key、pkg name is empty!");
		}
		AdUserInfo userInfo=adreq.getUserinfo();
		UserAdSpaceAttri  attr=adreq.getUserAdSpaceAttri();
		WanyueV2SignParam signParam=new WanyueV2SignParam();
		WanyueV2AdPullReq param =new WanyueV2AdPullReq();
		param.setAppid(positionInfo.getText1());
		param.setTimestamp((int)(System.currentTimeMillis()/1000));
		param.setRandnum((int)(Math.random()*1000000));
		param.setVersion("2.0.10");
		//device
		WanyueV2Device device=new WanyueV2Device();
		device.setBrand(userInfo.getBrand_name());
		device.setModel(userInfo.getPhonemodel());
		device.setImei(userInfo.getImei());
		device.setImsi(userInfo.getImsi());
		device.setMac(userInfo.getMac());
		device.setCarrier(getOp(userInfo.getMobile()));
		device.setNetwork(getNetwork(adreq.getNet()));
		device.setNetwork_type(getNetworkType(adreq.getNet()));//简直脑残
		device.setLanguage("cn");
		device.setClient_ip(userInfo.getClient_ip());
		device.setUser_agent(userInfo.getUa());
		device.setScreen_resolution(userInfo.getDevice_width()+"*"+userInfo.getDevice_height());
		device.setScreen_orientation(1);
		String density=userInfo.getDensity();
		if(StringUtils.isEmpty(density)||"0".equals(density)){
			density="401";
		}
		device.setScreen_density(Integer.parseInt(density));
		if(StringUtils.isNotEmpty(userInfo.getLat())&&StringUtils.isNotEmpty(userInfo.getLon())){
			
			device.setLatitude(Double.parseDouble(userInfo.getLat()));
			device.setLongitude(Double.parseDouble(userInfo.getLon()));
		}
		String os = userInfo.getOs();
		if(OS_ANDROID.equals(os)){
			device.setOs_type(1);
		}else if(OS_IOS.equals(os)){
			device.setIdfa(userInfo.getIdfa());
			device.setOs_type(2);
		}else{
			device.setOs_type(0);
		}
		device.setOs_version(userInfo.getOsversion());
		device.setOs_api_level(Integer.parseInt(userInfo.getOs_api_level()));
		device.setAndroid_id(userInfo.getAdid());
		if(userInfo.getLac()>0&&userInfo.getCid()>0){
			device.setLac(String.valueOf(userInfo.getLac()));
			device.setCid(String.valueOf(userInfo.getCid()));
		}
		param.setDevice(device);
		
		//app
		WanyueV2App app=new WanyueV2App();
		app.setApp_pkg_name(positionInfo.getText3());
		app.setApp_version(adreq.getVersion());
		param.setApp(app);
		
		//space
		WanyueV2Adspace space=new WanyueV2Adspace();
		space.setAd_space_id(Integer.parseInt(positionInfo.getPos()));
		space.setWidth(attr.getSpaceWidth());
		space.setHeight(attr.getSpaceHeight());
		space.setPage_index(0);
		space.setPage_size(adreq.getResult_num());
		
		param.setAdspace(Collections.singletonList(space));
		String jsonStr=JsonUtils.toJson(param);
		String sign=SignUtil.sign(jsonStr, positionInfo.getText2());
		
		logger.info("wangyue {} param {} after key :{} encrypt:{}",adreq.getHash(),jsonStr,positionInfo.getText2(),sign);
		signParam.setSign(sign);
		signParam.setParamJson(jsonStr);
		return signParam;
	}
	private  int getNetwork(String net){	
		if(NETWORK_WIFI.equals(net)){
			return 1;
		}else if(NETWORK_2G.equals(net)){
			return 2;
		}
		else if(NETWORK_3G.equals(net)){
			return 3;
		}
		else if(NETWORK_4G.equals(net)){
			return 4;
		}else if(NETWORK_5G.equals(net)){
			return 5;
		}
		return 0;
	}
	private  int getNetworkType(String net){	
		if(NETWORK_WIFI.equals(net)){
			return 2;
		}else if(NETWORK_2G.equals(net)){
			return 1;
		}
		else if(NETWORK_3G.equals(net)){
			return 1;
		}
		else if(NETWORK_4G.equals(net)){
			return 1;
		}else if(NETWORK_5G.equals(net)){
			return 1;
		}
		return 0;
	}
	private int getOp(String op){

		if(MOBILE_CMCC.equals(op)){
			return 1;
		}else if(MOBILE_CUCC.equals(op)){
			return 2;
		}else if(MOBILE_CTCC.equals(op)){
			return 3;
		}
		return 0;
	}
	private AdRecomReply parseResult(WanyueV2AdPullResp body, String lat,String lon,int width,int height)
			throws AdPullException {
		if(body == null||CollectionUtils.isEmpty(body.getData())){
			return null;
		}
		if(!"0".equals(body.getRc())){
			throw new AdPullException("ad request error, return error code:"+body.getRc()+" msg:"+body.getMsg());
			
		}
		AdRecomReply reply=new AdRecomReply();
		reply.setStatus(status);
		reply.setAd_contents(this.dealAds(body,  lat, lon, width, height));
		return reply;
	}
	private List<AdContent>  dealAds(WanyueV2AdPullResp resp, String lat,String lon,int width,int height){
		List<AdContent> list=new ArrayList<AdContent> ();
		WanyueV2Data data=resp.getData().get(0);
		for(WanyuV2Creative ad:data.getCreative()){
			//***********************BEGIN 常规设置*************
			AdContent content = new AdContent();
			AdMutiAction action = new AdMutiAction();
			String title = defTitle;
			String marketTitle=defTitle;
			// 内容类型1表示链接推广,2表示应用推广,3未使用 4表示电话推广
		 
	        int acType=ACTION_WEB;
	        //图片
	        List<AdImg> imgs = new ArrayList<AdImg>();
			//上报
	        Map<String, List<String>> map = new HashMap<String, List<String>>();
			//***********************END 常规设置***************	
	        if(ad.getSplash()!=null){
	        	WanyueV2Splash splash=ad.getSplash();
		        if(StringUtils.isNotEmpty(splash.getAssets_url())){

	    			AdImg img=new AdImg();
	    			img.setSrc(splash.getAssets_url());
	    			imgs.add(img);	
		
		        }
		        if(StringUtils.isNotEmpty(splash.getHtml_snippet())){
		        	content.setHtmlSnippet(splash.getHtml_snippet());
		        	content.setIsHtmlAd(true);
		        }
	        }else if(ad.getBanner()!=null){
	        	WanyueV2Banner banner=ad.getBanner();
		        if(StringUtils.isNotEmpty(banner.getAssets_url())){

	    			AdImg img=new AdImg();
	    			img.setSrc(banner.getAssets_url());
	    			imgs.add(img);	
		
		        }
		        if(StringUtils.isNotEmpty(banner.getHtml_snippet())){
		        	content.setHtmlSnippet(banner.getHtml_snippet());
		        	content.setIsHtmlAd(true);
		        }
	        }else if(ad.getBanner_text()!=null){
	        	WanyueV2BannerText bannerT=ad.getBanner_text();
		        if(StringUtils.isNotEmpty(bannerT.getAssets_url())){

	    			AdImg img=new AdImg();
	    			img.setSrc(bannerT.getAssets_url());
	    			imgs.add(img);	
		
		        }
		        if(StringUtils.isEmpty(bannerT.getTitle())){
		        	title=bannerT.getTitle();
		        }
	        }else if(ad.getInterstitial()!=null){
	        	WanyueV2Interstitial inter=ad.getInterstitial();
		        if(StringUtils.isNotEmpty(inter.getAssets_url())){

	    			AdImg img=new AdImg();
	    			img.setSrc(inter.getAssets_url());
	    			imgs.add(img);	
		
		        }    
		        if(StringUtils.isEmpty(inter.getTitle())){
		        	title=inter.getTitle();
		        }
		        if(StringUtils.isNotEmpty(inter.getHtml_snippet())){
		        	content.setHtmlSnippet(inter.getHtml_snippet());
		        	content.setIsHtmlAd(true);
		        }
	        }else if(ad.getStatus_bar()!=null){
	        	WanyueV2Bar bar=ad.getStatus_bar();
		        if(StringUtils.isNotEmpty(bar.getAssets_url())){

	    			AdImg img=new AdImg();
	    			img.setSrc(bar.getAssets_url());
	    			imgs.add(img);	
		
		        }   
			}
			else if(CollectionUtils.isNotEmpty(ad.get_native())){
				for(WanyueV2Native _native:ad.get_native()){
					if("1".equals(_native.getElement_type())){
						title=_native.getElement_value();
					}else if("2".equals(_native.getElement_type())){
		    			AdImg img=new AdImg();
		    			img.setSrc(_native.getElement_value());
		    			imgs.add(img);	
					}
				}
			}
	        if(CollectionUtils.isNotEmpty(ad.getInteraction())){
	        	for(WanyueV2Interaction inter:ad.getInteraction()){
	        		if(inter.getType()==1&&StringUtils.isNotEmpty(inter.getValue())){

                	    String url=format(REPORT_TYPE_REQ,inter.getValue(),  lat, lon, width, height);
        	        	content.setLinkurl(url);
        				action.setLinkurl(url);

	        	        
	        		}else 	if(inter.getType()==2&&StringUtils.isNotEmpty(inter.getValue())){
	        			String url=format(REPORT_TYPE_REQ,inter.getValue(),  lat, lon, width, height);
        	        	content.setLinkurl(url);
        				action.setLinkurl(url);
	        		}else 	if(inter.getType()==3&&StringUtils.isNotEmpty(inter.getValue())){
	        			action.setActiveUri(inter.getValue());
	        		}
	        	}
	        }

	        if(ad.getApp()!=null){
	        	if(StringUtils.isNotEmpty(ad.getApp().getPkg_name())){
	        		action.setCpPackage(ad.getApp().getPkg_name());
	        	}
	        	acType=ACTION_APP;
	        }
	        for(WanyueV2Track track:ad.getTrack()){
	 			
	 			if(track.getType()==1){
	 				List<String> showL=new ArrayList<String>();
	 				for(String url:track.getTrack_url()){
	 					showL.add(format(REPORT_TYPE_PV,url,  lat, lon, width, height));
	 				}
	 				 map.put(SHOW,showL);
	 			 }
	 			
	 			 
	 			if(track.getType()==2){
	 				List<String> clickL=new ArrayList<String>();
	 				//广点通下载广告的特殊处理
        			if("gdt".equals(ad.getAd_proc())&&CollectionUtils.isNotEmpty(track.getTrack_url())){
        				String url=format(REPORT_TYPE_REQ,track.getTrack_url().get(0),  lat, lon, width, height);
        	        	content.setLinkurl(url);
        				action.setLinkurl(url);
        	        	action.setLinkurl_type(1);
        	        
        	        }
	 				for(String url:track.getTrack_url()){
	 					
	 					clickL.add(format(REPORT_TYPE_CLICK,url,  lat, lon, width, height));
	 				}
	 				 map.put(CLICK, clickL);
	 			 }

	 			if(track.getType()==3){
	 				List<String> downSL=new ArrayList<String>();
	 				for(String url:track.getTrack_url()){
	 					downSL.add(format(REPORT_TYPE_DOWNLOAD_START,url,  lat, lon, width, height));
	 				}
	 				map.put(START_DOWN, downSL);
	 			 }
	 			if(track.getType()==4){
	 				List<String> downFL=new ArrayList<String>();
	 				for(String url:track.getTrack_url()){
	 					downFL.add(format(REPORT_TYPE_DOWNLOAD,url,  lat, lon, width, height));
	 				}
	 				map.put(DOWNLOAD, downFL);
	 			 }
	 			if(track.getType()==5){
	 				List<String> installS=new ArrayList<String>();
	 				for(String url:track.getTrack_url()){
	 					installS.add(format(REPORT_TYPE_INSTALL_START,url,  lat, lon, width, height));
	 				}
	 				map.put(START_INSTALL, installS);
	 			 }
	 			if(track.getType()==6){
	 				List<String> install=new ArrayList<String>();
	 				for(String url:track.getTrack_url()){
	 					install.add(format(REPORT_TYPE_INSTALL,url,  lat, lon, width, height));
	 				}
	 				map.put(INSTALL, install);
	 			 }
	 			List<String> active=new ArrayList<String>();
	 			if(track.getType()==7||track.getType()==10){
	 				
	 				for(String url:track.getTrack_url()){
	 					active.add(format(REPORT_TYPE_ACTIVE,url,  lat, lon, width, height));
	 				}
	 				
	 			 }
	 			map.put(ACTIVE, active);
	        }
	       
			
			content.setThirdReportLinks(map);
			//img
			content.setImglist(imgs);
		    action.setType(acType); 
			content.setMarketTitle(marketTitle);
			content.setGuideTitle(title);
			content.setMutiAction(Collections.singletonList(action));

			list.add(content);
		}
		return list;
	}
	private static String format(int type,String str, String lat,String lon,int width,int height){
		if(type!=REPORT_TYPE_PV){
			str= str.replaceAll(WY_MACRO_DOWN_X, MACRO_DOWN_X).replaceAll(WY_MACRO_DOWN_Y,MACRO_DOWN_Y)
					.replaceAll(WY_MACRO_UP_X, MACRO_UP_X).replaceAll(WY_MACRO_UP_Y, MACRO_UP_Y);
		}
		str=str.replaceAll(REQUEST_CLICKID, MACRO_CLICK_ID)
		       .replaceAll(WY_MACRO_EVENT_TIME, MACRO_EVENT_TIME_START)
		       .replaceAll(WY_MACRO_WIDTH, String.valueOf(width))
		       .replaceAll(WY_MACRO_HEIGHT, String.valueOf(height))
		       .replaceAll(WY_MACRO_LAT, lat)
		       .replaceAll(WY_MACRO_LON_, lon)
		       .replaceAll(WY_MACRO_DURATION, String.valueOf((int)(Math.random()*500)));
		       
				
		return str;
	}
	@Override
	public AsynAbstractTask packageTask(Parameter params, String hashCode) {
		// TODO Auto-generated method stub
		AdProxyWangyueV2Task task=new AdProxyWangyueV2Task();
		//heard
		Map<String, String> headers = new HashMap<String, String>(2);
		headers.put("Content-Type", "application/json");
		task.setHeaders(headers);
		WanyueV2SignParam param = (WanyueV2SignParam) params;
		

		task.setParam(param);
		task.setDataFormat(DataFormat.DATA_FORMAT_JSON);
		task.setInvokeType(InvokeType.INVOKE_TYPE_POST);
		task.setHashCode(hashCode);
		task.setUrl(SystemContext.getDynamicPropertyHandler().get(ProxyConstants.WANGYUE_URL));
		
		return task;
	}


}
