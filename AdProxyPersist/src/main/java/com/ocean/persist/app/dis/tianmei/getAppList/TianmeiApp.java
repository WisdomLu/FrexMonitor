package com.ocean.persist.app.dis.tianmei.getAppList;

import java.util.List;

public class TianmeiApp {
	private String name;//	string	是	名称
	private String pkg	;//string	是	包名
	private String size	;//string	是	大小
	private int adtype;//	number	是	广告类型: 0横幅,1 插屏, 2开屏,3 信息流 ;
    private int stype;//	number	是	表现形式:0 跳浏览器,1下载类型
	private String icon;//	string	否	图标
	private String downurl	;//string	是	下载地址
	private List<String> imgurl;//	Array	是	图片地址
	private List<String> show	;//Array	是	曝光上报地址
	private List<String> click	;//Array	是	点击上报地址
	private List<String> downst	;//Array	是	开始下载上报地址
	private List<String> downfin	;//Array	是	下载完成上报地址
	private List<String> instart	;//Array	是	开始安装上报地址
	private List<String> insfin	;//Array	是	安装完成上报地址
	private List<String> active	;//Array	是	打开激活上报地址
	public String getName() {
		return name;
	}
	public int getAdtype() {
		return adtype;
	}
	public int getStype() {
		return stype;
	}
	public String getIcon() {
		return icon;
	}
	public String getDownurl() {
		return downurl;
	}
	public List<String> getImgurl() {
		return imgurl;
	}
	public List<String> getShow() {
		return show;
	}
	public List<String> getClick() {
		return click;
	}
	public List<String> getDownst() {
		return downst;
	}
	public List<String> getDownfin() {
		return downfin;
	}
	public List<String> getInstart() {
		return instart;
	}
	public List<String> getInsfin() {
		return insfin;
	}
	public List<String> getActive() {
		return active;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setAdtype(int adtype) {
		this.adtype = adtype;
	}
	public void setStype(int stype) {
		this.stype = stype;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public void setDownurl(String downurl) {
		this.downurl = downurl;
	}
	public void setImgurl(List<String> imgurl) {
		this.imgurl = imgurl;
	}
	public void setShow(List<String> show) {
		this.show = show;
	}
	public void setClick(List<String> click) {
		this.click = click;
	}
	public void setDownst(List<String> downst) {
		this.downst = downst;
	}
	public void setDownfin(List<String> downfin) {
		this.downfin = downfin;
	}
	public void setInstart(List<String> instart) {
		this.instart = instart;
	}
	public void setInsfin(List<String> insfin) {
		this.insfin = insfin;
	}
	public void setActive(List<String> active) {
		this.active = active;
	}
	public String getPkg() {
		return pkg;
	}
	public String getSize() {
		return size;
	}
	public void setPkg(String pkg) {
		this.pkg = pkg;
	}
	public void setSize(String size) {
		this.size = size;
	}
}
