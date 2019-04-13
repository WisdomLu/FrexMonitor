package com.ocean.core.common.threadpool;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AsynCache<T extends Object> extends  Thread{
	 private T data;
	 private String action;
	 protected final Logger log = LoggerFactory.getLogger(AsynCache.class);
	 public AsynCache(String action,T data){
		 this.data=data;
		 this.action=action;
	 }
	public void run() {
/*		BidRedisDao dao=(BidRedisDao)SystemContext.getServiceHandler().getService(BidRedisDao.class);
		if(Constants.CACHE_APP.equals(this.action)){
			dao.cacheApp((List<AdPosition>)this.getData());
		}else if(Constants.CACHE_STRATAGY.equals(this.action)){
			dao.cacheStratagy((List<SpaceStrategy>)this.getData());
		}else if(Constants.CACHE_JOINSPACE.equals(this.action)){
			dao.cacheJoinSpace((List<JoinMediaSpace>)this.getData());
		}else if(Constants.CACHE_SPACE_ATTR.equals(this.action)){
			dao.cacheAddAttr((List<JoinSpaceAttr>)this.getData());
		}else{
			log.error("the BidRedisDao have not method match {}", this.action);
		}*/
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	
}
