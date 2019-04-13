package com.ocean.persist.api.proxy.yidianzx;

public class YidianzxImp {
	private String id;// string 是广告位的唯一标识符，同一广告位在多次请求中应保持一致
	private YidianzxBanner banner;// object 是Banner 广告对象，见Banner
	private Integer bidfloor ;// 否底价，单位：分
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public YidianzxBanner getBanner() {
		return banner;
	}
	public void setBanner(YidianzxBanner banner) {
		this.banner = banner;
	}
	public Integer getBidfloor() {
		return bidfloor;
	}
	public void setBidfloor(Integer bidfloor) {
		this.bidfloor = bidfloor;
	}
}
