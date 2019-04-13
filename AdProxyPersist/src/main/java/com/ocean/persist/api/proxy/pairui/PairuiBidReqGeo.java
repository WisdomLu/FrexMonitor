package com.ocean.persist.api.proxy.pairui;

public class PairuiBidReqGeo {

	// optional float  纬度,-90至+90，精度到小数后6位
	private Double lat;
	
	// optional  float 经度，-180至+180，精度到小数后6位
	private Double lon;
	
	// optional  String 城市名称。例如：“beijing”代表北京；
	// 若有地域码，请提供字典表信息
	private String city;

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLon() {
		return lon;
	}

	public void setLon(Double lon) {
		this.lon = lon;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
}
