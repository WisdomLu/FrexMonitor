package com.ocean.persist.api.proxy.pairui;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class PairuiBidImp {

	// required String imp的id，由媒体方提供，建议自增长；
	private String id;
	
	// required String 广告位唯一标识id
	private String tagid;
	
	// required  integer  每千次曝光底价，单位：分。默认bidfloor=0；
	// 默认币种为人民币。可在对接前协商具体币种
	private Integer bidfloor;
	
	// required  integer  广告位宽，单位px。
	private Integer w;
	
	// required  integer  广告位高，单位px。
	private Integer h;
	
	// optional  object array  描述Banner类型广告的对象
	private List<PairuiBidReqBanner> banner;
	
	// optional  object array  描述Video类型广告的对象
	private List<PairuiBidReqVideo> video;
	
	// optional  object array  描述Native类型广告的对象
	@SerializedName("native")
	private List<PairuiBidReqNative> natives;
	
	// optional  object  描述Prefer Deal类型广告的对象
	private PairuiBidReqPmp pmp;

	// required  String array  广告打开类型
	// 1：landing，2：download，3：deeplink
	private List<String> opentype;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTagid() {
		return tagid;
	}

	public void setTagid(String tagid) {
		this.tagid = tagid;
	}

	public Integer getBidfloor() {
		return bidfloor;
	}

	public void setBidfloor(Integer bidfloor) {
		this.bidfloor = bidfloor;
	}

	public Integer getW() {
		return w;
	}

	public void setW(Integer w) {
		this.w = w;
	}

	public Integer getH() {
		return h;
	}

	public void setH(Integer h) {
		this.h = h;
	}

	public List<PairuiBidReqBanner> getBanner() {
		return banner;
	}

	public void setBanner(List<PairuiBidReqBanner> banner) {
		this.banner = banner;
	}

	public List<PairuiBidReqVideo> getVideo() {
		return video;
	}

	public void setVideo(List<PairuiBidReqVideo> video) {
		this.video = video;
	}

	public List<PairuiBidReqNative> getNatives() {
		return natives;
	}

	public void setNatives(List<PairuiBidReqNative> natives) {
		this.natives = natives;
	}

	public PairuiBidReqPmp getPmp() {
		return pmp;
	}

	public void setPmp(PairuiBidReqPmp pmp) {
		this.pmp = pmp;
	}

	public List<String> getOpentype() {
		return opentype;
	}

	public void setOpentype(List<String> opentype) {
		this.opentype = opentype;
	}
}
