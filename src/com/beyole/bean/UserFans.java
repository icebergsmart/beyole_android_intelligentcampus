package com.beyole.bean;

public class UserFans {

	//用户粉丝关系编号
	private int id;
	//用户id
	private int userId;
	//粉丝id
	private int fansId;
	//粉丝名称
	private String fansUserName;
	//粉丝描述
	private String fansDescription;
	//用户粉丝关系 0：粉丝关注用户，用户未关注粉丝 1.双向关注
	private int userFansRelationship;
	//用户头像url
	private String fansImageUrl;

	public UserFans() {
	}

	public UserFans(int id, int userId, int fansId, String fansUserName, String fansDescription, int userFansRelationship) {
		this.id = id;
		this.userId = userId;
		this.fansId = fansId;
		this.fansUserName = fansUserName;
		this.fansDescription = fansDescription;
		this.userFansRelationship = userFansRelationship;
	}

	public UserFans(int id, int userId, int fansId, String fansUserName, String fansDescription, int userFansRelationship, String fansImageUrl) {
		this.id = id;
		this.userId = userId;
		this.fansId = fansId;
		this.fansUserName = fansUserName;
		this.fansDescription = fansDescription;
		this.userFansRelationship = userFansRelationship;
		this.fansImageUrl = fansImageUrl;
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

	public int getFansId() {
		return fansId;
	}

	public void setFansId(int fansId) {
		this.fansId = fansId;
	}

	public String getFansUserName() {
		return fansUserName;
	}

	public void setFansUserName(String fansUserName) {
		this.fansUserName = fansUserName;
	}

	public String getFansDescription() {
		return fansDescription;
	}

	public void setFansDescription(String fansDescription) {
		this.fansDescription = fansDescription;
	}

	public int getUserFansRelationship() {
		return userFansRelationship;
	}

	public void setUserFansRelationship(int userFansRelationship) {
		this.userFansRelationship = userFansRelationship;
	}

	public String getFansImageUrl() {
		return fansImageUrl;
	}

	public void setFansImageUrl(String fansImageUrl) {
		this.fansImageUrl = fansImageUrl;
	}

}
