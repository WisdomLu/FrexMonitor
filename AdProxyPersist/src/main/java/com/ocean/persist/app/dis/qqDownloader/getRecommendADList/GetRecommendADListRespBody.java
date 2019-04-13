package com.ocean.persist.app.dis.qqDownloader.getRecommendADList;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.app.dis.AppDisResponse;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年5月16日 
      @version 1.0 
 */
public class GetRecommendADListRespBody    implements AppDisResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5099916241180286041L;
    private int ret;
    private List<ADAppInfo> appList;
	public int getRet() {
		return ret;
	}
	public void setRet(int ret) {
		this.ret = ret;
	}
	public List<ADAppInfo> getAppList() {
		return appList;
	}
	public void setAppList(List<ADAppInfo> appList) {
		this.appList = appList;
	}
} 
