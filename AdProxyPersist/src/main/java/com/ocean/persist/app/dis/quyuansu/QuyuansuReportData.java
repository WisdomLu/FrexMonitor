package com.ocean.persist.app.dis.quyuansu;

public class QuyuansuReportData { 
	private String id ;
/*	趣元素分配给下游的用户 id 
	签名，接口校验密钥 
	包名 */
	
	//private String apiToken ;
	private String token;
	private String packageName ;
	private long timestamp ;
	private int type ;
/*	13 位时间毫秒数 
	数据上报类型： 
	1  数据展示上报； 
	2  点击数据上报； 
	3  开始下载数据上报； 
	4  完成下载数据上报； 
	5  安装完成数据上报； 
	6安装开始数据上报；*/
	private String x;
/*	string
	以广告左上角为原点的点击位置横坐标(单位：像素，且为正整数)
	点击时需要*/
	private String y;
/*	string
	以广告左上角为原点的点击位置纵坐标(单位：像素，且为正整数)
	点击时需要*/
	private String start;
/*	string
	事件开始触发的时间戳（单位：毫秒13位整形）
	展示,点击, 下载完成、安装完成和激活时需要*/
	private String end;
    public String getX() {
		return x;
	}
	public String getY() {
		return y;
	}
	public String getStart() {
		return start;
	}
	public String getEnd() {
		return end;
	}
	public void setX(String x) {
		this.x = x;
	}
	public void setY(String y) {
		this.y = y;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	/*	string
	结束触发的时间戳（单位：毫秒13位整形）
	展示,点击时, 下载完成、安装完成和激活需要*/
	private String 	channel ;//详情接口返回的编号 
	private String sequence ;// 接口序列号（详情接口返回）
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getSequence() {
		return sequence;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
}
