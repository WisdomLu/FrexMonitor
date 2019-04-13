package com.ocean.service.youyouInvokeHandler.base;

public enum YouyouMethodType {
	PKG_SEARCH(1, "pkgSearch");
	private int value;
	private String name;

	private YouyouMethodType(int value, String name) {
		this.value = value;
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
