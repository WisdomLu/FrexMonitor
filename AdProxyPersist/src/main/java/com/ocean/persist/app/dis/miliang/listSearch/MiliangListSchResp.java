package com.ocean.persist.app.dis.miliang.listSearch;

import java.util.List;

import com.ocean.persist.app.dis.AppDisResponse;

public class MiliangListSchResp   implements AppDisResponse{
    private String res;
    private String msg;
    private List<MiliangAd> ads;
	public String getRes() {
		return res;
	}
	public List<MiliangAd> getAds() {
		return ads;
	}
	public void setRes(String res) {
		this.res = res;
	}
	public void setAds(List<MiliangAd> ads) {
		this.ads = ads;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
