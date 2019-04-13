package com.ocean.persist.api.proxy.yuansheng;

import java.util.List;

import com.ocean.core.common.threadpool.AbstractInvokeParameter;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年1月8日 
      @version 1.0 
 */
public class YuanshengAdPullParams      extends AbstractInvokeParameter{
	private static final long serialVersionUID = 4075182569348284658L;
	private String apiv;//	string	Y	Bridge广告接口API版本号。	固定指定1.0
	private String time	;//	string	Y	请求的unix时间戳，精确到毫秒（13位）。	1414764477867
	private String token;//		string	Y	流量来源效验码。	
	private String reqid;//		string	Y	广告请求的唯一id，uuid，由媒体生成。	
	private List<YuanshengImp> imp	;//	array of object	Y	imp对象，见3.2.2。	
	private YuanshengApp app;//		object	Y	app对象，见3.2.3。	
	private YuanshengDevice	device;//	object	Y	device对象，见3.2.4。	
	private YuanshengEnv env;//		object	N	env对象，见3.2.5。	

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}

	public String getApiv() {
		return apiv;
	}

	public void setApiv(String apiv) {
		this.apiv = apiv;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getReqid() {
		return reqid;
	}

	public void setReqid(String reqid) {
		this.reqid = reqid;
	}

	public List<YuanshengImp> getImp() {
		return imp;
	}

	public void setImp(List<YuanshengImp> imp) {
		this.imp = imp;
	}

	public YuanshengApp getApp() {
		return app;
	}

	public void setApp(YuanshengApp app) {
		this.app = app;
	}

	public YuanshengDevice getDevice() {
		return device;
	}

	public void setDevice(YuanshengDevice device) {
		this.device = device;
	}

	public YuanshengEnv getEnv() {
		return env;
	}

	public void setEnv(YuanshengEnv env) {
		this.env = env;
	}

}
