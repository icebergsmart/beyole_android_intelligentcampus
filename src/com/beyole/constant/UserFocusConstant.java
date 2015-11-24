package com.beyole.constant;

public class UserFocusConstant {
	// 没有发现关注的人
	public static final int FIND_NO_FOCUS = 6001;
	// 成功查询关注的人
	public static final int FIND_FOCUS_SUCCESS = 6002;
	// 用户取消关注另外一个用户成功
	public static final int USER_CANCLE_FOCUS_USER_SUCCESS = 6003;
	// 用户取消关注另外一个用户失败
	public static final int USER_CANCLE_FOCUS_USER_FAILURE = 6004;
	// 用户取消关注另外一个用户失败|系统处理异常
	public static final int USER_CANCLE_FOCUS_USER_FAILURE_WITH_EXCEPTION = 6005;
	// 查询关注的人失败
	public static final int FIND_FOCUS_ERROR = 6006;
	// 查询关注的人失败|系统异常
	public static final int FIND_FOCUS_ERROR_WITH_EXCEPTION = 6007;
}
