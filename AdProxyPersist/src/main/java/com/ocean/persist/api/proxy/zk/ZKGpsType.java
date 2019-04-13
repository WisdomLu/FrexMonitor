package com.ocean.persist.api.proxy.zk;

public enum ZKGpsType {

	/** 全球卫星定位系统坐标系 */
	WGS84(1),

	/** 国家测绘局坐标系 */
	GCJ02(2),

	/** 百度坐标系 */
	BD09(3),

	;

	private int value;

	public int getValue() {
		return value;
	}

	private ZKGpsType(int value) {
		this.value = value;
	}
}
