package com.ocean.persist.app.dis.qqDownloader;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.app.dis.AppDisResponse;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年5月15日 
      @version 1.0 
 */
public class ResponseHead   implements AppDisResponse{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9063863122629607761L;
	private String callbackPara;
	private int nonce;
	private int ret;
	private String signature;
	public String getCallbackPara() {
		return callbackPara;
	}
	public void setCallbackPara(String callbackPara) {
		this.callbackPara = callbackPara;
	}
	public int getNonce() {
		return nonce;
	}
	public void setNonce(int nonce) {
		this.nonce = nonce;
	}
	public int getRet() {
		return ret;
	}
	public void setRet(int ret) {
		this.ret = ret;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
}
