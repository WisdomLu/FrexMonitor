package com.ocean.persist.app.dis.wanka;

import com.ocean.persist.app.dis.AppDisResponse;

public class WankaApp implements AppDisResponse{
	private String _package ;//": "org.mystock.client.usapp",
	private String 	name;//": "美股短线王",
	private String 	type;//": "soft",
	private String 	version_name;//": "1.0.7",
	private String 	version_code;//": 107,
	private String 	apk_size;//": 11212778,
	private String 	apk_md5;//": "25fc8e11fc361cc07fbcc6431de2247c",
	
	
	
	//pkg app
	private String icon_url;// 应用icon
	private String apk_url;// 下载地址
	private String download_cnt;// 应用的下载数
	private String screen_url ;//应用的截图
	private String update_info ;//应用版本更新说明
	private String update_time ;//应用更新版本时间
	private String desc;// 应用描述
	private String category_id ;//应用的分类
	private String category_name;// 应用分类名称
	private String developer ;//应用所属开发者
	private String editor_intro;// 应用一句话简介
	private String Star;// 应用评级
	public String get_package() {
		return _package;
	}
	public void set_package(String _package) {
		this._package = _package;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getVersion_name() {
		return version_name;
	}
	public void setVersion_name(String version_name) {
		this.version_name = version_name;
	}
	public String getVersion_code() {
		return version_code;
	}
	public void setVersion_code(String version_code) {
		this.version_code = version_code;
	}
	public String getApk_size() {
		return apk_size;
	}
	public void setApk_size(String apk_size) {
		this.apk_size = apk_size;
	}
	public String getApk_md5() {
		return apk_md5;
	}
	public void setApk_md5(String apk_md5) {
		this.apk_md5 = apk_md5;
	}
	public String getIcon_url() {
		return icon_url;
	}
	public String getApk_url() {
		return apk_url;
	}
	public String getDownload_cnt() {
		return download_cnt;
	}
	public String getScreen_url() {
		return screen_url;
	}
	public String getUpdate_info() {
		return update_info;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public String getDesc() {
		return desc;
	}
	public String getCategory_id() {
		return category_id;
	}
	public String getCategory_name() {
		return category_name;
	}
	public String getDeveloper() {
		return developer;
	}
	public String getEditor_intro() {
		return editor_intro;
	}
	public String getStar() {
		return Star;
	}
	public void setIcon_url(String icon_url) {
		this.icon_url = icon_url;
	}
	public void setApk_url(String apk_url) {
		this.apk_url = apk_url;
	}
	public void setDownload_cnt(String download_cnt) {
		this.download_cnt = download_cnt;
	}
	public void setScreen_url(String screen_url) {
		this.screen_url = screen_url;
	}
	public void setUpdate_info(String update_info) {
		this.update_info = update_info;
	}
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public void setDeveloper(String developer) {
		this.developer = developer;
	}
	public void setEditor_intro(String editor_intro) {
		this.editor_intro = editor_intro;
	}
	public void setStar(String star) {
		Star = star;
	}


}
