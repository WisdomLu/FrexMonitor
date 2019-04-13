package com.ocean.persist.api.proxy.zk;

public enum ZKEventType {
	   SHOW(1,"SHOW"),
	   CLICK(2,"CLICK"),
	   OPEN (3,"OPEN"),
	   DOWNLOAD(4,"DOWNLOAD"),
	   INSTALL (5,"INSTALL"),
	   ACTIVE (6,"ACTIVE");
	   private int value;
	   private String name;
	private ZKEventType(int value, String name) {
		this.name = name;
		this.value = value;

	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
}
