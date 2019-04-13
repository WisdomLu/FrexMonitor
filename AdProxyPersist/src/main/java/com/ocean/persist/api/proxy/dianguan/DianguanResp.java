package com.ocean.persist.api.proxy.dianguan;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

public class DianguanResp  extends AbstractBaseEntity  implements AdPullResponse {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4713948493509048148L;
	private boolean success;
	private DianguanAd ads;
	private String search_id;
	private String message;
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public DianguanAd getAds() {
		return ads;
	}
	public void setAds(DianguanAd ads) {
		this.ads = ads;
	}
	public String getSearch_id() {
		return search_id;
	}
	public void setSearch_id(String search_id) {
		this.search_id = search_id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
