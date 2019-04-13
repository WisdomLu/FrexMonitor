package com.ocean.persist.api.proxy.oneway;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

public class OnewayAdPullResp  extends AbstractBaseEntity  implements AdPullResponse{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5491064765501934444L;
	private boolean success;//	boolean	状态值，true代表正常，false代表错误
	private String message	;//	String	消息内容
	private int errorCode	;//	int	错误码
	private OnewayData data;//		Object	广告信息
	public boolean isSuccess() {
		return success;
	}
	public String getMessage() {
		return message;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public OnewayData getData() {
		return data;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public void setData(OnewayData data) {
		this.data = data;
	}
}
