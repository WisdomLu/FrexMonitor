package com.ocean.proxy.api.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ocean.core.common.threadpool.Parameter;
import com.ocean.persist.api.proxy.AdPullException;
import com.ocean.persist.api.proxy.maiguang.MaiguangAdpullParams;
import com.ocean.persist.api.proxy.maiguang.MaiguangAdpullResponse;
import com.ocean.persist.api.proxy.maiguang.MaiguangAdpuller;
import com.ocean.persist.api.proxy.yijifen.YijifenAdPuller;
import com.ocean.persist.model.proxy.DSPPosition;
import com.ocean.proxy.api.base.AbstractAdSupplier;
import com.ocean.proxy.thrift.entity.AdContent;
import com.ocean.proxy.thrift.entity.AdMutiAction;
import com.ocean.proxy.thrift.entity.AdRecomReply;
import com.ocean.proxy.thrift.entity.AdRecomReq;
import com.ocean.proxy.thrift.entity.AdSpaceType;
import com.ocean.proxy.thrift.entity.AdUserInfo;
import com.ocean.proxy.thrift.entity.UserAdSpaceAttri;
import com.ocean.core.common.threadpool.workthread.AsynAbstractTask;


/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年7月3日 
      @version 1.0 
 */
@Component(value="maiguangAdSupplier")
public class MaiguangAdSupplier   extends AbstractAdSupplier{
    @Autowired 
    private MaiguangAdpuller maiguangAdpuller;
	private static final Map<String, Integer> conn = new HashMap<String, Integer>();
	private static final Map<Short, Integer> pt = new HashMap<Short, Integer>();
	static{

		conn.put(NETWORK_2G, 2);
		conn.put(NETWORK_3G, 3);
		conn.put(NETWORK_4G, 4);
		conn.put(NETWORK_WIFI, 1);

		pt.put((short)1, 1);
		pt.put((short)3, 3);
		pt.put((short)2, 2);
		pt.put((short)4, 4);
	}
	public AdRecomReply invoke(InvokeAttribute attribute)
			throws AdPullException {
		// TODO Auto-generated method stub

		AdRecomReq adreq = attribute.getAdreq();
		DSPPosition positionInfo = attribute.getDspPosition();
		// 参数转换
		MaiguangAdpullParams params = parseParams(adreq, positionInfo);
		// 调用API
		MaiguangAdpullResponse response = (MaiguangAdpullResponse)maiguangAdpuller.api(params,adreq.getHash(),String.valueOf(adreq.getUserAdSpaceAttri().getAdSpaceId()));
		// 解析结果
		return parseResult(response);
	}
	private MaiguangAdpullParams parseParams(AdRecomReq adreq, 
			DSPPosition positionInfo) throws AdPullException {
		
		if(StringUtils.isEmpty(positionInfo.getText1())||StringUtils.isEmpty(positionInfo.getText2())){
			throw  new AdPullException("app id or package name is empty!");
		}
		AdUserInfo user=adreq.getUserinfo();
		UserAdSpaceAttri attr=adreq.getUserAdSpaceAttri();
		MaiguangAdpullParams param =new MaiguangAdpullParams();
		param.setAction("HB");
		param.setAppid(positionInfo.getText1());
		param.setVer("2100");
		param.setTp(String.valueOf(System.currentTimeMillis()/1000));
		param.setIs(user.getImsi());
		String osCode = user.getOs();
		// 如果是ios
	    String os = "android";
	    if(OS_IOS.equals(osCode)){
			// ios 必填信息
	    	param.setDtv(user.getIdfa());
	    	param.setDt(2);
			
			os="ios";
	    }else if(OS_ANDROID.equals(osCode)){

	    	param.setDtv(user.getImei());
	    	param.setDt(1);
		}
	    param.setW(String.valueOf(user.getDevice_width()));
	    param.setH(String.valueOf(user.getDevice_height()));
	    param.setBrand(user.getBrand_name());
	    param.setMod(user.getPhonemodel());
	    param.setOs(os);
	    param.setOv(this.convOSVersion(user.getOsversion()));
	    Integer nt=conn.get(adreq.getNet());
	    param.setNt(nt==null?0:nt);
	    param.setMac(user.getMac());
	    Integer mgpt=pt.get(positionInfo.getPosType());
	    param.setPt(mgpt==null?1:mgpt);
	    param.setAdrid(user.getAdid());
	    param.setUa(user.getUa());
	    param.setDip(user.getDensity());
	    param.setIp(user.getClient_ip());
	    param.setDensity(user.getDip());
	    
	   //=========由于参数问题，未开发完，待续============
		return param;
	}
	private AdRecomReply parseResult(MaiguangAdpullResponse response)
			throws AdPullException {
		
		if(response == null){
			return null;
		}
		AdRecomReply reply=new AdRecomReply();
		reply.setStatus(status);
		//***********************BEGIN 常规设置*************
		AdContent content = new AdContent();
		AdMutiAction action = new AdMutiAction();
		String title = defTitle;
		String markTitle= defTitle;
		// 内容类型1表示链接推广,2表示应用推广,3未使用 4表示电话推广
	 
        int acType=ACTION_WEB;
		//***********************END 常规设置***************
		
		//上报
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		
		
		
		
		content.setThirdReportLinks(map);
		
	    action.setType(acType); 
	    action.setGuideTitle(title);
		content.setMarketTitle(markTitle);
		content.setGuideTitle(title);
		content.setMutiAction(Collections.singletonList(action));

		reply.setAd_contents(Collections.singletonList(content));
		return reply;
	}
	public MaiguangAdpuller getMaiguangAdpuller() {
		return maiguangAdpuller;
	}
	public void setMaiguangAdpuller(MaiguangAdpuller maiguangAdpuller) {
		this.maiguangAdpuller = maiguangAdpuller;
	}


}
