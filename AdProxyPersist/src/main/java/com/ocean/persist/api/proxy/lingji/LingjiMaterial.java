package com.ocean.persist.api.proxy.lingji;

/**
 * "I"表示图片物料，"F"表示Flash物料，"V"表示视频物料，
 * "X"表示多点击地址Flash物料/富媒体物料(多点击地址Flash物料是已嵌入跳转地址和Click监测代码的物料)，
 * "C"表示动态物料(html创意代码)，"R"表示原生信息流广告 
 */
public enum LingjiMaterial {
	
	/** 表示图片物料*/
	I("I"),
	
	/** Flash*/
	X("X"),
	
	/** 表示多点击地址Flash物料/富媒体物料(多点击地址Flash物料是已嵌入跳转地址和Click监测代码的物料)*/
	F("F"),
	
	/** 表示视频物料*/
	V("V"),
	
	/** 表示动态物料(html创意代码)*/
	C("C"),
	
	/** 表示原生信息流广告 */
	R("R");
	
	private final String code;
	
	private LingjiMaterial(String code) {
		this.code = code;
	}
	public String getCode(){
		return code;
	}
}
