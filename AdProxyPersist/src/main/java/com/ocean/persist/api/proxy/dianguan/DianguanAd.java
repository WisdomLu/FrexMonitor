package com.ocean.persist.api.proxy.dianguan;

public class DianguanAd {
	private int material_type;
	private String html_snippet;
	private DianguanNative native_material;
	public int getMaterial_type() {
		return material_type;
	}
	public void setMaterial_type(int material_type) {
		this.material_type = material_type;
	}
	public String getHtml_snippet() {
		return html_snippet;
	}
	public void setHtml_snippet(String html_snippet) {
		this.html_snippet = html_snippet;
	}
	public DianguanNative getNative_material() {
		return native_material;
	}
	public void setNative_material(DianguanNative native_material) {
		this.native_material = native_material;
	}
}
