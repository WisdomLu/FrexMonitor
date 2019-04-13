package com.ocean.forex.server.base;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Logger;

import com.inveno.util.CollectionUtils;
import com.ocean.core.common.lifecycle.ILifecycleListener;
import com.ocean.core.common.lifecycle.LifeCycle;
import com.ocean.core.common.lifecycle.LifecycleEvent;
import com.ocean.core.common.lifecycle.LifecycleState;
import com.ocean.core.common.system.MyLogManager;
import com.ocean.core.common.system.SystemContext;
import com.ocean.forex.common.FrexCurrency;
import com.ocean.forex.common.MonitorConstants;
import com.ocean.forex.common.MonitorException;
import com.ocean.forex.entity.ExchangeRate;
import com.ocean.forex.entity.analyzedata.AnalyticalData;
import com.ocean.forex.service.dataAsyn.IDataAsynService;
import com.ocean.forex.service.dataAsyn.DataAsynServiceFactory;
import com.ocean.forex.service.dataAsyn.DataAsynServiceType;

public abstract class AbstractServer extends LifeCycle{
	public  final Logger logger = MyLogManager.getLogger();
	private  final Args args;
	private  DataAnalyst analyst;

	public static class Args{
		private int taskQueueSize;
		private long interval;
		public int getTaskQueueSize() {
			return taskQueueSize;
		}

		public Args setTaskQueueSize(int taskQueueSize) {
			this.taskQueueSize = taskQueueSize;
			return this;
		}

		public long getInterval() {
			return interval;
		}

		public Args setInterval(long interval) {
			this.interval = interval;
			return this;
		}
	}
	public AbstractServer(Args args){
		this.args=args;
		this.initServer();
	}
	private void initServer(){
		if(args==null){
			logger.error("init server error:args empty!");
			
		}else{
			analyst=new DataAnalyst(args.getTaskQueueSize());
			analyst.start();
		}
	}
	private void insertTask(AnalyticalData ad){
		analyst.addTask(ad);
	}
	public class DataAnalyst extends Thread{
		private final BlockingQueue<AnalyticalData> taskQueue;
		public DataAnalyst (int queueSize){
			taskQueue=new ArrayBlockingQueue<AnalyticalData>(queueSize);
		}
		public void addTask(AnalyticalData ad){
			taskQueue.offer(ad);
		}
		public AnalyticalData getTask(){
			try {
				return taskQueue.take();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				logger.error("get task from queue error(InterruptedException):{}",e.getMessage(),e);
			}
			return null;
		}
		public void run(){
			while(true){
				try{
					AnalyticalData ad=this.getTask();
					if(ad!=null){
						AbstractServer.this.analyze(ad);
					}
				}catch(MonitorException e){
					logger.error("monitor server running error(MonitorException):{}",e.getMsg(),e);
				}catch(Throwable e){
					logger.error("Data analyst error:{}",e.getMessage(),e);
				}

			}
		}
	}
	public class Monitor extends Thread implements ILifecycleListener{
		private IDataAsynService daService;
		
		public Monitor(IDataAsynService daService){
			this.daService=daService;
		}
		public void run(){
			while(true){
				if(AbstractServer.this.getState()==LifecycleState.DESTROYED){
					break;
				}
				try{
					String currcyArr=SystemContext.getDynamicPropertyHandler().get(MonitorConstants.MONITOR_CURRENCY_LIST);
					if(StringUtils.isEmpty(currcyArr)){
						logger.error("monitor server get monitoring currency  empty:{}");
						break;
					}
					for(String fcStr:currcyArr.split(";")){
						FrexCurrency fc=FrexCurrency.getCurrencyType(fcStr);
						if(fc==null){
							logger.error("monitor server get monitoring currency  error name not mapping:{}",fcStr);	
						}
						List<ExchangeRate>  er=daService.getExchangeRate(fc);
						if(CollectionUtils.isNotEmpty(er)){
							AnalyticalData ad=new AnalyticalData ();
							ad.setEr(er);
							ad.setFc(fc);
							AbstractServer.this.insertTask(ad);
						}
					}
					
				}catch(MonitorException e){
					logger.error("monitor server running error(MonitorException):{}",e.getMsg(),e);
				}
				catch(Exception e){
					logger.error("monitor server running error(Exception):{}",e.getMessage(),e);
				}catch(Throwable e){
					logger.error("monitor server running error(Throwable):{}",e.getMessage(),e);
				}
				try {
					Thread.sleep(args.interval);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					logger.error("monitor server sleeping error:{}",e.getMessage(),e);
				}
			}
		}

		@Override
		public void lifecycleEvent(LifecycleEvent event) {
			// TODO Auto-generated method stub
			if(event.getState()==LifecycleState.INITIALIZING){
				this.init();
			}else if(event.getState()==LifecycleState.INITIALIZED){
				this.start();
			}else if(event.getState()==LifecycleState.DESTROYING){
				logger.info("monitor server destrying.....");
			}else if(event.getState()==LifecycleState.DESTROYED){
				logger.info("monitor server destryed.....");
			}
			
		}
		
		private void init(){

		}
	}
	public Args getArgs() {
		return args;
	}
	public DataAnalyst getAnalyst() {
		return analyst;
	}
	public void setAnalyst(DataAnalyst analyst) {
		this.analyst = analyst;
	}
	public abstract void analyze(AnalyticalData anaData)throws MonitorException;
}
