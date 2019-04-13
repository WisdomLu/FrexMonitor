package com.ocean.persist.dao.lbs;

import java.util.Map;

import com.ocean.persist.dao.proxy.AbstractCacheDao;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年8月10日 
      @version 1.0 
 */
public abstract class AbstractLBSCacheDao extends AbstractCacheDao{
	public abstract void cacheCityLocation(String city,Map<String,String> map);
	public abstract Map<String,String> getIPLocationCache(String ip);
	public abstract  Map<String,String> getCityLocationCache(String city);
	public abstract void cacheIPLocation(String ip,Map<String,String> map);
}
