package com.ocean.service.qqInvokHandler.base;
import com.ocean.core.common.system.ErrorCode;
import com.ocean.core.common.system.SystemContext;
import com.ocean.persist.app.dis.AppDisException;
import com.ocean.service.base.BaseHandler;
import com.ocean.service.qqInvokHandler.GetAppListHandler;
import com.ocean.service.qqInvokHandler.GetCategoryListHandler;
import com.ocean.service.qqInvokHandler.GetRankAppADListHandler;
import com.ocean.service.qqInvokHandler.GetRecommendADListHandler;
import com.ocean.service.qqInvokHandler.GetSubjectListHandler;
import com.ocean.service.qqInvokHandler.KeyWordSearchHandler;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年5月15日 
      @version 1.0 
 */
public class QQInvokHandlerFactory {
    public static BaseHandler getHandler(QQMethodType interType)throws AppDisException{
    	switch(interType){
    	    case GET_CATEGORY_LIST:
    	    	return    (GetCategoryListHandler)SystemContext.getServiceHandler().getService(GetCategoryListHandler.class);
    	    case GET_APP_LIST:
    	    	return    (GetAppListHandler)SystemContext.getServiceHandler().getService(GetAppListHandler.class);
    	    case KEYWORD_SEARCH:
    	    	return    (KeyWordSearchHandler)SystemContext.getServiceHandler().getService(KeyWordSearchHandler.class);
    	    case GET_RANK_APP_LIST:
    	    	return    (GetRankAppADListHandler)SystemContext.getServiceHandler().getService(GetRankAppADListHandler.class);
    	    case GET_RECOMMEND_APP_LIST:
    	    	return    (GetRecommendADListHandler)SystemContext.getServiceHandler().getService(GetRecommendADListHandler.class);
    	    case	GET_SUBJECTLIST:
    	    	return (GetSubjectListHandler)SystemContext.getServiceHandler().getService(GetSubjectListHandler.class);
    	    default:
    	    	throw new AppDisException(ErrorCode.PARAM_ERROR,"undefined interType!");
    	}
    	
    }
}
