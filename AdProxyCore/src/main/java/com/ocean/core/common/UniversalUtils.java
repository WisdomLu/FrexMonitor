package com.ocean.core.common;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UniversalUtils {
	
	public static final String CONTENT_CHARSET = "UTF-8";
	
	private static final Logger logger = LoggerFactory.getLogger(UniversalUtils.class);
	
	/**
	 * 随机字符串
	 */
	public static String randomString(int length) {

		String str = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		int range = str.length();

		Random r = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++)
			sb.append(str.charAt(r.nextInt(range)));

		return sb.toString();
	}

	public static String string2Unicode(String string) {
		  
	     StringBuffer unicode = new StringBuffer();
	  
	     for (int i = 0; i < string.length(); i++) {
	  
	         // 取出每一个字符
	        char c = string.charAt(i);
	  
	         // 转换为unicode
	         unicode.append("\\u" + Integer.toHexString(c));
	     }
	  
	     return unicode.toString();
	 }
	
	/**
	 * 随机字符串(只有数字)
	 */
	public static String randomNumber(int length) {

		String str = "0123456789";
		int range = str.length();

		Random r = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++)
			sb.append(str.charAt(r.nextInt(range)));

		return sb.toString();
	}
	
	public static <T, E> void parseMap(Map<T, List<E>> map, E value, T key){
		
		if(map == null) return;
		
		if(!map.containsKey(key))
		{
			List<E> list = new ArrayList<E>();
			list.add(value);
			map.put(key, list);
		}
		else
		{
			List<E> list = map.get(key);
			if(!list.contains(value)) list.add(value);
		}
	}

	/**
	 * 验证是否是邮箱格式
	 */
	public static boolean isEmail(String str) {

		String regex = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
		return matches(str, regex);
	}

	/**
	 * 验证是否是密码格式
	 */
	public static final boolean isPassword(String str) {

		return matches(str, "^([0-9a-f]{32})$");
	}

	/**
	 * 验证是否是数字
	 */
	public static boolean isNumer(String str) {
		
//		if(str == null || str.equals("")) return false;
//		
//		for (int i = 0; i < str.length() - 1; i++) 
//		{
//			 char ch = str.charAt(i);
//			 if(ch < '0' && ch > '9') return false;
//		}
//		return true;
		return matches(str, "[0-9]*");
	}

	/**
	 * 是否是姓名格式("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
	 */
	public static boolean isRealname(String str) {
		
//		Pattern pat1 = Pattern.compile("[\u4E00-\u9FA5]");
		return matches(str, "([\u4E00-\u9FA5]{2,4})$");
	}
	
	/**
	 * 验证是否是手机号 
	 */
	public static boolean isMobile(String str){
		
		return matches(str, "^((13[0-9])|(15[^4,\\D])|(18[0236789])|(14[57]))\\d{8}$");
	}
	
	/**
	 * 是否是可用用户名 
	 */ 
	public static boolean isUsername(String str){
		
		return matches(str, "^[a-zA-Z0-9_]{6,16}$") || isEmail(str);
	}
	
	private static boolean matches(String str, String regex) {

		if(str == null || str.trim().equals("")) return false;
		
		Pattern pat1 = Pattern.compile(regex);
		Matcher mat1 = pat1.matcher(str);
		return mat1.matches();
	}

	/**
	 * 得到异常堆栈详细信息
	 */
	public static String getStackTraceElement(Exception e) {

		if (e == null)
			return null;

		String n = "\n";
		StringBuilder sb = new StringBuilder("系统异常信息: ");
		sb.append(n);

		StackTraceElement[] error = e.getStackTrace();
		for (StackTraceElement stackTraceElement : error)
			sb.append(stackTraceElement.toString()).append(n);

		return sb.toString();
	}
	
	public static String getHostAddress(){
		
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			throw new RuntimeException("获取本机ip失败！！");
		}
	}
	  public static String replaceBlank(String str) {
		  
		  String dest = "";
		  
		  if (str!=null) {
		  
		  	            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
		  
		  	            Matcher m = p.matcher(str);
		  
		  	            dest = m.replaceAll("");
		  
		  }
		  
		   return dest;
		  
	}    /** 
	     * 通过imei的前14位获取完整的imei(15位) 
	     * @param imeiString 
	     * @return 
	     */  
	    public static String getImeiBy14(String imeiString) {  
	    	if(StringUtils.isEmpty(imeiString)){
	    		
	    		return "";
	    	
	    	}

	        String retVal = null;  
	          
	        char[] imeiChar=imeiString.toCharArray();  
	        int resultInt=0;  
	        for (int i = 0; i < imeiChar.length; i++) {  
	            int a=Integer.parseInt(String.valueOf(imeiChar[i]));  
	            i++;  
	            final int temp=Integer.parseInt(String.valueOf(imeiChar[i]))*2;  
	            final int b=temp<10?temp:temp-9;  
	            resultInt+=a+b;  
	        }  
	        resultInt%=10;  
	        resultInt=resultInt==0?0:10-resultInt;  
	        retVal = imeiString+resultInt;  
	        //System.out.println("imei:"+imeiString+resultInt);  
	          
	        return retVal;  
	    }  
	    public static void main(String args[]){
	    	String v="http://a.vlion.cn/ssp?tagid=1051&appid=464&appname=TX 主题商店&pkgname=com.xinzhi.calendar&appversion=2219&os=1&carrier=2&conn=1&ip=113.88.160.188&make=HUAWEI&imei=863175036993356&anid=4ac67fd5e13a94f7&mac=5c:a8:6a:bc:fe:10&lon=114.025973657&lat=22.5460535462&sw=0&sh=0&devicetype=1&ua=&adt=2&news_pagesize=0&news_pageindex=0&zkrequestId=8631750369933561528995737369481";
	    	
	    	System.out.print(UniversalUtils.replaceBlank(v));
	    }

		public static Double convert2Double(String str) {
			try {
				return Double.valueOf(str);
			} catch(Throwable e) {
				logger.error("convert to double error:{}", str);
				return null;
			}
		}
}
