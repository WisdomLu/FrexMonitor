package com.ocean.core.common.http;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class Bean2Utils{
	
	private static Logger logger = LoggerFactory.getLogger(Bean2Utils.class);
	
	/**
	 *获取对象全部属性名 不包括静态属性
	 */
	public static List<String> getFieldNames(Object obj){
		
		if(obj == null) return null;
		
		return getFieldNames(obj.getClass());
	}
	
	/**
	 *获取对象全部属性名 不包括静态属性
	 */
	public static List<String> getFieldNames(Class<?> clazz){
		
		if(clazz == null) return null;
		
		List<String> fieldNames = new ArrayList<String>();
		
		addFieldName(clazz, fieldNames);
		
		return fieldNames;
	}

	/**
	 *将对象属性名添加到list中 不包括静态属性
	 */
	private static void addFieldName(Class<?> clazz,List<String> fieldNames){
		
		Field[] fields = clazz.getDeclaredFields();
		
		if(fields == null || fields.length == 0) return;
		
		if(fieldNames == null) fieldNames = new ArrayList<String>();
		
		try 
		{
			for (int i = 0; i < fields.length; i++) 
			{
				
				Field field = fields[i];
				
				int mod = field.getModifiers();
				
				if(Modifier.isStatic(mod)) continue;
				
				String fieldName = field.getName();
				
				fieldNames.add(fieldName);
			}
			
			clazz = clazz.getSuperclass();
			
			if(clazz != null) addFieldName(clazz, fieldNames);
			
		} 
		catch (Exception e) 
		{
			logger.error("getFieldNames | Exception: " + e);
		}
	}

	/**
	 * 将对象属性名，值 转换成   “属性名=属性值&属性名=属性值”的格式  (url参数格式)
	 * @param obj 被操作对象
	 * @param except 不需要连接的属性
	 * @throws Exception 
	 */
	public static String toHttpParams(Object arg){
		
		String param = make(arg, "&");
		
		return param.substring(0, param.length() -1);
	}

	/**
	 * 字符串连接 
	 */
	private static String make(Object obj, String substr){
		
		if(obj == null) return null;
		
		StringBuilder sb = new StringBuilder();
		
		make(null, obj, sb, substr);
		
		return sb.toString();
	}
	
	/**
	 * 字符串连接 
	 */
	private static void make(Class<?> clazz, Object obj, StringBuilder sb, String substr) {
		
		if(clazz == null) clazz = obj.getClass();
		
		Field[] fields = clazz.getDeclaredFields();
		try {
			for (Field field : fields) {
				int mod = field.getModifiers();
				if(Modifier.isStatic(mod)){
					continue;
				} 
				String fieldName = field.getName();
			
				Object param = BeanUtils.getProperty(obj, fieldName);
				if(param != null){
					sb.append(fieldName).append("=").append(param);
					if(!StringUtils.isEmpty(substr)){
						 sb.append(substr);
					}
				}
			}
		} 
		catch (Exception e) {
			logger.error("BeanUtil.make | Exception: " + e.getMessage());
		}
		clazz = clazz.getSuperclass();
		if(clazz != null) make(clazz, obj, sb, substr);
	}
	
	/**
	 * 首字母小写
	 * @param str
	 * @return
	 */
	public static String lowerFirstChar(String str){	
		
		StringBuffer sb = new StringBuffer(str);
		
		int va = (int)sb.charAt(0);
		
		if(((int)'A' <= va) && (va <= (int)'Z')){
			
			sb.setCharAt(0,(char)(va+32));
		}
		return sb.toString();
	}
	
	/**
	 * 
	 * @param str
	 * @return
	 */
	public static String upperFirstChar(String str){	
		
		StringBuffer sb = new StringBuffer(str);
		
		int va = (int)sb.charAt(0);
		
		if(((int)'a' <= va)&&(va <= (int)'z')){
			
			sb.setCharAt(0,(char)(va-32));
		}
		
		return sb.toString();
	}
}
