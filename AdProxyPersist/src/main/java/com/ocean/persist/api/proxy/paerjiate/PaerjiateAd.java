package com.ocean.persist.api.proxy.paerjiate;

public class PaerjiateAd {
	private String adv_id;//	广告id
	private String title;//	广告标题
	private String description;//	广告介绍
	private String data_type;//	数据类型（apk下载、url跳转）
	private String data_content	;//对应“数据类型”的数据内容，一般是以链接方式呈现
	private String price;//	单价，货币单位USD
	private String icon	;//图标图片
	private String image;//	素材图片
	private String pname;
	public String getAdv_id() {
		return adv_id;
	}
	public void setAdv_id(String adv_id) {
		this.adv_id = adv_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getData_type() {
		return data_type;
	}
	public void setData_type(String data_type) {
		this.data_type = data_type;
	}
	public String getData_content() {
		return data_content;
	}
	public void setData_content(String data_content) {
		this.data_content = data_content;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}

}
