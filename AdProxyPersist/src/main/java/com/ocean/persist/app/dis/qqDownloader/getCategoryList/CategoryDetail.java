package com.ocean.persist.app.dis.qqDownloader.getCategoryList;

import java.util.List;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.app.dis.AppDisResponse;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年5月12日 
      @version 1.0 
 */
public class CategoryDetail   implements AppDisResponse{


	private static final long serialVersionUID = 1301393451118071375L;
	private int categoryId;//分类Id
	private String categoryName;//分类名称
	private int parentId;//父类Id：-1软件，-2游戏
	private String iconUrl;//icon url
	private String description;
	private List<TagItem> tagList;//分类下的Tag列表
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public String getIconUrl() {
		return iconUrl;
	}
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<TagItem> getTagList() {
		return tagList;
	}
	public void setTagList(List<TagItem> tagList) {
		this.tagList = tagList;
	}
}
