package com.ocean.service.baiduInvokeHandler;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inveno.util.StringUtil;
import com.ocean.app.dis.proxy.thrift.entity.AppDisRecomReq;
import com.ocean.app.dis.proxy.thrift.entity.DeviceInfo;
import com.ocean.app.dis.proxy.thrift.entity.ExtData;
import com.ocean.app.dis.proxy.thrift.entity.InterParam;
import com.ocean.app.dis.proxy.thrift.entity.UserInfo;
import com.ocean.persist.app.dis.AppDisException;
import com.ocean.persist.app.dis.AppDisResponse;
import com.ocean.persist.app.dis.appSearch360.keywordSearch.KeywordSearch360Request;
import com.ocean.persist.app.dis.baiduAppSearch.keywordSearch.KeywordSearchBaiduPuller;
import com.ocean.persist.app.dis.baiduAppSearch.keywordSearch.KeywordSearchBaiduRequest;
import com.ocean.persist.app.dis.baiduAppSearch.keywordSearch.Signer;
import com.ocean.persist.common.AppDisErrorCode;
import com.ocean.service.baiduInvokeHandler.base.BaiduBaseHandler;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年10月10日 
      @version 1.0 
 */
@Component(value="baiduKeywordSearchHandler")
public class BaiduKeywordSearchHandler  extends BaiduBaseHandler{
	@Autowired
	private KeywordSearchBaiduPuller puller;
	public KeywordSearchBaiduPuller getPuller() {
		return puller;
	}
	public void setPuller(KeywordSearchBaiduPuller puller) {
		this.puller = puller;
	}
	@Override
	public AppDisResponse invok(AppDisRecomReq req) throws AppDisException {
		// TODO Auto-generated method stub
		KeywordSearchBaiduRequest request=parseKeyWordSearchParam(req);
		return puller.api(request,req.getUserInfo().getUid(),String.valueOf(req.getAppSpaceInfo().getAdSpaceId()));

	}
	
	public KeywordSearchBaiduRequest parseKeyWordSearchParam(AppDisRecomReq req) throws AppDisException{
		KeywordSearchBaiduRequest request=new KeywordSearchBaiduRequest();
		UserInfo user=req.getUserInfo();
		DeviceInfo device=req.getDevice();
		String iv="";
		String key="";
		for(int i=0;i<req.getJoinParam().size();i++){
			ExtData ext=req.getJoinParam().get(i);
			if(i==0){
				request.setFrom(ext.getValue());
			}else if(i==1){
				request.setToken(ext.getValue());
			}else if(i==2){
				iv=ext.getValue();
			}else if(i==3){
				key=ext.getValue();
			}

		}
		request.setType("app");
		request.setBdi_imei(Signer.encode(device.getImei(), request.getFrom(),key , iv));
		request.setBdi_loc(user.getCity());
		request.setBdi_uip(user.getClientIp());
		if("wifi".equals(user.getNet())||StringUtils.isEmpty(user.getNet())){
			request.setBdi_bear("WF");
		}else{
			request.setBdi_bear(user.getNet().toUpperCase());
		}
		request.setResolution(device.getDeviceWidth()+"_"+device.getDeviceHeight());
	    request.setDpi(device.getDpi());
	    request.setApilevel(device.getOsversion());
	    request.setOs_version(device.getOsVerName());
	    request.setBrand(device.getBrandName());
	    try {
			request.setModel(URLEncoder.encode(device.getPhonemodel(), "utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    request.setPver("3");
	    request.setBdi_mac(device.getMac());
	    
		InterParam interParam=req.getInterParam();
		if(StringUtil.isEmpty(interParam.getKeyWord())&&StringUtils.isEmpty(interParam.getHotWord())){
			throw new AppDisException(AppDisErrorCode.RC_PARAM_ERROR,"key word search parameter is empty!");
		}
		String keyWord=interParam.getKeyWord();
		if(StringUtil.isEmpty(keyWord)){
			keyWord=interParam.getHotWord();
		}
		request.setWord(keyWord);
		if(req.isSetPage()){
			request.setRn(req.getPage().getPageSize());
		}else{
			request.setRn(pageSize);//默认请求10个
		}

	    StringBuffer buffer=new StringBuffer();
	    buffer.append("from=").append(request.getFrom()).append("&")
	    .append("type=").append(request.getType()).append("&")
	    .append("word=").append(request.getWord()).append("&")
	    .append("bdi_imei=").append(request.getBdi_imei()).append("&")
	    .append("bdi_loc=").append(request.getBdi_loc()).append("&")
	    .append("bdi_uip=").append(request.getBdi_uip()).append("&")
	    .append("bdi_bear=").append(request.getBdi_bear()).append("&")
	    .append("resolution=").append(request.getResolution()).append("&")
	    .append("dpi=").append(request.getDpi()).append("&")
	    .append("apilevel=").append(request.getApilevel()).append("&")
	    .append("os_version=").append(request.getOs_version()).append("&")
	    .append("brand=").append(request.getBrand()).append("&")
	    .append("model=").append(request.getModel()).append("&")
	    .append("pver=").append(request.getPver()).append("&")
	    .append("bdi_mac=").append(request.getBdi_mac()).append("&") 
	    .append("rn=").append(request.getResolution());
	    
	    request.setSign(Signer.toMd5(buffer.toString()));
		


		//request.setPn(0);
		return request;
	}
}
