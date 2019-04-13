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
import com.inveno.util.StringUtil;
import com.ocean.core.common.http.Bean2Utils;
import com.ocean.core.common.system.SystemContext;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
import com.ocean.core.common.threadpool.workthread.Task.DataFormat;
import com.ocean.core.common.threadpool.workthread.Task.InvokeType;
import com.ocean.persist.api.proxy.AdPullException;
import com.ocean.persist.api.proxy.ruishi.RuishiAdPullParams;
import com.ocean.persist.api.proxy.ruishi.RuishiAdPullResponse;
import com.ocean.persist.api.proxy.ruishi.RuishiNativeAd;
import com.ocean.persist.api.proxy.ruishi.RuishiNativeImg;
import com.ocean.persist.api.proxy.ruishi.RuishiTracking;
import com.ocean.persist.api.proxy.youken.MD5Utils;
import com.ocean.persist.common.ProxyConstants;
import com.ocean.persist.model.proxy.DSPPosition;
import com.ocean.proxy.api.base.AbstractAsynAdSupplier;
import com.ocean.proxy.serverdis.AdProxyRuishiTask;
import com.ocean.proxy.thrift.entity.AdContent;
import com.ocean.proxy.thrift.entity.AdImg;
import com.ocean.proxy.thrift.entity.AdMutiAction;
import com.ocean.proxy.thrift.entity.AdRecomReply;
import com.ocean.proxy.thrift.entity.AdRecomReq;
import com.ocean.proxy.thrift.entity.AdSpaceType;
import com.ocean.proxy.thrift.entity.AdUserInfo;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年2月28日 
      @version 1.0 
 */
@Component(value="RuishiAdSupplier")
public class RuishiAdSupplier    extends AbstractAsynAdSupplier {
	private static final String RUISHI_DOWN_X="__CLICK_DOWN_X__"	;/*用户手指按下时的横坐标	

	如无法获取时，需替换为-999。

	替换未无效值或未替换，流量可能无法获取收益！*/
	private static final  String RUISHI_DOWN_Y="__CLICK_DOWN_Y__"	;//用户手指按下时的纵坐标
	private static final  String RUISHI_UP_X="__CLICK_UP_X__"	;

	//用户手指离开设备屏幕时的横坐标
	private static final  String RUISHI_UP_Y="__CLICK_UP_Y__"	;

	//用户手指离开设备屏幕时的横坐标
	public AdRecomReply invoke(InvokeAttribute attribute)
				throws AdPullException {
			// TODO Auto-generated method stub
			AdRecomReq adreq = attribute.getAdreq();
			DSPPosition positionInfo = attribute.getDspPosition();
			// 参数转换
			RuishiAdPullParams params = parseParams(adreq, positionInfo);
			// 调用API
			RuishiAdPullResponse response = (RuishiAdPullResponse) invoke(params,RuishiAdPullResponse.class,adreq.getHash()
					,String.valueOf(adreq.getUserAdSpaceAttri().getAdSpaceId()));
			
			// 解析结果
			return parseResult(response,positionInfo.getPosType());
		}

	private RuishiAdPullParams parseParams(AdRecomReq adreq, 
			DSPPosition positionInfo) throws AdPullException {
		if(StringUtils.isEmpty(positionInfo.getText1())||StringUtils.isEmpty(positionInfo.getText3())){
			throw  new AdPullException("appId  or pkg name is empty!");
		}
		AdUserInfo userInfo=adreq.getUserinfo();
		RuishiAdPullParams param =new RuishiAdPullParams();
		param.setTagid(positionInfo.getPos());
		param.setAppid(positionInfo.getText1());
		param.setAppname(adreq.getApp());
		param.setPkgname(positionInfo.getText3());
		param.setAppversion(adreq.getVersion());
	
		String osCode = userInfo.getOs();
		// 如果是ios
	
	    if(OS_IOS.equals(osCode)){

	    	param.setOs(2);
	    	param.setIdfa(userInfo.getIdfa());
	    }else if(OS_ANDROID.equals(osCode)){
	    	param.setOs(1);
	        param.setAnid(userInfo.getAdid());
	    }
	    param.setOsv(this.convOSVersion(userInfo.getOsversion()));
	    param.setCarrier(getOp(userInfo.getMobile()));
	    param.setConn(getNetwork(adreq.getNet()));
	    param.setIp(userInfo.getClient_ip());
	    param.setMake(userInfo.getBrand_name());
	    try {
			param.setModel(URLEncoder.encode(userInfo.getPhonemodel(),CHARSET_CODER));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    param.setImei(userInfo.getImei());
	    param.setMac(userInfo.getMac());
	    param.setAnid(userInfo.getAdid());
		if(StringUtils.isNotEmpty(userInfo.getLat())&&StringUtils.isNotEmpty(userInfo.getLon())){
			param.setLat(userInfo.getLat());
			param.setLon(userInfo.getLon());
			
		}
		param.setSw(userInfo.getDevice_width());
		param.setSh(userInfo.getDevice_height());
		param.setDevicetype(1);
		try {
			param.setUa(URLEncoder.encode(userInfo.getUa(),CHARSET_CODER));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int poseType = positionInfo.getPosType();
		if(poseType == AdSpaceType.BANNER.getValue()){
			param.setAdt(0);
		}else if(poseType == AdSpaceType.INTERSTITIAL.getValue()){
			param.setAdt(1);
		}else if(poseType == AdSpaceType.OPENING.getValue()){
			param.setAdt(2);
		}else if(poseType == AdSpaceType.INFOFLOW.getValue()){
			param.setAdt(3);
		}else {
			throw new AdPullException("position type erro:type is {}"+positionInfo.getPosType());
		}

		return param;
	}	/**
	 * 生成token
	 * @param req
	 * @param ctime
	 * @return
	 * @throws Exception
	 */
	public static String getToken(String req,String token, Long ctime) throws Exception {
		String tokenLocal = MD5Utils.md5AsString(req + token + ctime);
		return tokenLocal;
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
		}else if(NETWORK_5G.equals(net)){
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
	private AdRecomReply parseResult(RuishiAdPullResponse body,	int poseType )
			throws AdPullException {
		if(body == null){
			return null;
		}
		if(0!=body.getStatus()&&101!=body.getStatus()){
			throw new AdPullException("ad request error, return error code:"+body.getStatus());
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
    	//***********************END 常规设置***************	
        //图片
        List<AdImg> imgs = new ArrayList<AdImg>();
        if(StringUtils.isNotEmpty(body.getImgurl())){
            	AdImg img = new AdImg();
    			img.setSrc(body.getImgurl());
    			img.setHeight(body.getH());
    			img.setWidth(body.getW());
    			imgs.add(img);
        }
        if(StringUtils.isNotEmpty(body.getDeeplink())){//优先获取deepLink
        	content.setLinkurl(body.getDeeplink());
        	action.setLinkurl(body.getDeeplink());
        }else if(StringUtils.isNotEmpty(body.getLdp())){
        	content.setLinkurl(format(body.getLdp()));
        	action.setLinkurl(format(body.getLdp()));
        }
        if(StringUtils.isNotEmpty(body.getAdtitle())){
        	title=body.getAdtitle();
        }
        if(body.getAdt()<3&&body.getCtype()==1&&StringUtil.isNotEmpty(body.getAdm())){
        	content.setIsHtmlAd(true);
        	content.setHtmlSnippet(body.getAdm());
        }
        if(body.getAdt()==3){//原生广告
        	RuishiNativeAd _native=body.getNativead();
            if(StringUtils.isNotEmpty(_native.getTitle())){
            	title=_native.getTitle();
            }
            if(CollectionUtils.isNotEmpty(_native.getImg())){
            	
            	for(RuishiNativeImg imgR:_native.getImg()){
            		AdImg img = new AdImg();
        			img.setSrc(imgR.getUrl());
        			img.setHeight(imgR.getH());
        			img.setWidth(imgR.getW());
        			imgs.add(img);
            	}
            }

            if(StringUtils.isNotEmpty(_native.getDeeplink())){
            	content.setLinkurl(format(_native.getDeeplink()));
            	action.setLinkurl(format(_native.getDeeplink()));
            }else if(StringUtils.isNotEmpty(_native.getLdp())){
            	content.setLinkurl(format(_native.getLdp()));
            	action.setLinkurl(format(_native.getLdp()));
            }else if(StringUtils.isNotEmpty(_native.getApp_download_url())){
            	content.setLinkurl(format(_native.getApp_download_url()));
            	action.setLinkurl(format(_native.getApp_download_url()));
            }
        }
		//上报
        Map<String, List<String>> map = new HashMap<String, List<String>>();
	
        if(CollectionUtils.isNotEmpty(body.getImp_tracking())){
        	map.put(SHOW,body.getImp_tracking());
        	
        }
        List<String> clickL=new ArrayList<String>();
        List<String> downL=new ArrayList<String>();
        List<String> downEndL=new ArrayList<String>();
        List<String> installL=new ArrayList<String>();
        List<String> installEndL=new ArrayList<String>();
        List<String> activeL=new ArrayList<String>();
        if(CollectionUtils.isNotEmpty(body.getClk_tracking())){
        	for(String url:body.getClk_tracking()){
        	  	clickL.add(format(url));
        	}
        }
    

        if(CollectionUtils.isNotEmpty(body.getConv_tracking())){
        	for(RuishiTracking tracking:body.getConv_tracking()){
        		if(tracking.getTrack_type()==5){
        			downL.add(tracking.getUrl());
        		}else if(tracking.getTrack_type()==6){//安装完成
        			installEndL.add(tracking.getUrl());
        		}else if(tracking.getTrack_type()==7){//下载完成
        			downEndL.add(tracking.getUrl());
        		}else if(tracking.getTrack_type()==8){//安装开始
        			installL.add(tracking.getUrl());
        		}else if(tracking.getTrack_type()==9){//激活
        			activeL.add(tracking.getUrl());
        		}
        		
        	}
        }
        if(CollectionUtils.isNotEmpty(body.getDp_tracking())){
        	activeL.addAll(body.getDp_tracking());
        }
        if(StringUtils.isNotEmpty(body.getPkgname())){
        	action.setCpPackage(body.getPkgname());
        }
        if(StringUtils.isNotEmpty(body.getApp_name())){
        	action.setCpName(body.getApp_name());
        }
        if(body.getInteract_type()==1){
        	 acType=ACTION_APP;
        }
        map.put(CLICK, clickL);
        map.put(START_DOWN, downL);
        map.put(DOWNLOAD, downEndL);
        map.put(START_INSTALL, installL);
        map.put(INSTALL, installEndL);
        map.put(ACTIVE, activeL);
        
	    content.setThirdReportLinks(map);	
		content.setImglist(imgs);
	    action.setType(acType); 
		content.setMarketTitle(marketTitle);
		content.setGuideTitle(title);
		content.setMutiAction(Collections.singletonList(action));

		reply.setAd_contents(Collections.singletonList(content));
		return reply;
	}
	private  String format(String str){

		String format= str.replaceAll(RUISHI_DOWN_X, MACRO_DOWN_X).replaceAll(RUISHI_DOWN_Y,MACRO_DOWN_Y)
				.replaceAll(RUISHI_UP_X, MACRO_UP_X).replaceAll(RUISHI_UP_Y, MACRO_UP_Y);
		return format;
	}
	@Override
	public AsynAbstractTask packageTask(Parameter params, String hashCode) {
		// TODO Auto-generated method stub
		AdProxyRuishiTask task=new AdProxyRuishiTask();
		RuishiAdPullParams param = (RuishiAdPullParams) params;

		Map<String, String> headers = new HashMap<String, String>(2);
		headers.put("User-Agent", param.getUa());
		task.setHeaders(headers);
		
		param.setUa("");
		StringBuilder buff=new StringBuilder(SystemContext.getDynamicPropertyHandler().get(ProxyConstants.RUISHI_URL));
		buff.append("?").append(Bean2Utils.toHttpParams(param)).append("&zkrequestId=").append(hashCode);
		task.setParam(param);
		task.setDataFormat(DataFormat.DATA_FORMAT_KVP);
		task.setInvokeType(InvokeType.INVOKE_TYPE_GET);
		task.setHashCode(hashCode);
		task.setUrl(buff.toString());
		
		return task;
	}

}
