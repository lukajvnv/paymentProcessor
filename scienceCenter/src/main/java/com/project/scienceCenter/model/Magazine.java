package com.project.scienceCenter.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Magazine {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long magazineId;
	@Column
	private String ISSN;
	
	@Column
	private String name;

/*	@ManyToMany
	private Set<ScienceArea> scienceAreas;*/

	@Column
	private WayOfPayment wayOfPayment;
	
/*	@ManyToMany
	private User chiefEditor;
/*
	private Set<EditorByScienceArea> editorsByScienceArea;

	private Set<User> reviewers;
	
	private Set<MagazineEdition> magazineEditions;*/
	@Column
	private boolean active;
	//zbog KP -- id prodavca u KP-u ???
	@Column
	private Long sellerIdentifier;
	
	@Column
	private Double price;
	
	@OneToMany(mappedBy = "magazine", fetch = FetchType.EAGER)
	private Set<MagazineEdition> magazineEditions;
	

	public Magazine() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	public Magazine(Long magazineId, String iSSN, String name, WayOfPayment wayOfPayment, boolean active,
			Long sellerIdentifier) {
		super();
		this.magazineId = magazineId;
		this.ISSN = iSSN;
		this.name = name;
		this.wayOfPayment = wayOfPayment;
		this.active = active;
		this.sellerIdentifier = sellerIdentifier;
	}



public Magazine(Long magazineId, String iSSN, String name, WayOfPayment wayOfPayment, boolean active,
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

public Magazine(String iSSN, String name, WayOfPayment wayOfPayment, boolean active,
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



	public Magazine(Long magazineId, String iSSN, WayOfPayment wayOfPayment, boolean active, Long sellerIdentifier) {
		super();
		this.magazineId = magazineId;
		this.ISSN = iSSN;
		this.wayOfPayment = wayOfPayment;
		this.active = active;
		this.sellerIdentifier = sellerIdentifier;
	}



	public Magazine(Long magazineId, String iSSN, WayOfPayment wayOfPayment, boolean active) {
		super();
		this.magazineId = magazineId;
		ISSN = iSSN;
		this.wayOfPayment = wayOfPayment;
		this.active = active;
	}

	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public Long getSellerIdentifier() {
		return sellerIdentifier;
	}



	public void setSellerIdentifier(Long sellerIdentifier) {
		this.sellerIdentifier = sellerIdentifier;
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



	public Double getPrice() {
		return price;
	}



	public void setPrice(Double price) {
		this.price = price;
	}



	public Set<MagazineEdition> getMagazineEditions() {
		return magazineEditions;
	}



	public void setMagazineEditions(Set<MagazineEdition> magazineEditions) {
		this.magazineEditions = magazineEditions;
	}
	
	
	

}
