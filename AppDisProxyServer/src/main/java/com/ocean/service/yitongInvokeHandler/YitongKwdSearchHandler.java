package com.ocean.service.yitongInvokeHandler;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.inveno.util.CollectionUtils;
import com.inveno.util.StringUtil;
import com.ocean.app.dis.proxy.thrift.entity.AppDisRecomReq;
import com.ocean.app.dis.proxy.thrift.entity.DeviceInfo;
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
import com.ocean.persist.app.dis.yitong.applist.ListSearchYitongReq;
import com.ocean.persist.app.dis.yitong.applist.ListSearchYitongSecParams;
import com.ocean.service.base.BaseHandler;
import com.ocean.task.YitongKwdTask;
import com.ocean.persist.app.dis.yitong.kwd.KwdSearchYitongResp;
import com.ocean.persist.app.dis.yitong.kwd.KwdSearchYitongReq;
import com.ocean.persist.common.AppDisConstants;
import com.ocean.persist.common.AppDisErrorCode;
/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年5月10日 
      @version 1.0 
 */
@Component(value="YitongKwdSearchHandler")
public class YitongKwdSearchHandler   extends BaseHandler{
	private  final Logger logger = MyLogManager.getLogger();
	@Override
	public AppDisResponse invok(AppDisRecomReq req) throws AppDisException {
		// TODO Auto-generated method stub
		KwdSearchYitongReq request=parseKwdSearchParam(req);
		return invok(req,request);
	}
	public AppDisResponse invok(AppDisRecomReq proxyReq,ListSearchYitongReq request) throws AppDisException {
		// TODO Auto-generated method stub
		return (KwdSearchYitongResp)this.invoke(request, KwdSearchYitongResp.class, proxyReq.getUserInfo().getUid(),String.valueOf(proxyReq.getAppSpaceInfo().getAdSpaceId()));
		
	}
	public KwdSearchYitongReq parseKwdSearchParam(AppDisRecomReq req) throws AppDisException{
		if(CollectionUtils.isEmpty(req.getJoinParam())||req.getJoinParam().size()<3){
			throw new AppDisException(ErrorCode.PARAM_ERROR,"get join params empty!");

		}
		UserInfo user=req.getUserInfo();
		KwdSearchYitongReq param =new KwdSearchYitongReq();
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
		InterParam interParam=req.getInterParam();
		if(StringUtil.isEmpty(interParam.getKeyWord())&&StringUtils.isEmpty(interParam.getHotWord())){
			throw new AppDisException(AppDisErrorCode.RC_PARAM_ERROR,"key word search parameter is empty!");
		}
		String keyword=interParam.getKeyWord();
		if(StringUtil.isEmpty(keyword)){
			keyword=interParam.getHotWord();
		}
		param.setKeyword(keyword);
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
	@Override
	public AsynAbstractTask packageTask(Parameter params, String hashCode) {
		// TODO Auto-generated method stub
		YitongKwdTask task = new YitongKwdTask();
		KwdSearchYitongReq param = (KwdSearchYitongReq) params;
		
	
		StringBuilder url = new StringBuilder();
		url.append(SystemContext.getDynamicPropertyHandler().get(AppDisConstants.YITONG_KWD_URL));
		url.append("?").append(Bean2Utils.toHttpParams(param));

		task.setParam(param);
		task.setDataFormat(DataFormat.DATA_FORMAT_KVP);
		task.setInvokeType(InvokeType.INVOKE_TYPE_GET);
		task.setHashCode(hashCode);
		task.setUrl(url.toString());
		
		return task;
	}
}
