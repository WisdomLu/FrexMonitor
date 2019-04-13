package com.ocean.persist.api.proxy.xunfa;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

public class XunfaAdPullResp   extends AbstractBaseEntity  implements AdPullResponse{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5113178315276880761L;
	private int res;//	Int	返回结果，各个参数都正确，并且数据正确值为 0， 否则其他
	private List<XunfaAd> data;//	data 是 JSON  数组，数组中的每个 item 元素是一个 resource 的信息。	
	public int getRes() {
		return res;
	}
	public List<XunfaAd> getData() {
		return data;
	}
	public void setRes(int res) {
		this.res = res;
	}
	public void setData(List<XunfaAd> data) {
		this.data = data;
	}
}
