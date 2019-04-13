package com.ocean.proxy.api.service;

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
import com.ocean.persist.api.proxy.uniplay.UniPlayAd;
import com.ocean.persist.api.proxy.uniplay.UniPlayAdExt;
import com.ocean.persist.api.proxy.uniplay.UniPlayAdPullParams;
import com.ocean.persist.api.proxy.uniplay.UniPlayAdPullResponse;
import com.ocean.persist.api.proxy.uniplay.UniPlayApp;
import com.ocean.persist.api.proxy.uniplay.UniPlayDevice;
import com.ocean.persist.common.ProxyConstants;
import com.ocean.persist.model.proxy.DSPPosition;
import com.ocean.proxy.api.base.AbstractAsynAdSupplier;
import com.ocean.proxy.serverdis.AdProxyUniPlayTask;
import com.ocean.proxy.thrift.entity.AdContent;
import com.ocean.proxy.thrift.entity.AdImg;
import com.ocean.proxy.thrift.entity.AdMutiAction;
import com.ocean.proxy.thrift.entity.AdRecomReply;
import com.ocean.proxy.thrift.entity.AdRecomReq;
import com.ocean.proxy.thrift.entity.AdUserInfo;
import com.ocean.proxy.thrift.entity.UserAdSpaceAttri;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年9月7日 
      @version 1.0 
 */
@Component(value="uniPlayAdSupplier")
public class UniPlayAdSupplier    extends AbstractAsynAdSupplier{

	public AdRecomReply invoke(InvokeAttribute attribute)
			throws AdPullException {
		// TODO Auto-generated method stub
		AdRecomReq adreq = attribute.getAdreq();
		DSPPosition positionInfo = attribute.getDspPosition();
		// 参数转换
		UniPlayAdPullParams params = parseParams(adreq, positionInfo);
		// 调用API
		UniPlayAdPullResponse response = (UniPlayAdPullResponse)invoke(params,UniPlayAdPullResponse.class,adreq.getHash(),String.valueOf(adreq.getUserAdSpaceAttri().getAdSpaceId()),"joinDSP:uniplay");
		// 解析结果
		return parseResult(response);
	}
	private UniPlayAdPullParams parseParams(AdRecomReq adreq, 
			DSPPosition positionInfo) throws AdPullException {
		UniPlayAdPullParams  param=new UniPlayAdPullParams();
		if(StringUtils.isEmpty(positionInfo.getText1())||StringUtils.isEmpty(positionInfo.getText3())){
		    throw new 	AdPullException("app id or package name is empty!");
		}
		AdUserInfo userInfo=adreq.getUserinfo();
		UserAdSpaceAttri  attri=adreq.getUserAdSpaceAttri();
		
		UniPlayApp app=new UniPlayApp();
		app.setPkg(positionInfo.getText3());
		app.setVer(adreq.getVersion());
		param.setApp(app);
		
		UniPlayDevice device=new UniPlayDevice();
		String osCode = userInfo.getOs();
		// 如果是ios
	    if(OS_IOS.equals(osCode)){
			// ios 必填信息
	    	device.setIdfa(userInfo.getIdfa());
	    	device.setPlt(2);

	    }else if(OS_ANDROID.equals(osCode)){
	    	device.setPlt(1);
	    	device.setIme(userInfo.getImei());
	    	if(StringUtils.isNotEmpty(userInfo.getImsi())){
	    		device.setIcc(userInfo.getImsi());
	    	}
	    	
	    	device.setAid(userInfo.getAdid());
		}else{
			device.setPlt(3);
		}
	    device.setOv(this.convOSVersion(userInfo.getOsversion()));
	    device.setSwidth(String.valueOf(userInfo.getDevice_width()));
	    device.setSheight(String.valueOf(userInfo.getDevice_height()));
	    device.setMdl(userInfo.getPhonemodel());
	    device.setBrd(userInfo.getBrand_name());
	    Integer net =getNetwork(adreq.getNet());
	    device.setNet(net);

	   
	    
	    String mobile=mobiles.get(userInfo.getMobile());
	    device.setOpt(StringUtils.isEmpty(mobile)?"unknown":mobile);
	    device.setMac(userInfo.getMac());
	    device.setUa(userInfo.getUa());
	    param.setDevice(device);
	    
	    param.setAdh(attri.getSpaceHeight());
	    param.setAdw(attri.getSpaceWidth());
	    param.setAppid(positionInfo.getText1());
	    if(positionInfo.getPosType()==1){
	    	param.setSlotid("banner");
	    }else if(positionInfo.getPosType()==2){
	    	param.setSlotid("splash");
	    }
	    else if(positionInfo.getPosType()==3){
	    	param.setSlotid("interst");
	    }
	    else if(positionInfo.getPosType()==4){
	    	param.setSlotid("feed");
	    }
	    param.setIp(userInfo.getClient_ip());
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
	private AdRecomReply parseResult(UniPlayAdPullResponse response)
			throws AdPullException {
		
		if(response == null||response.getAd()==null){
			return null;
		}
		if(0!=response.getRes()){
			throw new AdPullException("ad request error,error msg:"+response.getMsg()+",error code:"+response.getRes());
		}
		UniPlayAd ad=response.getAd();
		AdRecomReply reply=new AdRecomReply();
		reply.setStatus(status);
		//***********************BEGIN 常规设置*************
		AdContent content = new AdContent();
		AdMutiAction action = new AdMutiAction();
		String title = defTitle;
		String marketTitle=defTitle;
		// 内容类型1表示链接推广,2表示应用推广,3未使用 4表示电话推广
	 
        int acType=ACTION_WEB;
		//***********************END 常规设置***************
        //图片
        List<AdImg> imgs = new ArrayList<AdImg>();
        if(StringUtils.isNotEmpty(ad.getImg())){
    		AdImg img = new AdImg();
    		img.setSrc(ad.getImg());
    		imgs.add(img);
        }
        if(StringUtils.isNotEmpty(ad.getTitle())){
        	title=ad.getTitle();
        }
        if(StringUtils.isNotEmpty(ad.getTxt())){
        	marketTitle=ad.getTxt();
        }

    	//上报
    	Map<String, List<String>> map = new HashMap<String, List<String>>();
		if(CollectionUtils.isNotEmpty(ad.getImp())){
			map.put(SHOW, ad.getImp());
		}
		if(CollectionUtils.isNotEmpty(ad.getClick())){
			map.put(CLICK, ad.getClick());
		}
		if(StringUtils.isNotEmpty(ad.getHtml())){
			content.setHtmlSnippet(ad.getHtml());
			content.setIsHtmlAd(true);
		}
		if(StringUtils.isNotEmpty(ad.getLpg())){
			String link=this.format(ad.getLpg());
/*			if(!link.equals(ad.getLpg())){
				action.setLinkurl_type(1);
			}*/
        	content.setLinkurl(link);
        	action.setLinkurl(link);
        }
		UniPlayAdExt ext=ad.getAdext();
    	if(ext!=null){
    		
    		if(CollectionUtils.isNotEmpty(ext.getDownsucc())){
    			map.put(DOWNLOAD, ext.getDownsucc());
    			//acType=ACTION_APP;
    		}
    		if(CollectionUtils.isNotEmpty(ext.getInstallsucc())){
    			map.put(INSTALL, ext.getInstallsucc());
    		//	acType=ACTION_APP;
    		}
    		if(CollectionUtils.isNotEmpty(ext.getAppactive())){
    			map.put(ACTIVE, ext.getAppactive());
    		//	acType=ACTION_APP;
    		}
    		
    	}
    	content.setThirdReportLinks(map);
		//img
		content.setImglist(imgs);
	    action.setType(acType); 
		content.setMarketTitle(marketTitle);
		content.setGuideTitle(title);
		action.setGuideTitle(title);
		content.setMutiAction(Collections.singletonList(action));

		reply.setAd_contents(Collections.singletonList(content));
		return reply;
	}
	public String format(String str){
		String format= str.replaceAll("\\[down_x\\]","%%DOWNX%%").replaceAll("\\[down_y\\]","%%DOWNY%%").replaceAll("\\[up_x\\]","%%UPX%%")
    			.replaceAll("\\[up_y\\]", "%%UPY%%")
				.replaceAll("\\[clickid\\]", "%%CLICKID%%");

		return format;
	}
	@Override
	public AsynAbstractTask packageTask(Parameter params, String hashCode) {
		// TODO Auto-generated method stub
		AdProxyUniPlayTask task=new AdProxyUniPlayTask();
		Map<String, String> headers = new HashMap<String, String>(2);
		headers.put("Content-Type", "application/json");
		UniPlayAdPullParams param = (UniPlayAdPullParams) params;
		
		task.setHeaders(headers);
		task.setParam(param);
		task.setDataFormat(DataFormat.DATA_FORMAT_JSON);
		task.setInvokeType(InvokeType.INVOKE_TYPE_POST);
		task.setHashCode(hashCode);
		task.setUrl(SystemContext.getDynamicPropertyHandler().get(ProxyConstants.UNIPLAY_URL));
		return  task;
	}

/*	@Override
	public AdPullResponse invoke(Parameter params, String... exts) {
		// TODO Auto-generated method stub
		AsynInvokeResponse data=asynRequest(params, exts[0]);
		if(data!=null){
			UniPlayAdPullResponse response =(UniPlayAdPullResponse)data.getData();
			
			logger.info("joinDSP:uniplay {} ad return result:{}",exts, response);
			return response;
		}
		return null;
	}*/


}
