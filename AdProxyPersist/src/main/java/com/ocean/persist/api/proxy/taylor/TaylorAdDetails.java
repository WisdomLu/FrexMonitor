package com.ocean.persist.api.proxy.taylor;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;

public class TaylorAdDetails  {

	private static final long serialVersionUID = 1L;

	private String adcode;
	
	private String adtype;
	
	private Integer action;
	
	private Integer aps;
	
	private List<String> clickreport;
	
	private List<TaylorAdReport> displayreport;
	
	private Integer em;
	
	private String width;
	
	private String height;
	
	private String icon;
	
	private String link;
	
	private String page;
	
	private Integer showtime;
	
	private Integer smarth;
	
	private String src;
	
	private String title;

	private String desc;
	
	public String getAdcode() {
		return adcode;
	}

	public void setAdcode(String adcode) {
		this.adcode = adcode;
	}

	public String getAdtype() {
		return adtype;
	}

	public void setAdtype(String adtype) {
		this.adtype = adtype;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Integer getAction() {
		return action;
	}

	public void setAction(Integer action) {
		this.action = action;
	}

	public Integer getAps() {
		return aps;
	}

	public void setAps(Integer aps) {
		this.aps = aps;
	}

	public List<String> getClickreport() {
		return clickreport;
	}

	public void setClickreport(List<String> clickreport) {
		this.clickreport = clickreport;
	}

	public List<TaylorAdReport> getDisplayreport() {
		return displayreport;
	}

	public void setDisplayreport(List<TaylorAdReport> displayreport) {
		this.displayreport = displayreport;
	}

	public Integer getEm() {
		return em;
	}

	public void setEm(Integer em) {
		this.em = em;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public Integer getShowtime() {
		return showtime;
	}

	public void setShowtime(Integer showtime) {
		this.showtime = showtime;
	}

	public Integer getSmarth() {
		return smarth;
	}

	public void setSmarth(Integer smarth) {
		this.smarth = smarth;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
