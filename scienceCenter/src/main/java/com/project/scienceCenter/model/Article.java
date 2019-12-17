package com.project.scienceCenter.model;

import java.util.Set;

public class Article {
	
	private Long articleId;
	private String articleAbstract;
	
	private User author;
	private Set<User> coAuthors;
	
	private Set<Term> keyTerms;
	private ScienceArea scienceArea;
	private byte[] pdfDoc;
	
	private ArticleStatus status;
	private String doi;
	
	private MagazineEdition magazineEdition;
	
	private Float articlePrice;

}
