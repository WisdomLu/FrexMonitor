package com.ocean.service;

import java.util.ArrayList;
import java.util.Arrays;
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
import com.ocean.persist.app.dis.zk.ZKAppContent;
import com.ocean.persist.app.dis.zk.ZKAppDetail;
import com.ocean.persist.app.dis.zk.ZKAppImg;
import com.ocean.persist.app.dis.zk.ZKAppRecomReply;
import com.ocean.persist.app.dis.zk.ZKAppRecomReq;
import com.ocean.service.zkInvokeHandler.ZKAppListSearchHandler;
import com.ocean.service.zkInvokeHandler.base.ZKInvokeHandlerFactory;
import com.ocean.service.zkInvokeHandler.base.ZKMethodType;
/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年10月10日 
      @version 1.0 
 */
@Component(value="ZKDownloaderAppSupplier")
public class ZKDownloaderAppSupplier extends AbstractAppDisSupplier{

	public AppDisRecomReply invoke(AppDisRecomReq params)
			throws AppDisException {
		switch(params.getInterType()){
			case APP_LIST_SEARCH:
				return appListSearch(params);
			case PKG_SEARCH:
				return appPkgSearch(params);//掌酷透传，不做特殊处理
			default:
			    throw new AppDisException(ErrorCode.PARAM_ERROR,"can not find the mapping interface:"+params.getInterType().name());
	    }
		
	}
	private AppDisRecomReply appListSearch(AppDisRecomReq req) throws AppDisException{
		ZKAppListSearchHandler handler=(ZKAppListSearchHandler)ZKInvokeHandlerFactory.getHandler(ZKMethodType.APP_LIST_SEARCH);
		return parseAppListSearchResp(req,(ZKAppRecomReply)handler.invok(req),handler.parseAppListSearchParam(req));

	}
	private AppDisRecomReply appPkgSearch(AppDisRecomReq req) throws AppDisException{
		ZKAppListSearchHandler handler=(ZKAppListSearchHandler)ZKInvokeHandlerFactory.getHandler(ZKMethodType.PKG_SEARCH);
		return parseAppListSearchResp(req,(ZKAppRecomReply)handler.invok(req),handler.parseAppListSearchParam(req));

	}
	private AppDisRecomReply parseAppListSearchResp(AppDisRecomReq req,ZKAppRecomReply ZKReply,ZKAppRecomReq ZKReq) throws AppDisException{
		
		
		if(!"0".equals(ZKReply.getErrorCode())){
			throw new AppDisException(ErrorCode.INTER_ERROR,"return error code:"+ZKReply.getErrorCode()+" msg:"+ZKReply.getErrorMsg());
		}
		List<ZKAppContent> appList=ZKReply.getAppDisContent();
		if(CollectionUtils.isEmpty(appList)){
			return null;
		}
		
		//返回值封装
		AppDisRecomReply reply=new AppDisRecomReply();
		reply.setErrorCode(com.ocean.app.dis.proxy.thrift.entity.ErrorCode.RC_SUCCESS);
		List<AppDisContent> appL=new ArrayList<AppDisContent>();
		if(CollectionUtils.isNotEmpty(ZKReply.getThirdReportLinks())){
			reply.setThirdReportLinks(ZKReply.getThirdReportLinks());
		}
		for(ZKAppContent app:appList){
			    AppDisContent appDisContent=new AppDisContent();
				appDisContent.setApkId(app.getApkId());
				appDisContent.setApkMd5(app.getApkMd5());
				appDisContent.setApkUrl(app.getApkUrl());

				appDisContent.setCategoryName(app.getCategoryName());
				appDisContent.setContent(app.getContent());
				appDisContent.setInstalledCount(app.getInstalledCount());
				appDisContent.setSignMd5(app.getSignMd5());
				appDisContent.setJoinSource(req.getJoinSource());
				
				if(1==app.getIsAd()){
					appDisContent.setIsAd(app.getIsAd());
					appDisContent.setPayMode(app.getPayMode());
					appDisContent.setPrice(app.getPrice());
				}

				if(CollectionUtils.isNotEmpty(app.getImglist())){
					List<AppImg> imgL=new ArrayList<AppImg>();
					for(ZKAppImg zkImg:app.getImglist()){
						AppImg img=new AppImg();
						img.setFormate(zkImg.getFormate());
						img.setHeight(zkImg.getHeight());
						img.setWidth(zkImg.getWidth());
						img.setSrc(zkImg.getSrc());
						imgL.add(img);
					}
					appDisContent.setImglist(imgL);
				}
				
				
				ZKAppDetail appDetail=app.getAppInfo();
				if(appDetail!=null){
					AppInfo replyApp=new AppInfo();
					
					replyApp.setAppName(appDetail.getAppName());
					replyApp.setIconUrl(appDetail.getIconUrl());
					replyApp.setMinSdkVersion(appDetail.getMinSdkVersion());
					replyApp.setPkgName(appDetail.getPkgName());
					
					replyApp.setType(AppType.findByValue(appDetail.getType()));
					replyApp.setVersionCode(appDetail.getVersionCode());
					replyApp.setVersionName(appDetail.getVersionName());
					replyApp.setFileSize(appDetail.getFileSize());
					appDisContent.setAppInfo(replyApp);
				}
	            
	            if(CollectionUtils.isNotEmpty(app.getThirdReportParams())){
	            	  appDisContent.setThirdReportParams(app.getThirdReportParams());
	            }
	            
	            if(CollectionUtils.isNotEmpty(app.getThirdReportLinks())){
	            	 appDisContent.setThirdReportLinks(app.getThirdReportLinks());
	            }
	          
	            
	            appDisContent.setReportType(ReportType.findByValue(app.getReportType()));

				appL.add(appDisContent);
		}
		
		
		reply.setAppDisContent(appL);
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
