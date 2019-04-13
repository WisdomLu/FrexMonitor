package com.ocean.persist.app.dis.quyuansu.pkgSearch;

import com.ocean.persist.app.dis.AdDisParams;
import com.ocean.persist.app.dis.quyuansu.BaseQuyuansuReq;

public class PkgSearchQuyuansuReq   extends BaseQuyuansuReq  implements AdDisParams{

	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}
	private int num;
    private String packageNames;
	public String getPackageNames() {
		return packageNames;
	}
	public void setPackageNames(String packageNames) {
		this.packageNames = packageNames;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}

}
