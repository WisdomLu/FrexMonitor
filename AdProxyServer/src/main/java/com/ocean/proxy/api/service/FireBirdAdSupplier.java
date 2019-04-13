package com.ocean.proxy.api.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.ocean.persist.api.proxy.firebird.FireBirdAd;
import com.ocean.persist.api.proxy.firebird.FireBirdAdPullParams;
import com.ocean.persist.api.proxy.firebird.FireBirdAdPullResponse;
import com.ocean.persist.common.ProxyConstants;
import com.ocean.persist.model.proxy.DSPPosition;
import com.ocean.proxy.api.base.AbstractAsynAdSupplier;
import com.ocean.proxy.serverdis.AdProxyFireBirdTask;
import com.ocean.proxy.thrift.entity.AdContent;
import com.ocean.proxy.thrift.entity.AdImg;
import com.ocean.proxy.thrift.entity.AdMutiAction;
import com.ocean.proxy.thrift.entity.AdRecomReply;
import com.ocean.proxy.thrift.entity.AdRecomReq;
import com.ocean.proxy.thrift.entity.AdUserInfo;



/** * 储世界
 * 	  @author Alex & E-mail:569246607@qq.com
      @date   2017年12月19日 
      @version 1.0 
 */
@Component(value="FireBirdAdSupplier")
public class FireBirdAdSupplier    extends AbstractAsynAdSupplier {

	public AdRecomReply invoke(InvokeAttribute attribute)
			throws AdPullException {
		// TODO Auto-generated method stub
		AdRecomReq adreq = attribute.getAdreq();
		DSPPosition positionInfo = attribute.getDspPosition();
		// 参数转换
		FireBirdAdPullParams params = parseParams(adreq, positionInfo);
		// 调用API
		FireBirdAdPullResponse response = (FireBirdAdPullResponse) invoke(params,FireBirdAdPullResponse.class,adreq.getHash(),String.valueOf(adreq.getUserAdSpaceAttri().getAdSpaceId()),"joinDSP:adwo");
		
		// 解析结果
		return parseResult(response);
	}
	private FireBirdAdPullParams parseParams(AdRecomReq adreq, 
			DSPPosition positionInfo) throws AdPullException {
		if(StringUtils.isEmpty(positionInfo.getText1())){
			throw  new AdPullException("appid is empty!");
		}
		AdUserInfo userInfo=adreq.getUserinfo();
	
		
		FireBirdAdPullParams param =new FireBirdAdPullParams();
		param.setAppid(positionInfo.getText1());
		param.setPosid(positionInfo.getPos());
		param.setImei(userInfo.getImei());
		param.setImsi(userInfo.getImsi());
		
		param.setHsman(userInfo.getBrand_name());
		param.setOsver(this.convOSVersion(userInfo.getOsversion()));
		param.setDensity(userInfo.getDensity());
		param.setNettype(getNetty(adreq.getNet()));
		param.setDevicetype(0);
		String os = userInfo.getOs();
		if(OS_ANDROID.equals(os)){
			param.setOs("Android");
		}else{
			param.setOs("IOS");
		}
		param.setAdid(userInfo.getAdid());
		param.setMac(userInfo.getMac());
		param.setDvw(String.valueOf(userInfo.getDevice_width()));
		param.setDvh(String.valueOf(userInfo.getDevice_height()));
		param.setClientIp(userInfo.getClient_ip());
		try {
			param.setHstype(URLEncoder.encode(userInfo.getPhonemodel(),CHARSET_CODER));
			param.setClientUa(URLEncoder.encode(userInfo.getUa(),CHARSET_CODER));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		param.setOperator(getOp(userInfo.getMobile()));
		if(StringUtils.isNotEmpty(userInfo.getLat())&&StringUtils.isNotEmpty(userInfo.getLon())){
			param.setLat(userInfo.getLat());
			param.setLon(userInfo.getLon());
		}
		return param;
	}
	private int  getNetty(String net){
		if(NETWORK_WIFI.equals(net)){
			return 1;
		}else if(NETWORK_2G.equals(net)){
			return 2;
		}else if(NETWORK_3G.equals(net)){
			return 3;
		}else if(NETWORK_4G.equals(net)){
			return 4;
		}
		return 0;
	}
	private String  getOp(String mobile){
		if(MOBILE_CMCC.equals(mobile)){
			return "0";
		}else if(MOBILE_CUCC.equals(mobile)){
			return "1";
		}else if(MOBILE_CTCC.equals(mobile)){
			return "2";
		}
		return "-1";
	}
	private AdRecomReply parseResult(FireBirdAdPullResponse resp)
			throws AdPullException {
		if(resp == null||CollectionUtils.isEmpty(resp.getRspObject())){
			return null;
		}
		if(resp.getRspCode()!=200){
			throw new AdPullException("ad request error, return error code:"+resp.getRspCode()+",error massage:"+resp.getRspMsg());
			
		}
		AdRecomReply reply=new AdRecomReply();
		reply.setStatus(status);
		reply.setAd_contents(this.dealAds(resp));
		return reply;
	}
	private List<AdContent>  dealAds(FireBirdAdPullResponse resp){
		List<AdContent> list=new ArrayList<AdContent> ();
		for(FireBirdAd ad:resp.getRspObject()){
			//***********************BEGIN 常规设置*************
			AdContent content = new AdContent();
			AdMutiAction action = new AdMutiAction();
			String title = defTitle;
			String marketTitle=defTitle;
			// 内容类型1表示链接推广,2表示应用推广,3未使用 4表示电话推广
		 
	        int acType=ACTION_WEB;
	        if(ad.getType()==1){
	        	acType=ACTION_APP;
	        }
	        //图片
	        List<AdImg> imgs = new ArrayList<AdImg>();
	        if(StringUtils.isNotEmpty(ad.getPhoto())){
	            AdImg img=new AdImg();
	            img.setSrc(ad.getPhoto());
	            imgs.add(img);
	        }

	        if(StringUtils.isNotEmpty(ad.getTitle())){
	        	title=ad.getTitle();
	        }
			content.setLinkurl(ad.getLink());
			action.setLinkurl(ad.getLink());

			//上报
	        Map<String, List<String>> map = new HashMap<String, List<String>>();
			//***********************END 常规设置***************
	        if(CollectionUtils.isNotEmpty(ad.getExposure())){
	        	map.put(SHOW, ad.getExposure());
	        }
	        List<String> clickL=new ArrayList<String>();
	        if(CollectionUtils.isNotEmpty(ad.getClick())){
	        	clickL.addAll(ad.getClick());
	        }
	        if(CollectionUtils.isNotEmpty(ad.getDldpre())){
	        	clickL.addAll(ad.getDldpre());
	        }
	        map.put(CLICK, clickL);
	        List<String> downloadL=new ArrayList<String>();
	        if(CollectionUtils.isNotEmpty(ad.getDldok())){
	        	downloadL.addAll(ad.getDldok());
	        	//acType=ACTION_APP;
	        }
	        if(CollectionUtils.isNotEmpty(ad.getInspre())){
	        	downloadL.addAll(ad.getInspre());
	        	//acType=ACTION_APP;
	        }
	        map.put(DOWNLOAD, downloadL);
	        
	        if(CollectionUtils.isNotEmpty(ad.getInsok())){
	        	 map.put(INSTALL, ad.getInsok());
	        	// acType=ACTION_APP;
	        }
	        
	        if(CollectionUtils.isNotEmpty(ad.getLaunch())){
	        	 map.put(ACTIVE, ad.getLaunch());
	        	// acType=ACTION_APP;
	        }
	        
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
		AdProxyFireBirdTask task=new AdProxyFireBirdTask();
		
		FireBirdAdPullParams param = (FireBirdAdPullParams) params;
		StringBuilder url = new StringBuilder();
		url.append(SystemContext.getDynamicPropertyHandler().get(ProxyConstants.FIREBIRD_URL));
		url.append("?").append(Bean2Utils.toHttpParams(param)).append("&requestId=").append(hashCode);


		
		///logger.info("joinDSP:firebird {} request param:{}",hashCode,url.toString());
		task.setParam(param);
		task.setDataFormat(DataFormat.DATA_FORMAT_KVP);
		task.setInvokeType(InvokeType.INVOKE_TYPE_GET);
		task.setHashCode(hashCode);
		task.setUrl(url.toString());
		
		return task;
	}

}
