package com.ocean.persist.api.proxy.mex;

import com.ocean.core.common.base.AbstractBaseEntity;

import com.ocean.persist.api.proxy.AdPullParams;


public class MexImp  {

	private static final long serialVersionUID = 1L;
	private  int js;
	private MexBanner banner;//banner对象，如果广告位是一个banner位，则这个对象是必须的。 
	private MexNativeReq native1;// native对象，如果这是一次原生广告展示请求，则这个对象是必须的 

	public MexBanner getBanner() {
		return banner;
	}

	public void setBanner(MexBanner banner) {
		this.banner = banner;
	}

	public MexNativeReq getNative1() {
		return native1;
	}

	public void setNative1(MexNativeReq native1) {
		this.native1 = native1;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getJs() {
		return js;
	}

	public void setJs(int js) {
		this.js = js;
	}
}
