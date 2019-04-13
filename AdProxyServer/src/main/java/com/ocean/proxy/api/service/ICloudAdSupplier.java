package com.ocean.proxy.api.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inveno.util.CollectionUtils;
import com.ocean.core.common.system.ErrorCode;
import com.ocean.core.common.system.SystemContext;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
import com.ocean.persist.api.proxy.AdPullException;
import com.ocean.persist.api.proxy.icloud.ICloudAd;
import com.ocean.persist.api.proxy.icloud.ICloudAdPullParams;
import com.ocean.persist.api.proxy.icloud.ICloudAdPullResponse;
import com.ocean.persist.api.proxy.icloud.ICloudAdPuller;
import com.ocean.persist.common.ProxyConstants;
import com.ocean.persist.model.proxy.DSPPosition;
import com.ocean.proxy.api.base.AbstractAdSupplier;
import com.ocean.proxy.thrift.entity.AdContent;
import com.ocean.proxy.thrift.entity.AdImg;
import com.ocean.proxy.thrift.entity.AdMutiAction;
import com.ocean.proxy.thrift.entity.AdRecomReply;
import com.ocean.proxy.thrift.entity.AdRecomReq;
import com.ocean.proxy.thrift.entity.AdSpaceType;
import com.ocean.proxy.thrift.entity.AdUserInfo;
import com.ocean.proxy.thrift.entity.UserAdSpaceAttri;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年7月12日 
      @version 1.0 
 */
@Component(value="iCloudAdSupplier")
public class ICloudAdSupplier      extends AbstractAdSupplier{
	protected static final Map<String, Integer> icMobiles = new HashMap<String, Integer>(3);
	private static final Map<String, Integer> icconn = new HashMap<String, Integer>(4);

	static{

		icMobiles.put("CMCC", 1);
		icMobiles.put("CUCC", 3);
		icMobiles.put("CTCC", 2);
		icconn.put(NETWORK_2G, 2);
		icconn.put(NETWORK_3G, 3);
		icconn.put(NETWORK_4G, 4);
		icconn.put(NETWORK_WIFI, 1);
	}
    @Autowired
    private ICloudAdPuller iCloudAdPuller;
    private AtomicInteger ai=new AtomicInteger(1);
	public AdRecomReply invoke(InvokeAttribute attribute)
			throws AdPullException {
		// TODO Auto-generated method stub

		AdRecomReq adreq = attribute.getAdreq();
		DSPPosition positionInfo = attribute.getDspPosition();
		// 参数转换
		ICloudAdPullParams params = parseParams(adreq, positionInfo);
		// 调用API
		ICloudAdPullResponse response = (ICloudAdPullResponse)iCloudAdPuller.api(params,positionInfo.getText1(),adreq.getHash(),String.valueOf(adreq.getUserAdSpaceAttri().getAdSpaceId()));
		// 解析结果
		return parseResult(response);
	}
	private ICloudAdPullParams parseParams(AdRecomReq adreq, 
			DSPPosition positionInfo) throws AdPullException {
		if(StringUtils.isEmpty(positionInfo.getText1())||StringUtils.isEmpty(positionInfo.getText2())){
			throw new AdPullException("channel id or channel id is empty!");
		}
		UserAdSpaceAttri attri=adreq.getUserAdSpaceAttri();
		AdUserInfo user=adreq.getUserinfo();
		ICloudAdPullParams param=new ICloudAdPullParams();
		param.setChannelid(positionInfo.getText1());
		int index=ai.getAndIncrement();
		if(Integer.MAX_VALUE==index){
			ai.getAndSet(1);
		}
		String bid=new StringBuffer().append(positionInfo.getPos()).append(System.currentTimeMillis())+""+index%10000;
		param.setBid(bid);

	    String adType=conPosType(positionInfo.getPosType());
/*		if(adType.equals("2")){
	    	param.setAdspaceid("8DB6738425A2EC87");
	    }else if(adType.equals("4")){
	    	param.setAdspaceid("BB85961C7F212385");
	    }*/
	    param.setAdspaceid(positionInfo.getPos());
		param.setAdtype(adType);
		param.setPkgname(positionInfo.getText2());
		param.setAppname(adreq.getApp());
		Integer conn=icconn.get(adreq.getNet());
		
		param.setConn(conn==null?"0":String.valueOf(conn));
		Integer car=icMobiles.get(user.getMobile());
		param.setCarrier(car==null?"0":String.valueOf(car));
		param.setApitype("2");
		String osCode = user.getOs();
		// 如果是ios
		String osType="0";
	    if(OS_IOS.equals(osCode)){
			// ios 必填信息
	    	osType="1";
	    	param.setIdfa(user.getIdfa());
	    	param.setOid("");
	    }else if(OS_ANDROID.equals(osCode)){
	    	
	    	param.setImei(user.getImei());
	    	if(StringUtils.isNotEmpty(user.getAaid())){
	    		param.setAid(user.getAaid());
	    	}else{
	    		throw new AdPullException(ErrorCode.PARAM_ERROR,"imei is empty");
	    	}
	    	if(StringUtils.isEmpty(user.getAaid())){
	    		param.setAaid(user.getAdid());
	    	}else{
	    		param.setAaid("");
	    	}
	    	
	    
		}
	    if(StringUtils.isNotEmpty(user.getMac())&&!user.getMac().equals(user.getImei())){
	    	param.setWma(user.getMac().replaceAll(":", "").toUpperCase());
	    }else{
	    	throw new AdPullException(ErrorCode.PARAM_ERROR,"mac is empty or mac equals imei");
	    }
	    
	    	
	    
	    param.setOsv(this.convOSVersion(user.getOsversion()));
	    param.setOs(osType);
		param.setDevice(user.getBrand_name()+user.getPhonemodel());
		param.setUa(user.getUa());
		param.setIp(user.getClient_ip());
		param.setWidth(String.valueOf(attri.getSpaceWidth()));
		param.setHeight(String.valueOf(attri.getSpaceHeight()));
		param.setPid("yy000000");
		param.setPcat("10");
		param.setMedia("2");
		//param.setDebug(""+SystemContext.getDynamicPropertyHandler().getInt(ProxyConstants.ICLOUD_IS_TEST,0));
		if(StringUtils.isNotEmpty(user.getDensity())){
			param.setDensity(user.getDensity());
		}else{
			param.setDensity("");
		}
		if(StringUtils.isNotEmpty(user.getLat())&&StringUtils.isNotEmpty(user.getLon())){
			param.setLat(user.getLat());
			param.setLon(user.getLon());
		}else{
			param.setLat("");
			param.setLon("");	
		}
		if(StringUtils.isNotEmpty(user.getImsi())){
			param.setImsi(user.getImsi());
		}else{
			throw new AdPullException(ErrorCode.PARAM_ERROR,"imsi is empty");
			
		}
		String ggid=DigestUtils.md5Hex(param.getImsi()+param.getImei()+param.getWma());
		param.setGgid(ggid);
		param.setPh(String.valueOf(user.getDevice_height()));
		param.setPw(String.valueOf(user.getDevice_width()));
		return param;
	}
	private String  conPosType(int posType) throws AdPullException{
		try{
			AdSpaceType type=AdSpaceType.findByValue(posType);
			switch(type){
			    case BANNER:
			    	return "2";
			    case OPENING:
			    	return "4";
			    case INTERSTITIAL:
			    	throw new AdPullException("the ad type INTERSTITIAL is not suported!");
			    case INFOFLOW:
			    	throw new AdPullException("the ad type is INFOFLOW not suported!");
			    default:
			    	throw new AdPullException("the ad type "+type+" is not suported!");
			}
		}catch(Exception e){
			throw new AdPullException("get ad type error,error msg:"+e.getMessage());
			
		}
		
	}
	private AdRecomReply parseResult(ICloudAdPullResponse response)
			throws AdPullException {
		
		if(response == null){
			return null;
		}
		if(response.getReturncode()!=200){
			throw new AdPullException("icloud ad request error,return error code:"+response.getReturncode());
		}
	
		AdRecomReply reply=new AdRecomReply();
		reply.setStatus(status);
		//***********************BEGIN 常规设置*************
		AdContent content = new AdContent();
		AdMutiAction action = new AdMutiAction();
		String title = defTitle;
		String markTitle= defTitle;
		// 内容类型1表示链接推广,2表示应用推广,3未使用 4表示电话推广
        int acType=ACTION_WEB;
        
		//***********************END 常规设置***************
        //落地页
        String linkurl=response.getClickurl();
        if(StringUtils.isNotEmpty(linkurl)){
        	content.setLinkurl(linkurl);
        	action.setLinkurl(linkurl);
        }
        
        //img
        List<AdImg> imgs = new ArrayList<AdImg>();
        if(StringUtils.isNotEmpty(response.getImgurl())){


    			AdImg img=new AdImg();
    			img.setSrc(response.getImgurl());
    			imgs.add(img);
    			
        }
        content.setImglist(imgs);
        if(StringUtils.isNotEmpty(response.getTitle())){
        	title=response.getTitle();
        }
        if(StringUtils.isNotEmpty(response.getDescription())){
        	markTitle=response.getDescription();
        }
        
        
        //上报
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        if(CollectionUtils.isNotEmpty(response.getImgtracking())){
        	map.put(SHOW, response.getImgtracking());
        }
        if(CollectionUtils.isNotEmpty(response.getThclkurl())){
        	map.put(CLICK, response.getThclkurl());
        }
        
        content.setThirdReportLinks(map);
		
	    action.setType(acType); 
	    action.setGuideTitle(title);
		content.setMarketTitle(markTitle);
		content.setGuideTitle(title);
		content.setMutiAction(Collections.singletonList(action));

		reply.setAd_contents(Collections.singletonList(content));
		return reply;
	}

}
