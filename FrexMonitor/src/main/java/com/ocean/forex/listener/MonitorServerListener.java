package com.ocean.forex.listener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.logging.log4j.Logger;

import com.ocean.core.common.lifecycle.LifecycleException;
import com.ocean.core.common.system.MyLogManager;
import com.ocean.forex.server.MonitorServorHandler;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年1月16日 
      @version 1.0 
 */
public class MonitorServerListener implements ServletContextListener {
    private MonitorServorHandler serverHandler=null;
	private  final Logger logger = MyLogManager.getLogger();
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		logger.info("server shutdown......");
		if (null != serverHandler.getServer()) {
			try {
				serverHandler.getServer().destroy();
			} catch (LifecycleException e) {
				// TODO Auto-generated catch block
				logger.info("monitor server destroy error!");
			}
		}
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {	
			logger.error("server shutdown error!",e);
		}
	}

	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		serverHandler=MonitorServorHandler.builder();
		logger.info("start the monitor server......");
		try {
			serverHandler.getServer().start();
		} catch (LifecycleException e) {
			// TODO Auto-generated catch block
			logger.info("start the monitor server error!");
		}

	}
}
