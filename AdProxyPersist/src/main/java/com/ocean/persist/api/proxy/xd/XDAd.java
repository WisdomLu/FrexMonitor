package com.ocean.persist.api.proxy.xd;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年7月10日 
      @version 1.0 
 */
public class XDAd    implements AdPullResponse{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1899821611143036817L;
	private String title;
	private String desc;
	private String imageUrl;
	private int imageWidth;
	private int imageHeight;
	private String htmlSnippet;
	private String iconUrl;
	private String clickUrl;
	private String gdtClickUrl;
	private int creativeType;
	private int interactionType;
	private String packageName;
    private List<XDTracks>	tracks;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public int getImageWidth() {
		return imageWidth;
	}
	public void setImageWidth(int imageWidth) {
		this.imageWidth = imageWidth;
	}
	public int getImageHeight() {
		return imageHeight;
	}
	public void setImageHeight(int imageHeight) {
		this.imageHeight = imageHeight;
	}
	public String getHtmlSnippet() {
		return htmlSnippet;
	}
	public void setHtmlSnippet(String htmlSnippet) {
		this.htmlSnippet = htmlSnippet;
	}
	public String getIconUrl() {
		return iconUrl;
	}
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
	public String getClickUrl() {
		return clickUrl;
	}
	public void setClickUrl(String clickUrl) {
		this.clickUrl = clickUrl;
	}
	public String getGdtClickUrl() {
		return gdtClickUrl;
	}
	public void setGdtClickUrl(String gdtClickUrl) {
		this.gdtClickUrl = gdtClickUrl;
	}
	public int getCreativeType() {
		return creativeType;
	}
	public void setCreativeType(int creativeType) {
		this.creativeType = creativeType;
	}
	public int getInteractionType() {
		return interactionType;
	}
	public void setInteractionType(int interactionType) {
		this.interactionType = interactionType;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public List<XDTracks> getTracks() {
		return tracks;
	}
	public void setTracks(List<XDTracks> tracks) {
		this.tracks = tracks;
	}
}
