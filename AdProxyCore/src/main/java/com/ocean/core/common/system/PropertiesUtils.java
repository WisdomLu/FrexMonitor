package com.ocean.core.common.system;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;


public class PropertiesUtils {
   public static String getValue(String key,String proj){
	   Map<String,String> map=new HashMap<String,String>();
	   String vl=SystemContext.getStaticPropertyHandler().get(key);
	   if(StringUtils.isEmpty(vl)){	
	   		return null;
	   }
	   String vArr[]=vl.split(";");
	   for(int i=0;i<vArr.length;i++){
		   map.put(vArr[i].split(":")[0], vArr[i].split(":")[1]);
	   }
	   return map.get(proj);
   }

   
/*   public static String getHostname(String proj){
	   String projL=SystemContext.getStaticPropertyHandler().get(Constants.SSH_PROJECT_SERVER);
	   if(StringUtils.isEmpty(projL)){	
	   		return null;
	   }
	   
   }
   public static String getPath(String proj){
	   
   }*/
}
