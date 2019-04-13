package com.ocean.persist.app.dis.wxcpd;


import com.ocean.persist.app.dis.AdDisParams;

public class WxApplistParams   implements AdDisParams{

	/**
	 * 
	 */
	private static final long serialVersionUID = 787342113774299516L;


	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}
	private String channel_id;// string 媒体的唯一标识
	private String token ;//string 媒体秘钥key 与旺翔sign，拼接的字符串md5 的值

	public String getChannel_id() {
		return channel_id;
	}
	public void setChannel_id(String channel_id) {
		this.channel_id = channel_id;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
}
