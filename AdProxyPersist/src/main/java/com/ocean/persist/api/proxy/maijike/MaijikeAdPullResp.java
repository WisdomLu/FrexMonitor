package com.ocean.persist.api.proxy.maijike;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

public class MaijikeAdPullResp   extends AbstractBaseEntity  implements AdPullResponse{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5820104729307315712L;
	private String res;
	private List<MaijikeAd> ads;
	public String getRes() {
		return res;
	}
	public List<MaijikeAd> getAds() {
		return ads;
	}
	public void setRes(String res) {
		this.res = res;
	}
	public void setAds(List<MaijikeAd> ads) {
		this.ads = ads;
	}
}
