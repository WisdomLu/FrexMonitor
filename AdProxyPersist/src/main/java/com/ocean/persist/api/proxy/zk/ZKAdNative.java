package com.ocean.persist.api.proxy.zk;
import com.ocean.persist.api.proxy.AdPullResponse;

public class ZKAdNative    implements AdPullResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2686443683535502971L;
    private ZKAdImg img;
    private ZKAdTile title;
    private String smallImg;
    private String desc;
    private ZKAdVideo video;
	public ZKAdImg getImg() {
		return img;
	}
	public void setImg(ZKAdImg img) {
		this.img = img;
	}
	public ZKAdTile getTitle() {
		return title;
	}
	public void setTitle(ZKAdTile title) {
		this.title = title;
	}
	public String getSmallImg() {
		return smallImg;
	}
	public void setSmallImg(String smallImg) {
		this.smallImg = smallImg;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public ZKAdVideo getVideo() {
		return video;
	}
	public void setVideo(ZKAdVideo video) {
		this.video = video;
	}
}
