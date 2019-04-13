package com.ocean.forex.service.dataAsyn;

import com.ocean.core.common.system.ErrorCode;
import com.ocean.core.common.system.SystemContext;
import com.ocean.forex.common.MonitorException;
import com.ocean.forex.service.dataAsyn.fallowme.FallowmeDSServiceImpl;
import com.ocean.forex.service.dataAsyn.jinshi.JinshiDSServiceImpl;
public class DataAsynServiceFactory {
	 public static IDataAsynService getService(DataAsynServiceType type) throws MonitorException{
		 switch(type){
	 	     case SERVICE_JINSHI:
	 	    	return    (JinshiDSServiceImpl)SystemContext.getServiceHandler().getService(JinshiDSServiceImpl.class);
	 	     case SERVICE_FALLOWME:
	 	    	return    (FallowmeDSServiceImpl)SystemContext.getServiceHandler().getService(FallowmeDSServiceImpl.class);
	
	 	    default:
	 	    	throw new MonitorException(ErrorCode.PARAM_ERROR,"undefined monitor service!");
 	     }
	 }
}
