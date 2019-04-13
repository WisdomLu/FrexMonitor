package com.ocean.persist.lbs.baidu.address;
/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年8月8日 
      @version 1.0 
 */
public class AddrLocationResult {
	private AddrLocationPoint location 	;
	private int precise 	;//Int 	位置的附加信息，是否精确查找。1为精确查找，即准确打点；0为不精确，即模糊打点。
	private int confidence 	;//Int 	可信度，描述打点准确度
	private String level 	;//string 	地址类型 
	public AddrLocationPoint getLocation() {
		return location;
	}
	public void setLocation(AddrLocationPoint location) {
		this.location = location;
	}
	public int getPrecise() {
		return precise;
	}
	public void setPrecise(int precise) {
		this.precise = precise;
	}
	public int getConfidence() {
		return confidence;
	}
	public void setConfidence(int confidence) {
		this.confidence = confidence;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
}
