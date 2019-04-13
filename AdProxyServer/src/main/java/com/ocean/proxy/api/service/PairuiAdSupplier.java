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
import com.ocean.core.common.security.SecurityEncode;
import com.ocean.core.common.system.Constants;
import com.ocean.core.common.system.SystemContext;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
import com.ocean.core.common.threadpool.workthread.Task.DataFormat;
import com.ocean.core.common.threadpool.workthread.Task.InvokeType;
import com.ocean.persist.api.proxy.AdPullException;
import com.ocean.persist.api.proxy.pairui.PairuiBidImp;
import com.ocean.persist.api.proxy.pairui.PairuiBidReq;
import com.ocean.persist.api.proxy.pairui.PairuiBidReqApp;
import com.ocean.persist.api.proxy.pairui.PairuiBidReqDev;
import com.ocean.persist.api.proxy.pairui.PairuiBidReqGeo;
import com.ocean.persist.api.proxy.pairui.PairuiBidReqNative;
import com.ocean.persist.api.proxy.pairui.PairuiBidReqNativeImg;
import com.ocean.persist.api.proxy.pairui.PairuiResp;
import com.ocean.persist.api.proxy.pairui.PairuiRespBid;
import com.ocean.persist.api.proxy.pairui.PairuiRespImg;
import com.ocean.persist.api.proxy.pairui.PairuiRespNav;
import com.ocean.persist.model.proxy.DSPPosition;
import com.ocean.proxy.api.base.AbstractAsynAdSupplier;
import com.ocean.proxy.serverdis.AdProxyPairuiTask;
import com.ocean.proxy.thrift.entity.AdContent;
import com.ocean.proxy.thrift.entity.AdImg;
import com.ocean.proxy.thrift.entity.AdMutiAction;
import com.ocean.proxy.thrift.entity.AdRecomReply;
import com.ocean.proxy.thrift.entity.AdRecomReq;
import com.ocean.proxy.thrift.entity.AdUserInfo;
import com.ocean.proxy.thrift.entity.UserAdSpaceAttri;

@Component(value = "PairuiAdSupplier")
public class PairuiAdSupplier extends AbstractAsynAdSupplier {

	private static Logger logger = LoggerFactory.getLogger(PairuiAdSupplier.class);

	private static final String PROP_KEY_ISTEST = "pairui.istest";
	
	private static final String PROP_KEY_URL = "pairui.url";

	private static final int DEV_TYPE_PHONE = 2;

	private static final String OPENTYPE_DOWN = "2";

	private static final List<String> openTypes = new ArrayList<String>();

	private static final List<String> imageMimes = new ArrayList<String>();

	private static final List<String> iconMimes = new ArrayList<String>();
	 
	//
	// private static final int RETURN_SUCCESS_CODE = 0;

	static {
		openTypes.add("1");
		openTypes.add("2");
		openTypes.add("3");

		imageMimes.add("image/jpeg");
		imageMimes.add("image/png");
		imageMimes.add("image/gif");
		imageMimes.add("application/javascript");
		imageMimes.add("text/html");

		iconMimes.add("image/jpeg");
		iconMimes.add("image/png");
		iconMimes.add("image/gif");
	}

	public AdRecomReply invoke(InvokeAttribute attribute) throws AdPullException {
		// 参数转换
		AdRecomReq adreq = attribute.getAdreq();
		PairuiBidReq params = parseParams(attribute);
		// 调用API
		PairuiResp response = (PairuiResp) invoke(params, PairuiResp.class, adreq.getHash(), String.valueOf(adreq.getUserAdSpaceAttri().getAdSpaceId()),
				"joinDSP:pairui");
		// 解析结果
		return parseResult(response);
	}

	public AdRecomReply parseResult(PairuiResp resp) throws AdPullException {
		if (resp == null) {
			throw new AdPullException("pairui return null");
		}
		if (CollectionUtils.isEmpty(resp.getSeatbid())) {
			return null;
		}
		List<PairuiRespBid> bids = resp.getSeatbid().get(0).getBids();
		if (CollectionUtils.isEmpty(bids)) {
			return null;
		}

		AdRecomReply adresp = new AdRecomReply();
		adresp.setStatus(status);
		adresp.setAd_contents(dealAds(bids));
		return adresp;
	}
	private List<AdContent>  dealAds(List<PairuiRespBid> adDatas) throws AdPullException{
		List<AdContent> list=new ArrayList<AdContent> ();
		
		for(PairuiRespBid bid:adDatas){
			AdContent content = new AdContent();

			AdMutiAction action = new AdMutiAction();
			content.setMutiAction(Collections.singletonList(action));

			String linkUrl = findLinkUrl(bid);

			// 落地页
			action.setLinkurl(linkUrl);
			content.setLinkurl(linkUrl);

			// 上报链接
			Map<String, List<String>> map = new HashMap<String, List<String>>();
			content.setThirdReportLinks(map);
			List<String> clickL = new ArrayList<String>();
			List<String> viewL = new ArrayList<String>();
			if (CollectionUtils.isNotEmpty(bid.getTrackurls())) {
				viewL.addAll(bid.getTrackurls());
				map.put(SHOW, viewL);
			}

			if (CollectionUtils.isNotEmpty(bid.getClicktracking())) {
				clickL.addAll(bid.getClicktracking());

			}
			map.put(CLICK, clickL);

			// 内容类型1表示链接推广,2表示应用推广,3未使用 4表示电话推广
			int actType = ACTION_WEB;
			if (OPENTYPE_DOWN.equals(bid.getOpentype())) {
				actType = ACTION_APP;
				action.setCpApk(linkUrl);
				action.setCpPackage(bid.getBundle());
				// action.setCpApkSize(bid.getApp_size());
			}
			action.setType(actType);

			PairuiRespNav nav = bid.getNativeObj();
			if (nav == null) {
				logger.error("pairui return native object is null.");
				continue;
			}

			// 标题, 描述
			String title = StringUtils.isEmpty(nav.getTitle()) ? defTitle : nav.getTitle();
			String desc = nav.getDescription();
			content.setMarketTitle(title);
			String guideTitle = StringUtils.isEmpty(desc) ? title : desc;
			content.setGuideTitle(guideTitle);
			action.setGuideTitle(guideTitle);

			//暂时只取第一张图
			PairuiRespImg pairuiImg = nav.getImage().get(0);
			PairuiRespImg pairuiIcon = nav.getIcon();
			String imgUrl = pairuiImg != null ? pairuiImg.getUrl() : null;
			String iconUrl = pairuiIcon != null ? pairuiIcon.getUrl() : null;

			// icon_src是指广告图标
			content.setCpIcon(iconUrl);

			// 图片
			List<AdImg> imgs = new ArrayList<AdImg>();
			content.setImglist(imgs);
			AdImg img = new AdImg();
			img.setSrc(imgUrl);
			imgs.add(img);

			// APP广告
			if(actType == ACTION_APP) {
				action.setCpIcon(iconUrl);
				action.setCpMemo(nav.getDescription());
			}
			list.add(content);
		}
		return list;
	}
	private String findLinkUrl(PairuiRespBid bid) throws AdPullException {
		if (!StringUtils.isEmpty(bid.getClickthrough())) {
			return bid.getClickthrough();
		}
		if (!StringUtils.isEmpty(bid.getDownloadurl())) {
			return bid.getDownloadurl();
		}
		if (!StringUtils.isEmpty(bid.getLandpage())) {
			return bid.getLandpage();
		}
		throw new AdPullException("pairui return link url is empty.");
	}

//	private String removePvEventMacro(String url) {
//		if (StringUtils.isEmpty(url)) {
//			return url;
//		}
//		return url.replaceAll("%%WIN_PRICE%%", "0");
//	}

	public PairuiBidReq parseParams(InvokeAttribute attribute) throws AdPullException {
		DSPPosition posInfo = attribute.getDspPosition();
		if (StringUtils.isEmpty(posInfo.getText3())) {
			throw new AdPullException("pairui app pkg is empty!");
		}

		AdRecomReq adreq = attribute.getAdreq();
		PairuiBidReq req = new PairuiBidReq();
		req.setId(adreq.getHash());
//		req.setIstest(false);
		req.setIstest(isTest());
		// 当前只有安卓的流量
		req.setSecure(0);
		req.setImp(convertImp(adreq, posInfo));
		req.setApp(convertApp(adreq, posInfo));
		req.setDevice(convertDev(adreq));
		req.setGeo(convertGeo(adreq));
		// req.setUser(user);
		return req;
	}

	private PairuiBidReqGeo convertGeo(AdRecomReq adreq) {
		AdUserInfo aduser = adreq.getUserinfo();

		PairuiBidReqGeo geo = new PairuiBidReqGeo();
		geo.setCity(aduser.getCity());
		geo.setLat(Double.valueOf(aduser.getLat()));
		geo.setLon(Double.valueOf(aduser.getLon()));
		return geo;
	}

	private PairuiBidReqDev convertDev(AdRecomReq adreq) {
		AdUserInfo aduser = adreq.getUserinfo();

		PairuiBidReqDev dev = new PairuiBidReqDev();
		dev.setUa(aduser.getUa());
		dev.setIp(aduser.getClient_ip());
		dev.setDevicetype(DEV_TYPE_PHONE);
		dev.setMake(aduser.getBrand_name());
		dev.setModel(aduser.getPhonemodel());
		dev.setOs(convertOs(aduser.getOs()));
		dev.setOsv(aduser.getOsversion());
		dev.setW(aduser.getDevice_width());
		dev.setH(aduser.getDevice_height());
		dev.setCarrier(convertCarrier(aduser.getMobile()));
		dev.setConnectiontype(convertConnType(adreq.getNet()));
		dev.setIfa(aduser.getIdfa());
		convertImeis(dev, aduser);
		dev.setAidplain(aduser.getAdid());
		dev.setMacplain(aduser.getMac());
		return dev;
	}

	private void convertImeis(PairuiBidReqDev dev, AdUserInfo aduser) {
		String imei = aduser.getImei();
		if (StringUtils.isEmpty(imei)) {
			return;
		}
		if (imei.length() == Constants.MD5_STR_LEN) {
			// dev.setImeiplain(imei);
			dev.setImeimd5(imei);
		} else if (imei.length() == Constants.SHA1_STR_LEN) {
			dev.setImeisha1(imei);
		} else {
			dev.setImeiplain(imei);
			dev.setImeimd5(SecurityEncode.md5Encode(imei));
		}
	}

	private Integer convertConnType(String net) {
		if ("wifi".equals(net)) {
			return 1;
		}
		if ("4g".equals(net)) {
			return 4;
		}
		if ("3g".equals(net)) {
			return 3;
		}
		if ("2g".equals(net)) {
			return 2;
		}
		if ("5g".equals(net)) {
			return 5;
		}
		return 0;
	}

	private Integer convertCarrier(String mobile) {
		if ("CMCC".equals(mobile)) {
			return 1;
		}
		if ("CUCC".equals(mobile)) {
			return 2;
		}
		if ("CTCC".equals(mobile)) {
			return 3;
		}
		return 0;

	}

	private String convertOs(String os) {
		if ("ios".equals(os)) {
			return "IOS";
		}
		return "Android";
	}

	private PairuiBidReqApp convertApp(AdRecomReq adreq, DSPPosition posInfo) {
		String pkg = posInfo.getText3();

		PairuiBidReqApp app = new PairuiBidReqApp();
		app.setBundle(pkg);
		return app;
	}

	private List<PairuiBidImp> convertImp(AdRecomReq adreq, DSPPosition posInfo) {
		UserAdSpaceAttri userAttr = adreq.getUserAdSpaceAttri();

		List<PairuiBidImp> imps = new ArrayList<PairuiBidImp>();
		PairuiBidImp imp = new PairuiBidImp();
		imps.add(imp);

		// id/tagId都用posId
		imp.setId(posInfo.getPos());
		imp.setTagid(posInfo.getPos());
		imp.setBidfloor(posInfo.getSettlementPrice());
		imp.setW(userAttr.getSpaceWidth());
		imp.setH(userAttr.getSpaceHeight());
		imp.setOpentype(openTypes);
		List<PairuiBidReqNative> natives = new ArrayList<PairuiBidReqNative>();
		PairuiBidReqNative nav = new PairuiBidReqNative();
		natives.add(nav);
		imp.setNatives(natives);

		nav.setImage(new PairuiBidReqNativeImg());
		nav.getImage().setW(imp.getW());
		nav.getImage().setH(imp.getH());
		nav.getImage().setMimes(imageMimes);

		// icon最大100px
		nav.setIcon(new PairuiBidReqNativeImg());
		nav.getIcon().setW(100);
		nav.getIcon().setH(100);
		nav.getIcon().setMimes(iconMimes);
		return imps;
	}

	private Boolean isTest() {
		String istest = SystemContext.getDynamicPropertyHandler().get(PROP_KEY_ISTEST, "0");
		return "1".equals(istest);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public AsynAbstractTask packageTask(Parameter params, String hashCode) {
		AdProxyPairuiTask task = new AdProxyPairuiTask();
		PairuiBidReq param = (PairuiBidReq) params;

		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("p-version", "1.0.3");

		task.setParam(param);
		task.setDataFormat(DataFormat.DATA_FORMAT_JSON);
		task.setInvokeType(InvokeType.INVOKE_TYPE_POST);
		task.setHashCode(hashCode);
		task.setUrl(SystemContext.getDynamicPropertyHandler().get(PROP_KEY_URL));
		task.setHeaders(headers);
		return task;
	}

}
