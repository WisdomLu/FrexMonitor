package com.ocean.core.common.system;

/**  
 * ��Ŀ��ƣ�   dawdle  
 *  ����ƣ�    BusinessException  
 *  �����ˣ�    Alex 
 * ����ʱ�䣺  2016��8��8�� 
 * �޸�ʱ�䣺  2016��8��8��  
 */
public class BusinessException extends RuntimeException{
	private static final long serialVersionUID = 2151796332077394231L;
	private int errorCode;
	private String msg;
	public BusinessException(int errorCode,String msg){
		this.errorCode = errorCode;
		this.msg=msg;
		
	}
	/**
	 * @return the errorCode
	 */
	public int getErrorCode() {
		return errorCode;
	}
	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}
	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}


}
