package com.ocean.persist.api.proxy.oneway;

import com.ocean.core.common.threadpool.AbstractInvokeParameter;
//文档：http://doc.oneway.mobi/API
public class OnewayAdPullReq       extends AbstractInvokeParameter{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8111506992762599945L;
	private String publishId;//	是	String	应用 publishId
	private String token;//		是	String	应用 token
	private long ts	;//	是	long	请求时间戳
	private String 	apiVersion	;//	是	String	此 API 版本
	private String placementId	;//	是	String	广告位 ID
	private int creativeType	;//	是	int	指定创意类型 1: 视频, 2: 图片 (信息流广告位需传该字段)
	private String bundleId	;//	是	String	应用包名
	private String bundleVersion	;//	是	String	应用版本
	private String deviceId	;//	是	String	设备广告ID (iOS填IDFA，Android填GAID，Android 获取不到可不填 )
	private String imei	;//是	String	Android必填, iOS 获取不到可不填
	private String androidId	;//	是	String	Android必填
	private int apiLevel	;//	是	int	Android API level (iOS不填)
	private String osVersion	;//	是	String	操作系统版本
	private String connectionType	;//	是	String	网络连接类型 取值: wifi，cellular
	private int networkType	;//	是	int	网络类型 取值参考 附录 networkType
	private String networkOperator	;//	是	String	运营商编码 eg: "46001"
	private String simOperator	;//	是	String	SIM卡运营商编码
	private String imsi	;//	是	String	IMSI 国际移动用户识别码
	private String deviceMake	;//	是	String	设备产商
	private String deviceModel	;//	是	String	设备型号
	private String mac	;//	是	String	设备MAC地址
	private String wifiBSSID	;//	是	String	WIFI MAC地址
	private String wifiSSID	;//	是	String	WIFI用户名
	private int screenWidth	;//	是	int	设备屏宽 eg: 1080
	private int screenHeight	;//	是	int	设备屏高 eg: 1920
	private int screenDensity	;//	是	int	屏幕密度 eg: 240
	private String userAgent	;//	是	String	User-Agent
	private String ip	;//	是	String	客户端 ip
	private String language;//		是	String	语言 eg: zh_CN
	private String timeZone	;//	是	String	时区， eg: GMT+08:00
	private String installedApp	;//	否	String	系统已安装包名，多个用逗号隔开 eg: com.abc.app,com.def.app 。 建议结合获取检查APP列表拿到需要检查的包名列表，然后逐个检查安装情况
	public String getPublishId() {
		return publishId;
	}
	public String getToken() {
		return token;
	}
	public long getTs() {
		return ts;
	}
	public String getApiVersion() {
		return apiVersion;
	}
	public String getPlacementId() {
		return placementId;
	}
	public int getCreativeType() {
		return creativeType;
	}
	public String getBundleId() {
		return bundleId;
	}
	public String getBundleVersion() {
		return bundleVersion;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public String getImei() {
		return imei;
	}
	public String getAndroidId() {
		return androidId;
	}
	public int getApiLevel() {
		return apiLevel;
	}
	public String getOsVersion() {
		return osVersion;
	}
	public String getConnectionType() {
		return connectionType;
	}
	public int getNetworkType() {
		return networkType;
	}
	public String getNetworkOperator() {
		return networkOperator;
	}
	public String getSimOperator() {
		return simOperator;
	}
	public String getImsi() {
		return imsi;
	}
	public String getDeviceMake() {
		return deviceMake;
	}
	public String getDeviceModel() {
		return deviceModel;
	}
	public String getMac() {
		return mac;
	}
	public String getWifiBSSID() {
		return wifiBSSID;
	}
	public String getWifiSSID() {
		return wifiSSID;
	}
	public int getScreenWidth() {
		return screenWidth;
	}
	public int getScreenHeight() {
		return screenHeight;
	}
	public int getScreenDensity() {
		return screenDensity;
	}
	public String getUserAgent() {
		return userAgent;
	}
	public String getIp() {
		return ip;
	}
	public String getLanguage() {
		return language;
	}
	public String getTimeZone() {
		return timeZone;
	}
	public String getInstalledApp() {
		return installedApp;
	}
	public void setPublishId(String publishId) {
		this.publishId = publishId;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public void setTs(long ts) {
		this.ts = ts;
	}
	public void setApiVersion(String apiVersion) {
		this.apiVersion = apiVersion;
	}
	public void setPlacementId(String placementId) {
		this.placementId = placementId;
	}
	public void setCreativeType(int creativeType) {
		this.creativeType = creativeType;
	}
	public void setBundleId(String bundleId) {
		this.bundleId = bundleId;
	}
	public void setBundleVersion(String bundleVersion) {
		this.bundleVersion = bundleVersion;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public void setAndroidId(String androidId) {
		this.androidId = androidId;
	}
	public void setApiLevel(int apiLevel) {
		this.apiLevel = apiLevel;
	}
	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}
	public void setConnectionType(String connectionType) {
		this.connectionType = connectionType;
	}
	public void setNetworkType(int networkType) {
		this.networkType = networkType;
	}
	public void setNetworkOperator(String networkOperator) {
		this.networkOperator = networkOperator;
	}
	public void setSimOperator(String simOperator) {
		this.simOperator = simOperator;
	}
	public void setImsi(String imsi) {
		this.imsi = imsi;
	}
	public void setDeviceMake(String deviceMake) {
		this.deviceMake = deviceMake;
	}
	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public void setWifiBSSID(String wifiBSSID) {
		this.wifiBSSID = wifiBSSID;
	}
	public void setWifiSSID(String wifiSSID) {
		this.wifiSSID = wifiSSID;
	}
	public void setScreenWidth(int screenWidth) {
		this.screenWidth = screenWidth;
	}
	public void setScreenHeight(int screenHeight) {
		this.screenHeight = screenHeight;
	}
	public void setScreenDensity(int screenDensity) {
		this.screenDensity = screenDensity;
	}
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}
	public void setInstalledApp(String installedApp) {
		this.installedApp = installedApp;
	}
	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}
}
