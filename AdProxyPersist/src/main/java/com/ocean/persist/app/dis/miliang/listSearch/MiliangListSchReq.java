package com.ocean.persist.app.dis.miliang.listSearch;

import com.ocean.persist.app.dis.AdDisParams;

public class MiliangListSchReq  implements AdDisParams{
	private int sid;// int 是 广告位 id，需要申请
	private int aid;// int 是 app id，用于唯一标识一个 app，需要申请
	private String imsi ;//string 是 手机 sim 卡标识
	//例如：460023642723856
	private String mac;// string 是 设备 WiFi 网卡 MAC 地址;
	//例如：d0:37:42:84:c2:91
	private String adrid;// string 是 Android ID，Android 手机设备系统 ID
	//例如：64ed496b2d31d900
	private String opid ;//string 是 运营商 ID
/*	46000、46002、46007=>中国移动
	46001、46006=>中国联通
	46003、46005=>中国电信
	未知则为空*/
	private String imei ;//string 是 Android 设备的 IMEI 
	//例如：351708269178793
	private String osv ;//String 是 Android 系统版本
	//例如：6.0
	private String dv ;//string 是 Devide 制造商
	//例如：samsung
	private String dm ;//string 是 Device 型号
	//例如：SM-J7008
	private int nt ;//int 是 网络类型
/*	0:unknow
	1:wlan
	2:wifi
	3:蜂窝数据网络-unknow
	4:蜂窝数据网络-2G
	5:蜂窝数据网络-3G
	6:蜂窝数据网络-4G*/
	private int sw ;//int 是 设备屏幕逻辑分辨率宽度，单位为像素
	private int sh ;//int 是 设备屏幕逻辑分辨率高度，单位为像素
	private int w ;//int 否 广告位的逻辑像素宽度，单位为像素
	private int h ;//int 否 广告位的逻辑像素高度，单位为像素
	private double lat ;//double 否 纬度，例如 21.39980504
	private double lon;// double 否 经度，例如 210.1646447
	private String num ;//string 否 手机号码
	private String useragent ;//string 是 为 Webview 的 UA，注意点击和展示等上报的请
	//求 User–Agent 设置为 Webview 的 UA 
	private String ip ;//string 否 如果是服务器端请求，需要填写客户端 ip
	//如果是客户端直接请求，则不需要填写该字段
	private String density ;//string 是 屏幕密度 例如 2.0
	private String dpi ;//string 是 例如 240
	private long tm ;//long 是 手机 unix 时间戳
	private int osapilevel ;//int 是 osapilevel
	private String sn ;//String 是 手机序列号
	private String lac ;//string 是 基站位置区域码
	private String cellularid;// String 是 基站编码
	public int getSid() {
		return sid;
	}
	public int getAid() {
		return aid;
	}
	public String getImsi() {
		return imsi;
	}
	public String getMac() {
		return mac;
	}
	public String getAdrid() {
		return adrid;
	}
	public String getOpid() {
		return opid;
	}
	public String getImei() {
		return imei;
	}
	public String getOsv() {
		return osv;
	}
	public String getDv() {
		return dv;
	}
	public String getDm() {
		return dm;
	}
	public int getNt() {
		return nt;
	}
	public int getSw() {
		return sw;
	}
	public int getSh() {
		return sh;
	}
	public int getW() {
		return w;
	}
	public int getH() {
		return h;
	}
	public double getLat() {
		return lat;
	}
	public double getLon() {
		return lon;
	}
	public String getNum() {
		return num;
	}
	public String getUseragent() {
		return useragent;
	}
	public String getIp() {
		return ip;
	}
	public String getDensity() {
		return density;
	}
	public String getDpi() {
		return dpi;
	}
	public int getOsapilevel() {
		return osapilevel;
	}
	public String getSn() {
		return sn;
	}
	public String getLac() {
		return lac;
	}
	public String getCellularid() {
		return cellularid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public void setImsi(String imsi) {
		this.imsi = imsi;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public void setAdrid(String adrid) {
		this.adrid = adrid;
	}
	public void setOpid(String opid) {
		this.opid = opid;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public void setOsv(String osv) {
		this.osv = osv;
	}
	public void setDv(String dv) {
		this.dv = dv;
	}
	public void setDm(String dm) {
		this.dm = dm;
	}
	public void setNt(int nt) {
		this.nt = nt;
	}
	public void setSw(int sw) {
		this.sw = sw;
	}
	public void setSh(int sh) {
		this.sh = sh;
	}
	public void setW(int w) {
		this.w = w;
	}
	public void setH(int h) {
		this.h = h;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public void setUseragent(String useragent) {
		this.useragent = useragent;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public void setDensity(String density) {
		this.density = density;
	}
	public void setDpi(String dpi) {
		this.dpi = dpi;
	}
	public void setOsapilevel(int osapilevel) {
		this.osapilevel = osapilevel;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public void setLac(String lac) {
		this.lac = lac;
	}
	public void setCellularid(String cellularid) {
		this.cellularid = cellularid;
	}
	public long getTm() {
		return tm;
	}
	public void setTm(long tm) {
		this.tm = tm;
	}
	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}
}
