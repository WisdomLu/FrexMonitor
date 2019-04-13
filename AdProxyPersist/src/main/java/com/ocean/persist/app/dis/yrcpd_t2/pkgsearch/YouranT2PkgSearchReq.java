package com.ocean.persist.app.dis.yrcpd_t2.pkgsearch;

import com.ocean.persist.app.dis.AdDisParams;

public class YouranT2PkgSearchReq  implements AdDisParams{
	private String app_id;//	string	由悠燃分配值 	是	310000
	private String hz_id;//	string	由悠燃分配值 ：	是	hz_id_00008
	private String appName	;//string	应用名称	是	
	private String appVersion	;//string	应用版本号是	
	private String versionName;//	string	合作方客户端版本名称	是	3.0.1
	private int versionCode;//	int	合作方客户端版本号	是	301
	private String ip	;//string	手机设备网络IP	是	192.168.1.10
	private String mac	;//string	手机设备Mac地址	是	40:45:DA:53:48:0E
	private String imei;//	string	手机设备IMEI	是	861065014834397
	private String imsi;//	string	手机设备IMSI	是	460118634817426
	private String model;//	string	手机model，Android 的 Build.MODEL格式为手机品牌_手机型号 这种格式例如OPPO_R7	是	OPPO_R7
	private String manufacturer;//	string	手机设备厂商	是	xiaomi
	private String apiLevel	;//string	手机系统的 APILevel	是	24
	private String androidId	;//string	手机设备ID	是	67c7cdc9cefeffed
	private String serialno	;//string	移动设备序列号, 详见附录获取方法	是	CQA6UGZ56DCIU4CI
	private double dip;//	double	屏幕密度	是	3.0
	private int networkType;//	int	网络类型，0：未知 1：WIFI，2：2G，3：3G，4：4G，5：未知移动网络	是	1
	private String ua	;//string	http USER_AGENT	是	Dalvik/2.1.0 (Linux; U; Android 7.1.2; MI 5X MIUI/V9.5.3.0.NDBCNFA)
	private String packageName	;//string	查询包名，多个包用英文逗号隔开，最多不超过30个。	是	com.Qunar,com.ss.android.article.news
	private Integer lac	;//Int	LAC,基站位置区域码		34860
	private Integer cellularID	;//Int	CID,基站编号		28883
	
	private int deviceType;
	private int osType;
	private String osVersion	;//string	设备版本	是	5.1.1
	private String osVersionCode	;//string	设备版本号	是	24
	
	private String 	screenSize	;//string	屏幕尺寸	是	1080x1920
	private String screenOrientation;//	string	屏幕方向1 竖屏，2 横屏	是	
	private String screenHeight	;//string	屏幕高	是	
	private String screenWidth	;//string	屏幕宽	是	
	private int operatorType;
	private String applicationType	;//string	列表类型应用=1 	游戏=2 推荐=3是	
	private String pageSize	;//string	;//翻页参数，一次请求返回数量，上限50	是	
	private String pageContext	;//string	翻页参数，第一次请求可以不传，后续请求翻页需要原样返回pageContext字段		是	
	private String keyword	;//string	搜索关键字	是	
	public String getApp_id() {
		return app_id;
	}
	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}
	public String getHz_id() {
		return hz_id;
	}
	public void setHz_id(String hz_id) {
		this.hz_id = hz_id;
	}
	public String getVersionName() {
		return versionName;
	}
	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}
	public int getVersionCode() {
		return versionCode;
	}
	public void setVersionCode(int versionCode) {
		this.versionCode = versionCode;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getImsi() {
		return imsi;
	}
	public void setImsi(String imsi) {
		this.imsi = imsi;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}

	public String getAndroidId() {
		return androidId;
	}
	public void setAndroidId(String androidId) {
		this.androidId = androidId;
	}
	public String getSerialno() {
		return serialno;
	}
	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}
	
	public double getDip() {
		return dip;
	}
	public void setDip(double dip) {
		this.dip = dip;
	}
	
	public String getUa() {
		return ua;
	}
	public void setUa(String ua) {
		this.ua = ua;
	}
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}
	public String getAppName() {
		return appName;
	}
	public String getAppVersion() {
		return appVersion;
	}
	public String getApiLevel() {
		return apiLevel;
	}
	public int getNetworkType() {
		return networkType;
	}
	public Integer getLac() {
		return lac;
	}
	public int getDeviceType() {
		return deviceType;
	}
	public int getOsType() {
		return osType;
	}
	public String getOsVersion() {
		return osVersion;
	}
	public String getOsVersionCode() {
		return osVersionCode;
	}
	public String getScreenSize() {
		return screenSize;
	}
	public String getScreenOrientation() {
		return screenOrientation;
	}
	public int getOperatorType() {
		return operatorType;
	}
	public String getApplicationType() {
		return applicationType;
	}
	public String getPageSize() {
		return pageSize;
	}
	public String getPageContext() {
		return pageContext;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}
	public void setApiLevel(String apiLevel) {
		this.apiLevel = apiLevel;
	}
	public void setNetworkType(int networkType) {
		this.networkType = networkType;
	}
	public void setLac(Integer lac) {
		this.lac = lac;
	}

	public void setDeviceType(int deviceType) {
		this.deviceType = deviceType;
	}
	public void setOsType(int osType) {
		this.osType = osType;
	}
	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}
	public void setOsVersionCode(String osVersionCode) {
		this.osVersionCode = osVersionCode;
	}
	public void setScreenSize(String screenSize) {
		this.screenSize = screenSize;
	}
	public void setScreenOrientation(String screenOrientation) {
		this.screenOrientation = screenOrientation;
	}
	public void setOperatorType(int operatorType) {
		this.operatorType = operatorType;
	}
	public void setApplicationType(String applicationType) {
		this.applicationType = applicationType;
	}
	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
	public void setPageContext(String pageContext) {
		this.pageContext = pageContext;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public Integer getCellularID() {
		return cellularID;
	}
	public String getScreenHeight() {
		return screenHeight;
	}
	public String getScreenWidth() {
		return screenWidth;
	}
	public void setCellularID(Integer cellularID) {
		this.cellularID = cellularID;
	}
	public void setScreenHeight(String screenHeight) {
		this.screenHeight = screenHeight;
	}
	public void setScreenWidth(String screenWidth) {
		this.screenWidth = screenWidth;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	
}
