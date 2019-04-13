package com.ocean.persist.api.proxy.zk;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum ZKAdmType {

	PIC(0, "PIC"), HTML(1, "HTML"), NATIVE(3, "NATIVE");

	private int type;
	private String name;

	private static final Map<String, ZKAdmType> byNameMap = new HashMap<String, ZKAdmType>();
	static {
		for (ZKAdmType t : EnumSet.allOf(ZKAdmType.class)) {
			byNameMap.put(t.getName(), t);
		}
	}

	public static ZKAdmType getByName(String name) {
		return byNameMap.get(name);
	}

	public static ZKAdmType getByType(int type) {
		switch (type) {
		case 0:
			return PIC;
		case 1:
			return HTML;
		case 3:
			return NATIVE;
		default:
			break;
		}
		return null;
	}

	public int getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	private ZKAdmType(int type, String name) {
		this.type = type;
		this.name = name;

	}

	@Override
	public String toString() {
		return getType() + "";
	}
}
