package com.ocean.proxy.api.service;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

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
import com.ocean.persist.api.proxy.boyuV2.BoyuV2Ad;
import com.ocean.persist.api.proxy.boyuV2.BoyuV2AdPullReq;
import com.ocean.persist.api.proxy.boyuV2.BoyuV2AdPullResp;
import com.ocean.persist.api.proxy.boyuV2.BoyuV2App;
import com.ocean.persist.api.proxy.boyuV2.BoyuV2Data;
import com.ocean.persist.api.proxy.boyuV2.BoyuV2Device;
import com.ocean.persist.api.proxy.boyuV2.BoyuV2Geo;
import com.ocean.persist.api.proxy.boyuV2.BoyuV2Img;
import com.ocean.persist.api.proxy.boyuV2.BoyuV2Imp;
import com.ocean.persist.api.proxy.boyuV2.BoyuV2Size;
import com.ocean.persist.api.proxy.boyuV2.BoyuV2Tracking;
import com.ocean.persist.api.proxy.boyuV2.BoyuV2Video;
import com.ocean.persist.api.proxy.youken.MD5Utils;
import com.ocean.persist.common.ProxyConstants;
import com.ocean.persist.model.proxy.DSPPosition;
import com.ocean.proxy.api.base.AbstractAsynAdSupplier;
import com.ocean.proxy.serverdis.AdProxyBoyuV2Task;
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
@Component(value="BoyuV2AdSupplier")
public class BoyuV2AdSupplier    extends AbstractAsynAdSupplier {
    private AtomicInteger ai=new AtomicInteger(1);
	 public AdRecomReply invoke(InvokeAttribute attribute)
				throws AdPullException {
			// TODO Auto-generated method stub
			AdRecomReq adreq = attribute.getAdreq();
			DSPPosition positionInfo = attribute.getDspPosition();
			// 参数转换
			BoyuV2AdPullReq params = parseParams(adreq, positionInfo);
			// 调用API
			BoyuV2AdPullResp response = (BoyuV2AdPullResp) invoke(params,BoyuV2AdPullResp.class,adreq.getHash()
					,String.valueOf(adreq.getUserAdSpaceAttri().getAdSpaceId()));
			
			// 解析结果
			return parseResult(response);
		}

	private BoyuV2AdPullReq parseParams(AdRecomReq adreq, 
			DSPPosition positionInfo) throws AdPullException {
		if(StringUtils.isEmpty(positionInfo.getText1())||StringUtils.isEmpty(positionInfo.getText2())){
			throw  new AdPullException("requestid prefix /token is empty!");
		}
		AdUserInfo userInfo=adreq.getUserinfo();
		BoyuV2AdPullReq param =new BoyuV2AdPullReq();
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
		//device
		BoyuV2Device device=new BoyuV2Device();
		device.setDeviceId(userInfo.getImei());
		device.setNetwork(getNetwork(adreq.getNet()));
		device.setDeviceType(1);
		device.setImei(userInfo.getImei());
		
		
		String os = userInfo.getOs();
		if(OS_ANDROID.equals(os)){
			device.setOs(os);
			device.setImei(userInfo.getImei());
			device.setImeiMd5(DigestUtils.md5Hex(userInfo.getImei()).toUpperCase());
			device.setAndroidId(userInfo.getAdid().toUpperCase());
		}else{
			device.setOs("ios");
			device.setIdfa(userInfo.getIdfa());
		}
	
		try {
			device.setBrand(URLEncoder.encode(userInfo.getBrand_name(),CHARSET_CODER));
			device.setModel(URLEncoder.encode(userInfo.getPhonemodel(),CHARSET_CODER));
			device.setUserAgent(URLEncoder.encode(userInfo.getUa(),CHARSET_CODER));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		device.setOsVersion(userInfo.getOsversion());
		device.setCarrier(getOp(userInfo.getMobile()));
		device.setMac(userInfo.getMac().toUpperCase());
		device.setIp(userInfo.getClient_ip());
		param.setDevice(device);
		if(StringUtils.isNotEmpty(userInfo.getLat())&&StringUtils.isNotEmpty(userInfo.getLon())){
			BoyuV2Geo geo=new BoyuV2Geo();
			geo.setLatitude(Double.parseDouble(userInfo.getLat()));
			geo.setLongitude(Double.parseDouble(userInfo.getLon()));
			device.setGeo(geo);
		}
		//imp
		BoyuV2Imp imp=new BoyuV2Imp();
		int index=ai.getAndIncrement();
		if(Integer.MAX_VALUE==index){
			ai.getAndSet(1);
		}
		imp.setId(index);
		imp.setCount(adreq.getResult_num());
	
		if(1==positionInfo.getVideoFlag()){
			BoyuV2Video video=new BoyuV2Video();
			BoyuV2Size size=new BoyuV2Size();
			size.setHeight(adreq.getUserAdSpaceAttri().getSpaceHeight());
			size.setWidth(adreq.getUserAdSpaceAttri().getSpaceWidth());
			video.setSizes(Collections.singletonList(size));
			//video.setMimeTypes(mimeTypes);
			imp.setVideo(video);
		}
		param.setImps(Collections.singletonList(imp));
		//app
		BoyuV2App app=new BoyuV2App();
		app.setAppName(adreq.getApp());
		app.setPkgName(positionInfo.getText3());
		param.setApp(app);
		param.setVersion("2.1.0");
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

	private AdRecomReply parseResult(BoyuV2AdPullResp body)
			throws AdPullException {
		if(body == null||null==body.getData()||CollectionUtils.isEmpty(body.getData().getGroups())){
			return null;
		}
		if(0!=body.getCode()){
			throw new AdPullException("ad request error, return error code:"+body.getCode()+" msg:"+body.getMsg());
		}
		AdRecomReply reply=new AdRecomReply();
		reply.setStatus(status);


		reply.setAd_contents(dealAds(body.getData()));
		return reply;
	}
	private List<AdContent>  dealAds(BoyuV2Data data){
		List<AdContent> list=new ArrayList<AdContent> ();
		
		for(BoyuV2Ad ad:data.getGroups().get(0).getAds()){
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
	        if(StringUtils.isNotEmpty(ad.getLink())){
	    		content.setLinkurl(ad.getLink());
	    		action.setLinkurl(ad.getLink());
	        }
	        if(ad.getAppInfo()!=null){
	        	BoyuV2App app=ad.getAppInfo();
		        if(StringUtils.isNotEmpty(app.getPkgName())){
		        	action.setCpPackage(app.getPkgName());
		        }
				if(StringUtils.isNotEmpty(app.getIcon())){
					action.setCpIcon(app.getIcon());
				}
		        //acType=ACTION_APP;
	        	if(ad.getDownloadAd()==0){
	        		acType=ACTION_WEB;
	        	}else{
	        		acType=ACTION_APP;
	        	}
		        if(StringUtils.isNotEmpty(app.getDeepLink())){
		        	action.setActiveUri(app.getDeepLink());

		        }
		        
		        
				 List<String> downL=new ArrayList<String>();
				 if(StringUtils.isNotEmpty(app.getDsUrl())){
			
					downL.add(app.getDsUrl());
				 }
				 map.put(START_DOWN, downL);
				 
				 List<String> downEnd=new ArrayList<String>();
				 if(StringUtils.isNotEmpty(app.getDfUrl())){
						
					 downEnd.add(app.getDfUrl());
				 }
				 map.put(DOWNLOAD, downL);

				 List<String> installL=new ArrayList<String>();
				 if(StringUtils.isNotEmpty(app.getSfUrl())){
					
					installL.add(app.getSfUrl());
				 }
				 map.put(INSTALL,installL); 
	        }
	        if(ad.getAdType()==5){
	        	acType=ACTION_VIDEO;
				AdVid rpcVideo = new AdVid();
				BoyuV2Video video=ad.getVideo();
				if(video.getDuration()>0) {
					rpcVideo.setDuration(video.getDuration());
				}
				rpcVideo.setSrc(video.getUrl());
				if(video.getWidth()>0) {
					rpcVideo.setWidth(video.getWidth());
				}
				if(video.getHeight()>0) {
					rpcVideo.setHeight(video.getHeight());
				}
				int lastIndex = rpcVideo.getSrc().lastIndexOf(".");
				if (lastIndex < 0) {
					rpcVideo.setFormat("unknow");
				} else {
					rpcVideo.setFormat(rpcVideo.getSrc().substring(lastIndex));
				}
				
				//上报
				if(CollectionUtils.isNotEmpty(video.getEventTrackInfos())){
					List<String> progress=new ArrayList<String>();
					for(BoyuV2Tracking track:video.getEventTrackInfos()){
						if(track.getEventType()==1){
		        			List<String> report=new ArrayList<String>();
		        			report.addAll(track.getEventTrackUrls());
		        			map.put(VIDEO_START,report);
						}
						else if(track.getEventType()==2){
		        			for(String url:track.getEventTrackUrls()){
			        			progress.add(getProgressUrl(url,video.getDuration(),0.25f));
		        			}
						}else if(track.getEventType()==3){
		        			for(String url:track.getEventTrackUrls()){
			        			progress.add(getProgressUrl(url,video.getDuration(),0.5f));
		        			}
						}else if(track.getEventType()==4){
		        			for(String url:track.getEventTrackUrls()){
			        			progress.add(getProgressUrl(url,video.getDuration(),0.75f));
		        			}
						}else if(track.getEventType()==5){
							List<String> report=new ArrayList<String>();
		        			report.addAll(track.getEventTrackUrls());
		        			map.put(VIDEO_END,report);
						}else if(track.getEventType()==6){
							List<String> report=new ArrayList<String>();
		        			report.addAll(track.getEventTrackUrls());
		        			map.put(VIDEO_MUTE,report);
						}else if(track.getEventType()==7){
		        			List<String> report=new ArrayList<String>();
		        			report.addAll(track.getEventTrackUrls());
		        			map.put(VIDEO_SKIP,report);
			        		
						}else if(track.getEventType()==8){
		        			List<String> report=new ArrayList<String>();
		        			report.addAll(track.getEventTrackUrls());
		        			map.put(VIDEO_CLOSE,report);
						}
						map.put(VIDEO_PROGRESS,progress);
					}

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
	        if(CollectionUtils.isNotEmpty(ad.getImgs())){
		        for(BoyuV2Img byImg:ad.getImgs()){
					if(StringUtils.isNotEmpty(byImg.getUrl())){
						AdImg img=new AdImg();
						img.setSrc(byImg.getUrl());
						imgs.add(img);	
					}
		        }
	        }
		    List<String> showL=new ArrayList<String>();
			if(CollectionUtils.isNotEmpty(ad.getImpTrackUrls())){
				showL.addAll(ad.getImpTrackUrls());
				
			 }
			 map.put(SHOW,showL);
			 List<String> clickL=new ArrayList<String>();
			 if(CollectionUtils.isNotEmpty(ad.getClickTrackUrls())){

				clickL.addAll(ad.getClickTrackUrls());
		
			 }
			 map.put(CLICK, clickL);
			 

			 

				 
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
	private String getProgressUrl(String url,int duration,float progress){
			String prefix=url.indexOf("?")<0?"?":"&";
			return url+prefix+ZK_VIDEO_PROGRESS_PARAM+"="+duration*1000*progress;

		
	}
	private int  getOp(String mobile){
		if(MOBILE_CMCC.equals(mobile)){
			return 1;
		}else if(MOBILE_CUCC.equals(mobile)){
			return 2;
		}else if(MOBILE_CTCC.equals(mobile)){
			return 3;
		}
		return 9;
	}
	@Override
	public AsynAbstractTask packageTask(Parameter params, String hashCode) {
		// TODO Auto-generated method stub
		AdProxyBoyuV2Task task=new AdProxyBoyuV2Task();
		BoyuV2AdPullReq param = (BoyuV2AdPullReq) params;


		Map<String, String> headers = new HashMap<String, String>(2);
		headers.put("Content-Type", "application/json");
		task.setHeaders(headers);
		
		StringBuilder buff=new StringBuilder(SystemContext.getDynamicPropertyHandler().get(ProxyConstants.BOYU_V2_URL));
		buff.append("?").append("&zkrequestId=").append(hashCode);
		task.setParam(param);
		task.setDataFormat(DataFormat.DATA_FORMAT_JSON);
		task.setInvokeType(InvokeType.INVOKE_TYPE_POST);
		task.setHashCode(hashCode);
		task.setUrl(buff.toString());
		
		return task;
	}

}
