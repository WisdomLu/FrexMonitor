package com.ocean.persist.api.proxy.boyuV2;

public class BoyuV2Device {
		private String deviceId;//	string	Y	1EA55320-60C6-4B87-8D61-	设备唯一ID，详见2.1.1.4							
		private int network;//	int	Y	6	用户所在的网络环境 2 2g,3 3g,4 4g, 5 5g, 6 wifi, 1	unknown							
		private int deviceType;//	int	Y	1	设备类型 1 手机 2 平板 3 OTT终端 4 PC 9 其他。注：	
			/*	OTT终端包括互联网电视和电视机顶盒					
				安卓设备的imei号明文，长度<=16位，数字或字符,优先	*/
		private String imei;//	string	R	861916030230770或A01234567890FF	取imei；如果没有，则填写meid；如果meid没有则留	
				//空。当os为android时， imei、imeiMd5作为必选字段，可以二选⼀。其中imei字段传输明⽂imei使用md5加密，全大写的字符串。当os为android	
		private String imeiMd5;//	string	R	ED5B86E6771370C5C172829975E684DC	时， imei、imeiMd5作为必选字段，可以二选⼀。其中	
				//imei字段传输明⽂值								
		private String idfa;//	string	R	9C287922-EE26-4501-94B5-	当os为ios设备时， idfa字段为必填。 idfa明⽂为16进制	
			//DDE6F83E1475	32位⻓度，按8-4-4-4-12的⻓度分布						
		private String androidId;//	string	R	8F029BD7BCF2942	当os为android时，可通过此字段传输安卓id。值为15位	
				//16进制全⼤写字符串						
		private String brand;//	string	R	Huawei	设备品牌。deviceType为1或2时，该字段必填，iOS系统	
				//设备请统一填写为Apple							
		private String model;//	string	R	P7	设备型号。deviceType为1或2时，该字段必填						
				//设备操作系统版本号。deviceType为1或2时，该字段必	
		private String os;//	string	R	Android	填。android系统请填android，ios系统请填ios，其它系	
			//统请按实际情况填写						
		private String osVersion;//	string	R	5.0	设备操作系统版本。deviceType为1或2时，该字段必填								
		//设备运营商信息，1 中国移动 2 中国联通 3 中国电信 9	
		private int carrier;//	int	R	2	其他，如果有多个运营商，请传第一个即可。	
		//deviceType为1时，该字段必填	
							
		private String mac;//	string	R		设备mac标识，大写明文(保留冒号)。deviceType为1或2	
		//时，该字段必填					
		private String ip;//	string	N		请求的设备ip。设备的公⽹IP。校验规则：必须是公⽹	
		//IP，不允许传输类似10打头的内⽹ip或服务器ip						
		private String userAgent;//	String	Y		设备的客户端UA,传输设备的webview标准ua，优先使用	
		//userAgent，若传空，则使用header中的userAgent								
		private BoyuV2Geo geo;//	object	N		地理位置信息	
		public String getDeviceId() {
			return deviceId;
		}
		public int getNetwork() {
			return network;
		}
		public int getDeviceType() {
			return deviceType;
		}
		public String getImei() {
			return imei;
		}
		public String getImeiMd5() {
			return imeiMd5;
		}
		public String getIdfa() {
			return idfa;
		}
		public String getAndroidId() {
			return androidId;
		}
		public String getBrand() {
			return brand;
		}
		public String getModel() {
			return model;
		}
		public String getOs() {
			return os;
		}
		public String getOsVersion() {
			return osVersion;
		}
		public int getCarrier() {
			return carrier;
		}
		public String getMac() {
			return mac;
		}
		public String getIp() {
			return ip;
		}
		public String getUserAgent() {
			return userAgent;
		}
		public BoyuV2Geo getGeo() {
			return geo;
		}
		public void setDeviceId(String deviceId) {
			this.deviceId = deviceId;
		}
		public void setNetwork(int network) {
			this.network = network;
		}
		public void setDeviceType(int deviceType) {
			this.deviceType = deviceType;
		}
		public void setImei(String imei) {
			this.imei = imei;
		}
		public void setImeiMd5(String imeiMd5) {
			this.imeiMd5 = imeiMd5;
		}
		public void setIdfa(String idfa) {
			this.idfa = idfa;
		}
		public void setAndroidId(String androidId) {
			this.androidId = androidId;
		}
		public void setBrand(String brand) {
			this.brand = brand;
		}
		public void setModel(String model) {
			this.model = model;
		}
		public void setOs(String os) {
			this.os = os;
		}
		public void setOsVersion(String osVersion) {
			this.osVersion = osVersion;
		}
		public void setCarrier(int carrier) {
			this.carrier = carrier;
		}
		public void setMac(String mac) {
			this.mac = mac;
		}
		public void setIp(String ip) {
			this.ip = ip;
		}
		public void setUserAgent(String userAgent) {
			this.userAgent = userAgent;
		}
		public void setGeo(BoyuV2Geo geo) {
			this.geo = geo;
		}
							

}
