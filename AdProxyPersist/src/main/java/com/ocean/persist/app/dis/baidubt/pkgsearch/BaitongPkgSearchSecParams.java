package com.ocean.persist.app.dis.baidubt.pkgsearch;

public class BaitongPkgSearchSecParams {
	private long timestamp;//	这次请求时间戳	必需	　1440406574(unix时间戳)
	private Long prev_timestamp	;//上次请求时间戳	否	　1440406574
	private String download_app_name;//	用户下载的app名称	否	　请求分发不填，下载填
	private String host_app_name;//	宿主应用名称	否	　用户在“百度手机助手”下载了 APP“微信”
	// host_app_name;//就是“百度手机助手”
	 //download_app_name ;//就是 “微信”
	private String host_app_version;//	宿主应用版本号	否	　
	private int action_type	;//用户行为	必需	展示填1
	private String action;//	用户产生的行为日志	必需	展现填：show_list
	private String network;//	网络情况	是	wf,3g,2g,4g
	private String product_version	;//　产品版本号	必需	　渠道所属产品版本
	private String imei	;//手机imei号	必需	　Ios无法获取imei时，使用idfa代替
	private String gps	;//用户定位信息	否	经纬度坐标
	private String imsi	;//android手机imsi号	android必需	　
	private String os_version;//	系统版本号	必需	　
	private String brand	;//手机品牌	必需	　
	private String model	;//手机型号	必需	　
	private String resolution;//	手机分辨率	必需	　例子：1280x860 （小写字母x）
	private String mac	;//用户手机mac地址	必需	　
	//running_app_list	用户当前运行的app列表	否	去除系统进程，取前10个app
	public long getTimestamp() {
		return timestamp;
	}
	public String getDownload_app_name() {
		return download_app_name;
	}
	public String getHost_app_name() {
		return host_app_name;
	}
	public String getHost_app_version() {
		return host_app_version;
	}
	public int getAction_type() {
		return action_type;
	}
	public String getAction() {
		return action;
	}
	public String getNetwork() {
		return network;
	}
	public String getProduct_version() {
		return product_version;
	}
	public String getImei() {
		return imei;
	}
	public String getGps() {
		return gps;
	}
	public String getImsi() {
		return imsi;
	}
	public String getOs_version() {
		return os_version;
	}
	public String getBrand() {
		return brand;
	}
	public String getModel() {
		return model;
	}
	public String getResolution() {
		return resolution;
	}
	public String getMac() {
		return mac;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public void setDownload_app_name(String download_app_name) {
		this.download_app_name = download_app_name;
	}
	public void setHost_app_name(String host_app_name) {
		this.host_app_name = host_app_name;
	}
	public void setHost_app_version(String host_app_version) {
		this.host_app_version = host_app_version;
	}
	public void setAction_type(int action_type) {
		this.action_type = action_type;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public void setNetwork(String network) {
		this.network = network;
	}
	public void setProduct_version(String product_version) {
		this.product_version = product_version;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public void setGps(String gps) {
		this.gps = gps;
	}
	public void setImsi(String imsi) {
		this.imsi = imsi;
	}
	public void setOs_version(String os_version) {
		this.os_version = os_version;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public void setResolution(String resolution) {
		this.resolution = resolution;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public Long getPrev_timestamp() {
		return prev_timestamp;
	}
	public void setPrev_timestamp(Long prev_timestamp) {
		this.prev_timestamp = prev_timestamp;
	}
}
