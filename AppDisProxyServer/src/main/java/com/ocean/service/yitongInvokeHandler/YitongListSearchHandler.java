package com.ocean.service.yitongInvokeHandler;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inveno.util.CollectionUtils;
import com.ocean.app.dis.proxy.thrift.entity.AppDisRecomReq;
import com.ocean.app.dis.proxy.thrift.entity.DeviceInfo;
import com.ocean.app.dis.proxy.thrift.entity.UserInfo;
import com.ocean.core.common.JsonUtils;
import com.ocean.core.common.http.Bean2Utils;
import com.ocean.core.common.system.ErrorCode;
import com.ocean.core.common.system.MyLogManager;
import com.ocean.persist.app.dis.AppDisException;
import com.ocean.persist.app.dis.AppDisResponse;
import com.ocean.persist.app.dis.yitong.applist.ListSearchYitongPuller;
import com.ocean.persist.app.dis.yitong.applist.ListSearchYitongPuller_v2;
import com.ocean.persist.app.dis.yitong.applist.ListSearchYitongReq;
import com.ocean.persist.app.dis.yitong.applist.ListSearchYitongSecParams;
import com.ocean.service.base.BaseHandler;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年5月10日 
      @version 1.0 
 */
@Component(value="YitongListSearchHandler")
public class YitongListSearchHandler   extends BaseHandler{
	@Autowired
	private ListSearchYitongPuller puller;
	
	@Autowired
	private ListSearchYitongPuller_v2 puller_v2;
	private  final Logger logger = MyLogManager.getLogger();
	@Override
	public AppDisResponse invok(AppDisRecomReq req) throws AppDisException {
		// TODO Auto-generated method stub
		ListSearchYitongReq request=parseListSearchParam(req);
		
		return puller.api(request,req.getUserInfo().getUid(),String.valueOf(req.getAppSpaceInfo().getAdSpaceId()),req.getJoinSource().toString());

	}
	public AppDisResponse invok_v2(AppDisRecomReq req) throws AppDisException {
		// TODO Auto-generated method stub
		ListSearchYitongReq request=parseListSearchParam(req);
		
		return puller_v2.api(request,req.getUserInfo().getUid(),String.valueOf(req.getAppSpaceInfo().getAdSpaceId()),req.getJoinSource().toString());

	}
	public AppDisResponse invok(AppDisRecomReq proxyReq,ListSearchYitongReq req) throws AppDisException {
		// TODO Auto-generated method stub
		return puller.api(req,proxyReq.getUserInfo().getUid(),String.valueOf(proxyReq.getAppSpaceInfo().getAdSpaceId()),proxyReq.getJoinSource().toString());

	}
	public AppDisResponse invok_v2(AppDisRecomReq proxyReq,ListSearchYitongReq req) throws AppDisException {
		// TODO Auto-generated method stub
		return puller_v2.api(req,proxyReq.getUserInfo().getUid(),String.valueOf(proxyReq.getAppSpaceInfo().getAdSpaceId()),proxyReq.getJoinSource().toString());

	}
	public ListSearchYitongReq parseListSearchParam(AppDisRecomReq req) throws AppDisException{
		if(CollectionUtils.isEmpty(req.getJoinParam())||req.getJoinParam().size()<3){
			throw new AppDisException(ErrorCode.PARAM_ERROR,"get join params empty!");

		}
		UserInfo user=req.getUserInfo();
		ListSearchYitongReq param =new ListSearchYitongReq();
		param.setDeveloperid(req.getJoinParam().get(0).getValue());
		param.setAppid(req.getJoinParam().get(1).getValue());
		param.setItemspaceid(req.getJoinParam().get(2).getValue());
		
		ListSearchYitongSecParams secParam=this.getSecParam(req);
		logger.info("YITONG_APPSTORE:GET_APP_LIST secParam:{}", JsonUtils.toJson(secParam));
		String secParamStr=Bean2Utils.toHttpParams(secParam);
		try {
			param.setParams(URLEncoder.encode(Base64.encodeBase64String(secParamStr.getBytes(CHARSET_CODER)) ,CHARSET_CODER));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		param.setAd_type(1);
		param.setPlatform("android");
		param.setLog_id(DigestUtils.md5Hex(secParam.getImei()+System.currentTimeMillis()));
		if(req.isSetPage()){
			param.setPage(req.getPage().getFrom()==0?1:req.getPage().getFrom());
			param.setPage_size(req.getPage().getPageSize());
			param.setCount(req.getPage().getPageSize());
		}else{
			param.setPage(1);
			param.setPage_size(pageSize);
			param.setCount(pageSize);
		}
		param.setUser_ip(user.getClientIp());

	
		return param;
	}
	public ListSearchYitongSecParams getSecParam(AppDisRecomReq req){
		UserInfo user=req.getUserInfo();
		DeviceInfo device=req.getDevice();
		ListSearchYitongSecParams secParam=new ListSearchYitongSecParams();
		secParam.setTimestamp(System.currentTimeMillis());
		secParam.setAction_type(1);
		secParam.setAction("show_list");
		secParam.setNetwork(getNetwork(user.getNet()));
		secParam.setImei(device.getImei());
		secParam.setImsi(device.getImsi());
		if(req.isSetAppInfo()){
		    secParam.setProduct_version(req.getAppInfo().getVersionName());
		}
		secParam.setOs_version(device.getOsVerName());
		secParam.setBrand(device.getBrandName());
		secParam.setModel(device.getPhonemodel());

		secParam.setResolution(device.getDeviceHeight()+"x"+device.getDeviceWidth());
		secParam.setMac(device.getMac());
		return secParam;
	}
	private  String getNetwork(String net){	
		if(NETWORK_WIFI.equals(net)){
			return "wf";
		}else if(NETWORK_2G.equals(net)){
			return "2g";
		}
		else if(NETWORK_3G.equals(net)){
			return "3g";
		}
		else if(NETWORK_4G.equals(net)){
			return "4g";
		}
		return "wf";
	}
	@Override
	public boolean validate(AppDisRecomReq req) {
		// TODO Auto-generated method stub
		return false;
	}
	public ListSearchYitongPuller getPuller() {
		return puller;
	}
	public void setPuller(ListSearchYitongPuller puller) {
		this.puller = puller;
	}
	public ListSearchYitongPuller_v2 getPuller_v2() {
		return puller_v2;
	}
	public void setPuller_v2(ListSearchYitongPuller_v2 puller_v2) {
		this.puller_v2 = puller_v2;
	}
	
}
