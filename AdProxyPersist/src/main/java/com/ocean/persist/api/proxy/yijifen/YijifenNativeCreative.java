package com.ocean.persist.api.proxy.yijifen;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;
public class YijifenNativeCreative  implements AdPullResponse{

	private static final long serialVersionUID = 1L;

	private String native_template_id;// 原生模板ID
	private List<YijifenNativeTempAttr> tempAttrs;// 模板元素信息

	public String getNative_template_id() {
		return native_template_id;
	}

	public void setNative_template_id(String native_template_id) {
		this.native_template_id = native_template_id;
	}

	public List<YijifenNativeTempAttr> getTempAttrs() {
		return tempAttrs;
	}

	public void setTempAttrs(List<YijifenNativeTempAttr> tempAttrs) {
		this.tempAttrs = tempAttrs;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
