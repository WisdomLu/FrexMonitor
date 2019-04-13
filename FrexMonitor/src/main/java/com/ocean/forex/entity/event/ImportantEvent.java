package com.ocean.forex.entity.event;

import com.ocean.forex.entity.event.base.DefaultEvent;

public class ImportantEvent extends DefaultEvent {
	private String country;//国家
	private String city;//城市
	public String getCountry() {
		return country;
	}
	public String getCity() {
		return city;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public void setCity(String city) {
		this.city = city;
	}
}
