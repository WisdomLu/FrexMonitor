package com.ocean.service.base;

import org.apache.logging.log4j.Logger;

import com.ocean.core.common.system.MyLogManager;
import com.ocean.core.common.system.SystemContext;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
import com.ocean.core.common.threadpool.workthread.AsynTaskDisServer;
import com.ocean.persist.api.proxy.AdPullResponse;
import com.ocean.persist.app.dis.AppDisResponse;
import com.ocean.task.TaskServerDispatcher;

public abstract class AbstractAsynAppSupplier {
	public abstract AsynAbstractTask packageTask(Parameter params,String hashCode);
	public static final Logger LOGGER = MyLogManager.getLogger();
	public <T extends AdPullResponse> Object invoke(Parameter params,Class<? extends AppDisResponse> pojo, String... exts){
		// TODO Auto-generated method stub
/*		AsynInvokeResponse data=asynRequest(params, exts[0]);
		if(data!=null){
			logger.info("{} ad return result:{}",exts, data);
			return pojo.cast(data.getData());
		}
		return null*/
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
			LOGGER.error("get task {} result error",task.getHashCode(), e);
			task.setValid(false);
			
		}finally{
			task=null;
		}
		return resp;
	}
}
