package com.beyole.bean;

public class AllCourseCategory {

	private int id;
	private String categoryName;
	private int categoryId;

	public AllCourseCategory() {
	}

	public AllCourseCategory(int id, String categoryName, int categoryId) {
		this.id = id;
		this.categoryName = categoryName;
		this.categoryId = categoryId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

}
