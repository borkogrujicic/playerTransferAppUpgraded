package com.ftninformatika.jwd.web.dto;


public class IgracDTO {
	
	private Long id;
	private String ime;
	private String pozicija;
	
	
	private int brojDresa;
	private String datum;
	
	private boolean naProdaju;
	private KlubDTO klub;
	
	
	public IgracDTO() {
		super();
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getIme() {
		return ime;
	}


	public void setIme(String ime) {
		this.ime = ime;
	}


	public String getPozicija() {
		return pozicija;
	}


	public void setPozicija(String pozicija) {
		this.pozicija = pozicija;
	}


	public int getBrojDresa() {
		return brojDresa;
	}


	public void setBrojDresa(int brojDresa) {
		this.brojDresa = brojDresa;
	}


	public String getDatum() {
		return datum;
	}


	public void setDatum(String datum) {
		this.datum = datum;
	}


	public boolean isNaProdaju() {
		return naProdaju;
	}


	public void setNaProdaju(boolean naProdaju) {
		this.naProdaju = naProdaju;
	}


	public KlubDTO getKlub() {
		return klub;
	}


	public void setKlub(KlubDTO klub) {
		this.klub = klub;
	}
	
	
	
	

}
