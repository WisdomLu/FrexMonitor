package com.ocean.persist.api.proxy.yuansheng;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年1月8日 
      @version 1.0 
 */
public class YuanshengAdPullResponse   extends AbstractBaseEntity  implements AdPullResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5237153099455323411L;
	private int code	;//integer	Y	状态码，详见5。
	private String msg;//	string	Y	附加消息。
	private List<YuanshengImpResp> adsimp;//	array of object	Y	广告曝光信息，见4.1.2。
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<YuanshengImpResp> getAdsimp() {
		return adsimp;
	}
	public void setAdsimp(List<YuanshengImpResp> adsimp) {
		this.adsimp = adsimp;
	}

}
