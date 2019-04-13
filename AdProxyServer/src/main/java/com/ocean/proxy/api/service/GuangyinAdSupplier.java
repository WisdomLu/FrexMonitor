package com.ocean.proxy.api.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ocean.core.common.JsonUtils;
import com.ocean.core.common.system.SystemContext;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
import com.ocean.persist.api.proxy.AdPullException;
import com.ocean.persist.api.proxy.JoinDSPEmu;
import com.ocean.persist.api.proxy.gmobi.GmobiAd;
import com.ocean.persist.api.proxy.gmobi.GmobiAdPullResponse;
import com.ocean.persist.api.proxy.guangyin.GuangyinAdPuller;
import com.ocean.persist.api.proxy.guangyin.GuangyinAdm;
import com.ocean.persist.api.proxy.guangyin.OpenrtbV29.AdType;
import com.ocean.persist.api.proxy.guangyin.OpenrtbV29.AdmType;
import com.ocean.persist.api.proxy.guangyin.OpenrtbV29.App;
import com.ocean.persist.api.proxy.guangyin.OpenrtbV29.Banner;
import com.ocean.persist.api.proxy.guangyin.OpenrtbV29.Bid;
import com.ocean.persist.api.proxy.guangyin.OpenrtbV29.BidRequest;
import com.ocean.persist.api.proxy.guangyin.OpenrtbV29.BidResponse;
import com.ocean.persist.api.proxy.guangyin.OpenrtbV29.ConnectionType;
import com.ocean.persist.api.proxy.guangyin.OpenrtbV29.Device;
import com.ocean.persist.api.proxy.guangyin.OpenrtbV29.DeviceType;
import com.ocean.persist.api.proxy.guangyin.OpenrtbV29.Geo;
import com.ocean.persist.api.proxy.guangyin.OpenrtbV29.Imp;
import com.ocean.persist.api.proxy.guangyin.OpenrtbV29.LocationType;
import com.ocean.persist.api.proxy.guangyin.OpenrtbV29.Publisher;
import com.ocean.persist.api.proxy.guangyin.OpenrtbV29.Scenario;
import com.ocean.persist.api.proxy.guangyin.OpenrtbV29.ScenarioType;
import com.ocean.persist.api.proxy.guangyin.OpenrtbV29.SeatBid;
import com.ocean.persist.common.ProxyConstants;
import com.ocean.persist.model.proxy.DSPPosition;
import com.ocean.proxy.api.base.AbstractAdSupplier;
import com.ocean.proxy.api.helper.InvenoIdGenerator;
import com.ocean.proxy.thrift.entity.AdContent;
import com.ocean.proxy.thrift.entity.AdImg;
import com.ocean.proxy.thrift.entity.AdMutiAction;
import com.ocean.proxy.thrift.entity.AdRecomReply;
import com.ocean.proxy.thrift.entity.AdRecomReq;
import com.ocean.proxy.thrift.entity.AdUserInfo;
import com.ocean.proxy.thrift.entity.UserAdSpaceAttri;
@Component(value="guangyinAdSupplier")
public class GuangyinAdSupplier  extends AbstractAdSupplier{
    @Autowired
	private GuangyinAdPuller guangyinAdPuller;
	private static volatile int impId = 0;
	public AdRecomReply invoke(InvokeAttribute attribute) throws AdPullException {
		AdRecomReq adreq = attribute.getAdreq();
		DSPPosition positionInfo = attribute.getDspPosition();
		// 参数转换
		BidRequest params = parseParams(adreq, positionInfo);
		BidResponse response = (BidResponse) guangyinAdPuller.api(params,adreq.getHash(),String.valueOf(adreq.getUserAdSpaceAttri().getAdSpaceId()));
		return parseResult(response);
	}
	
	private AdRecomReply parseResult(BidResponse response)
			throws AdPullException {
		
		if(response == null){
			return null;
		}
		System.out.println(response);
//		List<Ad> ads = response.getAdsList();
		List<SeatBid> list = response.getSeatbidList();
		if(list == null || list.isEmpty()){
			return null;
		}
		
		AdRecomReply recomReply = new AdRecomReply();
		recomReply.setStatus(status);
		SeatBid seatBid = response.getSeatbid(0);
		recomReply.setAd_contents(this.dealAds(seatBid));
		return recomReply;
	}
	private List<AdContent>  dealAds(SeatBid seatBid){
		List<AdContent> list=new ArrayList<AdContent> ();
		List<Bid> bids = seatBid.getBidList();
		if(bids == null || bids.isEmpty()){
			return null;
		}
		for(Bid bid:bids){
			
			// 不支持html
			if(bid.getAdmtype() == AdmType.HTML){
				return null;
			}	
			AdContent content = new AdContent();
			AdMutiAction action = new AdMutiAction();
			// click,install
			String ac = bid.getAction();
			// 1表示链接推广,2表示应用推广,3未使用 4表示电话推广
			Integer type = 1;
			if("install".equals(ac)){
				action.setCpclass(bid.getBundle());
				type = 2;
			}
			action.setType(type);
			
			AdmType admType = bid.getAdmtype();
			String title = null;
			if(admType == AdmType.JSON){
				// 素材图片
				GuangyinAdm adm = JsonUtils.toBean(bid.getAdm(), GuangyinAdm.class);
				List<AdImg> imgs = new ArrayList<AdImg>();
				AdImg img = new AdImg();
				img.setSrc(adm.getSrc());
				imgs.add(img);
				content.setImglist(imgs);
				
				action.setCpIcon(adm.getIcon());
				content.setCpIcon(adm.getIcon());
				content.setContent(adm.getText());
				title = adm.getTitle();
			}
			// html 素材
			else{
				content.setHtmlSnippet(bid.getAdm());
				content.setIsHtmlAd(true);
			}
			if(StringUtils.isEmpty(title)){
				title = defTitle;
			}
			content.setMarketTitle(title);
			content.setGuideTitle(title);
			action.setGuideTitle(title);
			
			String linkurl = bid.getCurl();
			action.setLinkurl(linkurl);
			
			content.setMutiAction(Collections.singletonList(action));
			Map<String, List<String>> map = new HashMap<String, List<String>>();
			// 点击监测
			List<String> click = bid.getExtcurlList();
			map.put(CLICK, click);
			
//			// 曝光监测
			List<String> show = bid.getExtiurlList();
			if(show != null && !show.isEmpty()){
				
				show = new ArrayList<String>(show);
				show.add(bid.getIurl());
				map.put(SHOW, show);
			}
			content.setThirdReportLinks(map);
			list.add(content);
		}
		return list;
	}
	private BidRequest parseParams(AdRecomReq adreq, DSPPosition positionInfo){
		
		BidRequest.Builder request = BidRequest.newBuilder();
		request.setId(DigestUtils.md5Hex(UUID.randomUUID().toString()));
		// 是否测试 0否 1是 默认0
		request.setTest(0);
		request.setTmax(300);
		request.setAt(1);
//		request.set
		Scenario.Builder scenario = Scenario.newBuilder();
		scenario.setType(ScenarioType.APP);
		request.setScenario(scenario);
		// 设备信息
		AdUserInfo userInfo = adreq.getUserinfo();
		Device.Builder device = Device.newBuilder();
		// 设备类型 手机
		device.setDevicetype(DeviceType.MOBILE);
		device.setUa(userInfo.getUa());
		device.setOsv(userInfo.getOsversion());
		device.setImei(userInfo.getImei());
		device.setIp(userInfo.getClient_ip());
		device.setConnectiontype(ConnectionType.WIFI);
		device.setMake(userInfo.getBrand_name());
		device.setModel(userInfo.getPhonemodel());
		device.setW(userInfo.getDevice_width());
		device.setH(userInfo.getDevice_height());
		String os = userInfo.getOs();
		if(OS_ANDROID.equals(os)){
			device.setAndroidid(userInfo.getAdid());
		}
		// ios
		else{
			device.setIdfa(userInfo.getIdfa());
		}
		device.setOs(os);
		
		// geo
		Geo.Builder geo = Geo.newBuilder();
		String lat = userInfo.getLat();
		if(!StringUtils.isEmpty(lat)){
			geo.setLat(Float.valueOf(lat));
		}
		String lon = userInfo.getLon();
		if(!StringUtils.isEmpty(lon)){
			geo.setLon(Float.valueOf(lon));
		}
		String city = userInfo.getCity();
		if(!StringUtils.isEmpty(city)){
			geo.setCity(city);
		}
		geo.setType(LocationType.GPS);
		device.setGeo(geo);
		request.setDevice(device);
		// 广告位信息
		UserAdSpaceAttri attri = adreq.getUserAdSpaceAttri();
		Imp.Builder imp = Imp.newBuilder();
		imp.setId(String.valueOf(++impId));
		imp.setBidfloor(Float.valueOf(0));
		
		Banner.Builder banner = Banner.newBuilder();
		banner.setW(attri.getSpaceWidth());
		banner.setH(attri.getSpaceHeight());
		banner.setId(imp.getId());
		banner.addWtype(AdType.IMAGE);
		banner.addWtype(AdType.IMAGETEXT);
		imp.setBanner(banner);
		request.addImp(imp);
		// 应用信息
		App.Builder app = App.newBuilder();
		app.setId(positionInfo.getText1());
		String appName = adreq.getApp();
		if(StringUtils.isEmpty(appName)){
			appName = "Alphago主题商店";
		}
		app.setName(appName);
		app.setBundle("com.alphagolauncher.launcher");
		app.setVer("1.0");
		app.setPaid(0);
		app.addKeywords("主题");
		Publisher.Builder publisher = Publisher.newBuilder();
		publisher.setId("5851fc25c0f179001f026f9d");
		publisher.setDomain("http://www.coolpad.com");
		publisher.setSlot("");
		app.setPublisher(publisher);
		request.setApp(app);
		
		return request.build();
	}

	public GuangyinAdPuller getGuangyinAdPuller() {
		return guangyinAdPuller;
	}

	public void setGuangyinAdPuller(GuangyinAdPuller guangyinAdPuller) {
		this.guangyinAdPuller = guangyinAdPuller;
	}
}
