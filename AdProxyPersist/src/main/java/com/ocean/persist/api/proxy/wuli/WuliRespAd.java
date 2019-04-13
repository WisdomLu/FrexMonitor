package com.ocean.persist.api.proxy.wuli;

import java.util.List;

public class WuliRespAd {
	
	private String title;
	
	private String desc;
	
	/** 广告类型  1：跳转类，2：下载类，3：品牌类 */
	private Integer at;
	
	/** 物料类型 001–图文，002–图片，003–文字，004–微信，005–视频，006-SDK，007 – 脚本 */
	private String mt;
	
	/** 目标地址*/
	private String ms;
	
	/** 目标地址  */
	private String url;
	
	/** 下载地址  */
	private String dlurl;
	
	/** 曝光上报地址   */
	private List<String> els;
	
	/** 点击上报地址   */
	private List<String> cls;
	
	/** 下载开始上报地址  */
	private List<String> dsls;
	
	/** 下载完成上报地址   */
	private List<String> dels;
	
	/** 安装开始上报地址  */
	private List<String> isls;
	
	/** 安装完成上报地址  */
	private List<String> iels;
	
	/** 播放开始上报地址  */
	private List<String> psls;
	
	/** 播放结束上报地址  */
	private List<String> pels;
	
	/** Deeplink 地址  */
	private String dl;
	
	/** Deeplink 点击回调地址  */
	private List<String> dlls;
	
	private WuliRespAdmInfo info;

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

	public Integer getAt() {
		return at;
	}

	public void setAt(Integer at) {
		this.at = at;
	}

	public String getMt() {
		return mt;
	}

	public void setMt(String mt) {
		this.mt = mt;
	}

	public String getMs() {
		return ms;
	}

	public void setMs(String ms) {
		this.ms = ms;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDlurl() {
		return dlurl;
	}

	public void setDlurl(String dlurl) {
		this.dlurl = dlurl;
	}

	public List<String> getEls() {
		return els;
	}

	public void setEls(List<String> els) {
		this.els = els;
	}

	public List<String> getCls() {
		return cls;
	}

	public void setCls(List<String> cls) {
		this.cls = cls;
	}

	public List<String> getDsls() {
		return dsls;
	}

	public void setDsls(List<String> dsls) {
		this.dsls = dsls;
	}

	public List<String> getDels() {
		return dels;
	}

	public void setDels(List<String> dels) {
		this.dels = dels;
	}

	public List<String> getIsls() {
		return isls;
	}

	public void setIsls(List<String> isls) {
		this.isls = isls;
	}

	public List<String> getIels() {
		return iels;
	}

	public void setIels(List<String> iels) {
		this.iels = iels;
	}

	public List<String> getPsls() {
		return psls;
	}

	public void setPsls(List<String> psls) {
		this.psls = psls;
	}

	public List<String> getPels() {
		return pels;
	}

	public void setPels(List<String> pels) {
		this.pels = pels;
	}

	public String getDl() {
		return dl;
	}

	public void setDl(String dl) {
		this.dl = dl;
	}

	public List<String> getDlls() {
		return dlls;
	}

	public void setDlls(List<String> dlls) {
		this.dlls = dlls;
	}

	public WuliRespAdmInfo getInfo() {
		return info;
	}

	public void setInfo(WuliRespAdmInfo info) {
		this.info = info;
	}
}
