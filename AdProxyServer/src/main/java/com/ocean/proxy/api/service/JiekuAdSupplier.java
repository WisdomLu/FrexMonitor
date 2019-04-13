package com.ocean.proxy.api.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ocean.core.common.system.SystemContext;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
import com.ocean.persist.api.proxy.AdPullException;
import com.ocean.persist.api.proxy.diankai.DiankaiAd;
import com.ocean.persist.api.proxy.diankai.DiankaiAdPullResponse;
import com.ocean.persist.api.proxy.jieku.JiekuAdContent;
import com.ocean.persist.api.proxy.jieku.JiekuAdPullParams;
import com.ocean.persist.api.proxy.jieku.JiekuAdPuller;
import com.ocean.persist.api.proxy.jieku.JiekuAdResponse;
import com.ocean.persist.api.proxy.jieku.JiekuAdm;
import com.ocean.persist.api.proxy.jieku.JiekuAdslot;
import com.ocean.persist.api.proxy.jieku.JiekuApp;
import com.ocean.persist.api.proxy.jieku.JiekuBrowser;
import com.ocean.persist.api.proxy.jieku.JiekuClient;
import com.ocean.persist.api.proxy.jieku.JiekuDevice;
import com.ocean.persist.api.proxy.jieku.JiekuGeo;
import com.ocean.persist.api.proxy.jieku.JiekuId;
import com.ocean.persist.api.proxy.jieku.JiekuMedia;
import com.ocean.persist.api.proxy.jieku.JiekuNetwork;
import com.ocean.persist.api.proxy.jieku.JiekuSize;
import com.ocean.persist.api.proxy.jieku.JiekuVersion;
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
@Component(value="jiekuAdSupplier")
public class JiekuAdSupplier extends AbstractAdSupplier{
    @Autowired
	private   JiekuAdPuller jiekuAdPuller ;
	
	// 1.BANNER(横幅) 4.INITIALIZATION(开屏) 5.INSERT(插屏) 9.NATIVE(信息流)
	private static final Map<AdSpaceType, Integer> adtype = new HashMap<AdSpaceType, Integer>();
	
	private static final Map<String, Integer> conn = new HashMap<String, Integer>();
	
	private static final JiekuVersion apiVersion = new JiekuVersion("1.3.0.0");
	
	static{
		adtype.put(AdSpaceType.BANNER, 1);
		adtype.put(AdSpaceType.OPENING, 4);
		adtype.put(AdSpaceType.INTERSTITIAL, 5);
		adtype.put(AdSpaceType.INFOFLOW, 9);
		
		conn.put(NETWORK_2G, 3);
		conn.put(NETWORK_3G, 4);
		conn.put(NETWORK_4G, 5);
		conn.put(NETWORK_WIFI, 1);
	}
	
	public AdRecomReply invoke(InvokeAttribute attribute) throws AdPullException {
		
		AdRecomReq adreq = attribute.getAdreq();
		DSPPosition positionInfo = attribute.getDspPosition();
		boolean isNative = positionInfo.getPosType() == AdSpaceType.INFOFLOW.getValue();
		// 参数转换
		JiekuAdPullParams params = parseParams(adreq, positionInfo, isNative);
		// 调用API
		JiekuAdResponse response = (JiekuAdResponse)jiekuAdPuller.api(params,adreq.getHash(),String.valueOf(adreq.getUserAdSpaceAttri().getAdSpaceId()));
		
		return parseResult(response, isNative);
	}
	
	private AdRecomReply parseResult(JiekuAdResponse response, boolean isNative)
			throws AdPullException {
		
		if(response == null){
			return null;
		}
		List<JiekuAdContent> ads = response.getAds();
		if(ads == null || ads.isEmpty()){
			return null;
		}
		// 返回对象
		AdRecomReply recomReply = new AdRecomReply();
		recomReply.setStatus(status);
		recomReply.setAd_contents(this.dealAds(ads));
		return recomReply;
	}
	private List<AdContent>  dealAds(List<JiekuAdContent> ads){
		List<AdContent> list=new ArrayList<AdContent> ();
		for(JiekuAdContent ad:ads){
			
			AdContent content = new AdContent();
			AdMutiAction action = new AdMutiAction();
			int actType=ACTION_WEB;
			if(ad.getNative_material()!=null&&ad.getNative_material().getInteraction_type()==2){
				actType=ACTION_APP;
			}
			String title = null, desc = null;
			// 0.DYNAMIC(HTML) 1.NATIVE(Native)
			if(1 == ad.getMaterial_type()){
				JiekuAdm adm = ad.getNative_material();
				action.setLinkurl(adm.getClick_url());
				List<AdImg> imgs = new ArrayList<AdImg>();
				String imgurl = adm.getImage_url();
				if(!StringUtils.isEmpty(imgurl)){
					AdImg img = new AdImg();
					img.setSrc(imgurl);
					JiekuSize size = adm.getImage_size();
					if(size != null){
						img.setWidth(size.getWidth());
						img.setHeight(size.getHeight());
					}
					imgs.add(img);
				}
				String icon = adm.getLogo_url();
				if(StringUtils.isEmpty(icon)){
					content.setCpIcon(icon);
					action.setCpIcon(icon);
					if(imgs.isEmpty()){
						AdImg img = new AdImg();
						img.setSrc(icon);
						JiekuSize size = adm.getLogo_size();
						if(size != null){
							img.setWidth(size.getWidth());
							img.setHeight(size.getHeight());
						}
						imgs.add(img);
					}
				}
				// 图片地址
				content.setImglist(imgs);

				desc = adm.getDescription1();
				title = adm.getTitle();
				// 监测
				Map<String, List<String>> map = new HashMap<String, List<String>>();
				// 点击监测
				List<String> urls = adm.getClick_monitor_url();
				if(urls != null){
					map.put(CLICK, urls);
				}
				// 曝光监测
				urls = adm.getImpression_log_url();
				if(urls != null){
					map.put(SHOW, urls);
				}
				// app下载监控
				urls = adm.getAppDownload();
				if(urls != null){
					actType=ACTION_APP;
					map.put(DOWNLOAD, urls);
				}
				// app安装监控
				urls = adm.getAppInstall();
				if(urls != null){
					actType=ACTION_APP;
					map.put(INSTALL, urls);
				}
				// app激活监控
				urls = adm.getAppActive();
				if(urls != null){
					actType=ACTION_APP;
					map.put(ACTIVE, urls);
				}
				content.setThirdReportLinks(map);
			}
			// 非原生广告都是html物料
			else{
				content.setHtmlSnippet(ad.getHtml_snippet());
				content.setIsHtmlAd(true);
			}
			
			if(StringUtils.isEmpty(title)){
				title = defTitle;
			}
			action.setType(actType);
			content.setMarketTitle(title);
			content.setGuideTitle(title);
			action.setGuideTitle(title);
			content.setContent(desc);
			content.setMutiAction(Collections.singletonList(action));
			list.add(content);
		}
		return list;
		
	}
	private JiekuAdPullParams parseParams(AdRecomReq adreq, 
			DSPPosition positionInfo, boolean isNative){
		
		AdUserInfo userInfo = adreq.getUserinfo();
		UserAdSpaceAttri attri = adreq.getUserAdSpaceAttri();
		JiekuAdPullParams params = new JiekuAdPullParams();
		
		// ---------媒体信息
		JiekuApp app = new JiekuApp();
		app.setPackage_name("com.alphagolauncher.launcher");
		JiekuBrowser browser = new JiekuBrowser();
		browser.setUser_agent(userInfo.getUa());
		JiekuMedia media = new JiekuMedia();
		media.setApp(app);
		// 媒体类别(1.app 2.web 3.wap)
		media.setType(1);
		media.setBrowser(browser);
		params.setMedia(media);
		// ---------媒体信息 end
		
		// ---------设备信息
		JiekuDevice device = new JiekuDevice();
		// 0. DEV_UNKONWN 1.PC 2.DEV_PHONE 3.TABLER 4.TV
		device.setType(2);
		device.setOs_version(new JiekuVersion(userInfo.getOsversion()));
		device.setModel(userInfo.getPhonemodel());
		device.setBrand(userInfo.getBrand_name());
		device.setScreen_size(new JiekuSize(
				userInfo.getDevice_width(), userInfo.getDevice_height()));
		String os = userInfo.getOs();
		// (1.IMEI 2.MAC 3.IDFA 6. ANDROIDID 7.IMSI) 
		// 注意：
		// android设备必须包含
		// IMEI，MAC，
		// ANDROIDID，IMSI
		// iOS设备必须包含
		// IDFA
		List<JiekuId> ids = new ArrayList<JiekuId>();
		device.setIds(ids);
		// android
		if(OS_ANDROID.equals(os)){
			// (0.OS_UNKNOWN 1.ANDROID 2.IOS 3.WP)
			device.setOs_type(1);
			ids.add(new JiekuId(1, userInfo.getImei()));
			String id = userInfo.getMac();
			if(!StringUtils.isEmpty(id)){
				ids.add(new JiekuId(2, id));
			}
			id = userInfo.getAdid();
			if(!StringUtils.isEmpty(id)){
				ids.add(new JiekuId(6, id));
			}
			id = userInfo.getImsi();
			if(!StringUtils.isEmpty(id)){
				ids.add(new JiekuId(7, id));
			}
		}
		// ios
		else{
			device.setOs_type(2);
			ids.add(new JiekuId(3, userInfo.getIdfa()));
		}
		params.setDevice(device);
		// ---------设备信息 end
		
		// ---------网络信息
		JiekuNetwork network = new JiekuNetwork();
		network.setCellular_id(mobiles.get(userInfo.getMobile()));
		network.setIp(userInfo.getClient_ip());
		network.setType(conn.get(adreq.getNet()));
		params.setNetwork(network);
		// ---------网络信息 end
		
		// ---------客户端信息
		JiekuClient client = new JiekuClient();
		// 广告客户端类型 (固定值 3)
		client.setType(3);
		// 版本(API接入目前版本是 1.3.0.0)
		client.setVersion(apiVersion);
		params.setClient(client);
		// ---------客户端信息 end
		
		// ---------坐标信息
		String lat = userInfo.getLat(), lon = userInfo.getLon();
		if(!StringUtils.isEmpty(lat) && !StringUtils.isEmpty(lon)){
			JiekuGeo geo = new JiekuGeo();
			// 坐标类型 (1. WGS84 2. GCJ02 3. BD09)
			geo.setType(1);
			geo.setLatitude(Double.valueOf(lat));
			geo.setLongitude(Double.valueOf(lon));
			params.setGeo(geo);
		}
		// ---------坐标信息 end
		
		// 广告位信息
		for(int  i=0;i<adreq.getResult_num();i++){
			JiekuAdslot adslot = new JiekuAdslot();
			adslot.setId(positionInfo.getPos());
			adslot.setSize(new JiekuSize(
					attri.getSpaceWidth(), attri.getSpaceHeight()));
			adslot.setType(adtype.get(attri.getSpaceType()));
			
			params.setAdslots(Collections.singletonList(adslot));
		}

		// 广告位信息 end
		return params;
	}

	public JiekuAdPuller getJiekuAdPuller() {
		return jiekuAdPuller;
	}

	public void setJiekuAdPuller(JiekuAdPuller jiekuAdPuller) {
		this.jiekuAdPuller = jiekuAdPuller;
	}


}
