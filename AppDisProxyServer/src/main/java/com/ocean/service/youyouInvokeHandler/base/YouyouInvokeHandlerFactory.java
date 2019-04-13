package com.ocean.service.youyouInvokeHandler.base;

import com.ocean.core.common.system.ErrorCode;
import com.ocean.core.common.system.SystemContext;
import com.ocean.persist.app.dis.AppDisException;
import com.ocean.service.base.BaseHandler;
import com.ocean.service.youyouInvokeHandler.YouyouPkgSearchHandler;

public class YouyouInvokeHandlerFactory {
	public static BaseHandler getHandler(YouyouMethodType interType)
			throws AppDisException {
		switch (interType) {
		case PKG_SEARCH:
			return (YouyouPkgSearchHandler) SystemContext.getServiceHandler()
					.getService(YouyouPkgSearchHandler.class);
		default:
			throw new AppDisException(ErrorCode.PARAM_ERROR,
					"undefined interType!");
		}
	}
}
