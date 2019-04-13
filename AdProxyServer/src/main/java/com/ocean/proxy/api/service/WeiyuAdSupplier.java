package com.ocean.proxy.api.service;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
import com.ocean.persist.api.proxy.weiyu.WeiyuAdPullParams;
import com.ocean.persist.api.proxy.weiyu.WeiyuAdResponse;
import com.ocean.persist.api.proxy.weiyu.WeiyuApp;
import com.ocean.persist.api.proxy.weiyu.WeiyuImg;
import com.ocean.persist.api.proxy.weiyu.WeiyuNative;
import com.ocean.persist.api.proxy.weiyu.WeiyuTracking;
import com.ocean.persist.api.proxy.youken.MD5Utils;
import com.ocean.persist.common.ProxyConstants;
import com.ocean.persist.model.proxy.DSPPosition;
import com.ocean.proxy.api.base.AbstractAsynAdSupplier;
import com.ocean.proxy.serverdis.AdProxyWeiyuTask;
import com.ocean.proxy.thrift.entity.AdContent;
import com.ocean.proxy.thrift.entity.AdImg;
import com.ocean.proxy.thrift.entity.AdMutiAction;
import com.ocean.proxy.thrift.entity.AdRecomReply;
import com.ocean.proxy.thrift.entity.AdRecomReq;
import com.ocean.proxy.thrift.entity.AdSpaceType;
import com.ocean.proxy.thrift.entity.AdUserInfo;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年2月28日 
      @version 1.0 
 */
@Component(value="WeiyuAdSupplier")
public class WeiyuAdSupplier    extends AbstractAsynAdSupplier {
	 private static final String WY_CLICK_ID="__CLICK_ID__";
	 private static final String WY_CLICK_DOWN_X="__CLICK_DOWN_X__";
	 private static final String WY_CLICK_DOWN_Y="__CLICK_DOWN_Y__";
	 private static final String WY_CLICK_UP_X="__CLICK_UP_X__";
	 private static final String WY_CLICK_UP_Y="__CLICK_UP_Y__";
	 
	 public AdRecomReply invoke(InvokeAttribute attribute)
				throws AdPullException {
			// TODO Auto-generated method stub
			AdRecomReq adreq = attribute.getAdreq();
			DSPPosition positionInfo = attribute.getDspPosition();
			// 参数转换
			WeiyuAdPullParams params = parseParams(adreq, positionInfo);
			// 调用API
			WeiyuAdResponse response = (WeiyuAdResponse) invoke(params,WeiyuAdResponse.class,adreq.getHash()
					,String.valueOf(adreq.getUserAdSpaceAttri().getAdSpaceId()));
			
			// 解析结果
			return parseResult(response);
		}

	private WeiyuAdPullParams parseParams(AdRecomReq adreq, 
			DSPPosition positionInfo) throws AdPullException {
		if(StringUtils.isEmpty(positionInfo.getText1())||StringUtils.isEmpty(positionInfo.getText3())){
			throw  new AdPullException("appid or pkgname is empty!");
		}
		AdUserInfo userInfo=adreq.getUserinfo();
		WeiyuAdPullParams param =new WeiyuAdPullParams();
		param.setTagid(positionInfo.getPos());
		param.setAppid(positionInfo.getText1());
		try {
			param.setAppname(URLEncoder.encode(adreq.getApp(), CHARSET_CODER));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		param.setPkgname(positionInfo.getText3());
		param.setAppversion(adreq.getVersion());
		
		String os = userInfo.getOs();
		if(OS_ANDROID.equals(os)){
			param.setOs(1);
		}else if(OS_IOS.equals(os)){
			param.setOs(2);
			param.setIdfa(userInfo.getIdfa());
		}else{
			param.setOs(0);
		}
		param.setOsv(userInfo.getOsversion());
		param.setCarrier(getOp(userInfo.getMobile()));
		param.setConn(getNetwork(adreq.getNet()));
		param.setIp(userInfo.getClient_ip());
		param.setMake(userInfo.getBrand_name());
		try {
			param.setModel(URLEncoder.encode(userInfo.getPhonemodel(),CHARSET_CODER));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		param.setAnid(userInfo.getAdid());
		param.setMac(userInfo.getMac());
		try{
			param.setDpi(Integer.parseInt(userInfo.getDensity()));
		}catch(Exception e){
			param.setDpi(320);
		}
		
		param.setOrientation(0);
		if(StringUtils.isNotEmpty(userInfo.getLat())&&StringUtils.isNotEmpty(userInfo.getLon())){
			param.setLat(userInfo.getLat());
			param.setLon(userInfo.getLon());
		}
		param.setSw(userInfo.getDevice_width());
		param.setSh(userInfo.getDevice_height());
		param.setDevicetype(1);
		param.setUa(userInfo.getUa());
		param.setAdt(this.getST(adreq.getUserAdSpaceAttri().getSpaceType()));
		return param;
	}	/**
	 * 生成token
	 * @param req
	 * @param ctime
	 * @return
	 * @throws Exception
	 */
	public static String getToken(String req,String token, Long ctime) throws Exception {
		String tokenLocal = MD5Utils.md5AsString(req + token + ctime);
		return tokenLocal;
	}
	private int  getST(AdSpaceType st){
		switch(st){
			case BANNER:return 0;
			case INTERSTITIAL:return  1;
			case OPENING:return 2;
			case INFOFLOW:return 3;
			default:return 0;
		}
	}
	private int  getOp(String mobile){
		if(MOBILE_CMCC.equals(mobile)){
			return 1;
		}else if(MOBILE_CUCC.equals(mobile)){
			return 2;
		}else if(MOBILE_CTCC.equals(mobile)){
			return 3;
		}
		return 0;
	}
	private  int getNetwork(String net){	
		if(NETWORK_WIFI.equals(net)){
			return 1;
		}else if(NETWORK_2G.equals(net)){
			return 2;
		}
		else if(NETWORK_3G.equals(net)){
			return 3;
		}
		else if(NETWORK_4G.equals(net)){
			return 4;
		}else if(NETWORK_5G.equals(net)){
			return 5;
		}
		return 0;
	}

	private AdRecomReply parseResult(WeiyuAdResponse body)
			throws AdPullException {
		if(body == null){
			return null;
		}
		if(0!=body.getStatus()&&101!=body.getStatus()){
			throw new AdPullException("ad request error, return error code:"+body.getStatus());
		}
		AdRecomReply reply=new AdRecomReply();
		reply.setStatus(status);


		reply.setAd_contents(dealAds(body));
		return reply;
	}
	private List<AdContent>  dealAds(WeiyuAdResponse body){
		List<AdContent> list=new ArrayList<AdContent> ();
		if(body.getAdt()==30){
			return this.getAppInfo(body);
		}else if(body.getAdt()==3){
		
			AdContent content=this.getInflow(body);
			list.add(content);
			return list;
		}else{
			AdContent content=this.initContent(body);
			list.add(content);
		}
		return list;
	}
	private AdContent getInflow(WeiyuAdResponse body){
		AdContent content=this.initContent(body);
		WeiyuNative nat=body.getNativead();
		AdMutiAction action =content.getMutiAction().get(0);
		if(StringUtils.isNotEmpty(nat.getTitle())){
			content.setMarketTitle(nat.getTitle());
			content.setGuideTitle(nat.getTitle());
		}
		if(CollectionUtils.isNotEmpty(nat.getImg())){
		  List<AdImg> imgs =content.getImglist();
		  if(imgs==null){ 
			  new ArrayList<AdImg>();
		  }
		  for(WeiyuImg wimg:nat.getImg()){
				AdImg img=new AdImg();
				img.setSrc(wimg.getUrl());
				imgs.add(img);	
			}
		  content.setImglist(imgs);
		}
		if(StringUtils.isNotEmpty(nat.getLdp())){
			if(body.isIs_gdt()){
        		String url=nat.getLdp();
        		content.setLinkurl(url);
        		action.setLinkurl(url);
        		action.setLinkurl_type(1);
        	}else{
        		String link=format(REPORT_TYPE_REQ,nat.getLdp());
        		content.setLinkurl(link);
        		action.setLinkurl(link);
        	}
    		
		}else if(StringUtils.isNotEmpty(nat.getApp_download_url())){
			if(body.isIs_gdt()){
        		String url=nat.getApp_download_url();
        		content.setLinkurl(url);
        		action.setLinkurl(url);
        		action.setLinkurl_type(1);
        	}else{
        		String link=format(REPORT_TYPE_REQ,nat.getApp_download_url());
	    		content.setLinkurl(link);
	    		action.setLinkurl(link);
        	}
			if(StringUtils.isNotEmpty(nat.getDeeplink())){
				action.setActiveUri(nat.getDeeplink());
				//设置deeplink上报
		        Map<String, List<String>> reportMap=content.getThirdReportLinks();
				if(CollectionUtils.isNotEmpty(reportMap)){
					 reportMap = new HashMap<String, List<String>>();
			    }
		        List<String> clickL=reportMap.get(CLICK);
		        if(clickL==null){
		        	clickL=new ArrayList<String>();
		        }
		        
		        clickL.addAll(body.getDp_tracking());
		        reportMap.put(CLICK, clickL);
		        content.setThirdReportLinks(reportMap);

			}
		}

		content.setMutiAction(Collections.singletonList(action));
		return content;
	}
	private List<AdContent> getAppInfo(WeiyuAdResponse body){
		List<AdContent> list=new ArrayList<AdContent> ();
		
		for(WeiyuApp ad:body.getApp_down_list()){
			AdContent content=this.initContent( body);
			AdMutiAction action =content.getMutiAction().get(0);
			if(StringUtils.isNotEmpty(ad.getHref_download())){
        		content.setLinkurl(ad.getHref_download());
        		action.setLinkurl(ad.getHref_download());
	 
			}
	        if(StringUtils.isNotEmpty(ad.getApk())){
	        	action.setCpPackage(ad.getApk());
	        }
	       
	        action.setType(ACTION_APP);
	        content.setMutiAction(Collections.singletonList(action));
	        Map<String, List<String>> reportMap=content.getThirdReportLinks();
			if(CollectionUtils.isNotEmpty(reportMap)){
				 reportMap = new HashMap<String, List<String>>();
		    }
	        List<String> showL=reportMap.get(SHOW);
	        if(showL==null){
	        	showL=new ArrayList<String>();
	        }
	        showL.addAll(ad.getRpt_ss());
	        reportMap.put(SHOW, showL);
	        
	        List<String> startDL=reportMap.get(START_DOWN);
	   	    List<String> downL=reportMap.get(DOWNLOAD);
	   	   // List<String> startInstlL=reportMap.get(START_INSTALL);
	   	    List<String> instlL=reportMap.get(INSTALL);
	     	List<String> activeL=reportMap.get(ACTIVE);
	        if(startDL==null){
	        	startDL=new ArrayList<String>();
	        }
	        startDL.addAll(ad.getRpt_cd());
			if(downL==null){
				downL=new ArrayList<String>();      	
			}
			downL.addAll(ad.getRpt_dc());
			
			if(instlL==null){
				instlL=new ArrayList<String>();
			}
			instlL.addAll(ad.getRpt_ic());
			
			if(activeL==null){
				activeL=new ArrayList<String>();
			}
			activeL.addAll(ad.getRpt_ac());
			reportMap.put(START_DOWN, startDL);
			reportMap.put(DOWNLOAD, downL);

			reportMap.put(INSTALL, instlL);
			reportMap.put(ACTIVE, activeL); 
	     	
			content.setThirdReportLinks(reportMap);

		}
		return list;
	}
	private AdContent initContent(WeiyuAdResponse body){
		//***********************BEGIN 常规设置*************
		AdContent content=new AdContent();
		AdMutiAction action = new AdMutiAction();
		String title = defTitle;
		String marketTitle=defTitle;
		// 内容类型1表示链接推广,2表示应用推广,3未使用 4表示电话推广
	 
        int acType=ACTION_WEB;
        //图片
        List<AdImg> imgs = new ArrayList<AdImg>();
		//上报
        Map<String, List<String>> map = new HashMap<String, List<String>>();
		//***********************END 常规设置***************	
        if(StringUtils.isNotEmpty(body.getLdp())){
        	if(body.isIs_gdt()){
        		String url=body.getLdp();
        		content.setLinkurl(url);
        		action.setLinkurl(url);
        		action.setLinkurl_type(1);
        	}else{
        		String link=format(REPORT_TYPE_REQ,body.getLdp());
        		content.setLinkurl(link);
        		action.setLinkurl(link);
        	}
    		
        }
        if(StringUtils.isNotEmpty(body.getAdm())){
        	content.setHtmlSnippet(body.getAdm());
        	content.setIsHtmlAd(true);
        }
        if(StringUtils.isNotEmpty(body.getAdtitle())){
        	try {
				title = URLDecoder.decode(body.getAdtitle(),CHARSET_CODER);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
        
        

		if(StringUtils.isNotEmpty(body.getImgurl())){
			AdImg img=new AdImg();
			img.setSrc(body.getImgurl());
			imgs.add(img);	
		}
		//上报逻辑，又是一脑残协议
		if(body.getAdt()!=30){
			List<String> showL=new ArrayList<String>();
			if(CollectionUtils.isNotEmpty(body.getImp_tracking())){
				
				showL.addAll(body.getImp_tracking());
			 }
			 map.put(SHOW,showL);
			 List<String> clickL=new ArrayList<String>();
			 if(CollectionUtils.isNotEmpty(body.getClk_tracking())){
				 for(String url:body.getClk_tracking()){
					 clickL.add(format(REPORT_TYPE_CLICK,url));
				 }
				 
			 }

			 map.put(CLICK, clickL);
			 
		}
	    
		 if(body.getInteract_type()==1){
			    acType=ACTION_APP;
		        List<String> startDL=new ArrayList<String>();
		   	    List<String> downL=new ArrayList<String>();
		   	    List<String> startInstlL=new ArrayList<String>();
		   	    List<String> instlL=new ArrayList<String>();
		     	List<String> activeL=new ArrayList<String>();
				if(CollectionUtils.isNotEmpty(body.getConv_tracking())){
					 for(WeiyuTracking tracking:body.getConv_tracking()){
						 if(tracking.getTrack_type()==5){//下载开始
							 startDL.add(tracking.getUrl().replaceAll(WY_CLICK_ID,MACRO_CLICK_ID));
						 }else if(tracking.getTrack_type()==7){//下载完成
							 downL.add(tracking.getUrl().replaceAll(WY_CLICK_ID,MACRO_CLICK_ID));
						 }else if(tracking.getTrack_type()==8){//开始安装
							 startInstlL.add(tracking.getUrl().replaceAll(WY_CLICK_ID,MACRO_CLICK_ID));
						 }else if(tracking.getTrack_type()==6){//安装完成1
							 instlL.add(tracking.getUrl().replaceAll(WY_CLICK_ID,MACRO_CLICK_ID));
						 }else if(tracking.getTrack_type()==9){
							 activeL.add(tracking.getUrl().replaceAll(WY_CLICK_ID,MACRO_CLICK_ID));
						 }
					 }
				 }

				 map.put(START_DOWN, startDL);
				 map.put(DOWNLOAD, downL);
				 map.put(START_INSTALL, startInstlL);
				 map.put(INSTALL, instlL);
				 map.put(ACTIVE, activeL); 
		 }
	    content.setThirdReportLinks(map);	
		content.setImglist(imgs);
	    action.setType(acType); 
		content.setMarketTitle(marketTitle);
		content.setGuideTitle(title);
		content.setMutiAction(Collections.singletonList(action));
		return content;
	}
	private   String format(int type,String str){
		if(type==REPORT_TYPE_PV){
			str=str.replaceAll(WY_CLICK_DOWN_X, MACRO_CLICK_DEFUALT).replaceAll(WY_CLICK_DOWN_Y,MACRO_CLICK_DEFUALT)
				.replaceAll(WY_CLICK_UP_X,MACRO_CLICK_DEFUALT).replaceAll(WY_CLICK_UP_Y,MACRO_CLICK_DEFUALT);
		}else{
			str=str.replaceAll(WY_CLICK_DOWN_X, MACRO_DOWN_X).replaceAll(WY_CLICK_DOWN_Y, MACRO_DOWN_Y)
					.replaceAll(WY_CLICK_UP_X,MACRO_UP_X).replaceAll(WY_CLICK_UP_Y, MACRO_UP_Y);
		}

		return str;
	}
	@Override
	public AsynAbstractTask packageTask(Parameter params, String hashCode) {
		// TODO Auto-generated method stub
		AdProxyWeiyuTask task=new AdProxyWeiyuTask();
		WeiyuAdPullParams param = (WeiyuAdPullParams) params;
		StringBuilder buff=new StringBuilder(SystemContext.getDynamicPropertyHandler().get(ProxyConstants.WEIYU_URL));
		buff.append("?").append(Bean2Utils.toHttpParams(param)).append("&zkrequestId=").append(hashCode);
		task.setParam(param);
		task.setDataFormat(DataFormat.DATA_FORMAT_KVP);
		task.setInvokeType(InvokeType.INVOKE_TYPE_GET);
		task.setHashCode(hashCode);
		task.setUrl(buff.toString());
		
		return task;
	}

}
