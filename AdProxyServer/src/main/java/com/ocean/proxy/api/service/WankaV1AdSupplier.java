package com.ocean.proxy.api.service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import com.ocean.persist.api.proxy.wanka_v1.SignatureGen;
import com.ocean.persist.api.proxy.wanka_v1.WankaAd;
import com.ocean.persist.api.proxy.wanka_v1.WankaAdPullParams;
import com.ocean.persist.api.proxy.wanka_v1.WankaAdSlot;
import com.ocean.persist.api.proxy.wanka_v1.WankaApp;
import com.ocean.persist.api.proxy.wanka_v1.WankaDevice;
import com.ocean.persist.api.proxy.wanka_v1.WankaGps;
import com.ocean.persist.api.proxy.wanka_v1.WankaMaterial;
import com.ocean.persist.api.proxy.wanka_v1.WankaNetwork;
import com.ocean.persist.api.proxy.wanka_v1.WankaReport;
import com.ocean.persist.api.proxy.wanka_v1.WankaReq;
import com.ocean.persist.api.proxy.wanka_v1.WankaResponse;
import com.ocean.persist.api.proxy.wanka_v1.WankaTracker;
import com.ocean.persist.api.proxy.woso.WosoAd;
import com.ocean.persist.api.proxy.woso.WosoAdResponse;
import com.ocean.persist.common.ProxyConstants;
import com.ocean.persist.model.proxy.DSPPosition;
import com.ocean.proxy.api.base.AbstractAsynAdSupplier;
import com.ocean.proxy.serverdis.AdProxyWankaV1Task;
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
@Component(value="WankaV1AdSupplier")
public class WankaV1AdSupplier    extends AbstractAsynAdSupplier {
	 public static final String WK_MACRO_DOWN_X= "\\$\\{down_x\\}";
	 public static final String WK_MACRO_DOWN_Y = "\\$\\{down_y\\}";
	 public static final String WK_MACRO_UP_X = "\\$\\{up_x\\}";
	 public static final String WK_MACRO_UP_Y = "\\$\\{up_y\\}";
	 public static final String WK_MACRO_RELATIVE_DOWN_X= "\\$\\{relative_down_x\\}";
	 public static final String WK_MACRO_RELATIVE_DOWN_Y = "\\$\\{relative_down_y\\}";
	 public static final String WK_MACRO_RELATIVE_UP_X = "\\$\\{relative_up_x\\}";
	 public static final String WK_MACRO_RELATIVE_UP_Y = "\\$\\{relative_up_y\\}";
	 public static final String WK_MACRO_CLICKID="\\$\\{click_id\\}";
	 
	 
	 
	 public AdRecomReply invoke(InvokeAttribute attribute)
				throws AdPullException {
			// TODO Auto-generated method stub
			AdRecomReq adreq = attribute.getAdreq();
			DSPPosition positionInfo = attribute.getDspPosition();
			// 参数转换
			WankaAdPullParams params = parseParams(adreq, positionInfo);
			// 调用API
			WankaResponse response = (WankaResponse) invoke(params,WankaResponse.class,adreq.getHash()
					,String.valueOf(adreq.getUserAdSpaceAttri().getAdSpaceId()));
			
			// 解析结果
			return parseResult(response);
		}

	private WankaAdPullParams parseParams(AdRecomReq adreq, 
			DSPPosition positionInfo) throws AdPullException {
		if(StringUtils.isEmpty(positionInfo.getText1())||StringUtils.isEmpty(positionInfo.getText2())||StringUtils.isEmpty(positionInfo.getText3())){
			throw  new AdPullException("app id /token or pkg name is empty!");
		}
		AdUserInfo userInfo=adreq.getUserinfo();
		WankaAdPullParams param =new WankaAdPullParams();
		String channel=SystemContext.getDynamicPropertyHandler().get(ProxyConstants.WANKA_CHANNEL);
		param.setChannel_id(channel);
		long timestamp=System.currentTimeMillis();
		param.setTimestamp(timestamp);
		WankaReq req=new WankaReq();
		req.setApi_version("3.0.0");
		
		//app
		WankaApp app =new WankaApp();
		app.setApp_id(positionInfo.getText1());
		app.setApp_version(formatAV(adreq.getVersion()));
		
		app.setPackage_name(positionInfo.getText3());
		req.setApp(app);
		
		//device
		WankaDevice device =new WankaDevice();
		device.setDevice_type(4);
		String os = userInfo.getOs();
		if(OS_ANDROID.equals(os)){
			device.setOs_type(OS_ANDROID);
			device.setOs_version(this.convOSVersion(userInfo.getOsversion()));
		}else{
			device.setOs_type("ios");
			device.setIdfa(userInfo.getIdfa());
		}
		device.setLanguage("zh");
		device.setAndroid_advertising_id("");
		device.setOpenudid("");
		device.setCountry("China");
		device.setResolution("0*0");
		device.setOrientation(0);
		device.setReferer("");
		device.setIsroot(0);
		device.setBtmac("");
		device.setPdunid("");
		device.setCputy("");
		device.setCpusn("");
		device.setIccid("");
		
		
		
		device.setVendor(userInfo.getBrand_name());
		device.setModel(userInfo.getPhonemodel());
		device.setAndroid_id(userInfo.getAdid());
		device.setAndroid_id_md5(DigestUtils.md5Hex(userInfo.getAdid()));
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
		WankaAdSlot slot=new WankaAdSlot();
		slot.setAdslot_id(positionInfo.getPos());
		slot.setRender_type(0);
		slot.setDeeplink(1);
		req.setAdslot(slot);
		//network
		WankaNetwork network=new WankaNetwork();
		network.setIp(userInfo.getClient_ip());
		network.setConnect_type(getNetwork(adreq.getNet()));
		network.setCarrier(getOp(userInfo.getMobile()));
		network.setMcc("460");
		network.setCellular_id("");
		network.setBss_id("");
		network.setLac("");

		network.setNetwk_id("");
		network.setSsid("");
		network.setLksd(0);
		network.setRoaming(0);
		
		
		req.setNetwork(network);
		
		//gps
		if(StringUtils.isNotEmpty(userInfo.getLat())&&StringUtils.isNotEmpty(userInfo.getLon())){
			WankaGps gps=new WankaGps();
			gps.setCoordinate_type(4);
			gps.setLon(Double.parseDouble(userInfo.getLon()));
			gps.setLat(Double.parseDouble(userInfo.getLat()));
			gps.setCoord_time(timestamp);
			gps.setLocation_accuracy(10);
			req.setGps(gps);
		}
		
		URL  url=this.getUrl(SystemContext.getDynamicPropertyHandler().get(ProxyConstants.WANKA_URL));
		
		if(url==null){
			System.out.println("url:"+url);
			throw  new AdPullException("request url is empty!");
			
		}
		
		String reqJson=JsonUtils.toJson(req);
		param.setReqjson(reqJson);
		try {
			param.setSignature(SignatureGen.generateToken("POST", url.getHost(), url.getPath(),positionInfo.getText2(), reqJson,timestamp));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw  new AdPullException("get sinature error {}"+e.getMessage());
		}
		return param;
	}
	private String formatAV(String appVersion){
		final String DEF_VERSION="1.1.1";
		if(StringUtils.isEmpty(appVersion)){
			return  DEF_VERSION;
		}
		Pattern pattern = Pattern.compile("^\\d+(\\.\\d+){2,2}$");
		Matcher matcher = pattern.matcher(appVersion);

		if (matcher.find()){
			return appVersion;
	    }else{
	    	return DEF_VERSION;
	    }
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
	private URL getUrl(String url){
		  try {
			return new URL(url);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  return null;
	}
	private AdRecomReply parseResult(WankaResponse body)
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
	private List<AdContent>  dealAds(WankaResponse resp){
		List<AdContent> list=new ArrayList<AdContent> ();
		for(WankaAd ad:resp.getAdms()){
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
	        	String link=format(REPORT_TYPE_REQ,ad.getClkurl());
	    		content.setLinkurl(link);
	    		action.setLinkurl(link);
	        }else if(StringUtils.isNotEmpty(ad.getDplk())){
	        	content.setLinkurl(ad.getDplk());
	    		action.setLinkurl(ad.getDplk());
	        }
	        if(StringUtils.isNotEmpty(ad.getHtml_segment())){
	        	content.setHtmlSnippet(ad.getHtml_segment());
	        	content.setIsHtmlAd(true);
	        }
	        if(ad.getInteraction_type()==2){
	        	acType=ACTION_APP;
	        }
			if(StringUtils.isNotEmpty(ad.getBundle())){
				action.setCpPackage(ad.getBundle());
			}
			
			WankaMaterial mat=ad.getMaterialVO();
			if(mat!=null){
				title=mat.getTitle();
				for(String url:mat.getImgurl()){
	    			AdImg img=new AdImg();
	    			img.setSrc(url);
	    			imgs.add(img);	
				}
			}
			WankaReport report=ad.getReportVO();
		    List<String> showL=new ArrayList<String>();
			if(CollectionUtils.isNotEmpty(report.getImptrackers())){
				for(WankaTracker tracker:report.getImptrackers()){
					showL.add(format(REPORT_TYPE_PV,tracker.getUrl()));
				}
				
			 }
			 map.put(SHOW,showL);
			 List<String> clickL=new ArrayList<String>();
			 if(CollectionUtils.isNotEmpty(report.getClktrackers())){

				for(WankaTracker tracker:report.getClktrackers()){
					if(tracker.getTemplate_type()==1&&ad.getInteraction_type()==2){
						
						//获取正式下载链接
						String url=format(REPORT_TYPE_CLICK,tracker.getUrl());
						content.setLinkurl(url);
			    		action.setLinkurl(url);
			    		action.setLinkurl_type(1);
			    		
			    		clickL.add(url);
					}else{
						clickL.add(format(REPORT_TYPE_CLICK,tracker.getUrl()));
					}
					
				}
				
			 }
			 if(CollectionUtils.isNotEmpty(report.getDwnlsts())){
				 for(WankaTracker tracker:report.getDwnlsts()){
					 clickL.add(format(REPORT_TYPE_CLICK,tracker.getUrl()));
				 }
			     
			
			 }
			 
			 map.put(CLICK, clickL);
			 
			 List<String> downL=new ArrayList<String>();
			 if(CollectionUtils.isNotEmpty(report.getDplktrackers())){
				 for(WankaTracker tracker:report.getDplktrackers()){
					 downL.add(format(REPORT_TYPE_DOWNLOAD,tracker.getUrl()));
				 }
				
			 }
			 map.put(DOWNLOAD, downL);
			 
			 List<String> installL=new ArrayList<String>();
			 if(CollectionUtils.isNotEmpty(report.getIntltrackers())){
				 for(WankaTracker tracker:report.getIntltrackers()){
					 installL.add(format(REPORT_TYPE_INSTALL,tracker.getUrl()));
				 }
			     

			 }
			 map.put(INSTALL,installL); 
			 
			 List<String> activeL=new ArrayList<String>();
			 if(CollectionUtils.isNotEmpty(report.getActvtrackers())){
				 for(WankaTracker tracker:report.getActvtrackers()){
					 activeL.add(format(REPORT_TYPE_ACTIVE,tracker.getUrl())); 
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
	private static String format(int type ,String str){
		if(type==REPORT_TYPE_PV){
			str=str.replaceAll(WK_MACRO_DOWN_X, MACRO_CLICK_DEFUALT).replaceAll(WK_MACRO_DOWN_Y,MACRO_CLICK_DEFUALT)
			.replaceAll(WK_MACRO_UP_X,MACRO_CLICK_DEFUALT).replaceAll(WK_MACRO_UP_Y, MACRO_CLICK_DEFUALT)
			.replaceAll(WK_MACRO_RELATIVE_DOWN_X, MACRO_CLICK_DEFUALT).replaceAll(WK_MACRO_RELATIVE_DOWN_Y,MACRO_CLICK_DEFUALT)
			.replaceAll(WK_MACRO_RELATIVE_UP_X, MACRO_CLICK_DEFUALT).replaceAll(WK_MACRO_RELATIVE_UP_Y,MACRO_CLICK_DEFUALT);
		}else{
			str=str.replaceAll(WK_MACRO_DOWN_X, MACRO_DOWN_X).replaceAll(WK_MACRO_DOWN_Y,MACRO_DOWN_Y)
			.replaceAll(WK_MACRO_UP_X, MACRO_UP_X).replaceAll(WK_MACRO_UP_Y, MACRO_UP_Y)
			.replaceAll(WK_MACRO_RELATIVE_DOWN_X, MACRO_DOWN_X).replaceAll(WK_MACRO_RELATIVE_DOWN_Y,MACRO_DOWN_Y)
			.replaceAll(WK_MACRO_RELATIVE_UP_X, MACRO_UP_X).replaceAll(WK_MACRO_RELATIVE_UP_Y, MACRO_UP_Y);
		}
		String format= str.replaceAll(WK_MACRO_CLICKID, MACRO_CLICK_ID);
		return format;
	}
	@Override
	public AsynAbstractTask packageTask(Parameter params, String hashCode) {
		// TODO Auto-generated method stub
		AdProxyWankaV1Task task=new AdProxyWankaV1Task();
		//heard
		Map<String, String> headers = new HashMap<String, String>(2);
		headers.put("Content-Type", "application/json;utf-8");
		headers.put("Accept-Encoding", "gzip");
		task.setHeaders(headers);
		WankaAdPullParams param = (WankaAdPullParams) params;

		task.setParam(param);
		task.setDataFormat(DataFormat.DATA_FORMAT_STREAM);
		task.setInvokeType(InvokeType.INVOKE_TYPE_POST);
		task.setHashCode(hashCode);
		task.setUrl(SystemContext.getDynamicPropertyHandler().get(ProxyConstants.WANKA_URL));
		
		return task;
	}

}
