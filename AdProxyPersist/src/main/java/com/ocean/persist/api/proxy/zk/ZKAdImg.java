package com.ocean.persist.api.proxy.zk;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

public class ZKAdImg    implements AdPullResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3399456570019638444L;
    private String url;
    private int width;
    private int height;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
}
