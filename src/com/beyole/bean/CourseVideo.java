package com.beyole.bean;

import java.io.Serializable;

/**
 * 课程详细视频信息
 * 
 * @date 2015/12/12
 * @author Iceberg
 * 
 */
public class CourseVideo implements Serializable{

	private int videoId;
	private String videoName;
	private String videoUrl;
	private int courseId;
	private int read;
	private int examined;
	private int hot;
	private int top;
	private int canPlay;
	private int order;

	public CourseVideo() {
	}

	public CourseVideo(int videoId, String videoName, String videoUrl, int courseId, int read, int examined, int hot, int top, int canPlay, int order) {
		this.videoId = videoId;
		this.videoName = videoName;
		this.videoUrl = videoUrl;
		this.courseId = courseId;
		this.read = read;
		this.examined = examined;
		this.hot = hot;
		this.top = top;
		this.canPlay = canPlay;
		this.order = order;
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

	public int getRead() {
		return read;
	}

	public void setRead(int read) {
		this.read = read;
	}

	public int getExamined() {
		return examined;
	}

	public void setExamined(int examined) {
		this.examined = examined;
	}

	public int getHot() {
		return hot;
	}

	public void setHot(int hot) {
		this.hot = hot;
	}

	public int getTop() {
		return top;
	}

	public void setTop(int top) {
		this.top = top;
	}

	public int getCanPlay() {
		return canPlay;
	}

	public void setCanPlay(int canPlay) {
		this.canPlay = canPlay;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

}
