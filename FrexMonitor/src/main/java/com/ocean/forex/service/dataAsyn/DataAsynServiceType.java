package com.ocean.forex.service.dataAsyn;
public enum DataAsynServiceType {
	SERVICE_JINSHI(1,"jinshi"),		 
	SERVICE_FALLOWME(2,"fallowme"),;	 		 
	private final int value;
	private final String name;

	private DataAsynServiceType(int value, String name) {
		this.value = value;
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public String getName() {
		return name;
	}
	public static DataAsynServiceType getServiceType(String name){
		
		DataAsynServiceType[] services = values();
		for (DataAsynServiceType service : services) 
		{
			if(service.getName().equals(name))
			{
				return service;
			}	
		}
		return SERVICE_JINSHI;
	}
}
