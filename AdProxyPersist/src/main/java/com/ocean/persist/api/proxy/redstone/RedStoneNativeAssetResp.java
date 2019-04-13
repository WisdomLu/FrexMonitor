package com.ocean.persist.api.proxy.redstone;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年3月8日 
      @version 1.0 
 */
public class RedStoneNativeAssetResp   implements  AdPullResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 596006357310411583L;
	private Integer id;
	private Integer required;
	private RedStoneNativeTitleResp title;
	private RedStoneNativeImgResp img;
	private RedStoneNativeVideoResp video;
	private RedStoneNativeDataResp data;
	private RedStoneNativeLink link;
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
	public RedStoneNativeTitleResp getTitle() {
		return title;
	}
	public void setTitle(RedStoneNativeTitleResp title) {
		this.title = title;
	}
	public RedStoneNativeImgResp getImg() {
		return img;
	}
	public void setImg(RedStoneNativeImgResp img) {
		this.img = img;
	}
	public RedStoneNativeVideoResp getVideo() {
		return video;
	}
	public void setVideo(RedStoneNativeVideoResp video) {
		this.video = video;
	}
	public RedStoneNativeDataResp getData() {
		return data;
	}
	public void setData(RedStoneNativeDataResp data) {
		this.data = data;
	}
	public RedStoneNativeLink getLink() {
		return link;
	}
	public void setLink(RedStoneNativeLink link) {
		this.link = link;
	}
	public RedStoneExt getExt() {
		return ext;
	}
	public void setExt(RedStoneExt ext) {
		this.ext = ext;
	}

}
