package com.beyole.bean;

import java.io.Serializable;
import java.sql.Timestamp;

public class Article implements Serializable{

	// 文章id
	private int articleId;
	// 文章名称
	private String articleName;
	// 文章小图url
	private String articlePicSmall;
	// 文章大图url
	private String articlePicBig;
	// 文章描述
	private String articleDescription;
	// 文章点击量
	private int articleClicked = 0;
	// 文章内容
	private String articleContent;
	// 文章类型
	private int articleType;
	// 文章是否热门
	private int articleHot;
	// 文章是否置顶
	private int articleUp;
	// 文章发布时间
	private Timestamp articleDeliveryDate;
	// 文章是否允许评论
	private int canComment;

	public Article() {
	}

	public Article(int articleId, String articleName, String articlePicSmall, String articlePicBig, String articleDescription, int articleClicked, String articleContent, int articleType, int articleHot, int articleUp, Timestamp articleDeliveryDate, int canComment) {
		this.articleId = articleId;
		this.articleName = articleName;
		this.articlePicSmall = articlePicSmall;
		this.articlePicBig = articlePicBig;
		this.articleDescription = articleDescription;
		this.articleClicked = articleClicked;
		this.articleContent = articleContent;
		this.articleType = articleType;
		this.articleHot = articleHot;
		this.articleUp = articleUp;
		this.articleDeliveryDate = articleDeliveryDate;
		this.canComment = canComment;
	}

	public int getArticleId() {
		return articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

	public String getArticleName() {
		return articleName;
	}

	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}

	public String getArticlePicSmall() {
		return articlePicSmall;
	}

	public void setArticlePicSmall(String articlePicSmall) {
		this.articlePicSmall = articlePicSmall;
	}

	public String getArticlePicBig() {
		return articlePicBig;
	}

	public void setArticlePicBig(String articlePicBig) {
		this.articlePicBig = articlePicBig;
	}

	public String getArticleDescription() {
		return articleDescription;
	}

	public void setArticleDescription(String articleDescription) {
		this.articleDescription = articleDescription;
	}

	public int getArticleClicked() {
		return articleClicked;
	}

	public void setArticleClicked(int articleClicked) {
		this.articleClicked = articleClicked;
	}

	public String getArticleContent() {
		return articleContent;
	}

	public void setArticleContent(String articleContent) {
		this.articleContent = articleContent;
	}

	public int getArticleType() {
		return articleType;
	}

	public void setArticleType(int articleType) {
		this.articleType = articleType;
	}

	public int getArticleHot() {
		return articleHot;
	}

	public void setArticleHot(int articleHot) {
		this.articleHot = articleHot;
	}

	public int getArticleUp() {
		return articleUp;
	}

	public void setArticleUp(int articleUp) {
		this.articleUp = articleUp;
	}

	public Timestamp getArticleDeliveryDate() {
		return articleDeliveryDate;
	}

	public void setArticleDeliveryDate(Timestamp articleDeliveryDate) {
		this.articleDeliveryDate = articleDeliveryDate;
	}

	public int getCanComment() {
		return canComment;
	}

	public void setCanComment(int canComment) {
		this.canComment = canComment;
	}

}
