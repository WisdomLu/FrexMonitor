package com.ocean.persist.api.proxy.zhuoyiV2;
public class ZhuoyiV2Ad {
	private String adslot_id	;//required	string	广告位 ID
	private int type;//	required	int	广告位类型
/*	1.BANNER（横幅）
	2.OPENSCREEN（开屏）
	3.INSERT（插屏）
	4.NATIVE（原生）
	7.	REWARDED_VIDEO（激 励视频）*/
	private int material_type;//	required	int	物料类型
/*	0.DYNAMIC（HTML）
	1.NATIVE（Native）*/
	private String html_snippet	;//optional	string	HTML 创意内容（type:0）
	private ZhuoyiV2Native  native_material	;//optional	native_material object	原生广告物料内容（type:1）
	public String getAdslot_id() {
		return adslot_id;
	}
	public int getType() {
		return type;
	}
	public int getMaterial_type() {
		return material_type;
	}
	public String getHtml_snippet() {
		return html_snippet;
	}
	public ZhuoyiV2Native getNative_material() {
		return native_material;
	}
	public void setAdslot_id(String adslot_id) {
		this.adslot_id = adslot_id;
	}
	public void setType(int type) {
		this.type = type;
	}
	public void setMaterial_type(int material_type) {
		this.material_type = material_type;
	}
	public void setHtml_snippet(String html_snippet) {
		this.html_snippet = html_snippet;
	}
	public void setNative_material(ZhuoyiV2Native native_material) {
		this.native_material = native_material;
	}
}
