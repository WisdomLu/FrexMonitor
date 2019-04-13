package com.ocean.persist.api.proxy.yijifen;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

public class YijifenAdResponse  extends AbstractBaseEntity implements AdPullResponse{

	private static final long serialVersionUID = 1L;
    private String status;
	private String msg;// 返回状态码描述信息可能取值： 1.ok 2.error 3.no ad
	private YijifenAdData data;// 返回的广告数据

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public YijifenAdData getData() {
		return data;
	}

	public void setData(YijifenAdData data) {
		this.data = data;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
