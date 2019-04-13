package com.ocean.persist.app.dis.wxcpd.asyn;

import com.ocean.persist.app.dis.qqDownloader.BaseAppADListItem;

public class WxAsynApp extends BaseAppADListItem{
	private String md5;// string apk 的MD5 值
	private String pkg ;//string 应用包名
	private String url ;//int 应用下载地址
	private String name ;//string 应用名称
	private String chn ;//string 写死1004，兼容原来的逻辑
	private String vcd ;//int 包版本号
	private String size ;//string 包大小（单位字节）
	private String id ;//int 应用id
	private String tag;// string 应用标签
	public String getMd5() {
		return md5;
	}
	public void setMd5(String md5) {
		this.md5 = md5;
	}
	public String getPkg() {
		return pkg;
	}
	public void setPkg(String pkg) {
		this.pkg = pkg;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getChn() {
		return chn;
	}
	public void setChn(String chn) {
		this.chn = chn;
	}

	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}

	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getVcd() {
		return vcd;
	}
	public void setVcd(String vcd) {
		this.vcd = vcd;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
