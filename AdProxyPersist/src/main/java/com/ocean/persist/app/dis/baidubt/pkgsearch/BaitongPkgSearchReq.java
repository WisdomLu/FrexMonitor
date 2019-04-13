package com.ocean.persist.app.dis.baidubt.pkgsearch;

import com.ocean.persist.app.dis.AdDisParams;

public class BaitongPkgSearchReq    implements AdDisParams{

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}
	private String params;
	private String secret;//	密钥加密串	是	　md5（密钥+api_key），与下载加密不同
	private String package_name;//	用户已安装列表	否	多个由逗号分隔(填此值就不会出对应的广告了)
	private String api_key	;//宿主应用ID或渠道号	是	开发者渠道由开发者中心分配
	private String clienttype;//	客户端类型	是	通过api方式提供填api
	private Integer ad_type	;//广告类型	是	广告墙（默认）填1
/*	banner填2
	screen插屏填3
	注意：（拿什么广告物料填对应值，联调环境可能物料都会返回，但线上环境只保证返回对应ad_type的物料）*/
	private String platform	;//广告平台类型	是	安卓手机填android
/*	正版ios填ios
	越狱ios填ios_break
	(全部小写)*/
	private String api_version	;//接口版本号	必需	　填20
	private String baiduid;//	中间页用户唯一标识	百度系必需	　第三方不填
	private String from	;//请求来源	否	不填
	private String cuid	;//百度用户唯一标识，非百度系使用cookie id	否	　填写用户唯一标识
	private String log_id	;//	是	md5(imei+13位时间戳)会话的唯一标示，客户端获取广告、点击广告、下载完成 会话全部使用该logid，作为串联用户行为标示
	private Integer page	;//分页编号	是	默认1
	private Integer page_size;//	每页广告数	是	默认20
	private String user_ip	;//用户ip	异步投放时必需	服务端请求必须透传填写用户ip，用户客户端请求可以不填
	private String sub_channel;//	子渠道号	否	第三方渠道子渠道号

	
	//
	private String app_key;
	private Integer down_complete;
	public String getParams() {
		return params;
	}
	public String getSecret() {
		return secret;
	}
	public String getPackage_name() {
		return package_name;
	}
	public String getApi_key() {
		return api_key;
	}
	public String getClienttype() {
		return clienttype;
	}
	public String getPlatform() {
		return platform;
	}
	public String getApi_version() {
		return api_version;
	}
	public String getBaiduid() {
		return baiduid;
	}
	public String getFrom() {
		return from;
	}
	public String getCuid() {
		return cuid;
	}
	public String getLog_id() {
		return log_id;
	}
	public String getUser_ip() {
		return user_ip;
	}
	public String getSub_channel() {
		return sub_channel;
	}
	public void setParams(String params) {
		this.params = params;
	}
	public void setSecret(String secret) {
		this.secret = secret;
	}
	public void setPackage_name(String package_name) {
		this.package_name = package_name;
	}
	public void setApi_key(String api_key) {
		this.api_key = api_key;
	}
	public void setClienttype(String clienttype) {
		this.clienttype = clienttype;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	public void setApi_version(String api_version) {
		this.api_version = api_version;
	}
	public void setBaiduid(String baiduid) {
		this.baiduid = baiduid;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public void setCuid(String cuid) {
		this.cuid = cuid;
	}
	public void setLog_id(String log_id) {
		this.log_id = log_id;
	}
	public void setUser_ip(String user_ip) {
		this.user_ip = user_ip;
	}
	public void setSub_channel(String sub_channel) {
		this.sub_channel = sub_channel;
	}
	public String getApp_key() {
		return app_key;
	}
	public void setApp_key(String app_key) {
		this.app_key = app_key;
	}
	public Integer getDown_complete() {
		return down_complete;
	}
	public void setDown_complete(Integer down_complete) {
		this.down_complete = down_complete;
	}
	public Integer getAd_type() {
		return ad_type;
	}
	public void setAd_type(Integer ad_type) {
		this.ad_type = ad_type;
	}
	public Integer getPage() {
		return page;
	}
	public Integer getPage_size() {
		return page_size;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public void setPage_size(Integer page_size) {
		this.page_size = page_size;
	}

}
