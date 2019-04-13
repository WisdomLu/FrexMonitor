package com.ocean.service.baiduInvokeHandler.base;

import com.ocean.core.common.system.ErrorCode;
import com.ocean.core.common.system.SystemContext;
import com.ocean.persist.app.dis.AppDisException;
import com.ocean.service.baiduInvokeHandler.BaiduKeywordSearchHandler;
import com.ocean.service.base.BaseHandler;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年10月10日 
      @version 1.0 
 */
public class BaiduInvokeHandlerFactory {
    public static BaseHandler getHandler(BaiduMethodType interType) throws AppDisException{
    	switch(interType){
    	    case KEYWORD_SEARCH:
    	    	return    (BaiduKeywordSearchHandler)SystemContext.getServiceHandler().getService(BaiduKeywordSearchHandler.class);
    	    default:
    	    	throw new AppDisException(ErrorCode.PARAM_ERROR,"undefined interType!");
    	}
    	
    }
}
