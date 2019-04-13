package com.ocean.persist.app.dis.wxcpd.keywordSearch;

import java.util.List;

public class KeywordSearchWxMsg {
	
		private int page;
		private int page_size;
		private List<KeywordSearchWxApp> apkList;
		public int getPage() {
			return page;
		}
		public void setPage(int page) {
			this.page = page;
		}
		public int getPage_size() {
			return page_size;
		}
		public void setPage_size(int page_size) {
			this.page_size = page_size;
		}
		public List<KeywordSearchWxApp> getApkList() {
			return apkList;
		}
		public void setApkList(List<KeywordSearchWxApp> apkList) {
			this.apkList = apkList;
		}
}
