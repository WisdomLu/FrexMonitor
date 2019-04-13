
package com.ocean.persist.app.dis.zk;

import com.ocean.core.common.base.AbstractBaseEntity;

public class ZKExtData  extends AbstractBaseEntity{
	  /**
	 * 
	 */
	  private static final long serialVersionUID = -792097281575971825L;
	  private String key; // required
	  private String value; // required
	  private String desc; // optional
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		public String getDesc() {
			return desc;
		}
		public void setDesc(String desc) {
			this.desc = desc;
		}

}

