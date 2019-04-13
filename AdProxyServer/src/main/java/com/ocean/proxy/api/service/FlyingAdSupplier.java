package com.ocean.proxy.api.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.ocean.persist.api.proxy.flying.FlyingAd;
import com.ocean.persist.api.proxy.flying.FlyingAdPullParams;
import com.ocean.persist.api.proxy.flying.FlyingAdPullResponse;
import com.ocean.persist.common.ProxyConstants;
import com.ocean.persist.model.proxy.DSPPosition;
import com.ocean.proxy.api.base.AbstractAsynAdSupplier;
import com.ocean.proxy.serverdis.AdProxyFlyingTask;
import com.ocean.proxy.thrift.entity.AdContent;
import com.ocean.proxy.thrift.entity.AdImg;
import com.ocean.proxy.thrift.entity.AdMutiAction;
import com.ocean.proxy.thrift.entity.AdRecomReply;
import com.ocean.proxy.thrift.entity.AdRecomReq;
import com.ocean.proxy.thrift.entity.AdUserInfo;



/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年12月19日 
      @version 1.0 
*/
@Component(value="FlyingAdSupplier")
public class FlyingAdSupplier    extends AbstractAsynAdSupplier {
	public AdRecomReply invoke(InvokeAttribute attribute)
			throws AdPullException {
		// TODO Auto-generated method stub
		AdRecomReq adreq = attribute.getAdreq();
		DSPPosition positionInfo = attribute.getDspPosition();
		// 参数转换
		FlyingAdPullParams params = parseParams(adreq, positionInfo);
		// 调用API
		FlyingAdPullResponse response = (FlyingAdPullResponse) invoke(params,FlyingAdPullResponse.class,adreq.getHash(),String.valueOf(adreq.getUserAdSpaceAttri().getAdSpaceId()),"joinDSP:adwo");
		
		// 解析结果
		return parseResult(response);
	}
	private FlyingAdPullParams parseParams(AdRecomReq adreq, 
			DSPPosition positionInfo) throws AdPullException {
		if(StringUtils.isEmpty(positionInfo.getText1())||StringUtils.isEmpty(positionInfo.getText2())||StringUtils.isEmpty(positionInfo.getText3())){
			throw  new AdPullException("appid/appkey or app pkg name is empty!");
		}
		AdUserInfo userInfo=adreq.getUserinfo();
	
		
		FlyingAdPullParams param =new FlyingAdPullParams();
		param.setAppid(positionInfo.getText1());
		param.setPosid(positionInfo.getPos());
		param.setSign(DigestUtils.md5Hex(positionInfo.getText1()+positionInfo.getText2()+positionInfo.getPos()));
		param.setBid(positionInfo.getText3());
		param.setAdcount(adreq.getResult_num());
		param.setMac(userInfo.getMac());
		param.setSw(userInfo.getDevice_width());
		param.setSh(userInfo.getDevice_height());
		param.setRemoteip(userInfo.getClient_ip());
		param.setBrand(userInfo.getBrand_name());
		try {
			param.setModel(URLEncoder.encode(userInfo.getPhonemodel(), CHARSET_CODER));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		param.setOs( userInfo.getOs());
		param.setAsver(this.convOSVersion(userInfo.getOsversion()));
		
		param.setImei(userInfo.getImei());
		param.setImsi(userInfo.getImsi());
		param.setAid(userInfo.getAdid());
		param.setNet(getNetty(adreq.getNet()));
		param.setIsp(getOp(userInfo.getMobile()));

		return param;
	}
	private int  getNetty(String net){
		if(NETWORK_WIFI.equals(net)){
			return 1;
		}else if(NETWORK_2G.equals(net)){
			return 3;
		}else if(NETWORK_3G.equals(net)){
			return 4;
		}else if(NETWORK_4G.equals(net)){
			return 5;
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
	private AdRecomReply parseResult(FlyingAdPullResponse resp)
			throws AdPullException {
		if(resp == null||CollectionUtils.isEmpty(resp.getObjAds())){
			return null;
		}
		if(resp.getIntError()!=0){
			throw new AdPullException("ad request error, return error code:"+resp.getIntError()+",error massage:"+resp.getStrMsg());
			
		}
		AdRecomReply reply=new AdRecomReply();
		reply.setStatus(status);
		reply.setAd_contents(dealAds(resp));
		return reply;
	}
	private List<AdContent>  dealAds(FlyingAdPullResponse resp){
		List<AdContent> list=new ArrayList<AdContent> ();
		for(FlyingAd ad:resp.getObjAds()){
			//***********************BEGIN 常规设置*************
			AdContent content = new AdContent();
			AdMutiAction action = new AdMutiAction();
			String title = defTitle;
			String marketTitle=defTitle;
			// 内容类型1表示链接推广,2表示应用推广,3未使用 4表示电话推广
	        int acType=ACTION_WEB;
	      //***********************END 常规设置*************
	      //  FlyingAd ad=resp.getObjAds().get(0);
	        if(ad.getIntActionType()!=2){
	        	acType=ACTION_APP;
	        }
	        
	        //图片
	        List<AdImg> imgs = new ArrayList<AdImg>();
	        if(StringUtils.isNotEmpty(ad.getStrImageUrl())){
	            AdImg img=new AdImg();
	            img.setSrc(ad.getStrImageUrl());
	            imgs.add(img);
	            
	        }
/*	        AdImg logo=  new AdImg();
	        logo.setSrc(TENSENT_LOGO);
	        content.setLogoImg(logo);*/
	        if(CollectionUtils.isNotEmpty(ad.getArrAdLogo())){
	        	AdImg logo=  new AdImg();
	        	for(String url:ad.getArrAdLogo()){
	        		if(StringUtils.isNotEmpty(url)){
	        			logo.setSrc(url);
	        			break;
	        		}
	        	}
		        
		        content.setLogoImg(logo);
	        }
	        
	        
	        if(StringUtils.isNotEmpty(ad.getStrTitle())){
	        	title=ad.getStrTitle();
	        }
	        String link=ad.getStrLinkUrl();
	        if(StringUtils.isNotEmpty(link)){
	        	StringBuilder sb=new StringBuilder(link);
				sb.append(link.indexOf("?")<0?"?":"&").append(MACRO_COORDINATE);
				content.setLinkurl(sb.toString());
				action.setLinkurl(sb.toString());
	        }
			if(ad.getIntActionType()==3){//需要解析获取真实下载链接
				
				action.setLinkurl_type(1);
			}
			

			//上报
	        Map<String, List<String>> map = new HashMap<String, List<String>>();
			//***********************END 常规设置***************
	        if(CollectionUtils.isNotEmpty(ad.getArrViewTrackUrl())){
	        	map.put(SHOW, ad.getArrViewTrackUrl());
	        }
	        List<String> clickL=new ArrayList<String>();
	        if(CollectionUtils.isNotEmpty(ad.getArrClickTrackUrl())){
	        	clickL.addAll(ad.getArrClickTrackUrl());
	        }
	        if(CollectionUtils.isNotEmpty(ad.getArrDownloadTrackUrl())){
	        	
	        	if(ad.getIntActionType()==3){
					for(String url:ad.getArrDownloadTrackUrl()){
						String sufix=url.indexOf("?")<0?"?":"&";
						clickL.add(url+sufix+"clickid=%%CLICKID%%"+"&"+MACRO_COORDINATE);
					}
	        	}else{
	        		clickL.addAll(ad.getArrDownloadTrackUrl());
	        	}
	        	acType=ACTION_APP;
	        }
	        map.put(CLICK, clickL);
	        List<String> downloadL=new ArrayList<String>();
	        if(CollectionUtils.isNotEmpty(ad.getArrDownloadedTrakUrl())){
	        	
	        	if(ad.getIntActionType()==3){
					for(String url:ad.getArrDownloadedTrakUrl()){
						String sufix=url.indexOf("?")<0?"?":"&";
						downloadL.add(url+sufix+"clickid=%%CLICKID%%");
					}
	        	}else{
	        		downloadL.addAll(ad.getArrDownloadedTrakUrl());
	        	}
	        	acType=ACTION_APP;
	        }
	        map.put(DOWNLOAD, downloadL);
	        
	        List<String> installL=new ArrayList<String>();
	        if(CollectionUtils.isNotEmpty(ad.getArrIntalledTrackUrl())){
	        	if(ad.getIntActionType()==3){
					for(String url:ad.getArrIntalledTrackUrl()){
						String sufix=url.indexOf("?")<0?"?":"&";
						installL.add(url+sufix+"clickid=%%CLICKID%%");
					}
	        	}else{
	        		installL.addAll(ad.getArrIntalledTrackUrl());
	        	}
	        	
	        	 acType=ACTION_APP;
	        }
	        map.put(INSTALL, installL);
	        
	        
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
		AdProxyFlyingTask task=new AdProxyFlyingTask();
		
		FlyingAdPullParams param = (FlyingAdPullParams) params;
		StringBuilder url = new StringBuilder();
		url.append(SystemContext.getDynamicPropertyHandler().get(ProxyConstants.FLYING_URL));
		url.append("?").append(Bean2Utils.toHttpParams(param)).append("&requestId=").append(hashCode);


		
		//logger.info("joinDSP:flying {} request param:{}",hashCode,url.toString());
		task.setParam(param);
		task.setDataFormat(DataFormat.DATA_FORMAT_KVP);
		task.setInvokeType(InvokeType.INVOKE_TYPE_GET);
		task.setHashCode(hashCode);
		task.setUrl(url.toString());
		
		return task;
	}

}
