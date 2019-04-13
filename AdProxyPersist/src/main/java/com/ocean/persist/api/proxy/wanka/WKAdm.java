package com.ocean.persist.api.proxy.wanka;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;

public class WKAdm {

	private static final long serialVersionUID = 1L;
	
	private List<String> imgurl;// 是 图片地址
	private String landingurl;// 是 落地页地址或者下载地址
	private String title;// 否 主标题
	private String subtitle;// 否 副标题
	private String icon;// 否 图标的url
	private String iconaction;// 否 行为图标的url
	private String source;// 否 推广源
	private Integer itemType;// 是 广告类型。1：信息流组图，2：信息流小图，3：信息流大图, 4:图片, 5:图文
	
	public List<String> getImgurl() {
		return imgurl;
	}

	public void setImgurl(List<String> imgurl) {
		this.imgurl = imgurl;
	}

	public String getLandingurl() {
		return landingurl;
	}

	public void setLandingurl(String landingurl) {
		this.landingurl = landingurl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getIconaction() {
		return iconaction;
	}

	public void setIconaction(String iconaction) {
		this.iconaction = iconaction;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Integer getItemType() {
		return itemType;
	}

	public void setItemType(Integer itemType) {
		this.itemType = itemType;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
