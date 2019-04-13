package com.ocean.persist.api.proxy.huixuan;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;

import com.ocean.persist.api.proxy.AdPullParams;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年6月16日 
      @version 1.0 
 */
public class HuixuanAdVideo    {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7453343914460433022L;
	private int w;
	private int h;
	private int pos;
	private List<Integer> mimes;
	private int linearity;
	private int minduration;
	private int maxduration;
	
	//response
	private String url;
	
	private int duration;//视频播放前第一帧的图片地址
	private String cover_img_url;
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
	public List<Integer> getMimes() {
		return mimes;
	}
	public void setMimes(List<Integer> mimes) {
		this.mimes = mimes;
	}
	public int getLinearity() {
		return linearity;
	}
	public void setLinearity(int linearity) {
		this.linearity = linearity;
	}
	public int getMinduration() {
		return minduration;
	}
	public void setMinduration(int minduration) {
		this.minduration = minduration;
	}
	public int getMaxduration() {
		return maxduration;
	}
	public void setMaxduration(int maxduration) {
		this.maxduration = maxduration;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getCover_img_url() {
		return cover_img_url;
	}
	public void setCover_img_url(String cover_img_url) {
		this.cover_img_url = cover_img_url;
	}


}
