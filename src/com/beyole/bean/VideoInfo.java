package com.beyole.bean;

import java.io.Serializable;

public class VideoInfo implements Serializable{
	// 编号
	private int id;
	// 视频编号
	private int videoId;
	// 视频名称
	private String videoName;
	// 视频url
	private String videoUrl;
	// 视频所属课程
	private int courseId;

	public VideoInfo() {
	}

	public VideoInfo(int id, int videoId, String videoName, String videoUrl, int courseId) {
		this.id = id;
		this.videoId = videoId;
		this.videoName = videoName;
		this.videoUrl = videoUrl;
		this.courseId = courseId;
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

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

}
