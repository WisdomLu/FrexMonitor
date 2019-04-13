package com.ocean.forex.entity.analyzedata;

import java.util.List;

import com.ocean.forex.entity.ExchangeRate;

public abstract class AbstractAnalyticalData {
	private List<ExchangeRate> er;

	public List<ExchangeRate> getEr() {
		return er;
	}

	public void setEr(List<ExchangeRate> er) {
		this.er = er;
	}
}
