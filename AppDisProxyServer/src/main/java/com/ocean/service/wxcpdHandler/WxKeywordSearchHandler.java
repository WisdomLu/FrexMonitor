package com.ocean.service.wxcpdHandler;

import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inveno.util.CollectionUtils;
import com.ocean.app.dis.proxy.thrift.entity.AppDisRecomReq;
import com.ocean.app.dis.proxy.thrift.entity.DeviceInfo;
import com.ocean.app.dis.proxy.thrift.entity.UserInfo;
import com.ocean.core.common.system.ErrorCode;
import com.ocean.persist.app.dis.AppDisException;
import com.ocean.persist.app.dis.AppDisResponse;
import com.ocean.persist.app.dis.wxcpd.keywordSearch.KeywordSearchWxPuller;
import com.ocean.persist.app.dis.wxcpd.keywordSearch.KeywordSearchWxReq;
import com.ocean.service.baiduInvokeHandler.base.BaiduBaseHandler;
import com.ocean.service.base.BaseHandler;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年5月10日 
      @version 1.0 
 */
@Component(value="WxKeywordSearchHandler")
public class WxKeywordSearchHandler  extends BaseHandler{
	@Autowired
	private KeywordSearchWxPuller puller;
	@Override
	public AppDisResponse invok(AppDisRecomReq req) throws AppDisException {
		// TODO Auto-generated method stub
		KeywordSearchWxReq request=parseKeywordSearchParam(req);
		return puller.api(request,req.getUserInfo().getUid(),String.valueOf(req.getAppSpaceInfo().getAdSpaceId()),req.getJoinSource().toString());

	}
	public AppDisResponse invok(AppDisRecomReq req,KeywordSearchWxReq wxReq) throws AppDisException {
		// TODO Auto-generated method stub
		//KeywordSearchWxReq request=parseKeywordSearchParam(req);
		return puller.api(wxReq,req.getUserInfo().getUid(),String.valueOf(req.getAppSpaceInfo().getAdSpaceId()),req.getJoinSource().toString());

	}
	public KeywordSearchWxReq parseKeywordSearchParam(AppDisRecomReq req) throws AppDisException{
		if(CollectionUtils.isEmpty(req.getJoinParam())||req.getJoinParam().size()<3){
			throw new AppDisException(ErrorCode.PARAM_ERROR,"get join params empty!");

		}
		UserInfo user=req.getUserInfo();
		DeviceInfo device=req.getDevice();
		KeywordSearchWxReq param =new KeywordSearchWxReq();
		param.setChannel_id(req.getJoinParam().get(0).getValue());
		param.setKeyword(req.getInterParam().getKeyWord());
		param.setToken(DigestUtils.md5Hex(req.getJoinParam().get(1).getValue()+req.getJoinParam().get(2).getValue()));
		param.setChannel_name("掌酷软件有限公司");
		param.setCuid(DigestUtils.md5Hex(device.getImei()+device.getAdid()+UUID.randomUUID().toString()).toUpperCase());
		param.setOvr(this.convOSVersion(device.getOsVerName()));
		param.setOs_level(device.getOsversion());
		param.setDevice(device.getBrandName());
		param.setSvr(req.getAppInfo().getVersionCode());
		param.setSvr_name(req.getAppInfo().getVersionName());
		param.setNet_type(getNetwork(user.getNet()));
		param.setResolution(device.getDeviceWidth()+"_"+device.getDeviceHeight());
		param.setInfo_ma(device.getMac());
		param.setInfo_ms(device.getImsi());
		param.setClient_id(device.getImei());
		param.setDpi(device.getDpi());
		param.setClient_ip(user.getClientIp());
		param.setMcc("460");
		param.setMno("SID");
		
		param.setInfo_la("4527");
		param.setInfo_ci("28883");
		param.setOs_id(device.getAdid());
		param.setBssid("58:20:B1:66:1B:B0");
		param.setNonce(String.valueOf(System.currentTimeMillis()/1000));
		param.setPkg(req.getAppInfo().getPkgName());
		param.setUa(user.getUa());
		
		
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
	@Override
	public boolean validate(AppDisRecomReq req) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
