package com.ocean.core.common.base;
public class BaseFormBean {
	private int pageSize;
	private int fromRow;
	private final int DEFAULT_PAGE_SIZE=15; 
	public int getPageSize() {
		if(pageSize==0){
			return DEFAULT_PAGE_SIZE;
		}
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		
		this.pageSize = pageSize;
	}
	public int getFromRow() {
		return fromRow;
	}
	public void setFromRow(int fromRow) {
		this.fromRow = fromRow;
	}


}
