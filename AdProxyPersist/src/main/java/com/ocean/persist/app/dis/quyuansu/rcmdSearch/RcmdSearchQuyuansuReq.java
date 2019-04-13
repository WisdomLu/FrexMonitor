package com.ocean.persist.app.dis.quyuansu.rcmdSearch;

import com.ocean.persist.app.dis.AdDisParams;
import com.ocean.persist.app.dis.quyuansu.BaseQuyuansuReq;

public class RcmdSearchQuyuansuReq   extends BaseQuyuansuReq  implements AdDisParams{

	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}
	private int num;
	private String recom_appname;
	private String recom_apppkg;
	public int getNum() {
		return num;
	}
	public String getRecom_appname() {
		return recom_appname;
	}
	public String getRecom_apppkg() {
		return recom_apppkg;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public void setRecom_appname(String recom_appname) {
		this.recom_appname = recom_appname;
	}
	public void setRecom_apppkg(String recom_apppkg) {
		this.recom_apppkg = recom_apppkg;
	}

}
