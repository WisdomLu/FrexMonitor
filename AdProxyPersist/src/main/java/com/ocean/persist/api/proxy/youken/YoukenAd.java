package com.ocean.persist.api.proxy.youken;

import java.util.List;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年2月27日 
      @version 1.0 
 */
public class YoukenAd {
	private String adslot_id;/*
	string
	是
	对应请求时填写的广告位 ID*/
	private int creative_type;
	private int ad_type;/*
	int
	否
	广告类型，保留字段*/
    private int interaction_type;/*
	int
	1
	是
	0：无动作，1：应用内打开网页，2： 下载应用。3:使用浏览器打开*/
	private String bundle;/*
	String
	否
	下载类广告，Android 应用包名， 例：”com.wk.demo”*/
	private YoukenNativ nativ; 
	private YoukenMix mix;/*
	Nativ
	否
	原生广告。creative_type 为 5 时， 该字段有值。*/
	private String imgurl;
	
	private int w;/*
	int
	否
	素材宽度*/
	private int h;/*
	int
	否
	素材高度*/
	private String clkurl;/*
	String
	否
	广告点击跳转地址*/
	private List<String> imptrackers;//	array of string		否	曝光追踪地址，允许有多个追踪地址
	private List<String> clktrackers;//		array of string		否	点击追踪地址，允许有多个追踪地址
	private List<String> dwnlst	;//	array of string		否	下载开始监播
	private List<String> dwnltrackers;//		array of string		否	下载完成监播
	private List<String> intltrackers	;//	array of string		否	安装监播
	private List<String> actvtrackers;//		array of string		否	激活监播

	

	public String getAdslot_id() {
		return adslot_id;
	}
	public void setAdslot_id(String adslot_id) {
		this.adslot_id = adslot_id;
	}
	public int getAd_type() {
		return ad_type;
	}
	public void setAd_type(int ad_type) {
		this.ad_type = ad_type;
	}
	public int getInteraction_type() {
		return interaction_type;
	}
	public void setInteraction_type(int interaction_type) {
		this.interaction_type = interaction_type;
	}
	public String getBundle() {
		return bundle;
	}
	public void setBundle(String bundle) {
		this.bundle = bundle;
	}
	
	public int getW() {
		return w;
	}
	public void setW(int w) {
		this.w = w;
	}
	public int getH() {
		return h;
	}
	public void setH(int h) {
		this.h = h;
	}
	public String getClkurl() {
		return clkurl;
	}
	public void setClkurl(String clkurl) {
		this.clkurl = clkurl;
	}
	public int getCreative_type() {
		return creative_type;
	}
	public void setCreative_type(int creative_type) {
		this.creative_type = creative_type;
	}
	public YoukenNativ getNativ() {
		return nativ;
	}
	public void setNativ(YoukenNativ nativ) {
		this.nativ = nativ;
	}
	public YoukenMix getMix() {
		return mix;
	}
	public void setMix(YoukenMix mix) {
		this.mix = mix;
	}
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	public List<String> getImptrackers() {
		return imptrackers;
	}
	public void setImptrackers(List<String> imptrackers) {
		this.imptrackers = imptrackers;
	}
	public List<String> getClktrackers() {
		return clktrackers;
	}
	public void setClktrackers(List<String> clktrackers) {
		this.clktrackers = clktrackers;
	}
	public List<String> getDwnlst() {
		return dwnlst;
	}
	public void setDwnlst(List<String> dwnlst) {
		this.dwnlst = dwnlst;
	}
	public List<String> getDwnltrackers() {
		return dwnltrackers;
	}
	public void setDwnltrackers(List<String> dwnltrackers) {
		this.dwnltrackers = dwnltrackers;
	}
	public List<String> getIntltrackers() {
		return intltrackers;
	}
	public void setIntltrackers(List<String> intltrackers) {
		this.intltrackers = intltrackers;
	}
	public List<String> getActvtrackers() {
		return actvtrackers;
	}
	public void setActvtrackers(List<String> actvtrackers) {
		this.actvtrackers = actvtrackers;
	}


}
