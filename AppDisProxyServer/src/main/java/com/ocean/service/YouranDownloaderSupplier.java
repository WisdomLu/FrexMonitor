package com.ocean.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.inveno.util.CollectionUtils;
import com.ocean.app.dis.proxy.thrift.entity.AppDisContent;
import com.ocean.app.dis.proxy.thrift.entity.AppDisRecomReply;
import com.ocean.app.dis.proxy.thrift.entity.AppDisRecomReq;
import com.ocean.app.dis.proxy.thrift.entity.AppInfo;
import com.ocean.app.dis.proxy.thrift.entity.AppType;
import com.ocean.app.dis.proxy.thrift.entity.ReportType;
import com.ocean.core.common.system.ErrorCode;
import com.ocean.core.common.system.SystemContext;
import com.ocean.handler.base.AbstractAppDisSupplier;
import com.ocean.persist.app.dis.AppDisException;
import com.ocean.persist.app.dis.AppDisResponse;
import com.ocean.persist.app.dis.yrcpd.pkgsearch.YouranPkgSearchApp;
import com.ocean.persist.app.dis.yrcpd.pkgsearch.YouranPkgSearchReq;
import com.ocean.persist.app.dis.yrcpd.pkgsearch.YouranPkgSearchResp;
import com.ocean.persist.common.AppDisConstants;
import com.ocean.service.yrcpd.YouranPkgSearchHandler;
import com.ocean.service.yrcpd.base.YouranInvokeHandlerFactory;
import com.ocean.service.yrcpd.base.YouranMethodType;
@Component(value="YouranDownloaderSupplier")
public class YouranDownloaderSupplier   extends AbstractAppDisSupplier{
	 public static final int YR_REPORT_TYPE_PV = 1;
	 public static final int YR_REPORT_TYPE_CLICK = 2;
	 public static final int YR_REPORT_TYPE_DOWNLOAD_BEGIN = 3;
	 public static final int YR_REPORT_TYPE_DOWNLOAD_END = 4;
	 public static final int YR_REPORT_TYPE_INSTALL_BEGIN = 5;
	 public static final int YR_REPORT_TYPE_INSTALL_END = 6;
	public AppDisRecomReply invoke(AppDisRecomReq params)
			throws AppDisException {
		// TODO Auto-generated method stub
		switch(params.getInterType()){
				case PKG_SEARCH:
					return pkgSearch(params);
		
				default:
				    throw new AppDisException(ErrorCode.PARAM_ERROR,"can not find the mapping interface:"+params.getInterType().name());
		        
		}
	}
	private AppDisRecomReply pkgSearch(AppDisRecomReq req) throws AppDisException{
		YouranPkgSearchHandler handler=(YouranPkgSearchHandler)YouranInvokeHandlerFactory.getHandler(YouranMethodType.PKG_SEARCH);
		
		return parseKeywordSearchResp(req,handler);

	}
	private AppDisRecomReply  parseKeywordSearchResp(AppDisRecomReq req,YouranPkgSearchHandler handler) throws AppDisException{
		YouranPkgSearchReq request=handler.parsePkgParam(req);
		YouranPkgSearchResp reply=(YouranPkgSearchResp)handler.invok(req,request);
		
		if(reply==null||CollectionUtils.isEmpty(reply.getInfo())){
			return null;
		}
		if(reply.getRetCode()!=0){
			throw new AppDisException(ErrorCode.INTER_ERROR,"return error code:"+reply.getRetCode()+" msg:"+reply.getRetMessage());
		}

		//返回值封装
		AppDisRecomReply proxyReply=new AppDisRecomReply();
		proxyReply.setErrorCode(com.ocean.app.dis.proxy.thrift.entity.ErrorCode.RC_SUCCESS);
		List<AppDisContent> appL=new ArrayList<AppDisContent>();
		for(YouranPkgSearchApp item:reply.getInfo()){
			AppDisContent appDisContent=new AppDisContent();
			appDisContent.setApkId(item.getApkId());
			appDisContent.setApkMd5(item.getMd5());
			appDisContent.setApkUrl(item.getDownloadUrl());
			appDisContent.setAppId(item.getAppId());
			//appDisContent.setCategoryId(String.valueOf(item.getCategoryId()));
			//appDisContent.setCategoryName(item.getCategoryName());
			appDisContent.setContent(item.getDescription());
			//appDisContent.setInstalledCount(item.getAppDownCount());
/*				if(StringUtils.isNotEmpty(item.getDownloadCount())){
				appDisContent.setInstalledCount(Integer.parseInt(item.getDownloadCount()));
			}*/
			
			appDisContent.setSignMd5(item.getSequence());
			
			
			appDisContent.setIsAd(1);
			
			AppInfo app=new AppInfo();
			app.setAppName(item.getTitle());	//item.setAppName(item.getTitle());
		
			app.setIconUrl(item.getIcons());
			//app.setMinSdkVersion(String.valueOf(item.getMinSdkVersion()));
			app.setPkgName(item.getPackageName());
			
			app.setType(AppType.TYPE_APP);
			app.setVersionCode(item.getVersionCode());
			app.setFileSize(item.getBytes());
			app.setVersionName(item.getVersionName());
			appDisContent.setAppInfo(app);
			
			//设置上报用字段
            Map<String,List<String>> report=new HashMap<String,List<String>>();
            report.put(SHOW, wrapReportData(YR_REPORT_TYPE_PV,request,item));
            //report.put(CLICK, wrapReportData(YR_REPORT_TYPE_CLICK,request,item));
            
            List<String> downBeginL=new ArrayList<String>();
            downBeginL.addAll(wrapReportData(YR_REPORT_TYPE_CLICK,request,item));//点击放入开始下载里面，应用分发的目前都这么处理
            downBeginL.addAll(wrapReportData(YR_REPORT_TYPE_DOWNLOAD_BEGIN,request,item));
            report.put(DOWNLOAD_BEGIN, downBeginL);
            
            
            report.put(DOWNLOAD_END, wrapReportData(YR_REPORT_TYPE_DOWNLOAD_END,request,item));
           
            List<String> installL=new ArrayList<String>();
            installL.addAll(wrapReportData(YR_REPORT_TYPE_INSTALL_BEGIN,request,item));
            installL.addAll(wrapReportData(YR_REPORT_TYPE_INSTALL_END,request,item));
            report.put(INSTALL,installL);
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
	public List<String> wrapReportData(int type, YouranPkgSearchReq param,
			YouranPkgSearchApp app) throws AppDisException {
		String url=SystemContext.getDynamicPropertyHandler().get(AppDisConstants.YOURAN_REPORT_URL);
		List<String> list=new ArrayList<String> ();
		StringBuilder sb=new StringBuilder(url);
		sb.append("?app_id=").append(param.getApp_id()).append("&hz_id=").append(param.getHz_id())
		.append("&pkg=").append(app.getPackageName()).append("&trackingEvent=").append(type)
		.append("&channel_id=").append(app.getChannel()).append("&sub1=").append(app.getSequence());
		list.add(sb.toString());
		return list;
	}
}
