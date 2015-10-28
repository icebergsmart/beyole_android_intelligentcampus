package com.beyole.bean;

public class Item {
	private int imgId;
	private String title;

	public Item() {
		super();
	}

	public Item(int imgId, String title) {
		super();
		this.imgId = imgId;
		this.title = title;
	}

	public int getImgId() {
		return imgId;
	}

	public void setImgId(int imgId) {
		this.imgId = imgId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
