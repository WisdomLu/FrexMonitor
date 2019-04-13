package com.ocean.persist.api.proxy.zk;

public enum ZKCarrierType {

	/** 未知 */
	UNKNOWN(0),

	/** 中国移动 */
	CHINA_MOBILE(70120),

	/** 中国电信 */
	CHINA_TELECOM(70121),

	/** 中国联通 */
	CHINA_UNICOM(70123),

	;

	private int value;

	public int getValue() {
		return value;
	}

	private ZKCarrierType(int value) {
		this.value = value;
	}
}
