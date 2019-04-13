package com.ocean.persist.api.proxy.jieku;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;

public class JiekuNetwork {

	private static final long serialVersionUID = 1L;
	
	private String ip;// optional string 设备当前ip，如果客户端直接请求不需要这个字段，服务端请求⼀一定要填客户端真实ip2.1.11
	private Integer type;// required uint32 设备网络环境(1. WIFI2. UNKNOWN3. 2G4. 3G5. 4G)
	private Integer cellular_operator;// required uint32 运营商nop (460001, 460002,460003 …)
	private String cellular_id;// optional string 基站ID
	private List<JiekuWifi> wifis;// optional array of wifi object 周边wifi热点
	
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getCellular_operator() {
		return cellular_operator;
	}

	public void setCellular_operator(Integer cellular_operator) {
		this.cellular_operator = cellular_operator;
	}

	public String getCellular_id() {
		return cellular_id;
	}

	public void setCellular_id(String cellular_id) {
		this.cellular_id = cellular_id;
	}

	public List<JiekuWifi> getWifis() {
		return wifis;
	}

	public void setWifis(List<JiekuWifi> wifis) {
		this.wifis = wifis;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
