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
import com.ocean.core.common.UniversalUtils;
import com.ocean.core.common.http.Bean2Utils;
import com.ocean.core.common.system.SystemContext;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
import com.ocean.core.common.threadpool.workthread.Task.DataFormat;
import com.ocean.core.common.threadpool.workthread.Task.InvokeType;
import com.ocean.persist.api.proxy.AdPullException;
import com.ocean.persist.api.proxy.woso.WosoAd;
import com.ocean.persist.api.proxy.woso.WosoAdPullParams;
import com.ocean.persist.api.proxy.woso.WosoAdResponse;
import com.ocean.persist.common.ProxyConstants;
import com.ocean.persist.model.proxy.DSPPosition;
import com.ocean.proxy.api.base.AbstractAsynAdSupplier;
import com.ocean.proxy.serverdis.AdProxyWosoTask;
import com.ocean.proxy.thrift.entity.AdContent;
import com.ocean.proxy.thrift.entity.AdImg;
import com.ocean.proxy.thrift.entity.AdMutiAction;
import com.ocean.proxy.thrift.entity.AdRecomReply;
import com.ocean.proxy.thrift.entity.AdRecomReq;
import com.ocean.proxy.thrift.entity.AdUserInfo;
import com.ocean.proxy.thrift.entity.UserAdSpaceAttri;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年4月21日 
      @version 1.0 
 */
@Component(value="WosoAdSupplier")
public class WosoAdSupplier    extends AbstractAsynAdSupplier{
	protected static final Map<String, Integer> wosomobiles = new HashMap<String, Integer>(4);
	//private static final String BAIDU_LOGO="https://cpro.baidustatic.com/cpro/ui/noexpire/img/2.0.1/bd-logo4.png";
	
	private static final String GDT_LOGO="https://cpro.baidustatic.com/cpro/ui/noexpire/img/mob_adicon.png";//目前都用这一个
	
	private static final Map<String, Integer> conn = new HashMap<String, Integer>();
	static{
		wosomobiles.put("CMCC", 1);
		wosomobiles.put("CTCC", 3);
		wosomobiles.put("CUCC", 2);
		
		conn.put(NETWORK_2G, 2);
		conn.put(NETWORK_3G, 3);
		conn.put(NETWORK_4G, 4);
		conn.put(NETWORK_WIFI, 1);
	}
	public AdRecomReply invoke(InvokeAttribute attribute)
			throws AdPullException {
		// 参数转换
		AdRecomReq adreq = attribute.getAdreq();
		WosoAdPullParams params = parseParams(attribute);
		// 调用API
		WosoAdResponse response = (WosoAdResponse) invoke(params,WosoAdResponse.class,adreq.getHash(),String.valueOf(adreq.getUserAdSpaceAttri().getAdSpaceId()),"joinDSP:woso");
		// 解析结果
		return parseResult(response);
	}
	private WosoAdPullParams parseParams(InvokeAttribute attribute) throws AdPullException {
		DSPPosition positionInfo = attribute.getDspPosition();
		if(StringUtils.isEmpty(positionInfo.getText3())){
			throw  new AdPullException("package name is empty!");
		}
		AdRecomReq adreq = attribute.getAdreq();
		UserAdSpaceAttri attri=	adreq.getUserAdSpaceAttri();
		AdUserInfo user=adreq.getUserinfo();
		WosoAdPullParams param=new WosoAdPullParams();
		param.setWsid(positionInfo.getPos());
		param.setAppname(adreq.getApp());
		String os = user.getOs();
		if(OS_ANDROID.equals(os)){
			param.setOs(1);
/*			if(StringUtils.isNotEmpty(user.getImei())&&user.getImei().length()==14){
	    		param.setMouid(UniversalUtils.getImeiBy14(user.getImei()));

	    	}*/
			if(StringUtils.isNotEmpty(user.getImei())&&user.getImei().length()==14&&!user.getImei().startsWith("A")){
	    		param.setMouid(UniversalUtils.getImeiBy14(user.getImei()));

	    	}else{
	    		param.setMouid(user.getImei());
	    	}
			
		}else{
			param.setOs(2);
			param.setIfa(user.getIdfa());
		}
		if(StringUtils.isNotEmpty(user.getPhonemodel())){
			try {
				param.setDve(URLEncoder.encode(user.getPhonemodel(),CHARSET_CODER));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			param.setDve("unknown");
		}
		
		try {
			param.setDmo(URLEncoder.encode(user.getPhonemodel(),CHARSET_CODER));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Integer dop=wosomobiles.get(user.getMobile());
		param.setDop(dop==null?0:dop);
		Integer net=conn.get(adreq.getNet());
		param.setNet_type(net==null?0:net);
		
		if(positionInfo.getPosType()==2){
			param.setAw(640);
			param.setAh(960);
		}else{
			param.setAw(attri.getSpaceWidth());
			param.setAh(attri.getSpaceHeight());
		}

		
		param.setAi(user.getAdid());
		param.setDetype(1);
		param.setApv(adreq.getVersion());
		param.setDev(this.convOSVersion(user.getOsversion()));
		param.setPckname(positionInfo.getText3());
		param.setAppname(adreq.getApp());
		param.setAdtype(getAdType(positionInfo.getPosType()));
		param.setIp(user.getClient_ip());
		param.setSecure(0);
		param.setUa(user.getUa());
		
		//这个值可能为空
		if(StringUtils.isNotEmpty(user.getMac())){
//			param.setMac(DigestUtils.md5Hex(user.getMac().replaceAll(":", "")));
			// woso don't need md5, modified by yunhui.zhang
			//param.setMac(user.getMac().replaceAll(":", ""));
			param.setMac(user.getMac());
		}
		
/*		if(StringUtils.isNotEmpty(user.getAaid())){//带这个参数对方返回400错误
			param.setAaid(user.getAaid());
		}*/
		param.setDw(user.getDevice_width());
		param.setDh(user.getDevice_height());
		return param;
	}
	
	private int getAdType(int zkAdType){
		switch(zkAdType){
			case SPACE_TYPE_BANNER:
				return 1;
			case SPACE_TYPE_INTERSTITIAL:
				return 2;
			case SPACE_TYPE_OPENING:
				return 4;
			case SPACE_TYPE_INFOFLOW:
				return 8;
			default:return 1;
		}

	}
	private AdRecomReply parseResult(WosoAdResponse response)
			throws AdPullException {
		if(response == null||CollectionUtils.isEmpty(response.getAds())){
			return null;
		}
		if(response.getCode()!=0){
			
			throw new AdPullException("ad request error,return error code:"+response.getCode()+" msg:"+response.getMessage());
		}
		// 返回对象
		AdRecomReply recomReply = new AdRecomReply();
		recomReply.setStatus(status);
		//返回结果解析
		recomReply.setAd_contents(dealAds(response.getAds()));
		return recomReply;
	}
	private List<AdContent>  dealAds(List<WosoAd> adDatas) throws AdPullException{
		List<AdContent> list=new ArrayList<AdContent> ();
		
		for(WosoAd ad:adDatas){
			//------------------BEGIN 常规设置-------------------
			AdContent content = new AdContent();
			AdMutiAction action = new AdMutiAction();
			String title = defTitle;
			int actType=ACTION_WEB;
			// 内容类型1表示链接推广,2表示应用推广,3未使用 4表示电话推广
			
			//------------------END 常规设置---------------------
			if(ad.getAdType()==2||ad.getAdType()==3){
				actType=ACTION_APP;
			}

	        //图片
	        List<AdImg> imgs = new ArrayList<AdImg>();
			if(CollectionUtils.isNotEmpty(ad.getImage_src())){
				for(String url:ad.getImage_src()){
					AdImg img=new AdImg();
					img.setSrc(url);
					imgs.add(img);
				}

			}
			
			//icon
			if(StringUtils.isNotEmpty(ad.getIcon_src())){
				content.setCpIcon(ad.getIcon_src() );
			}
		    if(StringUtils.isNotEmpty(ad.getAdfrom())){
				content.setAdSource(ad.getAdfrom());
				if("BAIDU".equals(ad.getAdfrom().toUpperCase())){
					AdImg img=new AdImg();
					img.setSrc(GDT_LOGO);
					content.setLogoImg(img );
				}	else if("GDT".equals(ad.getAdfrom().toUpperCase())){
					AdImg img=new AdImg();
					img.setSrc(GDT_LOGO);
					content.setLogoImg(img);
				}
			}
			//落地页
			if(StringUtils.isNotEmpty(ad.getClick_url())){
				if(ad.getAdType()==0||ad.getAdType()==3){
					String url=formatUrl(ad.getClick_url());
					content.setLinkurl(url);
					action.setLinkurl(url);
					action.setLinkurl_type(ad.getAdType()==3?1:0);//非直接下载类链接，需要解析
				}else{
					content.setLinkurl(ad.getClick_url());
					action.setLinkurl(ad.getClick_url());
				}

			}
			if(StringUtils.isNotEmpty(ad.getText())){
				title=ad.getText();
			}
			
			//下载地址
			Map<String, List<String>> map = new HashMap<String, List<String>>();
			// 点击监测
			List<String> clickL=new ArrayList<String>();
			List<String> viewL=new ArrayList<String>();
			List<String> download=new ArrayList<String>();
			List<String> install=new ArrayList<String>();
			List<String> active = new ArrayList<String>();
			if(CollectionUtils.isNotEmpty(ad.getExpose_url())){
				viewL.addAll(ad.getExpose_url());
				map.put(SHOW, viewL);
			}
			
			
			if(CollectionUtils.isNotEmpty(ad.getClicker_url())){
				clickL.addAll(ad.getClicker_url());
			
			}
			if(CollectionUtils.isNotEmpty(ad.getStartDownloadUrls())){
				clickL.addAll(ad.getStartDownloadUrls());
			}
			if(!clickL.isEmpty()){//替代宏
				for(int i=0;i<clickL.size();i++){
					clickL.set(i, checkClickId(clickL.get(i)));
				}
			}
			map.put(CLICK, clickL);
			
			
			
			if(CollectionUtils.isNotEmpty(ad.getFinishDownloadUrls())){
				
				download.addAll(ad.getFinishDownloadUrls());
			}
			
			if(!download.isEmpty()){//替代宏
				for(int i=0;i<download.size();i++){
					download.set(i, checkClickId(download.get(i)));
				}
			}
			map.put(DOWNLOAD,download);
			
			if(CollectionUtils.isNotEmpty(ad.getStartInstallUrls())){
				install.addAll(ad.getStartInstallUrls());
			}
			if(CollectionUtils.isNotEmpty(ad.getFinishInstallUrls())){
				install.addAll(ad.getFinishInstallUrls());
			}
			if(!install.isEmpty()){//替代宏
				for(int i=0;i<install.size();i++){
					install.set(i, checkClickId(install.get(i)));
				}
			}
			map.put(INSTALL, install);
			
			if(CollectionUtils.isNotEmpty(ad.getActiveUrls())){
				active.addAll(ad.getActiveUrls());
			}
			if(!active.isEmpty()){//替代宏
				for(int i=0;i<active.size();i++){
					active.set(i, checkClickId(active.get(i)));
				}
			}
			map.put(ACTIVE, active);
			
			content.setThirdReportLinks(map);
			// 类型
			
			//img
			content.setImglist(imgs);
			action.setType(actType);  
			action.setGuideTitle(title);
			content.setMarketTitle(title);
			content.setGuideTitle(title);
			content.setMutiAction(Collections.singletonList(action));
			list.add(content);
		}
		return list;
	}
	private String  checkClickId(String url){
		if(StringUtils.isEmpty(url)){
			return "";
		}
		try {
			url=URLDecoder.decode(url, CHARSET_CODER);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return url.replaceAll("__CLICK_ID__", MACRO_CLICK_ID);
	}
	private   String formatUrl(String url){
		
		if(StringUtils.isEmpty(url)){
			return "";
		}
		try {
			url=URLDecoder.decode(url, CHARSET_CODER);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return url.replaceAll("__DOWN_X__", MACRO_DOWN_X).replaceAll("__DOWN_Y__", MACRO_DOWN_Y).replaceAll("__UP_X__", MACRO_UP_X)
				.replaceAll("__UP_Y__", MACRO_UP_Y).replaceAll("__REQ_WIDTH__", MACRO_REQ_WIDTH).replaceAll("__REQ_HEIGHT__", MACRO_REQ_HEIGHT)
				.replaceAll("__WIDTH__", MACRO_REL_WIDTH).replaceAll("__HEIGHT__", MACRO_REL_HEIGHT);

	}
	@Override
	public AsynAbstractTask packageTask(Parameter params, String hashCode) {
		AdProxyWosoTask task=new AdProxyWosoTask();
		WosoAdPullParams param = (WosoAdPullParams) params;
		
		//TODO ua uriencode问题
		if(!StringUtils.isEmpty(param.getUa())) {
			try {
				param.setUa(URLEncoder.encode(param.getUa(), "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		StringBuilder url = new StringBuilder();
		url.append(SystemContext.getDynamicPropertyHandler().get(ProxyConstants.WOSO_URL));
		url.append("?").append(Bean2Utils.toHttpParams(params));
		
		//logger.info("joinDSP:woso {} request param:{}",hashCode,UniversalUtils.replaceBlank(JsonUtils.toJson(param)));
		task.setParam(param);
		task.setDataFormat(DataFormat.DATA_FORMAT_KVP);
		task.setInvokeType(InvokeType.INVOKE_TYPE_GET);
		task.setHashCode(hashCode);
		task.setUrl(url.toString());
		return task;
	}

}
