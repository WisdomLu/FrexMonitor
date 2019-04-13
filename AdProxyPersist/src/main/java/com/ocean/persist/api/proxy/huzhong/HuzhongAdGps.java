package com.ocean.persist.api.proxy.huzhong;

import com.ocean.core.common.base.AbstractBaseEntity;

import com.ocean.persist.api.proxy.AdPullParams;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年6月29日 
      @version 1.0 
 */
public class HuzhongAdGps     {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8878268130811872398L;
	private double lat;
	private double lng;
	private int timestamp;
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}
	public int getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(int timestamp) {
		this.timestamp = timestamp;
	}

}
