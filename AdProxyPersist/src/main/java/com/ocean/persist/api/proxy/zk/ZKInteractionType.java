package com.ocean.persist.api.proxy.zk;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum ZKInteractionType {
	
	ANY(0, "any"), 
	
	NO_INTERACTION(1, "NO_INTERACTION"), 
	
	BROWSE(2, "BROWSE"), 
	
	DOWNLOAD(3, "DOWNLOAD"), 
	
	DIALING(4, "DIALING"), 
	
	MESSAGE(5, "MESSAGE"), 
	
	MAIL(6, "MAIL"),
	
	VEDIO(7, "VEDIO"),
	
	;

	private String name;
	private int type;

	private static Map<String, ZKInteractionType> byNameMap = new HashMap<String, ZKInteractionType>();
	static {
		for (ZKInteractionType type : EnumSet.allOf(ZKInteractionType.class)) {
			byNameMap.put(type.getName(), type);
		}
	}

	private ZKInteractionType(int type, String name) {
		this.name = name;
		this.type = type;
	}

	public static ZKInteractionType findByType(int type) {
		switch (type) {
		case 0:
			return ANY;
		case 1:
			return NO_INTERACTION;
		case 2:
			return BROWSE;
		case 3:
			return DOWNLOAD;
		case 4:
			return DIALING;
		case 5:
			return MESSAGE;
		case 6:
			return MAIL;
		default:
			break;
		}
		return null;
	}

	public static ZKInteractionType getByName(String name) {
		return byNameMap.get(name);
	}

	public String getName() {
		return name;
	}

	public int getType() {
		return type;
	}

	@Override
	public String toString() {
		return getType() + "";
	}

}
