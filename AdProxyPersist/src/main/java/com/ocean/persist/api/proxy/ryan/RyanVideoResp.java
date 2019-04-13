package com.ocean.persist.api.proxy.ryan;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;

import com.ocean.persist.api.proxy.AdPullParams;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年1月4日 
      @version 1.0 
 */
public class RyanVideoResp   {
   private static final long serialVersionUID = 1280375117590400179L;
   private String src;//required,视频链接 URL
   private Integer duration;//required,视频时长， 单位： 毫秒
   private String landing;//required,落地页 URL， 参见 8.4 离散物料落地页说明
   private String deep_link;//recommend,deeplink 落地页， 参见 8.4 离散物料落地页说明
   private List<String> starttrackers;//required,视频开始播放监测
   private List<String> completetrackers;//recommend,视频播放完毕监测
   private List<String> clicktrackers;//required,点击监测
   private String package_name;//下载类广告必须，下载包名称
   private String app_name;//下载类广告必填，应用名， 如xx输入法 app的应用名为“xx输入法”
   private Integer is_marked;//requried,0，表示广告物料中未携带广告来源，此时 DSP 需要在ad_source_mark 中标识广告来源； 1，表示广告物料（如视频水印）中已经自带了广告来源及“ 广告”标识字样。
   private String ad_source_mark;//optional,纯文本说明，如“ xx 广告平台”，用于存放 dsp 指定的广告来源（若is_markded 为 1 则以广告物料中的来源标记为准，不再采用ad_source_mark 的值。 若is_markded 为 0 则需要提供该字段）
   private Object ext;//拓展字段
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public Integer getDuration() {
		return duration;
	}
	public void setDuration(Integer duration) {
		this.duration = duration;
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
	public List<String> getStarttrackers() {
		return starttrackers;
	}
	public void setStarttrackers(List<String> starttrackers) {
		this.starttrackers = starttrackers;
	}
	public List<String> getCompletetrackers() {
		return completetrackers;
	}
	public void setCompletetrackers(List<String> completetrackers) {
		this.completetrackers = completetrackers;
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
	public Object getExt() {
		return ext;
	}
	public void setExt(Object ext) {
		this.ext = ext;
	}
   
   
   
   
   
}
