package com.ocean.lbs.client.pool;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.thrift.TException;

import com.inveno.util.CollectionUtils;
import com.ocean.core.common.objectpool.AbstractBlokingPool;
import com.ocean.core.common.objectpool.ObjectFactory;
import com.ocean.core.common.system.MyLogManager;
import com.ocean.core.common.system.SystemContext;
import com.ocean.core.common.zookeeper.dis.ServerDistrBalancer;
import com.ocean.persist.common.ProxyConstants;
/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年8月11日 
      @version 1.0 
 */
public class LBSClientPool extends AbstractBlokingPool<LBSClient>{
	private  final Logger logger = MyLogManager.getLbsLogger();
    private  ObjectFactory<LBSClient> factory;
	private  final ServerDistrBalancer balancer;
	private  final int POOL_SIZE=20;
	private  final Map<String,BlockingQueue<LBSClient>> clients=new ConcurrentHashMap<String,BlockingQueue<LBSClient>>();
	private  final Object clientLock=new Object(); 
	private  final Recycled recycled;
    public LBSClientPool(ObjectFactory<LBSClient> factory) throws Exception{
	    CountDownLatch countDownLatch = new CountDownLatch(1);
    	balancer=new ServerDistrBalancer(countDownLatch);
    	countDownLatch.await();
    	this.factory=factory;
    	//initClients() ;
    	new ClientsInitor().start(); 
    	
    	//init recycled
    	int recycledSize=balancer.getNodes().size();
    	recycled=new Recycled(POOL_SIZE*(recycledSize==0?4:recycledSize));
    	recycled.start();
    }
	public LBSClient get() throws Exception{
		// TODO Auto-generated method stub
		return this.createObj(balancer.getServer());

	}

	public void close() {
		// TODO Auto-generated method stub
		
	}
	protected class ClientsInitor extends Thread{
		public void run(){
			try{
			   while(true){
				   int isLbsSearch=SystemContext.getDynamicPropertyHandler().getInt(ProxyConstants.LBS_THRIFT_STATE,0);
				   if(isLbsSearch==0){//服务停止，则停止维护
					   logger.info("lbs server is stoped,checke later");
					   balancer.setAlive(false);//关闭zookeeper节点监控
					   this.sleep(1000*60*5);
					   continue;
				   }else if(!balancer.isAlive()){//重新启动zookeeper服务
					   balancer.init();
					   this.sleep(1000*5);
				   }
				   
				   logger.info("nodes:{} ,clients:{}",balancer.getNodes(),clients.size());
					if(balancer.getNodes().size()!=clients.size()){
							initClients();
					}else{
						List<String> nodes=balancer.getNodes();
						for(String node:nodes){//给每个节点补充客户端
						
				    		BlockingQueue<LBSClient> q=clients.get(node);
				    		if(q==null){
				    			addNewNode(node);
				    		}else if(q.size()<=POOL_SIZE*0.5){//补给客户端，有的客户端没有回收成功
				    			logger.info("node {} clients size:{}",node,q.size());
				    			supplyNodes(node,(int)(POOL_SIZE*0.4));
				    		}
						}
					}
					
					this.sleep(1000);
			    }
	    	}catch(Exception e){
	    		logger.error("init clients error",e);
	    	}
		}
	}
    private void initClients() throws Exception{
	    	List<String> nodes=balancer.getNodes();
	    	for(String node:nodes){//初始化新增的节点
	    		if(!clients.containsKey(node)){
	    			addNewNode( node);
	    		}
	    		
	    	}

	    	Set<String > keySet= clients.keySet();
	    	for(String key:keySet){//清除无效节点
	    		if(!nodes.contains(key)){
	    			clients.remove(key);
	    		}
	    	}
	    	
	    	logger.info("init clients succeed,{}" ,clients.keySet());

    } 
    private void supplyNodes(String node,int size){
    	BlockingQueue<LBSClient> q=clients.get(node);
    	for(int i=0;i<size;i++){
			LBSClient client;
			try {
				client = createObj(node);
				if(client!=null){
					
					q.put(client);
				}
				logger.debug("supply client {} succeed ",client);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error("supply client error",e);
			}

    		
    	}
    	
    }
	private void addNewNode(String node) throws Exception{
		if(clients.get(node)!=null){//已经新增
			return ;
		}
		synchronized(clientLock){
			if(clients.get(node)==null){
				//
				BlockingQueue<LBSClient> clientL=new ArrayBlockingQueue<LBSClient>(POOL_SIZE);
				for(int i=0;i<POOL_SIZE;i++){
					LBSClient client = createObj(node);
					if(client!=null){
						clientL.put(client);
					}
					
				}
				clients.put(node, clientL);
				logger.info("add a new node {},size {}",node,clientL.size());
			}
			
		}


	}
	@Override
	public LBSClient get(long time, TimeUnit timeUnit) {
		// TODO Auto-generated method stub
		String node=balancer.getServer();
		try {
			if(clients.get(node)==null){//新增的节点
				addNewNode(node);
			}/*else if(clients.get(node).size()==0){
				supplyNodes(node,POOL_SIZE);
			}
			if(clients.get(node)==null){
				return null;
			}*/
			
			LBSClient client= getLBSClient(node,time, timeUnit);
			if(client!=null){
				client.open();
			}
			return client;
		}catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("get client {} error {}",node,e.getMessage(),e);
			return null;
		}

	}
    private LBSClient getLBSClient(String node,long time, TimeUnit timeUnit) throws InterruptedException{
		if(clients.get(node)!=null){
			logger.debug("get LBSClient node {} ,size {}",node,clients.get(node).size());
			return clients.get(node).poll(time, timeUnit);
		}
		return null;
    }
	@Override
	public void handlerInvalidObj(LBSClient obj) throws Exception {
		// TODO Auto-generated method stub
		
		
		LBSClient client=this.createObj(obj.getHostAndPort());
		if(client!=null){
			this.putToPool(client);
			obj=null;
		}
	
	}
	
	
    protected class Recycled extends Thread{
    	BlockingQueue<LBSClient> recycleQue;
    	public Recycled(int queueSize){
    		recycleQue=new ArrayBlockingQueue<LBSClient>(queueSize);
    	}
    	public void run(){
    		while(true){

				LBSClient client = this.get();
				this.recycle(client);
				

    		}
    	}
    	public void add(LBSClient client){
    		try {
				recycleQue.put(client);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				logger.error("add client {} to Recycled queue error (InterruptedException)",client.getHostAndPort(),e);
			}
    	}
    	public LBSClient get() {
    		try {
				return recycleQue.take();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				logger.error("get client  from Recycled queue error  (InterruptedException)",e);
			}
    		return null;
    	}
    	public void recycle(LBSClient obj){//应该单独用一个或多个线程来回收
    		// TODO Auto-generated method stub
    		if(obj==null){
    			return;
    		}
    		BlockingQueue<LBSClient> queue=clients.get(obj.getHostAndPort());
    		if(queue!=null){
    			try {
    				obj.close();
    				queue.put(obj);
    			} catch (InterruptedException e) {
    				// TODO Auto-generated catch block
    				logger.error("return client to node pool error {}  ",obj.getHostAndPort(),e);
    				Thread.currentThread().interrupt();
    			}catch(Exception e){
    				logger.error("return client to node pool error {}  ",obj.getHostAndPort(),e);
    			}
    			logger.debug("return client to node pool {}",obj.getHostAndPort());
    		}else{
    			logger.debug("return client to node pool {} failed,queue {} | client {} is empty",obj.getHostAndPort(),queue,obj);
    		}
    		
    		
    	}
    }
    
    
	@Override
	public void putToPool(LBSClient obj){//应该单独用一个或多个线程来回收
	
		recycled.add(obj);
	}
    private LBSClient createObj(String node) throws Exception{
	    if(StringUtils.isNotEmpty(node)){
	    	String[] nodeArr=node.split(":");
	    	return factory.create(nodeArr[0],Integer.parseInt(nodeArr[1]));
	    }else{
	    	throw new Exception("Factory create client error,balancer getting node is empty!");
	    }

    }
	@Override
	public boolean validate(LBSClient obj) {
		// TODO Auto-generated method stub
		try {
			obj.getClient().ping();
		} catch (TException e) {
			// TODO Auto-generated catch block
			return false;
		}
		return true;
	}

}
