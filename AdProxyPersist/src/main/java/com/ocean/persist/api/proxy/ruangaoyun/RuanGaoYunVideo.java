package com.ocean.persist.api.proxy.ruangaoyun;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullParams;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年4月1日 
      @version 1.0 
 */
public class RuanGaoYunVideo    {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5887717030330836997L;
    private Integer pos;
    private Integer minduration;
    private Integer maxduration;
    private Integer w;
    private Integer h;
    public Integer getPos() {
		return pos;
	}
    public void setPos(Integer pos) {
		this.pos = pos;
	}
	public Integer getMinduration() {
		return minduration;
	}
	public void setMinduration(Integer minduration) {
		this.minduration = minduration;
	}
	public Integer getMaxduration() {
		return maxduration;
	}
	public void setMaxduration(Integer maxduration) {
		this.maxduration = maxduration;
	}
	public Integer getW() {
		return w;
	}
	public void setW(Integer w) {
		this.w = w;
	}
	public Integer getH() {
		return h;
	}
	public void setH(Integer h) {
		this.h = h;
	}
}
