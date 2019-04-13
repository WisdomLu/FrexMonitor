package com.ocean.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inveno.util.CollectionUtils;
import com.ocean.app.dis.proxy.thrift.entity.AppDisContent;
import com.ocean.app.dis.proxy.thrift.entity.AppDisRecomReply;
import com.ocean.app.dis.proxy.thrift.entity.AppDisRecomReq;
import com.ocean.app.dis.proxy.thrift.entity.AppInfo;
import com.ocean.app.dis.proxy.thrift.entity.AppType;
import com.ocean.app.dis.proxy.thrift.entity.ReportType;
import com.ocean.core.common.system.ErrorCode;
import com.ocean.core.common.system.SystemContext;
import com.ocean.handler.base.AbstractAppDisSupplier;
import com.ocean.persist.app.dis.AppDisException;
import com.ocean.persist.app.dis.AppDisResponse;
import com.ocean.persist.app.dis.appSearch360.keywordSearch.KeywordSearch360App;
import com.ocean.persist.app.dis.appSearch360.keywordSearch.KeywordSearch360Respose;
import com.ocean.persist.app.dis.appSearch360.keywordSearch.KeywordSearch360Track;
import com.ocean.persist.app.dis.onemobi.OnemobReqParams;
import com.ocean.persist.app.dis.onemobi.OnemobiAPP;
import com.ocean.persist.app.dis.onemobi.OnemobiResponse;
import com.ocean.service.OnemobiInvokeHandler.OnemobiAppListSearchHandler;
/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年10月10日 
      @version 1.0 
 */
@Component(value="OnemobiDownloaderAppSupplier")
public class OnemobiDownloaderAppSupplier extends AbstractAppDisSupplier{
	@Autowired
	private OnemobiAppListSearchHandler handler;
	public AppDisRecomReply invoke(AppDisRecomReq params)
			throws AppDisException {
		switch(params.getInterType()){
			case APP_LIST_SEARCH:
				return appListSearch(params);
			default:
			    throw new AppDisException(ErrorCode.PARAM_ERROR,"can not find the mapping interface:"+params.getInterType().name());
	    }
	}
	private AppDisRecomReply appListSearch(AppDisRecomReq req) throws AppDisException{

		return parseAppListSearchResp(req,(OnemobiResponse)handler.invok(req));

	}
	private AppDisRecomReply parseAppListSearchResp(AppDisRecomReq req,OnemobiResponse resp) throws AppDisException{

		if(0!=resp.getCode()){
			throw new AppDisException(ErrorCode.INTER_ERROR,"return error code:"+resp.getCode()+" error msg:"+resp.getMessage());
		}
		List<OnemobiAPP> apps=resp.getLists(); 
		if(CollectionUtils.isEmpty(apps)){
			return null;
		}
		
		
		//返回值封装
		AppDisRecomReply reply=new AppDisRecomReply();
		reply.setErrorCode(com.ocean.app.dis.proxy.thrift.entity.ErrorCode.RC_SUCCESS);
		List<AppDisContent> appL=new ArrayList<AppDisContent>();

		for(OnemobiAPP app:apps){
			  AppDisContent appDisContent=new AppDisContent();
				appDisContent.setApkId(app.getAppid());
				appDisContent.setApkUrl(app.getHref_download());
				AppInfo replyApp=new AppInfo();
				replyApp.setAppName(app.getAppname());
				replyApp.setIconUrl(app.getIcon());
				replyApp.setPkgName(app.getApk());

				
				replyApp.setType(AppType.TYPE_APP);
				replyApp.setVersionCode(app.getVersioncode());
				replyApp.setVersionName(app.getVersionname());
				replyApp.setFileSize(app.getSize());
				appDisContent.setAppInfo(replyApp);
				
				//设置上报用字段
	            Map<String,List<String>> report=new HashMap<String,List<String>>();
	            if(CollectionUtils.isNotEmpty(app.getRpt_ss())){
	            	report.put(SHOW,app.getRpt_ss());
	            }
/*	            if(CollectionUtils.isNotEmpty(app.getRpt_cd())){
	            	report.put(CLICK, app.getRpt_cd());
	            }*/
	            if(CollectionUtils.isNotEmpty(app.getRpt_cd())){
	            	report.put(DOWNLOAD_BEGIN, app.getRpt_cd());
	            }
	            if(CollectionUtils.isNotEmpty(app.getRpt_dc())){
	            	report.put(DOWNLOAD_END, app.getRpt_dc());
	            }
	            if(CollectionUtils.isNotEmpty( app.getRpt_ic())){
	            	report.put(INSTALL,  app.getRpt_ic());
	            }
	            if(CollectionUtils.isNotEmpty(app.getRpt_ac())){
	            	report.put(ACTIVE, app.getRpt_ac());
	            }
	            
	            
	            
	            
	            
	            appDisContent.setThirdReportLinks(report);
	            
	            appDisContent.setReportType(ReportType.REPORT_GET);

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
	public OnemobiAppListSearchHandler getHandler() {
		return handler;
	}
	public void setHandler(OnemobiAppListSearchHandler handler) {
		this.handler = handler;
	}

}
