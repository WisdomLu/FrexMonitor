package com.ocean.service.base;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import com.inveno.util.CollectionUtils;
import com.ocean.app.dis.proxy.thrift.entity.AppDisRecomReq;
import com.ocean.app.dis.proxy.thrift.entity.AppInfo;
import com.ocean.app.dis.proxy.thrift.entity.DeviceInfo;
import com.ocean.app.dis.proxy.thrift.entity.ExtData;
import com.ocean.app.dis.proxy.thrift.entity.UserInfo;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
import com.ocean.persist.app.dis.AdDisParams;
import com.ocean.persist.app.dis.AppDisException;
import com.ocean.persist.app.dis.AppDisResponse;
import com.ocean.persist.app.dis.qqDownloader.QQDownloaderRequest;
import com.ocean.persist.app.dis.qqDownloader.RequestHead;
import com.ocean.persist.app.dis.qqDownloader.Terminal;
import com.ocean.persist.common.AppDisErrorCode;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年5月15日 
      @version 1.0 
 */
public abstract class BaseHandler  extends AbstractAsynAppSupplier{
	/** 联网类型 2G*/
	public static final String NETWORK_2G = "2g";
	/** 联网类型 3G*/
	public static final String NETWORK_3G = "3g";
	/** 联网类型 4G*/
	public static final String NETWORK_4G = "4g";
	/** 联网类型 5G*/
	public static final String NETWORK_5G ="5g";
	/** 联网类型 wifi*/
	public static final String NETWORK_WIFI = "wifi";
	public static final String CHARSET_CODER="UTF-8";
	public static final String  MOBILE_CMCC="CMCC";
	public static final String  MOBILE_CUCC="CUCC";
	public static final String  MOBILE_CTCC="CTCC";
	public static final int pageSize=10;
	public static int LISTTYPE_SUBJECT=9;
	public static int LISTTYPE_CATEGORY=10;
	
	/** 安卓操作系统*/
	public static  final String OS_ANDROID = "android";
	
	/** ios操作系统*/
	public	static  final String OS_IOS = "iOS";
	
	
	public static final String VALIDATE_OK="ok";
	public AsynAbstractTask packageTask(Parameter params,String hashCode){
		return null;
	}
	public QQDownloaderRequest getQQDownloaderAttri(String interName,AppDisRecomReq req,AdDisParams body) throws AppDisException{
		
		if(req.getJoinParam()==null){
		    throw new AppDisException(AppDisErrorCode.RC_PARAM_ERROR,req.getJoinSource().name()+": please check the join source's paramters is  empty or not");
		}
		QQDownloaderRequest attri=new QQDownloaderRequest();
		RequestHead head=this.getRequestHead(req);
		if(CollectionUtils.isEmpty(req.getJoinParam())){
			throw new AppDisException(AppDisErrorCode.RC_PARAM_ERROR,req.getJoinSource().name()+": please check third plat applying paramters is  empty or not");
			
		}
		for(int i=0;i<req.getJoinParam().size();i++){
			ExtData ext=req.getJoinParam().get(i);
			if(i==0){
				attri.setBusinessId(ext.getValue());
				head.setBusinessId(ext.getValue());
			}else if(i==1){
				attri.setBusinessKey(ext.getValue());
			}

		}
		if(StringUtils.isEmpty(attri.getBusinessId())||StringUtils.isEmpty(attri.getBusinessKey())){
			throw new AppDisException(AppDisErrorCode.RC_PARAM_ERROR,req.getJoinSource().name()+": please check the businessId/businessKey is  empty or not");
			
		}
		attri.setInterName(interName);
		attri.setHead(head);
		attri.setBody(body);
		return attri;
	}
	private RequestHead getRequestHead(AppDisRecomReq req) throws AppDisException{
		RequestHead head=new RequestHead();
		String bidId=UUID.randomUUID().toString().replaceAll("-", "");
		head.setCallbackPara(bidId);
		Random r = new Random();
		head.setNonce((int)(System.currentTimeMillis()/1000)+r.nextInt(100000));
		head.setTimestamp((int)(System.currentTimeMillis()/1000));
		
		UserInfo userInfo=req.getUserInfo();
		head.setClient_ip(userInfo.getClientIp());
		DeviceInfo device=req.getDevice();
		Terminal term=new Terminal();
		term.setAndroidId(device.getAdid());
		term.setImei(device.getImei());
		term.setImsi(device.getImsi());
		term.setMacAddress(device.getMac());
		term.setManufacture(device.getBrandName());
		term.setMode(device.getPhonemodel());
		
		head.setTerminal(term);

		head.validate();//校验参数
			
		
		return head;
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

	public abstract AppDisResponse invok(AppDisRecomReq req) throws AppDisException ;
	public abstract boolean validate(AppDisRecomReq req);
	public String strValidate(AppDisRecomReq req){
		DeviceInfo device=req.getDevice();
		AppInfo app=req.getAppInfo();

		if(StringUtils.isEmpty(device.getDip())){
			return "dip";
		}
		if(StringUtils.isEmpty(device.getImei())){
			return "imei";
		}
		if(StringUtils.isEmpty(device.getMac())){
			return "mac";
		}
		if(StringUtils.isEmpty(device.getAdid())){
			return "androidId";
		}
		if(StringUtils.isEmpty(device.getSerialNo())){
			return "serialNo";
		}
		if(StringUtils.isEmpty(device.getImsi())){
			return "imsi";
		}
		if(StringUtils.isEmpty(device.getOsversion())){
			return "os version";
		}
		if(StringUtils.isEmpty(app.getVersionCode())){
			return "app version code";
		}
		if(StringUtils.isEmpty(app.getVersionName())){
			return "app version name";
		}

	  return VALIDATE_OK;
  }
}
