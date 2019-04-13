package com.ocean.persist.api.proxy.inmobi;

import com.ocean.core.common.base.AbstractBaseEntity;

public class InmobiDeviceExt {

	private static final long serialVersionUID = 1L;
	
	private String locale;// 
	private Integer orientation;
	
	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public Integer getOrientation() {
		return orientation;
	}

	public void setOrientation(Integer orientation) {
		this.orientation = orientation;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
