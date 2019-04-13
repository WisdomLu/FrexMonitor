package com.ocean.persist.app.dis.daluoma.keywordSearch;
import com.ocean.persist.app.dis.daluoma.BaseDaluomaRequest;

public class DaluomaKeywordReq extends BaseDaluomaRequest{

	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}
	private String searchword 	 ;//string	搜索tag	电影票;演出	Y	英文分号分隔
	public String getSearchword() {
		return searchword;
	}
	public void setSearchword(String searchword) {
		this.searchword = searchword;
	}

	

}
