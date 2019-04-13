package com.ocean.persist.api.proxy.maiguang;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年6月30日 
      @version 1.0 
 */
public class MaiguangReport     implements AdPullResponse{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1323143125012025283L;
	private List<String> pv;
	private List<String> c;
	private List<String> de;
	private List<String> ie;
	private List<String> a;
	public List<String> getPv() {
		return pv;
	}
	public void setPv(List<String> pv) {
		this.pv = pv;
	}
	public List<String> getC() {
		return c;
	}
	public void setC(List<String> c) {
		this.c = c;
	}
	public List<String> getDe() {
		return de;
	}
	public void setDe(List<String> de) {
		this.de = de;
	}
	public List<String> getIe() {
		return ie;
	}
	public void setIe(List<String> ie) {
		this.ie = ie;
	}
	public List<String> getA() {
		return a;
	}
	public void setA(List<String> a) {
		this.a = a;
	}
}
