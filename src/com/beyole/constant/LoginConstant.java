package com.beyole.constant;

public class LoginConstant {

	// 登录成功
	public static final int LOGIN_SUCCESS = 1001;
	// 登录失败|错误的用户名或密码
	public static final int LOGIN_ERROR_WITH_WRONGPASSWORD_OR_USERNAME = 1002;
	// 登录失败|网络异常
	public static final int LOGIN_ERROR_WITH_NETWORK_EXCEPTION = 1003;
	// 登录失败|不存在此用户
	public static final int LOGIN_ERROR_WITH_NO_SUCH_USER = 1004;
	// 登录成功|存在此用户
	public static final int LOGIN_SUCCESS_WITH_THIS_USER = 1005;
}
