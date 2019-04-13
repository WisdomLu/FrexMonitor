package com.ocean.persist.app.dis.fuxi.appasyn;

import com.ocean.persist.app.dis.AdDisParams;

public class FuxiAppasynParams  implements AdDisParams{

	private String from_client;// 固定值：server
	private String channel_id;//  固定值：00000a
	private String app_id;// 固定值：b00000
	private int pn ;// 当前页码，默认为1
	private int rn ;// 每页请求多少条，如果为0 返回全部列表
	private String timestamp ;// 请求的当前unix 时间戳
	private String sign ;// 生成的签名，具体请查看下文签名生成环节
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}
	public String getFrom_client() {
		return from_client;
	}
	public void setFrom_client(String from_client) {
		this.from_client = from_client;
	}
	public String getChannel_id() {
		return channel_id;
	}
	public void setChannel_id(String channel_id) {
		this.channel_id = channel_id;
	}
	public String getApp_id() {
		return app_id;
	}
	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}
	public int getPn() {
		return pn;
	}
	public void setPn(int pn) {
		this.pn = pn;
	}
	public int getRn() {
		return rn;
	}
	public void setRn(int rn) {
		this.rn = rn;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	} 
}