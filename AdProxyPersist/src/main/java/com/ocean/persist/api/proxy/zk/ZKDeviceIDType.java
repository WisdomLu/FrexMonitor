package com.ocean.persist.api.proxy.zk;

/**
 * 
 * @Description: 广告请求-设备信息-设备ID类型
 * @author yunhui.zhang
 * @date 2016年6月8日 上午11:19:59
 */
public enum ZKDeviceIDType {

	/** 未知 */
	UNKNOWN(0),

	/** imei */
	IMEI(1),

	/** iOS idfa */
	IDFA(2),

	/** Android Id */
	AAID(3),

	/** mac */
	MAC(4),

	/** iOS idfv */
	IDFV(5),

	/**  */
	M2ID(6),

	/**  */
	SERIALID(7),

	/** imsi */
	IMSI(8),

	;

	private int value;

	public int getValue() {
		return value;
	}

	private ZKDeviceIDType(int value) {
		this.value = value;
	}
}
