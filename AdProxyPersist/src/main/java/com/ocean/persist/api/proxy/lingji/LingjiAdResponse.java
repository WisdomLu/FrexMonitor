package com.ocean.persist.api.proxy.lingji;

import java.util.List;
import java.util.Map;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

public class LingjiAdResponse  extends AbstractBaseEntity implements AdPullResponse{

	private static final long serialVersionUID = 1L;

	private String pid;// String	广告位id，即灵集分配给媒体的合作广告位的唯一标识
	private String etype;// String	物料展示类型	C:动态物料 V：video(嵌入式视频广告) N：Banner
	// String	物料类型	"I"表示图片物料，"F"表示Flash物料，"V"表示视频物料，
	// "X"表示多点击地址Flash物料/富媒体物料(多点击地址Flash物料是已嵌入跳转地址和Click监测代码的物料)
	private Integer action;// 1-应用推广类 2-网页推广类 3-未知
	private String type;
	private String src;// String	物料URL	
	private Integer adw;// Number	广告位宽度	
	private Integer adh;// Number	广告位高度	
	private String ldp;// String	落地页URL地址	1.需要媒体支持外链 2.需要媒体支持302跳转
	// Array	点击监测URL(s)	1.需要媒体支持多条点击监测 2.需要媒体点击监测支持302跳转。
	// 该值为包含点击监测地址的数组(在cm数据非[]数据的情况下,媒体需要支持并行访问数组中的url发送点击监测) eg. ["url1","url2", ...]
	private List<String> cm;
	// Object	曝光监测URL(s)及发送曝光监测的时机	1.需要媒体支持多条曝光监测 2.需要媒体曝光监测支持302跳转key为发送曝光监测时机(秒)，
	// value为曝光监测的URL(s); e.g. {"0": ["url1","url2", ...], "10": ["url1", "url2", ...]}，目前灵集仅返回第零秒的曝光监测.
	private Map<String, List<String>> pm;
	private String reqid;// 和媒体请求中reqid一致，如果媒体请求中有指定唯一reqid，XTrader广告返回中会包含该reqid。
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getEtype() {
		return etype;
	}
	public void setEtype(String etype) {
		this.etype = etype;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public Integer getAdw() {
		return adw;
	}
	public void setAdw(Integer adw) {
		this.adw = adw;
	}
	public Integer getAdh() {
		return adh;
	}
	public void setAdh(Integer adh) {
		this.adh = adh;
	}
	public String getLdp() {
		return ldp;
	}
	public void setLdp(String ldp) {
		this.ldp = ldp;
	}
	public List<String> getCm() {
		return cm;
	}
	public void setCm(List<String> cm) {
		this.cm = cm;
	}
	public Map<String, List<String>> getPm() {
		return pm;
	}
	public void setPm(Map<String, List<String>> pm) {
		this.pm = pm;
	}
	
	public Integer getAction() {
		return action;
	}
	public void setAction(Integer action) {
		this.action = action;
	}
	public String getReqid() {
		return reqid;
	}
	public void setReqid(String reqid) {
		this.reqid = reqid;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
