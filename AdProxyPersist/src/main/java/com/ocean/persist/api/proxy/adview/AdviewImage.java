package com.ocean.persist.api.proxy.adview;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;


public class AdviewImage  implements AdPullResponse{

	private static final long serialVersionUID = 1L;
	private String url;
	private Integer w;
	private Integer h;
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getW() {
		return w;
	}

	public void setW(Integer w) {
		this.w = w;
	}

	public Integer getH() {
		return h;
	}

	public void setH(Integer h) {
		this.h = h;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
