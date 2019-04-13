package com.ocean.persist.app.dis.baiduAppSearch.keywordSearch;

import com.ocean.persist.app.dis.AppDisResponse;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年10月12日 
      @version 1.0 
 */
public class KeywordSearchBaiduReply   implements AppDisResponse{
    private KeywordSearchBaiduRespose response;

	public KeywordSearchBaiduRespose getResponse() {
		return response;
	}
	
	public void setResponse(KeywordSearchBaiduRespose response) {
		this.response = response;
	}
}
