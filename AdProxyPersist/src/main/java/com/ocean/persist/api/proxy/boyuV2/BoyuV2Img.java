package com.ocean.persist.api.proxy.boyuV2;

public class BoyuV2Img {
		
	private String url;//	string		http://adstream.123.sogoucdn.com/lst/pic/150218		广告图片的url	
	private double width;//	double	1200.0										图片宽度，如：1200	
	private double height;//	double	500.0										图片高度，如： 500		
	private String desc;//	string		xxx	urlencode之后的图片描述，没有留空	
	public String getUrl() {
		return url;
	}

	public double getWidth() {
		return width;
	}

	public double getHeight() {
		return height;
	}

	public String getDesc() {
		return desc;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
