package com.ocean.persist.api.proxy.redstone;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullParams;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年3月8日 
      @version 1.0 
 */
public class RedStoneNativeImg  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3782537944747180619L;
    private Integer type;
    private Integer w;
    private Integer wmin;
    private Integer h;
    private Integer hmin;
    private List<String> mimes;
    private RedStoneExt ext;
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getW() {
		return w;
	}
	public void setW(Integer w) {
		this.w = w;
	}
	public Integer getWmin() {
		return wmin;
	}
	public void setWmin(Integer wmin) {
		this.wmin = wmin;
	}
	public Integer getH() {
		return h;
	}
	public void setH(Integer h) {
		this.h = h;
	}
	public Integer getHmin() {
		return hmin;
	}
	public void setHmin(Integer hmin) {
		this.hmin = hmin;
	}
	public List<String> getMimes() {
		return mimes;
	}
	public void setMimes(List<String> mimes) {
		this.mimes = mimes;
	}
	public RedStoneExt getExt() {
		return ext;
	}
	public void setExt(RedStoneExt ext) {
		this.ext = ext;
	}
}
