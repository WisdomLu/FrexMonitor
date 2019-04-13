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
import com.ocean.persist.app.dis.wanyujx.pkgsearch.WanyujxPkgSchAsset;
import com.ocean.persist.app.dis.wanyujx.pkgsearch.WanyujxPkgSchReq;
import com.ocean.persist.app.dis.wanyujx.pkgsearch.WanyujxPkgSchResp;
import com.ocean.persist.app.dis.wanyujx.pkgsearch.WanyujxPkgSchRespApp;
import com.ocean.service.wanyujxHandler.WanyujxPkgSchHandler;
import com.ocean.service.wanyujxHandler.base.WanyujxHandlerFactory;
import com.ocean.service.wanyujxHandler.base.WanyujxMethodType;
@Component(value="WanyujxDownloaderSupplier")
public class WanyujxDownloaderSupplier   extends AbstractAppDisSupplier{
	
	private static final String WANYUJX_DOWN_X="\\{DOWNX\\}";
	private static final String WANYUJX_DOWN_Y="\\{DOWNY\\}";
	
	private static final String WANYUJX_UP_X="\\$\\{up_x\\}";
	private static final String WANYUJX_UP_Y="\\$\\{up_y\\}";

	
	private static final String WANYUJX_SBZMX="\\{REQHEIGHT\\}";
	private static final String WANYUJX_SBZMY="\\{REQWIDTH\\}";
	
	private static final String WANYUJX_REAL_SBZMX="\\{HEIGHT\\}";
	private static final String WANYUJX_REAL_SBZMY="\\{WIDTH\\}";

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
		WanyujxPkgSchHandler handler=(WanyujxPkgSchHandler)WanyujxHandlerFactory.getHandler(WanyujxMethodType.PKG_SEARCH);
		
		return parsePkgSearchResp(req,handler);

	}
	private AppDisRecomReply  parsePkgSearchResp(AppDisRecomReq req,WanyujxPkgSchHandler handler) throws AppDisException{
		WanyujxPkgSchReq request=handler.parsePkgParam(req);
		WanyujxPkgSchResp reply=(WanyujxPkgSchResp)handler.invok(req,request);
		
		if(reply==null||CollectionUtils.isEmpty(reply.getAdInfos())){
			return null;
		}
		if(reply.getCode()!=0){
			throw new AppDisException(ErrorCode.INTER_ERROR,"return error code:"+reply.getCode());
		}

		//返回值封装
		AppDisRecomReply proxyReply=new AppDisRecomReply();
		proxyReply.setErrorCode(com.ocean.app.dis.proxy.thrift.entity.ErrorCode.RC_SUCCESS);
		List<AppDisContent> appL=new ArrayList<AppDisContent>();
		for(WanyujxPkgSchRespApp item:reply.getAdInfos()){
			AppDisContent appDisContent=new AppDisContent();
			///appDisContent.setApkId(item.getApkId());
			//appDisContent.setApkMd5(item.getMd5());
			appDisContent.setApkUrl(item.getActionUrl());
			//appDisContent.setAppId(item.getAppId());
			//appDisContent.setCategoryId(String.valueOf(item.getCategoryId()));
			//appDisContent.setCategoryName(item.getCategoryName());
			appDisContent.setContent(item.getSummary());
			//appDisContent.setInstalledCount(item.getAppDownCount());
/*				if(StringUtils.isNotEmpty(item.getTotalDownloadTimes())){
				appDisContent.setInstalledCount(Integer.parseInt(item.getTotalDownloadTimes()));
			}
			*/
			//appDisContent.setSignMd5(item.getSequence());
			appDisContent.setIsAd(1);

			
			
			AppInfo app=new AppInfo();
			app.setAppName(item.getTitle());	//item.setAppName(item.getTitle());
		
			app.setIconUrl(item.getIconUrl());
			//app.setMinSdkVersion(String.valueOf(item.getMinSdkVersion()));
			///app.setPkgName(item.getPackageName());
			app.setPkgName(item.getPackageName());
			
			app.setType(AppType.TYPE_APP);
			//app.setVersionCode(item.getVersionCode());
			//app.setFileSize(item.getBytes());
			//app.setVersionName(item.getVersionName());
			appDisContent.setAppInfo(app);
			
			
			List<AppImg> imgL=new ArrayList<AppImg>();
			List<WanyujxPkgSchAsset> assetL=item.getAssets();
			for(WanyujxPkgSchAsset asset:assetL){
				AppImg img=new AppImg();
				img.setSrc(asset.getUrl());
				imgL.add(img);
			}
			appDisContent.setImglist(imgL);
			
			//设置上报用字段
            Map<String,List<String>> report=new HashMap<String,List<String>>();
            if(CollectionUtils.isNotEmpty(item.getViewMonitorUrls())){
            	   report.put(SHOW, item.getViewMonitorUrls());
            }
            List<String> clickL=new ArrayList<String>();
            if(CollectionUtils.isNotEmpty(item.getClickMonitorUrls())){
            	for(String url:item.getClickMonitorUrls()){
            		clickL.add(this.format(url,req.getAppSpaceInfo().getSpaceHeight(), req.getAppSpaceInfo().getSpaceWidth()));
            	}
         	   report.put(CLICK, clickL);
            }
            if(CollectionUtils.isNotEmpty(item.getStartDownloadMonitorUrls())){
          	   report.put(DOWNLOAD_BEGIN, item.getStartDownloadMonitorUrls());
             }
            if(CollectionUtils.isNotEmpty(item.getFinishDownloadMonitorUrls())){
          	   report.put(DOWNLOAD_END, item.getFinishDownloadMonitorUrls());
             }
            List<String> installL=new ArrayList<String>();
            if(CollectionUtils.isNotEmpty(item.getStartInstallMonitorUrls())){
            	installL.addAll(item.getStartInstallMonitorUrls());
             }
            if(CollectionUtils.isNotEmpty(item.getFinishInstallMonitorUrls())){
            	installL.addAll(item.getFinishInstallMonitorUrls());
             }
      
            report.put(INSTALL,installL);
            appDisContent.setThirdReportLinks(report);
            
            appDisContent.setReportType(ReportType.REPORT_GET);
			appL.add(appDisContent);
		}
		
		proxyReply.setAppDisContent(appL);
		
		
		return proxyReply;
	}
	private  String format(String str,int height,int width){
		String format= str.replaceAll(WANYUJX_DOWN_X, MACRO_DOWN_X).replaceAll(WANYUJX_DOWN_Y, MACRO_DOWN_Y)
				.replaceAll(WANYUJX_UP_X, MACRO_UP_X).replaceAll(WANYUJX_UP_Y,MACRO_UP_Y)

		        .replaceAll(WANYUJX_SBZMX, String.valueOf(height)).replaceAll(WANYUJX_SBZMY,String.valueOf(width))
		        .replaceAll(WANYUJX_REAL_SBZMX, MACRO_REL_HEIGHT).replaceAll(WANYUJX_REAL_SBZMY,MACRO_REL_WIDTH);
		return format;
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
