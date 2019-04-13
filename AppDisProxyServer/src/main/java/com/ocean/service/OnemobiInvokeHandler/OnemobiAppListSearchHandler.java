package com.ocean.service.OnemobiInvokeHandler;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ocean.app.dis.proxy.thrift.entity.AppDisRecomReq;
import com.ocean.app.dis.proxy.thrift.entity.DeviceInfo;
import com.ocean.app.dis.proxy.thrift.entity.ExtData;
import com.ocean.app.dis.proxy.thrift.entity.UserInfo;
import com.ocean.persist.app.dis.AppDisException;
import com.ocean.persist.app.dis.AppDisResponse;
import com.ocean.persist.app.dis.onemobi.OnemobReqParams;
import com.ocean.persist.app.dis.onemobi.OnemobiAppListSearchPuller;
import com.ocean.service.baiduInvokeHandler.base.BaiduBaseHandler;
import com.ocean.service.base.BaseHandler;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年10月10日 
      @version 1.0 
 */
@Component(value="OnemobiAppListSearchHandler")
public class OnemobiAppListSearchHandler  extends BaseHandler{
	@Autowired
	private OnemobiAppListSearchPuller puller;

	@Override
	public AppDisResponse invok(AppDisRecomReq req) throws AppDisException {
		// TODO Auto-generated method stub
		OnemobReqParams request=parseAppListSearchParam(req);
		return puller.api(request,req.getUserInfo().getUid(),String.valueOf(req.getAppSpaceInfo().getAdSpaceId()),req.getJoinSource().toString());

	}
	
	public OnemobReqParams parseAppListSearchParam(AppDisRecomReq req) throws AppDisException{
		DeviceInfo device = req.getDevice();
		UserInfo  userInfo = req.getUserInfo();
		if(req.getAppInfo()==null||StringUtils.isEmpty(req.getAppInfo().getPkgName())){
			throw new AppDisException("pkg name is empty!");
		}
		OnemobReqParams params = new OnemobReqParams();

		for(int i=0;i<req.getJoinParam().size();i++){
			ExtData ext=req.getJoinParam().get(i);
			if(i==0){
				params.setCp(ext.getValue());
			}/*else if(i==1){
				//request.setCh(ext.getValue());
				params.setAn(ext.getValue());//应用包名
			}*/
		}
		
		params.setAn(req.getAppInfo().getPkgName());//应用包名
		params.setPan("appm212om");

		String adid = device.getAdid();
		if(StringUtils.isEmpty(adid)){
			throw new AppDisException("andrid id is empty!");
		
		}
		params.setAid(adid);
		
		params.setImei(device.getImei());
		
		String imsi = device.getImsi(); 
		if(StringUtils.isEmpty(imsi)){
			throw new AppDisException("imsi id is empty!");
		}
		params.setImsi(imsi);
		
		
		params.setMac(this.converMac(device.getMac()));
		
		params.setRel(this.convOSVersion(device.getOsversion()));
		params.setBrnd(device.getBrandName());
		try {
			params.setMdl(URLEncoder.encode(device.getPhonemodel(), "UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String dm = device.getDeviceWidth() + "x" + device.getDeviceHeight();
		params.setDm(dm);
		
		params.setClient_ip(userInfo.getClientIp());
		try {
			params.setClient_ua(URLEncoder.encode(userInfo.getUa(),"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			throw new AppDisException("ua url encode error"+e.getMessage());
		}
		
		
		params.setNt(userInfo.getNet());
        if(StringUtils.isEmpty(userInfo.getNet())){
        	params.setNt("0");
        }else{
        	params.setNt(userInfo.getNet().toLowerCase());
        }
        
        
        params.setType("list1");
	
		return params;
	}

	public OnemobiAppListSearchPuller getPuller() {
		return puller;
	}

	public void setPuller(OnemobiAppListSearchPuller puller) {
		this.puller = puller;
	}

	@Override
	public boolean validate(AppDisRecomReq req) {
		// TODO Auto-generated method stub
		return false;
	}

}
