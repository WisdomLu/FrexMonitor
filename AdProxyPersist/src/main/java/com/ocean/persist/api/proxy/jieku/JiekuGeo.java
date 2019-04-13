package com.ocean.persist.api.proxy.jieku;

import com.ocean.core.common.base.AbstractBaseEntity;


public class JiekuGeo {

	private static final long serialVersionUID = 1L;
	
	private Integer type;// required uint32 坐标类型(1. WGS84 2. GCJ02 3. BD09)
	private Double longitude;// required double 经度
	private Double latitude;// required double 纬度
	private Long timestamp;// required uint32 时间戳
	private Integer source;// optional uint32 定位来源(1.NATIVE 2. BAIDU)
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
