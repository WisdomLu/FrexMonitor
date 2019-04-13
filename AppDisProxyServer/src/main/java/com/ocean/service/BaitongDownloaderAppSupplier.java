package com.ocean.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

import com.inveno.util.CollectionUtils;
import com.ocean.app.dis.proxy.thrift.entity.AppDisContent;
import com.ocean.app.dis.proxy.thrift.entity.AppDisRecomReply;
import com.ocean.app.dis.proxy.thrift.entity.AppDisRecomReq;
import com.ocean.app.dis.proxy.thrift.entity.AppInfo;
import com.ocean.app.dis.proxy.thrift.entity.AppType;
import com.ocean.app.dis.proxy.thrift.entity.ReportType;
import com.ocean.core.common.http.Bean2Utils;
import com.ocean.core.common.system.ErrorCode;
import com.ocean.core.common.system.SystemContext;
import com.ocean.handler.base.AbstractAppDisSupplier;
import com.ocean.persist.app.dis.AppDisException;
import com.ocean.persist.app.dis.AppDisResponse;
import com.ocean.persist.app.dis.baidubt.pkgsearch.BaitongApp;
import com.ocean.persist.app.dis.baidubt.pkgsearch.BaitongPkgSearchReq;
import com.ocean.persist.app.dis.baidubt.pkgsearch.BaitongPkgSearchResp;
import com.ocean.persist.app.dis.baidubt.pkgsearch.BaitongPkgSearchSecParams;
import com.ocean.persist.common.AppDisConstants;
import com.ocean.service.baitongInvokerHandler.BaitongPkgSearchHandler;
import com.ocean.service.baitongInvokerHandler.base.BaitongInvokeHandlerFactory;
import com.ocean.service.baitongInvokerHandler.base.BaitongMethodType;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年5月12日 
      @version 1.0 
 */
@Component(value="BaitongDownloaderAppSupplier")
public class BaitongDownloaderAppSupplier extends AbstractAppDisSupplier{
	private static final int REPORT_TYPE_CLICK=0;
	private static final int REPORT_TYPE_DOWNLOAD=1;
	private static final int REPORT_TYPE_INSTALL=2;
	
	private static Map<String,AppType> appTypeMap=new HashMap<String,AppType>();
	{
		appTypeMap.put("soft", AppType.TYPE_APP);
		appTypeMap.put("game", AppType.TYPE_GAME);
	}

	public AppDisRecomReply invoke(AppDisRecomReq req)
			throws AppDisException {
		// TODO Auto-generated method stub
		switch(req.getInterType()){
			case PKG_SEARCH:
				return pkgSearch(req);
			default:
			    throw new AppDisException(ErrorCode.PARAM_ERROR,"can not find the mapping interface:"+req.getInterType().name());
		}

	}
	private AppDisRecomReply pkgSearch(AppDisRecomReq req) throws AppDisException{
		BaitongPkgSearchHandler pkgSearchHandler=(BaitongPkgSearchHandler)BaitongInvokeHandlerFactory.getHandler(BaitongMethodType.PKG_SEARCH);
		BaitongPkgSearchResp resp=(BaitongPkgSearchResp)pkgSearchHandler.invok(req);
		
		return parseResp(req,resp,pkgSearchHandler);
	}

	private AppDisRecomReply  parseResp (AppDisRecomReq req,BaitongPkgSearchResp resp,BaitongPkgSearchHandler pkgSearchHandler) throws AppDisException{
		if(resp==null||CollectionUtils.isEmpty(resp.getList())){
			throw new AppDisException(ErrorCode.INTER_ERROR,"DATA_EMPTY return error code:"+resp.getFlag());
			
		}
		if(resp.getFlag()!=1){
			throw new AppDisException(ErrorCode.INTER_ERROR,"DATA_ERROR return error code:"+resp.getFlag());
		}
		AppDisRecomReply reply=new AppDisRecomReply();
		reply.setErrorCode(com.ocean.app.dis.proxy.thrift.entity.ErrorCode.RC_SUCCESS);
		List<AppDisContent> appL=new ArrayList<AppDisContent>();
		
	  	String reportUrl=SystemContext.getDynamicPropertyHandler().get(AppDisConstants.BAITOGN_PKG_REPORT_URL);
		for(BaitongApp item:resp.getList()){
			AppDisContent appDisContent=new AppDisContent();
			
			appDisContent.setApkUrl(formatUrl(REPORT_TYPE_CLICK,item.getApp_name(),item.getDownload_url(),item.getApp_key(),resp.getLog_id(), req,pkgSearchHandler));
			
			appDisContent.setCategoryId(item.getCategory());
		
			//appDisContent.setContent(item.getShortDesc());
	
			//默认设置为是广告
			appDisContent.setIsAd(1);
			appDisContent.setInstalledCount(item.getDownnum());


			
			AppInfo app=new AppInfo();
			app.setAppName(item.getApp_name());
			app.setIconUrl(item.getApp_icon());
			app.setPkgName(item.getApp_package());
			app.setType(AppType.TYPE_APP);
			app.setVersionCode(String.valueOf(item.getVersion_code()));
			app.setFileSize(item.getApp_file_size());
			app.setVersionName(item.getVersion_name());
			appDisContent.setAppInfo(app);

			//设置上报用字段
            Map<String,List<String>> report=new HashMap<String,List<String>>();
            //report.put(CLICK, Collections.singletonList(formatUrl(REPORT_TYPE_CLICK,reportUrl,item.getApp_key(),resp.getLog_id(),req,pkgSearchHandler)));
            report.put(DOWNLOAD_END, Collections.singletonList(formatUrl(REPORT_TYPE_DOWNLOAD,item.getApp_name(),item.getDownload_url(),item.getApp_key(),resp.getLog_id(),req,pkgSearchHandler)));
            report.put(INSTALL,Collections.singletonList(formatUrl(REPORT_TYPE_INSTALL,item.getApp_name(),item.getDownload_url(),item.getApp_key(),resp.getLog_id(),req,pkgSearchHandler)));
            appDisContent.setThirdReportLinks(report);
            
            appDisContent.setReportType(ReportType.REPORT_GET);
			appL.add(appDisContent);
		}
		
		reply.setAppDisContent(appL);
		return reply;
	}
	private String formatUrl(int reportType,String appName,String url,String appKey,String logId,AppDisRecomReq req,BaitongPkgSearchHandler pkgSearchHandler) throws AppDisException{
		StringBuilder sb=new StringBuilder(url);
		BaitongPkgSearchReq baitReq=pkgSearchHandler.parsePkgParam(req);
		BaitongPkgSearchSecParams sec=pkgSearchHandler.getSecParams(req.getAppInfo(),req.getUserInfo(), req.getDevice());
		sec.setAction_type(2);
		sec.setAction("download_list");
		sec.setDownload_app_name(appName);
    	String secParamStr=Bean2Utils.toHttpParams(sec);
		try {
			String secStr = URLEncoder.encode(Base64.encodeBase64String(secParamStr.getBytes(CHARSET_CODER)) ,CHARSET_CODER);
			baitReq.setParams(secStr);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		//去掉不用的
		baitReq.setAd_type(null);
		baitReq.setPlatform(null);
		baitReq.setClienttype(null);
		baitReq.setSub_channel(null);
		baitReq.setPackage_name(null);
		baitReq.setPage(null);
		baitReq.setPage_size(null);
		
		baitReq.setApp_key(appKey);
		baitReq.setSecret(DigestUtils.md5Hex(req.getJoinParam().get(1).getValue()+appKey));
		
		
		if(REPORT_TYPE_CLICK==reportType){
			baitReq.setFrom("lc");
		}
		baitReq.setDown_complete(reportType);
		baitReq.setLog_id(logId);
		
		String sufix=url.indexOf("?")<0?"?":"&";
		sb.append(sufix).append(Bean2Utils.toHttpParams(baitReq));
		
		return sb.toString();
	}
	public   List<String> pvReport(String interTye,AppDisRecomReq req,AppDisResponse resp) throws AppDisException {
		// TODO Auto-generated method stub
		return null;
	}
	public List<String> clickReport(String interTye,AppDisRecomReq req,AppDisResponse resp) throws AppDisException {
		// TODO Auto-generated method stub

		return null;
	}

	public List<String> beginDownloadReport(String interTye,AppDisRecomReq req,AppDisResponse resp)
			throws AppDisException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<String> endDownloadReport(String interTye,AppDisRecomReq req,AppDisResponse resp) throws AppDisException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<String> installReport(String interTye,AppDisRecomReq req,AppDisResponse resp) throws AppDisException {
		// TODO Auto-generated method stub
		return null;
	}
	public List<String> activeReport(String interTye,AppDisRecomReq req,AppDisResponse resp) throws AppDisException {
		// TODO Auto-generated method stub
		return null;
	}

}
