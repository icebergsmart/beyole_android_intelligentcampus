package com.beyole.constant;

/**
 * 用户通知操作返回结果集
 * 
 * @date 2015/12/17
 * @author Iceberg
 * 
 */
public class UserNotificationConstant {
	// 根据用户id查询通知成功
	public static final int QUERY_FOR_INFORMATION_BY_USERID_SUCCESS = 23001;
	// 根据用户id查询通知成功|没有结果集
	public static final int QUERY_FOR_INFORMATION_BY_USERID_SUCCESS_WITHOUT_RESULT = 23002;
	// 根据用户id查询通知失败|处理异常
	public static final int QUERY_FOR_INFORMATION_BY_USERID_ERROR = 23003;
	// 根据用户id查询通知失败|服务器异常
	public static final int QUERY_FOR_INFORMATION_BY_USERID_ERROR_WITH_EXCEPTION = 23004;
}
