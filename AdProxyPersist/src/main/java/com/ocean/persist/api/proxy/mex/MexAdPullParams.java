package com.ocean.persist.api.proxy.mex;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.core.common.threadpool.AbstractInvokeParameter;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.persist.api.proxy.AdPullParams;

public class MexAdPullParams   extends AbstractInvokeParameter{

	private static final long serialVersionUID = 1L;
	private String apiver;// required 	api版本号，目前只有1.0 
	private String publisherid;// required 	开发者的ID 
	private String appid;// required 	应用的ID 
	private String adposid;// required 	广告位ID 
	private String token;// required 	令牌 
	private MexDevice device;// 设备信息对象 
	private MexImp imp;// 广告展示信息对象 
	private String bundleid;// 应用包名，安卓使用应用包名，苹果使用appstore 的iTunesId 

	public String getApiver() {
		return apiver;
	}

	public void setApiver(String apiver) {
		this.apiver = apiver;
	}

	public String getPublisherid() {
		return publisherid;
	}

	public void setPublisherid(String publisherid) {
		this.publisherid = publisherid;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getAdposid() {
		return adposid;
	}

	public void setAdposid(String adposid) {
		this.adposid = adposid;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public MexDevice getDevice() {
		return device;
	}

	public void setDevice(MexDevice device) {
		this.device = device;
	}

	public MexImp getImp() {
		return imp;
	}

	public void setImp(MexImp imp) {
		this.imp = imp;
	}

	public String getBundleid() {
		return bundleid;
	}

	public void setBundleid(String bundleid) {
		this.bundleid = bundleid;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public boolean validate() {
		return false;
		// TODO Auto-generated method stub
		
	}
}
