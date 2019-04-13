package com.ocean.core.common.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolHandler {
	  private ThreadPoolHandler(){}
	  private static class Temp{
		  static final  ThreadPoolExecutor pool=new ThreadPoolExecutor(2, 20, 10L, TimeUnit.SECONDS, new ArrayBlockingQueue(10), new TPRejectedExecutionHandler());
	  }
	  public static ThreadPoolExecutor getTreadPool()
	  {
	    return Temp.pool;
	  }
}
