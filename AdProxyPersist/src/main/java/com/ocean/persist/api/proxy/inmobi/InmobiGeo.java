package com.ocean.persist.api.proxy.inmobi;

import com.ocean.core.common.base.AbstractBaseEntity;

public class InmobiGeo {

	private static final long serialVersionUID = 1L;
	
	private Double lat;// recommended 	纬度，示例：118.1808009 
	private Double lon;// recommended 	经度，示例：24.4899117 
	private Integer accu;
	private Integer type;
	private String city;
	private String country;
	
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

	public Integer getAccu() {
		return accu;
	}

	public void setAccu(Integer accu) {
		this.accu = accu;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
