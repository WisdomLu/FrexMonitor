package com.ocean.proxy.api.service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.protobuf.ByteString;
import com.inveno.util.CollectionUtils;
import com.ocean.core.common.system.ErrorCode;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.persist.api.proxy.AdPullException;
import com.ocean.persist.api.proxy.diankai.DiankaiAd;
import com.ocean.persist.api.proxy.diankai.DiankaiAdPullResponse;
import com.ocean.persist.api.proxy.yincheng.YcmModel;
import com.ocean.persist.api.proxy.yincheng.YcmModel.Network.ConnectionType;
import com.ocean.persist.api.proxy.yincheng.YcmModel.Network.OperatorType;
import com.ocean.persist.api.proxy.yincheng.YinchengAdPuller;
import com.ocean.persist.model.proxy.DSPPosition;
import com.ocean.proxy.api.base.AbstractAdSupplier;
import com.ocean.proxy.thrift.entity.AdContent;
import com.ocean.proxy.thrift.entity.AdImg;
import com.ocean.proxy.thrift.entity.AdMutiAction;
import com.ocean.proxy.thrift.entity.AdRecomReply;
import com.ocean.proxy.thrift.entity.AdRecomReq;
import com.ocean.proxy.thrift.entity.AdUserInfo;
import com.ocean.proxy.thrift.entity.UserAdSpaceAttri;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;


/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年6月12日 
      @version 1.0 
 */
@Component(value="yinchengAdSupplier")
public class YinchengAdSupplier extends AbstractAdSupplier{
	private static final Map<String, YcmModel.Network.ConnectionType> conn = new HashMap<String, YcmModel.Network.ConnectionType>(5);
	private static final Map<String, YcmModel.Network.OperatorType> mobile = new HashMap<String, YcmModel.Network.OperatorType>();
	static{
		conn.put(NETWORK_2G, ConnectionType.CELL_2G);
		conn.put(NETWORK_3G, ConnectionType.CELL_3G);
		conn.put(NETWORK_4G, ConnectionType.CELL_4G);
		conn.put(NETWORK_5G, ConnectionType.CELL_5G);
		conn.put(NETWORK_WIFI, ConnectionType.WIFI);
		
		mobile.put("CMCC", OperatorType.CHINA_MOBILE);
		mobile.put("CUCC", OperatorType.CHINA_UNICOM);
		mobile.put("CTCC", OperatorType.CHINA_TELECOM);
	}
    @Autowired
    private YinchengAdPuller yinchengAdPuller;
	public AdRecomReply invoke(InvokeAttribute attribute)
			throws AdPullException {
		AdRecomReq adreq = attribute.getAdreq();
		DSPPosition positionInfo = attribute.getDspPosition();
		// 参数转换
		YcmModel.MobadsRequest params = parseParams(adreq, positionInfo);
		
		YcmModel.MobadsResponse response = (YcmModel.MobadsResponse) yinchengAdPuller.api(params,adreq.getHash(),String.valueOf(adreq.getUserAdSpaceAttri().getAdSpaceId()));
		
		return parseResult(response,adreq.getUserAdSpaceAttri());
	}
	private YcmModel.MobadsRequest parseParams(AdRecomReq adreq, 
			DSPPosition positionInfo) throws AdPullException {
		AdUserInfo  userInfo=adreq.getUserinfo();
		if(StringUtils.isEmpty(positionInfo.getText1())||StringUtils.isEmpty(positionInfo.getText2())){
			throw new AdPullException(ErrorCode.PARAM_ERROR,"app id or app package name is empty!");
		}
		//version
		YcmModel.Version version=	YcmModel.Version.newBuilder().setMajor(1).setMinor(0).setMicro(0).build();
		//app
		YcmModel.Version.Builder appVerB=YcmModel.Version.newBuilder();
		if(StringUtils.isNotEmpty(adreq.getVersion())){
			try{
				String appVer=adreq.getVersion();
		        String appVerArr[]=appVer.split("\\.");
		        
		        for(int i=0;i<appVerArr.length;i++){
		        	if(i==0) appVerB.setMajor(Integer.parseInt(appVerArr[0]));
		        	if(i==1) appVerB.setMinor(Integer.parseInt(appVerArr[1]));
		        	if(i==2) appVerB.setMicro(Integer.parseInt(appVerArr[2]));
		        }
			}catch(Exception e){
				appVerB.setMajor(2);
	        	appVerB.setMinor(0);
	        	appVerB.setMicro(0);
			}
		}else{
			throw new AdPullException("app version is empty!");
		}

		

       
        
        YcmModel.App app=YcmModel.App.newBuilder().setAppVersion(appVerB.build())
		.setAppKey(positionInfo.getText1()).setAppPackageName(positionInfo.getText2()).build();
		
		//device 
		YcmModel.UdId.Builder udidB=YcmModel.UdId.newBuilder();
		   String osCode = userInfo.getOs();
			// 如果是ios
		   int os = 1;
		   if(OS_IOS.equals(osCode)){
				os=2;
				udidB.setIdfa(userInfo.getIdfa());
		   }else{
			   udidB.setImei(userInfo.getImei());
			   udidB.setMac(userInfo.getMac());
			   udidB.setImeiMd5(DigestUtils.md5Hex(userInfo.getImei()));
			   if(StringUtils.isNotEmpty(userInfo.getAdid())){
				   udidB.setAndroidId(userInfo.getAdid());
			   }
			   if(StringUtils.isNotEmpty(userInfo.getAaid())){
				   udidB.setAdvertisingId(userInfo.getAaid());
			   }
			  
		   }
		   
		   
	    YcmModel.Device.Builder deviceB=YcmModel.Device.newBuilder();
		try {
			if(StringUtils.isNotEmpty(userInfo.getBrand_name())){
				ByteString vender = ByteString.copyFrom(userInfo.getBrand_name(), "UTF-8");
				deviceB.setVendor(vender);
			}
			if(StringUtils.isNotEmpty(userInfo.getBrand_id())){
				ByteString model=ByteString.copyFrom(userInfo.getBrand_id(), "UTF-8");
				deviceB.setModel(model);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new AdPullException(ErrorCode.PARAM_ERROR,"vender/model is empty!");
		}
	    
		
		deviceB.setDeviceType(YcmModel.Device.DeviceType.PHONE);
		deviceB.setOsType(os==1?YcmModel.Device.OsType.ANDROID:YcmModel.Device.OsType.IOS);
		deviceB.setUdid(udidB.build());
		deviceB.setScreenSize(YcmModel.Size.newBuilder().setHeight(userInfo.getDevice_height())
				.setWidth(userInfo.getDevice_width()));
		deviceB.setYcScreenSize(YcmModel.YcSize.newBuilder().setHeight(userInfo.getDevice_height())
				.setWidth(userInfo.getDevice_width()));
		
		//network
		YcmModel.Network.Builder networkB=YcmModel.Network.newBuilder();
		networkB.setConnectionType(getNetWork(adreq.getNet()));
		if(StringUtils.isNotEmpty(userInfo.getMobile())){
			OperatorType operatorType=mobile.get(userInfo.getMobile());
			networkB.setOperatorType(operatorType==null?OperatorType.UNKNOWN_OPERATOR:operatorType);
		}else{
			networkB.setOperatorType(OperatorType.UNKNOWN_OPERATOR);
		}
		

		
		YcmModel.MobadsRequest.Builder requestBuilder=YcmModel.MobadsRequest.newBuilder();
		requestBuilder.setAdspacekey(positionInfo.getPos());
		requestBuilder.setSdkVersion(version);
		requestBuilder.setDevice(deviceB.build());
		requestBuilder.setNetwork(networkB.build());
		requestBuilder.setApp(app);
		//GPS
		if(StringUtils.isNotEmpty(userInfo.getLat())&&StringUtils.isNotEmpty(userInfo.getLon())){
			
			requestBuilder.setGps(
					YcmModel.Gps.newBuilder().setCoordinateType(YcmModel.Gps.CoordinateType.BD09)
					.setLongitude(Double.parseDouble(userInfo.getLon()))
					.setLatitude(Double.parseDouble(userInfo.getLat()))
					.setTimestamp((int)(System.currentTimeMillis()/1000)).build());
		}
		requestBuilder.setClientIp(userInfo.getClient_ip());
		requestBuilder.setDbName("bc");
		requestBuilder.setNoDefaultAds(1);
		requestBuilder.setFeedsSdkRender(1);
		return requestBuilder.build();
	}
	private YcmModel.Network.ConnectionType getNetWork(String net){
		try{
			YcmModel.Network.ConnectionType ct=conn.get(net);
			return ct==null?YcmModel.Network.ConnectionType.CONNCTION_UNKNOWN:ct;
		}catch(Exception e){
			return YcmModel.Network.ConnectionType.CONNCTION_UNKNOWN;
		}

		
	}
	private AdRecomReply parseResult(YcmModel.MobadsResponse response,UserAdSpaceAttri    attri)
			throws AdPullException {
		if(response==null|CollectionUtils.isEmpty(response.getAdsList())){
			return null;
		}
		if(response.getErrorCode()!=0){
			throw new AdPullException("ad return error,third DSP return code is "+response.getErrorCode());
		}
		AdRecomReply reply=new AdRecomReply();
		reply.setStatus(status);
		reply.setAd_contents(this.dealAds(response));
		return reply;
	}
	private List<AdContent>  dealAds(YcmModel.MobadsResponse resp){
		List<AdContent> list=new ArrayList<AdContent> ();
		for(YcmModel.Ad ad:resp.getAdsList()){

			//***********************BEGIN 常规设置*************
			AdContent content = new AdContent();
			AdMutiAction action = new AdMutiAction();
			String title = defTitle;
			// 内容类型1表示链接推广,2表示应用推广,3未使用 4表示电话推广
		 
	        int acType=ACTION_WEB;
			//***********************END 常规设置***************
			if(!ad.getHtmlSnippet().isEmpty()){
				try {
					
					content.setHtmlSnippet(ad.getHtmlSnippet().toString("UTF-8"));
					content.setIsHtmlAd(true);
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				YcmModel.Creative creative=ad.getCreativeInfo();
				List<String> imgL=creative.getSourceUrlsList();
				List<AdImg> imgs = new ArrayList<AdImg>();
				for(int i=0;imgL!=null&&i<imgL.size();i++){
					
					AdImg img = new AdImg();
					img.setSrc(imgL.get(i));
					imgs.add(img);

				}
				content.setImglist(imgs);
				if(StringUtils.isNotEmpty(creative.getClickUrl())){
					action.setLinkurl(creative.getClickUrl());
					content.setLinkurl(creative.getClickUrl());
				}
				
				//上报
				Map<String, List<String>> map = new HashMap<String, List<String>>();
		

				if(!creative.getImpressionUrlList().isEmpty()){

					map.put(SHOW, creative.getImpressionUrlList());
				}
				if(!creative.getClickTrackUrlList().isEmpty()){

					map.put(CLICK,creative.getClickTrackUrlList());
				}
				if(!creative.getDownloadCompleteList().isEmpty()){
					acType=ACTION_APP;
					map.put(DOWNLOAD, creative.getDownloadCompleteList());
				}
				
				content.setThirdReportLinks(map);
				if(StringUtils.isNotEmpty(creative.getTitle())){
					title=creative.getTitle();
					if(StringUtils.isNotEmpty(creative.getAdText())){
						//content.setMarketTitle(URLDecoder.decode(creative.getAdText().replaceAll("%", "%25"), "UTF-8"));
						content.setMarketTitle(creative.getAdText());
					}

				}
	 		}
			
			//
			    action.setType(acType); 
				action.setGuideTitle(title);
				content.setGuideTitle(title);
				content.setMutiAction(Collections.singletonList(action));
				list.add(content);
		}
		return list;
		
	}
}
