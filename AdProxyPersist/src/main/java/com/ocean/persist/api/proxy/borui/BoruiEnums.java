package com.ocean.persist.api.proxy.borui;

public class BoruiEnums {

	public enum DeviceType {
		PHONE,
		TABLET,
		;
	}
	
	public enum OsType {
		Android,
		iOS,
		;

		public static String findByAdReq(String os) {
			if("ios".equals(os)) {
				return OsType.iOS.name();
			}
			return OsType.Android.name();
		}
	}
	
	public enum SpType {
		YD,
		LT,
		DX,
		None,
		;

		public static String findByAdReq(String mobile) {
			if("CMCC".equals(mobile)) {
				return SpType.YD.name();
			}
			if("CUCC".equals(mobile)) {
				return SpType.LT.name();
			}
			if("CTCC".equals(mobile)) {
				return SpType.DX.name();
			}
			return SpType.None.name();
		}
	}
	
	public enum ConnectionType {
		CELL_2G,
		CELL_3G,
		CELL_4G,
		CELL_WIFI,
		;

		public static String findByAdReq(String net) {
			if("wifi".equals(net)) {
				return ConnectionType.CELL_WIFI.name();
			}
			if("4g".equals(net)) {
				return ConnectionType.CELL_4G.name();
			}
			if("3g".equals(net)) {
				return ConnectionType.CELL_3G.name();
			}
			if("2g".equals(net)) {
				return ConnectionType.CELL_2G.name();
			}
			return ConnectionType.CELL_WIFI.name();
		}
	}
	
	public enum AdType {
		//通用
		currency,
		
		//横幅
		banner,
		
		//开屏
		splash,
		
		//插屏
		ingame,
		
		//信息流
		feeds,
		
		//通知栏
		notice,
		
		//视频
		video,
		;
	}
	
	public enum InteractionType {
		//string 跳转类广告
		H5,
		
		// string 下载类型广告
		DOWNLOAD,
		
		//此交互类型是，是 html 类型代码
		HTML,
		;
	}
}
