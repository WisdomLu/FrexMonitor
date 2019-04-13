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
import com.ocean.persist.api.proxy.yingna.EncryptSample;
import com.ocean.persist.api.proxy.yingna.YingnaAd;
import com.ocean.persist.api.proxy.yingna.YingnaAdPullParams;
import com.ocean.persist.api.proxy.yingna.YingnaAdPullResponse;
import com.ocean.persist.common.ProxyConstants;
import com.ocean.persist.model.proxy.DSPPosition;
import com.ocean.proxy.api.base.AbstractAsynAdSupplier;
import com.ocean.proxy.serverdis.AdProxyYingnaTask;
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
@Component(value="YingnaAdSupplier")
public class YingnaAdSupplier    extends AbstractAsynAdSupplier {

	public AdRecomReply invoke(InvokeAttribute attribute)
			throws AdPullException {
		// TODO Auto-generated method stub
		AdRecomReq adreq = attribute.getAdreq();
		DSPPosition positionInfo = attribute.getDspPosition();
		// 参数转换
		YingnaAdPullParams params = parseParams(adreq, positionInfo);
		// 调用API
		YingnaAdPullResponse response = (YingnaAdPullResponse) invoke(params,YingnaAdPullResponse.class,adreq.getHash(),String.valueOf(adreq.getUserAdSpaceAttri().getAdSpaceId()),"joinDSP:adwo");
		
		// 解析结果
		return parseResult(response, positionInfo.getPosType());
	}
	private YingnaAdPullParams parseParams(AdRecomReq adreq, 
			DSPPosition positionInfo) throws AdPullException {
		if(StringUtils.isEmpty(positionInfo.getText3())){
			throw  new AdPullException("app pkg name is empty!");
		}
		AdUserInfo userInfo=adreq.getUserinfo();
		
		
		YingnaAdPullParams param =new YingnaAdPullParams();
		param.setCode_id(positionInfo.getPos());
		param.setOs_ver(this.convOSVersion(userInfo.getOsversion()));
		param.setApp_pkg(positionInfo.getText3());
		param.setApp_ver(adreq.getVersion());
		param.setDevice_type(1);
		String os = userInfo.getOs();
		if(OS_ANDROID.equals(os)){
			param.setOs_type(1);
			param.setImei(userInfo.getImei());
			param.setAndroid_id(userInfo.getAdid());
		}else{
			param.setOs_type(2);
			param.setIdfa(userInfo.getIdfa());

		}
		param.setMac(userInfo.getMac());
		param.setSw(userInfo.getDevice_width());
		param.setSh(userInfo.getDevice_height());
		param.setIp(userInfo.getClient_ip());
		param.setOt(this.getOp(userInfo.getMobile()));
		param.setCt(this.getNetty(adreq.getNet()));
		if(StringUtils.isNotEmpty(userInfo.getLat())&&StringUtils.isNotEmpty(userInfo.getLon())){
			param.setLat(Double.parseDouble(userInfo.getLat()));
			param.setLng(Double.parseDouble(userInfo.getLon()));
			
		}
		try {
			param.setVendor(URLEncoder.encode(userInfo.getBrand_name(),CHARSET_CODER));
			param.setModel(userInfo.getPhonemodel());
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		param.setUa(userInfo.getUa());
		return param;
	}
	private int  getNetty(String net){
		if(NETWORK_WIFI.equals(net)){
			return 100;
		}else if(NETWORK_2G.equals(net)){
			return 3;
		}else if(NETWORK_3G.equals(net)){
			return 4;
		}else if(NETWORK_4G.equals(net)){
			return 5;
		}
		return 1;
	}
	private int  getOp(String mobile){
		if(MOBILE_CMCC.equals(mobile)){
			return 1;
		}else if(MOBILE_CUCC.equals(mobile)){
			return 3;
		}else if(MOBILE_CTCC.equals(mobile)){
			return 2;
		}
		return 0;
	}
	private AdRecomReply parseResult(YingnaAdPullResponse resp,int poseType)
			throws AdPullException {
		if(resp == null){
			return null;
		}

		AdRecomReply reply=new AdRecomReply();
		reply.setStatus(status);
		//***********************BEGIN 常规设置*************
		AdContent content = new AdContent();
		AdMutiAction action = new AdMutiAction();
		String title = defTitle;
		String marketTitle=defTitle;
		// 内容类型1表示链接推广,2表示应用推广,3未使用 4表示电话推广
	 
        int acType=ACTION_WEB;
        YingnaAd ad=resp.getBanner();
        if(ad==null){
        	ad=resp.getPopup();
        }
        if(ad==null){
        	ad=resp.getFull();
        }
        if(ad==null&&resp.getFlowinfo()!=null&&CollectionUtils.isNotEmpty(resp.getFlowinfo().getAds())){
        	 ad=resp.getFlowinfo().getAds().get(0);
        }
		if(ad==null){
			return null;
		}
        if(ad.getInteraction()==2){
        	acType=ACTION_APP;
        }
        //图片
        List<AdImg> imgs = new ArrayList<AdImg>();
        if(StringUtils.isNotEmpty(ad.getImgSrc())){
            AdImg img=new AdImg();
            img.setSrc(ad.getImgSrc());
            imgs.add(img);
        }
        if(CollectionUtils.isNotEmpty(ad.getImgs())){
        	for(String url:ad.getImgs()){
                AdImg img=new AdImg();
                img.setSrc(url);
                imgs.add(img);
        	}

        }
        //落地页
		content.setLinkurl(ad.getClcUrl());
		action.setLinkurl(ad.getClcUrl());
		if(StringUtils.isNotEmpty(ad.getPkgname())){
			action.setCpPackage(ad.getPkgname());
		}
		
		if(StringUtils.isNotEmpty(ad.getTitle())){
			title = ad.getTitle();
		}
		//上报
        Map<String, List<String>> map = new HashMap<String, List<String>>();
		//***********************END 常规设置***************
        if(CollectionUtils.isNotEmpty(ad.getCallbackNoticeUrls())){
        	map.put(SHOW, ad.getCallbackNoticeUrls());
        }
        List<String> clickL=new ArrayList<String>();
        if(CollectionUtils.isNotEmpty(ad.getClickNoticeUrls())){
        	clickL.addAll(ad.getClickNoticeUrls());
        }
        if(CollectionUtils.isNotEmpty(ad.getDownstart())){
        	clickL.addAll(ad.getDownstart());

        }
        map.put(CLICK, clickL);
        List<String> downloadL=new ArrayList<String>();
        if(CollectionUtils.isNotEmpty(ad.getDownsucc())){
        	downloadL.addAll(ad.getDownsucc());

        }
        if(CollectionUtils.isNotEmpty(ad.getInststart())){
          	downloadL.addAll(ad.getInststart());
        }
        map.put(DOWNLOAD, downloadL);
        

        if(CollectionUtils.isNotEmpty(ad.getInstsucc())){
        	map.put(INSTALL, ad.getInstsucc());
        }
        if(CollectionUtils.isNotEmpty(ad.getActive())){
        	map.put(ACTIVE, ad.getActive());
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

	@Override
	public AsynAbstractTask packageTask(Parameter params, String hashCode) {
		// TODO Auto-generated method stub
		AdProxyYingnaTask task=new AdProxyYingnaTask();
		Map<String, String> headers = new HashMap<String, String>(2);
		
		YingnaAdPullParams param = (YingnaAdPullParams) params;
		headers.put("User-Agent",param.getUa());
		task.setHeaders(headers);
		
		StringBuilder url = new StringBuilder();
		url.append(SystemContext.getDynamicPropertyHandler().get(ProxyConstants.YINGNA_URL));
		url.append("?").append("p=").append(EncryptSample.encode(Bean2Utils.toHttpParams(param)));


		
		task.setParam(param);
		task.setDataFormat(DataFormat.DATA_FORMAT_KVP);
		task.setInvokeType(InvokeType.INVOKE_TYPE_GET);
		task.setHashCode(hashCode);
		task.setUrl(url.toString());
		
		return task;
	}

}
