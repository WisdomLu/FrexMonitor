package com.ocean.persist.api.proxy.zk;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullParams;

public class ZKNetworkInfo    {

	/**
	 * 
	 */
	private static final long serialVersionUID = -83546615333916668L;

	/** 客户IP */
	private String ip;

	/** 网络类型 */
	private int network_type;

	/** 运营商类型 */
	private int carrier_id;

	private ZKSCell cellular_id  ;
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getNetwork_type() {
		return network_type;
	}

	public void setNetwork_type(int network_type) {
		this.network_type = network_type;
	}

	public int getCarrier_id() {
		return carrier_id;
	}

	public void setCarrier_id(int carrier_id) {
		this.carrier_id = carrier_id;
	}

	public ZKSCell getCellular_id() {
		return cellular_id;
	}

	public void setCellular_id(ZKSCell cellular_id) {
		this.cellular_id = cellular_id;
	}

}
