/**
 * Autogenerated by Thrift Compiler (0.9.2)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.ocean.persist.app.dis.zk;

import com.ocean.core.common.base.AbstractBaseEntity;

public class ZKPage  extends AbstractBaseEntity{
  /**
	 * 
	 */
	private static final long serialVersionUID = -3873830491087260084L;
	private int pageSize; // required
    private int from; // required
    private int limit; // required
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getFrom() {
		return from;
	}
	public void setFrom(int from) {
		this.from = from;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}


}

