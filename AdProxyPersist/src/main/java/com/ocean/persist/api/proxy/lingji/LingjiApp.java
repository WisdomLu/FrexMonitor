package com.ocean.persist.api.proxy.lingji;

import com.ocean.core.common.base.AbstractBaseEntity;

import com.ocean.persist.api.proxy.AdPullParams;


public class LingjiApp  {

	private static final long serialVersionUID = 1L;
	
	private String m_app;// App Name
	private String m_app_pn;// Y	APP应用的包名称或bundleID,com.weitu.wantu
	private String m_cat;// app应用的分类编号,2001001
	private Integer m_mkt;// 应用商店的编号1--iOS AppStore 2--Google Play 3--91 Market
	private Integer m_mkt_app;// app在上述应用商店内的编号
	private String m_mkt_cat;// app在上述应用商店内的分类编号		
	private String m_mkt_tag;// app在上述应用商店内的标签(英文或中文UTF8-urlencode编码) 多个标签使用半角逗号分隔
	
	public String getM_app() {
		return m_app;
	}

	public void setM_app(String m_app) {
		this.m_app = m_app;
	}

	public String getM_app_pn() {
		return m_app_pn;
	}

	public void setM_app_pn(String m_app_pn) {
		this.m_app_pn = m_app_pn;
	}

	public String getM_cat() {
		return m_cat;
	}

	public void setM_cat(String m_cat) {
		this.m_cat = m_cat;
	}

	public Integer getM_mkt() {
		return m_mkt;
	}

	public void setM_mkt(Integer m_mkt) {
		this.m_mkt = m_mkt;
	}

	public Integer getM_mkt_app() {
		return m_mkt_app;
	}

	public void setM_mkt_app(Integer m_mkt_app) {
		this.m_mkt_app = m_mkt_app;
	}

	public String getM_mkt_cat() {
		return m_mkt_cat;
	}

	public void setM_mkt_cat(String m_mkt_cat) {
		this.m_mkt_cat = m_mkt_cat;
	}

	public String getM_mkt_tag() {
		return m_mkt_tag;
	}

	public void setM_mkt_tag(String m_mkt_tag) {
		this.m_mkt_tag = m_mkt_tag;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
