package com.ocean.persist.api.proxy.wuli;

import java.util.List;

public class WuliRespAdmInfo {

	private WuliRespImgVideoInfo logo;
	
	private String brandName;
	
	private Integer duration;
	
	private List<WuliRespImgVideoInfo> details;

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public WuliRespImgVideoInfo getLogo() {
		return logo;
	}

	public void setLogo(WuliRespImgVideoInfo logo) {
		this.logo = logo;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public List<WuliRespImgVideoInfo> getDetails() {
		return details;
	}

	public void setDetails(List<WuliRespImgVideoInfo> details) {
		this.details = details;
	}
}
