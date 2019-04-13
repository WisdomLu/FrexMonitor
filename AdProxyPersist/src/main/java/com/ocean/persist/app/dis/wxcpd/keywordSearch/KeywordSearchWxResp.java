package com.ocean.persist.app.dis.wxcpd.keywordSearch;

import com.ocean.persist.app.dis.AppDisResponse;

public class KeywordSearchWxResp implements AppDisResponse {

	private int code;// 200;
	private KeywordSearchWxMsg msg;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public KeywordSearchWxMsg getMsg() {
		return msg;
	}
	public void setMsg(KeywordSearchWxMsg msg) {
		this.msg = msg;
	}
}
