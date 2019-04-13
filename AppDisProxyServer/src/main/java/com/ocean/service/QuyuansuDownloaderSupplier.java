package com.ocean.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.inveno.util.CollectionUtils;
import com.ocean.app.dis.proxy.thrift.entity.AppDisContent;
import com.ocean.app.dis.proxy.thrift.entity.AppDisRecomReply;
import com.ocean.app.dis.proxy.thrift.entity.AppDisRecomReq;
import com.ocean.app.dis.proxy.thrift.entity.AppInfo;
import com.ocean.app.dis.proxy.thrift.entity.AppType;
import com.ocean.app.dis.proxy.thrift.entity.InterType;
import com.ocean.app.dis.proxy.thrift.entity.ReportType;
import com.ocean.core.common.JsonUtils;
import com.ocean.core.common.system.ErrorCode;
import com.ocean.handler.base.AbstractAppDisSupplier;
import com.ocean.persist.app.dis.AppDisException;
import com.ocean.persist.app.dis.AppDisResponse;
import com.ocean.persist.app.dis.quyuansu.BaseQuyuansuReq;
import com.ocean.persist.app.dis.quyuansu.BaseQuyuansuResp;
import com.ocean.persist.app.dis.quyuansu.QuyuansuApp;
import com.ocean.persist.app.dis.quyuansu.QuyuansuReportData;
import com.ocean.persist.app.dis.quyuansu.hotwordSearch.QuyuansuHotwdSchReq;
import com.ocean.persist.app.dis.quyuansu.hotwordSearch.QuyuansuHotwdSchResp;
import com.ocean.persist.app.dis.quyuansu.keywordSearch.QuyuansuKwdSchReq;
import com.ocean.persist.app.dis.quyuansu.keywordSearch.QuyuansuKwdSchResp;
import com.ocean.persist.app.dis.quyuansu.listSearch.QuyuansuListSchReq;
import com.ocean.persist.app.dis.quyuansu.listSearch.QuyuansuListSchResp;
import com.ocean.persist.app.dis.quyuansu.pkgSearch.PkgSearchQuyuansuReq;
import com.ocean.persist.app.dis.quyuansu.pkgSearch.PkgSearchQuyuansuResp;
import com.ocean.persist.app.dis.quyuansu.rcmdSearch.RcmdSearchQuyuansuReq;
import com.ocean.persist.app.dis.quyuansu.rcmdSearch.RcmdSearchQuyuansuResp;
import com.ocean.service.quyuansuInvokHandler.QuyuansuHotwdSearchHandler;
import com.ocean.service.quyuansuInvokHandler.QuyuansuKwdSearchHandler;
import com.ocean.service.quyuansuInvokHandler.QuyuansuPkgSearchHandler;
import com.ocean.service.quyuansuInvokHandler.QuyuansuListSearchHandler;
import com.ocean.service.quyuansuInvokHandler.QuyuansuRcmdSearchHandler;
import com.ocean.service.quyuansuInvokHandler.base.QuyuansuInvokeHandlerFactory;
import com.ocean.service.quyuansuInvokHandler.base.QuyuansuMethodType;
@Component(value="QuyuansuDownloaderSupplier")
public class QuyuansuDownloaderSupplier   extends AbstractAppDisSupplier{
	 public static final int QYS_REPORT_TYPE_PV = 1;
	 public static final int QYS_REPORT_TYPE_CLICK = 2;
	 public static final int QYS_REPORT_TYPE_DOWNLOAD_BEGIN = 3;
	 public static final int QYS_REPORT_TYPE_DOWNLOAD_END = 4;
	 public static final int QYS_REPORT_TYPE_INSTALL_BEGIN = 6;
	 public static final int QYS_REPORT_TYPE_INSTALL_END = 5;
	 public static final int QYS_REPORT_TYPE_ACTIVE = 7;
	public AppDisRecomReply invoke(AppDisRecomReq params)
			throws AppDisException {
		// TODO Auto-generated method stub
		String token=params.getJoinParam().get(1).getValue();
		switch(params.getInterType()){
				case PKG_SEARCH:
					return pkgSearch(params,token);
				case KEY_WORD_SEARCH:
					return kwdSearch(params,token);
				case APP_LIST_SEARCH:
					return listSearch(params,token);
				case HOT_WORD_SEARCH:
					return hotwdSearch(params,token);
				case APP_RECOMMEND_SEARCH:
					return rcmdSearch(params,token);
				default:
				    throw new AppDisException(ErrorCode.PARAM_ERROR,"can not find the mapping interface:"+params.getInterType().name());
		        
		}
	}
	private AppDisRecomReply pkgSearch(AppDisRecomReq req,String token) throws AppDisException{
		QuyuansuPkgSearchHandler handler=(QuyuansuPkgSearchHandler)QuyuansuInvokeHandlerFactory.getHandler(QuyuansuMethodType.PACKAGE_SEARCH);
		
		return parsePkgSearchResp(req,handler,token);

	}
	private AppDisRecomReply rcmdSearch(AppDisRecomReq req,String token) throws AppDisException{
		QuyuansuRcmdSearchHandler handler=(QuyuansuRcmdSearchHandler)QuyuansuInvokeHandlerFactory.getHandler(QuyuansuMethodType.APP_RECOMMEND_SEARCH);
		
		return parseRcmdSearchResp(req,handler,token);

	}
	
	private AppDisRecomReply kwdSearch(AppDisRecomReq req,String token) throws AppDisException{
		QuyuansuKwdSearchHandler handler=(QuyuansuKwdSearchHandler)QuyuansuInvokeHandlerFactory.getHandler(QuyuansuMethodType.KEYWORD_SEARCH);
		
		return parseKeywordSearchResp(req,handler,token);

	}
	private AppDisRecomReply hotwdSearch(AppDisRecomReq req,String token) throws AppDisException{
		QuyuansuHotwdSearchHandler handler=(QuyuansuHotwdSearchHandler)QuyuansuInvokeHandlerFactory.getHandler(QuyuansuMethodType.HOTWORD_SEARCH);
		
		return parseHotwordSearchResp(req,handler,token);

	}
	private AppDisRecomReply listSearch(AppDisRecomReq req,String token) throws AppDisException{
		QuyuansuListSearchHandler handler=(QuyuansuListSearchHandler)QuyuansuInvokeHandlerFactory.getHandler(QuyuansuMethodType.APP_LIST_SEARCH);
		
		return parseListSearchResp(req,handler,token);

	}
	private AppDisRecomReply  parseListSearchResp(AppDisRecomReq req,QuyuansuListSearchHandler handler,String token) throws AppDisException{
		QuyuansuListSchReq request=handler.parseListSearchParam(req);
		QuyuansuListSchResp reply=(QuyuansuListSchResp)handler.invok(req,request);
		
		if(reply==null||CollectionUtils.isEmpty(reply.getData())){
			return null;
		}
		if(reply.getCode()!=0){
			throw new AppDisException(ErrorCode.INTER_ERROR,"return error code:"+reply.getCode()+" msg:"+reply.getMsg());
		}
		return  parseCommonResp( InterType.APP_LIST_SEARCH,reply, request,token);
	}
	private AppDisRecomReply  parsePkgSearchResp(AppDisRecomReq req,QuyuansuPkgSearchHandler handler,String token) throws AppDisException{
		PkgSearchQuyuansuReq request=handler.parsePkgSearchParam(req);
		PkgSearchQuyuansuResp reply=(PkgSearchQuyuansuResp)handler.invok(req,request);
		
		if(reply==null||CollectionUtils.isEmpty(reply.getData())){
			return null;
		}
		if(reply.getCode()!=0){
			throw new AppDisException(ErrorCode.INTER_ERROR,"return error code:"+reply.getCode()+" msg:"+reply.getMsg());
		}
		
		return  parseCommonResp( InterType.PKG_SEARCH,reply, request,token);
		
	}
	private AppDisRecomReply  parseRcmdSearchResp(AppDisRecomReq req,QuyuansuRcmdSearchHandler handler,String token) throws AppDisException{
		RcmdSearchQuyuansuReq request=handler.parseRcmdSearchParam(req);
		RcmdSearchQuyuansuResp reply=(RcmdSearchQuyuansuResp)handler.invok(req,request);
		
		if(reply==null||CollectionUtils.isEmpty(reply.getData())){
			return null;
		}
		if(reply.getCode()!=0){
			throw new AppDisException(ErrorCode.INTER_ERROR,"return error code:"+reply.getCode()+" msg:"+reply.getMsg());
		}
		
		return  parseCommonResp( InterType.APP_RECOMMEND_SEARCH,reply, request,token);
		
	}
	private AppDisRecomReply  parseHotwordSearchResp(AppDisRecomReq req,QuyuansuHotwdSearchHandler handler,String token) throws AppDisException{
		QuyuansuHotwdSchReq request=handler.parseHotwordSearchParam(req);
		QuyuansuHotwdSchResp reply=(QuyuansuHotwdSchResp)handler.invok(req,request);
		
		if(reply==null||CollectionUtils.isEmpty(reply.getData())){
			return null;
		}
		if(reply.getCode()!=0){
			throw new AppDisException(ErrorCode.INTER_ERROR,"return error code:"+reply.getCode()+" msg:"+reply.getMsg());
		}
		return  parseCommonResp(InterType.HOT_WORD_SEARCH, reply, request,token);
	}
	private AppDisRecomReply  parseKeywordSearchResp(AppDisRecomReq req,QuyuansuKwdSearchHandler handler,String token) throws AppDisException{
		QuyuansuKwdSchReq request=handler.parseKeywordSearchParam(req);
		QuyuansuKwdSchResp reply=(QuyuansuKwdSchResp)handler.invok(req,request);
		
		if(reply==null||CollectionUtils.isEmpty(reply.getData())){
			return null;
		}
		if(reply.getCode()!=0){
			throw new AppDisException(ErrorCode.INTER_ERROR,"return error code:"+reply.getCode()+" msg:"+reply.getMsg());
		}
		return  parseCommonResp(InterType.KEY_WORD_SEARCH, reply, request,token);
	}
	private AppDisRecomReply parseCommonResp(InterType interType ,BaseQuyuansuResp reply,BaseQuyuansuReq request,String token) throws AppDisException{
		//返回值封装
		AppDisRecomReply proxyReply=new AppDisRecomReply();
		proxyReply.setErrorCode(com.ocean.app.dis.proxy.thrift.entity.ErrorCode.RC_SUCCESS);
		//String reportUrl=SystemContext.getDynamicPropertyHandler().get(AppDisConstants.QUYUANSU_REPORT_URL);
		List<AppDisContent> appL=new ArrayList<AppDisContent>();
		for(QuyuansuApp item:reply.getData()){
			AppDisContent appDisContent=new AppDisContent();
			appDisContent.setApkId(item.getApkId());
			appDisContent.setApkMd5(item.getMd5());
			appDisContent.setApkUrl(item.getDownloadUrl());
			appDisContent.setAppId(item.getAppId());
	
			appDisContent.setSignMd5(item.getSequence());
			//设置上报用字段
            Map<String,List<String>> report=new HashMap<String,List<String>>();
/*			if(interType==InterType.KEY_WORD_SEARCH){
				if(item.getIs_ad()==1){
					appDisContent.setIsAd(1);
				}else{
					appDisContent.setIsAd(2);
				}
			}else{	
			   appDisContent.setIsAd(1);

			}*/
			if((interType==InterType.KEY_WORD_SEARCH&&item.getIs_ad()!=1)||
					(interType==InterType.HOT_WORD_SEARCH&&StringUtils.isEmpty(item.getSequence()))){//tag搜索接口没有sequence就不是广告
				appDisContent.setIsAd(2);
				//do Nothing
			}else{
				appDisContent.setIsAd(1);
				
	            report.put(SHOW, wrapCommonReportData(QYS_REPORT_TYPE_PV,request,item,token));
	            List<String> downL=new ArrayList<String>();
	            downL.addAll(wrapCommonReportData(QYS_REPORT_TYPE_CLICK,request,item,token));//这也是一个坑，应用分发如果点击上报放在CLICK中，客户端是不会上报的，客户端是把下载看做点击
	            downL.addAll(wrapCommonReportData(QYS_REPORT_TYPE_DOWNLOAD_BEGIN,request,item,token));
	            report.put(DOWNLOAD_BEGIN, downL);
	            
	            
	            report.put(DOWNLOAD_END, wrapCommonReportData(QYS_REPORT_TYPE_DOWNLOAD_END,request,item,token));
	            List<String> installL=new ArrayList<String>();
	            installL.addAll(wrapCommonReportData(QYS_REPORT_TYPE_INSTALL_BEGIN,request,item,token));
	            installL.addAll(wrapCommonReportData(QYS_REPORT_TYPE_INSTALL_END,request,item,token));
	            report.put(INSTALL,installL);
	            
	            report.put(ACTIVE, wrapCommonReportData(QYS_REPORT_TYPE_ACTIVE,request,item,token));
            }
			AppInfo app=new AppInfo();
			app.setAppName(item.getTitle());
			if(StringUtils.isNotEmpty(item.getIcons())){
				String icons[]=item.getIcons().split(";");
				app.setIconUrl(icons[0]);
			}
			
			app.setPkgName(item.getPackageName());
			app.setType(AppType.TYPE_APP);
			if("game".equals(item.getTitle())){
				app.setType(AppType.TYPE_GAME);
			}
			
			
			app.setVersionCode(item.getVersionCode());
			app.setFileSize(item.getBytes());
			app.setVersionName(item.getVersionName());
			appDisContent.setAppInfo(app);
           
/*            appDisContent.setThirdReportLinks(report);
            appDisContent.setReportType(ReportType.REPORT_GET);*/
            appDisContent.setThirdReportParams(report);
            appDisContent.setReportType(ReportType.REPORT_POST);
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
	//时间戳的时效性修改
	public List<String> wrapCommonReportData(int type, BaseQuyuansuReq param,
			QuyuansuApp app,String token) throws AppDisException {
		QuyuansuReportData data=new QuyuansuReportData() ;

		data.setId(param.getId());
		data.setToken(token);
		//data.setApiToken(param.getApiToken());
		//data.setTimestamp(System.currentTimeMillis());
		data.setPackageName(app.getPackageName());
		data.setType(type);
		data.setChannel(app.getChannel());
		data.setSequence(app.getSequence());
		if(type==QYS_REPORT_TYPE_CLICK){
			data.setX(MACRO_DOWN_X);
			data.setY(MACRO_DOWN_Y);
		}
		if(type==QYS_REPORT_TYPE_PV||type==QYS_REPORT_TYPE_CLICK
				||type==QYS_REPORT_TYPE_DOWNLOAD_END
				||type==QYS_REPORT_TYPE_INSTALL_END
				||type==QYS_REPORT_TYPE_ACTIVE){
			data.setStart(MACRO_EVENT_TIME_START);
			data.setEnd(MACRO_EVENT_TIME_END);
		}

		List<String> list=new ArrayList<String> ();
		list.add(JsonUtils.toJson(data));
		return list;
	}
	/*public List<String> wrapCommonReportData(int type, BaseQuyuansuReq param,
			QuyuansuApp app,String reportUrl) throws AppDisException {
		QuyuansuReportData data=new QuyuansuReportData() ;

		data.setId(param.getId());
		data.setApiToken(param.getApiToken());
		data.setTimestamp(param.getTimestamp());
		data.setPackageName(app.getPackageName());
		data.setType(type);
		data.setChannel(app.getChannel());
		data.setSequence(app.getSequence());
		if(type==QYS_REPORT_TYPE_CLICK){
			data.setX(MACRO_DOWN_X);
			data.setY(MACRO_DOWN_Y);
		}
		if(type==QYS_REPORT_TYPE_PV||type==QYS_REPORT_TYPE_CLICK
				||type==QYS_REPORT_TYPE_DOWNLOAD_END
				||type==QYS_REPORT_TYPE_INSTALL_END
				||type==QYS_REPORT_TYPE_ACTIVE){
			data.setStart(MACRO_EVENT_TIME_START);
			data.setEnd(MACRO_EVENT_TIME_END);
		}

		List<String> list=new ArrayList<String> ();
		StringBuilder sb = new StringBuilder();
		sb.append(reportUrl);
		sb.append("?").append(Bean2Utils.toHttpParams(data));
		list.add(sb.toString());
		return list;
	}*/
}
