package com.ocean.service.zkInvokeHandler.base;
import com.ocean.core.common.system.ErrorCode;
import com.ocean.core.common.system.SystemContext;
import com.ocean.persist.app.dis.AppDisException;
import com.ocean.service.base.BaseHandler;
import com.ocean.service.zkInvokeHandler.ZKAppListSearchHandler;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年10月10日 
      @version 1.0 
 */
public class ZKInvokeHandlerFactory {
    public static BaseHandler getHandler(ZKMethodType interType) throws AppDisException{
    	switch(interType){
    	    case APP_LIST_SEARCH:
    	    	return    (ZKAppListSearchHandler)SystemContext.getServiceHandler().getService(ZKAppListSearchHandler.class);
    	    case PKG_SEARCH:
    	    	return    (ZKAppListSearchHandler)SystemContext.getServiceHandler().getService(ZKAppListSearchHandler.class);
    	    default:
    	    	throw new AppDisException(ErrorCode.PARAM_ERROR,"undefined interType!");
    	}
    
    }
}
