package com.ocean.persist.app.dis.wanka.appasyn;

import java.util.List;

import com.ocean.persist.app.dis.wanka.WankaApp;

public class WankaAppasynContent {
		private int total_cnt;
		private int has_more;
		private List<WankaApp> list;
		public int getTotal_cnt() {
			return total_cnt;
		}
		public void setTotal_cnt(int total_cnt) {
			this.total_cnt = total_cnt;
		}
		public int getHas_more() {
			return has_more;
		}
		public void setHas_more(int has_more) {
			this.has_more = has_more;
		}
		public List<WankaApp> getList() {
			return list;
		}
		public void setList(List<WankaApp> list) {
			this.list = list;
		}
}
