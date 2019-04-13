package com.ocean.persist.dao.proxy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.ocean.core.common.system.SystemContext;
import com.ocean.persist.common.PersistConstants;
import com.ocean.persist.model.proxy.DSPPosition;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年5月15日 
      @version 1.0 
 */
@Repository
public class AppDisCacheDao extends AbstractCacheDao{
	@Autowired
	private StringRedisTemplate redisTemplate;
	protected final Logger log = LoggerFactory.getLogger(AppDisCacheDao.class);
	public void cacheQQDownloaderCategoryId(Set<String> categoryIds){
		for(String categoryId:categoryIds){
			redisTemplate.opsForSet().add(CACHE_QQDOWNLOADER_CATEGORYIDS, categoryId);
		}
		
		redisTemplate.boundSetOps(CACHE_QQDOWNLOADER_CATEGORYIDS).expire(SystemContext.getDynamicPropertyHandler().getInt(PersistConstants.CACHE_EXPIRE, 600), TimeUnit.SECONDS);
		
    }
	public Set<String>  getCachedCategoryIds(){
		Set<String> resultSet =redisTemplate.opsForSet().members(CACHE_QQDOWNLOADER_CATEGORYIDS);  
		return resultSet;
	}
	public StringRedisTemplate getRedisTemplate() {
		return redisTemplate;
	}
	public void setRedisTemplate(StringRedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
}
