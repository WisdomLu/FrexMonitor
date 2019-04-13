package com.ocean.core.common.search;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.swing.JOptionPane;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.inveno.util.StringUtil;
import com.ocean.core.common.search.annotation.CriteriaType;
import com.ocean.core.common.search.annotation.DataType;
import com.ocean.core.common.search.annotation.DateSearchField;
import com.ocean.core.common.search.annotation.SearchField;
import com.ocean.core.common.system.BusinessException;
import com.ocean.core.common.system.ErrorCode;
/* 通用查询条件注入
 * @author Alex 2015-10-28
 * */
public class CriteriaUtil<F> {
	 public Criteria setCriteria(F from,Criteria criteria) throws Exception{
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat format2=new SimpleDateFormat("yyyy-MM-dd HH:mm");
    	 // 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
        Field[] fields_f = from.getClass().getDeclaredFields();
        for (short i = 0; i < fields_f.length; i++) {
		             Field field_f = fields_f[i];
		             String field_FName = field_f.getName();
                     String getMethodName_f = "get"+ field_FName.substring(0, 1).toUpperCase()+ field_FName.substring(1);//获取源对象的getter方法
                     try {
                         Class<?> fromCla=from.getClass();//获取类
                         Method[] methods_f= fromCla.getDeclaredMethods();
                         boolean flag=false;
                         if (!field_f.isAnnotationPresent(DateSearchField.class)&&!field_f.isAnnotationPresent(SearchField.class)){//如果当前字段不是查询相关的注解字段
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
                          if(value==null){
                        	 continue;
                          }
                         //判断是否有指定的注解
                         if (field_f.isAnnotationPresent(DateSearchField.class)&&(value instanceof String)) {//针对日期查询：今天，昨天，上周，本周
                             //AnnotatedElement接口中的方法getAnnotation(),获取传入注解类型的注解
                        	 DateSearchField dateSearchField = field_f.getAnnotation(DateSearchField.class);
                             //拿到注解中的值，即查询参数名称
                             String paraName = dateSearchField.paramName();
                             int paramValue=-1;
                                 paramValue=Integer.parseInt((String)value);//参数值
                                 Calendar cal = Calendar.getInstance();
    						     Calendar cal2 = Calendar.getInstance();
                                 switch(paramValue){
	                                 case 1://今天
	                                	 criteria.add(Restrictions.ge(paraName, format2.parse(dataFormat(format.format(cal.getTime()),"min"))));
	                                	 criteria.add(Restrictions.le(paraName, format2.parse(dataFormat(format.format(cal.getTime()),"max"))));
	    	                             break;
	                                 case 2://昨天
	    	                             cal.add(Calendar.DATE,-1);
	    	                             criteria.add(Restrictions.ge(paraName, format2.parse(dataFormat(format.format(cal.getTime()),"min"))));
	    	                             criteria.add(Restrictions.le(paraName, format2.parse(dataFormat(format.format(cal.getTime()),"max"))));
	    	                             break;
	                                 case 3://本周
		                                	//获取本周一日期
	                                     cal2.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); 
	                                	 criteria.add(Restrictions.ge(paraName,format.parse(dataFormat(format.format(cal2.getTime()),"min"))));
	                                	 criteria.add(Restrictions.le(paraName, format2.parse(dataFormat(format.format(cal.getTime()),"max"))));
	                                	 break;
	                                 case 4://上一周
	                                	 //获取上周一日期
	                                     cal.add(Calendar.WEEK_OF_MONTH, -1);
	                                     cal.set(Calendar.DAY_OF_WEEK, 2);
	                                     criteria.add(Restrictions.ge(paraName,format.parse(format.format(cal.getTime()))));
	                                     //获取上周天日期
	                                     cal2.set(Calendar.DAY_OF_WEEK, 1);
	                                     criteria.add(Restrictions.le(paraName, format2.parse(dataFormat(format.format(cal2.getTime()),"max"))));
	                                     break;
	                                 case 5://本月
	                                     
	                                     //获取当前月第一天： 
	                                     cal.add(Calendar.MONTH, 0);
	                                     cal.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
	                                     criteria.add(Restrictions.ge(paraName,format.parse(format.format(cal.getTime()))));
		                                   
	                                     //获取当前月最后一天 
	                                     cal2.set(Calendar.DAY_OF_MONTH, cal2.getActualMaximum(Calendar.DAY_OF_MONTH)); 
	                                     criteria.add(Restrictions.le(paraName, format2.parse(dataFormat(format.format(cal2.getTime()),"max"))));
	                                	 break;
	                                 default:
	                                	 break;
	                                 }
                          }else if (field_f.isAnnotationPresent(SearchField.class)) {
    	       		    	  if((value instanceof String)&&"".equals((String)value)){//
    	    		    		  continue;
    	    		    	  }
                        	  SearchField searchField=field_f.getAnnotation(SearchField.class);
                        	  boolean multi=searchField.multiple();
                        	  CriteriaType criteriaType =searchField.criteriaType();//获取查询类型
                        	  String paramNames[]=searchField.paramNames();
                        	  int paramNum= paramNames.length;
                        	  if(multi&&paramNum>0&&criteriaType==CriteriaType.ANY_LIKE&&(value instanceof String)){//如果是多个属性共用一个参数值进行模糊查询
                        		          String formatValue=((String)value).trim();
	                                	  if(paramNum==1&&StringUtil.isNotEmpty(paramNames[0])){//如果只有一个参数
	                                		  criteria.add(Restrictions.like(paramNames[0],formatValue, MatchMode.ANYWHERE).ignoreCase());
	                                	  }if(paramNum==2&&StringUtil.isNotEmpty(paramNames[1])){//如果只有两个参数，则被视为单参数的模糊查询
	                        				  criteria.add(Restrictions.or(Restrictions.like(paramNames[0],formatValue, MatchMode.ANYWHERE).ignoreCase(),Restrictions.like(paramNames[1],formatValue, MatchMode.ANYWHERE).ignoreCase()));
	                        			  }else{
                        		              Disjunction disjunction = Restrictions.disjunction();  
		                        		      for(int m=0;m<paramNum;m++){
		                                          disjunction.add(Restrictions.like(paramNames[m], formatValue,MatchMode.ANYWHERE).ignoreCase());  
	                        		            
			                        	       }  

		                        		      criteria.add(disjunction);
	                        			  } 
                        	  }else if(!multi){
                        		  String paramName=searchField.paramName();
                        		  DataType dataType=searchField.dataType();
                        		 
                                  //如果是非字符串，停止运行
        	       		    	  if(!(value instanceof String)||((value instanceof String)&&"".equals((String)value))){//
        	    		    		  continue;
        	    		    	  }
        	       		    	  
                        		  switch(criteriaType){
                        		      case EQ:
                        		    	  switch(dataType){  
                        		    	     case DATE:
                        		    	    	 criteria.add(Restrictions.ge(paramName, format2.parse((String)value)));
                        		    	    	 criteria.add(Restrictions.le(paramName, format2.parse(dataFormat((String)value,"max"))));
                        		    	    	 break;
                        		    	     default:
                        		    	    	 if((value instanceof String)){
                        		    	    		 criteria.add(Restrictions.eq(paramName, ((String)value).trim()));
                        		    	    	 }else
                        		    	    	     criteria.add(Restrictions.eq(paramName, value));
                        		    	    	 break;
                        		    	  }
                        		    	
                        		          break;
                        		      case LE:
                         		    	  switch(dataType){  
	                     		    	     case DATE:
	                     		    	    	 criteria.add(Restrictions.le(paramName, format2.parse(dataFormat((String)value,"max"))));
	                     		    	    	 break;
	                     		    	     default:
	                     		    	    	 criteria.add(Restrictions.le(paramName, value));
	                     		    	    	 break;
                     		    	     }
                    		              break;
                        		      case LT:
                         		    	  switch(dataType){  
	                     		    	     case DATE:
	                     		    	    	 criteria.add(Restrictions.lt(paramName, format2.parse(dataFormat((String)value,"max"))));
	                     		    	    	 break;
	                     		    	     default:
	                     		    	    	 criteria.add(Restrictions.lt(paramName, value));
	                     		    	    	 break;
                     		    	     }
                    		              break;
                        		      case GE:
                         		    	  switch(dataType){  
	                     		    	     case DATE:
	                     		    	    	 criteria.add(Restrictions.ge(paramName, format2.parse(dataFormat((String)value,"min"))));
	                     		    	    	 break;
	                     		    	     default:
	                     		    	    	 criteria.add(Restrictions.ge(paramName, value));
	                     		    	    	 break;
                     		    	     }
                    		              break;
                        		      case GT:
                         		    	  switch(dataType){  
	                     		    	     case DATE:
	                     		    	    	 criteria.add(Restrictions.gt(paramName, format2.parse(dataFormat((String)value,"min"))));
	                     		    	    	 break;
	                     		    	     default:
	                     		    	    	 criteria.add(Restrictions.gt(paramName, value));
	                     		    	    	 break;
                     		    	     }
                    		              break;
                        		      case BEFOR_LIKE:
                        		    	  if((value instanceof String)&&!"".equals((String)value)){
                            		    	  criteria.add(Restrictions.like(paramName, ((String)value).trim(), MatchMode.START));
                        		    	  }

                    		              break;
                        		      case END_LIKE:
                        		    	  if((value instanceof String)&&!"".equals((String)value)){
                        		    	      criteria.add(Restrictions.like(paramName,  ((String)value).trim(), MatchMode.END));
                        		    	  }
                    		              break;
                        		      case ANY_LIKE:
                        		    	  if((value instanceof String)&&!"".equals((String)value)){
                        		    	      criteria.add(Restrictions.like(paramName,  ((String)value).trim(), MatchMode.ANYWHERE));
                        		    	  }
                    		              break;
                        		      case IN:
                        		    	  if((value instanceof String)&&!"".equals((String)value)){
	                        		    	  String valueStr=(String)value;
											  String valueArr[]=valueStr.split(",");
	                        		    	  criteria.add(Restrictions.in(paramName, valueArr));
                        		    	  }
                    		              break;
                        		      case NE:
                        		    	  if((value instanceof String)&&!"".equals((String)value)){
                        		    	      criteria.add(Restrictions.ne(paramName, ((String)value).trim()));
                        		    	  }
                    		              break;
								    default:
									     break;
                        		  }
                        	  }
                     }
                     } catch (SecurityException e) {
                    	 throw new BusinessException(ErrorCode.INTER_ERROR,"SecurityException:automatic sreaching fire exception!");
                     } catch (NoSuchMethodException e) {
                    	 throw new BusinessException(ErrorCode.INTER_ERROR,"NoSuchMethodException :automatic sreaching fire exception!");
                     } catch (IllegalArgumentException e) {
                    	 throw new BusinessException(ErrorCode.INTER_ERROR,"IllegalArgumentException:automatic sreaching fire exception!");
                     } catch (IllegalAccessException e) {
                    	 throw new BusinessException(ErrorCode.INTER_ERROR,"IllegalAccessException:automatic sreaching fire exception!");
                     } catch (InvocationTargetException e) {
                    	 throw new BusinessException(ErrorCode.INTER_ERROR,"InvocationTargetException:automatic sreaching fire exception!");
                     }  catch (ParseException e) {
                    	 throw new BusinessException(ErrorCode.INTER_ERROR,"BusinessException:automatic sreaching fire exception!");
					 }finally {
                         //清理资源
                     }

        }
    	return criteria;
    }
	 private String dataFormat(String dateStr,String type){
    	 if(((String)dateStr).split(" ").length>1){
    		 return dateStr;
    	 }
		 if("min".equals(type)){
			 dateStr=dateStr+" 00:00";//将时间设置为截止时间的00：00分 
		 }else if("max".equals(type)){
			 dateStr=dateStr+" 23:59";//将时间设置为截止时间的23：59分 
		 }
		 return dateStr;
		 
	 }
}
