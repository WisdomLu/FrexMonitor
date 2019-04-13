package com.ocean.persist.app.dis.qqDownloader.keywordSearch;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.app.dis.AppDisResponse;
import com.ocean.persist.app.dis.qqDownloader.ResponseHead;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年5月15日 
      @version 1.0 
 */
public class KeyWordSearchResponse     implements AppDisResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9021200965871782056L;
    private ResponseHead head;
    private KeyWordSearchRespBody body;
	public ResponseHead getHead() {
		return head;
	}
	public void setHead(ResponseHead head) {
		this.head = head;
	}
	public KeyWordSearchRespBody getBody() {
		return body;
	}
	public void setBody(KeyWordSearchRespBody body) {
		this.body = body;
	}
}
