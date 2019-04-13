package com.ocean.persist.api.proxy.zk;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum ZKAdSpaceType {

	BANNER(1, "BANNER"), OPENING(2, "OPENING"), INTERSTITIAL(3, "INTERSTITIAL"), NATIVE(4, "NATIVE"), TEXT(5, "TEXT");

	private static final Map<String, ZKAdSpaceType> byNameMap = new HashMap<String, ZKAdSpaceType>();

	static {
		for (ZKAdSpaceType t : EnumSet.allOf(ZKAdSpaceType.class)) {
			byNameMap.put(t.getName(), t);
		}
	}

	public static ZKAdSpaceType getByName(String name) {
		return byNameMap.get(name);
	}

	public static ZKAdSpaceType getByType(int type) {
		switch (type) {
		case 1:
			return BANNER;
		case 2:
			return OPENING;
		case 3:
			return INTERSTITIAL;
		case 4:
			return NATIVE;
		case 5:
			return TEXT;
		default:
			break;
		}
		return null;
	}

	private int type;
	private String name;

	private ZKAdSpaceType(int type, String name) {
		this.name = name;
		this.type = type;

	}

	public int getType() {
		return type;
	}

	public String getName() {
		return name;
	}

}
