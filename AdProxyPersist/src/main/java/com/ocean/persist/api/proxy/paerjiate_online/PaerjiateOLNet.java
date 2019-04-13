package com.ocean.persist.api.proxy.paerjiate_online;

public class PaerjiateOLNet {
	private String ua;//": "Mozilla/5.0 (iPhone; CPU iPhone OS 10_2_1 like Mac OS X) AppleWebKit/ 602.4.6 (KHTML, like Gecko) Mobile/14D27",
    private String ip;//": "122.195.224.220",
    private int ipType;//": 0,
    private int httpType;//": 0
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
	public int getIpType() {
		return ipType;
	}
	public void setIpType(int ipType) {
		this.ipType = ipType;
	}
	public int getHttpType() {
		return httpType;
	}
	public void setHttpType(int httpType) {
		this.httpType = httpType;
	}

}
