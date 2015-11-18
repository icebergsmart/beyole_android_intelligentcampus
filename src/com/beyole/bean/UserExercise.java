package com.beyole.bean;

public class UserExercise {

	// 编号
	private int id;
	// 活动编号
	private int exerciseId;
	// 活动名称
	private String exerciseName;
	// 活动描述
	private String exerciseDescription;
	// 活动状态 0：正在进行 1.已过期 2.已取消 3.审核不通过
	private int enabled;
	// 活动类型
	private int exerciseType;
	// 状态 0:参加 1.未参加
	private int status;

	public UserExercise() {
	}

	public UserExercise(int id, int exerciseId, String exerciseName, String exerciseDescription, int enabled, int exerciseType, int status) {
		this.id = id;
		this.exerciseId = exerciseId;
		this.exerciseName = exerciseName;
		this.exerciseDescription = exerciseDescription;
		this.enabled = enabled;
		this.exerciseType = exerciseType;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getExerciseName() {
		return exerciseName;
	}

	public void setExerciseName(String exerciseName) {
		this.exerciseName = exerciseName;
	}

	public String getExerciseDescription() {
		return exerciseDescription;
	}

	public void setExerciseDescription(String exerciseDescription) {
		this.exerciseDescription = exerciseDescription;
	}

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	public int getExerciseType() {
		return exerciseType;
	}

	public void setExerciseType(int exerciseType) {
		this.exerciseType = exerciseType;
	}

	public int getExerciseId() {
		return exerciseId;
	}

	public void setExerciseId(int exerciseId) {
		this.exerciseId = exerciseId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
