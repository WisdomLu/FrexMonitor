package com.ocean.persist.app.dis.tianmei.getAppList;

import com.ocean.persist.app.dis.AdDisParams;
public class TianmeiAppListReq implements AdDisParams{

	
	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}
	private String sign;
	private String urlParams;
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getUrlParams() {
		return urlParams;
	}
	public void setUrlParams(String urlParams) {
		this.urlParams = urlParams;
	}
}
