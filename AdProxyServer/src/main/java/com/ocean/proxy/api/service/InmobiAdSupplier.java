package com.ocean.proxy.api.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ocean.core.common.JsonUtils;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
import com.ocean.persist.api.proxy.AdPullException;
import com.ocean.persist.api.proxy.diankai.DiankaiAd;
import com.ocean.persist.api.proxy.diankai.DiankaiAdPullResponse;
import com.ocean.persist.api.proxy.inmobi.InmobiAdContent;
import com.ocean.persist.api.proxy.inmobi.InmobiAdEventUrls;
import com.ocean.persist.api.proxy.inmobi.InmobiAdImage;
import com.ocean.persist.api.proxy.inmobi.InmobiAdPullParams;
import com.ocean.persist.api.proxy.inmobi.InmobiAdPuller;
import com.ocean.persist.api.proxy.inmobi.InmobiAdResponse;
import com.ocean.persist.api.proxy.inmobi.InmobiAdm;
import com.ocean.persist.api.proxy.inmobi.InmobiApp;
import com.ocean.persist.api.proxy.inmobi.InmobiBanner;
import com.ocean.persist.api.proxy.inmobi.InmobiDevice;
import com.ocean.persist.api.proxy.inmobi.InmobiGeo;
import com.ocean.persist.api.proxy.inmobi.InmobiImp;
import com.ocean.persist.api.proxy.inmobi.InmobiImpExt;
import com.ocean.persist.api.proxy.inmobi.InmobiNative;
import com.ocean.persist.api.proxy.inmobi.InmobiParamsExt;
import com.ocean.persist.model.proxy.DSPPosition;
import com.ocean.proxy.api.base.AbstractAdSupplier;
import com.ocean.proxy.thrift.entity.AdContent;
import com.ocean.proxy.thrift.entity.AdImg;
import com.ocean.proxy.thrift.entity.AdMutiAction;
import com.ocean.proxy.thrift.entity.AdRecomReply;
import com.ocean.proxy.thrift.entity.AdRecomReq;
import com.ocean.proxy.thrift.entity.AdUserInfo;
import com.ocean.proxy.thrift.entity.UserAdSpaceAttri;
@Component(value="inmobiAdSupplier")
public class InmobiAdSupplier extends AbstractAdSupplier{
    @Autowired
	private InmobiAdPuller inmobiAdPuller;
	private static final Map<String, String> carrier = new HashMap<String, String>();
	private static final Map<String, Integer> conn = new HashMap<String, Integer>();
	static{
		carrier.put(MOBILE_CMCC, "ChinaMobile");
		carrier.put(MOBILE_CUCC, "ChinaUnicom");
		carrier.put(MOBILE_CTCC, "ChinaTelecom");
		
		conn.put(NETWORK_2G, 4);
		conn.put(NETWORK_3G, 5);
		conn.put(NETWORK_4G, 6);
		conn.put(NETWORK_WIFI, 2);
	}
	public AdRecomReply invoke(InvokeAttribute attribute) throws AdPullException {
		AdRecomReq adreq = attribute.getAdreq();
		DSPPosition positionInfo = attribute.getDspPosition();

		InmobiAdPullParams params = parseParams(adreq, positionInfo);
		InmobiAdResponse response = (InmobiAdResponse) inmobiAdPuller.api(params,adreq.getHash(),String.valueOf(adreq.getUserAdSpaceAttri().getAdSpaceId()));
		return parseResult(response, positionInfo);
	}
	
	private AdRecomReply parseResult(InmobiAdResponse response,DSPPosition positionInfo)
			throws AdPullException {
		
		if(response == null){
			return null;
		}
		List<InmobiAdContent> ads = response.getAds();
		if(ads == null || ads.isEmpty()){
			return null;
		}
		// 返回对象
		AdRecomReply recomReply = new AdRecomReply();
		recomReply.setStatus(status);
		recomReply.setAd_contents(this.dealAds(ads, positionInfo));
		return recomReply;
	}
	private List<AdContent>  dealAds(List<InmobiAdContent> ads,DSPPosition positionInfo){
		List<AdContent> list=new ArrayList<AdContent> ();
		for(InmobiAdContent ad:ads){
			AdContent content = new AdContent();
			AdMutiAction action = new AdMutiAction();
			action.setType(ACTION_WEB);
			String mate = ad.getPubContent();
			mate = new String(Base64.decodeBase64(mate));
			
			String title = null, desc = null;
			if(positionInfo.getPosType()==4||positionInfo.getPosType()==2){//inflow
				InmobiAdm adm = JsonUtils.toBean(mate, InmobiAdm.class);
				String linkurl = adm.getLandingURL();
				if(StringUtils.isEmpty(linkurl)){
					linkurl = ad.getLandingPage();
				}
				action.setLinkurl(linkurl);
				List<AdImg> imgs = new ArrayList<AdImg>();
				InmobiAdImage image = adm.getScreenshots();
				if(image != null){
					AdImg img = new AdImg();
					img.setSrc(image.getUrl());
					img.setWidth(image.getWidth());
					img.setHeight(image.getHeight());
					imgs.add(img);
				}
				InmobiAdImage icon = adm.getIcon();
				if(icon != null){
					content.setCpIcon(icon.getUrl());
					action.setCpIcon(icon.getUrl());
					if(imgs.isEmpty()){
						AdImg img = new AdImg();
						img.setSrc(icon.getUrl());
						img.setWidth(icon.getWidth());
						img.setHeight(icon.getHeight());
						imgs.add(img);
					}
				}
				// 图片地址
				content.setImglist(imgs);

				desc = adm.getDescription();
				title = adm.getTitle();
				// 监测
				Map<String, InmobiAdEventUrls> eventUrl = ad.getEventTracking();
				Map<String, List<String>> map = new HashMap<String, List<String>>();
				// 点击监测
				InmobiAdEventUrls urls = eventUrl.get("8");
				if(urls != null){
					map.put(CLICK, urls.getUrls());
				}
				// 曝光监测
				urls = eventUrl.get("18");
				if(urls != null){
					map.put(SHOW, urls.getUrls());
				}
				content.setThirdReportLinks(map);
			}
			// 非原生广告都是html物料
			else{
				content.setHtmlSnippet(mate);
				content.setIsHtmlAd(true);
			}
			
			if(StringUtils.isEmpty(title)){
				title = defTitle;
			}
			content.setMarketTitle(title);
			content.setGuideTitle(title);
			action.setGuideTitle(title);
			content.setContent(desc);
			content.setMutiAction(Collections.singletonList(action));
			list.add(content);
		}
		return list;
		
	}
	private InmobiAdPullParams parseParams(AdRecomReq adreq
			,DSPPosition positionInfo) throws AdPullException{
		UserAdSpaceAttri attri=adreq.getUserAdSpaceAttri();
		AdUserInfo userInfo = adreq.getUserinfo();
		InmobiAdPullParams params = new InmobiAdPullParams();
		// 额外属性
		InmobiParamsExt paramsExt = new InmobiParamsExt();
		paramsExt.setResponseformat("json");
		params.setExt(paramsExt);
		if(StringUtils.isEmpty(positionInfo.getText2())){
			throw new AdPullException("bundle is empty!");
		}
		// app信息
		InmobiApp app = new InmobiApp();
		app.setId(positionInfo.getPos());
		app.setBundle(positionInfo.getText2());
		params.setApp(app);
		// 设备信息
		InmobiDevice device = new InmobiDevice();
		device.setCarrier(carrier.get(userInfo.getMobile()));
		Integer ct=conn.get(adreq.getNet());
		device.setConnectiontype(ct==null?3:ct);

		device.setUa(userInfo.getUa());
		device.setIp(userInfo.getClient_ip());

		
		Integer secure = 0;
		if(userInfo.getOs().equalsIgnoreCase(OS_IOS)){
			
			String idfa = userInfo.getIdfa();
			if(!StringUtils.isEmpty(idfa)){
				device.setIfa(idfa.toUpperCase());
			}
			secure = 1;
		}
		else{
			String adid = userInfo.getAdid();
			if(StringUtils.isNotEmpty(adid)){
				device.setO1(DigestUtils.sha1Hex(adid));
				device.setUm5(DigestUtils.md5Hex(adid));
			}
			device.setIem(userInfo.getImei());
			device.setMd5_imei(DigestUtils.md5Hex(userInfo.getImei()));
			device.setSha1_imei(DigestUtils.sha1Hex(userInfo.getImei()));
		}
		
		

		String lat = userInfo.getLat();
		String lon = userInfo.getLon();
		if(StringUtils.isNotEmpty(lat)&&StringUtils.isNotEmpty(lon)){
			InmobiGeo geo = new InmobiGeo();
			geo.setCity(userInfo.getCity());
			geo.setLat(Double.valueOf(lat));
			geo.setLon(Double.valueOf(lon));
			device.setGeo(geo);
		}
		params.setDevice(device);

		// 展示信息
		InmobiImp imp = new InmobiImp();
		// 0 http物料 1 https物料 仅限iOS
		imp.setSecure(secure);
		imp.setTrackertype("url_ping");
		if(positionInfo.getPosType()==4||positionInfo.getPosType()==2){//原生
			InmobiNative native1 = new InmobiNative();
			native1.setLayout(0);
			imp.setNative1(native1);
		}
		else{
			InmobiBanner banner = new InmobiBanner();
			banner.setH(attri.getSpaceHeight());
			banner.setW(attri.getSpaceWidth());
			imp.setBanner(banner);
		}
		InmobiImpExt impExt = new InmobiImpExt();
		impExt.setAds(adreq.getResult_num());
		imp.setExt(impExt);
		params.setImp(imp);
		return params;
	}

	public InmobiAdPuller getInmobiAdPuller() {
		return inmobiAdPuller;
	}

	public void setInmobiAdPuller(InmobiAdPuller inmobiAdPuller) {
		this.inmobiAdPuller = inmobiAdPuller;
	}
}
