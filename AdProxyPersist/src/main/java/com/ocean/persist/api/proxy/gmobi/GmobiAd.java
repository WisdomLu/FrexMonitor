package com.ocean.persist.api.proxy.gmobi;
/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年8月16日 
      @version 1.0 
 */
public class GmobiAd {
	private GmobiImage image;
	private GmobiImage icon;
	private String title;
	private String cta;
	private String url;
	private GmobiTracker trackers;
	public GmobiImage getImage() {
		return image;
	}
	public void setImage(GmobiImage image) {
		this.image = image;
	}
	public GmobiImage getIcon() {
		return icon;
	}
	public void setIcon(GmobiImage icon) {
		this.icon = icon;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCta() {
		return cta;
	}
	public void setCta(String cta) {
		this.cta = cta;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public GmobiTracker getTrackers() {
		return trackers;
	}
	public void setTrackers(GmobiTracker trackers) {
		this.trackers = trackers;
	}
}
