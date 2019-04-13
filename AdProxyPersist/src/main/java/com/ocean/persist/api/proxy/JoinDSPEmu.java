package com.ocean.persist.api.proxy;

public enum JoinDSPEmu {
	
	/** 灵集*/
	LINGJI(7, "灵集", "lj", 4),
	
	/** 泰莱*/
	TAYLOR(8, "泰莱", "tl", 5),

	/** 讯飞*/
	XUNFEI(9, "讯飞", "xf", 6),

	/** 搏点*/
	BOCLICK(10, "搏点", "bd", 7),
	
	/** 万流客*/
	VAMAKER(11, "万流客", "wlk", 8),
	
	/** 摩邑城*/
	MEX(12, "摩邑城", "myc", 9),
	
	/** adview*/
	ADVIEW(13, "adview", "av", 11),
	
	/** 易积分*/
	YIJIFEN(14, "易积分", "yjf", 12),
	
	/** 玩咖*/
	WANKA(15, "玩咖", "wk", 10),
	
	/** 申米*/
	SHENMI(16, "申米", "sm", 13),
	
	/** 头条*/
	TOUTIAO(17, "头条", "tt", 14),
	
	/** 光音*/
	GUANGYIN(18, "光音", "gy", 15),
	
	/** 搜影*/
	SOUYING(19, "搜影", "sy", 16),
	
	/** OneMob*/
	ONEMOB(20, "OneMob", "om", 17),
	
	/** 仙果*/
	XIANGUO(21, "仙果", "xg", 18),
	
	/** 有道*/
	YOUDAO(22, "有道", "yd", 19),
	
	/** 众橙*/
	ZHONGCHENG(23, "众橙", "zc", 20),
	
	/** InMobi*/
	INMOBI(24, "InMobi", "im", 21),
	
	/** 瑞恩*/
	RYAN(25, "瑞恩", "re", 22),
	
	/** 捷酷*/
	JIEKU(26, "捷酷", "jk", 23),
	/*掌游*/
	ZHANGYOU(27,"掌游","zhangyou",24),
	//红石
	REDSTONE(28,"红石","redstone",25),
	//维快
	ADHUB(29,"维快(AdHub)","adhub",26),
	//新数网络
	XINSHU(30,"新数网络","xinshu",27),
	//点入
	DIANRU(31,"点入","dianru",28),
	//禾连
	HELIAN(32,"禾连","helian",29),
	
	//软告云
	RUANGAOYUN(33,"软告云","ruangaoyun",30),
	
	//金立
	JINLI(34,"金立","jinli",31),
	//魅族
	MEIZU(35,"魅族","meizu",32),
	//oppo
	OPPO(36,"oppo","oppo",33),
	//欧朋
	OUPENG(37,"欧朋","oupeng",34),
	
	//搜狐
	SOUHU(38,"搜狐","souhu",35),
	
	//微品
	WEIPIN(39,"微品","weipin",36),
	//维京
	WEIJING(40,"维京","weijing",37),
	
	SPEED(41,"天旭汇丰","speed",38),
	
	JUGAO(42,"聚告","jugao",39),
	
	YINCHENG(43,"银橙","yincheng",40),
	
	
	GMOBI(44,"Gmobi","gmobi",41),
	
	HUIXUAN(45,"汇选","huixuan",42),
	
	SHAIBO(46,"晒铂","shaibo",43),
	
	AIDIANJI(47,"爱点击","aidianji",44),
	
	MOCHUANG(48,"魔窗","mochuang",45),
	
	DIANKAI(49,"点开","diankai",46),
	
	HUZHONG(50,"互众","huzhong",47),
	
	MAIGUANG(51,"麦广","maiguang",48),
	
	ZHUOYI(52,"卓易","zhuoyi",49),
	
	ZHANGKU(53,"掌酷","zhangku",50),
	
	XD(54,"XD","xd",51),
	
	ICLOUD(55,"ICLOUD","icloud",52),
	
	UNIPLAY(56,"玩转互联","uniplay",53),
	
	FMOBI(57,"猎鹰","fmobi",54),
	
	WOSO(58,"我搜","woso",55),
	ABAIDU(59,"abaidu","abaidu",56),
	
	ADWO(60,"安沃","adwo",57),
	FIREBIRD(61,"火峰鸟","firebird",58),
	YITONG(62,"亿桐","yitong",59),
	FLYING(63,"飞扬","flying",60),
	YUANSHENG(64,"元生","yuansheng",61),
	YOUXIAO(65,"优效","youxiao",62),
	YINGNA(66,"赢纳","yingna",63),
	WANGXIANG(67,"旺翔","wangxiang",64),
	WANGYUE(68,"玩悦","wangyue",65),
	JICHENG(69,"极程","jicheng",66),
	YOUKEN(70,"优肯","youken",67),
	YILEYUN(71,"易乐云","yileyun",68),
	JINGZHUN(72,"鲸准","jingzhun",69),
	QIDIAN(73,"奇点","qidian",70),
	
	//TODO 可支持一次请求多条广告
	BORUI(74, "博瑞", "borui", 71),
	
	//TODO 确认是否可支持一次请求多条广告
	PAIRUI(75, "派瑞", "pairui", 72),
	
	BOYU(76, "博娱", "boyu", 73),
	RUISHI(77, "瑞狮", "ruishi", 74),
	PAERJIATE(78, "帕尔加特", "paerjiate", 75),
	DIANGUAN(79, "点冠", "dianguan", 76),
	YIDIANZX(80, "一点资讯", "yidianzx", 77),
	
	WEIYU(81, "维誉", "weiyu", 78),

	WULI(82, "唔哩", "wuli", 79),
	XUANYIN(83, "铉音", "xuanyin", 80),
	MAIJIKE(84, "迈吉客", "maijike", 81),
	XUNFA(85, "讯发", "xunfa", 82),
	TIANMEI(86, "天美", "tianmei", 83),
	;
	
	private final int value;

	private final String name;
	
	private final String abbrev;
	
	private final int id2adr;
	
	private JoinDSPEmu(int value, String name, String abbrev, int id2adr) {
		this.value = value;
		this.name = name;
		this.abbrev = abbrev;
		this.id2adr = id2adr;
	}

	public int getValue() {
		return value;
	}
	
	public String getName(){
		return name;
	}

	public String getAbbrev() {
		return abbrev;
	}
	
	public int getId2adr() {
		return id2adr;
	}

	public static JoinDSPEmu getDsp(int value){
		
		JoinDSPEmu[] dsps = values();
		for (JoinDSPEmu dsp : dsps) 
		{
			if(dsp.getValue() == value)
			{
				return dsp;
			}	
		}
		return null;
	}
	public static JoinDSPEmu getJoinDspByName(String value){
		
		JoinDSPEmu[] dsps = values();
		for (JoinDSPEmu dsp : dsps) 
		{
			if(dsp.getAbbrev().equals(value))//english name
			{
				return dsp;
			}	
		}
		return null;
	}
}
