package com.ocean.persist.api.proxy.boyuV2;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

public class BoyuV2AdPullResp   extends AbstractBaseEntity  implements AdPullResponse{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7932125105158985755L;
	private int code	;//int	0											系统响应码，0为正常，其他为异常。详见系统	响应码表	
	private String msg	;//string													错误提示	
	private BoyuV2Data data;//
	public int getCode() {
		return code;
	}
	public String getMsg() {
		return msg;
	}
	public BoyuV2Data getData() {
		return data;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public void setData(BoyuV2Data data) {
		this.data = data;
	}
}
