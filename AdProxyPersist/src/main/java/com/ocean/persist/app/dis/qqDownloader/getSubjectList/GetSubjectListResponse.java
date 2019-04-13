package com.ocean.persist.app.dis.qqDownloader.getSubjectList;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.app.dis.AppDisResponse;
import com.ocean.persist.app.dis.qqDownloader.ResponseHead;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年5月18日 
      @version 1.0 
 */
public class GetSubjectListResponse       implements AppDisResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6369482284697756907L;
	private ResponseHead head;
	private GetSubjectListRespBody body;
	public ResponseHead getHead() {
		return head;
	}
	public void setHead(ResponseHead head) {
		this.head = head;
	}
	public GetSubjectListRespBody getBody() {
		return body;
	}
	public void setBody(GetSubjectListRespBody body) {
		this.body = body;
	}
}
