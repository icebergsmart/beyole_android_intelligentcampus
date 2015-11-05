package com.beyole.constant;

/**
 * 存储项目中用到的常量信息
 * 
 * @author Iceberg
 * 
 */
public class Constant {

	// 主页新闻访问网址常量
	public static final String HOMEURL = "http://www.imooc.com/api/teacher?type=4&num=30";

	// 远程服务器地址
	public static final String REMOTESERVER = "http://10.255.24.138";

	// 远程服务器端口
	public static final String REMOTESERVERPORT = "8080";

	// 远程项目名称
	public static final String REMOTESERVERPROJECTNAME = "IntelligentCampus";

	public static final String REMOTE_UPDATE_APK_URL = REMOTESERVER + ":" + REMOTESERVERPORT + "/" + REMOTESERVERPROJECTNAME + "/update/version.xml";
}
