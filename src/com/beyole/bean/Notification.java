package com.beyole.bean;

public class Notification {

	private int notificationId;
	private int notificationUserId;
	private String notificationContent;
	private int notificationType;
	private String remark;
	private String otherInfo;

	public Notification() {
	}

	public Notification(int notificationId, int notificationUserId, String notificationContent, int notificationType, String remark, String otherInfo) {
		this.notificationId = notificationId;
		this.notificationUserId = notificationUserId;
		this.notificationContent = notificationContent;
		this.notificationType = notificationType;
		this.remark = remark;
		this.otherInfo = otherInfo;
	}

	public int getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(int notificationId) {
		this.notificationId = notificationId;
	}

	public int getNotificationUserId() {
		return notificationUserId;
	}

	public void setNotificationUserId(int notificationUserId) {
		this.notificationUserId = notificationUserId;
	}

	public String getNotificationContent() {
		return notificationContent;
	}

	public void setNotificationContent(String notificationContent) {
		this.notificationContent = notificationContent;
	}

	public int getNotificationType() {
		return notificationType;
	}

	public void setNotificationType(int notificationType) {
		this.notificationType = notificationType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOtherInfo() {
		return otherInfo;
	}

	public void setOtherInfo(String otherInfo) {
		this.otherInfo = otherInfo;
	}

}
