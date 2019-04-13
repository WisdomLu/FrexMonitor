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
import com.ocean.persist.api.proxy.adwo.AdwoAd;
import com.ocean.persist.api.proxy.adwo.AdwoAdPullResponse;
import com.ocean.persist.api.proxy.adwo.AdwoAdPullParams;
import com.ocean.persist.api.proxy.adwo.AdwoApp;
import com.ocean.persist.api.proxy.adwo.AdwoDevice;
import com.ocean.persist.api.proxy.adwo.AdwoFeeds;
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
import com.ocean.proxy.serverdis.AdProxyAdwoTask;
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
@Component(value="AdwoAdSupplier")
public class AdwoAdSupplier   extends AbstractAsynAdSupplier {

	public AdRecomReply invoke(InvokeAttribute attribute)
			throws AdPullException {
		// TODO Auto-generated method stub
		AdRecomReq adreq = attribute.getAdreq();
		DSPPosition positionInfo = attribute.getDspPosition();
		// 参数转换
		AdwoAdPullParams params = parseParams(adreq, positionInfo);
		// 调用API
		AdwoAdPullResponse response = (AdwoAdPullResponse) invoke(params,AdwoAdPullResponse.class,adreq.getHash(),String.valueOf(adreq.getUserAdSpaceAttri().getAdSpaceId()),"joinDSP:adwo");
		
		// 解析结果
		return parseResult(response);
	}
	private AdwoAdPullParams parseParams(AdRecomReq adreq, 
			DSPPosition positionInfo) throws AdPullException {
		if(StringUtils.isEmpty(positionInfo.getText3())){
			throw  new AdPullException("package name is empty!");
		}
		AdUserInfo userInfo=adreq.getUserinfo();
		UserAdSpaceAttri attri=adreq.getUserAdSpaceAttri();
		
		
		AdwoAdPullParams param =new AdwoAdPullParams();
		param.setPid(positionInfo.getPos());
		param.setFormat(3);
		param.setW(attri.getSpaceWidth());
		param.setH(attri.getSpaceHeight());
		//app
		AdwoApp app=new AdwoApp();
		app.setName(adreq.getApp());
		app.setPn(positionInfo.getText3());
		app.setVer(adreq.getVersion());
		param.setApp(app);
		
		//device
		AdwoDevice device=new AdwoDevice();
		device.setIp(userInfo.getClient_ip());
		device.setImei(userInfo.getImei());
		device.setAndroidid(userInfo.getAdid());
		device.setMac(userInfo.getMac());
		
		if(StringUtils.isNotEmpty(userInfo.getDip())){
			device.setS(Float.parseFloat(userInfo.getDip()));
		}
		device.setNet(NETWORK_WIFI.equals(adreq.getNet())?1:0);
		device.setOsv(this.convOSVersion(userInfo.getOsversion()));
		device.setManu(userInfo.getBrand_name());
		device.setBn(userInfo.getPhonemodel());
		device.setSw(userInfo.getDevice_width());
		device.setSh(userInfo.getDevice_height());
		param.setDev(device);
		
		
		//feeds
		AdwoFeeds feeds=new AdwoFeeds();
		feeds.setImgh(attri.getSpaceHeight());
		feeds.setImgw(attri.getSpaceWidth());
		feeds.setTlen(0);
		param.setFeeds(feeds);
		//
		
		
		return param;
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
	private AdRecomReply parseResult(AdwoAdPullResponse resp)
			throws AdPullException {
		if(resp == null){
			return null;
		}
		if(1!=resp.getResultcode()){
			throw new AdPullException("request failed error code:"+resp.getErrorinfo()+" error msg:"+resp.getErrorinfo());
		}
		
		AdwoAd ad=resp.getAd();
		
		
		AdRecomReply reply=new AdRecomReply();
		reply.setStatus(status);
		//***********************BEGIN 常规设置*************
		AdContent content = new AdContent();
		AdMutiAction action = new AdMutiAction();
		String title = defTitle;
		String marketTitle=defTitle;
		// 内容类型1表示链接推广,2表示应用推广,3未使用 4表示电话推广
	 
        int acType=ACTION_WEB;
        if(ad.getActiontype()==3){
        	acType=ACTION_APP;
        }
        AdwoFeeds feeds=ad.getFeeds();
        
        
        //图片
        List<AdImg> imgs = new ArrayList<AdImg>();
        AdImg img=new AdImg();
        img.setSrc(feeds.getImg());
        imgs.add(img);
        
        if(StringUtils.isNotEmpty(feeds.getTxt())){
        	title=feeds.getTxt();
        }
		content.setLinkurl(checkMacro(ad.getTarget()));
		action.setLinkurl(checkMacro(ad.getTarget()));
		if(StringUtils.isNotEmpty(ad.getPk_name())){
			action.setCpPackage(ad.getPk_name());
			action.setCpIcon(feeds.getIco());
		}
		//上报
        Map<String, List<String>> map = new HashMap<String, List<String>>();
		//***********************END 常规设置***************
        if(CollectionUtils.isNotEmpty(ad.getShowbeacons())){
        	map.put(SHOW, ad.getShowbeacons());
        }
        List<String> clickL=new ArrayList<String>();
        if(CollectionUtils.isNotEmpty(ad.getBeacons())){
        	for(String url:ad.getBeacons()){
        		clickL.add(checkMacro(url));
        	}
        	map.put(CLICK, clickL);
        }
        
        
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
	private String checkMacro(String url){
		if(StringUtils.isEmpty(url)){
			return StringUtils.EMPTY;
		}
		String rpStr;
		try {
			rpStr = URLEncoder.encode("{\"down_x\":\""+MACRO_DOWN_X+"\",\"down_y\":\""+MACRO_DOWN_Y+"\",\"up_x\":\""+MACRO_UP_X+"\",\"up_y\":\""+MACRO_UP_Y+"\"}",CHARSET_CODER);
			return url.replaceAll("\\{CLICK_POSITION\\}", rpStr);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return StringUtils.EMPTY;
	}
	@Override
	public AsynAbstractTask packageTask(Parameter params, String hashCode) {
		// TODO Auto-generated method stub
		AdProxyAdwoTask task=new AdProxyAdwoTask();
		//heard
		Map<String, String> headers = new HashMap<String, String>(2);
		headers.put("Content-Type", "application/json");
		
		AdwoAdPullParams param = (AdwoAdPullParams) params;

		task.setHeaders(headers);
		

		
		//logger.info("joinDSP:adwo {} request param:{}",hashCode,UniversalUtils.replaceBlank(JsonUtils.toJson(param)));
		task.setParam(param);
		task.setDataFormat(DataFormat.DATA_FORMAT_JSON);
		task.setInvokeType(InvokeType.INVOKE_TYPE_POST);
		task.setHashCode(hashCode);
		task.setUrl(SystemContext.getDynamicPropertyHandler().get(ProxyConstants.ADWO_URL));
		
		return task;
	}


}
