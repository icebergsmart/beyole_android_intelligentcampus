package com.beyole.bean;

/**
 * 用户参加的活动实体类
 * 
 * @author Iceberg
 * 
 */
public class UserExercise {
	// 活动编号
	private int activityId;
	// 活动类型
	private int activityType;
	// 活动名称
	private String activityName;
	// 活动描述
	private String activityDescription;
	// 当前活动状态|活动是不是已经过期 0:表示正在进行中 1:已经过期 2.未通过审核
	private int activityStatus;

	public UserExercise() {
	}

	public UserExercise(int activityId, int activityType, String activityName, String activityDescription, int activityStatus) {
		this.activityId = activityId;
		this.activityType = activityType;
		this.activityName = activityName;
		this.activityDescription = activityDescription;
		this.activityStatus = activityStatus;
	}

	public int getActivityId() {
		return activityId;
	}

	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}

	public int getActivityType() {
		return activityType;
	}

	public void setActivityType(int activityType) {
		this.activityType = activityType;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getActivityDescription() {
		return activityDescription;
	}

	public void setActivityDescription(String activityDescription) {
		this.activityDescription = activityDescription;
	}

	public int getActivityStatus() {
		return activityStatus;
	}

	public void setActivityStatus(int activityStatus) {
		this.activityStatus = activityStatus;
	}

}
