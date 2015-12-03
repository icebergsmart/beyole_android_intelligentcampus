package com.beyole.bean;

import java.io.Serializable;

public class ExerciseInfo implements Serializable {

	// 编号
	private int id;
	// 活动名称
	private String exerciseName;
	// 活动发起人姓名
	private String chargeManName;
	// 活动发起人id
	private int chargeManUserId;
	// 活动发布时间
	private String exerciseDate;
	// 活动地点
	private String exerciseLocation;
	// 活动详情
	private String exerciseDetails;
	// 活动id
	private int exerciseId;
	// 活动描述图片
	private String exerciseImages;
	// 活动参与数
	private int participateCount;
	// 活动赞
	private int praiseCount;
	// 活动分享
	private int deliveryPostCount;
	// 活动性质
	private String exerciseType;
	// 活动是否过期 0：正在进行 1.已结束 2.未通过审核
	private int status;
	// 活动开始时间
	private String startDate;
	// 活动截止时间
	private String endDate;
	// 活动剩余天数
	private String restDay;

	public ExerciseInfo() {
	}

	public ExerciseInfo(String exerciseName, String chargeManName, String exerciseType, int status) {
		this.exerciseName = exerciseName;
		this.chargeManName = chargeManName;
		this.exerciseType = exerciseType;
		this.status = status;
	}

	public ExerciseInfo(int id, String exerciseName, String chargeManName, int chargeManUserId, String exerciseLocation, String exerciseDetails, int exerciseId, String exerciseImages, String exerciseType, int status, String startDate, String endDate, String restDay) {
		this.id = id;
		this.exerciseName = exerciseName;
		this.chargeManName = chargeManName;
		this.chargeManUserId = chargeManUserId;
		this.exerciseLocation = exerciseLocation;
		this.exerciseDetails = exerciseDetails;
		this.exerciseId = exerciseId;
		this.exerciseImages = exerciseImages;
		this.exerciseType = exerciseType;
		this.status = status;
		this.startDate = startDate;
		this.endDate = endDate;
		this.restDay = restDay;
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

	public String getChargeManName() {
		return chargeManName;
	}

	public void setChargeManName(String chargeManName) {
		this.chargeManName = chargeManName;
	}

	public int getChargeManUserId() {
		return chargeManUserId;
	}

	public void setChargeManUserId(int chargeManUserId) {
		this.chargeManUserId = chargeManUserId;
	}

	public String getExerciseDate() {
		return exerciseDate;
	}

	public void setExerciseDate(String exerciseDate) {
		this.exerciseDate = exerciseDate;
	}

	public String getExerciseLocation() {
		return exerciseLocation;
	}

	public void setExerciseLocation(String exerciseLocation) {
		this.exerciseLocation = exerciseLocation;
	}

	public String getExerciseDetails() {
		return exerciseDetails;
	}

	public void setExerciseDetails(String exerciseDetails) {
		this.exerciseDetails = exerciseDetails;
	}

	public int getExerciseId() {
		return exerciseId;
	}

	public void setExerciseId(int exerciseId) {
		this.exerciseId = exerciseId;
	}

	public String getExerciseImages() {
		return exerciseImages;
	}

	public void setExerciseImages(String exerciseImages) {
		this.exerciseImages = exerciseImages;
	}

	public int getParticipateCount() {
		return participateCount;
	}

	public void setParticipateCount(int participateCount) {
		this.participateCount = participateCount;
	}

	public int getPraiseCount() {
		return praiseCount;
	}

	public void setPraiseCount(int praiseCount) {
		this.praiseCount = praiseCount;
	}

	public int getDeliveryPostCount() {
		return deliveryPostCount;
	}

	public void setDeliveryPostCount(int deliveryPostCount) {
		this.deliveryPostCount = deliveryPostCount;
	}

	public String getExerciseType() {
		return exerciseType;
	}

	public void setExerciseType(String exerciseType) {
		this.exerciseType = exerciseType;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getRestDay() {
		return restDay;
	}

	public void setRestDay(String restDay) {
		this.restDay = restDay;
	}

}
