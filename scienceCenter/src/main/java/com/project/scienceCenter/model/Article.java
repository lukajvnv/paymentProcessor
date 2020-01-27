package com.project.scienceCenter.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Article {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long articleId;
	
	@Column
	private String articleTitle;
	
	@Column
	private String articleAbstract;
	
	@Column
	private Date publishingDate;
	
	@Column
	private byte[] file;
	
	@Column
	private String fileFormat;
	
//	@Column
//	private String file;
	
	@Column
	private String doi;
	
	@ManyToOne
	@JoinColumn(name = "magazine_edition_id")
	private MagazineEdition magazineEdition;
	
	@Column
	private Float articlePrice;

	public Article(String articleTitle, String articleAbstract, Date publishingDate, byte[] file, String fileFormat,
			String doi, MagazineEdition magazineEdition, Float articlePrice) {
		super();
		this.articleTitle = articleTitle;
		this.articleAbstract = articleAbstract;
		this.publishingDate = publishingDate;
		this.file = file;
		this.fileFormat = fileFormat;
		this.doi = doi;
		this.magazineEdition = magazineEdition;
		this.articlePrice = articlePrice;
	}

	public Article() {
		super();
		// TODO Auto-generated constructor stub
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

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	public String getFileFormat() {
		return fileFormat;
	}

	public void setFileFormat(String fileFormat) {
		this.fileFormat = fileFormat;
	}

	public String getDoi() {
		return doi;
	}

	public void setDoi(String doi) {
		this.doi = doi;
	}

	public MagazineEdition getMagazineEdition() {
		return magazineEdition;
	}

	public void setMagazineEdition(MagazineEdition magazineEdition) {
		this.magazineEdition = magazineEdition;
	}

	public Float getArticlePrice() {
		return articlePrice;
	}

	public void setArticlePrice(Float articlePrice) {
		this.articlePrice = articlePrice;
	}
	
	

}
