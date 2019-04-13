package com.ocean.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.inveno.util.CollectionUtils;
import com.ocean.app.dis.proxy.thrift.entity.AppDisContent;
import com.ocean.app.dis.proxy.thrift.entity.AppDisRecomReply;
import com.ocean.app.dis.proxy.thrift.entity.AppDisRecomReq;
import com.ocean.app.dis.proxy.thrift.entity.AppImg;
import com.ocean.app.dis.proxy.thrift.entity.AppInfo;
import com.ocean.app.dis.proxy.thrift.entity.AppType;
import com.ocean.app.dis.proxy.thrift.entity.ReportType;
import com.ocean.core.common.system.ErrorCode;
import com.ocean.handler.base.AbstractAppDisSupplier;
import com.ocean.persist.app.dis.AppDisException;
import com.ocean.persist.app.dis.AppDisResponse;
import com.ocean.persist.app.dis.zs.listsearch.ZsListSearchApp;
import com.ocean.persist.app.dis.zs.listsearch.ZsListSearchReply;
import com.ocean.persist.app.dis.zs.pkgsearch.ZsPkgSearchApp;
import com.ocean.persist.app.dis.zs.pkgsearch.ZsPkgSearchReply;
import com.ocean.persist.app.dis.zs.pkgsearch.ZsPkgSearchRequest;
import com.ocean.service.zsInvokeHandler.ZsListSearchHandler;
import com.ocean.service.zsInvokeHandler.ZsPkgSearchHandler;
import com.ocean.service.zsInvokeHandler.base.ZsInvokeHandlerFactory;
import com.ocean.service.zsInvokeHandler.base.ZsMethodType;

@Component(value = "ZsDownloaderSupplier")
public class ZsDownloaderSupplier extends AbstractAppDisSupplier {
	public AppDisRecomReply invoke(AppDisRecomReq params)
			throws AppDisException {
		switch (params.getInterType()) {
		case APP_LIST_SEARCH:
			return appListSearch(params);
		case PKG_SEARCH:
			return pkgSearch(params);
		default:
			throw new AppDisException(ErrorCode.PARAM_ERROR,
					"can not find the mapping interface:"
							+ params.getInterType().name());
		}
	}

	private AppDisRecomReply appListSearch(AppDisRecomReq req)
			throws AppDisException {
		ZsListSearchHandler handler = (ZsListSearchHandler) ZsInvokeHandlerFactory
				.getHandler(ZsMethodType.APP_LIST_SEARCH);
		ZsListSearchReply reply = (ZsListSearchReply) handler.invok(req);

		if (reply == null||reply.getLsit().size()==0) {
			return null;
		}

		AppDisRecomReply proxyReply = new AppDisRecomReply();
		proxyReply
				.setErrorCode(com.ocean.app.dis.proxy.thrift.entity.ErrorCode.RC_SUCCESS);
		List<AppDisContent> appL = new ArrayList<AppDisContent>();

		for (ZsListSearchApp item : reply.getLsit()) {
			AppDisContent appDisContent = new AppDisContent();
			appDisContent.setApkUrl(item.getDownload_url());
			appDisContent.setContent(item.getDescription());

			appDisContent.setIsAd(1);

			if (item.getImages() != null && item.getImages().size() != 0) {
				for (String s : item.getImages()) {
					AppImg img = new AppImg();
					img.setSrc(s);
					appDisContent.setImglist(Collections.singletonList(img));
				}
			}

			AppInfo app = new AppInfo();
			app.setAppName(item.getPackage_name());
			StringBuffer iconUrls = new StringBuffer();
			for (String s : item.getIcons()) {
				iconUrls.append(s);
			}
			app.setIconUrl(iconUrls.toString());
			app.setPkgName(item.getPackage_name());
			app.setType(AppType.TYPE_APP);
			app.setDesc(item.getDescription());
			appDisContent.setAppInfo(app);

			// 设置上报用字段
			Map<String, List<String>> report = new HashMap<String, List<String>>();
			if (CollectionUtils.isNotEmpty(item.getShow_url())) {
				report.put(SHOW, item.getShow_url());
			}
			
			List<String> downloadL = new ArrayList<String>();
			if (CollectionUtils.isNotEmpty(item.getClick_url())) {
				downloadL.addAll(item.getClick_url());
			}
			if (CollectionUtils.isNotEmpty(item.getDownload_start_url())) {
				downloadL.addAll(item.getDownload_start_url());
			}
			report.put(DOWNLOAD_BEGIN, downloadL);
			
			if (CollectionUtils.isNotEmpty(item.getDownload_finish_url())) {
				report.put(DOWNLOAD_END, item.getDownload_finish_url());
			}

			List<String> installL = new ArrayList<String>();
			if (CollectionUtils.isNotEmpty(item.getInstall_start_url())) {
				installL.addAll(item.getInstall_start_url());
			}
			if (CollectionUtils.isNotEmpty(item.getInstall_finish_url())) {
				installL.addAll(item.getInstall_finish_url());
			}
			report.put(INSTALL, installL);

			if (CollectionUtils.isNotEmpty(item.getOpen_url())) {
				report.put(ACTIVE, item.getOpen_url());
			}

			appDisContent.setThirdReportLinks(report);
			appDisContent.setReportType(ReportType.REPORT_GET);
			appL.add(appDisContent);
		}
		proxyReply.setAppDisContent(appL);
		return proxyReply;
	}

	private AppDisRecomReply pkgSearch(AppDisRecomReq req)
			throws AppDisException {
		ZsPkgSearchHandler handler = (ZsPkgSearchHandler) ZsInvokeHandlerFactory
				.getHandler(ZsMethodType.PKG_SEARCH);
		return parseKeywordSearchResp(req, handler);

	}

	private AppDisRecomReply parseKeywordSearchResp(AppDisRecomReq req,
			ZsPkgSearchHandler handler) throws AppDisException {
		ZsPkgSearchRequest request = handler.parsePkgParam(req);
		ZsPkgSearchReply reply = (ZsPkgSearchReply) handler.invok(req,
				request);

		if (reply == null||reply.getList().size()==0) {
			return null;
		}

		// 返回值封装
		AppDisRecomReply proxyReply = new AppDisRecomReply();
		proxyReply
				.setErrorCode(com.ocean.app.dis.proxy.thrift.entity.ErrorCode.RC_SUCCESS);
		List<AppDisContent> appL = new ArrayList<AppDisContent>();
		for (ZsPkgSearchApp item : reply.getList()) {
			AppDisContent appDisContent = new AppDisContent();

			appDisContent.setApkMd5(item.getMd5());
			appDisContent.setApkUrl(item.getDownload_url());
			appDisContent.setContent(item.getDescription());

			appDisContent.setIsAd(1);

			if (item.getScreenshots() != null
					&& item.getScreenshots().getNormal() != null
					&& item.getScreenshots().getNormal().size() != 0) {
				for (String s : item.getScreenshots().getNormal()) {
					AppImg img = new AppImg();
					img.setSrc(s);
					appDisContent.setImglist(Collections.singletonList(img));
				}
			}

			AppInfo app = new AppInfo();
			app.setAppName(item.getTitle());

			app.setIconUrl(item.getIcons().toString());
			app.setPkgName(item.getPackage_name());

			app.setType(AppType.TYPE_APP);
			app.setVersionCode(String.valueOf(item.getVersion_code()));
			app.setVersionName(item.getVersion_name());
			appDisContent.setAppInfo(app);

			// 设置上报用字段
			Map<String, List<String>> report = new HashMap<String, List<String>>();
			if (CollectionUtils.isNotEmpty(item.getShow_url())) {
				report.put(SHOW, item.getShow_url());
			}
			
			List<String> downloadL = new ArrayList<String>();
			if (CollectionUtils.isNotEmpty(item.getClick_url())) {
				downloadL.addAll(item.getClick_url());
			}
			if (CollectionUtils.isNotEmpty(item.getDownload_start_url())) {
				downloadL.addAll(item.getDownload_start_url());
			}
			report.put(DOWNLOAD_BEGIN, downloadL);
			
			if (CollectionUtils.isNotEmpty(item.getDownload_finish_url())) {
				report.put(DOWNLOAD_END, item.getDownload_finish_url());
			}

			List<String> installL = new ArrayList<String>();
			if (CollectionUtils.isNotEmpty(item.getInstall_start_url())) {
				installL.addAll(item.getInstall_start_url());
			}
			if (CollectionUtils.isNotEmpty(item.getInstall_finish_url())) {
				installL.addAll(item.getInstall_finish_url());
			}
			report.put(INSTALL, installL);

			if (CollectionUtils.isNotEmpty(item.getOpen_url())) {
				report.put(ACTIVE, item.getOpen_url());
			}
			
			appDisContent.setThirdReportLinks(report);
			appDisContent.setReportType(ReportType.REPORT_GET);
			appL.add(appDisContent);
		}

		proxyReply.setAppDisContent(appL);

		return proxyReply;
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
