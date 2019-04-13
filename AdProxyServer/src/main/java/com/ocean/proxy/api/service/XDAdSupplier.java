package com.ocean.proxy.api.service;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inveno.util.CollectionUtils;
import com.ocean.persist.api.proxy.AdPullException;
import com.ocean.persist.api.proxy.xd.XDAd;
import com.ocean.persist.api.proxy.xd.XDAdPullParams;
import com.ocean.persist.api.proxy.xd.XDAdPullResponse;
import com.ocean.persist.api.proxy.xd.XDAdPuller;
import com.ocean.persist.api.proxy.xd.XDApp;
import com.ocean.persist.api.proxy.xd.XDDevice;
import com.ocean.persist.api.proxy.xd.XDNetwork;
import com.ocean.persist.api.proxy.xd.XDSlot;
import com.ocean.persist.api.proxy.xd.XDTracks;
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
      @date   2017年7月10日 
      @version 1.0 
 */
@Component(value="xdAdSupplier")
public class XDAdSupplier      extends AbstractAdSupplier{
	protected static final Map<String, Integer> xdMobiles = new HashMap<String, Integer>(3);
	private static final Map<String, Integer> xdconn = new HashMap<String, Integer>(4);

	static{

		xdMobiles.put("CMCC", 1);
		xdMobiles.put("CUCC", 3);
		xdMobiles.put("CTCC", 2);
		xdconn.put(NETWORK_2G, 2);
		xdconn.put(NETWORK_3G, 3);
		xdconn.put(NETWORK_4G, 4);
		xdconn.put(NETWORK_WIFI, 1);
	}
    @Autowired
	private XDAdPuller xdAdPuller;
	public AdRecomReply invoke(InvokeAttribute attribute)
			throws AdPullException {
		// TODO Auto-generated method stub

		AdRecomReq adreq = attribute.getAdreq();
		DSPPosition positionInfo = attribute.getDspPosition();
		// 参数转换
		XDAdPullParams params = parseParams(adreq, positionInfo);
		// 调用API
		XDAdPullResponse response = (XDAdPullResponse)xdAdPuller.api(params,adreq.getHash(),String.valueOf(adreq.getUserAdSpaceAttri().getAdSpaceId()));
		// 解析结果
		return parseResult(response);
	}
	private XDAdPullParams parseParams(AdRecomReq adreq, 
			DSPPosition positionInfo) throws AdPullException {
		if(StringUtils.isEmpty(positionInfo.getText1())||StringUtils.isEmpty(positionInfo.getText2())){
			throw new AdPullException("app id or channel id is empty!");
		}
		UserAdSpaceAttri attri=adreq.getUserAdSpaceAttri();
		AdUserInfo user=adreq.getUserinfo();
		XDAdPullParams param=new XDAdPullParams();
		param.setChannelId(positionInfo.getText2());
		//slot
		XDSlot slot=new XDSlot();
		slot.setAdspaceId(positionInfo.getPos());
	    slot.setAdspaceHeight(attri.getSpaceHeight());
	    slot.setAdspaceWidth(attri.getSpaceWidth());
	    param.setSlot(slot);
	    //app
	    XDApp app=new XDApp();
		app.setAppId(positionInfo.getText1());
		if(StringUtils.isNotEmpty(adreq.getApp())){
			app.setAppName(adreq.getApp());
		}
	    if(StringUtils.isNotEmpty(adreq.getVersion())){
	    	app.setAppVersion(adreq.getVersion());
	    }
	    param.setApp(app);
	    
	    //device
	    XDDevice device =new XDDevice();
	    device.setDeviceType(2);
		String osCode = user.getOs();
		// 如果是ios
		int osType=0;
	    if(OS_IOS.equals(osCode)){
			// ios 必填信息
	    	osType=1;
            device.setIdfa(user.getIdfa());
	    }else if(OS_ANDROID.equals(osCode)){
	    	osType=2;
	    	device.setImei(user.getImei());
	    	device.setAndroidId(user.getAdid());
		}
	    device.setMac(this.converMac(user.getMac()));
	    
	    device.setOsType(osType);
	    
	    device.setOsVersion(this.convOSVersion(user.getOsversion()));
	    device.setModel(user.getPhonemodel());
	    device.setVendor(user.getBrand_name());
	    device.setUserAgent(user.getUa());
	    device.setScreenHeight(user.getDevice_height());
	    device.setScreenWidth(user.getDevice_width());
	    param.setDevice(device);
	    
	    //network
	    XDNetwork network=new XDNetwork();
	    network.setIp(user.getClient_ip());
	    Integer op=xdMobiles.get(user.getMobile());
	    network.setOperatorType(op==null?0:op);
	    Integer net=xdconn.get(adreq.getNet());
	    network.setConnectionType(net==null?0:net);
	    param.setNetwork(network);
		return param;
	}
	private AdRecomReply parseResult(XDAdPullResponse response)
			throws AdPullException {
		
		if(response == null||CollectionUtils.isEmpty(response.getAds())){
			return null;
		}
		if(response.getCode()!=0){
			throw new AdPullException("XD ad request error,return error code:"+response.getCode()+",error msg:"+response.getMsg());
		}
		AdRecomReply reply=new AdRecomReply();
		reply.setStatus(status);
		reply.setAd_contents(this.dealAds(response));
		return reply;
	}
	private List<AdContent>  dealAds(XDAdPullResponse resp){
		List<AdContent> list=new ArrayList<AdContent> ();
		for(XDAd ad:resp.getAds()){
			//***********************BEGIN 常规设置*************
			AdContent content = new AdContent();
			AdMutiAction action = new AdMutiAction();
			String title = defTitle;
			String markTitle= defTitle;
			// 内容类型1表示链接推广,2表示应用推广,3未使用 4表示电话推广
	        int acType=ACTION_WEB;
	        
			//***********************END 常规设置***************
	        if(StringUtils.isNotEmpty(ad.getTitle())){
	        	title=ad.getTitle();
	             	
	        }
	        if(StringUtils.isNotEmpty(ad.getDesc())){
	        	markTitle=ad.getDesc();
	        }
	        
	        //img
	        List<AdImg> imgs = new ArrayList<AdImg>();
	        if(StringUtils.isNotEmpty(ad.getImageUrl())){
				AdImg img=new AdImg();
				img.setSrc(ad.getImageUrl());
				imgs.add(img);
				content.setImglist(imgs);
	        }
	        if(StringUtils.isNotEmpty(ad.getHtmlSnippet())){
	        	content.setIsHtmlAd(true);
	        	content.setHtmlSnippet(ad.getHtmlSnippet());
	        }
	        
	        //落地页
	        if(StringUtils.isNotEmpty(ad.getClickUrl())){
	        	content.setLinkurl(ad.getClickUrl());
	        	action.setLinkurl(ad.getClickUrl());
	        }
	        if(StringUtils.isNotEmpty(ad.getGdtClickUrl())){
	        	 String link=ad.getGdtClickUrl();
	        	 if(ad.getInteractionType()==2){//浏览
	     			
	    			String sufix=link.indexOf("?")<0?"?":"&";
	    			link+=sufix+"s={\"down_x\":\"%%DOWNX%%\",\"down_y\":\"%%DOWNY%%\",\"up_x\":\"%%UPX%%\",\"up_y\":\"%%UPY%%\"}";
	    			
	        	 }else if(ad.getInteractionType()==3){//下载
	        		 action.setLinkurl_type(1);
	        	 }
	         	content.setLinkurl(link);
	         	action.setLinkurl(link);
	        }
	        if(ad.getInteractionType()==3){
	        	acType=ACTION_APP;
	        }
	        
	        //上报
	        Map<String, List<String>> map = new HashMap<String, List<String>>();
	        List<String> showL=new ArrayList<String>();
	        List<String> clickL=new ArrayList<String>();
	        List<String> downloadL=new ArrayList<String>();
	        List<String> installL=new ArrayList<String>();
	        List<String> activeL=new ArrayList<String>();
	        List<XDTracks> tracks=ad.getTracks();
	        if(CollectionUtils.isNotEmpty(tracks)){
	        	for(XDTracks track :tracks){
	        		if(track.getType()==1){
	        			showL.add(track.getUrl());
	        			
	        		}else if(track.getType()==2){
	        			clickL.add(track.getUrl());
	        			
	        		}
	        		else if(track.getType()==3){
						
						downloadL.add(format(track.getUrl()));
						acType=ACTION_APP;
	        		}
	        		else if(track.getType()==4){
	        			installL.add(format(track.getUrl()));
	        			acType=ACTION_APP;
	        		}
	        		else if(track.getType()==5){
	        			activeL.add(format(track.getUrl()));
	        			acType=ACTION_APP;
	        		}
	        	}
	        }
	        map.put(SHOW, showL);
	        map.put(CLICK, clickL);
	        map.put(DOWNLOAD, downloadL);
	        map.put(INSTALL, installL);
	        map.put(ACTIVE, activeL);
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
	private static String format(String str){
		String format= str.replaceAll("\\#CLICK_POINT\\#","{\"down_x\":\"%%DOWNX%%\",\"down_y\":\"%%DOWNY%%\",\"up_x\":\"%%UPX%%\",\"up_y\":\"%%UPY%%\"}")
				.replaceAll("\\#CLICK_ID\\#", "%%CLICKID%%")
				.replaceAll("\\#DST_LINK\\#", "%%DSTLINK%%");

		return format;
	}
	public XDAdPuller getXdAdPuller() {
		return xdAdPuller;
	}
	public void setXdAdPuller(XDAdPuller xdAdPuller) {
		this.xdAdPuller = xdAdPuller;
	}

}
