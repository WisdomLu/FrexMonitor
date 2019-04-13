package com.ocean.proxy.api.service;

import java.io.UnsupportedEncodingException;
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
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
import com.ocean.persist.api.proxy.AdPullException;
import com.ocean.persist.api.proxy.diankai.DiankaiAd;
import com.ocean.persist.api.proxy.diankai.DiankaiAdPullResponse;
import com.ocean.persist.api.proxy.jugao.JugaoAd;
import com.ocean.persist.api.proxy.jugao.JugaoAdPullParams;
import com.ocean.persist.api.proxy.jugao.JugaoAdPuller;
import com.ocean.persist.api.proxy.jugao.JugaoAdResponse;
import com.ocean.persist.api.proxy.speed.SpeedAd;
import com.ocean.persist.api.proxy.speed.SpeedAdPullParams;
import com.ocean.persist.api.proxy.speed.SpeedAdResponse;
import com.ocean.persist.api.proxy.speed.SpeedCreative;
import com.ocean.persist.api.proxy.zhangyou.ZhangYouVideo;
import com.ocean.persist.model.proxy.DSPPosition;
import com.ocean.proxy.api.base.AbstractAdSupplier;
import com.ocean.proxy.api.base.BaseAdSupplier.InvokeAttribute;
import com.ocean.proxy.thrift.entity.AdContent;
import com.ocean.proxy.thrift.entity.AdImg;
import com.ocean.proxy.thrift.entity.AdMutiAction;
import com.ocean.proxy.thrift.entity.AdRecomReply;
import com.ocean.proxy.thrift.entity.AdRecomReq;
import com.ocean.proxy.thrift.entity.AdSpaceType;
import com.ocean.proxy.thrift.entity.AdUserInfo;
import com.ocean.proxy.thrift.entity.AdVid;
import com.ocean.proxy.thrift.entity.ExpectedMarketType;
import com.ocean.proxy.thrift.entity.UserAdSpaceAttri;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年4月24日 
      @version 1.0 
 */

@Component(value="jugaoAdSupplier")
public class JugaoAdSupplier    extends AbstractAdSupplier{
	@Autowired
    private JugaoAdPuller jugaoAdPuller;
	// 1.BANNER(横幅) 4.INITIALIZATION(开屏) 5.INSERT(插屏) 9.NATIVE(信息流)
	private static final Map<AdSpaceType, Integer> adtype = new HashMap<AdSpaceType, Integer>();
	static{
		adtype.put(AdSpaceType.BANNER, 1);
		adtype.put(AdSpaceType.OPENING, 2);
		adtype.put(AdSpaceType.INFOFLOW, 3);
		adtype.put(AdSpaceType.INTERSTITIAL, 4);
	}
	protected static final Map<String, Integer> jugaomobiles = new HashMap<String, Integer>(4);

	private static final Map<String, Integer> conn = new HashMap<String, Integer>();
	static{
		jugaomobiles.put("CMCC", 1);
		jugaomobiles.put("CUCC", 2);
		jugaomobiles.put("CTCC", 3);
		
		conn.put(NETWORK_2G, 1);
		conn.put(NETWORK_3G, 2);
		conn.put(NETWORK_4G, 3);
		conn.put(NETWORK_WIFI, 4);
	}
	public AdRecomReply invoke(InvokeAttribute attribute)
			throws AdPullException {
		// TODO Auto-generated method stub
		AdRecomReq adreq = attribute.getAdreq();
		// 参数转换
		JugaoAdPullParams params = parseParams(attribute);
		// 调用API
		JugaoAdResponse response = (JugaoAdResponse) jugaoAdPuller.api(params,adreq.getHash(),String.valueOf(adreq.getUserAdSpaceAttri().getAdSpaceId()));
		// 解析结果
		return parseResult(response,attribute.getDspPosition().getPosType());
	}

	private JugaoAdPullParams parseParams(InvokeAttribute attribute) throws AdPullException {
		AdRecomReq adreq = attribute.getAdreq();
		UserAdSpaceAttri attri=adreq.getUserAdSpaceAttri();
		AdUserInfo user=adreq.getUserinfo();
		DSPPosition positionInfo = attribute.getDspPosition();
		JugaoAdPullParams param=new JugaoAdPullParams();
		param.setAdid(positionInfo.getPos());
		Integer adType=adtype.get(AdSpaceType.findByValue(positionInfo.getPosType()));
		if(adType==null){
			param.setAdtype(1);
			if(attri.isSetExpectedMarketTypes()&&attri.getExpectedMarketTypes().contains(ExpectedMarketType.VIDEO)){
				param.setAdtype(6);
			}
			
		}else{
			param.setAdtype(adType);
		}
		param.setWidth(attri.getSpaceWidth());
		param.setHeight(attri.getSpaceHeight());
		if(StringUtils.isEmpty(positionInfo.getText2())){
			throw new AdPullException("app package name is empty!");
		}
		param.setPkgname(positionInfo.getText2());
		try {
			
			param.setAppname(URLEncoder.encode(adreq.getApp(), "UTF-8"));
			param.setUa(URLEncoder.encode(user.getUa(), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			logger.error("ua/appname encoded fire exception!",e);
			throw new AdPullException("ua/appname url encoded fire exception!");
		}
		String osCode = user.getOs();
		/*0:Android
			1:Ios
			2:Windows Phone
			3:Others*/
        int osType=3;
	    if(OS_IOS.equals(osCode)){
	    	osType=1;
			// ios 必填信息
	        param.setUuid(user.getIdfa());
	    }else if(OS_ANDROID.equals(osCode)){
	    	osType=0;
	    	param.setUuid(user.getImei());
	    	if(StringUtils.isNotEmpty(user.getAdid())){
	    		param.setAnid(user.getAdid());
	    	}else{
	    		param.setAnid(user.getImei());
	    	}
	
		}
		param.setOs(osType);
	    param.setOsv(this.convOSVersion(user.getOsversion()));
		/*1:2g
		2:3g
		3:4g
		4:wifi
		5:其他*/
		if(StringUtils.isEmpty(adreq.getNet())){
			param.setConn(5);
		}else{
			Integer net=conn.get(adreq.getNet());
			param.setConn(net==null?5:net);
		}

		
		/*1:移动 2:联通 3:电信 4:其他*/
		if(StringUtils.isEmpty(user.getMobile())){
			param.setCarrier(4);
		}else{
			Integer carr=jugaomobiles.get(user.getMobile());
			param.setCarrier(carr==null?4:carr);
		}
		param.setIp(user.getClient_ip());
		try {
			param.setModel(URLEncoder.encode(user.getPhonemodel(),"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			logger.error("phone model url encoded fire exception!",e);
			throw new AdPullException("phone model url encoded fire exception!");
		}
		if(StringUtils.isNotEmpty(user.getDip())){
			param.setDensity(Float.parseFloat((user.getDip())));
		}
		try {
			param.setBrand(URLEncoder.encode(user.getBrand_name(),"UTF-8"));//
			param.setMac(URLEncoder.encode(user.getMac(),"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			logger.error("mac url encoded fire exception!",e);
			throw new AdPullException("mac url encoded  fire exception!");
		}
		
		param.setPw(user.getDevice_width());
		param.setPh(user.getDevice_height());
		return param;
	}
	
	private AdRecomReply parseResult(JugaoAdResponse response,short posType)
			throws AdPullException {
		if(response == null||response.getReturncode().equals("204")||CollectionUtils.isEmpty(response.getAds())){//no ad
			return null;
		}
		if(!response.getReturncode().equals("200")){//error
			
			throw new AdPullException("ad request error,return error code:"+response.getReturncode());
		}
		Integer adType=adtype.get(AdSpaceType.findByValue(posType));
		// 返回对象
		AdRecomReply recomReply = new AdRecomReply();
		recomReply.setStatus(status);
		recomReply.setAd_contents(this.dealAds(response, adType));
		return recomReply;
	}
	private List<AdContent>  dealAds(JugaoAdResponse resp,int adType){
		List<AdContent> list=new ArrayList<AdContent> ();
		for(JugaoAd ad:resp.getAds()){
			//------------------BEGIN 常规设置-------------------
			AdContent content = new AdContent();
			AdMutiAction action = new AdMutiAction();
			int action_type=ACTION_WEB;
			String title = defTitle;
			String markTitle=defTitle;
		
			//------------------END 常规设置---------------------
			if(ad.getAdct()==2){
				action_type=ACTION_APP;
			}
	        //没有图片和落地页
			//原生广告和视频广告特殊处理，其他类型广告都是html代码返回
			/*1:横幅广告
				2:开屏广告
				3:原生信息流广告
				4:插屏广告
				5:原生视频广告
				6:视频广告*/
			if(adType!=3&&adType!=6){
	           //adtype 为1、2、4、5 时，返回完整的html
				content.setIsHtmlAd(true);
				content.setHtmlSnippet(ad.getAdm());
			}
		    if(ad.getAdmt()==1){//视频
				AdVid adVid=new AdVid();
				adVid.setSrc(ad.getVideourl());
				adVid.setDuration(Integer.parseInt(ad.getDuration()));
				content.setAdVid(adVid);

				action_type=ACTION_VIDEO;
		    }
		    //图片
			if(StringUtils.isNotEmpty(ad.getImgurl())){
				List<AdImg> imgs = new ArrayList<AdImg>();
				AdImg img = new AdImg();
				img.setSrc(ad.getImgurl());
				imgs.add(img);
				content.setImglist(imgs);
			}
			
			//标题
			if(StringUtils.isNotEmpty(ad.getDisplaytitle())){
				title=ad.getDisplaytitle();
			}
			if(StringUtils.isNotEmpty(ad.getDisplaytext())){
				markTitle=ad.getDisplaytext();
			}
			//落地页
			if(StringUtils.isNotEmpty(ad.getClickurl())){
				content.setLinkurl(ad.getClickurl());
				action.setLinkurl(ad.getClickurl());
			}
	      

			Map<String, List<String>> map = new HashMap<String, List<String>>();
			// 点击监测
			List<String> clickL=new ArrayList<String>();
			List<String> viewL=new ArrayList<String>();
			if(!CollectionUtils.isEmpty(ad.getImgtracking())){
				viewL.addAll(ad.getImgtracking());
			}
			if(!CollectionUtils.isEmpty(ad.getThclkurl())){
				clickL.addAll(ad.getThclkurl());
			}
			map.put(CLICK, clickL);
			map.put(SHOW, viewL);
			content.setThirdReportLinks(map);
			
			// 内容类型1表示链接推广,2表示应用推广,3未使用 4表示电话推广
			action.setType(action_type); 
			action.setGuideTitle(title);
			content.setMarketTitle(markTitle);
			content.setGuideTitle(title);
			content.setMutiAction(Collections.singletonList(action));
			list.add(content);
		}
		return list;
		
	}
	public JugaoAdPuller getJugaoAdPuller() {
		return jugaoAdPuller;
	}

	public void setJugaoAdPuller(JugaoAdPuller jugaoAdPuller) {
		this.jugaoAdPuller = jugaoAdPuller;
	}

}
