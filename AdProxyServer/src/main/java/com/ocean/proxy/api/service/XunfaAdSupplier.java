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
import com.ocean.core.common.system.SystemContext;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
import com.ocean.core.common.threadpool.workthread.Task.DataFormat;
import com.ocean.core.common.threadpool.workthread.Task.InvokeType;
import com.ocean.persist.api.proxy.AdPullException;
import com.ocean.persist.api.proxy.xunfa.XunfaAd;
import com.ocean.persist.api.proxy.xunfa.XunfaAdPullReq;
import com.ocean.persist.api.proxy.xunfa.XunfaAdPullResp;
import com.ocean.persist.api.proxy.xunfa.XunfaImg;
import com.ocean.persist.common.ProxyConstants;
import com.ocean.persist.model.proxy.DSPPosition;
import com.ocean.proxy.api.base.AbstractAsynAdSupplier;
import com.ocean.proxy.serverdis.AdProxyXunfaTask;
import com.ocean.proxy.thrift.entity.AdContent;
import com.ocean.proxy.thrift.entity.AdImg;
import com.ocean.proxy.thrift.entity.AdMutiAction;
import com.ocean.proxy.thrift.entity.AdRecomReply;
import com.ocean.proxy.thrift.entity.AdRecomReq;
import com.ocean.proxy.thrift.entity.AdSpaceType;
import com.ocean.proxy.thrift.entity.AdUserInfo;
@Component(value = "XunfaAdSupplier")
public class XunfaAdSupplier extends AbstractAsynAdSupplier {
    private static final  String  XF_DOWN_X="IT_CLK_PNT_DOWN_X";
    private static final  String  XF_DOWN_Y="IT_CLK_PNT_DOWN_Y";
    private static final  String  XF_UP_X="IT_CLK_PNT_UP_X";
    private static final  String  XF_UP_Y="IT_CLK_PNT_UP_Y";
    private static final  String  XF_TS="IT_TS";
    private static final  String  XF_IMEI="IT_IMEI";
    private static final  String  XF_CLICK_ID="__CLICK_ID__";

	public AdRecomReply invoke(InvokeAttribute attribute) throws AdPullException {
		// 参数转换
		AdRecomReq adreq = attribute.getAdreq();
		XunfaAdPullReq params = parseParams(attribute,adreq.getUserAdSpaceAttri().getSpaceType());
		// 调用API
		XunfaAdPullResp response = (XunfaAdPullResp) invoke(params, XunfaAdPullResp.class, adreq.getHash(), String.valueOf(adreq.getUserAdSpaceAttri().getAdSpaceId()));
		// 解析结果
		return parseResult(response,adreq.getUserinfo().getImei());
	}

	public AdRecomReply parseResult(XunfaAdPullResp resp,String imei) throws AdPullException {
		if (resp == null||CollectionUtils.isEmpty(resp.getData())) {
			return null;
		}
		if (0!=resp.getRes()) {
			throw new AdPullException("ad request error,error code"+resp.getRes());
			
		}
		AdRecomReply adresp = new AdRecomReply();
		adresp.setStatus(status);
		adresp.setAd_contents(dealAds(resp.getData(),imei));
		return adresp;
	}
	private List<AdContent>  dealAds(List<XunfaAd> ads,String imei) throws AdPullException{
		List<AdContent> list=new ArrayList<AdContent> ();
		
		for(XunfaAd ad:ads){
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
	
	      
	        if("download".equals(ad.getAdtype())){
	        	acType=ACTION_APP;
	        }else if("redownload".equals(ad.getAdtype())){
	        	acType=ACTION_APP;
	        	action.setLinkurl_type(1);
	        }

	        if(CollectionUtils.isNotEmpty(ad.getImgs())){
	        	for(XunfaImg xfImg:ad.getImgs()){
	        		AdImg img=new AdImg();
	  	            img.setSrc(xfImg.getUrl());
	  	            imgs.add(img);
	        	}
		    }
	        if(StringUtils.isNotEmpty(ad.getLanding_url())){
        	    String url=format(REPORT_TYPE_REQ,ad.getLanding_url(),imei);
	        	content.setLinkurl(url);
				action.setLinkurl(url);

	        }
/*	        if(StringUtils.isNotEmpty(ad.getDeep_link())){
	        	action.setActiveUri(ad.getDeep_link());
	        }*/
	        if(StringUtils.isNotEmpty(ad.getTitle())){
	        	title=ad.getTitle();
	        }
	        if(StringUtils.isNotEmpty(ad.getPn())){
	        	action.setCpPackage(ad.getPn());
		        
	        }
	        //上报处理
	        List<String> showReport=new ArrayList<String>();

	        if(CollectionUtils.isNotEmpty(ad.getImpr())){
	        	for(String url:ad.getImpr()){
	        	 	showReport.add(format(REPORT_TYPE_PV,url,imei));
	        	}
	        }    	       
	     	map.put(SHOW, showReport);
	     	List<String> clickReport=new ArrayList<String>();
	        if(CollectionUtils.isNotEmpty(ad.getClick())){
	        	for(String url:ad.getClick()){
	        		clickReport.add(format(REPORT_TYPE_CLICK,url,imei));
	        	}
	        	map.put(CLICK, clickReport);
	        }
	    
    		if(CollectionUtils.isNotEmpty(ad.getInst_downstart())){
    			List<String> report=new ArrayList<String>();
    			for(String url:ad.getInst_downstart()){
    				report.add(format(REPORT_TYPE_DOWNLOAD,url,imei));
    			}
    			map.put(START_DOWN, report);
    		}
    		if(CollectionUtils.isNotEmpty(ad.getInst_downsucc())){
    			List<String> report=new ArrayList<String>();
    			for(String url:ad.getInst_downsucc()){
    				report.add(format(REPORT_TYPE_DOWNLOAD,url,imei));
    			}
    			map.put(DOWNLOAD, report);
    		}
    		if(CollectionUtils.isNotEmpty(ad.getInst_installstart())){
    			List<String> report=new ArrayList<String>();
    			for(String url:ad.getInst_installstart()){
    				report.add(format(REPORT_TYPE_INSTALL,url,imei));
    			}
    			map.put(START_INSTALL, report);
    		}
    		if(CollectionUtils.isNotEmpty(ad.getInst_installs())){
    			List<String> report=new ArrayList<String>();
    			for(String url:ad.getInst_installs()){
    				report.add(format(REPORT_TYPE_INSTALL,url,imei));
    			}
    			map.put(INSTALL, report);
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

	
	private  String format(int type,String str,	String imei){
		if(type!=REPORT_TYPE_PV){
			str=str.replaceAll(XF_DOWN_X, MACRO_DOWN_X).replaceAll(XF_DOWN_Y, MACRO_DOWN_Y)
					.replaceAll(XF_UP_X, MACRO_UP_X).replaceAll(XF_UP_Y,MACRO_UP_Y);
		}
		String format= str.replaceAll(XF_TS, String.valueOf(System.currentTimeMillis()))
						  .replaceAll(XF_IMEI,imei)
						  .replaceAll(XF_CLICK_ID,MACRO_CLICK_ID);

		return format;
	}

	public XunfaAdPullReq parseParams(InvokeAttribute attribute,AdSpaceType spaceType) throws AdPullException {
		DSPPosition posInfo = attribute.getDspPosition();
		if (StringUtils.isEmpty(posInfo.getText1())||StringUtils.isEmpty(posInfo.getText3())) {
			throw new AdPullException("app_id/pkgname  is empty!");
		}

		AdRecomReq adreq = attribute.getAdreq();
		AdUserInfo  user=adreq.getUserinfo();
		XunfaAdPullReq param = new XunfaAdPullReq();
		param.setAppid(posInfo.getText1());
		param.setAdunitid(posInfo.getPos());
		param.setAppn(posInfo.getText3());
		param.setAppv(adreq.getVersion());
		String os = user.getOs();
		if(OS_ANDROID.equals(os)){
			param.setIm(user.getImei());
			param.setOs("Android");
		}else if(OS_IOS.equals(os)){
			param.setIm(user.getIdfa());
			param.setOs("iOS");
		}else{
			param.setOs("Others");
		}
		param.setSm(user.getImsi());
		param.setOs(user.getOsversion());
		param.setBrd(user.getBrand_name());
		try {
			param.setMd(URLEncoder.encode(user.getPhonemodel(), CHARSET_CODER));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		param.setNt(getNetwork(adreq.getNet()));
		param.setMc(user.getMac());
		param.setIp(user.getClient_ip());
		param.setReqt(0);
		param.setPostcnt(adreq.getResult_num());
		if(StringUtils.isNotEmpty(user.getLat())&&StringUtils.isNotEmpty(user.getLon())){
	
			param.setLat(Float.parseFloat(user.getLat()));
			param.setLon(Float.parseFloat(user.getLon()));
		}
		
		param.setCarrier(this.getOp(user.getMobile()));
		param.setPost(getAdType(adreq.getUserAdSpaceAttri().getSpaceType()));
		param.setIs_support_deeplink(0);
		param.setDevicetype(0);
		param.setDeviceid(user.getAdid());
		param.setDensity(user.getDensity());
		param.setDvw(user.getDevice_width());
		param.setDvh(user.getDevice_height());
		param.setOrientation(0);
		param.setUa(user.getUa());
		param.setUsegzip(false);
		return param;
	}

	private int getAdType(AdSpaceType spaceType){
		switch(spaceType){
		    case BANNER:
		    	return 4;
		    case INTERSTITIAL:
		    	return 3;
		    case OPENING:
		    	return 2;
		    case INFOFLOW:
		    	return 1;
		    default:
		    	return 2;
		
		}
	}
	
	private  String getNetwork(String net){	
		if(NETWORK_WIFI.equals(net)){
			return "wf";
		}else if(NETWORK_2G.equals(net)){
			return net;
		}
		else if(NETWORK_3G.equals(net)){
			return net;
		}
		else if(NETWORK_4G.equals(net)){
			return net;
		}else if(NETWORK_5G.equals(net)){
			return net;
		}
		return "unknow";
	}
	private int  getOp(String mobile){
		if(MOBILE_CMCC.equals(mobile)){
			return 0;
		}else if(MOBILE_CUCC.equals(mobile)){
			return 1;
		}else if(MOBILE_CTCC.equals(mobile)){
			return 2;
		}
		return -1;
	}
	@Override
	public AsynAbstractTask packageTask(Parameter params, String hashCode) {
		AdProxyXunfaTask task = new AdProxyXunfaTask();
		XunfaAdPullReq param = (XunfaAdPullReq) params;
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("ua", param.getUa());
		
		StringBuilder buff=new StringBuilder(SystemContext.getDynamicPropertyHandler().get(ProxyConstants.XUNFA_URL));
		buff.append("?")
		    .append("appid=").append(param.getAppid())
		    .append("&adunitid=").append(param.getAdunitid())
		    .append("&appn=").append(param.getAppn())
		    .append("&appv=").append(param.getAppv())
		    .append("&im=").append(param.getIm())
		    .append("&sm=").append(param.getSm())
		    .append("&ovs=").append(param.getOvs())
		    .append("&brd=").append(param.getBrd())
		    .append("&md=").append(param.getMd())
		    .append("&nt=").append(param.getNt())
		    .append("&mc=").append(param.getMc())
		    .append("&ip=").append(param.getIp())
		    .append("&reqt=").append(param.getReqt())
		    .append("&postcnt=").append(param.getPost())
		    .append("&lat=").append(param.getLat())
		    .append("&lon=").append(param.getLon())
		    .append("&carrier=").append(param.getCarrier())
		    .append("&post=").append(param.getPost())
		    .append("&is_support_deeplink=").append(param.getIs_support_deeplink())
		    .append("&devicetype=").append(param.getDevicetype())
		    .append("&os=").append(param.getOs())
		    .append("&deviceid=").append(param.getDeviceid())
		    .append("&density=").append(param.getDensity())
		    .append("&dvw=").append(param.getDvw())
		    .append("&dvh=").append(param.getDvh())
		    .append("&orientation=").append(param.getOrientation())
		    .append("&ua=").append(param.getUa())
		    .append("&usegzip=").append(param.isUsegzip());
		
		task.setParam(param);
		task.setDataFormat(DataFormat.DATA_FORMAT_KVP);
		task.setInvokeType(InvokeType.INVOKE_TYPE_GET);
		task.setHashCode(hashCode);
		task.setUrl(buff.toString());
		task.setHeaders(headers);
		return task;
	}
}
