package com.ocean.persist.api.proxy.huixuan;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;

import com.ocean.persist.api.proxy.AdPullParams;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年6月16日 
      @version 1.0 
 */
public class HuixuanAdBanner   {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7073100281679870448L;
	private int w;
	private int h;
	private List<Integer> mimes;
	/*
	 * 广告位位置
		0：未知
		4：头部
		5：底部
		6：侧边栏
		7：全屏

	 * */
	private int pos;
	public int getPos() {
		return pos;
	}
	public void setPos(int pos) {
		this.pos = pos;
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

}
