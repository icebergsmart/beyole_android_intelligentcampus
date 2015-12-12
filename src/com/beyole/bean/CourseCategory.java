package com.beyole.bean;

import java.util.List;

public class CourseCategory {

	// 编号
	private int id;
	// 栏目id
	private int categoryId;
	// 栏目子目录信息
	private List<AllCourseCategory> subCategoryInfo;
	// 栏目信息
	private String categoryName;
	// 栏目图标地址
	private String categoryIconUrl;

	public CourseCategory() {
	}

	public CourseCategory(int id, int categoryId, List<AllCourseCategory> subCategoryInfo, String categoryName, String categoryIconUrl) {
		this.id = id;
		this.categoryId = categoryId;
		this.subCategoryInfo = subCategoryInfo;
		this.categoryName = categoryName;
		this.categoryIconUrl = categoryIconUrl;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public List<AllCourseCategory> getSubCategoryInfo() {
		return subCategoryInfo;
	}

	public void setSubCategoryInfo(List<AllCourseCategory> subCategoryInfo) {
		this.subCategoryInfo = subCategoryInfo;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryIconUrl() {
		return categoryIconUrl;
	}

	public void setCategoryIconUrl(String categoryIconUrl) {
		this.categoryIconUrl = categoryIconUrl;
	}

}
