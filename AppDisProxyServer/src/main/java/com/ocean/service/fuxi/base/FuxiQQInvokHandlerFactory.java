package com.ocean.service.fuxi.base;
import com.ocean.core.common.system.ErrorCode;
import com.ocean.core.common.system.SystemContext;
import com.ocean.persist.app.dis.AppDisException;
import com.ocean.service.base.BaseHandler;
import com.ocean.service.fuxi.FuxiGetRecommendADListHandler;
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
public class FuxiQQInvokHandlerFactory {
    public static BaseHandler getHandler(FuxiQQMethodType interType)throws AppDisException{
    	switch(interType){
    	    case GET_RECOMMEND_APP_LIST:
    	    	return    (FuxiGetRecommendADListHandler)SystemContext.getServiceHandler().getService(FuxiGetRecommendADListHandler.class);
    	    default:
    	    	throw new AppDisException(ErrorCode.PARAM_ERROR,"undefined interType!");
    	}
    	
    }
}
