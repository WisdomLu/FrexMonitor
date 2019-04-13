package com.ocean.persist.api.proxy.yidianzx;

import java.util.List;

public class YidianzxBanner {
	private List<String > template;// string array 是参与竞价的广告位模板类型：
/*	ICON_IMAGE：图标样式，320*320
	SINGLE_IMAGE：小图样式，480*320
	BIG_IMAGE：大图样式，1000*500
	THREE_IMAGE ： 组图样式（ 3 图），
	480*320*/
	private int ctype;// int 是广告点击类型：
	private int w;// int 否广告位宽，单位：像素
	private int h;// int 否广告位高，单位：像素
	private Integer pos ;//int 否见OpenRTB 文档附录5.4
	private String channelid;// string 否频道id
	private Integer pageindex ;//int 否广告位所在页码
	private Integer posid ;//int 否广告位所在序号
	public List<String> getTemplate() {
		return template;
	}
	public void setTemplate(List<String> template) {
		this.template = template;
	}
	public int getCtype() {
		return ctype;
	}
	public void setCtype(int ctype) {
		this.ctype = ctype;
	}
	public int getW() {
		return w;
	}
	public void setW(int w) {
		this.w = w;
	}
	public int getH() {
		return h;
	}
	public void setH(int h) {
		this.h = h;
	}
	public String getChannelid() {
		return channelid;
	}
	public void setChannelid(String channelid) {
		this.channelid = channelid;
	}
	public Integer getPos() {
		return pos;
	}
	public Integer getPageindex() {
		return pageindex;
	}
	public Integer getPosid() {
		return posid;
	}
	public void setPos(Integer pos) {
		this.pos = pos;
	}
	public void setPageindex(Integer pageindex) {
		this.pageindex = pageindex;
	}
	public void setPosid(Integer posid) {
		this.posid = posid;
	}

}
