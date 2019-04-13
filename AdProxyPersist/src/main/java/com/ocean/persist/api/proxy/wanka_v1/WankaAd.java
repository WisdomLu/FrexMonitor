package com.ocean.persist.api.proxy.wanka_v1;

import java.util.List;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年2月27日 
      @version 1.0 
 */
public class WankaAd {
	private String adslot_id;/*
	string
	是
	对应请求时填写的广告位 ID*/
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
	private WankaMaterial materialVO;/*
	Nativ
	否
	原生广告。creative_type 为 5 时， 该字段有值。*/

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
	private String dplk;
	private int template;
	private String html_segment;
	private WankaReport reportVO;
	

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
	public String getDplk() {
		return dplk;
	}
	public void setDplk(String dplk) {
		this.dplk = dplk;
	}
	public int getTemplate() {
		return template;
	}
	public void setTemplate(int template) {
		this.template = template;
	}
	public String getHtml_segment() {
		return html_segment;
	}
	public void setHtml_segment(String html_segment) {
		this.html_segment = html_segment;
	}
	public WankaMaterial getMaterialVO() {
		return materialVO;
	}
	public void setMaterialVO(WankaMaterial materialVO) {
		this.materialVO = materialVO;
	}
	public WankaReport getReportVO() {
		return reportVO;
	}
	public void setReportVO(WankaReport reportVO) {
		this.reportVO = reportVO;
	}

}
