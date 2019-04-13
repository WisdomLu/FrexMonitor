package com.ocean.proxy.api.service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.protobuf.ByteString;
import com.inveno.util.CollectionUtils;
import com.ocean.core.common.system.SystemContext;
import com.ocean.persist.api.proxy.AdPullException;
import com.ocean.persist.api.proxy.zhuoyi.BaiduMobadsApi5;
import com.ocean.persist.api.proxy.zhuoyi.BaiduMobadsApi5.Device;
import com.ocean.persist.api.proxy.zhuoyi.BaiduMobadsApi5.Device.DeviceType;
import com.ocean.persist.api.proxy.zhuoyi.BaiduMobadsApi5.Device.OsType;
import com.ocean.persist.api.proxy.zhuoyi.BaiduMobadsApi5.MaterialMeta;
import com.ocean.persist.api.proxy.zhuoyi.BaiduMobadsApi5.MobadsRequest;
import com.ocean.persist.api.proxy.zhuoyi.BaiduMobadsApi5.MobadsResponse;
import com.ocean.persist.api.proxy.zhuoyi.BaiduMobadsApi5.MaterialMeta.InteractionType;
import com.ocean.persist.api.proxy.zhuoyi.BaiduMobadsApi5.Network.ConnectionType;
import com.ocean.persist.api.proxy.zhuoyi.BaiduMobadsApi5.Network.OperatorType;
import com.ocean.persist.api.proxy.zhuoyi.BaiduMobadsApi5.Size;
import com.ocean.persist.api.proxy.zhuoyi.BaiduMobadsApi5.Tracking;
import com.ocean.persist.api.proxy.zhuoyi.BaiduMobadsApi5.Tracking.TrackingEvent;
import com.ocean.persist.api.proxy.zhuoyi.BaiduMobadsApi5.UdId;
import com.ocean.persist.api.proxy.zhuoyi.BaiduMobadsApi5.Version;
import com.ocean.persist.api.proxy.zhuoyi.ZhuoyiAdPuller;
import com.ocean.persist.common.ProxyConstants;
import com.ocean.persist.model.proxy.DSPPosition;
import com.ocean.proxy.api.base.AbstractAdSupplier;
import com.ocean.proxy.thrift.entity.AdContent;
import com.ocean.proxy.thrift.entity.AdImg;
import com.ocean.proxy.thrift.entity.AdMutiAction;
import com.ocean.proxy.thrift.entity.AdRecomReply;
import com.ocean.proxy.thrift.entity.AdRecomReq;
import com.ocean.proxy.thrift.entity.AdUserInfo;
import com.ocean.proxy.thrift.entity.UserAdSpaceAttri;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年7月6日 
      @version 1.0 
 */
@Component(value="zhuoyiAdSupplier")
public class ZhuoyiAdSupplier     extends AbstractAdSupplier{
	protected static final Map<String, OperatorType> zyMobiles = new HashMap<String, OperatorType>(3);
	private static final Map<String, ConnectionType> conn = new HashMap<String, ConnectionType>();

	static{

		zyMobiles.put("CMCC", OperatorType.CHINA_MOBILE);
		zyMobiles.put("CUCC", OperatorType.CHINA_UNICOM);
		zyMobiles.put("CTCC", OperatorType.CHINA_TELECOM);
		conn.put(NETWORK_2G, ConnectionType.CELL_2G);
		conn.put(NETWORK_3G, ConnectionType.CELL_3G);
		conn.put(NETWORK_4G, ConnectionType.CELL_4G);
		conn.put(NETWORK_WIFI, ConnectionType.WIFI);
	}
    @Autowired
    private ZhuoyiAdPuller zhuoyiAdPuller;
	public AdRecomReply invoke(InvokeAttribute attribute)
			throws AdPullException {
		// TODO Auto-generated method stub

		AdRecomReq adreq = attribute.getAdreq();
		DSPPosition positionInfo = attribute.getDspPosition();
		// 参数转换
		MobadsRequest params = parseParams(adreq, positionInfo);
		// 调用API
		MobadsResponse response = (MobadsResponse)zhuoyiAdPuller.api(params,adreq.getHash(),String.valueOf(adreq.getUserAdSpaceAttri().getAdSpaceId()));
		// 解析结果
		return parseResult(response);
	}
	private MobadsRequest parseParams(AdRecomReq adreq, 
			DSPPosition positionInfo) throws AdPullException {
		if(StringUtils.isEmpty(positionInfo.getText1())||StringUtils.isEmpty(positionInfo.getText2())){
			throw new AdPullException("appid or package name is empty!");
		}
		AdUserInfo user=adreq.getUserinfo();
		UserAdSpaceAttri  atti=adreq.getUserAdSpaceAttri();
		
		MobadsRequest.Builder requestB=MobadsRequest.newBuilder();
		
		requestB.setRequestId(UUID.randomUUID().toString());
		requestB.setApiVersion(Version.newBuilder().setMajor(5).setMinor(2).setMicro(0).build());
		
		//app
		com.ocean.persist.api.proxy.zhuoyi.BaiduMobadsApi5.App.Builder appB=com.ocean.persist.api.proxy.zhuoyi.BaiduMobadsApi5.App.newBuilder();
		appB.setAppId(positionInfo.getText1());
		appB.setAppVersion(convertVersion(adreq.getVersion()));
		appB.setAppPackage(positionInfo.getText2());
		requestB.setApp(appB.build());
		//device
		
	    Device.Builder deviceB=	Device.newBuilder();
	    deviceB.setDeviceType(DeviceType.PHONE);
		String osCode = user.getOs();
		// 如果是ios

	    UdId.Builder udid=UdId.newBuilder();
	    if(OS_IOS.equals(osCode)){
			// ios 必填信息
	    	deviceB.setOsType(OsType.IOS);
	    	udid.setIdfa(user.getIdfa());
	    	udid.setIdfaMd5(DigestUtils.md5Hex(user.getIdfa()));

	    }else if(OS_ANDROID.equals(osCode)){
	    	deviceB.setOsType(OsType.ANDROID);
	    	udid.setImei(user.getImei());
	    	udid.setMac(this.converMac(user.getMac()));
	    	udid.setImeiMd5(DigestUtils.md2Hex(user.getImei()));
	    	udid.setAndroidId(user.getAdid());
	    	udid.setAndroididMd5(DigestUtils.md5Hex(user.getAdid()));
		}
	    deviceB.setUdid(udid.build());
	    deviceB.setOsVersion(this.convertVersion(user.getOsversion()));
	    try {
			deviceB.setVendor(ByteString.copyFrom(user.getBrand_name(), "UTF-8"));
			deviceB.setModel(ByteString.copyFrom(user.getPhonemodel(), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    requestB.setDevice(deviceB.build());
	    
	    //Network
	    com.ocean.persist.api.proxy.zhuoyi.BaiduMobadsApi5.Network netWork=com.ocean.persist.api.proxy.zhuoyi.BaiduMobadsApi5.Network.newBuilder()
	    		.setIpv4(user.getClient_ip()).setConnectionType(conn.get(adreq.getNet())==null?ConnectionType.CELL_UNKNOWN:conn.get(adreq.getNet()))
	    		.setOperatorType(zyMobiles.get(user.getMobile())==null?OperatorType.UNKNOWN_OPERATOR:zyMobiles.get(user.getMobile()))
	    		.build();
	    requestB.setNetwork(netWork);
	    
	    //adSlot
	    com.ocean.persist.api.proxy.zhuoyi.BaiduMobadsApi5.AdSlot.Builder adB=com.ocean.persist.api.proxy.zhuoyi.BaiduMobadsApi5.AdSlot.newBuilder();
	    adB.setAdslotId(positionInfo.getPos());
	    adB.setAdslotSize(Size.newBuilder().setHeight(atti.getSpaceHeight()).setWidth(atti.getSpaceWidth()));
	    requestB.setAdslot(adB.build());
	    int isTest=SystemContext.getDynamicPropertyHandler().getInt(ProxyConstants.ZHUOYI_IS_TEST,0);
	    requestB.setIsDebug(isTest==0?false:true);
	    
	    
		return requestB.build();
	}
	private  Version convertVersion(String version) throws AdPullException{
	    if(StringUtils.isEmpty(version)){
	    	throw new AdPullException("app version is empty!");
	    }
	    String vArr[]=version.split("\\.");
	    Version.Builder vb= Version.newBuilder() ;
	    vb.setMajor(Integer.parseInt(vArr[0]));
	    if(vArr.length==1){
	    	vb.setMinor(0);
        	vb.setMicro(0);
	    }
	    else if(vArr.length==2){
        	vb.setMinor(Integer.parseInt(vArr[1]));
        	vb.setMicro(0);
        }else if(vArr.length==3){
        	vb.setMinor(Integer.parseInt(vArr[1]));
        	vb.setMicro(Integer.parseInt(vArr[2]));
        }
	    		
	    return vb.build();
         
		
	}
	private AdRecomReply parseResult(MobadsResponse response)
			throws AdPullException {
		
		if(response == null||CollectionUtils.isEmpty(response.getAdsList())){
			return null;
		}
		if(response.getErrorCode()!=0){
			throw new AdPullException("zhuoyi ad request error,return error code:"+response.getErrorCode());
		}
		AdRecomReply reply=new AdRecomReply();
		reply.setStatus(status);
		reply.setAd_contents(this.dealAds(response));
		return reply;
	}
	private List<AdContent>  dealAds(MobadsResponse resp){
		List<AdContent> list=new ArrayList<AdContent> ();
		for(BaiduMobadsApi5.Ad ad:resp.getAdsList()){
			
			//***********************BEGIN 常规设置*************
			AdContent content = new AdContent();
			AdMutiAction action = new AdMutiAction();
			String title = defTitle;
			String markTitle= defTitle;
			// 内容类型1表示链接推广,2表示应用推广,3未使用 4表示电话推广
		 
	        int acType=ACTION_WEB;
			//***********************END 常规设置***************
	      //上报
	        Map<String, List<String>> map = new HashMap<String, List<String>>();
	        List<String> showL=new  ArrayList<String>();

	        if(!ad.getHtmlSnippet().isEmpty()){
	        	content.setIsHtmlAd(true);
	        	try {
					content.setHtmlSnippet(ad.getHtmlSnippet().toString("UTF-8"));
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }else    if(ad.getMaterialMeta()!=null){
	        	MaterialMeta meta=ad.getMaterialMeta();
	        	if(meta.getInteractionType()==InteractionType.DOWNLOAD){
	        		acType=ACTION_APP;
	        	}
	        	if(CollectionUtils.isNotEmpty(meta.getWinNoticeUrlList())){

	        		
	        		showL.addAll(meta.getWinNoticeUrlList());
	        	}
	        	String link=meta.getClickUrl();
	        	if(StringUtils.isNotEmpty(link)){
	            	content.setLinkurl(link);
	            	action.setLinkurl(link);
	        	}
	        	if(StringUtils.isNotEmpty(meta.getAdTitle())){
	        		title=meta.getAdTitle();
	        	}
	        	if(CollectionUtils.isNotEmpty(meta.getDescriptionList())){
	        		try {
						markTitle=meta.getDescription(0).toString("UTF-8");
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        	}
	        	
	        	//img

	    		List<AdImg> imgs = new ArrayList<AdImg>();
	        	if(CollectionUtils.isNotEmpty(meta.getImageSrcList())){
	        		for(String src:meta.getImageSrcList()){
	    				AdImg img=new AdImg();
	    				img.setSrc(src);
	    				imgs.add(img);
	        		}
	        	}
	        	content.setImglist(imgs);
	        }
	        if(CollectionUtils.isNotEmpty(ad.getAdTrackingList())){
	        	for(Tracking trac:ad.getAdTrackingList()){
	        		if(trac.getTrackingEvent()==TrackingEvent.AD_EXPOSURE){
	        			showL.addAll(trac.getTrackingUrlList());
	        			
	        		}else if(trac.getTrackingEvent()==TrackingEvent.AD_CLICK){
	        			map.put(CLICK, trac.getTrackingUrlList());
	        		}else if(trac.getTrackingEvent()==TrackingEvent.APP_AD_DOWNLOAD){
	        			map.put(DOWNLOAD, trac.getTrackingUrlList());
	        			acType=ACTION_APP;
	        		}else if(trac.getTrackingEvent()==TrackingEvent.APP_AD_INSTALL){
	        			map.put(INSTALL, trac.getTrackingUrlList());
	        			acType=ACTION_APP;
	        		}else if(trac.getTrackingEvent()==TrackingEvent.APP_AD_ACTIVE){
	        			map.put(ACTIVE, trac.getTrackingUrlList());
	        			acType=ACTION_APP;
	        		}
	        		
	        	}
	        }
	        map.put(SHOW, showL);
	        content.setThirdReportLinks(map);
			
		    action.setType(acType); 
		    action.setGuideTitle(title);
			content.setMarketTitle(markTitle);
			content.setGuideTitle(title);
			content.setMutiAction(Collections.singletonList(action));

			list.add(content);
		}
		return list;
	}
	public ZhuoyiAdPuller getZhuoyiAdPuller() {
		return zhuoyiAdPuller;
	}
	public void setZhuoyiAdPuller(ZhuoyiAdPuller zhuoyiAdPuller) {
		this.zhuoyiAdPuller = zhuoyiAdPuller;
	}

}
