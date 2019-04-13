package com.ocean.service.zsInvokeHandler.base;

public enum ZsMethodType {
	APP_LIST_SEARCH(1, "appListSearch"), PKG_SEARCH(2, "pkgSearch"), APP_LIST_ASYN_SEARCH(
			3, "appListAsynSearch");
	private int value;
	private String name;

	private ZsMethodType(int value, String name) {
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
