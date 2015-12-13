package com.beyole.constant;

/**
 * 教室操作返回结果集
 * 
 * @date 2015/12/13
 * @author Iceberg
 * 
 */
public class ClassroomConstant {

	// 查询教室使用状态成功
	public static final int QUERY_FOR_CLASSROOM_SUCCESS = 20001;
	// 查询教室使用状态成功|没有结果集
	public static final int QUERY_FOR_CLASSROOM_SUCCESS_WITHOUT_RESULT = 20002;
	// 查询教室使用状态失败|处理异常
	public static final int QUERY_FOR_CLASSROOM_ERROR = 20003;
	// 查询教室使用状态失败|服务器异常
	public static final int QUERY_FOR_CLASSROOM_ERROR_WITH_EXCEPTION = 20004;

}
