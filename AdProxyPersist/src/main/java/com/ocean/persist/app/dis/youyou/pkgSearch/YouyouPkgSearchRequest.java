package com.ocean.persist.app.dis.youyou.pkgSearch;

import java.util.Vector;

import com.ocean.persist.app.dis.AdDisParams;
import com.ocean.persist.app.dis.youyou.BaseYouyouReq;

public class YouyouPkgSearchRequest extends BaseYouyouReq implements AdDisParams {

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}

	// 动态参数
	private Vector<String> appList = new Vector<String>();

	public Vector<String> getAppList() {
		return appList;
	}

	public void setAppList(Vector<String> appList) {
		this.appList = appList;
	}

}
