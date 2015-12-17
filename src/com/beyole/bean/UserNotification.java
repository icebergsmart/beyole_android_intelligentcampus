package com.beyole.bean;

/**
 * 用户通知映射表
 * 
 * @date 2015/12/17
 * @author Iceberg
 * 
 */
public class UserNotification {

	// 编号
	private int id;
	// 接收通知的用户id
	private int recievedUserId;
	// 通知发布给用户的时间
	private String deliveryDate;
	// 消息发送者的头像url
	private String userImage;
	// 消息内容
	private String content;
	// 发布者用户名
	private String userName;
	// 发布者uerId
	private int sendUserId;

	public UserNotification() {
	}

	public UserNotification(int id, int recievedUserId, String deliveryDate, String userImage, String content, String userName, int sendUserId) {
		this.id = id;
		this.recievedUserId = recievedUserId;
		this.deliveryDate = deliveryDate;
		this.userImage = userImage;
		this.content = content;
		this.userName = userName;
		this.sendUserId = sendUserId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRecievedUserId() {
		return recievedUserId;
	}

	public void setRecievedUserId(int recievedUserId) {
		this.recievedUserId = recievedUserId;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getUserImage() {
		return userImage;
	}

	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getSendUserId() {
		return sendUserId;
	}

	public void setSendUserId(int sendUserId) {
		this.sendUserId = sendUserId;
	}

}
