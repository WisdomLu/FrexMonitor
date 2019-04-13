package com.ocean.persist.api.proxy.mex;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;

import com.ocean.persist.api.proxy.AdPullParams;
public class MexAdm  {

	private static final long serialVersionUID = 1L;
	
	private List<MexAsset> assets;// recommended

	private String link;
	
	public List<MexAsset> getAssets() {
		return assets;
	}

	public void setAssets(List<MexAsset> assets) {
		this.assets = assets;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
