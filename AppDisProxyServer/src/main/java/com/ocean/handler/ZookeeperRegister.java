package com.ocean.handler;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.inveno.distribute.client.CommonDistributeServiceManager;
import com.ocean.core.common.UniversalUtils;
import com.ocean.core.common.system.SystemContext;
import com.ocean.persist.common.ProxyConstants;
public class ZookeeperRegister {
	protected static final Logger logger = LoggerFactory.getLogger(ZookeeperRegister.class);
	private Register register;
	public  void regist(){		
		register = new Register();
		register.start();
	}
	private static class Register extends Thread{
		
		@Override
		public void run(){

			try {
				Thread.sleep(10 * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				// 不做处理
			}
			
			CommonDistributeServiceManager serviceManager = new CommonDistributeServiceManager();
			serviceManager.setZkAddress(SystemContext.getStaticPropertyHandler().get(ProxyConstants.ZOOKEEPER_ADDRESS));
			serviceManager.init();
			String ip = SystemContext.getStaticPropertyHandler().get(ProxyConstants.PROXY_ADDRESS);
			// 如果没有配置proxyip 则用本机内网地址
			if(StringUtils.isEmpty(ip)){
				ip = UniversalUtils.getHostAddress();
			}
			int port = SystemContext.getDynamicPropertyHandler().getInt(ProxyConstants.THRIFT_PORT,9091);
			String proxyAddress = ip + ":" + port;
			String path =  SystemContext.getStaticPropertyHandler().get(ProxyConstants.ZOOKEEPER_PATH);
			serviceManager.regist(path, proxyAddress);
			logger.info("Zookeeper register succeed,path={}, proxyAddress={}",path, proxyAddress);
		}
	}
}
