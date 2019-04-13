package com.ocean.proxy.api.base;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;

import com.ocean.core.common.system.MyLogManager;
import com.ocean.core.common.system.SystemContext;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
import com.ocean.core.common.threadpool.workthread.AsynInvokeResponse;
import com.ocean.core.common.threadpool.workthread.AsynTaskDisServer;
import com.ocean.persist.api.proxy.AdPullResponse;
import com.ocean.persist.api.proxy.gmobi.GmobiAdPullResponse;
import com.ocean.persist.common.ProxyConstants;
import com.ocean.proxy.serverdis.AdProxyAdhubTask;
import com.ocean.proxy.serverdis.TaskServerDispatcher;
public abstract class AbstractAdSupplier implements BaseAdSupplier{
	 public static final String MACRO_DOWN_X = "%%DOWNX%%";
	 public static final String MACRO_DOWN_Y = "%%DOWNY%%";
	 public static final String MACRO_UP_X = "%%UPX%%";
	 public static final String MACRO_UP_Y = "%%UPY%%";
	 public static final String MACRO_REQ_WIDTH = "%%REQ_WIDTH%%";
	 public static final String MACRO_REQ_HEIGHT = "%%REQ_HEIGHT%%";
	 public static final String MACRO_REL_WIDTH = "%%WIDTH%%";
	 public static final String MACRO_REL_HEIGHT = "%%HEIGHT%%";
	 public static final String MACRO_CLICK_ID = "%%CLICKID%%";
	 public static final String MACRO_TIME_MS = "%%TM_MS%%";
	 public static final String MACRO_EVENT_TIME_START="%%EVENT_TIME_STAR%%";
	 public static final String MACRO_EVENT_TIME_END="%%EVENT_TIME_END%%";
	 
	 public static final String FLAG_VIDEO="1";
	 public static final String ZK_VIDEO_PROGRESS_PARAM="zk_video_progress";
	 public static final String MACRO_COORDINATE="s={\"down_x\":\"%%DOWNX%%\",\"down_y\":\"%%DOWNY%%\",\"up_x\":\"%%UPX%%\",\"up_y\":\"%%UPY%%\"}";
	
	 public static final String MACRO_RAW_COORDINATE="{\"down_x\":\"%%DOWNX%%\",\"down_y\":\"%%DOWNY%%\",\"up_x\":\"%%UPX%%\",\"up_y\":\"%%UPY%%\"}";
		
	 //视频宏
	 public static final String MACRO_VIDEO_PROGRESS="__VIDEO_PROGRESS__";//	视频播放进度：表示触发事件时的广告播放进度	
	 public static final String MACRO_VIDEO_TIME="__VIDEO_TIME__";//	视频总时长，单位为秒	
	 public static final String MACRO_VIDEO_BEGIN_TIME="__VIDEO_BEGIN_TIME__";//	视频播放开始时间，单位为秒。如果视频从头开始播放，  则为 0。
	 public static final String MACRO_VIDEO_END_TIME="__VIDEO_END_TIME__";//	视频播放结束时间，单位为秒。如果视频播放到结尾，则等于视频总时长。
	 public static final String VIDEO_MACRO_VALUE_SURFIX="\\{\\}";
	 public static final String MACRO_VIDEO_FIRST_FRAME="__VIDEO_FIRST_FRAME__{}";//	视频是否从第一帧开始播放。
/*	 -第一帧开始播放
	 -第一帧开始播放	__VIDEO_PLAY_FIRST_FRAME__{1,0}，表示1为第一帧播放，0-为非第一帧播放，后面的赋值类似
	 		*/						
	 public static final String MACRO_VIDEO_LAST_FRAME="__VIDEO_LAST_FRAME__{}";//	视频是否播放到最后一帧。
/*	 -播放到最后一帧
	 -没有播放到最后一帧	__VIDEO_PLAY_LAST_FRAME__{1,0}*/
	 public static final String MACRO_VIDEO_SCENE="__VIDEO_SCENE__{}";//	视频播放场景。推荐场景如下： 
/*	 - 在广告曝光区域播放；
	 - 全屏竖屏、只展示视频；
	 - 全屏竖屏、屏幕上方展示视频、下方展示广告推广目标网页
	 - 全屏横屏、只展示视频；
	 - 其他	__VIDEO_SCENE__{1,2,3,4,0}*/
	 public static final String MACRO_VIDEO_TYPE="__VIDEO_TYPE__{}";//	播放类型。
/*	 - 第一次播放；
	 - 暂停后继续播放；
	 - 重新开始播放	__VIDEO_TYPE__{1,2,3}*/
	 public static final String MACRO_VIDEO_BEHAVIOR="__VIDEO_BEHAVIOR__{}";//	播放行为。
/*	 - 自动播放（推荐联网方式为 wifi 或 4G 时，设置视频自动播放）；
	 - 点击播放。	__VIDEO_BEHAVIOR__{1,2}*/
	 public static final String MACRO_VIDEO_STATUS="__VIDEO_STATUS__{}";//	播放状态。
/*	 - 正常播放；
	 - 视频加载中；
	 - 播放错误。	__VIDEO_STATUS__{0,1,2}*/
	 
	 public static final int REPORT_TYPE_REQ=0;
	 public static final int REPORT_TYPE_PV=1;
	 public static final int REPORT_TYPE_CLICK=2;
	 public static final int REPORT_TYPE_DOWNLOAD=3;
	 public static final int REPORT_TYPE_DOWNLOAD_START=31;
	 public static final int REPORT_TYPE_INSTALL=4;
	 public static final int REPORT_TYPE_INSTALL_START=41;
	 public static final int REPORT_TYPE_ACTIVE=5;
	 
	 
	 //视频上报
	 public static final int REPORT_VIDEO_CLICK=6;//点击视频或预览图
	 public static final int REPORT_VIDEO_START=7;//开始播放
	 public static final int REPORT_VIDEO_SKIP=8;//视频跳过
	 public static final int REPORT_VIDEO_END=9;//视频结束
	 public static final int REPORT_VIDEO_FULL_SCREEN=10;//全屏播放
		
	 //非常用上报：视频加载成功、失败、静音、非静音、播放进度上报
	 public static final int REPORT_VIDEO_LOAD_SUCCEED=11;//加载成功
	 public static final int REPORT_VIDEO_LOAD_FAILED=12;//加载失败
	 public static final int REPORT_VIDEO_MUTE=13;//静音
	 public static final int REPORT_VIDEO_NON_MUTE=14;//非静音
	 public static final int REPORT_VIDEO_PROGRESS=15;//播放进度
	 
	 
	 public static final String MACRO_CLICK_DEFUALT="-999";
	 
	//public final Logger logger = LoggerFactory.getLogger("business");
	public static final String CHARSET_CODER="UTF-8";
	public  final Logger logger = MyLogManager.getLogger();
	protected static final Map<String, String> mobiles = new HashMap<String, String>(5);
	static{
		mobiles.put("CMCC", "46000");
		mobiles.put("CUCC", "46001");
		mobiles.put("CTCC", "46003");
	}

	protected String encode(String param){
		
		try {
			return URLEncoder.encode(param, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}
	public <T> T  convertV(T val,T defVal){
		try{
			if(val.getClass().isInstance(Integer.class)&&(Integer)val==0){
					return defVal;
				
			}else if(val.getClass().isInstance(String.class)&&StringUtils.isEmpty((String)val)){
				   return defVal;
			}
			
		}catch(Exception e){
			return defVal;
		}
		return val;
	}
	public boolean isAdapter(String dspName,float sourceH,float sourceW,float targetH,float targetW){
		int sizecheck=SystemContext.getDynamicPropertyHandler().getInt(ProxyConstants.IS_SPACE_CHECK,1);
		if(sizecheck==0){//校验总开关
			return true;
		}
		try{
			if(sourceH<=0||sourceW<=0){
				logger.error("no mapping ad to specified ad space size,joinDSP:{},sourceH:{},sourceW:{},targetH:{},targetW:{}",dspName,sourceH, sourceW, targetH, targetW);
				return false;
			}
			float  sizeRatio=sourceW/sourceH;
			float  arearRatio=sourceW*sourceH;
			if(Math.abs(sizeRatio-targetW/targetH)<=0.1&&Math.abs((arearRatio- targetW*targetH)/arearRatio)<=0.1){
				
				return true;
			}else{
				logger.error("no mapping ad to specified ad space size,joinDSP:{},sourceH:{},sourceW:{},targetH:{},targetW:{},size mapping:{},arear mapping",dspName,sourceH, sourceW, targetH, targetW,Math.abs(sizeRatio-targetW/targetH),Math.abs((arearRatio- targetW*targetH)/arearRatio));
				
				return false;
			}
		}catch(Exception e){
			logger.error("check space size mapping error,dspName:{},sourceH:{},sourceW:{},targetH:{},targetW:{}",dspName,sourceH, sourceW, targetH, targetW);
			
			return false;
		}

	}
   public String convOSVersion(String code){
	   if(StringUtils.isNotEmpty(code)&&code.indexOf(".")>0){
		   return code;
	   }
	   int _code=19;
	   try{
		   _code=Integer.parseInt(code);
	   }catch(Exception e){
		   
	   }
	   switch(_code){
	       case 1:
		         return "1.0";
	       case 2:
		         return "1.1";
	       case 3:
		         return "1.5";
	       case 4:
		         return "1.6";
	       case 5:
		         return "2.0";
	       case 6:
		         return "2.0.1";
	       case 7:
		         return "2.1";
	       case 8:
		         return "2.2.2";
	       case 9:
		         return "2.3";
	       case 10:
		         return "2.3.3";
	       case 11:
		         return "3.0";
	       case 12:
		         return "3.1";
	       case 13:
		         return "3.2.1";
	       case 14:
		         return "4.0.2";
	       case 15:
		         return "4.0.3";
	       case 16:
		         return "4.1.1"; 
	       case 17:
		         return "4.2.1";
	       case 18:
		         return "4.3.1";
	       case 19:
		         return "4.4.4";
	       case 20:
		         return "5.0";
	       case 21:
		         return "5.1";
	       case 22:
		         return "5.1.1";
	       case 23:
		         return "6.0";
	       case 24:
		         return "7.0";
	       case 25:
	    	   return "7.1.1";
	       case 26:
	    	   return "8.0.0";
	       case 27:
	    	   return "8.1.0";
	       case 28:
	    	   return "9.0";
		   default:
			     return "5.0";
	   }
   }
   public   String converMac(String mac){
	   if(StringUtils.isEmpty(mac)){
		   return null;
	   }
	   if(mac.indexOf(":")>0){
		   return mac;
	   }
	   StringBuffer buffer=new StringBuffer();
	   while(mac.length()>2){
		   if(buffer.length()>0){
			   buffer.append(":");
		   }
		   buffer.append(mac.substring(0, 2));
		   mac=mac.substring(2);
	   }
	   if(mac.length()>0){
		   buffer.append(":").append(mac);
	   }
	   return buffer.toString();
   }



}
