package com.ocean.persist.api.proxy.fmobi;

import java.util.ArrayList;
import java.util.List;

import com.ocean.core.common.threadpool.AbstractInvokeParameter;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年10月27日 
      @version 1.0 
 */
public class FmobiAdpullParams        extends AbstractInvokeParameter{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1588192725511481361L;
	@Override
	public boolean validate() {
		return false;
		// TODO Auto-generated method stub
		
	}
	private float version;
    private int ts;
    private List<FmobiImpression> impression;
    private FmobiDevice device;
    private String extend_data;
    private FmobiApp  app;
	public float getVersion() {
		return version;
	}
	public void setVersion(float version) {
		this.version = version;
	}
	public int getTs() {
		return ts;
	}
	public void setTs(int ts) {
		this.ts = ts;
	}

	public FmobiDevice getDevice() {
		return device;
	}
	public void setDevice(FmobiDevice device) {
		this.device = device;
	}
	public String getExtend_data() {
		return extend_data;
	}
	public void setExtend_data(String extend_data) {
		this.extend_data = extend_data;
	}
	public List<FmobiImpression> getImpression() {
		return impression;
	}
	public void setImpression(List<FmobiImpression> impression) {
		this.impression = impression;
	}
	public FmobiApp getApp() {
		return app;
	}
	public void setApp(FmobiApp app) {
		this.app = app;
	}
}
