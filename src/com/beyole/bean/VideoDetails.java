package com.beyole.bean;

public class VideoDetails {

	private int id;
	private int videoId;
	private String videoImageUrl;
	private String videoName;
	private String teacherName;

	public VideoDetails() {
	}

	public VideoDetails(int id, int videoId, String videoImageUrl, String videoName, String teacherName) {
		this.id = id;
		this.videoId = videoId;
		this.videoImageUrl = videoImageUrl;
		this.videoName = videoName;
		this.teacherName = teacherName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getVideoId() {
		return videoId;
	}

	public void setVideoId(int videoId) {
		this.videoId = videoId;
	}

	public String getVideoImageUrl() {
		return videoImageUrl;
	}

	public void setVideoImageUrl(String videoImageUrl) {
		this.videoImageUrl = videoImageUrl;
	}

	public String getVideoName() {
		return videoName;
	}

	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

}
