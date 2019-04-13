package com.ocean.persist.api.proxy.pairui;

import java.util.List;

public class PairuiBidReqUser {

	// optional  String  用户唯一标识，由媒体分配。
	private String id;
	
	// optional  String  性别，male：男性，female：女性，unknown：未知。
	private String gender;
	
	// optional  String  最小年龄，正整数。
	private String minage;
	
	// optional  String  最大年龄，正整数。
	private String maxage;
	
	// optional  String array  人群标签，请提供标签字典表。
	private List<String> tags;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMinage() {
		return minage;
	}

	public void setMinage(String minage) {
		this.minage = minage;
	}

	public String getMaxage() {
		return maxage;
	}

	public void setMaxage(String maxage) {
		this.maxage = maxage;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}
}
