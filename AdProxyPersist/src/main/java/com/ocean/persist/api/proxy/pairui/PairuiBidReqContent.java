package com.ocean.persist.api.proxy.pairui;

import java.util.List;

public class PairuiBidReqContent {

	// optional  String 标题;如：“指环王三”。
	private String title;
	
	// optional  String 当前频道/栏目等信息。
	private String channel;
	
	// optional  String array  当前页面的关键词关键字。
	private List<String> keyword;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public List<String> getKeyword() {
		return keyword;
	}

	public void setKeyword(List<String> keyword) {
		this.keyword = keyword;
	}
}
