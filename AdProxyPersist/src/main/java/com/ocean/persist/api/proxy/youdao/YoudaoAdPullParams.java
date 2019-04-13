package com.ocean.persist.api.proxy.youdao;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.core.common.threadpool.AbstractInvokeParameter;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.persist.api.proxy.AdPullParams;

public class YoudaoAdPullParams    extends AbstractInvokeParameter{

	private static final long serialVersionUID = 1L;

	private String id;// required	String	移动广告位ID
	private String av;// required String app 版本，内容要和广告位的app 版本一致。格式如：v1.2.3或1.2.3v 或1.2.3 等。
	private Integer ct;// required	Integer	网络连接类型，（ UNKNOWN, ETHERNET, WIFI, MOBILE;），值可能为0,1,2,3，分别以 上几种广告连接状态
	private Integer dct;// required	Integer	子网络连接类型。当ct字段为MOBILE时添加，为3G时值为12，4G时为13
	private String udid;// required	String	设备ID,如AndroidID或IDFA
	private String auidmd5;// required	String	MD5 后的 AndroidID, md5 算法可以使用 DigestUtils.md5Hex
	private String imei;// required	String	IMEI（International Mobile Equipment Identity）是移动设备国际身份码的缩写。
	private String imeimd5;// required	String	MD5 后的 IMEI. imei, udid, imeimd5, auidmd5 至少传入一个。
	private String rip;// required String 用户原始IP 地址，当通过服务器请求广告时，必须传入此参数。客户端请求广告，无需传入此参数。
	private String ll;// recommended	String	位置信息，GPS或者网络位置，经纬度逗号分隔（经度在前，纬度在后）
	private String lla ;//required String 经纬度精确度
	private String llt  ;//required String 位置时间跟请求广告时间差，单位为分钟
	private String llp  ;//required String 定位所用的provider，n(network) 为网络定位，g(gps) 为
	/*gps 定位,p(passive) 为其他app 里面的定位信息，f(fused)
	为系统返回的最佳定位*/
	private String wifi ;// required String wifi 信息，用户将当前连接或者附近的wifi 的ssid 和mac
	/*传送过来，非当前连接无法获取mac，格式上，第一个参
	数为当前wifi 的mac，第二个为当前wifi 的ssid，第
	三个以后就是其他网络的mac 和ssid，参数以逗号分隔，
	如：ac:f7:f3:a4:bc:5a，NetEase，ac:f7:f3:a4:bc:5a，liaofan，
	ac:f7:f3:a4:bc:5a*/
	private String s ;//recommended String 加密请求时使用此字段，其值为整个加密后的字段
	private Integer ydet;// recommended	Integer	加密请求时使用此字段 ，表明具体使用的加密方法
	private String dn;// optional	String	设备信息，如samsung,GT-S5830,GT-S5830
	private String z;// optional	String	时区，如：+0800
	private Integer isSecure;// optional Integer 广告物料是否需要满足HTTPS URL, 如果值为1，表示需
	/*要返回物料URL 为HTTPS 协议(如落地页、素材、展示
	点击tracker 等URL); 如果无此参数，表示无HTTPS 要
	求，但广告物料URL 可能为HTTPS。*/
	private String osv;// optional String 操作系统版本信息，如：3.1.2
	private String o;// optional	String	竖屏横屏,可能值分别为:p,l,s,u; u:未知,p:portrait,l:landscape,s:square
	private String sc_a;// optional	String	屏幕分辨率，值如：1.0. Android 平台参考DisplayMetrics.density , iOS 平台参考UIScreen.scale
	private String mcc;// optional	String	国家类型，如中国460
	private String mnc;// optional	String	运营商，如移动00联通01
	private String iso;// optional	String	国家代号，值如cn
	private String cn;// optional	String	运营商名，值可能为‘中国联通’
	private String lac;// optional	String	小区码
	private String cid;// optional	String	基站码，更加准确定位位置
	// optional	String	wifi信息，用户将当前连接或者附近的wifi的 ssid和mac传送过来，非当前连接无法获取 mac，格式上，第一个参数为当前wifi的 mac，
	// 第二个为当前wifi的ssid，第三个以后就是其他网络的mac和ssid，参数以逗号分隔，如：ac:f7:f3:a4:bc:5a，NetEase，
	// ac:f7:f3:a4:bc:5a，liaofan， ac:f7:f3:a4:bc:5a，outfoxer
	private Integer ran ;/*optional Integer 一次请求的广告数量，默认值为1。如果大于1 为批量广告
	请求（请注意广告返回格式），建议一次不要请求太多广告，
	如果有需求可以分批取*/
	
	// optional	List	在同一个信息流广告会话中，会将最新加载的广告推广创意的ID都传送给服务端，以便服务端进行广告去重。
	// 示例：cids=1,2,3。那么本次请求将不会返回变体id为1, 或者 2 或者 3 的广告
	private List<String> cids;
	
	private String isrd;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getCt() {
		return ct;
	}

	public void setCt(Integer ct) {
		this.ct = ct;
	}

	public Integer getDct() {
		return dct;
	}

	public void setDct(Integer dct) {
		this.dct = dct;
	}

	public String getUdid() {
		return udid;
	}

	public void setUdid(String udid) {
		this.udid = udid;
	}

	public String getAuidmd5() {
		return auidmd5;
	}

	public void setAuidmd5(String auidmd5) {
		this.auidmd5 = auidmd5;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getImeimd5() {
		return imeimd5;
	}

	public void setImeimd5(String imeimd5) {
		this.imeimd5 = imeimd5;
	}

	public String getLl() {
		return ll;
	}

	public void setLl(String ll) {
		this.ll = ll;
	}

	public String getS() {
		return s;
	}

	public void setS(String s) {
		this.s = s;
	}

	public Integer getYdet() {
		return ydet;
	}

	public void setYdet(Integer ydet) {
		this.ydet = ydet;
	}

	public String getDn() {
		return dn;
	}

	public void setDn(String dn) {
		this.dn = dn;
	}

	public String getZ() {
		return z;
	}

	public void setZ(String z) {
		this.z = z;
	}

	public String getO() {
		return o;
	}

	public void setO(String o) {
		this.o = o;
	}

	public String getSc_a() {
		return sc_a;
	}

	public void setSc_a(String sc_a) {
		this.sc_a = sc_a;
	}

	public String getMcc() {
		return mcc;
	}

	public void setMcc(String mcc) {
		this.mcc = mcc;
	}

	public String getMnc() {
		return mnc;
	}

	public void setMnc(String mnc) {
		this.mnc = mnc;
	}

	public String getIso() {
		return iso;
	}

	public void setIso(String iso) {
		this.iso = iso;
	}

	public String getCn() {
		return cn;
	}

	public void setCn(String cn) {
		this.cn = cn;
	}

	public String getLac() {
		return lac;
	}

	public void setLac(String lac) {
		this.lac = lac;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getWifi() {
		return wifi;
	}

	public void setWifi(String wifi) {
		this.wifi = wifi;
	}

	public List<String> getCids() {
		return cids;
	}

	public void setCids(List<String> cids) {
		this.cids = cids;
	}

	public String getRip() {
		return rip;
	}

	public void setRip(String rip) {
		this.rip = rip;
	}

	public String getIsrd() {
		return isrd;
	}

	public void setIsrd(String isrd) {
		this.isrd = isrd;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public boolean validate() {
		return false;
		// TODO Auto-generated method stub
		
	}

	public String getAv() {
		return av;
	}

	public void setAv(String av) {
		this.av = av;
	}

	public String getLla() {
		return lla;
	}

	public void setLla(String lla) {
		this.lla = lla;
	}

	public String getLlt() {
		return llt;
	}

	public void setLlt(String llt) {
		this.llt = llt;
	}

	public String getLlp() {
		return llp;
	}

	public void setLlp(String llp) {
		this.llp = llp;
	}

	public Integer getIsSecure() {
		return isSecure;
	}

	public void setIsSecure(Integer isSecure) {
		this.isSecure = isSecure;
	}

	public String getOsv() {
		return osv;
	}

	public void setOsv(String osv) {
		this.osv = osv;
	}

	public Integer getRan() {
		return ran;
	}

	public void setRan(Integer ran) {
		this.ran = ran;
	}
}
