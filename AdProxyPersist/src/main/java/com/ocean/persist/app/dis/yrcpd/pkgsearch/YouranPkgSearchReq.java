package com.ocean.persist.app.dis.yrcpd.pkgsearch;

import com.ocean.persist.app.dis.AdDisParams;

public class YouranPkgSearchReq  implements AdDisParams{
	private String app_id;//	string	由悠燃分配值 	是	310000
	private String hz_id;//	string	由悠燃分配值 ：	是	hz_id_00008
	private String versionName;//	string	合作方客户端版本名称	是	3.0.1
	private int versionCode;//	int	合作方客户端版本号	是	301
	private String ip	;//string	手机设备网络IP	是	192.168.1.10
	private String mac	;//string	手机设备Mac地址	是	40:45:DA:53:48:0E
	private String imei;//	string	手机设备IMEI	是	861065014834397
	private String imsi;//	string	手机设备IMSI	是	460118634817426
	private String model;//	string	手机model，Android 的 Build.MODEL格式为手机品牌_手机型号 这种格式例如OPPO_R7	是	OPPO_R7
	private String manufacture;//	string	手机设备厂商	是	xiaomi
	private String api_level	;//string	手机系统的 APILevel	是	24
	private String osv	;//string;//	Android 系统版本	是	7.0
	private String androidId	;//string	手机设备ID	是	67c7cdc9cefeffed
	private String serialno	;//string	移动设备序列号, 详见附录获取方法	是	CQA6UGZ56DCIU4CI
	private int sw	;//int	屏幕宽	是	1080
	private int sh;//	int	屏幕高	是	1920
	private double dip;//	double	屏幕密度	是	3.0
	private int so	;//int	屏幕方向，1 竖屏，2 横屏	是	1
	private int net;//	int	网络类型，0：未知 1：WIFI，2：2G，3：3G，4：4G，5：未知移动网络	是	1
	private String ua	;//string	http USER_AGENT	是	Dalvik/2.1.0 (Linux; U; Android 7.1.2; MI 5X MIUI/V9.5.3.0.NDBCNFA)
	private String packageNames	;//string	查询包名，多个包用英文逗号隔开，最多不超过30个。	是	com.Qunar,com.ss.android.article.news
	private Integer info_la	;//Int	LAC,基站位置区域码		34860
	private Integer info_ci	;//Int	CID,基站编号		28883
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
	public String getManufacture() {
		return manufacture;
	}
	public void setManufacture(String manufacture) {
		this.manufacture = manufacture;
	}
	public String getApi_level() {
		return api_level;
	}
	public void setApi_level(String api_level) {
		this.api_level = api_level;
	}
	public String getOsv() {
		return osv;
	}
	public void setOsv(String osv) {
		this.osv = osv;
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
	public int getSw() {
		return sw;
	}
	public void setSw(int sw) {
		this.sw = sw;
	}
	public int getSh() {
		return sh;
	}
	public void setSh(int sh) {
		this.sh = sh;
	}
	public double getDip() {
		return dip;
	}
	public void setDip(double dip) {
		this.dip = dip;
	}
	public int getNet() {
		return net;
	}
	public void setNet(int net) {
		this.net = net;
	}
	public String getUa() {
		return ua;
	}
	public void setUa(String ua) {
		this.ua = ua;
	}
	public String getPackageNames() {
		return packageNames;
	}
	public void setPackageNames(String packageNames) {
		this.packageNames = packageNames;
	}
	public int getInfo_la() {
		return info_la;
	}
	public void setInfo_la(int info_la) {
		this.info_la = info_la;
	}
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}
	public Integer getInfo_ci() {
		return info_ci;
	}
	public void setInfo_ci(Integer info_ci) {
		this.info_ci = info_ci;
	}
	public void setInfo_la(Integer info_la) {
		this.info_la = info_la;
	}
	public int getSo() {
		return so;
	}
	public void setSo(int so) {
		this.so = so;
	}
}
