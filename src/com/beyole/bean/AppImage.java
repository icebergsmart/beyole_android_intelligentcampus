package com.beyole.bean;

/**
 * app轮播图片实体类
 * 
 * @date 2016/01/11
 * @author Iceberg
 * 
 */
public class AppImage {
	private int id;
	private String imageUrl;
	private int orderSq;

	public AppImage() {
	}

	public AppImage(String imageUrl, int orderSq) {
		this.imageUrl = imageUrl;
		this.orderSq = orderSq;
	}

	public AppImage(int id, String imageUrl, int orderSq) {
		this.id = id;
		this.imageUrl = imageUrl;
		this.orderSq = orderSq;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public int getOrderSq() {
		return orderSq;
	}

	public void setOrderSq(int orderSq) {
		this.orderSq = orderSq;
	}

}
