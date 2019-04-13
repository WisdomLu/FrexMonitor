package com.ocean.persist.api.proxy.zhangyou;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullParams;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年2月9日 
      @version 1.0 
 */
public class ZhangYouAdResp  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -424807569397759920L;
    private String id;
    private int action;//广告动作类型， 1: 在 app 内 webview打开目标链接， 2： 在系统浏览器打开目标链接 ,3：打开 地图， 4： 拨打电话， 5：播放视频 , 6:App 下载
    private String html_snippet;//html广告代码
    private String image_url;//图片地址
    private int w;//广告宽度
    private int h;//广告高度
    private String app_bundle;//Android应用为包名，例： “com.zplay.demo” ；iOS应用为 iTunes ID 例：“12345678” ，app推广广告需要
    private String app_ver;
    private String target_url;
    private List<String> click_trackers;//当点击广告时，监控 URLURLURL列表，应在后台访问
    private List<String> imp_trackers;//当广告被展示时，监控 URL列表，应在后台访问
    private int refresh_interval;//广告应该在这个间隔后刷新，若为 0则不刷新
    private int inventory_type;//广告资源类型 , 1:图片， 2: 图文， 3: 视频， 4:html5，5: 文本 ，6: 原生 , 7:html5 url, 即一个指向 html5素材页面的 url
    private String title;//广告标题，图文广告时需要
    private String desc;//广告描述，图文广告时需要
    private String ssp_id;//ssp id, 当 ssp api 返回的广告时 是具体的 ssp id值， 当是 dsp返回的广告时 ，为自主 ADX的 ssp id （10 ）
    private String download_file_name;//下载文件名，动作类型为下载类型时需要
    private int file_size;//当广告位下载广告时，为下载文件大小
    private float price;//广告价格，若没有该数据则为 0, 单位分
    private String cur;//广告价格货币类型，默认为"CNY"
    private String[] ex_param;
    private String ssp_ad_id;
    private ZhangYouVideo video;
    private ZhangYouNativeResp _native;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getAction() {
		return action;
	}
	public void setAction(int action) {
		this.action = action;
	}
	public String getHtml_snippet() {
		return html_snippet;
	}
	public void setHtml_snippet(String html_snippet) {
		this.html_snippet = html_snippet;
	}
	public int getW() {
		return w;
	}
	public void setW(int w) {
		this.w = w;
	}
	public int getH() {
		return h;
	}
	public void setH(int h) {
		this.h = h;
	}
	public String getApp_bundle() {
		return app_bundle;
	}
	public void setApp_bundle(String app_bundle) {
		this.app_bundle = app_bundle;
	}
	public String getApp_ver() {
		return app_ver;
	}
	public void setApp_ver(String app_ver) {
		this.app_ver = app_ver;
	}
	public String getTarget_url() {
		return target_url;
	}
	public void setTarget_url(String target_url) {
		this.target_url = target_url;
	}

	public int getRefresh_interval() {
		return refresh_interval;
	}
	public void setRefresh_interval(int refresh_interval) {
		this.refresh_interval = refresh_interval;
	}
	public int getInventory_type() {
		return inventory_type;
	}
	public void setInventory_type(int inventory_type) {
		this.inventory_type = inventory_type;
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
	public String getSsp_id() {
		return ssp_id;
	}
	public void setSsp_id(String ssp_id) {
		this.ssp_id = ssp_id;
	}
	public String getDownload_file_name() {
		return download_file_name;
	}
	public void setDownload_file_name(String download_file_name) {
		this.download_file_name = download_file_name;
	}
	public int getFile_size() {
		return file_size;
	}
	public void setFile_size(int file_size) {
		this.file_size = file_size;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getCur() {
		return cur;
	}
	public void setCur(String cur) {
		this.cur = cur;
	}
	public String getSsp_ad_id() {
		return ssp_ad_id;
	}
	public void setSsp_ad_id(String ssp_ad_id) {
		this.ssp_ad_id = ssp_ad_id;
	}
	public ZhangYouVideo getVideo() {
		return video;
	}
	public void setVideo(ZhangYouVideo video) {
		this.video = video;
	}
	public ZhangYouNativeResp get_native() {
		return _native;
	}
	public void set_native(ZhangYouNativeResp _native) {
		this._native = _native;
	}

	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	public String[] getEx_param() {
		return ex_param;
	}
	public List<String> getClick_trackers() {
		return click_trackers;
	}
	public void setClick_trackers(List<String> click_trackers) {
		this.click_trackers = click_trackers;
	}
	public void setEx_param(String[] ex_param) {
		this.ex_param = ex_param;
	}
	public List<String> getImp_trackers() {
		return imp_trackers;
	}
	public void setImp_trackers(List<String> imp_trackers) {
		this.imp_trackers = imp_trackers;
	}
    
    
}
