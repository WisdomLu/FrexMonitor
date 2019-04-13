package com.ocean.proxy.api.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
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
import com.ocean.persist.api.proxy.fmobi.FmobiAdPullResponse;
import com.ocean.persist.api.proxy.fmobi.FmobiAdpullParams;
import com.ocean.persist.api.proxy.fmobi.FmobiAdspace;
import com.ocean.persist.api.proxy.fmobi.FmobiApp;
import com.ocean.persist.api.proxy.fmobi.FmobiBanner;
import com.ocean.persist.api.proxy.fmobi.FmobiCreative;
import com.ocean.persist.api.proxy.fmobi.FmobiDevice;
import com.ocean.persist.api.proxy.fmobi.FmobiEvent;
import com.ocean.persist.api.proxy.fmobi.FmobiImpression;
import com.ocean.persist.api.proxy.fmobi.FmobiNative;
import com.ocean.persist.api.proxy.fmobi.FmobiRespBody;
import com.ocean.persist.api.proxy.fmobi.FmobiTracking;
import com.ocean.persist.api.proxy.fmobi.FmobiVideo;
import com.ocean.persist.common.ProxyConstants;
import com.ocean.persist.model.proxy.DSPPosition;
import com.ocean.proxy.api.base.AbstractAsynAdSupplier;
import com.ocean.proxy.serverdis.AdProxyAdhubTask;
import com.ocean.proxy.serverdis.AdProxyFmobiTask;
import com.ocean.proxy.thrift.entity.AdContent;
import com.ocean.proxy.thrift.entity.AdImg;
import com.ocean.proxy.thrift.entity.AdMutiAction;
import com.ocean.proxy.thrift.entity.AdRecomReply;
import com.ocean.proxy.thrift.entity.AdRecomReq;
import com.ocean.proxy.thrift.entity.AdUserInfo;
import com.ocean.proxy.thrift.entity.AdVid;
import com.ocean.proxy.thrift.entity.UserAdSpaceAttri;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年10月27日 
      @version 1.0 
 */
@Component(value="fmobiAdSupplier")
public class FmobiAdSupplier   extends AbstractAsynAdSupplier {

	public AdRecomReply invoke(InvokeAttribute attribute)
			throws AdPullException {
		// TODO Auto-generated method stub
		AdRecomReq adreq = attribute.getAdreq();
		DSPPosition positionInfo = attribute.getDspPosition();
		// 参数转换
		FmobiAdpullParams params = parseParams(adreq, positionInfo);
		// 调用API
		FmobiRespBody response = (FmobiRespBody) invoke(params,FmobiRespBody.class,adreq.getHash(),String.valueOf(adreq.getUserAdSpaceAttri().getAdSpaceId()),"joinDSP:fmobi");
		
		// 解析结果
		return parseResult(response);
	}
	private FmobiAdpullParams parseParams(AdRecomReq adreq, 
			DSPPosition positionInfo) throws AdPullException {
		if(StringUtils.isEmpty(positionInfo.getText1())||StringUtils.isEmpty(positionInfo.getText2())||StringUtils.isEmpty(positionInfo.getText3())){
			throw  new AdPullException("appId、appKey or package name is empty!");
		}
		AdUserInfo userInfo=adreq.getUserinfo();
		UserAdSpaceAttri attri=adreq.getUserAdSpaceAttri();
		
		
		FmobiAdpullParams param =new FmobiAdpullParams();
		param.setVersion(1.0f);
		param.setTs((int)(System.currentTimeMillis()/1000));
		
		FmobiImpression imp=new FmobiImpression();
		imp.setAid(Integer.parseInt(positionInfo.getPos()));
		imp.setWidth(attri.getSpaceWidth());
		imp.setHeight(attri.getSpaceHeight());
		param.setImpression(Collections.singletonList(imp));
		
		FmobiDevice device=new FmobiDevice();
		String os = userInfo.getOs();
		if(OS_ANDROID.equals(os)){
			device.setOs(1);
			device.setImeiori(userInfo.getImei());
			device.setImei(DigestUtils.md5Hex(userInfo.getImei()));
			device.setMacori(userInfo.getMac());
			device.setMac(DigestUtils.md5Hex(userInfo.getMac()));
			device.setAnidori(userInfo.getAdid());
			if(StringUtils.isNotEmpty(userInfo.getAdid())){
				device.setAnid(DigestUtils.md5Hex(userInfo.getAdid()));
			}
			
		}
		// ios
		else{
			device.setOs(2);
			device.setIdfaori(userInfo.getIdfa());
			device.setIdfa(DigestUtils.md5Hex(userInfo.getIdfa()));
		}
		device.setBrand(userInfo.getBrand_name());
		try {
			device.setPlatform(URLEncoder.encode(userInfo.getPhonemodel(),"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		device.setOs_version(this.convOSVersion(userInfo.getOsversion()));
		device.setDevice_size(userInfo.getDevice_height()+"*"+userInfo.getDevice_width());
		device.setNetwork(getNetwork(adreq.getNet()));
		device.setOperator(getOp(userInfo.getMobile()));
		device.setIp(userInfo.getClient_ip());
		device.setUa(userInfo.getUa());
		device.setDensity(userInfo.getDensity());
		device.setImsi(StringUtils.isEmpty(userInfo.getImsi())?"":userInfo.getImsi());
		param.setDevice(device);
		
		FmobiApp app=new FmobiApp();
		app.setAppver(adreq.getVersion());
		
		app.setAppId(positionInfo.getText1());
		app.setAppKey(positionInfo.getText2());
		app.setPkgname(positionInfo.getText3());
		param.setApp(app);
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

		if("CMCC".equals(op)){
			return 1;
		}else if("CUCC".equals(op)){
			return 2;
		}else if("CTCC".equals(op)){
			return 3;
		}
		return 0;
	}
	private AdRecomReply parseResult(FmobiRespBody body)
			throws AdPullException {
		if(body == null||body.getData()==null){
			return null;
		}
		if(body.getStatus()!=0){
			throw new AdPullException("ad request error,joinDSP:fmobi return error code:"+body.getStatus()+",error massage:"+body.getMessage());
			
		}
		FmobiAdPullResponse resp=body.getData();
		FmobiAdspace adspace=resp.getAdspace().get(0);
		FmobiCreative creative=adspace.getCreative().get(0);//这协议设计简直是脑残
		
		
		
		
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
        if(creative.isIs_html()){
			content.setIsHtmlAd(true);
			content.setHtmlSnippet(creative.getHtml_snippet());
        }else if(adspace.getAdformat()<4){
        	FmobiBanner banner=creative.getBanner();
			AdImg img=new AdImg();
			img.setSrc(banner.getCreative_url());
			img.setHeight(banner.getHeight());
			img.setWidth(banner.getWidth());
			imgs.add(img);
			
        }else if(adspace.getAdformat()==4){
        	FmobiVideo video=creative.getVideo();
			AdVid adVid=new AdVid();
			adVid.setSrc(video.getCreative_url());
			adVid.setDuration(video.getDuration());
			content.setAdVid(adVid);
			content.setLinkurl(video.getCreative_url());
			action.setLinkurl(video.getCreative_url());
			acType=ACTION_VIDEO;
        }else if(adspace.getAdformat()==5){
        	
        	List<FmobiNative> natList=creative.getAd_native();
        	for(FmobiNative nat:natList){
        		if(nat.getRequired_field()==1){
        			title=nat.getRequired_value();
        		}else if(nat.getRequired_field()==2&&"image".equals(nat.getIndex_value())){
        			
        			AdImg img=new AdImg();
        			img.setSrc(nat.getRequired_value());
        			imgs.add(img);
        		}
        	}
        }
        List<FmobiEvent> events= creative.getEvent();
        if(CollectionUtils.isNotEmpty(events)){
        	FmobiEvent event= creative.getEvent().get(0);
        	if(event.getEvent_key()==2){
        		String formatUrl=formatUrl(event.getEvent_value());
        		action.setLinkurl(formatUrl);
        		content.setLinkurl(formatUrl);
        	}else{
    			action.setLinkurl(event.getEvent_value());
    			content.setLinkurl(event.getEvent_value());
        	}

			acType=event.getEvent_key()==2?ACTION_APP:ACTION_WEB;
        }
        List<String> clickL=new ArrayList<String>();
        if(CollectionUtils.isNotEmpty(creative.getImpression())){
    	
    		map.put(SHOW, creative.getImpression());
        }
        if(CollectionUtils.isNotEmpty(creative.getClick())){
        	clickL.addAll(creative.getClick());
        	
        }
        List<FmobiTracking> tracks= creative.getTracking();
        if(CollectionUtils.isNotEmpty(tracks)){
        	acType=ACTION_APP;
            for(FmobiTracking track:tracks){
            	if(track!=null&&track.getTracking_key()==1){
            		clickL.addAll(track.getTracking_value());
            		
            	}else if(track!=null&&track.getTracking_key()==2){
            		map.put(DOWNLOAD, track.getTracking_value());
            	}else if(track!=null&&track.getTracking_key()==3){
            		map.put(INSTALL, track.getTracking_value());
            	}
            }
        }


    	map.put(CLICK, clickL);
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
	private   String formatUrl(String url){
		return url.replace("acttype=1", "acttype=").replace("acttype=", "acttype=1");
		
	}
	@Override
	public AsynAbstractTask packageTask(Parameter params, String hashCode) {
		// TODO Auto-generated method stub
		AdProxyFmobiTask task=new AdProxyFmobiTask();
		//heard
		Map<String, String> headers = new HashMap<String, String>(2);
		headers.put("Content-Type", "application/json");
		
		FmobiAdpullParams param = (FmobiAdpullParams) params;
		String sign=DigestUtils.sha1Hex(param.getApp().getAppKey()+"|"+param.getTs());
		String tokenOri=param.getApp().getAppId()+"|"+param.getTs()+"|"+sign;
		String token=Base64.encodeBase64String(tokenOri.getBytes());
		headers.put("X-TOKEN", token);
		task.setHeaders(headers);
		

		
		//logger.info("joinDSP:fmobi {} request param:{}",hashCode,UniversalUtils.replaceBlank(JsonUtils.toJson(param)));
		task.setParam(param);
		task.setDataFormat(DataFormat.DATA_FORMAT_JSON);
		task.setInvokeType(InvokeType.INVOKE_TYPE_POST);
		task.setHashCode(hashCode);
		task.setUrl(SystemContext.getDynamicPropertyHandler().get(ProxyConstants.YUANSHEN_URL));
		
		return task;
	}


}
