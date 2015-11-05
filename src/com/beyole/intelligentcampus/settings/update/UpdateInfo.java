package com.beyole.intelligentcampus.settings.update;

/**
 * 更新信息
 * 
 * @author Iceberg
 * 
 */
public class UpdateInfo {

	// 获取远程更新版本
	private String version;
	// 获取版本更新日期
	private String date;
	// 获取版本更新描述
	private String description;
	// 获取版本更新地址
	private String url;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
