package com.ocean.persist.api.proxy.yijifen;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

public class YijifenNativeTempAttr  implements AdPullResponse{

	private static final long serialVersionUID = 1L;

	private Long attr_id;// 属性id
	private String name;// 属性名称
	private String value;// 1.图片链接 2.文字内容
	private Integer type;// 属性类型 1.图片 2.文字
	private String requirement;// 1 图片尺寸要求 2 文字最大字数要求	属性要求

	public Long getAttr_id() {
		return attr_id;
	}

	public void setAttr_id(Long attr_id) {
		this.attr_id = attr_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getRequirement() {
		return requirement;
	}

	public void setRequirement(String requirement) {
		this.requirement = requirement;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
