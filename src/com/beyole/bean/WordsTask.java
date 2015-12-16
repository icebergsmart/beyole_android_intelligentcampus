package com.beyole.bean;

/**
 * 单词任务列表实体类
 * 
 * @date 2015/12/14
 * @author Iceberg
 * 
 */
public class WordsTask {

	// 编号
	private int taskId;
	// 任务名称
	private String taskName;
	// 任务等级（英语四级、英语六级、专业四级、专业八级）
	private int level;
	// 任务单词列表
	private String taskContent;
	// 是否完成
	private int finished;
	// 完成时间
	private String finishDate;
	// 排序
	private int order;

	public WordsTask() {
	}

	public WordsTask(int taskId, String taskName, int level, String taskContent) {
		this.taskId = taskId;
		this.taskName = taskName;
		this.level = level;
		this.taskContent = taskContent;
	}

	public WordsTask(int taskId, String taskName, int level, String taskContent, int finished, String finishDate, int order) {
		this.taskId = taskId;
		this.taskName = taskName;
		this.level = level;
		this.taskContent = taskContent;
		this.finished = finished;
		this.finishDate = finishDate;
		this.order = order;
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getTaskContent() {
		return taskContent;
	}

	public void setTaskContent(String taskContent) {
		this.taskContent = taskContent;
	}

	public int getFinished() {
		return finished;
	}

	public void setFinished(int finished) {
		this.finished = finished;
	}

	public String getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(String finishDate) {
		this.finishDate = finishDate;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

}
