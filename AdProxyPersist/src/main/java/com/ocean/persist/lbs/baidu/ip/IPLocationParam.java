package com.ocean.persist.lbs.baidu.ip;

import com.ocean.core.common.threadpool.AbstractInvokeParameter;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年8月7日 
      @version 1.0 
 */
public class IPLocationParam extends AbstractInvokeParameter{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1386841220497057400L;
	private String ip;// 	IP地址 	string 	可选，IP不出现，或者出现且为空字符串的情况下，会使用当前访问者的IP地址作为定位参数。
	private String ak ;// 	开发者密钥 	string 	必选，登录API控制台，申请AK，作为访问的依据。
	private String sn;//  	用户的权限签名 	string 	可选，若用户所用AK的校验方式为SN校验时该参数必须。（SN生成算法）
	private String coor ;// 	输出的坐标格式 	string 	可选，coor不出现时，默认为百度墨卡托坐标；coor=bd09ll时，返回为百度经纬度坐标；coor=gcj02时，返回为国测局坐标。 
	@Override
	public boolean validate() {
		return false;
		// TODO Auto-generated method stub
		
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getAk() {
		return ak;
	}
	public void setAk(String ak) {
		this.ak = ak;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public String getCoor() {
		return coor;
	}
	public void setCoor(String coor) {
		this.coor = coor;
	}
}
