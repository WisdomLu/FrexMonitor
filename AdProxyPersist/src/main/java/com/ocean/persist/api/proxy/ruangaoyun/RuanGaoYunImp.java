package com.ocean.persist.api.proxy.ruangaoyun;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullParams;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年4月1日 
      @version 1.0 
 */
public class RuanGaoYunImp    {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4627096974869585706L;
    private String id;
    private String tagid;
    private RuanGaoYunBanner banner;
    private RuanGaoYunVideo video;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public RuanGaoYunBanner getBanner() {
		return banner;
	}
	public void setBanner(RuanGaoYunBanner banner) {
		this.banner = banner;
	}
	public RuanGaoYunVideo getVideo() {
		return video;
	}
	public void setVideo(RuanGaoYunVideo video) {
		this.video = video;
	}
	public String getTagid() {
		return tagid;
	}
	public void setTagid(String tagid) {
		this.tagid = tagid;
	}
}
