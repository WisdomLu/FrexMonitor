package com.ocean.persist.api.proxy.ryan;

import java.util.Map;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullParams;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年1月4日 
      @version 1.0 
 */
public class RyanDevice   {
   /**
	 * 
	 */
   private static final long serialVersionUID = 506694536479683294L;
   private Integer w;//recommend,设备屏幕宽度， 单位： 像素
   private Integer h;//recommend,设备屏幕高度， 单位： 像素
   private String ua;//recommend;浏览器属性 User Agent
   private String ip;//required 设备的公网 ip 地址
   //{key:lat value:<float  recommend  纬度 >,key:lon  value:<float  recommend  经度>,key:ext   value:<object optional  扩展字段>
   private Map<String,Object> geo;//recommend,设备的当前地理位置信息 
   private String did;//recommend,设备 ID（ imei）
   private String didsha1;//recommend,设备 ID（ imei） sha1 值
   private String didmd5;//recommend,设备 ID（ imei） md5 值
   private String dpid;//recommend,平台 ID（ 如 Android ID 或 openUDID）
   private String dpidsha1;//recommend,平台 ID（ 如 Android ID 或 openUDID） sha1 值
   private String dpidmd5;//recommend,平台 ID（ 如 Android ID 或 openUDID） md5 值
   private String mac;//recommend,MAC 地址
   private String macsha1;//recommend,MAC 的 sha1 值
   private String macmd5;//recommend,MAC 的 MD5 值
   private String ifa;//recommend,广告 ID： Android Advertising ID 或 idfa
   private String make;//recommend,设备生产商，如” samsang”
   private String model;//recommend,设备型号,如” gt-9128”
   private String os;//required,操作系统 ： Android iOS Windows Phone other
   private String osv;//recommend,操作系统版本号，如” 4.1”
   private String carrier;//recommend,运营商 ID： 46000 – 中国移动 46001 – 中国联通 46003 – 中国电信 46020 – 中国铁通
   private String language;//optional,如 zh-CN
   private Integer js;//optional,是否启用 Javascript: 0—否 1—是（默认值）
   private Integer connectionty;//recommend,网络连接类型： 0—未知 1—Ethernet 2—wifi 3—2G 4—3G 5—4G
   private Integer devicetype;//recommend,设备类型: 0—手机 1—平板 2—PC 3—互联网电视
   private Object Ext;//optional,扩展信息，参见device，ext
	public Integer getW() {
		return w;
	}
	public void setW(Integer w) {
		this.w = w;
	}
	public Integer getH() {
		return h;
	}
	public void setH(Integer h) {
		this.h = h;
	}
	public String getUa() {
		return ua;
	}
	public void setUa(String ua) {
		this.ua = ua;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Map<String, Object> getGeo() {
		return geo;
	}
	public void setGeo(Map<String, Object> geo) {
		this.geo = geo;
	}
	public String getDid() {
		return did;
	}
	public void setDid(String did) {
		this.did = did;
	}
	public String getDidsha1() {
		return didsha1;
	}
	public void setDidsha1(String didsha1) {
		this.didsha1 = didsha1;
	}
	public String getDidmd5() {
		return didmd5;
	}
	public void setDidmd5(String didmd5) {
		this.didmd5 = didmd5;
	}
	public String getDpid() {
		return dpid;
	}
	public void setDpid(String dpid) {
		this.dpid = dpid;
	}
	public String getDpidsha1() {
		return dpidsha1;
	}
	public void setDpidsha1(String dpidsha1) {
		this.dpidsha1 = dpidsha1;
	}
	public String getDpidmd5() {
		return dpidmd5;
	}
	public void setDpidmd5(String dpidmd5) {
		this.dpidmd5 = dpidmd5;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getMacsha1() {
		return macsha1;
	}
	public void setMacsha1(String macsha1) {
		this.macsha1 = macsha1;
	}
	public String getMacmd5() {
		return macmd5;
	}
	public void setMacmd5(String macmd5) {
		this.macmd5 = macmd5;
	}
	public String getIfa() {
		return ifa;
	}
	public void setIfa(String ifa) {
		this.ifa = ifa;
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
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public String getOsv() {
		return osv;
	}
	public void setOsv(String osv) {
		this.osv = osv;
	}
	public String getCarrier() {
		return carrier;
	}
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public Integer getJs() {
		return js;
	}
	public void setJs(Integer js) {
		this.js = js;
	}
	public Integer getConnectionty() {
		return connectionty;
	}
	public void setConnectionty(Integer connectionty) {
		this.connectionty = connectionty;
	}
	public Integer getDevicetype() {
		return devicetype;
	}
	public void setDevicetype(Integer devicetype) {
		this.devicetype = devicetype;
	}
	public Object getExt() {
		return Ext;
	}
	public void setExt(Object ext) {
		Ext = ext;
	}
}
