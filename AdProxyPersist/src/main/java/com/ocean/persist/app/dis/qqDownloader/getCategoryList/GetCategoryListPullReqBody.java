package com.ocean.persist.app.dis.qqDownloader.getCategoryList;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.app.dis.AdDisParams;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年5月12日 
      @version 1.0 
 */
public class GetCategoryListPullReqBody     implements AdDisParams{
   /**
	 * 
	 */
	private static final long serialVersionUID = -3891587664891382011L;
	private int reqType ;
	
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public int getReqType() {
		return reqType;
	}
	
	public void setReqType(int reqType) {
		this.reqType = reqType;
	}
}
