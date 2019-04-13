package com.ocean.proxy.api.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ocean.core.common.threadpool.Parameter;
import com.ocean.persist.api.proxy.AdPullException;
import com.ocean.persist.api.proxy.vamaker.VamakerAdPullParams;
import com.ocean.persist.api.proxy.vamaker.VamakerAdPuller;
import com.ocean.persist.api.proxy.vamaker.VamakerAdResponse;
import com.ocean.persist.model.proxy.DSPPosition;
import com.ocean.proxy.api.base.AbstractAdSupplier;
import com.ocean.proxy.thrift.entity.AdContent;
import com.ocean.proxy.thrift.entity.AdImg;
import com.ocean.proxy.thrift.entity.AdMutiAction;
import com.ocean.proxy.thrift.entity.AdRecomReply;
import com.ocean.proxy.thrift.entity.AdRecomReq;
import com.ocean.proxy.thrift.entity.AdUserInfo;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;

@Component(value="vamakerAdSupplier")
public class VamakerAdSupplier   extends AbstractAdSupplier{
	@Autowired
	private   VamakerAdPuller vamakerAdPuller ;
	
	private static final Map<String, Integer> mobile = new HashMap<String, Integer>();
	
	private static final Map<String, Integer> os = new HashMap<String, Integer>();
	
//	private static final String videoPos = "1483212600";
	static{
		
		// 运营商
		mobile.put("CMCC", 1);
		mobile.put("CUCC", 2);
		mobile.put("CTCC", 3);
		
		// 操作系统
		os.put(OS_ANDROID, 1);
		os.put(OS_IOS, 2);
	}

	public AdRecomReply invoke(InvokeAttribute attribute) throws AdPullException {
		
		AdRecomReq adreq = attribute.getAdreq();
		DSPPosition positionInfo = attribute.getDspPosition();
		String pid = positionInfo.getPos();
//		if(ANDROID_OS.equalsIgnoreCase(userInfo.getOs())){
//			pid = pos.get(attri.getAdSpaceId());
//		}else{
//			pid="1804472100";
//		}
		// 参数转换
		VamakerAdPullParams params = parseParams(adreq, pid);
		
		VamakerAdResponse response = (VamakerAdResponse)vamakerAdPuller.api(params,adreq.getHash(),String.valueOf(adreq.getUserAdSpaceAttri().getAdSpaceId()));
		if(!params.getIsVideo()){
			return parseResult(response);
		}
		return null;
	}
	
	private AdRecomReply parseResult(VamakerAdResponse response)
			throws AdPullException {
		
		// 返回对象
		AdRecomReply recomReply = new AdRecomReply();
		recomReply.setStatus(status);
		
		String title = response.getTitle();
		if(StringUtils.isEmpty(title)){
			title = defTitle;
		}
		AdContent content = new AdContent();
		content.setMarketTitle(title);
		content.setGuideTitle(title);
		content.setContent(response.getDescprtion());
		
		List<String> list = response.getImg();
		if(list == null || list.isEmpty()){
			throw new AdPullException("广告物料信为空");
		}
		
		// 广告图片
		List<AdImg> imgs = new ArrayList<AdImg>();
		for (String string : list) {
			AdImg img = new AdImg();
			img.setSrc(string);
			
			imgs.add(img);
		}
		content.setImglist(imgs);
		
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		// 点击监测
		List<String> click = response.getCm();
		if(click == null){
			click = new ArrayList<String>();
		}
		map.put(CLICK, click);
		// 曝光监测
		List<String> show = response.getPm();
		if(show == null){
			show = new ArrayList<String>();
		}
		map.put(SHOW, show);
		
		content.setThirdReportLinks(map);
		
		// 类型
		AdMutiAction action = new AdMutiAction();
		// 内容类型1表示链接推广,2表示应用推广,3未使用 4表示电话推广
		action.setType(ACTION_WEB);
		action.setLinkurl(response.getLp());
		action.setGuideTitle(title);
		
		content.setMutiAction(Collections.singletonList(action));
		recomReply.setAd_contents(Collections.singletonList(content));
		
		return recomReply;
	}
	
	private VamakerAdPullParams parseParams(AdRecomReq adreq, String pid){
		
//		UserAdSpaceAttri attri = adreq.getUserAdSpaceAttri();
		AdUserInfo userInfo = adreq.getUserinfo();
		VamakerAdPullParams params = new VamakerAdPullParams();
		// 设置后拿不到广告
//		params.set_adh(attri.getSpaceHeight());
//		params.set_adw(attri.getSpaceWidth());
		params.set_a(pid);
		params.set_pgn(adreq.getApp());
		params.set_osv(userInfo.getOsversion());
		params.set_ip(userInfo.getClient_ip());
		params.set_lon(userInfo.getLon());
		params.set_lat(userInfo.getLat());
		// 1安卓 2iOS
		params.set_os(os.get(userInfo.getOs()));
		params.set_o(mobile.get(userInfo.getMobile()));
//		params.set_nt("WIFI");
		// 安卓必填参数
		if(OS_ANDROID.equalsIgnoreCase(userInfo.getOs())){
			params.set_imei(userInfo.getImei());
			params.set_md(userInfo.getPhonemodel());
//			params.set_aaid(aaid);
//			params.set_aid(androidId);
			params.set_mc(defMac.replace(":", ""));
		}
		// ios必填参数
		else{
			// 苹果的先写死
			params.set_idfa(userInfo.getIdfa());
		}
		params.setIsVideo(false);
//		params.set_debug(1);
		params.set_t(24);
//		params.set_w(1080);
//		params.set_h(1920);
		return params;
	}
	public VamakerAdPuller getVamakerAdPuller() {
		return vamakerAdPuller;
	}

	public void setVamakerAdPuller(VamakerAdPuller vamakerAdPuller) {
		this.vamakerAdPuller = vamakerAdPuller;
	}

}
