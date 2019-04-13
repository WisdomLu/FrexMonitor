package com.ocean.persist.api.proxy.youxiao;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年1月15日 
      @version 1.0 
 */
public class YouxiaoAdPullResponse    extends AbstractBaseEntity  implements AdPullResponse{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7848871305622761417L;
	private int status_code	;//响应状态码	取值:0失败,1成功,2 no ad,3请求异常
	private YouxiaoAd msg	;//	返回内容	

	
	public int getStatus_code() {
		return status_code;
	}
	public void setStatus_code(int status_code) {
		this.status_code = status_code;
	}
	public YouxiaoAd getMsg() {
		return msg;
	}
	public void setMsg(YouxiaoAd msg) {
		this.msg = msg;
	}

}
