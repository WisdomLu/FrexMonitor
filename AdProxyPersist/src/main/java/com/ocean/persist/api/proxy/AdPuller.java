package com.ocean.persist.api.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ocean.core.common.threadpool.AbstractInvokeParameter;
import com.ocean.core.common.threadpool.Invoker;
import com.ocean.core.common.threadpool.Parameter;

public interface AdPuller extends Invoker<AbstractInvokeParameter>{

	/**
	 * 通用请求接口
	 * @param req
	 * @return
	 */
    
	AdPullResponse api(Parameter params,String ... exts) throws AdPullException;
	
	boolean supports(Parameter params) throws AdPullException;
}
