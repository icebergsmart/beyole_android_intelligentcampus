package com.beyole.constant;

public class RegisterConstant {

	// 注册成功
	public static final int REGISTER_SUCCESS_WITH_THIS_USER = 3001;
	// 注册失败|存在此用户名
	public static final int REGISTER_ERROR_WITH_EXIST_USERNAME = 3002;
	// 注册失败|网络异常
	public static final int REGISTER_ERROR_WITH_NETWORK_EXCEPTION = 3003;
	// 注册失败|非法的用户名
	public static final int REGISTER_ERROR_WITH_ILLEGAL_USERNAME = 3004;
	// 注册失败|非法的密码
	public static final int REGISTER_ERROR_WITH_ILLEGAL_PASSWORD = 3005;
	// 注册失败|其他系统异常
	public static final int REGISTER_ERROR_WITH_OTHER_EXCEPTION = 3006;
	// 注册失败|用户名或密码不能为空
	public static final int REGISTER_ERROR_WITH_EMPTY_USERNAME_OR_PASSWORD = 3007;

}
