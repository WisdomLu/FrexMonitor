package com.ocean.proxy.api.service;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.inveno.util.CollectionUtils;
import com.ocean.core.common.JsonUtils;
import com.ocean.core.common.UniversalUtils;
import com.ocean.core.common.system.SystemContext;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
import com.ocean.core.common.threadpool.workthread.Task.DataFormat;
import com.ocean.core.common.threadpool.workthread.Task.InvokeType;
import com.ocean.persist.api.proxy.AdPullException;
import com.ocean.persist.api.proxy.wangxiang.WangxiangAd;
import com.ocean.persist.api.proxy.wangxiang.WangxiangAdPullParams;
import com.ocean.persist.api.proxy.wangxiang.WangxiangAdPullResponse;
import com.ocean.persist.api.proxy.wangxiang.WangxiangAdslot;
import com.ocean.persist.api.proxy.wangxiang.WangxiangApp;
import com.ocean.persist.api.proxy.wangxiang.WangxiangData;
import com.ocean.persist.api.proxy.wangxiang.WangxiangDevice;
import com.ocean.persist.api.proxy.wangxiang.WangxiangGps;
import com.ocean.persist.api.proxy.wangxiang.WangxiangNetwork;
import com.ocean.persist.api.proxy.wangxiang.WangxiangUdid;
import com.ocean.persist.api.proxy.wangxiang.WangxiangVersion;
import com.ocean.persist.api.proxy.wangxiang.WangxinagSize;
import com.ocean.persist.api.proxy.yuansheng2.YuanshengAdPullParams;
import com.ocean.persist.api.proxy.yuansheng2.YuanshengAdPullResponse;
import com.ocean.persist.api.proxy.yuansheng2.YuanshengImag;
import com.ocean.persist.api.proxy.yuansheng2.YuanshengImp;
import com.ocean.persist.api.proxy.yuansheng2.YuanshengWord;
import com.ocean.persist.common.ProxyConstants;
import com.ocean.persist.model.proxy.DSPPosition;
import com.ocean.proxy.api.base.AbstractAsynAdSupplier;
import com.ocean.proxy.serverdis.AdProxyWangxiangTask;
import com.ocean.proxy.serverdis.AdProxyYuanshenTask;
import com.ocean.proxy.thrift.entity.AdContent;
import com.ocean.proxy.thrift.entity.AdImg;
import com.ocean.proxy.thrift.entity.AdMutiAction;
import com.ocean.proxy.thrift.entity.AdRecomReply;
import com.ocean.proxy.thrift.entity.AdRecomReq;
import com.ocean.proxy.thrift.entity.AdUserInfo;
import com.ocean.proxy.thrift.entity.UserAdSpaceAttri;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年10月27日 
      @version 1.0 
 */
@Component(value="WangxiangAdSupplier")
public class WangxiangAdSupplier   extends AbstractAsynAdSupplier {

		 public static final String WX_MACRO_DOWN_X = "__DOWN_X__";
		 public static final String WX_MACRO_DOWN_Y = "__DOWN_Y__";
		 public static final String WX_MACRO_UP_X = "__UP_X__";
		 public static final String WX_MACRO_UP_Y = "__UP_Y__";
		 public static final String WX_MACRO_REQ_WIDTH = "__REQ_WIDTH__";
		 public static final String WX_MACRO_REQ_HEIGHT = "__REQ_HEIGHT__";
		 public static final String WX_MACRO_REL_WIDTH = "__WIDTH__";
		 public static final String WX_MACRO_REL_HEIGHT = "__HEIGHT__";

	public AdRecomReply invoke(InvokeAttribute attribute)
			throws AdPullException {
		// TODO Auto-generated method stub
		AdRecomReq adreq = attribute.getAdreq();
		DSPPosition positionInfo = attribute.getDspPosition();
		// 参数转换
		WangxiangAdPullParams params = parseParams(adreq, positionInfo);
		// 调用API
		WangxiangAdPullResponse response = (WangxiangAdPullResponse) invoke(params,YuanshengAdPullResponse.class,adreq.getHash()
				,String.valueOf(adreq.getUserAdSpaceAttri().getAdSpaceId()));
		
		// 解析结果
		return parseResult(response);
	}

	private WangxiangAdPullParams parseParams(AdRecomReq adreq, 
			DSPPosition positionInfo) throws AdPullException {
		if(StringUtils.isEmpty(positionInfo.getText1())||StringUtils.isEmpty(positionInfo.getText2())){
			throw  new AdPullException("appId  or appKey is empty!");
		}
		AdUserInfo userInfo=adreq.getUserinfo();
		UserAdSpaceAttri  attr=adreq.getUserAdSpaceAttri();
		WangxiangAdPullParams param =new WangxiangAdPullParams();
		String sign=DigestUtils.md5Hex(positionInfo.getText2()+positionInfo.getText1());
		param.setApptoken(positionInfo.getText1());
		param.setSign(sign);
		WangxiangData data=new WangxiangData();
		//app
		WangxiangApp app=new WangxiangApp();
		if(StringUtils.isNotEmpty(adreq.getVersion())){
			WangxiangVersion appV=new WangxiangVersion();
			try{
				
				String appVer=adreq.getVersion();
		        String appVerArr[]=appVer.split("\\.");
		        
		        for(int i=0;i<appVerArr.length;i++){
		        	if(i==0) appV.setMajor(appVerArr[0]);
		        	if(i==1) appV.setMinor(appVerArr[1]);
		        	if(i==2) appV.setMicro(appVerArr[2]);
		        }
			}catch(Exception e){
				appV.setMajor("2");
				appV.setMinor("0");
				appV.setMicro("0");
			}
			app.setApp_version(appV);
		}
		data.setApp(app);
		
		//adslot
		WangxiangAdslot slot=new WangxiangAdslot();
		slot.setAdslot_id(positionInfo.getPos());
		WangxinagSize slotSize=new WangxinagSize();
		slotSize.setHeight(attr.getSpaceHeight());
		slotSize.setWidth(attr.getSpaceWidth());
		slot.setAdslot_size(slotSize);
		data.setAdslot(slot);
		
		//device
		WangxiangDevice device=new WangxiangDevice();
		device.setDevice_type(1);
		String os = userInfo.getOs();
		WangxiangUdid udid=new WangxiangUdid();
		if(OS_ANDROID.equals(os)){
			device.setOs_type(1);
			udid.setAndroid_id(userInfo.getAdid());
			udid.setImei(userInfo.getImei());
			udid.setMac(userInfo.getMac());
		}else{
			device.setOs_type(2);	
			udid.setIdfa(userInfo.getIdfa());
		}
		device.setUdid(udid);
		if(StringUtils.isNotEmpty(userInfo.getOsversion())){
			WangxiangVersion osV=new WangxiangVersion();
			try{
				
				String osVer=this.convOSVersion(userInfo.getOsversion());
		        String osVerArr[]=osVer.split("\\.");
		        
		        for(int i=0;i<osVerArr.length;i++){
		        	if(i==0) osV.setMajor(osVerArr[0]);
		        	if(i==1) osV.setMinor(osVerArr[1]);
		        	if(i==2) osV.setMicro(osVerArr[2]);
		        }
			}catch(Exception e){
				osV.setMajor("5");

			}
			device.setOs_version(osV);
		}
		try {
			device.setVendor(URLEncoder.encode(userInfo.getBrand_name(), CHARSET_CODER));
			device.setModel(URLEncoder.encode(userInfo.getPhonemodel(), CHARSET_CODER));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WangxinagSize screenSize=new WangxinagSize();
		screenSize.setHeight(userInfo.getDevice_height());
		
		screenSize.setWidth(userInfo.getDevice_width());
		device.setScreen_size(screenSize);
		data.setDevice(device);
		
		
		//network
		WangxiangNetwork netWork=new WangxiangNetwork();
		netWork.setIpv4(userInfo.getClient_ip());
		netWork.setConnection_type(getNetwork(adreq.getNet()));
		netWork.setOperator_type(getOp(userInfo.getMobile()));
		data.setNetwork(netWork);
		
		//gps
		if(StringUtils.isNotEmpty(userInfo.getLat())&&StringUtils.isNotEmpty(userInfo.getLon())){
			WangxiangGps gps=new WangxiangGps();
			gps.setLatitude(userInfo.getLat());
			gps.setLongitude(userInfo.getLon());
			data.setGps(gps);
		}
		
		param.setData(data);
		return param;
	}
	private  String getNetwork(String net){	
		if(NETWORK_WIFI.equals(net)){
			return "100";
		}else if(NETWORK_2G.equals(net)){
			return "2";
		}
		else if(NETWORK_3G.equals(net)){
			return "3";
		}
		else if(NETWORK_4G.equals(net)){
			return "4";
		}
		return "0";
	}
	private String getOp(String op){

		if(MOBILE_CMCC.equals(op)){
			return "1";
		}else if(MOBILE_CUCC.equals(op)){
			return "2";
		}else if(MOBILE_CTCC.equals(op)){
			return "3";
		}
		return "0";
	}
	private AdRecomReply parseResult(WangxiangAdPullResponse body)
			throws AdPullException {
		if(body == null){
			return null;
		}
		if(body.getError_code()!=0){
			throw new AdPullException("ad request error, return error code:"+body.getError_code());
			
		}
		
		WangxiangAd ad=body.getWxad();

		AdRecomReply reply=new AdRecomReply();
		reply.setStatus(status);
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
        
        if(StringUtils.isNotEmpty(ad.getImage_src())){

    			AdImg img=new AdImg();
    			img.setSrc(ad.getImage_src());
    			imgs.add(img);


        }
        if(ad.getInteraction_type()==6||ad.getInteraction_type()==7){
    		content.setLinkurl(format(ad.getStrLinkUrl()));
    		action.setLinkurl(format(ad.getStrLinkUrl()));
        }else{
    		content.setLinkurl(ad.getStrLinkUrl());
    		action.setLinkurl(ad.getStrLinkUrl());
        }

		
		if((ad.getInteraction_type()>1&&ad.getInteraction_type()<6)||ad.getInteraction_type()==7){
			acType=ACTION_APP;
		}
		if(StringUtils.isNotEmpty(ad.getApp_package())){
			action.setCpPackage(ad.getApp_package());
		}
		
		if(CollectionUtils.isNotEmpty(ad.getOther_notice_url())){
			 map.put(SHOW, ad.getOther_notice_url());
		 }
		 List<String> clickL=new ArrayList<String>();
		 if(CollectionUtils.isNotEmpty(ad.getOther_click_url())){
			 clickL.addAll(ad.getOther_click_url());
		 }
		 if(CollectionUtils.isNotEmpty(ad.getArrDownloadTrackUrl())){
			 for(String url:ad.getArrDownloadTrackUrl()){
				 if(ad.getInteraction_type()==7){
					 clickL.add(url.replaceAll("\\$\\{CLICKID\\}", MACRO_CLICK_ID));
					 action.setLinkurl_type(1);
				 }else{
					 clickL.add(url);
				 }
			 }
		
		 }
		 
		 map.put(CLICK, clickL);
		 
		 List<String> downL=new ArrayList<String>();
		 if(CollectionUtils.isNotEmpty(ad.getArrDownloadedTrakUrl())){
			 for(String url:ad.getArrDownloadedTrakUrl()){
				 if(ad.getInteraction_type()==7){
					 downL.add(url.replaceAll("\\$\\{CLICKID\\}", MACRO_CLICK_ID));
					 action.setLinkurl_type(1);
				 }else{
					 downL.add(url);
				 }
			 }
		 }
		 if(CollectionUtils.isNotEmpty(ad.getArrIntallTrackUrl())){
			 for(String url:ad.getArrIntallTrackUrl()){
				 if(ad.getInteraction_type()==7){
					 downL.add(url.replaceAll("\\$\\{CLICKID\\}", MACRO_CLICK_ID));
					 action.setLinkurl_type(1);
				 }else{
					 downL.add(url);
				 }
			 }
			
		 }
		 map.put(DOWNLOAD, downL);
		 
		 List<String> installL=new ArrayList<String>();
		 if(CollectionUtils.isNotEmpty(ad.getArrIntalledTrackUrl())){
			 for(String url:ad.getArrIntalledTrackUrl()){
				 if(ad.getInteraction_type()==7){
					 installL.add(url.replaceAll("\\$\\{CLICKID\\}", MACRO_CLICK_ID));
				 }else{
					 installL.add(url);
				 }
			 }
			 
		 }
		 map.put(INSTALL,installL); 
		
		content.setThirdReportLinks(map);
		//img
		content.setImglist(imgs);
	    action.setType(acType); 
		content.setMarketTitle(marketTitle);
		content.setGuideTitle(title);
		content.setMutiAction(Collections.singletonList(action));

		reply.setAd_contents(Collections.singletonList(content));
		return reply;
	}
	private static String format(String str){

		String format= str.replaceAll(WX_MACRO_DOWN_X, MACRO_DOWN_X).replaceAll(WX_MACRO_DOWN_Y,MACRO_DOWN_Y)
				.replaceAll(WX_MACRO_UP_X, MACRO_UP_X).replaceAll(WX_MACRO_UP_Y, MACRO_UP_Y)
				.replaceAll(WX_MACRO_REQ_WIDTH, MACRO_REQ_WIDTH).replaceAll(WX_MACRO_REQ_HEIGHT, MACRO_REQ_HEIGHT)
				.replaceAll(WX_MACRO_REL_HEIGHT, MACRO_REL_HEIGHT).replaceAll(WX_MACRO_REL_WIDTH, MACRO_REL_WIDTH);

		return format;
	}
	@Override
	public AsynAbstractTask packageTask(Parameter params, String hashCode) {
		// TODO Auto-generated method stub
		AdProxyWangxiangTask task=new AdProxyWangxiangTask();
		//heard
		Map<String, String> headers = new HashMap<String, String>(2);
		headers.put("Content-Type", "application/json");
		task.setHeaders(headers);
		WangxiangAdPullParams param = (WangxiangAdPullParams) params;

		task.setParam(param);
		task.setDataFormat(DataFormat.DATA_FORMAT_JSON);
		task.setInvokeType(InvokeType.INVOKE_TYPE_POST);
		task.setHashCode(hashCode);
		task.setUrl(SystemContext.getDynamicPropertyHandler().get(ProxyConstants.WANGXIANG_URL));
		
		return task;
	}


}
