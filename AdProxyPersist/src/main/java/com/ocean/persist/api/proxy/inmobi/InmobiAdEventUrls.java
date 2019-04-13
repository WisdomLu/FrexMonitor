package com.ocean.persist.api.proxy.inmobi;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
public class InmobiAdEventUrls {

	private static final long serialVersionUID = 1L;
	
	private List<String> urls;
	
	public List<String> getUrls() {
		return urls;
	}

	public void setUrls(List<String> urls) {
		this.urls = urls;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
