package com.ocean.persist.app.dis.yitong.applist;

public class ListSearchYitongSecParams {
	//params
	private long timestamp;
	private Long prev_timestamp;
	private String download_app_name;
	private String host_app_name;
	private String host_app_version;
	private int action_type;
	private String  action;
	private String network;
	private String product_version;
	private String imei;
	private String gps;
	private String imsi;
	private String os_version;
	private String brand;
	private String model;
	private String resolution;
	private String mac;
	private String running_app_list;
	
	//v2上报参数
	private Long show_timestamp;
	private String user_agent;
	
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public String getDownload_app_name() {
		return download_app_name;
	}
	public void setDownload_app_name(String download_app_name) {
		this.download_app_name = download_app_name;
	}
	public String getHost_app_name() {
		return host_app_name;
	}
	public void setHost_app_name(String host_app_name) {
		this.host_app_name = host_app_name;
	}
	public String getHost_app_version() {
		return host_app_version;
	}
	public void setHost_app_version(String host_app_version) {
		this.host_app_version = host_app_version;
	}
	public int getAction_type() {
		return action_type;
	}
	public void setAction_type(int action_type) {
		this.action_type = action_type;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getNetwork() {
		return network;
	}
	public void setNetwork(String network) {
		this.network = network;
	}
	public String getProduct_version() {
		return product_version;
	}
	public void setProduct_version(String product_version) {
		this.product_version = product_version;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getGps() {
		return gps;
	}
	public void setGps(String gps) {
		this.gps = gps;
	}
	public String getImsi() {
		return imsi;
	}
	public void setImsi(String imsi) {
		this.imsi = imsi;
	}
	public String getOs_version() {
		return os_version;
	}
	public void setOs_version(String os_version) {
		this.os_version = os_version;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getResolution() {
		return resolution;
	}
	public void setResolution(String resolution) {
		this.resolution = resolution;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getRunning_app_list() {
		return running_app_list;
	}
	public void setRunning_app_list(String running_app_list) {
		this.running_app_list = running_app_list;
	}
	public Long getPrev_timestamp() {
		return prev_timestamp;
	}
	public void setPrev_timestamp(Long prev_timestamp) {
		this.prev_timestamp = prev_timestamp;
	}
	public Long getShow_timestamp() {
		return show_timestamp;
	}
	public void setShow_timestamp(Long show_timestamp) {
		this.show_timestamp = show_timestamp;
	}
	public String getUser_agent() {
		return user_agent;
	}
	public void setUser_agent(String user_agent) {
		this.user_agent = user_agent;
	}
}
