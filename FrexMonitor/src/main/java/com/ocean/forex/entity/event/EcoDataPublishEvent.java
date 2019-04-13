package com.ocean.forex.entity.event;

import com.ocean.forex.entity.event.base.DefaultEvent;

public class EcoDataPublishEvent extends DefaultEvent {
	private String country;//国家
	private String impact;//影响
	private String previousValue;//前值
    private String predictedValue;//预测值
    private String actualValue;//实际值 
	public String getCountry() {
		return country;
	}
	public String getImpact() {
		return impact;
	}
	public String getPreviousValue() {
		return previousValue;
	}
	public String getPredictedValue() {
		return predictedValue;
	}
	public String getActualValue() {
		return actualValue;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public void setImpact(String impact) {
		this.impact = impact;
	}
	public void setPreviousValue(String previousValue) {
		this.previousValue = previousValue;
	}
	public void setPredictedValue(String predictedValue) {
		this.predictedValue = predictedValue;
	}
	public void setActualValue(String actualValue) {
		this.actualValue = actualValue;
	}
}
