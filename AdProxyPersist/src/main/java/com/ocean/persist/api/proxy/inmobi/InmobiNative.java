package com.ocean.persist.api.proxy.inmobi;

import com.ocean.core.common.base.AbstractBaseEntity;

public class InmobiNative  {

	private static final long serialVersionUID = 1L;

	// required 原生广告布局类型（详见字典 原生广告布局类型）。根据类型的不同，
	// MEX在返回的数据中按照MEX原生广告的标准填充所需数据
	// 1 内容墙
	// 2 应用墙
	// 3 信息流
	// 4 对话列表
	// 5 旋转木马
	// 6 内容流
	// 7 方格连接内容
	// 500 通知栏信息
	// 501+ 暂未使用
	private Integer layout;

	public Integer getLayout() {
		return layout;
	}

	public void setLayout(Integer layout) {
		this.layout = layout;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
