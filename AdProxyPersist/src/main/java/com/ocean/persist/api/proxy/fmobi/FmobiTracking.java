package com.ocean.persist.api.proxy.fmobi;

import java.util.List;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年10月27日 
      @version 1.0 
 */
public class FmobiTracking {
	private int tracking_key;
	private List<String> tracking_value;
	public int getTracking_key() {
		return tracking_key;
	}
	public void setTracking_key(int tracking_key) {
		this.tracking_key = tracking_key;
	}
	public List<String> getTracking_value() {
		return tracking_value;
	}
	public void setTracking_value(List<String> tracking_value) {
		this.tracking_value = tracking_value;
	}
}
