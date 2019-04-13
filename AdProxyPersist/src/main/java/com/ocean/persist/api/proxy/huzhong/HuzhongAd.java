package com.ocean.persist.api.proxy.huzhong;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年6月29日 
      @version 1.0 
 */
public class HuzhongAd   implements AdPullResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6861480722521379943L;
	private int action;
	private String aid;
	private HuzhongApp app;

	private String desc;
	private List<String> download_urls;
	private List<String> downloaded_urls;
	private List<String> install_urls;
	private List<String> installed_urls;
	private int width;
	private int height;
	private HuzhongImp imp;
	private List<String> clk;
	private String mime;
	private String sid;
	private String src;
	private String html;
	private List<String> ext_urls;
	private String title;
	private String url;
	private String dp_url;
	private List<String> dp_clk;
	public int getAction() {
		return action;
	}
	public void setAction(int action) {
		this.action = action;
	}
	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}
	public HuzhongApp getApp() {
		return app;
	}
	public void setApp(HuzhongApp app) {
		this.app = app;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public List<String> getDownload_urls() {
		return download_urls;
	}
	public void setDownload_urls(List<String> download_urls) {
		this.download_urls = download_urls;
	}
	public List<String> getDownloaded_urls() {
		return downloaded_urls;
	}
	public void setDownloaded_urls(List<String> downloaded_urls) {
		this.downloaded_urls = downloaded_urls;
	}
	public List<String> getInstalled_urls() {
		return installed_urls;
	}
	public void setInstalled_urls(List<String> installed_urls) {
		this.installed_urls = installed_urls;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public HuzhongImp getImp() {
		return imp;
	}
	public void setImp(HuzhongImp imp) {
		this.imp = imp;
	}
	public List<String> getClk() {
		return clk;
	}
	public void setClk(List<String> clk) {
		this.clk = clk;
	}
	public String getMime() {
		return mime;
	}
	public void setMime(String mime) {
		this.mime = mime;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public List<String> getExt_urls() {
		return ext_urls;
	}
	public void setExt_urls(List<String> ext_urls) {
		this.ext_urls = ext_urls;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDp_url() {
		return dp_url;
	}
	public void setDp_url(String dp_url) {
		this.dp_url = dp_url;
	}
	public List<String> getDp_clk() {
		return dp_clk;
	}
	public void setDp_clk(List<String> dp_clk) {
		this.dp_clk = dp_clk;
	}
	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}
	public List<String> getInstall_urls() {
		return install_urls;
	}
	public void setInstall_urls(List<String> install_urls) {
		this.install_urls = install_urls;
	}

}
