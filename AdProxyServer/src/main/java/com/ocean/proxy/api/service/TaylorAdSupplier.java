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
import com.ocean.persist.api.proxy.AdPullException;
import com.ocean.persist.api.proxy.diankai.DiankaiAd;
import com.ocean.persist.api.proxy.diankai.DiankaiAdPullResponse;
import com.ocean.persist.api.proxy.taylor.TaylorAdDetails;
import com.ocean.persist.api.proxy.taylor.TaylorAdPullParams;
import com.ocean.persist.api.proxy.taylor.TaylorAdPuller;
import com.ocean.persist.api.proxy.taylor.TaylorAdReport;
import com.ocean.persist.api.proxy.taylor.TaylorAdResponse;
import com.ocean.persist.common.ProxyConstants;
import com.ocean.persist.model.proxy.DSPPosition;
import com.ocean.proxy.api.base.AbstractAdSupplier;
import com.ocean.proxy.thrift.entity.AdContent;
import com.ocean.proxy.thrift.entity.AdImg;
import com.ocean.proxy.thrift.entity.AdMutiAction;
import com.ocean.proxy.thrift.entity.AdRecomReply;
import com.ocean.proxy.thrift.entity.AdRecomReq;
import com.ocean.proxy.thrift.entity.AdUserInfo;
import com.ocean.proxy.thrift.entity.UserAdSpaceAttri;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;

@Component(value="taylorAdSupplier")
public class TaylorAdSupplier extends AbstractAdSupplier{
    @Autowired
	private TaylorAdPuller taylorAdPuller ;
	
	private static final int success = 0;
	
	public AdRecomReply invoke(InvokeAttribute attribute) throws AdPullException {
		
		AdRecomReq adreq = attribute.getAdreq();
		DSPPosition positionInfo = attribute.getDspPosition();
		// 参数转换
		UserAdSpaceAttri attri = adreq.getUserAdSpaceAttri();
		AdUserInfo userInfo = adreq.getUserinfo();

		TaylorAdPullParams params = new TaylorAdPullParams();
		params.setHeight(attri.getSpaceHeight());
		params.setWidth(attri.getSpaceWidth());
		params.setAppname(adreq.getApp());
		params.setVer(SystemContext.getDynamicPropertyHandler().get(ProxyConstants.TAYLORMEDIA_VERSION));
		params.setAppversion(adreq.getVersion());
		params.setModel(userInfo.getPhonemodel());
		params.setIp(userInfo.getClient_ip());
		params.setOsversion(convOSVersion(userInfo.getOsversion()));
		params.setLanguage("zh-CN");
		params.setAppid(positionInfo.getText1());
		params.setAppkey(positionInfo.getText2());
		if(userInfo.getOs().equalsIgnoreCase("ios")){
			params.setOs(String.valueOf(0));
			params.setIdfa(userInfo.getImei());
//			params.setAppid("5D9A4B7AE9FE404DA69529FD3A7B9E53");
//			params.setAppkey("166F2CFD59E92886B1EBEF92FE16D33D");
		}
		else{
			params.setOs(String.valueOf(1));
//			params.setAppid("FD218B3B2F85454CAA8AD13AF44BF2C2");
//			params.setAppkey("B8CCF2CD23E9BFA1CE0756D8610420E3");
			params.setImei(userInfo.getImei());
		}
		// Android: 1, IOS: 2
		if(StringUtils.isEmpty(userInfo.getMobile())){
			params.setImsi("unknown");
		}else{
			String imsi=mobiles.get(userInfo.getMobile());
			params.setImsi(StringUtils.isEmpty(imsi)?"unknown":imsi);
			
		}

		params.setNetwork("wifi");
		params.setAndroidid(userInfo.getAdid());
		// 广告位
		params.setLid(positionInfo.getPos());
		params.setApppackagename(pgn);
		
		params.setMac(defMac);
		
		// 屏幕宽高
		params.setScreenheight(1920);
		params.setScreenwidth(1080);
		params.setTime(String.valueOf(System.currentTimeMillis()));
		params.setWifissid("");
		params.setUa(userInfo.getUa());
		TaylorAdResponse response = (TaylorAdResponse)taylorAdPuller.api(params,adreq.getHash(),String.valueOf(adreq.getUserAdSpaceAttri().getAdSpaceId()));
		
		return parseResult(response);
	}
	
	private AdRecomReply parseResult(TaylorAdResponse response)
			throws AdPullException {
		
		if(response == null){
			return null;
		}
		int code = response.getCode();
		if(code != success){
			throw new AdPullException("返回结果有误code：" + code);
		}
		
		List<TaylorAdDetails> ads = response.getAds();
		if(ads == null || ads.isEmpty()){
			return null;
		}
		// 返回对象
		AdRecomReply recomReply = new AdRecomReply();
		recomReply.setStatus(status);
		recomReply.setAd_contents(this.dealAds(ads));
		return recomReply;
	}
	private List<AdContent>  dealAds(List<TaylorAdDetails> ads ){
		List<AdContent> list=new ArrayList<AdContent> ();
		for(TaylorAdDetails ad:ads){
			String title = ad.getTitle();
			if(StringUtils.isEmpty(title)){
				title = defTitle;
			}

			AdContent content = new AdContent();
			content.setMarketTitle(title);
			content.setGuideTitle(title);
			content.setContent(ad.getDesc());
			content.setCpIcon(ad.getIcon());
			// 广告图片
			List<AdImg> imgs = new ArrayList<AdImg>();
			if(StringUtils.isNotEmpty(ad.getSrc())){
				AdImg img = new AdImg();
				img.setSrc(ad.getSrc());
				imgs.add(img);
			}else if(StringUtils.isNotEmpty(ad.getIcon())){
				AdImg img = new AdImg();
				img.setSrc(ad.getIcon());
				imgs.add(img);
			}

			content.setImglist(imgs);
			
			// 曝光 点击
			Map<String, List<String>> map = new HashMap<String, List<String>>();
			// 点击监测
			List<String> click = ad.getClickreport();
			if(click == null){
				click = new ArrayList<String>();
			}
			map.put(CLICK, click);
			// 曝光监测
			List<TaylorAdReport> reports = ad.getDisplayreport();
			List<String> urls = new ArrayList<String>();
			if(reports != null && !reports.isEmpty()){
				
				for (TaylorAdReport report : reports) {
					urls.add(report.getReporturl());
				}
			}
			map.put(SHOW, urls);
			content.setThirdReportLinks(map);
			
			// 类型
			AdMutiAction action = new AdMutiAction();
			// 内容类型1表示链接推广,2表示应用推广,3未使用 4表示电话推广
			action.setGuideTitle(title);
			action.setType(ACTION_WEB);
			action.setLinkurl(ad.getLink());
			action.setCpIcon(ad.getIcon());
			
			content.setMutiAction(Collections.singletonList(action));
			list.add(content);
		}
		return list;
	}
	public TaylorAdPuller getTaylorAdPuller() {
		return taylorAdPuller;
	}

	public void setTaylorAdPuller(TaylorAdPuller taylorAdPuller) {
		this.taylorAdPuller = taylorAdPuller;
	}

}
