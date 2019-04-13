package com.ocean.persist.api.proxy.dianguan;

public class DianguanNet {
	private String ip;//设备当前 ip，如果客户 端直接请求不需要这个 端直接请求不需要这个 端直接请求不需要这个 端直接请求不需要这个 端直接请求不需要这个 端直接请求不需要这个 端直接请求不需要这个 端直接请求不需要这个 端直接请求不需要这个 端直接请求不需要这个 字段，服务端请求一定要 字段，服务端请求一定要 填客户端真实 ip。
	private int type;
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
}
