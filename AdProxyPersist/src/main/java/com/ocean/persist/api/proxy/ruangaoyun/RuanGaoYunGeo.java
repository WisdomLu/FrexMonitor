package com.ocean.persist.api.proxy.ruangaoyun;

public class RuanGaoYunGeo {
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
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	private Float lat;
    private Float lon;
    private String country;
}
