package com.beyole.bean;

import java.io.Serializable;

public class CourseCategoryItem implements Serializable{
	// 栏目id
	private int categoryId;
	// 父栏目id
	private int parentCategoryId;
	// 栏目名称
	private String courseCategoryName;
	// 是否为推荐栏目
	private int isRecommend;

	public CourseCategoryItem() {
	}

	public CourseCategoryItem(int categoryId, int parentCategoryId, String courseCategoryName, int isRecommend) {
		this.categoryId = categoryId;
		this.parentCategoryId = parentCategoryId;
		this.courseCategoryName = courseCategoryName;
		this.isRecommend = isRecommend;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getParentCategoryId() {
		return parentCategoryId;
	}

	public void setParentCategoryId(int parentCategoryId) {
		this.parentCategoryId = parentCategoryId;
	}

	public String getCourseCategoryName() {
		return courseCategoryName;
	}

	public void setCourseCategoryName(String courseCategoryName) {
		this.courseCategoryName = courseCategoryName;
	}

	public int getIsRecommend() {
		return isRecommend;
	}

	public void setIsRecommend(int isRecommend) {
		this.isRecommend = isRecommend;
	}

}
