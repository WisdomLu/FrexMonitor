package com.ocean.persist.api.proxy.ryan;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullParams;

/** 1. mtype=1 时， title、 desc 必填；
 *  2. mtype=2 时， image_url 必填； 
 *  3. mtype=3 时， title、 desc、 image_url 必填；
 *  4. mtype=4 时， html 必填， 且需要和 ADX 对接人员协商 ADX 监控添加的方式， 建议 DSP返回非 html 物料。 
 * * @author Alex & E-mail:569246607@qq.com
      @date   2017年1月4日 
      @version 1.0 
 */
public class RyanBannerResp     {
	private static final long serialVersionUID = 8246725603151474249L;
	private Integer mtype;//required,物料类型：1 – 纯文字 2 – 纯图片 3 – 图文 4 – html
	private String title;//recommend,标题
	private String desc;//recommend,描述
	private String image_url;//recommend,图片地址 URL
	private String html;//recommend,mtype=4 时，具体描述参见8.3 普通广告 html 物料说明
	private String landing;//required,落地页， 参见 8.4 离散物料落地页说明
	private String deep_link;//recommend,deeplink 落地页， 参见 8.4离散物料落地页说明
	private Integer w;//recommend,图片宽度
	private Integer h;//recommend,图片高度
	private List<String> impress;//required,展示监控
	private List<String> click;//required,点击监控
	private String package_name;//下载类广告必填，下载包名称
	private String app_name;//下载类广告必填，应用名， 如xx输入法 app的应用名为“xx输入法”
	private Integer is_marked;//required,0，表示广告物料中未携带广告的来源，此时 DSP 需要在ad_source_mark 中标识广告来源； 1，表示广告物料（如图片水印或者 html）中已经自带了广告的来源和“ 广告” 标识字样。 注意，若平台下发完整 html 或者html 片段，请务必在 html物料中携带广告的来源及“ 广告”标识字样。
	private String ad_source_mark;//optonal,纯文本说明，如“ xx 广告平台”，用于存放 dsp 指定的广告来源（若 is_markded为 1 则以广告物料中的来源为准，不再采用此处ad_source_mark 的值。 若is_markded 为 0 则需要提供该字段）
	private Object ext;//optional,拓展字段
	public Integer getMtype() {
		return mtype;
	}
	public void setMtype(Integer mtype) {
		this.mtype = mtype;
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
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
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
	public Integer getW() {
		return w;
	}
	public void setW(Integer w) {
		this.w = w;
	}
	public Integer getH() {
		return h;
	}
	public void setH(Integer h) {
		this.h = h;
	}
	public List<String> getImpress() {
		return impress;
	}
	public void setImpress(List<String> impress) {
		this.impress = impress;
	}
	public List<String> getClick() {
		return click;
	}
	public void setClick(List<String> click) {
		this.click = click;
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
