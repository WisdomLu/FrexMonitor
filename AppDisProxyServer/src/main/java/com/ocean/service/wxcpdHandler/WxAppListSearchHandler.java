package com.ocean.service.wxcpdHandler;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inveno.util.CollectionUtils;
import com.ocean.app.dis.proxy.thrift.entity.AppDisRecomReq;
import com.ocean.app.dis.proxy.thrift.entity.DeviceInfo;
import com.ocean.app.dis.proxy.thrift.entity.UserInfo;
import com.ocean.core.common.JsonUtils;
import com.ocean.core.common.http.HttpClient;
import com.ocean.core.common.http.HttpInvokeException;
import com.ocean.core.common.system.SystemContext;
import com.ocean.persist.app.dis.AppDisException;
import com.ocean.persist.app.dis.AppDisResponse;
import com.ocean.persist.app.dis.wxcpd.WxAppListSearchPuller;
import com.ocean.persist.app.dis.wxcpd.WxAppParamData;
import com.ocean.persist.app.dis.wxcpd.WxAppPullParams;
import com.ocean.persist.app.dis.wxcpd.WxApplistParams;
import com.ocean.persist.app.dis.wxcpd.WxApplistResponse;
import com.ocean.service.baiduInvokeHandler.base.BaiduBaseHandler;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年5月10日 
      @version 1.0 
 */
@Component(value="WxAppListSearchHandler")
public class WxAppListSearchHandler  extends BaiduBaseHandler{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private static final String WANGXIANG_APPLIST_URL="wangxiang.applist.url";
	@Autowired
	private WxAppListSearchPuller puller;
	private ThreadLocal<List<WxAppParamData>> wx_requestList=new ThreadLocal<List<WxAppParamData>> ();
	@Override
	public AppDisResponse invok(AppDisRecomReq req) throws AppDisException {
		// TODO Auto-generated method stub
		WxAppPullParams request=parseAppListSearchParam(req);
		return puller.api(request,req.getUserInfo().getUid(),String.valueOf(req.getAppSpaceInfo().getAdSpaceId()),req.getJoinSource().toString());

	}
	public WxAppPullParams parseAppListSearchParam(AppDisRecomReq req) throws AppDisException{
		WxAppParamData app=this.getWxAppInfo(req);
		if(app==null){
			return null;
		}
	
		// 参数转换
		WxAppPullParams params = parseParams(req,app);
		params.setData(app);//WxAppParamData>WxAppPullParams>WxAppResponse   透传
		return params;
	}
	private WxAppParamData getWxAppInfo(AppDisRecomReq params) throws AppDisException{
		if(CollectionUtils.isEmpty(wx_requestList.get())){
			reqWxAppList(params);
		}
		{
			Iterator<WxAppParamData> it=wx_requestList.get().iterator();
			WxAppParamData data=null;
			if(it.hasNext()){
				data=it.next();
				it.remove();
			}
			return data;
		}
	
	}
	private void reqWxAppList(AppDisRecomReq req) throws AppDisException{

		String url=SystemContext.getDynamicPropertyHandler().get(WANGXIANG_APPLIST_URL);
		Map<String, String> headers = new HashMap<String, String>(2);
		headers.put("Content-Type", "application/json");
		WxApplistParams param =new WxApplistParams();
		if(req.getJoinParam().size()<3){
			throw new AppDisException("channel_id  or key and sign is empty!");
		}
		param.setChannel_id(req.getJoinParam().get(0).getValue());
		param.setToken(DigestUtils.md5Hex(req.getJoinParam().get(1).getValue()+req.getJoinParam().get(2).getValue()));
		try {
			String result=HttpClient.getInstance().post(url, JsonUtils.toJson(param), headers);
			
			// TODO Auto-generated method stub
				
			if(StringUtils.isNotEmpty(result)){
				
				WxApplistResponse resp=JsonUtils.toBean(result, WxApplistResponse.class);
				
				putAppListToQueue(resp,param.getChannel_id(),param.getToken());
			}
	
		} catch (HttpInvokeException e) {
			// TODO Auto-generated catch block

			throw  new AppDisException("wangxiang cpd get app list from queue error(HttpInvokeException) !"+e.getMessage());
		}catch (Throwable e) {
			throw  new AppDisException("wangxiang cpd get app list from queue error(Throwable) !"+e.getMessage());
		}
	}
	private void putAppListToQueue(WxApplistResponse resp,String channelId,String token) throws InterruptedException{
		if(resp==null||CollectionUtils.isEmpty(resp.getData())){
			return;
		}
		logger.info("wangxiang return applist request info:{}",JsonUtils.toJson(resp));
		List<WxAppParamData> dataL=resp.getData();
		for(WxAppParamData data:dataL){
			data.setChannelId(channelId);
			data.setToken(token);
		}
		if(CollectionUtils.isNotEmpty(dataL)){
			wx_requestList.remove();
			wx_requestList.set(dataL);
		}
		

	}
	private WxAppPullParams parseParams(AppDisRecomReq adreq, 
			WxAppParamData app) throws AppDisException {
		UserInfo user=adreq.getUserInfo();
		DeviceInfo device=adreq.getDevice();
		WxAppPullParams param =new WxAppPullParams();
		param.setChannel_id(app.getChannelId());
		param.setChannel_name("掌酷软件有限公司");
		param.setToken(app.getToken());
		param.setRequest_id(UUID.randomUUID().toString().replaceAll("-", ""));
		param.setPkg(app.getF_packname());
		param.setMd5(app.getF_apkMD5());
		param.setImei(device.getImei());
		param.setIp(user.getClientIp());
		param.setUa(user.getUa());
		param.setAndroid_id(device.getAdid());
		param.setMac(device.getMac());
		param.setModel(device.getPhonemodel());
		param.setAdrv(this.convOSVersion(device.getOsversion()));
		param.setNet(getNetwork(user.getNet()));
		
		
		
		return param;
	}
	private  String getNetwork(String net){	
		if(NETWORK_WIFI.equals(net)){
			return "100";
		}else if(NETWORK_2G.equals(net)){
			return "2";
		}
		else if(NETWORK_3G.equals(net)){
			return "3";
		}
		else if(NETWORK_4G.equals(net)){
			return "4";
		}
		return "0";
	}
	
}
