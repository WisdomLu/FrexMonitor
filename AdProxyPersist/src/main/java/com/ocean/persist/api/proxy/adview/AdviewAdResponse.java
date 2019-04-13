package com.ocean.persist.api.proxy.adview;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

public class AdviewAdResponse  implements AdPullResponse{

	private static final long serialVersionUID = 1L;

	public static final int success = 1;
	
	private Integer res;// 广告回应情况标识 	0=>失败，1=>成功 
	private String mg;// 广告回应失败的原因（适用于失败的情况） 	 
	private Integer co;// 返回的广告条数 	1 
	private List<AdviewAdContent> ad;// 广告信息，正确返回 0 或多个广告 	 
	public Integer getRes() {
		return res;
	}
	public void setRes(Integer res) {
		this.res = res;
	}
	public String getMg() {
		return mg;
	}
	public void setMg(String mg) {
		this.mg = mg;
	}
	public Integer getCo() {
		return co;
	}
	public void setCo(Integer co) {
		this.co = co;
	}
	public List<AdviewAdContent> getAd() {
		return ad;
	}
	public void setAd(List<AdviewAdContent> ad) {
		this.ad = ad;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
