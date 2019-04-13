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
import com.ocean.service.fuxi.FuxiGetRecommendADListHandler;
import com.ocean.service.fuxi.base.FuxiQQInvokHandlerFactory;
import com.ocean.service.fuxi.base.FuxiQQMethodType;
import com.ocean.service.qqInvokHandler.GetAppListHandler;
import com.ocean.service.qqInvokHandler.GetCategoryListHandler;
import com.ocean.service.qqInvokHandler.GetRankAppADListHandler;
import com.ocean.service.qqInvokHandler.GetSubjectListHandler;
import com.ocean.service.qqInvokHandler.KeyWordSearchHandler;
import com.ocean.service.qqInvokHandler.base.QQInvokHandlerFactory;
import com.ocean.service.qqInvokHandler.base.QQMethodType;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年5月12日 
      @version 1.0 
 */
@Component(value="FuxiQQDownloaderAppSupplier")
public class FuxiQQDownloaderAppSupplier extends AbstractAppDisSupplier{
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
				return pkgSearch(req);
			default:
			    throw new AppDisException(ErrorCode.PARAM_ERROR,"can not find the mapping interface:"+req.getInterType().name());
		}

	}
	private AppDisRecomReply pkgSearch(AppDisRecomReq req) throws AppDisException{
		FuxiGetRecommendADListHandler keyWordSearchHandler=(FuxiGetRecommendADListHandler)FuxiQQInvokHandlerFactory.getHandler(FuxiQQMethodType.GET_RECOMMEND_APP_LIST);
		GetRecommendADListResponse resp=(GetRecommendADListResponse)keyWordSearchHandler.invok(req);
		
		return parseRecommendADListResp(req,resp);
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
			//默认设置为是广告
			appDisContent.setIsAd(1);
			
			
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
	
	public   List<String> pvReport(String interTye,AppDisRecomReq req,AppDisResponse resp) throws AppDisException {
		// TODO Auto-generated method stub
		List<String> list=new ArrayList<String>();
		ReportBody report =new ReportBody();
		DeviceInfo device=req.getDevice();
		report=getBaseReportBody(device,resp);
		
		list.add(JsonUtils.toJson(report));
		return list;
	}
	public List<String> clickReport(String interTye,AppDisRecomReq req,AppDisResponse resp) throws AppDisException {
		// TODO Auto-generated method stub
		List<String> list=new ArrayList<String>();
		ReportBody report =new ReportBody();
		DeviceInfo device=req.getDevice();
		report=getBaseReportBody(device,resp);
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
		report=getBaseReportBody(device,resp);
		list.add(JsonUtils.toJson(report));
		return list;
	}

	public List<String> endDownloadReport(String interTye,AppDisRecomReq req,AppDisResponse resp) throws AppDisException {
		// TODO Auto-generated method stub
		List<String> list=new ArrayList<String>();
		ReportBody report =new ReportBody();
		DeviceInfo device=req.getDevice();
		report=getBaseReportBody(device,resp);
		list.add(JsonUtils.toJson(report));
		return list;
	}

	public List<String> installReport(String interTye,AppDisRecomReq req,AppDisResponse resp) throws AppDisException {
		// TODO Auto-generated method stub
		List<String> list=new ArrayList<String>();
		ReportBody report =new ReportBody();
		DeviceInfo device=req.getDevice();
		report=getBaseReportBody(device,resp);
		list.add(JsonUtils.toJson(report));
		return list;
	}
    private ReportBody getBaseReportBody(DeviceInfo device,AppDisResponse resp){
		ReportBody report =new ReportBody();
		if(resp instanceof BaseAppADListItem){
			BaseAppADListItem item=(BaseAppADListItem)resp;
			report.setApkId(Long.parseLong(item.getApkId()));
			report.setAppId(Long.parseLong(item.getAppId()));
			report.setChannelId(item.getChannelId());
			report.setDataAnalysisId(StringUtils.isEmpty(item.getDataAnalysisId())?"":item.getDataAnalysisId());
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
