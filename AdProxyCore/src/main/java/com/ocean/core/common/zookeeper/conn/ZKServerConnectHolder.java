package com.ocean.core.common.zookeeper.conn;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import com.ocean.core.common.system.Constants;
import com.ocean.core.common.system.MyLogManager;
import com.ocean.core.common.system.SystemContext;
import com.ocean.core.common.zookeeper.dis.ServerNodeChangedNotify;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年8月14日 
      @version 1.0 
 */
public class ZKServerConnectHolder implements IZKServerConnector<String>{
	private  final Logger logger = MyLogManager.getLbsLogger();
	private  final ZooKeeperConnector connector;
	public ZKServerConnectHolder(ServerNodeChangedNotify notify) throws Exception{
		//后期吧这个改成xml配置
		String hosts=SystemContext.getStaticPropertyHandler().get(Constants.ZOOKEEPER_ADDRESS);
		if(StringUtils.isEmpty(hosts)){
			logger.error("zookeeper config error,hosts address is empty");
			throw new  Exception("zookeeper config error,hosts address is empty");
		}
		int timeOut=SystemContext.getStaticPropertyHandler().getInt(Constants.ZOOKEEPER_TIMEOUT,1000);
		String path=SystemContext.getStaticPropertyHandler().get(Constants.ZOOKEEPER_LBS_PATH);
		if(StringUtils.isEmpty(path)){
			logger.error("zookeeper config error,lbs server path of zookeeper  is empty");
			throw new  Exception("zookeeper config error,lbs server path of zookeeper  is empty");
		}
		connector=new ZooKeeperConnector(hosts,timeOut,path,notify);
        init();
	}
	private void init() throws Exception{

		try {
			connector.connect();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("connect to zookeeper error : IOException",e);
			throw new  Exception("connect to zookeeper error : IOException");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			logger.error("connect to zookeeper error : InterruptedException",e);
			throw new  Exception("connect to zookeeper error : InterruptedException");
		}
	}
	public List<String> getServers() throws Exception {
		// TODO Auto-generated method stub

		 List<String> servers=new ArrayList<String>();
		try {
			List<String> nodes=this.connector.getNodes();
			for(String node:nodes){
				byte[] data=connector.getData(connector.path+File.separator+node);
				if(data!=null){
					String nodeData=new String(data);
					logger.debug("the lbs server node data:{}",nodeData);
					servers.add(nodeData);
				}
				
			}
            return servers;
		} catch (KeeperException e) {
			// TODO Auto-generated catch block
			logger.error("get lbs server node from zookeeper  error : KeeperException",e);
			throw new  Exception("get lbs server node from zookeeper  error : KeeperException");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			logger.error("get lbs server node from zookeeper  error :  InterruptedException",e);
			throw new  Exception("get lbs server node from zookeeper  error :  InterruptedException");
		}
	}

    protected class AbstractZooKeeper implements Watcher{
        protected ZooKeeper zooKeeper;
        protected CountDownLatch countDownLatch = new CountDownLatch(1); 
        protected String hosts;
        protected String path;
        protected int timeOut;
        private ServerNodeChangedNotify notify;
		public void connect()throws IOException, InterruptedException{
	
				zooKeeper=new ZooKeeper(this.hosts,this.timeOut,this);
				countDownLatch.await();

		}
		public void process(WatchedEvent event) {
			// TODO Auto-generated method stub
	/*        if(event.getState() == KeeperState.SyncConnected){  
	            countDownLatch.countDown();  
	        }*/
	        Watcher.Event.EventType eventType = event.getType();
	        if ((Watcher.Event.EventType.None == eventType) && 
	          (Watcher.Event.KeeperState.SyncConnected == event.getState()))
	        {
	        	countDownLatch.countDown();  
	        }
	        else if (Watcher.Event.EventType.NodeChildrenChanged == eventType)
	        {
	        	logger.info("Node children has changed!");
	        	notify.notify();
	        }
	        else if ((Watcher.Event.EventType.None == eventType) && 
	          (Watcher.Event.KeeperState.Disconnected == event.getState()))
	        {
	        	logger.info("zookeeper disconnect to server {}",this.hosts);
	        }
	        else if ((Watcher.Event.EventType.None == eventType) && 
	          (Watcher.Event.KeeperState.Expired == event.getState()))
	        {
	          logger.info("session expired and rebuild session again");
	  
	          reBuildSession();
	        }
	        else
	        {
	          logger.info("other event : {} : {}" , this.hosts , 
	            event.toString());
	        }
		} 
		 private void reBuildSession()
		 {
		    destroy();
		    try
		    {
		      this.zooKeeper = new ZooKeeper(this.hosts, this.timeOut, this);
		    }
		    catch (Exception e)
		    {
		    	logger.error("reBuild session exception");
		    	logger.error(e.getMessage(), e);
		    }
		 }
		 public void destroy()
		  {
			 logger.info("destroy the zk {}" ,this.hosts);
		    try
		    {
		      this.zooKeeper.close();
		    }
		    catch (Exception e)
		    {
		    	logger.error(e.getCause());
		    }
		  }
    }
    protected class ZooKeeperConnector extends AbstractZooKeeper{
    	public ZooKeeperConnector(String hosts,int timeOut,String path ,ServerNodeChangedNotify notify){
        	super.hosts=hosts;
        	super.timeOut=timeOut;
        	super.path=path;
        	super.notify=notify;
        }
    	public List<String> getNodes() throws KeeperException, InterruptedException{

            List<String> children = this.zooKeeper.getChildren(path, false);  
            if (children.isEmpty()) {  
            	logger.error("the node of path {} is empty!",path);
                return null;  
            }else{  
            	logger.info("the nodes of path {} : {}",path,children);
               return children; 
            }  

    	}
        public byte[] getData(String fullPath) throws KeeperException, InterruptedException {  
            return  this.zooKeeper.getData(fullPath, false,null);  
        }  
    }

}
