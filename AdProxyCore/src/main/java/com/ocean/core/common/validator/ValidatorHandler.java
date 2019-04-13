package com.ocean.core.common.validator;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import com.ocean.core.common.system.BusinessException;
import com.ocean.core.common.system.ErrorCode;
import com.ocean.core.common.system.ReturnData;
/**  parameter validate tool
@author Alex & E-mail:569246607@qq.com
@date 创建时间：2016年12月2日 
@version 1.0 
*/
public class ValidatorHandler{
	  private ValidatorHandler(){}
	  private static class Temp{
		static final  ValidatorHandler instance=new ValidatorHandler();
	  }
	  public static ValidatorHandler getValidator(){
		  return ValidatorHandler.Temp.instance;
	  }
	  public  ReturnData validate(Object from) throws Exception{
	        Field[] fields_f = from.getClass().getDeclaredFields();
	        ReturnData returnData=new ReturnData();
	        for (short i = 0; i < fields_f.length; i++) {
			             Field field_f = fields_f[i];
			             String field_FName = field_f.getName();
	                     String getMethodName_f = "get"+ field_FName.substring(0, 1).toUpperCase()+ field_FName.substring(1);//获取源对象的getter方法
	                     try {
	                         Class<?> fromCla=from.getClass();//获取类
	                         Method[] methods_f= fromCla.getDeclaredMethods();
	                         boolean flag=false;
	                         if (!field_f.isAnnotationPresent(ValidateField.class)){
	                        	continue;
	                         }
	                         for (Method method : methods_f) {//判断是否存在getter方法
	                             if(getMethodName_f.equals(method.getName())){
	                            	 flag=true;
	                            	 break;
	                             }
	                         }
	                         if(!flag){
	                        	 continue;
	                         }
	                         //获取查询参数值
	                         Method method_f = fromCla.getDeclaredMethod(getMethodName_f, new Class[]{});
	                         Object value= method_f.invoke(from, new Object[]{});
	                         //AnnotatedElement接口中的方法getAnnotation(),获取传入注解类型的注解
                        	 ValidateField validateField = field_f.getAnnotation(ValidateField.class);
                             //拿到注解中的值
                        	 ValidateType validateType = validateField.validateType();
                        	 if(ValidateType.REQUIRED!=validateType){
                        		 continue;
                        	 }
                        	 DataType dataType=validateField.dataType();
                        	 boolean valiFlag=true;
                             switch(dataType){
	                                 case NOT_NULL://非空
	                                	 if(value==null){
	                                		 valiFlag=false;
	        	                         }
			                             break;
	                                 case INTEGER:
	                                	 if(value==null||!(value instanceof Integer)){
	                                		 valiFlag=false;
	                                	 }
	                                	 break;
	                                 case STRING:
	                                	 if(value==null||!(value instanceof String)){
	                                		 valiFlag=false;
	                                	 }
	                                	 break;
	                                 default :
	                                	 
	                                	 break;
	                         }
                             if(!valiFlag){
                       		    returnData.setErrorCode(ErrorCode.INTER_ERROR);
                       		    returnData.setErrorMsg("field "+field_FName+" required not null!");
                       		    return returnData;
                             }
	                     } catch (SecurityException e) {
	                    	 throw new BusinessException(ErrorCode.INTER_ERROR,"SecurityException:automatic validate fire exception!");
	                     } catch (NoSuchMethodException e) {
	                    	 throw new BusinessException(ErrorCode.INTER_ERROR,"NoSuchMethodException :automatic validate fire exception!");
	                     } catch (IllegalArgumentException e) {
	                    	 throw new BusinessException(ErrorCode.INTER_ERROR,"IllegalArgumentException:automatic validate fire exception!");
	                     } catch (IllegalAccessException e) {
	                    	 throw new BusinessException(ErrorCode.INTER_ERROR,"IllegalAccessException:automatic validate fire exception!");
	                     } catch (InvocationTargetException e) {
	                    	 throw new BusinessException(ErrorCode.INTER_ERROR,"InvocationTargetException:automatic validate fire exception!");
	                     }

	        }
	        returnData.setErrorCode(ErrorCode.SUCCEED);
	    	return returnData;
       }
}
