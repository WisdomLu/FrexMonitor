package com.ocean.proxy.api.base;

import com.ocean.core.common.system.SystemContext;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
import com.ocean.core.common.threadpool.workthread.AsynTaskDisServer;
import com.ocean.persist.api.proxy.AdPullResponse;
import com.ocean.proxy.serverdis.TaskServerDispatcher;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年8月16日 
      @version 1.0 
 */
public abstract class AbstractAsynAdSupplier extends AbstractAdSupplier{
	private static final String HTTP_TIME_OUT="http.time.out";
	 //private final int TIME_INTERVAL=30;
	public abstract AsynAbstractTask packageTask(Parameter params,String hashCode);
	
	public <T extends AdPullResponse> Object invoke(Parameter params,Class<? extends AdPullResponse> pojo, String... exts){
		// TODO Auto-generated method stub
/*		AsynInvokeResponse data=asynRequest(params, exts[0]);
		if(data!=null){
			logger.info("{} ad return result:{}",exts, data);
			return pojo.cast(data.getData());
		}
		return null*/
		return asynRequest(params, exts[0]);

	}

	//轮询
/*	public Object asynRequest(Parameter params,String hashCode){
		AsynAbstractTask task=this.packageTask(params, hashCode);
		AsynTaskDisServer ds=TaskServerDispatcher.builder().getTaskHandler();
		boolean succeed=ds.accept(task);//提交任务
		if(!succeed){
			return null;
		}
		int timeOut = SystemContext.getDynamicPropertyHandler().getInt("http.time.out", 500);
		Object resp=null;
		try {
			Thread.sleep(TIME_INTERVAL);//结果没这么快返回，先等待一段时间
			timeOut-=TIME_INTERVAL;
			while(timeOut>=0&&task.isValid()){
				//resp=ds.getResult(task.getHashCode());
				resp=task.getRespose();
				if(resp==null){
						Thread.sleep(TIME_INTERVAL);
						timeOut-=TIME_INTERVAL;
				}else{
					    break;
				}
			}
		}catch (InterruptedException e) {
			// TODO Auto-generated catch block
			logger.error("get task {} result error",task.getHashCode(), e);
			task.setValid(false);
			
		}finally{
			task.setRespose(null);
		}
		return resp;
	}*/
	//异步提交任务
	public final Object asynRequest(Parameter params,String hashCode){
		AsynAbstractTask task=this.packageTask(params, hashCode);
		AsynTaskDisServer ds=TaskServerDispatcher.builder().getTaskHandler();
		boolean succeed=ds.accept(task);//提交任务
		if(!succeed){
			return null;
		}
		int timeOut = SystemContext.getDynamicPropertyHandler().getInt(HTTP_TIME_OUT, 400);
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
