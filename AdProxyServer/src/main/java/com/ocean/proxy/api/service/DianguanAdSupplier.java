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
import com.ocean.core.common.JsonUtils;
import com.ocean.core.common.system.SystemContext;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
import com.ocean.core.common.threadpool.workthread.Task.DataFormat;
import com.ocean.core.common.threadpool.workthread.Task.InvokeType;
import com.ocean.persist.api.proxy.AdPullException;
import com.ocean.persist.api.proxy.dianguan.DianguanAd;
import com.ocean.persist.api.proxy.dianguan.DianguanAdslot;
import com.ocean.persist.api.proxy.dianguan.DianguanApp;
import com.ocean.persist.api.proxy.dianguan.DianguanBrowser;
import com.ocean.persist.api.proxy.dianguan.DianguanClient;
import com.ocean.persist.api.proxy.dianguan.DianguanDevice;
import com.ocean.persist.api.proxy.dianguan.DianguanExt;
import com.ocean.persist.api.proxy.dianguan.DianguanMedia;
import com.ocean.persist.api.proxy.dianguan.DianguanNative;
import com.ocean.persist.api.proxy.dianguan.DianguanNet;
import com.ocean.persist.api.proxy.dianguan.DianguanReq;
import com.ocean.persist.api.proxy.dianguan.DianguanResp;
import com.ocean.persist.api.proxy.dianguan.DianguanSnippet;
import com.ocean.persist.api.proxy.dianguan.DianguanStepimp;
import com.ocean.persist.common.ProxyConstants;
import com.ocean.persist.model.proxy.DSPPosition;
import com.ocean.proxy.api.base.AbstractAsynAdSupplier;
import com.ocean.proxy.serverdis.AdProxyDianguanTask;
import com.ocean.proxy.serverdis.AdProxyPaerjiateOLTask;
import com.ocean.proxy.thrift.entity.AdContent;
import com.ocean.proxy.thrift.entity.AdImg;
import com.ocean.proxy.thrift.entity.AdMutiAction;
import com.ocean.proxy.thrift.entity.AdRecomReply;
import com.ocean.proxy.thrift.entity.AdRecomReq;
import com.ocean.proxy.thrift.entity.AdUserInfo;
import com.ocean.proxy.thrift.entity.UserAdSpaceAttri;


@Component(value = "DianguanAdSupplier")
public class DianguanAdSupplier extends AbstractAsynAdSupplier {
	private static final String DG_AZMX="__AZMX__";
	private static final String DG_AZMY="__AZMY__";
	
	private static final String DG_IT_CLK_PNT_DOWN_X="IT_CLK_PNT_DOWN_X";
	private static final String DG_IT_CLK_PNT_DOWN_Y="IT_CLK_PNT_DOWN_Y";
	
	private static final String DG_DOWN_X="\\$\\{down_x\\}";
	private static final String DG_DOWN_Y="\\$\\{down_y\\}";
	
	private static final String DG_AZCX="_AZCX_";
	private static final String DG_AZCY="_AZCY_";
	
	
	private static final String DG_IT_CLK_PNT_UP_X="IT_CLK_PNT_UP_X";
	private static final String DG_IT_CLK_PNT_UP_Y="IT_CLK_PNT_UP_Y";
	
	private static final String DG_UP_X="\\$\\{up_x\\}";
	private static final String DG_UP_Y="\\$\\{up_y\\}";

	
	private static final String DG_SBZMX="__WIDTH__";
	private static final String DG_SBZMY="__HEIGHT__";
	
	
	private static final String DG_RELATIVE_DOWN_X="\\$\\{relative_down_x\\}";
	private static final String DG_RELATIVE_DOWN_Y="\\$\\{relative_down_y\\}";
	
	private static final String DG_RELATIVE_UP_X="\\$\\{relative_up_x\\}";
	private static final String DG_RELATIVE_UP_Y="\\$\\{relative_up_y\\}";
	
	private static final String DG_FORMAT_URL="rcv.aiclk.com/click";
	public AdRecomReply invoke(InvokeAttribute attribute) throws AdPullException {
		// 参数转换
		AdRecomReq adreq = attribute.getAdreq();
		DianguanReq params = parseParams(attribute);
		// 调用API
		DianguanResp response = (DianguanResp) invoke(params, DianguanResp.class, adreq.getHash(), String.valueOf(adreq.getUserAdSpaceAttri().getAdSpaceId()),
				"joinDSP:dianguan");
		// 解析结果
		return parseResult(response,adreq.getUserAdSpaceAttri().getSpaceHeight(),adreq.getUserAdSpaceAttri().getSpaceWidth());
	}

	public AdRecomReply parseResult(DianguanResp resp,int height,int width) throws AdPullException {
		if (resp == null) {
			return null;
		}
		if (resp.getAds()==null||!resp.isSuccess()) {
			throw new AdPullException("ad request error, error massage:"+resp.getMessage());
			
		}
		DianguanAd ad = resp.getAds();
		
		AdRecomReply adresp = new AdRecomReply();
		adresp.setStatus(status);
		adresp.setAd_contents(dealAds(Collections.singletonList(ad), height, width));
		return adresp;
	}
	private List<AdContent>  dealAds(List<DianguanAd> ads,int height,int width) throws AdPullException{
		List<AdContent> list=new ArrayList<AdContent> ();
		
		for(DianguanAd ad:ads){
			//***********************BEGIN 常规设置*************
			AdContent content = new AdContent();
			AdMutiAction action = new AdMutiAction();
			String title = defTitle;
			String marketTitle=defTitle;
			// 内容类型1表示链接推广,2表示应用推广,3未使用 4表示电话推广
	        int acType=ACTION_WEB;
	      //***********************END 常规设置*************
	        //图片
	        List<AdImg> imgs = new ArrayList<AdImg>();
			//上报
	        Map<String, List<String>> map = new HashMap<String, List<String>>();
	        if(ad.getMaterial_type()==0){
	        	content.setIsHtmlAd(true);
	        	content.setHtmlSnippet(ad.getHtml_snippet());
	        }else if (ad.getMaterial_type()==1){
	        	DianguanNative _native=ad.getNative_material();
		        if(_native.getInteraction_type()==2){
		        	acType=ACTION_APP;
		        }
		        DianguanSnippet snippet=null;
		        if(_native.getType()==2||_native.getType()==6||_native.getType()==7){
		        	snippet=JsonUtils.toBean(_native.getImage_snippet(),DianguanSnippet.class);
		        	
		        }else if(_native.getType()==1||_native.getType()==3||_native.getType()==5){
		        	snippet=JsonUtils.toBean(_native.getText_icon_snippet(),DianguanSnippet.class);
		        }else if(_native.getType()==4){
		        	snippet=JsonUtils.toBean(_native.getVideo_snippet(),DianguanSnippet.class);
		        }
		        if(snippet==null){
		        	break;
		        }

		        if(StringUtils.isNotEmpty(snippet.getUrl())){
		      
		            AdImg img=new AdImg();
		            img.setSrc(snippet.getUrl());
		            img.setWidth(snippet.getWidth());
		            img.setHeight(snippet.getHeight());
		            imgs.add(img);
		
			    }
		        String link=snippet.getC_url();
		        if(StringUtils.isNotEmpty(link)){
	        	    String url=addSK(REPORT_TYPE_REQ,link,height,width);
		        	content.setLinkurl(url);
					action.setLinkurl(url);

		        	
		        }
		        if(StringUtils.isNotEmpty(snippet.getTitle())){
		        	title=snippet.getTitle();
		        }
		        if(StringUtils.isNotEmpty(_native.getExt())){
		        	DianguanExt ext=JsonUtils.toBean(_native.getExt(),DianguanExt.class);
			        action.setCpPackage(ext.getApppackage());
			        
		        }

		        List<String> showReport=new ArrayList<String>();

    	        if(CollectionUtils.isNotEmpty(snippet.getImp())){
    	        	for(String url:snippet.getImp()){	
    	        		showReport.add(format(REPORT_TYPE_PV,url,height,width));
    	        	}
    	        }    	       
    	        if(CollectionUtils.isNotEmpty(snippet.getVideo_imp())){
    	        	for(DianguanStepimp step:snippet.getVideo_imp()){
        	        	for(String url:step.getImp_url()){
        	        		showReport.add(format(REPORT_TYPE_PV,url,height,width));
        	        	}
    	        	}
    	        	
    	        }
    	     	map.put(SHOW, showReport);
    	        if(CollectionUtils.isNotEmpty(snippet.getClk())){
    	        	List<String> report=new ArrayList<String>();
    	        	for(String url:snippet.getClk()){
    	        		if(url.contains(DG_FORMAT_URL)){
    	        			url=this.addSK(REPORT_TYPE_CLICK,url, height, width);
    	        			report.add(url);
    	        		}else{
    	        			report.add(format(REPORT_TYPE_CLICK,url,height,width));
    	        		}
    	        		
    	        	}
    	        	map.put(CLICK, report);
    	        }
	        }else{
	        	break;
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
	private String addSK(int type,String link,int height,int width){
		String sufix=link.indexOf("?")<0?"?":"&";
		
		try {
			String tmp= link+sufix+"sk="+URLEncoder.encode("{\"down_x\":\"%%DOWNX%%\",\"down_y\":\"%%DOWNY%%\",\"slot_w\":"+width+",\"slot_h\":"+height+"}",CHARSET_CODER);
			String url=format(type,tmp,height,width);
			return url;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return link;
	}
	
	private  String format(int type,String str,int height,int width){

		if(type!=REPORT_TYPE_PV){
			str=str.replaceAll(DG_AZMX, MACRO_DOWN_X).replaceAll(DG_AZMY, MACRO_DOWN_Y)
					.replaceAll(DG_IT_CLK_PNT_DOWN_X, MACRO_DOWN_X).replaceAll(DG_IT_CLK_PNT_DOWN_Y, MACRO_DOWN_Y)
					.replaceAll(DG_DOWN_X, MACRO_DOWN_X).replaceAll(DG_DOWN_Y, MACRO_DOWN_Y)
					.replaceAll(DG_RELATIVE_DOWN_X, MACRO_DOWN_X).replaceAll(DG_RELATIVE_DOWN_Y, MACRO_DOWN_Y)
					
					.replaceAll(DG_AZCX, MACRO_UP_X).replaceAll(DG_AZCY,MACRO_UP_Y)
					.replaceAll(DG_IT_CLK_PNT_UP_X, MACRO_UP_X).replaceAll(DG_IT_CLK_PNT_UP_Y,MACRO_UP_Y)
					.replaceAll(DG_UP_X, MACRO_UP_X).replaceAll(DG_UP_Y,MACRO_UP_Y)
					.replaceAll(DG_RELATIVE_UP_X, MACRO_UP_X).replaceAll(DG_RELATIVE_UP_Y,MACRO_UP_Y);
		}
		String format= str.replaceAll(DG_SBZMY, MACRO_REL_HEIGHT).replaceAll(DG_SBZMX,MACRO_REL_WIDTH);

		return format;
	}

	public DianguanReq parseParams(InvokeAttribute attribute) throws AdPullException {
		DSPPosition posInfo = attribute.getDspPosition();
		if (StringUtils.isEmpty(posInfo.getText3())) {
			throw new AdPullException("pkg name is empty!");
		}

		AdRecomReq adreq = attribute.getAdreq();
		AdUserInfo  user=adreq.getUserinfo();
		UserAdSpaceAttri  attr=adreq.getUserAdSpaceAttri();
		DianguanReq param = new DianguanReq();
		//
		DianguanMedia media=new DianguanMedia();
		media.setType(1);
		
		
		DianguanApp app=new DianguanApp();
		app.setPackage_name(posInfo.getText3());
		app.setApp_version(adreq.getVersion());
		
		media.setApp(app);
		
		//browser
		DianguanBrowser browser=new DianguanBrowser();
		browser.setUser_agent(user.getUa());
		media.setBrowser(browser);
		param.setMedia(media);
		//
		DianguanDevice device=new DianguanDevice();
		String os = user.getOs();
		if(OS_ANDROID.equals(os)){
			
			device.setId_imei(user.getImei());
			device.setId_imsi(user.getImsi());
			device.setId_androidid(user.getAdid());
			
			device.setOs_type(1);
		}else{
			device.setId_idfa(user.getIdfa());
			device.setOs_type(2);
		}
		device.setId_mac(user.getMac());
		device.setHeight(user.getDevice_height());
		device.setWidth(user.getDevice_width());
		device.setBrand(user.getBrand_name());
		device.setModel(user.getPhonemodel());
		device.setOs_version(this.convOSVersion(user.getOsversion()));
		
		param.setDevice(device);
		
		//
		DianguanNet net=new DianguanNet();
		net.setIp(user.getClient_ip());
		net.setType(this.convertConnType(adreq.getNet()));
		param.setNetwork(net);
		
		//
		DianguanClient client=new DianguanClient();
		client.setType(3);
		client.setVersion("1.6.11");
		param.setClient(client);
		//
		DianguanAdslot adslot=new DianguanAdslot();
		adslot.setId(posInfo.getPos());
		adslot.setType(1);
		adslot.setHeight(attr.getSpaceHeight());
		adslot.setWidth(attr.getSpaceWidth());
		
		param.setAdslot(adslot);
		
		
		return param;
	}

	
	private Integer convertConnType(String net) {
		if ("wifi".equals(net)) {
			return 1;
		}
		if ("4g".equals(net)) {
			return 5;
		}
		if ("3g".equals(net)) {
			return 4;
		}
		if ("2g".equals(net)) {
			return 3;
		}
		if ("5g".equals(net)) {
			return 5;
		}
		return 2;
	}

	@Override
	public AsynAbstractTask packageTask(Parameter params, String hashCode) {
		AdProxyDianguanTask task = new AdProxyDianguanTask();
		DianguanReq param = (DianguanReq) params;

		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json;utf-8");
		task.setParam(param);
		task.setDataFormat(DataFormat.DATA_FORMAT_JSON);
		task.setInvokeType(InvokeType.INVOKE_TYPE_POST);
		task.setHashCode(hashCode);
		task.setUrl(SystemContext.getDynamicPropertyHandler().get(ProxyConstants.DIANGUAN_ONLINE_URL));
		task.setHeaders(headers);
		return task;
	}
}
