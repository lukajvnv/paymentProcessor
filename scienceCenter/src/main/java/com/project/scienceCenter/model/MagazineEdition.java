package com.project.scienceCenter.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class MagazineEdition {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long magazineEditionId;
	
	@Column
	private Date publishingDate;
	
	@Column
	private Float magazineEditionPrice;
	
	@ManyToOne
	@JoinColumn(name = "magazine_id")
	private Magazine magazine;
	
	@OneToMany(mappedBy = "magazineEdition", fetch= FetchType.EAGER)
    private Set<Article> articles;
	
	

	public MagazineEdition() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public MagazineEdition(Long magazineEditionId, Date publishingDate, Float magazineEditionPrice, Magazine magazine,
			Set<Article> articles) {
		super();
		this.magazineEditionId = magazineEditionId;
		this.publishingDate = publishingDate;
		this.magazineEditionPrice = magazineEditionPrice;
		this.magazine = magazine;
		this.articles = articles;
	}



	public MagazineEdition(Long magazineEditionId, Date publishingDate, Float magazineEditionPrice, Magazine magazine) {
		super();
		this.magazineEditionId = magazineEditionId;
		this.publishingDate = publishingDate;
		this.magazineEditionPrice = magazineEditionPrice;
		this.magazine = magazine;
	}



	public MagazineEdition(Date publishingDate, Float magazineEditionPrice, Magazine magazine) {
		super();
		this.publishingDate = publishingDate;
		this.magazineEditionPrice = magazineEditionPrice;
		this.magazine = magazine;
	}



	public Long getMagazineEditionId() {
		return magazineEditionId;
	}

	public void setMagazineEditionId(Long magazineEditionId) {
		this.magazineEditionId = magazineEditionId;
	}

	public Date getPublishingDate() {
		return publishingDate;
	}

	public void setPublishingDate(Date publishingDate) {
		this.publishingDate = publishingDate;
	}

	public Float getMagazineEditionPrice() {
		return magazineEditionPrice;
	}

	public void setMagazineEditionPrice(Float magazineEditionPrice) {
		this.magazineEditionPrice = magazineEditionPrice;
	}

	public Magazine getMagazine() {
		return magazine;
	}

	public void setMagazine(Magazine magazine) {
		this.magazine = magazine;
	}

	public Set<Article> getArticles() {
		return articles;
	}

	public void setArticles(Set<Article> articles) {
		this.articles = articles;
	}
	
	

	
}
