package com.ocean.persist.api.proxy.jieku;

import com.ocean.core.common.base.AbstractBaseEntity;


public class JiekuSize {

	private static final long serialVersionUID = 1L;
	
	private Integer width;// required uint32 宽
	private Integer height;// required uint32 ⾼高Field
	
	public JiekuSize() {
		super();
	}

	public JiekuSize(Integer width, Integer height) {
		super();
		this.width = width;
		this.height = height;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
