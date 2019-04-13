package com.ocean.persist.app.dis.haiqibing.pkgSearch;

import com.ocean.persist.app.dis.AppDisResponse;

public class HaiqibingPkgSchResp   implements AppDisResponse{
	private int code;// int 是请求结果码
	private HaiqibingPkgSchApp data;// Json 是应用信息
	private String msg ;//String 是请求结果信息
	public int getCode() {
		return code;
	}
	public HaiqibingPkgSchApp getData() {
		return data;
	}
	public String getMsg() {
		return msg;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public void setData(HaiqibingPkgSchApp data) {
		this.data = data;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
