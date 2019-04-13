package com.ocean.persist.api.proxy.redstone;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullParams;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年3月8日 
      @version 1.0 
 */
public class RedStoneNativeAsset   {

	/**
	 * 
	 */
	private static final long serialVersionUID = 471715585823369670L;
    private Integer id;
    private Integer required;
    private RedStoneNativeTitle title;
    private RedStoneNativeImg img;
    private RedStoneNativeVideo video;
    private RedStoneNativeData data;
    private RedStoneExt ext;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getRequired() {
		return required;
	}
	public void setRequired(Integer required) {
		this.required = required;
	}
	public RedStoneNativeTitle getTitle() {
		return title;
	}
	public void setTitle(RedStoneNativeTitle title) {
		this.title = title;
	}
	public RedStoneNativeImg getImg() {
		return img;
	}
	public void setImg(RedStoneNativeImg img) {
		this.img = img;
	}
	public RedStoneNativeVideo getVideo() {
		return video;
	}
	public void setVideo(RedStoneNativeVideo video) {
		this.video = video;
	}
	public RedStoneNativeData getData() {
		return data;
	}
	public void setData(RedStoneNativeData data) {
		this.data = data;
	}
	public RedStoneExt getExt() {
		return ext;
	}
	public void setExt(RedStoneExt ext) {
		this.ext = ext;
	}
    
}
