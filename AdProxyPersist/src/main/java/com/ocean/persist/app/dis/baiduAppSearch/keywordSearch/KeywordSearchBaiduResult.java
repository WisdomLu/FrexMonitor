package com.ocean.persist.app.dis.baiduAppSearch.keywordSearch;

import java.util.List;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年10月10日 
      @version 1.0 
 */
public class KeywordSearchBaiduResult {
    private String title;
    private int pn;
    private int rn;
    private int disp_num;
    private int ret_num;
    private List<KeywordSearchBaiduAppList> apps;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getPn() {
		return pn;
	}
	public void setPn(int pn) {
		this.pn = pn;
	}
	public int getRn() {
		return rn;
	}
	public void setRn(int rn) {
		this.rn = rn;
	}
	public int getDisp_num() {
		return disp_num;
	}
	public void setDisp_num(int disp_num) {
		this.disp_num = disp_num;
	}
	public int getRet_num() {
		return ret_num;
	}
	public void setRet_num(int ret_num) {
		this.ret_num = ret_num;
	}
	public List<KeywordSearchBaiduAppList> getApps() {
		return apps;
	}
	public void setApps(List<KeywordSearchBaiduAppList> apps) {
		this.apps = apps;
	}
}
