package com.ocean.forex.service.dataAsyn.task;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.commons.lang3.StringUtils;

import com.ocean.core.common.http.Bean2Utils;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
import com.ocean.forex.common.FrexCurrency;
import com.ocean.forex.entity.ExchangeRate;
import com.ocean.forex.entity.analyzedata.AnalyticalData;
import com.ocean.forex.service.dataAsyn.jinshi.persist.JinshiReq;
import com.ocean.forex.service.dataAsyn.jinshi.persist.JinshiResp;

public class JinshiTask  extends AsynAbstractTask<JinshiReq,JinshiResp>{

	@Override
	public String reqFormat() {
		// TODO Auto-generated method stub
	
		return Bean2Utils.toHttpParams(param);
	}

	@Override
	public byte[] reqByteFormat() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JinshiResp respFormat(String result)
			throws Exception {
		// TODO Auto-generated method stub
		JinshiResp resp=null;
		try{
			
			if(StringUtils.isNotEmpty(result)){
				
				resp=check(result);
			}
		}catch (Throwable e) {
			LOGGER.error("{} {} parse response data error(Exception),msg:{}",this.getClass().getName(),this.getHashCode(),e.getMessage(),e);
		}finally{
			this.setResponse(resp);
		}
		return  this.getResponse();
	}
	private JinshiResp check(String data){
		if(StringUtils.isEmpty(data)||data.indexOf("[")<0){
			return null;
		}
		String str=data.substring(data.indexOf("["),data.length()-1);
       	JSONArray jsonArray=JSONArray.fromObject(str);
       	Object[] os = jsonArray .toArray();
       	JinshiResp resp=new JinshiResp();
       	AnalyticalData ad=new AnalyticalData();
       	//
       	String currName=param.getType();
       	ad.setFc(FrexCurrency.getCurrencyType(currName));
        List<ExchangeRate> adL=new ArrayList<ExchangeRate>();
       	for(int i=0;i<os.length; i++) {
           	
       		JSONArray dataArr = JSONArray.fromObject(os[i]);
       		ExchangeRate er=new ExchangeRate();
/*       		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//这个是你要转成后的时间的格式
       		String sd = sdf.format(new Date(Long.parseLong(String.valueOf(dataArr .toArray()[0]))));   // 时间戳转换成时间
*/       		er.setTimestamp(Long.parseLong(String.valueOf(dataArr .toArray()[0])));
       		er.setPrivce(Double.parseDouble(String.valueOf(dataArr .toArray()[1])));
       		adL.add(er);
       	}
       	ad.setEr(adL);
       	resp.setAd(ad);
       	return resp;
       	
	}
	@Override
	public JinshiResp respFormat(byte[] result) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
