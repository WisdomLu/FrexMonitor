package com.ocean.listener;
import java.util.concurrent.CountDownLatch;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ocean.handler.ZookeeperRegister;
import com.ocean.server.ProxyServer;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年1月16日 
      @version 1.0 
 */
public class AppDisProxyServerListener implements ServletContextListener {
    private ProxyServer server=null;
	//protected final Logger logger = LoggerFactory.getLogger(ProxyServerListener.class);
    protected final Log logger = LogFactory.getLog(AppDisProxyServerListener.class);
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
			logger.info("the ProxyServer started......");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		//register to zookeeper
		logger.info("register zookeeper node......");
		ZookeeperRegister regist=new ZookeeperRegister();
		regist.regist();
	}
}
