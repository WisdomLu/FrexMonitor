package com.ocean.proxy.api.service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inveno.util.CollectionUtils;
import com.ocean.core.common.security.SecurityEncode;
import com.ocean.core.common.system.ErrorCode;
import com.ocean.core.common.system.SystemContext;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.proxy.api.base.AbstractAdSupplier;
import com.ocean.proxy.api.helper.InvenoIdGenerator;
import com.ocean.persist.api.proxy.AdPullException;
import com.ocean.persist.api.proxy.JoinDSPEmu;
import com.ocean.persist.api.proxy.diankai.DiankaiAd;
import com.ocean.persist.api.proxy.diankai.DiankaiAdPullResponse;
import com.ocean.persist.api.proxy.yijifen.YijifenAdContent;
import com.ocean.persist.api.proxy.yijifen.YijifenAdData;
import com.ocean.persist.api.proxy.yijifen.YijifenAdPullParams;
import com.ocean.persist.api.proxy.yijifen.YijifenAdPuller;
import com.ocean.persist.api.proxy.yijifen.YijifenAdResponse;
import com.ocean.persist.api.proxy.yijifen.YijifenApp;
import com.ocean.persist.api.proxy.yijifen.YijifenDevice;
import com.ocean.persist.api.proxy.yijifen.YijifenImp;
import com.ocean.persist.api.proxy.yijifen.YijifenNativeCreative;
import com.ocean.persist.api.proxy.yijifen.YijifenNativeTempAttr;
import com.ocean.persist.api.proxy.yijifen.YijifenUser;
import com.ocean.persist.common.ProxyConstants;
import com.ocean.persist.model.proxy.DSPPosition;
import com.ocean.proxy.thrift.entity.AdContent;
import com.ocean.proxy.thrift.entity.AdImg;
import com.ocean.proxy.thrift.entity.AdMutiAction;
import com.ocean.proxy.thrift.entity.AdRecomReply;
import com.ocean.proxy.thrift.entity.AdRecomReq;
import com.ocean.proxy.thrift.entity.AdSpaceType;
import com.ocean.proxy.thrift.entity.AdUserInfo;
import com.ocean.proxy.thrift.entity.UserAdSpaceAttri;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;

@Component(value="yijifenAdSupplier")
public class YijifenAdSupplier  extends AbstractAdSupplier{
	private static Map<AdSpaceType, Integer> pt = new HashMap<AdSpaceType, Integer>(6);
	// 运营商
	private static Map<String, Integer> mobile = new HashMap<String, Integer>();
    @Autowired 
    YijifenAdPuller yijifenAdPuller;
	private static final int BANNER = 1;
	private static final int INTERSTITIAL = 2;
	private static final int OPENING = 3;
	private static final int INFOFLOW = 4;
	static{
		pt.put(AdSpaceType.BANNER, BANNER);
		pt.put(AdSpaceType.INTERSTITIAL, INTERSTITIAL);
		pt.put(AdSpaceType.OPENING, OPENING);
		pt.put(AdSpaceType.INFOFLOW, INFOFLOW);
		
		mobile.put("CMCC", 1);
		mobile.put("CUCC", 2);
		mobile.put("CTCC", 3);
	}
	
	public AdRecomReply invoke(InvokeAttribute attribute) throws AdPullException {
		
		AdRecomReq adreq = attribute.getAdreq();
		DSPPosition positionInfo = attribute.getDspPosition();
		String pid = positionInfo.getPos();
		YijifenAdPullParams params = parseParams(adreq, pid);
		YijifenAdResponse response = (YijifenAdResponse) yijifenAdPuller.api(params,positionInfo.getText1()
				,SystemContext.getDynamicPropertyHandler().get(ProxyConstants.YIJIFEN_VERSION),adreq.getHash(),adreq.getHash(),String.valueOf(adreq.getUserAdSpaceAttri().getAdSpaceId()));
		return parseResult(response);
	}
	
	private AdRecomReply parseResult(YijifenAdResponse response)
			throws AdPullException {
		
		if(response == null){
			return null;
		}
		// 返回对象
		AdRecomReply recomReply = new AdRecomReply();
		recomReply.setStatus(status);
		recomReply.setAd_contents(this.dealAds(response));

		
		return recomReply;
	}
	private List<AdContent>  dealAds(YijifenAdResponse resp){
		List<AdContent> list=new ArrayList<AdContent> ();
		YijifenAdData data = resp.getData();
		List<YijifenAdContent> ads = data.getAdList();
		
		if(ads == null || ads.isEmpty()){
			ads=data.getDefaultAd();
		}
		if(ads == null || ads.isEmpty()){
			logger.error("joinDSP:yjf ad request faired,return status:{}",resp.getStatus());
			return null;
		}
		for(YijifenAdContent ad:ads){
			AdContent content = new AdContent();
			String linkurl = ad.getClick_url();
			content.setLinkurl(linkurl);
			Map<String, List<String>> map = new HashMap<String, List<String>>();
			// 点击监测
			List<String> click = ad.getFeedback_click_address();
			if(click == null){
				click = new ArrayList<String>();
			}
			map.put(CLICK, click);
			// 曝光监测
			List<String> show = ad.getFeedback_pv_address();
			map.put(SHOW, show);
			content.setThirdReportLinks(map);
			// --------------物料信息------------------
			List<AdImg> imgs = new ArrayList<AdImg>();
			if(ad.getAdType() == INFOFLOW){
				YijifenNativeCreative nat=ad.getNativeCreaqtive();
				if(CollectionUtils.isNotEmpty(nat.getTempAttrs())){
	/*				YijifenNativeTempAttr tem=nat.getTempAttrs().get(0);*/
					for(YijifenNativeTempAttr tem:nat.getTempAttrs()){
						if(tem.getType()==1){//图片链接
							AdImg img = new AdImg();
							img.setSrc(tem.getValue());
							imgs.add(img);
						}
					}

				}
			}
			else{
				AdImg img = new AdImg();
				img.setSrc(ad.getImage_url());
				imgs.add(img);
			}
				
			AdMutiAction action = new AdMutiAction();
			/*
			 * 点击后动作类型
				可能取值
				0.打开native 详情页
				1.应用内下载
				2.webview 方式打开
				3.浏览器打开
				4.应用内下载且支持触
				发url
				5.直接跳转AppStore
			 * */
			if(ad.getLanding_type()==1||ad.getLanding_type()==3||ad.getLanding_type()==5){
				action.setType(ACTION_APP);
			}else{
				action.setType(ACTION_WEB);
			}
			
			
			action.setLinkurl(linkurl);
			String title = ad.getTitle();
			if(StringUtils.isEmpty(title)){
				title = defTitle;
			}
			content.setMarketTitle(title);
			content.setGuideTitle(title);
			//content.setContent(ad.getWords());
			action.setGuideTitle(title);
			// 图片地址
			content.setImglist(imgs);
			content.setMutiAction(Collections.singletonList(action));
			list.add(content);
		}
		return list;
	
	}
	private YijifenAdPullParams parseParams(AdRecomReq adreq, String pid) throws AdPullException{
		
		UserAdSpaceAttri attri = adreq.getUserAdSpaceAttri();
		AdUserInfo userInfo = adreq.getUserinfo();
		YijifenAdPullParams params = new YijifenAdPullParams();
		params.setChannel(SystemContext.getDynamicPropertyHandler().getInt(ProxyConstants.YIJIFEN_CHANNEL,16));
		params.setIsTest(SystemContext.getDynamicPropertyHandler().getInt(ProxyConstants.YIJIFEN_IS_TEST,0));
		params.setToken(SystemContext.getDynamicPropertyHandler().get(ProxyConstants.YIJIFEN_TOKEN));
		
		// 设备信息
		YijifenDevice device = new YijifenDevice();
		device.setClient_ip(userInfo.getClient_ip());
		String ua;
		try {
			ua = Base64.encodeBase64String(userInfo.getUa().getBytes("UTF-8"));
			device.setUa(ua);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		device.setUa(userInfo.getUa());
		// 必填 0.未知 1.手机 2:平板电脑
		device.setDevicetype(1);
		// 可填	GalaxyS6	设备型号，小写，去掉空格
		String model = userInfo.getPhonemodel();
		if(StringUtils.isNotEmpty(model)){
			device.setTelModel(model.replace(" ", "").toLowerCase());
		}else{
			throw new AdPullException(ErrorCode.PARAM_ERROR, "device parameter phonemodel is empty!,uid:"+adreq.ogin_name+",space id:"+attri.getAdSpaceId());
	     	   
		}
		// 必填 0.未知 1.移动 2.联通 3.电信
		if(StringUtils.isNotEmpty(userInfo.getMobile())){
			Integer m=mobile.get(userInfo.getMobile());
			device.setOperator(m==null?1:m);
		}else{
			device.setOperator(1);
		}
		
		if(!StringUtils.isEmpty(userInfo.getBrand_name())){
			device.setBrand(userInfo.getBrand_name());
		}else{
			throw new AdPullException(ErrorCode.PARAM_ERROR, "device parameter brand_name is empty!,uid:"+adreq.ogin_name+",space id:"+attri.getAdSpaceId());
	     	   
		}
		
		// 必填 0.未知 1.wifi 2.2G 3.3G 4.4G
		device.setNetwork("1");
		// 可填	5.0	操作系统版本号
		if(StringUtils.isNotEmpty(userInfo.getOsversion())){
			device.setOs_version(userInfo.getOsversion());
		}else{
			device.setOs_version("5.0");
		}
	


		// 如果是ios
		String osCode = userInfo.getOs();
		if(OS_IOS.equalsIgnoreCase(osCode)){
			// ios 必填信息
			device.setIdfa(userInfo.getIdfa());
		}else if(OS_ANDROID.equalsIgnoreCase(osCode)){
			if(!StringUtils.isEmpty(userInfo.getImei())){
				device.setImei(userInfo.getImei());
			}
			if(!StringUtils.isEmpty(userInfo.getAdid())){
				device.setAndroidid(userInfo.getImei());
			}
			if(!StringUtils.isEmpty(userInfo.getMac())){
			   device.setMac(userInfo.getMac());
			}else{
			   device.setMac("a0:32:99:92:15:0d");
				
			}
		}
		// 展现信息
		YijifenImp imp = new YijifenImp();
		imp.setAdSlotId(Long.valueOf(pid));
		
		//1:BANNER 2:插屏 3:开屏 4.信息流
		//banner的固定尺寸是720*120的。开屏是720*1280的。信息流尺寸可以勾选

        if(pt.get(attri.getSpaceType())==1&&!isAdapter("yijifen",attri.getSpaceHeight(),attri.getSpaceWidth(),720,120)){
        	throw new AdPullException(ErrorCode.PARAM_ERROR, "no mapping ad to  specified ad space size,uid:"+adreq.ogin_name+",space id:"+attri.getAdSpaceId()+",space size:"+attri.getSpaceHeight()+"*"+attri.getSpaceWidth());
     	   
        }else if(pt.get(attri.getSpaceType())==3&&!isAdapter("yijifen",attri.getSpaceHeight(),attri.getSpaceWidth(),720,1280)){
        	throw new AdPullException(ErrorCode.PARAM_ERROR, "no mapping ad to  specified ad space size,uid:"+adreq.ogin_name+",space id:"+attri.getAdSpaceId()+",space size:"+attri.getSpaceHeight()+"*"+attri.getSpaceWidth());
     	   
        }
		imp.setAdType(pt.get(attri.getSpaceType()));
		
		imp.setEventime(System.currentTimeMillis());
		
		// 用户信息

		if(!StringUtils.isEmpty(userInfo.getLon())&&!StringUtils.isEmpty(userInfo.getLat())){
			YijifenUser user = new YijifenUser();
			user.setLongitude(userInfo.getLon());
			user.setLatitude(userInfo.getLat());
			params.setUser(user);
		}

		
		YijifenApp app = new YijifenApp();
		params.setImp(imp);
		params.setDevice(device);
		
		params.setApp(app);
		return params;
	}

	public YijifenAdPuller getYijifenAdPuller() {
		return yijifenAdPuller;
	}

	public void setYijifenAdPuller(YijifenAdPuller yijifenAdPuller) {
		this.yijifenAdPuller = yijifenAdPuller;
	}

}
