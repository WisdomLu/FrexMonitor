package com.ocean.proxy.api.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inveno.util.CollectionUtils;
import com.ocean.core.common.system.ErrorCode;
import com.ocean.core.common.system.SystemContext;
import com.ocean.persist.api.proxy.AdPullException;
import com.ocean.persist.api.proxy.ruangaoyun.RuanGaoYunAdPullParam;
import com.ocean.persist.api.proxy.ruangaoyun.RuanGaoYunAdPullResponse;
import com.ocean.persist.api.proxy.ruangaoyun.RuanGaoYunAdPuller;
import com.ocean.persist.api.proxy.ruangaoyun.RuanGaoYunApp;
import com.ocean.persist.api.proxy.ruangaoyun.RuanGaoYunBanner;
import com.ocean.persist.api.proxy.ruangaoyun.RuanGaoYunDevice;
import com.ocean.persist.api.proxy.ruangaoyun.RuanGaoYunGeo;
import com.ocean.persist.api.proxy.ruangaoyun.RuanGaoYunImg;
import com.ocean.persist.api.proxy.ruangaoyun.RuanGaoYunImp;
import com.ocean.persist.api.proxy.ruangaoyun.RuanGaoYunReport;
import com.ocean.persist.api.proxy.ruangaoyun.RuanGaoYunSite;
import com.ocean.persist.api.proxy.ruangaoyun.RuanGaoYunUser;
import com.ocean.persist.api.proxy.ruangaoyun.RuanGaoYunVideo;
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
import com.ocean.proxy.thrift.entity.ExpectedMarketType;
import com.ocean.proxy.thrift.entity.UserAdSpaceAttri;
/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年4月1日 
      @version 1.0 
 */
@Component(value="ruanGaoYunAdSupplier")
public class RuanGaoYunAdSupplier    extends AbstractAdSupplier{
	@Autowired
    private RuanGaoYunAdPuller ruanGaoYunAdPuller;
	public AdRecomReply invoke(InvokeAttribute attribute)
			throws AdPullException {
		// TODO Auto-generated method stub
		AdRecomReq adreq = attribute.getAdreq();
		DSPPosition positionInfo = attribute.getDspPosition();
		RuanGaoYunAdPullParam params = parseParams(adreq, positionInfo);
		RuanGaoYunAdPullResponse response = (RuanGaoYunAdPullResponse)ruanGaoYunAdPuller.api(params,adreq.getHash(),String.valueOf(adreq.getUserAdSpaceAttri().getAdSpaceId()));	
		return parseResult(response, attribute);
	}
	private RuanGaoYunAdPullParam parseParams(AdRecomReq adreq, DSPPosition positionInfo) throws AdPullException{
		RuanGaoYunAdPullParam param=new RuanGaoYunAdPullParam();
		String bidId=UUID.randomUUID().toString().replaceAll("-", "");
		param.setId(bidId);
		param.setVersion("1.1");
		//site
		RuanGaoYunSite site=new RuanGaoYunSite();
		if(StringUtils.isNotEmpty(positionInfo.getText1())){
			   site.setId(positionInfo.getText1());
		}else{
			throw new AdPullException(ErrorCode.PARAM_ERROR,"joinDSP:ruangaoyun site id is empty!");
		}
		site.setName("乐点日历");
        param.setSite(site);
        //impression
        RuanGaoYunImp imp=new RuanGaoYunImp();
    	String impId=UUID.randomUUID().toString().replaceAll("-", "");
        imp.setId(impId);
        imp.setTagid(positionInfo.getPos());//广告位id
        UserAdSpaceAttri attr= adreq.getUserAdSpaceAttri();
        boolean banner_flag=true;
		if(positionInfo.getPosType() == AdSpaceType.INFOFLOW.getValue()||positionInfo.getPosType() == AdSpaceType.INTERSTITIAL.getValue()||positionInfo.getPosType() == AdSpaceType.OPENING.getValue()||positionInfo.getPosType() ==AdSpaceType.BANNER.getValue()){
			banner_flag=true;
		}else if(attr.isSetExpectedMarketTypes()&&attr.getExpectedMarketTypes().contains(ExpectedMarketType.VIDEO)){
			banner_flag=false;
		}
		if(banner_flag){
			Map<String,Integer> sizeM=getAdapterSize(positionInfo.getPosType());
			RuanGaoYunBanner banner=new RuanGaoYunBanner();
			if(sizeM==null){
				banner.setW(attr.getSpaceWidth());
				banner.setH(attr.getSpaceHeight());
			}else{
				banner.setW(sizeM.get("w"));
				banner.setH(sizeM.get("h"));
			}
			banner.setPos(7);//广告位未知，0：pc端默认；7：开屏；
			imp.setBanner(banner);
		}else{
			RuanGaoYunVideo video=new RuanGaoYunVideo();
			video.setPos(7);//广告位未知，0：pc端默认；7：开屏；
            video.setMinduration(1);
            video.setMaxduration(5*60);
            video.setH(attr.getSpaceHeight());
            video.setW(attr.getSpaceWidth());
            imp.setVideo(video);
		}
		param.setImp(imp);
		//app
		RuanGaoYunApp app=new RuanGaoYunApp();
		app.setId(positionInfo.getText3());//app packagename
		if(StringUtils.isNotEmpty(adreq.getApp())){
			app.setName(adreq.getApp());
		}
		if(StringUtils.isNotEmpty(adreq.getVersion())){
			app.setVer(adreq.getVersion());
		}
		param.setApp(app);
		AdUserInfo  userInfo=adreq.getUserinfo();
		//device
		RuanGaoYunDevice device=new RuanGaoYunDevice();
		String osCode = userInfo.getOs();
		if(StringUtils.isNotEmpty(userInfo.getAdid())){
			device.setId(userInfo.getAdid());
		}else{
			device.setId(userInfo.getImei());
		}
		if(StringUtils.isNotEmpty(userInfo.getMac())&&!userInfo.getMac().equals(userInfo.getImei())){
			device.setMac(userInfo.getMac());
		}
		if(OS_IOS.equals(osCode)){
			// ios 必填信息
			device.setDpid(userInfo.getIdfa());
		}else if(OS_ANDROID.equals(osCode)){
			device.setDevicetype(3);//设备类型，1=iPhone；2=iPad；3=android
			device.setDpid(userInfo.getImei());
		}
        device.setOs(osCode);
		if(StringUtils.isNotEmpty(userInfo.getUa())){
			device.setUa(userInfo.getUa());
		}
		if(StringUtils.isNotEmpty(userInfo.getClient_ip())){
			device.setIp(userInfo.getClient_ip());
		}
		if(StringUtils.isNotEmpty(userInfo.getMobile())){
			String carrier=mobiles.get(userInfo.getMobile());
			device.setCarrier(carrier==null?"46004":carrier);
		}
        if(StringUtils.isNoneEmpty(userInfo.getPhonemodel())){
        	device.setModel(userInfo.getPhonemodel());
        }
		if(StringUtils.isNoneEmpty(userInfo.getOsversion())){
			device.setOsv(userInfo.getOsversion());
		}
		if(StringUtils.isNoneEmpty(adreq.getNet())){

			device.setConnectiontype(adreq.getNet().equals(NETWORK_WIFI)?1:2);
		}else{
			device.setConnectiontype(1);
		}
		
		RuanGaoYunGeo geo=new RuanGaoYunGeo();
		if(StringUtils.isNotEmpty(userInfo.getLat())&&StringUtils.isNotEmpty(userInfo.getLon())) {
			geo.setLat(Float.parseFloat(userInfo.getLat()));
			geo.setLon(Float.parseFloat(userInfo.getLon()));
			device.setGeo(geo);
		}
		param.setDevice(device);
		//user
		RuanGaoYunUser user=new RuanGaoYunUser();
		user.setId(userInfo.getImei());
		param.setUser(user);
		return param;
	}
	private Map<String,Integer> getAdapterSize(int posType){
		try{
			String spaceSize=SystemContext.getDynamicPropertyHandler().get(ProxyConstants.RUANGAOYUN_ADAPTER_ADSPACE_SIZE);
			if(StringUtils.isEmpty(spaceSize)){
				return null;
			}
			String[] spaceSizeArr=spaceSize.split("::");
			Map<String,Integer> sizeM;
			Integer w=0;
			Integer h=0;
			switch(posType){
				case 1://banner
				    w=Integer.parseInt(spaceSizeArr[0].split("\\*")[0]);
				    h=Integer.parseInt(spaceSizeArr[0].split("\\*")[1]);
				    break;
				case 4://inflow
					if(spaceSizeArr.length>1){
					    w=Integer.parseInt(spaceSizeArr[1].split("\\*")[0]);
					    h=Integer.parseInt(spaceSizeArr[1].split("\\*")[1]);
					}
				    break;
				case 2://opening
					if(spaceSizeArr.length>2){
					    w=Integer.parseInt(spaceSizeArr[2].split("\\*")[0]);
					    h=Integer.parseInt(spaceSizeArr[2].split("\\*")[1]);
					}
				    break;
				default:
	               return null;

		       }
		    
			    if(w>0&&h>0){
			    	sizeM=new HashMap<String,Integer>();
				    sizeM.put("w", w);
				    sizeM.put("h", h);
				    return sizeM;
			    }
			  
		}catch(Exception e){
			return null;
		}
		return null;
	}
	private AdRecomReply parseResult(RuanGaoYunAdPullResponse response,InvokeAttribute attribute)
			throws AdPullException {
		
		if(response == null){
			return null;
		}
		// 返回对象
		AdRecomReply recomReply = new AdRecomReply();
		AdContent content = new AdContent();
		AdMutiAction action = new AdMutiAction();
		recomReply.setStatus(status);
        //-------------BEGIN 常规设置----------------
		String title = response.getTitle();
		if(StringUtils.isEmpty(title)){
			title = defTitle;
		}
		content.setMarketTitle(title);
		if(StringUtils.isNotEmpty(response.getDesc())){
			content.setGuideTitle(response.getDesc());
		}else{
			content.setGuideTitle(title);
		}
		action.setGuideTitle(title);
        //-------------END 常规设置----------------	
        UserAdSpaceAttri attr= attribute.getAdreq().getUserAdSpaceAttri();
        DSPPosition positionInfo=attribute.getDspPosition();
        boolean banner_flag=true;
		if(positionInfo.getPosType() == AdSpaceType.INFOFLOW.getValue()||positionInfo.getPosType() == AdSpaceType.INTERSTITIAL.getValue()||positionInfo.getPosType() == AdSpaceType.OPENING.getValue()||positionInfo.getPosType() ==AdSpaceType.BANNER.getValue()){
			banner_flag=true;
		}else if(attr.isSetExpectedMarketTypes()&&attr.getExpectedMarketTypes().contains(ExpectedMarketType.VIDEO)){
			banner_flag=false;
		}
		if(!banner_flag){//暂时不接视频广告
			throw new AdPullException("video interface have not been developed!");
		}
		
		//落地页
		String linkurl = response.getTarget();//落地页
		content.setLinkurl(linkurl);
		action.setLinkurl(linkurl);
		//-------------BEGIN 上报设置---------------
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		// 点击监测
		List<String> click = new ArrayList<String>(response.getClick().size());
		if(CollectionUtils.isNotEmpty(response.getClick())){
			for(RuanGaoYunReport report:response.getClick()){
				if(StringUtils.isNotEmpty(report.getUrl())){
					click.add(report.getUrl());
				}
			}
		}
		map.put(CLICK, click);
		
		// 曝光监测
		List<String> show = new ArrayList<String>(response.getPv().size());
		if(CollectionUtils.isNotEmpty(response.getPv())){
			for(RuanGaoYunReport report:response.getPv()){
				if(StringUtils.isNotEmpty(report.getUrl())){
					show.add(report.getUrl());
				}
			}
		}
		map.put(SHOW, show);
		content.setThirdReportLinks(map);
		//-------------END 上报设置---------------
		
		//-------------BEGIN 物料信息 -------------
		List<AdImg> imgs = new ArrayList<AdImg>();
		AdImg img = new AdImg();
		img.setSrc(response.getAd_url());
		img.setWidth(response.getWidth());
		img.setHeight(response.getHeight());
		imgs.add(img);
		content.setImglist(imgs);
		//-------------END 物料信息 -------------
		if(response.getAd_type() == 0){
			action.setType(ACTION_WEB);
		}
		else{
			action.setType(ACTION_APP);
		}
		content.setMutiAction(java.util.Collections.singletonList(action));
		recomReply.setAd_contents(java.util.Collections.singletonList(content));
		return recomReply;
	}
}
