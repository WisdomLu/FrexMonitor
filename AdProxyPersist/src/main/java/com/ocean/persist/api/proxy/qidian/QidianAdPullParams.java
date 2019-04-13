package com.ocean.persist.api.proxy.qidian;

import com.ocean.core.common.threadpool.AbstractInvokeParameter;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年4月4日 
      @version 1.0 
 */
public class QidianAdPullParams  extends AbstractInvokeParameter{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6296411780921045185L;

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}
	private String requestId;/* St ring Y 见上节
	请求id，由我方提供前缀，流量方提供的id
	格式为前缀+时间戳*/
	private String auth;/* St ring Y 鉴权字符串，见上节说明*/
	private String deviceId ;/*St ring Y 设备标识，能唯一标识用户的使用设备即可*/
	private int network;/* int Y 1
	用户所在的网络环境
	2 2g
	3 3g
	4 4g
	5 5g
	6 wifi
	1 unknown*/
	private int count ;/*int N 1
	请求的广告条数，不传则默认为1。
	单次请求的广告条数上限是50，超过50条
	修正为50，为了避免重复广告原则上不要
	预取，渲染几条就请求几条*/
	private String keyword ;/*St ring N 京东
	请求关键词，如果不为空，系统将根据该关
	键词匹配广告*/
	private String 	jp ;/*St ring N
	是否使用jsonP，如果为空不使用，如果有
	值则作为callback
	奇点系统广告获取接口 - 4/9*/
	private String catalogId;/* St ring N 广告上下文所在的分类*/
	private int terminal ;/*int Y 1
	用户终端类型，目前如下
	1 移动设备
	2 pc
	3 其他*/
	private String imei ;/*St ring Y 移动设备标识，其他设备为空*/
	private String mid ;/*St ring Y
	安卓设备请传android id，ios设备请传
	idfa，其他设备为空*/
	private String model ;/*St ring N
	HUAWEI
	Ascend P7
	设备型号*/
	private String os ;/*St ring N AndroidOS4.4 设备操作系统版本号*/
	private String resolution ;/*St ring N 128*128 设备分辨率*/
	private String browser ;/*String N IE 浏览器型号*/
	private double lng ;/*double N 30.122313
	经度，传递该参数可以基于用户位置更加精
	准的投放广告*/
	private double lat ;/*double N 45.123123
	纬度，传递该参数可以基于用户位置更加精
	准的投放广告*/
	private String mac ;/*St ring N 设备mac标识*/
	private String channel;/* St ring N 渠道标识*/
	private String version ;/*St ring Y
	接口版本号，见文档开头的列表*/
	private String ip ;/*St ring N 请求的ip，详见服务器代理请求须知部分*/

	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public int getNetwork() {
		return network;
	}
	public void setNetwork(int network) {
		this.network = network;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getJp() {
		return jp;
	}
	public void setJp(String jp) {
		this.jp = jp;
	}
	public String getCatalogId() {
		return catalogId;
	}
	public void setCatalogId(String catalogId) {
		this.catalogId = catalogId;
	}
	public int getTerminal() {
		return terminal;
	}
	public void setTerminal(int terminal) {
		this.terminal = terminal;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
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
	public String getResolution() {
		return resolution;
	}
	public void setResolution(String resolution) {
		this.resolution = resolution;
	}
	public String getBrowser() {
		return browser;
	}
	public void setBrowser(String browser) {
		this.browser = browser;
	}
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}

}
