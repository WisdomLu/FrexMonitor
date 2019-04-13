package com.ocean.persist.api.proxy.zhuoyiV2;

public class ZhuoyiV2Client {
	private int type	;//required	int	广告客户端类型（
/*	1.NATIVESDK
	2.JSSDK
	3.OPENAPI）*/
	private ZhuiyiV2Version version	;//required	version object	版本（
/*	1.API 接入目前版本是 2.3.7
	2.SDK 接入目前版本是 1.0）*/
	private int h5	;//optional	int	是否支持 html5
	//0:不支持(默认) 1:支持
	private int deeplink	;//optional	int	是否支持 deeplink
	//0:不支持(默认) 1:支持
	public int getType() {
		return type;
	}
	public ZhuiyiV2Version getVersion() {
		return version;
	}
	public int getH5() {
		return h5;
	}
	public int getDeeplink() {
		return deeplink;
	}
	public void setType(int type) {
		this.type = type;
	}
	public void setVersion(ZhuiyiV2Version version) {
		this.version = version;
	}
	public void setH5(int h5) {
		this.h5 = h5;
	}
	public void setDeeplink(int deeplink) {
		this.deeplink = deeplink;
	}
}
