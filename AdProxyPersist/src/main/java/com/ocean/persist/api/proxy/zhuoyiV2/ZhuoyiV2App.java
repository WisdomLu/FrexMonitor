package com.ocean.persist.api.proxy.zhuoyiV2;

public class ZhuoyiV2App {
	private String package_name	;//required	string	app 包名
	private String channel_id	;//optional	string	渠道 ID(暂不使用)
	private String version_id	;//required	string	软件版本号
	private int versioncode_id	;//required	int	软件内部版本号
	public String getPackage_name() {
		return package_name;
	}
	public String getChannel_id() {
		return channel_id;
	}
	public String getVersion_id() {
		return version_id;
	}
	public int getVersioncode_id() {
		return versioncode_id;
	}
	public void setPackage_name(String package_name) {
		this.package_name = package_name;
	}
	public void setChannel_id(String channel_id) {
		this.channel_id = channel_id;
	}
	public void setVersion_id(String version_id) {
		this.version_id = version_id;
	}
	public void setVersioncode_id(int versioncode_id) {
		this.versioncode_id = versioncode_id;
	}
}
