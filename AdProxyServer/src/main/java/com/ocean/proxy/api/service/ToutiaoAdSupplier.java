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

import com.inveno.util.CollectionUtils;
import com.ocean.persist.api.proxy.AdPullException;
import com.ocean.persist.api.proxy.diankai.DiankaiAd;
import com.ocean.persist.api.proxy.diankai.DiankaiAdPullResponse;
import com.ocean.persist.api.proxy.toutiao.AppLianmengApiv18;
import com.ocean.persist.api.proxy.toutiao.AppLianmengApiv18.BidRequest;
import com.ocean.persist.api.proxy.toutiao.AppLianmengApiv18.BidRequest.AdSlot;
import com.ocean.persist.api.proxy.toutiao.AppLianmengApiv18.BidRequest.AdSlot.AdType;
import com.ocean.persist.api.proxy.toutiao.AppLianmengApiv18.BidRequest.AdSlot.Position;
import com.ocean.persist.api.proxy.toutiao.AppLianmengApiv18.BidRequest.AdSlot.Size;
import com.ocean.persist.api.proxy.toutiao.AppLianmengApiv18.BidRequest.App;
import com.ocean.persist.api.proxy.toutiao.AppLianmengApiv18.BidRequest.Device;
import com.ocean.persist.api.proxy.toutiao.AppLianmengApiv18.BidRequest.Device.ConnectionType;
import com.ocean.persist.api.proxy.toutiao.AppLianmengApiv18.BidRequest.Device.DeviceType;
import com.ocean.persist.api.proxy.toutiao.AppLianmengApiv18.BidRequest.Device.OsType;
import com.ocean.persist.api.proxy.toutiao.AppLianmengApiv18.BidRequest.Geo;
import com.ocean.persist.api.proxy.toutiao.AppLianmengApiv18.BidResponse;
import com.ocean.persist.api.proxy.toutiao.AppLianmengApiv18.BidResponse.Ad;
import com.ocean.persist.api.proxy.toutiao.AppLianmengApiv18.BidResponse.Ad.MaterialMeta;
import com.ocean.persist.api.proxy.toutiao.AppLianmengApiv18.BidResponse.Ad.MaterialMeta.Image;
import com.ocean.persist.api.proxy.toutiao.AppLianmengApiv18.BidResponse.Ad.MaterialMeta.InteractionType;
import com.ocean.persist.api.proxy.toutiao.ToutiaoAdPuller;
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
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
@Component(value="toutiaoAdSupplier")
public class ToutiaoAdSupplier   extends AbstractAdSupplier{
	private static final Map<String, OsType> os = new HashMap<String, OsType>(3);
	private static final Map<AdSpaceType, Position> position = new HashMap<AdSpaceType, Position>(6);
	private static final Map<InteractionType, Integer> interaction = new HashMap<InteractionType, Integer>(6);
	private static final Map<AdSpaceType, AdType> adtype = new HashMap<AdSpaceType, AdType>();
	@Autowired
	private ToutiaoAdPuller toutiaoAdPuller;
	static{
		os.put(OS_ANDROID, OsType.ANDROID);
		os.put(OS_IOS, OsType.IOS);
		
		position.put(AdSpaceType.BANNER, Position.TOP);
		position.put(AdSpaceType.INTERSTITIAL, Position.MIDDLE);
		position.put(AdSpaceType.INFOFLOW, Position.FLOW);
		position.put(AdSpaceType.OPENING, Position.FULLSCREEN);
		
		// 1表示链接推广,2表示应用推广,3未使用 4表示电话推广
		interaction.put(InteractionType.NO_INTERACTION, 0);
		interaction.put(InteractionType.SURFING, 1);
		interaction.put(InteractionType.IN_APP, 1);
		interaction.put(InteractionType.DOWLOAD, 2);
		
		// 广告位类型
		adtype.put(AdSpaceType.BANNER, AdType.BANNER);
		adtype.put(AdSpaceType.INTERSTITIAL, AdType.INTERSTITIAL);
		adtype.put(AdSpaceType.INFOFLOW, AdType.STREAM);
		adtype.put(AdSpaceType.OPENING, AdType.SPLASH);
	}
	
	public AdRecomReply invoke(InvokeAttribute attribute) throws AdPullException {
		AdRecomReq adreq = attribute.getAdreq();
		DSPPosition positionInfo = attribute.getDspPosition();
		// 参数转换
		BidRequest params = parseParams(adreq, positionInfo);
		BidResponse response = (BidResponse) toutiaoAdPuller.api(params,adreq.getHash(),String.valueOf(adreq.getUserAdSpaceAttri().getAdSpaceId()));
		return parseResult(response);
	}
	
	private AdRecomReply parseResult(BidResponse response)
			throws AdPullException {
		
		if(response == null||CollectionUtils.isEmpty(response.getAdsList())){
			return null;
		}
		// 返回对象
		AdRecomReply recomReply = new AdRecomReply();
		recomReply.setStatus(status);
		recomReply.setAd_contents(this.dealAds(response));
		return recomReply;
	}
	private List<AdContent>  dealAds(BidResponse resp){
		List<AdContent> list=new ArrayList<AdContent> ();
		for(Ad ad:resp.getAdsList()){
			
			MaterialMeta meta = ad.getCreative();
			if(meta == null){
				return null;
			}
			AdContent content = new AdContent();
			AdMutiAction action = new AdMutiAction();
			AppLianmengApiv18.BidResponse.Ad.MaterialMeta.InteractionType it = meta.getInteractionType();
			if(it == MaterialMeta.InteractionType.DOWLOAD){
				action.setCpName(meta.getAppName());
				action.setCpclass(meta.getPackageName());
				action.setCpApk(meta.getDownloadUrl());
				action.setLinkurl(meta.getDownloadUrl());
			}
			// 1表示链接推广,2表示应用推广,3未使用 4表示电话推广
			Integer type = interaction.get(it);
			if(type == null){
				type = ACTION_WEB;
			}
			action.setType(type);
			// 素材图片
			Image image = meta.getImage();
			List<AdImg> imgs = new ArrayList<AdImg>();
			AdImg img = new AdImg();
			img.setSrc(image.getUrl());
			img.setWidth(image.getWidth());
			img.setHeight(image.getHeight());
			imgs.add(img);

			if(CollectionUtils.isNotEmpty(meta.getImageListList())){
				for(Image tem:meta.getImageListList()){
					AdImg imgT = new AdImg();
					imgT.setSrc(tem.getUrl());
					imgT.setWidth(tem.getWidth());
					imgT.setHeight(tem.getHeight());
					imgs.add(imgT);
				}
			}
			content.setImglist(imgs);

			
			String title = meta.getTitle();
			if(StringUtils.isEmpty(title)){
				title = defTitle;
			}
			content.setContent(meta.getDescription());
			content.setMarketTitle(title);
			content.setGuideTitle(title);
			action.setGuideTitle(title);
			
			String linkurl = meta.getTargetUrl();
			if(StringUtils.isNotEmpty(linkurl)){
				action.setLinkurl(linkurl);
			}
		
			
			content.setMutiAction(Collections.singletonList(action));
			Map<String, List<String>> map = new HashMap<String, List<String>>();
			// 点击监测
			List<String> click = meta.getClickUrlList();
			map.put(CLICK, click);
			// 曝光监测
			List<String> show = meta.getShowUrlList();
			map.put(SHOW, show);
			content.setThirdReportLinks(map);
			list.add(content);
		}
		return list;
	}
	private BidRequest parseParams(AdRecomReq adreq, DSPPosition positionInfo) throws AdPullException{
		if(StringUtils.isEmpty(positionInfo.getText1())||StringUtils.isEmpty(positionInfo.getText3())){
			throw  new AdPullException("appId ,or package name is empty!");
		}
		// 设备信息
		AdUserInfo userInfo = adreq.getUserinfo();
		BidRequest.Builder request = BidRequest.newBuilder();
		request.setRequestId(DigestUtils.md5Hex(UUID.randomUUID().toString()));
		request.setApiVersion("1.8");
		//request.setUid(positionInfo.getText2());
		request.setUid(adreq.getOgin_name());

		request.setSourceType("app");
		request.setUa(userInfo.getUa());
		request.setIp(userInfo.getClient_ip());
		
		//device
		Device.Builder device = Device.newBuilder();
		// 设备类型 手机
		device.setDid("");
		device.setImei(userInfo.getImei());
		if(StringUtils.isNotEmpty(userInfo.getAdid())){
			device.setAndroidId(userInfo.getAdid());
		}
		
		
		device.setType(DeviceType.PHONE);
		device.setOsVersion(userInfo.getOsversion());
		device.setModel(userInfo.getPhonemodel());
		device.setType(DeviceType.PHONE);
		OsType osType = os.get(userInfo.getOs());
		if(osType == null){
			osType = OsType.WINDOWS;
		}
		device.setOs(osType);
		device.setOsVersion(this.convOSVersion(userInfo.getOsversion()));
		device.setVendor(userInfo.getBrand_name());
		device.setModel(userInfo.brand_name);
		device.setLanguage("中文");
		device.setConnType(getCntType(adreq.getNet()));
		device.setMac(userInfo.getMac());
		device.setScreenHeight(userInfo.getDevice_height());
		device.setScreenWidth(userInfo.getDevice_width());
		
		request.setDevice(device);
		
		
		// 广告位信息
		UserAdSpaceAttri attri = adreq.getUserAdSpaceAttri();
		AdSlot.Builder adSlot = AdSlot.newBuilder();
		// 返回记录数 目前只支持1条
		adSlot.setId(positionInfo.getPos());
		adSlot.setAdtype(adtype.get(attri.getSpaceType()));
		adSlot.setPos(position.get(attri.getSpaceType()));
		
		Size.Builder size=Size.newBuilder();
		size.setHeight(attri.getSpaceHeight());
		size.setWidth(attri.getSpaceWidth());
		adSlot.addAcceptedSize(size);
		adSlot.setAdCount(adreq.getResult_num());		
		request.addAdslots(adSlot);
		
		
		// 应用信息
		App.Builder app = App.newBuilder();
		app.setAppid(positionInfo.getText1());
		app.setName(adreq.getApp());
		app.setPackageName(positionInfo.getText3());
		app.setVersion(adreq.getVersion());
		
		// geo
		Geo.Builder geo = Geo.newBuilder();
		String lat = userInfo.getLat();
		String lon = userInfo.getLon();
		if(StringUtils.isNotEmpty(lat)&&StringUtils.isNotEmpty(lon)){
			geo.setLatitude(Float.valueOf(lat));
			geo.setLongitude(Float.valueOf(lon));
			geo.setCity(userInfo.getCity());
			app.setGeo(geo);
		}

		request.setApp(app);
		return request.build();
	}
	private ConnectionType getCntType(String net){
		if(NETWORK_WIFI.equals(net)){
			return ConnectionType.WIFI;
		}else if(NETWORK_2G.equals(net)){
			return ConnectionType.MOBILE_2G;
		}
		else if(NETWORK_3G.equals(net)){
			return ConnectionType.MOBILE_3G;
		}
		else if(NETWORK_4G.equals(net)){
			return ConnectionType.MOBILE_4G;
		}

		return ConnectionType.CONN_UNKNOWN;
	} 
	public ToutiaoAdPuller getToutiaoAdPuller() {
		return toutiaoAdPuller;
	}

	public void setToutiaoAdPuller(ToutiaoAdPuller toutiaoAdPuller) {
		this.toutiaoAdPuller = toutiaoAdPuller;
	}

}
