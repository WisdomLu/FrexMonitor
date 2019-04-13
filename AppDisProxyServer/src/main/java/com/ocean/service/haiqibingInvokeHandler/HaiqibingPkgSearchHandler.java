package com.ocean.service.haiqibingInvokeHandler;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.ocean.core.common.system.ErrorCode;
import com.ocean.core.common.system.MyLogManager;
import com.ocean.core.common.system.SystemContext;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;
import com.ocean.core.common.threadpool.workthread.Task.DataFormat;
import com.ocean.core.common.threadpool.workthread.Task.InvokeType;
import com.ocean.persist.app.dis.AppDisException;
import com.ocean.persist.app.dis.AppDisResponse;
import com.ocean.persist.app.dis.haiqibing.appAsyn.HaiqibingAppasynApp;
import com.ocean.persist.app.dis.haiqibing.appAsyn.HaiqibingAppasynResp;
import com.ocean.persist.app.dis.haiqibing.pkgSearch.HaiqibingPkgSchReq;
import com.ocean.persist.app.dis.haiqibing.pkgSearch.HaiqibingPkgSchResp;
import com.ocean.persist.common.AppDisConstants;
import com.ocean.persist.common.AppDisErrorCode;
import com.ocean.service.base.BaseHandler;
import com.ocean.service.haiqibingInvokeHandler.base.HaiqibingInvokeHandlerFactory;
import com.ocean.service.haiqibingInvokeHandler.base.HaiqibingMethodType;
import com.ocean.task.HaiqibingPkgTask;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年5月10日 
      @version 1.0 
 */
@Component(value="HaiqibingPkgSearchHandler")
public class HaiqibingPkgSearchHandler   extends BaseHandler{
	private ThreadLocal<List<HaiqibingAppasynApp>> hqb_requestList=new ThreadLocal<List<HaiqibingAppasynApp>> ();
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
		HaiqibingPkgSchReq request=parseListSearchParam(req);
		return invok(req,request);
	}
	public AppDisResponse invok(AppDisRecomReq req,HaiqibingPkgSchReq hqbReq) throws AppDisException {

		return (HaiqibingPkgSchResp)this.invoke(hqbReq, HaiqibingPkgSchResp.class, req.getUserInfo().getUid(),String.valueOf(req.getAppSpaceInfo().getAdSpaceId()));
		
	}
	public HaiqibingPkgSchReq parseListSearchParam(AppDisRecomReq req) throws AppDisException{
		if(CollectionUtils.isEmpty(req.getJoinParam())||req.getJoinParam().size()<1){
			throw new AppDisException(ErrorCode.PARAM_ERROR,"get join params empty!");

		}
		UserInfo user=req.getUserInfo();
		DeviceInfo device=req.getDevice();
		HaiqibingPkgSchReq param =new HaiqibingPkgSchReq();
		param.setImei(device.getImei());
		param.setAndroidId(device.getAdid());
		param.setMacAddress(device.getMac());
		param.setManufacture(device.getBrandName());
		param.setMode(device.getPhonemodel());
		param.setChannel(req.getJoinParam().get(0).getValue());
		
		
		param.setPackageName(getPkgName(req));
		
		param.setIp(user.getClientIp());
		param.setSerialno(device.getSerialNo());
		param.setBrand(device.getBrandName());
		param.setCarrier(this.getOp(device.getMobile()));
		if(StringUtils.isNotEmpty(device.getDip())){
			param.setDip(Double.parseDouble(device.getDip()));
		}else{
			param.setDip(2.0);
		}
		
		param.setSw(device.getDeviceWidth());
		param.setSh(device.getDeviceHeight());
		param.setUserAgent(user.getUa());
		param.setOs(Integer.parseInt(device.getOsversion()));
		param.setOsv(device.getOsVerName());
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
		
		HaiqibingAppasynApp hqbApp=this.getAppInfo(req);
		if(hqbApp==null){
			throw new AppDisException(ErrorCode.PARAM_ERROR,"get target app pkgname empty!");
		}
		return this.getAppInfo(req).getPkg();
	}
	 private boolean checkKey(){
	    	if(StringUtils.isEmpty(updateKey.get())){
	    		return true;
	    	}
	    	SimpleDateFormat sdf=new SimpleDateFormat(strDateFormat);// 使用这个这个方法将变量改为strDateFormat= "yyyy-MM-dd-HH-mm-ss-SSS"，否则存在一定的误差; 
	    	try {
				long inter=new Date().getTime()-sdf.parse(updateKey.get()).getTime();
				long threshold=SystemContext.getDynamicPropertyHandler().getLong(AppDisConstants.HAIQIBING_TIME_OUT,5*60*1000);
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
		private HaiqibingAppasynApp getAppInfo(AppDisRecomReq params) throws AppDisException{
			//if(StringUtils.isEmpty(updateKey.get())||!updateKey.get().equals(getLocalKey())){//每分钟更新一次
				
			if(checkKey()){
				reqAppList(params);
				
			}else if(CollectionUtils.isEmpty(hqb_requestList.get())){
				reqAppList(params);
				
			}

			{
				//随机取
				int rand=(int)(Math.random()*hqb_requestList.get().size());
				HaiqibingAppasynApp data=hqb_requestList.get().get(rand);
				return data;
			}
		
		}

		private void reqAppList(AppDisRecomReq req) throws AppDisException{
		
			try {
				if(CollectionUtils.isNotEmpty(hqb_requestList.get())){
					hqb_requestList.remove();
				}//先清空列表，本应可以暂时先用着，但是对方时效要求比较高
				
				
				HaiqibingAppasynHandler asynHandler=(HaiqibingAppasynHandler)HaiqibingInvokeHandlerFactory.getHandler(HaiqibingMethodType.APP_LIST_ASYN_SEARCH);
				HaiqibingAppasynResp resp=(HaiqibingAppasynResp)asynHandler.invok(req);
				logger.info("HAIQIBING_APPSTORE {} update key {} asyn app list :{}",req.getUserInfo().getUid(),updateKey.get(),JsonUtils.toJson(resp));
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
		private void putAppListToQueue(HaiqibingAppasynResp resp) throws InterruptedException, AppDisException{
			if(resp==null||CollectionUtils.isEmpty(resp.getData())){
				throw new AppDisException(ErrorCode.INTER_ERROR,"DATA_EMPTY return error code:"+resp.getCode()+" msg:"+resp.getMsg());
				
			}
			if(resp.getCode()!=200){
				throw new AppDisException(ErrorCode.INTER_ERROR,"DATA_EMPTY return error code:"+resp.getCode()+" msg:"+resp.getMsg());
			}
			
			List<HaiqibingAppasynApp> dataL=resp.getData();
			if(CollectionUtils.isNotEmpty(dataL)){
				hqb_requestList.set(dataL);
			}
			

		}
	private String  getOp(String mobile){
		if(MOBILE_CMCC.equals(mobile)){
			return "46000";
		}else if(MOBILE_CUCC.equals(mobile)){
			return "46001";
		}else if(MOBILE_CTCC.equals(mobile)){
			return "46003";
		}
		return "46000";
	}
	@Override
	public boolean validate(AppDisRecomReq req) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public AsynAbstractTask packageTask(Parameter params, String hashCode) {
		// TODO Auto-generated method stub
		HaiqibingPkgTask task = new HaiqibingPkgTask();
		HaiqibingPkgSchReq param = (HaiqibingPkgSchReq) params;
		String url =SystemContext.getDynamicPropertyHandler().get(AppDisConstants.HAIQIBING_PKG_URL);

		
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		task.setParam(param);
		task.setDataFormat(DataFormat.DATA_FORMAT_JSON);
		task.setInvokeType(InvokeType.INVOKE_TYPE_POST);
		task.setHashCode(hashCode);
		task.setUrl(url);
		task.setHeaders(headers);
		return task;
	}
}
