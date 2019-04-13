package com.ocean.persist.api.proxy.zhangyou;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullParams;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年2月3日 
      @version 1.0 
 */
public class ZhangYouDevice  {

	private static final long serialVersionUID = 3348157896443570434L;
    private String model;//设备型号
    private String make;//生产厂商，例如 :"Samsung"
    private String brand;//手机品牌，例如:"MI4"
    private String plmn;//国家运营商编号
    private int adt;//是否允许通过追踪用户行为进定向投放， 0：不允许， 1：允许，默认为 1
    private String connection_type;//链接类型， 空串表示未知wifi, 2g, 3g, 4g, ethernet, cell_unknown
    private int carrier;//运营商，0：移动，1：电信，3：联通，4：unknown
    private int orientation;//设备方向， 1：纵向， 3：横向
    private String mac;//MACMACMAC地址 ；iosiosios7以上 取不到 ，可
    private String imei;//IMEI码。ios没有 (cdma手机请传 meid码)，Android手机 不传 会影响填充
    private String imsi;
    private String android_id;
    private String android_adid;
    private String ios_adid;
    private String idfv;
    private String openudid;
    private String local;//设备上的本地首选项设置
    private String os_type;//操作系统类型 ,  "ios", "android", wp"(windows phone)"
    private String os_version;//操作系统版本
    private ZhangYouScreen screen;
    private ZhangYouGEO geo;
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getPlmn() {
		return plmn;
	}
	public void setPlmn(String plmn) {
		this.plmn = plmn;
	}
	public String getConnection_type() {
		return connection_type;
	}
	public void setConnection_type(String connection_type) {
		this.connection_type = connection_type;
	}
	public int getCarrier() {
		return carrier;
	}
	public void setCarrier(int carrier) {
		this.carrier = carrier;
	}
	public int getOrientation() {
		return orientation;
	}
	public void setOrientation(int orientation) {
		this.orientation = orientation;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getImsi() {
		return imsi;
	}
	public void setImsi(String imsi) {
		this.imsi = imsi;
	}
	public String getAndroid_id() {
		return android_id;
	}
	public void setAndroid_id(String android_id) {
		this.android_id = android_id;
	}
	public String getAndroid_adid() {
		return android_adid;
	}
	public void setAndroid_adid(String android_adid) {
		this.android_adid = android_adid;
	}
	public String getIos_adid() {
		return ios_adid;
	}
	public void setIos_adid(String ios_adid) {
		this.ios_adid = ios_adid;
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
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	public String getOs_type() {
		return os_type;
	}
	public void setOs_type(String os_type) {
		this.os_type = os_type;
	}
	public String getOs_version() {
		return os_version;
	}
	public void setOs_version(String os_version) {
		this.os_version = os_version;
	}
	public ZhangYouScreen getScreen() {
		return screen;
	}
	public void setScreen(ZhangYouScreen screen) {
		this.screen = screen;
	}
	public ZhangYouGEO getGeo() {
		return geo;
	}
	public void setGeo(ZhangYouGEO geo) {
		this.geo = geo;
	}
	public int getAdt() {
		return adt;
	}
	public void setAdt(int adt) {
		this.adt = adt;
	}
    
}
