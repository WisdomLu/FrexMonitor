package com.ocean.persist.app.dis.daluoma;

import java.util.List;

import com.ocean.persist.app.dis.AppDisResponse;
public class BaseDaluomaResponse implements AppDisResponse{
	private String errno;
	private String end_state;
	private String 	total;
    private List<BaseDaluomaApp> data;
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
	public List<BaseDaluomaApp> getData() {
		return data;
	}
	public void setData(List<BaseDaluomaApp> data) {
		this.data = data;
	}

}
