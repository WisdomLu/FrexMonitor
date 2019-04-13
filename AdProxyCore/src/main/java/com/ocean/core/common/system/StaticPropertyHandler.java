package com.ocean.core.common.system;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**  
 *   
 * 项目名称：   dawdle  
 *  类名称：    StaticPropertyHandler  
 *  创建人：    Alex 
 */
public class StaticPropertyHandler extends PropertyPlaceholderConfigurer  {
	private static  Properties properties;
	private  String configName;
	private  String envName = "TOMCAT_HOME";
	private   String fileEncoding;
	protected final Logger log = LoggerFactory.getLogger(getClass());

	static class singleton{
		private static StaticPropertyHandler staticPropertyHandler=new StaticPropertyHandler();
	}
	public static StaticPropertyHandler getStaticPropertyHandler(){
		return StaticPropertyHandler.singleton.staticPropertyHandler;
	}
	@Override
	protected void loadProperties(Properties p)throws IOException {
		InputStream in = null;
		InputStreamReader reader = null;
		try {
			String rootDir = System.getenv().get(envName);// 配置的环境变量的名字
			if (StringUtils.isEmpty(rootDir)) {
				log.error("the value of environment  paramenter {} is empty,please check and try again!",envName);
				throw new RuntimeException("the value of environment  paramenter "+envName+" is empty");
			}

			log.info("config location:" + rootDir);
			String filename=rootDir + File.separator + "conf"+ File.separator + getConfigName();
			File file = new File(filename);
			if (!file.exists()) {
				log.error("the file {} is not exist!",filename);
			}
			in = new FileInputStream(file);
			reader = new InputStreamReader(in, "UTF-8");
			p.load(reader);
			p.put(Constants.TOMCAT_HOME, rootDir); // add tomcat_home to
			this.setProperties(p);
		} finally {
			if (null != reader) {
				
				reader.close();
			}
			if (null != in) {
				in.close();
			}
		}

	}
	public String get(String key) {
		return properties.getProperty(key);
	}
	public int getInt(String key, int def) {
		String value = get(key);
		if (StringUtils.isEmpty(value)) {
			log.error("the value of key {} is empty",key);
			return def;
		}
		try {
			return Integer.parseInt(value.trim());
		} catch (Exception e) {
			return def;
		}
		
	}
	/**
	 * @return the properties
	 */
	public Properties getProperties() {
		return properties;
	}

	/**
	 * @param properties the properties to set
	 */
	public void setProperties(Properties properties) {
		this.properties = properties;
	}
	/**
	 * @return the configName
	 */
	public String getConfigName() {
		return configName;
	}
	/**
	 * @param configName the configName to set
	 */
	public void setConfigName(String configName) {
		this.configName = configName;
	}
	/**
	 * @return the envName
	 */
	public String getEnvName() {
		return envName;
	}
	/**
	 * @param envName the envName to set
	 */
	public void setEnvName(String envName) {
		this.envName = envName;
	}
	/**
	 * @return the fileEncoding
	 */
	public String getFileEncoding() {
		return fileEncoding;
	}
	/**
	 * @param fileEncoding the fileEncoding to set
	 */
	public void setFileEncoding(String fileEncoding) {
		this.fileEncoding = fileEncoding;
	}
}
