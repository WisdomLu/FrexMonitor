package com.ocean.persist.api.proxy.yileyun;
/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年3月13日 
      @version 1.0 
 */
public class YileyunGeo {
	private float latitude;/*
	float
	Optional
	纬度*/
	private float longitude;/*
	float
	Optional
	经度*/
	private String city;/*
	string
	Optional
	城市，中文即可(utf-8 编码)*/
	private String province;/*
	string
	Optional
	省份，中文即可(utf-8 编码)*/
	private String district;/*
	string
	Optional
	区县，中文即可(utf-8 编码)*/
	public float getLatitude() {
		return latitude;
	}
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	public float getLongitude() {
		return longitude;
	}
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
}
