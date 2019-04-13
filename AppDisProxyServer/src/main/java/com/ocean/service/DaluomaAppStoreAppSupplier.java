package com.ocean.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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
import com.ocean.app.dis.proxy.thrift.entity.AppInfo;
import com.ocean.app.dis.proxy.thrift.entity.AppType;
import com.ocean.app.dis.proxy.thrift.entity.ReportType;
import com.ocean.core.common.system.ErrorCode;
import com.ocean.core.common.system.SystemContext;
import com.ocean.handler.base.AbstractAppDisSupplier;
import com.ocean.persist.app.dis.AppDisException;
import com.ocean.persist.app.dis.AppDisResponse;
import com.ocean.persist.app.dis.daluoma.BaseDaluomaApp;
import com.ocean.persist.app.dis.daluoma.BaseDaluomaResponse;
import com.ocean.persist.app.dis.daluoma.keywordSearch.DaluomaKeywordResp;
import com.ocean.persist.app.dis.daluoma.pkgSearch.DaluomaPkgSearchResp;
import com.ocean.service.daluomaInvokeHandler.DaluomaKeywordSearchHandler;
import com.ocean.service.daluomaInvokeHandler.DaluomaPkgSearchHandler;
import com.ocean.service.daluomaInvokeHandler.base.DaluomaInvokeHandlerFactory;
import com.ocean.service.daluomaInvokeHandler.base.DaluomaMethodType;


/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年7月27日 
      @version 1.0 
 */
@Component(value="DaluomaAppStoreAppSupplier")
public class DaluomaAppStoreAppSupplier  extends AbstractAppDisSupplier{
	private final static String QIHOO_PAY_MODE="qihoo.pay.mode";
	public AppDisRecomReply invoke(AppDisRecomReq req)
			throws AppDisException {
		// TODO Auto-generated method stub
		switch(req.getInterType()){
			case KEY_WORD_SEARCH:
				return keyWordSearch(req);
			case HOT_WORD_SEARCH:
				return keyWordSearch(req);
			case PKG_SEARCH:
				return pkgSearch(req);
			default:
			    throw new AppDisException(ErrorCode.PARAM_ERROR,"can not find the mapping interface:"+req.getInterType().name());
	    }

	}
	private AppDisRecomReply parseResp(AppDisRecomReq req,BaseDaluomaResponse resp) throws AppDisException{
		int total=StringUtils.isEmpty(resp.getTotal())?0:Integer.parseInt(resp.getTotal());
		if(total<1){
			throw new AppDisException(ErrorCode.INTER_ERROR,"return error code:"+resp.getErrno());
		}
		
		List<BaseDaluomaApp> apps=resp.getData(); 
		if(CollectionUtils.isEmpty(apps)){
			return null;
		}
		
		
		//返回值封装
		AppDisRecomReply reply=new AppDisRecomReply();
		reply.setErrorCode(com.ocean.app.dis.proxy.thrift.entity.ErrorCode.RC_SUCCESS);
		List<AppDisContent> appL=new ArrayList<AppDisContent>();


		for(BaseDaluomaApp app:apps){
			  AppDisContent appDisContent=new AppDisContent();
				appDisContent.setApkId(app.getId());
				appDisContent.setApkMd5(app.getApk_md5());
				appDisContent.setApkUrl(app.getDown_url());

				appDisContent.setCategoryName(app.getCategory());
				//appDisContent.setContent(app.getShortDesc());
				appDisContent.setInstalledCount(parseDownloadTime(app.getDownload_times()));
				appDisContent.setSignMd5(app.getSignature_md5());
				boolean isAd="1".equals(app.getIs_ad());
				appDisContent.setIsAd(isAd?1:2);
				
				String payMode=SystemContext.getDynamicPropertyHandler().get(QIHOO_PAY_MODE);
				if("1".equals(app.getIs_ad())&&app.getEcpc()>0&&PAY_MODE_CPC.equals(payMode)){
					appDisContent.setPayMode(payMode);
					appDisContent.setPrice(String.valueOf(app.getEcpc()));
				}else if("1".equals(app.getIs_ad())&&app.getEcpm()>0&&PAY_MODE_CPM.equals(payMode)){
					appDisContent.setPayMode(payMode);
					appDisContent.setPrice(String.valueOf(app.getEcpm()));
				}
				
				
				AppInfo replyApp=new AppInfo();
				replyApp.setAppName(app.getName());
				replyApp.setIconUrl(app.getLogo_url());
				replyApp.setMinSdkVersion(String.valueOf(app.getOs_version()));
				replyApp.setPkgName(app.getApkid());

				
				replyApp.setType("soft".equals(app.getType())?AppType.TYPE_APP:AppType.TYPE_GAME);
				replyApp.setVersionCode(app.getVersion_code());
				replyApp.setVersionName(app.getVersion_name());
				replyApp.setFileSize(app.getSize());
				appDisContent.setAppInfo(replyApp);
				
	            Map<String,List<String>> report=new HashMap<String,List<String>>();
	            if(StringUtils.isNotEmpty(app.getTk_imp())){
	            	report.put(SHOW,Collections.singletonList(app.getTk_imp()));
	            }
	            
	            if(StringUtils.isNotEmpty(app.getTk_clk())){
	            	report.put(CLICK, Collections.singletonList(app.getTk_clk()));
	            }
	            if(StringUtils.isNotEmpty( app.getTk_ins())){
	              
	            	report.put(INSTALL, Collections.singletonList(app.getTk_ins()));
	            	
	            }
	          
	            if(StringUtils.isNotEmpty(app.getTk_act())){
	            	report.put(ACTIVE, Collections.singletonList(app.getTk_act()));
	            }
	        	
	            
	            appDisContent.setThirdReportLinks(report);
	            
	            appDisContent.setReportType(ReportType.REPORT_GET);

				appL.add(appDisContent);
		}
		
		
		reply.setAppDisContent(appL);
		return reply;

	}

	private long parseDownloadTime(String str){
		
		  StringBuffer buffer=new StringBuffer();
		  for (int i = 0; i < str.length(); i++){

			   if (Character.isDigit(str.charAt(i))){
				   buffer.append(str.charAt(i));
			   }else if("万".equals(String.valueOf(str.charAt(i)))){
				   buffer.append("0000");
			   }else{
				   break;
			   }
		  }
		  if(buffer.length()>0){
			  return Long.parseLong(buffer.toString());
		  }
		  return 0;
	}
	private AppDisRecomReply keyWordSearch(AppDisRecomReq req) throws AppDisException{
		DaluomaKeywordSearchHandler handler=(DaluomaKeywordSearchHandler)DaluomaInvokeHandlerFactory.getHandler(DaluomaMethodType.KEYWORD_SEARCH);
		return parseResp(req,(DaluomaKeywordResp)handler.invok(req));

	}

	private AppDisRecomReply pkgSearch(AppDisRecomReq req) throws AppDisException{
		
		DaluomaPkgSearchHandler handler=(DaluomaPkgSearchHandler)DaluomaInvokeHandlerFactory.getHandler(DaluomaMethodType.PACKAGE_SEARCH);
		return parseResp(req,(DaluomaPkgSearchResp)handler.invok(req));
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
