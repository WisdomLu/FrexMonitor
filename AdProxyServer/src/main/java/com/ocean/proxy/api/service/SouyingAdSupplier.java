package com.ocean.proxy.api.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inveno.util.CollectionUtils;
import com.ocean.core.common.system.SystemContext;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.persist.api.proxy.AdPullException;
import com.ocean.persist.api.proxy.JoinDSPEmu;
import com.ocean.persist.api.proxy.diankai.DiankaiAd;
import com.ocean.persist.api.proxy.diankai.DiankaiAdPullResponse;
import com.ocean.persist.api.proxy.souying.SouyingAdContent;
import com.ocean.persist.api.proxy.souying.SouyingAdImageSize;
import com.ocean.persist.api.proxy.souying.SouyingAdMaterial;
import com.ocean.persist.api.proxy.souying.SouyingAdPullParams;
import com.ocean.persist.api.proxy.souying.SouyingAdPuller;
import com.ocean.persist.api.proxy.souying.SouyingAdResponse;
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
@Component(value="souyingAdSupplier")
public class SouyingAdSupplier   extends AbstractAdSupplier{
    @Autowired
	private SouyingAdPuller SouyingAdPuller;
	private static final Map<AdSpaceType, Integer> spaceType = new HashMap<AdSpaceType, Integer>();
	static{
		spaceType.put(AdSpaceType.BANNER, 1);
		spaceType.put(AdSpaceType.INTERSTITIAL, 2);
		spaceType.put(AdSpaceType.OPENING, 3);
	}
	
	public AdRecomReply invoke(InvokeAttribute attribute) throws AdPullException {
		
		AdRecomReq adreq = attribute.getAdreq();
		DSPPosition positionInfo = attribute.getDspPosition();
		// 参数转换
		SouyingAdPullParams params = parseParams(adreq, positionInfo);
		
		SouyingAdResponse response = (SouyingAdResponse) SouyingAdPuller.api(params,adreq.getHash(),String.valueOf(adreq.getUserAdSpaceAttri().getAdSpaceId()));
		
		return parseResult(response);
	}
	
	private AdRecomReply parseResult(SouyingAdResponse response)
			throws AdPullException {
		
		if(response == null){
			return null;
		}
		List<SouyingAdContent> ads = response.getAds();
		if(ads == null || ads.isEmpty()){
			return null;
		}
		// 返回对象
		AdRecomReply recomReply = new AdRecomReply();
		recomReply.setStatus(status);
		recomReply.setAd_contents(this.dealAds(ads));
		
		return recomReply;
	}
	private List<AdContent>  dealAds(List<SouyingAdContent> ads){
		List<AdContent> list=new ArrayList<AdContent> ();
		for(SouyingAdContent ad:ads){
			
			AdContent content = new AdContent();
			AdMutiAction action = new AdMutiAction();
			SouyingAdMaterial mate = ad.getNative_material();
			int actType=ACTION_WEB;
			// 素材类型: 0 html，1 native
			if(ad.getMaterial_type() == 0){
				content.setIsHtmlAd(true);
				content.setHtmlSnippet(ad.getHtml_snippet());
			}
			String title = mate.getTitle();
			if(StringUtils.isEmpty(title)){
				title = defTitle;
			}
			content.setMarketTitle(title);
			content.setGuideTitle(title);
			content.setContent(mate.getDescription1());
			String icon = mate.getLogo_url();
			content.setCpIcon(icon);
			// 图片素材
			AdImg img = new AdImg();
			img.setSrc(mate.getImage_url());
			SouyingAdImageSize size = mate.getImage_size();
			if(size != null){
				img.setWidth(size.getWidth());
				img.setHeight(size.getHeight());
			}
			List<AdImg> imgs = new ArrayList<AdImg>();
			imgs.add(img);
			content.setImglist(imgs);
			
			Map<String, List<String>> map = new HashMap<String, List<String>>();
			// 点击监测
			List<String> click = mate.getClick_monitor_url();
			if(click == null){
				click = new ArrayList<String>();
			}
			map.put(CLICK, click);
			// 曝光监测
			List<String> show = mate.getImpression_log_url();
			if(show == null){
				show = new ArrayList<String>();
			}
			map.put(SHOW, show);
			if(CollectionUtils.isNotEmpty(mate.getAppDownload())){
				map.put(DOWNLOAD, mate.getAppDownload());
				actType=ACTION_APP;
			}
			
			if(CollectionUtils.isNotEmpty(mate.getAppActive())){
				map.put(ACTIVE, mate.getAppDownload());
				actType=ACTION_APP;
			}
			
			if(CollectionUtils.isNotEmpty(mate.getAppInstall())){
				map.put(INSTALL, mate.getAppInstall());
				actType=ACTION_APP;
			}
			content.setThirdReportLinks(map);
			
			// 类型

			// 内容类型1表示链接推广,2表示应用推广,3未使用 4表示电话推广
			action.setType(actType);
			action.setCpPackage(mate.getPackage());
			action.setCpName(mate.getAppName());
			
			// 落地页
			String link = mate.getClick_url();
			action.setLinkurl(link);
			action.setGuideTitle(title);
			action.setCpIcon(icon);
			content.setMutiAction(Collections.singletonList(action));

			list.add(content);
		}
		return list;
		
	}
	private SouyingAdPullParams parseParams(AdRecomReq adreq, 
			DSPPosition positionInfo) throws AdPullException {
		
		UserAdSpaceAttri attri = adreq.getUserAdSpaceAttri();
		AdUserInfo userInfo = adreq.getUserinfo();
		SouyingAdPullParams params = new SouyingAdPullParams();
		params.setHeight(attri.getSpaceHeight());
		params.setWidth(attri.getSpaceWidth());
		params.setApiver(SystemContext.getDynamicPropertyHandler().getInt(ProxyConstants.SOUYING_API_VERSION,3)+"");
		params.setAppversion("1.1");
		params.setConnecttype(String.valueOf(1));
		params.setCountrycode("CN");
		if(StringUtils.isEmpty(positionInfo.getText1())||StringUtils.isEmpty(positionInfo.getText2())){
			throw new AdPullException("proxy configure error:appId or app package name is empty!");
		}
		params.setAppid(positionInfo.getText1());
		params.setDevicename(userInfo.getPhonemodel());
		params.setDlanguage("zh");
		params.setDmanufacturer(userInfo.getBrand_name());
		params.setDversion(userInfo.getOsversion());
		params.setImei(userInfo.getImei());
		params.setIps(userInfo.getClient_ip());
//		params.setMacadd(macadd)
		params.setPkgname(positionInfo.getText2());
		params.setScreentype(1);
		params.setUuid(UUID.randomUUID().toString());
		
		Integer adslottype = spaceType.get(attri.getSpaceType());
		if(adslottype == null){
			throw new AdPullException("无效的广告位类型");
		}
		params.setAdslottype(adslottype);
		params.setUa(userInfo.getUa());
		params.setAndroidid(userInfo.getAdid());
		return params;
	}

	public SouyingAdPuller getSouyingAdPuller() {
		return SouyingAdPuller;
	}

	public void setSouyingAdPuller(SouyingAdPuller souyingAdPuller) {
		SouyingAdPuller = souyingAdPuller;
	}

}
