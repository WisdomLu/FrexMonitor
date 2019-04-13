package com.ocean.persist.api.proxy.yidianzx;

public class YidianzxGeo {
	private double lat;// double 否纬度
	private double lon;// double 否经度
	private int lalotype;// int 否经纬度类型
	private String city;// string 否城市
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLon() {
		return lon;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}
	public int getLalotype() {
		return lalotype;
	}
	public void setLalotype(int lalotype) {
		this.lalotype = lalotype;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
}
