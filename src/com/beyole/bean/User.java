package com.beyole.bean;

public class User {

	private int userId;
	private String userName;
	private String password;
	private String userImagePhoto;
	private String userDescription;
	private String otherInfo;
	private String userStatus;
	private String createDate;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserImagePhoto() {
		return userImagePhoto;
	}

	public void setUserImagePhoto(String userImagePhoto) {
		this.userImagePhoto = userImagePhoto;
	}

	public String getUserDescription() {
		return userDescription;
	}

	public void setUserDescription(String userDescription) {
		this.userDescription = userDescription;
	}

	public String getOtherInfo() {
		return otherInfo;
	}

	public void setOtherInfo(String otherInfo) {
		this.otherInfo = otherInfo;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public User() {

	}

	public User(int userId, String userName, String password, String userImagePhoto, String userDescription, String otherInfo, String userStatus, String createDate) {
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.userImagePhoto = userImagePhoto;
		this.userDescription = userDescription;
		this.otherInfo = otherInfo;
		this.userStatus = userStatus;
		this.createDate = createDate;
	}

}
