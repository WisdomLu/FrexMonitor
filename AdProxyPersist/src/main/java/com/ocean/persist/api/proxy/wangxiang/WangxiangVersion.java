package com.ocean.persist.api.proxy.wangxiang;
/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年1月23日 
      @version 1.0 
 */
public class WangxiangVersion {
	private String major;//	主版本号	必填，应用主版本号 
	private String minor;//	副版本号	选填，应用副版本号
	private String micro	;//小版本号	选填，应用小版本号
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getMinor() {
		return minor;
	}
	public void setMinor(String minor) {
		this.minor = minor;
	}
	public String getMicro() {
		return micro;
	}
	public void setMicro(String micro) {
		this.micro = micro;
	}

}
