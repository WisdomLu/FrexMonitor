package com.ocean.forex.common;

public class MonitorException   extends Exception {

	private static final long serialVersionUID = -1830917455348L;

	private int code;
	
	private String msg;
	
	public MonitorException(int code){
		
		this(code, "业务处理异常");
	}
	public MonitorException(String msg){
		
		this(0, msg);
	}
	public MonitorException(int code, String msg){
		
		super();
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
}