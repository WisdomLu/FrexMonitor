package com.ocean.persist.api.proxy.wanyueV2;

import java.util.List;

public class WanyueV2Track {
	private int type	;//监测类型	number	监测类型:
/*		1: 广告展示
		2: 广告点击
		3：下载开始
		4：下载完成
		5：点击安装
		6: 安装完成
		7: 启动应用
		10: deeplink 拉活成功的监测	是	*/
	private List<String> track_url	;//监测地址	array	监测上报地址	是	
	private int http_method;//	发送监测的HTTP方法	number	0为GET 1为POST	默认：0	
	private String content	;//上报内容	string	如果检测方法为GET方式，该项为空，如果为POST方式，此项为上报内容	否	
	public int getType() {
		return type;
	}
	public List<String> getTrack_url() {
		return track_url;
	}
	public int getHttp_method() {
		return http_method;
	}
	public String getContent() {
		return content;
	}
	public void setType(int type) {
		this.type = type;
	}
	public void setTrack_url(List<String> track_url) {
		this.track_url = track_url;
	}
	public void setHttp_method(int http_method) {
		this.http_method = http_method;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
