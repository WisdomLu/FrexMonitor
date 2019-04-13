package com.ocean.persist.api.proxy.zk;

public class ZKSCell {
	private Integer MCC;
	private Integer MNC;
	private Integer LAC;
	private Integer CID;
	public Integer getMCC() {
		return MCC;
	}
	public void setMCC(Integer mCC) {
		MCC = mCC;
	}
	public Integer getMNC() {
		return MNC;
	}
	public void setMNC(Integer mNC) {
		MNC = mNC;
	}
	public Integer getLAC() {
		return LAC;
	}
	public void setLAC(Integer lAC) {
		LAC = lAC;
	}
	public Integer getCID() {
		return CID;
	}
	public void setCID(Integer cID) {
		CID = cID;
	}
}
