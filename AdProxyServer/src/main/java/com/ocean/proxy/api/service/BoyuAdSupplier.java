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
import com.ocean.persist.api.proxy.borui.BoruiBidRespBid;
import com.ocean.persist.api.proxy.boyu.BoyuAd;
import com.ocean.persist.api.proxy.boyu.BoyuAdPullParams;
import com.ocean.persist.api.proxy.boyu.BoyuAdPullResponse;
import com.ocean.persist.api.proxy.youken.MD5Utils;
import com.ocean.persist.common.ProxyConstants;
import com.ocean.persist.model.proxy.DSPPosition;
import com.ocean.proxy.api.base.AbstractAsynAdSupplier;
import com.ocean.proxy.serverdis.AdProxyBoyuTask;
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
@Component(value="BoyuAdSupplier")
public class BoyuAdSupplier    extends AbstractAsynAdSupplier {

	 public AdRecomReply invoke(InvokeAttribute attribute)
				throws AdPullException {
			// TODO Auto-generated method stub
			AdRecomReq adreq = attribute.getAdreq();
			DSPPosition positionInfo = attribute.getDspPosition();
			// 参数转换
			BoyuAdPullParams params = parseParams(adreq, positionInfo);
			// 调用API
			BoyuAdPullResponse response = (BoyuAdPullResponse) invoke(params,BoyuAdPullResponse.class,adreq.getHash()
					,String.valueOf(adreq.getUserAdSpaceAttri().getAdSpaceId()));
			
			// 解析结果
			return parseResult(response);
		}

	private BoyuAdPullParams parseParams(AdRecomReq adreq, 
			DSPPosition positionInfo) throws AdPullException {
		if(StringUtils.isEmpty(positionInfo.getText1())||StringUtils.isEmpty(positionInfo.getText2())){
			throw  new AdPullException("requestid prefix /token is empty!");
		}
		AdUserInfo userInfo=adreq.getUserinfo();
		BoyuAdPullParams param =new BoyuAdPullParams();
		//String requestIdPre=SystemContext.getDynamicPropertyHandler().get(ProxyConstants.Boyu_REQUESTID_PREFIX);
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
		param.setUa(userInfo.getUa());
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

	private AdRecomReply parseResult(BoyuAdPullResponse body)
			throws AdPullException {
		if(body == null||null==body.getData()||CollectionUtils.isEmpty(body.getData().getAdList())){
			return null;
		}
		if(0!=body.getCode()){
			throw new AdPullException("ad request error, return error code:"+body.getCode()+" msg:"+body.getMessage());
		}
		AdRecomReply reply=new AdRecomReply();
		reply.setStatus(status);


		reply.setAd_contents(dealAds(body));
		return reply;
	}
	private List<AdContent>  dealAds(BoyuAdPullResponse body){
		List<AdContent> list=new ArrayList<AdContent> ();
		
		for(BoyuAd ad:body.getData().getAdList()){
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
		AdProxyBoyuTask task=new AdProxyBoyuTask();
		BoyuAdPullParams param = (BoyuAdPullParams) params;

		Map<String, String> headers = new HashMap<String, String>(2);
		headers.put("User-Agent", param.getUa());
		task.setHeaders(headers);
		
		param.setUa("");
		StringBuilder buff=new StringBuilder(SystemContext.getDynamicPropertyHandler().get(ProxyConstants.BOYU_URL));
		buff.append("?").append(Bean2Utils.toHttpParams(param)).append("&zkrequestId=").append(hashCode);
		task.setParam(param);
		task.setDataFormat(DataFormat.DATA_FORMAT_KVP);
		task.setInvokeType(InvokeType.INVOKE_TYPE_GET);
		task.setHashCode(hashCode);
		task.setUrl(buff.toString());
		
		return task;
	}

}
