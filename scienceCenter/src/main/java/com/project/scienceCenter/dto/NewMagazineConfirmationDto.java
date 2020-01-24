package com.project.scienceCenter.dto;

public class NewMagazineConfirmationDto {
	
	private long scMagazineIdentifier;
	private long kpMagazineIdentifier;
	
	public NewMagazineConfirmationDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NewMagazineConfirmationDto(long scMagazineIdentifier, long kpMagazineIdentifier) {
		super();
		this.scMagazineIdentifier = scMagazineIdentifier;
		this.kpMagazineIdentifier = kpMagazineIdentifier;
	}

	public long getScMagazineIdentifier() {
		return scMagazineIdentifier;
	}

	public void setScMagazineIdentifier(long scMagazineIdentifier) {
		this.scMagazineIdentifier = scMagazineIdentifier;
	}

	public long getKpMagazineIdentifier() {
		return kpMagazineIdentifier;
	}

	public void setKpMagazineIdentifier(long kpMagazineIdentifier) {
		this.kpMagazineIdentifier = kpMagazineIdentifier;
	}
	
	

}
