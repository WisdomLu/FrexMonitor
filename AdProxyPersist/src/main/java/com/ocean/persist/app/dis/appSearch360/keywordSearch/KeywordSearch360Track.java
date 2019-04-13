package com.ocean.persist.app.dis.appSearch360.keywordSearch;

import java.util.List;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年7月27日 
      @version 1.0 
 */
public class KeywordSearch360Track {
	private String bindid;
	private List<String> tk_imp;
	private List<String> tk_clk;
	private List<String> tk_ins;
	private List<String> tk_act;
	public String getBindid() {
		return bindid;
	}
	public void setBindid(String bindid) {
		this.bindid = bindid;
	}
	public List<String> getTk_imp() {
		return tk_imp;
	}
	public void setTk_imp(List<String> tk_imp) {
		this.tk_imp = tk_imp;
	}
	public List<String> getTk_clk() {
		return tk_clk;
	}
	public void setTk_clk(List<String> tk_clk) {
		this.tk_clk = tk_clk;
	}
	public List<String> getTk_ins() {
		return tk_ins;
	}
	public void setTk_ins(List<String> tk_ins) {
		this.tk_ins = tk_ins;
	}
	public List<String> getTk_act() {
		return tk_act;
	}
	public void setTk_act(List<String> tk_act) {
		this.tk_act = tk_act;
	}
}
