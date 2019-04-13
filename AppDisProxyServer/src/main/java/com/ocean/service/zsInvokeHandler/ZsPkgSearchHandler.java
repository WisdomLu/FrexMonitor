package com.ocean.service.zsInvokeHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.inveno.util.CollectionUtils;
import com.inveno.util.StringUtil;
import com.ocean.app.dis.proxy.thrift.entity.AppDisRecomReq;
import com.ocean.app.dis.proxy.thrift.entity.AppInfo;
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
import com.ocean.persist.app.dis.zs.appasyn.ZsAppasynApp;
import com.ocean.persist.app.dis.zs.appasyn.ZsAppasynReply;
import com.ocean.persist.app.dis.zs.pkgsearch.ZsPkgSearchReply;
import com.ocean.persist.app.dis.zs.pkgsearch.ZsPkgSearchRequest;
import com.ocean.persist.common.AppDisConstants;
import com.ocean.persist.common.AppDisErrorCode;
import com.ocean.service.zsInvokeHandler.base.ZsBaseHandler;
import com.ocean.service.zsInvokeHandler.base.ZsInvokeHandlerFactory;
import com.ocean.service.zsInvokeHandler.base.ZsMethodType;
import com.ocean.task.ZsPkgSearchTask;

@Component(value="ZsT2PkgSearchHandler")
public class ZsPkgSearchHandler extends ZsBaseHandler{
	private ThreadLocal<List<ZsAppasynApp>> zs2_requestList=new ThreadLocal<List<ZsAppasynApp>> ();
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
		ZsPkgSearchRequest request=this.parsePkgParam(req);
		return invok(req,request);
		
	}
	public AppDisResponse invok(AppDisRecomReq req,ZsPkgSearchRequest request) throws AppDisException {
		return (ZsPkgSearchReply)this.invoke(request, ZsPkgSearchReply.class, req.getUserInfo().getUid(),String.valueOf(req.getAppSpaceInfo().getAdSpaceId()));
		
	}
/*	@Override
	public boolean validate(AppDisRecomReq req) {
		return false;
	}*/
	
    public ZsPkgSearchRequest parsePkgParam(AppDisRecomReq req)throws AppDisException{
    	ZsPkgSearchRequest request = new ZsPkgSearchRequest();
		if (CollectionUtils.isEmpty(req.getJoinParam())||req.getJoinParam().size()<2) {
			throw new AppDisException(AppDisErrorCode.RC_PARAM_ERROR,req.getJoinSource().name()
					+ ": please check third plat applying paramters is  empty or not");
		}
		
		UserInfo user = req.getUserInfo();
		DeviceInfo device = req.getDevice();
		AppInfo app=req.getAppInfo();

		if (!validate(req)) {
			throw new AppDisException(AppDisErrorCode.RC_PARAM_ERROR,
					"parameter is Error!");
		}
		String id=(req.getJoinParam().get(0).getValue());
		String key=(req.getJoinParam().get(1).getValue());
		long timestamp=System.currentTimeMillis();
		
		request.setId(id);
		request.setTimestamp(System.currentTimeMillis());
		
		StringBuilder str=new StringBuilder();
		str.append(id).append(key).append(timestamp);
		request.setToken(((DigestUtils.md5Hex(str.toString()))));
		
		request.setPackage_names(getPkgName(req));
		request.setIp(user.getClientIp());
		
		String cat = zSMobiles.get(device.getMobile());
		if (StringUtils.isNotEmpty(cat)) {
			request.setCarrior(Integer.parseInt(cat));
		} else {
			request.setCarrior(Integer.parseInt("0"));
		}
	
		Integer net = zSCtype.get(user.getNet());
		request.setNetwork_type((net == null ? 0 :net));
		
		request.setImei(device.getImei());
		request.setMac(converMac(device.getMac()));
		request.setImsi(device.getImsi());
		if (StringUtils.isNotEmpty(device.getSerialNo())) {
			request.setSerial_no(device.getSerialNo());
		} else {
			request.setSerial_no(device.getImsi());
		}

		request.setAndroid_id(device.getAdid());
		request.setVendor(device.getBrandName());//手机产商 ok
		request.setBrand(device.getBrandName());//手机品牌?
		request.setModel(device.getPhonemodel());//型号 ok
		request.setDevice_type(1);//?设备类型---0:未知; 1:手机; 2:平板; 3:智能TV
		request.setDpi(device.getDpi());
		request.setScreen_size(device.getDeviceHeight()+"x"+device.getDeviceWidth());
		request.setOrientation(1);
		request.setUa(user.getUa());
		request.setVersion(device.getOsVerName());//?操作系统版本号
		request.setVersion_int(device.getOsversion());//?操作系统版本号, 采集自Build.VERSION.SDK_INT
		request.setUuid(UUID.randomUUID().toString());
		request.setApp_id(app.getPkgName());
		request.setApp_name(app.getAppName());
		request.setApp_version(app.getVersionCode());
		request.setGeo_type(1);//?1;全球卫星定位系统坐标系2;国家测绘局坐标系3;百度坐标系 optional
		request.setGeo_latitude(Float.valueOf(user.getLat()));
		request.setGeo_longitude(Float.valueOf(user.getLon()));
		request.setGeo_time(System.currentTimeMillis());
//		request.setGeo_location_accuracy(1);//?经纬度精度半径，单位为米 optional
		request.setSupport_redirect(0);//?是否支持302重定向0：否，1：是
		return request;
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
		
    	ZsAppasynApp app=getZsAppInfo(req);
    	if(app!=null){
    		return app.getPackage_name();
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
			long threshold=SystemContext.getDynamicPropertyHandler().getLong(AppDisConstants.ZS_TIME_OUT,5*60*1000);
			if(inter>=threshold){//一段时间更新一次
				return true;
			}
		} catch (ParseException e) {
			//e.printStackTrace();
			return true;
		}
    	return false;
    }
    
	private ZsAppasynApp getZsAppInfo(AppDisRecomReq params) throws AppDisException{
		//if(StringUtils.isEmpty(updateKey.get())||!updateKey.get().equals(getLocalKey())){//每分钟更新一次
		if(checkKey()){
			reqZsAppList(params);
		}else if(CollectionUtils.isEmpty(zs2_requestList.get())){
			reqZsAppList(params);
		}

		{
			//随机取
			int rand=(int)(Math.random()*zs2_requestList.get().size());
			ZsAppasynApp data=zs2_requestList.get().get(rand);
			
/*			Iterator<YouranT2AppasynApp> it=zs2_requestList.get().iterator();
			YouranT2AppasynApp data=null;
			if(it.hasNext()){
				data=it.next();
				it.remove();
			}*/
			return data;
		}
	}

	private void reqZsAppList(AppDisRecomReq req) throws AppDisException{
		try {
			if(CollectionUtils.isNotEmpty(zs2_requestList.get())){
				zs2_requestList.remove();
			}//先清空列表，本应可以暂时先用着，但是对方时效要求比较高
			
			ZsAppasynHandler asynHandler=(ZsAppasynHandler)ZsInvokeHandlerFactory.getHandler(ZsMethodType.APP_LIST_ASYN_SEARCH);
			ZsAppasynReply resp=(ZsAppasynReply)asynHandler.invok(req);
			logger.info("Zs_TYPE2_APPSTORE {} update key {} asyn app list :{}",req.getUserInfo().getUid(),updateKey.get(),JsonUtils.toJson(resp));
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
	private void putAppListToQueue(ZsAppasynReply resp) throws InterruptedException, AppDisException{
		/*if(resp==null||CollectionUtils.isEmpty(resp.getInfo())){
			throw new AppDisException(ErrorCode.INTER_ERROR,"DATA_EMPTY return error code:"+resp.getRetCode()+" msg:"+resp.getRetMessage());
			
		}
		if(resp.getRetCode()!=0){
			throw new AppDisException(ErrorCode.INTER_ERROR,"return error code:"+resp.getRetCode()+" msg:"+resp.getRetMessage());
		}*/
		if(resp==null || resp.getList()==null || resp.getList().size()==0){
			throw new AppDisException(ErrorCode.INTER_ERROR,"DATA_EMPTY return error code: resp is null");
		}
		
		List<ZsAppasynApp> dataL=resp.getList();
		if(CollectionUtils.isNotEmpty(dataL)){
			zs2_requestList.set(dataL);
		}
	}
	
	@Override
	public AsynAbstractTask packageTask(Parameter params, String hashCode) {
		ZsPkgSearchTask task = new ZsPkgSearchTask();
		ZsPkgSearchRequest param = (ZsPkgSearchRequest) params;
	
		StringBuilder url = new StringBuilder();
		url.append(SystemContext.getDynamicPropertyHandler().get(AppDisConstants.ZS_PGK_URL));
		url.append("?").append(Bean2Utils.toHttpParams(param));

		task.setParam(param);
		task.setDataFormat(DataFormat.DATA_FORMAT_KVP);
		task.setInvokeType(InvokeType.INVOKE_TYPE_GET);
		task.setHashCode(hashCode);
		task.setUrl(url.toString());
		return task;
	}
}
