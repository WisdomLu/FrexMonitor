package com.ocean.persist.api.proxy.inmobi;

import com.ocean.core.common.base.AbstractBaseEntity;

public class InmobiApp {

	private static final long serialVersionUID = 1L;
	
	private String id;// appid 
	private String bundle;// 包路径 

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBundle() {
		return bundle;
	}

	public void setBundle(String bundle) {
		this.bundle = bundle;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
