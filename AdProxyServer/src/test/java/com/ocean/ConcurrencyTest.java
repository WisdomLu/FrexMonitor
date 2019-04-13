package com.ocean;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID; 
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;   
import java.util.concurrent.Executors;   
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ocean.persist.api.proxy.JoinDSPEmu;
import com.ocean.proxy.thrift.entity.AdRecomReply;
import com.ocean.proxy.thrift.entity.AdRecomReq;
import com.ocean.proxy.thrift.entity.AdSpaceImgFmt;
import com.ocean.proxy.thrift.entity.AdSpaceType;
import com.ocean.proxy.thrift.entity.AdUserInfo;
import com.ocean.proxy.thrift.entity.ApiProxy;
import com.ocean.proxy.thrift.entity.ExpectedMarketType;
import com.ocean.proxy.thrift.entity.UserAdSpaceAttri;
public class ConcurrencyTest {  
		private static final int zkpos = 457;     
		private Object lock=new Object();
		protected final Logger log = LoggerFactory.getLogger(ConcurrencyTest.class);


    
        //总访问量是client_num，并发量是thread_num  
        int thread_num = 10;  
        int client_num = 10;  
        int request_num=5000;
        BlockingQueue<ThriftClient> queue =  new LinkedBlockingQueue(client_num);
        private void init(){
        	for(int i=0;i<client_num;i++){
        		ThriftClient client=new ThriftClient();
        		queue.add(client);
        	}
        }

        public  ConcurrencyTest(){  
        	init();
        }  
          
          
        public void run() {  
             
      
            // 建立ExecutorService线程池  
            ExecutorService exec = Executors.newFixedThreadPool(thread_num);  
            // thread_num个线程可以同时访问   
            // 模拟client_num个客户端访问  
              
          //  final CountDownLatch doneSignal = new CountDownLatch(client_num);  
              
            for (int i = 0; i < client_num; i++) {  
                  
                Runnable run = new Runnable() {  
                      
                    public void run() {  
                        for(int t=0;t<request_num;t++){
                        	
                        	String hash=UUID.randomUUID().toString()+System.currentTimeMillis();
                        	AdRecomReply result =getAd(hash);
                        	if(result!=null){
                       			String returnHash=result.getRequstId();
                    			if(!returnHash.equals(hash)){
                    				 System.out.println("the hash code is mass,request hash:"+hash+",reply hash:"+returnHash);  
                    				 log.error("the hash code is mass,request hash:{},reply hash:{}",hash,returnHash);
                    			}else{
                    				 System.out.println(t+" the hash code is ok!");  
                    				// log.error("the hash code is ok,request hash:{},reply hash:{}",hash,returnHash);
                    			}
                        	}
 
                        }
                      //  doneSignal.countDown();//每调用一次countDown()方法，计数器减1  
                    }   
                };   
                exec.execute(run);   
            }  

        }  
          
        public static void main(String[] args) {  
             new ConcurrencyTest().run();  
        }  
        
        private AdRecomReply getAd(String hash){
        	    ThriftClient client =getClient();
    			if(client ==null){
    				for(int i=0;i<3;i++){
    					client=getClient();
    					if(client!=null){
    						break;
    					}
    				}
    			}
    			if(client ==null){
    				System.out.println("miss one mission");  
    				return null;
    			}
    			AdRecomReq adreq = new AdRecomReq();
    			adreq.setApp("meinvtuji");
    			adreq.setType("");
    			adreq.setVersion("3.5.6");
    			adreq.setResult_num(1);
    			adreq.setOgin_name("");
    			adreq.setLog(true);
    			adreq.setToken("7");
    			adreq.setRver(0);
    			adreq.setCheck_ver(false);
    			adreq.setHash(hash);
    			AdUserInfo userInfo = new AdUserInfo();
    			userInfo.setOs("android");
    			userInfo.setImei("866867021441253");
    			
    			// 116.226.72.4 58.221.56.201
    			userInfo.setClient_ip("116.236.204.78");
    			userInfo.setOsversion("5.0");
//    			userInfo.setPhonemodel("iPhone 7");
    			userInfo.setPhonemodel("ivvi SS1-01");
    			userInfo.setMobile("CMCC");
    			//userInfo.setCity("150100");
    			userInfo.setMac("d0374262c525");
    			userInfo.setAaid("5071b66c660b118b");
    			//userInfo.setAdid("40cc5d9703a840d52f5c0053ad905424");
//    			userInfo.setBrand_name("Apple");
    			userInfo.setBrand_name("ivvi");
//    			userInfo.setIdfa("160e18a715ccea95");
    			userInfo.setUa("Mozilla/5.0 (Linux; Android 4.4.4; ivvi SS1-01 Build/KTU84P) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/33.0.0.0 Mobile Safari/537.36");
    			userInfo.setDevice_height(640);
    			userInfo.setDevice_width(360);
//    			userInfo.setUa("AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 OPR/12.0.0.2147483647 Mobile Safari/537.36");
    			adreq.setUserinfo(userInfo);
    			
    			UserAdSpaceAttri attri = new UserAdSpaceAttri();
    			attri.setTitleMax(100);
    			attri.setTitleMin(0);
    			attri.setCwMax(100);
    			attri.setAppId(61);
    			attri.setSpaceType(AdSpaceType.OPENING);
    			attri.setAdSpaceId(zkpos);
    			//1:BANNER 2:插屏 3:开屏 4.信息流
    		
    			attri.setSpaceHeight(640);
    			attri.setSpaceWidth(960);
    			attri.setAllowedOpentype(1);
    			attri.setSpacePosition(0);
    			Set<AdSpaceImgFmt> imgFormats=new HashSet<AdSpaceImgFmt> ();
    			imgFormats.add(AdSpaceImgFmt.GIF);imgFormats.add(AdSpaceImgFmt.JPG);
    			attri.setImgFormats(imgFormats);
    			attri.setExpectedMarketTypes(Arrays.asList(ExpectedMarketType.LINK,ExpectedMarketType.APP));
    			attri.setTitleMin(0);
    			attri.setTitleMax(100);
    			attri.setCwMin(0);
    			attri.setCwMax(100);
    			attri.setJoinDspName(JoinDSPEmu.JUGAO.getAbbrev());
    			adreq.setUserAdSpaceAttri(attri);
    			AdRecomReply result=null;
				try {
					result = client.getClient().poll("13535355", adreq);
				} catch (TException e) {
					// TODO Auto-generated catch block
					client.destroy();
				}
				this.recycle(client);
                return result;
        }
        private void recycle(ThriftClient client){
        	if(client==null){
        		
        		client=new ThriftClient();//补充一个客户端
        		
        	}else if(client.getClient()==null||!client.getTransport().isOpen()){
        		
        		client=null;//交给虚拟机回收
        		client=new ThriftClient();
        	}
        	queue.add(client);
       }
/*        private ApiProxy.Client createClient(){
        	TTransport transport = null;
    		try {
    			transport = new TFramedTransport(new TSocket(SERVER_IP, SERVER_PORT, TIMEOUT));
    			TProtocol protocol = new TBinaryProtocol(transport);
    			ApiProxy.Client client = new ApiProxy.Client(protocol);
    			transport.open();
    			return client;
    		} catch (TTransportException e) {
    			//e.printStackTrace();
    		} catch (TException e) {
    			//e.printStackTrace();
    		} 
    		return null;
        
        }*/
/*        private ApiProxy.Client getClient(){
        
        	while(queue.isEmpty()){
        		try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}


        	}
    		if(!queue.isEmpty()){
        		synchronized(lock){
        			if(!queue.isEmpty()){
        				return queue.poll();
        			}
        		}
    		}
    		return null;
        }*/
        private ThriftClient getClient(){
            
        	while(queue.isEmpty()){
        		try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}


        	}
    		if(!queue.isEmpty()){
        		synchronized(lock){
        			if(!queue.isEmpty()){
        				return queue.poll();
        			}
        		}
    		}
    		return null;
        }
    }  

