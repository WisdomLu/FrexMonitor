package com.ocean.persist.api.proxy.uniplay;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年9月7日 
      @version 1.0 
 */
public class UniPlayAd  extends AbstractBaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5780675777244019844L;
	private String img;
	private String title;
	private String txt;
	private List<String> click;
	private List<String> imp;
	private int act;
	private String lpg;
	private String dplink;
	private int adw;
	private int adh;
	private String logo;
	private String html;
	private String icon;
	private UniPlayAdExt adext;
	//videoext
	//adext
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTxt() {
		return txt;
	}
	public void setTxt(String txt) {
		this.txt = txt;
	}
	public List<String> getClick() {
		return click;
	}
	public void setClick(List<String> click) {
		this.click = click;
	}
	public List<String> getImp() {
		return imp;
	}
	public void setImp(List<String> imp) {
		this.imp = imp;
	}
	public int getAct() {
		return act;
	}
	public void setAct(int act) {
		this.act = act;
	}
	public String getLpg() {
		return lpg;
	}
	public void setLpg(String lpg) {
		this.lpg = lpg;
	}
	public String getDplink() {
		return dplink;
	}
	public void setDplink(String dplink) {
		this.dplink = dplink;
	}
	public int getAdw() {
		return adw;
	}
	public void setAdw(int adw) {
		this.adw = adw;
	}
	public int getAdh() {
		return adh;
	}
	public void setAdh(int adh) {
		this.adh = adh;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public UniPlayAdExt getAdext() {
		return adext;
	}
	public void setAdext(UniPlayAdExt adext) {
		this.adext = adext;
	}
}
