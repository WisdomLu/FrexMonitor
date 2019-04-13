package com.ocean.persist.api.proxy.paerjiate_t2;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

public class ParerjiateT2AdPullResponse    extends AbstractBaseEntity  implements AdPullResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = -493260912041152017L;
	private int code ;//􀂭 􀄠􀁙􀀧􀀆􀄴 0 􀄅􀃢􀀶􀄱
	private String msg;// 􀁏 􀄱􀄒􀀦􀂐􀂠􀄥􀃩􀀆status 􀄴 0 􀂫􀂲􀁣􀀫
	private List<ParerjiateT2Ad> ads;// 􀁏 􀂂􀁑􀀦􀂐􀀆json array
	public int getCode() {
		return code;
	}
	public String getMsg() {
		return msg;
	}
	public List<ParerjiateT2Ad> getAds() {
		return ads;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public void setAds(List<ParerjiateT2Ad> ads) {
		this.ads = ads;
	}
}
