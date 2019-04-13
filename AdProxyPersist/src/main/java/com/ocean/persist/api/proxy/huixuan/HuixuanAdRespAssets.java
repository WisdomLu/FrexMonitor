package com.ocean.persist.api.proxy.huixuan;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年6月16日 
      @version 1.0 
 */
public class HuixuanAdRespAssets    implements AdPullResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5836038441938368861L;
	private int Id;
	private  HuixuanAdTitle title;
	private  HuixuanAdImg img;
	private  HuixuanAdData data;
	private HuixuanAdLink link;
	private  HuixuanAdVideo video;
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public HuixuanAdTitle getTitle() {
		return title;
	}
	public void setTitle(HuixuanAdTitle title) {
		this.title = title;
	}
	public HuixuanAdImg getImg() {
		return img;
	}
	public void setImg(HuixuanAdImg img) {
		this.img = img;
	}
	public HuixuanAdData getData() {
		return data;
	}
	public void setData(HuixuanAdData data) {
		this.data = data;
	}
	public HuixuanAdLink getLink() {
		return link;
	}
	public void setLink(HuixuanAdLink link) {
		this.link = link;
	}
	public HuixuanAdVideo getVideo() {
		return video;
	}
	public void setVideo(HuixuanAdVideo video) {
		this.video = video;
	}
}
