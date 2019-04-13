package com.ocean.proxy.api.service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.inveno.util.CollectionUtils;
import com.ocean.core.common.system.SystemContext;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
import com.ocean.core.common.threadpool.workthread.Task.DataFormat;
import com.ocean.core.common.threadpool.workthread.Task.InvokeType;
import com.ocean.persist.api.proxy.AdPullException;
import com.ocean.persist.api.proxy.maijike.MaijikeUrlFmt;
import com.ocean.persist.api.proxy.zhuoyiV2.ZhuiyiV2Version;
import com.ocean.persist.api.proxy.zhuoyiV2.ZhuoyiV2Ad;
import com.ocean.persist.api.proxy.zhuoyiV2.ZhuoyiV2AdPullReq;
import com.ocean.persist.api.proxy.zhuoyiV2.ZhuoyiV2AdPullResp;
import com.ocean.persist.api.proxy.zhuoyiV2.ZhuoyiV2Adslot;
import com.ocean.persist.api.proxy.zhuoyiV2.ZhuoyiV2App;
import com.ocean.persist.api.proxy.zhuoyiV2.ZhuoyiV2Cellular;
import com.ocean.persist.api.proxy.zhuoyiV2.ZhuoyiV2Client;
import com.ocean.persist.api.proxy.zhuoyiV2.ZhuoyiV2Device;
import com.ocean.persist.api.proxy.zhuoyiV2.ZhuoyiV2DeviceId;
import com.ocean.persist.api.proxy.zhuoyiV2.ZhuoyiV2Geo;
import com.ocean.persist.api.proxy.zhuoyiV2.ZhuoyiV2Media;
import com.ocean.persist.api.proxy.zhuoyiV2.ZhuoyiV2Native;
import com.ocean.persist.api.proxy.zhuoyiV2.ZhuoyiV2Network;
import com.ocean.persist.api.proxy.zhuoyiV2.ZhuoyiV2Size;
import com.ocean.persist.api.proxy.zhuoyiV2.ZhuoyiVdPlayPct;
import com.ocean.persist.api.proxy.zhuoyiV2.ZhuoyiVdPlayTrack;
import com.ocean.persist.api.proxy.zhuoyiV2.ZhuoyiVdTrack;
import com.ocean.persist.common.ProxyConstants;
import com.ocean.persist.model.proxy.DSPPosition;
import com.ocean.proxy.api.base.AbstractAsynAdSupplier;
import com.ocean.proxy.serverdis.AdProxyZhuoyiTask;
import com.ocean.proxy.thrift.entity.AdContent;
import com.ocean.proxy.thrift.entity.AdImg;
import com.ocean.proxy.thrift.entity.AdMutiAction;
import com.ocean.proxy.thrift.entity.AdRecomReply;
import com.ocean.proxy.thrift.entity.AdRecomReq;
import com.ocean.proxy.thrift.entity.AdSpaceType;
import com.ocean.proxy.thrift.entity.AdUserInfo;
import com.ocean.proxy.thrift.entity.AdVid;
@Component(value = "ZhuoyiV2AdSupplier")
public class ZhuoyiV2AdSupplier extends AbstractAsynAdSupplier {
    private static final  String  ZY_DOWN_X="down_x";
    private static final  String  ZY_DOWN_Y="down_y";
    private static final  String  ZY_UP_X="up_x";
    private static final  String  ZY_UP_Y="up_y";
    private static final String   ZY_ABSOLUTE_COORD ="__ABSOLUTE_COORD__"; 
    private static final String   ZY_RELATIVE_COORD  ="__RELATIVE_COORD__";
    private static final String   ZY_ORIGIN_TIME="%25%25origin_time%25%25";// 代表事件发生 unix 时间戳(毫秒)；
    private static final String   ZY_AREA="%25%25area%25%25";// 代表点击区域,目前只用 hot 替换；
    private static final String   ZY_PLAY_MODE= "%25%25play_mode%25%25";// 代表播放方式 0：自动播放、 1：手动播放；
    private static final String   ZY_CUR_TIME="%25%25cur_time%25%25";// 代 表 打印当前播放的进度（秒）、
    private static final String   ZY_START_TIME="%25%25start_time%25%25";// 代表打印开始播放时的进度（秒）
    
    private static final String   ZY_AREA_VALUE= "hot";
	 private static final String ZY_VIDEO_TIME="_VIDEO_TIME_";//	视频总时长，单位为秒	
	 private static final String ZY_VIDEO_BEGIN_TIME="_BEGIN_TIME_";//	视频播放开始时间，单位为秒。如果视频从头开始播放，  则为 0。
	 private static final String ZY_VIDEO_END_TIME="_END_TIME_";//	视频播放结束时间，单位为秒。如果视频播放到结尾，则等于视频总时长。
	 
	
	 private static final String ZY_VIDEO_FIRST_FRAME="_FIRST_FRAME_";//	视频是否从第一帧开始播放。
	 private static final String ZY_VIDEO_FIRST_FRAME_VALUE="{1,0}";
	/*	 -第一帧开始播放
		 -第一帧开始播放	__VIDEO_PLAY_FIRST_FRAME__{1,0}，表示1为第一帧播放，0-为非第一帧播放，后面的赋值类似
		 		*/						
	 private static final String ZY_VIDEO_LAST_FRAME="_LAST_FRAME_";//	视频是否播放到最后一帧。
	 private static final String ZY_VIDEO_LAST_FRAME_VALUE="{1,0}";
	/*	 -播放到最后一帧
		 -没有播放到最后一帧	__VIDEO_PLAY_LAST_FRAME__{1,0}*/
	 private static final String ZY_VIDEO_SCENE="_SCENE_";//	视频播放场景。推荐场景如下： 
	 private static final String ZY_VIDEO_SCENE_VALUE="{1,2,3,4,0}";
	/*	 - 在广告曝光区域播放；
		 - 全屏竖屏、只展示视频；
		 - 全屏竖屏、屏幕上方展示视频、下方展示广告推广目标网页
		 - 全屏横屏、只展示视频；
		 - 其他	__VIDEO_SCENE__{1,2,3,4,0}*/
	 private static final String ZY_VIDEO_TYPE="_TYPE_";//	播放类型。
	 private static final String ZY_VIDEO_TYPE_VALUE="{1,2,3}";
	/*	 - 第一次播放；
		 - 暂停后继续播放；
		 - 重新开始播放	__VIDEO_TYPE__{1,2,3}*/
	 private static final String ZY_VIDEO_BEHAVIOR="_BEHAVIOR_";//	播放行为。
	 private static final String ZY_VIDEO_BEHAVIOR_VALUE="{1,2}";
	/*	 - 自动播放（推荐联网方式为 wifi 或 4G 时，设置视频自动播放）；
		 - 点击播放。	__VIDEO_BEHAVIOR__{1,2}*/
	 private static final String ZY_VIDEO_STATUS="_STATUS_";//	播放状态。
	 //播放状态。0 - 正常播放；1 - 视频加载中；2 - 播放错误。
	 private static final String ZY_VIDEO_STATUS_VALUE="{0,1,2}";
	 
	 private static int ZY_VIDEO_AD_START=101000;// 视频开始播放
	 private static int ZY_VIDEO_AD_END=101002 ;//视频正常播放结束
	 private static int ZY_VIDEO_AD_CLOSE=102001;// 广告被关闭
	 private static int ZY_VIDEO_AD_CLICK=102002 ;//广告被点击
	 private static int ZY_VIDEO_AD_SKIP=102003;//广告跳过
	 private static int ZY_VIDEO_AD_LOADED=101101;//视频加载成功
	public AdRecomReply invoke(InvokeAttribute attribute) throws AdPullException {
		// 参数转换
		AdRecomReq adreq = attribute.getAdreq();
		ZhuoyiV2AdPullReq params = parseParams(attribute,adreq.getUserAdSpaceAttri().getSpaceType());
		// 调用API
		ZhuoyiV2AdPullResp response = (ZhuoyiV2AdPullResp) invoke(params, ZhuoyiV2AdPullResp.class, adreq.getHash(), String.valueOf(adreq.getUserAdSpaceAttri().getAdSpaceId()));
		// 解析结果
		return parseResult(response,adreq);
	}

	public AdRecomReply parseResult(ZhuoyiV2AdPullResp resp,AdRecomReq adreq ) throws AdPullException {
		if (resp == null||CollectionUtils.isEmpty(resp.getAds())) {
			return null;
		}
		if (!resp.isSuccess()) {
			throw new AdPullException("ad request error,error");
			
		}
		AdRecomReply adresp = new AdRecomReply();
		adresp.setStatus(status);
		adresp.setAd_contents(dealAds(resp.getAds(),adreq));
		return adresp;
	}
	private List<AdContent>  dealAds(List<ZhuoyiV2Ad> ads,AdRecomReq adreq ) throws AdPullException{
		List<AdContent> list=new ArrayList<AdContent> ();
		
		for(ZhuoyiV2Ad ad:ads){
			//***********************BEGIN 常规设置*************
			AdContent content = new AdContent();
			AdMutiAction action = new AdMutiAction();
			String title = defTitle;
			String marketTitle=defTitle;
			// 内容类型1表示链接推广,2表示应用推广,3未使用 4表示电话推广
	        int acType=ACTION_WEB;
	       //***********************END 常规设置*************
	        //图片
	        List<AdImg> imgs = new ArrayList<AdImg>();
			//上报
	        Map<String, List<String>> map = new HashMap<String, List<String>>();
	        if(ad.getMaterial_type()==0){
	        	content.setIsHtmlAd(true);
	        	content.setHtmlSnippet(ad.getHtml_snippet());
	        }else{
	        	ZhuoyiV2Native _native=ad.getNative_material();
	        	if(_native.getInteraction_type()>=2){
	        		acType=ACTION_APP;
	        	}
	        	if(StringUtils.isNotEmpty(_native.getTitle())){
	        		title = _native.getTitle();
	        	}
	        	if(StringUtils.isNotEmpty(_native.getDescription())){
	        
	    			 marketTitle=_native.getDescription();
	        	}
		        if(StringUtils.isNotEmpty(_native.getImage_url())){

	        		AdImg img=new AdImg();
	  	            img.setSrc(_native.getImage_url());
	  	            imgs.add(img);

			    }
		        if(CollectionUtils.isNotEmpty(_native.getExt_image_url())){
		        	for(String url:_native.getExt_image_url()){
		        		AdImg img=new AdImg();
		  	            img.setSrc(url);
		  	            imgs.add(img);
		        	}
		        }
		        if(StringUtils.isNotEmpty(_native.getLogo_url())){
		        	action.setCpIcon(_native.getLogo_url());
		        }
		        if(StringUtils.isNotEmpty(_native.getClick_url())){
	        	    String url=format(REPORT_TYPE_REQ,_native.getClick_url());
		        	content.setLinkurl(url);
					action.setLinkurl(url);
					if(_native.getInteraction_type()==3){//需要二次解析
						action.setLinkurl_type(1);
					}

		        }
		        if(StringUtils.isNotEmpty(_native.getApp_pkg())){
		        	action.setCpPackage(_native.getApp_pkg());
			        
		        }
		        //上报处理
		       

		        if(CollectionUtils.isNotEmpty(_native.getImpression_log_url())){
		        	 List<String> showReport=new ArrayList<String>();
		        	for(String url:_native.getImpression_log_url()){
		        		if(_native.getNative_material_type()==4){//视频广告处理
		        			showReport.add(formatVdUrl(format(REPORT_TYPE_PV,url)));
		        		}else{
		        			showReport.add(format(REPORT_TYPE_PV,url));	
		        		}
		        		
		        	}
		        	map.put(SHOW, showReport);
		        }    	       
		     
		     	List<String> clickReport=new ArrayList<String>();
		        if(CollectionUtils.isNotEmpty(_native.getClick_monitor_url())){
		        	for(String url:_native.getClick_monitor_url()){
		        		clickReport.add(format(REPORT_TYPE_CLICK,url));
		        	}
		        	map.put(CLICK, clickReport);
		        }
		        List<String> DSReport=new ArrayList<String>();
	    		if(CollectionUtils.isNotEmpty(_native.getApp_download_start())){
	    			
	    			for(String url:_native.getApp_download_start()){
	    				if(_native.getInteraction_type()==3){
		        			StringBuilder sb=new StringBuilder(url);
		        			sb.append(url.indexOf("?")<0?"?":"&").append("clickid=").append(MACRO_CLICK_ID);
		        			DSReport.add(format(REPORT_TYPE_DOWNLOAD_START,sb.toString()));
		        		}else{
		        			DSReport.add(format(REPORT_TYPE_DOWNLOAD_START,url));
		        		}
	    			
	    			}
	    		
	    		}
	    		if(_native.getInteraction_type()==6){
	    			DSReport.add(this.wrapReportLink(REPORT_TYPE_DOWNLOAD_START, adreq));
	    		}
	    		
	    		map.put(START_DOWN, DSReport);
	    		List<String> DFReport=new ArrayList<String>();
	    		if(CollectionUtils.isNotEmpty(_native.getApp_download())){
	    			for(String url:_native.getApp_download()){
	    				if(_native.getInteraction_type()==3){
		        			StringBuilder sb=new StringBuilder(url);
		        			sb.append(url.indexOf("?")<0?"?":"&").append("clickid=").append(MACRO_CLICK_ID);
		        			DFReport.add(format(REPORT_TYPE_DOWNLOAD,sb.toString()));
		        		}else{
		        			DFReport.add(format(REPORT_TYPE_DOWNLOAD,url));
		        		}
	    			}
	    			
	    		}
	    		if(_native.getInteraction_type()==6){
	    			DFReport.add(this.wrapReportLink(REPORT_TYPE_DOWNLOAD, adreq));
	    		}
	    		
	    		map.put(DOWNLOAD, DFReport);
	    		
	    		
	    		if(CollectionUtils.isNotEmpty(_native.getApp_install_start())){
	    			List<String> report=new ArrayList<String>();
	    			for(String url:_native.getApp_install_start()){

		        		report.add(format(REPORT_TYPE_INSTALL_START,url));
		        		
	    			}
	    			map.put(START_INSTALL, report);
	    		}
	    		
	    		List<String> installReport=new ArrayList<String>();
	    		if(CollectionUtils.isNotEmpty(_native.getApp_install())){
	    			for(String url:_native.getApp_install()){
	    				if(_native.getInteraction_type()==3){
		        			StringBuilder sb=new StringBuilder(url);
		        			sb.append(url.indexOf("?")<0?"?":"&").append("clickid=").append(MACRO_CLICK_ID);
		        			installReport.add(format(REPORT_TYPE_INSTALL,sb.toString()));
		        		}else{
		        			installReport.add(format(REPORT_TYPE_INSTALL,url));
		        		}
	    			}
	    			
	    		}
	    		if(CollectionUtils.isNotEmpty(_native.getApp_open())){
	    			for(String url:_native.getApp_open()){
	    				installReport.add(format(REPORT_TYPE_INSTALL,url));
	    			}
	    			
	    		}
	    		if(_native.getInteraction_type()==6){
	    			installReport.add(this.wrapReportLink(REPORT_TYPE_INSTALL, adreq));
	    		}
	    		
	    		map.put(INSTALL, installReport);
	    		if(CollectionUtils.isNotEmpty(_native.getApp_active())){
	    			List<String> report=new ArrayList<String>();
	    			for(String url:_native.getApp_active()){
	    				report.add(format(REPORT_TYPE_ACTIVE,url));
	    			}
	    			map.put(ACTIVE, report);
	    		}
	    		
		        /***********视频广告******************/
		        if(_native.getNative_material_type()==4){
		        	String INVALID_SURFIX_JPG="jpg";
		        	acType=ACTION_VIDEO;
					AdVid rpcVideo = new AdVid();
					
					if(_native.getVideo_duration()>0) {
						rpcVideo.setDuration(_native.getVideo_duration());
					}
					if(StringUtils.isNotEmpty(_native.getVideo_url())&&_native.getVideo_url().endsWith(INVALID_SURFIX_JPG)){
						throw new AdPullException("zhuoyi return video ad url error!");
					}
					rpcVideo.setSrc(_native.getVideo_url());
					if(_native.getImage_size()!=null){
						if(_native.getImage_size().getWidth()>0) {
							rpcVideo.setWidth(_native.getImage_size().getWidth());
						}
						if(_native.getImage_size().getHeight()>0) {
							rpcVideo.setHeight(_native.getImage_size().getHeight());
						}
					}
	
					int lastIndex = rpcVideo.getSrc().lastIndexOf(".");
					if (lastIndex < 0) {
						rpcVideo.setFormat("unknow");
					} else {
						rpcVideo.setFormat(rpcVideo.getSrc().substring(lastIndex));
					}
					rpcVideo.setImg_src(_native.getImage_url());
					content.setAdVid(rpcVideo);
					
					
					//上报处理

					List<String> vdStart=new ArrayList<String>();
					List<String> vdEnd=new ArrayList<String>();
					List<String> vdClose=new ArrayList<String>();
					List<String> vdClick=new ArrayList<String>();
					List<String> vdSkip=new ArrayList<String>();
					List<String> vdLoaded=new ArrayList<String>();
					List<String> vdProgress=new ArrayList<String>();
					List<String> vdMute=new ArrayList<String>();
					List<String> vdUnmute=new ArrayList<String>();
					if(CollectionUtils.isNotEmpty(_native.getAd_tracking())){
						for(ZhuoyiVdTrack vt:_native.getAd_tracking()){
							if(vt.getTracking_event()==ZY_VIDEO_AD_START){
								vdStart.add(vt.getTracking_url());
							}else if(vt.getTracking_event()==ZY_VIDEO_AD_END){
								vdEnd.add(vt.getTracking_url());
							}else if(vt.getTracking_event()==ZY_VIDEO_AD_CLOSE){
								vdClose.add(vt.getTracking_url());
							}else if(vt.getTracking_event()==ZY_VIDEO_AD_CLICK){
								vdClick.add(vt.getTracking_url());
							}else if(vt.getTracking_event()==ZY_VIDEO_AD_SKIP){
								vdSkip.add(vt.getTracking_url());
							}else if(vt.getTracking_event()==ZY_VIDEO_AD_LOADED){
								vdLoaded.add(vt.getTracking_url());
							}
						}
						
					}
					if(_native.getAd_playtracker()!=null){
						ZhuoyiVdPlayTrack vpt=_native.getAd_playtracker();
						vdMute.addAll(vpt.getMute());
						vdUnmute.addAll(vpt.getUnmute());
						vdClose.addAll(vpt.getVideoclose());
						if(CollectionUtils.isNotEmpty(vpt.getPlaypercentage())){
							for(ZhuoyiVdPlayPct vpp:vpt.getPlaypercentage()){
								for(String url:vpp.getUrls()){
									String prefix=url.indexOf("?")<0?"?":"&";
									vdProgress.add(url+prefix+ZK_VIDEO_PROGRESS_PARAM+"="+vpp.getCheckpoint()*_native.getVideo_duration()*1000);
								}
							}
						}
					}
					map.put(VIDEO_CLICK, vdClick);
					map.put(VIDEO_START, vdStart);
					map.put(VIDEO_SKIP, vdSkip);
					map.put(VIDEO_END, vdEnd);
					map.put(VIDEO_LOAD_SUCCEED, vdLoaded);
					//map.put(VIDEO_LOAD_FAILED, report);
					map.put(VIDEO_PROGRESS, vdProgress);
					map.put(VIDEO_MUTE, vdMute);
					map.put(VIDEO_NON_MUTE, vdUnmute);

		        }
	        }

	        
 
			content.setThirdReportLinks(map);
			//img
			content.setImglist(imgs);
		    action.setType(acType); 
			content.setMarketTitle(marketTitle);
			content.setGuideTitle(title);
			content.setMutiAction(Collections.singletonList(action));
			list.add(content);
		}
		return list;
	}
	//又是一个脑残技术文档
	private String wrapReportLink(int type,AdRecomReq adreq){
		String url=SystemContext.getDynamicPropertyHandler().get(ProxyConstants.ZHUOYI_JRTT_REPORT_URL);
		StringBuilder sb=new StringBuilder(url);
		sb.append("?").append("event_type=");
		if(type==REPORT_TYPE_DOWNLOAD_START){
			sb.append("1");
		}else if(type==REPORT_TYPE_DOWNLOAD){
			sb.append("2");
		}else if(type==REPORT_TYPE_INSTALL){
			sb.append("3");
		}
		AdUserInfo  user=adreq.getUserinfo();
		//sb.append("&extra=").append("");
		sb.append("&app_version=").append(adreq.getVersion())
		  .append("&os_version=").append(user.getOsversion())
		  .append("&os_api=").append(user.getOs_api_level())
		  .append("&mac=").append(user.getMac())
		  .append("&sim_op=").append(getOp(user.getMobile()))
		  .append("&width=").append(user.getDevice_width())
		  .append("&height=").append(user.getDevice_height())
		  .append("&manufacturer=").append(user.getBrand_name())
		  .append("&imei=").append(user.getImei())
		  .append("&openudid=").append(user.getAdid())
		  .append("&nt=").append(this.getJRTTNetwork(adreq.getNet()))
		  .append("&device_model=").append(user.getPhonemodel())
		  .append("&device_brand=").append(user.getBrand_name())
		  .append("&device_manufaturer=").append(user.getBrand_name())
		  .append("&language=").append("zh")
		  .append("&display_density=").append(user.getDensity());
		return sb.toString();
	}
	private int  getOp(String mobile){
		if(MOBILE_CMCC.equals(mobile)){
			return 46000;
		}else if(MOBILE_CUCC.equals(mobile)){
			return 46001;
		}else if(MOBILE_CTCC.equals(mobile)){
			return 46003;
		}
		return 0;
	}
	private  String format(int type,String str){
		if(type!=REPORT_TYPE_PV){
			str=str.replaceAll(ZY_DOWN_X, MACRO_DOWN_X).replaceAll(ZY_DOWN_Y, MACRO_DOWN_Y)
					.replaceAll(ZY_UP_X, MACRO_UP_X).replaceAll(ZY_UP_Y,MACRO_UP_Y);
		}
		String format= str.replaceAll(ZY_ABSOLUTE_COORD,MACRO_RAW_COORDINATE)
						  .replaceAll(ZY_RELATIVE_COORD,MACRO_RAW_COORDINATE);

		return format;
	}
	private  String formatVdUrl(String str){
	
		str=str.replaceAll(ZY_ORIGIN_TIME, String.valueOf(System.currentTimeMillis())).replaceAll(ZY_AREA, ZY_AREA_VALUE)
				.replaceAll(ZY_PLAY_MODE, ZY_VIDEO_BEHAVIOR).replaceAll(ZY_CUR_TIME,MACRO_EVENT_TIME_START)
				.replaceAll(ZY_START_TIME,MACRO_VIDEO_PROGRESS);
				

		 str=str.replaceAll(ZY_VIDEO_TIME, MACRO_VIDEO_TIME)
				 .replaceAll(ZY_VIDEO_BEGIN_TIME, MACRO_VIDEO_BEGIN_TIME)
				 .replaceAll(ZY_VIDEO_END_TIME, MACRO_VIDEO_END_TIME)
				 .replaceAll(ZY_VIDEO_FIRST_FRAME, MACRO_VIDEO_FIRST_FRAME).replaceAll(VIDEO_MACRO_VALUE_SURFIX, ZY_VIDEO_FIRST_FRAME_VALUE)
				 .replaceAll(ZY_VIDEO_LAST_FRAME, MACRO_VIDEO_LAST_FRAME).replaceAll(VIDEO_MACRO_VALUE_SURFIX, ZY_VIDEO_LAST_FRAME_VALUE)
				 .replaceAll(ZY_VIDEO_SCENE, MACRO_VIDEO_SCENE).replaceAll(VIDEO_MACRO_VALUE_SURFIX, ZY_VIDEO_SCENE_VALUE)
				 .replaceAll(ZY_VIDEO_TYPE, MACRO_VIDEO_TYPE).replaceAll(VIDEO_MACRO_VALUE_SURFIX, ZY_VIDEO_TYPE_VALUE)
				 .replaceAll(ZY_VIDEO_BEHAVIOR, MACRO_VIDEO_BEHAVIOR).replaceAll(VIDEO_MACRO_VALUE_SURFIX, ZY_VIDEO_BEHAVIOR_VALUE)
				 .replaceAll(ZY_VIDEO_STATUS, MACRO_VIDEO_STATUS).replaceAll(VIDEO_MACRO_VALUE_SURFIX, ZY_VIDEO_STATUS_VALUE);
				 
		return str;
	}
	public ZhuoyiV2AdPullReq parseParams(InvokeAttribute attribute,AdSpaceType spaceType) throws AdPullException {
		DSPPosition posInfo = attribute.getDspPosition();
		if (StringUtils.isEmpty(posInfo.getText1())||StringUtils.isEmpty(posInfo.getText3())) {
			throw new AdPullException("app_id/pkgname  is empty!");
		}

		AdRecomReq adreq = attribute.getAdreq();
		AdUserInfo  user=adreq.getUserinfo();
		ZhuoyiV2AdPullReq param = new ZhuoyiV2AdPullReq();
		//media 
		ZhuoyiV2Media media=new ZhuoyiV2Media();
		media.setId(posInfo.getText1());
		media.setType(1);
		//app
		ZhuoyiV2App app=new ZhuoyiV2App();
		app.setPackage_name(posInfo.getText3());
		app.setVersion_id(adreq.getVersion());
		app.setVersioncode_id(this.getAppVC(adreq.getVersion()));
		media.setApp(app);
		param.setMedia(media);
		
		//device
		ZhuoyiV2Device device =new ZhuoyiV2Device();
		device.setType(1);
		String os = user.getOs();
		device.setIds(this.getDvIds(user,os));
		
		if(OS_ANDROID.equals(os)){
			device.setOs_type(1);
		}else if(OS_IOS.equals(os)){
			device.setOs_type(2);
		}else{
			device.setOs_type(0);
		}
		device.setOs_version(this.convertVersion(user.getOsversion()));
		device.setBrand(user.getBrand_name());
		device.setModel(user.getPhonemodel());
		
		ZhuoyiV2Size ds=new ZhuoyiV2Size();
		ds.setHeight(user.getDevice_height());
		ds.setWidth(user.getDevice_width());
		device.setScreen_size(ds);
		if(StringUtils.isNotEmpty(user.getDensity())){
			device.setScreen_density(Double.parseDouble(user.getDensity()));
		}
		
		param.setDevice(device);
		
		//network
		ZhuoyiV2Network network=new ZhuoyiV2Network();
		network.setIp(user.getClient_ip());
		network.setType(getNetwork(adreq.getNet()));
		network.setImsi(user.getImsi());
		if(user.getCid()>0&&user.getLac()>0){
			ZhuoyiV2Cellular cl=new ZhuoyiV2Cellular();
			cl.setCid(String.valueOf(user.getCid()));
			cl.setLac(String.valueOf(user.getLac()));
			network.setCellular(cl);
		}
		
		param.setNetwork(network);
		
		//client
		ZhuoyiV2Client client=new ZhuoyiV2Client();
		client.setType(3);
		client.setH5(1);
		ZhuiyiV2Version cv=new ZhuiyiV2Version();
		cv.setMajor(2);
		cv.setMinor(4);
		cv.setMajor(2);
		client.setVersion(cv);
		client.setDeeplink(0);
		param.setClient(client);
		
		//geo
		if(StringUtils.isNotEmpty(user.getLat())&&StringUtils.isNotEmpty(user.getLon())){
			ZhuoyiV2Geo geo=new ZhuoyiV2Geo();
			geo.setType(3);
			geo.setLatitude(Double.parseDouble(user.getLat()));
			geo.setLongitude(Double.parseDouble(user.getLon()));
			geo.setTimestamp(System.currentTimeMillis());
			param.setGeo(geo);
		}
		
		//adslot
		
		ZhuoyiV2Adslot slot=new ZhuoyiV2Adslot();
		slot.setId(posInfo.getPos());
		List<Integer> styles=new ArrayList<Integer>();
		if(!FLAG_VIDEO.equals(posInfo.getText2())){
			Integer[] styleInt={2,3};
			styles.addAll(Arrays.asList(styleInt));
			
		}
		
		slot.setStyles(styles);
		ZhuoyiV2Size ss=new ZhuoyiV2Size();
		ss.setHeight(adreq.getUserAdSpaceAttri().getSpaceHeight());
		ss.setWidth(adreq.getUserAdSpaceAttri().getSpaceWidth());
		slot.setSize(ss);
		param.setAdslots(Collections.singletonList(slot));
		return param;
	}
	private List<ZhuoyiV2DeviceId> getDvIds(AdUserInfo  user,String osType){
		List<ZhuoyiV2DeviceId> list=new ArrayList<ZhuoyiV2DeviceId>();
		if(OS_ANDROID.equals(osType)){

			ZhuoyiV2DeviceId id_imei=new ZhuoyiV2DeviceId();
			id_imei.setType(1);
			id_imei.setId(user.getImei());
			list.add(id_imei);
			
			ZhuoyiV2DeviceId id_mac=new ZhuoyiV2DeviceId();
			id_mac.setType(2);
			id_mac.setId(user.getMac());
			list.add(id_mac);
			
			ZhuoyiV2DeviceId id_adid=new ZhuoyiV2DeviceId();
			id_adid.setType(4);
			id_adid.setId(user.getAdid());
			list.add(id_adid);
		}else if(OS_IOS.equals(osType)){
			ZhuoyiV2DeviceId id_idfa=new ZhuoyiV2DeviceId();
			id_idfa.setType(3);
			id_idfa.setId(user.getIdfa());
			list.add(id_idfa);
		}
		
		return list;
	}
	private int getAppVC(String version){
		if(StringUtils.isEmpty(version)){
			return 20;
		}
		String[] vArr=version.split(".");
		try{
			return Integer.parseInt(vArr[vArr.length-1]);
		}catch(Exception e){
			
		}
		return 20;
	}
	private  ZhuiyiV2Version convertVersion(String version) throws AdPullException{
	    if(StringUtils.isEmpty(version)){
	    	throw new AdPullException("app version is empty!");
	    }
	    String vArr[]=version.split("\\.");
	    ZhuiyiV2Version v= new ZhuiyiV2Version() ;
	    v.setMajor(Integer.parseInt(vArr[0]));
	    if(vArr.length==1){
	    	v.setMinor(0);
        	v.setMicro(0);
	    }
	    else if(vArr.length==2){
        	v.setMinor(Integer.parseInt(vArr[1]));
        	v.setMicro(0);
        }else if(vArr.length==3){
        	v.setMinor(Integer.parseInt(vArr[1]));
        	v.setMicro(Integer.parseInt(vArr[2]));
        }
	    		
	    return v;
         
		
	}
	private int getAdType(AdSpaceType spaceType){
		switch(spaceType){
		    case BANNER:
		    	return 4;
		    case INTERSTITIAL:
		    	return 3;
		    case OPENING:
		    	return 2;
		    case INFOFLOW:
		    	return 1;
		    default:
		    	return 2;
		
		}
	}
	private  int getJRTTNetwork(String net){	
		if(NETWORK_WIFI.equals(net)){
			return 4;
		}else if(NETWORK_2G.equals(net)){
			return 1;
		}
		else if(NETWORK_3G.equals(net)){
			return 2;
		}
		else if(NETWORK_4G.equals(net)){
			return 3;
		}else if(NETWORK_5G.equals(net)){
			return 4;
		}
		return 0;
	}
	private  int getNetwork(String net){	
		if(NETWORK_WIFI.equals(net)){
			return 1;
		}else if(NETWORK_2G.equals(net)){
			return 3;
		}
		else if(NETWORK_3G.equals(net)){
			return 4;
		}
		else if(NETWORK_4G.equals(net)){
			return 5;
		}else if(NETWORK_5G.equals(net)){
			return 6;
		}
		return 2;
	}
	@Override
	public AsynAbstractTask packageTask(Parameter params, String hashCode) {
		AdProxyZhuoyiTask task = new AdProxyZhuoyiTask();
		ZhuoyiV2AdPullReq param = (ZhuoyiV2AdPullReq) params;
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		
		String url=SystemContext.getDynamicPropertyHandler().get(ProxyConstants.ZHUOYI_URL);

		
		task.setParam(param);
		task.setDataFormat(DataFormat.DATA_FORMAT_JSON);
		task.setInvokeType(InvokeType.INVOKE_TYPE_POST);
		task.setHashCode(hashCode);
		task.setUrl(url);
		task.setHeaders(headers);
		return task;
	}
}
