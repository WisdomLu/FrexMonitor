package com.ocean.persist.lbs.baidu.ip;
/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年8月7日 
      @version 1.0 
 */
public class IPLocationAddrDetail {

       private String city;//"长春市"
       private int city_code;// 53
       private String district;
       private String province; //"吉林省"
       private String street; 
       private String street_number;
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getCity_code() {
		return city_code;
	}
	public void setCity_code(int city_code) {
		this.city_code = city_code;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getStreet_number() {
		return street_number;
	}
	public void setStreet_number(String street_number) {
		this.street_number = street_number;
	}

}
