package com.ocean.proxy.api.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inveno.util.CollectionUtils;
import com.ocean.persist.api.proxy.AdPullException;
import com.ocean.persist.api.proxy.wanyueV2.DigestUtil;
import com.ocean.persist.api.proxy.xunfei.XunfeiAdPullParams;
import com.ocean.persist.api.proxy.xunfei.XunfeiAdPuller;
import com.ocean.persist.api.proxy.xunfei.XunfeiAdRes;
import com.ocean.persist.api.proxy.xunfei.XunfeiAdResponse;
import com.ocean.persist.api.proxy.xunfei.XunfeiApp;
import com.ocean.persist.api.proxy.xunfei.XunfeiDebug;
import com.ocean.persist.api.proxy.xunfei.XunfeiDevice;
import com.ocean.persist.api.proxy.xunfei.XunfeiGeo;
import com.ocean.persist.api.proxy.xunfei.XunfeiImageRes;
import com.ocean.persist.api.proxy.xunfei.XunfeiImp;
import com.ocean.persist.api.proxy.xunfei.XunfeiMonitorRes;
import com.ocean.persist.model.proxy.DSPPosition;
import com.ocean.proxy.api.base.AbstractAdSupplier;
import com.ocean.proxy.thrift.entity.AdContent;
import com.ocean.proxy.thrift.entity.AdImg;
import com.ocean.proxy.thrift.entity.AdMutiAction;
import com.ocean.proxy.thrift.entity.AdRecomReply;
import com.ocean.proxy.thrift.entity.AdRecomReq;
import com.ocean.proxy.thrift.entity.AdUserInfo;
import com.ocean.proxy.thrift.entity.UserAdSpaceAttri;

@Component(value="xunfeiAdSupplier")
public class XunfeiAdSupplier  extends AbstractAdSupplier{
	private static final List<String> DEEPLINK_PREFIX;
	private static final Map<String,String> DEEPLINK_APK_MAP;
	private static final Map<String,String> DEEPLINK_PKG_MAP;
	static{
		DEEPLINK_PREFIX=new ArrayList<String>();
		DEEPLINK_PREFIX.addAll(Arrays.asList("tbopen","tmall","openapp.jdmobile","vipshop","suning"));
				
		DEEPLINK_PKG_MAP=new HashMap<String,String>();
		DEEPLINK_PKG_MAP.put("tbopen", "com.taobao.taobao");
		DEEPLINK_PKG_MAP.put("tmall", "com.tmall.wireless");
		DEEPLINK_PKG_MAP.put("openapp.jdmobile", "com.jingdong.app.mall");
		DEEPLINK_PKG_MAP.put("vipshop", "com.achievo.vipshop");
		DEEPLINK_PKG_MAP.put("suning", "com.suning.mobile.ebuy");
		
		DEEPLINK_APK_MAP=new HashMap<String,String>();
		DEEPLINK_APK_MAP.put("tbopen", "http://openbox.mobilem.360.cn/third/download?downloadUrl=http%3A%2F%2Fshouji.360tpcdn.com%2F190118%2F522c2a418ed43207e2f778da392aafa8%2Fcom.taobao.taobao_229.apk&softId=3570&from=ivvi&pname=com.taobao.taobao");
		DEEPLINK_APK_MAP.put("tmall",  "http://p.gdown.baidu.com/bc4e950440d80c0203ca4119d373e46183714976ae62109869f861ba5ae6d651c58f29b5df2eeb1133195d39eb0968a2bb4daab5a717d45f58c246097dc98e4140dbdcd513f6baec4adce26cb2c347c849012da2a441d0e338d6bc6bc8b606908396fe0c33dd9f182a48ff39d85ae4bd20d8b597260e9087");         
		DEEPLINK_APK_MAP.put("openapp.jdmobile", "http://p.gdown.baidu.com/379fcd527ab52876410ad2e557dd93a7807fd52a219e3a6f7c5b131d3436145a7ccdae1ec37a839248d5c625340fc0880127fbe0b0ab1557bfe9d8597ad2237a7847c19d8b3829d8611830d51bd000de258ee6f74f28fb0532352eb01a0e4b6759059fd03e8553294dd4e14dc8d793100573a14f1627f2c822c2decb7c71863b8f26233b780f2bd306da0d10b4d56b839c9635232d8de7491eb2da5b09a4be6cd2a77450c1ca932bae039bee9d3af839f4e33712cc84a081");
		DEEPLINK_APK_MAP.put("vipshop", "http://p.gdown.baidu.com/4ce9133a4030ef22684233dd2a17c0eb42921b332c392e8f57843a9239ed801ed91663b72e69bee461c5a80de7d44217343ba8002e970b7e7ecf4ddb47a5e8ed3d3e2e0f0a9e52edcdb6416af45837dc816d04c4e5f380baa8d325cf26d28c9897fd2f9bad3019b83a2ceebad665d835df2fc14c4d5ebc6537b32a8fe3c4b20dfe6e67ac7785993aa073b41668c9073614f907849a789f9fcf965748dc29d60f4def4ef7a3e20b670f6cc1275e3985d334cf40df162a90bb7e06e59144176eef47f72cb055fe882c79debf05ba239bad2093102d7e6c0d3ed2a49aa5db5d2b6182eab668abbfc4d1e180254a93eddab94cf44fc8cf8c3274f663a0b8898337d0ec3c3695ce0171aaa6f905cf867f8b396b8d9bb1c0c1cec438109542368bf0aeefda948f73bba017");
		DEEPLINK_APK_MAP.put("suning", "http://openbox.mobilem.360.cn/third/download?downloadUrl=http%3A%2F%2Fshouji.360tpcdn.com%2F190213%2F4b28c581f720fc82579358f4cdbea85b%2Fcom.suning.mobile.ebuy_261.apk&softId=65533&from=ivvi&pname=com.suning.mobile.ebuy");
	}
    @Autowired
	private XunfeiAdPuller XunfeiAdPuller;
	public AdRecomReply invoke(InvokeAttribute attribute) throws AdPullException {
		AdRecomReq adreq = attribute.getAdreq();
		DSPPosition positionInfo = attribute.getDspPosition();
		XunfeiAdPullParams params = parseParams(adreq, positionInfo);
		XunfeiAdResponse response = (XunfeiAdResponse) XunfeiAdPuller.api(params,adreq.getHash(),String.valueOf(adreq.getUserAdSpaceAttri().getAdSpaceId()));
		return parseResult(response,adreq.getHash());
	}
	
	private XunfeiAdPullParams parseParams(AdRecomReq adreq, DSPPosition posInfo)
			 throws AdPullException {
		
		// 广告属性
		UserAdSpaceAttri attri = adreq.getUserAdSpaceAttri();
		AdUserInfo userInfo = adreq.getUserinfo();
		
		XunfeiAdPullParams params = new XunfeiAdPullParams();
		String Id=UUID.randomUUID().toString().replaceAll("-", "");
		params.setId(Id);
		params.setApi_ver("3.0.2");
		params.setSettle_type(0);
		List<String> cur=new ArrayList<>();
		cur.add("CNY");
		cur.add("USD");
		params.setCur(cur);
		//imps
		XunfeiImp imp=new XunfeiImp();
		imp.setAdunit_id(posInfo.getPos());// 讯飞广告位id
		imp.setAdw(attri.getSpaceWidth());
		imp.setAdh(attri.getSpaceHeight());
		List<Integer> supportStatus=new ArrayList<>();
		supportStatus.add(1);
		imp.setDp_support_status(supportStatus);
		imp.setSecure(3);
		//debug
		XunfeiDebug debug = new XunfeiDebug();
		debug.setAction_type(2);
		debug.setLanding_type(1);
		imp.setDebug(debug);
		//debug end
		List<XunfeiImp> imps=new ArrayList<>();
		imps.add(imp);
		params.setImps(imps);
		//imps end
		
		//app
		XunfeiApp app=new XunfeiApp();
		app.setApp_name(adreq.getApp());//应用名称，必须和在讯飞后台注册的应用名称保持一致
		app.setApp_ver(adreq.getVersion());//App 自身的版本号
		params.setApp(app);
		//app end
		//device
		XunfeiDevice device=new XunfeiDevice();
		device.setDevice_type(0);
		device.setOs(0);
		device.setOsv(userInfo.getOsversion());
		device.setAdid(userInfo.getAdid());
		device.setAdid_md5(DigestUtil.md5Hex(userInfo.getAdid()));
		device.setImei(userInfo.getImei());
		device.setImei_md5(DigestUtil.md5Hex(userInfo.getImei()));
		device.setMac(userInfo.getMac());
		device.setMac_md5(DigestUtil.md5Hex(userInfo.getMac()));
		if(StringUtils.isNoneEmpty(userInfo.getMobile())){
			String carrier=mobiles.get(userInfo.getMobile());
			if(carrier.equals("46000")) {
				device.setCarrier(1);
			}else if(carrier.equals("46001")) {
				device.setCarrier(2);
			}else if(carrier.equals("46003")) {
				device.setCarrier(3);
			}else{
				device.setCarrier(0);
			}		
		}
		if(StringUtils.isNoneEmpty(adreq.getNet())){
			String net=adreq.getNet();
			if(net.equals(NETWORK_WIFI)) {
				device.setNet(2);
			}else if(net.equals(NETWORK_ETHERNET)){
				device.setNet(1);
			}else if(net.equals(NETWORK_2G)){
				device.setNet(4);
			}else if(net.equals(NETWORK_3G)){
				device.setNet(5);
			}else if(net.equals(NETWORK_4G)){
				device.setNet(6);
			}else if(net.equals(NETWORK_5G)){
				device.setNet(7);
			}else{
				device.setNet(0);
			}
		}
		device.setIp(userInfo.getClient_ip());
		device.setUa(userInfo.getUa_webv());
		device.setTs(System.currentTimeMillis());
		// 设备宽高
		device.setDvw(userInfo.getDevice_width());
		device.setDvh(userInfo.getDevice_height());
		device.setOrientation(0);
		device.setMake(userInfo.getBrand_name());
		device.setModel(userInfo.getPhonemodel());
		device.setLan("zh-CN");
		XunfeiGeo xunfeiGeo=new XunfeiGeo();
		if(StringUtils.isNotEmpty(userInfo.getLat())&&StringUtils.isNotEmpty(userInfo.getLon())) {
			xunfeiGeo.setLat(Double.parseDouble(userInfo.getLat()));
			xunfeiGeo.setLon(Double.parseDouble(userInfo.getLon()));
			device.setGeo(xunfeiGeo);
		}
		params.setDevice(device);
		//device end
		return params;
	}
	
	private AdRecomReply parseResult(XunfeiAdResponse response,String hashcode)
			throws AdPullException {
		
		if(response == null||CollectionUtils.isEmpty(response.getAds())){
			return null;
		}
		AdRecomReply adresp = new AdRecomReply();
		adresp.setStatus(status);
		List<XunfeiAdRes> adRes=response.getAds();
		if(adRes == null || adRes.isEmpty()){
			return null;
		}
		adresp.setAd_contents(dealAds(adRes,hashcode));
		return adresp;
	}
	private List<AdContent>  dealAds(List<XunfeiAdRes> details,String hashcode ){
		List<AdContent> list=new ArrayList<AdContent> ();
		for(XunfeiAdRes detail :details){
			// 广告内容
			AdContent content = new AdContent();
			String title = detail.getTitle();
			// 标题不能为null对象
			if(StringUtils.isEmpty(title)){
				title = defTitle;
			}
			// 类型
			AdMutiAction action = new AdMutiAction();
			// 内容类型1表示链接推广,2表示应用推广,3未使用 4表示电话推广
			int actType=ACTION_WEB;
			List<AdImg> imgs = new ArrayList<AdImg>();
			Map<String, List<String>> map = new HashMap<String, List<String>>();
	        if(StringUtils.isNotEmpty(detail.getHtml())){
	        	content.setIsHtmlAd(true);
	        	content.setHtmlSnippet(detail.getHtml());
	        }else {
				if(detail.getAction_type()==3){
		        	actType=ACTION_APP;
		        }
	
				action.setLinkurl(detail.getLanding());
		        if(StringUtils.isNotEmpty(detail.getDeeplink())){
		        	action.setActiveUri(detail.getDeeplink());
		        }
				// 广告图片
				XunfeiImageRes imageRes=detail.getImg();
				XunfeiImageRes imageRes1=detail.getImg1();
				XunfeiImageRes imageRes2=detail.getImg2();
				XunfeiImageRes imageRes3=detail.getImg3();
				if(imageRes!=null) {
					AdImg img=new AdImg();
					img.setSrc(imageRes.getUrl());
					img.setWidth(imageRes.getWidth());
					img.setHeight(imageRes.getHeight());
					imgs.add(img);
				}
				if(imageRes1!=null) {
					AdImg img=new AdImg();
					img.setSrc(imageRes1.getUrl());
					img.setWidth(imageRes1.getWidth());
					img.setHeight(imageRes1.getHeight());
					imgs.add(img);
				}
				if(imageRes2!=null) {
					AdImg img=new AdImg();
					img.setSrc(imageRes2.getUrl());
					img.setWidth(imageRes2.getWidth());
					img.setHeight(imageRes2.getHeight());
					imgs.add(img);
				}
				if(imageRes3!=null) {
					AdImg img=new AdImg();
					img.setSrc(imageRes3.getUrl());
					img.setWidth(imageRes3.getWidth());
					img.setHeight(imageRes3.getHeight());
					imgs.add(img);
				}
				XunfeiImageRes imageResIcon=detail.getIcon();
				if(imageResIcon!=null) {
					if(imgs.size()==0) {
						AdImg img=new AdImg();
						img.setSrc(imageResIcon.getUrl());
						img.setWidth(imageResIcon.getWidth());
						img.setHeight(imageResIcon.getHeight());
						imgs.add(img);
					}else {
						content.setCpIcon(imageResIcon.getUrl());
						action.setCpIcon(imageResIcon.getUrl());
					}
	
				}
				content.setImglist(imgs);
		    }
			//特殊处理,只要有deeplink就一定要传
			if(detail.getAction_type()!=3&&StringUtils.isNotEmpty(detail.getDeeplink())){
				action.setLinkurl(detail.getDeeplink());
				String prefix=this.extractDLPrefix(detail.getDeeplink(), hashcode);
				if(StringUtils.isNotEmpty(prefix)){
					actType=ACTION_APP;
					action.setLinkurl(DEEPLINK_APK_MAP.get(prefix));
					content.setCpPackage(DEEPLINK_PKG_MAP.get(prefix));
					action.setCpPackage(DEEPLINK_PKG_MAP.get(prefix));
					action.setActiveUri(detail.getDeeplink());
				}
			}

			// 点击监测
	        XunfeiMonitorRes monitorRes=detail.getMonitor();
	        List<String> click =monitorRes.getClick_urls();
	        if(CollectionUtils.isNotEmpty(click)) {
	        	map.put(CLICK, click);
	        }
	        
	        //下载开始
	        List<String> downloadStartUrls=monitorRes.getDownload_start_urls();
	        if(CollectionUtils.isNotEmpty(downloadStartUrls)) {
	        	map.put(START_DOWN, downloadStartUrls);
	        }
			
			
			// 曝光监测
	        List<String> impressurls =monitorRes.getImpress_urls();
	        if(CollectionUtils.isNotEmpty(impressurls)) {
				map.put(SHOW, impressurls);
	        }
	        

			// app下载监控
			
	        List<String> downloadcompleteurls =monitorRes.getDownload_complete_urls();
	        if(CollectionUtils.isNotEmpty(downloadcompleteurls)) {
				map.put(DOWNLOAD, downloadcompleteurls);
	        }

			// app开始安装监控
	        List<String> installstarturls =monitorRes.getInstall_start_urls();
	        if(CollectionUtils.isNotEmpty(installstarturls)) {
				map.put(START_INSTALL, installstarturls);
	        }

			// app结束安装监控
	        List<String> installcompleteurls =monitorRes.getInstall_complete_urls();
	        if(CollectionUtils.isNotEmpty(installcompleteurls)) {
				map.put(INSTALL, installcompleteurls);
	        }

			action.setGuideTitle(title);
			content.setGuideTitle(title);
			content.setMarketTitle(title);
			content.setThirdReportLinks(map);
			action.setType(actType);
			content.setMutiAction(Collections.singletonList(action));
			list.add(content);

		}
		return list;
	
	}
	private String extractDLPrefix(String deeplink,String hashcode){
		try{
			for(Iterator<String> it=DEEPLINK_PREFIX.iterator();it.hasNext();){
				String prefix=it.next();
				if(deeplink.startsWith(prefix)){
					return prefix;
				}
			}
		}catch(Throwable e){
			logger.error("{} extract deeplink prefix error:{}",hashcode,deeplink,e);
		}

		return null;
	}
	public XunfeiAdPuller getXunfeiAdPuller() {
		return XunfeiAdPuller;
	}

	public void setXunfeiAdPuller(XunfeiAdPuller xunfeiAdPuller) {
		XunfeiAdPuller = xunfeiAdPuller;
	}

}
