package com.ocean.persist.api.proxy.boclick;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;
public class BoclickAdResponse  implements AdPullResponse{

	private static final long serialVersionUID = 1L;

	private List<BoclickAdData> ad;

	public List<BoclickAdData> getAd() {
		return ad;
	}

	public void setAd(List<BoclickAdData> ad) {
		this.ad = ad;
	}
}
