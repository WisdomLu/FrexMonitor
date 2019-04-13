package com.ocean.persist.api.proxy.flying;

import com.ocean.core.common.threadpool.AbstractInvokeParameter;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年1月2日 
      @version 1.0 
 */
public class FlyingAdPullParams    extends AbstractInvokeParameter{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7584015534902716516L;
	private String appid;// string 是飞扬平台分配的应用id 202063620000
	private String posid;//  string 是飞扬平台分配的广告位id 1520206362000059086
	/* string 是通过飞扬平台分配的appid，posid，appkey 计算出的校验字符串，校验字符串需要小写，
	具体计算方法为：
	md5(appid+appkey+posid)
	a909d7392ddc51f
	b15d02b64826ed
	707*/
	private String sign ;//
	private String bid ;//string 是app 包名。对于Android，是应用的packageName；对于iOS,是Bundle identifier。Com.test.abc
	private int adcount;// int 是请求广告个数3
	private String mac;// string 是mac 地址
	private int sw ;//int 是屏幕像素宽度1080
	private int sh ;//int 是屏幕像素高度1920
	private String remoteip;// string 是用户设备的公网Ipv4 地址，服务器发起请求类，请务必确保填写的内容为用户设备的公网出口IP 地址。
	private String brand ;//string 是手机品牌，如不填写会影响流量变现能力Xiaomi
	private String model;// string 是手机型号，如不填写会影响流量变现能力Redmi+Note+2
	private String os ;//string 是操作系统，android 或ios android
	private String asver;// string 否android 版本或ios 版本5.0
	private String aver ;//string 否android api 版本22
	private String imei ;//string 否设备手机串号，当os 是android时，imei 为必填项866148022294847
	private String aid ;//string 否AndroidID ， 当os 是android时，aid 建议填写，否则可能会判作弊并影响收入41da7c64dae6b7a1
	private String idfa;// string 否ios 设备的idfa，取原始字段，36 字节。当os 是ios 时，idfa是必填项E2DFA89-496A-47FD-9941-DF1FC4E6484A
	
	private String imsi ;//string 否手机imsi，如果获取不到请设置为000000000000000460025491607872
	private int dpi;// int 否手机分辨率
	private int net ;//int 否手机联网情况， 0:unknown1:wifi 3:2G 4:3G 5:4G1
	private int 	isp ;//int 否运营商，0:unknown 1:移动2:联通3:电信，如不填写会影响流量变现能力1
	private String co ;//string 否国家iso 编码CN
	private String lang ;//string 否语言zh
	private int root ;//int 否是否root，0：未root 1：root 0
	private int or ;//int 否屏幕方向，0：未知1 竖屏2横屏1响
	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getPosid() {
		return posid;
	}
	public void setPosid(String posid) {
		this.posid = posid;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
	public int getAdcount() {
		return adcount;
	}
	public void setAdcount(int adcount) {
		this.adcount = adcount;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
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
	public String getRemoteip() {
		return remoteip;
	}
	public void setRemoteip(String remoteip) {
		this.remoteip = remoteip;
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
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public String getAsver() {
		return asver;
	}
	public void setAsver(String asver) {
		this.asver = asver;
	}
	public String getAver() {
		return aver;
	}
	public void setAver(String aver) {
		this.aver = aver;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}
	public String getIdfa() {
		return idfa;
	}
	public void setIdfa(String idfa) {
		this.idfa = idfa;
	}
	public String getImsi() {
		return imsi;
	}
	public void setImsi(String imsi) {
		this.imsi = imsi;
	}
	public int getDpi() {
		return dpi;
	}
	public void setDpi(int dpi) {
		this.dpi = dpi;
	}
	public int getNet() {
		return net;
	}
	public void setNet(int net) {
		this.net = net;
	}
	public int getIsp() {
		return isp;
	}
	public void setIsp(int isp) {
		this.isp = isp;
	}
	public String getCo() {
		return co;
	}
	public void setCo(String co) {
		this.co = co;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public int getRoot() {
		return root;
	}
	public void setRoot(int root) {
		this.root = root;
	}
	public int getOr() {
		return or;
	}
	public void setOr(int or) {
		this.or = or;
	}

}
