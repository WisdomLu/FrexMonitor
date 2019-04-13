package com.ocean.core.common;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.persistence.Column;

import org.apache.logging.log4j.Logger;
import com.ocean.core.common.system.MyLogManager;
/* 通用查询条件注入
 * @author Alex 2015-10-28
 * */
public class AsignByAnotationUtils<F> {
	private static final Logger LOGGER=MyLogManager.getLogger();
	/* 通用查询条件注入方法
	 * @author Alex 2015-10-28
	 * @return F
	 * */
	 public F asign(F from,String fieldName,String value) throws Exception{
        Field[] fields_f = from.getClass().getDeclaredFields();
        for (short i = 0; i < fields_f.length; i++) {
		             Field field_f = fields_f[i];
		             String field_FName = field_f.getName();
                     String setMethodName_f = "set"+ field_FName.substring(0, 1).toUpperCase()+ field_FName.substring(1);//获取源对象的getter方法
                     try {

                         //判断是否有指定的注解
                         if (field_f.isAnnotationPresent(Column.class)&&(value instanceof String)) {
                             //AnnotatedElement接口中的方法getAnnotation(),获取传入注解类型的注解
                        	 Column dateSearchField = field_f.getAnnotation(Column.class);
                             //拿到注解中的值，即查询参数名称
                             String name = dateSearchField.name();
                             if(fieldName.equals(name)){//如果字段名称相同
                            	 Class<?> fCla=from.getClass();//获取目标类的类和方法
                                 Class<?> typeClass_t = field_f.getType();

                                 Method method_f = fCla.getDeclaredMethod(setMethodName_f, new Class[]{typeClass_t});
                                 method_f.invoke(from, new Object[]{value});
                             }
                            
                         }
                     }catch (SecurityException e) {
                    	 LOGGER.error("Asigning fired SecurityException：条件设置错误!",e);

                     } catch (NoSuchMethodException e) {
                    	 LOGGER.error( "Asigning fired NoSuchMethodException：条件设置错误!",e);
       
                     } catch (IllegalArgumentException e) {
                    	 LOGGER.error("Asigning fired IllegalArgumentException：条件设置错误!",e);
               
                     } catch (IllegalAccessException e) {
                    	 LOGGER.error("Asigning fired IllegalAccessException：条件设置错误!",e);
                 
                     } catch (InvocationTargetException e) {
                    	 LOGGER.error("Asigning fired InvocationTargetException：条件设置错误!",e);
             
                     }  catch (Exception e) {
					
                    	 LOGGER.error("Asigning fired Exception!");
                    	
					 }

        }
    	return from;
    }

}
