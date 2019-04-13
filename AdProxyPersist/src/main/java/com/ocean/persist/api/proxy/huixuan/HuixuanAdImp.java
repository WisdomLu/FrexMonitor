package com.ocean.persist.api.proxy.huixuan;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;

import com.ocean.persist.api.proxy.AdPullParams;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年6月16日 
      @version 1.0 
 */
public class HuixuanAdImp    {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5894101116080315440L;
	private String id;
	private String tagid;
	private int instl;
	private int bidfloor;
	private HuixuanAdBanner banner;
	private HuixuanAdVideo video;
	private HuixuanAdNative _native;
	private int screenlocation;
	private boolean allow_adm;
	private   List<Integer> action_type;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTagid() {
		return tagid;
	}
	public void setTagid(String tagid) {
		this.tagid = tagid;
	}
	public int getInstl() {
		return instl;
	}
	public void setInstl(int instl) {
		this.instl = instl;
	}
	public int getBidfloor() {
		return bidfloor;
	}
	public void setBidfloor(int bidfloor) {
		this.bidfloor = bidfloor;
	}
	public HuixuanAdBanner getBanner() {
		return banner;
	}
	public void setBanner(HuixuanAdBanner banner) {
		this.banner = banner;
	}
	public HuixuanAdVideo getVideo() {
		return video;
	}
	public void setVideo(HuixuanAdVideo video) {
		this.video = video;
	}
	public HuixuanAdNative get_native() {
		return _native;
	}
	public void set_native(HuixuanAdNative _native) {
		this._native = _native;
	}
	public int getScreenlocation() {
		return screenlocation;
	}
	public void setScreenlocation(int screenlocation) {
		this.screenlocation = screenlocation;
	}
	public boolean isAllow_adm() {
		return allow_adm;
	}
	public void setAllow_adm(boolean allow_adm) {
		this.allow_adm = allow_adm;
	}
	public List<Integer> getAction_type() {
		return action_type;
	}
	public void setAction_type(List<Integer> action_type) {
		this.action_type = action_type;
	}

}
