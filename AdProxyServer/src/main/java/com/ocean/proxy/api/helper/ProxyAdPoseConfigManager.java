package com.ocean.proxy.api.helper;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Logger;

import com.ocean.core.common.system.MyLogManager;
import com.ocean.core.common.system.SystemContext;
import com.ocean.persist.dao.proxy.ProxyCacheDao;
import com.ocean.persist.model.proxy.DSPPosition;
import com.ocean.persist.service.proxy.ProxyService;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年11月29日 
      @version 1.0 
 */
public class ProxyAdPoseConfigManager
{
   private static class Temp{
	   private static final ProxyAdPoseConfigManager loader=new ProxyAdPoseConfigManager();
   }
   public static ProxyAdPoseConfigManager buildLoader(){
	   return ProxyAdPoseConfigManager.Temp.loader;
   }
   private static final String KEY_SEPERATE="#";
   private BlockingQueue<String> queue;
   private ProxyAdPoseConfigManager(){
	  queue=new LinkedBlockingQueue<String>();
   }
  
   private Logger logger=MyLogManager.getLogger();
   public void startServer(){
	   ConfigMngThread thread =new ConfigMngThread();
	   thread.setName("proxyCfgThread");
	   thread.start();
   }
   private String  wrapKey(String zkSpaceId,String joinDSP,String ppid){
	   StringBuilder sb=new StringBuilder();
	   sb.append(zkSpaceId).append(KEY_SEPERATE).append(joinDSP);
	   if(StringUtils.isNotEmpty(ppid)){
		   sb.append(KEY_SEPERATE).append(ppid);
	   }
	   return sb.toString();
   }
   public void put(String key){
	   try {
		   
		   if(StringUtils.isNotEmpty(key)&&!queue.contains(key)){
			   queue.offer(key, 200, TimeUnit.MILLISECONDS);
		   }
		
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			logger.error("put ad pose cahekey flag error",e);
			
		}
   }
   private class ConfigMngThread extends Thread{
	  
	   public void run(){
		   while(true){
			   try{
				 
				   this.doWork();
			   }catch(Throwable e){
				   logger.error("pose config fresh error",e);  
			   }
		   }

	   }

	   public String get(){
		   try {
			   return queue.take();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block

				logger.error("get ad pose cahekey flag error",e);
			}
		   return null;
	   }
	   private ProxyService getService(){
		   return (ProxyService)SystemContext.getServiceHandler().getService(ProxyService.class);
	   }
	   private ProxyCacheDao getDao(){
		   return  (ProxyCacheDao)SystemContext.getServiceHandler().getService(ProxyCacheDao.class);
	   }
	   
	   private void doWork(){
		   //get from db
		   String key=this.get();
		   String[] keyArr=key.split(KEY_SEPERATE);
		   DSPPosition pose=null;
		   if(keyArr.length==3){
			   pose= getService().getPoseByZPoseId(keyArr[0],keyArr[1],keyArr[2]);
		   }else{
			   pose= getService().getPoseByZPoseId(keyArr[0],keyArr[1],null);
		   }

		   
		   //cache to redis
		   if(pose!=null){
			   try{
				   getDao().cachePose(keyArr[0],keyArr[1], pose);
				  // getDao().setPosFlag(keyArr[0],keyArr[1], ppid, "1");
			   }catch(Exception e){
				   logger.error("try to catch position info error",e);
			   }
			  
		   }
	   }
   }
}
