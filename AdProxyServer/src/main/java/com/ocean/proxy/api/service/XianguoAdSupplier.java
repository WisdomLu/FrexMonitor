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

import com.ocean.core.common.system.SystemContext;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.persist.api.proxy.AdPullException;
import com.ocean.persist.api.proxy.JoinDSPEmu;
import com.ocean.persist.api.proxy.diankai.DiankaiAd;
import com.ocean.persist.api.proxy.diankai.DiankaiAdPullResponse;
import com.ocean.persist.api.proxy.xianguo.XianguoAdPullParams;
import com.ocean.persist.api.proxy.xianguo.XianguoAdPuller;
import com.ocean.persist.api.proxy.xianguo.XianguoAdResponse;
import com.ocean.persist.api.proxy.xianguo.XinGuoV204;
import com.ocean.persist.api.proxy.xianguo.XinGuoV204.AdSlot;
import com.ocean.persist.api.proxy.xianguo.XinGuoV204.AdType;
import com.ocean.persist.api.proxy.xianguo.XinGuoV204.App;
import com.ocean.persist.api.proxy.xianguo.XinGuoV204.Bid;
import com.ocean.persist.api.proxy.xianguo.XinGuoV204.BidRequest;
import com.ocean.persist.api.proxy.xianguo.XinGuoV204.BidResponse;
import com.ocean.persist.api.proxy.xianguo.XinGuoV204.Config;
import com.ocean.persist.api.proxy.xianguo.XinGuoV204.ConnectionType;
import com.ocean.persist.api.proxy.xianguo.XinGuoV204.Device;
import com.ocean.persist.api.proxy.xianguo.XinGuoV204.Geo;
import com.ocean.persist.api.proxy.xianguo.XinGuoV204.InteractionType;
import com.ocean.persist.api.proxy.xianguo.XinGuoV204.SpType;
import com.ocean.persist.api.proxy.xianguo.XinGuoV204.Version;
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

@Component(value="xianguoAdSupplier")
public class XianguoAdSupplier    extends AbstractAdSupplier{
	private static final Map<String, ConnectionType> ctype = new HashMap<String, ConnectionType>();
	private static final Map<String, SpType> sptype = new HashMap<String, XinGuoV204.SpType>(5);
	private static final Map<AdSpaceType, AdType> adtype = new HashMap<AdSpaceType, XinGuoV204.AdType>();
    @Autowired
	private XianguoAdPuller xianguoAdPuller;
	static{
		ctype.put(NETWORK_2G, ConnectionType.CELL_2G);
		ctype.put(NETWORK_3G, ConnectionType.CELL_3G);
		ctype.put(NETWORK_4G, ConnectionType.CELL_4G);
		ctype.put(NETWORK_WIFI, ConnectionType.CELL_WIFI);
		
		sptype.put(MOBILE_CMCC, SpType.YD);
		sptype.put(MOBILE_CUCC, SpType.LT);
		sptype.put(MOBILE_CTCC, SpType.DX);
		
		adtype.put(AdSpaceType.BANNER, AdType.banner);
		adtype.put(AdSpaceType.INFOFLOW, AdType.feeds);
		adtype.put(AdSpaceType.INTERSTITIAL, AdType.ingame);
		adtype.put(AdSpaceType.OPENING, AdType.splash);
	}
	public AdRecomReply invoke(InvokeAttribute attribute) throws AdPullException {
		
		AdRecomReq adreq = attribute.getAdreq();
		DSPPosition positionInfo = attribute.getDspPosition();
		
		// 参数转换
		XianguoAdPullParams params = parseParams(adreq, positionInfo);
		XianguoAdResponse response = (XianguoAdResponse) xianguoAdPuller.api(params,adreq.getHash(),String.valueOf(adreq.getUserAdSpaceAttri().getAdSpaceId()));
		return parseResult(response, positionInfo);
	}
	
	private AdRecomReply parseResult(XianguoAdResponse response, DSPPosition positionInfo)
			throws AdPullException {
		
		if(response == null){
			return null;
		}
		BidResponse r = response.getResponse();
		
		List<Bid> bids = r.getBidsList();
		if(bids == null || bids.isEmpty()){
			return null;
		}
		// 返回对象
		AdRecomReply recomReply = new AdRecomReply();
		recomReply.setStatus(status);
		recomReply.setAd_contents(this.dealAds(bids, positionInfo));
		return recomReply;
	}
	private List<AdContent>  dealAds(List<Bid> bids,DSPPosition positionInfo){
		List<AdContent> list=new ArrayList<AdContent> ();
		for(Bid ad:bids){
			
			AdContent content = new AdContent();
			AdMutiAction action = new AdMutiAction();
			Integer type = ACTION_WEB;
			if(ad.getInteractionType() == InteractionType.DOWNLOAD){
				type = ACTION_APP;
			}
			
			action.setType(type);
			List<AdImg> imgs = new ArrayList<AdImg>();
			// 非原生广告
			String image = ad.getImageSrc();
			if(!StringUtils.isEmpty(image)){
				AdImg img = new AdImg();
				img.setSrc(image);
				imgs.add(img);
			}
			String icon = ad.getIconSrc();
			action.setCpIcon(icon);
			content.setCpIcon(icon);
			content.setImglist(imgs);
			
			String title = ad.getTitle();
			if(StringUtils.isEmpty(title)){
				title = defTitle;
			}
			content.setMarketTitle(title);
			content.setGuideTitle(title);
			content.setContent(ad.getDescription());
			action.setGuideTitle(title);
			action.setLinkurl(ad.getHref());
			content.setMutiAction(Collections.singletonList(action));
			
//			String linkUrl = content.getMutiAction().get(0).getLinkurl();
			//content.setAdId(InvenoIdGenerator.genThirdDspId(SystemContext.getDynamicPropertyHandler().getInt(ProxyConstants.PROXY_SERVER_NODE_INDEX,100)));

			Map<String, List<String>> map = new HashMap<String, List<String>>();
			// 点击监测
			List<String> click = ad.getClickUrlList();
//			System.out.println("点击监测" + click);
			if(click == null){
				click = new ArrayList<String>();
			}
			map.put(CLICK, click);
			// 曝光监测
			List<String> show = ad.getShowUrlList();
			if(show != null && !show.isEmpty()){
				List<String> showurl = new ArrayList<String>();
				for(String surl : show){
					surl = surl.replace("%%WIN_PRICE%%", 
							positionInfo.getSettlementPrice().toString());
					showurl.add(surl);
				}
				map.put(SHOW, showurl);
			}
			// 下载上报
			List<String> download = ad.getDownUrlList();
			if(download != null){
				map.put(DOWNLOAD, download);
			}
			// 安装上报
			List<String> install = ad.getInstallUrlList();
			if(install != null){
				map.put(INSTALL, install);
			}
			content.setThirdReportLinks(map);
			list.add(content);
		}
		return list;
		
	}
	private XianguoAdPullParams parseParams(AdRecomReq adreq, DSPPosition positionInfo) throws AdPullException{
		
		BidRequest.Builder request = BidRequest.newBuilder();
		request.setId(DigestUtils.md5Hex(
				String.valueOf(System.currentTimeMillis())));
		// 配置信息
		Config.Builder config = Config.newBuilder();
		config.setSspId(SystemContext.getDynamicPropertyHandler().getInt(ProxyConstants.XIANGUO_SSP_ID,0));
		config.setMadsId(Integer.valueOf(positionInfo.getPos()));
		if(StringUtils.isEmpty(positionInfo.getText1())){
			throw new AdPullException("param parse error,joinDSP:xg app id is empty!");
		}
		config.setMediumId(Integer.valueOf(positionInfo.getText1()));
		request.setConfig(config);
		
		Version.Builder version = Version.newBuilder();
		version.setMajor(2);
		version.setMinor(0);
		version.setMicro(4);
		request.setApiVersion(version);
		// 设备信息
		AdUserInfo userInfo = adreq.getUserinfo();
		Device.Builder device = Device.newBuilder();
		// 设备类型 1手机 2平板
		device.setDevicetype("PHONE");
		if("android".equals(userInfo.getOs())){
			device.setOstype("Android");
		}else{
			device.setOstype("iOS");
			device.setIdfa(userInfo.getIdfa());
		}
		
		device.setOsVersion(convOSVersion(userInfo.getOsversion()));
		device.setModel(userInfo.getPhonemodel());
		String imei = userInfo.getImei();
		device.setImei(imei);
		device.setImeiMd5(DigestUtils.md5Hex(imei));
		device.setIp(userInfo.getClient_ip().trim());
		String mac = userInfo.getMac();
		if(StringUtils.isNotEmpty(mac)&&!mac.equals(imei)){
			device.setMac(mac);
		}else{
			device.setMac("");
		}
		
		String imsi = userInfo.getImsi();
		if(StringUtils.isNotEmpty(imsi)&&!imsi.equals(imei)){
			device.setImsi(imsi);
		}else{
			device.setImsi("");
		}
		SpType spType=sptype.get(userInfo.getMobile());
		if(spType==null){
			spType=SpType.None;
		}
		device.setSptype(spType);
		ConnectionType contype = ctype.get(adreq.getNet());
		if(contype == null){
			contype = ConnectionType.CELL_WIFI;
		}
		device.setConnectiontype(contype);
		
		Integer w = userInfo.getDevice_width();
		if(w == null){
			w = 1080;
		}
		device.setScreenWidth(w);
		Integer h = userInfo.getDevice_height();
		if(h == null){
			h = 1920;
		}
		device.setScreenHeight(h);
		String adid = userInfo.getAdid();
		if(StringUtils.isNotEmpty(adid)&&!adid.equals(imei)){
			device.setAndroidId(adid);
		}else{
			device.setAndroidId("");
		}
		
		
		String vendor = userInfo.getBrand_name();
		if(StringUtils.isEmpty(vendor)){
			vendor = "huawei";
		}
		device.setVendor(vendor);
		
		Geo.Builder geo = Geo.newBuilder();
		if(StringUtils.isNotEmpty(userInfo.getCity())){
			geo.setCity(userInfo.getCity());
		}
		
		
		String lat = userInfo.getLat();
		if(!StringUtils.isEmpty(lat)){
			geo.setLatitude(Double.valueOf(lat));
		}
		String lon = userInfo.getLon();
		if(!StringUtils.isEmpty(lon)){
			geo.setLongitude(Double.valueOf(lon));
		}
		device.setGeo(geo.build());
		device.setUa(userInfo.getUa());
		
		request.setDevice(device);
		// 广告位信息
		UserAdSpaceAttri attri = adreq.getUserAdSpaceAttri();
		AdSlot.Builder adSlot = AdSlot.newBuilder();
		adSlot.setBidfloor(positionInfo.getSettlementPrice());
		adSlot.setAdslotHeight(attri.getSpaceHeight());
		adSlot.setAdslotWidth(attri.getSpaceWidth());
		adSlot.setAdtype(adtype.get(attri.getSpaceType()));
		request.setAdslot(adSlot);
		// 应用信息
		App.Builder app = App.newBuilder();
		String appName = adreq.getApp();
		if(StringUtils.isEmpty(appName)){
			appName = "alpha go";
		}
		app.setAppname(appName);
		app.setAppPackagename("com.zookingsoft.themestore");
		app.setCat("主题美化");
//		app.setVer("1.0");
		request.setApp(app);
		

		XianguoAdPullParams params = new XianguoAdPullParams();
		params.setBidRequest(request.build());
		return params;
	}
	public XianguoAdPuller getXianguoAdPuller() {
		return xianguoAdPuller;
	}

	public void setXianguoAdPuller(XianguoAdPuller xianguoAdPuller) {
		this.xianguoAdPuller = xianguoAdPuller;
	}

}
