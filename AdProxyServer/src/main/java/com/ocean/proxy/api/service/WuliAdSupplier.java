package com.ocean.proxy.api.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.inveno.util.CollectionUtils;
import com.ocean.core.common.UniversalUtils;
import com.ocean.core.common.system.SystemContext;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
import com.ocean.core.common.threadpool.workthread.Task.DataFormat;
import com.ocean.core.common.threadpool.workthread.Task.InvokeType;
import com.ocean.persist.api.proxy.AdPullException;
import com.ocean.persist.api.proxy.wuli.WuliReq;
import com.ocean.persist.api.proxy.wuli.WuliReqApp;
import com.ocean.persist.api.proxy.wuli.WuliReqDev;
import com.ocean.persist.api.proxy.wuli.WuliReqNet;
import com.ocean.persist.api.proxy.wuli.WuliResp;
import com.ocean.persist.api.proxy.wuli.WuliRespAd;
import com.ocean.persist.api.proxy.wuli.WuliRespAdmInfo;
import com.ocean.persist.api.proxy.wuli.WuliRespImgVideoInfo;
import com.ocean.persist.model.proxy.DSPPosition;
import com.ocean.proxy.api.base.AbstractAsynAdSupplier;
import com.ocean.proxy.serverdis.AdProxyWuliTask;
import com.ocean.proxy.thrift.entity.AdContent;
import com.ocean.proxy.thrift.entity.AdImg;
import com.ocean.proxy.thrift.entity.AdMutiAction;
import com.ocean.proxy.thrift.entity.AdRecomReply;
import com.ocean.proxy.thrift.entity.AdRecomReq;
import com.ocean.proxy.thrift.entity.AdUserInfo;
import com.ocean.proxy.thrift.entity.AdVid;

@Component(value = "WuliAdSupplier")
public class WuliAdSupplier extends AbstractAsynAdSupplier {

	private static Logger logger = LoggerFactory.getLogger(WuliAdSupplier.class);

	private static final String PROP_KEY_URL = "wuli.url";

	@Override
	public AdRecomReply invoke(InvokeAttribute attribute) throws AdPullException {
		// 参数转换
		AdRecomReq adreq = attribute.getAdreq();
		WuliReq params = parseParams(attribute);
		// 调用API
		WuliResp response = (WuliResp) invoke(params, WuliResp.class, adreq.getHash(), String.valueOf(adreq.getUserAdSpaceAttri().getAdSpaceId()),
				"joinDSP:wuli");
		// 解析结果
		return parseResult(response);
	}

	public AdRecomReply parseResult(WuliResp resp) throws AdPullException {
		if (resp == null || resp.getData() == null) {
			throw new AdPullException("woli return null");
		}
		
		
		if (!"10000".equals(resp.getData().getCode())) {
			logger.error("wuli resp error code:{}", resp.getCode());
			throw new AdPullException("woli return error");
		}

		if (resp.getData().getAd() == null) {
			logger.error("wuli resp ad is null");
			throw new AdPullException("woli return null");
		}

		AdRecomReply adresp = new AdRecomReply();
		adresp.setStatus(status);
		adresp.setAd_contents(dealAds(resp.getData().getAd()));
		return adresp;
	}

	private List<AdContent> dealAds(WuliRespAd wuliAd) throws AdPullException {
		List<AdContent> list = new ArrayList<AdContent>();
		AdContent content = new AdContent();
		list.add(content);

		AdMutiAction action = new AdMutiAction();
		content.setMutiAction(Collections.singletonList(action));

		String linkUrl = wuliAd.getUrl();
		if (StringUtils.isEmpty(linkUrl)) {
			linkUrl = wuliAd.getDlurl();
		}
		if (StringUtils.isEmpty(linkUrl)) {
			linkUrl = wuliAd.getDl();
		}
		// TODO 把deeplink当成落地页，没有单独激活deeplink的场景

		// 落地页
		action.setLinkurl(linkUrl);
		content.setLinkurl(linkUrl);

		// 上报链接
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		content.setThirdReportLinks(map);

		List<String> viewL = new ArrayList<String>();
		if (CollectionUtils.isNotEmpty(wuliAd.getEls())) {
			viewL.addAll(wuliAd.getEls());
			map.put(SHOW, viewL);
		}

		List<String> clickL = new ArrayList<String>();
		if (CollectionUtils.isNotEmpty(wuliAd.getCls())) {
			clickL.addAll(wuliAd.getCls());
		}
		if (!StringUtils.isEmpty(wuliAd.getDl()) && CollectionUtils.isNotEmpty(wuliAd.getDlls())) {
			clickL.addAll(wuliAd.getDlls());
		}
		if (!clickL.isEmpty()) {
			map.put(CLICK, clickL);
		}

		List<String> startDown = new ArrayList<String>();
		if (CollectionUtils.isNotEmpty(wuliAd.getDsls())) {
			startDown.addAll(wuliAd.getDsls());
			map.put(START_DOWN, startDown);
		}

		List<String> endDown = new ArrayList<String>();
		if (CollectionUtils.isNotEmpty(wuliAd.getDels())) {
			endDown.addAll(wuliAd.getDels());
			map.put(DOWNLOAD, endDown);
		}

		List<String> startInstall = new ArrayList<String>();
		if (CollectionUtils.isNotEmpty(wuliAd.getIsls())) {
			startInstall.addAll(wuliAd.getIsls());
			map.put(START_INSTALL, startInstall);
		}

		List<String> endInstall = new ArrayList<String>();
		if (CollectionUtils.isNotEmpty(wuliAd.getIels())) {
			endInstall.addAll(wuliAd.getIels());
			map.put(INSTALL, endInstall);
		}

		// 内容类型1表示链接推广,2表示应用推广,3未使用 4表示电话推广
		int actType = ACTION_WEB;
		if (Integer.valueOf(2).equals(wuliAd.getAt())) {
			actType = ACTION_APP;
		}
		action.setType(actType);

		// 标题, 描述
		String title = wuliAd.getTitle();
		String desc = wuliAd.getDesc();
		content.setMarketTitle(title);
		String guideTitle = StringUtils.isEmpty(desc) ? title : desc;
		content.setGuideTitle(guideTitle);
		action.setGuideTitle(guideTitle);

		WuliRespAdmInfo wuliAdm = wuliAd.getInfo();
		if (wuliAdm == null) {
			logger.error("wuli resp ad material is null.");
			// throw new AdPullException("wuli resp ad material is null.");
			return list;
		}

		// icon_src是指广告图标
		content.setCpIcon(getUrlFromImgInfo(wuliAdm.getLogo()));

		// APP广告
		if (actType == ACTION_APP) {
			action.setCpIcon(content.getCpIcon());
			action.setCpMemo(guideTitle);
		}

		if (CollectionUtils.isEmpty(wuliAdm.getDetails())) {
			logger.error("wuli resp adm detail is empty.");
			return list;
		}

		// 视频广告
		if ("005".equals(wuliAd.getMt())) {
			WuliRespImgVideoInfo wuliVid = wuliAdm.getDetails().get(0);
			AdVid rpcVideo = new AdVid();
			content.setAdVid(rpcVideo);
			if(wuliVid.getDuration() != null) {
				rpcVideo.setDuration(wuliVid.getDuration().intValue());
			}
			rpcVideo.setSrc(wuliVid.getSrc());
			if(wuliVid.getWidth() != null) {
				rpcVideo.setWidth(wuliVid.getWidth());
			}
			if(wuliVid.getHeight() != null) {
				rpcVideo.setHeight(wuliVid.getHeight());
			}
			int lastIndex = rpcVideo.getSrc().lastIndexOf(".");
			if (lastIndex < 0) {
				rpcVideo.setFormat("unknow");
			} else {
				rpcVideo.setFormat(rpcVideo.getSrc().substring(lastIndex));
			}
			rpcVideo.setImg_src(wuliVid.getCover());
		} else {
			// 图片
			List<AdImg> imgs = new ArrayList<AdImg>();
			content.setImglist(imgs);
			for (WuliRespImgVideoInfo imgInfo : wuliAdm.getDetails()) {
				AdImg img = new AdImg();
				img.setSrc(imgInfo.getSrc());
				if (imgInfo.getWidth() != null) {
					img.setWidth(imgInfo.getWidth());
				}
				if (imgInfo.getHeight() != null) {
					img.setHeight(imgInfo.getHeight());
				}
				img.setFormate(imgInfo.getFormat());
				imgs.add(img);
			}
		}

		return list;
	}

	private String getUrlFromImgInfo(WuliRespImgVideoInfo imgInfo) {
		if (imgInfo == null) {
			return null;
		}
		return imgInfo.getSrc();
	}

	public WuliReq parseParams(InvokeAttribute attribute) throws AdPullException {
		DSPPosition posInfo = attribute.getDspPosition();
		if (StringUtils.isEmpty(posInfo.getText1())) {
			throw new AdPullException("wuli sid is empty!");
		}
		if (StringUtils.isEmpty(posInfo.getText2())) {
			throw new AdPullException("wuli app name is empty!");
		}
		if (StringUtils.isEmpty(posInfo.getText3())) {
			throw new AdPullException("wuli app pkg is empty!");
		}

		AdRecomReq adreq = attribute.getAdreq();
		WuliReq req = new WuliReq();
		req.setSid(posInfo.getText1());
		req.setC(posInfo.getPos());
		req.setTm(System.currentTimeMillis());
		req.setDevice(convertDev(adreq));
		req.setApp(convertApp(adreq, posInfo));
		req.setNet(convertNet(adreq));
		return req;
	}

	private WuliReqNet convertNet(AdRecomReq adreq) {
		AdUserInfo aduser = adreq.getUserinfo();
		WuliReqNet net = new WuliReqNet();
		net.setIp(aduser.getClient_ip());
		net.setMac(aduser.getMac());
		net.setNetwork(convertNetwork(adreq.getNet()));
		net.setUa(aduser.getUa());
		return net;
	}

	private String convertNetwork(String net) {
		if ("wifi".equals(net)) {
			return "DNS1201";
		}
		if ("5g".equals(net)) {
			return "DNS1205";
		}
		if ("4g".equals(net)) {
			return "DNS1204";
		}
		if ("3g".equals(net)) {
			return "DNS1203";
		}
		if ("2g".equals(net)) {
			return "DNS1202";
		}
		return "DNS1200";
	}

	private WuliReqDev convertDev(AdRecomReq adreq) {
		AdUserInfo aduser = adreq.getUserinfo();

		WuliReqDev dev = new WuliReqDev();
		dev.setId(aduser.getImei());
		dev.setImei(aduser.getImei());
		dev.setAdh(aduser.getDevice_height());
		dev.setAdw(aduser.getDevice_width());
		dev.setDpi(aduser.getDip());
		dev.setIdfa(aduser.getIdfa());
		dev.setImsi(aduser.getImsi());
		dev.setLatitude(UniversalUtils.convert2Double(aduser.getLat()));
		dev.setLongitude(UniversalUtils.convert2Double(aduser.getLon()));
		dev.setMake(aduser.getBrand_name());
		dev.setModel(aduser.getPhonemodel());
		dev.setOrientation(1);
		dev.setOsv(convertOsv(aduser.getOsversion()));
		dev.setPlatform(convertOs(aduser.getOs()));
		dev.setType("DTS1101");
		dev.setAdid(aduser.getAdid());
		return dev;
	}

	private String convertOsv(String osv) {
		if(StringUtils.isEmpty(osv) || "unknow".equalsIgnoreCase(osv)) {
			return "5.1.1";
		}
		
		int firstIndex = osv.indexOf('.');
		if(firstIndex == -1) {
			return osv + ".0.0";
		}
		
		if(firstIndex == osv.lastIndexOf('.')) {
			return osv + ".0";
		}
		
		return osv;
	}
	
	public static void main(String[] args) {
		System.out.println(new WuliAdSupplier().convertOsv("7"));
		System.out.println(new WuliAdSupplier().convertOsv("unkown"));
		System.out.println(new WuliAdSupplier().convertOsv(null));
		System.out.println(new WuliAdSupplier().convertOsv("7.0"));
		System.out.println(new WuliAdSupplier().convertOsv("7.0.0"));
	}

	private String convertOs(String os) {
		if ("ios".equals(os)) {
			return "DPS1002";
		}
		return "DPS1001";
	}

	private WuliReqApp convertApp(AdRecomReq adreq, DSPPosition posInfo) {
		String pkg = posInfo.getText3();
		String key = posInfo.getText2();
		String[] keys = key.split("\\|");

		WuliReqApp app = new WuliReqApp();
		app.setBundle(pkg);
		app.setName(keys[0]);
		app.setVer(keys[1]);
		return app;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public AsynAbstractTask packageTask(Parameter params, String hashCode) {
		AdProxyWuliTask task = new AdProxyWuliTask();
		WuliReq param = (WuliReq) params;

		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("X-Forwarded-For", param.getNet().getIp());
		headers.put("User-Agent", param.getNet().getUa());
		param.getNet().setUa(null);

		task.setParam(param);
		task.setDataFormat(DataFormat.DATA_FORMAT_JSON);
		task.setInvokeType(InvokeType.INVOKE_TYPE_POST);
		task.setHashCode(hashCode);
		task.setUrl(SystemContext.getDynamicPropertyHandler().get(PROP_KEY_URL));
		task.setHeaders(headers);
		return task;
	}

}
