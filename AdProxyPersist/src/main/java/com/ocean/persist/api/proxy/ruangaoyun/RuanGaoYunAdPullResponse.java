package com.ocean.persist.api.proxy.ruangaoyun;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年4月1日 
      @version 1.0 
 */
public class RuanGaoYunAdPullResponse  extends AbstractBaseEntity  implements  AdPullResponse{

	public String getAd_url() {
		return ad_url;
	}
	public void setAd_url(String ad_url) {
		this.ad_url = ad_url;
	}
	public Integer getWidth() {
		return width;
	}
	public void setWidth(Integer width) {
		this.width = width;
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	public String getMaterialtype() {
		return materialtype;
	}
	public void setMaterialtype(String materialtype) {
		this.materialtype = materialtype;
	}
	public String getDplurl() {
		return dplurl;
	}
	public void setDplurl(String dplurl) {
		this.dplurl = dplurl;
	}
	public Integer getDuration() {
		return duration;
	}
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	public String getIcon_url() {
		return icon_url;
	}
	public void setIcon_url(String icon_url) {
		this.icon_url = icon_url;
	}
	public String getButtontext() {
		return buttontext;
	}
	public void setButtontext(String buttontext) {
		this.buttontext = buttontext;
	}
	public Integer getInstallcount() {
		return installcount;
	}
	public void setInstallcount(Integer installcount) {
		this.installcount = installcount;
	}
	public Float getStarcount() {
		return starcount;
	}
	public void setStarcount(Float starcount) {
		this.starcount = starcount;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getCoverimg() {
		return coverimg;
	}
	public void setCoverimg(String coverimg) {
		this.coverimg = coverimg;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 520295291165070955L;
    private String id;
    private String title;
    private String desc;
    private String ad_url;
    private Integer width;
    private Integer height;
    private String materialtype;
    
    private List<RuanGaoYunReport> pv;
    private List<RuanGaoYunReport> click;
    
    
    private String target;
    private String dplurl;
    
    private String apkname;
    private Integer duration;
    
    
    private Integer ad_type;
    
    private String _package;
    private String app_store_id;
    private String icon_url;
    private String buttontext;
    private Integer installcount;
    private Float starcount;
    private String price;
    private String coverimg;
    
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getApkname() {
		return apkname;
	}
	public void setApkname(String apkname) {
		this.apkname = apkname;
	}
	public Integer getAd_type() {
		return ad_type;
	}
	public void setAd_type(Integer ad_type) {
		this.ad_type = ad_type;
	}
	public String get_package() {
		return _package;
	}
	public void set_package(String _package) {
		this._package = _package;
	}
	public String getApp_store_id() {
		return app_store_id;
	}
	public void setApp_store_id(String app_store_id) {
		this.app_store_id = app_store_id;
	}
	public List<RuanGaoYunReport> getPv() {
		return pv;
	}
	public void setPv(List<RuanGaoYunReport> pv) {
		this.pv = pv;
	}
	public List<RuanGaoYunReport> getClick() {
		return click;
	}
	public void setClick(List<RuanGaoYunReport> click) {
		this.click = click;
	}
}
