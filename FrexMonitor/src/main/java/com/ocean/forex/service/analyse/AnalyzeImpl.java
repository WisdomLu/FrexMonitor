package com.ocean.forex.service.analyse;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ocean.core.common.system.MyLogManager;
import com.ocean.forex.common.MonitorException;
import com.ocean.forex.entity.analyzedata.AbstractAnalyticalData;
import com.ocean.forex.entity.analyzedata.AnalyticalData;
import com.ocean.forex.entity.event.ImportantEvent;
import com.ocean.forex.entity.event.base.AbstractEvent;
import com.ocean.forex.entity.event.base.DefaultEvent;
import com.ocean.forex.entity.strategy.AbstractStrategy;
import com.ocean.forex.entity.strategy.MonitorStrategy;
import com.ocean.forex.service.notify.email.EmailAlertorImpl;
@Component(value = "Analyze")
public class AnalyzeImpl implements IAnalyze {
	public  final Logger logger = MyLogManager.getLogger();
	@Autowired 
	private EmailAlertorImpl emailAlertor;
	@Override
	public AbstractEvent analyze(AbstractAnalyticalData aad, AbstractStrategy st) throws MonitorException{
		// TODO Auto-generated method stub

		AnalyticalData ad=(AnalyticalData)aad;
		switch(st.getAt()){
		    case ALERT_RAPID_CHANGE:
		    	RCAnalyze( ad,  st);
		    	break;
		    case ALERT_IMPORT_EVENT:
		    	IEAnalyze( ad,  st);
		    	break;
		    default:
		    
		    	PriceAnalyze( ad,  st);
		    	break;
		}
		return null;
	}
	private void RCAnalyze(AnalyticalData ad, AbstractStrategy st){
	
	} 
	private void IEAnalyze(AnalyticalData ad, AbstractStrategy st){
		ImportantEvent ie=new ImportantEvent();
		if(st instanceof MonitorStrategy){
			MonitorStrategy ms=(MonitorStrategy)st;
			double realPrice=ad.getEr().get(ad.getEr().size()-1).getPrivce();
			if(ie.getDateStr().equals(((MonitorStrategy) st).getEventStart())){
				
				ie.setName(ad.getFc().getName()+"|"+ms.getAt().getChName());
				ie.setDesc(ie.getDateStr()+"|"+ie.getName()+"|"+realPrice);
				emailAlertor.notify(ie);
			}

		}
	
	} 
	private void PriceAnalyze(AnalyticalData ad, AbstractStrategy st){
		if(st instanceof MonitorStrategy){
			MonitorStrategy ms=(MonitorStrategy)st;
			double realPrice=ad.getEr().get(ad.getEr().size()-1).getPrivce();
			logger.debug("analyzing.......realPrice:{},{},expPrice:{}",realPrice,ms.getOrientation(),ms.getPrice());
			if(ms.getOrientation()<0&&realPrice<ms.getPrice()){
				this.notify(ms.getAt().getChName(),ad.getFc().getName(), realPrice, ms.getPrice(), MonitorStrategy.LESS_THAN);
			}else if(ms.getOrientation()>0&&realPrice>ms.getPrice()){
				this.notify(ms.getAt().getChName(),ad.getFc().getName(), realPrice, ms.getPrice(),MonitorStrategy. GREATER_THAN);
			}
		}
	}
	private void notify(String atName,String fcName,double realPrice,double expPrivce,String orientation){
		DefaultEvent e=new DefaultEvent();
		e.setName(fcName+"|"+atName);
		e.setDesc(e.getDateStr()+"|"+e.getName()+"|"+realPrice+orientation+expPrivce);
		emailAlertor.notify(e);
	}
	public EmailAlertorImpl getEmailAlertor() {
		return emailAlertor;
	}
	public void setEmailAlertor(EmailAlertorImpl emailAlertor) {
		this.emailAlertor = emailAlertor;
	} 
	 
}
