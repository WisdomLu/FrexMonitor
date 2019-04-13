package com.ocean.service.tianmeiInvokeHandler.base;
import com.ocean.core.common.system.ErrorCode;
import com.ocean.core.common.system.SystemContext;
import com.ocean.persist.app.dis.AppDisException;
import com.ocean.service.base.BaseHandler;
import com.ocean.service.tianmeiInvokeHandler.TianmeiListSearchHandler;
import com.ocean.service.tianmeiInvokeHandler.TianmeiKeywordSearchHandler;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年10月10日 
      @version 1.0 
 */
public class TianmeiInvokeHandlerFactory {
    public static BaseHandler getHandler(TianmeiMethodType interType) throws AppDisException{
    	switch(interType){
    	    case APP_LIST_SEARCH:
    	    	return    (TianmeiListSearchHandler)SystemContext.getServiceHandler().getService(TianmeiListSearchHandler.class);
    	    case KEYWORD_SEARCH:
    	    	return    (TianmeiKeywordSearchHandler)SystemContext.getServiceHandler().getService(TianmeiKeywordSearchHandler.class);
    	    default:
    	    	throw new AppDisException(ErrorCode.PARAM_ERROR,"undefined interType!");
    	}
    
    }
}
