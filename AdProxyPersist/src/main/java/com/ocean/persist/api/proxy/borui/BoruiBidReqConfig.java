package com.ocean.persist.api.proxy.borui;

public class BoruiBidReqConfig {

	// 必填！提供此 ID
	private Integer ssp_id;
	
	// 必填！提供此 ID
	private Integer medium_id;
	
	// 必填！提供此 ID
	private Integer mads_id;

	public Integer getSsp_id() {
		return ssp_id;
	}

	public void setSsp_id(Integer ssp_id) {
		this.ssp_id = ssp_id;
	}

	public Integer getMedium_id() {
		return medium_id;
	}

	public void setMedium_id(Integer medium_id) {
		this.medium_id = medium_id;
	}

	public Integer getMads_id() {
		return mads_id;
	}

	public void setMads_id(Integer mads_id) {
		this.mads_id = mads_id;
	}
}
