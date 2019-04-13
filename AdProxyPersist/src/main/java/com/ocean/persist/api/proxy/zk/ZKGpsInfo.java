package com.ocean.persist.api.proxy.zk;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullParams;

public class ZKGpsInfo    {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8377477140540441012L;

	/** GPS类型 */
	private ZKGpsType gpsType;

	/** 经度 */
	private double longitude;

	/** 维度 */
	private double latitude;

	/** 时间戳 */
	private long timestamp;

	public ZKGpsType getGpsType() {
		return gpsType;
	}

	public void setGpsType(ZKGpsType gpsType) {
		this.gpsType = gpsType;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
}
