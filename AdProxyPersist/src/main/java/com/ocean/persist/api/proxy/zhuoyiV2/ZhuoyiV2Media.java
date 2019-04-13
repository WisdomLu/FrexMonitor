package com.ocean.persist.api.proxy.zhuoyiV2;

public class ZhuoyiV2Media {
	private String id	;//required	string	媒体 ID（appid）由 ADroi 提供
	private String channel_id	;//optional	string	渠道 ID(暂不使用)
	private int type	;//required	int	媒体 类别（ 1.app 2.web 3.wap）
	//目前只支持 1.app
	private ZhuoyiV2App app	;//optional	app object	当前 app 信息
	//当 type 为 1(app) 时required
	//site	optional	site object	当前站点信息
	//browser	optional	browser object	浏览器信息
	public String getId() {
		return id;
	}
	public String getChannel_id() {
		return channel_id;
	}
	public int getType() {
		return type;
	}
	public ZhuoyiV2App getApp() {
		return app;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setChannel_id(String channel_id) {
		this.channel_id = channel_id;
	}
	public void setType(int type) {
		this.type = type;
	}
	public void setApp(ZhuoyiV2App app) {
		this.app = app;
	}
}
