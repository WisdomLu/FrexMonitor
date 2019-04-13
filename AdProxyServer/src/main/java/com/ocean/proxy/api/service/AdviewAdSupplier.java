package com.ocean.proxy.api.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inveno.util.CollectionUtils;
import com.ocean.core.common.system.ErrorCode;
import com.ocean.core.common.system.SystemContext;
import com.ocean.persist.api.proxy.AdPullException;
import com.ocean.persist.api.proxy.adview.AdviewAdContent;
import com.ocean.persist.api.proxy.adview.AdviewAdPullParams;
import com.ocean.persist.api.proxy.adview.AdviewAdPuller;
import com.ocean.persist.api.proxy.adview.AdviewAdResponse;
import com.ocean.persist.api.proxy.adview.AdviewImage;
import com.ocean.persist.api.proxy.adview.AdviewNative;
import com.ocean.persist.common.ProxyConstants;
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
@Component(value="adviewAdSupplier")
public class AdviewAdSupplier extends AbstractAdSupplier{
    @Autowired
	private AdviewAdPuller adviewAdPuller;
	private static Map<AdSpaceType, Integer> pt = new HashMap<AdSpaceType, Integer>(6);
	private static Map<AdSpaceType, Integer> at = new HashMap<AdSpaceType, Integer>();
	private static Map<Integer, Integer> actType = new HashMap<Integer, Integer> ();
	private static final String AV_ABSOLUTE_COORD="\\{ABSOLUTE_COORD\\}";
	private static final String AV_RELATIVE_COORD="\\{RELATIVE_COORD\\}";
	private static final String AV_UUID="\\{UUID\\}";
	private static final String AV_LATITUDE="\\{LATITUDE\\}";
	private static final String AV_LONGITUDE="\\{LONGITUDE\\}";
	private  static final String AV_COORDINATE_REPLACEMENT="{\"down_x\":\"%%DOWNX%%\",\"down_y\":\"%%DOWNY%%\",\"up_x\":\"%%UPX%%\",\"up_y\":\"%%UPY%%\"}";
	
	private  static final String AV_DOWN_X="__DOWN_X__";
	private  static final String AV_DOWN_Y="__DOWN_Y__";
	private  static final String AV_UP_X="__UP_X__";
	private  static final String AV_UP_Y="__UP_Y__";
	private  static final String AV_ACTION_ID="__ACTION_ID__";
	private  static final String AV_CLICK_ID="__CLICK_ID__";
	static{
		pt.put(AdSpaceType.BANNER, 0);
		pt.put(AdSpaceType.INTERSTITIAL, 1);
		pt.put(AdSpaceType.OPENING, 4);
		pt.put(AdSpaceType.INFOFLOW, 6);
		
		at.put(AdSpaceType.BANNER, 0);
		at.put(AdSpaceType.OPENING, 5);
		at.put(AdSpaceType.INTERSTITIAL, 3);
		at.put(AdSpaceType.INFOFLOW, 8);
		
		actType.put(1, ACTION_WEB);
		actType.put(2, ACTION_APP);
		actType.put(4, ACTION_WEB);
		actType.put(8, ACTION_WEB);
		actType.put(16, ACTION_WEB);
		actType.put(32, ACTION_CALL);
		actType.put(64, ACTION_VIDEO);
	}
	
	public AdRecomReply invoke(InvokeAttribute attribute) throws AdPullException {
		
		AdRecomReq adreq = attribute.getAdreq();
		DSPPosition positionInfo = attribute.getDspPosition();
		// 参数转换
		AdviewAdPullParams params = parseParams(adreq, positionInfo);
		// 调用API
		AdviewAdResponse response = (AdviewAdResponse) adviewAdPuller.api(params,adreq.getHash(),String.valueOf(adreq.getUserAdSpaceAttri().getAdSpaceId()));
		return parseResult(response,adreq );
	}
	
	private AdRecomReply parseResult(AdviewAdResponse response, AdRecomReq adreq)
			throws AdPullException {
		
		if(response == null){
			return null;
		}
		// 返回对象
		AdRecomReply recomReply = new AdRecomReply();
		recomReply.setStatus(status);
		
		List<AdviewAdContent> ads = response.getAd();
		if(response.getRes()==0){
			throw new AdPullException("joinDSP:av ad request error code:"+response.getRes()+",return massage:"+response.getMg());
		}
		if(ads == null || ads.isEmpty()){
			return null;
		}
		String uuid="";
		AdUserInfo user=adreq.getUserinfo();
		if(OS_IOS.equals(user.getOs())){
			// ios 必填信息
			uuid=user.getIdfa();
		}else{
			uuid=user.getImei();
		}
		recomReply.setAd_contents(this.dealAds(response,uuid, user.getLon(),user.getLat()));
		return recomReply;
	}
	private List<AdContent>  dealAds(AdviewAdResponse resp,String uuid,String lat,String lon){
		List<AdContent> list=new ArrayList<AdContent> ();
		for(AdviewAdContent ad:resp.getAd()){

			//----------------BEGIN 常规选项--------------
			AdContent content = new AdContent();
			AdMutiAction action = new AdMutiAction();
			content.setMarketTitle(defTitle);
			content.setGuideTitle(defTitle);
			action.setGuideTitle(defTitle);
			
			//----------------END 常规选项--------------
//			0=>图片横幅广告 1=>混合文字链 2=>图形文字链（带行为图标） 3=>图片插屏广告
//			4=>html 广告 5=>开屏纯图片 8=>原生广告 100=>富媒体插屏
			
			int at = ad.getAt();
			if((at==4||at==10)&&StringUtils.isNotEmpty(ad.getXs())){//h5广告
				content.setIsHtmlAd(true);
				content.setHtmlSnippet(ad.getXs());
			}
			String linkurl = ad.getAl();
			if(ad.getAltype()==1){
				linkurl=this.format(REPORT_TYPE_REQ, linkurl, uuid, lon, lat);
				content.setLinkurl(linkurl);
				action.setLinkurl(linkurl);
				action.setLinkurl_type(1);
			}else{
				content.setLinkurl(linkurl);
				action.setLinkurl(linkurl);
			}

	        if(StringUtils.isNotEmpty(ad.getDl())){
	        	action.setActiveUri(ad.getDl());
	        }
			// 上报监测
			Map<String, List<String>> map = new HashMap<String, List<String>>();
			
			List<String> click= new ArrayList<String>();
			for(String url:ad.getEc()){
				click.add(format(REPORT_TYPE_CLICK,url, uuid,lat, lon));
			}
			map.put(CLICK, click);
			
			List<String> show = new ArrayList<String>();
			Map<String, List<String>> es = ad.getEs();
			if(es != null && !es.isEmpty()){
				Set<String> keys = es.keySet();
				for (String key : keys) {
					for(String url:es.get(key)){
						show.add(format(REPORT_TYPE_PV,url, uuid,lat, lon));
					}
					
				}
			}
			map.put(SHOW, show);
    		if(CollectionUtils.isNotEmpty(ad.getSurl())){
    			List<String> report=new ArrayList<String>();
    			for(String url:ad.getSurl()){
    				report.add(format(REPORT_TYPE_DOWNLOAD_START,url, uuid,lat, lon));
    			}
    			if(ad.getAct()==2&&ad.getAltype()==1){
    				report.add(format(REPORT_TYPE_DOWNLOAD_START,ad.getGdt_conversion_link(), uuid,lat, lon));
    			}
    			map.put(START_DOWN, report);
    		}
    		if(CollectionUtils.isNotEmpty(ad.getFurl())){
    			List<String> report=new ArrayList<String>();
    			for(String url:ad.getSurl()){
    				report.add(format(REPORT_TYPE_DOWNLOAD,url, uuid,lat, lon));
    			}
    			if(ad.getAct()==2&&ad.getAltype()==1){
    				report.add(format(REPORT_TYPE_DOWNLOAD,ad.getGdt_conversion_link(), uuid,lat, lon));
    			}
    			map.put(DOWNLOAD, report);
    		}
    		if(CollectionUtils.isNotEmpty(ad.getIurl())){
    			List<String> report=new ArrayList<String>();
    			for(String url:ad.getSurl()){
    				report.add(format(REPORT_TYPE_INSTALL,url, uuid,lat, lon));
    			}
    			if(ad.getAct()==2&&ad.getAltype()==1){
    				report.add(format(REPORT_TYPE_INSTALL,ad.getGdt_conversion_link(), uuid,lat, lon));
    			}
    			map.put(INSTALL, report);
    		}
    		if(CollectionUtils.isNotEmpty(ad.getOurl())){
    			List<String> report=new ArrayList<String>();
    			for(String url:ad.getSurl()){
    				report.add(format(REPORT_TYPE_ACTIVE,url, uuid,lat, lon));
    			}
    			map.put(ACTIVE, report);
    		}
			
			
			content.setThirdReportLinks(map);

			//设置交互类型
			Integer type=actType.get(ad.getAct());
			action.setType(type==null?ACTION_WEB:type);
			
			if(StringUtils.isNotEmpty(ad.getAti())){
				content.setMarketTitle(ad.getAti());
				content.setGuideTitle(ad.getAti());
				action.setGuideTitle(ad.getAti());
			}
			if(StringUtils.isNotEmpty(ad.getAst())){
				if(StringUtils.isEmpty(content.getMarketTitle())){
					content.setMarketTitle(ad.getAst());
					action.setGuideTitle(ad.getAst());
				}
				content.setGuideTitle(ad.getAst());
				
			}
			// 广告尺寸 	320x50 
			String as = ad.getAs();
			Integer w = null, h = null;
			if(StringUtils.isNotEmpty(as) && as.indexOf("x") > 0){
				String[] ass = as.split("x");
				w = Integer.valueOf(ass[0]);
				h = Integer.valueOf(ass[1]);
			}
			
			
			// 广告图片
			List<AdImg> imgs = new ArrayList<AdImg>();
			// 图片广告时，图片的 URL 地址 
			List<String> api = ad.getApi();
			if(api != null){
				for(String url : api){
					AdImg img = new AdImg();
					img.setSrc(url);
					if(StringUtils.isNotEmpty(as)){
						img.setWidth(w);
						img.setHeight(h);
					}
					imgs.add(img);
				}
			}
			if(StringUtils.isNotEmpty(ad.getAdLogo())){
				AdImg logo=  new AdImg();
		        logo.setSrc(ad.getAdLogo());
		        content.setLogoImg(logo);
			}

			//图标
			if(StringUtils.isNotEmpty(ad.getAic())){
				action.setCpIcon(ad.getAic());
			}

			if(at == 8||at==9){
				AdviewNative nat = ad.getNat();
				List<AdviewImage> images = nat.getImages();
				if(images != null){
					for (AdviewImage adviewImage : images) {
						AdImg img = new AdImg();
						img.setSrc(adviewImage.getUrl());
						img.setWidth(adviewImage.getW());
						img.setHeight(adviewImage.getH());
						imgs.add(img);
					}
				}
				
				AdviewImage icon = nat.getIcon();
				if(icon != null){
					action.setCpIcon(icon.getUrl());
					if(imgs.isEmpty()){
						AdImg img = new AdImg();
						img.setSrc(icon.getUrl());
						img.setWidth(icon.getW());
						img.setHeight(icon.getH());
						imgs.add(img);
					}
				}
				AdviewImage logo = nat.getLogo();
				if(logo != null){
					AdImg img = new AdImg();
					img.setSrc(logo.getUrl());
					img.setWidth(logo.getW());
					img.setHeight(logo.getH());
					content.setLogoImg(img);
				}
				if(StringUtils.isNotEmpty(nat.getTitle())){
					content.setGuideTitle(nat.getTitle());
					action.setGuideTitle(nat.getTitle());

				}
				if(StringUtils.isNotEmpty(nat.getDesc())){
					content.setMarketTitle(nat.getDesc());
					content.setContent(nat.getDesc());
				}
				
			}
			// 图片地址
			content.setImglist(imgs);
			content.setMutiAction(Collections.singletonList(action));
			list.add(content);
		}
		return list;
	}
	private  String format(int type,String str,String uuid,	String lon, String lat){
		if(type!=REPORT_TYPE_PV){
			str=str.replaceAll(AV_DOWN_X, MACRO_DOWN_X).replaceAll(AV_DOWN_Y, MACRO_DOWN_Y)	
			       .replaceAll(AV_UP_X, MACRO_UP_X).replaceAll(AV_UP_Y, MACRO_UP_Y);	
		}else{//设置默认值
			str=str.replaceAll(AV_DOWN_X, MACRO_CLICK_DEFUALT).replaceAll(AV_DOWN_Y, MACRO_CLICK_DEFUALT)
			       .replaceAll(AV_UP_X, MACRO_CLICK_DEFUALT).replaceAll(AV_UP_Y, MACRO_CLICK_DEFUALT);	
		}
		str=str.replaceAll(AV_ABSOLUTE_COORD, AV_COORDINATE_REPLACEMENT).replaceAll(AV_RELATIVE_COORD, AV_COORDINATE_REPLACEMENT)		
			   
				  .replaceAll(AV_LATITUDE,lat).replaceAll(AV_LONGITUDE, lon)
		          .replaceAll(AV_UUID, uuid)
				  .replaceAll(AV_CLICK_ID, MACRO_CLICK_ID);
		if(type==REPORT_TYPE_DOWNLOAD_START){
			str=str.replaceAll(AV_ACTION_ID, "5");
		}else if(type==REPORT_TYPE_DOWNLOAD){
			str=str.replaceAll(AV_ACTION_ID, "7");
		}else if(type==REPORT_TYPE_INSTALL){
			str=str.replaceAll(AV_ACTION_ID, "5");
		}
		return str;
	}

	private AdviewAdPullParams parseParams(AdRecomReq adreq, DSPPosition pos) throws AdPullException{
		if(StringUtils.isEmpty(pos.getText1())||StringUtils.isEmpty(pos.getText2())){
			throw new AdPullException(ErrorCode.PARAM_ERROR,"App id or App package name is empty!");
		}
		UserAdSpaceAttri attri = adreq.getUserAdSpaceAttri();
		AdUserInfo userInfo = adreq.getUserinfo();
		AdviewAdPullParams params = new AdviewAdPullParams();
		params.setVer("2.5");
		params.setN(adreq.getResult_num());
		params.setAppid(pos.getText1());
		params.setPt(pt.get(attri.getSpaceType()));
		params.setHtml5(attri.getH5type()==1?1:0);//h5type 0：h5和h5都可以：1：只拉h5; 2:只拉非h5
		params.setSecure(0);
		/* 
		 * 0=>任意(Native或html5)
	       1=>html5广告(非h5我们会转成h5广告下发)
		 * 
		 * */
	    params.setSupmacro(1);
	    params.setSupGdtUrl(1);
	    params.setPosId(pos.getPos());
	    params.setPack(pos.getText2());
		params.setW(attri.getSpaceWidth());
		params.setH(attri.getSpaceHeight());
		//0=>手机,1=>平板
        params.setTab(0);
		params.setIp(userInfo.getClient_ip());
		params.setBdr(userInfo.getOsversion());
		params.setTp(encode(userInfo.getPhonemodel()));
		params.setBdr(userInfo.getBrand_name());
		params.setUa(encode(userInfo.getUa()));
		params.setSh(userInfo.getDevice_height());
		params.setSw(userInfo.getDevice_width());
		if(StringUtils.isNotEmpty(userInfo.getDip())){
			params.setDeny(Double.parseDouble(userInfo.getDip()));
		}else{
			params.setDeny(2.0);
		}
		params.setAt(at.get(attri.getSpaceType()));
		String osCode = userInfo.getOs();

		// 如果是ios
		int os = 0;
		if(OS_IOS.equals(osCode)){
			// ios 必填信息
			params.setIdfa(userInfo.getIdfa());
			params.setSn(userInfo.getIdfa());
			params.setIdfv(userInfo.getIdfa());
			os = 1;
		}else if(OS_ANDROID.equals(osCode)){
			params.setAndid(userInfo.getAdid());
			// 0=>支持安卓设备传IMEI和AndroidId 1=>支持安卓设备传Google Advertising Id
			params.setAndt(0);
			params.setSn(userInfo.getImei());
		}
		params.setOs(os);
		params.setN(adreq.getResult_num());// 返回的广告条数
		params.setMc(userInfo.getMac());
		params.setNt(adreq.getNet());
		
		if(StringUtils.isNotEmpty(userInfo.getMobile())){
			// 46000、46002、46007=>中国移动 
			// 46001、46006=>中国联通 46003、46005=>中国电信没有就填""字符串
	    	String carrier=mobiles.get(userInfo.getMobile());
	    	params.setNop(carrier==null?"":carrier);
		}else{
			params.setNop("");
		}
		if(StringUtils.isNotEmpty(userInfo.getLat())&&StringUtils.isNotEmpty(userInfo.getLon())){
			params.setLat(Double.parseDouble(userInfo.getLat()));
			params.setLon(Double.parseDouble(userInfo.getLon()));
			params.setLocType(2);
		}
		params.setImsi(userInfo.getImsi());
		if(userInfo.isSetCid()&&userInfo.isSetLac()){
			params.setLac(String.valueOf(userInfo.getLac()));
			params.setCid(String.valueOf(userInfo.getCid()));
		}
		// 0=>正式模式 1=>测试模式
		params.setTm(SystemContext.getDynamicPropertyHandler().getInt(ProxyConstants.ADVIEW_IS_TEST,0));
		params.setTime(System.currentTimeMillis());

		//生成token,MD5(appid+sn+os+nop+pack+time+secretKey) 
		StringBuilder md5str = new StringBuilder();
		md5str.append(params.getAppid());
		md5str.append(params.getSn());
		md5str.append(params.getOs());
		md5str.append(params.getNop());
		md5str.append(params.getPack());
		md5str.append(params.getTime());
		md5str.append(SystemContext.getDynamicPropertyHandler().get(ProxyConstants.ADVIEW_SECRET));
		String token = DigestUtils.md5Hex(md5str.toString());
		params.setToken(token);
		params.setBaseFloor(pos.getSettlementPrice() * 100);
		
		return params;
	}

	public AdviewAdPuller getAdviewAdPuller() {
		return adviewAdPuller;
	}

	public void setAdviewAdPuller(AdviewAdPuller adviewAdPuller) {
		this.adviewAdPuller = adviewAdPuller;
	}

}
