package com.ocean.persist.api.proxy.qidian;

public class QidianImp {
	private int id ;
	private int count;
/*	当前请求的广告条数，不填默认为1，最多为50。超过50
	修正为50；小于1修正为1
	imps[x].video object N 视频广告请求，视频广告请求不能为空
	imps[x].video.minDuration int N 30
	最小的视频长度，闭区间，包含最小长度， 以毫秒为单
	位，默认为0不限制
	imps[x].video.maxDuration int N 70
	最大的视频长度，闭区间，包含最大长度， 以毫秒为单
	位，默认为0不限制
	imps[x].video.mimeTypes string N
	支持的视频类型, 1：video/mp4, mp4格式；2：
	video/3gpp, 3gp格式；3：video/x-msvideo, avi格式；
	4：video/x-flv, flv格式；5：video/x-ms-wmv, wmv格
	式；6：video/quicktime, mov格式。默认为空不限制，
	多个请用英文逗号(,)分割
	imps[x].video.sizes list N 允许的视频尺寸
	imps[x].video.sizes[y].width int N 允许的视频尺寸宽度，默认为0不限制
	imps[x].video.sizes[y].height int N 允许的视频尺寸高度，默认为0不限制
	imps[x].video.maxLength int N 允许的视频最大长度，以KB为单位，默认为0不限制*/
	public int getId() {
		return id;
	}
	public int getCount() {
		return count;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
