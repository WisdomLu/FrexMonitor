package com.ocean.persist.api.proxy.zk;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

public class ZKAdTile    implements AdPullResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7564474240261921094L;
	private String text;
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
}
