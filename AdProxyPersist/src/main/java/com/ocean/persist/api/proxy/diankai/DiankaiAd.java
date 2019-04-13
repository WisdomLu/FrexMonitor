package com.ocean.persist.api.proxy.diankai;

import java.util.List;
import java.util.Map;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年6月24日 
      @version 1.0 
 */
public class DiankaiAd   implements AdPullResponse{
    /**
	 * 
	 */
	private static final long serialVersionUID = 6912951921602949814L;
	private String mark;
	private String ad_logo;
	private String  ad_text;
	private List<String> icon;
	private String pic;
	private String app_name;
	private String package_name;
	private String click_url;
	private String brand_name;
	private List<String> description;
	private String memo;
	private int s;
    private String ad_category;
    private List<String> view_report;
    private List<String> click_report;
    private List<DiankaiDownloadReport> download_report;
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	public String getAd_logo() {
		return ad_logo;
	}
	public void setAd_logo(String ad_logo) {
		this.ad_logo = ad_logo;
	}
	public String getAd_text() {
		return ad_text;
	}
	public void setAd_text(String ad_text) {
		this.ad_text = ad_text;
	}

	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getApp_name() {
		return app_name;
	}
	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}
	public String getPackage_name() {
		return package_name;
	}
	public void setPackage_name(String package_name) {
		this.package_name = package_name;
	}
	public String getClick_url() {
		return click_url;
	}
	public void setClick_url(String click_url) {
		this.click_url = click_url;
	}
	public String getBrand_name() {
		return brand_name;
	}
	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}
	public List<String> getView_report() {
		return view_report;
	}
	public void setView_report(List<String> view_report) {
		this.view_report = view_report;
	}
	public List<String> getClick_report() {
		return click_report;
	}
	public void setClick_report(List<String> click_report) {
		this.click_report = click_report;
	}
	public String getAd_category() {
		return ad_category;
	}
	public void setAd_category(String ad_category) {
		this.ad_category = ad_category;
	}
	public List<String> getDescription() {
		return description;
	}
	public void setDescription(List<String> description) {
		this.description = description;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public int getS() {
		return s;
	}
	public void setS(int s) {
		this.s = s;
	}
	public List<DiankaiDownloadReport> getDownload_report() {
		return download_report;
	}
	public void setDownload_report(List<DiankaiDownloadReport> download_report) {
		this.download_report = download_report;
	}
	public List<String> getIcon() {
		return icon;
	}
	public void setIcon(List<String> icon) {
		this.icon = icon;
	}
}
