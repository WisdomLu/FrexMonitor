package com.ocean.persist.api.proxy.zk;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullParams;

/**
 * 用户属性
 * 
 * @author dell
 *
 */
public class ZKUserProfile    {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8160419150884006307L;

	/** 用户ID */
	private String userId;

	/** 性别 */
	private ZKGender gender;

	/** 年龄 */
	private Integer age;

	/** 是否已婚 */
	private Boolean marry;

	/** 是否有小孩 */
	private Boolean haveBaby;

	/** 是否有宠物 */
	private Boolean havePet;

	/** 关键词 */
	private String keywords;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public ZKGender getGender() {
		return gender;
	}

	public void setGender(ZKGender gender) {
		this.gender = gender;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Boolean isMarry() {
		return marry;
	}

	public void setMarry(Boolean marry) {
		this.marry = marry;
	}

	public Boolean isHaveBaby() {
		return haveBaby;
	}

	public void setHaveBaby(Boolean haveBaby) {
		this.haveBaby = haveBaby;
	}

	public Boolean isHavePet() {
		return havePet;
	}

	public void setHavePet(Boolean havePet) {
		this.havePet = havePet;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
}
