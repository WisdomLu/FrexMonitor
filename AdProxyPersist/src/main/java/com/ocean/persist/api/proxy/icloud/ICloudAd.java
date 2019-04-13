package com.ocean.persist.api.proxy.icloud;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年7月12日 
      @version 1.0 
 */
public class ICloudAd    implements AdPullResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = -214323466267489619L;
	private String channelid;
	private String adspaceid;
	private int returncode;
	private int adwidth;
	private int adheight;
	private int adtype;
	private int showtime;
	private String clickurl;
	private List<String> imgtracking;
	private long systime;
	private List<String>thclkurl;
	private String cid;
	private String bid;
	private String imgurl;
	private String title;
	private String description;
	private String deeplink;
	public String getChannelid() {
		return channelid;
	}
	public void setChannelid(String channelid) {
		this.channelid = channelid;
	}
	public String getAdspaceid() {
		return adspaceid;
	}
	public void setAdspaceid(String adspaceid) {
		this.adspaceid = adspaceid;
	}
	public int getReturncode() {
		return returncode;
	}
	public void setReturncode(int returncode) {
		this.returncode = returncode;
	}
	public int getAdwidth() {
		return adwidth;
	}
	public void setAdwidth(int adwidth) {
		this.adwidth = adwidth;
	}
	public int getAdheight() {
		return adheight;
	}
	public void setAdheight(int adheight) {
		this.adheight = adheight;
	}
	public int getAdtype() {
		return adtype;
	}
	public void setAdtype(int adtype) {
		this.adtype = adtype;
	}
	public int getShowtime() {
		return showtime;
	}
	public void setShowtime(int showtime) {
		this.showtime = showtime;
	}
	public String getClickurl() {
		return clickurl;
	}
	public void setClickurl(String clickurl) {
		this.clickurl = clickurl;
	}
	public List<String> getImgtracking() {
		return imgtracking;
	}
	public void setImgtracking(List<String> imgtracking) {
		this.imgtracking = imgtracking;
	}
	public long getSystime() {
		return systime;
	}
	public void setSystime(long systime) {
		this.systime = systime;
	}
	public List<String> getThclkurl() {
		return thclkurl;
	}
	public void setThclkurl(List<String> thclkurl) {
		this.thclkurl = thclkurl;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDeeplink() {
		return deeplink;
	}
	public void setDeeplink(String deeplink) {
		this.deeplink = deeplink;
	}
}
