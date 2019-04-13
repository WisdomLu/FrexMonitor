package com.ocean.persist.api.proxy.boyuV2;

import java.util.List;

public class BoyuV2Video {
	private int minDuration;//	int	N	30	最小的视频长度，闭区间，包含最小长度， 以毫秒为单	
	//位，默认为0不限制					
	private int maxDuration	;//int	N	70	最大的视频长度，闭区间，包含最大长度， 以毫秒为单	
		/*		位，默认为0不限制字段名	类型	必	示例	说明	填			
				支持的视频类型, 1：video/mp4, mp4格式；2：	
				video/3gpp, 3gp格式；3：video/x-msvideo, avi格式；	*/
	private String mimeTypes;//	string	N		4：video/x-flv, flv格式；5：video/x-ms-wmv, wmv格	
		/*		式；6：video/quicktime, mov格式。默认为空不限制，	
				多个请用英文逗号(,)分割	*/	
	private List<BoyuV2Size> sizes;//	list	N		允许的视频尺寸	
	private int maxLength;//	int	N		允许的视频最大长度，以KB为单位，默认为0不限制	
	//返回
	private int duration;//	int	1	视频的播放时长，以毫秒为单位	视频类型, 1：video/mp4, mp4格式；2：	video/3gpp, 3gp格式；3：video/x-msvideo,	
	private int mimeType;//	int	1	avi格式；4：video/x-flv, flv格式；5：video/x-	ms-wmv, wmv格式；6：video/quicktime, mov	格式。	
	
	private int width;//	int	600	视频文件宽度	
	
	private int height;//	int	500	视频文件高度	
	
	private String url;//	string		视频文件的url，流视频文件为转码以后的地址	
	
	private int length;//	int		视频大小，以KB为单位	
	
	private List<BoyuV2Tracking> eventTrackInfos;//	list		事件监测url	
	

	public int getMinDuration() {
		return minDuration;
	}
	public int getMaxDuration() {
		return maxDuration;
	}
	public String getMimeTypes() {
		return mimeTypes;
	}
	public List<BoyuV2Size> getSizes() {
		return sizes;
	}
	public int getMaxLength() {
		return maxLength;
	}
	public void setMinDuration(int minDuration) {
		this.minDuration = minDuration;
	}
	public void setMaxDuration(int maxDuration) {
		this.maxDuration = maxDuration;
	}
	public void setMimeTypes(String mimeTypes) {
		this.mimeTypes = mimeTypes;
	}
	public void setSizes(List<BoyuV2Size> sizes) {
		this.sizes = sizes;
	}
	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}
	public int getDuration() {
		return duration;
	}
	public int getMimeType() {
		return mimeType;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public String getUrl() {
		return url;
	}
	public int getLength() {
		return length;
	}
	public List<BoyuV2Tracking> getEventTrackInfos() {
		return eventTrackInfos;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public void setMimeType(int mimeType) {
		this.mimeType = mimeType;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public void setEventTrackInfos(List<BoyuV2Tracking> eventTrackInfos) {
		this.eventTrackInfos = eventTrackInfos;
	}
}
