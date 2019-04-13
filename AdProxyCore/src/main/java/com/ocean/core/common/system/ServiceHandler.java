package com.ocean.core.common.system;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;

public class ServiceHandler {

	private ApplicationContext context;

	public ServiceHandler(ApplicationContext context) {
		super();
		this.context = context;
	}
	static class singleton{
		private static ServiceHandler serviceHandler=null;
	}
	public static ServiceHandler getServiceHandler(ApplicationContext context){
		if(ServiceHandler.singleton.serviceHandler==null){
			ServiceHandler.singleton.serviceHandler =new ServiceHandler(context);
		}
		return ServiceHandler.singleton.serviceHandler;
	}
	public Object getService(Class clazz) {
		return context.getBean(clazz);
	}

	public Object getService(String name) {
		return context.getBean(name);
	}

	public <T> List<T> getServices(Class<T> clazz) {
		String[] names = context.getBeanNamesForType(clazz);
		List<T> list = new ArrayList<T>();
		for (String beanNames : names) {
			list.add((T) getService(beanNames));
		}
		return list;
	}

}
