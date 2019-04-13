package com.ocean.persist.common.util.vast;

import com.ocean.core.common.base.AbstractBaseEntity;

public class VastMedia {

	private static final long serialVersionUID = 1L;

	private String delivery;
	
	private String type;
	
	private Integer width;
	
	private Integer height;
	
	private String url;

	public String getDelivery() {
		return delivery;
	}

	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "VastMedia [delivery=" + delivery + ", type=" + type
				+ ", width=" + width + ", height=" + height + ", url=" + url
				+ "]";
	}
}
