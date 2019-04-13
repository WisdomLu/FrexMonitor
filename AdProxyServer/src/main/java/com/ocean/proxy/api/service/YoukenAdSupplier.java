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
import com.ocean.core.common.JsonUtils;
import com.ocean.core.common.system.SystemContext;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
import com.ocean.core.common.threadpool.workthread.Task.DataFormat;
import com.ocean.core.common.threadpool.workthread.Task.InvokeType;
import com.ocean.persist.api.proxy.AdPullException;
import com.ocean.persist.api.proxy.flying.FlyingAd;
import com.ocean.persist.api.proxy.flying.FlyingAdPullResponse;
import com.ocean.persist.api.proxy.youken.MD5Utils;
import com.ocean.persist.api.proxy.youken.YoukenAd;
import com.ocean.persist.api.proxy.youken.YoukenAdPullParams;
import com.ocean.persist.api.proxy.youken.YoukenAdslot;
import com.ocean.persist.api.proxy.youken.YoukenApp;
import com.ocean.persist.api.proxy.youken.YoukenDevice;
import com.ocean.persist.api.proxy.youken.YoukenGps;
import com.ocean.persist.api.proxy.youken.YoukenMix;
import com.ocean.persist.api.proxy.youken.YoukenNetwork;
import com.ocean.persist.api.proxy.youken.YoukenReq;
import com.ocean.persist.api.proxy.youken.YoukenResponse;
import com.ocean.persist.common.ProxyConstants;
import com.ocean.persist.model.proxy.DSPPosition;
import com.ocean.proxy.api.base.AbstractAsynAdSupplier;
import com.ocean.proxy.serverdis.AdProxyYoukenTask;
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
@Component(value="YoukenAdSupplier")
public class YoukenAdSupplier    extends AbstractAsynAdSupplier {
 
	 public static final String YK_MACRO_DOWN_X = "__DOWN_X__";
	 public static final String YK_MACRO_DOWN_Y = "__DOWN_Y__";
	 public static final String YK_MACRO_UP_X = "__UP_X__";
	 public static final String YK_MACRO_UP_Y = "__UP_Y__";
	 
	 public static final String YK_MACRO_REQ_WIDTH = "__REQ_WIDTH__";
	 public static final String YK_MACRO_REQ_HEIGHT = "__REQ_HEIGHT__";
	 public static final String YK_MACRO_REL_WIDTH = "__WIDTH__";
	 public static final String YK_MACRO_REL_HEIGHT = "__HEIGHT__";
	 public static final String YK_MACRO_CLICKID="_CLICK_ID_";

	 public AdRecomReply invoke(InvokeAttribute attribute)
				throws AdPullException {
			// TODO Auto-generated method stub
			AdRecomReq adreq = attribute.getAdreq();
			DSPPosition positionInfo = attribute.getDspPosition();
			// 参数转换
			YoukenAdPullParams params = parseParams(adreq, positionInfo);
			// 调用API
			YoukenResponse response = (YoukenResponse) invoke(params,YoukenResponse.class,adreq.getHash()
					,String.valueOf(adreq.getUserAdSpaceAttri().getAdSpaceId()));
			
			// 解析结果
			return parseResult(response);
		}

	private YoukenAdPullParams parseParams(AdRecomReq adreq, 
			DSPPosition positionInfo) throws AdPullException {
		if(StringUtils.isEmpty(positionInfo.getText1())||StringUtils.isEmpty(positionInfo.getText2())||StringUtils.isEmpty(positionInfo.getText3())){
			throw  new AdPullException("app id /token or pkg name is empty!");
		}
		AdUserInfo userInfo=adreq.getUserinfo();
		YoukenAdPullParams param =new YoukenAdPullParams();

		long timestamp=System.currentTimeMillis();

		YoukenReq req=new YoukenReq();
		req.setApi_version("1.0.0");
		
		//app
		YoukenApp app =new YoukenApp();
		app.setApp_id(positionInfo.getText1());
		app.setApp_version(adreq.getVersion());
		app.setPackage_name(positionInfo.getText3());
		req.setApp(app);
		
		//device
		YoukenDevice device =new YoukenDevice();
		device.setDevice_type(4);
		String os = userInfo.getOs();
		if(OS_ANDROID.equals(os)){
			device.setOs_type(1);
			device.setOs_version(this.convOSVersion(userInfo.getOsversion()));
		}else{
			device.setOs_type(2);
			device.setIdfa(userInfo.getIdfa());
		}
		device.setVendor(userInfo.getBrand_name());
		device.setModel(userInfo.getPhonemodel());
		device.setManufacturer("Unknown");
		device.setAndroid_id(userInfo.getAdid());
		device.setImei(userInfo.getImei());
		device.setImei_md5(DigestUtils.md5Hex(userInfo.getImei()));
		device.setW(userInfo.getDevice_width());
		device.setH(userInfo.getDevice_height());
		device.setUa(userInfo.getUa());
		device.setImsi(userInfo.getImsi());
		device.setMac(userInfo.getMac());
		if(StringUtils.isNotEmpty(userInfo.getDensity())){
			device.setDpi(Integer.parseInt(userInfo.getDensity()));
		}

		req.setDevice(device);
		
		//adslot
		YoukenAdslot slot=new YoukenAdslot();
		slot.setAdslot_id(positionInfo.getPos());
		slot.setAdslot_h(adreq.getUserAdSpaceAttri().getSpaceHeight());
		slot.setAdslot_w(adreq.getUserAdSpaceAttri().getSpaceWidth());
		req.setAdslot(slot);
		//network
		YoukenNetwork network=new YoukenNetwork();
		network.setIp(userInfo.getClient_ip());
		network.setConnect_type(getNetwork(adreq.getNet()));
		network.setCarrier(getOp(userInfo.getMobile()));
		network.setMcc("460");
		network.setCellular_id("");
		network.setBss_id("");
		network.setLac("");

		network.setSsid("");
		
		
		req.setNetwork(network);
		
		//gps
		if(StringUtils.isNotEmpty(userInfo.getLat())&&StringUtils.isNotEmpty(userInfo.getLon())){
			YoukenGps gps=new YoukenGps();
			gps.setCoordinate_type(4);
			gps.setLon(Double.parseDouble(userInfo.getLon()));
			gps.setLat(Double.parseDouble(userInfo.getLat()));
			gps.setTimestamp((int)(timestamp/1000));
			req.setGps(gps);
		}
	
		String reqJson=JsonUtils.toJson(req);
		param.setTimestamp(timestamp);
		try {
			logger.info("youken {} reqest params:{}",adreq.getHash(),reqJson);
			param.setToken(getToken(reqJson,positionInfo.getText2(),timestamp));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			throw  new AdPullException("youken get token error!");
		}
		try {
			param.setReqjson(URLEncoder.encode(reqJson,CHARSET_CODER));
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			return 2;
		}else if(NETWORK_2G.equals(net)){
			return 4;
		}
		else if(NETWORK_3G.equals(net)){
			return 5;
		}
		else if(NETWORK_4G.equals(net)){
			return 6;
		}
		return 0;
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
	private AdRecomReply parseResult(YoukenResponse body)
			throws AdPullException {
		if(body == null||CollectionUtils.isEmpty(body.getAdms())){
			return null;
		}
		if(0!=body.getError_code()){
			throw new AdPullException("ad request error, return error code:"+body.getError_code());
		}
		AdRecomReply reply=new AdRecomReply();
		reply.setStatus(status);
		reply.setAd_contents(this.dealAds(body));
		return reply;
	}
	private List<AdContent>  dealAds(YoukenResponse resp){
		List<AdContent> list=new ArrayList<AdContent> ();
		for(YoukenAd ad:resp.getAdms()){
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
	        if(StringUtils.isNotEmpty(ad.getClkurl())){

	    		content.setLinkurl(format(REPORT_TYPE_REQ,ad.getClkurl()));
	    		action.setLinkurl(format(REPORT_TYPE_REQ,ad.getClkurl()));
	        }

	        if(ad.getInteraction_type()==2){
	        	acType=ACTION_APP;
	        }
			if(StringUtils.isNotEmpty(ad.getBundle())){
				action.setCpPackage(ad.getBundle());
			}
			if(StringUtils.isNotEmpty(ad.getImgurl())){
				AdImg img=new AdImg();
				img.setSrc(ad.getImgurl());
				imgs.add(img);	
			}
			YoukenMix mat=ad.getMix();
			if(mat!=null){
				title=mat.getTitle();
				for(String url:mat.getImgurl()){
	    			AdImg img=new AdImg();
	    			img.setSrc(url);
	    			imgs.add(img);	
				}
			}
		    List<String> showL=new ArrayList<String>();
			if(CollectionUtils.isNotEmpty(ad.getImptrackers())){
				for(String url:ad.getImptrackers()){
					showL.add(format(REPORT_TYPE_PV,url));
				}
				
			 }
			 map.put(SHOW,showL);
			 List<String> clickL=new ArrayList<String>();
			 if(CollectionUtils.isNotEmpty(ad.getClktrackers())){

				for(String url:ad.getClktrackers()){
					if(ad.getInteraction_type()==1&&url.contains("gdt.qq.com")){
						
						//获取正式下载链接
						String fUrl=format(REPORT_TYPE_CLICK,url);
						content.setLinkurl(fUrl);
			    		action.setLinkurl(fUrl);
			    		action.setLinkurl_type(1);
			    		
			    		clickL.add(fUrl);
					}else{
						clickL.add(format(REPORT_TYPE_CLICK,url));
					}
					
				}
				
			 }
			 if(CollectionUtils.isNotEmpty(ad.getDwnlst())){
				 for(String url:ad.getDwnlst()){
					 clickL.add(format(REPORT_TYPE_CLICK,url));
				 }
			 }
			 
			 map.put(CLICK, clickL);
			 
			 List<String> downL=new ArrayList<String>();
			 if(CollectionUtils.isNotEmpty(ad.getDwnltrackers())){
				 for(String url:ad.getDwnltrackers()){
					 downL.add(format(REPORT_TYPE_DOWNLOAD,url));
				 }
				
			 }
			 map.put(DOWNLOAD, downL);
			 
			 List<String> installL=new ArrayList<String>();
			 if(CollectionUtils.isNotEmpty(ad.getIntltrackers())){
				 for(String url:ad.getIntltrackers()){
					 installL.add(format(REPORT_TYPE_INSTALL,url));
				 }
			     

			 }
			 map.put(INSTALL,installL); 
			 
			 List<String> activeL=new ArrayList<String>();
			 if(CollectionUtils.isNotEmpty(ad.getActvtrackers())){
				 for(String url:ad.getActvtrackers()){
					 activeL.add(format(REPORT_TYPE_ACTIVE,url)); 
				 }
				 
			 }
			 map.put(ACTIVE,activeL); 
				 
		    content.setThirdReportLinks(map);	
			content.setImglist(imgs);
		    action.setType(acType); 
			content.setMarketTitle(marketTitle);
			content.setGuideTitle(title);
			content.setMutiAction(Collections.singletonList(action));

			list.add(content);
		}
		return list;
	}
	private static String format(int type,String str){
		if(type==REPORT_TYPE_PV){
			str=str.replaceAll(YK_MACRO_DOWN_X, MACRO_CLICK_DEFUALT).replaceAll(YK_MACRO_DOWN_Y,MACRO_CLICK_DEFUALT)
					.replaceAll(YK_MACRO_UP_X, MACRO_CLICK_DEFUALT).replaceAll(YK_MACRO_UP_Y, MACRO_CLICK_DEFUALT);
		}else{
			str=str.replaceAll(YK_MACRO_DOWN_X, MACRO_DOWN_X).replaceAll(YK_MACRO_DOWN_Y,MACRO_DOWN_Y)
					.replaceAll(YK_MACRO_UP_X, MACRO_UP_X).replaceAll(YK_MACRO_UP_Y, MACRO_UP_Y);	
		}
		String format= str.replaceAll(YK_MACRO_REQ_WIDTH, MACRO_REQ_WIDTH).replaceAll(YK_MACRO_REQ_HEIGHT, MACRO_REQ_HEIGHT)
				.replaceAll(YK_MACRO_REL_WIDTH, MACRO_REL_WIDTH).replaceAll(YK_MACRO_REL_HEIGHT, MACRO_REL_HEIGHT)
	 
				.replaceAll(YK_MACRO_CLICKID, MACRO_CLICK_ID);
		return format;
	}
	@Override
	public AsynAbstractTask packageTask(Parameter params, String hashCode) {
		// TODO Auto-generated method stub
		AdProxyYoukenTask task=new AdProxyYoukenTask();
		//heard
		Map<String, String> headers = new HashMap<String, String>(2);
		headers.put("Content-Type", "application/json");
		YoukenAdPullParams param = (YoukenAdPullParams) params;
		StringBuilder buff=new StringBuilder(SystemContext.getDynamicPropertyHandler().get(ProxyConstants.YOUKEN_URL));
		buff.append("?reqjson=").append(param.getReqjson()).append("&token=").append(param.getToken());
		task.setHeaders(headers);
		
		headers.put("ctime",param.getTimestamp()+"");
		task.setParam(param);
		task.setDataFormat(DataFormat.DATA_FORMAT_KVP);
		task.setInvokeType(InvokeType.INVOKE_TYPE_GET);
		task.setHashCode(hashCode);
		task.setUrl(buff.toString());
		
		return task;
	}

}
