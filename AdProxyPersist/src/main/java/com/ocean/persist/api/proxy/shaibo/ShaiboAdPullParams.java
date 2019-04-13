package com.ocean.persist.api.proxy.shaibo;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.core.common.threadpool.AbstractInvokeParameter;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.persist.api.proxy.AdPullParams;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年6月20日 
      @version 1.0 
 */
public class ShaiboAdPullParams    extends AbstractInvokeParameter{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3026957706581507077L;
	private String version;// 	string 	Y 	接口版本号,目前为2.0。
	private String time 	;//string 	Y 	请求的unix时间戳,精确到毫秒(13位),见示例请求。
	private String token 	;//string 	Y 	流量校验码,见2.2。
	private String reqid 	;//string 	Y 	此次请求的id,uuid,见2.2。
	private String appid 	;//string 	Y 	APP的id。
	private String appver 	;//string 	Y 	APP的版本号。
	private String adspotid 	;//string 	Y 	广告位的id。
	private int impsize 	;//int 	Y 	请求曝光的数量。
	private String ip 	;//string 	Y 	设备的ip地址。
	private String ua 	;//string 	Y 	user agent信息。
	private float lat 	;//float 		纬度。
	private float lon 	;//float 		经度。
	private int sw 	;//int 	Y 	设备屏幕宽度,物理像素。
	private int sh 	;//int 	Y 	设备屏幕高度,物理像素。
	private int ppi 	;//int 		设备像素密度,物理像素。
	private String make 	;//string 	Y 	制造商。
	private String model 	;//string 	Y 	机型。
	private int os 	;//int 	Y 	操作系统类型,0:未识别, 1:ios, 2:android。
	private String osv 	;//string 	Y 	操作系统版本号,只取前两位数字表示。如:5.5/10.8。
	private String imei 	;//string 	Y* 	imei(只适用于android)。
	private String mac 	;//string 	Y* 	mac地址(只适用于android)。注:若获取不到请传空字符串。
	private String androidid 	;//string 	Y* 	androidid(只适用于android)。
	private String idfa 	;//string 	Y* 	idfa(只适用于ios)。
	private String carrier 	;//string 	Y 	运营商信息, 使用标准MCC/MNC码。注:若获取不到请传空字符串。示例编码请参考4.5。
	private int network 	;//int 	Y 	网络连接类型, 0:未识别,1:WIFI,2:2G,3:3G,4:4G。
	private int devicetype 	;//int 	Y 	设备类型, 0:未识别,1:手机,2:平板,3:机顶盒。
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getReqid() {
		return reqid;
	}
	public void setReqid(String reqid) {
		this.reqid = reqid;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getAppver() {
		return appver;
	}
	public void setAppver(String appver) {
		this.appver = appver;
	}
	public String getAdspotid() {
		return adspotid;
	}
	public void setAdspotid(String adspotid) {
		this.adspotid = adspotid;
	}
	public int getImpsize() {
		return impsize;
	}
	public void setImpsize(int impsize) {
		this.impsize = impsize;
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
	public float getLat() {
		return lat;
	}
	public void setLat(float lat) {
		this.lat = lat;
	}
	public float getLon() {
		return lon;
	}
	public void setLon(float lon) {
		this.lon = lon;
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
	public int getPpi() {
		return ppi;
	}
	public void setPpi(int ppi) {
		this.ppi = ppi;
	}
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getOs() {
		return os;
	}
	public void setOs(int os) {
		this.os = os;
	}
	public String getOsv() {
		return osv;
	}
	public void setOsv(String osv) {
		this.osv = osv;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getAndroidid() {
		return androidid;
	}
	public void setAndroidid(String androidid) {
		this.androidid = androidid;
	}
	public String getIdfa() {
		return idfa;
	}
	public void setIdfa(String idfa) {
		this.idfa = idfa;
	}
	public String getCarrier() {
		return carrier;
	}
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
	public int getNetwork() {
		return network;
	}
	public void setNetwork(int network) {
		this.network = network;
	}
	public int getDevicetype() {
		return devicetype;
	}
	public void setDevicetype(int devicetype) {
		this.devicetype = devicetype;
	}
	@Override
	public boolean validate() {
		return false;
		// TODO Auto-generated method stub
		
	}
}
