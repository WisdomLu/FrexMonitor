package com.ocean.persist.api.proxy.yileyun;
/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年3月13日 
      @version 1.0 
 */
public class YileyunAd {
	private String ad_id;/*
	string
	Required
	创意 id*/
	private YileyunMaterial creative;/*
	MaterialMeta
	Required
	广告物料*/
	private long price;/*
	Uint64
	Optional
	出价*/
	public String getAd_id() {
		return ad_id;
	}
	public void setAd_id(String ad_id) {
		this.ad_id = ad_id;
	}
	public YileyunMaterial getCreative() {
		return creative;
	}
	public void setCreative(YileyunMaterial creative) {
		this.creative = creative;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
}
