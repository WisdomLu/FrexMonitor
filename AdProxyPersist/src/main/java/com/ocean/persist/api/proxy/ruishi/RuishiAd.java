package com.ocean.persist.api.proxy.ruishi;

import java.util.List;

public class RuishiAd {
	private String title;//	string	是	标题
	private String desc	;//	string	是	描述
	private String img	;//	string	是	广告图片地址
	private int w	;//	int	是	图片宽
	private int h	;//	int	是	图片高
	private String ldp	;//	string	是	广告点击地址
	private List<String> c_imp_tracking	;//	string[]	是	该广告展示的曝光监测，可能有多个，需要全部依次发送
	private List<String> c_clk_tracking;//		string[]	否	该广告展示的点击监测，该字段有值的话，元素可能有多个，需要全部依次发送。没有该字段则无需处理
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
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
	public String getLdp() {
		return ldp;
	}
	public void setLdp(String ldp) {
		this.ldp = ldp;
	}
	public List<String> getC_imp_tracking() {
		return c_imp_tracking;
	}
	public void setC_imp_tracking(List<String> c_imp_tracking) {
		this.c_imp_tracking = c_imp_tracking;
	}
	public List<String> getC_clk_tracking() {
		return c_clk_tracking;
	}
	public void setC_clk_tracking(List<String> c_clk_tracking) {
		this.c_clk_tracking = c_clk_tracking;
	}

}
