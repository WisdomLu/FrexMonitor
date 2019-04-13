package com.ocean.proxy.api.service;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
import com.ocean.persist.api.proxy.diankai.DiankaiAd;
import com.ocean.persist.api.proxy.diankai.DiankaiAdPullResponse;
import com.ocean.persist.api.proxy.qidian.QidainAppInfo;
import com.ocean.persist.api.proxy.qidian.QidianAd;
import com.ocean.persist.api.proxy.qidian.QidianAdPullParams;
import com.ocean.persist.api.proxy.qidian.QidianAdPullParamsV2;
import com.ocean.persist.api.proxy.qidian.QidianAdPullRespV2;
import com.ocean.persist.api.proxy.qidian.QidianAdPullResponse;
import com.ocean.persist.api.proxy.qidian.QidianAdV2;
import com.ocean.persist.api.proxy.qidian.QidianApp;
import com.ocean.persist.api.proxy.qidian.QidianDevice;
import com.ocean.persist.api.proxy.qidian.QidianGeo;
import com.ocean.persist.api.proxy.qidian.QidianGroup;
import com.ocean.persist.api.proxy.qidian.QidianImg;
import com.ocean.persist.api.proxy.qidian.QidianImp;
import com.ocean.persist.api.proxy.youken.MD5Utils;
import com.ocean.persist.common.ProxyConstants;
import com.ocean.persist.model.proxy.DSPPosition;
import com.ocean.proxy.api.base.AbstractAsynAdSupplier;
import com.ocean.proxy.serverdis.AdProxyQidianTask;
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
@Component(value="QidianAdSupplier")
public class QidianAdSupplier    extends AbstractAsynAdSupplier {

	 public AdRecomReply invoke(InvokeAttribute attribute)
				throws AdPullException {
			// TODO Auto-generated method stub
			AdRecomReq adreq = attribute.getAdreq();
			DSPPosition positionInfo = attribute.getDspPosition();
			// 参数转换
/*			QidianAdPullParams params = parseParams(adreq, positionInfo);
			// 调用API
			QidianAdPullResponse response = (QidianAdPullResponse) invoke(params,QidianAdPullResponse.class,adreq.getHash()
					,String.valueOf(adreq.getUserAdSpaceAttri().getAdSpaceId()));
			return parseResult(response);
			*/
			QidianAdPullParamsV2 params = parseParamsV2(adreq, positionInfo);
			// 调用API
			QidianAdPullRespV2 response = (QidianAdPullRespV2) invoke(params,QidianAdPullRespV2.class,adreq.getHash()
					,String.valueOf(adreq.getUserAdSpaceAttri().getAdSpaceId()));
			
			// 解析结果
			return parseResultV2(response);
		}
		private QidianAdPullParamsV2 parseParamsV2(AdRecomReq adreq, 
				DSPPosition positionInfo) throws AdPullException {
			if(StringUtils.isEmpty(positionInfo.getText1())||StringUtils.isEmpty(positionInfo.getText2())||StringUtils.isEmpty(positionInfo.getText3())){
				throw  new AdPullException("requestid prefix /token/pkgname is empty!");
			}
			AdUserInfo userInfo=adreq.getUserinfo();
			QidianAdPullParamsV2 param=new QidianAdPullParamsV2();
			String platId=positionInfo.getText1().trim();
			StringBuilder b=new StringBuilder();
			for(int i=0;i<5-platId.length();i++){
				b.append("0");
			}
			b.append(platId);
			String poseId=positionInfo.getPos().trim();
			StringBuilder c=new StringBuilder();
			for(int i=0;i<7-poseId.length();i++){
				c.append("0");
			}
			c.append(poseId);
			
			param.setRequestId(b.toString()+c.toString()+System.currentTimeMillis());
			param.setAuth(DigestUtils.md5Hex(param.getRequestId()+positionInfo.getText2().trim()).toUpperCase());
			//device
			QidianDevice device =new QidianDevice();
			device.setDeviceId(userInfo.getImei());
			device.setNetwork(getNetwork(adreq.getNet()));
			device.setImei(userInfo.getImei());
			device.setDeviceType(1);
			device.setImeiMd5(DigestUtils.md5Hex(userInfo.getImei()).toUpperCase());
			String os = userInfo.getOs();
			if(OS_ANDROID.equals(os)){
				device.setAndroidId(userInfo.getAdid().toUpperCase());
				device.setOs("android");
			}else{
			
				device.setIdfa(userInfo.getIdfa());
				device.setOs("ios");
			}
			device.setOsVersion(userInfo.getOsversion());
			
			
			
			device.setBrand(userInfo.getBrand_name());
			device.setModel(userInfo.getPhonemodel());
		    device.setCarrier(getOp(userInfo.getMobile()));
		    device.setMac(userInfo.getMac().toUpperCase());
		    device.setIp(userInfo.getClient_ip());
		    device.setUserAgent(userInfo.getUa());
		    
		    //GEO
			if(StringUtils.isNotEmpty(userInfo.getLat())&&StringUtils.isNotEmpty(userInfo.getLon())){
				QidianGeo geo=new QidianGeo();
				geo.setLatitude(Double.parseDouble(userInfo.getLat()));
				geo.setLongitude(Double.parseDouble(userInfo.getLon()));
				device.setGeo(geo);
			}
			param.setDevice(device);
			
		    //imp
			QidianImp imp=new QidianImp();
			imp.setId(0);
			imp.setCount(adreq.getResult_num());
		    param.setImps(Collections.singletonList(imp));
			
			
		    //app
		    QidianApp app=new QidianApp();
			app.setPkgName(positionInfo.getText3());
			app.setAppName(adreq.getApp());
			param.setApp(app);
			param.setVersion("2.3.1");
			return param;
		}
		private int  getOp(String mobile){
			if(MOBILE_CMCC.equals(mobile)){
				return 1;
			}else if(MOBILE_CUCC.equals(mobile)){
				return 2;
			}else if(MOBILE_CTCC.equals(mobile)){
				return 3;
			}
			return 9;
		}
	private QidianAdPullParams parseParams(AdRecomReq adreq, 
			DSPPosition positionInfo) throws AdPullException {
		if(StringUtils.isEmpty(positionInfo.getText1())||StringUtils.isEmpty(positionInfo.getText2())){
			throw  new AdPullException("requestid prefix /token is empty!");
		}
		AdUserInfo userInfo=adreq.getUserinfo();
		QidianAdPullParams param =new QidianAdPullParams();
		//String requestIdPre=SystemContext.getDynamicPropertyHandler().get(ProxyConstants.QIDIAN_REQUESTID_PREFIX);
		String platId=positionInfo.getText1().trim();
		StringBuilder b=new StringBuilder();
		for(int i=0;i<5-platId.length();i++){
			b.append("0");
		}
		b.append(platId);
		String poseId=positionInfo.getPos().trim();
		StringBuilder c=new StringBuilder();
		for(int i=0;i<7-poseId.length();i++){
			c.append("0");
		}
		c.append(poseId);
		
		param.setRequestId(b.toString()+c.toString()+System.currentTimeMillis());
		param.setAuth(DigestUtils.md5Hex(param.getRequestId()+positionInfo.getText2().trim()).toUpperCase());
		param.setDeviceId(userInfo.getImei());
		param.setNetwork(getNetwork(adreq.getNet()));
		param.setCount(adreq.getResult_num());
		param.setTerminal(1);
		param.setImei(userInfo.getImei());
		
		String os = userInfo.getOs();
		if(OS_ANDROID.equals(os)){
			//param.setOs(os);
			param.setMid(userInfo.getAdid());
		}else{
			//param.setOs("ios");
			param.setMid(userInfo.getIdfa());
		}
		try {
			param.setModel(URLEncoder.encode(userInfo.getPhonemodel(),CHARSET_CODER));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(StringUtils.isNotEmpty(userInfo.getLat())&&StringUtils.isNotEmpty(userInfo.getLon())){
			param.setLat(Double.parseDouble(userInfo.getLat()));
			param.setLng(Double.parseDouble(userInfo.getLon()));
			
		}
		param.setMac(userInfo.getMac());
		param.setVersion("1.8.1");
		param.setIp(userInfo.getClient_ip());
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
			return 6;
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
		return 1;
	}
	private AdRecomReply parseResultV2(QidianAdPullRespV2 body)
			throws AdPullException {
		if(body == null||null==body.getData()||CollectionUtils.isEmpty(body.getData().getGroups())){
			return null;
		}
		if(0!=body.getCode()){
			throw new AdPullException("ad request error, return error code:"+body.getCode()+" msg:"+body.getMsg());
		}
		AdRecomReply reply=new AdRecomReply();
		reply.setStatus(status);
		reply.setAd_contents(this.dealAdsV2(body.getData().getGroups()));
		return reply;
	}
	private List<AdContent> dealAdsV2(List<QidianGroup> groupL)
			throws AdPullException {
		List<AdContent> list=new ArrayList<AdContent> ();
		for(QidianGroup group:groupL){
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
	        if(CollectionUtils.isEmpty(group.getAds())){
	        	continue;
	        }
	        QidianAdV2 ad=group.getAds().get(0);
	        if(StringUtils.isNotEmpty(ad.getLink())){
	    		content.setLinkurl(ad.getLink());
	    		action.setLinkurl(ad.getLink());
	        }
	        
	        if(ad.getAppInfo()!=null){
	        	acType=ACTION_APP;
	        	QidainAppInfo app=ad.getAppInfo();
	        	action.setCpPackage(app.getPkgName());
	        	
		        if(StringUtils.isNotEmpty(app.getDeepLink())){
		        	action.setActiveUri(app.getDeepLink());
		        }
		        if(StringUtils.isNotEmpty(app.getIcon())){
		          	action.setCpIcon(app.getIcon());
		        }
				if(StringUtils.isNotEmpty(app.getDsUrl())){
					 map.put(START_DOWN, Collections.singletonList(app.getDsUrl()));
				 }
		        
				if(StringUtils.isNotEmpty(app.getDfUrl())){
					 map.put(DOWNLOAD, Collections.singletonList(app.getDfUrl()));
				 }

				if(StringUtils.isNotEmpty(app.getSfUrl())){
					 map.put(INSTALL,Collections.singletonList(app.getSfUrl())); 
				 }
				
	        	
	        }
	        if(StringUtils.isNotEmpty(ad.getTitle())){
	        	try {
					title = URLDecoder.decode(ad.getTitle(),CHARSET_CODER);
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }


			if(CollectionUtils.isNotEmpty(ad.getImgs())){
				for(QidianImg qdImg:ad.getImgs()){
					AdImg img=new AdImg();
					img.setSrc(qdImg.getUrl());
					imgs.add(img);	
				}
			
			}
			if(CollectionUtils.isNotEmpty(ad.getImpTrackUrls())){
				 map.put(SHOW,ad.getImpTrackUrls());
			 }
			
			
	        List<String> clickL=new ArrayList<String>();
			 if(CollectionUtils.isNotEmpty(ad.getClickTrackUrls())){

				clickL.addAll(ad.getClickTrackUrls());
		
			 }
/*			 if(StringUtils.isNotEmpty(ad.getDsUrl())){
				 clickL.add(ad.getDsUrl());
			 }*/
			 
			 map.put(CLICK, clickL);
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
	private AdRecomReply parseResult(QidianAdPullResponse body)
			throws AdPullException {
		if(body == null||null==body.getData()||CollectionUtils.isEmpty(body.getData().getAdList())){
			return null;
		}
		if(0!=body.getCode()){
			throw new AdPullException("ad request error, return error code:"+body.getCode()+" msg:"+body.getMessage());
		}
		AdRecomReply reply=new AdRecomReply();
		reply.setStatus(status);
		reply.setAd_contents(this.dealAds(body));
		return reply;
	}
	private List<AdContent>  dealAds(QidianAdPullResponse resp){
		List<AdContent> list=new ArrayList<AdContent> ();
		for(QidianAd ad:resp.getData().getAdList()){
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
	        if(StringUtils.isNotEmpty(ad.getLink())){
	    		content.setLinkurl(ad.getLink());
	    		action.setLinkurl(ad.getLink());
	        }
	        if(StringUtils.isNotEmpty(ad.getPackageName())){
	        	action.setCpPackage(ad.getPackageName());
	        }
	        if(StringUtils.isNotEmpty(ad.getTitle())){
	        	try {
					title = URLDecoder.decode(ad.getTitle(),CHARSET_CODER);
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	        if(ad.getType()!=1){
	        	acType=ACTION_APP;
	        }

			if(StringUtils.isNotEmpty(ad.getImage1())){
				AdImg img=new AdImg();
				img.setSrc(ad.getImage1());
				imgs.add(img);	
			}
			if(StringUtils.isNotEmpty(ad.getImage2())){
				AdImg img=new AdImg();
				img.setSrc(ad.getImage2());
				imgs.add(img);	
			}

		    List<String> showL=new ArrayList<String>();
			if(CollectionUtils.isNotEmpty(ad.getTrackUrls())){
				showL.addAll(ad.getTrackUrls());
			 }
			 map.put(SHOW,showL);
			 List<String> clickL=new ArrayList<String>();
			 if(CollectionUtils.isNotEmpty(ad.getClickUrls())){
				clickL.addAll(ad.getClickUrls());
			 }
			 if(StringUtils.isNotEmpty(ad.getDsUrl())){
				 clickL.add(ad.getDsUrl());
			 }
			 
			 map.put(CLICK, clickL);
			 
			 List<String> downL=new ArrayList<String>();
			 if(StringUtils.isNotEmpty(ad.getDfUrl())){		
				downL.add(ad.getDfUrl());
			 }
			 map.put(DOWNLOAD, downL);
			 
			 List<String> installL=new ArrayList<String>();
			 if(StringUtils.isNotEmpty(ad.getSfUrl())){
				
				installL.add(ad.getSfUrl());
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
	@Override
	public AsynAbstractTask packageTask(Parameter params, String hashCode) {
		// TODO Auto-generated method stub
/*		AdProxyQidianTask task=new AdProxyQidianTask();
		QidianAdPullParams param = (QidianAdPullParams) params;*/
		Map<String, String> headers = new HashMap<String, String>(2);
		headers.put("Content-Type", "application/json");
		AdProxyQidianTask task=new AdProxyQidianTask();
		QidianAdPullParamsV2 param = (QidianAdPullParamsV2) params;
		StringBuilder buff=new StringBuilder(SystemContext.getDynamicPropertyHandler().get(ProxyConstants.QIDIAN_URL));
		//buff.append("?").append(Bean2Utils.toHttpParams(param)).append("&zkrequestId=").append(hashCode);
	
		task.setParam(param);
/*		task.setDataFormat(DataFormat.DATA_FORMAT_KVP);
		task.setInvokeType(InvokeType.INVOKE_TYPE_GET);*/
		task.setDataFormat(DataFormat.DATA_FORMAT_JSON);
		task.setInvokeType(InvokeType.INVOKE_TYPE_POST);
		task.setHeaders(headers);
		task.setHashCode(hashCode);
		task.setUrl(buff.toString());
		
		return task;
	}

}
