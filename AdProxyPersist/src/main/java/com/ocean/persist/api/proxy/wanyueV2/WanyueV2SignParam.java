package com.ocean.persist.api.proxy.wanyueV2;

import com.ocean.core.common.threadpool.AbstractInvokeParameter;

public class WanyueV2SignParam  extends AbstractInvokeParameter{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4921179885785977705L;

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}
	private String sign;
	private String paramJson;
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getParamJson() {
		return paramJson;
	}
	public void setParamJson(String paramJson) {
		this.paramJson = paramJson;
	}
}
