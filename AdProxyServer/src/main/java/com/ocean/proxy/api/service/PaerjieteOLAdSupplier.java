package com.ocean.proxy.api.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.inveno.util.CollectionUtils;
import com.ocean.core.common.system.SystemContext;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
import com.ocean.core.common.threadpool.workthread.Task.DataFormat;
import com.ocean.core.common.threadpool.workthread.Task.InvokeType;
import com.ocean.persist.api.proxy.AdPullException;
import com.ocean.persist.api.proxy.paerjiate_online.PaerjiateOLAd;
import com.ocean.persist.api.proxy.paerjiate_online.PaerjiateOLAdPullParams;
import com.ocean.persist.api.proxy.paerjiate_online.PaerjiateOLAdPullResponse;
import com.ocean.persist.api.proxy.paerjiate_online.PaerjiateOLAdslot;
import com.ocean.persist.api.proxy.paerjiate_online.PaerjiateOLDevice;
import com.ocean.persist.api.proxy.paerjiate_online.PaerjiateOLGps;
import com.ocean.persist.api.proxy.paerjiate_online.PaerjiateOLMate;
import com.ocean.persist.api.proxy.paerjiate_online.PaerjiateOLNet;
import com.ocean.persist.api.proxy.paerjiate_online.PaerjiateOLReqInfo;
import com.ocean.persist.api.proxy.paerjiate_online.PaerjiateOLTracking;
import com.ocean.persist.common.ProxyConstants;
import com.ocean.persist.model.proxy.DSPPosition;
import com.ocean.proxy.api.base.AbstractAsynAdSupplier;
import com.ocean.proxy.serverdis.AdProxyPaerjiateOLTask;
import com.ocean.proxy.thrift.entity.AdContent;
import com.ocean.proxy.thrift.entity.AdImg;
import com.ocean.proxy.thrift.entity.AdMutiAction;
import com.ocean.proxy.thrift.entity.AdRecomReply;
import com.ocean.proxy.thrift.entity.AdRecomReq;
import com.ocean.proxy.thrift.entity.AdUserInfo;
import com.ocean.proxy.thrift.entity.UserAdSpaceAttri;


@Component(value = "PaerjieteOLAdSupplier")
public class PaerjieteOLAdSupplier extends AbstractAsynAdSupplier {
	private static final String PN_ABZMX="_ABZMX_";
	private static final String PN_ABZMY="_ABZMY_";
	private static final String PN_ABZCX="_ABZCX_";
	private static final String PN_ABZCY="_ABZCY_";

	private static final String PN_AZMX="_AZMX_";
	private static final String PN_AZMY="_AZMX_";
	private static final String PN_AZCX="_AZCX_";
	private static final String PN_AZCY="_AZCY_";
	
	private static final String PN_SBZMX="_SBZMX_";
	private static final String PN_SBZMY="_SBZMY_";
	private static final String PN_SBZCX="_SBZCX_";
	private static final String PN_SBZCY="_SBZCY_";
	
	private static final String PN_TIMESTAMP="_LOCALTIMESTAMP_";
	private static final String PN_DPNAME="_DPNAME_";
	private static final String PN_UA="_USERAGENT_";

	public AdRecomReply invoke(InvokeAttribute attribute) throws AdPullException {
		// 参数转换
		AdRecomReq adreq = attribute.getAdreq();
		PaerjiateOLAdPullParams params = parseParams(attribute);
		// 调用API
		PaerjiateOLAdPullResponse response = (PaerjiateOLAdPullResponse) invoke(params, PaerjiateOLAdPullResponse.class, adreq.getHash(), String.valueOf(adreq.getUserAdSpaceAttri().getAdSpaceId()),
				"joinDSP:paerjiate_online");
		// 解析结果
		return parseResult(response,adreq.getUserinfo().getUa());
	}

	public AdRecomReply parseResult(PaerjiateOLAdPullResponse resp,String ua) throws AdPullException {
		if (resp == null) {
			return null;
		}
		if (resp.getErrorCode()!=0) {
			throw new AdPullException("ad request error, return error code:"+resp.getErrorCode()+",error massage:"+resp.getErrMsg());
			
		}
		List<PaerjiateOLAd> bids = resp.getAds();
		if (CollectionUtils.isEmpty(bids)) {
			return null;
		}

		AdRecomReply adresp = new AdRecomReply();
		adresp.setStatus(status);
		adresp.setAd_contents(dealAds(bids,ua));
		return adresp;
	}
	private List<AdContent>  dealAds(List<PaerjiateOLAd> adDatas,String ua) throws AdPullException{
		List<AdContent> list=new ArrayList<AdContent> ();
		
		for(PaerjiateOLAd ad:adDatas){
			//***********************BEGIN 常规设置*************
			AdContent content = new AdContent();
			AdMutiAction action = new AdMutiAction();
			String title = defTitle;
			String marketTitle=defTitle;
			// 内容类型1表示链接推广,2表示应用推广,3未使用 4表示电话推广
	        int acType=ACTION_WEB;
	      //***********************END 常规设置*************
	        PaerjiateOLMate mate=ad.getMaterialMetas().get(0);
	        if(mate.getInteractionType()==2){
	        	acType=ACTION_APP;
	        }
	        
	        //图片
	        List<AdImg> imgs = new ArrayList<AdImg>();
	        if(CollectionUtils.isNotEmpty(mate.getImageSrcs())){
		         for(String url:mate.getImageSrcs()){
		            AdImg img=new AdImg();
		            img.setSrc(url);
		            imgs.add(img);
		            
		        }
		    }
	        if(CollectionUtils.isNotEmpty(mate.getIconSrcs())){

		        action.setCpIcon(mate.getIconSrcs().get(0));
	        }
	        
	        
	        if(StringUtils.isNotEmpty(mate.getTitle())){
	        	title=mate.getTitle();
	        }
	        String link=mate.getLandingUrl();
	        if(StringUtils.isNotEmpty(link)){
	        	String url=format(REPORT_TYPE_REQ,link,mate.getPackageName(),ua);
	        	content.setLinkurl(url);
				action.setLinkurl(url);
	        }
	        if(StringUtils.isNotEmpty(mate.getPackageName())){
	        	action.setCpPackage(mate.getPackageName());
	        }

	        List<PaerjiateOLTracking> trckings=ad.getAdTracking();
	        
			//上报
	        Map<String, List<String>> map = new HashMap<String, List<String>>();
			//***********************END 常规设置***************
	        for(PaerjiateOLTracking tracking:trckings){
	        	if(tracking.getTrackingEventType()==1){
	    	        if(CollectionUtils.isNotEmpty(tracking.getTrackingUrls())){
	    	        	List<String> report=new ArrayList<String>();
	    	        	for(String url:tracking.getTrackingUrls()){
	    	        		
	    	        		report.add(format(REPORT_TYPE_PV,url,mate.getPackageName(),ua));
	    	        	}
	    	        	map.put(SHOW, report);
	    	        }
	        	}else if(tracking.getTrackingEventType()==0){
	    	        if(CollectionUtils.isNotEmpty(tracking.getTrackingUrls())){
	    	        	List<String> report=new ArrayList<String>();
	    	        	for(String url:tracking.getTrackingUrls()){
	    	        		report.add(format(REPORT_TYPE_CLICK,url,mate.getPackageName(),ua));
	    	        	}
	    	        	map.put(CLICK, report);
	    	        }
	        	}else if(tracking.getTrackingEventType()==1000){
	    	        if(CollectionUtils.isNotEmpty(tracking.getTrackingUrls())){
	    	        	List<String> report=new ArrayList<String>();
	    	        	for(String url:tracking.getTrackingUrls()){
	    	        		
	    	        		report.add(format(REPORT_TYPE_DOWNLOAD,url,mate.getPackageName(),ua));
	    	        	}
	    	        	map.put(START_DOWN, report);
	    	        }
	        	}else if(tracking.getTrackingEventType()==1001){
	    	        if(CollectionUtils.isNotEmpty(tracking.getTrackingUrls())){
	       	        	List<String> report=new ArrayList<String>();
	    	        	for(String url:tracking.getTrackingUrls()){
	    	        		
	    	        		report.add(format(REPORT_TYPE_DOWNLOAD,url,mate.getPackageName(),ua));
	    	        	}
	    	        	map.put(DOWNLOAD,report);
	    	        }
	        	}else if(tracking.getTrackingEventType()==1002){
	    	        if(CollectionUtils.isNotEmpty(tracking.getTrackingUrls())){
	       	        	List<String> report=new ArrayList<String>();
	    	        	for(String url:tracking.getTrackingUrls()){
	    	        		
	    	        		report.add(format(REPORT_TYPE_INSTALL,url,mate.getPackageName(),ua));
	    	        	}
	    	        	map.put(START_INSTALL, report);
	    	        }
	        	}else if(tracking.getTrackingEventType()==1003){
	    	        if(CollectionUtils.isNotEmpty(tracking.getTrackingUrls())){
	       	        	List<String> report=new ArrayList<String>();
	    	        	for(String url:tracking.getTrackingUrls()){
	    	        		
	    	        		report.add(format(REPORT_TYPE_INSTALL,url,mate.getPackageName(),ua));
	    	        	}
	    	        	map.put(INSTALL,report);
	    	        }
	        	}
	        	else if(tracking.getTrackingEventType()==10000){
	    	        if(CollectionUtils.isNotEmpty(tracking.getTrackingUrls())){
	       	        	List<String> report=new ArrayList<String>();
	    	        	for(String url:tracking.getTrackingUrls()){
	    	        		
	    	        		report.add(format(REPORT_TYPE_ACTIVE,url,mate.getPackageName(),ua));
	    	        	}
	    	        	map.put(ACTIVE, report);
	    	        }
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
	private  String format(int type,String str,String pkgName,String ua){
		if(StringUtils.isEmpty(pkgName)){
			pkgName="";
		}
		if(type!=REPORT_TYPE_PV){
			str=str.replaceAll(PN_ABZMX, MACRO_DOWN_X).replaceAll(PN_ABZMY, MACRO_DOWN_Y)
					.replaceAll(PN_ABZCX, MACRO_UP_X).replaceAll(PN_ABZCY,MACRO_UP_Y)
					
					.replaceAll(PN_AZMX, MACRO_DOWN_X).replaceAll(PN_AZMY, MACRO_DOWN_Y)
					.replaceAll(PN_AZCX, MACRO_UP_X).replaceAll(PN_AZCY,MACRO_UP_Y)
					
					.replaceAll(PN_SBZMX, MACRO_DOWN_X).replaceAll(PN_SBZMY, MACRO_DOWN_Y)
					.replaceAll(PN_SBZCX, MACRO_UP_X).replaceAll(PN_SBZCY,MACRO_UP_Y);
		}
		String format= str.replaceAll(PN_DPNAME, pkgName).replaceAll(PN_UA,ua)
				.replaceAll(PN_TIMESTAMP, String.valueOf(System.currentTimeMillis()));

		return format;
	}

	public PaerjiateOLAdPullParams parseParams(InvokeAttribute attribute) throws AdPullException {
		DSPPosition posInfo = attribute.getDspPosition();
		if (StringUtils.isEmpty(posInfo.getText2())||StringUtils.isEmpty(posInfo.getText3())) {
			throw new AdPullException(" token or pkg name is empty!");
		}

		AdRecomReq adreq = attribute.getAdreq();
		AdUserInfo  user=adreq.getUserinfo();
		UserAdSpaceAttri  attr=adreq.getUserAdSpaceAttri();
		PaerjiateOLAdPullParams param = new PaerjiateOLAdPullParams();
		PaerjiateOLReqInfo pr=new PaerjiateOLReqInfo();
		pr.setAccessToken(posInfo.getText2());
		pr.setAdSlotId(posInfo.getPos());
		param.setReqInfo(pr);
		
		PaerjiateOLAdslot slot=new PaerjiateOLAdslot();
		slot.setMimes("jpg,gif,icon,png");
		slot.setSlotHeight(attr.getSpaceHeight());
		slot.setSlotWidth(attr.getSpaceWidth());
		param.setAdSlotInfo(slot);
		
		PaerjiateOLDevice device =new PaerjiateOLDevice();
		device.setOsVersion(this.convOSVersion(user.getOsversion()));
		device.setAppVersion(adreq.getVersion());
		device.setMobileModel(user.getPhonemodel());
		device.setVendor(user.getBrand_name());
		device.setConnectionType(this.convertConnType(adreq.getNet()));
		device.setOperatorType(this.convertCarrier(user.getMobile()));
		String os = user.getOs();
		if(OS_ANDROID.equals(os)){
			device.setImei(user.getImei());
			device.setImsi(user.getImsi());
			device.setAndroidId(user.getAdid());
			device.setOsType(0);
		}else{
			device.setIdfa(user.getIdfa());
			device.setIdfv(user.getIdfa());
			device.setOsType(1);
		}
		device.setMac(user.getMac());
		device.setDeviceType(1);
		device.setScreenHeight(user.getDevice_height());
		device.setScreenWidth(user.getDevice_width());
		param.setMobileInfo(device);
		
		
		PaerjiateOLNet net=new PaerjiateOLNet();
		net.setIp(user.getClient_ip());
		net.setUa(user.getUa());
		net.setIpType(0);
		param.setNetworkInfo(net);
		if(StringUtils.isNotEmpty(user.getLat())&&StringUtils.isNotEmpty(user.getLon())){
			PaerjiateOLGps gps=new PaerjiateOLGps();
			gps.setCoordinateType(2);
			gps.setLng(Double.parseDouble(user.getLon()));
			gps.setLat(Double.parseDouble(user.getLat()));
			param.setCoordinateInfo(gps);
		}

		return param;
	}

	
	private Integer convertConnType(String net) {
		if ("wifi".equals(net)) {
			return 1;
		}
		if ("4g".equals(net)) {
			return 4;
		}
		if ("3g".equals(net)) {
			return 3;
		}
		if ("2g".equals(net)) {
			return 2;
		}
		if ("5g".equals(net)) {
			return 5;
		}
		return 100;
	}

	private Integer convertCarrier(String mobile) {
		if ("CMCC".equals(mobile)) {
			return 1;
		}
		if ("CUCC".equals(mobile)) {
			return 3;
		}
		if ("CTCC".equals(mobile)) {
			return 2;
		}
		return 99;

	}

	@Override
	public AsynAbstractTask packageTask(Parameter params, String hashCode) {
		AdProxyPaerjiateOLTask task = new AdProxyPaerjiateOLTask();
		PaerjiateOLAdPullParams param = (PaerjiateOLAdPullParams) params;

		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json;utf-8");
		headers.put("Accept-Encoding", "identity");
		//headers.put("Accept-Encoding", "gzip");
		task.setParam(param);
		task.setDataFormat(DataFormat.DATA_FORMAT_JSON);
		task.setInvokeType(InvokeType.INVOKE_TYPE_POST);
		task.setHashCode(hashCode);
		task.setUrl(SystemContext.getDynamicPropertyHandler().get(ProxyConstants.PAERJIATE_ONLINE_URL));
		task.setHeaders(headers);
		return task;
	}

}
