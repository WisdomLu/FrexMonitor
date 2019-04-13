package com.ocean.forex.entity.strategy;

import com.ocean.forex.common.FrexCurrency;
import com.ocean.forex.service.notify.AlertType;

public abstract class AbstractStrategy {
	private AlertType at;
	private FrexCurrency fc;
	public AlertType getAt() {
		return at;
	}
	public void setAt(AlertType at) {
		this.at = at;
	}
	public FrexCurrency getFc() {
		return fc;
	}
	public void setFc(FrexCurrency fc) {
		this.fc = fc;
	}

}
