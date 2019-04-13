package com.ocean.persist.api.proxy.mex;

import com.ocean.core.common.base.AbstractBaseEntity;

import com.ocean.persist.api.proxy.AdPullParams;


public class MexVideo  {

	private static final long serialVersionUID = 1L;
	
	private String vasttag;// VAST xml 
	
	public String getVasttag() {
		return vasttag;
	}

	public void setVasttag(String vasttag) {
		this.vasttag = vasttag;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
