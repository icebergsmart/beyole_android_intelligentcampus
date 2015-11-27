package com.beyole.bean;

import java.io.Serializable;

public class UserLoss implements Serializable {

	// 编号
	private int id;
	// 发布人
	private String userName;
	// 发布时间
	private String deliveryDate;
	// 用户头像
	private String userImageUrl;
	// 发布丢失物品图片
	private String goodsImageUrl;
	// 丢失物品信息
	private String content;
	// 是否置顶
	private int top;
	// 该发布是否结束
	private int finished;
	// 联系人
	private String contact;
	// 联系方式
	private String contactInfo;

	public UserLoss() {
	}

	public UserLoss(int id, String userName, String deliveryDate, String userImageUrl, String goodsImageUrl, String content, int top, int finished, String contact, String contactInfo) {
		this.id = id;
		this.userName = userName;
		this.deliveryDate = deliveryDate;
		this.userImageUrl = userImageUrl;
		this.goodsImageUrl = goodsImageUrl;
		this.content = content;
		this.top = top;
		this.finished = finished;
		this.contact = contact;
		this.contactInfo = contactInfo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getUserImageUrl() {
		return userImageUrl;
	}

	public void setUserImageUrl(String userImageUrl) {
		this.userImageUrl = userImageUrl;
	}

	public String getGoodsImageUrl() {
		return goodsImageUrl;
	}

	public void setGoodsImageUrl(String goodsImageUrl) {
		this.goodsImageUrl = goodsImageUrl;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getTop() {
		return top;
	}

	public void setTop(int top) {
		this.top = top;
	}

	public int getFinished() {
		return finished;
	}

	public void setFinished(int finished) {
		this.finished = finished;
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

}
