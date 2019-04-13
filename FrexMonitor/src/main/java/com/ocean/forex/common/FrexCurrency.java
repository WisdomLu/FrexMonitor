package com.ocean.forex.common;
public enum FrexCurrency {
	FREX_XAU_USD(1,"xauusd","XAUUSD"),		 //黄金
	FREX_WTI(2,"usoil","USCRUDE"),		 	 //美国原油
	FREX_EUR_USD(3,"eurusd","EURUSD"),		 //欧元兑美元
	FREX_USD_INDEX(4,"dxy","DXY"),		     //美元指数
	FREX_USD_JPY(5,"usdjpy","USDJPY");		 //美元日元
	private final int value;
	private final String name;
	private final String fmName;//fallowme的外汇名称

	private FrexCurrency(int value, String name,String fmName) {
		this.value = value;
		this.name = name;
		this.fmName=fmName;
	}

	public int getValue() {
		return value;
	}

	public String getName() {
		return name;
	}
	public static FrexCurrency getCurrencyType(String name){
		
		FrexCurrency[] curs = values();
		for (FrexCurrency cur : curs) 
		{
			if(cur.getName().equals(name))
			{
				return cur;
			}	
		}
		return null;
	}

	public String getFmName() {
		return fmName;
	}
}
