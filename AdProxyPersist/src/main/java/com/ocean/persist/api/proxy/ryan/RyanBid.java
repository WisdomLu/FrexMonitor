package com.ocean.persist.api.proxy.ryan;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullParams;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年1月4日 
      @version 1.0 
 */
public class RyanBid    {
   private static final long serialVersionUID = 4369715061694634483L;
   private String impid;//required,DSP 回填 Bid Request 中对应的曝 光 ID
   private float price;//required,DSP 出价， 单位是元/千次曝光,即 CPM
   private String nurl;//recommend,获胜通知地址
   private RyanBannerResp banner_ad;//required,banner 请求返回
   private RyanNativeResp native_ad;//required,原生请求返回
   private RyanVideoResp video_ad;//required,视频请求返回
   private Integer lattr;//required,落地页点击行为类型： 1 – 网页 2 – 下载 3 – 应用市场
   private String dealid;//optional,用于 pmp 交易，指向请求中的dealid
   private Object ext;//optional,拓展字段
	public String getImpid() {
		return impid;
	}
	public void setImpid(String impid) {
		this.impid = impid;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getNurl() {
		return nurl;
	}
	public void setNurl(String nurl) {
		this.nurl = nurl;
	}
	public RyanBannerResp getBanner_ad() {
		return banner_ad;
	}
	public void setBanner_ad(RyanBannerResp banner_ad) {
		this.banner_ad = banner_ad;
	}
	public RyanNativeResp getNative_ad() {
		return native_ad;
	}
	public void setNative_ad(RyanNativeResp native_ad) {
		this.native_ad = native_ad;
	}
	public RyanVideoResp getVideo_ad() {
		return video_ad;
	}
	public void setVideo_ad(RyanVideoResp video_ad) {
		this.video_ad = video_ad;
	}
	public Integer getLattr() {
		return lattr;
	}
	public void setLattr(Integer lattr) {
		this.lattr = lattr;
	}
	public String getDealid() {
		return dealid;
	}
	public void setDealid(String dealid) {
		this.dealid = dealid;
	}
	public Object getExt() {
		return ext;
	}
	public void setExt(Object ext) {
		this.ext = ext;
	}
}
