package com.ocean.persist.api.proxy.wanyueV2;

public class WanyueV2Adspace {
	private int ad_space_id;//	广告位Code	number	玩悦广告系统分配	是	
	private int width	;//宽带	number		是	
	private int height	;//高度	number		是	
	private int page_index	;//分页索引	number	分页时使用，当前页面的 index， index 从 0 开
	/*始计数	是	*/
	private int page_size	;//每页条数	number	分页时使用，每页显示的记录条数	是	
	private String keywords;//	关键字	string	当前广告位所在媒体环境的关键字, 多个英文逗号隔开	否	“体育,新闻,生活”
	public int getAd_space_id() {
		return ad_space_id;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public int getPage_index() {
		return page_index;
	}
	public int getPage_size() {
		return page_size;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setAd_space_id(int ad_space_id) {
		this.ad_space_id = ad_space_id;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public void setPage_index(int page_index) {
		this.page_index = page_index;
	}
	public void setPage_size(int page_size) {
		this.page_size = page_size;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
}
