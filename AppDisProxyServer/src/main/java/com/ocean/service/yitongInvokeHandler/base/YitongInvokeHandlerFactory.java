package com.ocean.service.yitongInvokeHandler.base;
import com.ocean.core.common.system.ErrorCode;
import com.ocean.core.common.system.SystemContext;
import com.ocean.persist.app.dis.AppDisException;
import com.ocean.service.base.BaseHandler;
import com.ocean.service.yitongInvokeHandler.YitongKwdSearchHandler;
import com.ocean.service.yitongInvokeHandler.YitongListSearchHandler;
import com.ocean.service.yitongInvokeHandler.YitongPkgSearchHandler;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年10月10日 
      @version 1.0 
 */
public class YitongInvokeHandlerFactory {
    public static BaseHandler getHandler(YitongMethodType interType) throws AppDisException{
    	switch(interType){
    	    case APP_LIST_SEARCH:
    	    	return    (YitongListSearchHandler)SystemContext.getServiceHandler().getService(YitongListSearchHandler.class);
    	    case KEYWORD_SEARCH:
    	    	return    (YitongKwdSearchHandler)SystemContext.getServiceHandler().getService(YitongKwdSearchHandler.class);
    	    case PACKAGE_SEARCH:
    	    	return    (YitongPkgSearchHandler)SystemContext.getServiceHandler().getService(YitongPkgSearchHandler.class);
    	    	
    	    	
    	    default:
    	    	throw new AppDisException(ErrorCode.PARAM_ERROR,"undefined interType!");
    	}
    
    }
}
