package com.ocean.forex.service.notify;
public enum AlertType {
	ALERT_ORDER_PRICE(1,"OP","开单价格报警"),		 //开单价格
	ALERT_STOP_PROFIT_POINT(2,"SPP","止盈价格报警"), //止盈点	
	ALERT_STOP_LOSS_POINT(3,"SLP","止损价格报警"),	 //止损点
	ALERT_RAPID_CHANGE(4,"RC","价格跳水报警"),	//价格快速变动
	ALERT_IMPORT_EVENT(5,"IE","重大事件报警");//重大事件
	private final int value;
	private final String name;
	private final String chName;
	private AlertType(int value, String name,String chName) {
		this.value = value;
		this.name = name;
		this.chName=chName;
	}

	public int getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	public String getChName() {
		return chName;
	}
}
