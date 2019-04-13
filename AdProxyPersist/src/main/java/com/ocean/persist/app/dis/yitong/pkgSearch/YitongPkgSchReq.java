package com.ocean.persist.app.dis.yitong.pkgSearch;

import com.ocean.persist.app.dis.yitong.applist.ListSearchYitongReq;

public class YitongPkgSchReq   extends ListSearchYitongReq{
	private String packagenames;

	public String getPackagenames() {
		return packagenames;
	}

	public void setPackagenames(String packagenames) {
		this.packagenames = packagenames;
	}
}
