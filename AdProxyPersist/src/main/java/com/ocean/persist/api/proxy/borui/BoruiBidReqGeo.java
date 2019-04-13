package com.ocean.persist.api.proxy.borui;

public class BoruiBidReqGeo {

	//经度-非必传
	private Double longitude;
	
	//纬度-非必传
	private Double latitude;

	//省份-必须
	private String province;
	
	//城市-非必传
	private String city;

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

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
}
