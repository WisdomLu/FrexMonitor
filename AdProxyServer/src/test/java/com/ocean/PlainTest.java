package com.ocean;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.ocean.core.common.http.HttpClient;
import com.ocean.core.common.http.HttpClientPlain;
import com.ocean.core.common.http.HttpInvokeException;


/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年5月22日 
      @version 1.0 
 */
public class PlainTest {

	private int num=9;
    public static void main(String[] args) {	
/*    			String str="http://sspapi.gm825.net/api_2";
    			System.out.println(str.replaceAll("\\$\\{clickpixels\\}", "%%DOWNX%%_%%DOWNY%%"));
    			Integer i1 = 200;
    			Integer i2 = 200;
    			System.out.println(i1.equals(i2));
    			System.out.println(i1==i2);*/
    	StringBuilder st=new StringBuilder();
    	st.append("https://chart.jin10.com/baojia.php?").append("type=").append("xauusd").append("&_=");
    	String data="";
		for(int m=0;m<1;m++){
		    	try {
		    		 
		    		 System.out.println(st.toString()+System.currentTimeMillis());
		       		 data=HttpClientPlain.getInstance().get(st.toString()+System.currentTimeMillis());
		   		} catch (HttpInvokeException e) {
		   			// TODO Auto-generated catch block
		   			e.printStackTrace();
		   		}
		       	if(StringUtils.isEmpty(data)){
		       		continue;
		       	}
		       	String str=data.substring(data.indexOf("["),data.length()-1);
		       	JSONArray jsonArray=JSONArray.fromObject(str);
		       	Object[] os = jsonArray .toArray();
		       	double sum=0;
		       	int len=os.length>120?120:os.length;
		       
		       	for(int i=len;i>0; i--) {
		           	
		       		JSONArray dataArr = JSONArray.fromObject(os[os.length-i]);
		       		
		       		
		       		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//这个是你要转成后的时间的格式
		       		String sd = sdf.format(new Date(Long.parseLong(String.valueOf(dataArr .toArray()[0]))));   // 时间戳转换成时间
		       		System.out.println("time:"+sd+" value:"+dataArr .toArray()[1]);
		       		Object num=dataArr .toArray()[1];
		       		if(num instanceof Integer){
		       			sum+=((Integer)num)/1.0;
		       		}else if(num instanceof Double){
		       			sum+=(Double)num;
		       		}
		       		
		       	}
		       	System.out.println(sum/len);
		       	try {
					Thread.sleep(20*1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		       	
		       	
    	}
    	
    }
    @Test
    public void test(){
    	Student student=new Student();
    	MyThread thread1=new MyThread(student);
    	MyThread thread2=new MyThread(student);
   
    	thread1.start();
    	thread2.start();
    }

	protected class  Student{
		private final Object lock=new Object();
		private int age;
		public void setAge(){
			System.out.println(	Thread.currentThread().getName()+" thread start...");
			synchronized(lock){
				System.out.println(	Thread.currentThread().getName()+"  block start...");
				while(true){
					int random=(int)(Math.random()*10);
					System.out.println(Thread.currentThread().getName()+" random:"+random+" age:"+age);
					if(this.age==random){
						System.out.println(	Thread.currentThread().getName()+" thread is interrupted...");
						Thread.currentThread().interrupt();
						
					}
					if(Thread.currentThread().isInterrupted()){
						break;
					}
					this.age=random;
				}

			}
		}
		public int getAge(){
			
				return age;
	
		}
	}
    protected class MyThread extends Thread{
    	private Student student;
    	MyThread( Student student){
    		this.student=student;
    	}
    	public void run(){
    
    		
    		student.setAge();

    	}

    }
    public static void setValue(String value){
    	System.out.println(value);
    }
    public static  String getValue(String url){
    	String rpStr=null;
    	String aa="%%dooo_x%%";
 
    	try {
    	   	rpStr = URLEncoder.encode("{\"down_x\":\""+aa+"\",\"down_y\":\""+aa+"\",\"up_x\":\""+aa+"\",\"up_y\":\""+aa+"\"}","UTF-8");
	    	return url.replaceAll("{CLICK_POSITION}", rpStr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("value format error");
		}finally{
			System.out.println(rpStr);
		}
    	return url;
    }
    
}
/*class Outer {
	 
    class Inner {}
 
    public static void foo() { new Outer().new Inner(); }
 
    public void bar() { new Inner(); }
 
    public static void main(String[] args) {
        new Inner();
    }
}*/
abstract class Doctor {
     //多态，情况不定
     public void workInDay(){
         System.out.println("白天传授理论知识");
     }
     public abstract void test();
 }

class OutClass {  
    public Doctor getInnerClass(final int age,final String name){  
        return new Doctor() {  
            int age_ ;  
            String name_;  
            //构造代码块完成初始化工作  
            {  
                if(0 < age && age < 200){  
                    age_ = age;  
                    name_ = name;  
                }  
            }  
            public String getName() {  
                return name_;  
            }  
              
            public int getAge() {  
                return age_;  
            }

			@Override
			public void test() {
				// TODO Auto-generated method stub
				
			}  
        };  
    } 
}