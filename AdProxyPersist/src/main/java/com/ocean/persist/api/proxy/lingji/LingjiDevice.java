package com.ocean.persist.api.proxy.lingji;

import com.ocean.core.common.base.AbstractBaseEntity;

import com.ocean.persist.api.proxy.AdPullParams;


public class LingjiDevice  {

	private static final long serialVersionUID = 1L;
	
	private Integer devicetype;// 设备类型	bt为空默认为2 0-phone 1-pad 2-pc 3-tv 
	private Integer os;// 用户终端的操作系统类型0-Android 1-iOS 2-WP 3-Others
	private String osv;// 4.1	Y	操作系统版本号
	private String androidid;// 原生值,示例:9774D56D682E549C	Y	用户终端的AndroidID
	private String androidid1;// 取md5sum摘要,示例:B8CE8A9A45816ADCD30950B39189B304	Y	用户终端的 AndroidID,md5 加密
	private String imei_md5;// 取md5sum摘要,示例:F1C7976BC455CB548BFC550EB7687F06	Y	用户终端的IMEI,md5 加密
	private String imei;// 原生值,示例:A0000037194570	Y	用户终端的 IMEI
	private String idfa;// 原生值,示例:EC8AF55A-111B-40A3-A461-09FF1E786901	Y	用户终端的IDFA
	private String mac;// 去除分隔符":",（保持大写）取md5sum 摘要,示例:3D8A278F33E4F97181DF1EAEFE500D05	Y	用户终端的 eth0 接口的MAC 地址（大写去除冒号分隔符），md5 加密
	private String mac1;// 保留分隔符":"，（保持大写）取md5sum 摘要	Y	用户终端的 eth0 接口的MAC 地址（大写且保留冒号分隔符），md5 加密
	private String aaid;// 取md5sum摘要	Y	Android Advertising ID
	private String m_opr;// 网络运营商,CMCC
	private Integer m_net;// 联网类型(0—未知，1—Ethernet，2—wifi，3—蜂窝网络，未知代，4—蜂窝网络，2G，5—蜂窝网络，3G，6—蜂窝网络，4G)
	private String m_ip;// 客户端IP地址
	private String m_ua;// User-Agent,示例:Mozilla/5.0(Linux;Android4.0.4;GT-I9220 Build/IMM76D)
	private Integer m_ts;// 发送请求时的本地UNIX时间戳(秒数, 10进制),示例:1374225975
	private Integer m_adw;// 广告位的宽度，以像素为单位。(指密度无关像素，即DIP或CSS pixel)
	private Integer m_adh;// 广告位的高度，以像素为单位。( 指密度无关像素，即DIP或CSS pixel)
	private Integer m_dvw;// 设备屏幕的宽度，以像素为单位。(指密度无关像素，即DIP或CSS pixel)
	private Integer m_dvh;// 设备屏幕的宽度，以像素为单位。(指密度无关像素，即DIP或CSS pixel)
	private String m_mfr;// 设备生产商
	private String m_mdl;// 设备型号
	private String m_sdk;// 投放sdk版本
	private String m_lan;// 目前使用的语言-国家
	private Integer m_int;// 是否全屏/互动展示广告(interstitial ad)。 1--是，0--否(默认)
	private Integer m_brk;// iOS设备是否越狱。1--是, 0--否/未知(默认)
	private Integer m_js;// 广告展示环境是否启用了js。1--已启用(默认), 0--未启用
	private String m_pos;// 地理位置(经度, 维度, 精确度为单位) 23.458335,-50.273971,50
	private String m_ssi;// Wifi SSID
	
	public Integer getDevicetype() {
		return devicetype;
	}

	public void setDevicetype(Integer devicetype) {
		this.devicetype = devicetype;
	}

	public Integer getOs() {
		return os;
	}

	public void setOs(Integer os) {
		this.os = os;
	}

	public String getOsv() {
		return osv;
	}

	public void setOsv(String osv) {
		this.osv = osv;
	}
	
	public String getAndroidid() {
		return androidid;
	}

	public void setAndroidid(String androidid) {
		this.androidid = androidid;
	}

	public String getAndroidid1() {
		return androidid1;
	}

	public void setAndroidid1(String androidid1) {
		this.androidid1 = androidid1;
	}

	public String getImei_md5() {
		return imei_md5;
	}

	public void setImei_md5(String imei_md5) {
		this.imei_md5 = imei_md5;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getIdfa() {
		return idfa;
	}

	public void setIdfa(String idfa) {
		this.idfa = idfa;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getMac1() {
		return mac1;
	}

	public void setMac1(String mac1) {
		this.mac1 = mac1;
	}

	public String getAaid() {
		return aaid;
	}

	public void setAaid(String aaid) {
		this.aaid = aaid;
	}

	public String getM_opr() {
		return m_opr;
	}

	public void setM_opr(String m_opr) {
		this.m_opr = m_opr;
	}

	public Integer getM_net() {
		return m_net;
	}

	public void setM_net(Integer m_net) {
		this.m_net = m_net;
	}

	public String getM_ip() {
		return m_ip;
	}

	public void setM_ip(String m_ip) {
		this.m_ip = m_ip;
	}

	public String getM_ua() {
		return m_ua;
	}

	public void setM_ua(String m_ua) {
		this.m_ua = m_ua;
	}

	public Integer getM_ts() {
		return m_ts;
	}

	public void setM_ts(Integer m_ts) {
		this.m_ts = m_ts;
	}

	public Integer getM_adw() {
		return m_adw;
	}

	public void setM_adw(Integer m_adw) {
		this.m_adw = m_adw;
	}

	public Integer getM_adh() {
		return m_adh;
	}

	public void setM_adh(Integer m_adh) {
		this.m_adh = m_adh;
	}

	public Integer getM_dvw() {
		return m_dvw;
	}

	public void setM_dvw(Integer m_dvw) {
		this.m_dvw = m_dvw;
	}

	public Integer getM_dvh() {
		return m_dvh;
	}

	public void setM_dvh(Integer m_dvh) {
		this.m_dvh = m_dvh;
	}

	public String getM_mfr() {
		return m_mfr;
	}

	public void setM_mfr(String m_mfr) {
		this.m_mfr = m_mfr;
	}

	public String getM_mdl() {
		return m_mdl;
	}

	public void setM_mdl(String m_mdl) {
		this.m_mdl = m_mdl;
	}

	public String getM_sdk() {
		return m_sdk;
	}

	public void setM_sdk(String m_sdk) {
		this.m_sdk = m_sdk;
	}

	public String getM_lan() {
		return m_lan;
	}

	public void setM_lan(String m_lan) {
		this.m_lan = m_lan;
	}

	public Integer getM_int() {
		return m_int;
	}

	public void setM_int(Integer m_int) {
		this.m_int = m_int;
	}

	public Integer getM_brk() {
		return m_brk;
	}

	public void setM_brk(Integer m_brk) {
		this.m_brk = m_brk;
	}

	public Integer getM_js() {
		return m_js;
	}

	public void setM_js(Integer m_js) {
		this.m_js = m_js;
	}

	public String getM_pos() {
		return m_pos;
	}

	public void setM_pos(String m_pos) {
		this.m_pos = m_pos;
	}

	public String getM_ssi() {
		return m_ssi;
	}

	public void setM_ssi(String m_ssi) {
		this.m_ssi = m_ssi;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
