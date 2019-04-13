package com.ocean.service.haiqibingInvokeHandler.base;
import com.ocean.core.common.system.ErrorCode;
import com.ocean.core.common.system.SystemContext;
import com.ocean.persist.app.dis.AppDisException;
import com.ocean.service.base.BaseHandler;
import com.ocean.service.haiqibingInvokeHandler.HaiqibingAppasynHandler;
import com.ocean.service.haiqibingInvokeHandler.HaiqibingPkgSearchHandler;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年10月10日 
      @version 1.0 
 */
public class HaiqibingInvokeHandlerFactory {
    public static BaseHandler getHandler(HaiqibingMethodType interType) throws AppDisException{
    	switch(interType){
    	    case PACKAGE_SEARCH:
    	    	return    (HaiqibingPkgSearchHandler)SystemContext.getServiceHandler().getService(HaiqibingPkgSearchHandler.class);
    	    case APP_LIST_ASYN_SEARCH:
    	    	return    (HaiqibingAppasynHandler)SystemContext.getServiceHandler().getService(HaiqibingAppasynHandler.class);
    	    default:
    	    	throw new AppDisException(ErrorCode.PARAM_ERROR,"undefined interType!");
    	}
    
    }
}
