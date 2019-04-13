package com.ocean.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;

import com.inveno.util.CollectionUtils;
import com.ocean.app.dis.proxy.thrift.entity.AppDisContent;
import com.ocean.app.dis.proxy.thrift.entity.AppDisRecomReply;
import com.ocean.app.dis.proxy.thrift.entity.AppDisRecomReq;
import com.ocean.app.dis.proxy.thrift.entity.AppInfo;
import com.ocean.app.dis.proxy.thrift.entity.AppType;
import com.ocean.app.dis.proxy.thrift.entity.ReportType;
import com.ocean.core.common.AsignUtil;
import com.ocean.core.common.http.Bean2Utils;
import com.ocean.core.common.system.ErrorCode;
import com.ocean.core.common.system.SystemContext;
import com.ocean.handler.base.AbstractAppDisSupplier;
import com.ocean.persist.app.dis.AppDisException;
import com.ocean.persist.app.dis.AppDisResponse;
import com.ocean.persist.app.dis.yitong.applist.ListSearchYitongApp;
import com.ocean.persist.app.dis.yitong.applist.ListSearchYitongApp_v2;
import com.ocean.persist.app.dis.yitong.applist.ListSearchYitongReq;
import com.ocean.persist.app.dis.yitong.applist.ListSearchYitongResp;
import com.ocean.persist.app.dis.yitong.applist.ListSearchYitongResp_v2;
import com.ocean.persist.app.dis.yitong.applist.ListSearchYitongSecParams;
import com.ocean.persist.app.dis.yitong.kwd.KwdSearchYitongResp;
import com.ocean.persist.app.dis.yitong.pkgSearch.YitongPkgSchReq;
import com.ocean.persist.app.dis.yitong.pkgSearch.YitongPkgSchResp;
import com.ocean.persist.common.AppDisConstants;
import com.ocean.service.yitongInvokeHandler.YitongKwdSearchHandler;
import com.ocean.service.yitongInvokeHandler.YitongListSearchHandler;
import com.ocean.service.yitongInvokeHandler.YitongPkgSearchHandler;
import com.ocean.service.yitongInvokeHandler.base.YitongInvokeHandlerFactory;
import com.ocean.service.yitongInvokeHandler.base.YitongMethodType;
@Component(value="YitongDownloaderSupplier")
public class YitongDownloaderSupplier   extends AbstractAppDisSupplier{
/*	 public static final int YT_REPORT_TYPE_PV = 1;
	 public static final int YT_REPORT_TYPE_CLICK = 2;*/
	 public static final int YT_REPORT_TYPE_DOWNLOAD_BEGIN = 2;
	 public static final int YT_REPORT_TYPE_DOWNLOAD_END = 3;
/*	 public static final int YT_REPORT_TYPE_INSTALL_BEGIN = 6;
	 public static final int YT_REPORT_TYPE_INSTALL_END = 5;*/
	 private static final Map<Integer,String> reportAct=new HashMap<Integer,String>();
	 static{
		 reportAct.put(2, "download_list");
		 reportAct.put(3, "download_list");
	 }
	 private AsignUtil<ListSearchYitongReq,ListSearchYitongReq> asign=new AsignUtil<ListSearchYitongReq,ListSearchYitongReq>();

	public AppDisRecomReply invoke(AppDisRecomReq params)
			throws AppDisException {
		// TODO Auto-generated method stub
		switch(params.getInterType()){
				case APP_LIST_SEARCH:
					
					return appListSearch(params);
				case KEY_WORD_SEARCH:
					return appKwdSearch(params);
				case HOT_WORD_SEARCH:
					return appKwdSearch(params);
				case PKG_SEARCH:
					return appPkgSearch(params);
				default:
				    throw new AppDisException(ErrorCode.PARAM_ERROR,"can not find the mapping interface:"+params.getInterType().name());
		        
		}
	}
	private AppDisRecomReply appListSearch(AppDisRecomReq req) throws AppDisException{
		YitongListSearchHandler handler=(YitongListSearchHandler)YitongInvokeHandlerFactory.getHandler(YitongMethodType.APP_LIST_SEARCH);
		int type=SystemContext.getDynamicPropertyHandler().getInt(AppDisConstants.YITONG_LIST_TYPE,2);
		if(type==2){
			return parseAppListSearchResp_v2(req,handler);
		}
		return parseAppListSearchResp(req,handler);

	}
	private AppDisRecomReply appKwdSearch(AppDisRecomReq req) throws AppDisException{
		YitongKwdSearchHandler handler=(YitongKwdSearchHandler)YitongInvokeHandlerFactory.getHandler(YitongMethodType.KEYWORD_SEARCH);
		return parseAppKwdSearchResp(req,handler);

	}
	private AppDisRecomReply appPkgSearch(AppDisRecomReq req) throws AppDisException{
		YitongPkgSearchHandler handler=(YitongPkgSearchHandler)YitongInvokeHandlerFactory.getHandler(YitongMethodType.PACKAGE_SEARCH);
		return parseAppPkgSearchResp(req,handler);

	}
	private AppDisRecomReply  parseAppKwdSearchResp(AppDisRecomReq req,YitongKwdSearchHandler handler) throws AppDisException{
		ListSearchYitongReq request=handler.parseKwdSearchParam(req);
		ListSearchYitongSecParams secParam=handler.getSecParam(req);
		secParam.setUser_agent(req.getUserInfo().getUa());
		KwdSearchYitongResp reply=(KwdSearchYitongResp)handler.invok(req,request);
		
		if(reply==null||CollectionUtils.isEmpty(reply.getData())){
			return null;
		}
		if(reply.getCode()!=0){
			throw new AppDisException(ErrorCode.INTER_ERROR,"return error code:"+reply.getCode()+" msg:"+reply.getMsg());
		}
		//返回值封装
		AppDisRecomReply proxyReply=new AppDisRecomReply();
		proxyReply.setErrorCode(com.ocean.app.dis.proxy.thrift.entity.ErrorCode.RC_SUCCESS);
		List<AppDisContent> appL=getAppList(request,reply,secParam);
		
		proxyReply.setAppDisContent(appL);
		return proxyReply;
	}
	private List<AppDisContent> getAppList(ListSearchYitongReq request,ListSearchYitongResp_v2 reply,ListSearchYitongSecParams secParam) throws AppDisException{
		List<AppDisContent> appL=new ArrayList<AppDisContent>();
		for(ListSearchYitongApp_v2 item:reply.getData()){
			AppDisContent appDisContent=new AppDisContent();
			//appDisContent.setApkId(item.getApkId());
			appDisContent.setApkMd5(item.getApk_md5());
			appDisContent.setApkUrl(item.getApk_url());
			//appDisContent.setAppId(item.getAppId());
			appDisContent.setCategoryId(String.valueOf(item.getCategory_id1()));
			appDisContent.setCategoryName(item.getCategory_name1());
			//appDisContent.setContent(item.getDesc());
			//appDisContent.setInstalledCount(item.getAppDownCount());
/*				if(StringUtils.isNotEmpty(item.getTotalDownloadTimes())){
				appDisContent.setInstalledCount(Integer.parseInt(item.getTotalDownloadTimes()));
			}
			*/
			//appDisContent.setSignMd5(item.getSequence());
			
			appDisContent.setIsAd(1);
			AppInfo app=new AppInfo();
			app.setAppName(item.getName());	//item.setAppName(item.getTitle());
		
			app.setIconUrl(item.getIcon_url());
			//app.setMinSdkVersion(String.valueOf(item.getMinSdkVersion()));
			app.setPkgName(item.getPkgName());
			
			app.setType(AppType.TYPE_APP);
			app.setVersionCode(item.getVersion_code());
			
			app.setFileSize(String.valueOf(item.getApk_size()));
			app.setVersionName(item.getVersion_name());
			app.setVersionCode(item.getVersion_code());
			appDisContent.setAppInfo(app);
			
			//设置上报用字段
            Map<String,List<String>> report=new HashMap<String,List<String>>();
            //report.put(SHOW, wrapReportData(QYS_REPORT_TYPE_PV,request,item));
            //report.put(CLICK, wrapReportData(QYS_REPORT_TYPE_CLICK,request,item));
            report.put(DOWNLOAD_BEGIN, wrapReportData_v2(YT_REPORT_TYPE_DOWNLOAD_BEGIN,request,item,secParam));
            //report.put(DOWNLOAD_END, wrapReportData(YT_REPORT_TYPE_DOWNLOAD_END,request,item,secParam));
           
/*            List<String> installL=new ArrayList<String>();
            installL.addAll(wrapReportData(QYS_REPORT_TYPE_INSTALL_BEGIN,request,item));
            installL.addAll(wrapReportData(QYS_REPORT_TYPE_INSTALL_END,request,item));
            report.put(INSTALL,installL);*/
           // appDisContent.setThirdReportParams(report);
            appDisContent.setThirdReportLinks(report);
            appDisContent.setReportType(ReportType.REPORT_GET);
			appL.add(appDisContent);
		}
		return appL;
	}
	private AppDisRecomReply  parseAppPkgSearchResp(AppDisRecomReq req,YitongPkgSearchHandler handler) throws AppDisException{
		YitongPkgSchReq request=handler.parsepPkgSearchParam(req);
		ListSearchYitongSecParams secParam=handler.getSecParam(req);
		secParam.setUser_agent(req.getUserInfo().getUa());
		YitongPkgSchResp reply=(YitongPkgSchResp)handler.invok(req,request);
		
		if(reply==null||CollectionUtils.isEmpty(reply.getData())){
			return null;
		}
		if(reply.getCode()!=0){
			throw new AppDisException(ErrorCode.INTER_ERROR,"return error code:"+reply.getCode()+" msg:"+reply.getMsg());
		}
		//返回值封装
		AppDisRecomReply proxyReply=new AppDisRecomReply();
		proxyReply.setErrorCode(com.ocean.app.dis.proxy.thrift.entity.ErrorCode.RC_SUCCESS);
		List<AppDisContent> appL=new ArrayList<AppDisContent>();
		for(ListSearchYitongApp_v2 item:reply.getData()){
			AppDisContent appDisContent=new AppDisContent();
			//appDisContent.setApkId(item.getApkId());
			appDisContent.setApkMd5(item.getApk_md5());
			appDisContent.setApkUrl(item.getApk_url());
			//appDisContent.setAppId(item.getAppId());
			appDisContent.setCategoryId(String.valueOf(item.getCategory_id1()));
			appDisContent.setCategoryName(item.getCategory_name1());
			//appDisContent.setContent(item.getDesc());
			//appDisContent.setInstalledCount(item.getAppDownCount());
/*				if(StringUtils.isNotEmpty(item.getTotalDownloadTimes())){
				appDisContent.setInstalledCount(Integer.parseInt(item.getTotalDownloadTimes()));
			}
			*/
			//appDisContent.setSignMd5(item.getSequence());
			
			appDisContent.setIsAd(1);
			AppInfo app=new AppInfo();
			app.setAppName(item.getName());	//item.setAppName(item.getTitle());
		
			app.setIconUrl(item.getIcon_url());
			//app.setMinSdkVersion(String.valueOf(item.getMinSdkVersion()));
			app.setPkgName(item.getPkgName());
			
			app.setType(AppType.TYPE_APP);
			app.setVersionCode(item.getVersion_code());
			
			app.setFileSize(String.valueOf(item.getApk_size()));
			app.setVersionName(item.getVersion_name());
			app.setVersionCode(item.getVersion_code());
			appDisContent.setAppInfo(app);
			
			//设置上报用字段
            Map<String,List<String>> report=new HashMap<String,List<String>>();
            //report.put(SHOW, wrapReportData(QYS_REPORT_TYPE_PV,request,item));
            //report.put(CLICK, wrapReportData(QYS_REPORT_TYPE_CLICK,request,item));
            report.put(DOWNLOAD_BEGIN, wrapReportData_v2(YT_REPORT_TYPE_DOWNLOAD_BEGIN,request,item,secParam));
            //report.put(DOWNLOAD_END, wrapReportData(YT_REPORT_TYPE_DOWNLOAD_END,request,item,secParam));
           
/*            List<String> installL=new ArrayList<String>();
            installL.addAll(wrapReportData(QYS_REPORT_TYPE_INSTALL_BEGIN,request,item));
            installL.addAll(wrapReportData(QYS_REPORT_TYPE_INSTALL_END,request,item));
            report.put(INSTALL,installL);*/
           // appDisContent.setThirdReportParams(report);
            appDisContent.setThirdReportLinks(report);
            appDisContent.setReportType(ReportType.REPORT_GET);
			appL.add(appDisContent);
		}
		
		proxyReply.setAppDisContent(appL);
		return proxyReply;
	}
	private AppDisRecomReply  parseAppListSearchResp(AppDisRecomReq req,YitongListSearchHandler handler) throws AppDisException{
		ListSearchYitongReq request=handler.parseListSearchParam(req);
		ListSearchYitongSecParams secParam=handler.getSecParam(req);
		ListSearchYitongResp reply=(ListSearchYitongResp)handler.invok(req,request);
		
		if(reply==null||reply.getData()==null||CollectionUtils.isEmpty(reply.getData().getList())){
			return null;
		}
		if(reply.getCode()!=0){
			throw new AppDisException(ErrorCode.INTER_ERROR,"return error code:"+reply.getCode()+" msg:"+reply.getMsg());
		}

		//返回值封装
		AppDisRecomReply proxyReply=new AppDisRecomReply();
		proxyReply.setErrorCode(com.ocean.app.dis.proxy.thrift.entity.ErrorCode.RC_SUCCESS);
		List<AppDisContent> appL=new ArrayList<AppDisContent>();
		for(ListSearchYitongApp item:reply.getData().getList()){
			AppDisContent appDisContent=new AppDisContent();
			//appDisContent.setApkId(item.getApkId());
			//appDisContent.setApkMd5(item.getMd5());
			appDisContent.setApkUrl(item.getReal_dowload_url());
			//appDisContent.setAppId(item.getAppId());
			//appDisContent.setCategoryId(String.valueOf(item.getCategoryId()));
			//appDisContent.setCategoryName(item.getCategoryName());
			//appDisContent.setContent(item.getDescription());
			//appDisContent.setInstalledCount(item.getAppDownCount());
/*				if(StringUtils.isNotEmpty(item.getTotalDownloadTimes())){
				appDisContent.setInstalledCount(Integer.parseInt(item.getTotalDownloadTimes()));
			}
			*/
			//appDisContent.setSignMd5(item.getSequence());
			
			appDisContent.setIsAd(1);
			//appDisContent.setContent(item.getDesc());
			
			
			AppInfo app=new AppInfo();
			app.setAppName(item.getApp_name());	//item.setAppName(item.getTitle());
		
			app.setIconUrl(item.getApp_icon());
			//app.setMinSdkVersion(String.valueOf(item.getMinSdkVersion()));
			app.setPkgName(item.getApp_package());
			
			app.setType(AppType.TYPE_APP);
			app.setVersionCode(item.getVersion_code());
			
			app.setFileSize(String.valueOf(item.getApp_file_size()));
			app.setVersionName(item.getVersion_name());
			appDisContent.setAppInfo(app);
			
			//设置上报用字段
            Map<String,List<String>> report=new HashMap<String,List<String>>();
            //report.put(SHOW, wrapReportData(QYS_REPORT_TYPE_PV,request,item));
            //report.put(CLICK, wrapReportData(QYS_REPORT_TYPE_CLICK,request,item));
            report.put(DOWNLOAD_BEGIN, wrapReportData(YT_REPORT_TYPE_DOWNLOAD_BEGIN,request,item,secParam));
            report.put(DOWNLOAD_END, wrapReportData(YT_REPORT_TYPE_DOWNLOAD_END,request,item,secParam));
           
/*            List<String> installL=new ArrayList<String>();
            installL.addAll(wrapReportData(QYS_REPORT_TYPE_INSTALL_BEGIN,request,item));
            installL.addAll(wrapReportData(QYS_REPORT_TYPE_INSTALL_END,request,item));
            report.put(INSTALL,installL);*/
           // appDisContent.setThirdReportParams(report);
            appDisContent.setThirdReportLinks(report);
            appDisContent.setReportType(ReportType.REPORT_GET);
			appL.add(appDisContent);
		}
		
		proxyReply.setAppDisContent(appL);
		
		
		return proxyReply;
	}
	private AppDisRecomReply  parseAppListSearchResp_v2(AppDisRecomReq req,YitongListSearchHandler handler) throws AppDisException{
		ListSearchYitongReq request=handler.parseListSearchParam(req);
		ListSearchYitongSecParams secParam=handler.getSecParam(req);
		secParam.setUser_agent(req.getUserInfo().getUa());


		ListSearchYitongResp_v2 reply=(ListSearchYitongResp_v2)handler.invok_v2(req,request);
		
		if(reply==null||CollectionUtils.isEmpty(reply.getData())){
			return null;
		}
		if(reply.getCode()!=0){
			throw new AppDisException(ErrorCode.INTER_ERROR,"return error code:"+reply.getCode()+" msg:"+reply.getMsg());
		}
		//返回值封装
		AppDisRecomReply proxyReply=new AppDisRecomReply();
		proxyReply.setErrorCode(com.ocean.app.dis.proxy.thrift.entity.ErrorCode.RC_SUCCESS);
		List<AppDisContent> appL=new ArrayList<AppDisContent>();
		for(ListSearchYitongApp_v2 item:reply.getData()){
			AppDisContent appDisContent=new AppDisContent();
			//appDisContent.setApkId(item.getApkId());
			appDisContent.setApkMd5(item.getApk_md5());
			appDisContent.setApkUrl(item.getApk_url());
			//appDisContent.setAppId(item.getAppId());
			appDisContent.setCategoryId(String.valueOf(item.getCategory_id1()));
			appDisContent.setCategoryName(item.getCategory_name1());
			//appDisContent.setContent(item.getDesc());
			//appDisContent.setInstalledCount(item.getAppDownCount());
/*				if(StringUtils.isNotEmpty(item.getTotalDownloadTimes())){
				appDisContent.setInstalledCount(Integer.parseInt(item.getTotalDownloadTimes()));
			}
			*/
			//appDisContent.setSignMd5(item.getSequence());
			
			appDisContent.setIsAd(1);

			
			
			AppInfo app=new AppInfo();
			app.setAppName(item.getName());	//item.setAppName(item.getTitle());
		
			app.setIconUrl(item.getIcon_url());
			//app.setMinSdkVersion(String.valueOf(item.getMinSdkVersion()));
			app.setPkgName(item.getPkgName());
			
			app.setType(AppType.TYPE_APP);
			app.setVersionCode(item.getVersion_code());
			
			app.setFileSize(String.valueOf(item.getApk_size()));
			app.setVersionName(item.getVersion_name());
			app.setVersionCode(item.getVersion_code());
			appDisContent.setAppInfo(app);
			
			//设置上报用字段
            Map<String,List<String>> report=new HashMap<String,List<String>>();
            //report.put(SHOW, wrapReportData(QYS_REPORT_TYPE_PV,request,item));
            //report.put(CLICK, wrapReportData(QYS_REPORT_TYPE_CLICK,request,item));
            report.put(DOWNLOAD_BEGIN, wrapReportData_v2(YT_REPORT_TYPE_DOWNLOAD_BEGIN,request,item,secParam));
            //report.put(DOWNLOAD_END, wrapReportData(YT_REPORT_TYPE_DOWNLOAD_END,request,item,secParam));
           
/*            List<String> installL=new ArrayList<String>();
            installL.addAll(wrapReportData(QYS_REPORT_TYPE_INSTALL_BEGIN,request,item));
            installL.addAll(wrapReportData(QYS_REPORT_TYPE_INSTALL_END,request,item));
            report.put(INSTALL,installL);*/
           // appDisContent.setThirdReportParams(report);
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
	public List<String> wrapReportData(int type, ListSearchYitongReq param,
			ListSearchYitongApp app,ListSearchYitongSecParams secParam) throws AppDisException {
		ListSearchYitongReq reportData=new ListSearchYitongReq();
		
	
		try {
			asign.dataExchange(param, reportData, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new  AppDisException("data asign error "+e.getMessage());
		}
		secParam.setAction_type(2);
		secParam.setAction(reportAct.get(type));
		secParam.setDownload_app_name(app.getApp_name());
		secParam.setTimestamp(System.currentTimeMillis());
		String secParamStr=Bean2Utils.toHttpParams(secParam);
		try {
			reportData.setParams(URLEncoder.encode(Base64.encodeBase64String(secParamStr.getBytes(CHARSET_CODER)) ,CHARSET_CODER));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		reportData.setAppid(null);
		reportData.setDeveloperid(null);
		reportData.setItemspaceid(null);
		
		if(type==2){
			 return this.formatUrl(app.getDownloadstart_url(), reportData);
		}else if(type==3){
			 return this.formatUrl(app.getDownloadfinish_url(), reportData);
		}
	    return null;
	}
	public List<String> wrapReportData_v2(int type, ListSearchYitongReq param,
			ListSearchYitongApp_v2 app,ListSearchYitongSecParams secParam) throws AppDisException {
		ListSearchYitongReq reportData=new ListSearchYitongReq();
		
	
		try {
			asign.dataExchange(param, reportData, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new  AppDisException("data asign error "+e.getMessage());
		}
		
		secParam.setTimestamp(System.currentTimeMillis());
		secParam.setShow_timestamp(System.currentTimeMillis());
		//secParam.setUser_agent(param.getUser_agent());
		
		String secParamStr=Bean2Utils.toHttpParams(secParam);
		try {
			reportData.setParams(URLEncoder.encode(Base64.encodeBase64String(secParamStr.getBytes(CHARSET_CODER)) ,CHARSET_CODER));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		reportData.setClienttype("appsite");
		reportData.setPage(null);
		reportData.setPage_size(null);
		reportData.setAppid(null);
		reportData.setDeveloperid(null);
		reportData.setItemspaceid(null);
		reportData.setPlatform(null);
		reportData.setAd_type(null);
	
		
		return this.formatUrl(app.getUp_url(), reportData);
		
	}
	private List<String> formatUrl(List<String> rawUrls, ListSearchYitongReq params){
		List<String> list=new ArrayList<String>();
		for(String rawUrl:rawUrls){
			StringBuilder url = new StringBuilder();
			url.append(rawUrl);
			
			url.append(rawUrl.indexOf("?")<0?"?":"&").append(Bean2Utils.toHttpParams(params));
			list.add(url.toString());
		}

		return list;
	}
}
