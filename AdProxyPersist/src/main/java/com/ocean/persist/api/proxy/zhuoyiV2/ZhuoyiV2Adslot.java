package com.ocean.persist.api.proxy.zhuoyiV2;

import java.util.List;

public class ZhuoyiV2Adslot {
	private String id	;//required	string	广告位 ID，由 ADroi 提供
	private List<Integer> styles		;//optional	array of int	物料类型（
/*	1、TEXT


				2、IMAGE
	3、TEXT_ICON）
	非特定需要某些类型物料， 传空数组[]，提高填充*/
	private ZhuoyiV2Size size	;//	required	size object	广告位尺寸
	private int capacity		;//optional	int	广告容量
	private int need_render		;//optional	int	是否需要服务器渲染
	public String getId() {
		return id;
	}
	public ZhuoyiV2Size getSize() {
		return size;
	}
	public int getCapacity() {
		return capacity;
	}
	public int getNeed_render() {
		return need_render;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setSize(ZhuoyiV2Size size) {
		this.size = size;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public void setNeed_render(int need_render) {
		this.need_render = need_render;
	}
	public List<Integer> getStyles() {
		return styles;
	}
	public void setStyles(List<Integer> styles) {
		this.styles = styles;
	}
}
