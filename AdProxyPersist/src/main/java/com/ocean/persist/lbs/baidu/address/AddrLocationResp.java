package com.ocean.persist.lbs.baidu.address;

import com.ocean.core.common.threadpool.AbstractInvokeResponse;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年8月7日 
      @version 1.0 
 */
public class AddrLocationResp extends AbstractInvokeResponse{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int status;// 	Int 	返回结果状态值， 成功返回0，其他值请查看下方返回码状态表。
	private AddrLocationResult result;
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public AddrLocationResult getResult() {
		return result;
	}
	public void setResult(AddrLocationResult result) {
		this.result = result;
	}
	
}
