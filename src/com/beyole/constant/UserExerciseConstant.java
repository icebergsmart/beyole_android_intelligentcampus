package com.beyole.constant;

public class UserExerciseConstant {
	// 查询活动成功
	public static final int FIND_EXERCISE_SUCCESS = 5001;
	// 查询活动失败
	public static final int FIND_EXERCISE_FAILURE = 5002;
	// 查询活动失败|系统异常
	public static final int FIND_EXERCISE_FAILURE_WITH_EXCEPTION = 5003;
	// 查询活动成功|未参加任何活动
	public static final int FIND_EXERCISE_SUCCESS_WITH_NO_RESULT = 5004;
	// 退出参与活动成功
	public static final int EXIT_EXERCISE_SUCCESS = 5005;
	// 退出参与活动失败|不存在此活动
	public static final int EXIT_EXERCISE_ERROR = 5006;
	// 退出参与活动失败|系统异常
	public static final int EXIT_EXERCISE_ERROR_WITH_EXCEPTION = 5007;
}
