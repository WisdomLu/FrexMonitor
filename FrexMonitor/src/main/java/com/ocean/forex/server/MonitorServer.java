package com.ocean.forex.server;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.inveno.util.CollectionUtils;
import com.ocean.core.common.JsonUtils;
import com.ocean.core.common.lifecycle.LifecycleException;
import com.ocean.core.common.system.SystemContext;
import com.ocean.forex.common.FrexCurrency;
import com.ocean.forex.common.MonitorConstants;
import com.ocean.forex.common.MonitorException;
import com.ocean.forex.entity.analyzedata.AnalyticalData;
import com.ocean.forex.entity.strategy.MonitorStrategy;
import com.ocean.forex.server.base.AbstractServer;
import com.ocean.forex.service.analyse.AnalyzeImpl;
import com.ocean.forex.service.analyse.IAnalyze;
import com.ocean.forex.service.analyse.StrategyCfgCvt;
import com.ocean.forex.service.analyse.StrategyCvt;
import com.ocean.forex.service.dataAsyn.DataAsynServiceFactory;
import com.ocean.forex.service.dataAsyn.DataAsynServiceType;
import com.ocean.forex.service.dataAsyn.IDataAsynService;
public class MonitorServer extends AbstractServer{
	 public MonitorServer(Args args) {
		super(args);
		// TODO Auto-generated constructor stub
	}
	
	/* 
	  * @see DefaultLifecycle#init0()
	  */
	 @Override
	 protected void init0() throws LifecycleException {
		 String dataAsyServiceArr=SystemContext.getDynamicPropertyHandler().get(MonitorConstants.MONITOR_SERVICE_NAME);
		 if(StringUtils.isEmpty(dataAsyServiceArr)){
			 throw new LifecycleException("init monitor error:monitor service  list empty!");
		 }
		 try {
			 for(String dataAsyService:dataAsyServiceArr.split(";")){
				 DataAsynServiceType ds =DataAsynServiceType.getServiceType(dataAsyService);
				 if(ds!=null){
					 IDataAsynService daService;
					
					 daService = DataAsynServiceFactory.getService(ds);
					 
					 Monitor monitor=new Monitor(daService);
					 this.addLifecycleListener(monitor);
					 
				 }else{
					 logger.error("init monitor error,monitor name {} error",dataAsyService); 
				 }
			 }
		} catch (MonitorException e) {
			// TODO Auto-generated catch block
			logger.error("server init ,get monitor service error{}",e.getMsg(),e);
		}
 		
	 }
	
	 /* 
	  * @see DefaultLifecycle#start0()
	  */
	 @Override
	 protected void start0() throws LifecycleException {

	 }
	
	 /* 
	  * @see DefaultLifecycle#destroy0()
	  */
	 @Override
	 protected void destroy0() throws LifecycleException {

	 }
	
	private List<MonitorStrategy> getPMS(FrexCurrency currency){
		String str=SystemContext.getDynamicPropertyHandler().get(MonitorConstants.MONITOR_STRATEGY);
		StrategyCfgCvt sc=JsonUtils.toBean(str,StrategyCfgCvt.class); 
		for(StrategyCvt s:sc.getConfig()){
			if(s.getFc()==currency){
				return s.getStrategies();
			}
		}
		return null;
	}
	 
	public IAnalyze getAnalyze() {

		return (AnalyzeImpl)SystemContext.getServiceHandler().getService(AnalyzeImpl.class);
	}
	@Override
	public void analyze(AnalyticalData anaData) throws MonitorException{
		List<MonitorStrategy> pmsL= getPMS(anaData.getFc());
		if(CollectionUtils.isNotEmpty(pmsL)){
			for(MonitorStrategy pms:pmsL){
				 getAnalyze().analyze(anaData, pms);
			}
			
			
		}
	}
}
