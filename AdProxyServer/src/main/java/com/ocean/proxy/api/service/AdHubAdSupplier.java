package com.ocean.proxy.api.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.ocean.persist.api.proxy.adhub.AdHubAdPuller;
import com.ocean.persist.api.proxy.adhub.AdRequest;
import com.ocean.persist.api.proxy.adhub.AdResponseOuterClass;
import com.ocean.persist.api.proxy.adhub.AdResponseOuterClass.AdContentInfo;
import com.ocean.persist.api.proxy.adhub.AdResponseOuterClass.AdContentSlot;
import com.ocean.persist.api.proxy.adhub.AdResponseOuterClass.AdLogo;
import com.ocean.persist.api.proxy.adhub.AdResponseOuterClass.AdResponse;
import com.ocean.persist.api.proxy.adhub.AdResponseOuterClass.DetectInfo;
import com.ocean.persist.api.proxy.adhub.AdResponseOuterClass.SpaceInfo;
import com.ocean.persist.api.proxy.adhub.AdhubTemplate;
import com.ocean.persist.api.proxy.adhub.CommonInfo;
import com.ocean.persist.api.proxy.adhub.CommonInfo.DeviceInfo;
import com.ocean.persist.api.proxy.adhub.EnumType.AdpType;
import com.ocean.persist.api.proxy.adhub.EnumType.DeviceType;
import com.ocean.persist.api.proxy.adhub.EnumType.IspType;
import com.ocean.persist.api.proxy.adhub.EnumType.NetType;
import com.ocean.persist.api.proxy.adhub.EnumType.PlatformType;
import com.ocean.persist.api.proxy.adhub.EnumType.RenderType;
import com.ocean.persist.api.proxy.adhub.EnumType.ReqType;
import com.ocean.persist.api.proxy.adhub.EnumType.SrcType;
import com.ocean.persist.common.ProxyConstants;
import com.ocean.persist.model.proxy.DSPPosition;
import com.ocean.proxy.api.base.AbstractAsynAdSupplier;
import com.ocean.proxy.serverdis.AdProxyAdhubTask;
import com.ocean.proxy.thrift.entity.AdContent;
import com.ocean.proxy.thrift.entity.AdImg;
import com.ocean.proxy.thrift.entity.AdMutiAction;
import com.ocean.proxy.thrift.entity.AdRecomReply;
import com.ocean.proxy.thrift.entity.AdRecomReq;
import com.ocean.proxy.thrift.entity.AdSpaceType;
import com.ocean.proxy.thrift.entity.AdUserInfo;
import com.ocean.proxy.thrift.entity.AdVid;
import com.ocean.proxy.thrift.entity.ExpectedMarketType;
import com.ocean.proxy.thrift.entity.UserAdSpaceAttri;
@Component(value="adHubAdSupplier")
public class AdHubAdSupplier   extends AbstractAsynAdSupplier{
    @Autowired
	private AdHubAdPuller adHubAdPuller;
	protected static final Map<String, IspType> mobiles = new HashMap<String, IspType>(5);
	static{
		mobiles.put("CMCC", IspType.ISP_CN_MOBILE);
		mobiles.put("CUCC", IspType.ISP_CN_UNICOM);
		mobiles.put("CTCC", IspType.ISP_CN_TEL);

	}
	private static final Map<String, NetType> ctype = new HashMap<String, NetType>();
	static{
		ctype.put(NETWORK_2G, NetType.NET_2G);
		ctype.put(NETWORK_3G, NetType.NET_3G);
		ctype.put(NETWORK_4G, NetType.NET_4G);
		ctype.put(NETWORK_WIFI, NetType.NET_WIFI);
	}
	public AdRecomReply invoke(InvokeAttribute attribute) throws AdPullException {
		
		AdRecomReq adreq = attribute.getAdreq();
		DSPPosition positionInfo = attribute.getDspPosition();
		// 参数转换
	    AdRequest.SdkRequest params = parseParams(adreq, positionInfo);
		
		//AdHubAdResponse.ServerResponse response = (AdHubAdResponse.ServerResponse) adHubAdPuller.api(params,adreq.getHash(),String.valueOf(adreq.getUserAdSpaceAttri().getAdSpaceId()));
	    AdResponseOuterClass.ServerResponse response = (AdResponseOuterClass.ServerResponse) invoke(params,AdResponseOuterClass.ServerResponse.class,adreq.getHash(),String.valueOf(adreq.getUserAdSpaceAttri().getAdSpaceId()),"joinDSP:adhub");
		
		return parseResult(response,adreq.getUserAdSpaceAttri());
	}

	public AsynAbstractTask packageTask(Parameter params,String hashCode){
		AdProxyAdhubTask task=new AdProxyAdhubTask();
		//heard
		Map<String, String> headers = new HashMap<String, String>(2);
		headers.put("Content-Type", "application/x-protobuf");
		headers.put("x-protobuf-version", "v2_9");
		task.setHeaders(headers);
		

		AdRequest.SdkRequest param = (AdRequest.SdkRequest) params;
		logger.info("joinDSP:adhub {} request param:{}",hashCode,UniversalUtils.replaceBlank(param.toString()));
		task.setParam(param);
		task.setDataFormat(DataFormat.DATA_FORMAT_STREAM);
		task.setInvokeType(InvokeType.INVOKE_TYPE_POST);
		task.setHashCode(hashCode);
		task.setUrl(SystemContext.getDynamicPropertyHandler().get(ProxyConstants.ADHUB_URL));
		
		return task;
	}
	private AdRecomReply parseResult(AdResponseOuterClass.ServerResponse response,UserAdSpaceAttri    attri)
			throws AdPullException {
		
		if(response == null||CollectionUtils.isEmpty(response.getSpaceInfoList())){
			return null;
		}
		if(response.getStatus()==1){
			
			throw new AdPullException("ad request error,joinDSP:adhub return error code:"+response.getErrcode()+",error massage:"+response.getErrmsg());
		}
		SpaceInfo spaceInfo=response.getSpaceInfo(0);
		if(CollectionUtils.isEmpty(spaceInfo.getAdResponseList())){
			return null;
		}
		AdResponse adResponse=spaceInfo.getAdResponse(0);

		//判断广告位类型是否一致
/*		if(validateAdSpaceType(attri.getSpaceType(),spaceInfo.getAdpType())){
			throw new AdPullException("ad space type is not mapping!");
		}*/
		//广告类型校验
		if(!attri.getExpectedMarketTypes().contains(ExpectedMarketType.VIDEO)&&AdpType.ADP_IVIDEO==spaceInfo.getAdpType()){
			throw new AdPullException("ad  type is not mapping!");
		}
		// 可用素材类型, bit位组合, 1:图片; 2:图文; 4:文字链; 8:html; 16:视频; 32:vas视频; 64:json; 128:landing; 256: preload
		if(spaceInfo.getContentType()!=8&&attri.getH5type()==1){
			throw new AdPullException("return  ad is not h5,client need h5 ad!");
		}else if(spaceInfo.getContentType()==8&&attri.getH5type()==2){
			throw new AdPullException("return  ad is  h5,client do not need h5 ad!");
		}

		

		// 返回对象
		AdRecomReply recomReply = new AdRecomReply();
		recomReply.setStatus(status);
		recomReply.setAd_contents(this.dealAds(adResponse, spaceInfo));
		return recomReply;
	}
	private List<AdContent>  dealAds(AdResponse adResponse,SpaceInfo spaceInfo){
		List<AdContent> list=new ArrayList<AdContent> ();
		
		for(AdContentInfo adContentResp:adResponse.getContentInfoList()){
			AdContent content = new AdContent();
			// 类型
			AdMutiAction action = new AdMutiAction();

			//------------------BEGIN 常规设置-------------------

			String title = defTitle;
			String marketTile=defTitle;

			// 内容类型1表示链接推广,2表示应用推广,3未使用 4表示电话推广
			action.setType(ACTION_WEB);
			//------------------END 常规设置---------------------
			
			logger.info("joinDSP:adhub  adTypeL{},ad size:{},template:{}",adContentResp.getRenderType(),adContentResp.getSize(),adContentResp.getTemplate());
			List<AdImg> imgs = new ArrayList<AdImg>();
			if(adContentResp.getRenderType()==RenderType.RENDER_JSON){
				AdhubTemplate temp=JsonUtils.toBean(adContentResp.getTemplate(), AdhubTemplate.class);
				for(String url:temp.getImages()){//
					AdImg img=new AdImg();
					img.setSrc(url);
					imgs.add(img);
				}
				if(imgs.isEmpty()&&StringUtils.isNotEmpty(temp.getImage())){//防止图片未取到
					AdImg img=new AdImg();
					img.setSrc(temp.getImage());
					imgs.add(img);
				}
				if(StringUtils.isNotEmpty(temp.getHeadline())){
					title=temp.getHeadline();
				}
				if(CollectionUtils.isNotEmpty(temp.getTexts())){
					marketTile=temp.getTexts().get(0);
				}
				if(marketTile.equals(defTitle)&&StringUtils.isNotEmpty(temp.getBody())){
					marketTile=temp.getBody();
				}
			}else{
				for(AdContentSlot slot:adContentResp.getAdcontentSlotList()){
					
					AdImg img=new AdImg();
					img.setSrc(slot.getContent());
					imgs.add(img);

				}
			}
			if(adResponse.getAdLogo()!=null){
				AdLogo logo=adResponse.getAdLogo();
				AdImg img=new AdImg();
				if(StringUtils.isNotEmpty(logo.getAdLabelUrl())){
					img.setSrc(logo.getAdLabelUrl());
				}else if(StringUtils.isNotEmpty(logo.getSourceUrl())){
					img.setSrc(logo.getSourceUrl());
				}
				content.setLogoImg(img );
			}
			Map<String, List<String>> map = new HashMap<String, List<String>>();
			// 点击监测
			List<String> clickL=new ArrayList<String>();
			List<String> viewL=new ArrayList<String>();
			
			if(adContentResp.getRenderType()==RenderType.RENDER_H5&&StringUtils.isNotEmpty(adContentResp.getTemplate())){
				content.setIsHtmlAd(true);
				content.setHtmlSnippet(adContentResp.getTemplate());
			}
			
			if(adResponse.getInteractInfo()!=null){
				//三方监测
				for(DetectInfo thirdDet:adResponse.getInteractInfo().getThirdpartInfoList()){
					viewL.add(thirdDet.getViewUrl());
					clickL.add(thirdDet.getClickUrl());
					
				}
				//
				if(CollectionUtils.isNotEmpty(adResponse.getInteractInfo().getImpTrackersList())){
					viewL.addAll(adResponse.getInteractInfo().getImpTrackersList());
				}
				if(CollectionUtils.isNotEmpty(adResponse.getInteractInfo().getClickTrackersList())){
					clickL.addAll(adResponse.getInteractInfo().getClickTrackersList());
				}
				//
				if(StringUtils.isNotEmpty(adResponse.getInteractInfo().getHubDetectInfo().getViewUrl())){
					viewL.add(adResponse.getInteractInfo().getHubDetectInfo().getViewUrl());
				}
				if(StringUtils.isNotEmpty(adResponse.getInteractInfo().getHubDetectInfo().getClickUrl())){
					clickL.add(adResponse.getInteractInfo().getHubDetectInfo().getClickUrl());
				}
				// 落地页
				String link =adResponse.getInteractInfo().getLandingPageUrl();
				if(StringUtils.isNotEmpty(link)){
					action.setLinkurl(link);
					content.setLinkurl(link);
				}
				if(StringUtils.isNotEmpty(adResponse.getInteractInfo().getPackageName())){
					action.setCpPackage(adResponse.getInteractInfo().getPackageName());
				}
				//下载链接
				if(adResponse.getInteractInfo().getInteractType()==2){
					action.setLinkurl(adResponse.getInteractInfo().getAppDownloadURL());
					content.setLinkurl(adResponse.getInteractInfo().getAppDownloadURL());
				}
				//视频广告
				
				if(AdpType.ADP_IVIDEO==spaceInfo.getAdpType()&&CollectionUtils.isNotEmpty(spaceInfo.getPreloadCtxList())){//视频广告
					
					AdContentInfo videoAd=spaceInfo.getPreloadCtx(0);
					AdVid adVid=new AdVid();
					adVid.setSrc(link);
					adVid.setDuration(spaceInfo.getMinTime());
					content.setAdVid(adVid);
					action.setType(ACTION_VIDEO);

					for(AdContentSlot slot:videoAd.getAdcontentSlotList()){
						AdImg img=new AdImg();
						img.setSrc(slot.getContent());
						imgs.add(img);

					}
					
				}
				
			}

			content.setMarketTitle(marketTile);
			content.setGuideTitle(title);
			content.setImglist(imgs);
			map.put(CLICK, clickL);
			map.put(SHOW, viewL);
			content.setThirdReportLinks(map);
			action.setGuideTitle(title);
			content.setMutiAction(Collections.singletonList(action));
			list.add(content);
		}
		return list;
	}
	private boolean validateAdSpaceType(AdSpaceType   adSpaceType,AdpType adpType){
		if(adpType==null){
			return true;
		}
		switch(adSpaceType){
		     case BANNER:
		    	 if(adpType==AdpType.ADP_BANNER){
		    		 
		    		 return true;

		    	 }
		     case OPENING:
		    	 if(adpType==AdpType.ADP_LOADING){
		    		 
		    		 return true;

		    	 }
		     case INTERSTITIAL:
		    	 if(adpType==AdpType.ADP_TABLE){
		    		 
		    		 return true;

		    	 } 
		     case INFOFLOW:
		    	 if(adpType==AdpType.ADP_NATIVE){
		    		 
		    		 return true;

		    	 }
		     default:
		    	 if(adpType==AdpType.ADP_UNKNOWN){
		    		 return true;
		    	 }else{
		    		 return false;
		    	 }
		    		 
		    	 
		}

	}
	private AdRequest.SdkRequest parseParams(AdRecomReq adreq, 
			DSPPosition positionInfo) throws AdPullException {
		if(StringUtils.isEmpty(positionInfo.getText1())){
			throw new AdPullException("appid is empty!");
		}
		DeviceInfo.Builder deviceBuilder=DeviceInfo.newBuilder();
	    deviceBuilder.setSdkUID(adreq.getOgin_name());
		AdUserInfo userInfo=adreq.getUserinfo();
		String osCode = userInfo.getOs();
		// 如果是ios
	    String os = "Android ";
	    if(OS_IOS.equals(osCode)){
			// ios 必填信息
	    	deviceBuilder.setIdfa(userInfo.getIdfa());
			os="Ios ";
			deviceBuilder.setPlatform(PlatformType.PLATFORM_IOS);
	    }else if(OS_ANDROID.equals(osCode)){
	    	deviceBuilder.setImei(userInfo.getImei());
			deviceBuilder.setPlatform(PlatformType.PLATFORM_ANDROID);
			deviceBuilder.setAndroidID(userInfo.getAdid());
	    }
	   
	    deviceBuilder.setOs(os+this.convOSVersion(userInfo.getOsversion()));
		if(StringUtils.isNotEmpty(userInfo.getMac())){
			deviceBuilder.setMac(this.converMac(userInfo.getMac()));
		}
		
		deviceBuilder.setDevType(DeviceType.DEVICE_PHONE);
		if(StringUtils.isNotEmpty(userInfo.getBrand_name())){
			deviceBuilder.setBrand(userInfo.getBrand_name());
		}
		if(StringUtils.isNotEmpty(userInfo.getPhonemodel())){
			deviceBuilder.setModel(userInfo.getPhonemodel());
		}
		if(StringUtils.isNotEmpty(userInfo.getAdid())){
			deviceBuilder.setAndroidID(userInfo.getAdid());
		}
		deviceBuilder.setResolution(userInfo.getDevice_width()+"_"+userInfo.getDevice_height());
		if(StringUtils.isNotEmpty(userInfo.getDensity())){
			deviceBuilder.setDensity(userInfo.getDensity());
		}
		deviceBuilder.setLanguage("zh");
		
		//userInfo
		CommonInfo.UserEnvInfo.Builder userInfoBuilder=CommonInfo.UserEnvInfo.newBuilder();
		if(StringUtils.isNotEmpty(userInfo.getClient_ip())){
			userInfoBuilder.setIp(userInfo.getClient_ip());
		}
		IspType isp=mobiles.get(userInfo.getMobile());
		if(isp==null){
			userInfoBuilder.setIsp(IspType.ISP_OTHER);
		}else{
			userInfoBuilder.setIsp(isp);
		}
		NetType netType=ctype.get(adreq.getNet());
		userInfoBuilder.setNet(netType==null?NetType.NET_WIFI:netType);
		userInfoBuilder.setUserAgent(userInfo.getUa());
		if(StringUtils.isNotEmpty(userInfo.getLat())&&StringUtils.isNotEmpty(userInfo.getLon())){
			CommonInfo.Geo geo=CommonInfo.Geo.newBuilder()
					                                   .setLongitude(userInfo.getLon())
					                                   .setLatitude(userInfo.getLat()).build();
			userInfoBuilder.setGeo(geo);
		}
		
		AdRequest.SdkRequest.Builder sdkRequestBuilder=AdRequest.SdkRequest.newBuilder();
		sdkRequestBuilder.setVersion("1.8.5")
		                 .setSrcType(SrcType.SRC_APP)
		                 .setReqType(ReqType.REQ_AD)
		                 .setTimeStamp(System.currentTimeMillis())
		                 .setAppid(positionInfo.getText1())
		                 .setAppVersion(adreq.getVersion())
		                 .setIfType(1);
						 
	    sdkRequestBuilder.setDevInfo(deviceBuilder.build());
	    sdkRequestBuilder.setEnvInfo(userInfoBuilder.build());

		//ad request info
		for(int i=0;i<adreq.getResult_num();i++){//设置请求多条广告
			AdRequest.AdReqInfo adReq=AdRequest.AdReqInfo.newBuilder().setSpaceID(positionInfo.getPos()).build();
			
			sdkRequestBuilder.addAdReqInfo(adReq);
		}
	   
	    return sdkRequestBuilder.build();
	}


	public AdHubAdPuller getAdHubAdPuller() {
		return adHubAdPuller;
	}

	public void setAdHubAdPuller(AdHubAdPuller adHubAdPuller) {
		this.adHubAdPuller = adHubAdPuller;
	}


}
