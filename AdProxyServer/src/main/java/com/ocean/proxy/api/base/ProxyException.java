package com.ocean.proxy.api.base;

public class ProxyException extends Exception {

	private static final long serialVersionUID = -1830917455348L;

	private int code;
	
	private String msg;
	
	public ProxyException(int code){
		
		this(code, "业务处理异常");
	}
	public ProxyException(String msg){
		
		this(0, msg);
	}
	public ProxyException(int code, String msg){
		
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
