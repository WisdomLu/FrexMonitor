package com.ocean.service.yrcpd_t2;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.inveno.util.CollectionUtils;
import com.inveno.util.StringUtil;
import com.ocean.app.dis.proxy.thrift.entity.AppDisRecomReq;
import com.ocean.app.dis.proxy.thrift.entity.DeviceInfo;
import com.ocean.app.dis.proxy.thrift.entity.ExtData;
import com.ocean.app.dis.proxy.thrift.entity.InterParam;
import com.ocean.app.dis.proxy.thrift.entity.UserInfo;
import com.ocean.core.common.JsonUtils;
import com.ocean.core.common.http.Bean2Utils;
import com.ocean.core.common.system.ErrorCode;
import com.ocean.core.common.system.MyLogManager;
import com.ocean.core.common.system.SystemContext;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
import com.ocean.core.common.threadpool.workthread.Task.DataFormat;
import com.ocean.core.common.threadpool.workthread.Task.InvokeType;
import com.ocean.persist.app.dis.AppDisException;
import com.ocean.persist.app.dis.AppDisResponse;
import com.ocean.persist.app.dis.wanka.WankaApp;
import com.ocean.persist.app.dis.yrcpd_t2.appasyn.YouranT2AppasynApp;
import com.ocean.persist.app.dis.yrcpd_t2.appasyn.YouranT2AppasynResp;
import com.ocean.persist.app.dis.yrcpd_t2.pkgsearch.YouranT2PkgSearchReq;
import com.ocean.persist.app.dis.yrcpd_t2.pkgsearch.YouranT2PkgSearchResp;
import com.ocean.persist.common.AppDisConstants;
import com.ocean.service.base.BaseHandler;
import com.ocean.service.yrcpd.base.YouranMethodType;
import com.ocean.task.YouranT2PkgTask;

@Component(value="YouranT2PkgSearchHandler")
public class YouranT2PkgSearchHandler extends BaseHandler{
	private ThreadLocal<List<YouranT2AppasynApp>> yr2_requestList=new ThreadLocal<List<YouranT2AppasynApp>> ();
	private ThreadLocal<String> updateKey=new ThreadLocal<String>();//由于是单例模式，如果线程不各自维护，可能会造成该线程还没来得及更新列表，其他线程已经修改了这个key，而得不到更新
	private static final String strDateFormat= "yyyy-MM-dd-HH-mm-ss-SSS"; 
	private final Logger logger = MyLogManager.getLogger();
	private static String getLocalKey(){
		SimpleDateFormat sdf=new SimpleDateFormat(strDateFormat);
		return sdf.format(new Date());
	}
	private void initKey(){
		updateKey.set(getLocalKey());
	}

	@Override
	public AppDisResponse invok(AppDisRecomReq req) throws AppDisException {
		// TODO Auto-generated method stub
		YouranT2PkgSearchReq request=this.parsePkgParam(req);
		return invok(req,request);
		
	}
	public AppDisResponse invok(AppDisRecomReq req,YouranT2PkgSearchReq request) throws AppDisException {
		// TODO Auto-generated method stub
		return (YouranT2PkgSearchResp)this.invoke(request, YouranT2PkgSearchResp.class, req.getUserInfo().getUid(),String.valueOf(req.getAppSpaceInfo().getAdSpaceId()));
		
	}
	@Override
	public boolean validate(AppDisRecomReq req) {
		// TODO Auto-generated method stub
		return false;
	}
    public YouranT2PkgSearchReq parsePkgParam(AppDisRecomReq req)throws AppDisException{
		if(CollectionUtils.isEmpty(req.getJoinParam())||req.getJoinParam().size()<2){
			throw new AppDisException(ErrorCode.PARAM_ERROR,"get join params empty!");

		}
		UserInfo user=req.getUserInfo();
		DeviceInfo device=req.getDevice();
    	YouranT2PkgSearchReq param=new YouranT2PkgSearchReq();
		param.setApp_id(req.getJoinParam().get(0).getValue());
		param.setHz_id(req.getJoinParam().get(1).getValue());
		
		param.setAppName(req.getAppInfo().getAppName());
		param.setVersionName(req.getAppInfo().getVersionName());
    	param.setVersionCode(Integer.parseInt(req.getAppInfo().getVersionCode()));
    	param.setIp(user.getClientIp());
    	param.setMac(device.getMac());
    	param.setDeviceType(1);
    	
    	param.setImei(device.getImei());
    	param.setImsi(device.getImsi());
    	try {
			param.setModel(URLEncoder.encode(device.getPhonemodel(),CHARSET_CODER));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	param.setManufacturer(device.getBrandName());
    	param.setApiLevel(device.getOsversion());
    	param.setOsType(1);
    	param.setOsVersion(device.getOsVerName());
    	param.setOsVersionCode(device.getOsversion());
    	param.setApplicationType("1");
    	param.setAndroidId(device.getAdid());
    	param.setSerialno(device.getSerialNo());
    	
    	param.setScreenSize(device.getDeviceWidth()+"x"+device.getDeviceHeight());
    	param.setScreenWidth(String.valueOf(device.getDeviceWidth()));
    	param.setScreenHeight(String.valueOf(device.getDeviceHeight()));
    	
    	param.setDip(Double.parseDouble(device.getDip()));
    	param.setScreenOrientation("1");
    	param.setNetworkType(getNetwork(user.getNet()));
    	param.setOperatorType(this.getOp(device.getMobile()));
    	
		if(req.isSetPage()){
			param.setPageSize(String.valueOf(req.getPage().getPageSize()));
		}else{
			param.setPageSize(String.valueOf(pageSize));//默认请求10个
		}
    	param.setUa(user.getUa());

/*    	YouranT2AppasynApp app=getYrAppInfo(req);
    	if(app!=null){
    		param.setPackageName(app.getPackageName());
    	}else{
    		throw  new AppDisException(ErrorCode.PARAM_ERROR,"get asyn app list empty!");
    	}*/
    	param.setPackageName(getPkgName(req));
    	
    	if(user.isSetLac()&&user.isSetCid()){
    		param.setCellularID(user.getCid());
    		param.setLac(user.getLac());
    	}else{
    		param.setCellularID(34860);
    		param.setLac(28883);
		}
    	return param;
    }
	private String getPkgName(AppDisRecomReq req) throws AppDisException{
		InterParam interParam=req.getInterParam();
		List<ExtData> edL=interParam.getRequiredParam();
		if(CollectionUtils.isNotEmpty(edL)&&"1".equals(edL.get(0).getValue())){//不屏蔽客户端包名
			String pkgName=interParam.getPkgName();
			if(StringUtil.isNotEmpty(pkgName)){
				return pkgName;
			}
		}
		
    	YouranT2AppasynApp app=getYrAppInfo(req);
    	//YouranAppasynApp app=getYrAppInfo_queue(req);
    	if(app!=null){
    		return app.getPackageName();
    	}else{
    		throw  new AppDisException(ErrorCode.PARAM_ERROR,"get asyn app list empty!");
    	}
	}
    private boolean checkKey(){
    	if(StringUtils.isEmpty(updateKey.get())){
    		return true;
    	}
    	SimpleDateFormat sdf=new SimpleDateFormat(strDateFormat);// 使用这个这个方法将变量改为strDateFormat= "yyyy-MM-dd-HH-mm-ss-SSS"，否则存在一定的误差; 
    	try {
			long inter=new Date().getTime()-sdf.parse(updateKey.get()).getTime();
			long threshold=SystemContext.getDynamicPropertyHandler().getLong(AppDisConstants.YOURANT2_TIME_OUT,5*60*1000);
			if(inter>=threshold){//一段时间更新一次
				return true;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return true;
		}
    	return false;
    }
	private YouranT2AppasynApp getYrAppInfo(AppDisRecomReq params) throws AppDisException{
		//if(StringUtils.isEmpty(updateKey.get())||!updateKey.get().equals(getLocalKey())){//每分钟更新一次
			
		if(checkKey()){
			reqYrAppList(params);
			
		}else if(CollectionUtils.isEmpty(yr2_requestList.get())){
			reqYrAppList(params);
			
		}

		{
			//随机取
			int rand=(int)(Math.random()*yr2_requestList.get().size());
			YouranT2AppasynApp data=yr2_requestList.get().get(rand);
			
/*			Iterator<YouranT2AppasynApp> it=yr2_requestList.get().iterator();
			YouranT2AppasynApp data=null;
			if(it.hasNext()){
				data=it.next();
				it.remove();
			}*/
			return data;
		}
	
	}

	private void reqYrAppList(AppDisRecomReq req) throws AppDisException{
	
		try {
			if(CollectionUtils.isNotEmpty(yr2_requestList.get())){
				yr2_requestList.remove();
			}//先清空列表，本应可以暂时先用着，但是对方时效要求比较高
			
			
			YouranT2AppasynHandler asynHandler=(YouranT2AppasynHandler)YouranT2InvokeHandlerFactory.getHandler(YouranMethodType.APP_LIST_ASYN_SEARCH);
			YouranT2AppasynResp resp=(YouranT2AppasynResp)asynHandler.invok(req);
			logger.info("YOURAN_TYPE2_APPSTORE {} update key {} asyn app list :{}",req.getUserInfo().getUid(),updateKey.get(),JsonUtils.toJson(resp));
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
	private void putAppListToQueue(YouranT2AppasynResp resp) throws InterruptedException, AppDisException{
		if(resp==null||CollectionUtils.isEmpty(resp.getInfo())){
			throw new AppDisException(ErrorCode.INTER_ERROR,"DATA_EMPTY return error code:"+resp.getRetCode()+" msg:"+resp.getRetMessage());
			
		}
		if(resp.getRetCode()!=0){
			throw new AppDisException(ErrorCode.INTER_ERROR,"return error code:"+resp.getRetCode()+" msg:"+resp.getRetMessage());
		}
		
		List<YouranT2AppasynApp> dataL=resp.getInfo();
		if(CollectionUtils.isNotEmpty(dataL)){
			yr2_requestList.set(dataL);
		}
		

	}
	private  int getNetwork(String net){	
		if(NETWORK_WIFI.equals(net)){
			return 100;
		}else if(NETWORK_2G.equals(net)){
			return 2;
		}
		else if(NETWORK_3G.equals(net)){
			return 3;
		}
		else if(NETWORK_4G.equals(net)){
			return 4;
		}
		return 0;
	}
	private int  getOp(String mobile){
		if(MOBILE_CMCC.equals(mobile)){
			return 1;
		}else if(MOBILE_CUCC.equals(mobile)){
			return 3;
		}else if(MOBILE_CTCC.equals(mobile)){
			return 2;
		}
		return 0;
	}
	@Override
	public AsynAbstractTask packageTask(Parameter params, String hashCode) {
		// TODO Auto-generated method stub
		YouranT2PkgTask task = new YouranT2PkgTask();
		YouranT2PkgSearchReq param = (YouranT2PkgSearchReq) params;
		
	
		StringBuilder url = new StringBuilder();
		url.append(SystemContext.getDynamicPropertyHandler().get(AppDisConstants.YOURANT2_PKG_URL));
		url.append("&").append(Bean2Utils.toHttpParams(param));

		task.setParam(param);
		task.setDataFormat(DataFormat.DATA_FORMAT_KVP);
		task.setInvokeType(InvokeType.INVOKE_TYPE_GET);
		task.setHashCode(hashCode);
		task.setUrl(url.toString());
		
		return task;
	}
}
