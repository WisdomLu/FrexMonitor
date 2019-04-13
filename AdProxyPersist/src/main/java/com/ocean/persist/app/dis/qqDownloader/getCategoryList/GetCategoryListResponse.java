package com.ocean.persist.app.dis.qqDownloader.getCategoryList;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.app.dis.AppDisResponse;
import com.ocean.persist.app.dis.qqDownloader.ResponseHead;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年5月15日 
      @version 1.0 
 */
public class GetCategoryListResponse    implements AppDisResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8131328237226533283L;
    private GetCategoryListRespBody body;
    private ResponseHead head;
	public ResponseHead getHead() {
		return head;
	}
	public void setHead(ResponseHead head) {
		this.head = head;
	}
	public GetCategoryListRespBody getBody() {
		return body;
	}
	public void setBody(GetCategoryListRespBody body) {
		this.body = body;
	}

}
