package com.ocean.persist.lbs.baidu.address;

import com.ocean.core.common.threadpool.AbstractInvokeParameter;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年8月7日 
      @version 1.0 
 */
public class AddrLocationParam  extends AbstractInvokeParameter{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1930411608856399432L;
	private String output;// 	否 	xml 	json或xml 	输出格式为json或者xml
	private String ret_coordtype; //	否 	无 	gcj02ll、bd09mc 	可选参数，添加后返回国测局经纬度坐标或百度米制坐标
	private String ak; //	是 	无 	E4805d16520de693a3fe707cdc962045 	用户申请注册的key，自v2开始参数修改为“ak”，之前版本参数为“key”
	private String sn; //	否 	无 		若用户所用ak的校验方式为sn校验时该参数必须。 （sn生成算法）
	private String callback; 	//否 	无 	callback=showLocation(JavaScript函数名) 	将json格式的返回值通过callback函数返回以实现jsonp功能 
	private String address; 	//是 	无 	北京市海淀区上地十街10号 	

/*	根据指定地址进行坐标的反定向解析，最多支持100个字节输入。

	可以输入三种样式的值，分别是：
	1、标准的地址信息，如北京市海淀区上地十街十号
	2、名胜古迹、标志性建筑物，如天安门，百度大厦
	3、支持“*路与*路交叉口”描述方式，如北一环路和阜阳路的交叉路口

	注意：后两种方式并不总是有返回结果，只有当地址库中存在该地址描述时才有返回。

	最多支持84个字节*/
	private String city; 	//否 	“北京市” 	“广州市” 	地址所在的城市名。用于指定上述地址所在的城市，当多个城市都有上述地址时，该参数起到过滤作用。 

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	public String getRet_coordtype() {
		return ret_coordtype;
	}

	public void setRet_coordtype(String ret_coordtype) {
		this.ret_coordtype = ret_coordtype;
	}

	public String getAk() {
		return ak;
	}

	public void setAk(String ak) {
		this.ak = ak;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getCallback() {
		return callback;
	}

	public void setCallback(String callback) {
		this.callback = callback;
	}


	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public boolean validate() {
		return false;
		// TODO Auto-generated method stub
		
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
