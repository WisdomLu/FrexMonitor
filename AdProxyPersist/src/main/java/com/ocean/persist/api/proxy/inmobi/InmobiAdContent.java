package com.ocean.persist.api.proxy.inmobi;

import java.util.Map;

import com.ocean.core.common.base.AbstractBaseEntity;
public class InmobiAdContent {

	private static final long serialVersionUID = 1L;
	
	private String pubContent;
	private String landingPage;
	private String beaconUrl;
	private Map<String, InmobiAdEventUrls> eventTracking;
	private String requestId;
	public String getPubContent() {
		return pubContent;
	}

	public void setPubContent(String pubContent) {
		this.pubContent = pubContent;
	}

	public String getLandingPage() {
		return landingPage;
	}

	public void setLandingPage(String landingPage) {
		this.landingPage = landingPage;
	}

	public String getBeaconUrl() {
		return beaconUrl;
	}

	public void setBeaconUrl(String beaconUrl) {
		this.beaconUrl = beaconUrl;
	}

	public Map<String, InmobiAdEventUrls> getEventTracking() {
		return eventTracking;
	}

	public void setEventTracking(Map<String, InmobiAdEventUrls> eventTracking) {
		this.eventTracking = eventTracking;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
}
