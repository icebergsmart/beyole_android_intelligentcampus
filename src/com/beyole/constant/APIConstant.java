package com.beyole.constant;

public class APIConstant {

	public static final String COMMONURL = Constant.REMOTESERVER + ":" + Constant.REMOTESERVERPORT + "/" + Constant.REMOTESERVERPROJECTNAME;
	// 登录访问接口
	public static final String LOGININTERFACE = COMMONURL + "/user/login.action";

	// 注册访问接口
	public static final String REGISTERINTERFACE = COMMONURL + "/user/register.action";
}
