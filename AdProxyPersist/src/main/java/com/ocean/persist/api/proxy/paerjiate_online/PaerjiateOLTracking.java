package com.ocean.persist.api.proxy.paerjiate_online;

import java.util.List;

public class PaerjiateOLTracking {
    private int materialMetaIndex;
    private int delay;
    private int trackingEventType;
    private List<String> trackingUrls;
	public int getMaterialMetaIndex() {
		return materialMetaIndex;
	}
	public void setMaterialMetaIndex(int materialMetaIndex) {
		this.materialMetaIndex = materialMetaIndex;
	}
	public int getDelay() {
		return delay;
	}
	public void setDelay(int delay) {
		this.delay = delay;
	}
	public int getTrackingEventType() {
		return trackingEventType;
	}
	public void setTrackingEventType(int trackingEventType) {
		this.trackingEventType = trackingEventType;
	}
	public List<String> getTrackingUrls() {
		return trackingUrls;
	}
	public void setTrackingUrls(List<String> trackingUrls) {
		this.trackingUrls = trackingUrls;
	}
}
