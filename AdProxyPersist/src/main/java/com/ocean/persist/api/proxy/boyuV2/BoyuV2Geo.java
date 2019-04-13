package com.ocean.persist.api.proxy.boyuV2;

public class BoyuV2Geo {
	private double longitude;//	double	N	30.122313	经度，传递该参数可以基于用户位置更加精准的投放广告	
	
	private double latitude	;//double	N	45.123123	纬度，传递该参数可以基于用户位置更加精准的投放广告	

	public double getLongitude() {
		return longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
}
