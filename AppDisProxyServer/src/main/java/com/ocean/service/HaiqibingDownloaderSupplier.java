package com.ocean.service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import com.ocean.app.dis.proxy.thrift.entity.AppDisContent;
import com.ocean.app.dis.proxy.thrift.entity.AppDisRecomReply;
import com.ocean.app.dis.proxy.thrift.entity.AppDisRecomReq;
import com.ocean.app.dis.proxy.thrift.entity.AppInfo;
import com.ocean.app.dis.proxy.thrift.entity.AppType;
import com.ocean.app.dis.proxy.thrift.entity.ReportType;
import com.ocean.core.common.JsonUtils;
import com.ocean.core.common.system.ErrorCode;
import com.ocean.handler.base.AbstractAppDisSupplier;
import com.ocean.persist.app.dis.AppDisException;
import com.ocean.persist.app.dis.AppDisResponse;
import com.ocean.persist.app.dis.haiqibing.HaiqibingReportData;
import com.ocean.persist.app.dis.haiqibing.pkgSearch.HaiqibingPkgSchApp;
import com.ocean.persist.app.dis.haiqibing.pkgSearch.HaiqibingPkgSchReq;
import com.ocean.persist.app.dis.haiqibing.pkgSearch.HaiqibingPkgSchResp;
import com.ocean.service.haiqibingInvokeHandler.HaiqibingPkgSearchHandler;
import com.ocean.service.haiqibingInvokeHandler.base.HaiqibingInvokeHandlerFactory;
import com.ocean.service.haiqibingInvokeHandler.base.HaiqibingMethodType;
@Component(value="HaiqibingDownloaderSupplier")
public class HaiqibingDownloaderSupplier   extends AbstractAppDisSupplier{
	public final static String HQB_REPORT_TYPE_PV="exposure";
	public final static String HQB_REPORT_TYPE_CLK="click";
	public final static String HQB_REPORT_TYPE_DOWNLOAD="download";
	public final static String HQB_REPORT_TYPE_INSTALL="install";
	public AppDisRecomReply invoke(AppDisRecomReq params)
			throws AppDisException {
		// TODO Auto-generated method stub
		switch(params.getInterType()){
				case PKG_SEARCH:
					
					return appPkgSearch(params);
		
				default:
				    throw new AppDisException(ErrorCode.PARAM_ERROR,"can not find the mapping interface:"+params.getInterType().name());
		        
		}
	}
	private AppDisRecomReply appPkgSearch(AppDisRecomReq req) throws AppDisException{
		HaiqibingPkgSearchHandler handler=(HaiqibingPkgSearchHandler)HaiqibingInvokeHandlerFactory.getHandler(HaiqibingMethodType.PACKAGE_SEARCH);
		String hostVc=req.getAppInfo().getVersionCode();
		if(StringUtils.isEmpty(hostVc)){
			hostVc="0";
		}
		return parseAppPkgSearchResp(req,handler,Integer.parseInt(hostVc));

	}
	private AppDisRecomReply  parseAppPkgSearchResp(AppDisRecomReq req,HaiqibingPkgSearchHandler handler,int  hostVc) throws AppDisException{

		
		HaiqibingPkgSchReq request=handler.parseListSearchParam(req);
		HaiqibingPkgSchResp reply=(HaiqibingPkgSchResp)handler.invok(req,request);
		
		if(reply==null||reply.getData()==null||StringUtils.isEmpty(reply.getData().getApkUrl())){
			return null;
		}
		if(reply.getCode()!=200){
			throw new AppDisException(ErrorCode.INTER_ERROR,"return error code:"+reply.getCode()+" msg:"+reply.getMsg());
		}

		//返回值封装
		AppDisRecomReply proxyReply=new AppDisRecomReply();
		proxyReply.setErrorCode(com.ocean.app.dis.proxy.thrift.entity.ErrorCode.RC_SUCCESS);
		List<AppDisContent> appL=new ArrayList<AppDisContent>();
		HaiqibingPkgSchApp item=reply.getData();

		AppDisContent appDisContent=new AppDisContent();
		appDisContent.setApkId(String.valueOf(item.getApkId()));
		appDisContent.setApkMd5(item.getApkMd5());
		appDisContent.setApkUrl(item.getApkUrl());
		appDisContent.setAppId(String.valueOf(item.getAppId()));
		appDisContent.setCategoryId(String.valueOf(item.getCategoryId()));
		appDisContent.setCategoryName(item.getCategoryName());
		appDisContent.setContent(item.getShortDesc());
		//appDisContent.setInstalledCount(item.getAppDownCount());
/*			if(StringUtils.isNotEmpty(item.getTotalDownloadTimes())){
			appDisContent.setInstalledCount(Integer.parseInt(item.getTotalDownloadTimes()));
		}*/
		
		//appDisContent.setSignMd5(item.getSequence());
		
		appDisContent.setIsAd(1);
		AppInfo app=new AppInfo();
		app.setAppName(item.getAppName());	
	
		app.setIconUrl(item.getIconUrl());
		app.setMinSdkVersion(String.valueOf(item.getMinSdkVersion()));
		app.setPkgName(item.getPkgName());
		
		app.setType(AppType.TYPE_APP);
		app.setVersionCode(String.valueOf(item.getVersionCode()));
		app.setFileSize(String.valueOf(item.getFileSize()));
		app.setVersionName(item.getVersionName());
		appDisContent.setAppInfo(app);
		//设置上报用字段
        Map<String,List<String>> report=new HashMap<String,List<String>>();
        report.put(SHOW, wrapReportData(HQB_REPORT_TYPE_PV,request,item,hostVc));
        report.put(DOWNLOAD_BEGIN, wrapReportData(HQB_REPORT_TYPE_CLK,request,item,hostVc));
        report.put(DOWNLOAD_END, wrapReportData(HQB_REPORT_TYPE_DOWNLOAD,request,item,hostVc));
        report.put(INSTALL, wrapReportData(HQB_REPORT_TYPE_INSTALL,request,item,hostVc));
        appDisContent.setThirdReportParams(report);
        appDisContent.setReportType(ReportType.REPORT_POST);
		appL.add(appDisContent);
		
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
	public List<String> wrapReportData(String type,HaiqibingPkgSchReq param,
			HaiqibingPkgSchApp app,int hostVc) throws AppDisException {
		List<String> list=new ArrayList<String>();
		HaiqibingReportData reportData=new HaiqibingReportData();
		reportData.setAction(type);
		reportData.setImei(param.getImei());
		reportData.setAndroidId(param.getAndroidId());
		reportData.setMacAddress(param.getMacAddress());
		reportData.setManufacture(param.getManufacture());
		reportData.setMode(param.getMode());
		reportData.setAppId(app.getAppId());
		reportData.setApkId(app.getApkId());
		reportData.setPackageName(app.getPkgName());
		reportData.setVersionCode(app.getVersionCode());
		reportData.setRecommendId(app.getRecommendId());
		reportData.setSource(app.getSource());
		reportData.setChannelId(app.getChannelId());
		reportData.setApkUrl(app.getApkUrl());
		reportData.setDataAnalysisId(app.getDataAnalysisId());
		reportData.setHostVersionCode(hostVc);
		reportData.setChannel(param.getChannel());
		reportData.setIp(param.getIp());
		reportData.setUserAgent(param.getUserAgent());
		list.add(JsonUtils.toJson(reportData));
	    return list;
	}


}
