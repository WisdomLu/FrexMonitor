package com.ocean.persist.api.proxy.weiyu;

import com.ocean.core.common.threadpool.AbstractInvokeParameter;

public class WeiyuAdPullParams extends AbstractInvokeParameter{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6922488051909445456L;
	private String tagid;//	string	是	广告位id	由我司提供。请到我司SSP后台获取。后台相关操作流程请参考《操作手册》
	private String appid;//	string	是	appid	由我司分配的唯一id。请到我司SSP后台获取。
	private String appname	;//string	是	APP名称	APP名称(utf-8)，需要UrlEncode
	private String pkgname	;//string	是	APP包名	安卓是应用的PackageName，iOS是Bundle ID
	private String appversion;//	string	是	APP版本	
	private int os	;//int	是	操作系统	0：未知
/*	1：Android
	2：iOS
	3：Windows Phone*/
	private String osv;//	string	是	系统版本	如：9.3.4
	private int carrier	;//int	是	运营商	0：其他
/*	1：移动
	2：联通
	3：电信*/
	private int conn	;//int	是	网络连接类型	0：未知
/*	1：wifi
	2：2G
	3：3G
	4：4G*/
	private String ip;//	string	是	设备真实IP地址	如：180.173.139.168　
	private String make	;//string	是	设备制造商	如： samsang
	private String model;//	string	是	设备型号	　
	private String imei;//	string	是	imei	对于iOS设备，填空；
	//对于Android设备，为imei
	private String idfa;//	string	是	idfa	对于iOS设备，为 idfa；
	//对于Android设备，填空
	private String idfv	;//string	否	idfv	对于iOS设备，为 idfv；
	//对于Android设备，填空
	private String openudid	;//string	否	openudid	对于iOS设备，为 openudid；
	//对于Android设备，填空
	private String anid	;//string	是	安卓id	对于iOS设备，填空；
	//对于Android设备，为Android id
	private String mac	;//string	否	MAC地址	小写，以 ':' 分隔的mac地址
	//对于iOS设备，不必须；
	//对于Android设备，建议填
	private int dpi	;//int	否	每英寸多少个像素点	如 240
	private  int orientation;//	int	否	屏幕方向	0：竖屏
	//1：横屏
	private String lon	;//string	否	设备所在地理位置经度	　
	private String lat	;//string	否	设备所在地理位置维度	　
	private int sw	;//int	是	设备屏幕宽度	　
	private int sh	;//int	是	设备屏幕高度	　
	private int devicetype	;//int	是	设备类型	1：手机
	//2：平板
	private String ua	;//string	是	User-Agent	手机实际 User-Agent 值 ，需要Urlencode
	private int adt	;//int	是	创意类型	0：banner，1：插屏，2：开屏，3：原生
/*	11：前贴片，12：中贴片，13：后贴片，
	30：APP下载列表*/
	private String app_down_nextcur	;//string	否	APP下载列表下一页索引	当广告位类型是“APP下载列表”时，该字段有效
	//第一页填空字符串，第二页索引按照第一页返回的字段 app_down_nextcur 填写，以此类推
	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}
	public String getTagid() {
		return tagid;
	}
	public String getAppid() {
		return appid;
	}
	public String getAppname() {
		return appname;
	}
	public String getPkgname() {
		return pkgname;
	}
	public String getAppversion() {
		return appversion;
	}
	public int getOs() {
		return os;
	}
	public String getOsv() {
		return osv;
	}
	public int getCarrier() {
		return carrier;
	}
	public int getConn() {
		return conn;
	}
	public String getIp() {
		return ip;
	}
	public String getMake() {
		return make;
	}
	public String getModel() {
		return model;
	}
	public String getImei() {
		return imei;
	}
	public String getIdfa() {
		return idfa;
	}
	public String getIdfv() {
		return idfv;
	}
	public String getOpenudid() {
		return openudid;
	}
	public String getAnid() {
		return anid;
	}
	public String getMac() {
		return mac;
	}
	public int getDpi() {
		return dpi;
	}
	public int getOrientation() {
		return orientation;
	}
	public String getLon() {
		return lon;
	}
	public String getLat() {
		return lat;
	}
	public int getSw() {
		return sw;
	}
	public int getSh() {
		return sh;
	}
	public int getDevicetype() {
		return devicetype;
	}
	public String getUa() {
		return ua;
	}
	public int getAdt() {
		return adt;
	}
	public String getApp_down_nextcur() {
		return app_down_nextcur;
	}
	public void setTagid(String tagid) {
		this.tagid = tagid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public void setAppname(String appname) {
		this.appname = appname;
	}
	public void setPkgname(String pkgname) {
		this.pkgname = pkgname;
	}
	public void setAppversion(String appversion) {
		this.appversion = appversion;
	}
	public void setOs(int os) {
		this.os = os;
	}
	public void setOsv(String osv) {
		this.osv = osv;
	}
	public void setCarrier(int carrier) {
		this.carrier = carrier;
	}
	public void setConn(int conn) {
		this.conn = conn;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public void setIdfa(String idfa) {
		this.idfa = idfa;
	}
	public void setIdfv(String idfv) {
		this.idfv = idfv;
	}
	public void setOpenudid(String openudid) {
		this.openudid = openudid;
	}
	public void setAnid(String anid) {
		this.anid = anid;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public void setDpi(int dpi) {
		this.dpi = dpi;
	}
	public void setOrientation(int orientation) {
		this.orientation = orientation;
	}
	public void setLon(String lon) {
		this.lon = lon;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public void setSw(int sw) {
		this.sw = sw;
	}
	public void setSh(int sh) {
		this.sh = sh;
	}
	public void setDevicetype(int devicetype) {
		this.devicetype = devicetype;
	}
	public void setUa(String ua) {
		this.ua = ua;
	}
	public void setAdt(int adt) {
		this.adt = adt;
	}
	public void setApp_down_nextcur(String app_down_nextcur) {
		this.app_down_nextcur = app_down_nextcur;
	}
}
