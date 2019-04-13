package com.ocean.service.baitongInvokerHandler.base;
import com.ocean.core.common.system.ErrorCode;
import com.ocean.core.common.system.SystemContext;
import com.ocean.persist.app.dis.AppDisException;
import com.ocean.service.baitongInvokerHandler.BaitongPkgSearchHandler;
import com.ocean.service.base.BaseHandler;
import com.ocean.service.wankaInvokeHandler.WankaAppasynHandler;
import com.ocean.service.wankaInvokeHandler.WankaPkgSearchHandler;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年10月10日 
      @version 1.0 
 */
public class BaitongInvokeHandlerFactory {
    public static BaseHandler getHandler(BaitongMethodType interType) throws AppDisException{
    	switch(interType){
    	   case PKG_SEARCH:
    	    	return    (BaitongPkgSearchHandler)SystemContext.getServiceHandler().getService(BaitongPkgSearchHandler.class);
    	    default:
    	    	throw new AppDisException(ErrorCode.PARAM_ERROR,"undefined interType!");
    	}
    
    }
}
