package com.ocean.persist.api.proxy.borui;

import java.util.List;

public class BoruiBidReqAdSlot {
	
	//广告物料宽
	private Integer adslot_width;
	
	//广告物料高
	private Integer adslot_height;
	
	// 选填 详情数值咨询技术人员.....
	private String topics;
	
	//低价(单位：分)
	private Integer bidfloor;
	
	// 必填！广告类型
	private String adtype;
	
	// 选填,如果填充此值，意味着返回广告此包名的广告
	private List<String> packagename;

	public Integer getAdslot_width() {
		return adslot_width;
	}

	public void setAdslot_width(Integer adslot_width) {
		this.adslot_width = adslot_width;
	}

	public Integer getAdslot_height() {
		return adslot_height;
	}

	public void setAdslot_height(Integer adslot_height) {
		this.adslot_height = adslot_height;
	}

	public String getTopics() {
		return topics;
	}

	public void setTopics(String topics) {
		this.topics = topics;
	}

	public Integer getBidfloor() {
		return bidfloor;
	}

	public void setBidfloor(Integer bidfloor) {
		this.bidfloor = bidfloor;
	}

	public String getAdtype() {
		return adtype;
	}

	public void setAdtype(String adtype) {
		this.adtype = adtype;
	}

	public List<String> getPackagename() {
		return packagename;
	}

	public void setPackagename(List<String> packagename) {
		this.packagename = packagename;
	}
}
