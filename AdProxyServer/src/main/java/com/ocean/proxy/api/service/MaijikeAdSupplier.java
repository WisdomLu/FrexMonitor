package com.ocean.proxy.api.service;
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
import com.ocean.core.common.http.Bean2Utils;
import com.ocean.core.common.system.SystemContext;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
import com.ocean.core.common.threadpool.workthread.Task.DataFormat;
import com.ocean.core.common.threadpool.workthread.Task.InvokeType;
import com.ocean.persist.api.proxy.AdPullException;
import com.ocean.persist.api.proxy.maijike.MaijikeAd;
import com.ocean.persist.api.proxy.maijike.MaijikeAdPullReq;
import com.ocean.persist.api.proxy.maijike.MaijikeAdPullResp;
import com.ocean.persist.api.proxy.maijike.MaijikeUrlFmt;
import com.ocean.persist.common.AppDisConstants;
import com.ocean.persist.common.ProxyConstants;
import com.ocean.persist.model.proxy.DSPPosition;
import com.ocean.proxy.api.base.AbstractAsynAdSupplier;
import com.ocean.proxy.serverdis.AdProxyMaijikeTask;
import com.ocean.proxy.thrift.entity.AdContent;
import com.ocean.proxy.thrift.entity.AdImg;
import com.ocean.proxy.thrift.entity.AdMutiAction;
import com.ocean.proxy.thrift.entity.AdRecomReply;
import com.ocean.proxy.thrift.entity.AdRecomReq;
import com.ocean.proxy.thrift.entity.AdSpaceType;
import com.ocean.proxy.thrift.entity.AdUserInfo;
import com.ocean.proxy.thrift.entity.AdVid;
import com.ocean.proxy.thrift.entity.UserAdSpaceAttri;


@Component(value = "MaijikeAdSupplier")
public class MaijikeAdSupplier extends AbstractAsynAdSupplier {
    private static final  String  MJK_DX_A="AG_DX_A";
    private static final  String  MJK_DY_A="AG_DY_A";
    private static final  String  MJK_UX_A="AG_UX_A";
    private static final  String  MJK_UY_A="AG_UY_A";
    private static final  String  MJK_DX_R="AG_DX_R";
    private static final  String  MJK_DY_R="AG_DY_R";
    private static final  String  MJK_UX_R="AG_UX_R";
    private static final  String  MJK_UY_R="AG_UY_R";
    private static final  String  MJK_TIME_S="AG_TIME_S";
    private static final  String  MJK_VIDEO_PROGRESS="\\$\\{PROGRESS\\}";
    private static final  String  MJK_WEBVIEW_USERAGENT ="WEBVIEW_USERAGENT";

    
	public AdRecomReply invoke(InvokeAttribute attribute) throws AdPullException {
		// 参数转换
		AdRecomReq adreq = attribute.getAdreq();
		MaijikeAdPullReq params = parseParams(attribute,adreq.getUserAdSpaceAttri().getSpaceType());
		// 调用API
		MaijikeAdPullResp response = (MaijikeAdPullResp) invoke(params, MaijikeAdPullResp.class, adreq.getHash(), String.valueOf(adreq.getUserAdSpaceAttri().getAdSpaceId()));
		// 解析结果
		return parseResult(response,adreq.getUserinfo().getUa());
	}

	public AdRecomReply parseResult(MaijikeAdPullResp resp,String ua) throws AdPullException {
		if (resp == null||CollectionUtils.isEmpty(resp.getAds())) {
			return null;
		}
		if (!"1".equals(resp.getRes())) {
			throw new AdPullException("ad request error,error code"+resp.getRes());
			
		}
		AdRecomReply adresp = new AdRecomReply();
		adresp.setStatus(status);
		adresp.setAd_contents(dealAds(resp.getAds(),ua));
		return adresp;
	}
	private List<AdContent>  dealAds(List<MaijikeAd> ads,String ua) throws AdPullException{
		List<AdContent> list=new ArrayList<AdContent> ();
		
		for(MaijikeAd ad:ads){
			//***********************BEGIN 常规设置*************
			AdContent content = new AdContent();
			AdMutiAction action = new AdMutiAction();
			String title = defTitle;
			String marketTitle=defTitle;
			// 内容类型1表示链接推广,2表示应用推广,3未使用 4表示电话推广
	        int acType=ACTION_WEB;
	       //***********************END 常规设置*************
	        //图片
	        List<AdImg> imgs = new ArrayList<AdImg>();
			//上报
	        Map<String, List<String>> map = new HashMap<String, List<String>>();
	        if(StringUtils.isNotEmpty(ad.getHtml())){
	        	content.setIsHtmlAd(true);
	        	content.setHtmlSnippet(ad.getHtml());
	        }else{
	      
		        if(ad.getInteraction_type()==2||ad.getInteraction_type()==4){
		        	acType=ACTION_APP;
		        }

		        if(StringUtils.isNotEmpty(ad.getImage_url())){
		            AdImg img=new AdImg();
		            img.setSrc(ad.getImage_url());
		            imgs.add(img);
		
			    }
		        MaijikeUrlFmt  urlFmt=ad.getClick_url();
		        if(urlFmt!=null&&StringUtils.isNotEmpty(urlFmt.getUrl())){
		        	String link=ad.getClick_url().getUrl();
	        	    String url=format(REPORT_TYPE_REQ,link,ua);
		        	content.setLinkurl(url);
					action.setLinkurl(url);

		        }
		        if(StringUtils.isNotEmpty(ad.getDeeplink())){
		        	action.setActiveUri(ad.getDeeplink());
		        	if(ad.getFallback_type()==1){
		        		acType=ACTION_WEB;
		        	}else{
		        		acType=ACTION_APP;
		        	}
		        }
		        
		        
		        if(StringUtils.isNotEmpty(ad.getTitle())){
		        	title=ad.getTitle();
		        }
		        if(StringUtils.isNotEmpty(ad.getPkgname())){
		        	action.setCpPackage(ad.getPkgname());
			        
		        }

		        
		        //视频广告
		        if(ad.getCreative_type()==4){
		        	acType=ACTION_VIDEO;
					AdVid rpcVideo = new AdVid();
					
					if(StringUtils.isNotEmpty(ad.getVideo_duration())) {
						rpcVideo.setDuration(Integer.parseInt(ad.getVideo_duration()));
					}
					rpcVideo.setSrc(ad.getVideo_url());
					if(ad.getWidth()>0) {
						rpcVideo.setWidth(ad.getWidth());
					}
					if(ad.getHeight()>0) {
						rpcVideo.setHeight(ad.getHeight());
					}
					int lastIndex = rpcVideo.getSrc().lastIndexOf(".");
					if (lastIndex < 0) {
						rpcVideo.setFormat("unknow");
					} else {
						rpcVideo.setFormat(rpcVideo.getSrc().substring(lastIndex));
					}
					rpcVideo.setImg_src(ad.getImage_url());
					content.setAdVid(rpcVideo);
		        }
	        }
	        
	        //上报处理
	        List<String> showReport=new ArrayList<String>();

	        if(CollectionUtils.isNotEmpty(ad.getImpression_log_url())){
	        	for(MaijikeUrlFmt urlFmt:ad.getImpression_log_url()){
	        	 	showReport.add(format(REPORT_TYPE_PV,urlFmt.getUrl(),ua));
	        	}
	        }    	       
	     	map.put(SHOW, showReport);
	     	List<String> clickReport=new ArrayList<String>();
	        if(CollectionUtils.isNotEmpty(ad.getClick_monitor_url())){
	        	for(MaijikeUrlFmt urlFmt:ad.getClick_monitor_url()){
	        		clickReport.add(format(REPORT_TYPE_CLICK,urlFmt.getUrl(),ua));
	        	}
	        	
	        }
	    
	        if(CollectionUtils.isNotEmpty(ad.getDeeplink_click())){
	        	for(MaijikeUrlFmt urlFmt:ad.getDeeplink_click()){
	        		clickReport.add(format(REPORT_TYPE_CLICK,urlFmt.getUrl(),ua));
	        	}
	        	
	        }
	        map.put(CLICK, clickReport);
    		if(CollectionUtils.isNotEmpty(ad.getDown_start_url())){
    			List<String> report=new ArrayList<String>();
    			for(MaijikeUrlFmt urlFmt:ad.getDown_start_url()){
    				report.add(format(REPORT_TYPE_DOWNLOAD,urlFmt.getUrl(),ua));
    			}
    			map.put(START_DOWN, report);
    		}
    		if(CollectionUtils.isNotEmpty(ad.getDown_complete_url())){
    			List<String> report=new ArrayList<String>();
    			for(MaijikeUrlFmt urlFmt:ad.getDown_complete_url()){
    				report.add(format(REPORT_TYPE_DOWNLOAD,urlFmt.getUrl(),ua));
    			}
    			map.put(DOWNLOAD, report);
    		}
    		if(CollectionUtils.isNotEmpty(ad.getInstall_start_url())){
    			List<String> report=new ArrayList<String>();
    			for(MaijikeUrlFmt urlFmt:ad.getInstall_start_url()){
    				report.add(format(REPORT_TYPE_INSTALL,urlFmt.getUrl(),ua));
    			}
    			map.put(START_INSTALL, report);
    		}
    		if(CollectionUtils.isNotEmpty(ad.getInstall_complete_url())){
    			List<String> report=new ArrayList<String>();
    			for(MaijikeUrlFmt urlFmt:ad.getInstall_complete_url()){
    				report.add(format(REPORT_TYPE_INSTALL,urlFmt.getUrl(),ua));
    			}
    			map.put(INSTALL, report);
    		}
    		if(CollectionUtils.isNotEmpty(ad.getStart_app_url())){
    			List<String> report=new ArrayList<String>();
    			for(MaijikeUrlFmt urlFmt:ad.getStart_app_url()){
    				report.add(format(REPORT_TYPE_ACTIVE,urlFmt.getUrl(),ua));
    			}
    			map.put(ACTIVE, report);
    		}
    		
    		//视频广告上报
    		if(ad.getCreative_type()==4){
        		if(CollectionUtils.isNotEmpty(ad.getVideo_ad_start_card_click())){
        			List<String> report=new ArrayList<String>();
        			for(MaijikeUrlFmt urlFmt:ad.getVideo_ad_start_card_click()){
        				report.add(this.format(REPORT_VIDEO_CLICK, urlFmt.getUrl(), ua));
        			}
        			map.put(VIDEO_CLICK,report);
        		}
        		if(CollectionUtils.isNotEmpty(ad.getVideo_ad_start())){
        			List<String> report=new ArrayList<String>();
        			for(MaijikeUrlFmt urlFmt:ad.getVideo_ad_start()){
        				report.add(this.format(REPORT_VIDEO_START, urlFmt.getUrl(), ua));
        			}
        			map.put(VIDEO_START,report);
        		}
        		if(CollectionUtils.isNotEmpty(ad.getVideo_ad_full_screen())){
        			List<String> report=new ArrayList<String>();
        			for(MaijikeUrlFmt urlFmt:ad.getVideo_ad_full_screen()){
        				report.add(this.format(REPORT_VIDEO_FULL_SCREEN, urlFmt.getUrl(), ua));
        			}
        			map.put(VIDEO_FULL_SCREEN,report);
        		}
        		if(CollectionUtils.isNotEmpty(ad.getAd_close_monitor())){
        			List<String> report=new ArrayList<String>();
        			for(MaijikeUrlFmt urlFmt:ad.getAd_close_monitor()){
        				report.add(this.format(REPORT_VIDEO_SKIP, urlFmt.getUrl(), ua));
        			}
        			map.put(VIDEO_SKIP,report);
        		}
        		
        		if(CollectionUtils.isNotEmpty(ad.getVideo_ad_end())){
        			List<String> report=new ArrayList<String>();
        			for(MaijikeUrlFmt urlFmt:ad.getVideo_ad_end()){
        				report.add(this.format(REPORT_VIDEO_END, urlFmt.getUrl(), ua));
        			}
        			map.put(VIDEO_END,report);
        		}
    		}
    		
    		
			content.setThirdReportLinks(map);
			//img
			content.setImglist(imgs);
		    action.setType(acType); 
			content.setMarketTitle(marketTitle);
			content.setGuideTitle(title);
			content.setMutiAction(Collections.singletonList(action));
			list.add(content);
		}
		return list;
	}

	
	private  String format(int type,String str,	String ua){
		if(type!=REPORT_TYPE_PV){
			str=str.replaceAll(MJK_DX_A, MACRO_DOWN_X).replaceAll(MJK_DY_A, MACRO_DOWN_Y)
					.replaceAll(MJK_UX_A, MACRO_UP_X).replaceAll(MJK_UY_A,MACRO_UP_Y)
					.replaceAll(MJK_DX_R, MACRO_DOWN_X).replaceAll(MJK_DY_R, MACRO_DOWN_Y)
					.replaceAll(MJK_UX_R, MACRO_UP_X).replaceAll(MJK_UY_R,MACRO_UP_Y);
		}
		str= str.replaceAll(MJK_TIME_S, String.valueOf(System.currentTimeMillis())).replaceAll(MJK_WEBVIEW_USERAGENT,ua)
				.replaceAll(MJK_VIDEO_PROGRESS, MACRO_VIDEO_PROGRESS);
		
		
		return str;
	}

	public MaijikeAdPullReq parseParams(InvokeAttribute attribute,AdSpaceType spaceType) throws AdPullException {
		DSPPosition posInfo = attribute.getDspPosition();
		if (StringUtils.isEmpty(posInfo.getText1())||StringUtils.isEmpty(posInfo.getText2())) {
			throw new AdPullException("app_id/video flag  is empty!");
		}

		AdRecomReq adreq = attribute.getAdreq();
		AdUserInfo  user=adreq.getUserinfo();
		UserAdSpaceAttri  attr=adreq.getUserAdSpaceAttri();
		MaijikeAdPullReq param = new MaijikeAdPullReq();
		param.setSid(Integer.parseInt(posInfo.getPos()));
		param.setAid(Integer.parseInt(posInfo.getText1()));
		if(posInfo.getText2().equals(FLAG_VIDEO)){
			param.setIsVideo(FLAG_VIDEO);
		}
		
		
		param.setAdtype(getAdType(spaceType));
		param.setImsi(user.getImsi());
		param.setMac(user.getMac());
		param.setAdrid(user.getAdid());
		param.setOpid(getOp(user.getMobile()));
		param.setImei(user.getImei());
		param.setOsv(user.getOsversion());
		param.setDv(user.getBrand_name());
		try {
			param.setDm(URLEncoder.encode(user.getPhonemodel(),CHARSET_CODER));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		param.setNt(this.getNetwork(adreq.getNet()));
		param.setAdnum(adreq.getResult_num());
		param.setSh(user.getDevice_height());
		param.setSw(user.getDevice_width());
		param.setW(attr.getSpaceWidth());
		param.setH(attr.getSpaceHeight());
		if(StringUtils.isNotEmpty(user.getLat())&&StringUtils.isNotEmpty(user.getLon())){
			param.setLat(Double.parseDouble(user.getLat()));
			param.setLon(Double.parseDouble(user.getLon()));
		}
		param.setUseragent(user.getUa());
		param.setIp(user.getClient_ip());
		param.setDensity(user.getDip());
		param.setDpi(user.getDensity());
		param.setTm(System.currentTimeMillis());
		if(StringUtils.isNotEmpty(user.getOs_api_level())){
			param.setOsapilevel(Integer.parseInt(user.getOs_api_level()));
		}
		param.setSn(user.getSn());
		if(user.isSetCid()&&user.isSetLac()){
			param.setLac(String.valueOf(user.getLac()));
			param.setCellularid(String.valueOf(user.getCid()));
		}

		param.setVersionname(adreq.getVersion());
		param.setVersioncode(getAppVC(adreq.getVersion()));
		
		return param;
	}
	private String getAppVC(String version){
		if(StringUtils.isEmpty(version)){
			return "20";
		}
		String[] vArr=version.split(".");
		try{
			return vArr[vArr.length-1];
		}catch(Exception e){
			
		}
		return "20";
	}
	private int getAdType(AdSpaceType spaceType){
		switch(spaceType){
		    case BANNER:
		    	return 1;
		    case INTERSTITIAL:
		    	return 2;
		    case OPENING:
		    	return 3;
		    default:
		    	return 3;
		
		}
	}
	
	private  int getNetwork(String net){	
		if(NETWORK_WIFI.equals(net)){
			return 2;
		}else if(NETWORK_2G.equals(net)){
			return 3;
		}
		else if(NETWORK_3G.equals(net)){
			return 4;
		}
		else if(NETWORK_4G.equals(net)){
			return 6;
		}else if(NETWORK_5G.equals(net)){
			return 6;
		}
		return 0;
	}
	private String  getOp(String mobile){
		if(MOBILE_CMCC.equals(mobile)){
			return "46000";
		}else if(MOBILE_CUCC.equals(mobile)){
			return "46001";
		}else if(MOBILE_CTCC.equals(mobile)){
			return "46003";
		}
		return "";
	}
	private boolean checkId(String id){
		String videoIdL=SystemContext.getDynamicPropertyHandler().get(ProxyConstants.MAIJIKE_VIDEO_IDS);
		if(StringUtils.isNotEmpty(videoIdL)){
			String[] videoIdArr=videoIdL.split(";");
			for(String videoid:videoIdArr){
				if(videoid.equals(id)){
					return true;
				}
			}
		}
		return false;
	}
	@Override
	public AsynAbstractTask packageTask(Parameter params, String hashCode) {
		AdProxyMaijikeTask task = new AdProxyMaijikeTask();
		MaijikeAdPullReq param = (MaijikeAdPullReq) params;
		
		StringBuilder buff=new StringBuilder();
		//if(checkId(String.valueOf(param.getSid()))){
		if(StringUtils.isNotEmpty(param.getIsVideo())&&FLAG_VIDEO.equals(param.getIsVideo())){
			buff.append(SystemContext.getDynamicPropertyHandler().get(ProxyConstants.MAIJIKE_VIDEO_URL));
			param.setIsVideo(null);
		}else{
			buff.append(SystemContext.getDynamicPropertyHandler().get(ProxyConstants.MAIJIKE_URL));
		}
		
		//StringBuilder buff=new StringBuilder(SystemContext.getDynamicPropertyHandler().get(ProxyConstants.MAIJIKE_URL));
		buff.append("?").append(Bean2Utils.toHttpParams(param)).append("&zkrequestId=").append(hashCode);
		task.setParam(param);
		task.setDataFormat(DataFormat.DATA_FORMAT_KVP);
		task.setInvokeType(InvokeType.INVOKE_TYPE_GET);
		
		
		task.setParam(param);
		task.setHashCode(hashCode);
		task.setUrl(buff.toString());
		//task.setHeaders(headers);
		return task;
	}
}
