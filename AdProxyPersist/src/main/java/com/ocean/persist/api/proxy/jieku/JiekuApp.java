package com.ocean.persist.api.proxy.jieku;

import com.ocean.core.common.base.AbstractBaseEntity;


public class JiekuApp {

	private static final long serialVersionUID = 1L;
	
	private String package_name;// required string app的包名 

	public String getPackage_name() {
		return package_name;
	}

	public void setPackage_name(String package_name) {
		this.package_name = package_name;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
