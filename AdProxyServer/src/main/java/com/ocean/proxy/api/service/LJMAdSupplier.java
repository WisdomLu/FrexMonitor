package com.ocean.proxy.api.service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inveno.util.CollectionUtils;
import com.ocean.core.common.JsonUtils;
import com.ocean.persist.api.proxy.AdPullException;
import com.ocean.persist.api.proxy.lingji.LingjiAdPullParams;
import com.ocean.persist.api.proxy.lingji.LingjiAdPuller;
import com.ocean.persist.api.proxy.lingji.LingjiAdResponse;
import com.ocean.persist.api.proxy.lingji.LingjiApp;
import com.ocean.persist.api.proxy.lingji.LingjiDevice;
import com.ocean.persist.api.proxy.lingji.LingjiGeneralMeta;
import com.ocean.persist.api.proxy.lingji.LingjiImage;
import com.ocean.persist.api.proxy.lingji.LingjiNativeMeta;
import com.ocean.persist.api.proxy.lingji.LingjiNativead;
import com.ocean.persist.api.proxy.lingji.LingjiVideo;
import com.ocean.persist.model.proxy.DSPPosition;
import com.ocean.proxy.thrift.entity.AdContent;
import com.ocean.proxy.thrift.entity.AdImg;
import com.ocean.proxy.thrift.entity.AdMutiAction;
import com.ocean.proxy.thrift.entity.AdRecomReply;
import com.ocean.proxy.thrift.entity.AdRecomReq;
import com.ocean.proxy.thrift.entity.AdSpaceType;
import com.ocean.proxy.thrift.entity.AdUserInfo;
import com.ocean.proxy.thrift.entity.AdVid;
import com.ocean.proxy.thrift.entity.UserAdSpaceAttri;

/**
 * 从灵集获取手机原生广告数据<p>
 * 灵集的请求参数<p>
 * <a href="http://docs.thextrader.cn/media/mobile_native_protocol.html">
 * http://docs.thextrader.cn/media/mobile_native_protocol.html
 * </a>
 * @author
 *
 */
@Component(value="lJMAdSupplier")
public class LJMAdSupplier extends LingjiAdSupplier{
    @Autowired
	private LingjiAdPuller lingjiAdPuller;
	@Override
	public AdRecomReply invoke(InvokeAttribute attribute) throws AdPullException {
		
		AdRecomReq adreq = attribute.getAdreq();
		DSPPosition positionInfo = attribute.getDspPosition();
		// 是否是信息流广告
		boolean isNatived = positionInfo.getPosType() == AdSpaceType.INFOFLOW.getValue();
		
		// 参数转换
		LingjiAdPullParams params = parseParams(adreq, positionInfo.getPos(), isNatived);
		
		// 调用API获取
		LingjiAdResponse response = (LingjiAdResponse) lingjiAdPuller.api(params,adreq.getHash(),String.valueOf(adreq.getUserAdSpaceAttri().getAdSpaceId()));
		AdRecomReply recomReply = parseResult(response);
		// 没有广告
		if(recomReply == null){
			return null;
		}
		
		// 设置物料信息
		String src = response.getSrc();
		try {
			src = URLDecoder.decode(src, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new AdPullException("url信息解码失败");
		}
		// 非信息流广告
		if(!isNatived){
			set(JsonUtils.toBean(src, LingjiGeneralMeta.class), 
					recomReply, response.getType());
		}
		else{
			set(JsonUtils.toBean(src, LingjiNativeMeta.class), recomReply);
		}
		return recomReply;
	}
	

	private void set(LingjiGeneralMeta meta, 
			AdRecomReply recomReply, String type) throws AdPullException{
		if(CollectionUtils.isEmpty(recomReply.getAd_contents())){
			return;
		}
		AdContent content = recomReply.getAd_contents().get(0);
		content.setMarketTitle(defTitle);
		content.setGuideTitle(defTitle);
		
		// 默认标题
		AdMutiAction action = content.getMutiAction().get(0);
		action.setGuideTitle(defTitle);
		
		// "I"表示图片物料，"F"表示Flash物料，"V"表示视频物料，
		// "X"表示多点击地址Flash物料/富媒体物料(多点击地址Flash物料是已嵌入跳转地址和Click监测代码的物料)，
		// "C"表示动态物料(html创意代码)，"R"表示原生信息流广告
		if("I".equals(type)){
			AdImg img = new AdImg();
			img.setHeight(meta.getHeight());
			img.setWidth(meta.getWidth());
			img.setSrc(meta.getUrl());
			
			List<AdImg> imgs = new ArrayList<AdImg>();
			imgs.add(img);
			content.setImglist(imgs);
		}
		else{
			AdVid vid = new AdVid();
			vid.setDuration(integer2int(meta.getDuration()));
			vid.setWidth(integer2int(meta.getWidth()));
			vid.setHeight(integer2int(meta.getHeight()));
			vid.setSrc(meta.getUrl());
			content.setAdVid(vid);
			
			action.setType(ACTION_VIDEO);
		}
	}
	
	private void set(LingjiNativeMeta meta, AdRecomReply recomReply) throws AdPullException{
		
		String title = meta.getTitle();
		if(StringUtils.isEmpty(title)){
			title = defTitle;
		}
		if(CollectionUtils.isEmpty(recomReply.getAd_contents())){
			return;
		}
		AdContent content = recomReply.getAd_contents().get(0);
		content.setContent(meta.getDesc());
		content.setMarketTitle(title);
		content.setGuideTitle(title);
		content.setAdSource(meta.getSource());
		content.setCpclass(meta.getBundle());
		// 广告图集
		List<LingjiImage> lingjiImages = meta.getImage();
		if(lingjiImages != null && !lingjiImages.isEmpty()){
			
			List<AdImg> imgs = new ArrayList<AdImg>();
			for (LingjiImage lingjiImage : lingjiImages) {
				AdImg img = new AdImg();
				img.setHeight(lingjiImage.getHeight());
				img.setWidth(lingjiImage.getWidth());
				img.setSrc(lingjiImage.getUrl());
				imgs.add(img);
			}
			content.setImglist(imgs);
		}
		
		// 只有一条
		AdMutiAction action = content.getMutiAction().get(0);
		action.setCpclass(meta.getBundle());
		action.setCpName(meta.getAppname());
		action.setGuideTitle(title);
		// logo
		LingjiImage lingjiImage = meta.getLogo();
		if(lingjiImage != null){
			
			action.setCpIcon(lingjiImage.getUrl());
			content.setCpIcon(lingjiImage.getUrl());
		}
		
		LingjiVideo video = meta.getVideo();
		// 视频信息
		if(video != null){
			
			AdVid vid = new AdVid();
			vid.setDuration(integer2int(video.getDuration()));
			vid.setWidth(integer2int(video.getW()));
			vid.setHeight(integer2int(video.getH()));
			vid.setImg_src(video.getCover_img_url());
			vid.setSrc(video.getUrl());
			content.setAdVid(vid);
			
			action.setType(ACTION_VIDEO);
		}
	}
	
	private int integer2int(Integer i){
		if(i == null){
			return 0;
		}
		return i;
	}
	
	private LingjiAdPullParams parseParams(AdRecomReq adreq, String posId, boolean isNatived){
		
		// 用户手机信息
		UserAdSpaceAttri attri = adreq.getUserAdSpaceAttri();
		AdUserInfo userInfo = adreq.getUserinfo();
		
		LingjiAdPullParams params = new LingjiAdPullParams();
		// 1.支持网页打开类+下载类广告 2.只支持打开类广告 3.只支持下载类广告
		params.setAction_type(1);
		// 媒体系统对一次信息流广告请求定义的唯一ID,XTrader将在Response中返回该唯一ID
		params.setReqid(UUID.randomUUID().toString().replaceAll("-", ""));
		//params.setReqid("124");
		// 由XTrader平台提供,XTrader平台唯一广告位ID,对应媒体一个确定的资源位置.
		params.setPid(posId);
		logger.info("joinDSP:lj mob ad request,pose id:{},space width:{},spae height:{}",posId,attri.getSpaceWidth(),attri.getSpaceHeight());
		
		LingjiDevice device = new LingjiDevice();
		device.setM_ua(userInfo.getUa());
		device.setM_adh(attri.getSpaceHeight());
		device.setM_adw(attri.getSpaceWidth());
		device.setM_opr(userInfo.getMobile());
		device.setM_mdl(userInfo.getPhonemodel());
		device.setOsv(userInfo.getOsversion());
		device.setM_ip(userInfo.getClient_ip());
		String imei = userInfo.getImei(); 
		device.setImei(imei);
		device.setImei_md5(DigestUtils.md5Hex(imei).toUpperCase());
		
		String osCode = userInfo.getOs();
		// 如果是ios
		if(OS_IOS.equals(osCode)){
			device.setIdfa(userInfo.getIdfa());
//			device.set
			device.setOs(1);
		}else if(OS_ANDROID.equals(osCode)){
			String adid = userInfo.getAdid();
			if(!StringUtils.isEmpty(adid)){
				device.setAndroidid(adid);
				device.setAndroidid1(DigestUtils.md5Hex(adid).toUpperCase());
			}
			String aaid = userInfo.getAaid();
			if(!StringUtils.isEmpty(aaid)){
				device.setAaid(DigestUtils.md5Hex(aaid).toUpperCase());
			}
			device.setOs(0);
		}
		device.setDevicetype(0);// 手机
		String mac = userInfo.getMac();
		if(!StringUtils.isEmpty(mac)){
			device.setMac1(DigestUtils.md5Hex(mac).toUpperCase());
			device.setMac(DigestUtils.md5Hex(mac.replace(":", "")).toUpperCase());
		}
		device.setM_lan("zh-CN");
		params.setDevice(device);
		
		int maxSafeLength = 999;
		// 如果是信息流广告
		if(isNatived){
			
			LingjiNativead nativead = new LingjiNativead();
			nativead.setTitle_max_safe_length(attri.getTitleMax());
			nativead.setDesc_max_safe_length(attri.getCwMax());
			nativead.setCall_to_auction_max_safe_length(maxSafeLength);
			nativead.setDesc_max_safe_length(maxSafeLength);
			nativead.setLike_max_safe_length(maxSafeLength);
			nativead.setPrice_max_safe_length(maxSafeLength);
			nativead.setRating_max_safe_length(maxSafeLength);
			nativead.setSource_max_safe_length(maxSafeLength);
			nativead.setStore_max_safe_length(maxSafeLength);
			nativead.setVideo_height(100);
			nativead.setVideo_width(200);
			nativead.setVideo_maxduration(40);
			nativead.setVideo_minduration(1);
			//信息流广告请求中title、desc、img是必须元素,logo是可选元素
			List<String> list = new ArrayList<String>();
			list.add("1");
//			list.add("2");
//			list.add("3");
//			list.add("4");
			list.add("7");
			nativead.setRequired_fields(list);
			
			params.setNativead(nativead);
		}
		LingjiApp app = new LingjiApp();
		app.setM_app(adreq.getApp());
		app.setM_app_pn("com.zookingsoft");
//		app.set
		params.setApp(app);
		return params;
	}


	public LingjiAdPuller getLingjiAdPuller() {
		return lingjiAdPuller;
	}


	public void setLingjiAdPuller(LingjiAdPuller lingjiAdPuller) {
		this.lingjiAdPuller = lingjiAdPuller;
	}
}
