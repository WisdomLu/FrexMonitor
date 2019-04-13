package com.ocean.persist.app.dis.haiqibing;

public class HaiqibingReportData {
	private String action ;//String; 
	/*是exposure 曝光
	click 点击
	download 下载
	install 安装
	(一个完整的事件链包括以上四个事件)*/
	private String imei;// String 是手机的imei：deviceId
	private String androidId;//  String 是手机的androidId
	private String macAddress;//  String 是手机的mac地址
	private String manufacture;//  String 是手机的厂商
	private String mode;//  String 是手机的机型
	private long appId;//  long 是应用唯一标示id
	private long apkId;//  long 是安装包唯一标示id
	private String packageName ;// String 是应用包名
	private int versionCode ;// int 是应用的版本号
	private String recommendId;//  String 是上报需要的数据，原样返回
	private int source ;// int 是上报需要的数据，原样返回
	private String channelId ;// String 是渠道id
	private String apkUrl ;// String 是最新下载地址
	private String dataAnalysisId;//  String 是上报需要的数据，原样返回
	private int hostVersionCode ;// int 是合作方应用版本号
	private String channel ;//String 是渠道标示
	private String ip;//  String 是手机的IP地址
	private String userAgent ;// String 是设备的user-agent[新增]
	public String getAction() {
		return action;
	}
	public String getImei() {
		return imei;
	}
	public String getAndroidId() {
		return androidId;
	}
	public String getMacAddress() {
		return macAddress;
	}
	public String getManufacture() {
		return manufacture;
	}
	public String getMode() {
		return mode;
	}
	public long getAppId() {
		return appId;
	}
	public long getApkId() {
		return apkId;
	}
	public String getPackageName() {
		return packageName;
	}
	public int getVersionCode() {
		return versionCode;
	}
	public String getRecommendId() {
		return recommendId;
	}
	public int getSource() {
		return source;
	}
	public String getChannelId() {
		return channelId;
	}
	public String getApkUrl() {
		return apkUrl;
	}
	public String getDataAnalysisId() {
		return dataAnalysisId;
	}
	public int getHostVersionCode() {
		return hostVersionCode;
	}
	public String getChannel() {
		return channel;
	}
	public String getIp() {
		return ip;
	}
	public String getUserAgent() {
		return userAgent;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public void setAndroidId(String androidId) {
		this.androidId = androidId;
	}
	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}
	public void setManufacture(String manufacture) {
		this.manufacture = manufacture;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public void setAppId(long appId) {
		this.appId = appId;
	}
	public void setApkId(long apkId) {
		this.apkId = apkId;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public void setVersionCode(int versionCode) {
		this.versionCode = versionCode;
	}
	public void setRecommendId(String recommendId) {
		this.recommendId = recommendId;
	}
	public void setSource(int source) {
		this.source = source;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public void setApkUrl(String apkUrl) {
		this.apkUrl = apkUrl;
	}
	public void setDataAnalysisId(String dataAnalysisId) {
		this.dataAnalysisId = dataAnalysisId;
	}
	public void setHostVersionCode(int hostVersionCode) {
		this.hostVersionCode = hostVersionCode;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
}
