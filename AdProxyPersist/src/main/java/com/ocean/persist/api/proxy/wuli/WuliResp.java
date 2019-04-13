package com.ocean.persist.api.proxy.wuli;

import com.ocean.persist.api.proxy.AdPullResponse;

public class WuliResp implements AdPullResponse {

	private String code;

	private String message;
	
	private String requestId;
	
	private Long requestStartTime;
	
	private Long requestEndTime;
	
	private WuliRespData data;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public Long getRequestStartTime() {
		return requestStartTime;
	}

	public void setRequestStartTime(Long requestStartTime) {
		this.requestStartTime = requestStartTime;
	}

	public Long getRequestEndTime() {
		return requestEndTime;
	}

	public void setRequestEndTime(Long requestEndTime) {
		this.requestEndTime = requestEndTime;
	}

	public WuliRespData getData() {
		return data;
	}

	public void setData(WuliRespData data) {
		this.data = data;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
