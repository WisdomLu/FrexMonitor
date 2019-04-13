package com.ocean.persist.api.proxy.wanyueV2;

import java.util.List;

public class WanyueV2Data {
	private int ad_type	;//广告类型	number	广告类型:
/*		1 开屏广告	
		2 横幅图片广告
		3 插屏广告	
		4 原生广告	
		5 视频广告	
		6 互动广告	
		7 状态栏广告
		8 横幅图文广告	是*/	
	private List<WanyuV2Creative> creative;//	创意对象	array		是	
	public int getAd_type() {
		return ad_type;
	}
	public List<WanyuV2Creative> getCreative() {
		return creative;
	}
	public void setAd_type(int ad_type) {
		this.ad_type = ad_type;
	}
	public void setCreative(List<WanyuV2Creative> creative) {
		this.creative = creative;
	}
}
