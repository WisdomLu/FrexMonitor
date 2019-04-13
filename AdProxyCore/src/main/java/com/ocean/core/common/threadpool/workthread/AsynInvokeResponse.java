package com.ocean.core.common.threadpool.workthread;

import com.ocean.core.common.threadpool.AbstractInvokeResponse;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年7月28日 
      @version 1.0 
 */
public class AsynInvokeResponse extends AbstractInvokeResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 135574990692345726L;
    private Object data;
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
