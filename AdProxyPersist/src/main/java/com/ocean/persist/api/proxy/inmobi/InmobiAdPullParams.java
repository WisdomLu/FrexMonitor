package com.ocean.persist.api.proxy.inmobi;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.core.common.threadpool.AbstractInvokeParameter;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.persist.api.proxy.AdPullParams;

public class InmobiAdPullParams  extends AbstractInvokeParameter{

	private static final long serialVersionUID = 1L;
	private InmobiApp app;
	private InmobiDevice device;// 设备信息对象 
	private InmobiImp imp;// 广告展示信息对象 
	private InmobiParamsExt ext;// 返回格式 json

	public InmobiApp getApp() {
		return app;
	}

	public void setApp(InmobiApp app) {
		this.app = app;
	}

	public InmobiDevice getDevice() {
		return device;
	}

	public void setDevice(InmobiDevice device) {
		this.device = device;
	}

	public InmobiImp getImp() {
		return imp;
	}

	public void setImp(InmobiImp imp) {
		this.imp = imp;
	}

	public InmobiParamsExt getExt() {
		return ext;
	}

	public void setExt(InmobiParamsExt ext) {
		this.ext = ext;
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
