package com.ocean.lbs.client.pool;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.pool.KeyedObjectPool;
import org.apache.commons.pool.impl.StackKeyedObjectPool;
import org.apache.logging.log4j.Logger;

import com.ocean.core.common.system.MyLogManager;
import com.ocean.core.common.system.SystemContext;
import com.ocean.core.common.zookeeper.dis.ServerDistrBalancer;
import com.ocean.lbs.entity.LBSByIPReq;
import com.ocean.lbs.entity.LBSByIPResp;
import com.ocean.persist.common.ProxyConstants;
/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年8月11日 
      @version 1.0 
 */
public class LBSClientDispatcherT {
	private  final Logger logger = MyLogManager.getLbsLogger();
	private  final LBSClientPoolableObjectFactory factory;
	private  final KeyedObjectPool pool ;
	private  final ExecutorService execut;
	private   ServerDistrBalancer balancer;
	private static class Singleton{
		
		private static final LBSClientDispatcherT handler=new LBSClientDispatcherT();
	}
	public static LBSClientDispatcherT build(){
		return LBSClientDispatcherT.Singleton.handler;
	}
	private LBSClientDispatcherT(){
		int threadPoolSize = SystemContext.getDynamicPropertyHandler().getInt(ProxyConstants.THRIFT_MAX_WORKER, 16);
		execut= Executors.newFixedThreadPool(threadPoolSize*2);
		
		factory=new LBSClientPoolableObjectFactory();
		pool = new StackKeyedObjectPool(factory,20,20);//这里没有对失效的节点进行清理
		
		CountDownLatch countDownLatch = new CountDownLatch(1);
		try {
			balancer=new ServerDistrBalancer(countDownLatch);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("balancer init error{}",e.getMessage(),e);
			e.printStackTrace();
		}

	}

	public LBSByIPResp invok(LBSByIPReq req){
		FutureTask<LBSByIPResp> future =    
			       new FutureTask<LBSByIPResp>(new Invoker(req));
		try {

			execut.execute(future);
			int timeOut=SystemContext.getStaticPropertyHandler().getInt(ProxyConstants.LBS_THRIFT_TIME_OUT,200);
			LBSByIPResp resp=future.get(timeOut,TimeUnit.MILLISECONDS);
			return resp;
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			logger.error("lbs invoke error TimeoutException");
			future.cancel(true);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			logger.error("lbs invoke error InterruptedException",e);
			future.cancel(true);
		
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			logger.error("lbs invoke error ExecutionException",e);
			future.cancel(true);
			
		}
		return null;
	}
	protected class Invoker implements Callable<LBSByIPResp>{
		private LBSByIPReq req;
        public Invoker(LBSByIPReq req){
        	this.req=req;
        }
		public LBSByIPResp call() throws Exception {
			// TODO Auto-generated method stub
			String node=balancer.getServer();
			if(StringUtils.isEmpty(node)){
				logger.error("get node  from distribute balancer is empty");
				return null;
			}
			LBSClient lbsClient=(LBSClient)pool.borrowObject(node);
			if(lbsClient==null){
				logger.error("get client  from pool is empty");
				return null;
			}
			try{
				LBSByIPResp resp= lbsClient.getClient().searchByIP(req);
				return resp;
			}catch(Exception e){
				logger.error("lbs invoker  call error,{}",e.getMessage(),e);
				return null;
			}finally{
				logger.debug("recycle client {}  to pool",lbsClient.getHostAndPort());
				pool.returnObject(node, lbsClient);

			}
		}
	}
}
