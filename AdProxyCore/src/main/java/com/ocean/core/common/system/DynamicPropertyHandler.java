package com.ocean.core.common.system;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.helpers.FileWatchdog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;







/**  
 * ��Ŀ��ƣ�   dawdle  
 *  ����ƣ�    DynamicPropertyHandler   
 *  �����ˣ�    Alex  
 * @version 1.2
 *   
 */
public class DynamicPropertyHandler  extends FileWatchdog{
	private static  final Logger log = LoggerFactory.getLogger(DynamicPropertyHandler.class);
	private  static Properties properties=new Properties();
	protected DynamicPropertyHandler(String filename){
		super(filename);
		setDaemon(true);
		checkAndConfigure();
		this.setName("T_FileWatchdog");
		this.start();
	}
	static class singleton{
		private static DynamicPropertyHandler dynamicPropertyHandler=null;
	}
	public static DynamicPropertyHandler getDynamicPropertyHandler(String filename){
		if(DynamicPropertyHandler.singleton.dynamicPropertyHandler==null){
			DynamicPropertyHandler.singleton.dynamicPropertyHandler =new DynamicPropertyHandler(filename);
		}
		return DynamicPropertyHandler.singleton.dynamicPropertyHandler;
	}

	/**
	 * @return the properties
	 */
	public Properties getProperties() {
		return DynamicPropertyHandler.properties;
	}

	/**
	 * @param properties the properties to set
	 */
	public void setProperties(Properties properties) {
		DynamicPropertyHandler.properties = properties;
	}
	public String get(String key,String def) {
		try{
			return DynamicPropertyHandler.properties.getProperty(key);
		}catch(Exception e){
			return def;
		}
		
	}
	public String get(String key) {

			return DynamicPropertyHandler.properties.getProperty(key);	
	}
	public int getInt(String key,int def) {
		String val= DynamicPropertyHandler.properties.getProperty(key);
		try{
			return Integer.parseInt(val);
		}catch(Exception e){
			return def;
		}
	}
	public Long getLong(String key,long def) {
		String val= DynamicPropertyHandler.properties.getProperty(key);
		try{
			return Long.parseLong(val);
		}catch(Exception e){
			return def;
		}
	}
	/* (non-Javadoc)
	 * @see org.apache.log4j.helpers.FileWatchdog#doOnChange()
	 */
	@Override
	protected void doOnChange() {
		log.info("dynamic properties intition....");
		InputStream in = null;
		try {
			in = new FileInputStream(filename);
			DynamicPropertyHandler.properties.load(in);
		} catch (Exception e) {
			log.error("properties file changed,inition fired exception!", e);
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					log.error("properties file changed,inition fired exception!", e);
				}
			}
		}
	}
}
