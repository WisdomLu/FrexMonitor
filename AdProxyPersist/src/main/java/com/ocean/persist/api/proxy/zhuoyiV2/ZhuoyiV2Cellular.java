package com.ocean.persist.api.proxy.zhuoyiV2;

public class ZhuoyiV2Cellular {
	private int type;//	required	int	基站类型（
/*	0.GSM
	1.CDMA
	）*/
	private String cid;//	required	string	基站编码
	private String lac	;//required	string	位置区域码
	public int getType() {
		return type;
	}
	public String getCid() {
		return cid;
	}
	public String getLac() {
		return lac;
	}
	public void setType(int type) {
		this.type = type;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public void setLac(String lac) {
		this.lac = lac;
	}
}
