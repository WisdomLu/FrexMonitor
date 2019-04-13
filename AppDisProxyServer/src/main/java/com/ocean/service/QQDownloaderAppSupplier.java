package com.ocean.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.inveno.util.CollectionUtils;
import com.ocean.app.dis.proxy.thrift.entity.AppDisContent;
import com.ocean.app.dis.proxy.thrift.entity.AppDisRecomReply;
import com.ocean.app.dis.proxy.thrift.entity.AppDisRecomReq;
import com.ocean.app.dis.proxy.thrift.entity.AppInfo;
import com.ocean.app.dis.proxy.thrift.entity.AppType;
import com.ocean.app.dis.proxy.thrift.entity.DeviceInfo;
import com.ocean.app.dis.proxy.thrift.entity.ExtData;
import com.ocean.app.dis.proxy.thrift.entity.InterType;
import com.ocean.app.dis.proxy.thrift.entity.ReportType;
import com.ocean.core.common.JsonUtils;
import com.ocean.core.common.system.ErrorCode;
import com.ocean.core.common.system.SystemContext;
import com.ocean.handler.RedisHandler;
import com.ocean.handler.base.AbstractAppDisSupplier;
import com.ocean.persist.app.dis.AppDisException;
import com.ocean.persist.app.dis.AppDisResponse;
import com.ocean.persist.app.dis.qqDownloader.BaseAppADListItem;
import com.ocean.persist.app.dis.qqDownloader.ReportBody;
import com.ocean.persist.app.dis.qqDownloader.QQDownloaderRequest;
import com.ocean.persist.app.dis.qqDownloader.getAppList.GetAppListPullRespBody;
import com.ocean.persist.app.dis.qqDownloader.getAppList.GetAppListResponse;
import com.ocean.persist.app.dis.qqDownloader.getCategoryList.GetCategoryListRespBody;
import com.ocean.persist.app.dis.qqDownloader.getCategoryList.GetCategoryListResponse;
import com.ocean.persist.app.dis.qqDownloader.getRankAppADList.GetRankAppADListRespBody;
import com.ocean.persist.app.dis.qqDownloader.getRankAppADList.GetRankAppADListResponse;
import com.ocean.persist.app.dis.qqDownloader.getRecommendADList.ADAppInfo;
import com.ocean.persist.app.dis.qqDownloader.getRecommendADList.GetRecommendADListRespBody;
import com.ocean.persist.app.dis.qqDownloader.getRecommendADList.GetRecommendADListResponse;
import com.ocean.persist.app.dis.qqDownloader.getSubjectList.GetSubjectListRespBody;
import com.ocean.persist.app.dis.qqDownloader.getSubjectList.GetSubjectListResponse;
import com.ocean.persist.app.dis.qqDownloader.keywordSearch.KeyWordSearchRespBody;
import com.ocean.persist.app.dis.qqDownloader.keywordSearch.KeyWordSearchResponse;
import com.ocean.persist.common.AppDisConstants;
import com.ocean.persist.common.AppDisErrorCode;
import com.ocean.service.qqInvokHandler.GetAppListHandler;
import com.ocean.service.qqInvokHandler.GetCategoryListHandler;
import com.ocean.service.qqInvokHandler.GetRankAppADListHandler;
import com.ocean.service.qqInvokHandler.GetRecommendADListHandler;
import com.ocean.service.qqInvokHandler.GetSubjectListHandler;
import com.ocean.service.qqInvokHandler.KeyWordSearchHandler;
import com.ocean.service.qqInvokHandler.base.QQInvokHandlerFactory;
import com.ocean.service.qqInvokHandler.base.QQMethodType;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年5月12日 
      @version 1.0 
 */
@Component(value="qqDownloaderAppSupplier")
public class QQDownloaderAppSupplier extends AbstractAppDisSupplier{
	private static Map<Integer,AppType> appTypeMap=new HashMap<Integer,AppType>();
	{
		appTypeMap.put(-1, AppType.TYPE_APP);
		appTypeMap.put(-2, AppType.TYPE_GAME);
	}

	public AppDisRecomReply invoke(AppDisRecomReq req)
			throws AppDisException {
		// TODO Auto-generated method stub
		switch(req.getInterType()){
			case APP_LIST_SEARCH:
				int type=SystemContext.getDynamicPropertyHandler().getInt(AppDisConstants.INTER_QQDOWNLOADER_APP_LIST_TYPE, 1);
				if(type==1){
					return appListSearch(req);
				}else if(type==2){
					return appListSearchBySubject(req);
				}
			case KEY_WORD_SEARCH:
				return keyWordSearch(req);
			case HOT_WORD_SEARCH:
				return keyWordSearch(req);
			case TOP_LIST_SEARCH:
				return topListSearch(req);
			case PKG_SEARCH:
				return pkgSearch(req);
			default:
			    throw new AppDisException(ErrorCode.PARAM_ERROR,"can not find the mapping interface:"+req.getInterType().name());
		}

	}
	private AppDisRecomReply pkgSearch(AppDisRecomReq req) throws AppDisException{
		GetRecommendADListHandler keyWordSearchHandler=(GetRecommendADListHandler)QQInvokHandlerFactory.getHandler(QQMethodType.GET_RECOMMEND_APP_LIST);
		GetRecommendADListResponse resp=(GetRecommendADListResponse)keyWordSearchHandler.invok(req);
		
		return parseRecommendADListResp(req,resp);
	}
	private AppDisRecomReply topListSearch(AppDisRecomReq req) throws AppDisException{
		GetRankAppADListHandler keyWordSearchHandler=(GetRankAppADListHandler)QQInvokHandlerFactory.getHandler(QQMethodType.GET_RANK_APP_LIST);
		GetRankAppADListResponse resp=(GetRankAppADListResponse)keyWordSearchHandler.invok(req);
		
		return paseRankAppListResp(req,resp);
	}
	private AppDisRecomReply keyWordSearch(AppDisRecomReq req) throws AppDisException{
		KeyWordSearchHandler keyWordSearchHandler=(KeyWordSearchHandler)QQInvokHandlerFactory.getHandler(QQMethodType.KEYWORD_SEARCH);
		KeyWordSearchResponse resp=(KeyWordSearchResponse)keyWordSearchHandler.invok(req);
		 return parseKeySearchResp(req,resp);
	}
    private AppDisRecomReply appListSearch(AppDisRecomReq req) throws AppDisException{
	   /*先从缓存中获取分类列表和聚合标签列表*/
		RedisHandler redisHandler=RedisHandler.buildHandler();
		Set<String> categoryIds=redisHandler.getCacheCategoryId(req.getJoinSource());
		
		if(CollectionUtils.isEmpty(categoryIds)){//如果缓存中没有就查询接口
			logger.info("{}:{} get catgoryId list from cache  is empty!",req.getJoinSource().name(),InterType.APP_LIST_SEARCH.name());
			GetCategoryListHandler getCategoryListHandler=(GetCategoryListHandler)QQInvokHandlerFactory.getHandler(QQMethodType.GET_CATEGORY_LIST);
			GetCategoryListResponse catResp=(GetCategoryListResponse)getCategoryListHandler.invok(req);
			if(catResp==null){
				logger.error("joinAppStore:TENCENT_QQDOWNLOADER , get category list is empty!");
				return null;
			}
			GetCategoryListRespBody catRB=(GetCategoryListRespBody)catResp.getBody();
			//查询应用列表
			GetAppListHandler getAppListHander=(GetAppListHandler)QQInvokHandlerFactory.getHandler(QQMethodType.GET_APP_LIST);
			GetAppListResponse getAppListResponse=getAppListHander.invok(req,catRB);
			return parseGetAppListResponse(req,getAppListResponse);
		}else{
			GetAppListHandler getAppListHander=(GetAppListHandler)QQInvokHandlerFactory.getHandler(QQMethodType.GET_APP_LIST);
			GetAppListResponse getAppListResponse=getAppListHander.invok(req, categoryIds);
			return parseGetAppListResponse(req,getAppListResponse);
		}
   }
    private AppDisRecomReply appListSearchBySubject(AppDisRecomReq req) throws AppDisException{
    	GetSubjectListHandler getSubjectListHandler=(GetSubjectListHandler)QQInvokHandlerFactory.getHandler(QQMethodType.GET_SUBJECTLIST);
    	GetSubjectListResponse subResp=(GetSubjectListResponse)getSubjectListHandler.invok(req);
		if(subResp==null){
			logger.error("joinAppStore:TENCENT_QQDOWNLOADER , get subject list is empty!");
			return null;
		}
		GetSubjectListRespBody subBody= subResp.getBody();
		if(subBody.getRet()!=0){
			logger.error("joinAppStore:TENCENT_QQDOWNLOADER , get subject list error,error code:{}",subBody.getRet());
		}
		GetAppListHandler getAppListHander=(GetAppListHandler)QQInvokHandlerFactory.getHandler(QQMethodType.GET_APP_LIST);
		GetAppListResponse getAppListResponse=getAppListHander.invok(req,subBody);
		return parseGetAppListResponse(req,getAppListResponse);
    }
	private AppDisRecomReply  parseRecommendADListResp (AppDisRecomReq req,GetRecommendADListResponse resp) throws AppDisException{
		if(resp==null){
			return null;
		}
		GetRecommendADListRespBody respBody=resp.getBody();
		if(respBody.getRet()!=0){
			throw new AppDisException(AppDisErrorCode.RC_INTER_ERROR,"get rank app list search error,return error code="+respBody.getRet());
		}
		if(CollectionUtils.isEmpty(respBody.getAppList())){
			return null;
		}
		AppDisRecomReply reply=new AppDisRecomReply();
		reply.setErrorCode(com.ocean.app.dis.proxy.thrift.entity.ErrorCode.RC_SUCCESS);
		List<AppDisContent> appL=new ArrayList<AppDisContent>();
		for(ADAppInfo item:respBody.getAppList()){
			if(StringUtils.isEmpty(item.getPkgName())){
				item.setPkgName(item.getPackageName());//特殊处理
			}else{
				item.setPackageName(item.getPkgName());//特殊处理
			}
			
			AppDisContent appDisContent=new AppDisContent();
			appDisContent.setApkId(item.getApkId());
			appDisContent.setApkMd5(item.getApkMd5());
			appDisContent.setApkUrl(item.getApkUrl());
			appDisContent.setAppId(item.getAppId());
			appDisContent.setCategoryId(String.valueOf(item.getCategoryId()));
			appDisContent.setCategoryName(item.getCategoryName());
			appDisContent.setContent(item.getShortDesc());
			//appDisContent.setInstalledCount(item.getAppDownCount());
			if(StringUtils.isNotEmpty(item.getTotalDownloadTimes())){
				appDisContent.setInstalledCount(Integer.parseInt(item.getTotalDownloadTimes()));
			}
			
			appDisContent.setSignMd5(item.getSignatureMd5());
			
			
			AppInfo app=new AppInfo();
			app.setAppName(item.getAppName());
			app.setIconUrl(item.getIconUrl());
			app.setMinSdkVersion(String.valueOf(item.getMinSdkVersion()));
			///app.setPkgName(item.getPackageName());
			app.setPkgName(item.getPkgName());
			AppType appType=appTypeMap.get(item.getParentCategoryID());
			app.setType(appType==null?AppType.TYPE_APP:appType);
			app.setVersionCode(String.valueOf(item.getVersionCode()));
			app.setFileSize(item.getFileSize());
			app.setVersionName(item.getVersionName());
			appDisContent.setAppInfo(app);
			
			//设置上报用字段
            Map<String,List<String>> report=new HashMap<String,List<String>>();
            report.put(SHOW, pvReport(AppDisConstants.INTER_GETRECOMMEND_APPLIST,req,item));
            report.put(CLICK, clickReport(AppDisConstants.INTER_GETRECOMMEND_APPLIST,req,item));
            report.put(DOWNLOAD_BEGIN, beginDownloadReport(AppDisConstants.INTER_GETRECOMMEND_APPLIST,req,item));
            report.put(DOWNLOAD_END, endDownloadReport(AppDisConstants.INTER_GETRECOMMEND_APPLIST,req,item));
            report.put(INSTALL, installReport(AppDisConstants.INTER_GETRECOMMEND_APPLIST,req,item));
            appDisContent.setThirdReportParams(report);
            
            appDisContent.setReportType(ReportType.REPORT_POST);
			appL.add(appDisContent);
		}
		
		reply.setAppDisContent(appL);
		return reply;
	}
	private AppDisRecomReply paseRankAppListResp(AppDisRecomReq req,GetRankAppADListResponse resp) throws AppDisException{
		if(resp==null){
			return null;
		}
		GetRankAppADListRespBody respBody=resp.getBody();
		if(respBody.getRet()!=0){
			throw new AppDisException(AppDisErrorCode.RC_INTER_ERROR,"get rank app list search error,return error code="+respBody.getRet());
		}
		if(CollectionUtils.isEmpty(respBody.getAppList())){
			return null;
		}
		AppDisRecomReply reply=new AppDisRecomReply();
		reply.setErrorCode(com.ocean.app.dis.proxy.thrift.entity.ErrorCode.RC_SUCCESS);
		List<AppDisContent> appL=new ArrayList<AppDisContent>();
		for(BaseAppADListItem item:respBody.getAppList()){
			if(StringUtils.isEmpty(item.getPkgName())){
				item.setPkgName(item.getPackageName());//特殊处理
			}else{
				item.setPackageName(item.getPkgName());//特殊处理
			}
			
			AppDisContent appDisContent=new AppDisContent();
			appDisContent.setApkId(item.getApkId());
			appDisContent.setApkMd5(item.getApkMd5());
			appDisContent.setApkUrl(item.getApkUrl());
			appDisContent.setAppId(item.getAppId());
			appDisContent.setCategoryId(String.valueOf(item.getCategoryId()));
			appDisContent.setCategoryName(item.getCategoryName());
			appDisContent.setContent(item.getShortDesc());
			appDisContent.setInstalledCount(item.getAppDownCount());
			appDisContent.setSignMd5(item.getSignatureMd5());
			
			
			AppInfo app=new AppInfo();
			app.setAppName(item.getAppName());
			app.setIconUrl(item.getIconUrl());
			app.setMinSdkVersion(String.valueOf(item.getMinSdkVersion()));
			app.setPkgName(item.getPackageName());
			AppType appType=appTypeMap.get(item.getParentCategoryID());
			app.setType(appType==null?AppType.TYPE_APP:appType);
			app.setVersionCode(String.valueOf(item.getVersionCode()));
			app.setVersionName(item.getVersionName());
			app.setFileSize(item.getFileSize());
			appDisContent.setAppInfo(app);
			
			//设置上报用字段
            Map<String,List<String>> report=new HashMap<String,List<String>>();
            report.put(SHOW, pvReport(AppDisConstants.INTER_RANK_SEARCH,req,item));
            report.put(CLICK, clickReport(AppDisConstants.INTER_RANK_SEARCH,req,item));
            
            report.put(DOWNLOAD_BEGIN, beginDownloadReport(AppDisConstants.INTER_RANK_SEARCH,req,item));
            report.put(DOWNLOAD_END, endDownloadReport(AppDisConstants.INTER_RANK_SEARCH,req,item));
            report.put(INSTALL, installReport(AppDisConstants.INTER_RANK_SEARCH,req,item));
            appDisContent.setThirdReportParams(report);
            
            appDisContent.setReportType(ReportType.REPORT_POST);

            //上下文信息
			if(CollectionUtils.isNotEmpty(respBody.getPageContext())){
			    StringBuffer buffer=new StringBuffer();
				for(String str :respBody.getPageContext()){
					if(buffer.length()>0){
						buffer.append(",");
					}
					buffer.append(str);
				}
				
				ExtData ext=new ExtData ();
				ext.setKey(QQDownloaderRequest.CONTEXTDATA);
				ext.setValue(buffer.toString());
				appDisContent.setExt(Arrays.asList(ext));
			}
			appL.add(appDisContent);
		}
		
		reply.setAppDisContent(appL);
		return reply;
	}
	private AppDisRecomReply parseKeySearchResp(AppDisRecomReq req,KeyWordSearchResponse resp) throws AppDisException{

		if(resp==null){
			return null;
		}
		KeyWordSearchRespBody respBody=resp.getBody();
		if(respBody.getRet()!=0){
			throw new AppDisException(AppDisErrorCode.RC_INTER_ERROR,"keyword search error,return error code="+respBody.getRet());
		}
		if(CollectionUtils.isEmpty(respBody.getAppList())){
			return null;
		}
		AppDisRecomReply reply=new AppDisRecomReply();
		reply.setErrorCode(com.ocean.app.dis.proxy.thrift.entity.ErrorCode.RC_SUCCESS);
		List<AppDisContent> appL=new ArrayList<AppDisContent>();
		for(BaseAppADListItem item:respBody.getAppList()){
			if(StringUtils.isEmpty(item.getPkgName())){
				item.setPkgName(item.getPackageName());//特殊处理
			}else{
				item.setPackageName(item.getPkgName());//特殊处理
			}
			
			AppDisContent appDisContent=new AppDisContent();
			appDisContent.setApkId(item.getApkId());
			appDisContent.setApkMd5(item.getApkMd5());
			appDisContent.setApkUrl(item.getApkUrl());
			appDisContent.setAppId(item.getAppId());
			appDisContent.setCategoryId(String.valueOf(item.getCategoryId()));
			appDisContent.setCategoryName(item.getCategoryName());
			appDisContent.setContent(item.getShortDesc());
			appDisContent.setInstalledCount(item.getAppDownCount());
			appDisContent.setSignMd5(item.getSignatureMd5());
			
			
			AppInfo app=new AppInfo();
			app.setAppName(item.getAppName());
			app.setIconUrl(item.getIconUrl());
			app.setMinSdkVersion(String.valueOf(item.getMinSdkVersion()));
			app.setPkgName(item.getPackageName());

			AppType appType=appTypeMap.get(item.getParentCategoryID());
			app.setType(appType==null?AppType.TYPE_APP:appType);
			app.setVersionCode(String.valueOf(item.getVersionCode()));
			app.setVersionName(item.getVersionName());
			app.setFileSize(item.getFileSize());
			appDisContent.setAppInfo(app);
			
			//设置上报用字段
            Map<String,List<String>> report=new HashMap<String,List<String>>();
            report.put(SHOW, pvReport(AppDisConstants.INTER_KEYWORD_SEARCH,req,item));
            report.put(CLICK, clickReport(AppDisConstants.INTER_KEYWORD_SEARCH,req,item));
            
            report.put(DOWNLOAD_BEGIN, beginDownloadReport(AppDisConstants.INTER_KEYWORD_SEARCH,req,item));
            report.put(DOWNLOAD_END, endDownloadReport(AppDisConstants.INTER_KEYWORD_SEARCH,req,item));
            report.put(INSTALL, installReport(AppDisConstants.INTER_KEYWORD_SEARCH,req,item));
            appDisContent.setThirdReportParams(report);
            
            appDisContent.setReportType(ReportType.REPORT_POST);

            //上下文信息
			if(CollectionUtils.isNotEmpty(respBody.getContextData())){
			    StringBuffer buffer=new StringBuffer();
				for(String str :respBody.getContextData()){
					if(buffer.length()>0){
						buffer.append(",");
					}
					buffer.append(str);
				}
				
				ExtData ext=new ExtData ();
				ext.setKey(QQDownloaderRequest.CONTEXTDATA);
				ext.setValue(buffer.toString());
				appDisContent.setExt(Arrays.asList(ext));
			}
			appL.add(appDisContent);
		}
		
		reply.setAppDisContent(appL);
		return reply;
	}
	private AppDisRecomReply parseGetAppListResponse(AppDisRecomReq req,GetAppListResponse resp) throws AppDisException{

		if(resp==null){
			return null;
		}
		GetAppListPullRespBody appRB=resp.getBody();
		if(appRB.getRet()!=0){
			throw new AppDisException(AppDisErrorCode.RC_INTER_ERROR,"get getAppList error,return error code="+appRB.getRet());
		}
		if(CollectionUtils.isEmpty(appRB.getAppADList())){
			return null;
		}
		AppDisRecomReply reply=new AppDisRecomReply();
		reply.setErrorCode(com.ocean.app.dis.proxy.thrift.entity.ErrorCode.RC_SUCCESS);
		List<AppDisContent> appL=new ArrayList<AppDisContent>();
		for(BaseAppADListItem item:appRB.getAppADList()){
			AppDisContent appDisContent=new AppDisContent();
			appDisContent.setApkId(item.getApkId());
			appDisContent.setApkMd5(item.getApkMd5());
			appDisContent.setApkUrl(item.getApkUrl());
			appDisContent.setAppId(item.getAppId());
			appDisContent.setCategoryId(String.valueOf(item.getCategoryId()));
			appDisContent.setCategoryName(item.getCategoryName());
			appDisContent.setContent(item.getShortDesc());
			appDisContent.setInstalledCount(item.getAppDownCount());
			appDisContent.setSignMd5(item.getSignatureMd5());
			
			
			AppInfo app=new AppInfo();
			app.setAppName(item.getAppName());
			app.setIconUrl(item.getIconUrl());
			app.setMinSdkVersion(String.valueOf(item.getMinSdkVersion()));
			app.setPkgName(item.getPackageName());
			app.setType(appTypeMap.get(item.getParentCategoryID()));
			app.setVersionCode(String.valueOf(item.getVersionCode()));
			app.setVersionName(item.getVersionName());
			app.setFileSize(item.getFileSize());
			appDisContent.setAppInfo(app);
			
			//设置上报用字段
            Map<String,List<String>> report=new HashMap<String,List<String>>();
            report.put(SHOW, pvReport(AppDisConstants.INTER_GETAPPLIST,req,item));
            report.put(CLICK, clickReport(AppDisConstants.INTER_GETAPPLIST,req,item));
            
            report.put(DOWNLOAD_BEGIN, beginDownloadReport(AppDisConstants.INTER_GETAPPLIST,req,item));
            report.put(DOWNLOAD_END, endDownloadReport(AppDisConstants.INTER_GETAPPLIST,req,item));
            report.put(INSTALL, installReport(AppDisConstants.INTER_GETAPPLIST,req,item));
            appDisContent.setThirdReportParams(report);
            
            appDisContent.setReportType(ReportType.REPORT_POST);

            //上下文信息
			if(CollectionUtils.isNotEmpty(appRB.getContextData())){
			    StringBuffer buffer=new StringBuffer();
				for(String str :appRB.getContextData()){
					if(buffer.length()>0){
						buffer.append(",");
					}
					buffer.append(str);
				}
				
				ExtData ext=new ExtData ();
				ext.setKey(QQDownloaderRequest.CONTEXTDATA);
				ext.setValue(buffer.toString());
				appDisContent.setExt(Arrays.asList(ext));
			}
			appL.add(appDisContent);
		}
		
		reply.setAppDisContent(appL);
		return reply;
	}

	public   List<String> pvReport(String interTye,AppDisRecomReq req,AppDisResponse resp) throws AppDisException {
		// TODO Auto-generated method stub
		List<String> list=new ArrayList<String>();
		ReportBody report =new ReportBody();
		DeviceInfo device=req.getDevice();
		report=getBaseReportBody(interTye,device,resp);
		
		list.add(JsonUtils.toJson(report));
		return list;
	}
	public List<String> clickReport(String interTye,AppDisRecomReq req,AppDisResponse resp) throws AppDisException {
		// TODO Auto-generated method stub
		List<String> list=new ArrayList<String>();
		ReportBody report =new ReportBody();
		DeviceInfo device=req.getDevice();
		report=getBaseReportBody(interTye,device,resp);
		report.setClickType(900);
		list.add(JsonUtils.toJson(report));
		return list;
	}

	public List<String> beginDownloadReport(String interTye,AppDisRecomReq req,AppDisResponse resp)
			throws AppDisException {
		// TODO Auto-generated method stub
		List<String> list=new ArrayList<String>();
		ReportBody report =new ReportBody();
		DeviceInfo device=req.getDevice();
		report=getBaseReportBody(interTye,device,resp);
		list.add(JsonUtils.toJson(report));
		return list;
	}

	public List<String> endDownloadReport(String interTye,AppDisRecomReq req,AppDisResponse resp) throws AppDisException {
		// TODO Auto-generated method stub
		List<String> list=new ArrayList<String>();
		ReportBody report =new ReportBody();
		DeviceInfo device=req.getDevice();
		report=getBaseReportBody(interTye,device,resp);
		list.add(JsonUtils.toJson(report));
		return list;
	}

	public List<String> installReport(String interTye,AppDisRecomReq req,AppDisResponse resp) throws AppDisException {
		// TODO Auto-generated method stub
		List<String> list=new ArrayList<String>();
		ReportBody report =new ReportBody();
		DeviceInfo device=req.getDevice();
		report=getBaseReportBody(interTye,device,resp);
		list.add(JsonUtils.toJson(report));
		return list;
	}
    private ReportBody getBaseReportBody(String interName,DeviceInfo device,AppDisResponse resp){
		ReportBody report =new ReportBody();
		if(resp instanceof BaseAppADListItem){
			BaseAppADListItem item=(BaseAppADListItem)resp;
			report.setApkId(Long.parseLong(item.getApkId()));
			report.setAppId(Long.parseLong(item.getAppId()));
			report.setChannelId(item.getChannelId());
			report.setDataAnalysisId(item.getDataAnalysisId());
			report.setOperateTime((int)(System.currentTimeMillis()/1000));//单位秒
			report.setPackageName(item.getPkgName());
			report.setRecommendId(item.getRecommendId());
			report.setSource(item.getSource());
			
			report.setHostVersionCode(item.getHostVersionCode());//数字版本号
			report.setImei(device.getImei());
			report.setImsi(device.getImsi());
			report.setInterfaceName(item.getInterfaceName());
			report.setMacAddr(device.getMac());
			report.setVersionCode(item.getVersionCode());
			
		}
		return report;
    }


}
