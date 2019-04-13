package com.ocean.persist.app.dis.qqDownloader;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.app.dis.AdDisParams;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年5月12日 
      @version 1.0 
 */
public class RequestHead     implements AdDisParams{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5265555588841729747L;
    private String businessId;//应用宝后台为接入业务分配的业务Id，string类型（必填）
    private String  callbackPara;//回调标识，服务器会原样返回该字段以唯一标识该请求，（必填）
    private int nonce;//随机数，每次请求时随机生成，unsigned int 类型（必填）
    private int timestamp;//请求发起时的时间戳，单位为秒，unsigned int 类型（必填）
    private String client_ip;//客户端网关IP（必填）
    private Terminal terminal;//设备信息
    public static final String BUSINESSID="businessId";
    
	public boolean validate() {
		// TODO Auto-generated method stub
		return true;
	}


	public String getBusinessId() {
		return businessId;
	}


	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}


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


	public int getTimestamp() {
		return timestamp;
	}


	public void setTimestamp(int timestamp) {
		this.timestamp = timestamp;
	}


	public String getClient_ip() {
		return client_ip;
	}


	public void setClient_ip(String client_ip) {
		this.client_ip = client_ip;
	}


	public Terminal getTerminal() {
		return terminal;
	}


	public void setTerminal(Terminal terminal) {
		this.terminal = terminal;
	}

}
