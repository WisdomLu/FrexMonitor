package com.ocean.service.wankaInvokeHandler.base;
import com.ocean.core.common.system.ErrorCode;
import com.ocean.core.common.system.SystemContext;
import com.ocean.persist.app.dis.AppDisException;
import com.ocean.service.base.BaseHandler;
import com.ocean.service.wankaInvokeHandler.WankaAppasynHandler;
import com.ocean.service.wankaInvokeHandler.WankaPkgSearchHandler;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年10月10日 
      @version 1.0 
 */
public class WankaInvokeHandlerFactory {
    public static BaseHandler getHandler(WankaMethodType interType) throws AppDisException{
    	switch(interType){
    	    case APP_LIST_ASYN_SEARCH:
    	    	return    (WankaAppasynHandler)SystemContext.getServiceHandler().getService(WankaAppasynHandler.class);
    	    case PKG_SEARCH:
    	    	return    (WankaPkgSearchHandler)SystemContext.getServiceHandler().getService(WankaPkgSearchHandler.class);
    	    default:
    	    	throw new AppDisException(ErrorCode.PARAM_ERROR,"undefined interType!");
    	}
    
    }
}
