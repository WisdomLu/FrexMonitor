package com.ocean.core.common.threadpool.workthread;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Logger;

import com.inveno.util.CollectionUtils;
import com.ocean.core.common.system.MyLogManager;



/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年7月26日 
      @version 1.0 
 */
public abstract class Task{
	private  final Logger LOGGER = MyLogManager.getDisLoggerV2();
	public enum InvokeType{
		INVOKE_TYPE_POST(1,"POST"),
		INVOKE_TYPE_GET(2,"GET");
		private int value;
		private String name;
		private InvokeType(int value,String name){
			this.value=value;
			this.name=name;
		}
		public int getValue() {
			return value;
		}
		public void setValue(int value) {
			this.value = value;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	} 
	public enum DataFormat{
		DATA_FORMAT_JSON(1,"JSON"),
		DATA_FORMAT_STREAM(2,"STREAM"),
		DATA_FORMAT_KVP(3,"KVP"),
		DATA_FORMAT_ZIP(4,"ZIP");
		private int value;
		private String name;
		private DataFormat(int value,String name){
			this.value=value;
			this.name=name;
		}
		public int getValue() {
			return value;
		}
		public void setValue(int value) {
			this.value = value;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	}
	protected String hashCode;
	protected InvokeType invokeType=InvokeType.INVOKE_TYPE_POST;
	protected DataFormat dataFormat=DataFormat.DATA_FORMAT_JSON;
	protected String url;
	protected Map<String, String>  headers;
   
	public InvokeType getInvokeType() {
		return invokeType;
	}
	public void setInvokeType(InvokeType invokeType) {
		this.invokeType = invokeType;
	}
	public DataFormat getDataFormat() {
		return dataFormat;
	}
	public void setDataFormat(DataFormat dataFormat) {
		this.dataFormat = dataFormat;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	public String getHashCode() {
		return hashCode;
	}
	public void setHashCode(String hashCode) {
		this.hashCode = hashCode;
	}

	public boolean validate() {
		// TODO Auto-generated method stub
		if(StringUtils.isEmpty(this.getHashCode())){
			LOGGER.error("task {} parameter error hashcode is empty!",this.getHashCode());
			return false;
		}
		if(StringUtils.isEmpty(this.getUrl())){
			LOGGER.error("task {} parameter error url is empty!",this.getHashCode());
			return false;
		}

		
		return true;
	}
	public Map<String, String> getHeaders() {
		return headers;
	}
	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}
}
