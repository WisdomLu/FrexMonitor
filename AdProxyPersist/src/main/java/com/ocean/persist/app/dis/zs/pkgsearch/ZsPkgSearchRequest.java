package com.ocean.persist.app.dis.zs.pkgsearch;

import com.ocean.persist.app.dis.zs.ZsBaseSearchRequest;

public class ZsPkgSearchRequest extends ZsBaseSearchRequest {
	private String package_names;

	public String getPackage_names() {
		return package_names;
	}

	public void setPackage_names(String package_names) {
		this.package_names = package_names;
	}
}
