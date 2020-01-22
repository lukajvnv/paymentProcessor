package com.project.pcc.dto;

public class Proba {
	
	private int broj;
	private String tekst;
	
	public int getBroj() {
		return broj;
	}
	public void setBroj(int broj) {
		this.broj = broj;
	}
	public String getTekst() {
		return tekst;
	}
	public void setTekst(String tekst) {
		this.tekst = tekst;
	}
	
	public Proba() {
		super();
	}
	
	public Proba(int broj, String tekst) {
		super();
		this.broj = broj;
		this.tekst = tekst;
	}
	

}
