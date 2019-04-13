package com.ocean.persist.api.proxy.firebird;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年12月20日 
      @version 1.0 
 */
public class FireBirdAd  extends AbstractBaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4535065177754229759L;
	private String title;// String 􀀾􀁥
	private String photo;//  String 􀀘􀁄
	private int type ;// Integer 0:URL, 1:APK
	private String link;//  String 􀁗􀀚􀁢
	private List<String> exposure;//  ArrayList<String> 􀀺􀀇􀁡􀀶􀁨􀁛􀀴􀁣􀀫􀀈􀁠􀀙􀀣􀀱􀁌􀀺􀀇
	private List<String> click ;// ArrayList<String> 􀁃􀀉􀁡􀀶􀁨􀁛􀀴􀁣􀀫􀀈􀁠􀀙􀀣􀀱􀁌􀀺􀀇
	private List<String> dldpre ;// ArrayList<String> 􀀂􀁜􀀊􀁡􀀶􀁨􀁛􀀴􀁣􀀫􀀈􀁠􀀙􀀣􀀱􀁌􀀺􀀇
	private List<String> dldok ;// ArrayList<String> 􀀂􀁜􀀡􀀰􀁡􀀶􀁨􀁛􀀴􀁣􀀫􀀈􀁠􀀙􀀣􀀱􀁌􀀺􀀇
	private List<String> inspre ;// ArrayList<String> 􀀠􀁙􀀊􀁡􀀶􀁨􀁛􀀴􀁣􀀫􀀈􀁠􀀙􀀣􀀱􀁌􀀺􀀇
	private List<String> insok;//  ArrayList<String> 􀀠􀁙􀀰􀀋􀁡􀀶􀁨􀁛􀀴􀁣􀀫􀀈􀁠􀀙􀀣􀀱􀁌􀀺􀀇
	private List<String> launch ;// ArrayList<String> 􀀓􀀌􀁡􀀶􀁨􀁛􀀴􀁣􀀫􀀈􀁠􀀙􀀣􀀱􀁌􀀺􀀇
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public List<String> getExposure() {
		return exposure;
	}
	public void setExposure(List<String> exposure) {
		this.exposure = exposure;
	}
	public List<String> getClick() {
		return click;
	}
	public void setClick(List<String> click) {
		this.click = click;
	}
	public List<String> getDldpre() {
		return dldpre;
	}
	public void setDldpre(List<String> dldpre) {
		this.dldpre = dldpre;
	}
	public List<String> getDldok() {
		return dldok;
	}
	public void setDldok(List<String> dldok) {
		this.dldok = dldok;
	}
	public List<String> getInspre() {
		return inspre;
	}
	public void setInspre(List<String> inspre) {
		this.inspre = inspre;
	}
	public List<String> getInsok() {
		return insok;
	}
	public void setInsok(List<String> insok) {
		this.insok = insok;
	}
	public List<String> getLaunch() {
		return launch;
	}
	public void setLaunch(List<String> launch) {
		this.launch = launch;
	}
}
