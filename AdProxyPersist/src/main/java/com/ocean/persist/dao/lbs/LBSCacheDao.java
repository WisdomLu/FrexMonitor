package com.ocean.persist.dao.lbs;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import com.ocean.core.common.system.SystemContext;
import com.ocean.persist.common.PersistConstants;
import com.ocean.persist.dao.proxy.AbstractCacheDao;
import com.ocean.persist.dao.proxy.ProxyCacheDao;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年8月9日 
      @version 1.0 
 */
@Repository
public class LBSCacheDao  extends AbstractLBSCacheDao{
	protected final Logger log = LoggerFactory.getLogger(ProxyCacheDao.class);
	private final String ENCODER_CHARSET="UTF-8";
	@Autowired
	private StringRedisTemplate redisTemplate;
	private String getIPCatheKey(String ip){
		return PersistConstants.CACHE_LBS_IP+ip.replaceAll("\\.", "::");
	}
	private String getCityCatheKey(String city){
		try {
			return PersistConstants.CACHE_LBS_CITY+URLEncoder.encode(city,ENCODER_CHARSET);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public void cacheCityLocation(String city,Map<String,String> map){
		String key=getCityCatheKey(city);
		this.cityCache(key, map);
	}
	public void cacheIPLocation(String ip,Map<String,String> map){
		String key=getIPCatheKey(ip);
		this.cache(key, map);
	}
	private void cache(String key,Map<String,String> map){
		log.info("cache key:{}",key);
		redisTemplate.opsForHash().putAll(key, map);
		redisTemplate.boundSetOps(key).expire(SystemContext.getDynamicPropertyHandler().getInt(PersistConstants.CACHE_EXPIRE, 1), TimeUnit.DAYS);
	
	}
	private void cityCache(String key,Map<String,String> map){
		log.info("cache key:{}",key);
		redisTemplate.opsForHash().putAll(key, map);
		
	}
	public Map<String,String> getIPLocationCache(String ip){
		String key=getIPCatheKey(ip);
		return this.getCache(key);
		
	}
	public  Map<String,String> getCityLocationCache(String city){
		String key=getCityCatheKey(city);
		return this.getCache(key);
	}
	private Map<String,String> getCache(String key){
		Map<Object,Object> map= redisTemplate.opsForHash().entries(key);
		if(map.isEmpty()){
			return Collections.EMPTY_MAP;
		}
		return (Map)map;
	}
	public StringRedisTemplate getRedisTemplate() {
		return redisTemplate;
	}
	public void setRedisTemplate(StringRedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
}
