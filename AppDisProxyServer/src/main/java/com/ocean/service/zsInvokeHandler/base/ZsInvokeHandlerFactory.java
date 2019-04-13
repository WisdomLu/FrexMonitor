package com.ocean.service.zsInvokeHandler.base;

import com.ocean.core.common.system.ErrorCode;
import com.ocean.core.common.system.SystemContext;
import com.ocean.persist.app.dis.AppDisException;
import com.ocean.service.base.BaseHandler;
import com.ocean.service.zsInvokeHandler.ZsListSearchHandler;
import com.ocean.service.zsInvokeHandler.ZsAppasynHandler;
import com.ocean.service.zsInvokeHandler.ZsPkgSearchHandler;

public class ZsInvokeHandlerFactory {
	public static BaseHandler getHandler(ZsMethodType interType)
			throws AppDisException {
		switch (interType) {
		case APP_LIST_SEARCH:
			return (ZsListSearchHandler) SystemContext.getServiceHandler()
					.getService(ZsListSearchHandler.class);
		  case APP_LIST_ASYN_SEARCH:
  	    	return    (ZsAppasynHandler)SystemContext.getServiceHandler().getService(ZsAppasynHandler.class);
  	    case PKG_SEARCH:
  	    	return    (ZsPkgSearchHandler)SystemContext.getServiceHandler().getService(ZsPkgSearchHandler.class);
		default:
			throw new AppDisException(ErrorCode.PARAM_ERROR,
					"undefined interType!");
		}
	}
}
