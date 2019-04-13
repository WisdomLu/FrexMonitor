package com.ocean.persist.lbs.baidu.ip;

import com.ocean.core.common.threadpool.AbstractInvokeResponse;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年8月7日 
      @version 1.0 
 */
public class IPLocatinResp extends AbstractInvokeResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7610016277411512026L;
	  
    private String address;// "CN|吉林|长春|None|CERNET|1|None",  
    private IPLocationContent content;// 
    private int status;//
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public IPLocationContent getContent() {
		return content;
	}
	public void setContent(IPLocationContent content) {
		this.content = content;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
