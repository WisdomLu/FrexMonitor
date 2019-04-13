package com.ocean.persist.app.dis.wxcpd;

import com.ocean.persist.app.dis.AdDisParams;

public class WxAppPullParams  implements AdDisParams{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9123228125077460452L;

	private String channel_id ;//string Y 媒体唯一标识
	private String channel_name ;//string Y 媒体名称
	private String token ;//string Y 媒体密钥key 与旺翔sign，拼接的字符串md5 的值
	private String 	request_id ;//string Y 随机码，取值范围[a-zA-Z0-9]{32}，每次请求提交务必保持唯一
	private String 	pkg ;//string Y 包名
	private String md5 ;//string Y apk 文件对应的md5 值，当传递md5 的时候，只匹配md5 一致的apk 广告
	private String imei ;//string Y 手机imei
	private String ip ;//string Y 用户ip 地址
	private String ua ;//string Y 用户请求user-agent
	private String android_id ;//string Y 安卓终端android id
	private String mac ;//string Y mac 地址
	private String model ;//string Y 手机机型
	private String adrv ;//string Y 手机版本
	private String net ;//string Y 网络类型

	//透传
	private WxAppParamData data;
	public String getChannel_id() {
		return channel_id;
	}
	public void setChannel_id(String channel_id) {
		this.channel_id = channel_id;
	}
	public String getChannel_name() {
		return channel_name;
	}
	public void setChannel_name(String channel_name) {
		this.channel_name = channel_name;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getRequest_id() {
		return request_id;
	}
	public void setRequest_id(String request_id) {
		this.request_id = request_id;
	}
	public String getPkg() {
		return pkg;
	}
	public void setPkg(String pkg) {
		this.pkg = pkg;
	}
	public String getMd5() {
		return md5;
	}
	public void setMd5(String md5) {
		this.md5 = md5;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getUa() {
		return ua;
	}
	public void setUa(String ua) {
		this.ua = ua;
	}
	public String getAndroid_id() {
		return android_id;
	}
	public void setAndroid_id(String android_id) {
		this.android_id = android_id;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getAdrv() {
		return adrv;
	}
	public void setAdrv(String adrv) {
		this.adrv = adrv;
	}
	public String getNet() {
		return net;
	}
	public void setNet(String net) {
		this.net = net;
	}
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}
	public WxAppParamData getData() {
		return data;
	}
	public void setData(WxAppParamData data) {
		this.data = data;
	}
}
