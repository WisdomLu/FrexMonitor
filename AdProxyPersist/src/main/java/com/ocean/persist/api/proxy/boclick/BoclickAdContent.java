package com.ocean.persist.api.proxy.boclick;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;
public class BoclickAdContent  implements AdPullResponse{

	private static final long serialVersionUID = 1L;

	// 素材类型	enum('gif','jpeg','png','swf','mp4')
	private List<String> type;
	// 素材标题
	private List<String> name;
	// 图片不显示时显示的文字
	private List<String> alt;
	// 素材icon
	private List<String> icon;
	// 说明
	private List<String> bannertext;
	// 素材地址
	private List<String> src;
	// 素材尺寸（宽*高）
	private List<String> size;
	// 视频时长(图片时忽略)
	private List<Integer> len;
	// 声音大小（图片时忽略）
	private List<Integer> volume;
	// 落地页地址
	private List<String> link;
	// 点击监测地址
	private List<String> monitor;
	// 曝光监测地址
	private List<String> pvBegin;

	public List<String> getType() {
		return type;
	}

	public void setType(List<String> type) {
		this.type = type;
	}

	public List<String> getSrc() {
		return src;
	}

	public void setSrc(List<String> src) {
		this.src = src;
	}

	public List<String> getSize() {
		return size;
	}

	public void setSize(List<String> size) {
		this.size = size;
	}

	public List<Integer> getLen() {
		return len;
	}

	public void setLen(List<Integer> len) {
		this.len = len;
	}

	public List<Integer> getVolume() {
		return volume;
	}

	public void setVolume(List<Integer> volume) {
		this.volume = volume;
	}

	public List<String> getLink() {
		return link;
	}

	public void setLink(List<String> link) {
		this.link = link;
	}

	public List<String> getMonitor() {
		return monitor;
	}

	public void setMonitor(List<String> monitor) {
		this.monitor = monitor;
	}

	public List<String> getPvBegin() {
		return pvBegin;
	}

	public void setPvBegin(List<String> pvBegin) {
		this.pvBegin = pvBegin;
	}

	public List<String> getName() {
		return name;
	}

	public void setName(List<String> name) {
		this.name = name;
	}

	public List<String> getAlt() {
		return alt;
	}

	public void setAlt(List<String> alt) {
		this.alt = alt;
	}

	public List<String> getIcon() {
		return icon;
	}

	public void setIcon(List<String> icon) {
		this.icon = icon;
	}

	public List<String> getBannertext() {
		return bannertext;
	}

	public void setBannertext(List<String> bannertext) {
		this.bannertext = bannertext;
	}
}
