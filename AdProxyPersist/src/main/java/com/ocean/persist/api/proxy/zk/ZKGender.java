package com.ocean.persist.api.proxy.zk;

public enum ZKGender {

	/** 男性 */
	MALE(1),

	/** 女性 */
	FEMAIL(2),

	/** 第三性 */
	THIRD_GENDER(3),

	;

	private int value;

	public int getValue() {
		return value;
	}

	private ZKGender(int value) {
		this.value = value;
	}

}
