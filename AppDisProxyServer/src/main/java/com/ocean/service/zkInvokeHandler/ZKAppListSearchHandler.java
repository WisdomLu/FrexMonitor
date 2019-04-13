package com.ocean.service.zkInvokeHandler;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inveno.util.CollectionUtils;
import com.ocean.app.dis.proxy.thrift.entity.AppDisRecomReq;
import com.ocean.app.dis.proxy.thrift.entity.AppInfo;
import com.ocean.app.dis.proxy.thrift.entity.AppSpaceInfo;
import com.ocean.app.dis.proxy.thrift.entity.AppType;
import com.ocean.app.dis.proxy.thrift.entity.DeviceInfo;
import com.ocean.app.dis.proxy.thrift.entity.ExtData;
import com.ocean.app.dis.proxy.thrift.entity.InterParam;
import com.ocean.app.dis.proxy.thrift.entity.Page;
import com.ocean.app.dis.proxy.thrift.entity.UserInfo;
import com.ocean.persist.app.dis.AppDisException;
import com.ocean.persist.app.dis.AppDisResponse;
import com.ocean.persist.app.dis.zk.ZKAppDetail;
import com.ocean.persist.app.dis.zk.ZKAppListSearchZKPuller;
import com.ocean.persist.app.dis.zk.ZKAppRecomReq;
import com.ocean.persist.app.dis.zk.ZKAppSpaceInfo;
import com.ocean.persist.app.dis.zk.ZKDeviceInfo;
import com.ocean.persist.app.dis.zk.ZKExtData;
import com.ocean.persist.app.dis.zk.ZKInterParam;
import com.ocean.persist.app.dis.zk.ZKPage;
import com.ocean.persist.app.dis.zk.ZKUserInfo;
import com.ocean.service.baiduInvokeHandler.base.BaiduBaseHandler;
import com.ocean.service.base.BaseHandler;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年10月10日 
      @version 1.0 
 */
@Component(value="ZKKeywordSearchHandler")
public class ZKAppListSearchHandler  extends BaseHandler{
	@Autowired
	private ZKAppListSearchZKPuller puller;

	@Override
	public AppDisResponse invok(AppDisRecomReq req) throws AppDisException {
		// TODO Auto-generated method stub
		ZKAppRecomReq request=parseAppListSearchParam(req);
		return puller.api(request,req.getUserInfo().getUid(),String.valueOf(req.getAppSpaceInfo().getAdSpaceId()),req.getJoinSource().toString());

	}
	
	public ZKAppRecomReq parseAppListSearchParam(AppDisRecomReq req) throws AppDisException{
		ZKAppRecomReq request=new ZKAppRecomReq();
		request.setRequestId(UUID.randomUUID().toString().replaceAll("-", ""));
		request.setVersion("1.0");
		if(CollectionUtils.isNotEmpty(req.getShieldType())){
			List<Integer> shieldL=new ArrayList<Integer>();
			for(AppType appType:req.getShieldType()){
				shieldL.add(appType.getValue());
			}
			request.setShieldType(shieldL);
		}
		request.setHasNext(req.isHasNext()?1:0);
		request.setJoinSource(req.getJoinSource().getValue());
		request.setInterType(req.getInterType().getValue());
		
		UserInfo userReq=req.getUserInfo();
		
		//user
		ZKUserInfo user=new ZKUserInfo();
		user.setUid(userReq.getUid());
		user.setClientIp(userReq.getClientIp());
		user.setCity(userReq.getCity());
		user.setLon(userReq.getLon());
		user.setLat(userReq.getLat());
		request.setUserInfo(user);
		
		//app
		if(req.getAppInfo()!=null){
			AppInfo appReq=req.getAppInfo();
			ZKAppDetail app=new ZKAppDetail();
			app.setType(appReq.getType()==null?1:appReq.getType().getValue());
			app.setPkgName(appReq.getPkgName());
			app.setAppName(appReq.getAppName());
			app.setVersionCode(appReq.getVersionCode());
			app.setVersionName(appReq.getVersionName());
			app.setAppLanguage(appReq.getAppLanguage());
			app.setDesc(appReq.getDesc());
			request.setAppInfo(app);
		}
		//ZKDeviceInfo
		ZKDeviceInfo device=new ZKDeviceInfo();
		DeviceInfo deviceReq=req.getDevice();
		device.setImei(deviceReq.getImei());
		device.setOs(deviceReq.getOs());
		device.setOsversion(deviceReq.getOsversion());
		device.setOsVerName(deviceReq.getOsVerName());
		device.setPhonemodel(deviceReq.getPhonemodel());
		device.setMobile(deviceReq.getMobile());
		device.setAaid(deviceReq.getAaid());
		device.setAdid(deviceReq.getAdid());
		device.setIdfa(deviceReq.getIdfa());
		device.setBrandId(deviceReq.getBrandId());
		device.setBrandName(deviceReq.getBrandName());
		device.setDeviceHeight(deviceReq.getDeviceHeight());
		device.setDeviceWidth(deviceReq.getDeviceWidth());
		device.setMac(deviceReq.getMac());
		device.setImsi(deviceReq.getImsi());
		device.setDip(deviceReq.getDip());
		device.setDpi(deviceReq.getDpi());
		request.setDevice(device);
		
		//appSpace
		ZKAppSpaceInfo space=new ZKAppSpaceInfo();
		AppSpaceInfo spaceReq=req.getAppSpaceInfo();
		space.setAdSpaceId(spaceReq.getAdSpaceId());
		space.setAppId(spaceReq.getAppId());
		space.setSpaceHeight(spaceReq.getSpaceHeight());
		space.setSpaceWidth(spaceReq.getSpaceWidth());
		request.setAppSpaceInfo(space);
		
		
		//page
		
		Page pageReq=req.getPage();
		if(pageReq!=null){
			ZKPage page=new ZKPage();
			page.setPageSize(pageReq.getPageSize());
			page.setFrom(pageReq.getFrom());
			page.setLimit(pageReq.getLimit());
			request.setPage(page);
		}
		
		
		//joinParam
		
		if (CollectionUtils.isNotEmpty(req.getJoinParam())){
			List<ExtData> joinParaReq=req.getJoinParam();
			List<ZKExtData>  extL=new ArrayList<ZKExtData>();
			for(ExtData extReq:joinParaReq){
				ZKExtData ext=new ZKExtData();
				ext.setKey(extReq.getKey());
				ext.setValue(extReq.getValue());
				ext.setDesc(extReq.getDesc());
				extL.add(ext);
			}
			request.setJoinParam(extL);
		}
		
		//interParm
		if (req.getInterParam()!=null){
			InterParam intParamReq= req.getInterParam();
			ZKInterParam intParam=new ZKInterParam();
			intParam.setHotWord(intParamReq.getHotWord());
			intParam.setKeyWord(intParamReq.getKeyWord());

			if (CollectionUtils.isNotEmpty(intParamReq.getRequiredParam())){
				List<ExtData> joinParaReq=intParamReq.getRequiredParam();
				List<ZKExtData>  extL=new ArrayList<ZKExtData>();
				for(ExtData extReq:joinParaReq){
					ZKExtData ext=new ZKExtData();
					ext.setKey(extReq.getKey());
					ext.setValue(extReq.getValue());
					ext.setDesc(extReq.getDesc());
					extL.add(ext);
				}

				intParam.setRequiredParam(extL);
			}
			request.setInterParam(intParam);
		}
		
		
		
		return request;
	}

	public ZKAppListSearchZKPuller getPuller() {
		return puller;
	}

	public void setPuller(ZKAppListSearchZKPuller puller) {
		this.puller = puller;
	}

	@Override
	public boolean validate(AppDisRecomReq req) {
		// TODO Auto-generated method stub
		return false;
	}
}
