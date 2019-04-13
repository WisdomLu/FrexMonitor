package com.ocean.persist.api.proxy.shenmi;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;


public class ShenmiAdEvent  {

	private static final long serialVersionUID = 1L;

	public static final String DOWNLOAD_COMPLETE = "downloadcomplete";
	public static final String INSTALL_COMPLETE="installcomplete";
	public static final String DOWNLOAD_START = "downloadstart";
	public static final String INSTALL_START="installstart";
	private String eventtype;
	
	private List<String> tracking;
	
	public String getEventtype() {
		return eventtype;
	}

	public void setEventtype(String eventtype) {
		this.eventtype = eventtype;
	}

	public List<String> getTracking() {
		return tracking;
	}

	public void setTracking(List<String> tracking) {
		this.tracking = tracking;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
