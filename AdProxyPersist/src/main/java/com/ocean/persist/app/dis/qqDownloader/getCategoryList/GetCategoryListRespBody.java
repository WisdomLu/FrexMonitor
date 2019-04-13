package com.ocean.persist.app.dis.qqDownloader.getCategoryList;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.app.dis.AppDisResponse;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年5月12日 
      @version 1.0 
 */
public class GetCategoryListRespBody  implements AppDisResponse{
    /**
	 * 
	 */
	private static final long serialVersionUID = -2459989885747461593L;
	private int ret;//0成功，-1失败
    private List<CategoryDetail> categoryList;//分类列表
	public int getRet() {
		return ret;
	}
	public void setRet(int ret) {
		this.ret = ret;
	}
	public List<CategoryDetail> getCategoryList() {
		return categoryList;
	}
	public void setCategoryList(List<CategoryDetail> categoryList) {
		this.categoryList = categoryList;
	}
}
