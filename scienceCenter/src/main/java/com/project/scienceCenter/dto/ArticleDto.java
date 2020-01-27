package com.project.scienceCenter.dto;

import java.io.Serializable;
import java.util.Date;


public class ArticleDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2678019881969247829L;
	
	
	private Long articleId;
	private String articleTitle;
	private String articleAbstract;
	
	private Date publishingDate;
	
	private Float price;
	
	private String file;
	//file, doi, edition, status
	
	private String doi;

	public ArticleDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ArticleDto(Long articleId, String articleTitle, String articleAbstract, Date publishingDate, Float price,
			String file, String doi) {
		super();
		this.articleId = articleId;
		this.articleTitle = articleTitle;
		this.articleAbstract = articleAbstract;
		this.publishingDate = publishingDate;
		this.price = price;
		this.file = file;
		this.doi = doi;
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

	public String getDoi() {
		return doi;
	}

	public void setDoi(String doi) {
		this.doi = doi;
	}
	
	

}
