package com.ocean.proxy.api.service;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
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
import com.ocean.persist.api.proxy.jingzhun.JingzhunAd;
import com.ocean.persist.api.proxy.jingzhun.JingzhunAdPullParams;
import com.ocean.persist.api.proxy.jingzhun.JingzhunAdPullResponse;
import com.ocean.persist.api.proxy.youken.MD5Utils;
import com.ocean.persist.common.ProxyConstants;
import com.ocean.persist.model.proxy.DSPPosition;
import com.ocean.proxy.api.base.AbstractAsynAdSupplier;
import com.ocean.proxy.serverdis.AdProxyJingzhunTask;
import com.ocean.proxy.thrift.entity.AdContent;
import com.ocean.proxy.thrift.entity.AdImg;
import com.ocean.proxy.thrift.entity.AdMutiAction;
import com.ocean.proxy.thrift.entity.AdRecomReply;
import com.ocean.proxy.thrift.entity.AdRecomReq;
import com.ocean.proxy.thrift.entity.AdUserInfo;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年2月28日 
      @version 1.0 
 */
@Component(value="JingzhunAdSupplier")
public class JingzhunAdSupplier    extends AbstractAsynAdSupplier {

	 public AdRecomReply invoke(InvokeAttribute attribute)
				throws AdPullException {
			// TODO Auto-generated method stub
			AdRecomReq adreq = attribute.getAdreq();
			DSPPosition positionInfo = attribute.getDspPosition();
			// 参数转换
			JingzhunAdPullParams params = parseParams(adreq, positionInfo);
			// 调用API
			JingzhunAdPullResponse response = (JingzhunAdPullResponse) invoke(params,JingzhunAdPullResponse.class,adreq.getHash()
					,String.valueOf(adreq.getUserAdSpaceAttri().getAdSpaceId()));
			
			// 解析结果
			return parseResult(response);
		}

	private JingzhunAdPullParams parseParams(AdRecomReq adreq, 
			DSPPosition positionInfo) throws AdPullException {
		if(StringUtils.isEmpty(positionInfo.getText1())||StringUtils.isEmpty(positionInfo.getText2())||StringUtils.isEmpty(positionInfo.getText3())){
			throw  new AdPullException("app id /key or pkg name is empty!");
		}
		AdUserInfo userInfo=adreq.getUserinfo();
		JingzhunAdPullParams param =new JingzhunAdPullParams();
		param.setAppid(positionInfo.getText1());
		param.setPosid(positionInfo.getPos());
		param.setBid(positionInfo.getText2());
		param.setSign(DigestUtils.md5Hex(param.getAppid()+param.getBid()+param.getPosid()));
		param.setAdcount(adreq.getResult_num());
		param.setMac(userInfo.getMac());
		param.setSw(adreq.getUserAdSpaceAttri().getSpaceWidth());
		param.setSh(adreq.getUserAdSpaceAttri().getSpaceHeight());
		param.setRemoteip(userInfo.getClient_ip());
		param.setBrand(userInfo.getBrand_name());
		try {
			param.setModel(URLEncoder.encode(userInfo.getPhonemodel(),CHARSET_CODER));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		String os = userInfo.getOs();
		if(OS_ANDROID.equals(os)){
			param.setOs(os);
			
		}else{
			param.setOs("ios");
			param.setIdfa(userInfo.getIdfa());
		}
		param.setAsver(this.convOSVersion(userInfo.getOsversion()));
		param.setImei(userInfo.getImei());
		param.setAid(userInfo.getAdid());
		param.setImsi(userInfo.getImsi());
		if(StringUtils.isNotEmpty(userInfo.getDensity())){
			param.setDpi(Integer.parseInt(userInfo.getDensity()));
		}
		
		param.setNet(getNetwork(adreq.getNet()));
		param.setIsp(getOp(userInfo.getMobile()));

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

	private  int getNetwork(String net){	
		if(NETWORK_WIFI.equals(net)){
			return 1;
		}else if(NETWORK_2G.equals(net)){
			return 3;
		}
		else if(NETWORK_3G.equals(net)){
			return 4;
		}
		else if(NETWORK_4G.equals(net)){
			return 5;
		}
		return 0;
	}
	private int getOp(String op){

		if(MOBILE_CMCC.equals(op)){
			return 1;
		}else if(MOBILE_CUCC.equals(op)){
			return 2;
		}else if(MOBILE_CTCC.equals(op)){
			return 3;
		}
		return 0;
	}
	private AdRecomReply parseResult(JingzhunAdPullResponse body)
			throws AdPullException {
		if(body == null||CollectionUtils.isEmpty(body.getObjAds())){
			return null;
		}
		if(0!=body.getIntError()){
			throw new AdPullException("ad request error, return error code:"+body.getIntError());
		}
		AdRecomReply reply=new AdRecomReply();
		reply.setStatus(status);
		reply.setAd_contents(this.dealAds(body));
		return reply;
	}
	private List<AdContent>  dealAds(JingzhunAdPullResponse resp){
		List<AdContent> list=new ArrayList<AdContent> ();
		
		for(JingzhunAd ad:resp.getObjAds()){
			//***********************BEGIN 常规设置*************
			AdContent content = new AdContent();
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
	     
	        if(StringUtils.isNotEmpty(ad.getStrLinkUrl())){

	        	StringBuffer buff=new StringBuffer();
	        	String link=ad.getStrLinkUrl();
	        	String sufix=link.indexOf("?")<0?"?":"&";
	        	
	        	buff.append(link).append(sufix).append(MACRO_COORDINATE);
	        	
	        	if(ad.getIntActionType()==3){
	        		action.setLinkurl_type(1);
	        		
	        	}
	    		content.setLinkurl(buff.toString());
	    		action.setLinkurl(buff.toString());
	        }

	        if(ad.getIntActionType()!=2){
	        	acType=ACTION_APP;
	        }

			if(StringUtils.isNotEmpty(ad.getStrImageUrl())){
				AdImg img=new AdImg();
				img.setSrc(ad.getStrImageUrl());
				imgs.add(img);	
			}
			if(StringUtils.isNotEmpty(ad.getStrTitle())){
				title =ad.getStrTitle();
			}
		    List<String> showL=new ArrayList<String>();
			if(CollectionUtils.isNotEmpty(ad.getArrViewTrackUrl())){
	/*			for(String url:ad.getArrViewTrackUrl()){
					showL.add(format(url));
				}*/
				showL.addAll(ad.getArrViewTrackUrl());
				
			 }
			 map.put(SHOW,showL);
			 List<String> clickL=new ArrayList<String>();
			 if(CollectionUtils.isNotEmpty(ad.getArrClickTrackUrl())){

				clickL.addAll(ad.getArrClickTrackUrl());
		
			 }
			 if(CollectionUtils.isNotEmpty(ad.getArrDownloadTrackUrl())){
				 if(ad.getIntActionType()==3){
					 for(String url:ad.getArrDownloadTrackUrl()){
						 clickL.add(format(url));
					 }
				 }else{
					 clickL.addAll(ad.getArrDownloadTrackUrl());
				 }

			 }
			 
			 map.put(CLICK, clickL);
			 
			 List<String> downL=new ArrayList<String>();
			 if(CollectionUtils.isNotEmpty(ad.getArrDownloadedTrakUrl())){
				 if(ad.getIntActionType()==3){
					 for(String url:ad.getArrDownloadedTrakUrl()){
						 downL.add(format(url));
					 }
				 }else{
					 downL.addAll(ad.getArrDownloadedTrakUrl());
				 }

				
			 }
			 map.put(DOWNLOAD, downL);
			 
			 List<String> installL=new ArrayList<String>();
			 if(CollectionUtils.isNotEmpty(ad.getArrIntalledTrackUrl())){
				 if(ad.getIntActionType()==3){
					 for(String url:ad.getArrIntalledTrackUrl()){
						 installL.add(format(url));
					 }
				 }else{
					 installL.addAll(ad.getArrIntalledTrackUrl());
				 }

			     

			 }
			 map.put(INSTALL,installL);  
		    content.setThirdReportLinks(map);	
			content.setImglist(imgs);
		    action.setType(acType); 
			content.setMarketTitle(marketTitle);
			content.setGuideTitle(title);
			content.setMutiAction(Collections.singletonList(action));

			list.add(content);
		}
		return list;
	}
	private static String format(String str){
		StringBuilder sb=new StringBuilder();
    	String sufix=str.indexOf("?")<0?"?":"&";

		return sb.append(str).append(sufix).append("clickid=").append(MACRO_CLICK_ID).toString();
	}
	@Override
	public AsynAbstractTask packageTask(Parameter params, String hashCode) {
		// TODO Auto-generated method stub
		AdProxyJingzhunTask task=new AdProxyJingzhunTask();
		JingzhunAdPullParams param = (JingzhunAdPullParams) params;
		StringBuilder buff=new StringBuilder(SystemContext.getDynamicPropertyHandler().get(ProxyConstants.JINGZHUN_URL));
		buff.append("?").append(Bean2Utils.toHttpParams(param)).append("&requestId=").append(hashCode);
	
		task.setParam(param);
		task.setDataFormat(DataFormat.DATA_FORMAT_KVP);
		task.setInvokeType(InvokeType.INVOKE_TYPE_GET);
		task.setHashCode(hashCode);
		task.setUrl(buff.toString());
		
		return task;
	}

}
