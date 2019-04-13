package com.ocean.persist.api.proxy.adview;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;
public class AdviewNative  implements AdPullResponse{

	private static final long serialVersionUID = 1L;
	private String ver;// 原生广告协议版本号 	1.0 
	private AdviewImage icon;// icon
	private AdviewImage logo;// logo
	private List<AdviewImage> images;// 大图
	private String title;// 广告标题 	"广告标题" 
	private String desc;// 广告描述内容 	"广告描述" 
	private String ctatext;// 动作行为按钮显示文本 	"安装" 
	private String desc2;// 	补充广告描述文本 	"补充广告描述" 
	private String rating;// 广告产品的评级 	"4" 
	private String likes;// 多少人喜欢或好评 	"5000" 
	private String downloads;// 多少下载或安装 	"300000" 
	private String price;// 产品/APP/应用内价格，包括价格单位 	"￥99.0" 
	private String saleprice;// 折扣价。包括价格单位 	"￥65.0" 
	private String phone;// 广告产品厂家或代理商联系电话 	"13812345678" 
	private String address;// 广告产品厂家或代理商联系地址 	"<address>" 
	private String displayurl;// 广告上显示出对应的广告内容网址 	"<display-url>" 
	private String sponsored;// 广告产品对应生产或销售厂商或公司名 	"<sponsored>" 
	public String getVer() {
		return ver;
	}
	public void setVer(String ver) {
		this.ver = ver;
	}
	public AdviewImage getIcon() {
		return icon;
	}
	public void setIcon(AdviewImage icon) {
		this.icon = icon;
	}
	public AdviewImage getLogo() {
		return logo;
	}
	public void setLogo(AdviewImage logo) {
		this.logo = logo;
	}
	public List<AdviewImage> getImages() {
		return images;
	}
	public void setImages(List<AdviewImage> images) {
		this.images = images;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getCtatext() {
		return ctatext;
	}
	public void setCtatext(String ctatext) {
		this.ctatext = ctatext;
	}
	public String getDesc2() {
		return desc2;
	}
	public void setDesc2(String desc2) {
		this.desc2 = desc2;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public String getLikes() {
		return likes;
	}
	public void setLikes(String likes) {
		this.likes = likes;
	}
	public String getDownloads() {
		return downloads;
	}
	public void setDownloads(String downloads) {
		this.downloads = downloads;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getSaleprice() {
		return saleprice;
	}
	public void setSaleprice(String saleprice) {
		this.saleprice = saleprice;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDisplayurl() {
		return displayurl;
	}
	public void setDisplayurl(String displayurl) {
		this.displayurl = displayurl;
	}
	public String getSponsored() {
		return sponsored;
	}
	public void setSponsored(String sponsored) {
		this.sponsored = sponsored;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
