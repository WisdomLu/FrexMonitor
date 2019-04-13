package com.ocean.core.common.system;

import java.util.HashMap;
import java.util.Map;

/**  
 * ��Ŀ��ƣ�   dawdle  
 *  ����ƣ�    ReturnData  
 *  �����ˣ�    Alex 
 * ����ʱ�䣺  2016��8��6�� 
 * �޸�ʱ�䣺  2016��8��6��  
 */
public class ReturnData {
   private int errorCode;
   private String errorMsg;
   private Object data;
   private Map<String,Object> extraData;
		/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}
	/**
	 * @return the extraData
	 */
	public Map<String, Object> getExtraData() {
		if(this.extraData==null){
			extraData=new HashMap<String,Object>();
		}
		return extraData;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public void setExtraData(Map<String, Object> extraData) {
		this.extraData = extraData;
	}

}
