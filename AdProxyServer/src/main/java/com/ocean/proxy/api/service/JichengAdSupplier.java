package com.ocean.proxy.api.service;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.inveno.util.CollectionUtils;
import com.ocean.core.common.system.SystemContext;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
import com.ocean.core.common.threadpool.workthread.Task.DataFormat;
import com.ocean.core.common.threadpool.workthread.Task.InvokeType;
import com.ocean.persist.api.proxy.AdPullException;
import com.ocean.persist.api.proxy.jicheng.JichengAd;
import com.ocean.persist.api.proxy.jicheng.JichengAdPullParams;
import com.ocean.persist.api.proxy.jicheng.JichengAdPullResponse;
import com.ocean.persist.api.proxy.jicheng.JichengTrack;
import com.ocean.persist.common.ProxyConstants;
import com.ocean.persist.model.proxy.DSPPosition;
import com.ocean.proxy.api.base.AbstractAsynAdSupplier;
import com.ocean.proxy.serverdis.AdProxyJichengTask;
import com.ocean.proxy.thrift.entity.AdContent;
import com.ocean.proxy.thrift.entity.AdImg;
import com.ocean.proxy.thrift.entity.AdMutiAction;
import com.ocean.proxy.thrift.entity.AdRecomReply;
import com.ocean.proxy.thrift.entity.AdRecomReq;
import com.ocean.proxy.thrift.entity.AdSpaceType;
import com.ocean.proxy.thrift.entity.AdUserInfo;
import com.ocean.proxy.thrift.entity.UserAdSpaceAttri;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年10月27日 
      @version 1.0 
 */
@Component(value="JichengAdSupplier")
public class JichengAdSupplier   extends AbstractAsynAdSupplier {
	 public AdRecomReply invoke(InvokeAttribute attribute)
			throws AdPullException {
		// TODO Auto-generated method stub
		AdRecomReq adreq = attribute.getAdreq();
		DSPPosition positionInfo = attribute.getDspPosition();
		// 参数转换
		JichengAdPullParams params = parseParams(adreq, positionInfo);
		// 调用API
		JichengAdPullResponse response = (JichengAdPullResponse) invoke(params,JichengAdPullResponse.class,adreq.getHash()
				,String.valueOf(adreq.getUserAdSpaceAttri().getAdSpaceId()));
		
		// 解析结果
		return parseResult(response);
	}

	private JichengAdPullParams parseParams(AdRecomReq adreq, 
			DSPPosition positionInfo) throws AdPullException {
		if(StringUtils.isEmpty(positionInfo.getText1())||StringUtils.isEmpty(positionInfo.getText2())
				||StringUtils.isEmpty(positionInfo.getText3())){
			throw  new AdPullException("app id | chennel id | pkg name is empty!");
		}
		AdUserInfo userInfo=adreq.getUserinfo();
		UserAdSpaceAttri  attr=adreq.getUserAdSpaceAttri();
		JichengAdPullParams param =new JichengAdPullParams();
		int poseType = positionInfo.getPosType();
		if(poseType == AdSpaceType.BANNER.getValue()){
			param.setAd_type("1001");
		}else if(poseType == AdSpaceType.INTERSTITIAL.getValue()){
			param.setAd_type("1002");
		}else if(poseType == AdSpaceType.OPENING.getValue()){
			param.setAd_type("1003");
		}else if(poseType == AdSpaceType.INFOFLOW.getValue()){
			param.setAd_type("1000");
		}
		param.setApp_id(positionInfo.getText1());
		param.setChannel_id(positionInfo.getText2());
		param.setAd_postion(positionInfo.getPos());
		param.setAd_size(attr.getSpaceWidth()+"x"+attr.getSpaceHeight());
		
		param.setAndroid_id(userInfo.getAdid());

		String os = userInfo.getOs();
		if(OS_ANDROID.equals(os)){
			param.setImei(userInfo.getImei());
			param.setOs(1);
		}else{
			param.setIdfa(userInfo.getIdfa());
			param.setOs(2);
		}
		param.setOs_ver(this.convOSVersion(userInfo.getOsversion()));
		param.setBrand(userInfo.getBrand_name());
		param.setModel(userInfo.getPhonemodel());
		param.setScreen_size(userInfo.getDevice_width()+"x"+userInfo.getDevice_height());
		param.setIp(userInfo.getClient_ip());
		param.setConnection_type(getNetwork(adreq.getNet()));
		param.setOperator_type(getOp(userInfo.getMobile()));
		param.setApp_version(adreq.getVersion());
		param.setPackage_name(positionInfo.getText3());
		param.setUa(userInfo.getUa());
		param.setImsi(userInfo.getImsi());
		param.setDevice_type(1);
		if(StringUtils.isNotEmpty(userInfo.getLat())&&StringUtils.isNotEmpty(userInfo.getLon())){
			param.setLat(userInfo.getLat());
			param.setLng(userInfo.getLon());
			
		}
		return param;
	}
	private  int getNetwork(String net){	
		if(NETWORK_WIFI.equals(net)){
			return 100;
		}else if(NETWORK_2G.equals(net)){
			return 2;
		}
		else if(NETWORK_3G.equals(net)){
			return 3;
		}
		else if(NETWORK_4G.equals(net)){
			return 4;
		}
		return 1;
	}
	private int getOp(String op){

		if(MOBILE_CMCC.equals(op)){
			return 1;
		}else if(MOBILE_CUCC.equals(op)){
			return 3;
		}else if(MOBILE_CTCC.equals(op)){
			return 2;
		}
		return 0;
	}
	private AdRecomReply parseResult(JichengAdPullResponse body)
			throws AdPullException {
		if(body == null||CollectionUtils.isEmpty(body.getAd_list())){
			return null;
		}
		if(0!=body.getRet()){
			throw new AdPullException("ad request error, return error code:"+body.getRet()+" msg:"+body.getMsg());
			
		}
		AdRecomReply reply=new AdRecomReply();
		reply.setStatus(status);
		reply.setAd_contents(this.dealAds(body));
		return reply;
	}
	private List<AdContent>  dealAds(JichengAdPullResponse resp){
		List<AdContent> list=new ArrayList<AdContent> ();
		
		for(JichengAd ad:resp.getAd_list()){
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
	        if(CollectionUtils.isNotEmpty(ad.getImage_src())){
	        	for(String url:ad.getImage_src()){
	    			AdImg img=new AdImg();
	    			img.setSrc(url);
	    			imgs.add(img);	
	        	}
	        }
	        if(StringUtils.isNotEmpty(ad.getClick_url())){

	    		content.setLinkurl(ad.getClick_url());
	    		action.setLinkurl(ad.getClick_url());
	        }
	       
	    	try {
	    		 if(StringUtils.isNotEmpty(ad.getTitle())){
				     title = URLDecoder.decode(ad.getTitle(),CHARSET_CODER);
	    		 }
	    		 if(StringUtils.isNotEmpty(ad.getDescription())){
	    				marketTitle=URLDecoder.decode(ad.getDescription(),CHARSET_CODER);
	    		 }
			
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
			
			if(ad.getInteraction_type()==2){
				acType=ACTION_APP;
			}
			if(StringUtils.isNotEmpty(ad.getApp_package())){
				action.setCpPackage(ad.getApp_package());
			}
			if(StringUtils.isNotEmpty(ad.getHtml_snippet())&&!"0".equals(ad.getHtml_snippet())){
				content.setHtmlSnippet(ad.getHtml_snippet());
				content.setIsHtmlAd(true);
			}
			
			if(ad.getTrack()!=null){
				JichengTrack track=ad.getTrack();
				if(CollectionUtils.isNotEmpty(track.getImp())){
					 map.put(SHOW,track.getImp());
				 }

				if(CollectionUtils.isNotEmpty(track.getClk())){
					 map.put(CLICK, track.getClk());
				 }

				if(CollectionUtils.isNotEmpty(track.getDownload())){
				
					map.put(DOWNLOAD, track.getDownload());
				}

				if(CollectionUtils.isNotEmpty(track.getInstall())){

					 map.put(INSTALL,track.getInstall());

				 }
				  
				 if(CollectionUtils.isNotEmpty(track.getActive())){

					 map.put(ACTIVE,track.getActive());

				 }
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
		AdProxyJichengTask task=new AdProxyJichengTask();
		//heard
		Map<String, String> headers = new HashMap<String, String>(2);
		headers.put("Content-Type", "application/json");
		task.setHeaders(headers);
		JichengAdPullParams param = (JichengAdPullParams) params;

		task.setParam(param);
		task.setDataFormat(DataFormat.DATA_FORMAT_JSON);
		task.setInvokeType(InvokeType.INVOKE_TYPE_POST);
		task.setHashCode(hashCode);
		task.setUrl(SystemContext.getDynamicPropertyHandler().get(ProxyConstants.JICHENG_URL));
		
		return task;
	}


}
