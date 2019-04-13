package com.ocean.proxy.api.service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inveno.util.CollectionUtils;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.persist.api.proxy.AdPullException;
import com.ocean.persist.api.proxy.onemob.OnemobAdPullParams;
import com.ocean.persist.api.proxy.onemob.OnemobAdPuller;
import com.ocean.persist.api.proxy.onemob.OnemobAdResponse;
import com.ocean.persist.api.proxy.onemob.OnemobDgfly;
import com.ocean.persist.model.proxy.DSPPosition;
import com.ocean.proxy.api.base.AbstractAdSupplier;
import com.ocean.proxy.thrift.entity.AdContent;
import com.ocean.proxy.thrift.entity.AdImg;
import com.ocean.proxy.thrift.entity.AdMutiAction;
import com.ocean.proxy.thrift.entity.AdRecomReply;
import com.ocean.proxy.thrift.entity.AdRecomReq;
import com.ocean.proxy.thrift.entity.AdSpaceType;
import com.ocean.proxy.thrift.entity.AdUserInfo;
import com.ocean.proxy.thrift.entity.UserAdSpaceAttri;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;


@Component(value="onemobAdSupplier")
public class OnemobAdSupplier extends AbstractAdSupplier{
    @Autowired
	private OnemobAdPuller onemobAdPuller;
	public AdRecomReply invoke(InvokeAttribute attribute) throws AdPullException {
		AdRecomReq adreq = attribute.getAdreq();
		DSPPosition positionInfo = attribute.getDspPosition();
		// 参数转换
		OnemobAdPullParams params = parseParams(adreq, positionInfo);
		// 调用API
		OnemobAdResponse response = (OnemobAdResponse)onemobAdPuller.api(params,adreq.getHash(),String.valueOf(adreq.getUserAdSpaceAttri().getAdSpaceId()));
		// 解析结果
		return parseResult(response);
	}
	
	private AdRecomReply parseResult(OnemobAdResponse response)
			throws AdPullException {
		
		if(response == null){
			return null;
		}
		OnemobDgfly dgfly=response.getCnf().getDgfly();
		if(dgfly==null){
			return null;
		}
		// 返回对象
		AdRecomReply recomReply = new AdRecomReply();
		recomReply.setStatus(status);
		
		AdContent content = new AdContent();
		AdMutiAction action = new AdMutiAction();
//		1表示落地页为普通页面(href)采用浏览器打开；
//		2表示落地页(href)为下载类app；
//		3表示落地页是下载类，但是需要从href字段解析获得，详情请看附录
		String title=defTitle;
		String marketTitle=defTitle;
		int acType=ACTION_WEB;
		
		int hrefType=0;
		if(StringUtils.isNotEmpty(dgfly.getHrefType())){//非原生广告返回字段
			hrefType = Integer.valueOf(dgfly.getHrefType());
		}
		if(hrefType>1){
			acType=ACTION_APP;
		}
		
		if(hrefType==3||dgfly.isRtp()){//需要宏替换
			action.setLinkurl_type(1);
		}
		
		if("bb_banner_app".equals(dgfly.getShow_type())){//原生广告类型
			acType=ACTION_APP;
		}
		

		
		String link="";
		if(StringUtils.isNotEmpty(dgfly.getDown_url())){//原生广告类型
			List<AdImg> imgs = new ArrayList<AdImg>();
			// 图片地址
			if(CollectionUtils.isNotEmpty(dgfly.getAd_img())){
				for(String url:dgfly.getAd_img()){
					AdImg img=new AdImg();
					img.setSrc(url);
					imgs.add(img);
				}
			}

			content.setImglist(imgs);
			if(StringUtils.isNotEmpty(dgfly.getName())){
				title=dgfly.getName();
			}
			if(StringUtils.isNotEmpty(dgfly.getDesc())){
				marketTitle=dgfly.getDesc();
			}
			link=dgfly.getDown_url();
		}else{// 只返回html的物料
			String html = dgfly.getUrl();
			try {
				html = URLDecoder.decode(html, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				// 不做其它处理
			}
			content.setHtmlSnippet(html);
			content.setIsHtmlAd(true);
			link=dgfly.getHref();
		}

		
		
		if(StringUtils.isNotEmpty(link)){//非原生广告落地页
			String sufix=link.indexOf("?")<0?"?":"&";
		
			if(dgfly.isRtp()){
				link+=sufix+"s={\"down_x\":\"%%DOWNX%%\",\"down_y\":\"%%DOWNY%%\",\"up_x\":\"%%UPX%%\",\"up_y\":\"%%UPY%%\"}";
				
			}
			if(dgfly.isRtp1()){
				link=format(link);
			}
			content.setLinkurl(link);
			action.setLinkurl(link);
		}


		
		content.setMutiAction(Collections.singletonList(action));
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		// 点击监测
		List<String> click = dgfly.getC_rpt();
		if(click == null){
			click = new ArrayList<String>();
		}
		if(CollectionUtils.isNotEmpty(dgfly.getD_rpt())){//开始下载
			if(hrefType==3){//需要解析herf来获取真实的落地页
				for(String url:dgfly.getD_rpt()){
					String sufix=url.indexOf("?")<0?"?":"&";
					click.add(format(url+sufix+"clickid=%%CLICKID%%"));
				}
			}else{
				click.addAll(dgfly.getD_rpt());
			}

		}
		map.put(CLICK, click);
		// 曝光监测
		List<String> show = dgfly.getS_rpt();
		if(show == null){
			show = new ArrayList<String>();
		}
		map.put(SHOW, show);
		
//		SHOW, CLICK, OPEN, DOWNLOAD, INSTALL, ACTIVE
		List<String> download = dgfly.getDc_rpt();
		if(CollectionUtils.isNotEmpty(download)){
			if(hrefType==3){//需要解析herf来获取真实的落地页
				for(int i=0;i<download.size();i++){
					String sufix=download.get(i).indexOf("?")<0?"?":"&";
					download.set(i,format(download.get(i)+sufix+"clickid=%%CLICKID%%"));
				}
			}
			map.put(DOWNLOAD, download);
			acType=ACTION_APP;
		}
		List<String> install = dgfly.getI_rpt();
		if(CollectionUtils.isNotEmpty(install)){
			if(hrefType==3){
				for(int i=0;i<install.size();i++){
					String sufix=install.get(i).indexOf("?")<0?"?":"&";
					install.set(i,install.get(i)+sufix+"clickid=%%CLICKID%%");
				}
			}
			map.put(INSTALL, install);
			acType=ACTION_APP;
		}
		List<String> active = dgfly.getA_rpt();
		if(CollectionUtils.isNotEmpty(active)){
			if(hrefType==3){
				for(int i=0;i<active.size();i++){
					String sufix=active.get(i).indexOf("?")<0?"?":"&";
					active.set(i,active.get(i)+sufix+"clickid=%%CLICKID%%");
				}
			}
			map.put(ACTIVE, active);
			acType=ACTION_APP;
		}
		
		content.setMarketTitle(marketTitle);
		content.setGuideTitle(title);
		action.setGuideTitle(title);
		action.setType(acType);
		content.setThirdReportLinks(map);
		recomReply.setAd_contents(Collections.singletonList(content));
		
		return recomReply;
	}
	private  String format(String str){
		String format= str.replaceAll("SZST_DX", "%%DOWNX%%").replaceAll("SZST_DY", "%%DOWNY%%")
				.replaceAll("SZST_UX", "%%UPX%%").replaceAll("SZST_UY", "%%UPY%%");

		return format;
	}
	private OnemobAdPullParams parseParams(AdRecomReq adreq, 
			DSPPosition positionInfo) throws AdPullException {
		
		UserAdSpaceAttri attri = adreq.getUserAdSpaceAttri();
		AdUserInfo userInfo = adreq.getUserinfo();
		OnemobAdPullParams params = new OnemobAdPullParams();
		if(AdSpaceType.OPENING==attri.getSpaceType()){
			params.setAction("app");
		}else if(AdSpaceType.INFOFLOW==attri.getSpaceType()||AdSpaceType.BANNER==attri.getSpaceType()||AdSpaceType.INTERSTITIAL==attri.getSpaceType()){
			params.setAction("us");
		}else{
			throw new AdPullException("目前不支持该类型广告");
		}
		
		
		params.setCp(positionInfo.getPos());
		params.setPan("api74tang");

		String adid = userInfo.getAdid();
		if(StringUtils.isEmpty(adid)){
			adid = userInfo.getImei();
		}
		params.setDid(userInfo.getImei());
		
		String imsi = userInfo.getImsi(); 
		if(StringUtils.isNotEmpty(imsi)){
			params.setImsi(imsi);
		}
		
		params.setAid(adid);
		params.setMac(this.converMac(userInfo.getMac()));
		
		params.setRel(this.convOSVersion(userInfo.getOsversion()));
		params.setBrnd(userInfo.getBrand_name());
		params.setMdl(encode(userInfo.getPhonemodel()));
		String dm = userInfo.getDevice_width() + "x" + userInfo.getDevice_height();
		params.setDm(dm);
		
		params.setClient_ip(userInfo.getClient_ip());
		try {
			params.setClient_ua(URLEncoder.encode(userInfo.getUa(),"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AdPullException("ua url encode error"+e.getMessage());
		}
		
		
		params.setNt(adreq.getNet());
        String no=mobiles.get(userInfo.getMobile());
        if(NETWORK_WIFI.equals(adreq.getNet())){
        	params.setN("WIFI");
        }else{
        	params.setN("Mobile");
        }
        if(StringUtils.isNotEmpty(no)){
        	params.setNo(no);
        }else{
        	params.setNo("46000");
        }
        params.setAn(positionInfo.getText2());
	

		return params;
	}

	public OnemobAdPuller getOnemobAdPuller() {
		return onemobAdPuller;
	}

	public void setOnemobAdPuller(OnemobAdPuller onemobAdPuller) {
		this.onemobAdPuller = onemobAdPuller;
	}

}
