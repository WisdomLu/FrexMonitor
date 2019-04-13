package com.ocean.persist.api.proxy.jieku;

import com.ocean.core.common.base.AbstractBaseEntity;

public class JiekuAdContent {

	private static final long serialVersionUID = 1L;
	
	private String Adslot_id;// required string ⼲⼴广告位ID
	private Integer Material_type;// required int 物料类型 0.DYNAMIC(HTML) 1.NATIVE(Native)
	private String Html_snippet;// optional string HTML创意内容(type:0)
	private JiekuAdm Native_material;// optional native_material object 原⽣生⼲⼴广告物料内容(type:1)
	
	public String getAdslot_id() {
		return Adslot_id;
	}

	public void setAdslot_id(String adslot_id) {
		Adslot_id = adslot_id;
	}

	public Integer getMaterial_type() {
		return Material_type;
	}

	public void setMaterial_type(Integer material_type) {
		Material_type = material_type;
	}

	public String getHtml_snippet() {
		return Html_snippet;
	}

	public void setHtml_snippet(String html_snippet) {
		Html_snippet = html_snippet;
	}

	public JiekuAdm getNative_material() {
		return Native_material;
	}

	public void setNative_material(JiekuAdm native_material) {
		Native_material = native_material;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
