package com.ocean.forex.entity.strategy;

public class MonitorStrategy extends AbstractStrategy {
	private double price;
	private int orientation;//-1为小于，1为大于
	private static final String strDateFormat= "yyyy-MM-dd-HH-mm";
	private String eventStart;
	public static final String LESS_THAN="<";
	public static final String GREATER_THAN=">"; 
	public double getPrice() {
		return price;
	}
	public int getOrientation() {
		return orientation;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public void setOrientation(int orientation) {
		this.orientation = orientation;
	}
	public String getEventStart() {
		return eventStart;
	}
	public void setEventStart(String eventStart) {
		this.eventStart = eventStart;
	}
}
