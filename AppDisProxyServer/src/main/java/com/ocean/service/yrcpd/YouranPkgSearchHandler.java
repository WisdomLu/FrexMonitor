package com.ocean.service.yrcpd;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.inveno.util.CollectionUtils;
import com.ocean.app.dis.proxy.thrift.entity.AppDisRecomReq;
import com.ocean.app.dis.proxy.thrift.entity.DeviceInfo;
import com.ocean.app.dis.proxy.thrift.entity.UserInfo;
import com.ocean.core.common.JsonUtils;
import com.ocean.core.common.system.ErrorCode;
import com.ocean.core.common.system.MyLogManager;
import com.ocean.core.common.system.SystemContext;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
import com.ocean.core.common.threadpool.workthread.Task.DataFormat;
import com.ocean.core.common.threadpool.workthread.Task.InvokeType;
import com.ocean.persist.app.dis.AppDisException;
import com.ocean.persist.app.dis.AppDisResponse;
import com.ocean.persist.app.dis.yrcpd.appasyn.YouranAppasynApp;
import com.ocean.persist.app.dis.yrcpd.appasyn.YouranAppasynResp;
import com.ocean.persist.app.dis.yrcpd.pkgsearch.YouranPkgSearchReq;
import com.ocean.persist.app.dis.yrcpd.pkgsearch.YouranPkgSearchResp;
import com.ocean.persist.common.AppDisConstants;
import com.ocean.service.base.BaseHandler;
import com.ocean.service.yrcpd.base.YouranInvokeHandlerFactory;
import com.ocean.service.yrcpd.base.YouranMethodType;
import com.ocean.task.YouranPkgTask;

@Component(value="YouranPkgSearchHandler")
public class YouranPkgSearchHandler extends BaseHandler{
	private ThreadLocal<List<YouranAppasynApp>> yr_requestList=new ThreadLocal<List<YouranAppasynApp>> ();
	//private BlockingQueue<YouranAppasynApp>  requestList=new LinkedBlockingQueue<YouranAppasynApp>();
	private static String DATA_UPDATE_KEY;
	private static final String strDateFormat= "yyyy-MM-dd-HH-mm"; 
	//private static Object lock=new Object();
	static{
		DATA_UPDATE_KEY=getKey();

	}
	private  final Logger logger = MyLogManager.getLogger();
	private static String getKey(){

		SimpleDateFormat sdf=new SimpleDateFormat(strDateFormat);
		return sdf.format(new Date());
	}
	private void initKey(){
/*		if(!DATA_UPDATE_KEY.equals(getKey())){
			synchronized(lock){
				if(!DATA_UPDATE_KEY.equals(getKey())){*/
					DATA_UPDATE_KEY=getKey();
/*				}
			}
		}*/
	}

	@Override
	public AppDisResponse invok(AppDisRecomReq req) throws AppDisException {
		// TODO Auto-generated method stub
		YouranPkgSearchReq request=this.parsePkgParam(req);
		return invok(req,request);
		
	}
	public AppDisResponse invok(AppDisRecomReq req,YouranPkgSearchReq request) throws AppDisException {
		// TODO Auto-generated method stub
		return (YouranPkgSearchResp)this.invoke(request, YouranPkgSearchResp.class, req.getUserInfo().getUid(),String.valueOf(req.getAppSpaceInfo().getAdSpaceId()));
		
	}
	@Override
	public boolean validate(AppDisRecomReq req) {
		// TODO Auto-generated method stub
		return false;
	}
    public YouranPkgSearchReq parsePkgParam(AppDisRecomReq req)throws AppDisException{
		if(CollectionUtils.isEmpty(req.getJoinParam())||req.getJoinParam().size()<2){
			throw new AppDisException(ErrorCode.PARAM_ERROR,"get join params empty!");

		}
		UserInfo user=req.getUserInfo();
		DeviceInfo device=req.getDevice();
    	YouranPkgSearchReq param=new YouranPkgSearchReq();
		param.setApp_id(req.getJoinParam().get(0).getValue());
		param.setHz_id(req.getJoinParam().get(1).getValue());
    	param.setVersionName(req.getAppInfo().getVersionName());
    	param.setVersionCode(Integer.parseInt(req.getAppInfo().getVersionCode()));
    	param.setIp(user.getClientIp());
    	param.setMac(device.getMac());
    	
    	param.setImei(device.getImei());
    	param.setImsi(device.getImsi());
    	param.setModel(device.getPhonemodel());
    	param.setManufacture(device.getBrandName());
    	param.setApi_level(device.getOsversion());
    	param.setOsv(device.getOsVerName());
    	param.setAndroidId(device.getAdid());
    	param.setSerialno(device.getSerialNo());
    	param.setSw(device.getDeviceWidth());
    	param.setSh(device.getDeviceHeight());
    	param.setDip(Double.parseDouble(device.getDip()));
    	param.setSo(1);
    	param.setNet(getNetwork(user.getNet()));
    	param.setUa(user.getUa());
    	YouranAppasynApp app=getYrAppInfo(req);
    	//YouranAppasynApp app=getYrAppInfo_queue(req);
    	if(app!=null){
    		param.setPackageNames(app.getPackageName());
    	}else{
    		throw  new AppDisException(ErrorCode.PARAM_ERROR,"get asyn app list empty!");
    	}
    	
    	if(user.isSetLac()&&user.isSetCid()){
    		param.setInfo_ci(user.getCid());
    		param.setInfo_la(user.getLac());
    	}else{
    		param.setInfo_ci(34860);
    		param.setInfo_la(28883);
		}
    	
    	return param;
    }
	private YouranAppasynApp getYrAppInfo(AppDisRecomReq params) throws AppDisException{
		if(!DATA_UPDATE_KEY.equals(getKey())){//每分钟更新一次
			reqYrAppList(params);
		}else if(CollectionUtils.isEmpty(yr_requestList.get())){
			reqYrAppList(params);
			
		}

		{
			Iterator<YouranAppasynApp> it=yr_requestList.get().iterator();
			YouranAppasynApp data=null;
			if(it.hasNext()){
				data=it.next();
				it.remove();
			}
			return data;
		}
	
	}
/*	private YouranAppasynApp getYrAppInfo_queue(AppDisRecomReq params) throws AppDisException{
		if(CollectionUtils.isEmpty(requestList)){
			reqYrAppList(params);
		}
		{
	
			YouranAppasynApp data=null;
			try {
				data = requestList.take();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				throw  new AppDisException(ErrorCode.INTER_ERROR,"get asyn app list from queue erro!"+e.getMessage());
			}
            return data;
		}
	
	}*/
	private void reqYrAppList(AppDisRecomReq req) throws AppDisException{
		try {
			if(CollectionUtils.isNotEmpty(yr_requestList.get())){
				yr_requestList.remove();
			}//先清空列表，本应可以暂时先用着，但是对方时效要求比较高
			
			
			YouranAppasynHandler asynHandler=(YouranAppasynHandler)YouranInvokeHandlerFactory.getHandler(YouranMethodType.APP_LIST_ASYN_SEARCH);
			
			YouranAppasynResp resp=(YouranAppasynResp)asynHandler.invok(req);
			logger.info("Youran {} asyn app list :{}",req.getUserInfo().getUid(),JsonUtils.toJson(resp));
			putAppListToQueue(resp);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			throw  new AppDisException(ErrorCode.INTER_ERROR,"put app list to  queue erro!"+e.getMessage());
		}catch (Exception e) {
			throw  new AppDisException(ErrorCode.INTER_ERROR,"get app list error!"+e.getMessage());
		}finally{
			this.initKey();//失败与否都要更新key
		}
	}
	private void putAppListToQueue(YouranAppasynResp resp) throws InterruptedException, AppDisException{
		if(resp==null||CollectionUtils.isEmpty(resp.getInfo())){
			throw new AppDisException(ErrorCode.INTER_ERROR,"DATA_EMPTY return error code:"+resp.getRetCode()+" msg:"+resp.getRetMessage());
			
		}
		if(resp.getRetCode()!=0){
			throw new AppDisException(ErrorCode.INTER_ERROR,"return error code:"+resp.getRetCode()+" msg:"+resp.getRetMessage());
		}

		List<YouranAppasynApp> dataL=resp.getInfo();
		if(CollectionUtils.isNotEmpty(dataL)){
			yr_requestList.set(dataL);
		}
		

	}
/*	private void putAppListToQueue(YouranAppasynResp resp) throws InterruptedException, AppDisException{
		if(resp==null||CollectionUtils.isEmpty(resp.getInfo())){
			throw new AppDisException(ErrorCode.INTER_ERROR,"DATA_EMPTY return error code:"+resp.getRetCode()+" msg:"+resp.getRetMessage());
			
		}
		if(resp.getRetCode()!=0){
			throw new AppDisException(ErrorCode.INTER_ERROR,"return error code:"+resp.getRetCode()+" msg:"+resp.getRetMessage());
		}

		List<YouranAppasynApp> dataL=resp.getInfo();
		for(YouranAppasynApp app :dataL){
			requestList.put(app);
		}
		

	}*/
	private  int getNetwork(String net){	
		if(NETWORK_WIFI.equals(net)){
			return 1;
		}else if(NETWORK_2G.equals(net)){
			return 2;
		}
		else if(NETWORK_3G.equals(net)){
			return 3;
		}
		else if(NETWORK_4G.equals(net)){
			return 4;
		}
		return 5;
	}
	@Override
	public AsynAbstractTask packageTask(Parameter params, String hashCode) {
		// TODO Auto-generated method stub
		YouranPkgTask task = new YouranPkgTask();
		YouranPkgSearchReq param = (YouranPkgSearchReq) params;
		
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/x-www-form-urlencoded");
		
		StringBuilder url = new StringBuilder();
		url.append(SystemContext.getDynamicPropertyHandler().get(AppDisConstants.YOURAN_PKG_URL));
		

		task.setParam(param);
		task.setDataFormat(DataFormat.DATA_FORMAT_JSON);
		task.setInvokeType(InvokeType.INVOKE_TYPE_POST);
		task.setHashCode(hashCode);
		task.setUrl(url.toString());
		task.setHeaders(headers);
		return task;
	}
}
