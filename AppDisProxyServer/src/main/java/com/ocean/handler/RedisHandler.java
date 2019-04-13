package com.ocean.handler;

import java.util.Set;

import com.inveno.util.CollectionUtils;
import com.ocean.app.dis.proxy.thrift.entity.JoinSource;
import com.ocean.core.common.system.SystemContext;
import com.ocean.persist.dao.proxy.AppDisCacheDao;


/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年5月15日 
      @version 1.0 
 */
public class RedisHandler {
	   private RedisHandler(){}
	   private static class Temp{
		   private static final RedisHandler handler=new RedisHandler();
	   }
	   public static RedisHandler buildHandler(){
		   return RedisHandler.Temp.handler;
	   }

	public  void cacheCategoryId(JoinSource joinSource,Set<String> categoryIds){
		if(CollectionUtils.isEmpty(categoryIds)){
			return;
		}
		AppDisCacheDao redisDao=  (AppDisCacheDao)SystemContext.getServiceHandler().getService(AppDisCacheDao.class);
		
		switch(joinSource){
		   case TENCENT_QQDOWNLOADER:
			   redisDao.cacheQQDownloaderCategoryId(categoryIds);
			   break;
		   default:
			   break;
		}
	}
	public  Set<String> getCacheCategoryId(JoinSource joinSource){
		AppDisCacheDao redisDao=  (AppDisCacheDao)SystemContext.getServiceHandler().getService(AppDisCacheDao.class);
		switch(joinSource){
		   case TENCENT_QQDOWNLOADER:
			   return redisDao.getCachedCategoryIds();

		   default:
              return null;
		}
		 
	}
}
