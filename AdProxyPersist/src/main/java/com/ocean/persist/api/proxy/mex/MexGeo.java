package com.ocean.persist.api.proxy.mex;

import com.ocean.core.common.base.AbstractBaseEntity;

import com.ocean.persist.api.proxy.AdPullParams;

public class MexGeo  
{

	private static final long serialVersionUID = 1L;
	
	private Float lat;// recommended 	纬度，示例：118.1808009 
	private Float lon;// recommended 	经度，示例：24.4899117 
	private String timestamp;// recommended 	获取经纬度的时间戳 

	public Float getLat() {
		return lat;
	}

	public void setLat(Float lat) {
		this.lat = lat;
	}

	public Float getLon() {
		return lon;
	}

	public void setLon(Float lon) {
		this.lon = lon;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
