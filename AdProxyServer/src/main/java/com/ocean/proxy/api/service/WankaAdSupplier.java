package com.ocean.proxy.api.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inveno.util.CollectionUtils;
import com.ocean.core.common.JsonUtils;
import com.ocean.core.common.system.SystemContext;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.persist.api.proxy.AdPullException;
import com.ocean.persist.api.proxy.JoinDSPEmu;
import com.ocean.persist.api.proxy.wanka.WKAdm;
import com.ocean.persist.api.proxy.wanka.WKAssetImg;
import com.ocean.persist.api.proxy.wanka.WKAssetTitle;
import com.ocean.persist.api.proxy.wanka.WKNativeAsset;
import com.ocean.persist.api.proxy.wanka.WKNativeRequest;
import com.ocean.persist.api.proxy.wanka.WKSSP.Ad;
import com.ocean.persist.api.proxy.wanka.WKSSP.AdSlot;
import com.ocean.persist.api.proxy.wanka.WKSSP.App;
import com.ocean.persist.api.proxy.wanka.WKSSP.Banner;
import com.ocean.persist.api.proxy.wanka.WKSSP.ConnectionType;
import com.ocean.persist.api.proxy.wanka.WKSSP.CreativeType;
import com.ocean.persist.api.proxy.wanka.WKSSP.Device;
import com.ocean.persist.api.proxy.wanka.WKSSP.DeviceType;
import com.ocean.persist.api.proxy.wanka.WKSSP.InteractionType;
import com.ocean.persist.api.proxy.wanka.WKSSP.Native;
import com.ocean.persist.api.proxy.wanka.WKSSP.OperatorType;
import com.ocean.persist.api.proxy.wanka.WKSSP.WKSSPRequest;
import com.ocean.persist.api.proxy.wanka.WKSSP.WKSSPResponse;
import com.ocean.persist.api.proxy.wanka.WankaAdPuller;
import com.ocean.persist.common.ProxyConstants;
import com.ocean.persist.model.proxy.DSPPosition;
import com.ocean.proxy.api.base.AbstractAdSupplier;
import com.ocean.proxy.api.helper.InvenoIdGenerator;
import com.ocean.proxy.thrift.entity.AdContent;
import com.ocean.proxy.thrift.entity.AdImg;
import com.ocean.proxy.thrift.entity.AdMutiAction;
import com.ocean.proxy.thrift.entity.AdRecomReply;
import com.ocean.proxy.thrift.entity.AdRecomReq;
import com.ocean.proxy.thrift.entity.AdSpaceType;
import com.ocean.proxy.thrift.entity.AdUserInfo;
import com.ocean.proxy.thrift.entity.UserAdSpaceAttri;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;

@Component(value="wankaAdSupplier")
public class WankaAdSupplier extends AbstractAdSupplier{	
	private static final String APP_DEFAULT_NAME= "coolshow";
	private static final Map<String, OperatorType> oper = new HashMap<String, OperatorType>();
    @Autowired
	private WankaAdPuller wankaAdPuller;
	private static final Map<InteractionType, Integer> types = new HashMap<InteractionType, Integer>();
	static{
		oper.put(MOBILE_CMCC, OperatorType.CHINA_MOBILE);
		oper.put(MOBILE_CUCC, OperatorType.CHINA_UNICOM);
		oper.put(MOBILE_CTCC, OperatorType.CHINA_TELECOM);
		
		types.put(InteractionType.SURFING, 1);
		types.put(InteractionType.DOWNLOAD, 2);
		types.put(InteractionType.NO_INTERACTION, 3);
	}
	
	public AdRecomReply invoke(InvokeAttribute attribute) throws AdPullException {
		
		AdRecomReq adreq = attribute.getAdreq();
		DSPPosition positionInfo = attribute.getDspPosition();
		
		UserAdSpaceAttri attri = adreq.getUserAdSpaceAttri();
		boolean isNative = attri.getSpaceType() == AdSpaceType.OPENING;
		// 参数转换
		WKSSPRequest params = parseParams(adreq, positionInfo);
		WKSSPResponse response = (WKSSPResponse) wankaAdPuller.api(params,adreq.getHash(),String.valueOf(adreq.getUserAdSpaceAttri().getAdSpaceId()));
		return parseResult(response, isNative);
	}
	
	private AdRecomReply parseResult(WKSSPResponse response, boolean isNative)
			throws AdPullException {
		
		if(response == null){
			return null;
		}
		if(response.getCode()!=0){
			throw new  AdPullException ("joinDSP:wk request error,return code:"+response.getCode()+",msg:"+response.getMsg());

		}
		Ad ad = response.getAds(0);
		if(ad == null){
			return null;
		}
		// 返回对象
		AdRecomReply recomReply = new AdRecomReply();
		recomReply.setStatus(status);
		//-----------------BEGIN 常规设置----------------
		AdContent content = new AdContent();
		AdMutiAction action = new AdMutiAction();
		WKAdm adm = JsonUtils.toBean(ad.getAdm(), WKAdm.class);

		Integer type = types.get(ad.getInteractionType());
		if(type == null){
			type = ACTION_WEB;
		}
		action.setType(type);
		List<AdImg> imgs = new ArrayList<AdImg>();
		
		if(StringUtils.isNotEmpty(ad.getImgurl())){
			AdImg imgU = new AdImg();
			imgU.setSrc(ad.getImgurl());
			imgs.add(imgU);
		}

		if(StringUtils.isNotEmpty(ad.getClkurl())){
			action.setLinkurl(ad.getClkurl());
		}
		//-----------------END 常规设置----------------
		// 非原生广告

		List<String> imgurls = adm.getImgurl();
		for (String string : imgurls) {
			AdImg img = new AdImg();
			img.setSrc(string);
			imgs.add(img);
		}
		content.setImglist(imgs);
		action.setCpIcon(adm.getIcon());
		
		String title = adm.getTitle();
		if(StringUtils.isEmpty(title)){
			title = defTitle;
		}
		content.setContent(adm.getSubtitle());
		content.setMarketTitle(title);
		content.setGuideTitle(title);
		action.setGuideTitle(title);
		action.setLinkurl(adm.getLandingurl());
		content.setMutiAction(Collections.singletonList(action));
		
//		String linkUrl = content.getMutiAction().get(0).getLinkurl();
		//content.setAdId(InvenoIdGenerator.genThirdDspId(SystemContext.getDynamicPropertyHandler().getInt(ProxyConstants.PROXY_SERVER_NODE_INDEX,100)));

		Map<String, List<String>> map = new HashMap<String, List<String>>();
		// 点击监测
		if(CollectionUtils.isNotEmpty(ad.getClktrackersList())){
			map.put(CLICK, ad.getClktrackersList());
		}
		
		// 曝光监测
	
		if(CollectionUtils.isNotEmpty(ad.getImptrackersList())){
			map.put(SHOW, ad.getImptrackersList());
		}
		
		content.setThirdReportLinks(map);
		recomReply.setAd_contents(Collections.singletonList(content));
		return recomReply;
	}
	
	private WKSSPRequest parseParams(AdRecomReq adreq, DSPPosition positionInfo){
		
		WKSSPRequest.Builder request = WKSSPRequest.newBuilder();
		request.setRequestId(DigestUtils.md5Hex(
				String.valueOf(System.currentTimeMillis())));
		// 设备信息
		AdUserInfo userInfo = adreq.getUserinfo();
		Device.Builder device = Device.newBuilder();
		// 设备类型 手机
		device.setDeviceType(DeviceType.PHONE);
	    if(StringUtils.isNotEmpty(userInfo.getOs())){
	    	device.setOsType(userInfo.getOs().toLowerCase());
	    }else{
	    	device.setOsType("android");
	    }
		
	    if(StringUtils.isNotEmpty(userInfo.getOsversion())){
	    	device.setOsVersion(userInfo.getOsversion());
	    }else{
	    	device.setOsVersion("5.0");
	    }
		
	    if(StringUtils.isNotEmpty(userInfo.getPhonemodel())){
			device.setModel(userInfo.getPhonemodel());
	    }else{
			device.setModel("");
	    }
		String adid = userInfo.getAdid();
		if(StringUtils.isEmpty(adid)){
			adid = defAndroidId;
		}
		device.setAndroidId(adid);
		device.setAndroidIdMd5(DigestUtils.md5Hex(adid));
		
		device.setImei(userInfo.getImei());
		device.setImeiMd5(DigestUtils.md5Hex(userInfo.getImei()));
		device.setIpv4(userInfo.getClient_ip().trim());
		device.setConnectType(ConnectionType.WIFI);
		Integer w = userInfo.getDevice_width();
		if(w == null){
			w = 1080;
		}
		device.setW(w);
		Integer h = userInfo.getDevice_height();
		if(h == null){
			h = 1920;
		}
		device.setH(h);
		
		String vendor = userInfo.getBrand_name();
		if(StringUtils.isEmpty(vendor)){
			vendor = "huawei";
		}
		device.setVendor(vendor);
		
		OperatorType operatorType = oper.get(userInfo.getMobile());
		if(operatorType == null){
			operatorType = OperatorType.CHINA_MOBILE;
		}
		device.setCarrier(operatorType);

		if(StringUtils.isNotEmpty(userInfo.getMac())){
			device.setMac(userInfo.getMac());
		}
		
		request.setDevice(device);
		// 广告位信息
		UserAdSpaceAttri attri = adreq.getUserAdSpaceAttri();
		AdSlot.Builder adSlot = AdSlot.newBuilder();
		// 返回记录数 目前只支持1条
		adSlot.setCount(1);
		adSlot.setId(positionInfo.getPos());
		adSlot.setBidfloor(positionInfo.getSettlementPrice() / 100f);
		// 屏蔽的广告动作类型
/*		String bitc = positionInfo.getText2();
		if(UniversalUtils.isNumer(bitc)){
			adSlot.addBitc(Integer.valueOf(bitc));
		}*/
		
		AdSpaceType spaceType = attri.getSpaceType();
		// 信息流
		if(spaceType == AdSpaceType.INFOFLOW){
			
			Native.Builder nat = Native.newBuilder();
			nat.setVer("1.1");
			WKNativeAsset asset = new WKNativeAsset();
			WKAssetImg assetImg = new WKAssetImg();
			assetImg.setH(attri.getSpaceHeight());
			assetImg.setW(attri.getSpaceWidth());
			asset.setReqImg(assetImg);
			
			WKAssetTitle assetTitle = new WKAssetTitle();
			assetTitle.setLen(20);
			assetTitle.setSub_len(20);
			
			asset.setReqTitle(assetTitle);
			// 原生广告类型。1：信息流组图，2： 信息流小图，3：信息流大图 
			asset.setType(3);
			WKNativeRequest natRequest = new WKNativeRequest();
			List<WKNativeAsset> assets = new ArrayList<WKNativeAsset>();
			assets.add(asset);
			natRequest.setReqAssets(assets);
			String str = JsonUtils.toJson(natRequest);
			nat.setRequest(str);
			
			adSlot.setNative(nat);
		}
		else{
			// 插屏
			int isInstl = 0, isSplashScreen = 0;
			Banner.Builder banner = Banner.newBuilder();
			banner.setW(attri.getSpaceWidth());
			banner.setH(attri.getSpaceHeight());
			banner.addCType(CreativeType.IMAGE);
			if(spaceType == AdSpaceType.INTERSTITIAL){
				isInstl = 1;
			} else if(spaceType == AdSpaceType.OPENING){
				isSplashScreen = 1;
			}
			adSlot.setBanner(banner);
			adSlot.setInstl(isInstl);
			adSlot.setIsSplashScreen(isSplashScreen);
		}
		request.setAdSlot(adSlot);
		// 应用信息
		App.Builder app = App.newBuilder();
		app.setId(positionInfo.getText1());
		String appName = adreq.getApp();
		if(StringUtils.isEmpty(appName)){
			appName =APP_DEFAULT_NAME;
		}
		app.setName(appName);
		app.setBundle(SystemContext.getDynamicPropertyHandler().get(ProxyConstants.WANKA_BUNDLE));
		app.setVer("1.0");
		request.setApp(app);
		return request.build();
	}

	public WankaAdPuller getWankaAdPuller() {
		return wankaAdPuller;
	}

	public void setWankaAdPuller(WankaAdPuller wankaAdPuller) {
		this.wankaAdPuller = wankaAdPuller;
	}

}
