package com.ocean.persist.api.proxy.adwo;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年12月13日 
      @version 1.0 
 */
public class AdwoAd  extends AbstractBaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3416884194133651125L;
	private int adid;// Y int 广告ID
	/*// Y int 广告点击类型
	1 代表打开网页类
	3 代表下载类
	15 代表视频播放*/
	private int actiontype ;
	private AdwoVideo video ;// N obejct 视频物料对象video obejct Format=4 时必有
	private AdwoFeeds feeds ;// N obejct 信息流物料对象feeds object Format=3 时必有
	private String target ;// Y String 广告推广地址
	private String pk_name;//  N String 下载广告包名只有actiontype
	private String pk_ver ;// N String 下载广告版本 为 3时才可能有
	private List<String> showbeacons ;// Y array of String 展示监测地址,可以有多个,展示时必须调用
	private List<String> beacons ;// Y array of String 点击监测地址可以有多个
	public int getAdid() {
		return adid;
	}
	public void setAdid(int adid) {
		this.adid = adid;
	}
	public int getActiontype() {
		return actiontype;
	}
	public void setActiontype(int actiontype) {
		this.actiontype = actiontype;
	}
	public AdwoVideo getVideo() {
		return video;
	}
	public void setVideo(AdwoVideo video) {
		this.video = video;
	}
	public AdwoFeeds getFeeds() {
		return feeds;
	}
	public void setFeeds(AdwoFeeds feeds) {
		this.feeds = feeds;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getPk_name() {
		return pk_name;
	}
	public void setPk_name(String pk_name) {
		this.pk_name = pk_name;
	}
	public String getPk_ver() {
		return pk_ver;
	}
	public void setPk_ver(String pk_ver) {
		this.pk_ver = pk_ver;
	}
	public List<String> getShowbeacons() {
		return showbeacons;
	}
	public void setShowbeacons(List<String> showbeacons) {
		this.showbeacons = showbeacons;
	}
	public List<String> getBeacons() {
		return beacons;
	}
	public void setBeacons(List<String> beacons) {
		this.beacons = beacons;
	}
}
