package com.ocean.forex.entity.analyzedata;

import com.ocean.forex.common.FrexCurrency;

public class AnalyticalData extends AbstractAnalyticalData {
	private FrexCurrency fc;

	public FrexCurrency getFc() {
		return fc;
	}

	public void setFc(FrexCurrency fc) {
		this.fc = fc;
	}
}
