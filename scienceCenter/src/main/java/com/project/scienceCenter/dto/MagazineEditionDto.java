package com.project.scienceCenter.dto;

import java.util.Date;

public class MagazineEditionDto {

	private Long magazineEditionId;
	
	
	private Date publishingDate;
	
	
	private Float magazineEditionPrice;


	public MagazineEditionDto() {
		super();
		// TODO Auto-generated constructor stub
	}


	public MagazineEditionDto(Long magazineEditionId, Date publishingDate, Float magazineEditionPrice) {
		super();
		this.magazineEditionId = magazineEditionId;
		this.publishingDate = publishingDate;
		this.magazineEditionPrice = magazineEditionPrice;
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
	
	
}
