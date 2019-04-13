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
import com.ocean.app.dis.proxy.thrift.entity.AppImg;
import com.ocean.app.dis.proxy.thrift.entity.AppInfo;
import com.ocean.app.dis.proxy.thrift.entity.AppType;
import com.ocean.app.dis.proxy.thrift.entity.ReportType;
import com.ocean.core.common.system.ErrorCode;
import com.ocean.handler.base.AbstractAppDisSupplier;
import com.ocean.persist.app.dis.AppDisException;
import com.ocean.persist.app.dis.AppDisResponse;
import com.ocean.persist.app.dis.tianmei.getAppList.TianmeiApp;
import com.ocean.persist.app.dis.tianmei.getAppList.TianmeiAppListReq;
import com.ocean.persist.app.dis.tianmei.getAppList.TianmeiAppListResp;
import com.ocean.persist.app.dis.tianmei.kwdSearch.TianmeiKeywordSchReq;
import com.ocean.persist.app.dis.tianmei.kwdSearch.TianmeiKeywordSchResp;
import com.ocean.service.tianmeiInvokeHandler.TianmeiKeywordSearchHandler;
import com.ocean.service.tianmeiInvokeHandler.TianmeiListSearchHandler;
import com.ocean.service.tianmeiInvokeHandler.base.TianmeiInvokeHandlerFactory;
import com.ocean.service.tianmeiInvokeHandler.base.TianmeiMethodType;
@Component(value="TianmeiDownloaderSupplier")
public class TianmeiDownloaderSupplier   extends AbstractAppDisSupplier{
	public AppDisRecomReply invoke(AppDisRecomReq params)
			throws AppDisException {
		// TODO Auto-generated method stub
		switch(params.getInterType()){
				case APP_LIST_SEARCH:
					
					return appListSearch(params);
				case KEY_WORD_SEARCH:
					
					return appKerwordSearch(params);
				default:
				    throw new AppDisException(ErrorCode.PARAM_ERROR,"can not find the mapping interface:"+params.getInterType().name());
		        
		}
	}
	private AppDisRecomReply appListSearch(AppDisRecomReq req) throws AppDisException{
		TianmeiListSearchHandler handler=(TianmeiListSearchHandler)TianmeiInvokeHandlerFactory.getHandler(TianmeiMethodType.APP_LIST_SEARCH);

		return parseAppListSearchResp(req,handler);

	}
	private AppDisRecomReply appKerwordSearch(AppDisRecomReq req) throws AppDisException{
		TianmeiKeywordSearchHandler handler=(TianmeiKeywordSearchHandler)TianmeiInvokeHandlerFactory.getHandler(TianmeiMethodType.KEYWORD_SEARCH);

		return parseAppKeywordSearchResp(req,handler);

	}
	private AppDisRecomReply  parseAppKeywordSearchResp(AppDisRecomReq req,TianmeiKeywordSearchHandler handler) throws AppDisException{
		TianmeiKeywordSchReq request=handler.parseKeywordSearchParam(req);
		TianmeiKeywordSchResp reply=(TianmeiKeywordSchResp)handler.invok(req,request);
		
		if(reply==null||reply.getAdms()==null||CollectionUtils.isEmpty(reply.getAdms())){
			return null;
		}
		if(reply.getResult()!=0){
			throw new AppDisException(ErrorCode.INTER_ERROR,"return error code:"+reply.getResult()+" msg:"+reply.getMsg());
		}
		return commonParseSearchResp(reply.getAdms());

	}
	private AppDisRecomReply  commonParseSearchResp(List<TianmeiApp> items){
		//返回值封装
		AppDisRecomReply proxyReply=new AppDisRecomReply();
		proxyReply.setErrorCode(com.ocean.app.dis.proxy.thrift.entity.ErrorCode.RC_SUCCESS);
		List<AppDisContent> appL=new ArrayList<AppDisContent>();
		for(TianmeiApp item:items){
			AppDisContent appDisContent=new AppDisContent();
			appDisContent.setApkUrl(item.getDownurl());
			appDisContent.setIsAd(1);
			AppInfo app=new AppInfo();
			app.setAppName(item.getName());	//item.setAppName(item.getTitle());
			app.setIconUrl(item.getIcon());
			List<AppImg> imgList=new ArrayList<AppImg>();
			if(CollectionUtils.isNotEmpty(item.getImgurl())){
				for(String url:item.getImgurl()){
					AppImg img=new AppImg();
					img.setSrc(url);
					imgList.add(img);
				}
				appDisContent.setImglist(imgList);
			}
			
			app.setPkgName(item.getPkg());
			app.setType(AppType.TYPE_APP);
			app.setFileSize(String.valueOf(item.getSize()));
			appDisContent.setAppInfo(app);
			//设置上报用字段
            Map<String,List<String>> report=new HashMap<String,List<String>>();
            report.put(SHOW, item.getShow());
            List<String> downstartL=new ArrayList<String>();
            downstartL.addAll(item.getClick());
            downstartL.addAll(item.getDownst());
            report.put(DOWNLOAD_BEGIN, downstartL);
            report.put(DOWNLOAD_END,item.getDownfin());
            List<String> installL=new ArrayList<String>();
            installL.addAll(item.getInstart());
            installL.addAll(item.getInsfin());
            report.put(INSTALL,installL);
            report.put(ACTIVE, item.getActive());
            appDisContent.setThirdReportLinks(report);
            appDisContent.setReportType(ReportType.REPORT_GET);
			appL.add(appDisContent);
		}
	
		proxyReply.setAppDisContent(appL);
		
		
		return proxyReply;
	}
	private AppDisRecomReply  parseAppListSearchResp(AppDisRecomReq req,TianmeiListSearchHandler handler) throws AppDisException{
		TianmeiAppListReq request=handler.parseListSearchParam(req);
		TianmeiAppListResp reply=(TianmeiAppListResp)handler.invok(req,request);
		
		if(reply==null||reply.getAdms()==null||CollectionUtils.isEmpty(reply.getAdms())){
			return null;
		}
		if(reply.getResult()!=0){
			throw new AppDisException(ErrorCode.INTER_ERROR,"return error code:"+reply.getResult()+" msg:"+reply.getMsg());
		}

		//返回值封装
		AppDisRecomReply proxyReply=new AppDisRecomReply();
		proxyReply.setErrorCode(com.ocean.app.dis.proxy.thrift.entity.ErrorCode.RC_SUCCESS);
		List<AppDisContent> appL=new ArrayList<AppDisContent>();
		for(TianmeiApp item:reply.getAdms()){
			AppDisContent appDisContent=new AppDisContent();
			//appDisContent.setApkId(item.getApkId());
			//appDisContent.setApkMd5(item.getMd5());
			appDisContent.setApkUrl(item.getDownurl());
			//appDisContent.setAppId(item.getAppId());
			//appDisContent.setCategoryId(String.valueOf(item.getCategoryId()));
			//appDisContent.setCategoryName(item.getCategoryName());
			//appDisContent.setContent(item.getDescription());
			//appDisContent.setInstalledCount(item.getAppDownCount());
/*				if(StringUtils.isNotEmpty(item.getTotalDownloadTimes())){
				appDisContent.setInstalledCount(Integer.parseInt(item.getTotalDownloadTimes()));
			}*/
			
			//appDisContent.setSignMd5(item.getSequence());
			
			appDisContent.setIsAd(1);
			//appDisContent.setContent(item.getDesc());
			
			
			AppInfo app=new AppInfo();
			app.setAppName(item.getName());	//item.setAppName(item.getTitle());
		
			app.setIconUrl(item.getIcon());
			//app.setMinSdkVersion(String.valueOf(item.getMinSdkVersion()));
			List<AppImg> imgList=new ArrayList<AppImg>();
			if(CollectionUtils.isNotEmpty(item.getImgurl())){
				for(String url:item.getImgurl()){
					AppImg img=new AppImg();
					img.setSrc(url);
					imgList.add(img);
				}
				appDisContent.setImglist(imgList);
			}
			
			app.setPkgName(item.getPkg());
			
			app.setType(AppType.TYPE_APP);
			//app.setVersionCode(item.getVersion_code());
			
			app.setFileSize(String.valueOf(item.getSize()));
			//app.setVersionName(item.getVersion_name());
			appDisContent.setAppInfo(app);
			
			//设置上报用字段
            Map<String,List<String>> report=new HashMap<String,List<String>>();
            report.put(SHOW, item.getShow());
            
            //report.put(CLICK, wrapReportData(QYS_REPORT_TYPE_CLICK,request,item));
            List<String> downstartL=new ArrayList<String>();
            downstartL.addAll(item.getClick());
            downstartL.addAll(item.getDownst());
            report.put(DOWNLOAD_BEGIN, downstartL);
            
            report.put(DOWNLOAD_END,item.getDownfin());
           
            List<String> installL=new ArrayList<String>();
            installL.addAll(item.getInstart());
            installL.addAll(item.getInsfin());
            report.put(INSTALL,installL);
            report.put(ACTIVE, item.getActive());
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
