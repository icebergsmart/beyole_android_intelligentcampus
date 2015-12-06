package com.beyole.constant;

/**
 * 存储项目中用到的常量信息
 * 
 * @author Iceberg
 * 
 */
public class Constant {

	// 远程服务器地址
	public static final String REMOTESERVER = "http://192.168.1.107";

	// 远程服务器端口
	public static final String REMOTESERVERPORT = "8080";

	// 远程项目名称
	public static final String REMOTESERVERPROJECTNAME = "IntelligentCampus";

	public static final String REMOTE_UPDATE_APK_URL = REMOTESERVER + ":" + REMOTESERVERPORT + "/" + REMOTESERVERPROJECTNAME + "/update/version.xml";
	// 图片存放地址
	public static final String REMOTE_SMALL_IMAGE_URL = REMOTESERVER + ":" + REMOTESERVERPORT + "/" + REMOTESERVERPROJECTNAME + "/images/";
	// 百度定位AK
	public static final String BAIDU_LOCATION_AK_KEY = "4bW3GNTvqLLkcwL8qcBSmKck";
	// 百度天气AK
	public static final String BAIDU_WEATHER_AK_KEY = "V83jCerVdt5fZN8sFltbLtH4";
}
