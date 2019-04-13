package com.ocean.persist.api.proxy.adhub;

import java.util.List;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年7月26日 
      @version 1.0 
 */
public class AdhubTemplate {
	private String Headline; //大众点评-美食频道",
	private String Image;//http://xxx.jpg",
	private List<String> Images;//["http://xxx.jpg","http://xxx.jpg"],  //文件后缀仅为示例
	private String Body;
	private List<String> Texts;
	private String AppIcon;
	private String Action;
	private String Star;
	private String Store;
	private String Price;
	private List<String>  Videos;//:["http://xxx.mp4","http://xxx.mp4"]   //文件后缀仅为示例
	public String getHeadline() {
		return Headline;
	}
	public void setHeadline(String headline) {
		Headline = headline;
	}
	public String getImage() {
		return Image;
	}
	public void setImage(String image) {
		Image = image;
	}
	public List<String> getImages() {
		return Images;
	}
	public void setImages(List<String> images) {
		Images = images;
	}
	public String getBody() {
		return Body;
	}
	public void setBody(String body) {
		Body = body;
	}
	public List<String> getTexts() {
		return Texts;
	}
	public void setTexts(List<String> texts) {
		Texts = texts;
	}
	public String getAppIcon() {
		return AppIcon;
	}
	public void setAppIcon(String appIcon) {
		AppIcon = appIcon;
	}
	public String getAction() {
		return Action;
	}
	public void setAction(String action) {
		Action = action;
	}
	public String getStar() {
		return Star;
	}
	public void setStar(String star) {
		Star = star;
	}
	public String getStore() {
		return Store;
	}
	public void setStore(String store) {
		Store = store;
	}
	public String getPrice() {
		return Price;
	}
	public void setPrice(String price) {
		Price = price;
	}
	public List<String> getVideos() {
		return Videos;
	}
	public void setVideos(List<String> videos) {
		Videos = videos;
	}
}
