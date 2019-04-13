package com.ocean.persist.app.dis.wxcpd.asyn;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.app.dis.AppDisResponse;

public class WxAsynAppResponse   extends AbstractBaseEntity implements AppDisResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3968895131882524187L;

	private int code;
	private String msg;
	private List<WxAsynApp> data;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<WxAsynApp> getData() {
		return data;
	}
	public void setData(List<WxAsynApp> data) {
		this.data = data;
	}
}
