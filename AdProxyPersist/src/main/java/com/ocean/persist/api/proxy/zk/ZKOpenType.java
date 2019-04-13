package com.ocean.persist.api.proxy.zk;

public enum ZKOpenType {
	/** 内开，外开都支持 */
	ALL(0),

	/** 内开，由媒体WebView打开 */
	INNER(1),

	/** 外开，由浏览器打开 */
	OUTER(2),

	;

	private int value;

	public int getValue() {
		return value;
	}

	private ZKOpenType(int value) {
		this.value = value;
	}

	public static ZKOpenType valueOf(int value) {
		for (ZKOpenType type : ZKOpenType.values()) {
			if (type.getValue() == value) {
				return type;
			}
		}
		return null;
	}
}
