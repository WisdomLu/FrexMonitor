package com.ocean.server;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.thrift.TException;



/*import com.dianping.cat.Cat;
import com.dianping.cat.message.Event;
import com.dianping.cat.message.Transaction;*/
import com.inveno.util.CollectionUtils;
import com.ocean.app.dis.proxy.thrift.entity.AppDisContent;
import com.ocean.app.dis.proxy.thrift.entity.AppDisRecomReply;
import com.ocean.app.dis.proxy.thrift.entity.AppDisRecomReq;
import com.ocean.app.dis.proxy.thrift.entity.AppSpaceInfo;
import com.ocean.app.dis.proxy.thrift.entity.DeviceInfo;
import com.ocean.app.dis.proxy.thrift.entity.ErrorCode;
import com.ocean.app.dis.proxy.thrift.entity.UserInfo;
import com.ocean.bid.service.AppDisBidService;
import com.ocean.core.common.system.MyLogManager;
import com.ocean.core.common.system.SystemContext;
import com.ocean.handler.IdGenerator;
import com.ocean.handler.base.AbstractAppDisSupplier;
import com.ocean.handler.base.BaseAppDisSupplier;
import com.ocean.persist.app.dis.AppDisException;
import com.ocean.persist.common.PersistConstants;
import com.ocean.persist.common.ProxyConstants;
/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年5月12日 
      @version 1.0 
 */
public class ProxyInvoker implements com.ocean.app.dis.proxy.thrift.entity.AppDisRecommend.Iface{
	private  final Logger logger = MyLogManager.getLogger();
	public  final Logger monitorLogger = MyLogManager.getMonitor();
	public void ping() throws TException {
		// TODO Auto-generated method stub
		
	}

	public AppDisRecomReply search(AppDisRecomReq req) throws TException {
		// TODO Auto-generated method stub
		int isLogOpen=SystemContext.getDynamicPropertyHandler().getInt(PersistConstants.LOG_IS_ALL_WRITE,0);
		AppSpaceInfo attri = req.getAppSpaceInfo();
		
		UserInfo userInfo=req.getUserInfo();		
		String rawUid=userInfo.getUid();
		
		userInfo.setUid(userInfo.getUid()+System.currentTimeMillis());
		String source=req.getJoinSource().name();
		
		int empty=0;
		long ts = System.currentTimeMillis();
		AppDisRecomReply recomReply=null;
		try {
			logger.info("app dis REQUEST_PARAM,source:{},interface:{},ad space id:{},uid:{},req info:{}",source,req.getInterType().name(),attri.getAdSpaceId(),userInfo.getUid(),req.toString());
			recomReply = invoke(req);
			// 没有广告
			if(recomReply == null){
				
				recomReply= noApp(req);
				logger.error("app dis REPLY,source:{},interface:{},ad space id:{},uid:{},reply Empty:{}",source,req.getInterType().name(),attri.getAdSpaceId(),userInfo.getUid(),recomReply.toString());
				empty=1;
				
			}else{
				String content=isLogOpen==1?recomReply.toString():PersistConstants.OMIT_CONTENT;
				logger.info("app dis REPLY,source:{},interface:{},ad space id:{},uid:{},reply info:{}",source,req.getInterType().name(),attri.getAdSpaceId(),userInfo.getUid(),content);
				
			}
			
		}
		catch (AppDisException e) {
			recomReply = new AppDisRecomReply();
			ErrorCode errorCode=ErrorCode.findByValue(e.getCode());
			recomReply.setErrorCode(errorCode==null?ErrorCode.RC_INTER_ERROR:errorCode);
			recomReply.setErrorMsg(e.getMsg());
			logger.error("app dis REPLY_ERROR(AppDisException),source:{},interface:{},ad space id:{},uid:{},error Code:{},error Msg:{},reply Exception:{}" ,source,req.getInterType().name(),attri.getAdSpaceId(),userInfo.getUid(),e.getCode(), e.getMsg(),recomReply.toString());
			empty=3;
		}catch(Throwable e){
			recomReply = new AppDisRecomReply();
			
			recomReply.setErrorCode(ErrorCode.RC_INTER_ERROR);
			recomReply.setErrorMsg(e.getMessage());
			logger.error("app dis REPLY_ERROR(Exception),source:{},interface:{},ad space id:{},uid:{},error Msg:{},reply Exception:{}" ,source,req.getInterType().name(),attri.getAdSpaceId(),userInfo.getUid(),e.getMessage(),recomReply.toString(),e);
			
			empty=3;
		}finally{
			long ls = System.currentTimeMillis();
			int timeOut=SystemContext.getDynamicPropertyHandler().getInt(PersistConstants.HTTP_TIME_OUT, 1000);
			if(ls - ts>=timeOut){
				empty=2;
			}
			logger.info("app dis TIME_CONSUMING source:{},interface:{},ad space id:{},uid:{},request time cost:{} ms",source,req.getInterType().name(),attri.getAdSpaceId(),userInfo.getUid(),ls - ts);
			monitorLogger.info("app|proxy|{}|{}|{}|{}|{}",attri.getAdSpaceId(),"",rawUid,empty,ls - ts);
			
		}
		return recomReply;
	}
	private AppDisRecomReply invoke(AppDisRecomReq req)throws AppDisException{
		UserInfo userInfo=req.getUserInfo();
		DeviceInfo device=req.getDevice();
		// 操作系统
		String os = device.getOs();
		if(StringUtils.isEmpty(os)){
			os = BaseAppDisSupplier.OS_ANDROID;
			device.setOs(os);
		}
		// 设备宽高
		if(device.getDeviceWidth() == 0){
			device.setDeviceWidth(BaseAppDisSupplier.PARAM_DEFAULT_DEVICE_WIDTH);
		}
		if(device.getDeviceHeight() == 0){
			device.setDeviceHeight(BaseAppDisSupplier.PARAM_DEFAULT_DEVICE_HEIGHT);
		}
		// aaid
		if(StringUtils.isEmpty(device.getAaid())){
			device.setAaid(StringUtils.EMPTY);
		}
		// adid
		if(StringUtils.isEmpty(device.getAdid())){
			device.setAdid(device.getImei());
		}
		// 厂商
		if(StringUtils.isEmpty(device.getBrandName())){
			if(BaseAppDisSupplier.OS_IOS.equals(os)){
				device.setBrandName(BaseAppDisSupplier.PARAM_DEFAULT_BRAND_APPLE);
			}
			else{
				device.setBrandName(BaseAppDisSupplier.PARAM_DEFAULT_BRAND_ANDROID);
			}
		}
		// ip
		userInfo.setClientIp(userInfo.getClientIp().trim());

		// imsi
		if(StringUtils.isEmpty(device.getMac())){
			device.setMac(device.getImei());
		}
		AppDisBidService adBidService=(AppDisBidService)SystemContext.getServiceHandler().getService(AppDisBidService.class);
		AbstractAppDisSupplier  proxy=adBidService.getAdSupplier(req);
		// 处理结果
		AppDisRecomReply recomReply = proxy.invoke(req);
		if(recomReply == null){
			return null;
		}
		
		List<AppDisContent> contents = recomReply.getAppDisContent();
		if(CollectionUtils.isEmpty(contents)){
			return null;
		}
		for(AppDisContent content:contents){
			
			content.setJoinSource(req.getJoinSource());
			//广告位id设置
			content.setAdId(IdGenerator.genThirdDspId(SystemContext.getDynamicPropertyHandler().getInt(ProxyConstants.PROXY_SERVER_NODE_INDEX,100)));
			content.setJoinSource(req.getJoinSource());
		}

		return recomReply;
	}
	private AppDisRecomReply noApp(AppDisRecomReq req){
	    //business code
		AppDisRecomReply recomReply = new AppDisRecomReply();
		
		recomReply.setErrorCode(com.ocean.app.dis.proxy.thrift.entity.ErrorCode.RC_SUCCESS);
		recomReply.setErrorMsg("return app list is empty!");
		return recomReply;

	}

}
