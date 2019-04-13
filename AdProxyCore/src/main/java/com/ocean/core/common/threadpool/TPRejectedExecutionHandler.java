package com.ocean.core.common.threadpool;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

import org.apache.logging.log4j.Logger;

import com.ocean.core.common.system.MyLogManager;
public class TPRejectedExecutionHandler implements RejectedExecutionHandler  {
	public  final Logger LOGGER = MyLogManager.getDisLoggerV2();
	public void rejectedExecution(Runnable arg0, ThreadPoolExecutor arg1) {
		// TODO Auto-generated method stub
		LOGGER.error("the thread pool regject to accept the thread,pool full exception!");
	}

}
