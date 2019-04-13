package com.ocean.proxy.api.helper;


import org.apache.logging.log4j.Logger;

import com.ocean.core.common.system.MyLogManager;
import com.ocean.core.common.system.SystemContext;
import com.ocean.persist.dao.proxy.ProxyCacheDao;
import com.ocean.persist.model.proxy.DSPPosition;
import com.ocean.persist.model.proxy.PositionTarget;
import com.ocean.persist.service.proxy.ProxyService;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年1月13日 
      @version 1.0 
 */
public class AdSupplierCfgLoader {
   private Logger logger=MyLogManager.getLogger();
   private AdSupplierCfgLoader(){}
   private static class Temp{
	   private static final AdSupplierCfgLoader loader=new AdSupplierCfgLoader();
   }
   public static AdSupplierCfgLoader buildLoader(){
	   return AdSupplierCfgLoader.Temp.loader;
   }
   public DSPPosition getPosition(String joinDSP, String zkSpaceId,String ppid){
	   //get from cache
	   DSPPosition pose=null;
	   ProxyCacheDao redisDao=  (ProxyCacheDao)SystemContext.getServiceHandler().getService(ProxyCacheDao.class);
	   try{
		   pose= redisDao.getPoseCache(joinDSP, zkSpaceId,ppid);
	   }catch(Exception e){
		   logger.error("get position info from cache error,{}",e.getMessage(),e);
	   }
	   if(pose!=null){
		   
		   return pose;
	   }
	   logger.info("get position cache missed,joinDSP:{},zk space id:{}",joinDSP,zkSpaceId);
	   //get from db
	   ProxyService service =(ProxyService)SystemContext.getServiceHandler().getService(ProxyService.class);
	   pose= service.getPoseByZPoseId(zkSpaceId,joinDSP,ppid);
	   
	   //cache to redis
	   if(pose!=null){
		   try{
			   redisDao.cachePose(joinDSP,zkSpaceId, pose);
		   }catch(Exception e){
			   logger.error("try to catch position info error",e);
		   }
		  
	   }

	   return pose;
   }

   public PositionTarget getPositionTarget(String joinDSP,String dspPoseId){
	   //get from cache
/*	   ProxyCacheDao redisDao=  (ProxyCacheDao)SystemContext.getServiceHandler().getService(ProxyCacheDao.class);
	   PositionTarget target=redisDao.getPoseTargetCache(joinDSP, dspPoseId);
	   if(target!=null){
		   logger.info("get position target cache missed,joinDSP:{},dsp position id:{}",joinDSP,dspPoseId);
		   return target;
	   }*/
	   //get from db
	   ProxyService service =(ProxyService)SystemContext.getServiceHandler().getService(ProxyService.class);
	   PositionTarget target=service.getPositionTarget(dspPoseId);
	   
	   //cache to redis
	  /* 
	   if(pose!=null){
	      try{
	          redisDao.cachePoseTarget(joinDSP,dspPoseId, target);
	      		   }catch(Exception e){
			   logger.error("try to catch position target info error",e);
		   }
	    }
	      */
	   return target;
   }
}
