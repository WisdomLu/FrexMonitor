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
import com.ocean.persist.api.proxy.AdPullException;
import com.ocean.persist.api.proxy.youdao.YoudaoAdPullParams;
import com.ocean.persist.api.proxy.youdao.YoudaoAdPuller;
import com.ocean.persist.api.proxy.youdao.YoudaoAdResponse;
import com.ocean.persist.model.proxy.DSPPosition;
import com.ocean.proxy.api.base.AbstractAdSupplier;
import com.ocean.proxy.thrift.entity.AdContent;
import com.ocean.proxy.thrift.entity.AdImg;
import com.ocean.proxy.thrift.entity.AdMutiAction;
import com.ocean.proxy.thrift.entity.AdRecomReply;
import com.ocean.proxy.thrift.entity.AdRecomReq;
import com.ocean.proxy.thrift.entity.AdUserInfo;
@Component(value="youdaoAdSupplier")
public class YoudaoAdSupplier extends AbstractAdSupplier{
    @Autowired 
	private YoudaoAdPuller youdaoAdPuller;
	public AdRecomReply invoke(InvokeAttribute attribute) throws AdPullException {
		
		AdRecomReq adreq = attribute.getAdreq();
		DSPPosition positionInfo = attribute.getDspPosition();
		// 参数转换
		YoudaoAdPullParams params = parseParams(adreq, positionInfo);
		// 调用API
		YoudaoAdResponse response = (YoudaoAdResponse) youdaoAdPuller.api(params,adreq.getHash(),String.valueOf(adreq.getUserAdSpaceAttri().getAdSpaceId()));
		// 解析结果
		return parseResult(response);
	}
	
	private AdRecomReply parseResult(YoudaoAdResponse response)
			throws AdPullException {
		
		if(response == null){
			return null;
		}
		
		// 返回对象
		AdRecomReply recomReply = new AdRecomReply();
		recomReply.setStatus(status);
		
		AdContent content = new AdContent();
		AdMutiAction action = new AdMutiAction();
		int atype = response.getYdAdType();
		// 广告类型，0：落地页广告；1：下载类型广告
		if(atype == 1){
			action.setType(ACTION_APP);
		}else{
			action.setType(ACTION_WEB);
		}
		String title = response.getTitle();
		if(StringUtils.isEmpty(title)){
			title = defTitle;
		}
		content.setMarketTitle(title);
		content.setGuideTitle(title);
		action.setGuideTitle(title);
		
		List<AdImg> imgs = new ArrayList<AdImg>();
		String image = response.getMainimage();
		if(StringUtils.isNotEmpty(image)){
			AdImg img = new AdImg();
			img.setSrc(image);
			imgs.add(img);
		}
		
		String icon = response.getIconimage();
		if(StringUtils.isNotEmpty(icon)){
			
			action.setCpIcon(icon);
			content.setCpIcon(icon);
			if(imgs.isEmpty()){
				AdImg img = new AdImg();
				img.setSrc(icon);
				imgs.add(img);
			}
		}
		if(StringUtils.isNotEmpty(response.getPackageName())){
			action.setCpPackage(response.getPackageName());
		}
		// 图片地址
		content.setImglist(imgs);
		
		List<String> click = new ArrayList<String>();
		if(StringUtils.isNotEmpty(response.getDeeplink())){
			action.setActiveUri(response.getDeeplink());
			if(CollectionUtils.isNotEmpty(response.getDptrackers())){	
				click.addAll(response.getDptrackers());
				
			}
		}
		if(StringUtils.isNotEmpty(response.getClk())){
			action.setLinkurl(response.getClk());

		}
		
		
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		// 点击监测
		if(CollectionUtils.isNotEmpty(response.getClktrackers())){
			click.addAll(response.getClktrackers());
		}
		map.put(CLICK, click);
		// 曝光监测
		List<String> show = response.getImptracker();
		if(show == null){
			show = new ArrayList<String>();
		}
		map.put(SHOW, show);
		content.setMutiAction(Collections.singletonList(action));
		content.setThirdReportLinks(map);
		recomReply.setAd_contents(Collections.singletonList(content));
		
		return recomReply;
	}
	
	private YoudaoAdPullParams parseParams(AdRecomReq adreq, 
			DSPPosition positionInfo) throws AdPullException {
		AdUserInfo userInfo = adreq.getUserinfo();
		YoudaoAdPullParams params = new YoudaoAdPullParams();
		params.setId(positionInfo.getPos());
		params.setAv(adreq.getVersion());
		String adid = userInfo.getAdid();
		String net = adreq.getNet();
		if(StringUtils.isNotEmpty(net)&&NETWORK_WIFI.equals(net)){
			params.setCt(2);
			params.setDct(0);
		
		}else if(StringUtils.isNotEmpty(net)&&net.endsWith("g")){
			params.setCt(3);
			params.setDct(getNetwork(net));
		}
		else{
			params.setCt(0);
			params.setDct(0);
		}
		
		
		if(StringUtils.isNotEmpty(adid)){
			params.setAuidmd5(DigestUtils.md5Hex(adid).toUpperCase());
			params.setUdid(adid.toUpperCase());
		}
		
		params.setImei(userInfo.getImei());
		params.setImeimd5(DigestUtils.md5Hex(userInfo.getImei()).toUpperCase());
		if(StringUtils.isNotEmpty(userInfo.getLat())&&StringUtils.isNotEmpty(userInfo.getLon())){
			params.setLl(userInfo.getLon()+","+userInfo.getLat());
			params.setLla("1000");
			params.setLlt("0");
			params.setLlp("g");
		}
		params.setWifi(userInfo.getMac());
		params.setOsv(this.convOSVersion(userInfo.getOsversion()));
		params.setMcc("460");
		params.setIso("cn");
		params.setRip(userInfo.getClient_ip());
		params.setDn(encode(userInfo.getPhonemodel()));
		String mobile = userInfo.getMobile();
		if(MOBILE_CMCC.equals(mobile)){
			params.setMnc("00");
			params.setCn("中国移动");
		} else if(MOBILE_CUCC.equals(mobile)){
			params.setMnc("01");
			params.setCn("中国联通");
		}else if(MOBILE_CTCC.equals(mobile)){
			params.setMnc("03");
			params.setCn("中国电信");
		}
		params.setRan(adreq.getResult_num());
		return params;
	}
	private  int getNetwork(String net){	
		if(NETWORK_2G.equals(net)){
			return 11;
		}
		else if(NETWORK_3G.equals(net)){
			return 12;
		}
		else if(NETWORK_4G.equals(net)){
			return 13;
		}
		return 0;
	}
	public YoudaoAdPuller getYoudaoAdPuller() {
		return youdaoAdPuller;
	}

	public void setYoudaoAdPuller(YoudaoAdPuller youdaoAdPuller) {
		this.youdaoAdPuller = youdaoAdPuller;
	}

}
