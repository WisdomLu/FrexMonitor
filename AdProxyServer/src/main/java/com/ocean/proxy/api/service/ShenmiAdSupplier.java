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
import com.ocean.core.common.system.ErrorCode;
import com.ocean.core.common.system.SystemContext;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.persist.api.proxy.AdPullException;
import com.ocean.persist.api.proxy.JoinDSPEmu;
import com.ocean.persist.api.proxy.diankai.DiankaiAd;
import com.ocean.persist.api.proxy.diankai.DiankaiAdPullResponse;
import com.ocean.persist.api.proxy.shenmi.ShenmiAdDetails;
import com.ocean.persist.api.proxy.shenmi.ShenmiAdEvent;
import com.ocean.persist.api.proxy.shenmi.ShenmiAdPullParams;
import com.ocean.persist.api.proxy.shenmi.ShenmiAdPuller;
import com.ocean.persist.api.proxy.shenmi.ShenmiAdReport;
import com.ocean.persist.api.proxy.shenmi.ShenmiAdResponse;
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
@Component(value="shenmiAdSupplier")
public class ShenmiAdSupplier  extends AbstractAdSupplier{
    @Autowired
	private ShenmiAdPuller shenmiAdPuller;
	public AdRecomReply invoke(InvokeAttribute attribute) throws AdPullException {
		AdRecomReq adreq = attribute.getAdreq();
		DSPPosition positionInfo = attribute.getDspPosition();
		// 参数转换
		UserAdSpaceAttri attri = adreq.getUserAdSpaceAttri();
		AdUserInfo userInfo = adreq.getUserinfo();
		// 申米的开屏，信息流返回json格式的广告 ，banner和插屏返回h5的
		AdSpaceType spaceType = AdSpaceType.findByValue(positionInfo.getPosType());
/*		if(spaceType != attri.getSpaceType()){
			throw new AdPullException("类型不匹配，广告位类型：" + spaceType);
		}
		*/
		if(StringUtils.isEmpty(positionInfo.getText1())||StringUtils.isEmpty(positionInfo.getText2())||StringUtils.isEmpty(positionInfo.getText3())){
			throw new AdPullException(ErrorCode.PARAM_ERROR,"appID|appKey|app packagename is empty!");
		}
		ShenmiAdPullParams params = new ShenmiAdPullParams();
		params.setHeight(attri.getSpaceHeight());
		params.setWidth(attri.getSpaceWidth());
		params.setAppname(adreq.getApp());
		params.setVer("1.7.4");
		params.setAppversion(adreq.getVersion());
		params.setModel(userInfo.getPhonemodel());
		params.setIp(userInfo.getClient_ip());
		params.setOsversion(this.convOSVersion(userInfo.getOsversion()));
		params.setLanguage("zh-CN");
		params.setAppid(positionInfo.getText1());
		params.setAppkey(positionInfo.getText2());
		if(userInfo.getOs().equalsIgnoreCase("ios")){
			params.setOs("2");
			params.setIdfa(userInfo.getIdfa());
		}
		else{
			params.setOs("1");
			params.setImei(userInfo.getImei());
			params.setMac(userInfo.getMac());
		}
		// 手机网络运营商 46000, 46002 中国移动 46001 中国联通 46003 中国电信 IOS请提供原始值
		String mobile=mobiles.get(userInfo.getMobile());
		params.setImsi(StringUtils.isEmpty(mobile)?"unknown":mobile);

	
		if("wifi".equalsIgnoreCase(adreq.getNet())){
			params.setNetwork("wifi");
		}else if(StringUtils.isNotEmpty(adreq.getNet())){
			params.setNetwork(adreq.getNet().toUpperCase());
		}else{
			params.setNetwork("unknown");
		}
		params.setNetwork(adreq.getNet());
		params.setAndroidid(userInfo.getAdid());
		// 广告位
		params.setLid(positionInfo.getPos());
		params.setApppackagename(positionInfo.getText3());
		
		params.setBrand(userInfo.getBrand_name());
		params.setModel(userInfo.getPhonemodel());
		// 屏幕宽高
		params.setScreenheight(userInfo.getDevice_height());
		params.setScreenwidth(userInfo.getDevice_width());
		params.setTime(String.valueOf(System.currentTimeMillis()));
		params.setWifissid("");
		params.setUa(userInfo.getUa());
		params.setToken(DigestUtils.md5Hex(params.getAppid()+params.getAppkey()+params.getLid()+params.getTime()));
		params.setIsGP(0);
		ShenmiAdResponse response =(ShenmiAdResponse)shenmiAdPuller.api(params,adreq.getHash(),String.valueOf(adreq.getUserAdSpaceAttri().getAdSpaceId()));
		
		boolean isH5 = spaceType == AdSpaceType.BANNER || spaceType == AdSpaceType.INTERSTITIAL;
		
		return parseResult(response, isH5);
	}
	
	private AdRecomReply parseResult(ShenmiAdResponse response, boolean isH5)
			throws AdPullException {
		
		if(response == null){
			return null;
		}
		int code = response.getCode();
		if(code != ErrorCode.SUCCEED){
			throw new AdPullException("返回结果有误code：" + code);
		}
		
		List<ShenmiAdDetails> ads = response.getAds();
		if(CollectionUtils.isEmpty(ads)){
			return null;
		}
		// 返回对象
		AdRecomReply recomReply = new AdRecomReply();
		recomReply.setStatus(status);
		recomReply.setAd_contents(this.dealAds(ads));
		return recomReply;
	}
	private List<AdContent>  dealAds(	List<ShenmiAdDetails> ads){
		List<AdContent> list=new ArrayList<AdContent> ();
		for(ShenmiAdDetails ad:ads){
			String title = ad.getTitle();
			AdContent content = new AdContent();
			AdMutiAction action = new AdMutiAction();
			// 不是html素材
			int actionType = ACTION_WEB;
			if(StringUtils.isEmpty(ad.getPage())){
				content.setContent(ad.getDesc());
				content.setCpIcon(ad.getIcon());
				action.setLinkurl(ad.getLink());
				action.setCpIcon(ad.getIcon());
				// 广告图片
				List<AdImg> imgs = new ArrayList<AdImg>();
				
				if(StringUtils.isNotEmpty(ad.getSrc())){
					AdImg img = new AdImg();
					img.setSrc(ad.getSrc());
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
				
				// 曝光监测
				List<ShenmiAdReport> reports = ad.getDisplayreport();
				List<String> urls = new ArrayList<String>();
				if(reports != null && !reports.isEmpty()){
					
					for (ShenmiAdReport report : reports) {
						urls.add(report.getReporturl());
					}
				}
				// 当广告中的action为2时，即表明是下载类广告
				if(ad.getAction() == 2){
					actionType = ACTION_APP;
					List<ShenmiAdEvent> events = ad.getTrackingevents();
					if(events != null && !events.isEmpty()){
						// 处理下载安装监控
						for(ShenmiAdEvent event : events){
							String e = event.getEventtype();
							if(ShenmiAdEvent.DOWNLOAD_START.equals(e)){
								click.addAll(event.getTracking());
							}else if(ShenmiAdEvent.DOWNLOAD_COMPLETE.equals(e)||ShenmiAdEvent.INSTALL_START.equals(e)){
								map.put(DOWNLOAD, event.getTracking());
							}else if(ShenmiAdEvent.INSTALL_COMPLETE.equals(e)){
								map.put(INSTALL, event.getTracking());
							}
						}
					}
				}
				map.put(CLICK, click);
				map.put(SHOW, urls);
				content.setThirdReportLinks(map);
			}
			// html素材
			else{
				content.setHtmlSnippet(ad.getPage());
				content.setIsHtmlAd(true);
			}
			if(StringUtils.isEmpty(title)){
				title = defTitle;
			}
			// 内容类型1表示链接推广,2表示应用推广,3未使用 4表示电话推广
			action.setType(actionType);
			content.setMarketTitle(title);
			content.setGuideTitle(title);
			action.setGuideTitle(title);
			content.setMutiAction(Collections.singletonList(action));
			
			list.add(content);
		}
		return list;
		
	}
	public ShenmiAdPuller getShenmiAdPuller() {
		return shenmiAdPuller;
	}

	public void setShenmiAdPuller(ShenmiAdPuller shenmiAdPuller) {
		this.shenmiAdPuller = shenmiAdPuller;
	}


}
