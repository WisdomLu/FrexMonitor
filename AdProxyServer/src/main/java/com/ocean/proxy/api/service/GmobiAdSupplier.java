package com.ocean.proxy.api.service;

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
import com.ocean.core.common.threadpool.workthread.AsynInvokeResponse;
import com.ocean.core.common.threadpool.workthread.Task.DataFormat;
import com.ocean.core.common.threadpool.workthread.Task.InvokeType;
import com.ocean.persist.api.proxy.AdPullException;
import com.ocean.persist.api.proxy.AdPullResponse;
import com.ocean.persist.api.proxy.diankai.DiankaiAd;
import com.ocean.persist.api.proxy.diankai.DiankaiAdPullParams;
import com.ocean.persist.api.proxy.diankai.DiankaiAdPullResponse;
import com.ocean.persist.api.proxy.gmobi.GmobiAd;
import com.ocean.persist.api.proxy.gmobi.GmobiAdPullParams;
import com.ocean.persist.api.proxy.gmobi.GmobiAdPullResponse;
import com.ocean.persist.common.ProxyConstants;
import com.ocean.persist.model.proxy.DSPPosition;
import com.ocean.proxy.api.base.AbstractAdSupplier;
import com.ocean.proxy.api.base.AbstractAsynAdSupplier;
import com.ocean.proxy.serverdis.AdProxyDiankaiTask;
import com.ocean.proxy.serverdis.AdProxyGmobiTask;
import com.ocean.proxy.thrift.entity.AdContent;
import com.ocean.proxy.thrift.entity.AdImg;
import com.ocean.proxy.thrift.entity.AdMutiAction;
import com.ocean.proxy.thrift.entity.AdRecomReply;
import com.ocean.proxy.thrift.entity.AdRecomReq;
import com.ocean.proxy.thrift.entity.AdSpaceType;
import com.ocean.proxy.thrift.entity.AdUserInfo;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年8月16日 
      @version 1.0 
 */
@Component(value="gmobiAdSupplier")
public class GmobiAdSupplier extends AbstractAsynAdSupplier{
	private static Map<Integer, String> pt = new HashMap<Integer, String>(4);
	static{
		pt.put(SPACE_TYPE_BANNER, "b");
		pt.put(SPACE_TYPE_INTERSTITIAL, "i");
		//pt.put(SPACE_TYPE_OPENING, );
		pt.put(SPACE_TYPE_INFOFLOW, "n");
	}
	public AdRecomReply invoke(InvokeAttribute attribute)
			throws AdPullException {
		// TODO Auto-generated method stub
		AdRecomReq adreq = attribute.getAdreq();
		DSPPosition positionInfo = attribute.getDspPosition();
		// 参数转换
		GmobiAdPullParams params = parseParams(adreq, positionInfo);
		// 调用API
		GmobiAdPullResponse response = (GmobiAdPullResponse) invoke(params,GmobiAdPullResponse.class,adreq.getHash(),"joinDSP:gmobi");
		
		// 解析结果
		return parseResult(response);
	}
/*	public  AdPullResponse invoke(Parameter params,String ... exts){
		AsynInvokeResponse data=asynRequest(params, exts[0]);

		if(data!=null){
			GmobiAdPullResponse response =(GmobiAdPullResponse)data.getData();
			
			logger.info("joinDSP:gmobi {} ad return result:{}",exts,response);
			return response;
		}
		return null;
	}*/
	private GmobiAdPullParams parseParams(AdRecomReq adreq, 
			DSPPosition positionInfo) throws AdPullException {
		GmobiAdPullParams param=new GmobiAdPullParams();
		AdUserInfo user=adreq.getUserinfo();
		if(StringUtils.isEmpty(positionInfo.getText1())||StringUtils.isEmpty(positionInfo.getText2())){
		    throw new 	AdPullException("app id or package key is empty!");
		}
		param.setApp(positionInfo.getText1());
		param.setKey(positionInfo.getText2());
		param.setPlacement(positionInfo.getPos());
		String type=pt.get(positionInfo.getPosType());
		if(StringUtils.isEmpty(type)){
			throw new 	AdPullException("no space type maching!");
		}
		param.setType(type);
		param.setAdid(user.getAaid());
		param.setUa(user.getUa());
		
		
		String osCode = user.getOs();
		// 如果是ios
	    String os = "android";
	    if(OS_IOS.equals(osCode)){

	    	 os = "ios";

	    }else if(OS_ANDROID.equals(osCode)){
		    if(StringUtils.isNotEmpty(user.getImei())){
		    	param.setImei(user.getImei());
		    }
		    if(StringUtils.isNotEmpty(user.getAdid())){
		    	param.setSn(user.getAdid());
		    }
	    }
	    param.setOs(os);
	    param.setOs_v(this.convOSVersion(user.getOsversion()));
	    if(StringUtils.isNotEmpty(adreq.getNet())){
	    	param.setNet(adreq.getNet().toUpperCase());
	    }
	    param.setIp(user.getClient_ip());
	    param.setCount(adreq.getResult_num());
	    

	    param.setIw(adreq.getUserAdSpaceAttri().getSpaceWidth());
	    param.setIh(adreq.getUserAdSpaceAttri().getSpaceHeight());
	    param.setSw(user.getDevice_width());
	    param.setSh(user.getDevice_height());
		return param;
	}


	private AdRecomReply parseResult(GmobiAdPullResponse response)
			throws AdPullException {
		
		if(response == null||CollectionUtils.isEmpty(response.getAds())){
			return null;
		}

		AdRecomReply reply=new AdRecomReply();
		reply.setStatus(status);
		reply.setAd_contents(this.dealAds(response));
        return reply;
	}
	private List<AdContent>  dealAds(GmobiAdPullResponse resp){
		List<AdContent> list=new ArrayList<AdContent> ();
		for(GmobiAd ad:resp.getAds()){
			//***********************BEGIN 常规设置*************
			AdContent content = new AdContent();
			AdMutiAction action = new AdMutiAction();
			String title = defTitle;
			String marketTitle=defTitle;
			// 内容类型1表示链接推广,2表示应用推广,3未使用 4表示电话推广
		 
	        int acType=ACTION_WEB;
			//***********************END 常规设置***************
	      
	        List<AdImg> imgs = new ArrayList<AdImg>();
	        if(ad.getImage()!=null){
	            
	            if(StringUtils.isNotEmpty(ad.getImage().getUrl())){
	    			AdImg img = new AdImg();
	    			img.setSrc(ad.getImage().getUrl());
	    			img.setHeight(ad.getImage().getH());
	    			img.setWidth(ad.getImage().getW());
	    			imgs.add(img);

	            }
	          
	        }
	        
	        if(StringUtils.isNotEmpty(ad.getTitle())){
	        	title=ad.getTitle();
	        }
	        Map<String, List<String>> map = new HashMap<String, List<String>>();
	        if(ad.getTrackers()!=null&&CollectionUtils.isNotEmpty(ad.getTrackers().getImpression())){
	        	map.put(SHOW,ad.getTrackers().getImpression());
	        	
	        }
	        if(ad.getTrackers()!=null&&CollectionUtils.isNotEmpty(ad.getTrackers().getClick())){
	        	map.put(CLICK, ad.getTrackers().getClick());
	        }
	        if(ad.getTrackers()!=null&&CollectionUtils.isNotEmpty(ad.getTrackers().getInstall())){
	        	map.put(INSTALL, ad.getTrackers().getInstall());
	        	 acType=ACTION_APP;
	        }
	        
	        if(StringUtils.isNotEmpty(ad.getUrl())){
	        	content.setLinkurl(ad.getUrl());
	        	action.setLinkurl(ad.getUrl());
	        }
	        if("install".equals(ad.getCta())){
	        	 acType=ACTION_APP;
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
		AdProxyGmobiTask task=new AdProxyGmobiTask();

		StringBuilder url = new StringBuilder();
		url.append(SystemContext.getDynamicPropertyHandler().get(ProxyConstants.GMOBI_URL));
		url.append("?").append(Bean2Utils.toHttpParams(params));
		//logger.info("joinDSP:gmobi {} request param:{}",hashCode,url.toString());
		
		GmobiAdPullParams param = (GmobiAdPullParams) params;
		//task.setHearders(headers);
		task.setParam(param);
		task.setDataFormat(DataFormat.DATA_FORMAT_KVP);
		task.setInvokeType(InvokeType.INVOKE_TYPE_GET);
		task.setHashCode(hashCode);
		task.setUrl(url.toString());
		return  task;
	}

}
