package com.beyole.bean;

public class Course {

	// 编号
	private int id;
	// 课程id
	private int courseId;
	// 课程类别
	private int categoryId;
	// 视频名称
	private String courseName;
	// 视频描述图片
	private String courseImageUrl;
	// 视频主讲教师
	private String teacherName;
	// 课程描述
	private String description;

	public Course() {
	}

	public Course(int id, int courseId, int categoryId, String courseName, String courseImageUrl, String teacherName, String description) {
		this.id = id;
		this.courseId = courseId;
		this.categoryId = categoryId;
		this.courseName = courseName;
		this.courseImageUrl = courseImageUrl;
		this.teacherName = teacherName;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseImageUrl() {
		return courseImageUrl;
	}

	public void setCourseImageUrl(String courseImageUrl) {
		this.courseImageUrl = courseImageUrl;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
