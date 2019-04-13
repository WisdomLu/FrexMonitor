package com.ocean.persist.api.proxy.yijifen;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullParams;

public class YijifenUser  {

	private static final long serialVersionUID = 1L;
	
	private Integer gender;// 选填 0.未知 1.男性 2.女性
	private String interest;// 选填	篮球	爱好
	private Integer age;// 选填	19	用户年龄
	private String longitude;// 选填	116.41667	地理位置：经度（小数格式）
	private String latitude;// 选填	39.91667	地理位置：纬度（小数格式）

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
