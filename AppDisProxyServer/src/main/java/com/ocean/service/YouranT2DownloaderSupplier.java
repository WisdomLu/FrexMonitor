package com.ocean.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
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
import com.ocean.persist.app.dis.yrcpd_t2.pkgsearch.YouranT2PkgSearchApp;
import com.ocean.persist.app.dis.yrcpd_t2.pkgsearch.YouranT2PkgSearchReq;
import com.ocean.persist.app.dis.yrcpd_t2.pkgsearch.YouranT2PkgSearchResp;
import com.ocean.service.yrcpd.base.YouranMethodType;
import com.ocean.service.yrcpd_t2.YouranT2InvokeHandlerFactory;
import com.ocean.service.yrcpd_t2.YouranT2PkgSearchHandler;
@Component(value="YouranT2DownloaderSupplier")
public class YouranT2DownloaderSupplier   extends AbstractAppDisSupplier{
    private static final String YR_EVENT_TIME_START="__EVENT_TIME_START__";//     string
   // 开始时间 ACTION_DOWN 触发时间（13位时间戳）
    private static final String YR_EVENT_TIME_END="__EVENT_TIME_END__";
   // 结束时间 ACTION_UP 触发时间（13位时间戳）  （曝光的开始和结束时间不可相同）
    private static final String YR_OFFSET_X="__OFFSET_X__";
   // 点击位置距离整个广告位左边距 坐标不能固定
    private static final String YR_OFFSET_Y="__OFFSET_Y__";  
	public final static int YR_REPORT_TYPE_PV=1;
	public final static int YR_REPORT_TYPE_CLK=2;
	public final static int YR_REPORT_TYPE_DOWNLOAD=3;
	public final static int YR_REPORT_TYPE_DOWNLOAD_BEGIN=31;
	public final static int YR_REPORT_TYPE_INSTALL=4;
	public final static int YR_REPORT_TYPE_BEGIN_INSTALL=4;
	public final static int YR_REPORT_TYPE_ACTIVE=5;
    //点击位置距离整个广告位顶部边距 坐标不能固定
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
		YouranT2PkgSearchHandler handler=(YouranT2PkgSearchHandler)YouranT2InvokeHandlerFactory.getHandler(YouranMethodType.PKG_SEARCH);
		
		return parseKeywordSearchResp(req,handler);

	}
	private AppDisRecomReply  parseKeywordSearchResp(AppDisRecomReq req,YouranT2PkgSearchHandler handler) throws AppDisException{
		YouranT2PkgSearchReq request=handler.parsePkgParam(req);
		YouranT2PkgSearchResp reply=(YouranT2PkgSearchResp)handler.invok(req,request);
		
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
		for(YouranT2PkgSearchApp item:reply.getInfo()){
			AppDisContent appDisContent=new AppDisContent();
		
			appDisContent.setApkMd5(item.getPackageMd5());
			appDisContent.setApkUrl(this.parseMacro(YR_REPORT_TYPE_CLK,item.getDownloadUrl()));
			appDisContent.setContent(item.getDescription());
			
			
			appDisContent.setIsAd(1);
			
			if(StringUtils.isNotEmpty(item.getImage())){
				AppImg img=new AppImg();
				img.setSrc(item.getImage());
				appDisContent.setImglist(Collections.singletonList(img));
			}
			
			AppInfo app=new AppInfo();
			app.setAppName(item.getAppName());	//item.setAppName(item.getTitle());
		
			app.setIconUrl(item.getIcon());
			//app.setMinSdkVersion(String.valueOf(item.getMinSdkVersion()));
			app.setPkgName(item.getPackageName());
			
			app.setType(AppType.TYPE_APP);
			app.setVersionCode(item.getVersionCode());
			app.setFileSize(item.getFileSize());
			app.setVersionName(item.getVersionName());
			appDisContent.setAppInfo(app);
			
			//设置上报用字段
            Map<String,List<String>> report=new HashMap<String,List<String>>();
            if(CollectionUtils.isNotEmpty(item.getReportShowUrl())){
            	List<String> showL=new ArrayList<String>();
            	for(String url:item.getReportShowUrl()){
            		showL.add(this.parseMacro(YR_REPORT_TYPE_PV, url));
            	}
            	 report.put(SHOW, showL);
            }
            List<String> DownBeginL=new ArrayList<String>();
	        if(CollectionUtils.isNotEmpty(item.getReportClickUrl())){
	    	   //report.put(CLICK, item.getReportClickUrl());
	        	for(String url:item.getReportClickUrl()){
	        		DownBeginL.add(this.parseMacro(YR_REPORT_TYPE_CLK, url));
	        	}
            }
           
	        if(CollectionUtils.isNotEmpty(item.getReportDownloadBeginUrl())){
	        	for(String url:item.getReportDownloadBeginUrl()){
	        		DownBeginL.add(this.parseMacro(YR_REPORT_TYPE_DOWNLOAD_BEGIN, url));
	        	}
	        	
            }
	        report.put(DOWNLOAD_BEGIN, DownBeginL);
	        if(CollectionUtils.isNotEmpty(item.getReportDownloadCompletedUrl())){
	        	 List<String> DownEndL=new ArrayList<String>();
	        	for(String url:item.getReportDownloadCompletedUrl()){
	        		DownEndL.add(this.parseMacro(YR_REPORT_TYPE_DOWNLOAD, url));
	        	}
	    	   report.put(DOWNLOAD_END,DownEndL);
            }
           
           
            List<String> installL=new ArrayList<String>();
	        if(CollectionUtils.isNotEmpty(item.getReportInstallBeginUrl())){
	        	for(String url:item.getReportInstallBeginUrl()){
	        		installL.add(this.parseMacro(YR_REPORT_TYPE_BEGIN_INSTALL, url));
	        	}
	    	   
            }
           
	        if(CollectionUtils.isNotEmpty(item.getReportInstallCompletedUrl())){
	        	for(String url:item.getReportInstallCompletedUrl()){
	        		installL.add(this.parseMacro(YR_REPORT_TYPE_INSTALL, url));
	        	}
            }
           
            report.put(INSTALL,installL);
            
	        if(CollectionUtils.isNotEmpty(item.getReportActiveCompletedUrl())){
	        	List<String> activeL=new ArrayList<String>();
	        	for(String url:item.getReportActiveCompletedUrl()){
	        		activeL.add(this.parseMacro(YR_REPORT_TYPE_ACTIVE, url));
	        	}
	        	report.put(ACTIVE,activeL);
            }
            appDisContent.setThirdReportLinks(report);
            
            appDisContent.setReportType(ReportType.REPORT_GET);
			appL.add(appDisContent);
		}
		
		proxyReply.setAppDisContent(appL);
		
		
		return proxyReply;
	}
	private  String parseMacro(int type,String str){
		if(type!=YR_REPORT_TYPE_PV){
			str=str.replaceAll(YR_OFFSET_X, MACRO_DOWN_X)
					  .replaceAll(YR_OFFSET_Y,MACRO_DOWN_Y);
			
		}
		str=str.replaceAll(YR_EVENT_TIME_START, MACRO_EVENT_TIME_START)
				  .replaceAll(YR_EVENT_TIME_END,MACRO_EVENT_TIME_END);
		return str;
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
