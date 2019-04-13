package com.ocean.service.daluomaInvokeHandler.base;

import com.ocean.core.common.system.ErrorCode;
import com.ocean.core.common.system.SystemContext;
import com.ocean.persist.app.dis.AppDisException;
import com.ocean.service.base.BaseHandler;
import com.ocean.service.daluomaInvokeHandler.DaluomaKeywordSearchHandler;
import com.ocean.service.daluomaInvokeHandler.DaluomaPkgSearchHandler;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年7月27日 
      @version 1.0 
 */
public class DaluomaInvokeHandlerFactory {
    public static BaseHandler getHandler(DaluomaMethodType interType) throws AppDisException{
    	switch(interType){
    	    case KEYWORD_SEARCH:
    	    	return    (DaluomaKeywordSearchHandler)SystemContext.getServiceHandler().getService(DaluomaKeywordSearchHandler.class);
    	    case PACKAGE_SEARCH:
    	    	return    (DaluomaPkgSearchHandler)SystemContext.getServiceHandler().getService(DaluomaPkgSearchHandler.class);

    	    default:
    	    	throw new AppDisException(ErrorCode.PARAM_ERROR,"undefined interType!");
    	}
    	
    }
}
