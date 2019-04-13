package com.ocean.persist.app.dis;

public class AppDisException extends Exception {

	private static final long serialVersionUID = -1830917455348L;

	private int code;
	
	private String msg;
	
	public AppDisException(int code){
		
		this(code, "业务处理异常");
	}
	public AppDisException(String msg){
		
		this(0, msg);
	}
	public AppDisException(int code, String msg){
		
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
