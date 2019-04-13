package com.ocean.service.miliang;
import com.ocean.core.common.system.ErrorCode;
import com.ocean.core.common.system.SystemContext;
import com.ocean.persist.app.dis.AppDisException;
import com.ocean.service.base.BaseHandler;
import com.ocean.service.base.CommonMethodType;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年10月10日 
      @version 1.0 
 */
public class MiliangInvokeHandlerFactory {
    public static BaseHandler getHandler(CommonMethodType interType) throws AppDisException{
    	switch(interType){
    	    case APP_LIST_ASYN_SEARCH:
    	    	return    (MiliangListSearchHandler)SystemContext.getServiceHandler().getService(MiliangListSearchHandler.class);
    	    default:
    	    	throw new AppDisException(ErrorCode.PARAM_ERROR,"undefined interType!");
    	}
    
    }
}
