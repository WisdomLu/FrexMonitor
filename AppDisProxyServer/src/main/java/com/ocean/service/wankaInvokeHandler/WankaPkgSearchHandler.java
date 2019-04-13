package com.ocean.service.wankaInvokeHandler;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.inveno.util.CollectionUtils;
import com.inveno.util.StringUtil;
import com.ocean.app.dis.proxy.thrift.entity.AppDisRecomReq;
import com.ocean.app.dis.proxy.thrift.entity.ExtData;
import com.ocean.app.dis.proxy.thrift.entity.InterParam;
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
import com.ocean.persist.app.dis.wanka.WankaAppReqBase;
import com.ocean.persist.app.dis.wanka.appasyn.WankaAsynResponse;
import com.ocean.persist.app.dis.wanka.pkgsearch.WankaPkgResponse;
import com.ocean.persist.app.dis.wanka.pkgsearch.WankaPkgSearchReq;
import com.ocean.persist.common.AppDisConstants;
import com.ocean.service.base.BaseHandler;
import com.ocean.service.wankaInvokeHandler.base.WankaInvokeHandlerFactory;
import com.ocean.service.wankaInvokeHandler.base.WankaMethodType;
import com.ocean.task.WankaPkgTask;

@Component(value="WankaPkgSearchHandler")
public class WankaPkgSearchHandler extends BaseHandler{
	private ThreadLocal<List<WankaApp>> wk_requestList=new ThreadLocal<List<WankaApp>> ();//没个线程维护一个列表
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
		WankaPkgSearchReq request=this.parsePkgParam(req);
		return invok(req,request);
		
	}
	public AppDisResponse invok(AppDisRecomReq req,WankaAppReqBase request) throws AppDisException {
		// TODO Auto-generated method stub
		return (WankaPkgResponse)this.invoke(request, WankaPkgResponse.class, req.getUserInfo().getUid(),String.valueOf(req.getAppSpaceInfo().getAdSpaceId()));
		
	}
	@Override
	public boolean validate(AppDisRecomReq req) {
		// TODO Auto-generated method stub
		return false;
	}
    public WankaPkgSearchReq parsePkgParam(AppDisRecomReq req)throws AppDisException{
    	if(CollectionUtils.isEmpty(req.getJoinParam())||req.getJoinParam().size()<3){
			throw new AppDisException(ErrorCode.PARAM_ERROR,"get join params empty!");

		}
    	WankaPkgSearchReq param=new WankaPkgSearchReq();
		param.setFrom_client("server");
		param.setApp_id(req.getJoinParam().get(0).getValue());
		param.setChannel_id(req.getJoinParam().get(1).getValue());
		
		param.set_package(getPkgName(req));

		param.setTimestamp(String.valueOf(System.currentTimeMillis()/1000));
		StringBuffer sb=new StringBuffer();
		sb.append("app_id=").append(param.getApp_id()).append("&channel_id=").append(param.getChannel_id())
		.append("&from_client=").append(param.getFrom_client()).append("&package=").append(param.get_package()).append("&timestamp=").append(param.getTimestamp())
		.append(req.getJoinParam().get(2).getValue());
		param.setSign(DigestUtils.md5Hex(sb.toString()).toLowerCase());
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
		
		WankaApp app=getWkAppInfo(req);
		   
    	if(app!=null){
    		return app.get_package();
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
			long threshold=SystemContext.getDynamicPropertyHandler().getLong(AppDisConstants.WANKA_TIME_OUT,5*60*1000);
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
	private WankaApp getWkAppInfo(AppDisRecomReq params) throws AppDisException{
		if(checkKey()){
			reqYrAppList(params);
			
		}else if(CollectionUtils.isEmpty(wk_requestList.get())){
			reqYrAppList(params);
			
		}

		{
			//随机取
			int rand=(int)(Math.random()*wk_requestList.get().size());
			WankaApp data=wk_requestList.get().get(rand);
			
			return data;
		}
	
	}

	private void reqYrAppList(AppDisRecomReq req) throws AppDisException{
	
		try {
			if(CollectionUtils.isNotEmpty(wk_requestList.get())){
				wk_requestList.remove();
			}//先清空列表，本应可以暂时先用着，但是对方时效要求比较高
			
			
			WankaAppasynHandler asynHandler=(WankaAppasynHandler)WankaInvokeHandlerFactory.getHandler(WankaMethodType.APP_LIST_ASYN_SEARCH);
			WankaAsynResponse resp=(WankaAsynResponse)asynHandler.invok(req);
			logger.info("WANKA_APPSTORE {} update key {} asyn app list :{}",req.getUserInfo().getUid(),updateKey.get(),JsonUtils.toJson(resp));
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
	private void putAppListToQueue(WankaAsynResponse resp) throws InterruptedException, AppDisException{
		if(resp==null||resp.getContent()==null||CollectionUtils.isEmpty(resp.getContent().getList())){
			throw new AppDisException(ErrorCode.INTER_ERROR,"DATA_EMPTY return error code:"+resp.getResult()+" msg:"+resp.getMsg());
			
		}
		if(resp.getResult()!=0){
			throw new AppDisException(ErrorCode.INTER_ERROR,"DATA_ERROR return error code:"+resp.getResult()+" msg:"+resp.getMsg());
		}
		
		List<WankaApp> dataL=resp.getContent().getList();
		if(CollectionUtils.isNotEmpty(dataL)){
			wk_requestList.set(dataL);
		}
		

	}
	@Override
	public AsynAbstractTask packageTask(Parameter params, String hashCode) {
		// TODO Auto-generated method stub
		WankaPkgTask task = new WankaPkgTask();
		WankaPkgSearchReq param = (WankaPkgSearchReq) params;
		
	
		StringBuilder url = new StringBuilder();
		url.append(SystemContext.getDynamicPropertyHandler().get(AppDisConstants.WANKA_APP_APPPKG_URL));
		url.append("?").append(Bean2Utils.toHttpParams(param));

		task.setParam(param);
		task.setDataFormat(DataFormat.DATA_FORMAT_KVP);
		task.setInvokeType(InvokeType.INVOKE_TYPE_GET);
		task.setHashCode(hashCode);
		task.setUrl(url.toString().replace("_package", "package"));
		
		return task;
	}
}
