package com.ocean.persist.api.proxy.redstone;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullParams;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年6月7日 
      @version 1.0 
 */
public class RedStoneUser      {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7275233574740315051L;
	private int id;
	private int yob;
	private int age_low;
	private int age_high;
	private String gender;
	private String keywords;
	private RedStoneGeo geo;
	private	 RedStoneExt ext;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getYob() {
		return yob;
	}
	public void setYob(int yob) {
		this.yob = yob;
	}
	public int getAge_low() {
		return age_low;
	}
	public void setAge_low(int age_low) {
		this.age_low = age_low;
	}
	public int getAge_high() {
		return age_high;
	}
	public void setAge_high(int age_high) {
		this.age_high = age_high;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public RedStoneGeo getGeo() {
		return geo;
	}
	public void setGeo(RedStoneGeo geo) {
		this.geo = geo;
	}
	public RedStoneExt getExt() {
		return ext;
	}
	public void setExt(RedStoneExt ext) {
		this.ext = ext;
	}
}
