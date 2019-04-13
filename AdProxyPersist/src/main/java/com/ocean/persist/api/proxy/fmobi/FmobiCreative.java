package com.ocean.persist.api.proxy.fmobi;

import java.util.List;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年10月27日 
      @version 1.0 
 */
public class FmobiCreative {
	private int cid;
	private boolean is_html;
	private int index;
	private FmobiBanner banner;
	private FmobiVideo video;
	private List<FmobiNative> ad_native;
	private String html_snippet;
	private FmobiAppResp app;
	private List<String> impression;
	private List<String> click;
	private List<FmobiTracking> tracking;
	private List<FmobiEvent> event;
	
	private String admark ;
	private int source;
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public boolean isIs_html() {
		return is_html;
	}
	public void setIs_html(boolean is_html) {
		this.is_html = is_html;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public FmobiBanner getBanner() {
		return banner;
	}
	public void setBanner(FmobiBanner banner) {
		this.banner = banner;
	}
	public FmobiVideo getVideo() {
		return video;
	}
	public void setVideo(FmobiVideo video) {
		this.video = video;
	}
	public List<FmobiNative> getAd_native() {
		return ad_native;
	}
	public void setAd_native(List<FmobiNative> ad_native) {
		this.ad_native = ad_native;
	}
	public String getHtml_snippet() {
		return html_snippet;
	}
	public void setHtml_snippet(String html_snippet) {
		this.html_snippet = html_snippet;
	}
	public FmobiAppResp getApp() {
		return app;
	}
	public void setApp(FmobiAppResp app) {
		this.app = app;
	}
	public List<String> getImpression() {
		return impression;
	}
	public void setImpression(List<String> impression) {
		this.impression = impression;
	}
	public List<String> getClick() {
		return click;
	}
	public void setClick(List<String> click) {
		this.click = click;
	}
	public List<FmobiTracking> getTracking() {
		return tracking;
	}
	public void setTracking(List<FmobiTracking> tracking) {
		this.tracking = tracking;
	}
	public String getAdmark() {
		return admark;
	}
	public void setAdmark(String admark) {
		this.admark = admark;
	}
	public int getSource() {
		return source;
	}
	public void setSource(int source) {
		this.source = source;
	}
	public List<FmobiEvent> getEvent() {
		return event;
	}
	public void setEvent(List<FmobiEvent> event) {
		this.event = event;
	}
	
	
	
	
	
}
