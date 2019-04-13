package com.ocean.forex.service.analyse;

import java.util.List;

import com.ocean.forex.common.FrexCurrency;
import com.ocean.forex.entity.strategy.MonitorStrategy;

public class StrategyCvt {
	 private FrexCurrency fc;
	 private List<MonitorStrategy> strategies;
	public FrexCurrency getFc() {
		return fc;
	}
	public List<MonitorStrategy> getStrategies() {
		return strategies;
	}
	public void setFc(FrexCurrency fc) {
		this.fc = fc;
	}
	public void setStrategies(List<MonitorStrategy> strategies) {
		this.strategies = strategies;
	}
	 
}
