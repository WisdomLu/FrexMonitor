package com.ocean.proxy.api.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.inveno.util.CollectionUtils;
import com.ocean.core.common.security.SecurityEncode;
import com.ocean.core.common.system.SystemContext;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
import com.ocean.core.common.threadpool.workthread.Task.DataFormat;
import com.ocean.core.common.threadpool.workthread.Task.InvokeType;
import com.ocean.persist.api.proxy.AdPullException;
import com.ocean.persist.api.proxy.boclick.BoclickAdContent;
import com.ocean.persist.api.proxy.boclick.BoclickAdData;
import com.ocean.persist.api.proxy.borui.BoruiBidReq;
import com.ocean.persist.api.proxy.borui.BoruiBidReqAdSlot;
import com.ocean.persist.api.proxy.borui.BoruiBidReqApp;
import com.ocean.persist.api.proxy.borui.BoruiBidReqConfig;
import com.ocean.persist.api.proxy.borui.BoruiBidReqDevice;
import com.ocean.persist.api.proxy.borui.BoruiBidReqGeo;
import com.ocean.persist.api.proxy.borui.BoruiBidResp;
import com.ocean.persist.api.proxy.borui.BoruiBidRespBid;
import com.ocean.persist.api.proxy.borui.BoruiEnums;
import com.ocean.persist.api.proxy.borui.BoruiEnums.AdType;
import com.ocean.persist.api.proxy.borui.BoruiEnums.InteractionType;
import com.ocean.persist.api.proxy.woso.WosoAdResponse;
import com.ocean.persist.model.proxy.DSPPosition;
import com.ocean.proxy.api.base.AbstractAsynAdSupplier;
import com.ocean.proxy.serverdis.AdProxyBoruiTask;
import com.ocean.proxy.thrift.entity.AdContent;
import com.ocean.proxy.thrift.entity.AdImg;
import com.ocean.proxy.thrift.entity.AdMutiAction;
import com.ocean.proxy.thrift.entity.AdRecomReply;
import com.ocean.proxy.thrift.entity.AdRecomReq;
import com.ocean.proxy.thrift.entity.AdSpaceType;
import com.ocean.proxy.thrift.entity.AdUserInfo;
import com.ocean.proxy.thrift.entity.UserAdSpaceAttri;

@Component(value="BoruiAdSupplier")
public class BoruiAdSupplier extends AbstractAsynAdSupplier {
	
	private static final String PROP_KEY_API_VER = "borui.apiver";
	
	private static final String PROP_KEY_URL = "borui.url";
	
	private static final int RETURN_SUCCESS_CODE = 0;
	
	public AdRecomReply invoke(InvokeAttribute attribute) throws AdPullException {
		// 参数转换
		AdRecomReq adreq = attribute.getAdreq();
		BoruiBidReq params = parseParams(attribute);
		// 调用API
		BoruiBidResp response = (BoruiBidResp) invoke(params,WosoAdResponse.class,adreq.getHash(),String.valueOf(adreq.getUserAdSpaceAttri().getAdSpaceId()),"joinDSP:borui");
		// 解析结果
		return parseResult(response);
	}

	public AdRecomReply parseResult(BoruiBidResp resp) throws AdPullException {
		if(resp == null) {
			throw new AdPullException("borui return null");
		}
		if(resp.getError_code() != RETURN_SUCCESS_CODE) {
			throw new AdPullException("borui return error:" + resp.getError_code());
		}
		if(resp.getBids() == null || resp.getBids().isEmpty()) {
			return null;
		}
		
		AdRecomReply adresp = new AdRecomReply();
		adresp.setStatus(status);
		
		adresp.setAd_contents(dealAds(resp.getBids()));
		
		
		return adresp;
	}
	private List<AdContent>  dealAds(List<BoruiBidRespBid> adDatas){
		List<AdContent> list=new ArrayList<AdContent> ();
		
		for(BoruiBidRespBid bid:adDatas){
			AdContent content = new AdContent();
			AdMutiAction action = new AdMutiAction();
			content.setMutiAction(Collections.singletonList(action));
			
			//落地页
			action.setLinkurl(bid.getHref());
			content.setLinkurl(bid.getHref());
			
			//上报链接
			Map<String, List<String>> map = new HashMap<String, List<String>>();
			content.setThirdReportLinks(map);
			List<String> clickL=new ArrayList<String>();
			List<String> viewL=new ArrayList<String>();
			List<String> download=new ArrayList<String>();
			List<String> install=new ArrayList<String>();
			List<String> active = new ArrayList<String>();
			if(CollectionUtils.isNotEmpty(bid.getShow_url())){
				viewL.addAll(bid.getShow_url());
				for(int i=0; i<viewL.size(); ++i) {
					viewL.set(i, removePvEventMacro(viewL.get(i)));
				}
				map.put(SHOW, viewL);
			}
			
			if(CollectionUtils.isNotEmpty(bid.getClick_url())){
				clickL.addAll(bid.getClick_url());
			
			}
			map.put(CLICK, clickL);
			
			if(CollectionUtils.isNotEmpty(bid.getDownStart_url())){
				List<String> startDowns = new ArrayList<String>();
				startDowns.addAll(bid.getDownStart_url());
				map.put(START_DOWN, startDowns);
			}
			
			if(CollectionUtils.isNotEmpty(bid.getDown_url())){
				download.addAll(bid.getDown_url());
			}
			map.put(DOWNLOAD,download);
			
			if(CollectionUtils.isNotEmpty(bid.getInstallStart_url())){
				List<String> startInstalls = new ArrayList<String>();
				startInstalls.addAll(bid.getInstallStart_url());
				map.put(START_INSTALL, startInstalls);
			}
			
			if(CollectionUtils.isNotEmpty(bid.getInstall_url())){
				install.addAll(bid.getInstall_url());
			}
			map.put(INSTALL, install);
			
			if(CollectionUtils.isNotEmpty(bid.getActivate_url())){
				active.addAll(bid.getActivate_url());
			}
//			if(!active.isEmpty()){//替代宏
//				for(int i=0;i<active.size();i++){
//					active.set(i, checkClickId(active.get(i)));
//				}
//			}
			map.put(ACTIVE, active);
			
			//标题, 描述
			String title = StringUtils.isEmpty(bid.getTitle()) ? defTitle : bid.getTitle();
			String desc = bid.getDescription();
			content.setMarketTitle(title);
			String guideTitle = StringUtils.isEmpty(desc) ? title : desc;
			content.setGuideTitle(guideTitle);
			action.setGuideTitle(guideTitle);
			
			
			// 内容类型1表示链接推广,2表示应用推广,3未使用 4表示电话推广 TODO 当前不支持视频广告
			int actType=ACTION_WEB;
			if(InteractionType.DOWNLOAD.name().equals(bid.getInteraction_type())) {
				actType = ACTION_APP;
			}
			action.setType(actType); 

			//icon_src是指广告图标
			content.setCpIcon(bid.getIcon_src());
//			if(StringUtils.isNotEmpty(ad.getIcon_src())){
//				AdImg logo=new AdImg();
//				logo.setSrc(ad.getIcon_src());
//				content.setLogoImg(logo);
//			}
			
	        //图片
	        List<AdImg> imgs = new ArrayList<AdImg>();
	        content.setImglist(imgs);
			AdImg img=new AdImg();
			img.setSrc(bid.getImage_src());
			imgs.add(img);

			//APP广告
			if(actType == ACTION_APP) {
				action.setCpIcon(bid.getIcon_src());
				action.setCpMemo(bid.getDescription());
				action.setCpApk(bid.getHref());
				action.setCpPackage(bid.getApp_package());
				action.setCpApkSize(bid.getApp_size());
			}
			
			list.add(content);
		}
		return list;
	}
	private String removePvEventMacro(String url) {
		if(StringUtils.isEmpty(url)) {
			return url;
		}
		return url.replaceAll("%%WIN_PRICE%%", "0");
	}

	public BoruiBidReq parseParams(InvokeAttribute attribute) throws AdPullException {
		DSPPosition posInfo = attribute.getDspPosition();
		Integer sspId = null;
		Integer mediumId = null;
		Integer madsId = null;
		if(StringUtils.isEmpty(posInfo.getText3())) {
			throw  new AdPullException("borui app pkg is empty!");
		}
		try {
			sspId = Integer.valueOf(posInfo.getText2());
			mediumId = Integer.valueOf(posInfo.getText1());
			madsId = Integer.valueOf(posInfo.getPos());
		} catch(NumberFormatException e) {
			throw  new AdPullException("borui params is invalid!");
		}

		AdRecomReq adreq = attribute.getAdreq();
		BoruiBidReq boruireq = new BoruiBidReq();
		boruireq.setId(adreq.getHash());
		boruireq.setApi_version(SystemContext.getDynamicPropertyHandler().get(PROP_KEY_API_VER, "2.0.5"));
//		boruireq.setApi_version("2.0.5");
		//TODO 当前只支持1次请求1条广告
		boruireq.setAdcount(1);
		boruireq.setConfig(convertReqConfig(sspId, mediumId, madsId));
		boruireq.setApp(convertReqApp(adreq, posInfo));
		boruireq.setDevice(convertReqDevice(adreq));
		boruireq.setAdslot(convertReqAdslot(adreq));
		return boruireq;
	}

	private BoruiBidReqAdSlot convertReqAdslot(AdRecomReq adreq) {
		BoruiBidReqAdSlot adslot = new BoruiBidReqAdSlot();
		UserAdSpaceAttri attr = adreq.getUserAdSpaceAttri();
		adslot.setAdslot_width(attr.getSpaceWidth());
		adslot.setAdslot_height(attr.getSpaceHeight());
		//TODO 没有底价
		adslot.setBidfloor(null);
		adslot.setAdtype(convertReqAtType(attr.getSpaceType()));
		return adslot;
	}

	private String convertReqAtType(AdSpaceType spaceType) {
		if(spaceType == null) {
			return AdType.currency.name();
		}
		switch(spaceType) {
		case BANNER:
			return AdType.banner.name();
		case OPENING:
			return AdType.splash.name();
		case INTERSTITIAL:
			return AdType.ingame.name();
		case INFOFLOW:
			return AdType.feeds.name();
		case TEXTLINK:
			return AdType.currency.name();
		default:
			return AdType.currency.name();
		}
		//TODO 暂时没考虑到视频广告
	}

	private BoruiBidReqDevice convertReqDevice(AdRecomReq adreq) {
		AdUserInfo adrequser = adreq.getUserinfo();
		BoruiBidReqDevice boruidev = new BoruiBidReqDevice();
		boruidev.setDevicetype(BoruiEnums.DeviceType.PHONE.name());
		boruidev.setOstype(BoruiEnums.OsType.findByAdReq(adrequser.getOs()));
		boruidev.setOs_version(adrequser.getOsversion());
		boruidev.setVendor(adrequser.getBrand_name());
		boruidev.setModel(adrequser.getPhonemodel());
		boruidev.setSptype(BoruiEnums.SpType.findByAdReq(adrequser.getMobile()));
		String imei = adrequser.getImei();
		if(!StringUtils.isEmpty(imei) && imei.length() == 32) {
			//TODO 是否需要同时给
			boruidev.setImei(imei);
			boruidev.setImei_md5(imei);
		} else {
			boruidev.setImei(imei);
			boruidev.setImei_md5(SecurityEncode.md5Encode(imei));
		}
		boruidev.setMac(adrequser.getMac());
		boruidev.setAndroid_id(adrequser.getAaid());
		boruidev.setScreen_width(adrequser.getDevice_width());
		boruidev.setScreen_height(adrequser.getDevice_height());
		boruidev.setImsi(adrequser.getImsi());
		boruidev.setIp(adrequser.getClient_ip());
		boruidev.setConnectiontype(BoruiEnums.ConnectionType.findByAdReq(adreq.getNet()));
		boruidev.setGeo(convertReqGeo(adrequser));
		boruidev.setUa(adrequser.getUa());
		boruidev.setIdfa(adrequser.getIdfa());
		return boruidev;
	}

	private BoruiBidReqGeo convertReqGeo(AdUserInfo adrequser) {
		String lon = adrequser.getLon();
		String lat = adrequser.getLat();
		String city = adrequser.getCity();
		if(StringUtils.isEmpty(lon) && StringUtils.isEmpty(lat) && StringUtils.isEmpty(city)) {
			//直接返回空
			return null;
		}
		
		BoruiBidReqGeo boruigeo = new BoruiBidReqGeo();
		if(!StringUtils.isEmpty(lon)) {
			boruigeo.setLongitude(Double.valueOf(lon));
		}
		if(!StringUtils.isEmpty(lat)) {
			boruigeo.setLatitude(Double.valueOf(lat));
		}
		boruigeo.setCity(city);
		//省份字段暂时没有，暂时先填city字段 TODO
		boruigeo.setProvince(city);
		return boruigeo;
	}

	private BoruiBidReqApp convertReqApp(AdRecomReq adreq, DSPPosition posInfo) {
		BoruiBidReqApp boruiapp = new BoruiBidReqApp();
		boruiapp.setAppname(adreq.getApp());
		boruiapp.setApp_packagename(posInfo.getText3());
		//TODO app cat 暂时没有
		return boruiapp;
	}

	private BoruiBidReqConfig convertReqConfig(Integer sspId, Integer mediumId, Integer madsId) {
		BoruiBidReqConfig config = new BoruiBidReqConfig();
		config.setSsp_id(sspId);
		config.setMedium_id(mediumId);
		config.setMads_id(madsId);
		return config;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public AsynAbstractTask packageTask(Parameter params, String hashCode) {
		AdProxyBoruiTask task = new AdProxyBoruiTask();
		BoruiBidReq param = (BoruiBidReq) params;
		
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("GType", "GZIP");
		headers.put("SSP_ID", param.getConfig().getSsp_id() + "");
		headers.put("MediumID", param.getConfig().getMedium_id() + "");
		
		task.setParam(param);
		task.setDataFormat(DataFormat.DATA_FORMAT_STREAM);
		task.setInvokeType(InvokeType.INVOKE_TYPE_POST);
		task.setHashCode(hashCode);
		task.setUrl(SystemContext.getDynamicPropertyHandler().get(PROP_KEY_URL));
		task.setHeaders(headers);
		return task;
	}

}
