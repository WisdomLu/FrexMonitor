package com.ocean.persist.api.proxy.zk;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

public class ZKAd     implements AdPullResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5579739421473398566L;

	/** 广告位ID */
	private String adspace_id;

	/** 一个广告可以有多个创意 */
	private List<ZKCreative> creative;

	public String getAdspace_id() {
		return adspace_id;
	}

	public void setAdspace_id(String adspace_id) {
		this.adspace_id = adspace_id;
	}

	public List<ZKCreative> getCreative() {
		return creative;
	}

	public void setCreative(List<ZKCreative> creative) {
		this.creative = creative;
	}

	
}