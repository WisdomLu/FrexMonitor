package com.ocean.persist.app.dis.qqDownloader.getRecommendADList;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.app.dis.AdDisParams;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年5月16日 
      @version 1.0 
 */
public class GetRecommendADListReqBody      implements AdDisParams{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7114928112727113113L;
    private List<String> appList;
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}
	public List<String> getAppList() {
		return appList;
	}
	public void setAppList(List<String> appList) {
		this.appList = appList;
	}

}
