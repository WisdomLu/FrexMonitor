package com.ocean.core.common.system;
import java.io.File;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
/**    
 * ��Ŀ��ƣ�   dawdle  
 *  ����ƣ�    SystemContext  
 *  �����ˣ�    Alex 
 * ����ʱ�䣺  2016��8��5�� 
 * �޸�ʱ�䣺  2016��8��5��  
 */
public class SystemContext implements ApplicationContextAware{
	private static DynamicPropertyHandler  dynamicPropertyHandler;
	private static StaticPropertyHandler staticPropertyHandler=StaticPropertyHandler.getStaticPropertyHandler();
	private static ApplicationContext context;
	private static ServiceHandler serviceHandler;
	protected final Logger log = LoggerFactory.getLogger(getClass());
	/* (non-Javadoc)
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 */
	public void setApplicationContext(ApplicationContext context)throws BeansException{
		SystemContext.context = context;
		String webHome = getStaticPropertyHandler().get(Constants.TOMCAT_HOME);
		if (StringUtils.isEmpty(webHome)) {
			log.error("web home {} have not been initialized,please check and try again!",Constants.TOMCAT_HOME);
			return;
		}

		String dynicProFileName = getStaticPropertyHandler().get(Constants.DYNIC_RESOURCE_FILENAME);
		if (StringUtils.isEmpty(dynicProFileName)) {
			log.error("the dynamic properties {} have not been initialized,please check and try again!",Constants.DYNIC_RESOURCE_FILENAME);
			return;
		}
		log.debug("dynamic Pro File Name: " + dynicProFileName);
		File file = new File(webHome + File.separator + "conf" + File.separator + dynicProFileName);
		if (!file.exists()) {
			log.error("the dynamic Pro File Name  is not exist");
			return;
		}
		dynamicPropertyHandler = DynamicPropertyHandler.getDynamicPropertyHandler(file.getAbsolutePath());
	}

	/**
	 * @return the dynamicPropertyHandler
	 */
	public static DynamicPropertyHandler getDynamicPropertyHandler() {
		return dynamicPropertyHandler;
	}

	/**
	 * @param dynamicPropertyHandler the dynamicPropertyHandler to set
	 */
	public static void setDynamicPropertyHandler(
			DynamicPropertyHandler dynamicPropertyHandler) {
		SystemContext.dynamicPropertyHandler = dynamicPropertyHandler;
	}

	/**
	 * @return the staticPropertyHandler
	 */
	public static StaticPropertyHandler getStaticPropertyHandler() {
		return staticPropertyHandler;
	}

	/**
	 * @param staticPropertyHandler the staticPropertyHandler to set
	 */
	public static void setStaticPropertyHandler(
			StaticPropertyHandler staticPropertyHandler) {
		SystemContext.staticPropertyHandler = staticPropertyHandler;
	}

	/**
	 * @return the context
	 */
	public static ApplicationContext getContext() {
		return context;
	}

	/**
	 * @param context the context to set
	 */
	public static void setContext(ApplicationContext context) {
		SystemContext.context = context;
	}

	/**
	 * @return the serviceHandler
	 */
	public static ServiceHandler getServiceHandler() {
		return ServiceHandler.getServiceHandler(context);
	}

	/**
	 * @param serviceHandler the serviceHandler to set
	 */
	public static void setServiceHandler(ServiceHandler serviceHandler) {
		SystemContext.serviceHandler = serviceHandler;
	}
	public void destroy() {
		if (null != dynamicPropertyHandler) {
			dynamicPropertyHandler.interrupt();
		}
	}


}
