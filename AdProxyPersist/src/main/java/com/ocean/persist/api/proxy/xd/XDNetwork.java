package com.ocean.persist.api.proxy.xd;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullParams;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年7月10日 
      @version 1.0 
 */
public class XDNetwork    {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8481833015805858105L;
	private String ip;
	private int connectionType;
	private int operatorType;
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getConnectionType() {
		return connectionType;
	}
	public void setConnectionType(int connectionType) {
		this.connectionType = connectionType;
	}
	public int getOperatorType() {
		return operatorType;
	}
	public void setOperatorType(int operatorType) {
		this.operatorType = operatorType;
	}

}
