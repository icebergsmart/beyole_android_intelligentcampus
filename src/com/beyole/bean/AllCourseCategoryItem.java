package com.beyole.bean;

import java.io.Serializable;
import java.util.List;

public class AllCourseCategoryItem implements Serializable {
	private int categoryId;
	private List<CourseCategoryItem> courseItems;
	private String categoryName;

	public AllCourseCategoryItem() {
	}

	public AllCourseCategoryItem(int categoryId, List<CourseCategoryItem> courseItems, String categoryName) {
		this.categoryId = categoryId;
		this.courseItems = courseItems;
		this.categoryName = categoryName;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public List<CourseCategoryItem> getCourseItems() {
		return courseItems;
	}

	public void setCourseItems(List<CourseCategoryItem> courseItems) {
		this.courseItems = courseItems;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

}
