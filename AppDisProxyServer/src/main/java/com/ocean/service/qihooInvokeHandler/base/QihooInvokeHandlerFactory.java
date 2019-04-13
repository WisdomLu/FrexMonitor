package com.ocean.service.qihooInvokeHandler.base;

import com.ocean.core.common.system.ErrorCode;
import com.ocean.core.common.system.SystemContext;
import com.ocean.persist.app.dis.AppDisException;
import com.ocean.service.base.BaseHandler;
import com.ocean.service.qihooInvokeHandler.QihooKeywordSearchHandler;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年7月27日 
      @version 1.0 
 */
public class QihooInvokeHandlerFactory {
    public static BaseHandler getHandler(QihooMethodType interType) throws AppDisException{
    	switch(interType){
    	    case KEYWORD_SEARCH:
    	    	return    (QihooKeywordSearchHandler)SystemContext.getServiceHandler().getService(QihooKeywordSearchHandler.class);
/*    	    case PACKAGE_SEARCH:
    	    	return    (GetAppListHandler)SystemContext.getServiceHandler().getService(GetAppListHandler.class);*/
    	    default:
    	    	throw new AppDisException(ErrorCode.PARAM_ERROR,"undefined interType!");
    	}
    	
    }
}
