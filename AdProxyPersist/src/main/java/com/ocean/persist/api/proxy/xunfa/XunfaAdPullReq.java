package com.ocean.persist.api.proxy.xunfa;

import com.ocean.core.common.threadpool.AbstractInvokeParameter;

public class XunfaAdPullReq  extends AbstractInvokeParameter{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5609231505576149437L;
	private String appid;//	给媒体分配的 appid	是
	private String adunitid	;//给媒体分配的 adunitid	是
	private String appn	;//appname,合作方的应用名称，值需要做 urlencode	是
	private String appv	;//appversioncode，合作方的应用版本号	是
	private String im	;//手机的 imei 号 API
	//媒体用户唯一标识，Android 取 IMEI，IOS 取 IDFA 媒体性质决定可能为空，则让媒体提供用户唯一 ID 或者手机号可以识别唯一用户的 ID	是
	private String sm;//	手机的 imsi 	是
	private String ovs;//	手机操作系统版本号 	是
	private String brd;//	brand，手机品牌 	是
	private String md;//	model，手机型号 	是
	private String nt;//	network,   网  络 类 型	unknow,ethernet,wf(wifi),
	private String cellular;//(蜂窝网络，未知几 G),2g, 3g,4g 	是
	private String mc	;//手机 mac 地址 	是
	private String ip	;//客户端 ip 地址（如果客户端直接对接，需要告知运
	//营人员配置为客户端请求） 	是
	private int reqt	;//请求类别，默认填 0 全部 1 下载类 2 品牌类 	是
	private int postcnt	;//请求广告数量，目前默认 1。一般情况下填 1。 	是
	private String package_name;//	用户已安装列表,多个由逗号分隔(填此值就不会出对
	//应的广告了)，建议填写准确，可以提高点击曝光率。	否
	private double lat;//	纬度 （无法获取填 0）	是
	private double lon;//	经度（无法获取填 0）	是
	private int carrier;//	运营商（0：移动，1：联通，2：电信）其它填-1	是
	private int post	;//请求广告位置信息：1  应用墙、2  开屏、3  插屏、4Banner	否
	private int ad_pid	;//广告分类父 ID 1  应用 2 单机 3  网游	否
	private int ad_sid	;//广告分类子 ID
/*	【1 应用】 对应子分类（1 系统工具；2 主题壁纸；
	3 社交通讯；4 生活实用；5 资讯阅读；6 影音播放；
	7 办公学习；8 拍摄美化；9 旅游出行；10 理财购物；
	11 其它应用）
	【2 单机】 对应子分类（1 休闲益智 ；2 角色扮演 ；
	3 动作射击；4 模拟辅助；5 体育竞技；6 赛车竞速；
	7 棋牌桌游；8 经营养成；9 其他游戏）
	【3 网游】 对应子分类（1 休闲益智 ；2 角色扮演 ；
	3 动作射击；4 模拟辅助；5 体育竞技；6 赛车竞速；
	7 棋牌桌游；8 经营养成；9 其他游戏）	

	否*/
	private int is_support_deeplink	;//是否支持 deeplink，0：不支持，1：支持。不填默认不支持	否
	private int devicetype;//	-1-unknow,0 - phone, 1 - pad, 2 - pc,	3- tv, 4 - wap	是
	private String os	;//Android iOS WP Others	是
	private String openudid;//	OpenUDID，IOS 必填(获取不到可以用uuid 自动生成)	IOS 必填
	private String deviceid	;//android 的 deviceid	Android 必填
	private String idfv	;//IOS 必填	IOS 必填
	private String density;//	屏幕密度	是
	private int dvw	;//设备屏幕的宽度，以像素为单位，指密度无关的像素，
	//即 dip 或者 css 的 pixel	是
	private int dvh;//	设备屏幕的高度，以像素为单位，指密度无关的像素，
	//即 dip 或者 css 的 pixel	是
	private  int orientation;//	0：竖屏，1：横屏	是
	private int brk;//	android 是否 root，ios 是否越狱。1 表示越狱或者 root，
	//默认 0	否
/*	csinfo	参见下面 csinfo 说明，csinfo 作为一个单独的 json 的
	object，详见 4.2.2 Csinfo 说明	否*/
	private String ua	;//客户端 user-agent	是

	private boolean usegzip	;//返回格式，默认 true，当为 true 时，返回数据经过 gzip
/*	加密，然后 base64 编码后的数据，false 为原始 json
	数据。如无特殊需要加密传输，建议直接设置为 false	
	是*/

	public String getAppid() {
		return appid;
	}

	public String getAdunitid() {
		return adunitid;
	}

	public String getAppn() {
		return appn;
	}

	public String getAppv() {
		return appv;
	}

	public String getIm() {
		return im;
	}

	public String getSm() {
		return sm;
	}

	public String getOvs() {
		return ovs;
	}

	public String getBrd() {
		return brd;
	}

	public String getMd() {
		return md;
	}

	public String getNt() {
		return nt;
	}

	public String getCellular() {
		return cellular;
	}

	public String getMc() {
		return mc;
	}

	public String getIp() {
		return ip;
	}

	public int getReqt() {
		return reqt;
	}

	public int getPostcnt() {
		return postcnt;
	}

	public String getPackage_name() {
		return package_name;
	}

	public double getLat() {
		return lat;
	}

	public double getLon() {
		return lon;
	}

	public int getCarrier() {
		return carrier;
	}

	public int getPost() {
		return post;
	}

	public int getAd_pid() {
		return ad_pid;
	}

	public int getAd_sid() {
		return ad_sid;
	}

	public int getIs_support_deeplink() {
		return is_support_deeplink;
	}

	public int getDevicetype() {
		return devicetype;
	}

	public String getOs() {
		return os;
	}

	public String getOpenudid() {
		return openudid;
	}

	public String getDeviceid() {
		return deviceid;
	}

	public String getIdfv() {
		return idfv;
	}

	public String getDensity() {
		return density;
	}

	public int getDvw() {
		return dvw;
	}

	public int getDvh() {
		return dvh;
	}

	public int getOrientation() {
		return orientation;
	}

	public int getBrk() {
		return brk;
	}

	public String getUa() {
		return ua;
	}

	public boolean isUsegzip() {
		return usegzip;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public void setAdunitid(String adunitid) {
		this.adunitid = adunitid;
	}

	public void setAppn(String appn) {
		this.appn = appn;
	}

	public void setAppv(String appv) {
		this.appv = appv;
	}

	public void setIm(String im) {
		this.im = im;
	}

	public void setSm(String sm) {
		this.sm = sm;
	}

	public void setOvs(String ovs) {
		this.ovs = ovs;
	}

	public void setBrd(String brd) {
		this.brd = brd;
	}

	public void setMd(String md) {
		this.md = md;
	}

	public void setNt(String nt) {
		this.nt = nt;
	}

	public void setCellular(String cellular) {
		this.cellular = cellular;
	}

	public void setMc(String mc) {
		this.mc = mc;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setReqt(int reqt) {
		this.reqt = reqt;
	}

	public void setPostcnt(int postcnt) {
		this.postcnt = postcnt;
	}

	public void setPackage_name(String package_name) {
		this.package_name = package_name;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public void setCarrier(int carrier) {
		this.carrier = carrier;
	}

	public void setPost(int post) {
		this.post = post;
	}

	public void setAd_pid(int ad_pid) {
		this.ad_pid = ad_pid;
	}

	public void setAd_sid(int ad_sid) {
		this.ad_sid = ad_sid;
	}

	public void setIs_support_deeplink(int is_support_deeplink) {
		this.is_support_deeplink = is_support_deeplink;
	}

	public void setDevicetype(int devicetype) {
		this.devicetype = devicetype;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public void setOpenudid(String openudid) {
		this.openudid = openudid;
	}

	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}

	public void setIdfv(String idfv) {
		this.idfv = idfv;
	}

	public void setDensity(String density) {
		this.density = density;
	}

	public void setDvw(int dvw) {
		this.dvw = dvw;
	}

	public void setDvh(int dvh) {
		this.dvh = dvh;
	}

	public void setOrientation(int orientation) {
		this.orientation = orientation;
	}

	public void setBrk(int brk) {
		this.brk = brk;
	}

	public void setUa(String ua) {
		this.ua = ua;
	}

	public void setUsegzip(boolean usegzip) {
		this.usegzip = usegzip;
	}

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}
}
