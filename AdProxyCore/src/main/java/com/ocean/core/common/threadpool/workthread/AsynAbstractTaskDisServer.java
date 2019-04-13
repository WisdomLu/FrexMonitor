package com.ocean.core.common.threadpool.workthread;

import org.apache.logging.log4j.Logger;

import com.ocean.core.common.system.MyLogManager;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年7月21日 
      @version 1.0 
 */
public abstract class AsynAbstractTaskDisServer extends AbstractTaskDisServer{
	public  final Logger LOGGER = MyLogManager.getDisLoggerV2();
     public  static abstract  class AsynAbstractServerArgs<T extends AsynAbstractServerArgs<T>> extends AbstractServerArgs<T>{
			
	 }
     public AsynAbstractTaskDisServer(AsynAbstractServerArgs args){
    	 super(args);
     }
     protected class AbstractWorkThread extends Thread{}
	 protected abstract void startThreads();
	 protected abstract void shutdown();
	 public boolean accept(AsynAbstractTask task){
		 return doAccept(task);
	 }
	 protected abstract  boolean  doAccept(AsynAbstractTask task);
}
