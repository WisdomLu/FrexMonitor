package com.ocean.persist.app.dis;

public interface BaseAppPuller {

	/**
	 * 通用请求接口
	 * @param req
	 * @return
	 */
 public abstract	AppDisResponse api(AdDisParams params,String ... exts) throws AppDisException;
	
 public abstract	boolean supports(AdDisParams params) throws AppDisException;
}
