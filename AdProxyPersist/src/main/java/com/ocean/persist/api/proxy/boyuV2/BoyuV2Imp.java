package com.ocean.persist.api.proxy.boyuV2;

public class BoyuV2Imp {	
		private int id;//	int	Y	1	impId，从1开始计数							
		private int count;//	int	N	2	当前请求的广告条数，不填默认为1，最多为50。超过50	
			//修正为50；小于1修正为1						
		private BoyuV2Video video;//	object	N		视频广告请求，视频广告请求不能为空	
		public int getId() {
			return id;
		}
		public int getCount() {
			return count;
		}
		public BoyuV2Video getVideo() {
			return video;
		}
		public void setId(int id) {
			this.id = id;
		}
		public void setCount(int count) {
			this.count = count;
		}
		public void setVideo(BoyuV2Video video) {
			this.video = video;
		}
								
		
				
}
