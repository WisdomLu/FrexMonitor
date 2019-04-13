package com.ocean.service.yrcpd_t2;
import com.ocean.core.common.system.ErrorCode;
import com.ocean.core.common.system.SystemContext;
import com.ocean.persist.app.dis.AppDisException;
import com.ocean.service.base.BaseHandler;
import com.ocean.service.yrcpd.base.YouranMethodType;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年10月10日 
      @version 1.0 
 */
public class YouranT2InvokeHandlerFactory {
    public static BaseHandler getHandler(YouranMethodType interType) throws AppDisException{
    	switch(interType){
    	    case APP_LIST_ASYN_SEARCH:
    	    	return    (YouranT2AppasynHandler)SystemContext.getServiceHandler().getService(YouranT2AppasynHandler.class);
    	    case PKG_SEARCH:
    	    	return    (YouranT2PkgSearchHandler)SystemContext.getServiceHandler().getService(YouranT2PkgSearchHandler.class);
    	    default:
    	    	throw new AppDisException(ErrorCode.PARAM_ERROR,"undefined interType!");
    	}
    
    }
}
