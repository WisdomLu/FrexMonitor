package com.ocean.core.common.threadpool.workthread;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Logger;

import com.inveno.util.CollectionUtils;
import com.ocean.core.common.UniversalUtils;
import com.ocean.core.common.http.HttpClient;
import com.ocean.core.common.http.HttpInvokeException;
import com.ocean.core.common.system.Constants;
import com.ocean.core.common.system.MyLogManager;
import com.ocean.core.common.system.SystemContext;


/** * 任务异步分发框架
 * 	  @author Alex & E-mail:569246607@qq.com
      @date   2017年7月21日 
      @version 1.0 
 */
public class AsynTaskDisServer extends AsynAbstractTaskDisServer{
	public  final Logger LOGGER = MyLogManager.getDisLoggerV2();
	public static class Args extends AsynAbstractServerArgs<Args>{
	    private int workThread = 8;
        private int queueSize=16;
        private int poolSize=8;
		public void validate() {
			if(this.workThread<=0){
				throw new IllegalArgumentException("work threads must be positive.");
			}
			if(queueSize<=0){
				throw new IllegalArgumentException("work thread queue size must be positive.");
			}
		}

		public int getQueueSize() {
			return queueSize;
		}

		public Args setQueueSize(int queueSize) {
			this.queueSize = queueSize;
			return this;
		}

		public int getWorkThread() {
			return workThread;
		}

		public Args setWorkThread(int workThread) {
			this.workThread = workThread;
			return this;
		}

		public int getPoolSize() {
			return poolSize;
		}

		public Args setPoolSize(int poolSize) {
			this.poolSize = poolSize;
			return this;
		}

	}
	private final Set<WorkThread> workThreads = new HashSet();
	private final Args args;
    private final ThreadSelectBalancer threadChooser;
    private  AcceptThread acceptThread;
    
    
   
    public AsynTaskDisServer(Args args) throws Exception{
    	super(args);
    	args.validate();
    	this.args=args;
        

    	
    	//任务处理线程初始化
        startThreads();
        this.threadChooser = new ThreadSelectBalancer(this.workThreads);
    }
    private  ExecutorService createExcutor(int poolSize){
    	 return poolSize > 0 ? Executors.newFixedThreadPool(poolSize) : null;
    }
    
    protected boolean startWorkThreads(CountDownLatch countdownlatch)
    {
      acceptThread=new AcceptThread(this.args);
      for (int i = 0; i < this.args.workThread; i++) {
    	  	this.workThreads.add(new WorkThread(this.args.queueSize/4));
      }
      for (WorkThread thread : this.workThreads) {
    	  thread.start();
      }
      acceptThread.start();
      this.LOGGER.info("work thread start succeed...");
      countdownlatch.countDown();
      return true;
    }
    public boolean doAccept(AsynAbstractTask task){
	    try {
    		return acceptThread.addTask(task);
    	    
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			this.LOGGER.info("do Accept  InterruptedException",e);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			this.LOGGER.info("do Accept  Exception",e);
		}
	    return this.acceptHanlder(task);//如果放入缓冲区失败，直接处理

    }
    
    public  boolean acceptHanlder(AsynAbstractTask task){

        if (task == null)
        {
	          this.LOGGER.error("add a null task to queue");
	          return false;
        }
        if (!task.validate()) {
        	  return false;
        }
        if(this.isClosed()){
	          this.LOGGER.error("task dispacher server is closed");
	          return false;
        }
        try
        {
	          WorkThread thread = (WorkThread)this.threadChooser.nextThread();
	          thread.addTask(task);
        }
        catch (Exception e)
        {
	          this.LOGGER.error("the work server accept request error", e);
	          return false;
        }
        return true;

      
    }

	@Override
	protected void startThreads() {
		// TODO Auto-generated method stub
		CountDownLatch countdownlatch = new CountDownLatch(1);
		startWorkThreads(countdownlatch);
        try
        {
        	countdownlatch.await();
        }catch (InterruptedException e)
        {
        	LOGGER.error("Try to start workthread error",e);

        }
	}
	@Override
	protected void shutdown() {
		// TODO Auto-generated method stub
	   this.setClosed(true);
	} 
	protected class AcceptThread extends Thread{
		private final BlockingQueue<AsynAbstractTask> requestQueue;
		private final ExecutorService invoker;
		public AcceptThread(Args args){
			
	    	//任务接收
	    	requestQueue=new ArrayBlockingQueue(args.queueSize);
	    	invoker=createExcutor(args.poolSize);
	    	
		}
		public void run(){
			while(true){
				AsynAbstractTask task = select();
				if(task!=null){//!!!!!
					handlerTask(task);
				}
			}
		}
		private AsynAbstractTask select(){
		
			//requestQueue.poll();
			try {
				return this.requestQueue.take();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
		
				AsynTaskDisServer.this.LOGGER.error("select  task from queue error (InterruptedException):{}",e.getMessage(),e);
			}catch(Exception e){
				AsynTaskDisServer.this.LOGGER.error("select  task from queue error (Exception):{}",e.getMessage(),e);
			}catch(Throwable e){
				AsynTaskDisServer.this.LOGGER.error("select  task from queue error  :{}",e.getMessage(),e);
			}
			return null;
		}
		private boolean addTask(AsynAbstractTask task) throws InterruptedException,Exception{
			return this.requestQueue.offer(task);//加不进去就丢弃,高峰时可以消峰
		}
		public void handlerTask(AsynAbstractTask task){
			if(task==null){
				return;
			}
			try{

				this.invoker.submit(new AsynTaskDisServer.AcceptRuner(task));

			}catch(Exception e){
				AsynTaskDisServer.this.LOGGER.error("AcceptThread handle   task error (Exception):{}",e.getMessage(),e);
			}catch(Throwable e){
				AsynTaskDisServer.this.LOGGER.error("AcceptThread handle   task  error :{}",e.getMessage(),e);
			}
		}

	}
	protected class AcceptRuner implements Runnable{
		private  AsynAbstractTask task;
		public AcceptRuner(AsynAbstractTask task) {
			this.task=task;
		}
        public void run()
        {
			 AsynTaskDisServer.this.acceptHanlder(task);
        }
	}
	protected class WorkThread extends AsynAbstractTaskDisServer.AbstractWorkThread
    {
	    private final BlockingQueue<AsynAbstractTask> queue;
	    public WorkThread() throws IOException
	    {
	    	  this(new LinkedBlockingQueue());
	    }
	    
	    public WorkThread(int queueSize)
	    {
		      if (queueSize <= 0) {
		    	  this.queue = new LinkedBlockingQueue();
		      } else {
		    	  this.queue = new ArrayBlockingQueue(queueSize);
		      }
	    }
	    
	    public WorkThread(BlockingQueue queue)throws IOException{
		      this.queue = queue;
	    }
	    private void doWork(AsynAbstractTask task){
	           try{
	        	    if(!task.isValid()){//任务无效直接退出
	        	    	return;
	        	    }
					long ts = System.currentTimeMillis();	
					
					switch(task.getInvokeType()){
					   case INVOKE_TYPE_GET:
						   httpGet(task);
						   break;
					   case INVOKE_TYPE_POST:
						   httpPost(task);
						   break;
					   default:
						   LOGGER.error("{} invalid invoke type!",task.getHashCode());
						   break;
						 
					}
					LOGGER.info("Task {} url:{},pull api  time cost:{} ms",task.getHashCode(),task.getUrl(),System.currentTimeMillis() - ts);
				} catch (HttpInvokeException e) {
					// TODO Auto-generated catch block
					LOGGER.error("Task {} ,url:{}, deal with  task error(HttpInvokeException) {}",task.getHashCode(),task.getUrl(),e.getMessage(),e);
					//task.setValid(false);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					LOGGER.error("Task {} ,url:{},deal with  task error(Exception) {}",task.getHashCode(),task.getUrl(),e.getMessage(),e);
					//task.setValid(false);
				}catch (Throwable  e) {
					// TODO Auto-generated catch block
					LOGGER.error("Task {} ,url:{}, deal with  task error {}",task.getHashCode(),task.getUrl(),e.getMessage(),e);
					//task.setValid(false);
				}

		}
	    private void httpGet(AsynAbstractTask task) throws Exception{
	    	//AsynInvokeResponse resp=null;
		    String strResult="";
		    String url=UniversalUtils.replaceBlank(task.getUrl());//去掉非法空格什么的
		    LOGGER.info("Task {} request[get] url:{} ",task.getHashCode(),url);
			if(CollectionUtils.isEmpty(task.getHeaders())){
				strResult =HttpClient.getInstance().get(url);
			}else{
				strResult =HttpClient.getInstance().get(url, task.getHeaders());
			}
			printResult(task.getHashCode(),strResult);
			
			task.respFormat(strResult);
			
	    }
	    private void httpPost(AsynAbstractTask task) throws Exception{
	        if(task.getDataFormat()==task.dataFormat.DATA_FORMAT_JSON){
	        	httpPostJson(task);
	        	return ;
	        }
	        
	        byte[] byteResult=null;
			if(task.getDataFormat()==Task.DataFormat.DATA_FORMAT_STREAM){
				byteResult =HttpClient.getInstance().postBytes(task.getUrl(), task.reqByteFormat(), CollectionUtils.isEmpty(task.getHeaders())?null:task.getHeaders());
				task.respFormat(byteResult);
			}
			else if(task.getDataFormat()==Task.DataFormat.DATA_FORMAT_ZIP){
				String result=HttpClient.getInstance().postBytesZip(task.getUrl(), task.reqByteFormat(), CollectionUtils.isEmpty(task.getHeaders())?null:task.getHeaders());
				
				int isLogOpen=SystemContext.getDynamicPropertyHandler().getInt(Constants.LOG_IS_ALL_WRITE,0);
				String content=isLogOpen==1?result:Constants.OMIT_CONTENT;
				printResult(task.getHashCode(),content);
				
				task.respFormat(result);
			}
			


	    }
	    private void httpPostJson(AsynAbstractTask task) throws Exception{
			int isLogOpen=SystemContext.getDynamicPropertyHandler().getInt(Constants.LOG_IS_ALL_WRITE,0);
	        String strResult="";
	        LOGGER.info("Task {} request[post] url:{}  param:{} ",task.getHashCode(),task.getUrl(),UniversalUtils.replaceBlank(task.reqFormat()));
			if(CollectionUtils.isEmpty(task.getHeaders())){
				strResult =HttpClient.getInstance().post(task.getUrl(), task.reqFormat());
				
				
			}else   if(CollectionUtils.isNotEmpty(task.getHeaders())){
				strResult =HttpClient.getInstance().post(task.getUrl(), task.reqFormat(), task.getHeaders());
				
			}

			String content=isLogOpen==1?strResult:Constants.OMIT_CONTENT;
			printResult(task.getHashCode(),content);
			
			task.respFormat(strResult);
	    }
	    private void printResult(String hashCode,String result){
			if(StringUtils.isNotEmpty(result)){
				LOGGER.info("Task {} return source data:{} ",hashCode,UniversalUtils.replaceBlank(result));
			}else{
				LOGGER.info("Task {} return  data  empty ",hashCode);
			}
	    }
	    public boolean addTask(AsynAbstractTask task)
	    {
		      try
		      {
		    	  //this.queue.offer(task,400,TimeUnit.MILLISECONDS);
		    	  this.queue.put(task);
		      }
		      catch (InterruptedException e)
		      {
		    	  LOGGER.error("{} Interrupted while adding task to queue!",task.getHashCode());
		    	  return false;
		      }
		      return true;
	    }
	    
	    public void run(){

	    	while (true){
	        	 AsynAbstractTask task = getTask();
	        	 if(task!=null){//!!!!!
	        		 doWork(task);
	        	 }
	    	}

	    	
	    }
	    
	    public AsynAbstractTask getTask()
	    {
		      AsynAbstractTask task = null;
		      try
		      {
		    	  task = (AsynAbstractTask)this.queue.take();
		    	  //task = (AsynAbstractTask)this.queue.poll();
		      }
		      catch (Exception e)
		      {
			        LOGGER.error("get task error", e);

		      }
		      return task;
        }
   }
   protected static class ThreadSelectBalancer{
	    private final Collection<? extends AsynAbstractTaskDisServer.AbstractWorkThread> threads;
	    private Iterator<? extends AsynAbstractTaskDisServer.AbstractWorkThread> nextThreadIterator; 
	    public <T extends AsynAbstractTaskDisServer.AbstractWorkThread> ThreadSelectBalancer(Collection<T> threads)
	    {
		      if (threads.isEmpty()) {
		        throw new IllegalArgumentException("At least one selector thread is required");
		      }
		      this.threads = Collections.unmodifiableList(new ArrayList(threads));
		      this.nextThreadIterator = this.threads.iterator();
	    }
	    
	    public AsynAbstractTaskDisServer.AbstractWorkThread nextThread()
	    {
		      if (!this.nextThreadIterator.hasNext()) {
		        this.nextThreadIterator = this.threads.iterator();
		      }
		      return (AsynAbstractTaskDisServer.AbstractWorkThread)this.nextThreadIterator.next();
	    }
	}
}
