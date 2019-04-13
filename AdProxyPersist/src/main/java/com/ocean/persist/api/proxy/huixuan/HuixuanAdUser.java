package com.ocean.persist.api.proxy.huixuan;

import com.ocean.core.common.base.AbstractBaseEntity;

import com.ocean.persist.api.proxy.AdPullParams;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年6月16日 
      @version 1.0 
 */
public class HuixuanAdUser    {

	/**
	 * 
	 */
	private static final long serialVersionUID = -699458613889554950L;
	private String id;
	private int yob;
	private String gender;
	private HuixuanAdGeo geo;
	private boolean marry;
	private int bree;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getYob() {
		return yob;
	}
	public void setYob(int yob) {
		this.yob = yob;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public HuixuanAdGeo getGeo() {
		return geo;
	}
	public void setGeo(HuixuanAdGeo geo) {
		this.geo = geo;
	}
	public boolean isMarry() {
		return marry;
	}
	public void setMarry(boolean marry) {
		this.marry = marry;
	}
	public int getBree() {
		return bree;
	}
	public void setBree(int bree) {
		this.bree = bree;
	}

}
