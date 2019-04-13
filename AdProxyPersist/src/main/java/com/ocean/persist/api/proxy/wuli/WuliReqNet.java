package com.ocean.persist.api.proxy.wuli;

public class WuliReqNet {

	private String ip;
	
	private String mac;
	
	private String network;
	
	private String netOperator;
	
	private String ua;

	public String getUa() {
		return ua;
	}

	public void setUa(String ua) {
		this.ua = ua;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getNetwork() {
		return network;
	}

	public void setNetwork(String network) {
		this.network = network;
	}

	public String getNetOperator() {
		return netOperator;
	}

	public void setNetOperator(String netOperator) {
		this.netOperator = netOperator;
	}
}
