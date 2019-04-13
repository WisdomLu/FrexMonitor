package com.ocean.persist.api.proxy.zk;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullParams;

public class ZKDeviceID    {


	/**
	 * 
	 */
	private static final long serialVersionUID = 2627535848898448463L;
	private String device_id;
	private int device_id_type;
	private int hash_type;// ID 加密类型
	public String getDevice_id() {
		return device_id;
	}
	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}
	public int getDevice_id_type() {
		return device_id_type;
	}
	public void setDevice_id_type(int device_id_type) {
		this.device_id_type = device_id_type;
	}
	public int getHash_type() {
		return hash_type;
	}
	public void setHash_type(int hash_type) {
		this.hash_type = hash_type;
	}

/*	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("(id=" + id);
		sb.append(",idType=").append(idType);
		sb.append(",hashType=").append(hashType).append(")");
		return sb.toString();
	}*/

}
