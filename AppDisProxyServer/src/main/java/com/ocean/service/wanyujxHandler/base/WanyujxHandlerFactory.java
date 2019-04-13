package com.ocean.service.wanyujxHandler.base;
import com.ocean.core.common.system.ErrorCode;
import com.ocean.core.common.system.SystemContext;
import com.ocean.persist.app.dis.AppDisException;
import com.ocean.service.base.BaseHandler;
import com.ocean.service.wanyujxHandler.WanyujxPkgSchHandler;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年10月10日 
      @version 1.0 
 */
public class WanyujxHandlerFactory {
    public static BaseHandler getHandler(WanyujxMethodType interType) throws AppDisException{
    	switch(interType){
    	    case PKG_SEARCH:
    	    	return    (WanyujxPkgSchHandler)SystemContext.getServiceHandler().getService(WanyujxPkgSchHandler.class);
    	    default:
    	    	throw new AppDisException(ErrorCode.PARAM_ERROR,"undefined interType!");
    	}
    
    }
}
