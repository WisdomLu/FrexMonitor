package com.ocean.persist.api.proxy.mex;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;

import com.ocean.persist.api.proxy.AdPullParams;
public class MexNativeRes  
{

	private static final long serialVersionUID = 1L;
	
	private List<String> imgurl;
	private String curl;
	private String titile;
	private List<String> subtitle;
	private String icon;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<String> getImgurl() {
		return imgurl;
	}

	public void setImgurl(List<String> imgurl) {
		this.imgurl = imgurl;
	}

	public String getCurl() {
		return curl;
	}

	public void setCurl(String curl) {
		this.curl = curl;
	}

	public String getTitile() {
		return titile;
	}

	public void setTitile(String titile) {
		this.titile = titile;
	}

	public List<String> getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(List<String> subtitle) {
		this.subtitle = subtitle;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
}
