package com.ocean.persist.api.proxy.qidian;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

public class QidianAdPullRespV2   extends AbstractBaseEntity  implements AdPullResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1333276770909839931L;
	private int code;
	private String msg ;
	private QidianDataV2 data;
	public int getCode() {
		return code;
	}
	public String getMsg() {
		return msg;
	}
	public QidianDataV2 getData() {
		return data;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public void setData(QidianDataV2 data) {
		this.data = data;
	}
}
