package com.ocean.listener;

import java.util.concurrent.CountDownLatch;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.logging.log4j.Logger;

import com.ocean.core.common.system.MyLogManager;
import com.ocean.proxy.api.helper.ZookeeperRegister;
import com.ocean.proxy.server.ProxyServer;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年1月16日 
      @version 1.0 
 */
public class ProxyServerListener implements ServletContextListener {
    private ProxyServer server=null;
	private  final Logger logger = MyLogManager.getLogger();
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		logger.info("ProxyServer shutdown......");
		if (null != server) {
			server.shutdownServer();
		}
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {	
			logger.error("ProxyServer shutdown error!",e);
		}
	}

	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		CountDownLatch countdownlatch=new CountDownLatch(1);
		server = new ProxyServer(countdownlatch);
		logger.info("start the ProxyServer......");
		server.startServer();

		try {
			countdownlatch.await();
			logger.info("ProxyServer started succeed......");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			

		//register to zookeeper

/*		try {
			LBSClientDispatcher.build().buildPool();
			logger.info("LBS client pool init succeed");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info("LBS client pool init error {}",e.getMessage(),e);
		}*/
		
		logger.info("register zookeeper node......");
		ZookeeperRegister regist=new ZookeeperRegister();
		regist.regist();
	}
}
