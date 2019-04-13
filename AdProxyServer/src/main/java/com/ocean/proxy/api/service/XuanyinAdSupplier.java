package com.ocean.proxy.api.service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.inveno.util.CollectionUtils;
import com.ocean.core.common.system.SystemContext;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
import com.ocean.core.common.threadpool.workthread.Task.DataFormat;
import com.ocean.core.common.threadpool.workthread.Task.InvokeType;
import com.ocean.persist.api.proxy.AdPullException;
import com.ocean.persist.api.proxy.xuanyin.XuanyinAd;
import com.ocean.persist.api.proxy.xuanyin.XuanyinAdPullReq;
import com.ocean.persist.api.proxy.xuanyin.XuanyinAdPullResp;
import com.ocean.persist.api.proxy.xuanyin.XuanyinExtendTracking;
import com.ocean.persist.common.ProxyConstants;
import com.ocean.persist.model.proxy.DSPPosition;
import com.ocean.proxy.api.base.AbstractAsynAdSupplier;
import com.ocean.proxy.serverdis.AdProxyXuanyinTask;
import com.ocean.proxy.thrift.entity.AdContent;
import com.ocean.proxy.thrift.entity.AdImg;
import com.ocean.proxy.thrift.entity.AdMutiAction;
import com.ocean.proxy.thrift.entity.AdRecomReply;
import com.ocean.proxy.thrift.entity.AdRecomReq;
import com.ocean.proxy.thrift.entity.AdUserInfo;
import com.ocean.proxy.thrift.entity.UserAdSpaceAttri;


@Component(value = "XuanyinAdSupplier")
public class XuanyinAdSupplier extends AbstractAsynAdSupplier {
	private static final String XUANYIN_LON="\\$\\$LON\\$\\$";
	private static final String XUANYIN_LAT="\\$\\$LAT\\$\\$";
	
	private static final String XUANYIN_DOWN_X="\\$\\$DOWN_X\\$\\$";
	private static final String XUANYIN_DOWN_Y="\\$\\$DOWN_Y\\$\\$";
	
	private static final String XUANYIN_UP_X="\\$\\$UP_X\\$\\$";
	private static final String XUANYIN_UP_Y="\\$\\$UP_Y\\$\\$";

	public AdRecomReply invoke(InvokeAttribute attribute) throws AdPullException {
		// 参数转换
		AdRecomReq adreq = attribute.getAdreq();
		XuanyinAdPullReq params = parseParams(attribute);
		// 调用API
		XuanyinAdPullResp response = (XuanyinAdPullResp) invoke(params, XuanyinAdPullResp.class, adreq.getHash(), String.valueOf(adreq.getUserAdSpaceAttri().getAdSpaceId()));
		// 解析结果

		return parseResult(response,adreq.getUserinfo().getLon(),adreq.getUserinfo().getLat());
	}

	public AdRecomReply parseResult(XuanyinAdPullResp resp,String lon, String lat) throws AdPullException {
		if (resp == null||CollectionUtils.isEmpty(resp.getAds())) {
			return null;
		}
		if (resp.getCode()!=0) {
			throw new AdPullException("ad request error,error code"+resp.getCode()+", error massage:"+resp.getMsg());
			
		}

		
		AdRecomReply adresp = new AdRecomReply();
		adresp.setStatus(status);
		adresp.setAd_contents(dealAds(resp.getAds(),lon, lat));
		return adresp;
	}
	private List<AdContent>  dealAds(List<XuanyinAd> ads,String lon, String lat) throws AdPullException{
		List<AdContent> list=new ArrayList<AdContent> ();
		
		for(XuanyinAd ad:ads){
			//***********************BEGIN 常规设置*************
			AdContent content = new AdContent();
			AdMutiAction action = new AdMutiAction();
			String title = defTitle;
			String marketTitle=defTitle;
			// 内容类型1表示链接推广,2表示应用推广,3未使用 4表示电话推广
	        int acType=ACTION_WEB;
	      //***********************END 常规设置*************
	        //图片
	        List<AdImg> imgs = new ArrayList<AdImg>();
			//上报
	        Map<String, List<String>> map = new HashMap<String, List<String>>();
	        if(StringUtils.isNotEmpty(ad.getHtml())){
	        	content.setIsHtmlAd(true);
	        	content.setHtmlSnippet(ad.getHtml());
	        }else{
	      
		        if(ad.getClick_action()==2){
		        	acType=ACTION_APP;
		        }

		        if(StringUtils.isNotEmpty(ad.getBanner_imgsrc())){
		      
		            AdImg img=new AdImg();
		            img.setSrc(ad.getBanner_imgsrc());
		            imgs.add(img);
		
			    }else if(CollectionUtils.isNotEmpty(ad.getNative_imgs())){
		      
		            AdImg img=new AdImg();
		            img.setSrc(ad.getBanner_imgsrc());
		            imgs.add(img);
		
			    }
		        if(StringUtils.isNotEmpty(ad.getApp_icon())){
		          	action.setCpIcon(ad.getApp_icon());
		        }
		        else if(StringUtils.isNotEmpty(ad.getIcon())){
		        	action.setCpIcon(ad.getIcon());
		        }
		        if(imgs.isEmpty()&&StringUtils.isNotEmpty(action.getCpIcon())){//图标当图片
		        	AdImg img=new AdImg();
		            img.setSrc(action.getCpIcon());
		            imgs.add(img);
		        }
		        
		        String link=ad.getLink();
		        if(StringUtils.isNotEmpty(link)){
	        	    String url=format(REPORT_TYPE_REQ,link,lon, lat);
		        	content.setLinkurl(url);
					action.setLinkurl(url);

		        	
		        }
		        if(StringUtils.isNotEmpty(ad.getDeeplink())){
		        	action.setActiveUri(ad.getDeeplink());
		        }
		        if(StringUtils.isNotEmpty(ad.getTitle())){
		        	title=ad.getTitle();
		        }
		        if(StringUtils.isNotEmpty(ad.getDesc())){
		        	marketTitle=ad.getDesc();
		        	
		        }
		        if(StringUtils.isNotEmpty(ad.getPackage_name())){
		        	action.setCpPackage(ad.getPackage_name());
			        
		        }
		        

	        }
	        
	        //上报处理
	        List<String> showReport=new ArrayList<String>();

	        if(CollectionUtils.isNotEmpty(ad.getExposure_tracking())){
	        	for(String url:ad.getExposure_tracking()){
	        		showReport.add(format(REPORT_TYPE_PV,url,lon, lat));
	        	}
	        	
	        
	        }    	       
	     	map.put(SHOW, showReport);
	     	List<String> clickReport=new ArrayList<String>();
	        if(CollectionUtils.isNotEmpty(ad.getClick_tracking())){
	        	for(String url:ad.getClick_tracking()){
	        		clickReport.add(format(REPORT_TYPE_CLICK,url,lon, lat));
	        	}
	        	
	        	map.put(CLICK, clickReport);
	        }
	        if(ad.getExtend_tracking()!=null){
	        	XuanyinExtendTracking track=ad.getExtend_tracking();
        		if(CollectionUtils.isNotEmpty(track.getDeeplink_evoke())){
        			List<String> report=new ArrayList<String>();
        			for(String url:track.getDeeplink_evoke()){
        				report.add(format(REPORT_TYPE_ACTIVE,url,lon, lat));
        			}
        			map.put(ACTIVE, report);
        		}
        		if(CollectionUtils.isNotEmpty(track.getDownload_start())){
        			List<String> report=new ArrayList<String>();
        			for(String url:track.getDownload_start()){
        				report.add(format(REPORT_TYPE_DOWNLOAD,url,lon, lat));
        			}
        			map.put(START_DOWN, report);
        		}
        		if(CollectionUtils.isNotEmpty(track.getDownload_complete())){
        			List<String> report=new ArrayList<String>();
        			for(String url:track.getDownload_complete()){
        				report.add(format(REPORT_TYPE_DOWNLOAD,url,lon, lat));
        			}
        			map.put(DOWNLOAD, report);
        		}
        		if(CollectionUtils.isNotEmpty(track.getInstallation_start())){
        			List<String> report=new ArrayList<String>();
        			for(String url:track.getInstallation_start()){
        				report.add(format(REPORT_TYPE_INSTALL,url,lon, lat));
        			}
        			map.put(START_INSTALL, report);
        		}
        		if(CollectionUtils.isNotEmpty(track.getInstallation_complete())){
        			List<String> report=new ArrayList<String>();
        			for(String url:track.getInstallation_complete()){
        				report.add(format(REPORT_TYPE_INSTALL,url,lon, lat));
        			}
        			map.put(INSTALL, report);
        		}
	        	
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

	
	private  String format(int type,String str,	String lon, String lat){
		if(type!=REPORT_TYPE_PV){
			str=str.replaceAll(XUANYIN_DOWN_X, MACRO_DOWN_X).replaceAll(XUANYIN_DOWN_Y, MACRO_DOWN_Y)
					.replaceAll(XUANYIN_UP_X, MACRO_UP_X).replaceAll(XUANYIN_UP_Y,MACRO_UP_Y);
		}
		String format= str.replaceAll(XUANYIN_LON, lon).replaceAll(XUANYIN_LAT,lat);

		return format;
	}

	public XuanyinAdPullReq parseParams(InvokeAttribute attribute) throws AdPullException {
		DSPPosition posInfo = attribute.getDspPosition();
		if (StringUtils.isEmpty(posInfo.getText1())||StringUtils.isEmpty(posInfo.getText2())) {
			throw new AdPullException("app_id/key  is empty!");
		}

		AdRecomReq adreq = attribute.getAdreq();
		AdUserInfo  user=adreq.getUserinfo();
		UserAdSpaceAttri  attr=adreq.getUserAdSpaceAttri();
		XuanyinAdPullReq param = new XuanyinAdPullReq();
		param.setApp_id(posInfo.getText1());
		param.setSlot_id(posInfo.getPos());
		param.setTime(System.currentTimeMillis());
		param.setSign(DigestUtils.md5Hex(param.getApp_id()+param.getTime()+param.getSlot_id()+posInfo.getText2()).toUpperCase());
		param.setApp_ver(adreq.getVersion());
		param.setWidth(attr.getSpaceWidth());
		param.setHeight(attr.getSpaceHeight());
		param.setIp(user.getClient_ip());
		param.setNetwork(this.getNetwork(adreq.getNet()));
		param.setOperator(this.getOp(user.getMobile()));
		param.setUser_agent(user.getUa());
		param.setDevice_type(1);
		String os = user.getOs();
		if(OS_ANDROID.equals(os)){
			param.setOs(1);
			param.setOs_ver(user.getOsversion());
			param.setAndroid_id(user.getAdid());
			param.setMac(user.getMac());
		}else if(OS_IOS.equals(os)){
			param.setOs(2);
			param.setIdfa(user.getIdfa());
		}else{
			param.setOs(1);
		}
		param.setImei(user.getImei());
		param.setBrand(user.getBrand_name());
		param.setModel(user.getPhonemodel());
		if(StringUtils.isEmpty(user.getDensity())){
			param.setDensity(Double.parseDouble(user.getDensity()));
		}else{
			param.setDensity(2.0);
		}
		param.setScreen_height(user.getDevice_height());
		param.setScreen_width(user.getDevice_width());
		param.setScreen_orientation(1);
		param.setLanguage("zh-cn");
		if(StringUtils.isNotEmpty(user.getLat())&&StringUtils.isNotEmpty(user.getLon())){
			param.setGps_type(1);
			param.setLatitude(Float.parseFloat(user.getLat()));
			param.setLongitude(Float.parseFloat(user.getLon()));
		}
		
		
		return param;
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
	private int  getOp(String mobile){
		if(MOBILE_CMCC.equals(mobile)){
			return 46000;
		}else if(MOBILE_CUCC.equals(mobile)){
			return 46001;
		}else if(MOBILE_CTCC.equals(mobile)){
			return 46003;
		}
		return 0;
	}
	@Override
	public AsynAbstractTask packageTask(Parameter params, String hashCode) {
		AdProxyXuanyinTask task = new AdProxyXuanyinTask();
		XuanyinAdPullReq param = (XuanyinAdPullReq) params;

		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		task.setParam(param);
		task.setDataFormat(DataFormat.DATA_FORMAT_JSON);
		task.setInvokeType(InvokeType.INVOKE_TYPE_POST);
		task.setHashCode(hashCode);
		task.setUrl(SystemContext.getDynamicPropertyHandler().get(ProxyConstants.XUANYIN_URL));
		task.setHeaders(headers);
		return task;
	}
}
