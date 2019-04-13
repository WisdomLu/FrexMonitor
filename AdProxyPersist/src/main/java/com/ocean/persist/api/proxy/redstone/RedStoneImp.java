package com.ocean.persist.api.proxy.redstone;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullParams;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年6月7日 
      @version 1.0 
 */
public class RedStoneImp    {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1181747148339767318L;
	private String id;
	private RedStoneBanner banner;
	private RedStoneNative _native;
	private RedStonePmp pmp;
	private Integer instl;
	private String tagid;
	private Integer bidfloor;
	private String bidfloorcur;
	private RedStoneExt ext;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public RedStoneBanner getBanner() {
		return banner;
	}
	public void setBanner(RedStoneBanner banner) {
		this.banner = banner;
	}
	public RedStoneNative get_native() {
		return _native;
	}
	public void set_native(RedStoneNative _native) {
		this._native = _native;
	}
	public RedStonePmp getPmp() {
		return pmp;
	}
	public void setPmp(RedStonePmp pmp) {
		this.pmp = pmp;
	}
	public Integer getInstl() {
		return instl;
	}
	public void setInstl(Integer instl) {
		this.instl = instl;
	}
	public String getTagid() {
		return tagid;
	}
	public void setTagid(String tagid) {
		this.tagid = tagid;
	}
	public Integer getBidfloor() {
		return bidfloor;
	}
	public void setBidfloor(Integer bidfloor) {
		this.bidfloor = bidfloor;
	}
	public String getBidfloorcur() {
		return bidfloorcur;
	}
	public void setBidfloorcur(String bidfloorcur) {
		this.bidfloorcur = bidfloorcur;
	}
	public RedStoneExt getExt() {
		return ext;
	}
	public void setExt(RedStoneExt ext) {
		this.ext = ext;
	}
}
