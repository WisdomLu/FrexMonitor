package com.ocean.proxy.api.service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.inveno.util.CollectionUtils;
import com.ocean.core.common.system.SystemContext;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
import com.ocean.core.common.threadpool.workthread.Task.DataFormat;
import com.ocean.core.common.threadpool.workthread.Task.InvokeType;
import com.ocean.persist.api.proxy.AdPullException;
import com.ocean.persist.api.proxy.diankai.DiankaiAd;
import com.ocean.persist.api.proxy.diankai.DiankaiAdPullResponse;
import com.ocean.persist.api.proxy.wangyue.WangyueAd;
import com.ocean.persist.api.proxy.wangyue.WangyueAdPullParams;
import com.ocean.persist.api.proxy.wangyue.WangyueAdPullResponse;
import com.ocean.persist.api.proxy.wangyue.WangyueSub;
import com.ocean.persist.common.ProxyConstants;
import com.ocean.persist.model.proxy.DSPPosition;
import com.ocean.proxy.api.base.AbstractAsynAdSupplier;
import com.ocean.proxy.serverdis.AdProxyWangyueTask;
import com.ocean.proxy.thrift.entity.AdContent;
import com.ocean.proxy.thrift.entity.AdImg;
import com.ocean.proxy.thrift.entity.AdMutiAction;
import com.ocean.proxy.thrift.entity.AdRecomReply;
import com.ocean.proxy.thrift.entity.AdRecomReq;
import com.ocean.proxy.thrift.entity.AdSpaceType;
import com.ocean.proxy.thrift.entity.AdUserInfo;
import com.ocean.proxy.thrift.entity.UserAdSpaceAttri;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年10月27日 
      @version 1.0 
 */
@Component(value="WangyueAdSupplier")
public class WangyueAdSupplier   extends AbstractAsynAdSupplier {
	 private static final String REQUEST_PREFIX="AA00000001";
	 public static final String WY_MACRO_DOWN_X = "IT_CLK_PNT_DOWN_X";
	 public static final String WY_MACRO_DOWN_Y = "IT_CLK_PNT_DOWN_Y";
	 public static final String WY_MACRO_UP_X = "IT_CLK_PNT_UP_X";
	 public static final String WY_MACRO_UP_Y = "IT_CLK_PNT_UP_Y";
	 public static final String WY_MACRO_TS="$TS";
	 public AdRecomReply invoke(InvokeAttribute attribute)
			throws AdPullException {
		// TODO Auto-generated method stub
		AdRecomReq adreq = attribute.getAdreq();
		DSPPosition positionInfo = attribute.getDspPosition();
		// 参数转换
		WangyueAdPullParams params = parseParams(adreq, positionInfo);
		// 调用API
		WangyueAdPullResponse response = (WangyueAdPullResponse) invoke(params,WangyueAdPullResponse.class,adreq.getHash()
				,String.valueOf(adreq.getUserAdSpaceAttri().getAdSpaceId()));
		
		// 解析结果
		return parseResult(response);
	}

	private WangyueAdPullParams parseParams(AdRecomReq adreq, 
			DSPPosition positionInfo) throws AdPullException {
		if(StringUtils.isEmpty(positionInfo.getText3())){
			throw  new AdPullException("pkg name is empty!");
		}
		AdUserInfo userInfo=adreq.getUserinfo();
		UserAdSpaceAttri  attr=adreq.getUserAdSpaceAttri();
		WangyueAdPullParams param =new WangyueAdPullParams();
		param.setRequestId(REQUEST_PREFIX+UUID.randomUUID().toString().replaceAll("-", ""));
		param.setVersionId("1.0");
		param.setBatch(1);
		param.setAdid(positionInfo.getPos());
		
		WangyueSub subInfo=new WangyueSub();
		subInfo.setPcat("15");
		subInfo.setPkgname(positionInfo.getText3());
		subInfo.setAppName(adreq.getApp());
		subInfo.setAppVersion(adreq.getVersion());
		subInfo.setNetworkType(getNetwork(adreq.getNet()));
		int poseType = positionInfo.getPosType();
		if(poseType == AdSpaceType.BANNER.getValue()){
			subInfo.setAdtype(2);
		}else if(poseType == AdSpaceType.INTERSTITIAL.getValue()){
			subInfo.setAdtype(5);
		}else if(poseType == AdSpaceType.OPENING.getValue()){
			subInfo.setAdtype(6);
		}else if(poseType == AdSpaceType.INFOFLOW.getValue()){
			subInfo.setAdtype(3);
		}
		subInfo.setCarrier(getOp(userInfo.getMobile()));
		String os = userInfo.getOs();
		if(OS_ANDROID.equals(os)){
			subInfo.setOsType(0);
			subInfo.setImei(userInfo.getImei());
			subInfo.setAndoidId(userInfo.getAdid());
		}else{
			subInfo.setOsType(1);
			subInfo.setIdfa(userInfo.getIdfa());
		}
		subInfo.setImsi(userInfo.getImsi());
		subInfo.setOsVersion(this.convOSVersion(userInfo.getOsversion()));
		subInfo.setDeType("1");
		subInfo.setDeMake(userInfo.getBrand_name());
		subInfo.setDeBrand(userInfo.getBrand_name());
		subInfo.setDeModel(userInfo.getPhonemodel());
		subInfo.setUa(userInfo.getUa());
		subInfo.setIp(userInfo.getClient_ip());
		subInfo.setWidth(attr.getSpaceWidth());
		subInfo.setHeight(attr.getSpaceHeight());
		subInfo.setDeWidth(userInfo.getDevice_width());
		subInfo.setDeHeight(userInfo.getDevice_height());
		subInfo.setDensity(userInfo.getDensity());
		subInfo.setMac(userInfo.getMac());
		if(StringUtils.isNotEmpty(userInfo.getLat())&&StringUtils.isNotEmpty(userInfo.getLon())){
			subInfo.setDeLatitude(userInfo.getLat());
			subInfo.setDeLongitude(userInfo.getLon());
			
		}
		param.setSubInfo(subInfo);
		
		return param;
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
	private AdRecomReply parseResult(WangyueAdPullResponse body)
			throws AdPullException {
		if(body == null||CollectionUtils.isEmpty(body.getAds())){
			return null;
		}
		if(!"1000".equals(body.getReturnCode())){
			throw new AdPullException("ad request error, return error code:"+body.getReturnCode()+" msg:"+body.getReturnMsg());
			
		}
		AdRecomReply reply=new AdRecomReply();
		reply.setStatus(status);
		reply.setAd_contents(this.dealAds(body));
		return reply;
	}
	private List<AdContent>  dealAds(WangyueAdPullResponse resp){
		List<AdContent> list=new ArrayList<AdContent> ();
		for(WangyueAd ad:resp.getAds()){
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
	        
	        if(CollectionUtils.isNotEmpty(ad.getImgurls())){
	        	for(String url:ad.getImgurls()){
	    			AdImg img=new AdImg();
	    			img.setSrc(url);
	    			imgs.add(img);	
	        	}
	        }
	        if(StringUtils.isNotEmpty(ad.getClickurl())){
	        	String link=format(REPORT_TYPE_REQ,ad.getClickurl());
	    		content.setLinkurl(link);
	    		action.setLinkurl(link);
	        }
	        if(StringUtils.isNotEmpty(ad.getDisplayTitle())){
	        	title = ad.getDisplayTitle();
	        }
			
			if("download".equals(ad.getAdType())){
				acType=ACTION_APP;
			}
			if(StringUtils.isNotEmpty(ad.getPkgname())){
				action.setCpPackage(ad.getPkgname());
			}
			 List<String> showL=new ArrayList<String>();
			if(CollectionUtils.isNotEmpty(ad.getShowFollowUrls())){
				for(String url:ad.getShowFollowUrls()){
					showL.add(format(REPORT_TYPE_PV,url));
				}
				
			 }
			 map.put(SHOW,showL);
			 List<String> clickL=new ArrayList<String>();
			 if(CollectionUtils.isNotEmpty(ad.getClickFollowUrls())){
				
				for(String url:ad.getClickFollowUrls()){
					clickL.add(format(REPORT_TYPE_CLICK,url));
				}
			 }
			 if(StringUtils.isNotEmpty(ad.getInst_downstart_url())){
			
			     clickL.add(ad.getInst_downstart_url());
			
			 }
			 
			 map.put(CLICK, clickL);
			 
			 List<String> downL=new ArrayList<String>();
			 if(StringUtils.isNotEmpty(ad.getInst_downsucc_url())){
				 downL.add(ad.getInst_downsucc_url());
			 }
			 if(StringUtils.isNotEmpty(ad.getInst_installstart_url())){

				downL.add(ad.getInst_installstart_url());
			 }
			 map.put(DOWNLOAD, downL);
			 
			 List<String> installL=new ArrayList<String>();
			 if(StringUtils.isNotEmpty(ad.getInst_installsucc_url())){

			     installL.add(ad.getInst_installsucc_url());

			 }
			 map.put(INSTALL,installL); 
			
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
	private static String format(int type, String str){
		if(type!=REPORT_TYPE_PV){
			str= str.replaceAll(WY_MACRO_DOWN_X, MACRO_DOWN_X).replaceAll(WY_MACRO_DOWN_Y,MACRO_DOWN_Y)
					.replaceAll(WY_MACRO_UP_X, MACRO_UP_X).replaceAll(WY_MACRO_UP_Y, MACRO_UP_Y);
		}
		String format=str.replaceAll(WY_MACRO_TS, MACRO_TIME_MS);
				
		return format;
	}
	@Override
	public AsynAbstractTask packageTask(Parameter params, String hashCode) {
		// TODO Auto-generated method stub
		AdProxyWangyueTask task=new AdProxyWangyueTask();
		//heard
		Map<String, String> headers = new HashMap<String, String>(2);
		headers.put("Content-Type", "application/json");
		task.setHeaders(headers);
		WangyueAdPullParams param = (WangyueAdPullParams) params;

		task.setParam(param);
		task.setDataFormat(DataFormat.DATA_FORMAT_JSON);
		task.setInvokeType(InvokeType.INVOKE_TYPE_POST);
		task.setHashCode(hashCode);
		task.setUrl(SystemContext.getDynamicPropertyHandler().get(ProxyConstants.WANGYUE_URL));
		
		return task;
	}


}
