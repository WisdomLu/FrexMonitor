package com.ocean.persist.api.proxy.wanka;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.core.common.threadpool.AbstractInvokeParameter;
import com.ocean.core.common.threadpool.Parameter;


public class WKNativeRequest   extends AbstractInvokeParameter{

	private static final long serialVersionUID = 1L;

	private String ver;
	
	private List<WKNativeAsset> reqAssets;

	public String getVer() {
		return ver;
	}

	public void setVer(String ver) {
		this.ver = ver;
	}

	public List<WKNativeAsset> getReqAssets() {
		return reqAssets;
	}

	public void setReqAssets(List<WKNativeAsset> reqAssets) {
		this.reqAssets = reqAssets;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public boolean validate() {
		return false;
		// TODO Auto-generated method stub
		
	}
}
