package com.ocean.persist.app.dis.fuxi.appasyn;

import java.util.List;
public class FuxiAppasynContent {
		private int total_cnt;
		private int has_more;
		private List<FuxiAsnApp> list;
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
		public List<FuxiAsnApp> getList() {
			return list;
		}
		public void setList(List<FuxiAsnApp> list) {
			this.list = list;
		}
}