package com.ocean.persist.api.proxy.ryan;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullParams;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年1月4日 
      @version 1.0 
 */
public class RyanImpression   {
   /**
	 * 
	 */
	private static final long serialVersionUID = -4647825093062095088L;
   private String id;//required,曝光id,由ADX生成
   private String tagid;//recommend,广告位id
   private RyanBannerReq banner;//required,banner广告位的详细描述
   private RyanNativeReq _native ;//required,原生广告
   private RyanVideoReq video;//required,视频广告
   private Integer instl;//required,广告类型，1:横幅,2:全屏,3:插屏,5:音频,6:视频,7:焦点图,8:信息流,9:内容流,10:动态消息,12:一图,13:三图一文
   private float bidfloor;//required,底价，单位：元/千次，即CPM
   private String cur;//optional,价格单位，USD,CNY,默认CNY
   private RyanPmp pmp;//optional,用于 pmp 交易模式如pdb
   private Integer is_support_deeplink;//required,媒体是否支持deeplink 落地页， 1:支持(媒体支持deeplink 落地页肯定也会支持普通的落地页) 0:支持（媒体只支持普通的落地页不支 持 deeplink 落地页）
   private Object ext;//optional，拓展信息
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTagid() {
		return tagid;
	}
	public void setTagid(String tagid) {
		this.tagid = tagid;
	}
	public RyanBannerReq getBanner() {
		return banner;
	}
	public void setBanner(RyanBannerReq banner) {
		this.banner = banner;
	}
	public RyanNativeReq get_native() {
		return _native;
	}
	public void set_native(RyanNativeReq _native) {
		this._native = _native;
	}
	public RyanVideoReq getVideo() {
		return video;
	}
	public void setVideo(RyanVideoReq video) {
		this.video = video;
	}
	public Integer getInstl() {
		return instl;
	}
	public void setInstl(Integer instl) {
		this.instl = instl;
	}
	public float getBidfloor() {
		return bidfloor;
	}
	public void setBidfloor(float bidfloor) {
		this.bidfloor = bidfloor;
	}
	public String getCur() {
		return cur;
	}
	public void setCur(String cur) {
		this.cur = cur;
	}
	public RyanPmp getPmp() {
		return pmp;
	}
	public void setPmp(RyanPmp pmp) {
		this.pmp = pmp;
	}
	public Integer getIs_support_deeplink() {
		return is_support_deeplink;
	}
	public void setIs_support_deeplink(Integer is_support_deeplink) {
		this.is_support_deeplink = is_support_deeplink;
	}
	public Object getExt() {
		return ext;
	}
	public void setExt(Object ext) {
		this.ext = ext;
	}
   
}
