package com.beyole.bean;

import java.io.Serializable;

public class Course1 implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String courseName; // �γ����
	private String teacher; // ��ʦ
	private int type; // ��������
	private String place; // �Ͽεص�
	private int courseWeek; // �Ͽ����ڼ�
	private int courseJs; // �Ͽεڼ���
	private String courseZc; // �Ͽ��ܴ�

	public Course1() {
	}

	public Course1(int id, String courseName, String teacher, int type, String place, int courseWeek, int courseJs, String courseZc) {
		this.id = id;
		this.courseName = courseName;
		this.teacher = teacher;
		this.type = type;
		this.place = place;
		this.courseWeek = courseWeek;
		this.courseJs = courseJs;
		this.courseZc = courseZc;
	}

	public String toTag() {
		return courseWeek + "" + courseJs;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public int getCourseWeek() {
		return courseWeek;
	}

	public void setCourseWeek(int courseWeek) {
		this.courseWeek = courseWeek;
	}

	public int getCourseJs() {
		return courseJs;
	}

	public void setCourseJs(int courseJs) {
		this.courseJs = courseJs;
	}

	public String getCourseZc() {
		return courseZc;
	}

	public void setCourseZc(String courseZc) {
		this.courseZc = courseZc;
	}

	public String toString() {
		return courseName + "/" + teacher;
	}
}
