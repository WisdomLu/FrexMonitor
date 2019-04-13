package com.ocean.core.common;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SortUrlParamsUtils {
	
	private static Logger logger = LoggerFactory.getLogger(SortUrlParamsUtils.class);
	/**
	 * @param paraMap 参数
	 * @param encode 编码
	 * @param isLower 是否小写
	 * @return
	 */
	public static String formatUrlParam(Map<String, Object> param, boolean isLower) {
		String params = "";
		Map<String, Object> map = param;
		
		try {
			List<Map.Entry<String, Object>> itmes = new ArrayList<Map.Entry<String, Object>>(map.entrySet());
			
			//对所有传入的参数按照字段名从小到大排序
			//Collections.sort(items); 默认正序
			//可通过实现Comparator接口的compare方法来完成自定义排序
			Collections.sort(itmes, new Comparator<Map.Entry<String, Object>>() {
				@Override
				public int compare(Entry<String, Object> o1, Entry<String, Object> o2) {
					// TODO Auto-generated method stub
					return (o1.getKey().toString().compareTo(o2.getKey()));
				}
			});
			
			//构造URL 键值对的形式
			StringBuffer sb = new StringBuffer();
			for (Map.Entry<String, Object> item : itmes) {
				if (StringUtils.isNotEmpty(item.getKey())) {
					String key = item.getKey();
					Object val = item.getValue();
					//val = URLEncoder.encode(val, encode);
					if (isLower) {
						sb.append(key.toLowerCase() + "=" + val);
					} else {
						sb.append(key + "=" + val);
					}
					sb.append("&");
				}
			}
			
			params = sb.toString();
			if (!params.isEmpty()&&params.endsWith("&")) {
				params = params.substring(0, params.length() - 1);
			}
		} catch (Exception e) {
			return params;
		}
		return params;
	}
	/**
	 * 将对象属性名，值 转换成   “属性名=属性值&属性名=属性值”的格式  (url参数格式)
	 * @param obj 被操作对象
	 * @param except 不需要连接的属性
	 * @throws Exception 
	 */
	public static String toSortHttpParams(Object arg){
		
		String param = make(arg);
		
		return param.substring(0, param.length());
	}

	/**
	 * 字符串连接 
	 */
	private static String make(Object obj){
		Map<String, Object> map=new HashMap<String,Object>();
		if(obj == null) return null;
		make(null, obj, map);
		
		return formatUrlParam(map, false);
		
	}
	
	/**
	 * 字符串连接 
	 */
	private static void make(Class<?> clazz, Object obj, Map<String, Object> map) {

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
					map.put(fieldName, param);
				}
			}
		} 
		catch (Exception e) {
			logger.error("BeanUtil.make | Exception: " + e.getMessage());
		}
		clazz = clazz.getSuperclass();
		if(clazz != null) make(clazz, obj, map);
	}
}