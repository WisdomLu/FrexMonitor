package com.ocean.persist.api.proxy.vamaker;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.core.common.threadpool.AbstractInvokeParameter;
import com.ocean.core.common.threadpool.Parameter;
import com.ocean.persist.api.proxy.AdPullParams;

public class VamakerAdPullParams     extends AbstractInvokeParameter{

	private static final long serialVersionUID = 1L;

	private String _a;// 必填	注册在万流客平台的广告位，唯一标识
	private String _pgn;// 必填	Android 上 传 Package Name ， iOS 上 传 Bundle Identifier，其他平台 App 暂时不接入流量。
	private String _nt;// 非必填，默认 0	网络类型。 0:unknow（默认） 1:WIFI 2:2G 3:3G 4:4G	
	private Integer _o;// 非必填，默认 0	运营商类型。0：unknow/其他运营商（默认）1：移动2：联通3：电信	
	private Integer _os;// 必填	系统类型。 0：其他 1：Android 2：iOS	
	private String _osv;// 非必填	系统版本。 Android4.4，填写为 4.4 iOS8.3，填写为 8.3	
	private String _dev;// 非必填	设备品牌，例如：xiaomi，samsung	
	private String _md;// 非必填	设备型号，例如：n70，galaxy	
	private String _imei;// _os=1 时参数_imei 必填，
	private String _idfa;// _os=2 时参数_idfa 必填，
	private String _mc;// 硬件终端地址。 终端网卡 MAC 地址去除冒号分隔符，比如： C8AA21024952
	private String _aid;// 用户终端的 AndroidID 如
	private String _aaid;// Android Advertiser ID
	private Integer _w;// 非必填	设备分辨率，宽
	private Integer _h;// 非必填	设备分辨率，高
	private String _lon;// 非必填	手机获取的经度
	private String _lat;// 非必填	手机获取的纬度
	private String _mpn;// 非必填	MD5 加密的手机号 出于隐私考虑，不直接接收手机号
	private Integer _gd;// 非必填	性别，如果 App 可以获得用户性别 0：未知 1：男性 2：女性
	private String _bd;// 非必填	生日，如果 App 可以获取到用户生日 YYYY[MM[DD]]
	private String _ip;// 非必填	设备的 ip 地址，当广告请求从 Server 发出，该字段必填。
	private String _z;// 非必填	广告播放器版本，移动视频请求专用
	private String _k;// 非必填	视频内容关键字(UTF-8)，移动视频请求专用
	private Integer _debug;// 非必填	0：非测试模式 1：测试模式默认非测试模式
	private Integer _adw;// 非必填	广告位尺寸，宽
	private Integer _adh;// 非必填	广告位尺寸，高
	private Integer _t;// 。。不加好像不行
	private Boolean isVideo;// 是否是视频广告
	
	public String get_a() {
		return _a;
	}

	public void set_a(String _a) {
		this._a = _a;
	}

	public String get_pgn() {
		return _pgn;
	}

	public void set_pgn(String _pgn) {
		this._pgn = _pgn;
	}

	public String get_nt() {
		return _nt;
	}

	public void set_nt(String _nt) {
		this._nt = _nt;
	}

	public Integer get_o() {
		return _o;
	}

	public void set_o(Integer _o) {
		this._o = _o;
	}

	public Integer get_os() {
		return _os;
	}

	public void set_os(Integer _os) {
		this._os = _os;
	}

	public String get_osv() {
		return _osv;
	}

	public void set_osv(String _osv) {
		this._osv = _osv;
	}

	public String get_dev() {
		return _dev;
	}

	public void set_dev(String _dev) {
		this._dev = _dev;
	}

	public String get_idfa() {
		return _idfa;
	}

	public void set_idfa(String _idfa) {
		this._idfa = _idfa;
	}

	public String get_md() {
		return _md;
	}

	public void set_md(String _md) {
		this._md = _md;
	}

	public String get_imei() {
		return _imei;
	}

	public void set_imei(String _imei) {
		this._imei = _imei;
	}

	public String get_mc() {
		return _mc;
	}

	public void set_mc(String _mc) {
		this._mc = _mc;
	}

	public String get_aid() {
		return _aid;
	}

	public void set_aid(String _aid) {
		this._aid = _aid;
	}

	public String get_aaid() {
		return _aaid;
	}

	public void set_aaid(String _aaid) {
		this._aaid = _aaid;
	}

	public Integer get_w() {
		return _w;
	}

	public void set_w(Integer _w) {
		this._w = _w;
	}

	public Integer get_h() {
		return _h;
	}

	public void set_h(Integer _h) {
		this._h = _h;
	}

	public String get_lon() {
		return _lon;
	}

	public void set_lon(String _lon) {
		this._lon = _lon;
	}

	public String get_lat() {
		return _lat;
	}

	public void set_lat(String _lat) {
		this._lat = _lat;
	}

	public String get_mpn() {
		return _mpn;
	}

	public void set_mpn(String _mpn) {
		this._mpn = _mpn;
	}

	public Integer get_gd() {
		return _gd;
	}

	public void set_gd(Integer _gd) {
		this._gd = _gd;
	}

	public String get_bd() {
		return _bd;
	}

	public void set_bd(String _bd) {
		this._bd = _bd;
	}

	public String get_ip() {
		return _ip;
	}

	public void set_ip(String _ip) {
		this._ip = _ip;
	}

	public String get_z() {
		return _z;
	}

	public void set_z(String _z) {
		this._z = _z;
	}

	public String get_k() {
		return _k;
	}

	public void set_k(String _k) {
		this._k = _k;
	}

	public Integer get_debug() {
		return _debug;
	}

	public void set_debug(Integer _debug) {
		this._debug = _debug;
	}

	public Integer get_adw() {
		return _adw;
	}

	public void set_adw(Integer _adw) {
		this._adw = _adw;
	}

	public Integer get_adh() {
		return _adh;
	}

	public void set_adh(Integer _adh) {
		this._adh = _adh;
	}

	public Integer get_t() {
		return _t;
	}

	public void set_t(Integer _t) {
		this._t = _t;
	}

	public Boolean getIsVideo() {
		return isVideo;
	}

	public void setIsVideo(Boolean isVideo) {
		this.isVideo = isVideo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public boolean validate() {
		return false;
		// TODO Auto-generated method stub
		
	}
}
