package com.ocean.core.common.system;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年7月20日 
      @version 1.0 
 */
public class MyLogManager {
	private  static final Logger loggerV2 = LogManager.getLogger("business");
	private  static final Logger lbsLoggerV2 = LogManager.getLogger("lbs");
	private  static final Logger disLoggerV2 = LogManager.getLogger("dis");
	private  static final Logger logsMonitor= LogManager.getLogger("monitor");
    public static  Logger getLogger(){
    	return loggerV2;
    }
    public static  Logger getLbsLogger(){
    	return lbsLoggerV2;
    }
    public static Logger getDisLoggerV2(){
    	return disLoggerV2;
    }
    public static Logger getMonitor(){
    	return logsMonitor;
    } 
/*    public org.slf4j.Logger  getLogger(){
    	return logger;
    }*/
}
