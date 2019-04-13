package com.ocean.service.qqInvokHandler.base;


/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年5月15日 
      @version 1.0 
 */
public enum QQMethodType {
    GET_CATEGORY_LIST(1, "getcCategoryList"),
    GET_APP_LIST(2,"getAppList"),    
    KEYWORD_SEARCH(3,"searchADApp"),
    GET_RANK_APP_LIST(4,"getRankAppADList"),
    GET_RECOMMEND_APP_LIST(5,"getRecommendADList"),
    GET_SUBJECTLIST(6,"getSubjectList");
	private final int value;
	private final String name;

	private QQMethodType(int value, String name) {
		this.value = value;
		this.name = name;
	}

	public int getValue() {
		return value;
	}
	
	public String getName(){
		return name;
	}

	public static QQMethodType getDsp(int value){
		
		QQMethodType[] types = values();
		for (QQMethodType interType : types) 
		{
			if(interType.getValue() == value)
			{
				return interType;
			}	
		}
		return null;
	}
}
