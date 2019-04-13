package com.ocean.persist.app.dis.appSearch360.keywordSearch;

import java.util.List;

import com.ocean.persist.app.dis.AppDisResponse;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年7月27日 
      @version 1.0 
 */
public class KeywordSearch360Respose   implements AppDisResponse{
	private String errno;
	private String end_state;
	private String 	total;
    private List<KeywordSearch360App> data;
    private List<KeywordSearch360Track> track;
	public String getErrno() {
		return errno;
	}
	public void setErrno(String errno) {
		this.errno = errno;
	}
	public String getEnd_state() {
		return end_state;
	}
	public void setEnd_state(String end_state) {
		this.end_state = end_state;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public List<KeywordSearch360App> getData() {
		return data;
	}
	public void setData(List<KeywordSearch360App> data) {
		this.data = data;
	}
	public List<KeywordSearch360Track> getTrack() {
		return track;
	}
	public void setTrack(List<KeywordSearch360Track> track) {
		this.track = track;
	} 
	
	
}
