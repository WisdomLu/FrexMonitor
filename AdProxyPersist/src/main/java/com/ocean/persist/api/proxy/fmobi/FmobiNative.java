package com.ocean.persist.api.proxy.fmobi;
/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年10月27日 
      @version 1.0 
 */
public class FmobiNative {
	private int template_id;
	private int index;
	private String index_value;
	
	private int required_field;
	
	private String required_value;
	
	private String type;

	public int getTemplate_id() {
		return template_id;
	}

	public void setTemplate_id(int template_id) {
		this.template_id = template_id;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getIndex_value() {
		return index_value;
	}

	public void setIndex_value(String index_value) {
		this.index_value = index_value;
	}

	public int getRequired_field() {
		return required_field;
	}

	public void setRequired_field(int required_field) {
		this.required_field = required_field;
	}

	public String getRequired_value() {
		return required_value;
	}

	public void setRequired_value(String required_value) {
		this.required_value = required_value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	
	
}
