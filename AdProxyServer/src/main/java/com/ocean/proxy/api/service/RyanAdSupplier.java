package com.ocean.proxy.api.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ocean.core.common.system.SystemContext;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.persist.api.proxy.AdPullException;
import com.ocean.persist.api.proxy.JoinDSPEmu;
import com.ocean.persist.api.proxy.ryan.RyanAdPullParams;
import com.ocean.persist.api.proxy.ryan.RyanAdPullResponse;
import com.ocean.persist.api.proxy.ryan.RyanAdPuller;
import com.ocean.persist.api.proxy.ryan.RyanApp;
import com.ocean.persist.api.proxy.ryan.RyanBannerReq;
import com.ocean.persist.api.proxy.ryan.RyanBannerResp;
import com.ocean.persist.api.proxy.ryan.RyanBid;
import com.ocean.persist.api.proxy.ryan.RyanDevice;
import com.ocean.persist.api.proxy.ryan.RyanImpression;
import com.ocean.persist.api.proxy.ryan.RyanNativeReq;
import com.ocean.persist.api.proxy.ryan.RyanNativeResp;
import com.ocean.persist.api.proxy.ryan.RyanSeatbid;
import com.ocean.persist.api.proxy.ryan.RyanVideoReq;
import com.ocean.persist.api.proxy.ryan.RyanVideoResp;
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
import com.ocean.proxy.thrift.entity.AdVid;
import com.ocean.proxy.thrift.entity.ExpectedMarketType;
import com.ocean.proxy.thrift.entity.UserAdSpaceAttri;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;


/**Ryan 广告请求
 *  * @author Alex & E-mail:569246607@qq.com
      @date   2017年1月4日 
      @version 1.0 
 */
@Component(value="ryanAdSupplier")
public class RyanAdSupplier  extends AbstractAdSupplier{
    @Autowired
	private RyanAdPuller ryanAdPuller;
	private static final Map<String,Integer>   netType=new HashMap<String,Integer>(4);
	static{
		netType.put(NETWORK_2G, 3);
		netType.put(NETWORK_3G, 4);
		netType.put(NETWORK_4G, 5);
		netType.put(NETWORK_WIFI, 2);
	}
	public AdRecomReply invoke(InvokeAttribute attribute) throws AdPullException {
	    // TODO Auto-generated method stub
		AdRecomReq adreq = attribute.getAdreq();
		DSPPosition positionInfo = attribute.getDspPosition();
		boolean isNative = positionInfo.getPosType() == AdSpaceType.INFOFLOW.getValue();
		// 参数转换
		RyanAdPullParams params = parseParams(adreq, positionInfo,isNative);
		RyanAdPullResponse response = (RyanAdPullResponse)ryanAdPuller.api(params,adreq.getHash(),String.valueOf(adreq.getUserAdSpaceAttri().getAdSpaceId()));	
		return parseResult(response, attribute);
	}
    
	private RyanAdPullParams parseParams(AdRecomReq adreq,DSPPosition positionInfo,boolean isNative) throws AdPullException{
		RyanAdPullParams ryanParam=new RyanAdPullParams();

		if(!adreq.isSetUserAdSpaceAttri()){
			throw new AdPullException("reuqest parameter UserAdSpaceAttri is empty!");
		}
		UserAdSpaceAttri attri = adreq.getUserAdSpaceAttri();
		String bidId=UUID.randomUUID().toString().replaceAll("-", "");
		ryanParam.setId(bidId);
		//广告位描述
		List<RyanImpression> impL=new ArrayList<RyanImpression>(1);
		RyanImpression imp=new RyanImpression();
		String impId=UUID.randomUUID().toString().replaceAll("-", "");
		imp.setId(impId);
		imp.setTagid(positionInfo.getId().toString());
		imp.setBidfloor(positionInfo.getSettlementPrice() / 100f);//底价
		imp.setIs_support_deeplink(0);//默认不支持deeplink
		logger.info("joinDSP:re bid id:{},imp id:{},pose id:{},attri info:{},ad size:{}",bidId,impId,positionInfo.getPos(),attri.getSpaceWidth()+"*"+attri.getSpaceWidth());

		if(isNative){//原生广告
			//原生广告的组合类型：title+desc+img(480*320  600*500  300*300)    title+img(640*160 720*240 640*200)  img(640*160 720*240 640*200) icon+title
			RyanNativeReq nativeR=new RyanNativeReq();
            //title
			if(attri.isSetTitleMax()){
				Map<String,Object> titleM=new HashMap<String,Object>(2);
				titleM.put("len",convertV(attri.getTitleMax(),30));//标题最大长度
				nativeR.setTitle(titleM);
				if(attri.isSetCwMax()){
				    //desc
					Map<String,Object> descM=new HashMap<String,Object>(2);
					descM.put("len", convertV(attri.getCwMax(),30));
					nativeR.setDesc(descM);
				}
			}
			//img
			Map<String,Object> imgM=new HashMap<String,Object>(3);
			imgM.put("h", attri.getSpaceHeight());
			imgM.put("w", attri.getSpaceWidth());
			nativeR.setImg(imgM);
			imp.set_native(nativeR);
			imp.setInstl(8);//信息流广告
			logger.debug("原生广告请求  bid id:{}",bidId);
		}else if(attri.isSetExpectedMarketTypes()&&attri.getExpectedMarketTypes().contains(ExpectedMarketType.VIDEO)){//视频广告
			RyanVideoReq videoR=new RyanVideoReq();
			videoR.setProtocol(101);
			videoR.setH(attri.getSpaceHeight());
			videoR.setW(attri.getSpaceWidth());
			imp.setVideo(videoR);
			imp.setInstl(6);//视频广告
			logger.debug("视频广告请求 bid id:{}",bidId);
		}else{
			RyanBannerReq bannerR=new RyanBannerReq();
			if(AdSpaceType.INFOFLOW.getValue()<4){// 横幅 –1 全屏 – 2 插屏 – 3
				bannerR.setType(AdSpaceType.INFOFLOW.getValue());
			}else{
				bannerR.setType(1);//默认横幅广告
			}
			bannerR.setH(attri.getSpaceHeight());
			bannerR.setW(attri.getSpaceWidth());
			imp.setBanner(bannerR);
			imp.setInstl(1);//
			logger.debug("横幅广告请求 bid id:{}",bidId);
		}
		
		impL.add(imp);
		ryanParam.setImp(impL);//目前只支持一个
		//app
		if(attri.isSetAppId()){
			RyanApp app=new RyanApp();
			String appId=String.valueOf(attri.getAppId());
			app.setId(appId);//应用id
			logger.info("joinDSP:re bid id:{},app id:{}",bidId,appId);
			ryanParam.setApp(app);
		}
	
		
		//Device info
		AdUserInfo userInfo=adreq.getUserinfo();
		RyanDevice device=new RyanDevice();
		if(userInfo.isSetDevice_height()){
			device.setH(userInfo.getDevice_height());
		}
		if(userInfo.isSetDevice_width()){
			device.setW(userInfo.getDevice_width());
		}
		if(userInfo.isSetUa()){
			device.setUa(userInfo.getUa());
		}
		
		device.setIp(userInfo.getClient_ip());
		if(userInfo.isSetLon()&&userInfo.isSetLat()){
			Map<String,Object> geoM=new HashMap<String,Object>();
			geoM.put("lon", userInfo.getLon());
			geoM.put("lat", userInfo.getLat());
			device.setGeo(geoM);
		}
		
		String osCode = userInfo.getOs();
		// 如果是ios
		String os = "other";
		if(OS_IOS.equals(osCode)){
			// ios 必填信息
			device.setDid(userInfo.getIdfa());
			device.setDidmd5(DigestUtils.md5Hex(userInfo.getIdfa()).toUpperCase());
			os = "iOS";
		}else if(OS_ANDROID.equals(osCode)){
			device.setDid(userInfo.getImei());
			device.setDidmd5(DigestUtils.md5Hex(userInfo.getImei()).toUpperCase());
			device.setDpid(userInfo.getAdid());
			os="Android";
		}else{
			os="other";
		}
		device.setOs(os);
		if(userInfo.isSetMac()){
			device.setMac(userInfo.getMac());
		}
        if(userInfo.isSetAaid()){
        	device.setIfa(userInfo.getAaid());
        }
        if(userInfo.isSetBrand_name()){
        	device.setMake(userInfo.getBrand_name());
        }
        if(userInfo.isSetPhonemodel()){
        	device.setModel(userInfo.getPhonemodel());
        }
        if(userInfo.isSetOsversion()){
        	device.setOsv(userInfo.getOsversion());
        }
        if(userInfo.isSetMobile()){
        	String carrier=mobiles.get(userInfo.getMobile());
        	device.setCarrier(carrier==null?"46020":carrier);
        }
        device.setLanguage("zh-CN");//
        if(adreq.isSetNet()){
        	int net=netType.get(adreq.getNet());
            device.setConnectionty(net);//0—未知 1—Ethernet 2—wifi 3—2G 4—3G 5—4G
        }
        device.setDevicetype(0);//设备类型: 0—手机 1—平板 2—PC 3—互联网电视
    	ryanParam.setDevice(device);
		return ryanParam;
	}

	private AdRecomReply parseResult(RyanAdPullResponse resp,InvokeAttribute attribute){
		if(resp == null){
			return null;
		}
		RyanSeatbid seatBid=resp.getSeatbid().get(0);//投标信息，目前只能提供一个
		RyanBid bid=seatBid.getBid().get(0);//跟imp对应，目前只能提供一个
		logger.debug("joinDSP:re bidId:{},response info:{}",resp.getBidid(),resp.toString());
		AdRecomReply recomReply = new AdRecomReply();
		recomReply.setStatus(status);
		DSPPosition positionInfo = attribute.getDspPosition();
		UserAdSpaceAttri attri = attribute.getAdreq().getUserAdSpaceAttri();
		AdContent adContent=new AdContent();
		//adContent.setAdId(InvenoIdGenerator.genThirdDspId(SystemContext.getDynamicPropertyHandler().getInt(ProxyConstants.PROXY_SERVER_NODE_INDEX,100)));

		if(positionInfo.getPosType() == AdSpaceType.INFOFLOW.getValue()){//native
			RyanNativeResp nativeR=bid.getNative_ad();
			
			adContent.setMarketTitle(nativeR.getTitle());
			adContent.setContent(nativeR.getDesc());
			adContent.setGuideTitle(nativeR.getTitle());
			logger.info("joinDSP:re 返回原生广告，bid id:{},title:{},desc:{}",resp.getBidid(),nativeR.getTitle(),nativeR.getDesc());
			//adContent.setType(5);
			List<AdImg> imgs = new ArrayList<AdImg>();
			String imgUrl=nativeR.getImg();
			AdImg adImg=new AdImg();
			adImg.setSrc(imgUrl);
			imgs.add(adImg);
			List<String> imgUrls=nativeR.getImg_urls();
			for(int i=0;imgUrls!=null&&imgUrls.size()>0&&i<imgUrls.size();i++){
				AdImg imgT=new AdImg();
				imgT.setSrc(imgUrls.get(i));
				imgs.add(imgT);
			}
			adContent.setImglist(imgs);
			//落地页
			String landing=nativeR.getLanding();
			adContent.setLinkurl(landing);
			logger.info("joinDSP:re 返回原生广告 bid id:{},landing:{},img url:{}",resp.getBidid(),landing,imgUrl);
			
			//上报
			Map<String, List<String>> map = new HashMap<String, List<String>>();
			// 点击监测

			map.put(CLICK, nativeR.getClicktrackers());
			// 曝光监测
			map.put(SHOW, nativeR.getImptrackers());
			adContent.setThirdReportLinks(map);
			
			// 类型
			AdMutiAction action = new AdMutiAction();
			/*内容类型1表示链接推广,2表示应用推广,3未使用 4表示电话推广 7表示视频*/
			action.setType(ACTION_WEB);
			action.setLinkurl(landing);
			action.setGuideTitle(nativeR.getTitle());
			if(StringUtils.isNotEmpty(nativeR.getApp_name())){
				action.setCpName(nativeR.getApp_name());
			}
			if(StringUtils.isNotEmpty(nativeR.getPackage_name())){
				action.setCpPackage(nativeR.getPackage_name());
			}
			adContent.setMutiAction(Collections.singletonList(action));
		}else if(attri.isSetExpectedMarketTypes()&&attri.getExpectedMarketTypes().contains(ExpectedMarketType.VIDEO)){
			RyanVideoResp videoR=bid.getVideo_ad();
			///adContent.setType(5);
			adContent.setMarketTitle(defTitle);
			adContent.setGuideTitle(defTitle);
			
			//落地页
			String landing=videoR.getLanding();
			adContent.setLinkurl(landing);
			logger.info("joinDSP:re 返回视频广告，bid id:{},landing:{}",landing);
			//上报
			Map<String, List<String>> map = new HashMap<String, List<String>>();
			// 点击监测
			map.put(CLICK, videoR.getClicktrackers());
			// 曝光监测
			map.put(SHOW, videoR.getStarttrackers());
			adContent.setThirdReportLinks(map);
			
			// 类型
			AdMutiAction action = new AdMutiAction();
			/*内容类型1表示链接推广,2表示应用推广,3未使用 4表示电话推广 7表示视频*/
			action.setType(ACTION_VIDEO);
			
			// 落地页
			action.setLinkurl(landing);
			//action.setGuideTitle(videoR.getTitle());
			if(StringUtils.isNotEmpty(videoR.getApp_name())){
				action.setCpName(videoR.getApp_name());
			}
			if(StringUtils.isNotEmpty(videoR.getPackage_name())){
				action.setCpPackage(videoR.getPackage_name());
			}
			
			AdVid adVid=new AdVid();
			adVid.setSrc(landing);
			adVid.setDuration(videoR.getDuration());
			adContent.setAdVid(adVid);
			adContent.setMutiAction(Collections.singletonList(action));
		}else{
			RyanBannerResp bannerR=bid.getBanner_ad();
			if(StringUtils.isEmpty(bannerR.getTitle())){
				adContent.setMarketTitle(bannerR.getTitle());
				adContent.setGuideTitle(bannerR.getTitle());
			}else{
				adContent.setMarketTitle(defTitle);
				adContent.setGuideTitle(defTitle);
			}
			
			if(StringUtils.isEmpty(bannerR.getDesc())){
				adContent.setContent(bannerR.getDesc());
			}
			adContent.setType(5);
			
			//落地页
			String landing=bannerR.getLanding();
			adContent.setLinkurl(landing);
			logger.info("joinDSP:re 返回广告，bid id:{},landing:{},title:{}",landing,adContent.getMarketTitle());
			//上报
			Map<String, List<String>> map = new HashMap<String, List<String>>();
			// 点击监测
			map.put(CLICK, bannerR.getImpress());
			// 曝光监测
			map.put(SHOW, bannerR.getClick());
			adContent.setThirdReportLinks(map);
			
			// 类型
			AdMutiAction action = new AdMutiAction();
			/*内容类型1表示链接推广,2表示应用推广,3未使用 4表示电话推广 7表示视频*/
			action.setType(ACTION_WEB);
			
			// 落地页
			action.setLinkurl(landing);
			//action.setGuideTitle(videoR.getTitle());
			if(StringUtils.isNotEmpty(bannerR.getApp_name())){
				action.setCpName(bannerR.getApp_name());
			}
			if(StringUtils.isNotEmpty(bannerR.getPackage_name())){
				action.setCpPackage(bannerR.getPackage_name());
			}
			
			adContent.setMutiAction(Collections.singletonList(action));
		}
		recomReply.setAd_contents(Collections.singletonList(adContent));
		return recomReply;
	}

	public RyanAdPuller getRyanAdPuller() {
		return ryanAdPuller;
	}

	public void setRyanAdPuller(RyanAdPuller ryanAdPuller) {
		this.ryanAdPuller = ryanAdPuller;
	}

}
