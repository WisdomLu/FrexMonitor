package com.ocean.persist.api.proxy.huixuan;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;

import com.ocean.persist.api.proxy.AdPullParams;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年6月16日 
      @version 1.0 
 */
public class HuixuanAdImg    {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7958157034932808405L;
	private int type;
	private int w;
	private int h;
	private List<Integer>  mimes;
	//response
	private List<String> urls;
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
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
	public List<Integer> getMimes() {
		return mimes;
	}
	public void setMimes(List<Integer> mimes) {
		this.mimes = mimes;
	}
	public List<String> getUrls() {
		return urls;
	}
	public void setUrls(List<String> urls) {
		this.urls = urls;
	}

}
