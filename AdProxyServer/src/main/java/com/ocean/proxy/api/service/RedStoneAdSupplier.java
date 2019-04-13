package com.ocean.proxy.api.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inveno.distribute.v3.utils.StringUtils;
import com.inveno.util.CollectionUtils;
import com.ocean.core.common.system.ErrorCode;
import com.ocean.core.common.system.SystemContext;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.persist.api.proxy.AdPullException;
import com.ocean.persist.api.proxy.JoinDSPEmu;
import com.ocean.persist.api.proxy.redstone.RedStoneAdPullParams;
import com.ocean.persist.api.proxy.redstone.RedStoneApp;
import com.ocean.persist.api.proxy.redstone.RedStoneBanner;
import com.ocean.persist.api.proxy.redstone.RedStoneDevice;
import com.ocean.persist.api.proxy.redstone.RedStoneImp;
import com.ocean.persist.api.proxy.redstone.RedStoneNative;
import com.ocean.persist.api.proxy.redstone.RedStoneNativeRequest;
import com.ocean.persist.api.proxy.redstone.RedStoneAdPullResponse;
import com.ocean.persist.api.proxy.redstone.RedStoneAdPuller;
import com.ocean.persist.api.proxy.redstone.RedStoneNativeAsset;
import com.ocean.persist.api.proxy.redstone.RedStoneNativeAssetResp;
import com.ocean.persist.api.proxy.redstone.RedStoneNativeImg;
import com.ocean.persist.api.proxy.redstone.RedStoneNativeImgResp;
import com.ocean.persist.api.proxy.redstone.RedStoneNativeLink;
import com.ocean.persist.api.proxy.redstone.RedStoneNativeTitle;
import com.ocean.persist.api.proxy.redstone.RedStoneNativeTitleResp;
import com.ocean.persist.api.proxy.redstone.RedStoneNativeVideo;
import com.ocean.persist.api.proxy.xianguo.XianguoAdPuller;
import com.ocean.persist.api.proxy.xianguo.XinGuoV204;
import com.ocean.persist.api.proxy.xianguo.XinGuoV204.AdType;
import com.ocean.persist.api.proxy.xianguo.XinGuoV204.ConnectionType;
import com.ocean.persist.api.proxy.xianguo.XinGuoV204.SpType;
import com.ocean.persist.api.proxy.yijifen.YijifenAdContent;
import com.ocean.persist.api.proxy.yijifen.YijifenAdData;
import com.ocean.persist.api.proxy.yijifen.YijifenNativeCreative;
import com.ocean.persist.api.proxy.yijifen.YijifenNativeTempAttr;
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


/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年2月3日 
      @version 1.0 
 */
@Component(value="redStoneAdSupplier")
public class RedStoneAdSupplier   extends AbstractAdSupplier{
    @Autowired
	private RedStoneAdPuller redStoneAdPuller;
	private static final Map<AdSpaceType, Integer> adtype = new HashMap<AdSpaceType, Integer>();
    @Autowired
	private XianguoAdPuller xianguoAdPuller;
    /*
     *  0- 横幅广告
		1- 插屏或全插屏广告
		4- 开屏广告
		5- 视频广告
		6- 原生广告
     * */
	static{
		
		adtype.put(AdSpaceType.BANNER, 0);
		adtype.put(AdSpaceType.INFOFLOW, 6);
		adtype.put(AdSpaceType.INTERSTITIAL, 1);
		adtype.put(AdSpaceType.OPENING, 4);
	}
	public AdRecomReply invoke(InvokeAttribute attribute)
			throws AdPullException {
		// TODO Auto-generated method stub
		AdRecomReq adreq = attribute.getAdreq();
		DSPPosition positionInfo = attribute.getDspPosition();
		RedStoneAdPullParams params = parseParams(adreq, positionInfo);
		RedStoneAdPullResponse response = (RedStoneAdPullResponse)redStoneAdPuller.api(params,adreq.getHash(),String.valueOf(adreq.getUserAdSpaceAttri().getAdSpaceId()));	
		return parseResult(response, attribute);

		// 参数转换
		
	}
   private RedStoneAdPullParams parseParams(AdRecomReq adreq,DSPPosition positionInfo) throws AdPullException{
	   UserAdSpaceAttri attri=adreq.getUserAdSpaceAttri();
	   AdUserInfo user=adreq.getUserinfo();
	   RedStoneAdPullParams param=new RedStoneAdPullParams();
	   param.setId(positionInfo.getPos());
	   //imp
	   RedStoneImp imp=new RedStoneImp();
	   if(StringUtils.isEmpty(positionInfo.getText1())||StringUtils.isEmpty(positionInfo.getText2())){
		   throw new AdPullException("app id or app package name is empty:proxy configure  Text1|Text2 is empty!");
	   }
	   imp.setId(SystemContext.getDynamicPropertyHandler().get(ProxyConstants.REDSTONE_IMP_ID));
	   if(AdSpaceType.BANNER==AdSpaceType.findByValue(positionInfo.getPosType())
	      ||AdSpaceType.INTERSTITIAL==AdSpaceType.findByValue(positionInfo.getPosType())){
		   RedStoneBanner banner=new RedStoneBanner();
		   banner.setH(attri.getSpaceHeight());
		   banner.setW(attri.getSpaceWidth());
		   imp.setBanner(banner);
		   
	   }else if(AdSpaceType.INFOFLOW==AdSpaceType.findByValue(positionInfo.getPosType())){
		   RedStoneNative _native=new RedStoneNative();
		   _native.setRequest(parseParams_native(adreq));
		   _native.setVer("1.0");
		   imp.set_native(_native);
		   
	   }
	   /*
	    *   0- 横幅广告
			1- 插屏或全插屏广告
			4- 开屏广告
			5- 视频广告
			6- 原生广告
	    * */
	   Integer adType=adtype.get(AdSpaceType.findByValue(positionInfo.getPosType()));
	   adType=adType==null?0:adType;
	   imp.setInstl(adType);
	   imp.setTagid(UUID.randomUUID().toString().replaceAll("-", ""));
	   
	   int bidType=SystemContext.getDynamicPropertyHandler().getInt(ProxyConstants.REDSTONE_BID_TYPE,2); 
	   param.setAt(bidType);
	   if(bidType!=2){
		   imp.setBidfloor(positionInfo.getSettlementPrice());
	   }
	   param.setImp(Collections.singletonList(imp));
	   
	   RedStoneApp app=new RedStoneApp();
	   app.setId(positionInfo.getText1());
	   app.setBundle(positionInfo.getText1());
	   app.setName(adreq.getApp());
	   app.setVer(adreq.getVersion());
	   
	   RedStoneDevice device=new RedStoneDevice();
	   device.setUa(user.getUa());
	   device.setIp(user.getClient_ip());
/*	   String osCode = user.getOs();
	   String os = "wp";
	   if(OS_IOS.equals(osCode)){
			// ios 必填信息
			device.setIdfa(user.getIdfa());
			os="ios";
	   }else if(OS_ANDROID.equals(osCode)){
			device.setDidsha1(user.getImei());
			device.setDidmd5(DigestUtils.md5Hex(user.getAdid()));
			if(!StringUtils.isEmpty(user.getAdid())){
				device.setDpidsha1(user.getAdid());
				device.setDpidmd5(DigestUtils.md5Hex(user.getAdid()));
			}

			os="android";
		}*/
	   
	   return param;
	}
	private AdRecomReply parseResult(RedStoneAdPullResponse resp,InvokeAttribute attribute) throws AdPullException{
		AdRecomReply reply=new AdRecomReply();
	   return reply;
	   
   }
   private RedStoneNativeRequest parseParams_native(AdRecomReq adreq) throws AdPullException{
	   RedStoneNativeRequest param=new RedStoneNativeRequest();
	   param.setVer("1.0");//当前api协议版本
	   param.setLayout(6);//信息流
	   param.setAdunit(3);
	   UserAdSpaceAttri attri=adreq.getUserAdSpaceAttri();
      //set assets
	   List<RedStoneNativeAsset> assets=new ArrayList<RedStoneNativeAsset>(2);
	   RedStoneNativeAsset asstTitle=new RedStoneNativeAsset();
	   RedStoneNativeTitle title=new RedStoneNativeTitle();
	   title.setLen(convertV(attri.getTitleMax(),30));
	   asstTitle.setTitle(title);
	   assets.add(asstTitle);
	   
	   RedStoneNativeAsset asstImg=new RedStoneNativeAsset();
	   RedStoneNativeImg img=new RedStoneNativeImg();
	   img.setType(3);//大图
	   img.setH(attri.getSpaceHeight());
	   img.setW(attri.getSpaceWidth());
	   asstImg.setImg(img);
	   assets.add(asstImg);
	   return param;
   }
   private RedStoneNativeRequest parseParams_banner(AdRecomReq adreq,DSPPosition positionInfo) throws AdPullException{
	   return null;
   }
	private AdRecomReply parseResult_native(RedStoneAdPullResponse resp,InvokeAttribute attribute) throws AdPullException{
        if(resp==null||CollectionUtils.isEmpty(resp.getAssets())){
        	return null;
        }
        RedStoneNativeAssetResp asset=resp.getAssets().get(0);
		AdRecomReply recomReply = new AdRecomReply();
		recomReply.setStatus(status);

        
		AdContent content = new AdContent();

		//content.setAdId(InvenoIdGenerator.genThirdDspId(SystemContext.getDynamicPropertyHandler().getInt(ProxyConstants.PROXY_SERVER_NODE_INDEX,100)));
        AdMutiAction action = new AdMutiAction();
		//--------------------BEGIN 常规设置----------------------
		content.setMarketTitle(defTitle);
		content.setGuideTitle(defTitle);
		action.setType(ACTION_WEB);
		action.setGuideTitle(defTitle);
		//--------------------END 常规设置------------------------
		//title
		RedStoneNativeTitleResp title=asset.getTitle();
		if(title!=null){
			content.setMarketTitle(title.getText());
			content.setGuideTitle(title.getText());
			action.setGuideTitle(title.getText());
		}
		List<AdImg> imgs = new ArrayList<AdImg>();
		//img
		RedStoneNativeImgResp img=asset.getImg();
		if(img!=null){
			AdImg adimg = new AdImg();
			adimg.setSrc(img.getUrl());
			adimg.setHeight(img.getH());
			adimg.setWidth(img.getW());
			imgs.add(adimg);
		}
		
		//link
		RedStoneNativeLink link=asset.getLink();
		if(link!=null){
			content.setLinkurl(link.getUrl());
			action.setLinkurl(link.getUrl());
		}
		

		
		//点击上报
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		// 点击监测
		List<String> click =link.getClicktrackers() ;
		if(click == null){
			click = new ArrayList<String>();
		}
		map.put(CLICK, click);
		
		// 曝光监测
		List<String> show = resp.getImptrackers();
		map.put(SHOW, show);
		content.setThirdReportLinks(map);

		// 图片地址
		content.setImglist(imgs);
		content.setMutiAction(Collections.singletonList(action));
		recomReply.setAd_contents(Collections.singletonList(content));
		
		return recomReply;
	}
	public RedStoneAdPuller getRedStoneAdPuller() {
		return redStoneAdPuller;
	}
	public void setRedStoneAdPuller(RedStoneAdPuller redStoneAdPuller) {
		this.redStoneAdPuller = redStoneAdPuller;
	}

}
