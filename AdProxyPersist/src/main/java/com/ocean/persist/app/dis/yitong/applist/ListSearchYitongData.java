package com.ocean.persist.app.dis.yitong.applist;

import java.util.List;

public class ListSearchYitongData {
	private int count;//": 20, 记录条数
	private int flag;//": 1,//处理标记，0为数据获取失败，1为成功
	private int channel_type;//":0 渠道号
	private int end_mark;//": 0, 数据是否完结 完结是1没完结是0
	private String log_id;//": "f6bca1212f8c2cb8b08bc5d6c50e8efb" , //logid可用于验证
	private String theme_type;//": "1",//积分墙样式
	private String ad_color;//": "1",//banner样式
	private List<ListSearchYitongApp> list;
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public int getChannel_type() {
		return channel_type;
	}
	public void setChannel_type(int channel_type) {
		this.channel_type = channel_type;
	}
	public int getEnd_mark() {
		return end_mark;
	}
	public void setEnd_mark(int end_mark) {
		this.end_mark = end_mark;
	}
	public String getLog_id() {
		return log_id;
	}
	public void setLog_id(String log_id) {
		this.log_id = log_id;
	}
	public String getTheme_type() {
		return theme_type;
	}
	public void setTheme_type(String theme_type) {
		this.theme_type = theme_type;
	}
	public String getAd_color() {
		return ad_color;
	}
	public void setAd_color(String ad_color) {
		this.ad_color = ad_color;
	}
	public List<ListSearchYitongApp> getList() {
		return list;
	}
	public void setList(List<ListSearchYitongApp> list) {
		this.list = list;
	}
}
