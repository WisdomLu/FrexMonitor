package com.ocean.persist.api.proxy.souying;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ocean.core.common.base.AbstractBaseEntity;
import com.ocean.persist.api.proxy.AdPullResponse;
public class SouyingAdContent  implements AdPullResponse{

	private static final long serialVersionUID = 1L;

	private String Adslot_id;// 广告位id
	private Integer Material_type;// 素材类型:0	html 1	native
	private String Html_snippet;// Html广告素材内容
	private SouyingAdMaterial Native_material;// 素材内容
	public String getAdslot_id() {
		return Adslot_id;
	}
	public void setAdslot_id(String adslot_id) {
		Adslot_id = adslot_id;
	}
	public Integer getMaterial_type() {
		return Material_type;
	}
	
	public String getHtml_snippet() {
		return Html_snippet;
	}
	public void setHtml_snippet(String html_snippet) {
		Html_snippet = html_snippet;
	}
	public void setMaterial_type(Integer material_type) {
		Material_type = material_type;
	}
	public SouyingAdMaterial getNative_material() {
		return Native_material;
	}
	public void setNative_material(SouyingAdMaterial native_material) {
		Native_material = native_material;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
