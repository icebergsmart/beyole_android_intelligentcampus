package com.beyole.bean;

/**
 * 新闻实体类
 * 
 * @date 2015/12/04
 * @author Iceberg
 * 
 */
public class Information {

	// 编号
	private int id;
	// 新闻id
	private int newsId;
	// 新闻发布时间
	private String publishDate;
	// 新闻阅读量
	private int readCount;
	// 新闻作者
	private String author;
	// 发布新闻的user
	private String publishUser;
	// 发布新闻的用户id
	private int publishUserId;
	// 新闻标题
	private String newsTitle;
	// 新闻内容
	private String newsContent;
	// 新闻缩略图
	private String newsImages;
	// 新闻类型
	private String type;
	// 新闻是否允许评论 0:不允许评论 1.允许评论
	private int canComment;
	// 新闻是否置顶 0:置顶 1.不置顶
	private int isTop;
	// 新闻是否是热门新闻
	private int isHot;

	public Information() {
	}

	public Information(int id, String newsTitle, String newsContent, String newsImages) {
		this.id = id;
		this.newsTitle = newsTitle;
		this.newsContent = newsContent;
		this.newsImages = newsImages;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNewsId() {
		return newsId;
	}

	public void setNewsId(int newsId) {
		this.newsId = newsId;
	}

	public String getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}

	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublishUser() {
		return publishUser;
	}

	public void setPublishUser(String publishUser) {
		this.publishUser = publishUser;
	}

	public int getPublishUserId() {
		return publishUserId;
	}

	public void setPublishUserId(int publishUserId) {
		this.publishUserId = publishUserId;
	}

	public String getNewsTitle() {
		return newsTitle;
	}

	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}

	public String getNewsContent() {
		return newsContent;
	}

	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}

	public String getNewsImages() {
		return newsImages;
	}

	public void setNewsImages(String newsImages) {
		this.newsImages = newsImages;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getCanComment() {
		return canComment;
	}

	public void setCanComment(int canComment) {
		this.canComment = canComment;
	}

	public int getIsTop() {
		return isTop;
	}

	public void setIsTop(int isTop) {
		this.isTop = isTop;
	}

	public int getIsHot() {
		return isHot;
	}

	public void setIsHot(int isHot) {
		this.isHot = isHot;
	}

}
