package com.beyole.bean;

import java.sql.Timestamp;

public class UserTalk {

	// 编号
	private int id;
	// 用户头像
	private String userImage;
	// 用户名
	private String userName;
	// 发布时间
	private String publishDate;
	// 发布内容
	private String talkContent;
	// 赞
	private int praise;
	// 评论
	private int comment;
	// 转发
	private int postDelivery;
	// 状态
	private int status;
	// 设备
	private String device;
	// 其他信息
	private String otherInfo;

	
	public UserTalk() {
	}

	public UserTalk(int id, String userImage, String userName, String publishDate, String talkContent, int praise, int comment, int postDelivery, int status, String device, String otherInfo) {
		this.id = id;
		this.userImage = userImage;
		this.userName = userName;
		this.publishDate = publishDate;
		this.talkContent = talkContent;
		this.praise = praise;
		this.comment = comment;
		this.postDelivery = postDelivery;
		this.status = status;
		this.device = device;
		this.otherInfo = otherInfo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserImage() {
		return userImage;
	}

	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}

	public String getTalkContent() {
		return talkContent;
	}

	public void setTalkContent(String talkContent) {
		this.talkContent = talkContent;
	}

	public int getPraise() {
		return praise;
	}

	public void setPraise(int praise) {
		this.praise = praise;
	}

	public int getComment() {
		return comment;
	}

	public void setComment(int comment) {
		this.comment = comment;
	}

	public int getPostDelivery() {
		return postDelivery;
	}

	public void setPostDelivery(int postDelivery) {
		this.postDelivery = postDelivery;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public String getOtherInfo() {
		return otherInfo;
	}

	public void setOtherInfo(String otherInfo) {
		this.otherInfo = otherInfo;
	}

}
