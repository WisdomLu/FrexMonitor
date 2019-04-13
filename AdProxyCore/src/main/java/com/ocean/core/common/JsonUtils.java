package com.ocean.core.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonUtils {
	
	private static final Gson gson=new GsonBuilder()
    .disableHtmlEscaping()
    .create();
	
	private static final JsonParser parser = new JsonParser();
	
	/**
	 * 将字符串转对象 
	 * @return 
	 */
	public static <T> T toBean(String json,Class<T> cls){
		
		if(StringUtils.isEmpty(json))
		{
			return null;
		}	
		return gson.fromJson(json, cls);
	}
	
	/**
	 * 将对象转为json格式字符串 
	 */
	public static String toJson(Object object){
		
		return gson.toJson(object);
	}
	
	/**
	 * 获取子节点下的数组元素 
	 */
	public static <T> List<T> getAsJsonList(Class<T> clazz, String json, String name){
		
        JsonObject jsonObject = parser.parse(json).getAsJsonObject();
        //将data节点下的内容转为JsonArray
        JsonArray jsonArray = jsonObject.getAsJsonArray(name);
        List<T> list = new ArrayList<T>();
        for (int i = 0; i < jsonArray.size(); i++) 
        {
        	//获取第i个数组元素
            JsonElement el = jsonArray.get(i);
            //映射为类实例
            T data = gson.fromJson(el, clazz);
            list.add(data);
        }
        return list;
	}
	
	/**
	 * 获取子节点下的对象
	 */
	public static <T> T getJsonObject(Class<T> clazz, String json, String name){
		
        JsonObject jsonObject = parser.parse(json).getAsJsonObject();
        JsonElement element = jsonObject.get(name);
        return gson.fromJson(element, clazz);
	}
	
	/**
	 * 获取子节点下的元素
	 */
	public static JsonElement getJsonElement(String json, String name){
		
		JsonObject jsonObject = parser.parse(json).getAsJsonObject();
		return jsonObject.get(name);
	}
	
	/**
	 * 获取子节点下的元素
	 */
	public static Boolean getBoolean(String json, String name){
		
		return getJsonElement(json, name).getAsBoolean();
	}
	
	/**
	 * 获取子节点下的元素
	 */
	public static String getString(String json, String name){
		
		return getJsonElement(json, name).getAsString();
	}
	
	/**
	 * 获取子节点下的元素
	 */
	public static int getInteger(String json, String name){
		
		return getJsonElement(json, name).getAsInt();
	}
	
	public static void main(String[] args) {
		
		List<String> us = new ArrayList<String>();
		us.add("http://www.baidu.com");
		
		Map<String, List<String>> urls = new HashMap<String, List<String>>();
		urls.put("1", us);
		
		SubObj subObj = new SubObj();
		subObj.setUrls(urls);
		
		TestObj testObj = new TestObj();
		testObj.setA("a");
		testObj.setSubObj(subObj);
		String jsonstr = toJson(testObj);
		System.out.println(jsonstr);
		
		testObj = toBean(jsonstr, TestObj.class);
		System.out.println(testObj.getSubObj().getUrls().get("1"));
	}
	/**
	 * 验证是否存在该子节点 
	 */
	public static boolean exists(String json, String name){
		
		JsonElement jsonElement = getJsonElement(json, name);
		if(jsonElement == null){
			return false;
		}
		return true;
	}
	
	static class TestObj{
		
		private String a;
		private SubObj subObj;
		public String getA() {
			return a;
		}
		public void setA(String a) {
			this.a = a;
		}
		public SubObj getSubObj() {
			return subObj;
		}
		public void setSubObj(SubObj subObj) {
			this.subObj = subObj;
		}
	}
	
	static class SubObj{
		private Map<String, List<String>> urls;

		public Map<String, List<String>> getUrls() {
			return urls;
		}

		public void setUrls(Map<String, List<String>> urls) {
			this.urls = urls;
		}
	}
}
