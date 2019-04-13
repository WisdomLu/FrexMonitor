package com.ocean.persist.api.proxy.inmobi;

import com.ocean.core.common.base.AbstractBaseEntity;

public class InmobiUser {

	private static final long serialVersionUID = 1L;
	
	private Integer yob;
	private String gender;

	public Integer getYob() {
		return yob;
	}

	public void setYob(Integer yob) {
		this.yob = yob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
