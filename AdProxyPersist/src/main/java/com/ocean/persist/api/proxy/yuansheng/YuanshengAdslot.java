package com.ocean.persist.api.proxy.yuansheng;

import java.util.List;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2018年1月8日 
      @version 1.0 
 */
public class YuanshengAdslot {
	private List<YuanshengImag> image;//	Array of object	Y	图片，见4.1.9。
	private List<YuanshengWord> word;//	Array of object	N	广告语，见4.1.10。
	public List<YuanshengImag> getImage() {
		return image;
	}
	public void setImage(List<YuanshengImag> image) {
		this.image = image;
	}
	public List<YuanshengWord> getWord() {
		return word;
	}
	public void setWord(List<YuanshengWord> word) {
		this.word = word;
	}

}
