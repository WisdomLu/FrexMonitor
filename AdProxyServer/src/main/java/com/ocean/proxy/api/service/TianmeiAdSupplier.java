package com.ocean.proxy.api.service;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.inveno.util.CollectionUtils;
import com.ocean.core.common.system.SystemContext;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
import com.ocean.core.common.threadpool.workthread.Task.DataFormat;
import com.ocean.core.common.threadpool.workthread.Task.InvokeType;
import com.ocean.persist.api.proxy.AdPullException;
import com.ocean.persist.api.proxy.oneway.OneWayImg;
import com.ocean.persist.api.proxy.oneway.OnewayAdPullReq;
import com.ocean.persist.api.proxy.oneway.OnewayAdPullResp;
import com.ocean.persist.api.proxy.oneway.OnewayData;
import com.ocean.persist.api.proxy.oneway.OnewayTracking;
import com.ocean.persist.api.proxy.oneway.OnewayVideo;
import com.ocean.persist.common.ProxyConstants;
import com.ocean.persist.model.proxy.DSPPosition;
import com.ocean.proxy.api.base.AbstractAsynAdSupplier;
import com.ocean.proxy.serverdis.AdProxyOnewayV2Task;
import com.ocean.proxy.thrift.entity.AdContent;
import com.ocean.proxy.thrift.entity.AdImg;
import com.ocean.proxy.thrift.entity.AdMutiAction;
import com.ocean.proxy.thrift.entity.AdRecomReply;
import com.ocean.proxy.thrift.entity.AdRecomReq;
import com.ocean.proxy.thrift.entity.AdUserInfo;
import com.ocean.proxy.thrift.entity.AdVid;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年2月28日 
      @version 1.0 
 */
@Component(value="TianmeiAdSupplier")
public class TianmeiAdSupplier    extends AbstractAsynAdSupplier {
	 public AdRecomReply invoke(InvokeAttribute attribute)
				throws AdPullException {
			// TODO Auto-generated method stub
			AdRecomReq adreq = attribute.getAdreq();
			DSPPosition positionInfo = attribute.getDspPosition();
			// 参数转换
			OnewayAdPullReq params = parseParams(adreq, positionInfo);
			// 调用API
			OnewayAdPullResp response = (OnewayAdPullResp) invoke(params,OnewayAdPullResp.class,adreq.getHash()
					,String.valueOf(adreq.getUserAdSpaceAttri().getAdSpaceId()));
			
			// 解析结果
			return parseResult(response);
		}

	private OnewayAdPullReq parseParams(AdRecomReq adreq, 
			DSPPosition positionInfo) throws AdPullException {
		if(StringUtils.isEmpty(positionInfo.getText1())||StringUtils.isEmpty(positionInfo.getText2())||StringUtils.isEmpty(positionInfo.getText3())){
			throw  new AdPullException("requestid prefix /token/pkgname is empty!");
		}
		AdUserInfo userInfo=adreq.getUserinfo();
		OnewayAdPullReq param =new OnewayAdPullReq();
		param.setPublishId(positionInfo.getText1());
		param.setToken(positionInfo.getText2());
		param.setTs(System.currentTimeMillis());
		
		param.setApiVersion("1.2.1");
		param.setPlacementId(positionInfo.getPos());
		if(positionInfo.getVideoFlag()==1){
			param.setCreativeType(1);
		}else{
			param.setCreativeType(2);
		}
		param.setBundleId(positionInfo.getText3());
		param.setBundleVersion(adreq.getVersion());
		
		String os = userInfo.getOs();
		if(OS_ANDROID.equals(os)){
			param.setDeviceId(userInfo.getAaid());
			param.setImei(userInfo.getImei());
			param.setApiLevel(Integer.parseInt(userInfo.getOs_api_level()));
			param.setAndroidId(userInfo.getAdid());
		}else{
			param.setDeviceId(userInfo.getIdfa());
		}
		param.setOsVersion(userInfo.getOsversion());
		param.setConnectionType(NETWORK_WIFI.equals(adreq.getNet())?"wifi":"cellular");
		param.setNetworkType(0);
		param.setNetworkOperator(getOp(userInfo.getMobile()));
		param.setSimOperator(getOp(userInfo.getMobile()));
		//param.setSimOperator(userInfo.getSn());
		param.setImsi(userInfo.getImsi());
		param.setDeviceMake(userInfo.getBrand_name());
		param.setDeviceModel(userInfo.getPhonemodel());
		param.setMac(userInfo.getMac());
		//param.setWifiBSSID(wifiBSSID);
		param.setScreenWidth(userInfo.getDevice_width());
		param.setScreenHeight(userInfo.getDevice_height());
		if(StringUtils.isNotEmpty(userInfo.getDensity())){
			param.setScreenDensity(Integer.parseInt(userInfo.getDensity()));
		}
		if(StringUtils.isNotEmpty(userInfo.getUa_webv())){
			param.setUserAgent(userInfo.getUa_webv());
		}else{
			param.setUserAgent(userInfo.getUa());
		}
		
		param.setIp(userInfo.getClient_ip());
		param.setLanguage("zh_CN");
		param.setTimeZone("GMT+08:00");
		return param;
	}	
	private AdRecomReply parseResult(OnewayAdPullResp body)
			throws AdPullException {
		if(body == null||null==body.getData()){
			return null;
		}
		if(!body.isSuccess()){
			throw new AdPullException("ad request error, return error code:"+body.getErrorCode()+" msg:"+body.getMessage());
		}
		AdRecomReply reply=new AdRecomReply();
		reply.setStatus(status);
		reply.setAd_contents(dealAds(body.getData()));
		return reply;
	}
	private List<AdContent>  dealAds(OnewayData ad){
		List<AdContent> list=new ArrayList<AdContent> ();
		//****************BEGIN 常规设置*************
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
	    if(StringUtils.isNotEmpty(ad.getClickUrl())){
			content.setLinkurl(ad.getClickUrl());
			action.setLinkurl(ad.getClickUrl());
	    }
	    if(StringUtils.isNotEmpty(ad.getAppIcon())&&StringUtils.isNotEmpty(ad.getAppName())){
	    	acType=ACTION_APP;
			if(StringUtils.isNotEmpty(ad.getAppIcon())){
				action.setCpIcon(ad.getAppIcon());
			}
	        if(StringUtils.isNotEmpty(ad.getDeeplink())){
	        	action.setActiveUri(ad.getDeeplink());
	        }
	    }
	    OnewayVideo video=ad.getVideo();
	    if(ad.getTrackingEvents()!=null){
	    	OnewayTracking track=ad.getTrackingEvents();
		    List<String> showL=new ArrayList<String>();
			if(CollectionUtils.isNotEmpty(track.getShow())){
				showL.addAll(track.getShow());
			 }
			 map.put(SHOW,showL);
			 
			 List<String> clickL=new ArrayList<String>();
			 if(CollectionUtils.isNotEmpty(track.getClick())){
				clickL.addAll(track.getClick());
			 }
			 map.put(CLICK, clickL);
			 
			 List<String> downL=new ArrayList<String>();
			 if(CollectionUtils.isNotEmpty(track.getApkDownloadStart())){
				downL.addAll(track.getApkDownloadStart());
			 }
			 map.put(START_DOWN, downL);
			 
			 List<String> downEnd=new ArrayList<String>();
			 if(CollectionUtils.isNotEmpty(track.getApkDownloadFinish())){
				 downEnd.addAll(track.getApkDownloadFinish());
			 }
			 map.put(DOWNLOAD, downL);
	
			 List<String> installL=new ArrayList<String>();
			 if(CollectionUtils.isNotEmpty(track.getPackageAdded())){
				installL.addAll(track.getPackageAdded());
			 }
			 map.put(INSTALL,installL);
			 
			 //视频
			if(CollectionUtils.isNotEmpty(track.getStart())){
				List<String> report=new ArrayList<String>();
				report.addAll(track.getStart());
				map.put(VIDEO_START,report);
			 }
			
			List<String> progress=new ArrayList<String>();
			if(CollectionUtils.isNotEmpty(track.getFirstQuartile())){
				for(String url:track.getFirstQuartile()){
	    			progress.add(getProgressUrl(url,video.getDuration(),0.25f));
				}
			 }
			if(CollectionUtils.isNotEmpty(track.getMidpoint())){
				for(String url:track.getMidpoint()){
	    			progress.add(getProgressUrl(url,video.getDuration(),0.5f));
				}
			 }
			if(CollectionUtils.isNotEmpty(track.getThirdQuartile())){
				for(String url:track.getThirdQuartile()){
	    			progress.add(getProgressUrl(url,video.getDuration(),0.75f));
				}
			 }
			map.put(VIDEO_PROGRESS,progress);
			
			if(CollectionUtils.isNotEmpty(track.getEnd())){
				List<String> report=new ArrayList<String>();
				report.addAll(track.getEnd());
				map.put(VIDEO_END,report);
			 }
			if(CollectionUtils.isNotEmpty(track.getSkip())){
				List<String> report=new ArrayList<String>();
				report.addAll(track.getSkip());
				map.put(VIDEO_SKIP,report);
	    		
			 }
			if(CollectionUtils.isNotEmpty(track.getClose())){
				List<String> report=new ArrayList<String>();
				report.addAll(track.getClose());
				map.put(VIDEO_CLOSE,report);
			 }
			 
	
	    	
	    }
		  
	    if(video!=null){
	    	acType=ACTION_VIDEO;
			AdVid rpcVideo = new AdVid();
			if(video.getDuration()>0) {
				rpcVideo.setDuration((int)video.getDuration());
			}
			rpcVideo.setSrc(video.getUrl());
			int lastIndex = rpcVideo.getSrc().lastIndexOf(".");
			if (lastIndex < 0) {
				rpcVideo.setFormat("unknow");
			} else {
				rpcVideo.setFormat(rpcVideo.getSrc().substring(lastIndex));
			}
			if(StringUtils.isNotEmpty(video.getCoverUrl())){
				rpcVideo.setImg_src(video.getCoverUrl());
			}else if(CollectionUtils.isNotEmpty(ad.getImages())){//如果盖视频的图片没有，就用广告本身的图片
				rpcVideo.setImg_src(ad.getImages().get(0).getUrl());
			}
			content.setAdVid(rpcVideo);
	    }
	
	    if(StringUtils.isNotEmpty(ad.getTitle())){
	    	try {
				title = URLDecoder.decode(ad.getTitle(),CHARSET_CODER);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    if(CollectionUtils.isNotEmpty(ad.getImages())){
	        for(OneWayImg byImg:ad.getImages()){
				if(StringUtils.isNotEmpty(byImg.getUrl())){
					AdImg img=new AdImg();
					img.setSrc(byImg.getUrl());
					imgs.add(img);	
				}
	        }
	    }
	    content.setThirdReportLinks(map);	
		content.setImglist(imgs);
	    action.setType(acType); 
		content.setMarketTitle(marketTitle);
		content.setGuideTitle(title);
		content.setMutiAction(Collections.singletonList(action));
		list.add(content);
		return list;
	}
	private String getProgressUrl(String url,float duration,float progress){
			String prefix=url.indexOf("?")<0?"?":"&";
			return url+prefix+ZK_VIDEO_PROGRESS_PARAM+"="+duration*1000*progress;

		
	}
	private String  getOp(String mobile){
		if(MOBILE_CMCC.equals(mobile)){
			return "46000";
		}else if(MOBILE_CUCC.equals(mobile)){
			return "46001";
		}else if(MOBILE_CTCC.equals(mobile)){
			return "46003";
		}
		return "";
	}
	@Override
	public AsynAbstractTask packageTask(Parameter params, String hashCode) {
		// TODO Auto-generated method stub
		AdProxyOnewayV2Task task=new AdProxyOnewayV2Task();
		OnewayAdPullReq param = (OnewayAdPullReq) params;

		Map<String, String> headers = new HashMap<String, String>(2);
		headers.put("Content-Type", "application/json");
		task.setHeaders(headers);
		
		
		StringBuilder buff=new StringBuilder(SystemContext.getDynamicPropertyHandler().get(ProxyConstants.TIANMEI_URL));
		buff.append("?").append("publishId=").append(param.getPublishId())
		.append("&token=").append(param.getToken())
		.append("&ts").append(param.getTs())
		.append("&zkrequestId=").append(hashCode);
		
		param.setPublishId(null);
		param.setToken(null);
		
		task.setParam(param);
		task.setDataFormat(DataFormat.DATA_FORMAT_JSON);
		task.setInvokeType(InvokeType.INVOKE_TYPE_POST);
		task.setHashCode(hashCode);
		task.setUrl(buff.toString());
		
		return task;
	}

}
