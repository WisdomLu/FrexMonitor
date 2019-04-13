package com.ocean.persist.api.proxy.xunfei;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;


public class XunfeiAdResponse  extends AbstractBaseEntity implements AdPullResponse{

//	/** 表示下发的广告为原生广告或json格式的普通广告*/
//	public static final int NATIVE_AD = 1;
//	
//	/** 视频广告*/
//	public static final int VIDEO_AD = 2;
	
	public int getRc() {
		return rc;
	}
	public void setRc(int rc) {
		this.rc = rc;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBid_id() {
		return bid_id;
	}
	public void setBid_id(String bid_id) {
		this.bid_id = bid_id;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getCur() {
		return cur;
	}
	public void setCur(String cur) {
		this.cur = cur;
	}
	public List<XunfeiAdRes> getAds() {
		return ads;
	}
	public void setAds(List<XunfeiAdRes> ads) {
		this.ads = ads;
	}
	private static final long serialVersionUID = 1L;

	private int rc;
	private String id;
	private String bid_id;
	private String info;
	private String cur;
	private List<XunfeiAdRes> ads;

	
}
