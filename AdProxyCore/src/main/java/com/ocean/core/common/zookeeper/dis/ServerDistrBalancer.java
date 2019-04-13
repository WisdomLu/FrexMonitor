package com.ocean.core.common.zookeeper.dis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.logging.log4j.Logger;
import com.inveno.util.CollectionUtils;
import com.ocean.core.common.system.MyLogManager;
import com.ocean.core.common.zookeeper.conn.ZKServerConnectHolder;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017骞�8鏈�14鏃� 
      @version 1.0 
 */
public class ServerDistrBalancer extends AbstractServerDistrBalancer<String> implements ServerNodeChangedNotify{
   private  final Logger logger = MyLogManager.getLbsLogger();
   private  List<String> nodes=new ArrayList<String>();
   private  final AtomicInteger ai=new AtomicInteger(1);
   private  ZKServerConnectHolder connectorHolder;
   private  boolean isAlive=false;

   private final Object lock=new Object();
   public ServerDistrBalancer(CountDownLatch countDownLatch) throws Exception{
	   connectorHolder=new ZKServerConnectHolder(this);
		init();
		countDownLatch.countDown();
   }
   public void init() throws Exception{
		initServers();
		//new Fresher().start();
		this.setAlive(true);
   }
   
   public void reset() throws Exception{
	   /* List<String> servers=disController.getServers();
	   if(!isChanged(servers,this.nodes)){//如果节点没有改变，无需更改
		   return ;
	   }*/
	   initServers();
   }
   public String getServer(){
	  
	   if(nodes.isEmpty()){
		   return null;
	   }
	   int index=ai.getAndIncrement();
	   
	   if(index<=0||index==Integer.MAX_VALUE){
		   ai.set(1);
	   }
	   return nodes.get(ai.get()%nodes.size());
   }
   private boolean isChanged(Collection a,Collection b){
	   if(com.inveno.util.CollectionUtils.isEmpty(a)||com.inveno.util.CollectionUtils.isEmpty(b)){
		   return true;
	   }
	   List tempA=new ArrayList();
	   tempA.addAll(a);
	   tempA.retainAll(b);

	   if(tempA.size()!=b.size()||!b.containsAll(tempA)||!tempA.containsAll(b)){//浜ら泦鐩哥瓑
		   return true;
	   }else   if(tempA.size()!=a.size()||!a.containsAll(tempA)||!tempA.containsAll(a)){//骞堕泦鐩哥瓑
		   return true;
	   }
	   return false;
	   
   }
   private void initServers() throws Exception{
	   List<String> servers=connectorHolder.getServers();
	   if(CollectionUtils.isEmpty(servers)){
		   logger.info("zookeeper server nodes is empty");
		   nodes.clear(); 
		   return ;
	   }
	   if(CollectionUtils.isNotEmpty(servers)){
		   Set<String> tem=new HashSet<String>();
		   for(String server:servers){
			   if(!tem.contains(server)){
				   tem.add(server);
			   }
			   
		   }
		   synchronized(lock){
			   if(!isChanged(servers,this.nodes)){//已经更新
				   return ;
			   }
			   
			   //开始修改
			   logger.info("lbs old server nodes: {}",nodes);
			   if(CollectionUtils.isNotEmpty(nodes)){
				   nodes.clear(); 
			   }
			   this.nodes.addAll(tem);
			   logger.info("lbs new server nodes: {}",nodes);
		   }

	   }else{
		   logger.error("lbs server nodes init/fresh error,get server empty");
		   throw new Exception("lbs server nodes init/fresh error,get server empty");
	   }
   }
/*   protected class Fresher extends Thread{
	   public void run(){
		   while(ServerDistrBalancer.this.isAlive()){
			 try {
				 ServerDistrBalancer.this.reset();
				 int interval=SystemContext.getDynamicPropertyHandler().getInt(Constants.ZOOKEEPER_LBS_NODE_RENEW_INTERVAL, 5000);
			     this.sleep(interval);//姣忓垎閽熸洿鏂颁竴娆�
			 } catch (Exception e) {
				// TODO Auto-generated catch block
				 logger.error("reset lbs server nodes  error {}",e.getMessage(),e);
			 }
		   }
	   }
   }*/
    public boolean isAlive() {
		return isAlive;
    }
    public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
	public List<String> getNodes() {
		return nodes;
	}
	@Override
	public void notify(String path) throws Exception {
		// TODO Auto-generated method stub
		reset();
	}
}
