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
import com.ocean.core.common.threadpool.workthread.Task.DataFormat;
import com.ocean.core.common.threadpool.workthread.Task.InvokeType;
import com.ocean.persist.api.proxy.AdPullException;
import com.ocean.persist.api.proxy.paerjiate.PaerjiateAd;
import com.ocean.persist.api.proxy.paerjiate.PaerjiateAdPullParams;
import com.ocean.persist.api.proxy.paerjiate.PaerjiateAdPullResponse;
import com.ocean.persist.api.proxy.ruishi.RuishiAdPullParams;
import com.ocean.persist.api.proxy.youken.MD5Utils;
import com.ocean.persist.common.ProxyConstants;
import com.ocean.persist.model.proxy.DSPPosition;
import com.ocean.proxy.api.base.AbstractAsynAdSupplier;
import com.ocean.proxy.serverdis.AdProxyPaerjeiteTask;
import com.ocean.proxy.thrift.entity.AdContent;
import com.ocean.proxy.thrift.entity.AdImg;
import com.ocean.proxy.thrift.entity.AdMutiAction;
import com.ocean.proxy.thrift.entity.AdRecomReply;
import com.ocean.proxy.thrift.entity.AdRecomReq;
import com.ocean.proxy.thrift.entity.AdUserInfo;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年2月28日 
      @version 1.0 
 */
@Component(value="PaerjieteAdSupplier")
public class PaerjieteAdSupplier    extends AbstractAsynAdSupplier {
	private static int start_index;//获取广告的开始索引
	private static List<PaerjiateAd> ads;
	private static long update_time;//广告更新的时间
	private final Object lock=new Object();
	static{
		ads=new ArrayList<PaerjiateAd>();
		start_index=0;
		update_time=System.currentTimeMillis();
	}
	//用户手指离开设备屏幕时的横坐标
	public AdRecomReply invoke(InvokeAttribute attribute)
				throws AdPullException {
			// 解析结果
			return getAds(attribute);
		}

	private PaerjiateAdPullParams parseParams(AdRecomReq adreq, 
			DSPPosition positionInfo) throws AdPullException {
		if(StringUtils.isEmpty(positionInfo.getText1())||StringUtils.isEmpty(positionInfo.getText2())){
			throw  new AdPullException("channel id or app key is empty!");
		}
		AdUserInfo userInfo=adreq.getUserinfo();
		PaerjiateAdPullParams param =new PaerjiateAdPullParams();
		param.setToken(positionInfo.getText2());
		param.setSid(positionInfo.getText1());
		param.setGaid(userInfo.getImei());
		param.setA_id(userInfo.getAdid());
		int count=SystemContext.getDynamicPropertyHandler().getInt(ProxyConstants.PAERJIATE_MAX_COUNT,100);
		param.setMaxpush(count);
		return param;
	}	
	private AdRecomReply getAds(InvokeAttribute attribute) throws AdPullException{
		long t=System.currentTimeMillis()-update_time;
		long intval=SystemContext.getDynamicPropertyHandler().getLong(ProxyConstants.PAERJIATE_CACHE_INTERVAL,3600000);
		int count=attribute.getAdreq().getResult_num();
		AdRecomReply reply=new AdRecomReply();
		reply.setStatus(status);
		if(CollectionUtils.isEmpty(ads)||t>intval){//空或过期了,重新请求
			synchronized(lock){
				if(CollectionUtils.isEmpty(ads)||t>intval){
					this.requesAds(attribute,count);
				}
			}

			
		}
		if(CollectionUtils.isEmpty(ads)){
			return null;
		}
		List<PaerjiateAd> adList =this.getAdsFromCache(count);	
		reply.setAd_contents(dealAds(adList));
		return reply;
	}
	private List<PaerjiateAd> getAdsFromCache(int count){
		List<PaerjiateAd> adList =new ArrayList<PaerjiateAd>();
		if(start_index<0){
			start_index=0;	
		}
		for(;count>0&&start_index>=0;start_index++,count--){
			int index=start_index%ads.size();
			adList.add(ads.get(index));
		}
		return adList;
	}
	private void requesAds(InvokeAttribute attribute,int count)
			throws AdPullException {
		DSPPosition positionInfo = attribute.getDspPosition();
		AdRecomReq adreq=attribute.getAdreq();
		// 参数转换
		PaerjiateAdPullParams params = parseParams(attribute.getAdreq(), positionInfo);

		// 调用API
		PaerjiateAdPullResponse body = (PaerjiateAdPullResponse) invoke(params,PaerjiateAdPullResponse.class,adreq.getHash()
				,String.valueOf(adreq.getUserAdSpaceAttri().getAdSpaceId()));
		if(body == null||CollectionUtils.isEmpty(body.getAds())){
			return ;
		}
		if(200!=body.getState()){
			throw new AdPullException("ad request error, return error code:"+body.getState()+" msg:"+body.getMsg());
		}
		AdRecomReply reply=new AdRecomReply();
		reply.setStatus(status);
		
		//存入缓存
		ads=body.getAds();//放入缓存
		start_index=0;
		update_time=System.currentTimeMillis();
		

	}
	private List<AdContent>  dealAds(List<PaerjiateAd> adList){
		List<AdContent> list=new ArrayList<AdContent> ();
		for(PaerjiateAd ad:adList){
			//***********************BEGIN 常规设置*************
			AdContent content = new AdContent();
			AdMutiAction action = new AdMutiAction();
			String title = defTitle;
			String marketTitle=defTitle;
			// 内容类型1表示链接推广,2表示应用推广,3未使用 4表示电话推广
		 
	        int acType=ACTION_WEB;
	    	//***********************END 常规设置***************	
	        if("1".equals(ad.getData_type())){
	        	acType=ACTION_APP;
	        }
	        //图片
	        List<AdImg> imgs = new ArrayList<AdImg>();
	        if(StringUtils.isNotEmpty(ad.getImage())){
	            	AdImg img = new AdImg();
	    			img.setSrc(ad.getImage());
	    			imgs.add(img);
	        }
	        if(StringUtils.isNotEmpty(ad.getTitle())){
	        	title=ad.getTitle();
	        }
	        if(StringUtils.isNotEmpty(ad.getData_content())){
	        	content.setLinkurl(ad.getData_content());
	        	action.setLinkurl(ad.getData_content());
	        }
	        
	        if(StringUtils.isNotEmpty(ad.getIcon())){
	        	action.setCpIcon(ad.getIcon());
	        }
	        if(StringUtils.isNotEmpty(ad.getPname())){
	        	action.setCpPackage(ad.getPname());
	        }
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
		AdProxyPaerjeiteTask task=new AdProxyPaerjeiteTask();
		PaerjiateAdPullParams param = (PaerjiateAdPullParams) params;
		StringBuilder buff=new StringBuilder(SystemContext.getDynamicPropertyHandler().get(ProxyConstants.PAERJIATE_URL));
		buff.append("?").append(Bean2Utils.toHttpParams(param)).append("&zkrequestId=").append(hashCode);
		task.setParam(param);
		task.setDataFormat(DataFormat.DATA_FORMAT_KVP);
		task.setInvokeType(InvokeType.INVOKE_TYPE_GET);
		task.setHashCode(hashCode);
		task.setUrl(buff.toString());
		
		return task;
	}

}
