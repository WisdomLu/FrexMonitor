package com.ocean.persist.api.proxy.redstone;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullParams;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年6月7日 
      @version 1.0 
 */
public class RedStoneBanner   {
 /**
	 * 
	 */
	private static final long serialVersionUID = -6542955720683553512L;
	private int w;
	 private int h;
	 private int pos;
	 private List<Integer> btype;
	 private List<Integer>  battr;
	 private List<Integer>  mimes;
	 private List<Integer> api;
	 private RedStoneExt ext;
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
	public int getPos() {
		return pos;
	}
	public void setPos(int pos) {
		this.pos = pos;
	}
	public List<Integer> getBtype() {
		return btype;
	}
	public void setBtype(List<Integer> btype) {
		this.btype = btype;
	}
	public List<Integer> getBattr() {
		return battr;
	}
	public void setBattr(List<Integer> battr) {
		this.battr = battr;
	}
	public List<Integer> getMimes() {
		return mimes;
	}
	public void setMimes(List<Integer> mimes) {
		this.mimes = mimes;
	}
	public List<Integer> getApi() {
		return api;
	}
	public void setApi(List<Integer> api) {
		this.api = api;
	}
	public RedStoneExt getExt() {
		return ext;
	}
	public void setExt(RedStoneExt ext) {
		this.ext = ext;
	}
}
