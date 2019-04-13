package com.ocean.persist.api.proxy.zk;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

public class ZKCreative     implements AdPullResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2399482240690516022L;
/*	public static int OPEN_TYPE_ANY = 0;// 随意
	public static int OPEN_TYPE_INNER = 1;// 内部webview打开
	public static int OPEN_TYPE_OUTER = 2;// 跳外部浏览器
*/
	/** 创意ID */
	private String banner_id;

	/** 广告位创意序列号(一个广告位可以有多个创意，每个创意都有个编号，从1开始) */
	private int adspace_slot_seq;

	/** 广告打开方式：随意，内部webview，外部browser　 */
	private int open_type;

	/** 交互类型 */
	private int interaction_type;
	private ZKInteractionObject interaction_object;

	private int adm_type;
	private ZKAdm adm;
	private String  package_name;///应用广告包名
	private List<ZKAdEventTrack> event_track;
	private int expiration_time;
	private int disable_dynamic;
/*	public static int getOPEN_TYPE_INNER() {
		return OPEN_TYPE_INNER;
	}
	public static void setOPEN_TYPE_INNER(int oPEN_TYPE_INNER) {
		OPEN_TYPE_INNER = oPEN_TYPE_INNER;
	}
	public static int getOPEN_TYPE_OUTER() {
		return OPEN_TYPE_OUTER;
	}
	public static void setOPEN_TYPE_OUTER(int oPEN_TYPE_OUTER) {
		OPEN_TYPE_OUTER = oPEN_TYPE_OUTER;
	}*/
	public String getBanner_id() {
		return banner_id;
	}
	public void setBanner_id(String banner_id) {
		this.banner_id = banner_id;
	}
	public int getAdspace_slot_seq() {
		return adspace_slot_seq;
	}
	public void setAdspace_slot_seq(int adspace_slot_seq) {
		this.adspace_slot_seq = adspace_slot_seq;
	}

	public ZKAdm getAdm() {
		return adm;
	}
	public void setAdm(ZKAdm adm) {
		this.adm = adm;
	}
	public int getExpiration_time() {
		return expiration_time;
	}
	public void setExpiration_time(int expiration_time) {
		this.expiration_time = expiration_time;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public ZKInteractionObject getInteraction_object() {
		return interaction_object;
	}
	public void setInteraction_object(ZKInteractionObject interaction_object) {
		this.interaction_object = interaction_object;
	}
	public List<ZKAdEventTrack> getEvent_track() {
		return event_track;
	}
	public void setEvent_track(List<ZKAdEventTrack> event_track) {
		this.event_track = event_track;
	}
	public int getOpen_type() {
		return open_type;
	}
	public void setOpen_type(int open_type) {
		this.open_type = open_type;
	}
	public int getInteraction_type() {
		return interaction_type;
	}
	public void setInteraction_type(int interaction_type) {
		this.interaction_type = interaction_type;
	}
	public int getAdm_type() {
		return adm_type;
	}
	public void setAdm_type(int adm_type) {
		this.adm_type = adm_type;
	}
	public String getPackage_name() {
		return package_name;
	}
	public void setPackage_name(String package_name) {
		this.package_name = package_name;
	}
	public int getDisable_dynamic() {
		return disable_dynamic;
	}
	public void setDisable_dynamic(int disable_dynamic) {
		this.disable_dynamic = disable_dynamic;
	}
}
