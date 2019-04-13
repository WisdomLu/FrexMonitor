package com.ocean.persist.api.proxy.yidianzx;

import java.util.List;

public class YidianzxBid {
	private String id ;//string 是DSP 产生的本次BID 的ID，用于日志与追踪
	private String impid  ;//string 是对应BidRequest.Imp.id
	private int price  ;//int 是CPM 出价，单位分
	private String 	nurl  ;//string 是Win Notice Url
	private int ctype ;// int 是广告点击类型：
/*	1：跳转
	2：下载*/
	private String 	template ;// string 是参与竞价的广告位模板类型：
/*	ICON_IMAGE：图标样式，320*320
	SINGLE_IMAGE：小图样式，480*320
	BIG_IMAGE：大图样式，1000*500
	THREE_IMAGE ： 组图样式（ 3 图），
	480*320*/
	private String adid ;// string 是广告ID
	private String crid  ;//string 是创意ID
	private int h  ;//int 否素材高，单位像素
	private int w  ;//int 否素材宽，单位像素
	private String curl ;// string 否点击跳转地址
	private String durl ;// string 否下载地址
	private String title ;// string 否广告标题
	private String source ;// string 否广告主名称
	private String summary  ;//string 否App 描述
	private List<String> aurl  ;//string array 否素材图片地址
	private List<String> murl  ;//string array 否曝光监测地址数组
	private List<String> cmurl ;// string array 否点击监测地址数组
	private List<String> dmurl  ;//string array 否app 下载的监测地址数组
	private String dpkgname  ;//string 否app 包名
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getImpid() {
		return impid;
	}
	public void setImpid(String impid) {
		this.impid = impid;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getNurl() {
		return nurl;
	}
	public void setNurl(String nurl) {
		this.nurl = nurl;
	}
	public int getCtype() {
		return ctype;
	}
	public void setCtype(int ctype) {
		this.ctype = ctype;
	}
	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
	}
	public String getAdid() {
		return adid;
	}
	public void setAdid(String adid) {
		this.adid = adid;
	}
	public String getCrid() {
		return crid;
	}
	public void setCrid(String crid) {
		this.crid = crid;
	}
	public int getH() {
		return h;
	}
	public void setH(int h) {
		this.h = h;
	}
	public int getW() {
		return w;
	}
	public void setW(int w) {
		this.w = w;
	}
	public String getCurl() {
		return curl;
	}
	public void setCurl(String curl) {
		this.curl = curl;
	}
	public String getDurl() {
		return durl;
	}
	public void setDurl(String durl) {
		this.durl = durl;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getDpkgname() {
		return dpkgname;
	}
	public void setDpkgname(String dpkgname) {
		this.dpkgname = dpkgname;
	}
	public List<String> getMurl() {
		return murl;
	}
	public void setMurl(List<String> murl) {
		this.murl = murl;
	}
	public List<String> getCmurl() {
		return cmurl;
	}
	public void setCmurl(List<String> cmurl) {
		this.cmurl = cmurl;
	}
	public List<String> getDmurl() {
		return dmurl;
	}
	public void setDmurl(List<String> dmurl) {
		this.dmurl = dmurl;
	}
	public List<String> getAurl() {
		return aurl;
	}
	public void setAurl(List<String> aurl) {
		this.aurl = aurl;
	}
}
