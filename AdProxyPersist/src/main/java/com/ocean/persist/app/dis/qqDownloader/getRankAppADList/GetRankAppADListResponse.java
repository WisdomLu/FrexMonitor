package com.ocean.persist.app.dis.qqDownloader.getRankAppADList;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.app.dis.AppDisResponse;
import com.ocean.persist.app.dis.qqDownloader.ResponseHead;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年5月16日 
      @version 1.0 
 */
public class GetRankAppADListResponse      implements AppDisResponse{
    /**
	 * 
	 */
	private static final long serialVersionUID = -64287128094403107L;
	private ResponseHead head;
    private GetRankAppADListRespBody body;
	public ResponseHead getHead() {
		return head;
	}
	public void setHead(ResponseHead head) {
		this.head = head;
	}
	public GetRankAppADListRespBody getBody() {
		return body;
	}
	public void setBody(GetRankAppADListRespBody body) {
		this.body = body;
	}
}
