package com.ocean.proxy.api.service;

import java.net.MalformedURLException;
import java.net.URL;
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
import com.ocean.persist.api.proxy.yileyun.YileyunAd;
import com.ocean.persist.api.proxy.yileyun.YileyunAdPullParams;
import com.ocean.persist.api.proxy.yileyun.YileyunAdResponse;
import com.ocean.persist.api.proxy.yileyun.YileyunAdSlot;
import com.ocean.persist.api.proxy.yileyun.YileyunApp;
import com.ocean.persist.api.proxy.yileyun.YileyunDevice;
import com.ocean.persist.api.proxy.yileyun.YileyunGeo;
import com.ocean.persist.api.proxy.yileyun.YileyunImage;
import com.ocean.persist.api.proxy.yileyun.YileyunMaterial;
import com.ocean.persist.api.proxy.yileyun.YileyunSize;
import com.ocean.persist.common.ProxyConstants;
import com.ocean.persist.model.proxy.DSPPosition;
import com.ocean.proxy.api.base.AbstractAsynAdSupplier;
import com.ocean.proxy.serverdis.AdProxyYileyunTask;
import com.ocean.proxy.thrift.entity.AdContent;
import com.ocean.proxy.thrift.entity.AdImg;
import com.ocean.proxy.thrift.entity.AdMutiAction;
import com.ocean.proxy.thrift.entity.AdRecomReply;
import com.ocean.proxy.thrift.entity.AdRecomReq;
import com.ocean.proxy.thrift.entity.AdSpaceType;
import com.ocean.proxy.thrift.entity.AdUserInfo;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年2月28日 
      @version 1.0 
 */
@Component(value="YileyunAdSupplier")
public class YileyunAdSupplier    extends AbstractAsynAdSupplier {

	 
	 
	 public AdRecomReply invoke(InvokeAttribute attribute)
				throws AdPullException {
			// TODO Auto-generated method stub
			AdRecomReq adreq = attribute.getAdreq();
			DSPPosition positionInfo = attribute.getDspPosition();
			// 参数转换
			YileyunAdPullParams params = parseParams(adreq, positionInfo);
			// 调用API
			YileyunAdResponse response = (YileyunAdResponse) invoke(params,YileyunAdResponse.class,adreq.getHash()
					,String.valueOf(adreq.getUserAdSpaceAttri().getAdSpaceId()));
			
			// 解析结果
			return parseResult(response);
		}

	private YileyunAdPullParams parseParams(AdRecomReq adreq, 
			DSPPosition positionInfo) throws AdPullException {
		if(StringUtils.isEmpty(positionInfo.getText1())){
			throw  new AdPullException("app id is empty!");
		}
		AdUserInfo userInfo=adreq.getUserinfo();
		YileyunAdPullParams param =new YileyunAdPullParams();
		param.setRequest_id(UUID.randomUUID().toString().replaceAll("-",""));
		param.setApi_version("1.4");
		param.setSource_type("app");
		param.setUa(userInfo.getUa());
		param.setIp(userInfo.getClient_ip());
		param.setUid(userInfo.getImei());
		//app
		YileyunApp app =new YileyunApp();
		app.setAppid(positionInfo.getText1());
		//gps
		if(StringUtils.isNotEmpty(userInfo.getLat())&&StringUtils.isNotEmpty(userInfo.getLon())){
			YileyunGeo gps=new YileyunGeo();

			gps.setLongitude(Float.parseFloat(userInfo.getLon()));
			gps.setLatitude(Float.parseFloat(userInfo.getLat()));
			app.setGeo(gps);
		}
		
		param.setApp(app);
		
		//device
		YileyunDevice device =new YileyunDevice();
		device.setDid("");
		device.setImei(userInfo.getImei());
		device.setAndroid_id(userInfo.getAdid());
		device.setType(1);
		device.setMccmnc(getOp(userInfo.getMobile()));
		String os = userInfo.getOs();
		if(OS_ANDROID.equals(os)){
			device.setOs(1);
			device.setOs_version(this.convOSVersion(userInfo.getOsversion()));
		}else{
			device.setOs(2);
			device.setDid(userInfo.getIdfa());
		}
		device.setLanguage("zh");
		device.setVendor(userInfo.getBrand_name());
		device.setModel(userInfo.getPhonemodel());
		
		
		device.setVendor(userInfo.getBrand_name());
		device.setModel(userInfo.getPhonemodel());
		device.setAndroid_id(userInfo.getAdid());
		device.setConn_type(getNetwork(adreq.getNet()));
		device.setMac(userInfo.getMac());
		device.setScreen_height(userInfo.getDevice_height());
		device.setScreen_width(userInfo.getDevice_width());
		param.setDevice(device);
		
		//adslot
		YileyunAdSlot slot=new YileyunAdSlot();
		slot.setId(positionInfo.getPos());
		int poseType = positionInfo.getPosType();
		if(poseType == AdSpaceType.BANNER.getValue()){
			slot.setAdtype(1);
			slot.setPos(1);
		}else if(poseType == AdSpaceType.INTERSTITIAL.getValue()){
			slot.setAdtype(3);
			slot.setPos(5);
		}/*else if(poseType == AdSpaceType.OPENING.getValue()){
			slot.setAdtype(3);
			slot.setPos(5);
		}*/else if(poseType == AdSpaceType.INFOFLOW.getValue()){
			slot.setAdtype(5);
			slot.setPos(4);
		}else{
			slot.setAdtype(3);
			slot.setPos(5);
		}
		YileyunSize size=new YileyunSize();
		size.setHeight(adreq.getUserAdSpaceAttri().getSpaceHeight());
		size.setWidth(adreq.getUserAdSpaceAttri().getSpaceWidth());
		slot.setAccepted_size(Collections.singletonList(size));
		slot.setAd_count(adreq.getResult_num());
		param.setAdslots(Collections.singletonList(slot));
	
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
			return 46000;
		}else if(MOBILE_CUCC.equals(op)){
			return 46001;
		}else if(MOBILE_CTCC.equals(op)){
			return 46002;
		}
		return 0;
	}
	private URL getUrl(String url){
		  try {
			return new URL(url);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  return null;
	}
	private AdRecomReply parseResult(YileyunAdResponse body)
			throws AdPullException {
		if(body == null||CollectionUtils.isEmpty(body.getAds())){
			return null;
		}
		if(20000!=body.getStatus_code()){
			throw new AdPullException("ad request error, return error code:"+body.getStatus_code());
		}
		AdRecomReply reply=new AdRecomReply();
		reply.setStatus(status);
		reply.setAd_contents(this.dealAds(body));
		return reply;
	}
	private List<AdContent>  dealAds(YileyunAdResponse resp){
		List<AdContent> list=new ArrayList<AdContent> ();
		for(YileyunAd ad:resp.getAds()){
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
	        YileyunMaterial mat=ad.getCreative();
	        if(mat==null){
	        	continue;
	        }
	        if((mat.getInteraction_type()==4||mat.getInteraction_type()==3)&&StringUtils.isNotEmpty(mat.getTarget_url())){

	    		content.setLinkurl(mat.getTarget_url());
	    		action.setLinkurl(mat.getTarget_url());
	        }else if(mat.getInteraction_type()==4&&StringUtils.isNotEmpty(mat.getDownload_url())){
	        	content.setLinkurl(mat.getDownload_url());
	    		action.setLinkurl(mat.getDownload_url());
	        }

	        if(mat.getInteraction_type()==4){
	        	acType=ACTION_APP;
	        }
			if(StringUtils.isNotEmpty(mat.getTitle())){
				title=mat.getTitle();
			}
			if(StringUtils.isNotEmpty(mat.getPackage_name())){
				action.setCpPackage(mat.getPackage_name());
			}

			if(mat.getImage()!=null){
	    			AdImg img=new AdImg();
	    			img.setSrc(mat.getImage().getUrl());
	    			imgs.add(img);	
				
			}
			for(YileyunImage yImg:mat.getImage_list()){
				AdImg img=new AdImg();
				img.setSrc(yImg.getUrl());
				imgs.add(img);	
			}
		    List<String> showL=new ArrayList<String>();
			if(CollectionUtils.isNotEmpty(mat.getShow_url())){
				
					showL.addAll(mat.getShow_url());
			 }
			 map.put(SHOW,showL);
			 List<String> clickL=new ArrayList<String>();
			 if(CollectionUtils.isNotEmpty(mat.getClick_url())){

		    		clickL.addAll(mat.getClick_url());
		
			 }
			 if(CollectionUtils.isNotEmpty(mat.getDs_url())){
				
					 clickL.addAll(mat.getDs_url());
			 }
			 
			 map.put(CLICK, clickL);
			 
			 List<String> downL=new ArrayList<String>();
			 if(CollectionUtils.isNotEmpty(mat.getDf_url())){
				 
					 downL.addAll(mat.getDf_url());
				 
				
			 }
			 map.put(DOWNLOAD, downL);
			 
			 List<String> installL=new ArrayList<String>();
			 if(CollectionUtils.isNotEmpty(mat.getSf_url())){
				 
					 installL.addAll(mat.getSf_url());
		     

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
		AdProxyYileyunTask task=new AdProxyYileyunTask();
		//heard
		Map<String, String> headers = new HashMap<String, String>(2);
		headers.put("Content-Type", "application/json");
		task.setHeaders(headers);
		YileyunAdPullParams param = (YileyunAdPullParams) params;

		task.setParam(param);
		task.setDataFormat(DataFormat.DATA_FORMAT_JSON);
		task.setInvokeType(InvokeType.INVOKE_TYPE_POST);
		task.setHashCode(hashCode);
		task.setUrl(SystemContext.getDynamicPropertyHandler().get(ProxyConstants.YILEYUN_URL));
		
		return task;
	}

}
