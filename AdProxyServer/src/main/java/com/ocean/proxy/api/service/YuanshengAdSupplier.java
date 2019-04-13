package com.ocean.proxy.api.service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.inveno.util.CollectionUtils;
import com.ocean.core.common.JsonUtils;
import com.ocean.core.common.UniversalUtils;
import com.ocean.core.common.system.SystemContext;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
import com.ocean.core.common.threadpool.workthread.Task.DataFormat;
import com.ocean.core.common.threadpool.workthread.Task.InvokeType;
import com.ocean.persist.api.proxy.AdPullException;
import com.ocean.persist.api.proxy.diankai.DiankaiAd;
import com.ocean.persist.api.proxy.diankai.DiankaiAdPullResponse;
import com.ocean.persist.api.proxy.yuansheng2.YuanshengAdPullParams;
import com.ocean.persist.api.proxy.yuansheng2.YuanshengAdPullResponse;
import com.ocean.persist.api.proxy.yuansheng2.YuanshengImag;
import com.ocean.persist.api.proxy.yuansheng2.YuanshengImp;
import com.ocean.persist.api.proxy.yuansheng2.YuanshengWord;
import com.ocean.persist.common.ProxyConstants;
import com.ocean.persist.model.proxy.DSPPosition;
import com.ocean.proxy.api.base.AbstractAsynAdSupplier;
import com.ocean.proxy.serverdis.AdProxyYuanshenTask;
import com.ocean.proxy.thrift.entity.AdContent;
import com.ocean.proxy.thrift.entity.AdImg;
import com.ocean.proxy.thrift.entity.AdMutiAction;
import com.ocean.proxy.thrift.entity.AdRecomReply;
import com.ocean.proxy.thrift.entity.AdRecomReq;
import com.ocean.proxy.thrift.entity.AdUserInfo;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年10月27日 
      @version 1.0 
 */
@Component(value="YuanshengAdSupplier")
public class YuanshengAdSupplier   extends AbstractAsynAdSupplier {

	public AdRecomReply invoke(InvokeAttribute attribute)
			throws AdPullException {
		// TODO Auto-generated method stub
		AdRecomReq adreq = attribute.getAdreq();
		DSPPosition positionInfo = attribute.getDspPosition();
		// 参数转换
		YuanshengAdPullParams params = parseParams(adreq, positionInfo);
		// 调用API
		YuanshengAdPullResponse response = (YuanshengAdPullResponse) invoke(params,YuanshengAdPullResponse.class,adreq.getHash()
				,String.valueOf(adreq.getUserAdSpaceAttri().getAdSpaceId()),"joinDSP:yuansheng");
		
		// 解析结果
		return parseResult(response);
	}

	private YuanshengAdPullParams parseParams(AdRecomReq adreq, 
			DSPPosition positionInfo) throws AdPullException {
		if(StringUtils.isEmpty(positionInfo.getText1())||StringUtils.isEmpty(positionInfo.getText2())){
			throw  new AdPullException("appId  or appKey is empty!");
		}
		AdUserInfo userInfo=adreq.getUserinfo();
		YuanshengAdPullParams param =new YuanshengAdPullParams();
		param.setVersion("2.0");
		String time=String.valueOf(System.currentTimeMillis());
		param.setTime(time);
		String token=DigestUtils.md5Hex(positionInfo.getText1()+positionInfo.getText2()+time).toLowerCase();
		param.setToken(token);
		param.setReqid(UUID.randomUUID().toString().replaceAll("-", ""));
		
		
		param.setAppid(positionInfo.getText1());
		param.setAppver(adreq.getVersion());
		param.setAdspotid(positionInfo.getPos());
		param.setImpsize(adreq.getResult_num());
		param.setIp(userInfo.getClient_ip());
		param.setUa(userInfo.getUa());
	
		if(StringUtils.isNotEmpty(userInfo.getLat())&&StringUtils.isNotEmpty(userInfo.getLon())){
			param.setLat(Float.parseFloat(userInfo.getLat()));
			param.setLon(Float.parseFloat(userInfo.getLon()));
			
		}
		param.setSw(userInfo.getDevice_width());
		param.setSh(userInfo.getDevice_height());
		param.setMake(userInfo.getBrand_name());
		param.setModel(userInfo.getPhonemodel());
		String os = userInfo.getOs();
		if(OS_ANDROID.equals(os)){
			param.setOs(2);
			param.setImei(userInfo.getImei());
			param.setMac(userInfo.getMac());
			param.setAndroidid(userInfo.getAdid());
		}else if(OS_IOS.equals(os)){
			param.setOs(1);
			param.setIdfa(userInfo.getIdfa());
		}else{
			param.setOs(0);
		}
		param.setOsv(this.convOSVersion(userInfo.getOsversion()));
		param.setCarrier(this.getOp(userInfo.getMobile()));
		param.setNetwork(this.getNetwork(adreq.getNet()));
		param.setDevicetype(1);
	
		return param;
	}
	private  int getNetwork(String net){
		if(NETWORK_WIFI.equals(net)){
			return 1;
		}else if(NETWORK_2G.equals(net)){
			return 2;
		}
		else if(NETWORK_3G.equals(net)){
			return 3;
		}
		else if(NETWORK_4G.equals(net)){
			return 4;
		}
		return 0;
	}
	private String getOp(String op){

		if(MOBILE_CMCC.equals(op)){
			return "46000";
		}else if(MOBILE_CUCC.equals(op)){
			return "46001";
		}else if(MOBILE_CTCC.equals(op)){
			return "46003";
		}
		return "";
	}
	private AdRecomReply parseResult(YuanshengAdPullResponse body)
			throws AdPullException {
		if(body == null||CollectionUtils.isEmpty(body.getImp())){
			return null;
		}
		if(body.getCode()!=200){
			throw new AdPullException("ad request error,joinDSP:yuansheng return error code:"+body.getCode()+",error massage:"+body.getMsg());
			
		}
	
		AdRecomReply reply=new AdRecomReply();
		reply.setStatus(status);
		reply.setAd_contents(this.dealAds(body));
		return reply;
	}
	private List<AdContent>  dealAds(YuanshengAdPullResponse resp){
		List<AdContent> list=new ArrayList<AdContent> ();
		for(YuanshengImp ad:resp.getImp()){
			//***********************BEGIN 常规设置*************
			AdContent content = new AdContent();
			AdMutiAction action = new AdMutiAction();
			String title = defTitle;
			String marketTitle=defTitle;
			// 内容类型1表示链接推广,2表示应用推广,3未使用 4表示电话推广
		 
	        int acType=ACTION_WEB;
	        //图片
	        List<AdImg> imgs = new ArrayList<AdImg>();
			//上报
	        Map<String, List<String>> map = new HashMap<String, List<String>>();
			//***********************END 常规设置***************	
	        
	        if(CollectionUtils.isNotEmpty(ad.getImage())){
	        	for(YuanshengImag imgR:ad.getImage()){
	        		if(imgR.getType()>=300){
	        			AdImg img=new AdImg();
	        			img.setSrc(imgFormat(imgR.getIurl()));
	        			imgs.add(img);
	        		}

	        	}
	        	if(imgs.isEmpty()){
	            	for(YuanshengImag imgR:ad.getImage()){
	            		if(imgR.getType()>=200){
	            			AdImg img=new AdImg();
	            			img.setSrc(imgFormat(imgR.getIurl()));
	            			imgs.add(img);
	            		}

	            	}
	        	}


	        }
			if(CollectionUtils.isNotEmpty(ad.getWord())){
				for(YuanshengWord word:ad.getWord()){
					if(word.getType()==1){
						title=word.getText();
					}
				}
			}
			content.setLinkurl(ad.getLink());
			action.setLinkurl(ad.getLink());
			
			if(ad.getAction()==2){
				acType=ACTION_APP;
			}
			
			if(CollectionUtils.isNotEmpty(ad.getImptk())){
				 map.put(SHOW, ad.getImptk());
			 }
			 
			 if(CollectionUtils.isNotEmpty(ad.getClicktk())){
				 List<String> clickL=new ArrayList<String>();
				 for(String  report:ad.getClicktk()){
					 clickL.add(macroFormate(report));
				 }
				 map.put(CLICK, clickL);
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
	private String imgFormat(String img){
		if(StringUtils.isEmpty(img)){
			return "";
		}
		return img.replaceAll("https", "http");
	}

	private String macroFormate(String str){
		return str.replaceAll("\\$\\{clickpixels\\}", "%%DOWNX%%_%%DOWNY%%");
	}
	@Override
	public AsynAbstractTask packageTask(Parameter params, String hashCode) {
		// TODO Auto-generated method stub
		AdProxyYuanshenTask task=new AdProxyYuanshenTask();
		//heard
		Map<String, String> headers = new HashMap<String, String>(2);
		headers.put("Content-Type", "application/json");
		task.setHeaders(headers);
		YuanshengAdPullParams param = (YuanshengAdPullParams) params;

		
		//logger.info("joinDSP:yuansheng {} request param:{}",hashCode,UniversalUtils.replaceBlank(JsonUtils.toJson(param)));
		task.setParam(param);
		task.setDataFormat(DataFormat.DATA_FORMAT_JSON);
		task.setInvokeType(InvokeType.INVOKE_TYPE_POST);
		task.setHashCode(hashCode);
		task.setUrl(SystemContext.getDynamicPropertyHandler().get(ProxyConstants.YUANSHEN_URL));
		
		return task;
	}


}
