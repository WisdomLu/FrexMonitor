package com.ocean.forex.entity;

public class ExchangeRate {
	private long timestamp;
	private double privce;
	public long getTimestamp() {
		return timestamp;
	}
	public double getPrivce() {
		return privce;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public void setPrivce(double privce) {
		this.privce = privce;
	}
}
