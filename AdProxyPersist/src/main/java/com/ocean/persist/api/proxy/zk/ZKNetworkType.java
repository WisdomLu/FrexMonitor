package com.ocean.persist.api.proxy.zk;

/**
 * 
 * @Description: 广告请求-网络信息-网络类型
 * @author yunhui.zhang
 * @date 2016年6月14日 上午9:45:18
 */
public enum ZKNetworkType {

	/** 未知 */
	UNKNOWN(0),

	/** WIFI */
	WIFI(1),

	/** 2G */
	MOBILE_2G(2),

	/** 3G */
	MOBILE_3G(3),

	/** 4G */
	MOBILE_4G(4),

	;

	private int value;

	public int getValue() {
		return value;
	}

	private ZKNetworkType(int value) {
		this.value = value;
	}
}
