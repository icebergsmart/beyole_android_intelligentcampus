package com.beyole.bean;

import java.io.Serializable;

public class UserRecruit implements Serializable {

	// 编号
	private int id;
	// 发布兼职招聘信息user编号
	private int userId;
	// 发布信息用户名
	private String userName;
	// 发布信息用户头像
	private String userImage;
	// 兼职招聘信息图片
	private String goodsImage;
	// 兼职招聘信息发布时间
	private String deliveryDate;
	// 兼职招聘信息是否结束
	private int finished;
	// 兼职招聘具体信息
	private String content;
	// 兼职招聘具体联系人
	private String contact;
	// 兼职招聘具体联系方式
	private String contactInfo;
	// 兼职招聘信息是否置顶
	private int top;

	public UserRecruit() {
	}

	public UserRecruit(int userId, String userName, String userImage, String goodsImage, String deliveryDate, int finished, String content, String contact, String contactInfo, int top) {
		this.userId = userId;
		this.userName = userName;
		this.userImage = userImage;
		this.goodsImage = goodsImage;
		this.deliveryDate = deliveryDate;
		this.finished = finished;
		this.content = content;
		this.contact = contact;
		this.contactInfo = contactInfo;
		this.top = top;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserImage() {
		return userImage;
	}

	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}

	public String getGoodsImage() {
		return goodsImage;
	}

	public void setGoodsImage(String goodsImage) {
		this.goodsImage = goodsImage;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public int getFinished() {
		return finished;
	}

	public void setFinished(int finished) {
		this.finished = finished;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getContactInfo() {
		return contactInfo;
	}

	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}

	public int getTop() {
		return top;
	}

	public void setTop(int top) {
		this.top = top;
	}

}
