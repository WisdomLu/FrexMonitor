package com.ocean.service.qihooT2InvokeHandler.base;

import com.ocean.core.common.system.ErrorCode;
import com.ocean.core.common.system.SystemContext;
import com.ocean.persist.app.dis.AppDisException;
import com.ocean.service.base.BaseHandler;
import com.ocean.service.qihooInvokeHandler.base.QihooMethodType;
import com.ocean.service.qihooT2InvokeHandler.Qihoo360T2KeywordSearchHandler;
import com.ocean.service.qihooT2InvokeHandler.Qihoo360T2AppRcmdSearchHandler;
import com.ocean.service.qihooT2InvokeHandler.Qihoo360T2PkgSearchHandler;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年7月27日 
      @version 1.0 
 */
public class Qihoo360T2InvokeHandlerFactory {
    public static BaseHandler getHandler(QihooMethodType interType) throws AppDisException{
    	switch(interType){
    	    case KEYWORD_SEARCH:
    	    	return    (Qihoo360T2KeywordSearchHandler)SystemContext.getServiceHandler().getService(Qihoo360T2KeywordSearchHandler.class);
    	    case APP_RECOMMEND_SEARCH:
    	    	return    (Qihoo360T2AppRcmdSearchHandler)SystemContext.getServiceHandler().getService(Qihoo360T2AppRcmdSearchHandler.class);

    	    case PACKAGE_SEARCH:
    	    	return    (Qihoo360T2PkgSearchHandler)SystemContext.getServiceHandler().getService(Qihoo360T2PkgSearchHandler.class);

    	    default:
    	    	throw new AppDisException(ErrorCode.PARAM_ERROR,"undefined interType!");
    	}
    	
    }
}
