package com.ocean.persist.app.dis.appSearch360T2.keywordSearch;
import com.ocean.persist.app.dis.appSearch360T2.BaseSearch360T2Request;

public class KeywordSearch360T2Request extends BaseSearch360T2Request{

	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}
	private String tag	 ;//string	搜索tag	电影票;演出	Y	英文分号分隔

	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	

}
