package com.ocean.persist.app.dis;

public enum JoinAppStoreEmu {
	TENCENT_QQDOWNLOADER(1, "TENCENT_QQDOWNLOADER"),//应用宝
	BAIDU_APPSEARCH(2,"BAIDU_APPSEARCH"),         // 百度
   	QIHOO_APPSTORE(3,"QIHOO_APPSTORE"),  		 // 360应用商城
	WANDOUJIA_PHOENIX2(4,"WANDOUJIA_PHOENIX2"),     	 // 豌豆荚
	XIAOMI_MARKET(5,"XIAOMI_MARKET"),			 //小米应用商店
	HUAWEI_APPMARKET(6,"HUAWEI_APPMARKET"),			 //华为应用商店
	DRAGON_PANDASPACE(7,"DRAGON_PANDASPACE"),			 //91手机助手
	HIAPK_MARKETPHO(8,"HIAPK_MARKETPHO"),			 //安智应用商店
	YINGYONGHUI_MARKET(9,"YINGYONGHUI_MARKET"),			 //应用汇
	TENCENT_QQPIMSECURE(10,"TENCENT_QQPIMSECURE"),		 //QQ手机管家
	MAPPN_GFAN(11,"MAPPN_GFAN"),		 //机锋应用市场
	PP_ASSISTANT(12,"PP_ASSISTANT"),		 //PP手机助手
	OPPO_MARKET(13,"OPPO_MARKET"),		 //OPPO应用商店
	GOAPK_MARKET(14,"GOAPK_MARKET"),		 //GO市场
	ZTE_MARKET(15,"ZTE_MARKET"),		 //中兴应用商店
	YULONG_COOLMART	(16,"YULONG_COOLMART"),	     //宇龙Coolpad应用商店
	LENOVO_LEOS_APPSTORE(17,"LENOVO_LEOS_APPSTORE"),		 //联想应用商店
	COOLAPK_MARKET(18,"COOLAPK_MARKET");		 //市场

	private final int value;
	private final String name;

	private JoinAppStoreEmu(int value, String name) {
		this.value = value;
		this.name = name;
	}

	public int getValue() {
		return value;
	}
	
	public String getName(){
		return name;
	}

	public static JoinAppStoreEmu getDsp(int value){
		
		JoinAppStoreEmu[] appStores = values();
		for (JoinAppStoreEmu appStore : appStores) 
		{
			if(appStore.getValue() == value)
			{
				return appStore;
			}	
		}
		return null;
	}
}
