package com.ocean.forex.service.dataAsyn;

import org.apache.logging.log4j.Logger;

import com.ocean.core.common.system.MyLogManager;
import com.ocean.core.common.system.SystemContext;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
import com.ocean.core.common.threadpool.workthread.AsynTaskDisServer;
import com.ocean.forex.service.dataAsyn.task.TaskServerDispatcher;

public abstract class DefaultDataAsynService {
	public  final Logger logger = MyLogManager.getLogger();
	public abstract AsynAbstractTask packageTask(Parameter params,String hashCode);
	public <T extends IDataAsynResponse> Object invoke(Parameter params,Class<? extends IDataAsynResponse> pojo, String... exts){
		return asynRequest(params, exts[0]);

	}
	//异步提交任务
	public final Object asynRequest(Parameter params,String hashCode){
		AsynAbstractTask task=this.packageTask(params, hashCode);
		AsynTaskDisServer ds=TaskServerDispatcher.builder().getTaskHandler();
		boolean succeed=ds.accept(task);//提交任务
		if(!succeed){
			return null;
		}
		int timeOut = SystemContext.getDynamicPropertyHandler().getInt("http.time.out", 400);
		Object resp=null;
		try {
			 resp=task.getResponseAsyn(timeOut);
		}catch (InterruptedException e) {
			// TODO Auto-generated catch block
			logger.error("get task {} result error",task.getHashCode(), e);
			task.setValid(false);
			
		}finally{
			task=null;
		}
		return resp;
	}
}
