package com.ocean.persist.api.proxy.lingji;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;


/**
 * 非信息流广告创意 object
 */
public class LingjiGeneralMeta  implements AdPullResponse{

	private static final long serialVersionUID = 1L;

	private String url;// 素材url地址
	private Integer width;// 素材宽度
	private Integer height;// 素材高度
	private Integer duration;// 素材时长，广告素材type为"V"时有效，其它素材类型请忽略时长字段
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
