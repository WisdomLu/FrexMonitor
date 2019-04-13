package com.ocean.persist.app.dis.zk;
import java.util.List;

import com.ocean.persist.app.dis.AdDisParams;

public class ZKAppRecomReq  implements AdDisParams{
	  private String requestId; // required
	  private ZKUserInfo userInfo; // required
	  private ZKAppDetail appInfo; // optional
	  private ZKDeviceInfo device; // required
	  private String version; // optional
	  private ZKAppSpaceInfo appSpaceInfo; // required
	  private List<Integer> shieldType; // optional
	  private ZKPage page; // optional
	  private int hasNext; // optional
	
	  private int joinSource; // optional
	  private List<ZKExtData> joinParam; // optional
	  /**
	   * 
	   * @see InterType
	   */
	  private int interType; // required
	  private ZKInterParam interParam; // optional
	  
		public String getRequestId() {
			return requestId;
		}
		public void setRequestId(String requestId) {
			this.requestId = requestId;
		}
		public ZKUserInfo getUserInfo() {
			return userInfo;
		}
		public void setUserInfo(ZKUserInfo userInfo) {
			this.userInfo = userInfo;
		}
		public ZKAppDetail getAppInfo() {
			return appInfo;
		}
		public void setAppInfo(ZKAppDetail appInfo) {
			this.appInfo = appInfo;
		}
		public ZKDeviceInfo getDevice() {
			return device;
		}
		public void setDevice(ZKDeviceInfo device) {
			this.device = device;
		}
		public String getVersion() {
			return version;
		}
		public void setVersion(String version) {
			this.version = version;
		}
		public ZKAppSpaceInfo getAppSpaceInfo() {
			return appSpaceInfo;
		}
		public void setAppSpaceInfo(ZKAppSpaceInfo appSpaceInfo) {
			this.appSpaceInfo = appSpaceInfo;
		}
		public List<Integer> getShieldType() {
			return shieldType;
		}
		public void setShieldType(List<Integer> shieldType) {
			this.shieldType = shieldType;
		}
		public ZKPage getPage() {
			return page;
		}
		public void setPage(ZKPage page) {
			this.page = page;
		}
		public int getHasNext() {
			return hasNext;
		}
		public void setHasNext(int hasNext) {
			this.hasNext = hasNext;
		}
		public int getJoinSource() {
			return joinSource;
		}
		public void setJoinSource(int joinSource) {
			this.joinSource = joinSource;
		}
		public List<ZKExtData> getJoinParam() {
			return joinParam;
		}
		public void setJoinParam(List<ZKExtData> joinParam) {
			this.joinParam = joinParam;
		}
		public int getInterType() {
			return interType;
		}
		public void setInterType(int interType) {
			this.interType = interType;
		}
		public ZKInterParam getInterParam() {
			return interParam;
		}
		public void setInterParam(ZKInterParam interParam) {
			this.interParam = interParam;
		}
		public boolean validate() {
			// TODO Auto-generated method stub
			return false;
		}
	
	  
}

