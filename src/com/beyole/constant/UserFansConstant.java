package com.beyole.constant;

public class UserFansConstant {
	// 没有发现粉丝
	public static final int FIND_NO_FANS = 4001;
	// 成功查询粉丝
	public static final int FIND_FANS_SUCCESS = 4002;
	// 关注成功|用户关注粉丝
	public static final int USER_FOCUS_FANS_SUCCESS = 4003;
	// 关注失败|用户关注粉丝
	public static final int USER_FOCUS_FANS_FAILURE = 4004;
	// 关注失败|用户关注粉丝 系统处理异常
	public static final int USER_FOCUS_FANS_FAILURE_WITH_EXCEPTION = 4005;
	// 用户取消关注粉丝成功
	public static final int USER_CANCLE_FOCUS_FANS_SUCCESS = 4006;
	// 用户取消关注粉丝失败
	public static final int USER_CANCLE_FOCUS_FANS_FAILURE = 4007;
	// 用户取消关注粉丝失败|系统处理异常
	public static final int USER_CANCLE_FOCUS_FANS_FAILURE_WITH_EXCEPTION = 4008;
	// 关系|粉丝关注用户
	public static final int FANS_FOCUS_USER = 1;
	// 关系|粉丝用户相互关注
	public static final int FANS_AND_USER_FOCUS_EACHOTHER = 0;
}
