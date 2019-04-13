package com.ocean.persist.app.dis.qqDownloader;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.app.dis.AdDisParams;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年5月13日 
      @version 1.0 
 */
public class ReportBody      implements AdDisParams{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2146614802813007016L;
	private String imei;
	private String imsi;
	private String macAddr;
	private String wifiSsid;
	private String wifiBssid;
	private String routeId; 
	private long appId; 
	private long apkId;
	private String packageName;
	private int versionCode;
	private String interfaceName;
	private int operateTime; 
	private String recommendId;
	private int 	source;
	private String channelId;
	private String dataAnalysisId; 
	private int hostVersionCode;
	private Integer clickType;
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
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
	public String getMacAddr() {
		return macAddr;
	}
	public void setMacAddr(String macAddr) {
		this.macAddr = macAddr;
	}
	public String getWifiSsid() {
		return wifiSsid;
	}
	public void setWifiSsid(String wifiSsid) {
		this.wifiSsid = wifiSsid;
	}
	public String getWifiBssid() {
		return wifiBssid;
	}
	public void setWifiBssid(String wifiBssid) {
		this.wifiBssid = wifiBssid;
	}
	public String getRouteId() {
		return routeId;
	}
	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}
	public long getAppId() {
		return appId;
	}
	public void setAppId(long appId) {
		this.appId = appId;
	}
	public long getApkId() {
		return apkId;
	}
	public void setApkId(long apkId) {
		this.apkId = apkId;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public int getVersionCode() {
		return versionCode;
	}
	public void setVersionCode(int versionCode) {
		this.versionCode = versionCode;
	}
	public String getInterfaceName() {
		return interfaceName;
	}
	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}
	public int getOperateTime() {
		return operateTime;
	}
	public void setOperateTime(int operateTime) {
		this.operateTime = operateTime;
	}
	public String getRecommendId() {
		return recommendId;
	}
	public void setRecommendId(String recommendId) {
		this.recommendId = recommendId;
	}
	public int getSource() {
		return source;
	}
	public void setSource(int source) {
		this.source = source;
	}
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public String getDataAnalysisId() {
		return dataAnalysisId;
	}
	public void setDataAnalysisId(String dataAnalysisId) {
		this.dataAnalysisId = dataAnalysisId;
	}
	public int getHostVersionCode() {
		return hostVersionCode;
	}
	public void setHostVersionCode(int hostVersionCode) {
		this.hostVersionCode = hostVersionCode;
	}
	public Integer getClickType() {
		return clickType;
	}
	public void setClickType(Integer clickType) {
		this.clickType = clickType;
	}

}
