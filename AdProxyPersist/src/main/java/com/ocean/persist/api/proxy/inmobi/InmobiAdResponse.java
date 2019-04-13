package com.ocean.persist.api.proxy.inmobi;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

public class InmobiAdResponse  extends AbstractBaseEntity implements AdPullResponse{

	private static final long serialVersionUID = 1L;

	private String requestId;
	private List<InmobiAdContent> ads;
	
	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public List<InmobiAdContent> getAds() {
		return ads;
	}

	public void setAds(List<InmobiAdContent> ads) {
		this.ads = ads;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
