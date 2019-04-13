package com.ocean.persist.api.proxy.ryan;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullParams;

/**title、 desc、 img、 icon 这四个返回请求中指定的字段即可（ 也可以四个全部返 回） ,指定字段需要满足请求中的规格约束。 
 * 
 * * @author Alex & E-mail:569246607@qq.com
      @date   2017年1月4日 
      @version 1.0 
 */
public class RyanNativeResp    {

   private static final long serialVersionUID = -8759191031702078674L;
   private String title;//required,标题
   private String desc;//required,描述
   private String icon;//required,图标 URL
   private String img;//required,图片 URL
   private String landing;//required,落地页， 参见 8.4 离散物料落地页说明
   private String deep_link;//recommend,deeplink 落地页， 参见8.4 离散物料落地页说明
   private List<String> imptrackers;//required,展示监测
   private List<String> clicktrackers;//required,点击监测
   private String package_name;//下载类广告必须，下载包名称
   private String app_name;//下载类广告必须，应用名， 如xx输入法 app 的应用名为“xx输 入法”
   private List<String> img_urls;//一图广告和 三图一文广 告必须,用于新增的一图和三图一 文广告类型，每一项表示 一个图片链接，该数组有 几项就是几个图。
   private Integer is_marked;//required,0，表示广告物料中未携带广告来源，此时 DSP 需要在 ad_source_mark 中标识广告来源； 1，表示广告物料（如图片水印）中已经自带了广告来源和“ 广告” 标识字样。
   private String ad_source_mark;//optional,纯文本说明，如“ xx 广告平台”，用于存放 dsp 指定的广告来源（若is_markded 为 1 则以广告物料中的来源标记为准，不再采用此处ad_source_mark 的值。 若is_markded 为 0 则需要提供该字段）
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
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getLanding() {
		return landing;
	}
	public void setLanding(String landing) {
		this.landing = landing;
	}
	public String getDeep_link() {
		return deep_link;
	}
	public void setDeep_link(String deep_link) {
		this.deep_link = deep_link;
	}
	public List<String> getImptrackers() {
		return imptrackers;
	}
	public void setImptrackers(List<String> imptrackers) {
		this.imptrackers = imptrackers;
	}
	public List<String> getClicktrackers() {
		return clicktrackers;
	}
	public void setClicktrackers(List<String> clicktrackers) {
		this.clicktrackers = clicktrackers;
	}
	public String getPackage_name() {
		return package_name;
	}
	public void setPackage_name(String package_name) {
		this.package_name = package_name;
	}
	public String getApp_name() {
		return app_name;
	}
	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}
	public List<String> getImg_urls() {
		return img_urls;
	}
	public void setImg_urls(List<String> img_urls) {
		this.img_urls = img_urls;
	}
	public Integer getIs_marked() {
		return is_marked;
	}
	public void setIs_marked(Integer is_marked) {
		this.is_marked = is_marked;
	}
	public String getAd_source_mark() {
		return ad_source_mark;
	}
	public void setAd_source_mark(String ad_source_mark) {
		this.ad_source_mark = ad_source_mark;
	}
   
}
