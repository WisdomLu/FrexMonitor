package com.ocean.persist.api.proxy.zk;

/**
 * 
 * @Description: 广告请求-设备信息-设备类型
 * @author yunhui.zhang
 * @date 2016年6月14日 上午9:45:18
 */
public enum ZKDeviceType {

	/** 未知 */
	UNKNOWN(0),

	/** 平板 */
	TABLET(1),

	/** 手机 */
	PHONE(2),

	;

	private int value;

	public int getValue() {
		return value;
	}

	private ZKDeviceType(int value) {
		this.value = value;
	}
}
