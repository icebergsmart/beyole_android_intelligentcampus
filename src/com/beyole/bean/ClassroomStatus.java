package com.beyole.bean;

/**
 * 教室使用状况
 * 
 * @author Iceberg
 * 
 */
public class ClassroomStatus {

	// 编号
	private int id;
	// 教室编号
	private int classroomId;
	// 教室名称
	private String classroomName;
	// 教室状态 0:使用中 1:空闲中
	private int status;
	// 时间区间
	private String timeDuring;
	// 开始时间
	private String startTime;
	// 结束时间
	private String endTime;
	// 第几个时间段
	private int timeNumber;
	// 第几个教室
	private int classroomNumber;

	public ClassroomStatus() {
	}

	public ClassroomStatus(int id, int status, int timeNumber, int classroomNumber) {
		this.id = id;
		this.status = status;
		this.timeNumber = timeNumber;
		this.classroomNumber = classroomNumber;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getTimeDuring() {
		return timeDuring;
	}

	public void setTimeDuring(String timeDuring) {
		this.timeDuring = timeDuring;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public int getTimeNumber() {
		return timeNumber;
	}

	public void setTimeNumber(int timeNumber) {
		this.timeNumber = timeNumber;
	}

	public int getClassroomNumber() {
		return classroomNumber;
	}

	public void setClassroomNumber(int classroomNumber) {
		this.classroomNumber = classroomNumber;
	}

}
