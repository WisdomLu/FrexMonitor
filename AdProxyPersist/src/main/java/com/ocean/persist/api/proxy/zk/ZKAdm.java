package com.ocean.persist.api.proxy.zk;

import com.ocean.persist.api.proxy.AdPullResponse;

public class ZKAdm    implements AdPullResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2928018703705812768L;
    private String source;
    private ZKAdNative nativ;
    private String html_id;
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public ZKAdNative getNativ() {
		return nativ;
	}
	public void setNativ(ZKAdNative nativ) {
		this.nativ = nativ;
	}
	public String getHtml_id() {
		return html_id;
	}
	public void setHtml_id(String html_id) {
		this.html_id = html_id;
	}
}
