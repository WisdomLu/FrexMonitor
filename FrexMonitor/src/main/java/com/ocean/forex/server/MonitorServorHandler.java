package com.ocean.forex.server;

import org.apache.commons.lang3.StringUtils;

import com.ocean.core.common.system.SystemContext;
import com.ocean.forex.common.MonitorConstants;
import com.ocean.forex.server.base.AbstractServer;
import com.ocean.forex.service.dataAsyn.DataAsynServiceType;


/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年7月31日 
      @version 1.0 
 */
public class MonitorServorHandler {
	private   MonitorServer disServer=null;
    private  final Object lock=new Object();

    private MonitorServorHandler(){
    	
    }
    private static class BuilderSinglton{
    	private static final MonitorServorHandler builder=new MonitorServorHandler();
    }
    public static MonitorServorHandler builder(){
    	return BuilderSinglton.builder;
    }
	public   MonitorServer getServer(){
		try {
			if(disServer==null){
				synchronized(lock){
					if(disServer==null){
		            		long interval=SystemContext.getDynamicPropertyHandler().getLong(MonitorConstants.MONITOR_SERVICE_INTERVAL, 1000*60);
		            		int queueBufferSize=SystemContext.getDynamicPropertyHandler().getInt(MonitorConstants.MONITOR_TASK_QUEUE_SIZE,64);
		            		AbstractServer.Args args=new AbstractServer.Args();
	            	    	args.setTaskQueueSize(queueBufferSize);
	            	
	            	    	args.setInterval(interval);
							disServer=new MonitorServer(args);
	
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
