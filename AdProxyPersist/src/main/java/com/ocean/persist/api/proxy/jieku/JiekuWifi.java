package com.ocean.persist.api.proxy.jieku;

import com.ocean.core.common.base.AbstractBaseEntity;


public class JiekuWifi {

	private static final long serialVersionUID = 1L;
	
	private String mac;// required string WIFI热点MAC地址
	private Integer rssi;// optional int32 信号强度
	
	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public Integer getRssi() {
		return rssi;
	}

	public void setRssi(Integer rssi) {
		this.rssi = rssi;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
