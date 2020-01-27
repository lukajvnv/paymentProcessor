package com.project.scienceCenter.dto;

import java.util.Date;

import javax.persistence.Column;

public class MagazineEditionArticleDto {

	
	
	private Long articleId;
	private String articleTitle;
	private String articleAbstract;
	
	private Date publishingDate;
	
	private String doi;
	
	private Float price;
	
	private String file;

	public MagazineEditionArticleDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MagazineEditionArticleDto(Long articleId, String articleTitle, String articleAbstract, Date publishingDate,
			String doi, Float price, String file) {
		super();
		this.articleId = articleId;
		this.articleTitle = articleTitle;
		this.articleAbstract = articleAbstract;
		this.publishingDate = publishingDate;
		this.doi = doi;
		this.price = price;
		this.file = file;
	}

	public Long getArticleId() {
		return articleId;
	}

	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}

	public String getArticleTitle() {
		return articleTitle;
	}

	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}

	public String getArticleAbstract() {
		return articleAbstract;
	}

	public void setArticleAbstract(String articleAbstract) {
		this.articleAbstract = articleAbstract;
	}

	public Date getPublishingDate() {
		return publishingDate;
	}

	public void setPublishingDate(Date publishingDate) {
		this.publishingDate = publishingDate;
	}

	public String getDoi() {
		return doi;
	}

	public void setDoi(String doi) {
		this.doi = doi;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}
	
	
}
