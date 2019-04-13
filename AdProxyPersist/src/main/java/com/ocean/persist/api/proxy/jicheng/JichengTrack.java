package com.ocean.persist.api.proxy.jicheng;

import java.util.List;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年1月31日 
      @version 1.0 
 */
public class JichengTrack {
	private List<String> imp;//	曝光地址里面可能包含多个地址必须逐一上报
	private List<String> clk;//	点击上报里面可能包含多个地址必须逐一上报
	private List<String> download	;//下载上报里面可能包含多个地址必须逐一上报
	private List<String> install	;//安装上报里面可能包含多个地址必须逐一上报
	private List<String> active	;//激活上报里面可能包含多个地址必须逐一上报
	public List<String> getImp() {
		return imp;
	}
	public void setImp(List<String> imp) {
		this.imp = imp;
	}
	public List<String> getClk() {
		return clk;
	}
	public void setClk(List<String> clk) {
		this.clk = clk;
	}
	public List<String> getDownload() {
		return download;
	}
	public void setDownload(List<String> download) {
		this.download = download;
	}
	public List<String> getInstall() {
		return install;
	}
	public void setInstall(List<String> install) {
		this.install = install;
	}
	public List<String> getActive() {
		return active;
	}
	public void setActive(List<String> active) {
		this.active = active;
	}
}
