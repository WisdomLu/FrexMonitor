package com.ocean.persist.dao.proxy;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.inveno.util.CollectionUtils;
import com.ocean.core.common.system.SystemContext;
import com.ocean.persist.common.PersistConstants;
import com.ocean.persist.model.proxy.DSPPosition;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年1月16日 
      @version 1.0 
 */
@Repository
public class ProxyCacheDao extends AbstractCacheDao{
	protected final Logger log = LoggerFactory.getLogger(ProxyCacheDao.class);
	private static final String POSE_CACHE_FLAG="isEmpty";
	@Autowired
	private StringRedisTemplate redisTemplate;
	private String getJoinPoseCacheKey(String joinDsp,String zkSpaceId,String ppid){
		if(StringUtils.isEmpty(ppid)){
			return PersistConstants.CACHE_POSITION_PREFIX+joinDsp+CACHE_SEPRATE+zkSpaceId;
		}
		return PersistConstants.CACHE_POSITION_PREFIX+joinDsp+CACHE_SEPRATE+zkSpaceId+CACHE_SEPRATE+ppid;
	}
/*	private String getJoinPoseCacheFlagKey(String joinDsp,String zkSpaceId,String ppid){
		if(StringUtils.isEmpty(ppid)){
			return PersistConstants.CACHE_POSITION_FLAG_PREFIX+joinDsp+CACHE_SEPRATE+zkSpaceId;
		}
		return PersistConstants.CACHE_POSITION_FLAG_PREFIX+joinDsp+CACHE_SEPRATE+zkSpaceId+CACHE_SEPRATE+ppid;
	}*/
	
	
	
	private String getPositionTargetCacheKey(String joinDsp,String zkSpaceId,String ppid){
		if(StringUtils.isEmpty(ppid)){
			return PersistConstants.CACHE_POSITION_TARGET_PREFIX+joinDsp+CACHE_SEPRATE+zkSpaceId;
		}
		return PersistConstants.CACHE_POSITION_TARGET_PREFIX+joinDsp+CACHE_SEPRATE+zkSpaceId+CACHE_SEPRATE+ppid;
	}
	//设置广告位配置信息
/*	public void cachePose(HashMap<String, DSPPosition> posM){
		for(Map.Entry<String, DSPPosition> entry:posM.entrySet()){
			DSPPosition pose=entry.getValue();
			Map<String,String> map=new HashMap<String,String>();

			map.put("dsp", String.valueOf(pose.getDsp()));
			if(StringUtils.isEmpty(pose.getPos())){
				map.put("pos", pose.getPos());
			}
			map.put("posType", String.valueOf(pose.getPosType()));
			map.put("settlementType", String.valueOf(pose.getSettlementType()));
			map.put("settlementPrice", String.valueOf(pose.getSettlementPrice()));
			if(!StringUtils.isEmpty( pose.getText1())){
				map.put("text1", pose.getText1());
			}
			if(!StringUtils.isEmpty(pose.getText2())){
				map.put("text2", pose.getText2());
			}
			redisTemplate.opsForHash().putAll(getJoinPoseCacheKey(pose.getDsp(),entry.getKey(),pose.getId()), map);
		}
	}*/
	public void cachePose(String jojinDSP,String zkSpaceId, DSPPosition pose){
			Map<String,String> map=new HashMap<String,String>();

			map.put("dsp", String.valueOf(pose.getDsp()));
			if(!StringUtils.isEmpty(pose.getPos())){
				map.put("pos", pose.getPos());
			}
			map.put("id", pose.getId());
			map.put("posType", String.valueOf(pose.getPosType()));
			map.put("settlementType", String.valueOf(pose.getSettlementType()));
			map.put("settlementPrice", String.valueOf(pose.getSettlementPrice()));
			if(!StringUtils.isEmpty( pose.getText1())){
				map.put("text1", pose.getText1());
			}
			if(!StringUtils.isEmpty(pose.getText2())){
				map.put("text2", pose.getText2());
			}
			if(!StringUtils.isEmpty(pose.getText3())){
				map.put("text3", pose.getText3());
			}
			if(!StringUtils.isEmpty( pose.getSummary())){
				map.put("summary", pose.getSummary());
			}
			
			if(!StringUtils.isEmpty( pose.getClickAreas())){
				map.put("clickArears", pose.getClickAreas());
			}
			if(pose.getSiRate()!=null){
				map.put("siRate", String.valueOf(pose.getSiRate()));
			}
			if(pose.getActiveRate()!=null){
				map.put("activeRate", String.valueOf(pose.getActiveRate()));
			}
			if(pose.getClickRate()!=null){
				map.put("clickRate", String.valueOf(pose.getClickRate()));
			}
			if(pose.getRetryDld()!=null){
				map.put("retryDld", String.valueOf(pose.getRetryDld()));
			}
			if(pose.getVideoFlag()!=null){
				map.put("videoFlag", String.valueOf(pose.getVideoFlag()));
			}
		
			
			
			
			String key=getJoinPoseCacheKey(jojinDSP,zkSpaceId,pose.getId());
			log.debug("cache key:{}",key);
			redisTemplate.opsForHash().putAll(key, map);
			redisTemplate.boundSetOps(key).expire(SystemContext.getDynamicPropertyHandler().getInt(PersistConstants.CACHE_EXPIRE, 600), TimeUnit.SECONDS);
		
	}
	public DSPPosition getPoseCache(String joinDSP,String zkSpaceId,String ppid){
		String key=getJoinPoseCacheKey(joinDSP,zkSpaceId,ppid);
		HashMap<Object,Object> map= (HashMap)redisTemplate.opsForHash().entries(key);
		if(CollectionUtils.isEmpty(map)){
			log.debug("try to get position chache {},but return empty!",key);
			return null;
		}
		DSPPosition pose=new DSPPosition();
		pose.setDsp((String)map.get("dsp"));
		pose.setId((String)map.get("id"));
		if(!StringUtils.isEmpty(map.get("pos"))){
			pose.setPos((String)map.get("pos"));
		}
		pose.setPosType(Short.parseShort((String)map.get("posType")));
		pose.setSettlementType(Integer.parseInt((String)map.get("settlementType")));
		pose.setSettlementPrice(Integer.parseInt((String)map.get("settlementPrice")));

		if(!StringUtils.isEmpty(map.get("text1"))){
			pose.setText1((String)map.get("text1"));
		}
		if(!StringUtils.isEmpty(map.get("text2"))){
			pose.setText2((String)map.get("text2"));
		}
		if(!StringUtils.isEmpty(map.get("text3"))){
			pose.setText3((String)map.get("text3"));
		}
		if(!StringUtils.isEmpty(map.get("summary"))){
			pose.setSummary((String)map.get("summary"));
		}
		if(!StringUtils.isEmpty(map.get("clickArears"))){
			pose.setClickAreas((String)map.get("clickArears"));
		}
		
		
		if(!StringUtils.isEmpty(map.get("siRate"))){
			pose.setSiRate(Integer.parseInt((String)map.get("siRate")));
		}
		if(!StringUtils.isEmpty(map.get("activeRate"))){
			pose.setActiveRate(Integer.parseInt((String)map.get("activeRate")));
		}
		if(!StringUtils.isEmpty(map.get("clickRate"))){
			pose.setClickRate(Integer.parseInt((String)map.get("clickRate")));
		}
		if(!StringUtils.isEmpty(map.get("retryDld"))){
			pose.setRetryDld(Integer.parseInt((String)map.get("retryDld")));
		}
		if(!StringUtils.isEmpty(map.get("videoFlag"))){
			pose.setVideoFlag(Integer.parseInt((String)map.get("videoFlag")));
		}
		
		
		return pose;
		

	}
	/*public void cachePoseTarget(String joinDSP,String zkSpaceId, PositionTarget target,String ppid){
		Map<String,String> map=new HashMap<String,String>();

		map.put("id", target.getId());
		map.put("dpid", target.getDpid());
		
		if(!StringUtils.isEmpty(target.getIp())){
			map.put("ip",target.getIp());
		}
		if(!StringUtils.isEmpty(target.getImei())){
			map.put("imei", target.getImei());
		}
		
		String key=getPositionTargetCacheKey(joinDSP,zkSpaceId,ppid);
		redisTemplate.opsForHash().putAll(key, map);
		redisTemplate.boundSetOps(key).expire(SystemContext.getDynamicPropertyHandler().getInt(PersistConstants.CACHE_EXPIRE, 300), TimeUnit.SECONDS);
		
   }
	public PositionTarget getPoseTargetCache(String joinDSP,String zkSpaceId,String ppid){
		String key=getJoinPoseCacheKey(zkSpaceId,joinDSP,ppid);
		HashMap<Object,Object> map= (HashMap)redisTemplate.opsForHash().entries(key);
		if(CollectionUtils.isEmpty(map)){
			log.debug("try to get posistion target chache {},but return empty!",key);
			return null;
		}
		PositionTarget poseTarget=new PositionTarget();
		poseTarget.setId((String)map.get("id"));
		poseTarget.setDpid((String)map.get("dpid"));
		
		if(!StringUtils.isEmpty(map.get("ip"))){
			poseTarget.setIp((String)map.get("ip"));
		}
		if(!StringUtils.isEmpty(map.get("imei"))){
			poseTarget.setImei((String)map.get("imei"));
		}

		return poseTarget;
		

	}*/

	public StringRedisTemplate getRedisTemplate() {
		return redisTemplate;
	}

	public void setRedisTemplate(StringRedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
}
