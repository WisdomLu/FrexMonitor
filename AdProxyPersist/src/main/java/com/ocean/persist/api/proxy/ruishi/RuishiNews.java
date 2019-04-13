package com.ocean.persist.api.proxy.ruishi;

import java.util.List;

public class RuishiNews {
	private String id	;//string	是	标识新闻的id
	private String title	;//string	是	新闻标题
	private String source;//	string	是	新闻来源，如 ”北青网“
	private String update	;//string	是	新闻更新时间，如 ”2017-10-25 14:26:56"
	private List<String> img	;//string[]	是	新闻图片地址，目前是三张图片，尺寸为 228x152
	private String ldp	;//string	是	新闻点击地址
	private String cat_name;//	string	否	新闻分类名称
	private int cat_id	;//int	否	新闻分类id
	private int comment_count	;//int	否	新闻评论数量
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getUpdate() {
		return update;
	}
	public void setUpdate(String update) {
		this.update = update;
	}
	public List<String> getImg() {
		return img;
	}
	public void setImg(List<String> img) {
		this.img = img;
	}
	public String getLdp() {
		return ldp;
	}
	public void setLdp(String ldp) {
		this.ldp = ldp;
	}
	public String getCat_name() {
		return cat_name;
	}
	public void setCat_name(String cat_name) {
		this.cat_name = cat_name;
	}
	public int getCat_id() {
		return cat_id;
	}
	public void setCat_id(int cat_id) {
		this.cat_id = cat_id;
	}
	public int getComment_count() {
		return comment_count;
	}
	public void setComment_count(int comment_count) {
		this.comment_count = comment_count;
	}

}
