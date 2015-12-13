package com.beyole.bean;

/**
 * 教室实体类
 * 
 * @date 2015/12/13
 * @author Iceberg
 * 
 */
public class Classroom {

	// 教室编号
	private int classroomId;
	// 教室名称
	private String classroomName;
	// 教室描述
	private String classroomDescription;
	// 教室所在校区(0:老校区 1:新校区)
	private int classroomLocation;
	// 教室时间段(0-15)
	private int duringIndex;
	// 教室使用状态 0:空闲 1.正在使用
	private int usingStatus;

	public Classroom() {
	}

	public Classroom(int classroomId, String classroomName, String classroomDescription, int classroomLocation, int duringIndex, int usingStatus) {
		this.classroomId = classroomId;
		this.classroomName = classroomName;
		this.classroomDescription = classroomDescription;
		this.classroomLocation = classroomLocation;
		this.duringIndex = duringIndex;
		this.usingStatus = usingStatus;
	}

	public int getClassroomId() {
		return classroomId;
	}

	public void setClassroomId(int classroomId) {
		this.classroomId = classroomId;
	}

	public String getClassroomName() {
		return classroomName;
	}

	public void setClassroomName(String classroomName) {
		this.classroomName = classroomName;
	}

	public String getClassroomDescription() {
		return classroomDescription;
	}

	public void setClassroomDescription(String classroomDescription) {
		this.classroomDescription = classroomDescription;
	}

	public int getClassroomLocation() {
		return classroomLocation;
	}

	public void setClassroomLocation(int classroomLocation) {
		this.classroomLocation = classroomLocation;
	}

	public int getDuringIndex() {
		return duringIndex;
	}

	public void setDuringIndex(int duringIndex) {
		this.duringIndex = duringIndex;
	}

	public int getUsingStatus() {
		return usingStatus;
	}

	public void setUsingStatus(int usingStatus) {
		this.usingStatus = usingStatus;
	}

}
