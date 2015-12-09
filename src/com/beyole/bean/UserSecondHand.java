package com.beyole.bean;

import java.io.Serializable;

/**
 * 二手市场实体类
 * 
 * @date 2015/12/09
 * @author Iceberg
 * 
 */
public class UserSecondHand implements Serializable {

	/**
	 * 
	 */
	// 二手商品id
	private int secondhandId;
	// 发布二手商品用户名
	private String userName;
	// 发布时间
	private String deliveryDate;
	// 是否结束
	private int finished;
	// 二手商品描述
	private String content;
	// 发布人id
	private int userId;
	// 二手商品图片
	private String goodsImage;
	// 二手商品联系人
	private String contact;
	// 二手商品联系方式
	private String contactInfo;
	// 用户图片
	private String userImages;
	// 是否置顶
	private int top;

	public UserSecondHand() {
	}

	public UserSecondHand(int secondhandId, String userName, String deliveryDate, int finished, String content, int userId, String goodsImage, String contact, String contactInfo, String userImages, int top) {
		this.secondhandId = secondhandId;
		this.userName = userName;
		this.deliveryDate = deliveryDate;
		this.finished = finished;
		this.content = content;
		this.userId = userId;
		this.goodsImage = goodsImage;
		this.contact = contact;
		this.contactInfo = contactInfo;
		this.userImages = userImages;
		this.top = top;
	}

	public int getSecondhandId() {
		return secondhandId;
	}

	public void setSecondhandId(int secondhandId) {
		this.secondhandId = secondhandId;
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

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getGoodsImage() {
		return goodsImage;
	}

	public void setGoodsImage(String goodsImage) {
		this.goodsImage = goodsImage;
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

	public String getUserImages() {
		return userImages;
	}

	public void setUserImages(String userImages) {
		this.userImages = userImages;
	}

	public int getTop() {
		return top;
	}

	public void setTop(int top) {
		this.top = top;
	}

}
