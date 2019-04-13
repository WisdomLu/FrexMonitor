package com.ocean.persist.api.proxy.wangxiang;
/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年1月23日 
      @version 1.0 
 */
public class WangxiangNetwork {
	private String ipv4	;//Ipv4地址	必填，公网Ipv4地址，确保填写的内容为用户设备的公网出口IP地址（app端直接请求广告可不填写，server端直接请求广告，需要填写）
	private String connection_type;/*	网络连接类型	必填，用于判断网速。
	CONNECTION_UNKNOWN = 0 无法探测当前网络状态; CELL_UNKNOWN = 1 蜂窝数据接入; 未知网络类型CELL_2G = 2 蜂窝数据2G网络; CELL_3G = 3 蜂窝数据3G网络;  CELL_4G = 4 蜂窝数据4G网; CELL_5G =5 蜂窝数据5G网络; WIFI = 100 Wi-Fi网络接入; ETHERNET = 101 以太网接入; NEW_TYPE = 999 未知新类型[int 类型]
	*/
	private String operator_type	;
	/*移动运营商类型	必填，用于运营商定向广告UNKNOWN_OPERATOR = 0 未知的运营商,CHINA_MOBILE = 1 中国移
	动,CHINA_TELECOM = 2 中国电
	信,CHINA_UNICOM = 3 中国联通,
	OTHER_OPERATOR = 99 其他运营商[int 类型]*/
	public String getIpv4() {
		return ipv4;
	}
	public void setIpv4(String ipv4) {
		this.ipv4 = ipv4;
	}
	public String getConnection_type() {
		return connection_type;
	}
	public void setConnection_type(String connection_type) {
		this.connection_type = connection_type;
	}
	public String getOperator_type() {
		return operator_type;
	}
	public void setOperator_type(String operator_type) {
		this.operator_type = operator_type;
	}

}
