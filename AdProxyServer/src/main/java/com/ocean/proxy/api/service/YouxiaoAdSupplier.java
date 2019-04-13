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
import com.ocean.persist.api.proxy.youxiao.YouxiaoAd;
import com.ocean.persist.api.proxy.youxiao.YouxiaoAdPullParams;
import com.ocean.persist.api.proxy.youxiao.YouxiaoAdPullResponse;
import com.ocean.persist.api.proxy.youxiao.YouxiaoTrack;
import com.ocean.persist.common.ProxyConstants;
import com.ocean.persist.model.proxy.DSPPosition;
import com.ocean.proxy.api.base.AbstractAsynAdSupplier;
import com.ocean.proxy.serverdis.AdProxyYouxiaoTask;
import com.ocean.proxy.thrift.entity.AdContent;
import com.ocean.proxy.thrift.entity.AdImg;
import com.ocean.proxy.thrift.entity.AdMutiAction;
import com.ocean.proxy.thrift.entity.AdRecomReply;
import com.ocean.proxy.thrift.entity.AdRecomReq;
import com.ocean.proxy.thrift.entity.AdSpaceType;
import com.ocean.proxy.thrift.entity.AdUserInfo;



/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年12月19日 
      @version 1.0 
*/
@Component(value="YouxiaoAdSupplier")
public class YouxiaoAdSupplier    extends AbstractAsynAdSupplier {

	public AdRecomReply invoke(InvokeAttribute attribute)
			throws AdPullException {
		// TODO Auto-generated method stub
		AdRecomReq adreq = attribute.getAdreq();
		DSPPosition positionInfo = attribute.getDspPosition();
		// 参数转换
		YouxiaoAdPullParams params = parseParams(adreq, positionInfo);
		// 调用API
		YouxiaoAdPullResponse response = (YouxiaoAdPullResponse) invoke(params,YouxiaoAdPullResponse.class,adreq.getHash(),String.valueOf(adreq.getUserAdSpaceAttri().getAdSpaceId()),"joinDSP:adwo");
		
		// 解析结果
		return parseResult(response);
	}
	private YouxiaoAdPullParams parseParams(AdRecomReq adreq, 
			DSPPosition positionInfo) throws AdPullException {
		if(StringUtils.isEmpty(positionInfo.getText1())||StringUtils.isEmpty(positionInfo.getText3())){
			throw  new AdPullException("appid or app pkg name is empty!");
		}
		AdUserInfo userInfo=adreq.getUserinfo();
		
		
		YouxiaoAdPullParams param =new YouxiaoAdPullParams();
		param.setAppid(positionInfo.getText1());
		param.setAdvplaceid(positionInfo.getPos());
		param.setMuidtype(1);
		param.setMode(2);
		param.setMac(userInfo.getMac());
		param.setOs(userInfo.getOs());
		param.setDevicetype("phone");
		if(StringUtils.isNotEmpty(userInfo.getLat())&&StringUtils.isNotEmpty(userInfo.getLon())){
			param.setLat(userInfo.getLat());
			param.setLng(userInfo.getLon());
			
		}
		param.setOsversion(this.convOSVersion(userInfo.getOsversion()));
		String os = userInfo.getOs();
		if(OS_ANDROID.equals(os)){
			param.setImeiidfa(userInfo.getImei());
		}else{
			param.setImeiidfa(userInfo.getIdfa());
		}
		try {
			param.setModel(URLEncoder.encode(userInfo.getPhonemodel(),CHARSET_CODER));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		param.setC_device(userInfo.getBrand_name());
		param.setCarrieroperator(this.getOp(userInfo.getMobile()));
		param.setNetwork(this.getNetty(adreq.getNet()));
		param.setC_w(userInfo.getDevice_width());
		param.setC_h(userInfo.getDevice_height());
		param.setIp(userInfo.getClient_ip());
		param.setC_pkgname(positionInfo.getText3());
		try {
			param.setUa(URLEncoder.encode(userInfo.getUa(),CHARSET_CODER));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		param.setDensity(userInfo.getDensity());
		param.setAppversion(adreq.getVersion());
		param.setAnid(userInfo.getAdid());
		int poseType = positionInfo.getPosType();
		if(poseType == AdSpaceType.BANNER.getValue()){
			String sizeId=SystemContext.getDynamicPropertyHandler().get(ProxyConstants.YOUDXIAO_BANNER_ID);
			param.setSizeid(StringUtils.isEmpty(sizeId)?"101":sizeId);
			
		}else if(poseType == AdSpaceType.INTERSTITIAL.getValue()){
			String sizeId=SystemContext.getDynamicPropertyHandler().get(ProxyConstants.YOUDXIAO_INTERSTITIAL_ID);
			param.setSizeid(StringUtils.isEmpty(sizeId)?"201":sizeId);
		}else if(poseType == AdSpaceType.OPENING.getValue()){
			String sizeId=SystemContext.getDynamicPropertyHandler().get(ProxyConstants.YOUDXIAO_OPENING_ID);
			param.setSizeid(StringUtils.isEmpty(sizeId)?"202":sizeId);
		}else if(poseType == AdSpaceType.INFOFLOW.getValue()){
			String sizeId=SystemContext.getDynamicPropertyHandler().get(ProxyConstants.YOUDXIAO_INFOFLOW_ID);
			param.setSizeid(StringUtils.isEmpty(sizeId)?"501":sizeId);
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
	private AdRecomReply parseResult(YouxiaoAdPullResponse resp)
			throws AdPullException {
		if(resp == null){
			return null;
		}
		if(resp.getStatus_code()!=1){
			throw new AdPullException("ad request error, return error code:"+resp.getStatus_code()+",error massage:"+resp.getMsg());
			
		}
		AdRecomReply reply=new AdRecomReply();
		reply.setStatus(status);
		//***********************BEGIN 常规设置*************
		AdContent content = new AdContent();
		AdMutiAction action = new AdMutiAction();
		String title = defTitle;
		String marketTitle=defTitle;
		// 内容类型1表示链接推广,2表示应用推广,3未使用 4表示电话推广
		YouxiaoAd ad=resp.getMsg();
        int acType=ACTION_WEB;
        //图片
        List<AdImg> imgs = new ArrayList<AdImg>();
        
        if(ad.getUseraction()==2){
        	acType=ACTION_APP;
        }
        if(StringUtils.isNotEmpty(ad.getImg())){
            AdImg img=new AdImg();
            img.setSrc(ad.getImg());
            imgs.add(img);
        }

        if(StringUtils.isNotEmpty(ad.getTitle())){
        	title=ad.getTitle();
        }
        String link=ad.getClk_url();
        if(StringUtils.isNotEmpty(link)){
			content.setLinkurl(link);
			action.setLinkurl(link);
        }

		

		//上报
        Map<String, List<String>> map = new HashMap<String, List<String>>();
		//***********************END 常规设置***************
      
     
        if(CollectionUtils.isNotEmpty(ad.getExp_track())){
        	map.put(SHOW, ad.getExp_track());
        }
        List<String> clickL=new ArrayList<String>();
        List<String> downL=new ArrayList<String>();
        if(CollectionUtils.isNotEmpty(ad.getClk_track())){
        	clickL.addAll(ad.getClk_track());
        }
        YouxiaoTrack inv=ad.getInv_track();
        if(inv!=null){
        	
        	
        	if(CollectionUtils.isNotEmpty(inv.getStarturl())){
        		clickL.addAll(inv.getStarturl());
        		
        	}
        	if(CollectionUtils.isNotEmpty(inv.getDoneurl())){
        		downL.addAll(inv.getDoneurl());
        		
        	}
        	if(CollectionUtils.isNotEmpty(inv.getInstallurl())){
        		downL.addAll(inv.getInstallurl());
        		
        	}
        	if(CollectionUtils.isNotEmpty(inv.getInstalldoneurl())){
        		map.put(INSTALL,inv.getInstalldoneurl());
        		
        	}



        }
        
        map.put(CLICK, clickL);
        map.put(DOWNLOAD, clickL);

        
        
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
	
	@Override
	public AsynAbstractTask packageTask(Parameter params, String hashCode) {
		// TODO Auto-generated method stub
		AdProxyYouxiaoTask task=new AdProxyYouxiaoTask();
		
		YouxiaoAdPullParams param = (YouxiaoAdPullParams) params;
		StringBuilder url = new StringBuilder();
		url.append(SystemContext.getDynamicPropertyHandler().get(ProxyConstants.YOUXIAO_URL));
	//	url.append("&requestId=").append(hashCode);

		
		Map<String, String> headers = new HashMap<String, String>(2);
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json;charset=utf-8");
		task.setHeaders(headers);
		//logger.info("joinDSP:flying {} request param:{}",hashCode,url.toString());
		task.setParam(param);
		task.setDataFormat(DataFormat.DATA_FORMAT_JSON);
		task.setInvokeType(InvokeType.INVOKE_TYPE_POST);
		task.setHashCode(hashCode);
		task.setUrl(url.toString());
		
		return task;
	}

}
