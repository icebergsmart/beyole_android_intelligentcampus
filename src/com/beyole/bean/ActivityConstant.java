package com.beyole.bean;

/**
 * 查询活动API返回结果集
 * 
 * @date 2015/12/3
 * @author Iceberg
 * 
 */
public class ActivityConstant {

	// 查询分页活动成功
	public static final int FIND_ACTIVITY_SUCCESS = 8001;
	// 查询分页活动成功||没有结果集
	public static final int FIND_ACTIVITY_SUCCESS_WITH_NO_RESULT = 8002;
	// 查询分页活动失败||服务器异常
	public static final int FIND_ACTIVITY_ERROR_WITH_EXCEPTION = 8003;
	// 查询分页活动失败
	public static final int FIND_ACTIVITY_ERROR = 8004;

}
