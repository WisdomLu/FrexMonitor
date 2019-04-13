package com.ocean.persist.api.proxy.wanka;

import com.ocean.core.common.base.AbstractBaseEntity;

public class WKNativeAsset {

	private static final long serialVersionUID = 1L;

	// 原生广告类型。1：信息流组图，2：信息流小图，3：信息流大图
	private Integer type;
	
	private WKAssetImg reqImg;
	
	private WKAssetTitle reqTitle;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public WKAssetImg getReqImg() {
		return reqImg;
	}

	public void setReqImg(WKAssetImg reqImg) {
		this.reqImg = reqImg;
	}

	public WKAssetTitle getReqTitle() {
		return reqTitle;
	}

	public void setReqTitle(WKAssetTitle reqTitle) {
		this.reqTitle = reqTitle;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
