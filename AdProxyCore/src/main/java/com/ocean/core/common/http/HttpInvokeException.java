package com.ocean.core.common.http;

public class HttpInvokeException extends Exception {

	private static final long serialVersionUID = -1830917455348L;

	private int code;
	
	public HttpInvokeException(int code){
		
		this(code, "http请求失败");
	}
	
	public HttpInvokeException(int code, String msg){
		
		super(msg);
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}
