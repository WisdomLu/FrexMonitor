package com.ocean.persist.app.dis.baiduAppSearch.keywordSearch;

import com.ocean.persist.app.dis.AdDisParams;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年10月10日 
      @version 1.0 
 */
public class KeywordSearchBaiduRequest  implements AdDisParams{

	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}
	private String from ;//	来源，合作方标识，由百度BD提供 	必选
	private String token ;//	标记，由百度分配的唯一的token，用于验证请求方的合法性 	必选
	private String type ;//	服务类别，type=app，对于android，type的值为app 	必选
	private String word ;//	检索关键词 	必选
	private String bdi_imei ;//	android手机imei号。参见 附录2》数据加密 	必选
	private String bdi_loc 	;//客户端所在的城市 例：北京市。（base64处理） 	必选
	private String bdi_uip 	;//客户端ip（客户端用户请求ip） 	必选
	private String bdi_bear 	;//网络环境（WF/4G/3G/2G） 	必选
	private String resolution 	;//手机分辨率 例：720_1280 	必选
	private String dpi 	;//屏幕密度 例：320 	必选
	private String apilevel 	;//系统api level 例：18（对应android 4.3） 	必选
	private String os_version 	;//系统版本号 例：4.3 	必选
	private String brand ;//	手机品牌 例：OPSSON 	必选
	private String model ;//	手机型号 例：Q1 	必选
	private String pver 	;//协议版本（此版本固定为3） 	必选
	private String sign ;//	签名，只针对该表的参数签名 参见 附录3》sign生成算法 	必选
	private String uid 	;//用户唯一标识 	可选
	private String bdi_mac ;//	无线网卡mac地址 	可选
	private String bdi_cid 	;//基站id 	可选
	private String bdi_imsi ;//	android手机imsi号。（base64处理） 	可选
	private String _class ;//	检索资源类型，g只检索游戏，s只检索软件，默认为混合检索 	可选
	private int rn 	;//每页显示结果数，默认rn=10，最大值为20 	可选
	private int pn 	;//偏移量，默认pn=0，即第一页，必须为rn的整数倍 	可选
	private String ct 	;//客户端点击时间 	可选
	private String cname 	;//客户端(宿主)名称 	可选
	private String cver 	;//客户端(宿主)版本号 	可选
	private String cpack 	;//客户端(宿主)包名 	可选
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public String getBdi_imei() {
		return bdi_imei;
	}
	public void setBdi_imei(String bdi_imei) {
		this.bdi_imei = bdi_imei;
	}
	public String getBdi_loc() {
		return bdi_loc;
	}
	public void setBdi_loc(String bdi_loc) {
		this.bdi_loc = bdi_loc;
	}
	public String getBdi_uip() {
		return bdi_uip;
	}
	public void setBdi_uip(String bdi_uip) {
		this.bdi_uip = bdi_uip;
	}
	public String getBdi_bear() {
		return bdi_bear;
	}
	public void setBdi_bear(String bdi_bear) {
		this.bdi_bear = bdi_bear;
	}
	public String getResolution() {
		return resolution;
	}
	public void setResolution(String resolution) {
		this.resolution = resolution;
	}
	public String getDpi() {
		return dpi;
	}
	public void setDpi(String dpi) {
		this.dpi = dpi;
	}
	public String getApilevel() {
		return apilevel;
	}
	public void setApilevel(String apilevel) {
		this.apilevel = apilevel;
	}
	public String getOs_version() {
		return os_version;
	}
	public void setOs_version(String os_version) {
		this.os_version = os_version;
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
	public String getPver() {
		return pver;
	}
	public void setPver(String pver) {
		this.pver = pver;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getBdi_mac() {
		return bdi_mac;
	}
	public void setBdi_mac(String bdi_mac) {
		this.bdi_mac = bdi_mac;
	}
	public String getBdi_cid() {
		return bdi_cid;
	}
	public void setBdi_cid(String bdi_cid) {
		this.bdi_cid = bdi_cid;
	}
	public String getBdi_imsi() {
		return bdi_imsi;
	}
	public void setBdi_imsi(String bdi_imsi) {
		this.bdi_imsi = bdi_imsi;
	}
	public String get_class() {
		return _class;
	}
	public void set_class(String _class) {
		this._class = _class;
	}
	public String getCt() {
		return ct;
	}
	public void setCt(String ct) {
		this.ct = ct;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getCver() {
		return cver;
	}
	public void setCver(String cver) {
		this.cver = cver;
	}
	public String getCpack() {
		return cpack;
	}
	public void setCpack(String cpack) {
		this.cpack = cpack;
	}
	public int getRn() {
		return rn;
	}
	public void setRn(int rn) {
		this.rn = rn;
	}
	public int getPn() {
		return pn;
	}
	public void setPn(int pn) {
		this.pn = pn;
	}
}
