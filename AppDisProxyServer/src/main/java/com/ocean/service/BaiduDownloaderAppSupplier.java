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
import com.ocean.app.dis.proxy.thrift.entity.AppInfo;
import com.ocean.app.dis.proxy.thrift.entity.AppType;
import com.ocean.app.dis.proxy.thrift.entity.ReportType;
import com.ocean.core.common.system.ErrorCode;
import com.ocean.handler.base.AbstractAppDisSupplier;
import com.ocean.persist.app.dis.AppDisException;
import com.ocean.persist.app.dis.AppDisResponse;
import com.ocean.persist.app.dis.baiduAppSearch.keywordSearch.KeywordSearchBaiduAppDetail;
import com.ocean.persist.app.dis.baiduAppSearch.keywordSearch.KeywordSearchBaiduAppList;
import com.ocean.persist.app.dis.baiduAppSearch.keywordSearch.KeywordSearchBaiduReply;
import com.ocean.persist.app.dis.baiduAppSearch.keywordSearch.KeywordSearchBaiduRequest;
import com.ocean.persist.app.dis.baiduAppSearch.keywordSearch.KeywordSearchBaiduRespose;
import com.ocean.persist.app.dis.baiduAppSearch.keywordSearch.Signer;
import com.ocean.service.baiduInvokeHandler.BaiduKeywordSearchHandler;
import com.ocean.service.baiduInvokeHandler.base.BaiduInvokeHandlerFactory;
import com.ocean.service.baiduInvokeHandler.base.BaiduMethodType;
/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年10月10日 
      @version 1.0 
 */
@Component(value="baiduDownloaderAppSupplier")
public class BaiduDownloaderAppSupplier extends AbstractAppDisSupplier{

	public AppDisRecomReply invoke(AppDisRecomReq params)
			throws AppDisException {
		// TODO Auto-generated method stub
		switch(params.getInterType()){
			case KEY_WORD_SEARCH:
				return keyWordSearch(params);
			case HOT_WORD_SEARCH:
				return keyWordSearch(params);
			default:
			    throw new AppDisException(ErrorCode.PARAM_ERROR,"can not find the mapping interface:"+params.getInterType().name());
	    }
	}
	private AppDisRecomReply keyWordSearch(AppDisRecomReq req) throws AppDisException{
		BaiduKeywordSearchHandler handler=(BaiduKeywordSearchHandler)BaiduInvokeHandlerFactory.getHandler(BaiduMethodType.KEYWORD_SEARCH);
		return parseKeySearchResp(req,(KeywordSearchBaiduReply)handler.invok(req),handler.parseKeyWordSearchParam(req));

	}
	private AppDisRecomReply parseKeySearchResp(AppDisRecomReq req,KeywordSearchBaiduReply baiduReply,KeywordSearchBaiduRequest baiduReq) throws AppDisException{
		KeywordSearchBaiduRespose resp=baiduReply.getResponse();
		if(!"0".equals(resp.getStatuscode())){
			throw new AppDisException(ErrorCode.INTER_ERROR,"return error code:"+resp.getStatuscode()+" msg:"+resp.getStatusmessage());
		}
		if(null==resp.getResult()){
			return null;
		}
		List<KeywordSearchBaiduAppList> appList=resp.getResult().get(0).getApps(); 
		if(CollectionUtils.isEmpty(appList)){
			return null;
		}
		List<KeywordSearchBaiduAppDetail> apps=appList.get(0).getApp(); 
		if(CollectionUtils.isEmpty(apps)){
			return null;
		}
		
		
		//返回值封装
		AppDisRecomReply reply=new AppDisRecomReply();
		reply.setErrorCode(com.ocean.app.dis.proxy.thrift.entity.ErrorCode.RC_SUCCESS);
		List<AppDisContent> appL=new ArrayList<AppDisContent>();

		for(KeywordSearchBaiduAppDetail app:apps){
			  AppDisContent appDisContent=new AppDisContent();
				appDisContent.setApkId(app.getDocid());
				appDisContent.setApkMd5(app.getMd5());
				appDisContent.setApkUrl(appendParam(app.getDownload_url(),baiduReq));

				appDisContent.setCategoryName(app.getCatename());
				appDisContent.setContent(app.getBrief());
				appDisContent.setInstalledCount(app.getAll_download_pid());
				appDisContent.setSignMd5(app.getSignmd5());
				
				
				AppInfo replyApp=new AppInfo();
				replyApp.setAppName(app.getSname());
				replyApp.setIconUrl(app.getIcon());
				//replyApp.setMinSdkVersion(String.valueOf(app.getOs_version()));
				replyApp.setPkgName(app.get_package());

				
				replyApp.setType("soft".equals(app.getType())?AppType.TYPE_APP:AppType.TYPE_GAME);
				replyApp.setVersionCode(app.getVersioncode());
				replyApp.setVersionName(app.getVersionname());
				replyApp.setFileSize(app.getSize()+"");
				appDisContent.setAppInfo(replyApp);
				
				//设置上报用字段
               Map<String,List<String>> report=new HashMap<String,List<String>>();
            /*	      if(CollectionUtils.isNotEmpty(track.getTk_imp())){
	            	report.put(SHOW,track.getTk_imp());
	            }
	            if(CollectionUtils.isNotEmpty(track.getTk_clk())){
	            	report.put(CLICK, track.getTk_clk());
	            }*/
	            if(StringUtils.isNotEmpty(app.getDl_callback())){
	            	report.put(DOWNLOAD_END, Arrays.asList(appendParam(app.getDl_callback(),baiduReq)));
	            }
	            /*
	            if(CollectionUtils.isNotEmpty( track.getTk_ins())){
	            	report.put(INSTALL, track.getTk_ins());
	            }
	            if(CollectionUtils.isNotEmpty(track.getTk_act())){
	            	report.put(ACTIVE, track.getTk_act());
	            }
	            
	            */
	            
	            
	            
	            appDisContent.setThirdReportLinks(report);
	            
	            appDisContent.setReportType(ReportType.REPORT_GET);

				appL.add(appDisContent);
		}
		
		
		reply.setAppDisContent(appL);
		return reply;
	}
	private String appendParam(String url,KeywordSearchBaiduRequest baiduReq){
		if(StringUtils.isEmpty(url)){
			return null;
		}
		StringBuffer urlBuffer=new StringBuffer(url);
		String sufix=url.indexOf("?")<0?"?":"&";
		urlBuffer.append(sufix);
	
		
		StringBuffer buffer=new StringBuffer();
	    buffer.append("bdi_imei=").append(baiduReq.getBdi_imei()).append("&")
	    .append("bdi_loc=").append(baiduReq.getBdi_loc()).append("&")
	    .append("bdi_uip=").append(baiduReq.getBdi_uip()).append("&")
	    .append("bdi_bear=").append(baiduReq.getBdi_bear()).append("&")
	    .append("resolution=").append(baiduReq.getResolution()).append("&")
	    .append("apilevel=").append(baiduReq.getApilevel()).append("&")
	    .append("os_version=").append(baiduReq.getOs_version()).append("&")
	    .append("brand=").append(baiduReq.getBrand()).append("&")
	    .append("model=").append(baiduReq.getModel()).append("&")
	    .append("pver=").append(baiduReq.getPver()).append("&")
	    .append("bdi_mac=").append(baiduReq.getBdi_mac()).append("&") ;
	    
	    buffer.append("sign=").append(Signer.toMd5(buffer.toString()));
	    urlBuffer.append(buffer.toString());
	    
	    return urlBuffer.toString();


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
