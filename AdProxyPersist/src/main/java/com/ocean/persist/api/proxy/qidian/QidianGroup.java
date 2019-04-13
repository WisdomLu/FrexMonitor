package com.ocean.persist.api.proxy.qidian;

import java.util.List;

public class QidianGroup {
	private int impId;
	private List<QidianAdV2> ads;
	public int getImpId() {
		return impId;
	}
	public List<QidianAdV2> getAds() {
		return ads;
	}
	public void setImpId(int impId) {
		this.impId = impId;
	}
	public void setAds(List<QidianAdV2> ads) {
		this.ads = ads;
	}
	
}
