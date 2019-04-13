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
import com.ocean.app.dis.proxy.thrift.entity.AppInfo;
import com.ocean.app.dis.proxy.thrift.entity.AppType;
import com.ocean.app.dis.proxy.thrift.entity.ReportType;
import com.ocean.core.common.system.ErrorCode;
import com.ocean.core.common.system.SystemContext;
import com.ocean.persist.app.dis.AppDisException;
import com.ocean.persist.app.dis.AppDisResponse;
import com.ocean.persist.app.dis.appSearch360.keywordSearch.KeywordSearch360App;
import com.ocean.persist.app.dis.appSearch360.keywordSearch.KeywordSearch360Respose;
import com.ocean.persist.app.dis.appSearch360.keywordSearch.KeywordSearch360Track;
import com.ocean.service.qihooInvokeHandler.QihooAppStoreSupplierBase;
import com.ocean.service.qihooInvokeHandler.QihooKeywordSearchHandler;
import com.ocean.service.qihooInvokeHandler.base.QihooInvokeHandlerFactory;
import com.ocean.service.qihooInvokeHandler.base.QihooMethodType;


/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年7月27日 
      @version 1.0 
 */
@Component(value="qihooAppStoreAppSupplier")
public class QihooAppStoreAppSupplier  extends QihooAppStoreSupplierBase{
	
	public AppDisRecomReply invoke(AppDisRecomReq req)
			throws AppDisException {
		// TODO Auto-generated method stub
		switch(req.getInterType()){
			case KEY_WORD_SEARCH:
				return keyWordSearch(req);
			case HOT_WORD_SEARCH:
				return keyWordSearch(req);
			/*case PKG_SEARCH:
				return pkgSearch(req);*/
			default:
			    throw new AppDisException(ErrorCode.PARAM_ERROR,"can not find the mapping interface:"+req.getInterType().name());
	    }

	}
	private AppDisRecomReply parseKeySearchResp(AppDisRecomReq req,KeywordSearch360Respose resp) throws AppDisException{

		if(!"0".equals(resp.getErrno())){
			throw new AppDisException(ErrorCode.INTER_ERROR,"return error code:"+resp.getErrno());
		}
		List<KeywordSearch360App> apps=resp.getData(); 
		if(CollectionUtils.isEmpty(apps)){
			return null;
		}
		
		
		//返回值封装
		AppDisRecomReply reply=new AppDisRecomReply();
		reply.setErrorCode(com.ocean.app.dis.proxy.thrift.entity.ErrorCode.RC_SUCCESS);
		List<AppDisContent> appL=new ArrayList<AppDisContent>();


		List<KeywordSearch360Track> tracks=resp.getTrack();
		for(KeywordSearch360App app:apps){
			  KeywordSearch360Track track=getKeyWordSearchTrack(app.getBindid(),tracks);
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
				
	            
				List<String> showL=new ArrayList<String>();
				List<String> downL=new ArrayList<String>();
				List<String> instL=new ArrayList<String>();
				List<String> activeL=new ArrayList<String>();
	            Map<String,List<String>> report=new HashMap<String,List<String>>();
	            if(CollectionUtils.isNotEmpty(track.getTk_imp())){
	            	for(String url:track.getTk_imp()){
	            		showL.add(this.parseMacro(QIHOO_REPORT_TYPE_PV, url));
	            	}
	            	
	            }
	            report.put(SHOW,showL);
	            if(CollectionUtils.isNotEmpty(track.getTk_clk())){
	              	for(String url:track.getTk_clk()){
	              		downL.add(this.parseMacro(QIHOO_REPORT_TYPE_CLK, url));
	            	}
	            	
	            }
	            report.put(DOWNLOAD_BEGIN, downL);
	            if(CollectionUtils.isNotEmpty( track.getTk_ins())){
	              	for(String url:track.getTk_ins()){
	              		instL.add(this.parseMacro(QIHOO_REPORT_TYPE_INSTALL, url));
	            	}
	            	
	            }
	            report.put(INSTALL, instL);
	            if(CollectionUtils.isNotEmpty(track.getTk_act())){
	              	for(String url:track.getTk_act()){
	              		activeL.add(this.parseMacro(QIHOO_REPORT_TYPE_ACTIVE, url));
	            	}
	            }
	        	report.put(ACTIVE, activeL);
	            
	            
	            appDisContent.setThirdReportLinks(report);
	            
	            appDisContent.setReportType(ReportType.REPORT_POST);

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
	private KeywordSearch360Track getKeyWordSearchTrack(String bindId,List<KeywordSearch360Track> tracks){
		KeywordSearch360Track returnTrack=null;
		for(KeywordSearch360Track track:tracks){
			if(bindId.equals(track.getBindid())){
				returnTrack= track;
				break;
			}
		}
		return returnTrack;
	}
	private AppDisRecomReply keyWordSearch(AppDisRecomReq req) throws AppDisException{
		QihooKeywordSearchHandler handler=(QihooKeywordSearchHandler)QihooInvokeHandlerFactory.getHandler(QihooMethodType.KEYWORD_SEARCH);
		return parseKeySearchResp(req,(KeywordSearch360Respose)handler.invok(req));

	}
	private AppDisRecomReply pkgSearch(AppDisRecomReq req) throws AppDisException{
		AppDisRecomReply reply=new AppDisRecomReply();
		
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
