package com.ocean.proxy.api.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.inveno.util.CollectionUtils;
import com.ocean.core.common.system.SystemContext;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.persist.api.proxy.AdPullException;
import com.ocean.persist.api.proxy.zk.ZKAdEventTrack;
import com.ocean.persist.api.proxy.zk.ZKAdImg;
import com.ocean.persist.api.proxy.zk.ZKAdNative;
import com.ocean.persist.api.proxy.zk.ZKAdPullParams;
import com.ocean.persist.api.proxy.zk.ZKAdPullResponse;
import com.ocean.persist.api.proxy.zk.ZKAdSpace;
import com.ocean.persist.api.proxy.zk.ZKAdTile;
import com.ocean.persist.api.proxy.zk.ZKAdVideo;
import com.ocean.persist.api.proxy.zk.ZKAdm;
import com.ocean.persist.api.proxy.zk.ZKAppInfo;
import com.ocean.persist.api.proxy.zk.ZKCarrierType;
import com.ocean.persist.api.proxy.zk.ZKCreative;
import com.ocean.persist.api.proxy.zk.ZKDeviceID;
import com.ocean.persist.api.proxy.zk.ZKDeviceIDHashType;
import com.ocean.persist.api.proxy.zk.ZKDeviceIDType;
import com.ocean.persist.api.proxy.zk.ZKDeviceInfo;
import com.ocean.persist.api.proxy.zk.ZKDeviceType;
import com.ocean.persist.api.proxy.zk.ZKGpsInfo;
import com.ocean.persist.api.proxy.zk.ZKGpsType;
import com.ocean.persist.api.proxy.zk.ZKInteractionObject;
import com.ocean.persist.api.proxy.zk.ZKNetworkInfo;
import com.ocean.persist.api.proxy.zk.ZKNetworkType;
import com.ocean.persist.api.proxy.zk.ZKSCell;
import com.ocean.persist.common.ProxyConstants;
import com.ocean.persist.model.proxy.DSPPosition;
import com.ocean.proxy.api.base.AbstractAsynAdSupplier;
import com.ocean.proxy.serverdis.AdProxyZhangKuTask;
import com.ocean.proxy.thrift.entity.AdContent;
import com.ocean.proxy.thrift.entity.AdImg;
import com.ocean.proxy.thrift.entity.AdMutiAction;
import com.ocean.proxy.thrift.entity.AdRecomReply;
import com.ocean.proxy.thrift.entity.AdRecomReq;
import com.ocean.proxy.thrift.entity.AdUserInfo;
import com.ocean.proxy.thrift.entity.AdVid;
import com.ocean.proxy.thrift.entity.UserAdSpaceAttri;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
import com.ocean.core.common.threadpool.workthread.Task.DataFormat;
import com.ocean.core.common.threadpool.workthread.Task.InvokeType;
@Component(value="zkAdSupplier")
public class ZKAdSupplier  extends AbstractAsynAdSupplier {
	protected static final Map<String, ZKCarrierType> zkMobiles = new HashMap<String, ZKCarrierType>(3);
	private static final Map<String, ZKNetworkType> conn = new HashMap<String, ZKNetworkType>();
	static{
		zkMobiles.put("CMCC", ZKCarrierType.CHINA_MOBILE);
		zkMobiles.put("CUCC", ZKCarrierType.CHINA_UNICOM);
		zkMobiles.put("CTCC", ZKCarrierType.CHINA_TELECOM);
		
		conn.put(NETWORK_2G, ZKNetworkType.MOBILE_2G);
		conn.put(NETWORK_3G, ZKNetworkType.MOBILE_3G);
		conn.put(NETWORK_4G, ZKNetworkType.MOBILE_4G);
		conn.put(NETWORK_WIFI, ZKNetworkType.WIFI);
	}
	

/*	@Autowired
	private ZKAdPuller zkAdPuller;*/
	public AdRecomReply invoke(InvokeAttribute attribute)
			throws AdPullException {
		// TODO Auto-generated method stub
		AdRecomReq adreq = attribute.getAdreq();
		DSPPosition positionInfo = attribute.getDspPosition();
		// 参数转换
		ZKAdPullParams params = parseParams(adreq, positionInfo);
		// 调用API
		//ZKAdPullResponse response = (ZKAdPullResponse)zkAdPuller.api(params,adreq.getHash(),String.valueOf(adreq.getUserAdSpaceAttri().getAdSpaceId()));
		ZKAdPullResponse response = (ZKAdPullResponse)invoke(params,ZKAdPullResponse.class,adreq.getHash(),String.valueOf(adreq.getUserAdSpaceAttri().getAdSpaceId()),"joinDSP:zhangku");
		// 解析结果
		return parseResult(response);
	}
	private ZKAdPullParams parseParams(AdRecomReq adreq, 
			DSPPosition positionInfo) throws AdPullException {
		
		
		if(StringUtils.isEmpty(positionInfo.getText1())||StringUtils.isEmpty(positionInfo.getText2())){
		    throw new 	AdPullException("app id or package name is empty!");
		}
		ZKAdPullParams param=new ZKAdPullParams();
		AdUserInfo userInfo=adreq.getUserinfo();
		UserAdSpaceAttri  attri=adreq.getUserAdSpaceAttri();
		
		param.setBid(DigestUtils.md5Hex(UUID.randomUUID().toString().replaceAll("-", "")));
		param.setApi_version("1.0");
		param.setUa(userInfo.getUa());
		//APP
		ZKAppInfo app = new ZKAppInfo();
		app.setApp_id(positionInfo.getText1());
		app.setApp_version(adreq.getVersion());
		if(StringUtils.isEmpty(adreq.getApp())){
			app.setApp_name(adreq.getApp());
		}
		app.setPackage_name(positionInfo.getText2());
		param.setApp(app);
		
		//device
		
		ZKDeviceInfo dev = new ZKDeviceInfo();
		List<ZKDeviceID> ids = new ArrayList<ZKDeviceID>();
		
		if(StringUtils.isNotEmpty(userInfo.getImei())){
			ZKDeviceID devID = new ZKDeviceID();
			devID.setDevice_id(userInfo.getImei());
			devID.setHash_type(ZKDeviceIDHashType.NONE.getValue());
			devID.setDevice_id_type(ZKDeviceIDType.IMEI.getValue());
			ids.add(devID);
		}
		if(StringUtils.isNotEmpty(userInfo.getAaid())){
			ZKDeviceID devID = new ZKDeviceID();
			devID.setDevice_id(userInfo.getAaid());
			devID.setHash_type(ZKDeviceIDHashType.NONE.getValue());
			devID.setDevice_id_type(ZKDeviceIDType.AAID.getValue());
			ids.add(devID);
		}
		if(StringUtils.isNotEmpty(userInfo.getImsi())){
			ZKDeviceID devID = new ZKDeviceID();
			devID.setDevice_id(userInfo.getImsi());
			devID.setHash_type(ZKDeviceIDHashType.NONE.getValue());
			devID.setDevice_id_type(ZKDeviceIDType.IMSI.getValue());
			ids.add(devID);
		}

		if(StringUtils.isNotEmpty(userInfo.getMac())){
			ZKDeviceID devID = new ZKDeviceID();
			devID.setDevice_id(userInfo.getMac());
			devID.setHash_type(ZKDeviceIDHashType.NONE.getValue());
			devID.setDevice_id_type(ZKDeviceIDType.MAC.getValue());
			ids.add(devID);
		}
		if(StringUtils.isNotEmpty(userInfo.getSn())){
			ZKDeviceID devID = new ZKDeviceID();
			devID.setDevice_id(userInfo.getSn());
			devID.setHash_type(ZKDeviceIDHashType.NONE.getValue());
			devID.setDevice_id_type(ZKDeviceIDType.SERIALID.getValue());
			ids.add(devID);
		}
		

		dev.setDevice_id(ids);
		String osCode = userInfo.getOs();
		// 如果是ios
	    if(OS_IOS.equals(osCode)){
			// ios 必填信息
	    	dev.setOs_type(1);

	    }else if(OS_ANDROID.equals(osCode)){
	    	dev.setOs_type(2);

		}else{
			dev.setOs_type(0);
		}
	    
		dev.setOs_version(this.convOSVersion(userInfo.getOsversion()));
		dev.setBrand(userInfo.getBrand_name());
		dev.setModel(userInfo.getPhonemodel());
		dev.setDevice_type(ZKDeviceType.PHONE.getValue());
		dev.setLanguage("zh_CN");
		dev.setScreen_width(userInfo.getDevice_width());
		dev.setScreen_height(userInfo.getDevice_height());
		if(StringUtils.isNotEmpty(userInfo.getDip())){
			dev.setScreen_density(Double.parseDouble(userInfo.getDip()));//客户端传过来的dip是原始值
		}
		
		dev.setOs_api_level(userInfo.getOs_api_level());
		param.setDevice(dev);
		
		//network
		ZKNetworkInfo network = new ZKNetworkInfo();
		network.setIp(userInfo.getClient_ip());
		ZKNetworkType net=conn.get(adreq.getNet());
		network.setNetwork_type(net==null?ZKNetworkType.UNKNOWN.getValue():net.getValue());
		ZKCarrierType carr=zkMobiles.get(userInfo.getMobile());
		network.setCarrier_id(carr==null?ZKCarrierType.CHINA_MOBILE.getValue():carr.getValue());
		
		ZKSCell scell=new ZKSCell();
		if(userInfo.isSetCid()&&userInfo.isSetLac()){
			scell.setLAC(userInfo.getLac());
			scell.setCID(userInfo.getCid());
		}
		
		
		
		param.setNetwork(network);
		//gps
		if(StringUtils.isNotEmpty(userInfo.getLat())&&StringUtils.isNotEmpty(userInfo.getLon())){
			ZKGpsInfo gps = new ZKGpsInfo();
			gps.setGpsType(ZKGpsType.WGS84);
			gps.setLongitude(Double.parseDouble(userInfo.getLon()));
			gps.setLatitude(Double.parseDouble(userInfo.getLat()));
			gps.setTimestamp(new Date().getTime());
		}

		//adspace
		List<ZKAdSpace> adSpaces = new ArrayList<ZKAdSpace>();
		ZKAdSpace adSpace = new ZKAdSpace();
		adSpace.setAdspace_id(positionInfo.getPos());
		adSpace.setAdspace_type(positionInfo.getPosType());
		adSpace.setWidth(attri.getSpaceWidth());
		adSpace.setHeight(attri.getSpaceHeight());
		adSpace.setAdNum(adreq.getResult_num());
		adSpace.setImpression_num(adreq.getResult_num());
		adSpace.setOpen_type(0);
		//adSpace.setChannel("AlphaGo");
		adSpace.setInteraction_type(Arrays.asList(0));
		adSpace.setPage_id("page_1");
		if(attri.getH5type()!=2){
			adSpace.setAllowed_html(true);
		}else{
			adSpace.setAllowed_html(false);
		}
		
		adSpaces.add(adSpace);
		
		//gps
		if(StringUtils.isNotEmpty(userInfo.getLat())&&StringUtils.isNotEmpty(userInfo.getLon())){
			ZKGpsInfo gps=new ZKGpsInfo();
			gps.setLatitude(Double.parseDouble(userInfo.getLat()));
			gps.setLongitude(Double.parseDouble(userInfo.getLon()));
			param.setGps(gps);
		}
		
		param.setAdspaces(adSpaces);
		return param;
	}
	private AdRecomReply parseResult(ZKAdPullResponse response)
			throws AdPullException {
		
		if(response == null||CollectionUtils.isEmpty(response.getAds())){
			return null;
		}
		if(!"0".equals(response.getError_code())){
			throw new AdPullException("ad request error,error msg:"+response.getError_msg()+",error code:"+response.getError_code());
		}

		AdRecomReply reply=new AdRecomReply();
		reply.setStatus(status);
		reply.setAd_contents(this.dealAds(response));
		return reply;
	}
	private List<AdContent>  dealAds(ZKAdPullResponse resp){
		List<AdContent> list=new ArrayList<AdContent> ();
		
		for(ZKCreative creat:resp.getAds().get(0).getCreative()){
			//***********************BEGIN 常规设置*************
			AdContent content = new AdContent();
			AdMutiAction action = new AdMutiAction();
			String title = defTitle;
			String marketTitle=defTitle;
			// 内容类型1表示链接推广,2表示应用推广,3未使用 4表示电话推广
		 
	        int acType=ACTION_WEB;
			//***********************END 常规设置***************
	       
	        if(creat.getAdm_type()!=1&&creat.getInteraction_type()==3){
	        	acType=ACTION_APP;
	        	
	        }
	        if(StringUtils.isNotEmpty(creat.getPackage_name())){
	         	acType=ACTION_APP;
	        	
	        	action.setCpPackage(creat.getPackage_name());
	        }
	        
			List<AdImg> imgs = new ArrayList<AdImg>();
			ZKAdm adm=creat.getAdm();
			content.setDisable_dynamic(creat.getDisable_dynamic());
			if(StringUtils.isNotEmpty(adm.getHtml_id())){
				content.setHtmlId(adm.getHtml_id());
			}
	        if(creat.getAdm_type()==1){
	        	content.setIsHtmlAd(true);
	        	content.setHtmlSnippet(adm.getSource());
	        }else if(creat.getAdm_type()==0){
	    		AdImg img = new AdImg();
	    		img.setSrc(adm.getSource());
	    		imgs.add(img);
	        }else if(creat.getAdm_type()==3){
	    		ZKAdNative nat=adm.getNativ();
	    		if(nat!=null){
	    			ZKAdImg zkImg=nat.getImg();
	    			if(zkImg!=null&&StringUtils.isNotEmpty(zkImg.getUrl())){
	    				AdImg img = new AdImg();
	    				img = new AdImg();
	    	    		img.setSrc(zkImg.getUrl());
	    	    		img.setWidth(zkImg.getWidth());
	    	    		img.setHeight(zkImg.getHeight());
	    	    		imgs.add(img);
	    			}
	    			if(StringUtils.isNotEmpty(nat.getSmallImg())){
	    				action.setCpIcon(nat.getSmallImg());
	    				
	    			}
	    			
	    			
	    			ZKAdTile zkT=nat.getTitle();
	    			if(zkT!=null&&StringUtils.isNotEmpty(zkT.getText())){
	    				title=zkT.getText();
	    			}
	    			if(StringUtils.isNotEmpty(nat.getDesc())){
	    				marketTitle=nat.getDesc();
	    			}
	    			
	    			if(nat.getVideo()!=null){
	    				ZKAdVideo video=nat.getVideo();
	    				AdVid adVid=new AdVid();
	    				adVid.setSrc(video.getVideoUrl());
	    				adVid.setDuration(video.getDuration());
	    				content.setAdVid(adVid);
	    			    content.setLinkurl(video.getVideoUrl());
	    				action.setLinkurl(video.getVideoUrl());
	    				acType=ACTION_VIDEO;
	    			}
	    		}
	        }
	        //落地页
	    	ZKInteractionObject io=creat.getInteraction_object();
	    	content.setLinkurl(io.getUrl());
	    	action.setLinkurl(io.getUrl());
	        action.setCpApkMd5(io.getFile_md5());
	        action.setCpApkSize(io.getFile_size());
	    	//上报
	    	Map<String, List<String>> map = new HashMap<String, List<String>>();
	    	List<ZKAdEventTrack> tracks=creat.getEvent_track();
	    	List<String> showR=new ArrayList<String>();
	    	List<String> clickR=new ArrayList<String>();
	    	List<String> downLodoadR=new ArrayList<String>();
	    	List<String> installR=new ArrayList<String>();
	    	List<String> activeR=new ArrayList<String>();
	    	if(CollectionUtils.isNotEmpty(tracks)){
	    		for(ZKAdEventTrack track:tracks ){
	    			if(track.getEvent_type()==1){
	    				showR.add(track.getNotify_url());
	    			}
	    			if(track.getEvent_type()==2){
	    				clickR.add(track.getNotify_url());
	    			}
	    			if(track.getEvent_type()==4){
	    				acType=ACTION_APP;
	    				downLodoadR.add(track.getNotify_url());
	    			}
	    			if(track.getEvent_type()==5){
	    				acType=ACTION_APP;
	    				installR.add(track.getNotify_url());
	    			}
	    			if(track.getEvent_type()==6){
	    				acType=ACTION_APP;
	    				activeR.add(track.getNotify_url());
	    			}
	    		}
	    	}
	    	map.put(SHOW, showR);
	    	map.put(CLICK, clickR);
	    	map.put(DOWNLOAD, downLodoadR);
	    	map.put(INSTALL, installR);
	    	map.put(ACTIVE, activeR);
	        
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
	@Override
	public AsynAbstractTask packageTask(Parameter params, String hashCode) {
		// TODO Auto-generated method stub
		AdProxyZhangKuTask task=new AdProxyZhangKuTask();
		Map<String, String> headers = new HashMap<String, String>(2);
		headers.put("Content-Type", "application/json");
		
		ZKAdPullParams param = (ZKAdPullParams) params;
		
		task.setHeaders(headers);
		task.setParam(param);
		task.setDataFormat(DataFormat.DATA_FORMAT_JSON);
		task.setInvokeType(InvokeType.INVOKE_TYPE_POST);
		task.setHashCode(hashCode);
		task.setUrl(SystemContext.getDynamicPropertyHandler().get(ProxyConstants.ZHANGKU_URL));
		return  task;
	}
}
