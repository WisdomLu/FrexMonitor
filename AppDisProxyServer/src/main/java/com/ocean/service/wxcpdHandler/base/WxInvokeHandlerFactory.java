package com.ocean.service.wxcpdHandler.base;
import com.ocean.core.common.system.ErrorCode;
import com.ocean.core.common.system.SystemContext;
import com.ocean.persist.app.dis.AppDisException;
import com.ocean.service.base.BaseHandler;
import com.ocean.service.wxcpdHandler.WxAppListSearchHandler;
import com.ocean.service.wxcpdHandler.WxAsynAppListSearchHandler;
import com.ocean.service.wxcpdHandler.WxKeywordSearchHandler;
import com.ocean.service.zkInvokeHandler.ZKAppListSearchHandler;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年10月10日 
      @version 1.0 
 */
public class WxInvokeHandlerFactory {
    public static BaseHandler getHandler(WxMethodType interType) throws AppDisException{
    	switch(interType){
    	    case APP_LIST_SEARCH:
    	    	//return    (WxAppListSearchHandler)SystemContext.getServiceHandler().getService(WxAppListSearchHandler.class);
    	    	return    (WxAsynAppListSearchHandler)SystemContext.getServiceHandler().getService(WxAsynAppListSearchHandler.class);
    	    case KEYWORD_SEARCH:
    	    	return    (WxKeywordSearchHandler)SystemContext.getServiceHandler().getService(WxKeywordSearchHandler.class);
    	    default:
    	    	throw new AppDisException(ErrorCode.PARAM_ERROR,"undefined interType!");
    	}
    
    }
}
