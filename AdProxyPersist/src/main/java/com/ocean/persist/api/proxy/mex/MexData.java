package com.ocean.persist.api.proxy.mex;

import com.ocean.core.common.base.AbstractBaseEntity;

import com.ocean.persist.api.proxy.AdPullParams;

public class MexData  {

	private static final long serialVersionUID = 1L;
	
	private Integer label;// 用于描述该数据元素的类型，见附录3 原生数据元素类型 
	private String value;// 数据 

	public Integer getLabel() {
		return label;
	}

	public void setLabel(Integer label) {
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
