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
import com.ocean.persist.api.proxy.diankai.DiankaiAd;
import com.ocean.persist.api.proxy.diankai.DiankaiAdPullResponse;
import com.ocean.persist.api.proxy.yitong.AbstractYitongResponse;
import com.ocean.persist.api.proxy.yitong.YitongAd;
import com.ocean.persist.api.proxy.yitong.YitongAdPullEmptyResponse;
import com.ocean.persist.api.proxy.yitong.YitongAdPullParams;
import com.ocean.persist.api.proxy.yitong.YitongAdPullResponse;
import com.ocean.persist.api.proxy.yitong.YitongDict;
import com.ocean.persist.api.proxy.yitong.YitongResource;
import com.ocean.persist.common.ProxyConstants;
import com.ocean.persist.model.proxy.DSPPosition;
import com.ocean.proxy.api.base.AbstractAsynAdSupplier;
import com.ocean.proxy.serverdis.AdProxyYitongTask;
import com.ocean.proxy.thrift.entity.AdContent;
import com.ocean.proxy.thrift.entity.AdImg;
import com.ocean.proxy.thrift.entity.AdMutiAction;
import com.ocean.proxy.thrift.entity.AdRecomReply;
import com.ocean.proxy.thrift.entity.AdRecomReq;
import com.ocean.proxy.thrift.entity.AdUserInfo;
import com.ocean.proxy.thrift.entity.UserAdSpaceAttri;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年12月26日 
      @version 1.0 
 */
@Component(value="YitongAdSupplier")
public class YitongAdSupplier   extends AbstractAsynAdSupplier{

	public AdRecomReply invoke(InvokeAttribute attribute)
			throws AdPullException {
		// TODO Auto-generated method stub
		AdRecomReq adreq = attribute.getAdreq();
		DSPPosition positionInfo = attribute.getDspPosition();
		// 参数转换
		YitongAdPullParams params = parseParams(adreq, positionInfo);
		// 调用API
		AbstractYitongResponse response = (AbstractYitongResponse) invoke(params,AbstractYitongResponse.class,adreq.getHash(),String.valueOf(adreq.getUserAdSpaceAttri().getAdSpaceId()),"joinDSP:adwo");
		
		// 解析结果
		return parseResult(params,response,adreq.getUserinfo(),adreq.getHash());
	}
	private YitongAdPullParams parseParams(AdRecomReq adreq, 
			DSPPosition positionInfo) throws AdPullException {
		if(StringUtils.isEmpty(positionInfo.getText1())||StringUtils.isEmpty(positionInfo.getText2())){
			throw  new AdPullException("appid/appkey is empty!");
		}
		YitongAdPullParams params =new YitongAdPullParams();
		
		params.setSupplyid("6");
		params.setItemspaceid(positionInfo.getPos());
		params.setDeveloperid(positionInfo.getText2());
		params.setAppid(positionInfo.getText1());
		params.setApt("1");

		

		AdUserInfo userInfo=adreq.getUserinfo();
		UserAdSpaceAttri attri=adreq.getUserAdSpaceAttri();
		String osV=this.convOSVersion(userInfo.getOsversion());
		if(OS_ANDROID.equals(userInfo.getOs())){

			params.setSv("Android"+osV);
			params.setCid(DigestUtils.md5Hex(userInfo.getImei()));
			params.setAdsid(userInfo.getImei());
			params.setImei(userInfo.getImei());
			params.setImsi(userInfo.getImsi());
			params.setAndroidID(userInfo.getAdid());
			params.setMac(userInfo.getMac());
			
		}else{
			params.setSv("iOS"+osV);
			params.setCid(DigestUtils.md5Hex(userInfo.getIdfa()));
			params.setAdsid(userInfo.getIdfa());
			params.setIdfa(userInfo.getIdfa());
		}
		try {
			params.setPn(URLEncoder.encode(userInfo.getPhonemodel(), CHARSET_CODER));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(StringUtils.isNotEmpty(adreq.getNet())){
			params.setNets(adreq.getNet().toLowerCase());
		}
		params.setAdps(String.valueOf(attri.getSpaceWidth()*10000+attri.getSpaceHeight()));
		params.setScs(String.valueOf(userInfo.getDevice_width()*100000+userInfo.getDevice_height()));
		params.setAppv(adreq.getVersion());
		params.setAdsid(userInfo.getImei());
		params.setDebugip(userInfo.getClient_ip());

		
		return params;
	}	

	private AdRecomReply parseResult(YitongAdPullParams params,AbstractYitongResponse resp,AdUserInfo userInfo,String hashCode)

			throws AdPullException {
		
		if(resp instanceof YitongAdPullEmptyResponse){
			YitongAdPullEmptyResponse emptyResp=(YitongAdPullEmptyResponse)resp;
			throw new AdPullException("ad request error,joinDSP:yitong return error code:"+emptyResp.getError()+" impressionid:"+emptyResp.getImpressionid());

		}
		
		if(resp == null){
			return null;
		}
		YitongAdPullResponse filledResp=(YitongAdPullResponse)resp;
		if(CollectionUtils.isEmpty(filledResp.getAds())){
			return null;
		}
		

		AdRecomReply reply=new AdRecomReply();
		reply.setStatus(status);
		reply.setAd_contents(this.dealAds(filledResp, params, userInfo, hashCode));
		return reply;
	}
	private List<AdContent>  dealAds(YitongAdPullResponse filledResp,YitongAdPullParams params,AdUserInfo userInfo,String hashCode) throws AdPullException{
		List<AdContent> list=new ArrayList<AdContent> ();
		for(YitongAd ad:filledResp.getAds()){
			//***********************BEGIN 常规设置*************
			AdContent content = new AdContent();
			AdMutiAction action = new AdMutiAction();
			String title = defTitle;
			String marketTitle=defTitle;
			// 内容类型1表示链接推广,2表示应用推广,3未使用 4表示电话推广
		 
	        int acType=ACTION_WEB;
	        List<AdImg> imgs = new ArrayList<AdImg>();
	        
	        YitongDict dict=ad.getSpecial().getDict();
	        YitongResource resource=null;
	        if("resource".equals(dict.getPicture())){
	            resource=ad.getResource();
	        }else{
	        	 resource=ad.getResource1();
	        }
	        
	       
	        if(StringUtils.isNotEmpty(resource.getFile())){
	            AdImg img=new AdImg();
	            img.setSrc(resource.getFile());
	            imgs.add(img);
	        }
	        content.setLinkurl(resource.getClick());
			action.setLinkurl(resource.getClick());
			if(StringUtils.isNotEmpty(resource.getText())){
				title=resource.getText();
			}
	        


			//上报
	        Map<String, List<String>> map = new HashMap<String, List<String>>();
	        List<String> viewL=new ArrayList<String>();
	        List<String> clickL=new ArrayList<String>();
	        viewL.add(this.getReportUrl(params, ad, userInfo, hashCode,1));
	        viewL.add(this.getReportUrl(params, ad, userInfo, hashCode,2));
	        if(resource!=null&&CollectionUtils.isNotEmpty(resource.getImp())){
	        	viewL.addAll(resource.getImp());
	        }
	        
	        map.put(SHOW, viewL);
	        
	        clickL.add(this.getReportUrl(params, ad, userInfo, hashCode, 3));
	        if(resource!=null&&CollectionUtils.isNotEmpty(resource.getClick_imp())){
	        	clickL.addAll(resource.getClick_imp());
	        }
	        map.put(CLICK, clickL);
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
	private String getReportUrl(YitongAdPullParams params,YitongAd ad,AdUserInfo userInfo,String hashCode,int type) throws AdPullException{
		String url="";
		switch(type){
		      case 1:
		    	  url=SystemContext.getDynamicPropertyHandler().get(ProxyConstants.YITONG_LOADING_URL);
		    	  break;
		      case 2:
		    	  url=SystemContext.getDynamicPropertyHandler().get(ProxyConstants.YITONG_VIEW_URL);
		    	  break;
		      case 3:
		    	  url=SystemContext.getDynamicPropertyHandler().get(ProxyConstants.YITONG_CLICK_URL);
		    	  break;
		      default:
		    	  throw new AdPullException("get report url error,type="+type);
			
		}
		StringBuilder sb = new StringBuilder();
		sb.append(url);
		sb.append("?").append(Bean2Utils.toHttpParams(params))
		.append("&impid=").append(ad.getImpressionid())
		.append("&apid=").append(ad.getItemspaceid())
		.append("&mkey=").append(ad.getMonitorkey())
		.append("&viewmonitor=").append(ad.getViewmonitor())
		.append("&clickmonitor=").append(ad.getClickmonitor())
		.append("&sys=").append(params.getSv())
		.append("&scs=").append(userInfo.getDevice_width()*100000+userInfo.getDevice_height())
		.append("&requestId=").append(hashCode);
		
		
		return sb.toString();
	}
	@Override
	public AsynAbstractTask packageTask(Parameter params, String hashCode) {
		// TODO Auto-generated method stub
		AdProxyYitongTask task=new AdProxyYitongTask();
		
		YitongAdPullParams param = (YitongAdPullParams) params;
		StringBuilder url = new StringBuilder();
		url.append(SystemContext.getDynamicPropertyHandler().get(ProxyConstants.YITONG_URL));
		url.append("?").append(Bean2Utils.toHttpParams(param)).append("&requestId=").append(hashCode);


		
		//logger.info("joinDSP:yitong {} request param:{}",hashCode,url.toString());
		task.setParam(param);
		task.setDataFormat(DataFormat.DATA_FORMAT_KVP);
		task.setInvokeType(InvokeType.INVOKE_TYPE_GET);
		task.setHashCode(hashCode);
		task.setUrl(url.toString());
		
		return task;
	}

}
