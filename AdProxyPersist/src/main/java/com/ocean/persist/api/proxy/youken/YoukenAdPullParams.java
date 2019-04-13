package com.ocean.persist.api.proxy.youken;

import com.ocean.core.common.threadpool.AbstractInvokeParameter;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年3月6日 
      @version 1.0 
 */
public class YoukenAdPullParams  extends AbstractInvokeParameter{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2697777833285320244L;
	private String reqjson;
	private String token;
	private long timestamp;
	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}
	public String getReqjson() {
		return reqjson;
	}
	public void setReqjson(String reqjson) {
		this.reqjson = reqjson;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

}
