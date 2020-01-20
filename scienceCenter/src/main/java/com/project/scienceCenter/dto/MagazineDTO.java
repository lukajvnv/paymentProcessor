package com.project.scienceCenter.dto;

import javax.persistence.Column;

import com.project.scienceCenter.model.Magazine;
import com.project.scienceCenter.model.WayOfPayment;

public class MagazineDTO {

	private Long magazineId;
	
	private String ISSN;

	private String name;

/*	@ManyToMany
	private Set<ScienceArea> scienceAreas;*/

	private WayOfPayment wayOfPayment;
	
/*	@ManyToMany
	private User chiefEditor;
/*
	private Set<EditorByScienceArea> editorsByScienceArea;

	private Set<User> reviewers;
	
	private Set<MagazineEdition> magazineEditions;*/

	private boolean active;
	//zbog KP -- id prodavca u KP-u ???

	private Long sellerIdentifier;
	
	private Double price;
	
	public MagazineDTO(Long magazineId, String iSSN, String name, WayOfPayment wayOfPayment, boolean active,
			Long sellerIdentifier, Double price) {
		super();
		this.magazineId = magazineId;
		ISSN = iSSN;
		this.name = name;
		this.wayOfPayment = wayOfPayment;
		this.active = active;
		this.sellerIdentifier = sellerIdentifier;
		this.price = price;
	}
	
	public MagazineDTO(Magazine m) {
		this.magazineId = m.getMagazineId();
		ISSN = m.getISSN();
		this.name = m.getName();
		this.wayOfPayment = m.getWayOfPayment();
		this.active = true;
		this.sellerIdentifier = m.getSellerIdentifier();
		this.price = m.getPrice();
	}
	
	public Long getMagazineId() {
		return magazineId;
	}
	public void setMagazineId(Long magazineId) {
		this.magazineId = magazineId;
	}
	public String getISSN() {
		return ISSN;
	}
	public void setISSN(String iSSN) {
		ISSN = iSSN;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public WayOfPayment getWayOfPayment() {
		return wayOfPayment;
	}
	public void setWayOfPayment(WayOfPayment wayOfPayment) {
		this.wayOfPayment = wayOfPayment;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public Long getSellerIdentifier() {
		return sellerIdentifier;
	}
	public void setSellerIdentifier(Long sellerIdentifier) {
		this.sellerIdentifier = sellerIdentifier;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	
	
	
}
