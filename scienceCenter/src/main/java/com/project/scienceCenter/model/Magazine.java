package com.project.scienceCenter.model;

import java.util.Set;

public class Magazine {
	
	private Long magazineId;
	private String ISSN;
	private Set<ScienceArea> scienceAreas;
	private WayOfPayment wayOfPayment;
	
	private User chiefEditor;
	private Set<EditorByScienceArea> editorsByScienceArea;
	private Set<User> reviewers;
	
	private Set<MagazineEdition> magazineEditions;
	private boolean active;
	
	private Float membershipPrice;
	
	//zbog KP
	private Long sellerIdentifier;

}
