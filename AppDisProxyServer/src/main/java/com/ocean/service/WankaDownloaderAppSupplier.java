package com.ocean.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.ocean.app.dis.proxy.thrift.entity.AppDisContent;
import com.ocean.app.dis.proxy.thrift.entity.AppDisRecomReply;
import com.ocean.app.dis.proxy.thrift.entity.AppDisRecomReq;
import com.ocean.app.dis.proxy.thrift.entity.AppInfo;
import com.ocean.app.dis.proxy.thrift.entity.AppType;
import com.ocean.app.dis.proxy.thrift.entity.DeviceInfo;
import com.ocean.app.dis.proxy.thrift.entity.ReportType;
import com.ocean.app.dis.proxy.thrift.entity.UserInfo;
import com.ocean.core.common.JsonUtils;
import com.ocean.core.common.system.ErrorCode;
import com.ocean.handler.base.AbstractAppDisSupplier;
import com.ocean.persist.app.dis.AppDisException;
import com.ocean.persist.app.dis.AppDisResponse;
import com.ocean.persist.app.dis.wanka.WankaApp;
import com.ocean.persist.app.dis.wanka.WankaReportData;
import com.ocean.persist.app.dis.wanka.appasyn.WankaAppAsynReq;
import com.ocean.persist.app.dis.wanka.pkgsearch.WankaPkgResponse;
import com.ocean.service.wankaInvokeHandler.WankaAppasynHandler;
import com.ocean.service.wankaInvokeHandler.WankaPkgSearchHandler;
import com.ocean.service.wankaInvokeHandler.base.WankaInvokeHandlerFactory;
import com.ocean.service.wankaInvokeHandler.base.WankaMethodType;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年5月12日 
      @version 1.0 
 */
@Component(value="WankaDownloaderAppSupplier")
public class WankaDownloaderAppSupplier extends AbstractAppDisSupplier{
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
		WankaPkgSearchHandler pkgSearchHandler=(WankaPkgSearchHandler)WankaInvokeHandlerFactory.getHandler(WankaMethodType.PKG_SEARCH);
		WankaPkgResponse resp=(WankaPkgResponse)pkgSearchHandler.invok(req);
		
		return parseRecommendADListResp(req,resp);
	}

	private AppDisRecomReply  parseRecommendADListResp (AppDisRecomReq req,WankaPkgResponse resp) throws AppDisException{
		if(resp==null||resp.getContent()==null||resp.getContent().getInfo()==null){
			throw new AppDisException(ErrorCode.INTER_ERROR,"DATA_EMPTY return error code:"+resp.getResult()+" msg:"+resp.getMsg());
			
		}
		if(resp.getResult()!=0){
			throw new AppDisException(ErrorCode.INTER_ERROR,"DATA_ERROR return error code:"+resp.getResult()+" msg:"+resp.getMsg());
		}
		AppDisRecomReply reply=new AppDisRecomReply();
		reply.setErrorCode(com.ocean.app.dis.proxy.thrift.entity.ErrorCode.RC_SUCCESS);
		List<AppDisContent> appL=new ArrayList<AppDisContent>();
		{
			WankaApp item=resp.getContent().getInfo();
			AppDisContent appDisContent=new AppDisContent();
			
			appDisContent.setApkMd5(item.getApk_md5());
			appDisContent.setApkUrl(item.getApk_url());
			
			appDisContent.setCategoryId(String.valueOf(item.getCategory_id()));
			appDisContent.setCategoryName(item.getCategory_name());
			//appDisContent.setContent(item.getShortDesc());
	
			//默认设置为是广告
			appDisContent.setIsAd(1);
			
			
			if(StringUtils.isNotEmpty(item.getDownload_cnt())){
				appDisContent.setInstalledCount(Integer.parseInt(item.getDownload_cnt()));
			}

			
			AppInfo app=new AppInfo();
			app.setAppName(item.getName());
			app.setIconUrl(item.getIcon_url());
			app.setPkgName(item.get_package());
			AppType appType=appTypeMap.get(item.getType());
			app.setType(appType==null?AppType.TYPE_APP:appType);
			app.setVersionCode(item.getVersion_code());
			app.setFileSize(item.getApk_size());
			app.setVersionName(item.getVersion_name());
			appDisContent.setAppInfo(app);
			
			//设置上报用字段
            Map<String,List<String>> report=new HashMap<String,List<String>>();
            report.put(SHOW, pvReport(WankaMethodType.PKG_SEARCH.getName(),req,item));
            report.put(CLICK, clickReport(WankaMethodType.PKG_SEARCH.getName(),req,item));
            report.put(DOWNLOAD_BEGIN, beginDownloadReport(WankaMethodType.PKG_SEARCH.getName(),req,item));
            report.put(DOWNLOAD_END, endDownloadReport(WankaMethodType.PKG_SEARCH.getName(),req,item));
            report.put(INSTALL, installReport(WankaMethodType.PKG_SEARCH.getName(),req,item));
            report.put(ACTIVE, activeReport(WankaMethodType.PKG_SEARCH.getName(),req,item));
            
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
		WankaReportData report =new WankaReportData();
	
		report=getBaseReportBody(req,resp);
		list.add(JsonUtils.toJson(report));
		return list;
	}
	public List<String> clickReport(String interTye,AppDisRecomReq req,AppDisResponse resp) throws AppDisException {
		// TODO Auto-generated method stub
		List<String> list=new ArrayList<String>();
		WankaReportData report =new WankaReportData();
		
		report=getBaseReportBody(req,resp);
		list.add(JsonUtils.toJson(report));
		return list;
	}

	public List<String> beginDownloadReport(String interTye,AppDisRecomReq req,AppDisResponse resp)
			throws AppDisException {
		// TODO Auto-generated method stub
		List<String> list=new ArrayList<String>();
		WankaReportData report =new WankaReportData();
		report=getBaseReportBody(req,resp);
		list.add(JsonUtils.toJson(report));
		return list;
	}

	public List<String> endDownloadReport(String interTye,AppDisRecomReq req,AppDisResponse resp) throws AppDisException {
		// TODO Auto-generated method stub
		List<String> list=new ArrayList<String>();
		WankaReportData report =new WankaReportData();
		report=getBaseReportBody(req,resp);
		list.add(JsonUtils.toJson(report));
		return list;
	}

	public List<String> installReport(String interTye,AppDisRecomReq req,AppDisResponse resp) throws AppDisException {
		// TODO Auto-generated method stub
		List<String> list=new ArrayList<String>();
		WankaReportData report =new WankaReportData();

		report=getBaseReportBody(req,resp);
		list.add(JsonUtils.toJson(report));
		return list;
	}
	public List<String> activeReport(String interTye,AppDisRecomReq req,AppDisResponse resp) throws AppDisException {
		// TODO Auto-generated method stub
		List<String> list=new ArrayList<String>();
		WankaReportData report =new WankaReportData();

		report=getBaseReportBody(req,resp);
		list.add(JsonUtils.toJson(report));
		return list;
	}
    private WankaReportData getBaseReportBody(AppDisRecomReq req,AppDisResponse resp)throws AppDisException{
    	WankaReportData report =new WankaReportData();
		if(resp instanceof WankaApp){
	    	WankaAppAsynReq param=null;
			try {
				param = WankaAppasynHandler.parseAppasynParam(req);
			} catch (AppDisException e) {
				// TODO Auto-generated catch block
				throw new AppDisException(ErrorCode.INTER_ERROR,"get signature error when invok parseAppasynParam method");
				
			}
			
			
			WankaApp item=(WankaApp)resp;
		
			report.setReportData(Collections.singletonList(item.get_package()));
			report.setSign(getSignature(req,param,item.get_package()));
			report.setUrlParams(getParamStr(req, param,item.get_package()).toString());
		}
		return report;
    }
    private String getSignature(AppDisRecomReq req,WankaAppAsynReq param,String pkg)throws AppDisException{

    	StringBuffer sb=getParamStr( req, param, pkg);
    	
    	sb.append(req.getDevice().getImei())
		.append(req.getAppInfo().getPkgName())
		.append(req.getJoinParam().get(2).getValue());
		
    	
		return DigestUtils.md5Hex(sb.toString()).toLowerCase();
		 
    }
    private StringBuffer getParamStr(AppDisRecomReq req,WankaAppAsynReq param,String pkg){
    	UserInfo user=req.getUserInfo();
		DeviceInfo device=req.getDevice();
		String uid=DigestUtils.md5Hex(device.getImei()+device.getAdid()+UUID.randomUUID().toString());//toUpercase(Md5(imei+adid+uuid))
    	StringBuffer sb=new StringBuffer();
		try {
			sb.append("app_id=").append(param.getApp_id())
			.append("&channel_id=").append(param.getChannel_id())
			.append("&client_id=").append(device.getImei())
			.append("&client_ip=").append(user.getClientIp())
			.append("&cuid=").append(uid.toUpperCase())
			.append("&device=").append(URLEncoder.encode(device.getPhonemodel(),CHARSET_CODER))
			.append("&info_ma=").append(device.getMac())
			.append("&info_ms=").append(device.getImsi())
			.append("&nonce=").append((int)(System.currentTimeMillis()/1000))
			.append("&os_id=").append(device.getAdid())
			.append("&reportData=").append(URLEncoder.encode(JsonUtils.toJson(Collections.singletonList(pkg)),CHARSET_CODER));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return sb;
    }
}
