package com.ocean.persist.api.proxy.dianguan;

public class DianguanClient {
	private int type;
	private String version;//请求 aiclk的版本号，当 前版本“ 1.6.11”
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
}
