package com.beyole.constant;

public class UserConstant {

	// 查询用户成功
	public static final int QUERY_FOR_USER_SUCCESS = 9001;
	// 查询用户失败|不存在此用户
	public static final int QUERY_FOR_USER_ERROR_WITH_NO_USER = 9002;
	// 查询用户失败|服务器异常
	public static final int QUERY_FOR_USER_ERROR_WITH_EXCEPTION = 9003;
	// 查询用户成功|此用户未通过审核
	public static final int QUERY_FOR_USER_SUCCESS_WITH_NO_CHECKED_OUT = 9004;
	// 查询用户成功|此用户被禁用
	public static final int QUERY_FOR_USER_SUCCESS_WITH_NOT_ENABLED = 9005;
	// 查询用户失败|服务器端工具初始化异常（通常为PrintWriter初始化失败）
	public static final int QUERY_FOR_USER_ERROR_WITH_INITIALIZE_EXCEPTION = 9006;
	// 通过用户名查询用户成功
	public static final int QUERY_USER_BY_USERNAME_SUCCESS = 9007;
	// 通过用户名查询用户失败|不存在此用户
	public static final int QUERY_USER_BY_USERNAME_ERROR_WITH_NO_SUCH_USER = 9008;
	// 通过用户名查询用户失败|系统异常
	public static final int QUERY_USER_BY_USERNAME_ERROR_WITH_SYSTEM_EXCEPTION = 9009;

}
