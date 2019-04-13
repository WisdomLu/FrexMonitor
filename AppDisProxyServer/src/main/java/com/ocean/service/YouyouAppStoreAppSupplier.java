package com.ocean.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.inveno.util.CollectionUtils;
import com.ocean.app.dis.proxy.thrift.entity.AppDisContent;
import com.ocean.app.dis.proxy.thrift.entity.AppDisRecomReply;
import com.ocean.app.dis.proxy.thrift.entity.AppDisRecomReq;
import com.ocean.app.dis.proxy.thrift.entity.AppInfo;
import com.ocean.app.dis.proxy.thrift.entity.AppType;
import com.ocean.app.dis.proxy.thrift.entity.DeviceInfo;
import com.ocean.app.dis.proxy.thrift.entity.ReportType;
import com.ocean.app.dis.proxy.thrift.entity.UserInfo;
import com.ocean.core.common.JsonUtils;
import com.ocean.core.common.system.ErrorCode;
import com.ocean.handler.base.AbstractAppDisSupplier;
import com.ocean.persist.app.dis.AppDisException;
import com.ocean.persist.app.dis.AppDisResponse;
import com.ocean.persist.app.dis.youyou.ADAppInfo;
import com.ocean.persist.app.dis.youyou.YouyouReportData;
import com.ocean.persist.app.dis.youyou.YouyouRespData;
import com.ocean.persist.app.dis.youyou.pkgSearch.YouyouPkgSearchRespose;
import com.ocean.service.youyouInvokeHandler.YouyouPkgSearchHandler;
import com.ocean.service.youyouInvokeHandler.base.YouyouInvokeHandlerFactory;
import com.ocean.service.youyouInvokeHandler.base.YouyouMethodType;

@Component
public class YouyouAppStoreAppSupplier extends AbstractAppDisSupplier {
	public static final String YY_REPORT_TYPE_TK_IMP = "tk_imp";
	public static final String YY_REPORT_TYPE_TK_CLK = "tk_clk";
	public static final String YY_REPORT_TYPE_TK_CLK200 = "tk_clk200";
	public static final String YY_REPORT_TYPE_TK_DLE = "tk_dle";
	public static final String YY_REPORT_TYPE_TK_INS = "tk_ins";
	public static final String YY_REPORT_TYPE_TK_ACK = "tk_ack";
	public static final String YY_REPORT_TYPE_TK_OPEN = "tk_open";

	@Override
	public AppDisRecomReply invoke(AppDisRecomReq req) throws AppDisException {
		switch (req.getInterType()) {
		case PKG_SEARCH:
			return pkgSearch(req);
		default:
			throw new AppDisException(ErrorCode.PARAM_ERROR,
					"can not find the mapping interface:"
							+ req.getInterType().name());
		}
	}

	private AppDisRecomReply pkgSearch(AppDisRecomReq req)
			throws AppDisException {
		YouyouPkgSearchHandler handler = (YouyouPkgSearchHandler) YouyouInvokeHandlerFactory
				.getHandler(YouyouMethodType.PKG_SEARCH);
		YouyouPkgSearchRespose resp = (YouyouPkgSearchRespose) handler
				.invok(req);

		if(resp==null){
			return null;
		}
		
		if (!"1000".equals(resp.getResp_status())) {
			throw new AppDisException(ErrorCode.INTER_ERROR,
					"return error code:" + resp.getResp_status());
		}

		YouyouRespData resp_data = resp.getResp_data();
		if (resp_data == null) {
			return null;
		}
		List<ADAppInfo> appLists = resp_data.getAppList();
		if (CollectionUtils.isEmpty(appLists)) {
			return null;
		}

		// 返回值封装
		AppDisRecomReply proxyReply = new AppDisRecomReply();
		proxyReply
				.setErrorCode(com.ocean.app.dis.proxy.thrift.entity.ErrorCode.RC_SUCCESS);

		List<AppDisContent> appL = new ArrayList<AppDisContent>();
		for (ADAppInfo item : resp.getResp_data().getAppList()) {
			AppDisContent appDisContent = new AppDisContent();
			appDisContent.setApkMd5(item.getApkMd5());
			appDisContent.setApkUrl(item.getDownloadUrl());
			appDisContent.setIsAd(1);

			// 设置上报用字段
			Map<String, List<String>> report = new HashMap<String, List<String>>();

			report.put(SHOW,
					wrapCommonReportData(YY_REPORT_TYPE_TK_IMP, req, item));

			report.put(CLICK,
					wrapCommonReportData(YY_REPORT_TYPE_TK_CLK200, req, item));

			List<String> downL = new ArrayList<String>();
			downL.addAll(wrapCommonReportData(YY_REPORT_TYPE_TK_CLK, req, item));
			report.put(DOWNLOAD_BEGIN, downL);

			report.put(DOWNLOAD_END,
					wrapCommonReportData(YY_REPORT_TYPE_TK_DLE, req, item));

			List<String> installL = new ArrayList<String>();
			installL.addAll(wrapCommonReportData(YY_REPORT_TYPE_TK_INS, req,
					item));
			report.put(INSTALL, installL);

			report.put(ACTIVE,
					wrapCommonReportData(YY_REPORT_TYPE_TK_ACK, req, item));

			AppInfo app = new AppInfo();
			app.setAppName(item.getName());
			if (StringUtils.isNotEmpty(item.getIconUrl())) {
				String icons[] = item.getIconUrl().split(";");
				app.setIconUrl(icons[0]);
			}

			app.setPkgName(item.getPackageName());
			app.setType(AppType.TYPE_APP);
			if ("game".equals(item.getPackageName())) {
				app.setType(AppType.TYPE_GAME);
			}
			app.setVersionCode(Integer.toString(item.getVersionCode()));
			app.setFileSize(Integer.toString(item.getSize()));
			app.setVersionName(item.getVersionName());
			appDisContent.setAppInfo(app);
			appDisContent.setThirdReportParams(report);
			appDisContent.setReportType(ReportType.REPORT_POST);
			appL.add(appDisContent);
		}
		proxyReply.setAppDisContent(appL);
		return proxyReply;
	}

	public List<String> wrapCommonReportData(String type, AppDisRecomReq param,
			ADAppInfo app) throws AppDisException {
		YouyouReportData data = new YouyouReportData();

		DeviceInfo device = param.getDevice();
		data.setMd(device.getPhonemodel());// 手机型号
		data.setAndroidid(device.getAdid());// 移动设备AndroidId
		data.setSerialno(device.getSerialNo());
		data.setBr(device.getBrandName());// 手机品牌
		data.setOs(Integer.valueOf(device.getOsversion()));
		data.setOsv(device.getOsVerName());
		data.setCarrier(getMobile(device.getMobile()));// 运营商
		data.setSw(Integer.valueOf(device.getDeviceWidth()));
		data.setSh(Integer.valueOf(device.getDeviceHeight()));
		data.setDip(Double.parseDouble(device.getDip()));
		data.setMac(device.mac);
		data.setImei(device.getImei());

		UserInfo userInfo = param.getUserInfo();
		data.setIp(userInfo.getClientIp());// Ip
		data.setUa(userInfo.getUa());
		data.setNet(getNetwork(userInfo.getNet()));

		data.setKey(app.getKey());
		data.setDot_type(type);
		data.setPnames(app.getPackageName());

		List<String> list = new ArrayList<String>();
		list.add(JsonUtils.toJson(data));
		return list;
	}

	private Integer getMobile(String moble) {
		if (MOBILE_CMCC.equals(moble)) {
			return 46000;
		} else if (MOBILE_CUCC.equals(moble)) {
			return 46002;
		} else if (MOBILE_CTCC.equals(moble)) {
			return 46003;
		}
		return null;
	}

	private int getNetwork(String net) {
		if (NETWORK_WIFI.equals(net)) {
			return 1;
		} else if (NETWORK_2G.equals(net)) {
			return 2;
		} else if (NETWORK_3G.equals(net)) {
			return 3;
		} else if (NETWORK_4G.equals(net)) {
			return 4;
		}
		return 0;
	}

	@Override
	public List<String> pvReport(String interTye, AppDisRecomReq req,
			AppDisResponse resp) throws AppDisException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> clickReport(String interTye, AppDisRecomReq req,
			AppDisResponse resp) throws AppDisException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> beginDownloadReport(String interTye,
			AppDisRecomReq req, AppDisResponse resp) throws AppDisException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> endDownloadReport(String interTye, AppDisRecomReq req,
			AppDisResponse resp) throws AppDisException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> installReport(String interTye, AppDisRecomReq req,
			AppDisResponse resp) throws AppDisException {
		// TODO Auto-generated method stub
		return null;
	}

}
