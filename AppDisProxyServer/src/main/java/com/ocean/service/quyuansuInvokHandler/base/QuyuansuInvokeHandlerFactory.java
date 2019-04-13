package com.ocean.service.quyuansuInvokHandler.base;
import com.ocean.core.common.system.ErrorCode;
import com.ocean.core.common.system.SystemContext;
import com.ocean.persist.app.dis.AppDisException;
import com.ocean.service.base.BaseHandler;
import com.ocean.service.quyuansuInvokHandler.QuyuansuHotwdSearchHandler;
import com.ocean.service.quyuansuInvokHandler.QuyuansuKwdSearchHandler;
import com.ocean.service.quyuansuInvokHandler.QuyuansuPkgSearchHandler;
import com.ocean.service.quyuansuInvokHandler.QuyuansuListSearchHandler;
import com.ocean.service.quyuansuInvokHandler.QuyuansuRcmdSearchHandler;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年10月10日 
      @version 1.0 
 */
public class QuyuansuInvokeHandlerFactory {
    public static BaseHandler getHandler(QuyuansuMethodType interType) throws AppDisException{
    	switch(interType){
    	    case PACKAGE_SEARCH:
    	    	return    (QuyuansuPkgSearchHandler)SystemContext.getServiceHandler().getService(QuyuansuPkgSearchHandler.class);
    	    case KEYWORD_SEARCH:
    	    	return    (QuyuansuKwdSearchHandler)SystemContext.getServiceHandler().getService(QuyuansuKwdSearchHandler.class);
    	    case APP_LIST_SEARCH:
    	    	return   (QuyuansuListSearchHandler)SystemContext.getServiceHandler().getService(QuyuansuListSearchHandler.class);
    	    case HOTWORD_SEARCH:
    	    	return   (QuyuansuHotwdSearchHandler)SystemContext.getServiceHandler().getService(QuyuansuHotwdSearchHandler.class);	
    	    case APP_RECOMMEND_SEARCH:
    	    	return   (QuyuansuRcmdSearchHandler)SystemContext.getServiceHandler().getService(QuyuansuRcmdSearchHandler.class);
    	    default:
    	    	throw new AppDisException(ErrorCode.PARAM_ERROR,"undefined interType!");
    	}
    
    }
}
