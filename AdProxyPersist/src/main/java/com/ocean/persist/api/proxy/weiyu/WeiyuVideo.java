package com.ocean.persist.api.proxy.weiyu;

import java.util.List;

public class WeiyuVideo {
	private String vurl	;//string	是	贴片物料地址
	private int duration		;//int	是	播放时长，单位秒
	private List<String> vm_d_start		;//string[]	否	视频开始下载监控地址，可能为空，也可能多个，如果不为空时需要逐条上报
	private List<String> vm_d_fail		;//string[]	否	视频下载失败监控地址，可能为空，也可能多个，如果不为空时需要逐条上报
	private List<String> vm_d_succ		;//string[]	否	视频下载成功监控地址，可能为空，也可能多个，如果不为空时需要逐条上报
	private List<String> vm_p_start	;//	string[]	否	视频开始播放监控地址，可能为空，也可能多个，如果不为空时需要逐条上报
	private List<String> vm_p_fail		;//string[]	否	视频播放失败监控地址，可能为空，也可能多个，如果不为空时需要逐条上报
	private List<String> vm_p_succ		;//string[]	否	视频播放成功监控地址，可能为空，也可能多个，如果不为空时需要逐条上报
	public String getVurl() {
		return vurl;
	}
	public int getDuration() {
		return duration;
	}
	public List<String> getVm_d_start() {
		return vm_d_start;
	}
	public List<String> getVm_d_fail() {
		return vm_d_fail;
	}
	public List<String> getVm_d_succ() {
		return vm_d_succ;
	}
	public List<String> getVm_p_start() {
		return vm_p_start;
	}
	public List<String> getVm_p_fail() {
		return vm_p_fail;
	}
	public List<String> getVm_p_succ() {
		return vm_p_succ;
	}
	public void setVurl(String vurl) {
		this.vurl = vurl;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public void setVm_d_start(List<String> vm_d_start) {
		this.vm_d_start = vm_d_start;
	}
	public void setVm_d_fail(List<String> vm_d_fail) {
		this.vm_d_fail = vm_d_fail;
	}
	public void setVm_d_succ(List<String> vm_d_succ) {
		this.vm_d_succ = vm_d_succ;
	}
	public void setVm_p_start(List<String> vm_p_start) {
		this.vm_p_start = vm_p_start;
	}
	public void setVm_p_fail(List<String> vm_p_fail) {
		this.vm_p_fail = vm_p_fail;
	}
	public void setVm_p_succ(List<String> vm_p_succ) {
		this.vm_p_succ = vm_p_succ;
	}
}
