package com.ocean.persist.app.dis.qqDownloader.getRecommendADList;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.app.dis.AppDisResponse;
import com.ocean.persist.app.dis.qqDownloader.ResponseHead;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年5月16日 
      @version 1.0 
 */
public class GetRecommendADListResponse       implements AppDisResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8562310285214051393L;
	private ResponseHead head;
	private GetRecommendADListRespBody body;
	public ResponseHead getHead() {
		return head;
	}
	public void setHead(ResponseHead head) {
		this.head = head;
	}
	public GetRecommendADListRespBody getBody() {
		return body;
	}
	public void setBody(GetRecommendADListRespBody body) {
		this.body = body;
	}
}
