package com.ocean.persist.app.dis.quyuansu;

import java.util.List;

import com.ocean.persist.app.dis.AppDisResponse;

public class BaseQuyuansuResp implements AppDisResponse {
	private int code;
	private String msg;
	private List<QuyuansuApp> data;
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<QuyuansuApp> getData() {
		return data;
	}
	public void setData(List<QuyuansuApp> data) {
		this.data = data;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
}