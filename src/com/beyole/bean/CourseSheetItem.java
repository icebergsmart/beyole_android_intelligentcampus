package com.beyole.bean;

public class CourseSheetItem {

	private int id;
	private int videoId;
	private String videoName;
	private String videoUrl;

	public CourseSheetItem() {
	}

	public CourseSheetItem(int id, int videoId, String videoName, String videoUrl) {
		this.id = id;
		this.videoId = videoId;
		this.videoName = videoName;
		this.videoUrl = videoUrl;
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

	public String getVideoName() {
		return videoName;
	}

	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

}
