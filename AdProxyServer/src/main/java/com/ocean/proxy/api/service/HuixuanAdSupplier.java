package com.ocean.proxy.api.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inveno.util.CollectionUtils;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
import com.ocean.persist.api.proxy.AdPullException;
import com.ocean.persist.api.proxy.diankai.DiankaiAd;
import com.ocean.persist.api.proxy.diankai.DiankaiAdPullResponse;
import com.ocean.persist.api.proxy.huixuan.HuixuanAdApp;
import com.ocean.persist.api.proxy.huixuan.HuixuanAdAssets;
import com.ocean.persist.api.proxy.huixuan.HuixuanAdBanner;
import com.ocean.persist.api.proxy.huixuan.HuixuanAdBid;
import com.ocean.persist.api.proxy.huixuan.HuixuanAdDevice;
import com.ocean.persist.api.proxy.huixuan.HuixuanAdImg;
import com.ocean.persist.api.proxy.huixuan.HuixuanAdImp;
import com.ocean.persist.api.proxy.huixuan.HuixuanAdLink;
import com.ocean.persist.api.proxy.huixuan.HuixuanAdNative;
import com.ocean.persist.api.proxy.huixuan.HuixuanAdPullParams;
import com.ocean.persist.api.proxy.huixuan.HuixuanAdPullResponse;
import com.ocean.persist.api.proxy.huixuan.HuixuanAdPuller;
import com.ocean.persist.api.proxy.huixuan.HuixuanAdRespAssets;
import com.ocean.persist.api.proxy.huixuan.HuixuanAdRespNative;
import com.ocean.persist.api.proxy.huixuan.HuixuanAdSeatbid;
import com.ocean.persist.api.proxy.huixuan.HuixuanAdVideo;
import com.ocean.persist.model.proxy.DSPPosition;
import com.ocean.proxy.api.base.AbstractAdSupplier;
import com.ocean.proxy.thrift.entity.AdContent;
import com.ocean.proxy.thrift.entity.AdImg;
import com.ocean.proxy.thrift.entity.AdMutiAction;
import com.ocean.proxy.thrift.entity.AdRecomReply;
import com.ocean.proxy.thrift.entity.AdRecomReq;
import com.ocean.proxy.thrift.entity.AdUserInfo;
import com.ocean.proxy.thrift.entity.AdVid;
import com.ocean.proxy.thrift.entity.ExpectedMarketType;
import com.ocean.proxy.thrift.entity.UserAdSpaceAttri;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年6月16日 
      @version 1.0 
 */
@Component(value="huixuanAdSupplier")
public class HuixuanAdSupplier   extends AbstractAdSupplier{
	private static final Map<String, Integer> conn = new HashMap<String, Integer>();

	static{	
		conn.put(NETWORK_2G, 4);
		conn.put(NETWORK_3G, 5);
		conn.put(NETWORK_4G, 6);
		conn.put(NETWORK_WIFI, 2);
	}
	@Autowired
	private HuixuanAdPuller huixuanAdPuller;
	public AdRecomReply invoke(InvokeAttribute attribute)
			throws AdPullException {
		// TODO Auto-generated method stub
		AdRecomReq adreq = attribute.getAdreq();
		DSPPosition positionInfo = attribute.getDspPosition();
		// 参数转换
		HuixuanAdPullParams params = parseParams(adreq, positionInfo);
		// 调用API
		HuixuanAdPullResponse response = (HuixuanAdPullResponse)huixuanAdPuller.api(params,adreq.getHash(),String.valueOf(adreq.getUserAdSpaceAttri().getAdSpaceId()));
		// 解析结果
		return parseResult(response);
	}
	private AdRecomReply parseResult(HuixuanAdPullResponse response)
			throws AdPullException {
		
		if(response == null){
			return null;
		}
		if(CollectionUtils.isEmpty(response.getSeatbid())){
			return null;
		}
		AdRecomReply reply=new AdRecomReply();
		reply.setStatus(status);
		reply.setAd_contents(this.dealAds(response));
		return reply;
	}
	private List<AdContent>  dealAds(HuixuanAdPullResponse response){
		List<AdContent> list=new ArrayList<AdContent> ();
		HuixuanAdSeatbid setBid=response.getSeatbid().get(0);
        if(CollectionUtils.isEmpty(setBid.getBid())){
        	return null;
        }
		for(HuixuanAdBid bid:setBid.getBid()){
			//***********************BEGIN 常规设置*************
			AdContent content = new AdContent();
			AdMutiAction action = new AdMutiAction();
			String title = defTitle;
			// 内容类型1表示链接推广,2表示应用推广,3未使用 4表示电话推广
		 
	        int acType=ACTION_WEB;
			//***********************END 常规设置***************
			List<AdImg> imgs = new ArrayList<AdImg>();
			AdImg img = new AdImg();
			img.setSrc(bid.getIurl());
			imgs.add(img);
			
	        //banner广告上报
			if(StringUtils.isNotEmpty(bid.getClkurl())){
				content.setLinkurl(bid.getClkurl());
				action.setLinkurl(bid.getClkurl());
			}else if(StringUtils.isNotEmpty(bid.getClkurl302())){
				content.setLinkurl(bid.getClkurl302());
				action.setLinkurl(bid.getClkurl302());
			}
			
			if(bid.getAction_type()==6){
				acType=ACTION_APP;
			}
				
				
				
			//上报
			Map<String, List<String>> map = new HashMap<String, List<String>>();
			List<String> reportShow=new ArrayList<String>();
			List<String> reportClick=new ArrayList<String>();
			if(CollectionUtils.isNotEmpty(bid.getImptrackers())){
				reportShow.addAll(bid.getImptrackers());
				
			}
			if(CollectionUtils.isNotEmpty(bid.getClktrackers())){
				reportClick.addAll(bid.getClktrackers());
			
			}
			if(bid.get_native()!=null){//  原生广告
				HuixuanAdRespNative _native=bid.get_native();
				if(CollectionUtils.isNotEmpty(_native.getAssets())){
					HuixuanAdRespAssets asset=_native.getAssets().get(0);
					if(asset.getTitle()!=null&&StringUtils.isNotEmpty(asset.getTitle().getText())){//标题
						title=asset.getTitle().getText();
					}
					HuixuanAdImg huixuanImg=asset.getImg();
					if(huixuanImg!=null&&CollectionUtils.isNotEmpty(huixuanImg.getUrls())){//图片
						for(String url:huixuanImg.getUrls()){
							AdImg img2 = new AdImg();
							img2.setSrc(url);
							imgs.add(img2);
						}
					
						
					}
					if(asset.getVideo()!=null&&StringUtils.isNotEmpty(asset.getVideo().getUrl())){//视频广告
						HuixuanAdVideo video=asset.getVideo();
						AdVid adVid=new AdVid();
						adVid.setSrc(video.getUrl());
						adVid.setDuration(video.getDuration());
						content.setAdVid(adVid);
						content.setLinkurl(video.getUrl());
						action.setLinkurl(video.getUrl());
						acType=ACTION_VIDEO;
					}
				}

				HuixuanAdLink link=_native.getLink();
				if(link!=null){//落地页和上报地址
					if(StringUtils.isNotEmpty(link.getClkurl())){
						content.setLinkurl(link.getClkurl());
						action.setLinkurl(link.getClkurl());
					}else if(StringUtils.isNotEmpty(link.getClkurl302())){
						content.setLinkurl(link.getClkurl302());
						action.setLinkurl(link.getClkurl302());
					}
					
					if(CollectionUtils.isNotEmpty(link.getClktrackers())){
						reportClick.addAll(link.getClktrackers());
					}
				}
				if(CollectionUtils.isNotEmpty(_native.getImptrackers())){
					reportShow.addAll(_native.getImptrackers());
				}
				
			}
	        
			//上报
			map.put(SHOW,reportShow);
			map.put(CLICK, reportClick);
			content.setThirdReportLinks(map);
			//img
			content.setImglist(imgs);
			
			
		    action.setType(acType); 
			content.setMarketTitle(title);
			content.setGuideTitle(title);
			content.setMutiAction(Collections.singletonList(action));

			list.add(content);
		}
		return list;
	}
	private HuixuanAdPullParams parseParams(AdRecomReq adreq, 
			DSPPosition positionInfo) throws AdPullException {
		
		if(StringUtils.isEmpty(positionInfo.getText1())||StringUtils.isEmpty(positionInfo.getText2())){
			throw  new AdPullException("media id or package name is empty!");
		}
		UserAdSpaceAttri attri=adreq.getUserAdSpaceAttri();
		AdUserInfo user=adreq.getUserinfo();
		HuixuanAdPullParams param=new HuixuanAdPullParams();
		param.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		//device
		HuixuanAdDevice device =new HuixuanAdDevice();
		device.setUa(user.getUa());
		device.setIp(user.getClient_ip());
		if(StringUtils.isNotEmpty(user.getMobile())){
			device.setCarrier(user.getMobile());
		}
		if(StringUtils.isNotEmpty(user.getBrand_name())){
			device.setMake(user.getBrand_name());
		}
		if(StringUtils.isNotEmpty(user.getBrand_id())){
			device.setModel(user.getBrand_id());
		}
		String osCode = user.getOs();
		device.setOs(osCode);
		device.setW(user.getDevice_width());
		device.setH(user.getDevice_height());
		Integer net=conn.get(adreq.getNet());
		device.setConnectiontype(net==null?2:net);
		device.setDevicetype(1);
		if(StringUtils.isNotEmpty(user.getMac())){
			device.setMac(user.getMac());
			device.setMacmd5(DigestUtils.md5Hex(user.getMac()));
		}
		if(StringUtils.isNotEmpty(user.getImei())){
			device.setImei(user.getImei());
		}
		if(StringUtils.isNotEmpty(user.getAdid())){
			device.setAndroidid(user.getAdid());
		}
		if(StringUtils.isNotEmpty(user.getAaid())){
			device.setAaid(user.getAaid());
		}
		param.setDevice(device);
		//app
		
		HuixuanAdApp app =new HuixuanAdApp();
		app.setId(String.valueOf(attri.getAppId()));
		app.setName(adreq.getApp());
		app.setVer(adreq.getVersion());
        app.setBundle(positionInfo.getText2());	
        param.setApp(app);
		
        
        //imp
        HuixuanAdImp imp=new HuixuanAdImp();
        imp.setId(System.currentTimeMillis()+""+new Random().nextInt(10000));
        imp.setTagid(positionInfo.getPos());
        imp.setInstl(getPosType(positionInfo.getPosType()));
        imp.setBidfloor(positionInfo.getSettlementPrice());
        if(positionInfo.getPosType()==4){
        	/*原生广告布局样式（1：文本内容列表；2：app列表；3：新闻提要；4：聊天列表；5：走马灯；6：信息流；7：网格）*/
        	HuixuanAdNative _native=new HuixuanAdNative();
        	_native.setLayout(6);
        	
        	HuixuanAdAssets asset=new HuixuanAdAssets();
        	asset.setId(1);
        	HuixuanAdImg img=new HuixuanAdImg();
        	img.setW(attri.getSpaceWidth());
        	img.setH(attri.getSpaceHeight());
        	img.setType(3);//图片类型（1：icon；2：logo；3：主图）
        	asset.setImg(img);
        	
        	_native.setAssets(Collections.singletonList(asset));
        	imp.set_native(_native);
        }else{
        	//banner
            	HuixuanAdBanner banner=new HuixuanAdBanner();
            	banner.setH(attri.getSpaceHeight());
            	banner.setW(attri.getSpaceWidth());
            	banner.setMimes(Arrays.asList(1,3));
            	imp.setBanner(banner);
            
        }
        if(attri.getExpectedMarketTypes().size()==1&&attri.getExpectedMarketTypes().contains(ExpectedMarketType.VIDEO)){
        	HuixuanAdVideo video=new HuixuanAdVideo();
        	video.setW(attri.getSpaceWidth());
        	video.setH(attri.getSpaceHeight());
        	video.setMimes(Arrays.asList(1,2));
        	imp.setVideo(video);
        }
       
        /*
         * 广告动作类型：
			1: 在app内webview打开目标链接，
			2：在系统浏览器打开目标链接
			3：打开地图
			4：拨打电话
			5：播放视频
			6:  App下载
			7：发送短信
			8：打开应用

         * */
        imp.setAction_type(Arrays.asList(1,2,6));
        param.setImp(Collections.singletonList(imp));
        param.setTs((int)(System.currentTimeMillis()/1000));
        param.setMedia_id(Integer.parseInt(positionInfo.getText1()));
		return param;
	}
	/*
	 * 展现形式：
		1：banner;
		2：video;
		3：背投;
		4：视频暂停;
		5：弹窗;
		6：视频悬浮;
		7：开屏;
		8：插屏;
		9：应用墙;
		10：信息流

	 * 
	 * */
	private int getPosType(int type){
		
		switch(type){
		   case 2:
			   return 7;
		   case 3:
			   return 8;
		   case 4:
			   return 10;
		   default:
			   return 1;
		}
	}
	public HuixuanAdPuller getHuixuanAdPuller() {
		return huixuanAdPuller;
	}
	public void setHuixuanAdPuller(HuixuanAdPuller huixuanAdPuller) {
		this.huixuanAdPuller = huixuanAdPuller;
	}

}
