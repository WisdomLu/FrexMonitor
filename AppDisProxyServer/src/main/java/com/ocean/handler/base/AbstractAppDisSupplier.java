package com.ocean.handler.base;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ocean.app.dis.proxy.thrift.entity.AppDisRecomReq;
import com.ocean.persist.app.dis.AppDisException;
import com.ocean.persist.app.dis.AppDisResponse;
public abstract class  AbstractAppDisSupplier implements BaseAppDisSupplier{
	public final static String PAY_MODE_CPC="CPC";
	public final static String PAY_MODE_CPM="CPM";
	public final static String PAY_MODE_CPA="CPA";
	public final static String PAY_MODE_CPI="CPI";
	public final static String PAY_MODE_CPD="CPD";
	
	
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
	public final Logger logger = LoggerFactory.getLogger("business");
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

   public String convOSVersion(String code){
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
		         return "6.0";
	       case 23:
		         return "7.0";
	       case 24:
		         return "7.1.1";
		   default:
			     return "5.0";
	   }
   }
    public abstract List<String> pvReport(String interTye,AppDisRecomReq req,AppDisResponse resp)      throws AppDisException;
	public abstract List<String> clickReport(String interTye,AppDisRecomReq req,AppDisResponse resp)   throws AppDisException;
	public abstract List<String> beginDownloadReport(String interTye,AppDisRecomReq req,AppDisResponse resp) throws AppDisException;
	public abstract List<String> endDownloadReport(String interTye,AppDisRecomReq req,AppDisResponse resp)   throws AppDisException;
	public abstract List<String> installReport(String interTye,AppDisRecomReq req,AppDisResponse resp) throws AppDisException;
}
