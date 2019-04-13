package com.ocean.persist.api.proxy.boyuV2;

import java.util.List;

public class BoyuV2Group {
	private int impId;//	int	0											impId，对应请求中imps结构中的id	
	private List<BoyuV2Ad> ads;//	array													对应某种规格下的广告列表	
	public int getImpId() {
		return impId;
	}
	public List<BoyuV2Ad> getAds() {
		return ads;
	}
	public void setImpId(int impId) {
		this.impId = impId;
	}
	public void setAds(List<BoyuV2Ad> ads) {
		this.ads = ads;
	}
	
}
