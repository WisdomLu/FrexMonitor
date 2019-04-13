package com.ocean.persist.api.proxy.woso;

import com.ocean.core.common.threadpool.AbstractInvokeParameter;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年11月29日 
      @version 1.0 
 */
public class WosoAdPullParams   extends AbstractInvokeParameter{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3776576503469191969L;

	@Override
	public boolean validate() {
		return false;
		// TODO Auto-generated method stub
		
	}
	private String wsid;//我搜移动id(由我搜提供)
	private int os;//操作系统。可能取值1:android 2:ios
	private String mouid;//由os决定android的imei
	private String ifa;//由os决定iOS设备的IDFA值
	/*1) android 设备：可调用系统接口android.os.B uild.MANUFACTURER 直接获得。如果获取不到 ，填写unknown(小写）。(有些设备型号中可能含有空格) 2) ios 设备： 无需填写。
	注：安卓设备不填将不返回广告，填写错误或填写 unknown 会影响流量变现效果。*/
	private String dve;//
	/*	1） android 设备:可调用系统接口android.os.Bu ild.MODEL 直接获得。(有些设备型号中可能含有空格) 2） ios 设备：对系统接口返回原始值做转换后得到，取值例如iPhone 5 、iPhone 6s 、iPhone 6s Plus 等，完整取值列表见附录 ios设备型号取值列表。 3） 如果获取不到，填写unknown(小写）。
	注：不填将不返回广告，填写错误或填写 unknown 会影响流量变现效果。*/
	private String dmo;

	private int dop;//运营商列别: 0:未知的运营商1:中国移动2:中国联通3:中国电信
	private int net_type;//网络连接类型: 0:无法探测当前网络状态1:WIFI 2:2G 3:3G 4:4G
	private int aw;//广告位的宽（具体见下）
	private int ah;//广告位的高（具体见下）
	private String ai;//android 用户终端的 AndroidID
	private int detype;//device_type （0 未知，1 是手机（包括iTouch），2 是平板，3 是电视）
	private String apv;//app_version
	private String dev;//设备的系统版本
	private String pckname;//程序包名
	private String appname;//APP名称
	private int adtype;//广告位类型。可能取值:1:banner 2:插屏 3:应用墙 4:开屏 5:feed 8:原生

	private String ip;//用户设备的公网 IPv4 地址，服务器发起请求类，请务必确保填写的内容为用户设备的公网出口 IP 地址。
	private int secure;//该值传0时返回的链接以http开头，传1时会返回https开头的链接。（如果您的APP全局禁用了ATS属性，则传0，否则传1）(暂时可不填)
	private String mac;//用户设备的 MAC，去除分隔符":"后转为大写,并取 md5sum 摘要，摘要小写。建议填写
	private String aaid;//Android Advertising ID,保留原始值,大陆大部分设备无法获取。建议填写，后续会将其用于定向优化。
	private int lat;//纬度*1,000,000，用于精确识别地域。
	private int lng;//经度*1,000,000，用于精确识别地域。
	private int coordtime;//获取经纬度(lat/lng)的时间。其值为从 GMT 1970-01-01 00:00:00 至今的毫秒值
	private int dw;//手机的屏幕宽
	private int dh;//手机屏幕的高
	private String ua;//移动设备的标准User-agent（用户代理）值
	private String referer;//终端用户 HTTP 请求头中的 referer字段
	private String screen_density;//屏幕密度，一个逻辑像素等于几个实际像素
	private boolean full_screen;//这个字段仅用于请求插屏大规格广告，请求其他类型广告时不填

	public String getAppname() {
		return appname;
	}
	public void setAppname(String appname) {
		this.appname = appname;
	}
	public String getWsid() {
		return wsid;
	}
	public void setWsid(String wsid) {
		this.wsid = wsid;
	}
	public int getOs() {
		return os;
	}
	public void setOs(int os) {
		this.os = os;
	}
	public String getMouid() {
		return mouid;
	}
	public void setMouid(String mouid) {
		this.mouid = mouid;
	}
	public String getIfa() {
		return ifa;
	}
	public void setIfa(String ifa) {
		this.ifa = ifa;
	}
	public String getDve() {
		return dve;
	}
	public void setDve(String dve) {
		this.dve = dve;
	}
	public String getDmo() {
		return dmo;
	}
	public void setDmo(String dmo) {
		this.dmo = dmo;
	}
	public int getDop() {
		return dop;
	}
	public void setDop(int dop) {
		this.dop = dop;
	}
	public int getNet_type() {
		return net_type;
	}
	public void setNet_type(int net_type) {
		this.net_type = net_type;
	}
	public int getAw() {
		return aw;
	}
	public void setAw(int aw) {
		this.aw = aw;
	}
	public int getAh() {
		return ah;
	}
	public void setAh(int ah) {
		this.ah = ah;
	}
	public String getAi() {
		return ai;
	}
	public void setAi(String ai) {
		this.ai = ai;
	}
	public int getDetype() {
		return detype;
	}
	public void setDetype(int detype) {
		this.detype = detype;
	}
	public String getApv() {
		return apv;
	}
	public void setApv(String apv) {
		this.apv = apv;
	}
	public String getDev() {
		return dev;
	}
	public void setDev(String dev) {
		this.dev = dev;
	}
	public String getPckname() {
		return pckname;
	}
	public void setPckname(String pckname) {
		this.pckname = pckname;
	}
	public int getAdtype() {
		return adtype;
	}
	public void setAdtype(int adtype) {
		this.adtype = adtype;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getSecure() {
		return secure;
	}
	public void setSecure(int secure) {
		this.secure = secure;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getAaid() {
		return aaid;
	}
	public void setAaid(String aaid) {
		this.aaid = aaid;
	}
	public int getLat() {
		return lat;
	}
	public void setLat(int lat) {
		this.lat = lat;
	}
	public int getLng() {
		return lng;
	}
	public void setLng(int lng) {
		this.lng = lng;
	}
	public int getCoordtime() {
		return coordtime;
	}
	public void setCoordtime(int coordtime) {
		this.coordtime = coordtime;
	}
	public int getDw() {
		return dw;
	}
	public void setDw(int dw) {
		this.dw = dw;
	}
	public int getDh() {
		return dh;
	}
	public void setDh(int dh) {
		this.dh = dh;
	}
	public String getUa() {
		return ua;
	}
	public void setUa(String ua) {
		this.ua = ua;
	}
	public String getReferer() {
		return referer;
	}
	public void setReferer(String referer) {
		this.referer = referer;
	}
	public String getScreen_density() {
		return screen_density;
	}
	public void setScreen_density(String screen_density) {
		this.screen_density = screen_density;
	}
	public boolean isFull_screen() {
		return full_screen;
	}
	public void setFull_screen(boolean full_screen) {
		this.full_screen = full_screen;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
