package com.ocean.persist.api.proxy.redstone;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullParams;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年6月7日 
      @version 1.0 
 */
public class RedStoneNative   {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5269672001013655653L;
	private RedStoneNativeRequest request;
	private String ver;
	private List<Integer> api;
	private List<Integer> battr;
	private RedStoneExt ext;
	public RedStoneNativeRequest getRequest() {
		return request;
	}
	public void setRequest(RedStoneNativeRequest request) {
		this.request = request;
	}
	public String getVer() {
		return ver;
	}
	public void setVer(String ver) {
		this.ver = ver;
	}
	public List<Integer> getApi() {
		return api;
	}
	public void setApi(List<Integer> api) {
		this.api = api;
	}
	public List<Integer> getBattr() {
		return battr;
	}
	public void setBattr(List<Integer> battr) {
		this.battr = battr;
	}
	public RedStoneExt getExt() {
		return ext;
	}
	public void setExt(RedStoneExt ext) {
		this.ext = ext;
	}
}
