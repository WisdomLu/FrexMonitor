package com.ocean.persist.api.proxy.guangyin;

import com.ocean.core.common.base.AbstractBaseEntity;

public class GuangyinAdm  {

	private static final long serialVersionUID = 1L;
	
	private String src;
	
	private String title;
	
	private String text;
	
	private String icon;

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
