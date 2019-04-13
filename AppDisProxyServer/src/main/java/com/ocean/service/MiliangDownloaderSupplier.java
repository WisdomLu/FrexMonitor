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
import com.ocean.app.dis.proxy.thrift.entity.AppImg;
import com.ocean.app.dis.proxy.thrift.entity.AppInfo;
import com.ocean.app.dis.proxy.thrift.entity.AppType;
import com.ocean.app.dis.proxy.thrift.entity.ReportType;
import com.ocean.core.common.system.ErrorCode;
import com.ocean.handler.base.AbstractAppDisSupplier;
import com.ocean.persist.app.dis.AppDisException;
import com.ocean.persist.app.dis.AppDisResponse;
import com.ocean.persist.app.dis.miliang.listSearch.MiliangAd;
import com.ocean.persist.app.dis.miliang.listSearch.MiliangListSchReq;
import com.ocean.persist.app.dis.miliang.listSearch.MiliangListSchResp;
import com.ocean.persist.app.dis.miliang.listSearch.MiliangTrack;
import com.ocean.service.base.CommonMethodType;
import com.ocean.service.miliang.MiliangInvokeHandlerFactory;
import com.ocean.service.miliang.MiliangListSearchHandler;
@Component(value="MiliangDownloaderSupplier")
public class MiliangDownloaderSupplier   extends AbstractAppDisSupplier{
    private static final String ML_EVENT_TIME_START="__EVENT_TIME_START__";//     string
   // 开始时间 ACTION_DOWN 触发时间（13位时间戳）
    private static final String ML_EVENT_TIME_END="__EVENT_TIME_END__";											  
   // 结束时间 ACTION_UP 触发时间（13位时间戳）  （曝光的开始和结束时间不可相同）
    private static final String ML_OFFSET_X="__OFFSET_X__";
   // 点击位置距离整个广告位左边距 坐标不能固定
    private static final String ML_OFFSET_Y="__OFFSET_Y__";  
    private static final String ML_LON="__LONGITUDE__";
    private static final String ML_LAT="__LATITUDE__";
    
	public final static int ML_REPORT_TYPE_PV=1;
	public final static int ML_REPORT_TYPE_CLK=2;
	public final static int ML_REPORT_TYPE_DOWNLOAD=3;
	public AppDisRecomReply invoke(AppDisRecomReq params)
			throws AppDisException {
		// TODO Auto-generated method stub
		switch(params.getInterType()){
				case APP_LIST_SEARCH:
					return appListSearch(params);
				default:
				    throw new AppDisException(ErrorCode.PARAM_ERROR,"can not find the mapping interface:"+params.getInterType().name());
		        
		}
	}
	private AppDisRecomReply appListSearch(AppDisRecomReq req) throws AppDisException{
		MiliangListSearchHandler handler=(MiliangListSearchHandler)MiliangInvokeHandlerFactory.getHandler(CommonMethodType.APP_LIST_ASYN_SEARCH);

		return parseAppListSearchResp(req,handler);

	}

	
	private AppDisRecomReply  parseAppListSearchResp(AppDisRecomReq req,MiliangListSearchHandler handler) throws AppDisException{
		MiliangListSchReq request=handler.parseListSearchParam(req);
		MiliangListSchResp reply=(MiliangListSchResp)handler.invok(req,request);
		
		if(reply==null||reply.getAds()==null||CollectionUtils.isEmpty(reply.getAds())){
			return null;
		}
		if(!"1".equals(reply.getRes())){
			throw new AppDisException(ErrorCode.INTER_ERROR,"return error code:"+reply.getRes()+" msg:"+reply.getMsg());
		}

		//返回值封装
		AppDisRecomReply proxyReply=new AppDisRecomReply();
		proxyReply.setErrorCode(com.ocean.app.dis.proxy.thrift.entity.ErrorCode.RC_SUCCESS);
		List<AppDisContent> appL=new ArrayList<AppDisContent>();
		for(MiliangAd item:reply.getAds()){
			if(item.getInteraction_type()!=3){
				continue;
			}
			AppDisContent appDisContent=new AppDisContent();
			appDisContent.setApkUrl(item.getClick_url().getUrl());
			appDisContent.setIsAd(1);
			AppInfo app=new AppInfo();
			app.setAppName(item.getTitle());	//item.setAppName(item.getTitle());
			if(StringUtils.isNotEmpty(item.getTitle())){
				app.setDesc(item.getDesc());
			}
			app.setIconUrl(item.getIcon_url());
			List<AppImg> imgList=new ArrayList<AppImg>();
			if(StringUtils.isNotEmpty(item.getImage_url())){
				AppImg img=new AppImg();
				img.setSrc(item.getImage_url());
				imgList.add(img);
				appDisContent.setImglist(imgList);
			}
			
			app.setPkgName(item.getPkgname());
			app.setType(AppType.TYPE_APP);
			appDisContent.setAppInfo(app);
			//设置上报用字段
            Map<String,List<String>> report=new HashMap<String,List<String>>();
            if(CollectionUtils.isNotEmpty(item.getImpression_log_url())){
            	List<String> list=new ArrayList<String>();
            	for(MiliangTrack track:item.getImpression_log_url()){
            		list.add(track.getUrl());
            	}
            	report.put(SHOW, list);
            }
            List<String> downstartL=new ArrayList<String>();
            if(CollectionUtils.isNotEmpty(item.getClick_monitor_url())){
            	for(MiliangTrack track:item.getClick_monitor_url()){
            		downstartL.add(this.parseMacro(ML_REPORT_TYPE_CLK, track.getUrl(), req.getUserInfo().getLon(), req.getUserInfo().getLat()));
            	}
            }
            if(CollectionUtils.isNotEmpty(item.getDownload_start_url())){
            	for(MiliangTrack track:item.getDownload_start_url()){
            		downstartL.add(track.getUrl());
            	}
            	
            }
            report.put(DOWNLOAD_BEGIN, downstartL);
            if(CollectionUtils.isNotEmpty(item.getDownload_complete_url())){
            	List<String> list=new ArrayList<String>();
            	for(MiliangTrack track:item.getDownload_complete_url()){
            		list.add(track.getUrl());
            	}
            	report.put(DOWNLOAD_END,list);
            }
            List<String> installL=new ArrayList<String>();
            if(CollectionUtils.isNotEmpty(item.getInstall_start_url())){
            	for(MiliangTrack track:item.getInstall_start_url()){
            		installL.add(track.getUrl());
            	}
            }
            if(CollectionUtils.isNotEmpty(item.getInstall_complete_url())){
            	for(MiliangTrack track:item.getInstall_complete_url()){
            		installL.add(track.getUrl());
            	}
            }
            report.put(INSTALL,installL);
            if(CollectionUtils.isNotEmpty(item.getStart_app_url())){
            	List<String> list=new ArrayList<String>();
            	for(MiliangTrack track:item.getStart_app_url()){
            		list.add(track.getUrl());
            	}
            	report.put(ACTIVE, list);
            }
            appDisContent.setThirdReportLinks(report);
            appDisContent.setReportType(ReportType.REPORT_GET);
			appL.add(appDisContent);
		}
	
		proxyReply.setAppDisContent(appL);
		return proxyReply;
	}
	private  String parseMacro(int type,String str,String lon,String lat){
		if(type!=ML_REPORT_TYPE_PV){
			str=str.replaceAll(ML_OFFSET_X, MACRO_DOWN_X)
					  .replaceAll(ML_OFFSET_Y,MACRO_DOWN_Y);
			
		}
		str=str.replaceAll(ML_EVENT_TIME_START, MACRO_EVENT_TIME_START)
				  .replaceAll(ML_EVENT_TIME_END,MACRO_EVENT_TIME_END)
				  .replaceAll(ML_LON, lon)
				  .replaceAll(ML_LAT, lat);
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
