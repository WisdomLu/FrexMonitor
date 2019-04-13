package com.ocean.persist.api.proxy.wanyueV2;

public class WanyueV2Native {
	private int tpl_id;//	模板ID	number		否	
	private int index;//;//	元素索引	number	元素在模板中的索引	否	
	private String index_value;//	元素的值	string	元素在模版中的自定义名称，由媒体方
/*	自己定义。 如：
	icon， title， description，image 等	*/	
	private int element_type;//	元素类型	number	1文本,2图片	是	
	private String element_mime;//	元素格式	string	如： png, jpg, bmp, gif, text, mp4等	是	
	private String element_value;//	元素内容	string	元素内容（素材地址/文本描述）	是	
	public int getTpl_id() {
		return tpl_id;
	}
	public int getIndex() {
		return index;
	}
	public String getIndex_value() {
		return index_value;
	}
	public int getElement_type() {
		return element_type;
	}
	public String getElement_mime() {
		return element_mime;
	}
	public String getElement_value() {
		return element_value;
	}
	public void setTpl_id(int tpl_id) {
		this.tpl_id = tpl_id;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public void setIndex_value(String index_value) {
		this.index_value = index_value;
	}
	public void setElement_type(int element_type) {
		this.element_type = element_type;
	}
	public void setElement_mime(String element_mime) {
		this.element_mime = element_mime;
	}
	public void setElement_value(String element_value) {
		this.element_value = element_value;
	}
}
