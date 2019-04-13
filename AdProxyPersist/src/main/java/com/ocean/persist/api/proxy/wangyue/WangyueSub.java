package com.ocean.persist.api.proxy.wangyue;
/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年1月29日 
      @version 1.0 
 */
public class WangyueSub {

	private String pcat	;//string	是	媒体类型ID	见枚举信息8.2
	private String pkgname;//	string	是	对于Android，是应用的PackageName，对于iOS，是Bundle Identifier	com.package.
	private String appName	;//string	是	应用程序名称	
	private String appVersion;//	String	是	应用版本号	
	private int networkType;//	integer	是	连网方式：0-未知，1-Wifi，2-2G，3-3G，4-4G	1
	private int adtype	;//integer	
	/*1-	文字链
	2-	Banner
	3-	图形文字链
	4-	全屏
	5-	插页6-	开屏	6*/
	private int carrier;//	integer	是	1:中国移动 2：中国联通 3：中国电信	1
	private int 	osType	;//integer	是	0：android  1：ios  2：windowsPhone 3：Others	0
	private String osVersion	;//string	是	比如4.3(android)  10.1(ios)操作系统系统版本号	4.3
	private String imei	;//string	是	osType为0时必传；Android操作系统设备号。14-17位纯数字	
	private String andoidId	;//string	是	osType为0时必传，android Id	
	private String idfa;//	string	是	osType为1时必传，仅iOS6.0以上系统的IDFA	4CFD11F0-09D0-4BF3-91CE-D50600BD0E64
	private String idfv	;//string	是	osType为1时必传,iOS设备idfv	
	private String oid	;//string	是	osType为1时必填，为IOS终端设备的OpenUDID	
	private String imsi;
	private String deType	;//string	是	设备类型	设备类型:0.未知 1.手机 2.Pad 3.BOX 4：TV  11：Computer
	private String deMake	;//string	是	设备制造商	HUAWEI
	private String deBrand	;//string	是	设备品牌	HUAWEI
	private String deModel	;//string	是	设备型号	Mate10； iphone6，mi3
	private String deMcc	;//String	否	移动设备国家代码 	如：460   (460代表中国)
	private String deMnc	;//String	否	移动网络号码	移动网络号码, 如：00   (00代表移动)
	private String deIso	;//String	否	国家代号	如果cn
	private String deLongitude	;//string	否	经度	40.7127
	private String deLatitude	;//string	否	纬度	47.0027
	private String ua	;//string	是	客户端游览器标识	
	private String ip	;//string	是	客户端IP	
	private int width	;//string	是	广告位宽度	
	private int height	;//string	是	广告位高度	
	private int deWidth	;//string	是	设备宽度	1080
	private int 	deHeight	;//string	是	设备高度	1920
	private String density	;//string	是	屏幕密度	480
	private String mac	;//String	是	设备的mac地址	设备的mac地址，对于android设备必须，ios7.0以上可以传空值
	public String getPcat() {
		return pcat;
	}
	public void setPcat(String pcat) {
		this.pcat = pcat;
	}
	public String getPkgname() {
		return pkgname;
	}
	public void setPkgname(String pkgname) {
		this.pkgname = pkgname;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getAppVersion() {
		return appVersion;
	}
	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}
	public int getNetworkType() {
		return networkType;
	}
	public void setNetworkType(int networkType) {
		this.networkType = networkType;
	}
	public int getAdtype() {
		return adtype;
	}
	public void setAdtype(int adtype) {
		this.adtype = adtype;
	}
	public int getCarrier() {
		return carrier;
	}
	public void setCarrier(int carrier) {
		this.carrier = carrier;
	}
	public int getOsType() {
		return osType;
	}
	public void setOsType(int osType) {
		this.osType = osType;
	}
	public String getOsVersion() {
		return osVersion;
	}
	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getAndoidId() {
		return andoidId;
	}
	public void setAndoidId(String andoidId) {
		this.andoidId = andoidId;
	}
	public String getIdfa() {
		return idfa;
	}
	public void setIdfa(String idfa) {
		this.idfa = idfa;
	}
	public String getIdfv() {
		return idfv;
	}
	public void setIdfv(String idfv) {
		this.idfv = idfv;
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public String getDeType() {
		return deType;
	}
	public void setDeType(String deType) {
		this.deType = deType;
	}
	public String getDeMake() {
		return deMake;
	}
	public void setDeMake(String deMake) {
		this.deMake = deMake;
	}
	public String getDeBrand() {
		return deBrand;
	}
	public void setDeBrand(String deBrand) {
		this.deBrand = deBrand;
	}
	public String getDeModel() {
		return deModel;
	}
	public void setDeModel(String deModel) {
		this.deModel = deModel;
	}
	public String getDeMcc() {
		return deMcc;
	}
	public void setDeMcc(String deMcc) {
		this.deMcc = deMcc;
	}
	public String getDeMnc() {
		return deMnc;
	}
	public void setDeMnc(String deMnc) {
		this.deMnc = deMnc;
	}
	public String getDeIso() {
		return deIso;
	}
	public void setDeIso(String deIso) {
		this.deIso = deIso;
	}
	public String getDeLongitude() {
		return deLongitude;
	}
	public void setDeLongitude(String deLongitude) {
		this.deLongitude = deLongitude;
	}
	public String getDeLatitude() {
		return deLatitude;
	}
	public void setDeLatitude(String deLatitude) {
		this.deLatitude = deLatitude;
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

	public String getDensity() {
		return density;
	}
	public void setDensity(String density) {
		this.density = density;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getDeWidth() {
		return deWidth;
	}
	public void setDeWidth(int deWidth) {
		this.deWidth = deWidth;
	}
	public int getDeHeight() {
		return deHeight;
	}
	public void setDeHeight(int deHeight) {
		this.deHeight = deHeight;
	}
	public String getImsi() {
		return imsi;
	}
	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

}
