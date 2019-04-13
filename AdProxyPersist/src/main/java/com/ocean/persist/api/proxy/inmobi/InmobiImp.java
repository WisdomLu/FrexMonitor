package com.ocean.persist.api.proxy.inmobi;

import com.ocean.core.common.base.AbstractBaseEntity;

public class InmobiImp {

	private static final long serialVersionUID = 1L;
	
	private InmobiImpExt ext;	
	private InmobiNative native1;// native对象，如果这是一次原生广告展示请求，则这个对象是必须的 
	private InmobiBanner banner;
	private Integer secure;
	private String trackertype;
	
	public InmobiImpExt getExt() {
		return ext;
	}

	public void setExt(InmobiImpExt ext) {
		this.ext = ext;
	}

	public InmobiBanner getBanner() {
		return banner;
	}

	public void setBanner(InmobiBanner banner) {
		this.banner = banner;
	}

	public Integer getSecure() {
		return secure;
	}

	public void setSecure(Integer secure) {
		this.secure = secure;
	}

	public String getTrackertype() {
		return trackertype;
	}

	public void setTrackertype(String trackertype) {
		this.trackertype = trackertype;
	}

	public InmobiNative getNative1() {
		return native1;
	}

	public void setNative1(InmobiNative native1) {
		this.native1 = native1;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
