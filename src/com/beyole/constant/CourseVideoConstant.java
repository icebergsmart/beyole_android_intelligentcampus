package com.beyole.constant;

/**
 * 课程详细视频返回结果集
 * 
 * @date 2015/12/12
 * @author Iceberg
 * 
 */
public class CourseVideoConstant {

	// 根据courseId查询视频列表成功
	public static final int QUERY_FOR_COURSE_VIDEO_BY_COURSEID_SUCCESS = 18001;
	// 根据courseId查询视频列表成功|没有结果集
	public static final int QUERY_FOR_COURSE_VIDEO_BY_COURSEID_SUCCESS_WITHOUT_RESULT = 18002;
	// 根据courseId查询视频列表失败|处理异常
	public static final int QUERY_FOR_COURSE_VIDEO_BY_COURSEID_ERROR = 18003;
	// 根据courseId查询视频列表失败|服务器异常
	public static final int QUERY_FOR_COURSE_VIDEO_BY_COURSEID_ERROR_WITH_EXCEPTION = 18004;
}
