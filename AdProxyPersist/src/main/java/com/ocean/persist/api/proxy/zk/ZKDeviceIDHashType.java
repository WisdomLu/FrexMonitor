package com.ocean.persist.api.proxy.zk;

/**
 * 
 * @Description: 广告请求-设备信息-设备ID加密类型
 * @author yunhui.zhang
 * @date 2016年6月8日 上午11:19:59
 */
public enum ZKDeviceIDHashType {

	/** 未加密 */
	NONE(0),

	/** MD5 加密 */
	MD5(1),

	/** SH1 加密 */
	SH1(2),

	/** OTHER 其他加密 */
	OTHER(3),

	;

	private int value;

	public int getValue() {
		return value;
	}

	private ZKDeviceIDHashType(int value) {
		this.value = value;
	}
}
