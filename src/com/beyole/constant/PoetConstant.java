package com.beyole.constant;

/**
 * 一行诗操作返回结果集
 * 
 * @date 2015/12/16
 * @author Iceberg
 * 
 */
public class PoetConstant {
	// 查询一行诗成功
	public static final int QUERY_FOR_POET_SUCCESS = 22001;
	// 查询一行诗成功|没有结果集
	public static final int QUERY_FOR_POET_SUCCESS_WITHOUT_RESULT = 22002;
	// 查询一行诗失败|操作异常
	public static final int QUERY_FOR_POET_ERROR = 22003;
	// 查询一行诗失败|服务器异常
	public static final int QUERY_FOR_POET_ERROR_WITH_EXCEPTION = 22004;
}
