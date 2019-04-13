package com.ocean.persist.api.proxy.wangxiang;
/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年1月23日 
      @version 1.0 
 */
public class WangxiangData {
	private WangxiangApp app;//	应用参数	必填，请正确填写
	private WangxiangAdslot adslot	;//代码位	必填，代码位在ssp平台设置，设置实时生效，请谨慎选择
	private  WangxiangDevice device	;//设备参数	必填，获取并回传正确的设备信息，有帮变现效果
	private WangxiangNetwork network;// 	移动网络参数	必填，用于广告系统针对性匹配广告和选择交互方式
	private WangxiangGps gps	;//GPS参数	选填，强烈建议填写，当前设备实时位置
	public WangxiangApp getApp() {
		return app;
	}
	public void setApp(WangxiangApp app) {
		this.app = app;
	}
	public WangxiangAdslot getAdslot() {
		return adslot;
	}
	public void setAdslot(WangxiangAdslot adslot) {
		this.adslot = adslot;
	}
	public WangxiangDevice getDevice() {
		return device;
	}
	public void setDevice(WangxiangDevice device) {
		this.device = device;
	}
	public WangxiangNetwork getNetwork() {
		return network;
	}
	public void setNetwork(WangxiangNetwork network) {
		this.network = network;
	}
	public WangxiangGps getGps() {
		return gps;
	}
	public void setGps(WangxiangGps gps) {
		this.gps = gps;
	}

}
