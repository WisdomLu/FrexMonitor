package com.ocean.service.yrcpd.base;
import com.ocean.core.common.system.ErrorCode;
import com.ocean.core.common.system.SystemContext;
import com.ocean.persist.app.dis.AppDisException;
import com.ocean.service.base.BaseHandler;
import com.ocean.service.yrcpd.YouranAppasynHandler;
import com.ocean.service.yrcpd.YouranPkgSearchHandler;
import com.ocean.service.zsInvokeHandler.base.ZsMethodType;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年10月10日 
      @version 1.0 
 */
public class YouranInvokeHandlerFactory {
    public static BaseHandler getHandler(YouranMethodType interType) throws AppDisException{
    	switch(interType){
    	    case APP_LIST_ASYN_SEARCH:
    	    	//return    (WxAppListSearchHandler)SystemContext.getServiceHandler().getService(WxAppListSearchHandler.class);
    	    	return    (YouranAppasynHandler)SystemContext.getServiceHandler().getService(YouranAppasynHandler.class);
    	    case PKG_SEARCH:
    	    	return    (YouranPkgSearchHandler)SystemContext.getServiceHandler().getService(YouranPkgSearchHandler.class);
    	    default:
    	    	throw new AppDisException(ErrorCode.PARAM_ERROR,"undefined interType!");
    	}
    
    }
}
