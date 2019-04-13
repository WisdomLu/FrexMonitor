package com.ocean.persist.app.dis.wanyujx.pkgsearch;


import java.util.List;

import com.ocean.persist.app.dis.AppDisResponse;

	public class WanyujxPkgSchResp   implements AppDisResponse{
	   private int code;
	   private List<WanyujxPkgSchRespApp> adInfos;
	   private List<WanyujxPkgSchAsset> cacheAssets;
		public int getCode() {
			return code;
		}
		public List<WanyujxPkgSchRespApp> getAdInfos() {
			return adInfos;
		}
		public void setCode(int code) {
			this.code = code;
		}
		public void setAdInfos(List<WanyujxPkgSchRespApp> adInfos) {
			this.adInfos = adInfos;
		}
		public List<WanyujxPkgSchAsset> getCacheAssets() {
			return cacheAssets;
		}
		public void setCacheAssets(List<WanyujxPkgSchAsset> cacheAssets) {
			this.cacheAssets = cacheAssets;
		}
}
