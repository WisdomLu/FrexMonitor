package com.ocean.persist.api.proxy.zk;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum ZKAssetType {

	TITLE(1, "TITLE"), TEXT(2, "TEXT"), ICON_IMAGE(3, "ICON_IMAGE"), MAIN_IMAGE(4, "MAIN_IMAGE");

	private static final Map<String, ZKAssetType> byNameMap = new HashMap<String, ZKAssetType>();

	static {
		for (ZKAssetType t : EnumSet.allOf(ZKAssetType.class)) {
			byNameMap.put(t.getName(), t);
		}
	}

	public static ZKAssetType getByName(String name) {
		return byNameMap.get(name);
	}

	public static ZKAssetType getByType(int type) {
		switch (type) {
		case 1:
			return TITLE;
		case 2:
			return TEXT;
		case 3:
			return ICON_IMAGE;
		case 4:
			return MAIN_IMAGE;
		default:
			break;
		}
		return null;
	}

	private int type;
	private String name;

	private ZKAssetType(int type, String name) {
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
