package com.ocean.proxy.api.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ocean.persist.api.proxy.AdPullException;
import com.ocean.persist.api.proxy.boclick.BoclickAdContent;
import com.ocean.persist.api.proxy.boclick.BoclickAdData;
import com.ocean.persist.api.proxy.boclick.BoclickAdPullParams;
import com.ocean.persist.api.proxy.boclick.BoclickAdPuller;
import com.ocean.persist.api.proxy.boclick.BoclickAdResponse;
import com.ocean.persist.model.proxy.DSPPosition;
import com.ocean.proxy.api.base.AbstractAdSupplier;
import com.ocean.proxy.thrift.entity.AdContent;
import com.ocean.proxy.thrift.entity.AdImg;
import com.ocean.proxy.thrift.entity.AdMutiAction;
import com.ocean.proxy.thrift.entity.AdRecomReply;
import com.ocean.proxy.thrift.entity.AdRecomReq;
import com.ocean.proxy.thrift.entity.AdUserInfo;
import com.ocean.proxy.thrift.entity.UserAdSpaceAttri;
@Component(value="boclickAdSupplier")
public class BoclickAdSupplier extends AbstractAdSupplier{
    @Autowired
	private   BoclickAdPuller boclickAdPuller;
	
	public AdRecomReply invoke(InvokeAttribute attribute) throws AdPullException {
		AdRecomReq adreq = attribute.getAdreq();
		DSPPosition positionInfo = attribute.getDspPosition();
		// 参数转换
		BoclickAdPullParams params = parseParams(adreq, positionInfo.getPos());
		// 调用API
		BoclickAdResponse response = (BoclickAdResponse)boclickAdPuller.api(params,adreq.getHash(),String.valueOf(adreq.getUserAdSpaceAttri().getAdSpaceId()));
		
		return parseResult(response);
	}
	
	private AdRecomReply parseResult(BoclickAdResponse response)
			throws AdPullException {
		
		if(response == null){
			return null;
		}
		List<BoclickAdData> adDatas = response.getAd();
		if(adDatas == null || adDatas.isEmpty()){
			return null;
		}
		// 返回对象
		AdRecomReply recomReply = new AdRecomReply();
		recomReply.setStatus(status);
		recomReply.setAd_contents(dealAds(adDatas));
		
		return recomReply;
	}
	private List<AdContent>  dealAds(List<BoclickAdData> adDatas){
		List<AdContent> list=new ArrayList<AdContent> ();
		
		BoclickAdData adData = adDatas.get(0);
		List<BoclickAdContent> ads = adData.getContent();
		if(ads == null || ads.isEmpty()){
			return null;
		}
		for(BoclickAdContent ad:ads){
			AdContent content = new AdContent();
			String title = get(ad.getName());
			if(StringUtils.isEmpty(title)){
				title = defTitle;
			}
			content.setMarketTitle(title);
			content.setGuideTitle(title);
			content.setContent(get(ad.getBannertext()));
			String icon = get(ad.getIcon());
			content.setCpIcon(icon);
			// 图片素材
			String size = get(ad.getSize());
			AdImg img = new AdImg();
			img.setSrc(get(ad.getSrc()));
			if(size != null && size.indexOf("\\*") > 0){
				String[] ss = size.split("\\*");
				img.setWidth(Integer.valueOf(ss[0]));
				img.setHeight(Integer.valueOf(ss[1]));
			}
			List<AdImg> imgs = new ArrayList<AdImg>();
			imgs.add(img);
			content.setImglist(imgs);
			
			Map<String, List<String>> map = new HashMap<String, List<String>>();
			// 点击监测
			List<String> click = ad.getMonitor();
			if(click == null){
				click = new ArrayList<String>();
			}
			map.put(CLICK, click);
			// 曝光监测
			List<String> show = ad.getPvBegin();
			if(show == null){
				show = new ArrayList<String>();
			}
			map.put(SHOW, show);
			
			content.setThirdReportLinks(map);
			
			// 类型
			AdMutiAction action = new AdMutiAction();
			// 内容类型1表示链接推广,2表示应用推广,3未使用 4表示电话推广
			action.setType(ACTION_WEB);
			
			// 落地页
			String link = get(ad.getLink());
			action.setLinkurl(link);
			action.setGuideTitle(title);
			action.setCpIcon(icon);
			content.setMutiAction(Collections.singletonList(action));
			//content.setAdId(InvenoIdGenerator.genThirdDspId(SystemContext.getDynamicPropertyHandler().getInt(ProxyConstants.PROXY_SERVER_NODE_INDEX,100)));
			
			list.add(content);
		}
		return list;
	}
	private BoclickAdPullParams parseParams(AdRecomReq adreq, String pid){
		
		UserAdSpaceAttri attri = adreq.getUserAdSpaceAttri();
		AdUserInfo userInfo = adreq.getUserinfo();
		BoclickAdPullParams params = new BoclickAdPullParams();
		params.setHeight(attri.getSpaceHeight());
		params.setWidth(attri.getSpaceWidth());
		params.setZoneid(pid);
		params.setMedia("YuanChuanMei");
		params.setLayerstyle("json");
		params.setPhone_model(userInfo.getPhonemodel());
		params.setSoftware_name(adreq.getApp());
		params.setUnid(userInfo.getImei());
		return params;
	}
	
	private <T> T get(List<T> list){
		
		if(list != null && !list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

	public BoclickAdPuller getBoclickAdPuller() {
		return boclickAdPuller;
	}

	public void setBoclickAdPuller(BoclickAdPuller boclickAdPuller) {
		this.boclickAdPuller = boclickAdPuller;
	}


}
