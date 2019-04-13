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
import org.springframework.beans.factory.annotation.Autowired;
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
import com.ocean.persist.api.proxy.diankai.DiankaiAdPullParams;
import com.ocean.persist.api.proxy.diankai.DiankaiAdPullResponse;
import com.ocean.persist.api.proxy.diankai.DiankaiAdPuller;
import com.ocean.persist.api.proxy.diankai.DiankaiDownloadReport;
import com.ocean.persist.common.ProxyConstants;
import com.ocean.persist.model.proxy.DSPPosition;
import com.ocean.proxy.api.base.AbstractAsynAdSupplier;
import com.ocean.proxy.serverdis.AdProxyDiankaiTask;
import com.ocean.proxy.thrift.entity.AdContent;
import com.ocean.proxy.thrift.entity.AdImg;
import com.ocean.proxy.thrift.entity.AdMutiAction;
import com.ocean.proxy.thrift.entity.AdRecomReply;
import com.ocean.proxy.thrift.entity.AdRecomReq;
import com.ocean.proxy.thrift.entity.AdUserInfo;


/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年6月24日 
      @version 1.0 
 */
@Component(value="diankaiAdSupplier")
public class DiankaiAdSupplier  extends AbstractAsynAdSupplier {
    @Autowired
    DiankaiAdPuller diankaiAdPuller;
	private static final Map<Integer, Integer> pt = new HashMap<Integer, Integer>();
	static{
		pt.put(1, 1);
		pt.put(3, 2);
		pt.put(2, 6);
		pt.put(4, 7);
	}
	public AdRecomReply invoke(InvokeAttribute attribute)
			throws AdPullException {
		// TODO Auto-generated method stub
		AdRecomReq adreq = attribute.getAdreq();
		DSPPosition positionInfo = attribute.getDspPosition();
		// 参数转换
		DiankaiAdPullParams params = parseParams(adreq, positionInfo);
		// 调用API
		//DiankaiAdPullResponse response = (DiankaiAdPullResponse)diankaiAdPuller.api(params,adreq.getHash(),String.valueOf(adreq.getUserAdSpaceAttri().getAdSpaceId()));
		DiankaiAdPullResponse response = (DiankaiAdPullResponse) invoke(params,DiankaiAdPullResponse.class,adreq.getHash(),String.valueOf(adreq.getUserAdSpaceAttri().getAdSpaceId()),"joinDSP:diankai");
		
		// 解析结果
		return parseResult(response);
	}
	private AdRecomReply parseResult(DiankaiAdPullResponse response)
			throws AdPullException {
		
		if(response == null||CollectionUtils.isEmpty(response.getAds())){
			return null;
		}
		if(response.getCode()!=0){
			throw new AdPullException("ad request error,error code:"+response.getCode()+"  error msg:"+response.getMessage());
		}
		AdRecomReply reply=new AdRecomReply();
		reply.setStatus(status);
		reply.setAd_contents(this.dealAds(response));
		return reply;
	}
	private List<AdContent>  dealAds(DiankaiAdPullResponse resp){
		List<AdContent> list=new ArrayList<AdContent> ();
		for(DiankaiAd ad:resp.getAds()){
			//***********************BEGIN 常规设置*************
			AdContent content = new AdContent();
			AdMutiAction action = new AdMutiAction();
			String title = defTitle;
			String marketTitle=defTitle;
			// 内容类型1表示链接推广,2表示应用推广,3未使用 4表示电话推广
		 
	        int acType=ACTION_WEB;
			//***********************END 常规设置***************
	    
	        List<AdImg> imgs = new ArrayList<AdImg>();
	        if(StringUtils.isNotEmpty(ad.getPic())){
				AdImg img = new AdImg();
				img.setSrc(ad.getPic());
				imgs.add(img);

	        }
	        String link=ad.getClick_url();
	        if(StringUtils.isNotEmpty(link)&&ad.getS()==1){
	        	content.setLinkurl(format(ad.getClick_url()));
	        	action.setLinkurl(format(ad.getClick_url()));
	        }else{
	        	content.setLinkurl(ad.getClick_url());
	        	action.setLinkurl(ad.getClick_url());
	        }
	        if(ad.getS()==1){
	        	action.setLinkurl_type(1);
	        }
			//上报
	        Map<String, List<String>> map = new HashMap<String, List<String>>();
	        
	        List<String> clickL=new ArrayList<String>();
	        if(CollectionUtils.isNotEmpty(ad.getView_report())){
	        	map.put(SHOW,ad.getView_report());
	        }
	        if(CollectionUtils.isNotEmpty(ad.getClick_report())){
	        	clickL.addAll( ad.getClick_report());
	        	//map.put(CLICK, ad.getClick_report());
	        }
	    
	        if(CollectionUtils.isNotEmpty(ad.getDownload_report())){
	            List<String> downloadL=new ArrayList<String>();
	            List<String> installL=new ArrayList<String>();
	        	for(DiankaiDownloadReport dr:ad.getDownload_report()){
	        		if(StringUtils.isNotEmpty(dr.getDownload_start())){
	        			if(ad.getS()==1){
	        				
	        				//downloadL.add(format(dr.getDownload_start()));
	        				clickL.add(format(dr.getDownload_start()));
	        			}else{
	        				
	        				//downloadL.add(dr.getDownload_start());
	        				clickL.add(dr.getDownload_start());
	        			}
	        			
	        		}else if(StringUtils.isNotEmpty(dr.getDownload_complete())){
	        			if(ad.getS()==1){
	        				
	        				downloadL.add(format(dr.getDownload_complete()));
	        			}else{
	        				
	        				downloadL.add(dr.getDownload_complete());
	        			}
	        			
	        		}else if(StringUtils.isNotEmpty(dr.getInstall())){
	        			if(ad.getS()==1){
	        				installL.add(format(dr.getInstall()));
	        			}else{	
	        				installL.add(dr.getInstall());
	        			}
	        			
	        		}
	        	}
	        	map.put(DOWNLOAD, downloadL);
	        	map.put(INSTALL, installL);
	        	acType=ACTION_APP;
	        }
	        map.put(CLICK, clickL);
	        
	        if(CollectionUtils.isNotEmpty(ad.getDescription())){
	        	marketTitle=ad.getDescription().get(0);
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
	private static String format(String str){
		String format= str.replaceAll("\\[down_x\\]","%%DOWNX%%").replaceAll("\\[down_y\\]","%%DOWNY%%").replaceAll("\\[up_x\\]","%%UPX%%")
    			.replaceAll("\\[up_y\\]", "%%UPY%%")
				.replaceAll("\\[clickid\\]", "%%CLICKID%%");

		return format;
	}
	private DiankaiAdPullParams parseParams(AdRecomReq adreq, 
			DSPPosition positionInfo) throws AdPullException {
		DiankaiAdPullParams param=new DiankaiAdPullParams();
		
		if(StringUtils.isEmpty(positionInfo.getText1())||StringUtils.isEmpty(positionInfo.getText2())){
		    throw new 	AdPullException("app id or package name is empty!");
		}
		AdUserInfo userInfo=adreq.getUserinfo();
		param.setPid(positionInfo.getText1());
		param.setAd_space(positionInfo.getPos());
		Integer adType=pt.get((int)positionInfo.getPosType());
		param.setAd_type(adType==null?1:adType);
		param.setPackage_name(positionInfo.getText2());
		param.setAd_category("cpc");
		param.setSim(userInfo.getImei());
		//device
		try {
			param.setDevice_name(URLEncoder.encode(userInfo.getPhonemodel(),"UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		param.setDevice_brand(userInfo.getBrand_name());
		
		String osCode = userInfo.getOs();
		// 如果是ios
	    String os = "android";
	    if(OS_IOS.equals(osCode)){
			// ios 必填信息
	    	if(StringUtils.isNotEmpty(userInfo.getIdfa())){
	    		param.setSn(userInfo.getIdfa());
	    	}
	    
	    	 os = "IOS";

	    }else if(OS_ANDROID.equals(osCode)){
	    	param.setSn(userInfo.getImei());
	    	param.setAndroid_id(userInfo.getAdid());
	    	param.setMac(this.converMac(userInfo.getMac()));
	    }		
	    param.setOs(os);
	    param.setOs_version(this.convOSVersion(userInfo.getOsversion()));
	    param.setScreen_height(userInfo.getDevice_height());
	    param.setScreen_width(userInfo.getDevice_width());
	    try {
			param.setUa(URLEncoder.encode(userInfo.getUa(),"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    //net
	   String carr= mobiles.get(userInfo.getMobile());
	   param.setCarrier(StringUtils.isEmpty(carr)?"46000":carr);
		//gps
	   if(StringUtils.isNotEmpty(userInfo.getLat())&&StringUtils.isNotEmpty(userInfo.getLon())){
			param.setLat(userInfo.getLat());
			param.setLng(userInfo.getLon());
	   }
	   param.setIp(userInfo.getClient_ip());
	   param.setClient(0);
	   
	   param.setApi_ver("2.2.0");
	   String token=DigestUtils.md5Hex(positionInfo.getPos()+positionInfo.getText1()+positionInfo.getText2());
	   param.setToken(token);
		return param;
	}
	public DiankaiAdPuller getDiankaiAdPuller() {
		return diankaiAdPuller;
	}
	public void setDiankaiAdPuller(DiankaiAdPuller diankaiAdPuller) {
		this.diankaiAdPuller = diankaiAdPuller;
	}
	@Override
	public AsynAbstractTask packageTask(Parameter params,
			String hashCode) {
		// TODO Auto-generated method stub
		    AdProxyDiankaiTask task=new AdProxyDiankaiTask();
			Map<String, String> headers = new HashMap<String, String>(2);
			headers.put("Content-Type", "application/json");
			
			StringBuilder url = new StringBuilder();
			url.append(SystemContext.getDynamicPropertyHandler().get(ProxyConstants.DIANKAI_URL));
			url.append("?").append(Bean2Utils.toHttpParams(params));
			//logger.info("joinDSP:diankai {} request param:{}",hashCode,url.toString());
			
			DiankaiAdPullParams param = (DiankaiAdPullParams) params;
			task.setHeaders(headers);
			task.setParam(param);
			task.setDataFormat(DataFormat.DATA_FORMAT_JSON);
			task.setInvokeType(InvokeType.INVOKE_TYPE_GET);
			task.setHashCode(hashCode);
			task.setUrl(url.toString());
			return  task;
	}
}
