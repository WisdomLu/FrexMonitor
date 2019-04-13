package com.ocean.proxy.api.service;
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
import com.ocean.persist.api.proxy.paerjiate_t2.ParerjiateT2Ad;
import com.ocean.persist.api.proxy.paerjiate_t2.ParerjiateT2AdPullParams;
import com.ocean.persist.api.proxy.paerjiate_t2.ParerjiateT2AdPullResponse;
import com.ocean.persist.common.ProxyConstants;
import com.ocean.persist.model.proxy.DSPPosition;
import com.ocean.proxy.api.base.AbstractAsynAdSupplier;
import com.ocean.proxy.serverdis.AdProxyPaerjiatePvTask;
import com.ocean.proxy.thrift.entity.AdContent;
import com.ocean.proxy.thrift.entity.AdImg;
import com.ocean.proxy.thrift.entity.AdMutiAction;
import com.ocean.proxy.thrift.entity.AdRecomReply;
import com.ocean.proxy.thrift.entity.AdRecomReq;
import com.ocean.proxy.thrift.entity.AdSpaceType;
import com.ocean.proxy.thrift.entity.AdUserInfo;



/** * 储世界
 * 	  @author Alex & E-mail:569246607@qq.com
      @date   2017年12月19日 
      @version 1.0 
 */
@Component(value="PaerjiateT2AdSupplier")
public class PaerjiateT2AdSupplier    extends AbstractAsynAdSupplier {
	private static final String PT2_ABZMX="\\[downx\\]";
	private static final String PT2_ABZMY="\\[downy\\]";
	private static final String PT2_ABZCX="\\[upx\\]";
	private static final String PT2_ABZCY="\\[upy\\]";
	
	public AdRecomReply invoke(InvokeAttribute attribute)
			throws AdPullException {
		// TODO Auto-generated method stub
		AdRecomReq adreq = attribute.getAdreq();
		DSPPosition positionInfo = attribute.getDspPosition();
		// 参数转换
		ParerjiateT2AdPullParams params = parseParams(adreq, positionInfo);
		// 调用API
		ParerjiateT2AdPullResponse response = (ParerjiateT2AdPullResponse) invoke(params,ParerjiateT2AdPullResponse.class,adreq.getHash(),String.valueOf(adreq.getUserAdSpaceAttri().getAdSpaceId()),"joinDSP:adwo");
		
		// 解析结果
		return parseResult(response);
	}
	private ParerjiateT2AdPullParams parseParams(AdRecomReq adreq, 
			DSPPosition positionInfo) throws AdPullException {
		AdUserInfo userInfo=adreq.getUserinfo();
	
		ParerjiateT2AdPullParams param =new ParerjiateT2AdPullParams();
		param.setRequest_id(UUID.randomUUID().toString().replaceAll("-", ""));
		param.setAdcount(1);
		param.setCount(1);
		param.setPosid(positionInfo.getPos());
		param.setDatafmt("json");
		param.setUa(userInfo.getUa());
		param.setVos(userInfo.getOsversion());
		
		
		String osCode = userInfo.getOs();
		if(OS_IOS.equals(osCode)){
			// ios 必填信息
			param.setMuidtype(1);
			param.setOs(0);
			param.setMuid(userInfo.getImei());
		}else if(OS_ANDROID.equals(osCode)){
			param.setMuidtype(2);
			param.setOs(1);
			param.setMuid(userInfo.getIdfa());
		}
		param.setConn(this.getNetty(adreq.getNet()));
		param.setCarrier(this.getOp(userInfo.getMobile()));
		param.setCw(userInfo.getDevice_width());
		param.setCh(userInfo.getDevice_height());
		param.setVendor(userInfo.getBrand_name());
		param.setModel(userInfo.getPhonemodel());
		param.setTdevice(1);
		param.setAdype(getAdType(adreq.getUserAdSpaceAttri().getSpaceType()));
		param.setVapi("1.4");
		param.setCori(0);
		param.setMac(userInfo.getMac());
		param.setAnid(userInfo.getAdid());
		param.setIp(userInfo.getClient_ip());
		param.setDensity(userInfo.getDensity());
		if(!StringUtils.isEmpty(userInfo.getLon())&&!StringUtils.isEmpty(userInfo.getLat())){
           param.setLat(userInfo.getLat());
           param.setLng(userInfo.getLon());
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
	private int  getOp(String mobile){
		if(MOBILE_CMCC.equals(mobile)){
			return 1;
		}else if(MOBILE_CUCC.equals(mobile)){
			return 2;
		}else if(MOBILE_CTCC.equals(mobile)){
			return 3;
		}
		return 0;
	}
	private int getAdType(AdSpaceType type){
		switch(type){
		   case BANNER:
			   return 1;
		   case INTERSTITIAL:
			   return 2;
		   case OPENING:
			   return 4;
		   case INFOFLOW:
			   return 3;
		   default:
			   return 4;
		}
	}
	private AdRecomReply parseResult(ParerjiateT2AdPullResponse resp)
			throws AdPullException {
		if(resp == null||CollectionUtils.isEmpty(resp.getAds())){
			return null;
		}
		if(resp.getCode()!=0){
			throw new AdPullException("ad request error, return error code:"+resp.getCode()+",error massage:"+resp.getMsg());
			
		}
		AdRecomReply reply=new AdRecomReply();
		reply.setStatus(status);
		reply.setAd_contents(this.dealAds(resp));
		return reply;
	}
	private List<AdContent>  dealAds(ParerjiateT2AdPullResponse resp){
		List<AdContent> list=new ArrayList<AdContent> ();
		for(ParerjiateT2Ad ad:resp.getAds()){
			//***********************BEGIN 常规设置*************
			AdContent content = new AdContent();
			AdMutiAction action = new AdMutiAction();
			String title = defTitle;
			String marketTitle=defTitle;
			// 内容类型1表示链接推广,2表示应用推广,3未使用 4表示电话推广
		 
	        int acType=ACTION_WEB;
	        if(ad.getAdtype()==2){
	        	acType=ACTION_APP;
	        }
	        //图片
	        List<AdImg> imgs = new ArrayList<AdImg>();
	        if(CollectionUtils.isNotEmpty(ad.getImgurls())){
	        	for(String url: ad.getImgurls()){
	        		AdImg img=new AdImg();
	 	            img.setSrc(url);
	 	            imgs.add(img);
	        	}
	           
	        }

	        if(StringUtils.isNotEmpty(ad.getTitle())){
	        	title=ad.getTitle();
	        }
	        if(StringUtils.isNotEmpty(ad.getLandingPageUrl())){
				content.setLinkurl(ad.getLandingPageUrl());
				action.setLinkurl(ad.getLandingPageUrl());
	        }

			if(StringUtils.isNotEmpty(ad.getDeeplink())){
				action.setActiveUri(ad.getDeeplink());
			}
			
			
			//上报
	        Map<String, List<String>> map = new HashMap<String, List<String>>();
			//***********************END 常规设置***************
	        if(CollectionUtils.isNotEmpty(ad.getViewUrls())){
	        	map.put(SHOW, ad.getViewUrls());
	        }
	        List<String> clickL=new ArrayList<String>();
	        if(CollectionUtils.isNotEmpty(ad.getClickUrls())){
	        	for(String url:ad.getClickUrls()){
	        		clickL.add(this.urlFormat(url));
	        	}
	        
	        }
	        if(CollectionUtils.isNotEmpty(ad.getStartdownUrls())){
	        	clickL.addAll(ad.getStartdownUrls());
	        }
	        map.put(CLICK, clickL);
	        List<String> downloadL=new ArrayList<String>();
	        if(CollectionUtils.isNotEmpty(ad.getFinishdownUrls())){
	        	downloadL.addAll(ad.getFinishdownUrls());
	        	//acType=ACTION_APP;
	        }
	        if(CollectionUtils.isNotEmpty(ad.getStartinstallUrls())){
	        	downloadL.addAll(ad.getStartinstallUrls());
	        	//acType=ACTION_APP;
	        }
	        map.put(DOWNLOAD, downloadL);
	        
	        if(CollectionUtils.isNotEmpty(ad.getFinishinstallUrls())){
	        	 map.put(INSTALL, ad.getFinishinstallUrls());
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
	private String urlFormat(String url){
		String format= url.replaceAll(PT2_ABZMX, MACRO_DOWN_X).replaceAll(PT2_ABZMY, MACRO_DOWN_Y)
				.replaceAll(PT2_ABZCX, MACRO_UP_X).replaceAll(PT2_ABZCY,MACRO_UP_Y);
		return format;
	}
	@Override
	public AsynAbstractTask packageTask(Parameter params, String hashCode) {
		// TODO Auto-generated method stub
		AdProxyPaerjiatePvTask task=new AdProxyPaerjiatePvTask();
		
		ParerjiateT2AdPullParams param = (ParerjiateT2AdPullParams) params;
		StringBuilder url = new StringBuilder();
		url.append(SystemContext.getDynamicPropertyHandler().get(ProxyConstants.PAERJAITE_PV_URL));

		Map<String, String> headers = new HashMap<String, String>(2);
		headers.put("Content-Type", "application/json");
		
		task.setParam(param);
		task.setDataFormat(DataFormat.DATA_FORMAT_JSON);
		task.setInvokeType(InvokeType.INVOKE_TYPE_POST);
		task.setHashCode(hashCode);
		task.setUrl(url.toString());
		task.setHeaders(headers);
		return task;
	}

}
