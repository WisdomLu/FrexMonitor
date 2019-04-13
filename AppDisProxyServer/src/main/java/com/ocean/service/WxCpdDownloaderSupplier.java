package com.ocean.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.inveno.util.CollectionUtils;
import com.ocean.app.dis.proxy.thrift.entity.AppDisContent;
import com.ocean.app.dis.proxy.thrift.entity.AppDisRecomReply;
import com.ocean.app.dis.proxy.thrift.entity.AppDisRecomReq;
import com.ocean.app.dis.proxy.thrift.entity.AppInfo;
import com.ocean.app.dis.proxy.thrift.entity.AppType;
import com.ocean.app.dis.proxy.thrift.entity.ReportType;
import com.ocean.core.common.AsignUtil;
import com.ocean.core.common.JsonUtils;
import com.ocean.core.common.system.ErrorCode;
import com.ocean.handler.base.AbstractAppDisSupplier;
import com.ocean.persist.app.dis.AppDisException;
import com.ocean.persist.app.dis.AppDisResponse;
import com.ocean.persist.app.dis.wxcpd.WxAppParamData;
import com.ocean.persist.app.dis.wxcpd.WxAppPullParams;
import com.ocean.persist.app.dis.wxcpd.WxAppRespData;
import com.ocean.persist.app.dis.wxcpd.WxAppResponse;
import com.ocean.persist.app.dis.wxcpd.WxTracking;
import com.ocean.persist.app.dis.wxcpd.asyn.WxAsynApp;
import com.ocean.persist.app.dis.wxcpd.asyn.WxAsynAppPullParams;
import com.ocean.persist.app.dis.wxcpd.asyn.WxAsynAppResponse;
import com.ocean.persist.app.dis.wxcpd.asyn.WxReportBody;
import com.ocean.persist.app.dis.wxcpd.keywordSearch.KeywordSearchWxApp;
import com.ocean.persist.app.dis.wxcpd.keywordSearch.KeywordSearchWxReq;
import com.ocean.persist.app.dis.wxcpd.keywordSearch.KeywordSearchWxResp;
import com.ocean.service.wxcpdHandler.WxAppListSearchHandler;
import com.ocean.service.wxcpdHandler.WxAsynAppListSearchHandler;
import com.ocean.service.wxcpdHandler.WxKeywordSearchHandler;
import com.ocean.service.wxcpdHandler.base.WxInvokeHandlerFactory;
import com.ocean.service.wxcpdHandler.base.WxMethodType;
@Component(value="WxCpdDownloaderSupplier")
public class  WxCpdDownloaderSupplier  extends AbstractAppDisSupplier{
		 public static final String WX_REPORT_TYPE_PV = "0";
		 public static final String WX_REPORT_TYPE_DOWNLOAD_BEGIN = "1";
		 public static final String WX_REPORT_TYPE_DOWNLOAD_END = "2";
		 public static final String WX_REPORT_TYPE_INSTALL = "3";
	
		 private AsignUtil<WxAsynAppPullParams,WxReportBody> asign=new AsignUtil<WxAsynAppPullParams,WxReportBody>();
		 private AsignUtil<KeywordSearchWxReq,WxReportBody> keyWordAsign=new AsignUtil<KeywordSearchWxReq,WxReportBody>();

		 public AppDisRecomReply invoke(AppDisRecomReq params)
				throws AppDisException {
			switch(params.getInterType()){
				case APP_LIST_SEARCH:
					//return appListSearch(params);
					return asynAppListSearch(params);
				case KEY_WORD_SEARCH:
					return keywordSearch(params);
				default:
				    throw new AppDisException(ErrorCode.PARAM_ERROR,"can not find the mapping interface:"+params.getInterType().name());
		    }
			
		}
		private AppDisRecomReply asynAppListSearch(AppDisRecomReq req) throws AppDisException{
			WxAsynAppListSearchHandler handler=(WxAsynAppListSearchHandler)WxInvokeHandlerFactory.getHandler(WxMethodType.APP_LIST_SEARCH);
			
			return parseAsynAppListSearchResp(req,handler);

		}
		private AppDisRecomReply keywordSearch(AppDisRecomReq req) throws AppDisException{
			WxKeywordSearchHandler handler=(WxKeywordSearchHandler)WxInvokeHandlerFactory.getHandler(WxMethodType.KEYWORD_SEARCH);
			
			return parseKeywordSearchResp(req,handler);

		}
		private AppDisRecomReply  parseKeywordSearchResp(AppDisRecomReq req,WxKeywordSearchHandler handler) throws AppDisException{
			KeywordSearchWxReq request=handler.parseKeywordSearchParam(req);
			KeywordSearchWxResp wxReply=(KeywordSearchWxResp)handler.invok(req,request);
			
			if(wxReply==null||CollectionUtils.isEmpty(wxReply.getMsg().getApkList())){
				return null;
			}
			if(wxReply.getCode()!=200){
				throw new AppDisException(ErrorCode.INTER_ERROR,"return error code:"+wxReply.getCode()+" msg:"+wxReply.getMsg());
			}

			//返回值封装
			AppDisRecomReply reply=new AppDisRecomReply();
			reply.setErrorCode(com.ocean.app.dis.proxy.thrift.entity.ErrorCode.RC_SUCCESS);
			List<AppDisContent> appL=new ArrayList<AppDisContent>();
			for(KeywordSearchWxApp item:wxReply.getMsg().getApkList()){
				AppDisContent appDisContent=new AppDisContent();
				appDisContent.setApkId(item.getId());
				appDisContent.setApkMd5(item.getApkMd5());
				appDisContent.setApkUrl(item.getDown_url());
				appDisContent.setAppId(item.getId());
				//appDisContent.setCategoryId(String.valueOf(item.getCategoryId()));
				//appDisContent.setCategoryName(item.getCategoryName());
				//appDisContent.setContent(item.getShortDesc());
				//appDisContent.setInstalledCount(item.getAppDownCount());
/*				if(StringUtils.isNotEmpty(item.getTotalDownloadTimes())){
					appDisContent.setInstalledCount(Integer.parseInt(item.getTotalDownloadTimes()));
				}
				
				appDisContent.setSignMd5(item.getSignatureMd5());*/
				
				
				AppInfo app=new AppInfo();
				app.setAppName(item.getApp_name());	item.setAppName(item.getApp_name());
			
				app.setIconUrl(item.getIcon());
				//app.setMinSdkVersion(String.valueOf(item.getMinSdkVersion()));
				///app.setPkgName(item.getPackageName());
				app.setPkgName(item.getPkg());
				
				app.setType(AppType.TYPE_APP);
				app.setVersionCode(item.getVer_code());
				app.setFileSize(item.getSize());
				//app.setVersionName(item.getVersionName());
				appDisContent.setAppInfo(app);
				
				//设置上报用字段
	            Map<String,List<String>> report=new HashMap<String,List<String>>();
	            report.put(SHOW, wrapKeyWordReportData(WX_REPORT_TYPE_PV,request,item));
	         
	            report.put(DOWNLOAD_BEGIN, wrapKeyWordReportData(WX_REPORT_TYPE_DOWNLOAD_BEGIN,request,item));
	            report.put(DOWNLOAD_END, wrapKeyWordReportData(WX_REPORT_TYPE_DOWNLOAD_END,request,item));
	            report.put(INSTALL, wrapKeyWordReportData(WX_REPORT_TYPE_INSTALL,request,item));
	            appDisContent.setThirdReportParams(report);
	            
	            appDisContent.setReportType(ReportType.REPORT_POST);
				appL.add(appDisContent);
			}
			
			reply.setAppDisContent(appL);
			
			
			return reply;
		}
		private AppDisRecomReply parseAsynAppListSearchResp(AppDisRecomReq req,WxAsynAppListSearchHandler handler) throws AppDisException{
			WxAsynAppPullParams request=handler.parseAppListSearchParam(req);
			WxAsynAppResponse wxReply=(WxAsynAppResponse)handler.invok(req,request);
			
			if(wxReply==null||CollectionUtils.isEmpty(wxReply.getData())){
				return null;
			}
			if(wxReply.getCode()!=200){
				throw new AppDisException(ErrorCode.INTER_ERROR,"return error code:"+wxReply.getCode()+" msg:"+wxReply.getMsg());
			}

			//返回值封装
			AppDisRecomReply reply=new AppDisRecomReply();
			reply.setErrorCode(com.ocean.app.dis.proxy.thrift.entity.ErrorCode.RC_SUCCESS);
			
			List<AppDisContent> appL=new ArrayList<AppDisContent>();
			for(WxAsynApp item:wxReply.getData()){
				AppDisContent appDisContent=new AppDisContent();
				//appDisContent.setApkId(item.getId());
				appDisContent.setApkMd5(item.getMd5());
				appDisContent.setApkUrl(item.getUrl());
				appDisContent.setAppId(item.getId());
				//appDisContent.setCategoryId(String.valueOf(item.getCategoryId()));
				//appDisContent.setCategoryName(item.getCategoryName());
				//appDisContent.setContent(item.getShortDesc());
				//appDisContent.setInstalledCount(item.getAppDownCount());
/*				if(StringUtils.isNotEmpty(item.getTotalDownloadTimes())){
					appDisContent.setInstalledCount(Integer.parseInt(item.getTotalDownloadTimes()));
				}
				
				appDisContent.setSignMd5(item.getSignatureMd5());*/
				
				
				AppInfo app=new AppInfo();
				app.setAppName(item.getName());	item.setAppName(item.getName());
			
				//app.setIconUrl(item.getIconUrl());
				//app.setMinSdkVersion(String.valueOf(item.getMinSdkVersion()));
				///app.setPkgName(item.getPackageName());
				app.setPkgName(item.getPkg());
				
				app.setType(AppType.TYPE_APP);
				app.setVersionCode(item.getVcd());
				app.setFileSize(item.getSize());
				//app.setVersionName(item.getVersionName());
				appDisContent.setAppInfo(app);
				
				//设置上报用字段
	            Map<String,List<String>> report=new HashMap<String,List<String>>();
	            report.put(SHOW, wrapReportData(WX_REPORT_TYPE_PV,request,item));
	         
	            report.put(DOWNLOAD_BEGIN, wrapReportData(WX_REPORT_TYPE_DOWNLOAD_BEGIN,request,item));
	            report.put(DOWNLOAD_END, wrapReportData(WX_REPORT_TYPE_DOWNLOAD_END,request,item));
	            report.put(INSTALL, wrapReportData(WX_REPORT_TYPE_INSTALL,request,item));
	            appDisContent.setThirdReportParams(report);
	            
	            appDisContent.setReportType(ReportType.REPORT_POST);
				appL.add(appDisContent);
			}
			
			reply.setAppDisContent(appL);
			return reply;
		}
		private AppDisRecomReply appListSearch(AppDisRecomReq req) throws AppDisException{
			WxAppListSearchHandler handler=(WxAppListSearchHandler)WxInvokeHandlerFactory.getHandler(WxMethodType.APP_LIST_SEARCH);
			return parseAppListSearchResp(req,(WxAppResponse)handler.invok(req));

		}
		private AppDisRecomReply parseAppListSearchResp(AppDisRecomReq req,WxAppResponse wxReply) throws AppDisException{
			if(wxReply.getCode()!=200){
				throw new AppDisException(ErrorCode.INTER_ERROR,"return error code:"+wxReply.getCode()+" msg:"+wxReply.getMsg());
			}
			WxAppRespData app=wxReply.getData();
			if(app==null){
				return null;
			}
			//获取之前的请求应用信息
			WxAppPullParams reqApp=wxReply.getParam();
			WxAppParamData reqData=reqApp.getData();
			//返回值封装
			AppDisRecomReply reply=new AppDisRecomReply();
			reply.setErrorCode(com.ocean.app.dis.proxy.thrift.entity.ErrorCode.RC_SUCCESS);

			AppDisContent appDisContent=new AppDisContent();
			//appDisContent.setApkId(app.getDocid());
			if(StringUtils.isNotEmpty(reqData.getF_apkMD5())){
				appDisContent.setApkMd5(reqData.getF_apkMD5());
			}
			
			appDisContent.setApkUrl(app.getFileUrl());
			
			

			//appDisContent.setCategoryName(app.getCatename());
			//appDisContent.setContent(app.getBrief());
			//appDisContent.setInstalledCount(app.getAll_download_pid());
			//appDisContent.setSignMd5();
			
			
			AppInfo replyApp=new AppInfo();
			if(StringUtils.isNotEmpty(reqData.getF_app_name())){
				replyApp.setAppName(reqData.getF_app_name());
			}
			
			//replyApp.setIconUrl(app.getIcon());
			//replyApp.setMinSdkVersion(String.valueOf(app.getOs_version()));
			replyApp.setPkgName(app.getPkg());

			
			replyApp.setType(AppType.TYPE_APP);
			if(StringUtils.isNotEmpty(reqData.getF_version_code())){
				replyApp.setVersionCode(reqData.getF_version_code());
			}
			if(StringUtils.isNotEmpty(reqData.getF_version_name())){
				replyApp.setVersionName(reqData.getF_version_name());
			}
			
			//replyApp.setFileSize(app.getSize()+"");
			appDisContent.setAppInfo(replyApp);
			
			
			//设置上报用字段
	        Map<String,List<String>> report=new HashMap<String,List<String>>();
	        List<String> dowload_end=new ArrayList<String>(2);
			for(WxTracking track:app.getApp_tracking()){
				if(track.getTrackingEvent()==1&&StringUtils.isNotEmpty(track.getTrackingUrl())){
					report.put(SHOW,  java.util.Collections.singletonList(track.getTrackingUrl()));
				}
				if(track.getTrackingEvent()==0&&StringUtils.isNotEmpty(track.getTrackingUrl())){
					report.put(CLICK,  java.util.Collections.singletonList(track.getTrackingUrl()));
				}
				if(track.getTrackingEvent()==102009&&StringUtils.isNotEmpty(track.getTrackingUrl())){
					report.put(DOWNLOAD_BEGIN,  java.util.Collections.singletonList(track.getTrackingUrl()));
				}
				if(track.getTrackingEvent()==102000&&StringUtils.isNotEmpty(track.getTrackingUrl())){
				
				    if(dowload_end.size()==0){//放在第一个位置
				    	dowload_end.add(track.getTrackingUrl());
				    }else{
				    	String temp=dowload_end.get(0);
				    	dowload_end.set(0,track.getTrackingUrl());
				    	dowload_end.add(temp);
				    }
				
				}
				if(track.getTrackingEvent()==102010&&StringUtils.isNotEmpty(track.getTrackingUrl())){//开始安装
				    	dowload_end.add(track.getTrackingUrl());
	
				}
				if(track.getTrackingEvent()==102001&&StringUtils.isNotEmpty(track.getTrackingUrl())){//安装完成
					report.put(INSTALL,  java.util.Collections.singletonList(track.getTrackingUrl()));
				}
			}
			report.put(DOWNLOAD_END, dowload_end);
            appDisContent.setThirdReportLinks(report);
            appDisContent.setReportType(ReportType.REPORT_GET);
			reply.setAppDisContent(Collections.singletonList(appDisContent));
			return reply;
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
		public List<String> endDownloadReport(String interTye,
				AppDisRecomReq req, AppDisResponse resp) throws AppDisException {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public List<String> installReport(String interTye, AppDisRecomReq req,
				AppDisResponse resp) throws AppDisException {
			// TODO Auto-generated method stub
			return null;
		}
		private WxReportBody getReportData(WxAsynAppPullParams param,
				AppDisResponse resp)throws AppDisException {
			WxReportBody data=new WxReportBody();
			WxAsynApp app=(WxAsynApp)resp;	
			data.setAd_name(app.getAppName());
			data.setApp_download_url(app.getUrl());
			data.setApk_size(app.getSize());
			data.setMd5(app.getMd5());
			
			try {
				asign.dataExchange(param, data, null);
				data.setCuid(DigestUtils.md5Hex(param.getClient_id()+param.getOs_id()+UUID.randomUUID().toString()).toUpperCase());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new  AppDisException("data asign error "+e.getMessage());
			}
		
			return data;
		}
		public List<String> wrapReportData(String type, WxAsynAppPullParams param,
				AppDisResponse resp) throws AppDisException {
			WxReportBody data= getReportData(  param,resp) ;
			data.setType(type);
			List<String> list=new ArrayList<String> ();
			list.add(JsonUtils.toJson(data));
			return list;
		}
		public List<String> wrapKeyWordReportData(String type, KeywordSearchWxReq param,
				AppDisResponse resp) throws AppDisException {
			WxReportBody data= getKeyWordReportData(  param,resp) ;
			data.setType(type);
			List<String> list=new ArrayList<String> ();
			list.add(JsonUtils.toJson(data));
			return list;
		}
		private WxReportBody getKeyWordReportData(KeywordSearchWxReq param,
				AppDisResponse resp)throws AppDisException {
			WxReportBody data=new WxReportBody();
			KeywordSearchWxApp app=(KeywordSearchWxApp)resp;	
			data.setAd_name(app.getAppName());
			data.setApp_download_url(app.getDown_url());
			data.setApk_size(app.getSize());
			data.setMd5(app.getApkMd5());
			
			try {
				keyWordAsign.dataExchange(param, data, null);
				data.setCuid(DigestUtils.md5Hex(param.getClient_id()+param.getOs_id()+UUID.randomUUID().toString()).toUpperCase());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new  AppDisException("data asign error "+e.getMessage());
			}
		
			return data;
		}
}
