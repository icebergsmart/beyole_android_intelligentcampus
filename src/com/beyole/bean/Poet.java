package com.beyole.bean;

import java.sql.Timestamp;

public class Poet {

	private int id;
	private int userId;
	private String content;
	private Timestamp deliveryDate;
	private String imageUrl;
	private int like;
	private String otherInfo;
	private String remark;

	public Poet() {
	}

	public Poet(int id, int userId, String content, Timestamp deliveryDate, String imageUrl, int like, String otherInfo, String remark) {
		this.id = id;
		this.userId = userId;
		this.content = content;
		this.deliveryDate = deliveryDate;
		this.imageUrl = imageUrl;
		this.like = like;
		this.otherInfo = otherInfo;
		this.remark = remark;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Timestamp deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public int getLike() {
		return like;
	}

	public void setLike(int like) {
		this.like = like;
	}

	public String getOtherInfo() {
		return otherInfo;
	}

	public void setOtherInfo(String otherInfo) {
		this.otherInfo = otherInfo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
