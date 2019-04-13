package com.ocean.persist.api.proxy.boyuV2;

public class BoyuV2Size {
	private int width;//	int	N		允许的视频尺寸宽度，默认为0不限制	
	
	private int height;//	int	N		允许的视频尺寸高度，默认为0不限制	

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
