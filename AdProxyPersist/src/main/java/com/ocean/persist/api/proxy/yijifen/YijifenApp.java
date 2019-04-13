package com.ocean.persist.api.proxy.yijifen;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullParams;

public class YijifenApp  {

	private static final long serialVersionUID = 1L;
	private String keywords;// 选填	卡牌策略	应用关键词
	
	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
