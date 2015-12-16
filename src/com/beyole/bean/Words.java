package com.beyole.bean;

/**
 * 单词实体类
 * 
 * @date 2015/12/14
 * @author Iceberg
 * 
 */
public class Words {

	// 编号
	private int id;
	// 单词
	private String words;
	// 过去式
	private String pastTense;
	// 过去分词
	private String pastParticiples;
	// 现在分词
	private String presentParticiples;
	// 复数
	private String complex;
	// 释义
	private String meanings;
	// 例句
	private String example;

	public Words() {
	}

	public Words(int id, String words, String pastTense, String pastParticiples, String presentParticiples, String complex, String meanings, String example) {
		this.id = id;
		this.words = words;
		this.pastTense = pastTense;
		this.pastParticiples = pastParticiples;
		this.presentParticiples = presentParticiples;
		this.complex = complex;
		this.meanings = meanings;
		this.example = example;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getWords() {
		return words;
	}

	public void setWords(String words) {
		this.words = words;
	}

	public String getPastTense() {
		return pastTense;
	}

	public void setPastTense(String pastTense) {
		this.pastTense = pastTense;
	}

	public String getPastParticiples() {
		return pastParticiples;
	}

	public void setPastParticiples(String pastParticiples) {
		this.pastParticiples = pastParticiples;
	}

	public String getPresentParticiples() {
		return presentParticiples;
	}

	public void setPresentParticiples(String presentParticiples) {
		this.presentParticiples = presentParticiples;
	}

	public String getComplex() {
		return complex;
	}

	public void setComplex(String complex) {
		this.complex = complex;
	}

	public String getMeanings() {
		return meanings;
	}

	public void setMeanings(String meanings) {
		this.meanings = meanings;
	}

	public String getExample() {
		return example;
	}

	public void setExample(String example) {
		this.example = example;
	}

}
