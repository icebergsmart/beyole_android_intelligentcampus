package com.beyole.constant;

/**
 * 失物招领查询返回结果集
 * 
 * @date 2015/12/08
 * @author Iceberg
 * 
 */
public class UserLossConstant {
	// 查询失物成功|分页
	public static final int QUERY_USERLOSS_SUCCESS = 12001;
	// 查询失物成功|没有失物信息
	public static final int QUERY_USERLOSS_SUCCESS_WITH_NO_RESULT = 12002;
	// 查询失物失败|处理异常
	public static final int QUERY_USERLOSS_ERROR_DEAL_FAILURE = 12003;
	// 查询失物失败|服务器异常
	public static final int QUERY_USERLOSS_ERROR_WITH_EXCEPTION = 12004;
}
