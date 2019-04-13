package com.ocean.persist.api.proxy.zk;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

public class ZKAdEventTrack    implements AdPullResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2003000065105923342L;
	private int event_type;
	private String notify_url;
	public String getNotify_url() {
		return notify_url;
	}
	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}
	public int getEvent_type() {
		return event_type;
	}
	public void setEvent_type(int event_type) {
		this.event_type = event_type;
	}
}
