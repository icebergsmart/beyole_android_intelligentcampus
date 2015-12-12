package com.beyole.bean;

import java.io.Serializable;

/**
 * 课程信息
 * 
 * @date 2015/12/12
 * @author Iceberg
 * 
 */
public class CourseAlbum implements Serializable{

	// 课程编号
	private int courseId;
	// 课程所属栏目id
	private int categoryId;
	// 课程名称
	private String courseName;
	// 课程描述图片
	private String courseImage;
	// 课程主讲教师
	private String teacherName;
	// 课程描述
	private String description;
	// 课程点击量
	private int read;
	// 课程是否通过审核
	private int examined;
	// 课程是否置顶
	private int top;
	// 课程是否热门
	private int hot;
	// 课程是否为推荐
	private int isRecommend;

	public CourseAlbum() {
	}

	public CourseAlbum(int courseId, int categoryId, String courseName, String courseImage, String teacherName, String description, int read, int examined, int top, int hot, int isRecommend) {
		this.courseId = courseId;
		this.categoryId = categoryId;
		this.courseName = courseName;
		this.courseImage = courseImage;
		this.teacherName = teacherName;
		this.description = description;
		this.read = read;
		this.examined = examined;
		this.top = top;
		this.hot = hot;
		this.isRecommend = isRecommend;
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

	public String getCourseImage() {
		return courseImage;
	}

	public void setCourseImage(String courseImage) {
		this.courseImage = courseImage;
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

	public int getRead() {
		return read;
	}

	public void setRead(int read) {
		this.read = read;
	}

	public int getExamined() {
		return examined;
	}

	public void setExamined(int examined) {
		this.examined = examined;
	}

	public int getTop() {
		return top;
	}

	public void setTop(int top) {
		this.top = top;
	}

	public int getHot() {
		return hot;
	}

	public void setHot(int hot) {
		this.hot = hot;
	}

	public int getIsRecommend() {
		return isRecommend;
	}

	public void setIsRecommend(int isRecommend) {
		this.isRecommend = isRecommend;
	}

}
