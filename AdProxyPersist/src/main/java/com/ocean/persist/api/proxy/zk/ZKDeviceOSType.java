package com.ocean.persist.api.proxy.zk;

/**
 * 
 * @Description: 广告请求-设备信息-设备操作系统类型
 * @author yunhui.zhang
 * @date 2016年6月14日 上午9:45:18
 */
public enum ZKDeviceOSType {

	OS_UNKNOWN  (0),  //未知
	OS_IOS (1),
	OS_ANDROID (2),
	OS_WP (3);  //windows


	private int value;

	public int getValue() {
		return value;
	}

	private ZKDeviceOSType(int value) {
		this.value = value;
	}
}
