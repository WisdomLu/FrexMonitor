package com.ocean.proxy.api.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ocean.core.common.JsonUtils;
import com.ocean.core.common.system.SystemContext;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.persist.api.proxy.AdPullException;

import com.ocean.persist.api.proxy.mex.MexAdPullParams;
import com.ocean.persist.api.proxy.mex.MexAdPuller;
import com.ocean.persist.api.proxy.mex.MexAdResponse;
import com.ocean.persist.api.proxy.mex.MexAdm;
import com.ocean.persist.api.proxy.mex.MexAsset;
import com.ocean.persist.api.proxy.mex.MexBanner;
import com.ocean.persist.api.proxy.mex.MexDevice;
import com.ocean.persist.api.proxy.mex.MexGeo;
import com.ocean.persist.api.proxy.mex.MexImage;
import com.ocean.persist.api.proxy.mex.MexImp;
import com.ocean.persist.api.proxy.mex.MexNativeReq;
import com.ocean.persist.api.proxy.mex.MexNativeRes;
import com.ocean.persist.common.ProxyConstants;
import com.ocean.persist.model.proxy.DSPPosition;
import com.ocean.proxy.api.base.AbstractAdSupplier;
import com.ocean.proxy.api.helper.InvenoIdGenerator;
import com.ocean.proxy.thrift.entity.AdContent;
import com.ocean.proxy.thrift.entity.AdImg;
import com.ocean.proxy.thrift.entity.AdMutiAction;
import com.ocean.proxy.thrift.entity.AdRecomReply;
import com.ocean.proxy.thrift.entity.AdRecomReq;
import com.ocean.proxy.thrift.entity.AdSpaceType;
import com.ocean.proxy.thrift.entity.AdUserInfo;
import com.ocean.proxy.thrift.entity.UserAdSpaceAttri;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;

@Component(value="mexAdSupplier")
public class MexAdSupplier  extends AbstractAdSupplier{
    @Autowired
	private   MexAdPuller mexAdPuller ;
	protected static final Map<String, String> mobiles = new HashMap<String, String>(3);
	static{
		mobiles.put("CMCC","China Mobile");
		mobiles.put("CUCC","China Uniom");
		mobiles.put("CTCC","China Telecom");

	}

	public AdRecomReply invoke(InvokeAttribute attribute) throws AdPullException {
		
		AdRecomReq adreq = attribute.getAdreq();
		DSPPosition positionInfo = attribute.getDspPosition();
		int js=adreq.getUserAdSpaceAttri().getH5type()==1?1:0;
		// 参数转换
		MexAdPullParams params = parseParams(adreq, positionInfo, js);
		// 调用API
		MexAdResponse response = (MexAdResponse)mexAdPuller.api(params,adreq.getHash(),String.valueOf(adreq.getUserAdSpaceAttri().getAdSpaceId()));
	
		return parseResult(response, js);
	}
	
	private AdRecomReply parseResult(MexAdResponse response, int js)
			throws AdPullException {
		if(response == null){
			return null;
		}
		// 返回对象
		AdRecomReply recomReply = new AdRecomReply();
		recomReply.setStatus(status);
		
		AdContent content = new AdContent();
		AdMutiAction action = new AdMutiAction();
		/*
		 *   点击事件类型（目前仅支持下载应用）：
			0：打开网页；
			1：下载应用；
		 * */
		if("1".equals(response.getCta())){
			action.setType(ACTION_APP);
		}else{
			action.setType(ACTION_WEB);
		}
		String title=defTitle;
		List<AdImg> imgs = new ArrayList<AdImg>();
		String adm = response.getAdm();
		String linkurl = "";
		if(StringUtils.isNotEmpty(adm)){
			if(js==0){//json串
				MexNativeRes nat=JsonUtils.toBean(adm, MexNativeRes.class);
				if(StringUtils.isNotEmpty(nat.getTitile())){
					title=nat.getTitile();
				}
				for(String url:nat.getImgurl()){
					AdImg img = new AdImg();
					img.setSrc(url);
					imgs.add(img);
					
				}
				if(StringUtils.isNotEmpty(nat.getCurl())){
					linkurl=nat.getCurl();
				}
			}else if(js==1){//h5广告
				content.setIsHtmlAd(true);
				content.setHtmlSnippet(adm);
			}

		}

		if(StringUtils.isNotEmpty(response.getCreative_url())){
			AdImg img = new AdImg();
			img.setSrc(response.getCreative_url());
			imgs.add(img);
		}
        if(StringUtils.isNotEmpty(response.getCurl())&&StringUtils.isEmpty(action.getLinkurl())){
        	linkurl=response.getCurl();
        }
		action.setLinkurl(linkurl);
    	content.setLinkurl(linkurl);

		Map<String, List<String>> map = new HashMap<String, List<String>>();
		// 点击监测
		List<String> click = response.getClick_url();
		if(click == null){
			click = new ArrayList<String>();
		}
		map.put(CLICK, click);
		
		// 曝光监测
		List<String> show = response.getImp_url();
		if(show == null){
			show = new ArrayList<String>();
		}
		map.put(SHOW, show);
		// 图片地址
		content.setImglist(imgs);
		content.setMarketTitle(title);
		content.setGuideTitle(title);
		action.setGuideTitle(title);
		
		
		content.setMutiAction(Collections.singletonList(action));
		content.setThirdReportLinks(map);
		recomReply.setAd_contents(Collections.singletonList(content));
		
		return recomReply;
	}
	
	private MexAdPullParams parseParams(AdRecomReq adreq, DSPPosition positionInfo, int js) throws AdPullException{
		if(StringUtils.isEmpty(positionInfo.getText1())||StringUtils.isEmpty(positionInfo.getText3())){
		    throw new 	AdPullException("app id or package name is empty!");
		}
		UserAdSpaceAttri attri = adreq.getUserAdSpaceAttri();
		AdUserInfo userInfo = adreq.getUserinfo();
		MexAdPullParams params = new MexAdPullParams();
		params.setApiver("2.0");
		params.setAppid(positionInfo.getText1());
		// 广告位
		params.setAdposid(positionInfo.getPos());
		params.setBundleid(positionInfo.getText3());
		// 开发者id
		params.setPublisherid("59c387d8dfe7cf01007347a9");
		params.setToken("62120f1320cd0ef7953a3188576805a4");
		// 设备信息
		MexDevice device = new MexDevice();
		
		device.setOsv(this.convOSVersion(userInfo.getOsversion()));
		
		device.setUa(userInfo.getUa());
		device.setIp(userInfo.getClient_ip());
		device.setDevicetype("phone");
		device.setMac(this.converMac(userInfo.getMac()));
		device.setModel(userInfo.getPhonemodel());
	    if(NETWORK_WIFI.equals(adreq.getNet())){
	    	device.setConnectiontype(NETWORK_WIFI);
	    } else if(StringUtils.isNotEmpty(adreq.getNet())){
	    	 device.setConnectiontype(adreq.getNet().toUpperCase());
	    }else{
	    	device.setConnectiontype("unknown");
	    }
		if(userInfo.getOs().equalsIgnoreCase(OS_IOS)){
			device.setOs("iOS");
			device.setIdfa(userInfo.getIdfa());
		}
		else{
			device.setOs("Android");
			device.setImei(userInfo.getImei());
			if(StringUtils.isNotEmpty(userInfo.getImsi())&&!userInfo.getImsi().equals(userInfo.getImei())){
				device.setImsi(userInfo.getImsi());
			}
	
			if(StringUtils.isNotEmpty(userInfo.getAaid())){
				device.setAdid(userInfo.getAaid());
			}
			

			if(StringUtils.isNotEmpty(userInfo.getAdid())){
				device.setAndroidid(userInfo.getAdid());
			}
			
		}
		String make = userInfo.getBrand_name();
		device.setMake(make);
		
		Integer dw = userInfo.getDevice_width();
		device.setHww(dw);
		
		Integer dh = userInfo.getDevice_height();
		device.setHwh(dh);
 
		String car=mobiles.get(userInfo.getMobile());
		device.setCarrier(StringUtils.isEmpty(car)?"unknown":car);
		if(StringUtils.isNotEmpty(userInfo.getLat())&&StringUtils.isNotEmpty(userInfo.getLon())){
			MexGeo geo = new MexGeo();
			
			geo.setLat(Float.parseFloat(userInfo.getLat()));
			geo.setLon(Float.parseFloat(userInfo.getLon()));
			device.setGeo(geo);
		}
		params.setDevice(device);
		
		// 展示信息
		MexImp imp = new MexImp();
		imp.setJs(js);
		// native广告
		if(positionInfo.getPosType()==4){
			MexNativeReq nat=new MexNativeReq();
			nat.setRequired(Arrays.asList("1","3"));
			
			MexImage img=new MexImage();
			img.setW(attri.getSpaceWidth());
			img.setH(attri.getSpaceHeight());
			nat.setType(3);
			imp.setNative1(nat);
		}
		// banner广告
		else{
			MexBanner  banner = new MexBanner();
			banner.setH(attri.getSpaceHeight());
			banner.setW(attri.getSpaceWidth());
			List<String> list = new ArrayList<String>();
			list.add("application/x-shockwave-flash");
			list.add("image/jpg");
			list.add("image/gif");
			banner.setMimes(list);
			imp.setBanner(banner);
		}
		params.setImp(imp);
		return params;
	}


	public MexAdPuller getMexAdPuller() {
		return mexAdPuller;
	}

	public void setMexAdPuller(MexAdPuller mexAdPuller) {
		this.mexAdPuller = mexAdPuller;
	}

}
