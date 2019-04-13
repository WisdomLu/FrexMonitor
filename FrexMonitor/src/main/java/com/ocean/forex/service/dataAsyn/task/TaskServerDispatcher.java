package com.ocean.forex.service.dataAsyn.task;

import com.ocean.core.common.system.SystemContext;
import com.ocean.core.common.threadpool.workthread.AsynTaskDisServer;
/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年7月31日 
      @version 1.0 
 */
public class TaskServerDispatcher {
	private   AsynTaskDisServer disServer=null;
    private  final Object lock=new Object();

    private TaskServerDispatcher(){
    	
    }
    private static class BuilderSinglton{
    	private static final TaskServerDispatcher builder=new TaskServerDispatcher();
    }
    public static TaskServerDispatcher builder(){
    	return BuilderSinglton.builder;
    }
	public   AsynTaskDisServer getTaskHandler(){
		
		
		try {
			if(disServer==null){
				synchronized(lock){
					if(disServer==null){
		            		int queueBufferSize = 8;
		            		//int acceptThread=SystemContext.getDynamicPropertyHandler().getInt(ProxyConstants.THRIFT_IO_SELECTOR_COUNT,8);
		            		int maxWorker = 16;
		            		AsynTaskDisServer.Args args=new AsynTaskDisServer.Args();
	            	    	args.setQueueSize(queueBufferSize);//工作线程的缓冲队列大小
	            	    	args.setWorkThread(maxWorker);//工作线程的数量
	            	    	args.setPoolSize(maxWorker/2);//接收任务线程池的大小
							disServer=new AsynTaskDisServer(args);
	
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return disServer;
	}
  
}
