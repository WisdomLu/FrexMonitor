package com.ocean.proxy.api.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inveno.util.CollectionUtils;
import com.ocean.core.common.system.ErrorCode;
import com.ocean.core.common.system.SystemContext;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.persist.api.proxy.AdPullException;
import com.ocean.persist.api.proxy.JoinDSPEmu;
import com.ocean.persist.api.proxy.diankai.DiankaiAd;
import com.ocean.persist.api.proxy.diankai.DiankaiAdPullResponse;
import com.ocean.persist.api.proxy.zhangyou.ZhangYouAdPullParams;
import com.ocean.persist.api.proxy.zhangyou.ZhangYouAdPullResponse;
import com.ocean.persist.api.proxy.zhangyou.ZhangYouAdPuller;
import com.ocean.persist.api.proxy.zhangyou.ZhangYouAdReq;
import com.ocean.persist.api.proxy.zhangyou.ZhangYouAdResp;
import com.ocean.persist.api.proxy.zhangyou.ZhangYouApp;
import com.ocean.persist.api.proxy.zhangyou.ZhangYouAssetReq;
import com.ocean.persist.api.proxy.zhangyou.ZhangYouAssetResp;
import com.ocean.persist.api.proxy.zhangyou.ZhangYouDevice;
import com.ocean.persist.api.proxy.zhangyou.ZhangYouGEO;
import com.ocean.persist.api.proxy.zhangyou.ZhangYouImageResp;
import com.ocean.persist.api.proxy.zhangyou.ZhangYouLinkResp;
import com.ocean.persist.api.proxy.zhangyou.ZhangYouNativeReq;
import com.ocean.persist.api.proxy.zhangyou.ZhangYouNativeResp;
import com.ocean.persist.api.proxy.zhangyou.ZhangYouScreen;
import com.ocean.persist.api.proxy.zhangyou.ZhangYouTitleResp;
import com.ocean.persist.api.proxy.zhangyou.ZhangYouVideo;
import com.ocean.persist.common.ProxyConstants;
import com.ocean.persist.model.proxy.DSPPosition;
import com.ocean.proxy.api.base.AbstractAdSupplier;
import com.ocean.proxy.api.base.BaseAdSupplier.InvokeAttribute;
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


/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年2月3日 
      @version 1.0 
 */
@Component(value="zhangyouAdSupplier")
public class ZhangYouAdSupplier   extends AbstractAdSupplier{
	protected static final Map<String, Integer> zymobiles = new HashMap<String, Integer>(4);
	private final int  NATIVE_WIDTH=640;
    private final int  NATIVE_HEIGHT=160;
    
    private final int  BANNER_WIDTH=640;
    private final int BANNER_HEIGHT=100;
    @Autowired
    private ZhangYouAdPuller zhangYouAdPuller;
	static{
		zymobiles.put("CMCC", 0);
		zymobiles.put("CUCC", 3);
		zymobiles.put("CTCC", 1);
	}
	public AdRecomReply invoke(InvokeAttribute attribute)
			throws AdPullException {
		// TODO Auto-generated method stub
		AdRecomReq adreq = attribute.getAdreq();
		DSPPosition positionInfo = attribute.getDspPosition();
		AdUserInfo userInfo=adreq.getUserinfo();
		// 参数转换
		ZhangYouAdPullParams params = parseParams(adreq, positionInfo);
		ZhangYouAdPullResponse response = (ZhangYouAdPullResponse)zhangYouAdPuller.api(params,userInfo.getClient_ip(),userInfo.getUa()
				,adreq.getHash(),String.valueOf(adreq.getUserAdSpaceAttri().getAdSpaceId()));	
		return parseResult(response, attribute);
	}
   private ZhangYouAdPullParams parseParams(AdRecomReq adreq,DSPPosition positionInfo) throws AdPullException{
	   ZhangYouAdPullParams param=new ZhangYouAdPullParams();
	   param.setVer("1.1");//当前api协议版本
	   param.setSsp_token(SystemContext.getDynamicPropertyHandler().get(ProxyConstants.ZHANGYOU_SSP_TOKEN));//adx提供的ssp token
	   param.setIs_test(SystemContext.getDynamicPropertyHandler().getInt(ProxyConstants.ZHANGYOU_IS_TEST,0));//测试提供测试的dsp,默认不测试
	  
	   //app
	   ZhangYouApp app=new ZhangYouApp();
	   UserAdSpaceAttri attri=adreq.getUserAdSpaceAttri();
	   if(StringUtils.isEmpty(positionInfo.getText1())){
		   throw new AdPullException(ErrorCode.PARAM_ERROR, "app id is empty!");
	   }
	   app.setId(positionInfo.getText1());
	   if(StringUtils.isEmpty(positionInfo.getText2())){
		   throw new AdPullException(ErrorCode.PARAM_ERROR, "app key is empty!");
	   }
	   app.setApp_key(positionInfo.getText2());
	   
/*	   if(StringUtils.isEmpty(positionInfo.getSummary())){
		   throw new AdPullException(ErrorCode.PARAM_ERROR, "app package name is empty!");
	   }*/
	   app.setName(adreq.getApp());//app 名称
	  // app.setBundle(positionInfo.getSummary());//app 包名
	   app.setBundle(SystemContext.getDynamicPropertyHandler().get(ProxyConstants.ZHANGYOU_APP_PACKAGE_NAME));
	   
	   param.setApp(app);
	   
	   //device
	   AdUserInfo userInfo=adreq.getUserinfo();
	   ZhangYouDevice device=new ZhangYouDevice();
       if(userInfo.isSetPhonemodel()){
         	device.setModel(userInfo.getPhonemodel());
       }
       if(userInfo.isSetBrand_name()){
            device.setBrand(userInfo.getBrand_name());
       }
       if(userInfo.isSetMobile()){
          	String carrierS=mobiles.get(userInfo.getMobile());
          	device.setPlmn(carrierS==null?"46020":carrierS);
          	
         	Integer carrierI=zymobiles.get(userInfo.getMobile());
         	device.setCarrier(carrierI==null?4:carrierI);
       }
       if(adreq.isSetNet()){
           device.setConnection_type(adreq.getNet());
       }
       
       if(attri.getSpaceHeight()<attri.getSpaceWidth()){
    	   device.setOrientation(3);//横向
       }else{
    	   device.setOrientation(1);//设备方向，默认为纵向
       }
	   
       
	   if(userInfo.isSetMac()){
			device.setMac(userInfo.getMac());
	   }
	   String osCode = userInfo.getOs();
		// 如果是ios
	   String os = "wp";
	   if(OS_IOS.equals(osCode)){
			// ios 必填信息
			device.setIos_adid(userInfo.getIdfa());
			os="ios";
	   }else if(OS_ANDROID.equals(osCode)){
			device.setImei(userInfo.getImei());
			device.setAndroid_id(userInfo.getAdid());
		    if(userInfo.isSetAaid()){
		       	device.setAndroid_adid(userInfo.getAaid());
		    }
			os="android";
		}
		device.setOs_type(os);
	    if(userInfo.isSetOsversion()){
	        device.setOs_version(userInfo.getOsversion());
	    }
		if(userInfo.isSetImsi()){
			device.setImsi(userInfo.getImsi());
		}
	    if(!StringUtils.isEmpty(userInfo.getLon())&&!StringUtils.isEmpty(userInfo.getLat())){
		    ZhangYouGEO geo=new ZhangYouGEO();
		    geo.setLat(Float.parseFloat(userInfo.getLat()));
		    geo.setLon(Float.parseFloat(userInfo.getLon()));
			device.setGeo(geo);
		}
		
	   //screen 

	   if(userInfo.isSetDevice_height()&&userInfo.isSetDevice_width()){
		   ZhangYouScreen screen=new ZhangYouScreen();
		   screen.setH(userInfo.getDevice_height());
		   screen.setW(userInfo.getDevice_width());
		   device.setScreen(screen);
	       param.setDevice(device);
	   }
       
       //ad
	   List<ZhangYouAdReq> adReqL=new ArrayList<ZhangYouAdReq>();

	   //请求多个广告，提高竞拍成功概率
	   boolean flag=false;
	   int poseType = positionInfo.getPosType();
       if(poseType == AdSpaceType.INFOFLOW.getValue()&&isAdapter("zhangyou",attri.getSpaceHeight(),attri.getSpaceWidth(),NATIVE_HEIGHT,NATIVE_WIDTH)){//原生广告
		 //Type 对方提供广告类型， 0：横幅 , 1: 插屏 ,  2：开屏， 3：原生 ，4：视频
		   ZhangYouAdReq adReq=new ZhangYouAdReq();
		   adReq.setType(3);
		   ZhangYouNativeReq nativeReq=new ZhangYouNativeReq();
		   ZhangYouAssetReq imgAsset=new ZhangYouAssetReq();
		   imgAsset.setId((int)System.currentTimeMillis());
		   imgAsset.setRequired(1);
		    
		   Map<String,Object> imgM=new HashMap<String,Object>(3);
		   imgM.put("h", attri.getSpaceHeight());
		   imgM.put("w", attri.getSpaceWidth());
		   imgM.put("type", 3);
		   imgAsset.setImg(imgM);
		   
		   
		   ZhangYouAssetReq titleAsset=new ZhangYouAssetReq();
		   titleAsset.setId((int)System.currentTimeMillis());
		   titleAsset.setRequired(1);
		   Map<String,Object> titleM=new HashMap<String,Object>(2);
           titleM.put("len",convertV(attri.getTitleMax(),30));//标题最大长度
           titleAsset.setTitle(titleM);
			
		   nativeReq.setAssets(Arrays.asList(imgAsset,titleAsset));
		   nativeReq.setLayout(3);
		   adReq.set_native(nativeReq);
		   //Inventory_type支持的广告资源类型 , 1:图片， 2: 图文， 3: 视频， 4:html5，5: 文本， 6: 原生 , 7:html5 url, 即一个指向 html素材页面的 urll。如果为空 ，则默认只支持 1: 图

		   adReq.setInventory_types(Arrays.asList(6));
		   
	       adReq.setFloor_price(positionInfo.getSettlementPrice());//底价
		   adReq.setH(attri.getSpaceHeight());
		   adReq.setW(attri.getSpaceWidth());
		   adReq.setPlace_id(String.valueOf(attri.getAdSpaceId()));//广告位 id ，由 ssp 给
		   adReqL.add(adReq);
		   flag=true;
       }else if(poseType == AdSpaceType.OPENING.getValue()){//开屏广告
		   ZhangYouAdReq adReq=new ZhangYouAdReq();
    	   adReq.setType(2);
           //0：h5和h5都可以：1：只拉h5; 2:只拉菲h5
           adReq.setInventory_types(getInventoryTypes(attri.getH5type()));

    	   
           adReq.setFloor_price(positionInfo.getSettlementPrice());//底价
    	   adReq.setH(attri.getSpaceHeight());
    	   adReq.setW(attri.getSpaceWidth());
    	   adReq.setPlace_id(String.valueOf(attri.getAdSpaceId()));//广告位 id ，由 ssp 给
    	   adReqL.add(adReq);
    	   flag=true;
       }else if(poseType == AdSpaceType.INTERSTITIAL.getValue()){//插屏
		   ZhangYouAdReq adReq=new ZhangYouAdReq();
    	   adReq.setType(1);
    	   adReq.setInventory_types(getInventoryTypes(attri.getH5type()));
           adReq.setFloor_price(positionInfo.getSettlementPrice());//底价
    	   adReq.setH(attri.getSpaceHeight());
    	   adReq.setW(attri.getSpaceWidth());
    	   adReq.setPlace_id(String.valueOf(attri.getAdSpaceId()));//广告位 id ，由 ssp 给
    	   adReqL.add(adReq);
    	   flag=true;
       }

	   else if(poseType == AdSpaceType.BANNER.getValue()&&isAdapter("zhangyou",attri.getSpaceHeight(),attri.getSpaceWidth(),BANNER_HEIGHT,BANNER_WIDTH)){
		   ZhangYouAdReq adReq=new ZhangYouAdReq();
    	   adReq.setType(0);//默认为横幅
    	   adReq.setInventory_types(getInventoryTypes(attri.getH5type()));
           adReq.setFloor_price(positionInfo.getSettlementPrice());//底价
    	   adReq.setH(attri.getSpaceHeight());
    	   adReq.setW(attri.getSpaceWidth());
    	   adReq.setPlace_id(String.valueOf(attri.getAdSpaceId()));//广告位 id ，由 ssp 给
    	   adReqL.add(adReq);
    	   flag=true;
	   } else if(attri.isSetExpectedMarketTypes()&&attri.getExpectedMarketTypes().contains(ExpectedMarketType.VIDEO)){//视频广告
		   ZhangYouAdReq adReq=new ZhangYouAdReq();
    	   adReq.setType(4);
    	   adReq.setInventory_types(Arrays.asList(3));
           adReq.setFloor_price(positionInfo.getSettlementPrice());//底价
    	   adReq.setH(attri.getSpaceHeight());
    	   adReq.setW(attri.getSpaceWidth());
    	   adReq.setPlace_id(String.valueOf(attri.getAdSpaceId()));//广告位 id ，由 ssp 给
    	   adReqL.add(adReq);
    	   flag=true;
       }
	   if(!flag){
		  // logger.error("zhangyou ad request,no mapping ad to specified ad space size,uid:{},space id:{},space size:{}",adreq.ogin_name,attri.getAdSpaceId(),+attri.getSpaceHeight()+"*"+attri.getSpaceWidth());
		   throw new AdPullException(ErrorCode.PARAM_ERROR, "no mapping ad to  specified ad space size,uid:"+adreq.ogin_name+",space id:"+attri.getAdSpaceId()+",space size:"+attri.getSpaceHeight()+"*"+attri.getSpaceWidth());
	   }
	   for(int i=1;i<adreq.getResult_num();i++){
		   adReqL.add(adReqL.get(0));
	   }
	   param.setAds(adReqL);
	   return param;
   }
    private List<Integer> getInventoryTypes(int h5type){
    	try{
        	switch(h5type){
	    	    case 1:
	    	    	return Arrays.asList(4);//1:图片， 2: 图文， 3: 视频， 4:html5，5: 文本， 6: 原生 , 7:html5 url
	    	    case 2:
	    	    	return Arrays.asList(1,2,7);
	    	    default:
	    	    	return Arrays.asList(1,2,4,7);
    	    }
    	}catch(Exception e){
    		return Arrays.asList(1,2,4,7);
    	}

    	   

    }
	private AdRecomReply parseResult(ZhangYouAdPullResponse resp,InvokeAttribute attribute) throws AdPullException{
		logger.info("joinDSP:zhangyou ad result,app id:{},space id:{},ZhangYouAdPullResponse:{}",attribute.getDspPosition().getText1(),attribute.getAdreq().getUserAdSpaceAttri().getAdSpaceId(),resp.toString());
		
		if(resp == null||CollectionUtils.isEmpty(resp.getAds())){
			return null;
		}
		if(resp.getResult()<0){
			throw new AdPullException(ErrorCode.INTER_ERROR, "zhangyou ad request error,msg:"+resp.getMsg()); 
			
		}
		AdRecomReply recomReply=new AdRecomReply();
		recomReply.setStatus(status);
		recomReply.setAd_contents(this.dealAds(resp, attribute));
		return recomReply;
	}
	private List<AdContent>  dealAds(ZhangYouAdPullResponse resp,InvokeAttribute attribute){
		List<AdContent> list=new ArrayList<AdContent> ();
		UserAdSpaceAttri attri = attribute.getAdreq().getUserAdSpaceAttri();
		DSPPosition positionInfo = attribute.getDspPosition();
		for(ZhangYouAdResp ad:resp.getAds()){
			
			AdContent adContent=new AdContent();
			//adContent.setAdId(InvenoIdGenerator.genThirdDspId(SystemContext.getDynamicPropertyHandler().getInt(ProxyConstants.PROXY_SERVER_NODE_INDEX,100)));
			logger.info("joinDSP:zhangyou ad result,app id:{},space id:{},zookingsoft ad id:{},zhangyou return ad id:{}",positionInfo.getText1(),attri.getAdSpaceId(),adContent.getAdId(),ad.getId(),resp.toString());
			// 类型
			AdMutiAction action = new AdMutiAction();
			//----------------------------BENGIN 常规设置----------------------
			//上报
			Map<String, List<String>> map = new HashMap<String, List<String>>();
			adContent.setMarketTitle(defTitle);
			adContent.setGuideTitle(defTitle);
			action.setGuideTitle(defTitle);
			
			if(!StringUtils.isEmpty(ad.getTarget_url())){//目标地址
				adContent.setLinkurl(ad.getTarget_url());
				action.setLinkurl(ad.getTarget_url());
			}
			
			// 点击监测
			if(CollectionUtils.isNotEmpty(ad.getClick_trackers())){
				map.put(CLICK, ad.getClick_trackers());
			}
			if(CollectionUtils.isNotEmpty(ad.getImp_trackers())){
				// 曝光监测
				map.put(SHOW, ad.getImp_trackers());
			}
			
			List<AdImg> imgs = new ArrayList<AdImg>();
			String imgUrl=ad.getImage_url();
			if(!StringUtils.isEmpty(imgUrl)){
				AdImg adImg=new AdImg();
				adImg.setSrc(imgUrl);
				imgs.add(adImg);
				adContent.setImglist(imgs);//广告图片
			}

			//-------------------------------END 常规设置-----------------------
			//Inventory_type 支持的广告资源类型 , 1:图片， 2: 图文， 3: 视频， 4:html5，5: 文本， 6: 原生 , 7:html5 url, 即一个指向 html素材页面的 urll。如果为空 ，则默认只支持 1: 图
			int action_type=-1;
			if(ad.getInventory_type()==6){//native
				ZhangYouNativeResp nativeResp=ad.get_native();
				if(CollectionUtils.isNotEmpty(nativeResp.getImptracker())){
					map.put(SHOW, nativeResp.getImptracker());
				}
	            for(ZhangYouAssetResp asset:nativeResp.getAssets()){
	            	//ZhangYouAssetResp asset=nativeResp.getAssets().get(0);
	    			ZhangYouLinkResp link=asset.getLink()==null?nativeResp.getLink():asset.getLink();
	    			if(link!=null){
	    				adContent.setLinkurl(link.getUrl());
	    				action.setLinkurl(link.getUrl());
	    				if(CollectionUtils.isNotEmpty(link.getClicktracker())){
	    					map.put(CLICK, link.getClicktracker());
	    				}
	    				
	    			}
	    			ZhangYouTitleResp title=asset.getTitle();
	    			if(title!=null){
	    				adContent.setMarketTitle(title.getText());
	    				adContent.setGuideTitle(title.getText());
	    				action.setGuideTitle(title.getText());
	    			}
	    			ZhangYouImageResp image=asset.getImg(); 
	    			if(image!=null&&!StringUtils.isEmpty(image.getUrl())){
	    				String nativeImgUrl=image.getUrl();
	    				AdImg nativeAdImg=new AdImg();
	    				nativeAdImg.setSrc(nativeImgUrl);
	    				imgs.add(nativeAdImg);
	    				adContent.setImglist(imgs);//广告图片
	    			}
	            }
				
				
			}else if(ad.getInventory_type()==3||5==ad.getAction()){//video
				ZhangYouVideo zyVideo=ad.getVideo();
				if(CollectionUtils.isNotEmpty(zyVideo.getPlayer_start_trackers())){
					// 曝光监测
					map.put(SHOW, zyVideo.getPlayer_start_trackers());
				}else if(CollectionUtils.isNotEmpty(zyVideo.getPlayer_end_trackers())){
					// 曝光监测
					map.put(SHOW, zyVideo.getPlayer_end_trackers());
				}
				if(CollectionUtils.isNotEmpty(zyVideo.getTarget_page_click_trackers())){
					map.put(CLICK, zyVideo.getTarget_page_click_trackers());
				}else if(CollectionUtils.isNotEmpty(zyVideo.getTarget_page_show_trackers())){
					map.put(CLICK, zyVideo.getTarget_page_show_trackers());
				}

				
				AdVid adVid=new AdVid();
				adVid.setSrc(zyVideo.getUrl());
				adVid.setDuration(zyVideo.getPlay_duration());
				adContent.setAdVid(adVid);
				adContent.setLinkurl(zyVideo.getUrl());
				action.setLinkurl(zyVideo.getUrl());
				action_type=ACTION_VIDEO;
			}else if(ad.getInventory_type()==2){//图文
				adContent.setMarketTitle(ad.getTitle());
				adContent.setContent(ad.getDesc());
				adContent.setGuideTitle(ad.getTitle());
				action.setGuideTitle(ad.getTitle());
			}else if(!StringUtils.isEmpty(ad.getHtml_snippet())){///html片段
			    	adContent.setIsHtmlAd(true);
			    	adContent.setHtmlSnippet(ad.getHtml_snippet());
			}

			//广告动作类型，1: 在 app 内 webview打开目标链接， 2： 在系统浏览器打开目标链接 ,3：打开 地图， 4： 拨打电话， 5：播放视频 , 6:App 下载
			if(action_type==-1){
				
				switch(ad.getAction()){
				/*内容类型1表示链接推广,2表示应用推广,3未使用 4表示电话推广 7表示视频*/
				   case 1:
					   action.setType(ACTION_WEB);	
					   break;
				   case 2:
					   action.setType(ACTION_WEB);	
					   break;
				   case 4:
					   action.setType(ACTION_CALL);	
					   break;
				   case 5:
					   action.setType(ACTION_VIDEO);	
					   break;
				   case 6:
					   action.setType(ACTION_APP);	
					   break;
				   default:
					   action.setType(ACTION_UNKOWN);	
					   break;
					 
				}
			}

			adContent.setMutiAction(Collections.singletonList(action));
			adContent.setThirdReportLinks(map);
			list.add(adContent);
		}
		return list;
	}
	public ZhangYouAdPuller getZhangYouAdPuller() {
		return zhangYouAdPuller;
	}
	public void setZhangYouAdPuller(ZhangYouAdPuller zhangYouAdPuller) {
		this.zhangYouAdPuller = zhangYouAdPuller;
	}

}
