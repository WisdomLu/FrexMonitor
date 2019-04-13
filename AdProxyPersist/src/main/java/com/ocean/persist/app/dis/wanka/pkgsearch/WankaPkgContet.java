package com.ocean.persist.app.dis.wanka.pkgsearch;


import com.ocean.persist.app.dis.wanka.WankaApp;

public class WankaPkgContet {
	private int total_cnt;
	private int has_more;
	private WankaApp info;
	public int getTotal_cnt() {
		return total_cnt;
	}
	public int getHas_more() {
		return has_more;
	}
	public WankaApp getInfo() {
		return info;
	}
	public void setTotal_cnt(int total_cnt) {
		this.total_cnt = total_cnt;
	}
	public void setHas_more(int has_more) {
		this.has_more = has_more;
	}
	public void setInfo(WankaApp info) {
		this.info = info;
	}
}
