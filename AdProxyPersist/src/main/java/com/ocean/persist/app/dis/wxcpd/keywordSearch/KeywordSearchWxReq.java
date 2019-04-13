package com.ocean.persist.app.dis.wxcpd.keywordSearch;

import com.ocean.persist.app.dis.AdDisParams;

public class KeywordSearchWxReq  implements AdDisParams{

	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}
	private String channel_id;// string Y 媒体唯一标识
	private String channel_name;// string Y 媒体名称
	private String token ;//string Y 媒体密钥key 与旺翔sign，拼接的字符串md5 的值
	private String 	keyword ;//string Y 搜索关键字（任意搜索字符）
	private String cuid ;//string Y 用户标识,生成方式由imei+androidid+uuid 生成md5 值的大写，第一次生成后需保存起来，保证此值的唯一性
	private String ovr;// string Y 系统版本
	private String os_level ;//string Y 系统api 版本
	private String device ;//string Y 手机品牌_手机型号
	private String svr;// string Y App 版本号(version_code)
	private String svr_name ;//string Y App 版本(version_name)
	private String net_type;// string Y 网络环境
	private String resolution;// string Y 分辨率
	private String info_ma;// string Y Mac 地址
	private String info_ms;// string Y Imsi 号
	private String client_id ;//string Y Imei 号
	private String dpi ;//string Y 像素密度
	private String client_ip ;//string Y 客户端外网ip
	private String mcc ;//string Y 国家代码
	private String mno;// string Y 运营商ID
	private String info_la ;//string Y LAC 基站位置区域码
	private String info_ci;// string Y CID,基站编号
	private String os_id ;//string Y 获取设备androidid
	private String bssid ;//string Y WIFI 的ID
	private String nonce;// string Y 请求时间戳（秒）
	private String 	pkg;// string Y 宿主包名
	private String ua ;//string Y 客户端的User-Agent
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
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getCuid() {
		return cuid;
	}
	public void setCuid(String cuid) {
		this.cuid = cuid;
	}
	public String getOvr() {
		return ovr;
	}
	public void setOvr(String ovr) {
		this.ovr = ovr;
	}
	public String getOs_level() {
		return os_level;
	}
	public void setOs_level(String os_level) {
		this.os_level = os_level;
	}
	public String getDevice() {
		return device;
	}
	public void setDevice(String device) {
		this.device = device;
	}
	public String getSvr() {
		return svr;
	}
	public void setSvr(String svr) {
		this.svr = svr;
	}
	public String getSvr_name() {
		return svr_name;
	}
	public void setSvr_name(String svr_name) {
		this.svr_name = svr_name;
	}
	public String getNet_type() {
		return net_type;
	}
	public void setNet_type(String net_type) {
		this.net_type = net_type;
	}
	public String getResolution() {
		return resolution;
	}
	public void setResolution(String resolution) {
		this.resolution = resolution;
	}
	public String getInfo_ma() {
		return info_ma;
	}
	public void setInfo_ma(String info_ma) {
		this.info_ma = info_ma;
	}
	public String getInfo_ms() {
		return info_ms;
	}
	public void setInfo_ms(String info_ms) {
		this.info_ms = info_ms;
	}
	public String getClient_id() {
		return client_id;
	}
	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}
	public String getDpi() {
		return dpi;
	}
	public void setDpi(String dpi) {
		this.dpi = dpi;
	}
	public String getClient_ip() {
		return client_ip;
	}
	public void setClient_ip(String client_ip) {
		this.client_ip = client_ip;
	}
	public String getMcc() {
		return mcc;
	}
	public void setMcc(String mcc) {
		this.mcc = mcc;
	}
	public String getMno() {
		return mno;
	}
	public void setMno(String mno) {
		this.mno = mno;
	}
	public String getInfo_la() {
		return info_la;
	}
	public void setInfo_la(String info_la) {
		this.info_la = info_la;
	}
	public String getInfo_ci() {
		return info_ci;
	}
	public void setInfo_ci(String info_ci) {
		this.info_ci = info_ci;
	}
	public String getOs_id() {
		return os_id;
	}
	public void setOs_id(String os_id) {
		this.os_id = os_id;
	}
	public String getBssid() {
		return bssid;
	}
	public void setBssid(String bssid) {
		this.bssid = bssid;
	}
	public String getNonce() {
		return nonce;
	}
	public void setNonce(String nonce) {
		this.nonce = nonce;
	}
	public String getPkg() {
		return pkg;
	}
	public void setPkg(String pkg) {
		this.pkg = pkg;
	}
	public String getUa() {
		return ua;
	}
	public void setUa(String ua) {
		this.ua = ua;
	}
	public String getChannel_id() {
		return channel_id;
	}
	public void setChannel_id(String channel_id) {
		this.channel_id = channel_id;
	}
}
