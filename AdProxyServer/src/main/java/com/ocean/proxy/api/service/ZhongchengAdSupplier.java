package com.ocean.proxy.api.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ocean.core.common.system.SystemContext;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.persist.api.proxy.AdPullException;
import com.ocean.persist.api.proxy.JoinDSPEmu;
import com.ocean.persist.api.proxy.zhongcheng.ZhongchengAdPullParams;
import com.ocean.persist.api.proxy.zhongcheng.ZhongchengAdPuller;
import com.ocean.persist.api.proxy.zhongcheng.ZhongchengAdResponse;
import com.ocean.persist.api.proxy.zhongcheng.ZhongchengNativeCreative;
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
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;

@Component(value="zhongchengAdSupplier")
public class ZhongchengAdSupplier  extends AbstractAdSupplier{
	// 联网类型（0:未知网络，1:以太网， 2:wifi，3:未知移动网络，4:2G网络， 5:3G 网络，6:4G 网络）
	private static final Map<String, Integer> nettype = new HashMap<String, Integer>();
    @Autowired
	private ZhongchengAdPuller zhongchengAdPuller;
	static{
		
		nettype.put(NETWORK_2G, 4);
		nettype.put(NETWORK_3G, 5);
		nettype.put(NETWORK_4G, 6);
		nettype.put(NETWORK_WIFI, 2);
	}
	
	public AdRecomReply invoke(InvokeAttribute attribute) throws AdPullException {
		
		AdRecomReq adreq = attribute.getAdreq();
		DSPPosition positionInfo = attribute.getDspPosition();
		// 参数转换
		ZhongchengAdPullParams params = parseParams(adreq, positionInfo);
		ZhongchengAdResponse response = (ZhongchengAdResponse)zhongchengAdPuller.api(params,adreq.getHash(),String.valueOf(adreq.getUserAdSpaceAttri().getAdSpaceId()));
		// 解析结果
		return parseResult(response);
	}
	
	private AdRecomReply parseResult(ZhongchengAdResponse response)
			throws AdPullException {
		
		if(response == null){
			return null;
		}
		String reptype = response.getReptype();
		// 原生广告返回不是html物料
		if("data".equals(reptype)){
			return getNatAd(response);
		}
		// 返回对象
		AdRecomReply recomReply = new AdRecomReply();
		recomReply.setStatus(status);
		AdContent content = new AdContent();
		AdMutiAction action = new AdMutiAction();
		// 落地方式，redirect为网址，download为下载
		String atype = response.getAdtype();
		if("download".equals(atype)){
			action.setType(ACTION_APP);
		}
		else{
			action.setType(ACTION_WEB);
		}
		content.setMarketTitle(defTitle);
		content.setGuideTitle(defTitle);
		action.setGuideTitle(defTitle);
		
		String ma = response.getMa();
		// 只返回html的物料
		content.setHtmlSnippet(new String(Base64.decodeBase64(ma)));
		content.setIsHtmlAd(true);
		List<AdImg> imgs = new ArrayList<AdImg>();
//		AdImg img = new AdImg();
		// 图片地址
		content.setImglist(imgs);
//		action.setLinkurl(response.getHref());
		
		content.setMutiAction(Collections.singletonList(action));
		//content.setAdId(InvenoIdGenerator.genThirdDspId(SystemContext.getDynamicPropertyHandler().getInt(ProxyConstants.PROXY_SERVER_NODE_INDEX,100)));

		
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		// 点击监测
		List<String> click = response.getClick_url();
		if(click == null){
			click = new ArrayList<String>();
		}
		map.put(CLICK, click);
		// 曝光监测
		List<String> show = response.getImpr_url();
		if(show == null){
			show = new ArrayList<String>();
		}
		map.put(SHOW, show);
		
		content.setThirdReportLinks(map);
		recomReply.setAd_contents(Collections.singletonList(content));
		
		return recomReply;
	}
	
	private AdRecomReply getNatAd(ZhongchengAdResponse response) throws AdPullException {
		
		
		List<ZhongchengNativeCreative> nats = response.getBatch_body();
		if(nats == null || nats.isEmpty()){
			return null;
		}
		ZhongchengNativeCreative natad = nats.get(0);
		// 返回对象
		AdRecomReply recomReply = new AdRecomReply();
		recomReply.setStatus(status);
		AdContent content = new AdContent();
		AdMutiAction action = new AdMutiAction();
		// 落地方式，redirect为网址，download为下载
		String atype = natad.getAdtype();
		if("download".equals(atype)){
			action.setType(ACTION_APP);
		}
		else{
			action.setType(ACTION_WEB);
		}
		String title = natad.getTitle();
		if(StringUtils.isEmpty(title)){
			title = defTitle;
		}
		content.setMarketTitle(title);
		content.setGuideTitle(title);
		action.setGuideTitle(title);
		
		// 原生广告不是html
		content.setIsHtmlAd(false);
		List<AdImg> imgs = new ArrayList<AdImg>();
		AdImg img = new AdImg();
		img.setSrc(natad.getImage());
		imgs.add(img);
		// 图片地址
		content.setImglist(imgs);
		action.setLinkurl(natad.getLanding_url());
		
		List<AdMutiAction> actions = new ArrayList<AdMutiAction>();
		actions.add(action);
		content.setMutiAction(actions);
		content.setAdId(InvenoIdGenerator.genThirdDspId(JoinDSPEmu.ZHONGCHENG.getValue()));
		
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		// 点击监测
		List<String> click = natad.getClick_url();
		if(click == null){
			click = new ArrayList<String>();
		}
		map.put(CLICK, click);
		// 曝光监测
		List<String> show = natad.getImpr_url();
		if(show == null){
			show = new ArrayList<String>();
		}
		map.put(SHOW, show);
		
		content.setThirdReportLinks(map);
		recomReply.setAd_contents(Collections.singletonList(content));
		
		return recomReply;
	}
	
	private ZhongchengAdPullParams parseParams(AdRecomReq adreq, 
			DSPPosition positionInfo) throws AdPullException {
		UserAdSpaceAttri attri = adreq.getUserAdSpaceAttri();
		AdUserInfo userInfo = adreq.getUserinfo();
		ZhongchengAdPullParams params = new ZhongchengAdPullParams();
		params.setAaid(userInfo.getAaid());
		params.setAdh(attri.getSpaceHeight());
		params.setAdw(attri.getSpaceWidth());
		params.setAdid(userInfo.getAdid());
		params.setAdunitid(positionInfo.getPos());
		params.setApi_ver("2.0");
		// 媒体展示 id，唯一标识一个媒体，一个媒体只有一个 appid 
		params.setAppid(positionInfo.getText1());
		params.setAppname("coolshow");
		// 请求原生广告数量，原生广告必填  // 取值可为 1,2,3 
		params.setBatch_count(1);
		// 是否越狱 1：是, 0：否/未知
		params.setBrk(0);
		// 设备类型 （-1:未知 ,0:手机,1:pad, 2:pc, 3:tv,4:wap）
		params.setDevicetype(0);
		params.setDvh(userInfo.getDevice_height());
		params.setDvw(userInfo.getDevice_width());
		String lat = userInfo.getLat(), lon = userInfo.getLon();
		if(!StringUtils.isEmpty(lat) && !StringUtils.isEmpty(lon)){
			params.setGeo(lat + "," + lon);
		}
		params.setIdfa(userInfo.getIdfa());
		params.setImei(userInfo.getImei());
		params.setIp(userInfo.getClient_ip());
		params.setLan("zh-CN");
		params.setMac(userInfo.getMac());
		params.setModel(encode(userInfo.getPhonemodel()));
		// 联网类型（0:未知网络，1:以太网， 2:wifi，3:未知移动网络，4:2G网络， 5:3G 网络，6:4G 网络）
		Integer net = nettype.get(adreq.getNet());
		if(net == null){
			net = 0;
		}
		params.setNet(net);
//		params.setOpenudid(openudid)
		params.setOperator(mobiles.get(userInfo.getMobile()));
//		params.setOrientation(orientation)
		params.setOs(userInfo.getOs());
		params.setOsv(userInfo.getOsversion());
		params.setPkg("com.zookingsoft.coolshow");
		params.setTs(String.valueOf(System.currentTimeMillis() / 1000));
		params.setUa(encode(userInfo.getUa()));
		params.setVendor(userInfo.getBrand_name());
//		params.setVersion(version)
		return params;
	}
	public ZhongchengAdPuller getZhongchengAdPuller() {
		return zhongchengAdPuller;
	}

	public void setZhongchengAdPuller(ZhongchengAdPuller zhongchengAdPuller) {
		this.zhongchengAdPuller = zhongchengAdPuller;
	}

}
