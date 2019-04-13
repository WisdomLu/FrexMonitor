package com.ocean.persist.app.dis.haiqibing.appAsyn;

import java.util.List;

import com.ocean.persist.app.dis.AppDisResponse;

public class HaiqibingAppasynResp implements AppDisResponse{
	private int code ;//int 是请求结果码
	private List<HaiqibingAppasynApp> data;// JsonArray 是应用列表
	private String msg;// String 是请求结果信息
	public int getCode() {
		return code;
	}
	public List<HaiqibingAppasynApp> getData() {
		return data;
	}
	public String getMsg() {
		return msg;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public void setData(List<HaiqibingAppasynApp> data) {
		this.data = data;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
