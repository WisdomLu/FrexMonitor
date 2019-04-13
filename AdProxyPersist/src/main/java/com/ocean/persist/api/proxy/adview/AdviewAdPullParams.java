package com.ocean.persist.api.proxy.adview;
import com.ocean.persist.api.proxy.AdPullParams;

public class AdviewAdPullParams   extends AdPullParams{

	private static final long serialVersionUID = 1L;
	private String ver;
	private Integer n;// 必需 	请求广告的数量，返回小于等于 n 条广告 	1 
	// 需要在 AdView 官网(http://www.adview.cn/) 注册申请，不同的接入 APP 建议分别注册 appid。
	private String appid; 
	private Integer pt;// 必需 	广告形式 	0=>广告条 1=>插屏 4=>开屏 5=>视频 6=>原生 
	private Integer html5;// 可选  	广告物料类型 	0=>任意(Native或html5) 1=>html5广告(非h5我们会转成h5广告下发) 
	// 可选 	请求的广告素材类型，不保证实际匹配。 	0=>图片横幅广告1=>混合文字链  2=>图形文字链（带行为图标） 
	// 3=>图片插屏广告 4=>html 广告 5=>开屏纯图片 6=>视频广告 7=>贴片广告 8=>原生广告 
	private int secure;
	private Integer at;
	// 可选 	广告点击行为类型，不保证实际匹配。1=>广告页 2	=>下载 4 =>打开地图
	// 8 =>发送短信  16 =>发送邮件 32 =>拨打电话 64 =>播放视频 
	private Integer act;
	private Integer supmacro;// 可选 	是否支持宏替换,默认为0。 	0=>不支持 1=>支持 
	private Integer supGdtUrl;
	private String posId;// 原生广告必需 	原生广告位 ID，在 AdView 平台上申请 	qrak8rs7z7a8 多个广告位 id，可以使用逗号隔开 例：qrak8rs7z7a8,srak8rs7z7a9, 
	private Integer w;// 必需 	广告宽度，单位为像素 	原生广告请求时，该字段传 0。 
	private Integer h;// 必需 	广告高度，单位为像素 	原生广告请求时，该字段传 0。 
	private String ip;// 必需 	终端设备外网的 IP 地址 	服务端发请求，ip 字段必传客户端发请求，获取不到外网 ip 字段可不传 
	private Integer os;// 必需 	终端操作系统名称 	0=>Android 1=>iOS 
	private String bdr;// 必需 	操作系统版本号 	4.2.1 
	private String tp;// 建议 	设备型号 	g7 
	private String brd;// 建议 	设备品牌 	htc 
	private Integer sw;// 可选 	设备屏宽 	480 
	private Integer sh;// 可选 	设备屏高 	854 
	private Double deny;// 可选 	设备屏幕密度 	2.0 
	private Integer andt;// 可选 	安卓设备唯一标识支持类型，默认 0 	0=>支持安卓设备传 IMEI 和 AndroidId 1=>支持安卓设备传 Google Advertising Id 
	private String sn;// Android 设 备且 andt=0，或者	iOS	设 备时，必需 	设备的唯一标识 	Android 设备的 IMEI 或者 iOS 设备的 IDFA 值。 
	private String gd;// Android 设 备且 andt=1 时，必需 	Android, Google Advertising Id andt=1 时，必需 	Android 设备的 Google Advertising Id 
	private String idfa;// 必需 	使用设备真实的 idfa 	iOS 设备的 idfa 值 
	private String idfv;// 必需 	iOS, idv value 	iOS 设备的 idv 值 
	private String openudid;// 建议 	苹果设备唯一识别号 	 
	private String mc;// 建议  MAC 值 	 
	private String andid;// 必需 	ANDROID_ID  	Android 设备的 Android ID 
	private String nt;// 建议 	设备联网类型 	wifi/2g/3g/4g 
	private String nop ;// 建议 	手机运营商代号 	46000、46002、46007=>中国移动 46001、46006=>中国联通 46003、46005=>中国电信没有就填""字符串 
	private Double lat;
	private Double lon;
	private Integer locType ;
	
	private String imsi;// 	建议 	国际移动用户识别码，储存在 SIM 卡中 	 
	private String lac;// 建议 	位置区号(location area code)，用于识别GSM 移动通信网中的一个位置区。 	 
	private String cid;// 建议 	基站小区号（cell identity）。小区识别，为了唯一地表示 GSMPLMN 中的每个小区，网络运营者需分配给网络中所有的小区一个代码 	取值是 0~65535 
	private Integer tab;// 建议 	当前设备类型 	0=>手机 1=>平板 
	private String bssid;// 可选 	WIFI 的 bssid 	网关的 bssid 
	private String ssid;// 可选 	WIFI 的 ssid 	网关的 ssid 
	private String iccid;// 可选 	Android 的 iccid 	Android 设备的 iccid 值 
	private String ua;// 建议 	手机实际 User-Agent 值 	手机实际 User-Agent 值 
	private Integer jb;// 可选 	iOS 设备越狱情况 	0=>没有越狱 1=>已经越狱 
	private Integer tm;// 必需 	是否为测试模式（测试模式下，服务器不统计产生的展示和点击数） 	0=>正式模式 1=>测试模式 
	private String pack;// 必需 	媒体的包名，即本 app 的包名 	com.adview.demo 
	private Long time;// 必需 	请求时间的时间戳 (精确到毫秒) 	1838483829383 
	private String token;// 必需 	授权使用API的授权码参见“3.1.2 token的生成”说明 	 
	// 可选 底价，数值为 CPM 价格*10000，如底价为 CPM 价格 0.6 元，则值 0.6*10000=6000 注：货币单位统一是 RMB。 	6000 
	private Integer baseFloor;
	private Integer yob;// 可选 	用户出生年，4 位数字 	如 1990 
	private Integer maxAge;// 可选 	用户最大年龄 	30 
	private Integer minAge;// 可选 	用户最小年龄 	20 
	private String gender;// 可选 性别：“M”-Male “F”-Female “O”-Other “Null”-Unknown 	M 
	public Integer getN() {
		return n;
	}
	public void setN(Integer n) {
		this.n = n;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public Integer getPt() {
		return pt;
	}
	public void setPt(Integer pt) {
		this.pt = pt;
	}
	public Integer getHtml5() {
		return html5;
	}
	public void setHtml5(Integer html5) {
		this.html5 = html5;
	}
	public Integer getAt() {
		return at;
	}
	public void setAt(Integer at) {
		this.at = at;
	}
	public Integer getAct() {
		return act;
	}
	public void setAct(Integer act) {
		this.act = act;
	}
	public Integer getSupmacro() {
		return supmacro;
	}
	public void setSupmacro(Integer supmacro) {
		this.supmacro = supmacro;
	}
	public String getPosId() {
		return posId;
	}
	public void setPosId(String posId) {
		this.posId = posId;
	}
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
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Integer getOs() {
		return os;
	}
	public void setOs(Integer os) {
		this.os = os;
	}
	public String getBdr() {
		return bdr;
	}
	public void setBdr(String bdr) {
		this.bdr = bdr;
	}
	public String getTp() {
		return tp;
	}
	public void setTp(String tp) {
		this.tp = tp;
	}
	public String getBrd() {
		return brd;
	}
	public void setBrd(String brd) {
		this.brd = brd;
	}
	public Integer getSw() {
		return sw;
	}
	public void setSw(Integer sw) {
		this.sw = sw;
	}
	public Integer getSh() {
		return sh;
	}
	public void setSh(Integer sh) {
		this.sh = sh;
	}
	public Double getDeny() {
		return deny;
	}
	public void setDeny(Double deny) {
		this.deny = deny;
	}
	public Integer getAndt() {
		return andt;
	}
	public void setAndt(Integer andt) {
		this.andt = andt;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public String getGd() {
		return gd;
	}
	public void setGd(String gd) {
		this.gd = gd;
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
	public String getOpenudid() {
		return openudid;
	}
	public void setOpenudid(String openudid) {
		this.openudid = openudid;
	}
	public String getMc() {
		return mc;
	}
	public void setMc(String mc) {
		this.mc = mc;
	}
	public String getAndid() {
		return andid;
	}
	public void setAndid(String andid) {
		this.andid = andid;
	}
	public String getNt() {
		return nt;
	}
	public void setNt(String nt) {
		this.nt = nt;
	}
	public String getNop() {
		return nop;
	}
	public void setNop(String nop) {
		this.nop = nop;
	}
	public String getImsi() {
		return imsi;
	}
	public void setImsi(String imsi) {
		this.imsi = imsi;
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
	public Integer getTab() {
		return tab;
	}
	public void setTab(Integer tab) {
		this.tab = tab;
	}
	public String getBssid() {
		return bssid;
	}
	public void setBssid(String bssid) {
		this.bssid = bssid;
	}
	public String getSsid() {
		return ssid;
	}
	public void setSsid(String ssid) {
		this.ssid = ssid;
	}
	public String getIccid() {
		return iccid;
	}
	public void setIccid(String iccid) {
		this.iccid = iccid;
	}
	public String getUa() {
		return ua;
	}
	public void setUa(String ua) {
		this.ua = ua;
	}
	public Integer getJb() {
		return jb;
	}
	public void setJb(Integer jb) {
		this.jb = jb;
	}
	public Integer getTm() {
		return tm;
	}
	public void setTm(Integer tm) {
		this.tm = tm;
	}
	public String getPack() {
		return pack;
	}
	public void setPack(String pack) {
		this.pack = pack;
	}
	public Long getTime() {
		return time;
	}
	public void setTime(Long time) {
		this.time = time;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Integer getBaseFloor() {
		return baseFloor;
	}
	public void setBaseFloor(Integer baseFloor) {
		this.baseFloor = baseFloor;
	}
	public Integer getYob() {
		return yob;
	}
	public void setYob(Integer yob) {
		this.yob = yob;
	}
	public Integer getMaxAge() {
		return maxAge;
	}
	public void setMaxAge(Integer maxAge) {
		this.maxAge = maxAge;
	}
	public Integer getMinAge() {
		return minAge;
	}
	public void setMinAge(Integer minAge) {
		this.minAge = minAge;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	@Override
	public boolean validate() {
		return false;
		// TODO Auto-generated method stub
		
	}
	public String getVer() {
		return ver;
	}
	public int getSecure() {
		return secure;
	}
	public Integer getSupGdtUrl() {
		return supGdtUrl;
	}
	public void setVer(String ver) {
		this.ver = ver;
	}
	public void setSecure(int secure) {
		this.secure = secure;
	}
	public void setSupGdtUrl(Integer supGdtUrl) {
		this.supGdtUrl = supGdtUrl;
	}
	public Double getLat() {
		return lat;
	}
	public Double getLon() {
		return lon;
	}
	public Integer getLocType() {
		return locType;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	public void setLon(Double lon) {
		this.lon = lon;
	}
	public void setLocType(Integer locType) {
		this.locType = locType;
	}
}
